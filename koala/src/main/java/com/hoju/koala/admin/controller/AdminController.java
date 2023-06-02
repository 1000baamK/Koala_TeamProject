package com.hoju.koala.admin.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hoju.koala.admin.model.service.AdminService;
import com.hoju.koala.admin.model.vo.AllCount;
import com.hoju.koala.admin.model.vo.CreateSetting;
import com.hoju.koala.admin.model.vo.Supporters;
import com.hoju.koala.board.model.vo.ErrorBoard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/main")
	public String adminMain(Model model) {
		AllCount all = adminService.selectAllCount();
		if(all != null) {
			model.addAttribute("allCount", all);
			System.out.println(all);
			return "admin/admin";
		}else {
			return "common/nullPoint";
		}
	}
	
	@GetMapping("/supporters")
	public ModelAndView adminSupportes(ModelAndView mav) {
		ArrayList<Supporters> supporter = adminService.selectSupporters();
		// ���� �߻� �� �α� ���Ϸ� �����
		if(supporter == null) {
			log.info("SelectSupporters is NullPoint");
			return new ModelAndView("common/nullPoint");
		}
		mav = new ModelAndView("admin/supportersList");
		mav.addObject("supporterList",supporter);
		// view�� ���� �� Ȯ�� �۾�
		for(Supporters s : supporter) {
			System.out.println(s);
			System.out.println(s.getNickName()); // ��ӹ��� �޼��带 ����Ͽ� �θ� ��ü�� ��ҿ� �����ؼ� ���ϴ� ���� ������ �� �ִ�.
		}
		return mav;
	}
	
	@GetMapping("/waitingLibrary")
	public String adminWaitingLibrary(Model model) {
		ArrayList<CreateSetting> libraryList = adminService.selectCreateSetting();
		for(CreateSetting c : libraryList) {
			System.out.println(c);
		}
		return ""; // watingLibrary �� ������ ���� �� ����
	}
	
	@GetMapping("/errorboard")
	public String adminErrorBoard(Model model) {
		ArrayList<ErrorBoard> errorBoardList = adminService.selectErrorBoard();
		for(ErrorBoard e : errorBoardList) {
			System.out.println(e);
		}
		return ""; // errorBoard �� ������ ���� �� ����
	}
}
