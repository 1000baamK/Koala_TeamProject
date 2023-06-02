package com.hoju.koala.member.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoju.koala.member.model.service.MemberService;
import com.hoju.koala.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	
	@GetMapping("/login")
	public String login() {
		
		
		return "member/loginPage";
	}
	
	@PostMapping("login")
	public String login(HttpSession session,
						Member m,
						Model model) {
		
		
		Member loginUser = memberService.loginMember(m);
		
		
		
		
		
		
		
		return null;
	}
	
	
}
