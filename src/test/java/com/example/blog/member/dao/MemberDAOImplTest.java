package com.example.blog.member.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import com.example.blog.member.dao.MemberDAO;
import com.example.blog.member.dao.MemberDAOImpl;
import com.example.blog.member.vo.MemberVO;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MemberDAOImpl.class)
public class MemberDAOImplTest {
	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void getEmailCountTest() {
		int count = memberDAO.getEmailCount("1@1");
		assertEquals(count, 1);
	}
	
	@Test
	public void createNewMemberTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail("user01@gmail.com");
		memberVO.setName("테스트사용자");
		memberVO.setPassword("testpassword");
		memberVO.setConfirmPassword("testpassword");
		memberVO.setSalt("testsalt");
		
		int count = memberDAO.createNewMember(memberVO);
		assertEquals(count, 1);
	}
	
	@Test
	public void deleteMeTest() {
		int count = memberDAO.deleteMe("1@1");
		assertEquals(count, 1);
	}
}
