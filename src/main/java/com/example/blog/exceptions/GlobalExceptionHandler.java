package com.example.blog.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PageNotFoundException.class)
	public ModelAndView viewErrorPage(PageNotFoundException runtimeException) {
		ModelAndView mav = new ModelAndView("error/404");
		mav.addObject("message", runtimeException.getMessage());
		return mav;
	}
	
	@ExceptionHandler({FileNotExistsException.class, MakeXlsxFileException.class})
	public ModelAndView viewErrorPage(RuntimeException runtimeException) {
		ModelAndView mav = new ModelAndView("error/500");
		mav.addObject("message", runtimeException.getMessage());
		return mav;
	}
	
	@ExceptionHandler(AlreadyUseException.class)
	public ModelAndView viewMemberRegistErrorPage(AlreadyUseException exception) {
		ModelAndView mav = new ModelAndView("member/memberregist");
		mav.addObject("memberVO", exception.getMemberVO());
		return mav;
	}
	
	@ExceptionHandler(UserIdentifyNotMatchException.class)
	public ModelAndView viewUserIdentifyNotMatchExceptionErrorPage(UserIdentifyNotMatchException exception) {
		ModelAndView mav = new ModelAndView("member/memberlogin");
		mav.addObject("memberVO", exception.getMemberVO());
		mav.addObject("message", exception.getMessage());
		return mav;
	}
	
	
}