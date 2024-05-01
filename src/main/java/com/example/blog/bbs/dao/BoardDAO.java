package com.example.blog.bbs.dao;

import java.util.List;

import com.example.blog.bbs.vo.BoardVO;
import com.example.blog.bbs.vo.SearchBoardVO;

public interface BoardDAO {
	public int getBoardAllCount(SearchBoardVO searchBoardVO);
	public List<BoardVO> getAllBoard();
	public List<BoardVO> searchAllBoard(SearchBoardVO searchBoardVO);
	public int createNewBoard(BoardVO boardVO);
	public int increaseViewCount(String id);
	public BoardVO getOneBoard(String id);
	public int updateOneBoard(BoardVO boardVO);
	public int deleteOneBoard(String id);
}
