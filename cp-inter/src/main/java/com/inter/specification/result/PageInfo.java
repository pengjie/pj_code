package com.inter.specification.result;

import java.io.Serializable;

/**
 * 页码信息
 */
public class PageInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 当前页码**/
	private int currPageNo = 1;
	/** 一页最大记录数**/
	private int pageSize = 10;
	/** 总记录数**/
	private int totalRecord;
	/** 总页码数(最后一页)**/
	private int lastPageNo;
	/** 下一页 页码**/
	private int nextPageNo;
	/** 上一页 页码**/
	private int prevPageNo;
	
	public PageInfo(){
		
	}
	
	public PageInfo(int pageNo, int pageSize, int totalRecord) {
		super();
		this.currPageNo = pageNo;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		if (this.currPageNo < 1) {
			this.currPageNo = 1;
		}
		if (this.pageSize <= 0) {
			this.pageSize = 10;
		}
		
		if (this.totalRecord == 0) {
			this.lastPageNo = 1;
		}else{
			this.lastPageNo = this.totalRecord / this.pageSize;
			if (this.totalRecord % this.pageSize != 0){
				this.lastPageNo ++;
			}
		}		
		if (this.totalRecord % this.pageSize != 0) {
			this.lastPageNo = this.totalRecord / this.pageSize + 1;
		}else{
			this.lastPageNo = this.totalRecord / this.pageSize;
		}
		if (this.currPageNo > lastPageNo) {
			this.currPageNo = lastPageNo;
		}
		nextPageNo = this.currPageNo + 1;
		if (nextPageNo > lastPageNo) {
			nextPageNo = lastPageNo;
		}
		prevPageNo = this.currPageNo - 1;
		if (prevPageNo < 1) {
			prevPageNo = 1;
		}
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	public int getPrevPageNo() {
		return prevPageNo;
	}
	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}

	public int getLastPageNo() {
		return lastPageNo;
	}

	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	public int getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}
}
