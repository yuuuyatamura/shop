package com.example.domain;

public class Page {

	private int totalCount; // total product count in db
	private int totalPage; // total page count
	private int pageNo; // current page no
	private int pageSize = 8; // show page size
	private int startPage; // first page
	private int endPage; // last page
	private int btnSize = 5;
	private int prev;
	private int next;
	
	
	
	public Page() {
	}

	public Page(int totalCount, int totalPage, int pageSize, int startPage, int endPage, int pageNo, int btnSize,
			int prev, int next) {
		super();
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.pageSize = pageSize;
		this.startPage = startPage;
		this.endPage = endPage;
		this.pageNo = pageNo;
		this.btnSize = btnSize;
		this.prev = prev;
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getBtnSize() {
		return btnSize;
	}

	public void setBtnSize(int btnSize) {
		this.btnSize = btnSize;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Page [totalCount=" + totalCount + ", totalPage=" + totalPage + ", pageSize=" + pageSize + ", startPage="
				+ startPage + ", endPage=" + endPage + ", pageNo=" + pageNo + ", btnSize=" + btnSize + ", prev=" + prev
				+ ", next=" + next + "]";
	}
	
}
