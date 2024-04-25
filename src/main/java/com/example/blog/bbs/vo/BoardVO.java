package com.example.blog.bbs.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private String id;
	@NotEmpty(message="제목을 입력해주세요.")
	private String subject;
	@NotEmpty(message="내용을 입력해주세요.")
	private String content;
	@NotEmpty(message="이메일을 입력해주세요.")
	@Email(message="올바른 형식으로 입력해주세요.")
	private String email;
	private int viewCnt;
	private String crtDt;
	private String mdfyDt;
	private String fileName;
	private String originFileName;
	private String ipAddr;
}
