package com.example.blog.beans;

import java.util.Enumeration;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.blog.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckSessionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("_LOGIN_USER_");
		
		if (memberVO == null) {
			String method = request.getMethod().toLowerCase();
			
			if (method.equals("get")) {
				String requestURI = request.getRequestURI();
				String queryString = getQueryStrings(request);
			}
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	private String getQueryStrings(HttpServletRequest request) {
		String queryString = "";
		
		Enumeration<String> parameterNames = request.getParameterNames();
		String parameterName = null;
		
		while (parameterNames.hasMoreElements()) {
			parameterName = parameterNames.nextElement();
			
			if (queryString.equals("")) {
				queryString = "?";
			}
			else {
				queryString += "&";
			}
			queryString += parameterName + "=" + request.getParameter(parameterName);
		}
		
		return  queryString;
	}
}
