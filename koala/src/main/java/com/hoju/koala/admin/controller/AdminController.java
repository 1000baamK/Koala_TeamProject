package com.hoju.koala.admin.controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hoju.koala.admin.model.service.AdminService;
import com.hoju.koala.admin.model.vo.AllCount;
import com.hoju.koala.admin.model.vo.BlockIp;
import com.hoju.koala.admin.model.vo.Client;
import com.hoju.koala.admin.model.vo.ErrorDivision;
import com.hoju.koala.admin.model.vo.IssuesAndError;
import com.hoju.koala.admin.model.vo.MemberSearch;
import com.hoju.koala.admin.model.vo.SettingDetail;
import com.hoju.koala.admin.model.vo.Supporters;
import com.hoju.koala.board.model.vo.ErrorSet;
import com.hoju.koala.common.model.vo.PageInfo;
import com.hoju.koala.common.template.Paging;
import com.hoju.koala.setting.model.vo.Setting;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

	private int i = 0;
	private PageInfo page;

	@Autowired
	Client client;

	@Autowired
	AdminService adminService;

	@GetMapping("/main")
	public String adminMain(Model model) {
		model.addAttribute("allCount", allCount());
		return "admin/admin";
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
		mav = new ModelAndView("admin/supportersList");
		mav.addObject("supporterList", supporters);
		mav.addObject("pi", page);
		return mav;
	}
	
	@GetMapping("/issuearea.list")
	public ModelAndView adminIssues(ModelAndView mav) {
		ArrayList<IssuesAndError> issues = adminService.selectIssues();
		mav.addObject("issues", issues);
		mav.setViewName("admin/issues");
		return mav;
	}

	@GetMapping("/supporters.demote")
	@ResponseBody
	public String adminSupportersDelete(String userId, Model model) {
		int result = adminService.deleteSupporter(userId);
		System.out.println("demote");
		return new Gson().toJson(String.valueOf(result));
	}

	@GetMapping("/waitingLibrary.list")
	public String waitingLibrary(Model model) {
		// 서포터즈가 작성한 라이브러리는 승인이 필요하다.
		// 승인이 필요한 라이브러리는 'w'의 상태값을 갖는다.
		ArrayList<Setting> list = adminService.selectWaitingLib();
		model.addAttribute("list", list);
		return "admin/waitingLibrary";
	}
	
	// 승인
	@GetMapping("/waitlibApprove")
	@ResponseBody
	public int waitlibApprove(int settingNo) {
		int result = adminService.approvelib(settingNo);
		return result;
	}
	
	// 거절
	@GetMapping("/waitlibdisapprove")
	@ResponseBody
	public int waitlibdisapprove(int settingNo) {
		int result = adminService.disapprovelib(settingNo);
		return result;
	}

	@GetMapping("/errorcheck.list")
	public String adminErrorBoard(Model model) {
		ArrayList<IssuesAndError> errorBoardList = adminService.selectErrorBoardCount(); // 해결 되지 않은 라이브러리, 개수, 설명을 가지고 있다.
		model.addAttribute("errorList", errorBoardList);
		return "admin/errorcheckList";
	}

	@GetMapping("/blockip.list")
	public String adminblockip(PageInfo p, Model model) {
		page = Paging.getPageInfo(allCount().getBlockIp(), p.getCurrentPage(), 10, 9);
		System.out.println("Class c를 매개변수로 " + page.test(this.getClass()));
		ArrayList<BlockIp> blockIpList = adminService.selectBlockIp(page);
		model.addAttribute("blackList", blockIpList);
		model.addAttribute("pi", page);
		return "admin/blockIpList";
	}

	@GetMapping("/blockip.clear")
	@ResponseBody
	public String adminblockipClear(String blackIp) {
		int result = adminService.updateblockClear(blackIp);
		return new Gson().toJson(String.valueOf(result));
	}

	@GetMapping("/blockip.action")
	@ResponseBody
	public String adminblockipAction(String blackIp) {
		int result = adminService.updateblockAction(blackIp);
		return new Gson().toJson(String.valueOf(result));
	}

	@GetMapping("/member.list")
	public String adminMemberList(PageInfo p, Model model,
								  @RequestParam(value = "searchQna", required=false, defaultValue="") String searchQna,
								  @RequestParam(value = "searchInput", required=false, defaultValue = "") String searchInput,
								  @RequestParam(value = "currentPage", required=false, defaultValue="1") int cp) {
		ArrayList<Supporters> memberList = null;
		MemberSearch ms = null;
		if(searchQna.equals("total") || searchQna.equals("")) {
			page = Paging.getPageInfo(allCount().getMember(), p.getCurrentPage(), 10, 10);
			memberList = adminService.selectMemberList(page); // 전체 리스트 조회
		}else {
			ms = MemberSearch.builder().searchQna(searchQna).searchInput(searchInput).build();
			page = Paging.getPageInfo(adminService.selectCountMemberCondition(ms), p.getCurrentPage(), 10, 10);
			memberList = adminService.selectMembercondition(page,ms); // 조건 검색 조회
			model.addAttribute("ms", ms);
		}
		model.addAttribute("memberList", memberList);
		model.addAttribute("pi", page);
		return "admin/memberList";
	}
	
	@GetMapping("/issuesDetail")
	public ModelAndView adminIssuesDetail(String settingTitle, ModelAndView mav) {
		System.out.println(settingTitle);
		ArrayList<SettingDetail> errorBoard = adminService.selectIssueDetail(settingTitle);
		System.out.println(errorBoard);
		mav.addObject("issueDetail", errorBoard);
		mav.setViewName("admin/issueDetail");
		return mav;
	}
	
	
	@GetMapping("/errorDetail")
	public String adminErrorDetail(String settingTitle, Model m,
								   @RequestParam(value = "page", required=false, defaultValue="1") int page) {
		ArrayList<ErrorSet> pickError = adminService.selectErrorDetail(settingTitle);
		try {			
			m.addAttribute("errorSet", pickError.get(page-1));
			m.addAttribute("size", pickError.size());
			return "admin/errorDetail";
		}catch(Exception e) {
			m.addAttribute("size", pickError.size());
			return "redirect:errorcheck.list";
		}
	}
	
	@GetMapping("/errorDivision")
	@ResponseBody
	public int adminErrorDivision(String tag, int bno) {
		System.out.println(tag);
		System.out.println(bno);
		ErrorDivision ed = ErrorDivision.builder().tagName(tag).boardNo(bno).build();
		int result = adminService.updateErrorType(ed);
		return result; 
	}
	
	// 모드를 쿠키로 저장할 메서드
	@GetMapping("mode.check")
	@ResponseBody
	public String displayMode(String mode, Model model, HttpServletResponse response) {
		Cookie cookie = new Cookie("mode", mode);
		cookie.setMaxAge(24 * 60 * 60 * 1000); // 24시간
		cookie.setPath("/");
		response.addCookie(cookie);
		return new Gson().toJson(cookie);
	}
	

}