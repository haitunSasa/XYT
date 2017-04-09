package com.Entity.Page;

public class Page {

	private int currpage=1;
	private int rows=10;
	private Long totalrecords=0L;
	private int totalpage=1;
	
	//加入查询条件
	private int topicClassifyId=0;
	private int orderflag=0;
	
	public Page(){
		
	}

	public Page(int currpage, int rows, Long totalrecords, int totalpage,int topicClassifyId,int orderflag) {
		super();
		this.currpage = currpage;
		this.rows = rows;
		this.totalrecords = totalrecords;
		this.totalpage = totalpage;
		
		this.topicClassifyId=topicClassifyId;
		this.orderflag=orderflag;
	}

	public int getCurrpage() {
		return currpage;
	}

	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Long getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(Long totalrecords) {
		this.totalrecords = totalrecords;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getTopicClassifyId() {
		return topicClassifyId;
	}

	public void setTopicClassifyId(int topicClassifyId) {
		this.topicClassifyId = topicClassifyId;
	}

	public int getOrderflag() {
		return orderflag;
	}

	public void setOrderflag(int orderflag) {
		this.orderflag = orderflag;
	}
}
