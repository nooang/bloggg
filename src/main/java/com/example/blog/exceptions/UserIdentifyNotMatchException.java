package com.example.blog.exceptions;

import com.example.blog.member.vo.MemberVO;

import lombok.Getter;

@Getter
public class UserIdentifyNotMatchException extends RuntimeException {
	private static final long serialVersionUID = 3424100999217064663L;
	
	private MemberVO memberVO;
	
	public UserIdentifyNotMatchException(MemberVO memberVO, String message) {
		super(message);
		this.memberVO = memberVO;
	}
}
