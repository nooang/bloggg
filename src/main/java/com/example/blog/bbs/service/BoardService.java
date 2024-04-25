package com.example.blog.bbs.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.blog.bbs.vo.BoardListVO;
import com.example.blog.bbs.vo.BoardVO;

public interface BoardService {
	public BoardListVO getAllBoard();
	public boolean createNewBoard(BoardVO boardVO, MultipartFile file);
	public BoardVO getOneBoard(String id, boolean isIncrease);
	public boolean updateOneBoard(BoardVO boardVO, MultipartFile file);
	public boolean deleteOneBoard(String id);
}
