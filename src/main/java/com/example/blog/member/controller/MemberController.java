package com.example.blog.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.blog.member.service.MemberService;
import com.example.blog.member.vo.MemberVO;

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
	public ModelAndView doRegistMember(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) {
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
}
