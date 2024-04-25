package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.blog.bbs.service.BoardService;
import com.example.blog.bbs.vo.BoardListVO;
import com.example.blog.bbs.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
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
	public ModelAndView doBoardWrite(@ModelAttribute BoardVO boardVO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/board/list");
		boardVO.setIpAddr(request.getRemoteAddr());
		
		boolean isSuccess = boardService.createNewBoard(boardVO);
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
	
	@GetMapping("/modify/{id}")
	public ModelAndView viewBoardModifyPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("board/boardmodify");
		
		BoardVO boardVO = boardService.getOneBoard(id, false);
		mav.addObject("board", boardVO);
		return mav;
	}
	
	@PostMapping("/modify")
	public ModelAndView doBoardUpdate(@ModelAttribute BoardVO boardVO) {
		ModelAndView mav = new ModelAndView("redirect:/board/view?id=" + boardVO.getId());
		
		boolean isSuccess = boardService.updateOneBoard(boardVO);
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
