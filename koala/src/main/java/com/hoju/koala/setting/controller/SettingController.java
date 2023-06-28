package com.hoju.koala.setting.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoju.koala.setting.model.service.SettingService;
import com.hoju.koala.setting.model.vo.Setting;

@RequestMapping("/setting")
@Controller
public class SettingController {

	@Autowired
	private SettingService stService;
	
	
	
	@GetMapping("/list")
	public String selectList(Model model) {
		
		//굴 리스트 조회
		ArrayList<Setting> slist = stService.selectSettingList();
		
		//버전정보도 추가해야함.
		
		if(!slist.isEmpty()) {
			
			model.addAttribute("slist", slist);
			
		}else {
			System.out.println("ss");
		}
		
		
		return "setting/ex";
	}
	
	//코드 작성 페이지 이동
	@RequestMapping("create")
	public String createCodePage() {
		
		return "board/errorBoard/createSettingForm";
	}
	
	//코드 작성 메소드
	@RequestMapping("insert")
	public String createCode(String input) {
		System.out.println(input);
		return null;
	}
	
	
	
}
