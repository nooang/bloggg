package com.example.blog.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.blog.bbs.vo.ReplyVO;

@Repository
public class ReplyDAOImpl extends SqlSessionDaoSupport implements ReplyDAO {
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	private Logger logger = LoggerFactory.getLogger(ReplyDAOImpl.class);
	
	@Override
	public List<ReplyVO> getAllReplies(String boardId) {
		return getSqlSession().selectList("getAllReplies", boardId);
	}

	@Override
	public ReplyVO getOneReply(String replyId) {
		return getSqlSession().selectOne("getOneReply", replyId);
	}

	@Override
	public int createNewReply(ReplyVO replyVO) {
		return getSqlSession().insert("createNewReply", replyVO);
	}

	@Override
	public int deleteOneReply(String replyId) {
		return getSqlSession().delete("deleteOneReply", replyId);
	}

	@Override
	public int modifyOneReply(ReplyVO replyVO) {
		return getSqlSession().update("modifyOneReply", replyVO);
	}

	@Override
	public int recommendOneReply(String replyId) {
		return getSqlSession().update("recommendOneReply", replyId);
	}
	
}
