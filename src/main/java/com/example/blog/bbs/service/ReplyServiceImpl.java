package com.example.blog.bbs.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.bbs.dao.ReplyDAO;
import com.example.blog.bbs.vo.ReplyVO;
import com.example.blog.exceptions.PageNotFoundException;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDAO replyDAO;
	
	private Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);

	@Override
	@Transactional
	public List<ReplyVO> getAllreplies(String boardId) {
		return replyDAO.getAllReplies(boardId);
	}
	
	@Override
	@Transactional
	public boolean createNewReply(ReplyVO replyVO) {
		if (replyVO.getParentReplyId() != null && !replyVO.getParentReplyId().equals("")) {
			Pattern pattern = Pattern.compile("(\\d+)$");
			Matcher matcher = pattern.matcher(replyVO.getParentReplyId());
			
			if (matcher.find()) {
				// 매칭된 전체 숫자열
				String allDigits = matcher.group(1);
				// 앞쪽의 불필요한 0 제거
				String trimmedDigits = allDigits.replaceFirst("^0+", "");
				replyVO.setParentReplyIdNum(Integer.parseInt(trimmedDigits));
			}
		}
		return replyDAO.createNewReply(replyVO) > 0;
	}

	@Override
	@Transactional
	public boolean deleteOneReply(String replyId, String email) {
		ReplyVO replyVO = replyDAO.getOneReply(replyId);
		if (!email.equals(replyVO.getEmail())) {
			throw new PageNotFoundException("당신의 댓글이 아닙니다. (delete one reply)");
		}
		return replyDAO.deleteOneReply(replyId) > 0;
	}

	@Override
	@Transactional
	public boolean modifyOneReply(ReplyVO replyVO) {
		ReplyVO originReplyVO = replyDAO.getOneReply(replyVO.getReplyId());
		if (!replyVO.getEmail().equals(originReplyVO.getEmail())) {
			throw new PageNotFoundException("당신의 댓글이 아닙니다. (modify one reply)");
		}
		return replyDAO.modifyOneReply(replyVO) > 0;
	}

	@Override
	@Transactional
	public boolean recommendOneReply(String replyId, String email) {
		ReplyVO replyVO = replyDAO.getOneReply(replyId);
		if (email.equals(replyVO.getEmail())) {
			throw new PageNotFoundException("당신의 댓글은 추천할 수 없습니다. (recommend one reply)");
		}
		return replyDAO.recommendOneReply(replyId) > 0;
	}

}
