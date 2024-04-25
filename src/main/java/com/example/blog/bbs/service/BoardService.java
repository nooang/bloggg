package com.example.blog.bbs.service;

import com.example.blog.bbs.vo.BoardListVO;
import com.example.blog.bbs.vo.BoardVO;

public interface BoardService {
	public BoardListVO getAllBoard();
	public boolean createNewBoard(BoardVO boardVO);
	public BoardVO getOneBoard(String id, boolean isIncrease);
	public boolean updateOneBoard(BoardVO boardVO);
	public boolean deleteOneBoard(String id);
}
