package com.example.blog.bbs.dao;

import java.util.List;

import com.example.blog.bbs.vo.BoardVO;

public interface BoardDAO {
	public int getBoardAllCount();
	public List<BoardVO> getAllBoard();
	public int createNewBoard(BoardVO boardVO);
	public int increaseViewCount(String id);
	public BoardVO getOneBoard(String id);
	public int updateOneBoard(BoardVO boardVO);
	public int deleteOneBoard(String id);
}
