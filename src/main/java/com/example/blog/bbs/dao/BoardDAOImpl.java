package com.example.blog.bbs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.blog.bbs.vo.BoardVO;

@Repository
public class BoardDAOImpl extends SqlSessionDaoSupport implements BoardDAO {
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public int getBoardAllCount() {
		return getSqlSession().selectOne("getBoardAllCount");
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return getSqlSession().selectList("getAllBoard");
	}
	
	@Override
	public int createNewBoard(BoardVO boardVO) {
		return getSqlSession().insert("createNewBoard", boardVO);
	}
	
	@Override
	public int increaseViewCount(String id) {
		return getSqlSession().update("increaseViewCount", id);
	}
	
	@Override
	public BoardVO getOneBoard(String id) {
		return getSqlSession().selectOne("getOneBoard", id);
	}
	
	@Override
	public int updateOneBoard(BoardVO boardVO) {
		return getSqlSession().update("updateOneBoard", boardVO);
	}
	
	@Override
	public int deleteOneBoard(String id) {
		return getSqlSession().delete("deleteOneBoard", id);
	}
}
