package com.example.blog.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.bbs.dao.BoardDAO;
import com.example.blog.bbs.vo.BoardListVO;
import com.example.blog.bbs.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public BoardListVO getAllBoard() {
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardCnt(boardDAO.getBoardAllCount());
		boardListVO.setBoardList(boardDAO.getAllBoard());
		return boardListVO;
	}
	
	@Override
	public boolean createNewBoard(BoardVO boardVO) {
		return boardDAO.createNewBoard(boardVO) > 0;
	}
	
	@Override
	public BoardVO getOneBoard(String id, boolean isIncrease) {
		if (isIncrease) {
			int updateCount = boardDAO.increaseViewCount(id);
			if (updateCount == 0) {
				throw new IllegalArgumentException("잘못된 접근입니다. 혹시 없는 게시글이 아닌지?");
			}			
		}
		
		BoardVO boardVO = boardDAO.getOneBoard(id);
		if (boardVO == null) {
			throw new IllegalArgumentException("잘못된 접근입니다. 혹시 없는 게시글이 아닌지?");
		}
		return boardVO;
	}
	
	@Override
	public boolean updateOneBoard(BoardVO boardVO) {
		return boardDAO.updateOneBoard(boardVO) > 0;
	}
	
	@Override
	public boolean deleteOneBoard(String id) {
		return boardDAO.deleteOneBoard(id) > 0;
	}
}
