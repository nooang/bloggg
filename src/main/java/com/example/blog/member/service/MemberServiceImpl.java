package com.example.blog.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.beans.SHA;
import com.example.blog.member.dao.MemberDAO;
import com.example.blog.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	SHA sha;
	
	@Override
	public boolean checkAvailableEmail(String email) {
		return memberDAO.getEmailCount(email) == 0;
	}

	@Override
	public boolean createNewMember(MemberVO memberVO) {
		int emailCount = memberDAO.getEmailCount(memberVO.getEmail());
		
		if (emailCount > 0) {
			throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
		}
		
		String salt = sha.generateSalt();
		String password = memberVO.getPassword();
		String encryptedPassword = sha.getEncrypt(password, salt);
		memberVO.setPassword(encryptedPassword);
		memberVO.setSalt(salt);
		
		return memberDAO.createNewMember(memberVO) > 0;
	}

	@Override
	public MemberVO getMember(MemberVO memberVO) {
		String salt = memberDAO.getSalt(memberVO.getEmail());
		if (salt == null) {
			throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.1");
		}
		
		String password = memberVO.getPassword();
		String encryptedPassword = sha.getEncrypt(password, salt);
		memberVO.setPassword(encryptedPassword);
		
		MemberVO member = memberDAO.getMember(memberVO);
		if (member == null) {
			memberVO.setPassword(password);
			memberDAO.failLogin(memberVO);
			memberDAO.blockMember(memberVO.getEmail());
			throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.2");
		}
		
		if (member.getBlockYn().equalsIgnoreCase("Y")) {
			memberVO.setPassword(password);
			throw new IllegalArgumentException("차단된 계정입니다.");
		}
		
		memberDAO.successLogin(memberVO);
		
		return member;
	}
}
