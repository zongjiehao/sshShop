package com.haozj.shop.utils;


import java.util.List;
/**
 * 分页功能
 * @author haozj
 *
 * @param <T>
 */
public class PageBean<T> {
	private int page;//当前页数
	private int pageCount;//总页数
	private int totalCount;//总记录数
	private int limit;//每一页显示的记录数
	private List<T> list;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
