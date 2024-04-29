package com.example.blog.bbs.dao;

import java.util.List;

import com.example.blog.bbs.vo.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> getAllReplies(String boardId);
	public ReplyVO getOneReply(String replyId);
	public int createNewReply(ReplyVO replyVO);
	public int deleteOneReply(String replyId);
	public int modifyOneReply(ReplyVO replyVO);
	public int recommendOneReply(String replyId);
}
