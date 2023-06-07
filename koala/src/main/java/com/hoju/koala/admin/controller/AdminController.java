package com.hoju.koala.admin.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hoju.koala.admin.model.service.AdminService;
import com.hoju.koala.admin.model.vo.AllCount;
import com.hoju.koala.admin.model.vo.BlockIp;
import com.hoju.koala.admin.model.vo.CreateSetting;
import com.hoju.koala.admin.model.vo.Supporters;
import com.hoju.koala.board.model.vo.ErrorBoard;
import com.hoju.koala.common.model.vo.PageInfo;
import com.hoju.koala.common.template.Paging;
import com.hoju.koala.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private PageInfo page;
	private AllCount all;
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/main")
	public String adminMain(Model model) {
		all = allCount();
		if(all != null) {
			model.addAttribute("allCount", all);
			System.out.println(all);
			return "admin/admin";
		}else {
			return "common/nullPoint";
		}
	}
	
	// 전체 적인 총 게시글 수를 확인할 수 있는 메서드
	public AllCount allCount() {
		return adminService.selectAllCount();
	}
	
	@GetMapping("/supporters.list")
	// Paging class에서 currentPage가 0이면 1로 변경 해주기 때문에 디폴트 값을 설정할 필요가 없다.
	public ModelAndView adminSupportes(PageInfo p, ModelAndView mav) {
		page = Paging.getPageInfo(allCount().getSupporters(), p.getCurrentPage(), 5, 4);
		ArrayList<Supporters> supporters = adminService.selectSupporters(page);
//		// log 남기기
//		if(supporters == null) {
//			log.info("SelectSupporters is NullPoint");
//			return new ModelAndView("common/nullPoint");
//		}
		mav = new ModelAndView("admin/supportersList");
		mav.addObject("supporterList",supporters);
		mav.addObject("pi", page);
		
//		for(Supporters s : supporters) {
//			System.out.println(s);
//			System.out.println(s.getNickName()); // getter함수를 사용해서 모두 가져올 수 상속 받은 객체의 필드에 접근 가능
//		}
		return mav;
	}
	
	@GetMapping("/supporters.demote")
	public String adminSupportersDelete(String userId, Model model) {
		int result = adminService.deleteSupporter(userId);
		
		new Gson().toJson(String.valueOf(result));
		return "admin/supportersList"; 
	}

	
	@GetMapping("/waitingLibrary.list")
	public String adminCreateSetting(Model model) {
		ArrayList<CreateSetting> libraryList = adminService.selectCreateSetting();
		for(CreateSetting c : libraryList) {
			System.out.println(c);
		}
		return "";
	}
	
	@GetMapping("/errorboard.list")
	public String adminErrorBoard(PageInfo p, Model model) {
		page = Paging.getPageInfo(allCount().getSupporters(), p.getCurrentPage(), 10, 10);
		ArrayList<ErrorBoard> errorBoardList = adminService.selectErrorBoard(page);
		model.addAttribute("errorList", errorBoardList);
		model.addAttribute("pi", page);
		for(ErrorBoard e : errorBoardList) {
			System.out.println(e);
		}
		return "";
	}
	
	@GetMapping("/blockip.list")
	public String adminblockip(PageInfo p, Model model) {
		page = Paging.getPageInfo(allCount().getBlockIp(), p.getCurrentPage(), 10, 9);
		System.out.println(page);
		ArrayList<BlockIp> blockIpList = adminService.selectBlockIp(page);
		model.addAttribute("blackList", blockIpList);
		model.addAttribute("pi", page);
		for(BlockIp b : blockIpList) {
			System.out.println(b);
		}
		return "admin/blockIpList";
	}
	
	@GetMapping("/member.list")
	public String adminMemberList(PageInfo p, Model model) {
		page = Paging.getPageInfo(allCount().getSupporters(), p.getCurrentPage(), 10, 10);
		ArrayList<Member> memberList = adminService.selectMemberList(page);
		model.addAttribute("memberList", memberList);
		model.addAttribute("pi", page);
		for(Member m : memberList) {
			System.out.println(m);
		}
		return "";
	}
}
