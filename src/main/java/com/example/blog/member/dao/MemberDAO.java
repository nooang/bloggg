package com.example.blog.member.dao;

import com.example.blog.member.vo.MemberVO;

public interface MemberDAO {
	public int getEmailCount(String email);
	public int createNewMember(MemberVO memberVO);
}
