package com.example.blog.bbs.service;

import java.util.List;

import com.example.blog.bbs.vo.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> getAllreplies(String boardId);
	public boolean createNewReply(ReplyVO replyVO);
	public boolean deleteOneReply(String replyId, String email);
	public boolean modifyOneReply(ReplyVO replyVO);
	public boolean recommendOneReply(String replyId, String email);
}
