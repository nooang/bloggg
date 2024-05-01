package com.example.blog.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractSearchVO {
	private int pageNo;
	private int listSize;
	private int pageCount;
	private int pageCountInGroup;
	private int groupCount;
	private int groupNo;
	private int groupStartPageNo;
	private int groupEndPageNo;
	private boolean hasNextGroup;
	private boolean hasPrevGroup;
	private int nextGroupStartPageNo;
	private int prevGroupStartPageNo;
	
	public AbstractSearchVO() {
		listSize = 10;
		pageCountInGroup = 10;
	}
	
	public void setPageCount(int listCount) {
		this.pageCount = (int) Math.ceil((double) listCount / this.listSize);
		this.groupCount = (int) Math.ceil((double) this.pageCount / this.pageCountInGroup);
		this.groupNo = this.pageNo / this.pageCountInGroup;
		this.groupStartPageNo = this.groupNo * this.pageCountInGroup;
		this.groupEndPageNo = (this.groupNo + 1) * this.pageCountInGroup - 1;
		if (groupEndPageNo > this.pageCount - 1) {
			this.groupEndPageNo = this.pageCount - 1;
		}
		this.hasNextGroup = this.groupNo + 1 < this.groupCount;
		this.hasPrevGroup = this.groupNo > 0;
		this.nextGroupStartPageNo = this.groupEndPageNo + 1;
		this.prevGroupStartPageNo = this.groupStartPageNo - this.pageCountInGroup;
	}
}
