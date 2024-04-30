package com.example.blog.bbs.vo;

import com.example.blog.common.vo.AbstractSearchVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBoardVO extends AbstractSearchVO {
	private String searchType;
	private String searchKeyword;
}
