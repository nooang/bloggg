package com.example.blog.bbs.vo;

import com.example.blog.member.vo.MemberVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
	private String level;
	private String replyId;
	private String boardId;
	private String email;
	private String content;
	private String crtDt;
	private String mdfyDt;
	private String recommendCnt;
	private String parentReplyId;
	private int parentReplyIdNum;
	private int replyIdNum;
	
	private MemberVO memberVO;
}
