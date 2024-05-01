package com.example.blog.bbs.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.bbs.dao.BoardDAO;
import com.example.blog.bbs.vo.BoardListVO;
import com.example.blog.bbs.vo.BoardVO;
import com.example.blog.bbs.vo.SearchBoardVO;
import com.example.blog.beans.FileHandler;
import com.example.blog.beans.FileHandler.StoredFile;
import com.example.blog.exceptions.PageNotFoundException;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private FileHandler fileHandler;
	
	@Override
	@Transactional
	public BoardListVO getAllBoard(SearchBoardVO searchBoardVO) {
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardCnt(boardDAO.getBoardAllCount(searchBoardVO));
		
		if (searchBoardVO == null) {
			boardListVO.setBoardList(boardDAO.getAllBoard());
		}
		else {
			boardListVO.setBoardList(boardDAO.searchAllBoard(searchBoardVO));
		}
		return boardListVO;
	}
	
	@Override
	@Transactional
	public boolean createNewBoard(BoardVO boardVO, MultipartFile file) {
		StoredFile storedFile = fileHandler.storeFile(file);
		
		boardVO.setFileName(storedFile.getRealFileName());
		boardVO.setOriginFileName(storedFile.getFileName());
		
		return boardDAO.createNewBoard(boardVO) > 0;
	}
	
	@Override
	@Transactional
	public BoardVO getOneBoard(String id, boolean isIncrease) {
		if (isIncrease) {
			int updateCount = boardDAO.increaseViewCount(id);
			if (updateCount == 0) {
				throw new PageNotFoundException("잘못된 접근입니다. 혹시 없는 게시글이 아닌지?");
			}			
		}
		
		BoardVO boardVO = boardDAO.getOneBoard(id);
		if (boardVO == null) {
			throw new PageNotFoundException("잘못된 접근입니다. 혹시 없는 게시글이 아닌지?");
		}
		return boardVO;
	}
	
	@Override
	@Transactional
	public boolean updateOneBoard(BoardVO boardVO, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			BoardVO originBoardVO = boardDAO.getOneBoard(boardVO.getId());
			
			if (originBoardVO != null && originBoardVO.getFileName() != null) {
				File originFile = fileHandler.getStoredFile(originBoardVO.getOriginFileName());
				
				if (originFile.exists() && originFile.isFile()) {
					originFile.delete();
				}
			}
			StoredFile storedFile = fileHandler.storeFile(file);
			boardVO.setFileName(storedFile.getRealFileName());
			boardVO.setOriginFileName(storedFile.getFileName());
		}
		
		return boardDAO.updateOneBoard(boardVO) > 0;
	}
	
	@Override
	@Transactional
	public boolean deleteOneBoard(String id) {
		BoardVO originBoardVO = boardDAO.getOneBoard(id);
		if (originBoardVO != null && originBoardVO.getFileName() != null) {
			File originFile = fileHandler.getStoredFile(originBoardVO.getFileName());
			
			if (originFile.exists() && originFile.isFile()) {
				originFile.delete();
			}
		}
		
		return boardDAO.deleteOneBoard(id) > 0;
	}
}
