package com.hoju.koala.member.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hoju.koala.member.model.service.MemberService;
import com.hoju.koala.member.model.vo.Follow;
import com.hoju.koala.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	
	
	//로그인 페이지 이동
	@GetMapping("/login")
	public String login() {
		
		
		return "member/loginPage";
	}
	
	//로그인
	@PostMapping("/login")
	public ModelAndView login(HttpSession session,
						Member m,
						ModelAndView mv) {
		
		Member loginUser = memberService.loginMember(m);
		
		if((loginUser != null) && (pwdEncoder.matches(m.getUserPwd(), loginUser.getUserPwd()))) {
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("msg", "로그인 완료");
			
			mv.setViewName("redirect:/");
		}else {
			session.setAttribute("msg", "로그인 실패");
			mv.setViewName("member/loginPage");
		}
		
		return mv;
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		
		return "redirect:/";
	}
	
	
	//회원가입페이지이동
	@GetMapping("/enroll")
	public String enroll() {
		
		return "member/enrollPage";
	}
	
	
	//회원가입
	@PostMapping("/insert")
	public ModelAndView insertMember(HttpSession session,
								Member m,
								ModelAndView mv) {
		
		String encPwd = pwdEncoder.encode(m.getUserPwd());
		
		m.setUserPwd(encPwd);
		
		int result = memberService.insertMember(m);
		
		if(result>0) {
			session.setAttribute("msg", "회원가입이 완료되었습니다.");
			mv.setViewName("redirect:/");
		}else {
			mv.addObject("msg", "회원가입 실패");
			mv.setViewName("redirect:/");
		}
		
		
		return mv;
	}
	
	
	//회원 정보 조회 (활동내역)
	@GetMapping("/ad")
	public ModelAndView ad(int userNo,
					 ModelAndView mv) {
		
		
		//조회해온 유저담기
		Member m = memberService.selectMember(userNo);
		//해당 유저팔로우수 조회
		int cnt = memberService.selectFollowCount(userNo);
		
		if(m != null) {
			mv.addObject("user", m);
			mv.addObject("followCnt", cnt);
			
			mv.setViewName("member/activityDetailPage");
		}
		
		
		return mv;
	}
	
	//자기소개 업데이트
	@PostMapping("/update.intro")
	public ModelAndView updateIntroduce(ModelAndView mv,
										String introduce,
										HttpSession session) {
		Member m = (Member)session.getAttribute("loginUser");
		m.setIntroduce(introduce);
		
		int result = memberService.updateIntroduce(m);
		
		if(result>0) {
			session.setAttribute("loginUser", m);
			session.setAttribute("user", m);
			session.setAttribute("msg", "소개글이 수정되었습니다.");
			
			mv.setViewName("redirect:/member/ad?userNo="+m.getUserNo());
		}else {
			session.setAttribute("msg", "소개글 수정이 실패하였습니다.");
			mv.setViewName("member/activityDetailPage");
		}
		
		return mv;
	}
	
	
	//팔로우
	@ResponseBody
	@GetMapping("/follow")
	public int follow(Follow f) {
		
		int result = memberService.addFollow(f);
		
		if(result>0) {
			result = 1;
		}else {
			int result2 = memberService.removeFollow(f);
			
			if(result2>0) {
				result = 2;
				
			}
		}
		
		return result;
		
	}
	
	//ID/PWD 찾기 페이지 이동
	@GetMapping("/forget")
	public String forget() {
		
		return "member/forgetPage";
	}
	
	//계정설정 페이지 이동
	@GetMapping("/as")
	public String as(HttpSession session) {
		
		return "member/accountSettingPage";
	}
	
	
	//비밀번호 변경
	@ResponseBody
	@PostMapping("/updatePwd")
	public int updatePwd(HttpServletRequest request) {
		
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		
		//해쉬함수로인해 암호화된 현재 비밀번호
		String currentPwd = loginUser.getUserPwd();

		//모달창에서 현재 비밀번호 인풋에 사용자 입력값
		String userPwd = request.getParameter("userPwd");
		//모달창에서 사용자가 입력한 새로운비밀번호
		String newPwd = request.getParameter("newPwd");
		
		int result = 0;
		if(pwdEncoder.matches(userPwd, currentPwd) && (!userPwd.equals(newPwd))) { //부합할때 진행
			
			loginUser.setUserPwd(pwdEncoder.encode(newPwd));
			
			result = memberService.updatePwd(loginUser);
			
			if(result>0) { //성공적으로 데이터베이스 업데이트했으면 세션loginUser 바꿔주기
				request.getSession().setAttribute("loginUser", loginUser);
			}
		}
		
		return result;
	}
	
	
}
