package com.example.blog.bbs.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListVO {
	private int boardCnt;
	private List<BoardVO> boardList;
}
