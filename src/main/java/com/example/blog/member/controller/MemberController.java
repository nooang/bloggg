package com.example.blog.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.blog.member.service.MemberService;
import com.example.blog.member.vo.MemberVO;
import com.example.blog.member.vo.validategroup.MemberLoginGroup;
import com.example.blog.member.vo.validategroup.MemberRegistGroup;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/regist")
	public String viewRegistMemberPage() {
		 return "member/memberregist";
	}
	
	@ResponseBody
	@GetMapping("/regist/available")
	public Map<String, Object> checkAvailableEmail(@RequestParam String email) {
		boolean isAvailableEmail = memberService.checkAvailableEmail(email);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("email", email);
		responseMap.put("available", isAvailableEmail);
		
		return responseMap;
	}
	
	@PostMapping("/regist")
	public ModelAndView doRegistMember(@Validated(MemberRegistGroup.class) @ModelAttribute MemberVO memberVO, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("redirect:/member/login");
		
		if (bindingResult.hasErrors()) {
			mav.setViewName("member/memberregist");
			mav.addObject("member", memberVO);
			return mav;
		}
		
		boolean isSuccess = memberService.createNewMember(memberVO);
		if (isSuccess) {
			return mav;
		}
		else {
			mav.setViewName("member/memberregist");
			mav.addObject("member", memberVO);
			return mav;
		}
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		return "member/memberlogin";
	}
	
	@PostMapping("/login")
	public ModelAndView doLogin(@Validated(MemberLoginGroup.class) @ModelAttribute MemberVO memberVO, BindingResult bindingResult
			                  , HttpSession session, HttpServletRequest request, @RequestParam(required=false, defaultValue="/board/list") String next) {
		ModelAndView mav = new ModelAndView("redirect:" + next);
		
		if (bindingResult.hasErrors()) {
			mav.setViewName("member/memberlogin");
			mav.addObject("member", memberVO);
			return mav;
		}
		
//		try {
			memberVO.setLatestAccessIp(request.getRemoteAddr());
			MemberVO member = memberService.getMember(memberVO);
			session.setAttribute("_LOGIN_USER_", member);
//		} catch (IllegalArgumentException iae) {
//			mav.setViewName("member/memberlogin");
//			mav.addObject("member", memberVO);
//			mav.addObject("message", iae.getMessage());
//			return mav;
//		}
		
		return mav;
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/board/list";
	}
	
	@GetMapping("/delete-me")
	public String doDeleteMe(@SessionAttribute("_LOGIN_USER_") MemberVO memberVO, HttpSession session) {
		boolean isSuccess = memberService.deleteMe(memberVO.getEmail());
		
		if (!isSuccess) {
			return "redirect:/member/fail-delete-me";
		}
		
		session.invalidate();
		return "redirect:/member/success-delete-me";
	}
	
	@GetMapping("{result}-delete-me")
	public String viewDeleteMePage(@PathVariable String result) {
		result = result.toLowerCase();
		
		if (!result.equals("fail") && !result.equals("success")) {
			return "error/404";
		}
		
		return "member/" + result + "deleteme";
	}
}
