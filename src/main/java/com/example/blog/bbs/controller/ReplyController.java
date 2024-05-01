package com.example.blog.bbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.blog.bbs.service.ReplyService;
import com.example.blog.bbs.vo.ReplyVO;
import com.example.blog.member.vo.MemberVO;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	private Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@GetMapping("/{boardId}")
	public Map<String, Object> getAllReplies(@PathVariable String boardId) {
		List<ReplyVO> replyList = replyService.getAllreplies(boardId);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("count", replyList.size());
		resultMap.put("replies", replyList);
		
		return resultMap;
	}
	
	@PostMapping("/{boardId}")
	public Map<String, Object> doCreateNewReplies(@PathVariable String boardId, @ModelAttribute ReplyVO replyVO, @SessionAttribute("_LOGIN_USER_") MemberVO memberVO) {
		replyVO.setBoardId(boardId);
		replyVO.setEmail(memberVO.getEmail());
		
		boolean isSuccess = replyService.createNewReply(replyVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		
		return resultMap;
	}
	
	@PostMapping("/modify/{replyId}")
	public Map<String, Object> doModifyReplies(@PathVariable String replyId, @ModelAttribute ReplyVO replyVO, @SessionAttribute("_LOGIN_USER_") MemberVO memberVO) {
		replyVO.setBoardId(replyId);
		replyVO.setEmail(memberVO.getEmail());
		
		boolean isSuccess = replyService.modifyOneReply(replyVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		
		return resultMap;
	}
	
	@GetMapping("/recommend/{replyId}")
	public Map<String, Object> doRecommendReplies(@PathVariable String replyId, @SessionAttribute("_LOGIN_USER_") MemberVO memberVO) {
		boolean isSuccess = replyService.recommendOneReply(replyId, memberVO.getEmail());
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		
		return resultMap;
	}
	
	@GetMapping("/delete/{replyId}")
	public Map<String, Object> doDeleteReplies(@PathVariable String replyId, @SessionAttribute("_LOGIN_USER_") MemberVO memberVO) {
		boolean isSuccess = replyService.deleteOneReply(replyId, memberVO.getEmail());
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		
		System.out.println(resultMap);
		
		return resultMap;
	}
} 
