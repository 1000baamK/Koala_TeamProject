package com.hoju.koala.admin.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoju.koala.admin.model.service.AdminService;
import com.hoju.koala.admin.model.vo.ModifyTeam;
import com.hoju.koala.admin.model.vo.SqlCloud;
import com.hoju.koala.admin.model.vo.SqlInvite;
import com.hoju.koala.admin.model.vo.Supporters;
import com.hoju.koala.member.model.vo.Member;
import com.hoju.koala.setting.model.vo.Setting;

@Controller
@RequestMapping("/together")
public class TestController {
	/*
	 * https://api.github.com/users/Dukbong/repos
	 * > repo 내역을 java단에게 list에 담는다.
	 * 객체 : repo이름 / 생성일 / 마지막 업데이트 일 / 마지막 푸시 일
	 * 
	 *  1. 게시판을 만든다.
	 *  2. 작은 깃허브를 만들꺼다.
	 * */
	
	// Supporters 게시판에서 

	@Autowired
	AdminService adminService;
	
	@GetMapping("/sqlCloud")
	public String sqlCloud(HttpSession session, Model model,
						   @RequestParam(value="teamNo", required = false, defaultValue = "0") int teamNo) {

		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		ArrayList<SqlCloud> teamList = adminService.selectTeam(userNo); 
		model.addAttribute("teamList", teamList); 
		
		if(teamNo != 0) {
			ArrayList<Member> memberList = adminService.selectTeamMember(teamNo);
			SqlCloud sqlInfo = adminService.selectSqlDate(teamNo);
			int creatorNo = adminService.selectCreator(teamNo);
			model.addAttribute("memberList", memberList);
			model.addAttribute("sqlInfo", sqlInfo);
			session.setAttribute("teamNo", teamNo);
			model.addAttribute("teamNo", teamNo);
			model.addAttribute("creatorNo",creatorNo);
		}
		return "fun/test";
	}
	
	@PostMapping("/sqlSave")
	@ResponseBody
	public int sqlSave(int teamNo, String sqlContent) {
		SqlCloud saveSql = SqlCloud.builder().teamNo(teamNo).sqlContent(sqlContent).build();
		return adminService.updateTeamSql(saveSql);
	}
	
	@GetMapping("/ShowcreateTeam")
	public String showCreatTeam(HttpServletRequest request, Model model) {
		Supporters owner = adminService.selectMemberDetailInfo(((Member)request.getSession().getAttribute("loginUser")).getUserId());
		model.addAttribute("owner", owner);
		return "fun/createTeam";
	}
	
	@GetMapping("/searchMember")
	@ResponseBody
	public ArrayList<Member> searchMember(String text) {
		ArrayList<Member> memberList = adminService.searchMember(text);
		ArrayList<String> arrList = new ArrayList<>();
		for(Member m : memberList) {
			arrList.add(m.getUserId());
		}
		return memberList;
	}
	
	@GetMapping("/userInfo")
	@ResponseBody
	public Supporters userInfo(String userId) {
		Supporters info = adminService.selectMemberDetailInfo(userId);
		return info;
	}
	
	@GetMapping("/createTeam")
	@ResponseBody
	public int createTeam(String team, String teamName) {
		String[] arr = team.split(",");
		SqlCloud sql = SqlCloud.builder().teamName(teamName).sqlContent(" ").creatorNo(Integer.parseInt(arr[0])).build();
		int createTeam = adminService.insertSQLteam(sql); // teamCreate
		int insertMember = 0;
		if(createTeam > 0) {
			for(int i = 0; i < arr.length; i++) {
				SqlInvite sqlIn = SqlInvite.builder().creatorNo(Integer.parseInt(arr[0])).userNo(Integer.parseInt(arr[i])).build();
				insertMember = adminService.insertSQLteamMember(sqlIn);
			}
		}
		return insertMember;
	}
	
	@GetMapping("/modifyTeamMember")
	@ResponseBody
	public int modifyTeamMember(String team, String teamName) {
		String[] arr = team.split(",");
//		System.out.println("TeamName = " + teamName);
		int teamNo = adminService.selectTeamTeamNo(teamName);
//		System.out.println("TeamNO = " + teamNo);
		int updateMember = 0;
		// delete로 다 날리고 다시 등록한다.
		int deleteMember = adminService.deleteTeamMember(teamName);
		if(deleteMember > 0) {
			for(int i = 0; i < arr.length; i++) {
				SqlInvite sqlIn = SqlInvite.builder().teamNo(teamNo).creatorNo(Integer.parseInt(arr[0])).userNo(Integer.parseInt(arr[i])).build();
//				System.out.println(sqlIn);
				updateMember = adminService.updateSQLteamMember(sqlIn);
			}
		}
		return updateMember;
	}
	
	@PostMapping("/modifyTeam")
	public String modifyTeam(int teamNo, Model model) {
		// 필요한거 팀이름 / 오너아이디 / 유저 이름, 유저번호, 사진, 닉네임, 서포터즈 유무
		// sqlCloud, sqlinvite, member, supporter, profile 조인...
		// 30일날 하기
		ArrayList<ModifyTeam> mt = adminService.selectOneTeam(teamNo);
//		System.out.println(mt);
		model.addAttribute("modify", mt.get(0));
		ArrayList<String> arr = new ArrayList<>();
		ObjectMapper om = new ObjectMapper();
		for(int i = 1; i < mt.size(); i++) {
			try {
				String jsonTest = om.writeValueAsString(mt.get(i));
				arr.add(jsonTest);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("test",arr);
		return "fun/modifyTeam";
	}
	
	// team & member Delete Button Click
	@GetMapping("/teamDelete")
	@ResponseBody
	@Transactional
	public int teamDelete(int teamNo) {
		// 구분가능한 Team No로 분류 한다.
		int result1 = adminService.deleteTeam(teamNo);
		int result2 = adminService.deleteLastTeamInfo(teamNo);
		
		return result1*result2;
	}
	
	// member >> quit Team
	@GetMapping("/teamQuit")
	public String teamQuit(int teamNo, HttpSession session, HttpServletRequest request) {
		SqlInvite sql = SqlInvite.builder().teamNo(teamNo).userNo(((Member)session.getAttribute("loginUser")).getUserNo()).build();
		int result = adminService.teamQuit(sql);
//		System.out.println(result);
		if(result > 0) {
			return "redirect:sqlCloud"; 
		}else {			
			String referer = request.getHeader("Referer");
			return "redirect:"+ referer;
		}
	}
}