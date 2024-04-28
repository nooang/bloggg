package com.example.blog.exceptions;

import com.example.blog.member.vo.MemberVO;

import lombok.Getter;

@Getter
public class UserBlockException extends RuntimeException {
	private static final long serialVersionUID = -2458457268019914880L;
	
	private MemberVO memberVO;

	public UserBlockException(MemberVO memberVO, String message) {
		super(message);
		this.memberVO = memberVO;
	}
}
