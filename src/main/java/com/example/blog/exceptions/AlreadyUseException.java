package com.example.blog.exceptions;

import com.example.blog.member.vo.MemberVO;

import lombok.Getter;

@Getter
public class AlreadyUseException extends RuntimeException {
	private static final long serialVersionUID = -8666349317805832790L;
	 
	private MemberVO memberVO;
	
	public AlreadyUseException(MemberVO memberVO, String message) {
		super(message);
		this.memberVO = memberVO;
	}
	
}
