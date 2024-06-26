package com.example.blog.member.service;

import com.example.blog.member.vo.MemberVO;

public interface MemberService {
	public boolean checkAvailableEmail(String email);
	public boolean createNewMember(MemberVO memberVO);
	public MemberVO getMember(MemberVO memberVO);
	public boolean deleteMe(String email);
}
