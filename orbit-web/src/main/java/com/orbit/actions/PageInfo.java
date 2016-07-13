package com.orbit.actions;

public class PageInfo {
	
	private long pageSize;
	private long pageIndex;
	private long pageCount;
	private long recordCount;
	
	public PageInfo(){}
	
	public PageInfo(Long pageIndex, Long pageSize, Long recordCount){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
	}
	
	public void setPageSize(long size){
		this.pageSize = size;
	}
	public long getPageSize(){
		return this.pageSize;
	}
	
	public void setPageIndex(long index){
		this.pageIndex = index;
	}
	public long getPageIndex(){
		return this.pageIndex;
	}
	
	public void setPageCount(long count){
		this.pageCount = count;
	}
	public long getPageCount(){
		if(this.pageCount == 0){
			this.pageCount = (this.recordCount % this.pageSize) == 0 ? 
					(this.recordCount / this.pageSize) : 
					(this.recordCount / this.pageSize + 1);
		}
		return this.pageCount;
	}
	
	public void setRecordCount(long count){
		this.recordCount = count;
	}
	public long getRecordCount(){
		return this.recordCount;
	}
	
}
