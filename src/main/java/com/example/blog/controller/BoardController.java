package com.example.blog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.blog.bbs.service.BoardService;
import com.example.blog.bbs.vo.BoardListVO;
import com.example.blog.bbs.vo.BoardVO;
import com.example.blog.beans.FileHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private FileHandler fileHandler;
	
	@GetMapping("/list")
	public ModelAndView viewBoardList() {
		ModelAndView mav = new ModelAndView("board/boardlist");
		BoardListVO boardListVO = boardService.getAllBoard();
		mav.addObject("boardList", boardListVO);
		
		return mav;
	}
	
	@GetMapping("/write")
	public String viewBoardWritePage() {
		return "board/boardwrite";
	}
	
	@PostMapping("/write")
	public ModelAndView doBoardWrite(@Valid @ModelAttribute BoardVO boardVO, BindingResult bindingResult
			                       , HttpServletRequest request, @RequestParam MultipartFile file) {
		ModelAndView mav = new ModelAndView("redirect:/board/list");
		
		if (bindingResult.hasErrors()) {
			mav.setViewName("board/boardwrite");
			mav.addObject("board", boardVO);
			
			return mav;
		}
		
		boardVO.setIpAddr(request.getRemoteAddr());
		
		boolean isSuccess = boardService.createNewBoard(boardVO, file);
		if (isSuccess) {
			return mav;
		}
		else {
			mav.setViewName("board/boardwrite");
			mav.addObject("board", boardVO);
			return mav;
		}
	}
	
	@GetMapping("/view")
	public ModelAndView viewOneBoard(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("board/boardview");
		
		BoardVO boardVO = boardService.getOneBoard(id, true);
		mav.addObject("board", boardVO);
		
		return mav;
	}
	
	@GetMapping("/file/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
		BoardVO boardVO = boardService.getOneBoard(id, false);
		
		if (boardVO == null) {
			throw new IllegalArgumentException("잘못된 접근입니다. 혹시 없는 게시글이 아닌지?");
		}
		
		File storedFile = fileHandler.getStoredFile(boardVO.getFileName());
		
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + boardVO.getOriginFileName());
		
		InputStreamResource resource;
		try {
			resource = new InputStreamResource(new FileInputStream(storedFile));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("파일이 존재하지 않습니다.");
		}
		
		return ResponseEntity.ok()
				             .headers(header)
				             .contentLength(storedFile.length())
				             .contentType(MediaType.parseMediaType("application/octet-stream"))
				             .body(resource);
	}
	
	@GetMapping("/modify/{id}")
	public ModelAndView viewBoardModifyPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("board/boardmodify");
		
		BoardVO boardVO = boardService.getOneBoard(id, false);
		mav.addObject("board", boardVO);
		return mav;
	}
	
	@PostMapping("/modify")
	public ModelAndView doBoardUpdate(@Valid @ModelAttribute BoardVO boardVO, BindingResult bindingResult, @RequestParam MultipartFile file) {
		ModelAndView mav = new ModelAndView("redirect:/board/view?id=" + boardVO.getId());
		
		if (bindingResult.hasErrors()) {
			mav.setViewName("board/boardmodify");
			mav.addObject("board", boardVO);
			
			return mav;
		}
		
		boolean isSuccess = boardService.updateOneBoard(boardVO, file);
		if (isSuccess) {
			return mav;
		}
		else {
			mav.setViewName("board/boardmodify");
			mav.addObject("board", boardVO);
			return mav;
		}
	}
	
	@GetMapping("/delete/{id}")
	public String doDeleteBoard(@PathVariable String id) {
		boolean isSuccess = boardService.deleteOneBoard(id);
		if (isSuccess) {
			return "redirect:/board/list";
		}
		else {
			return "redirect:/board/view?id=" + id;
		}
	}
}
