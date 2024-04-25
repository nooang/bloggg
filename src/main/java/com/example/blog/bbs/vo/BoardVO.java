package com.example.blog.bbs.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private String id;
	private String subject;
	private String content;
	private String email;
	private int viewCnt;
	private String crtDt;
	private String mdfyDt;
	private String fileName;
	private String originFileName;
	private String ipAddr;
}
