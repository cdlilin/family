package org.swz.com.family.repository.mybatis.plugs;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

public class Page<E> extends ArrayList<E> {  
	private boolean pageCountGetFlag = false;
	private int pageNum;
	private int pageSize;
	private int startRow;
	private int endRow;
	private long total;
	private int pages;

	public Page(int pageNum, int pageSize) {
		this(pageNum, pageSize, false);
	}

	public Page(int pageNum, int pageSize, boolean pageCountGetFlag) {
		super(pageSize);
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.pageCountGetFlag = pageCountGetFlag;
		this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
		this.endRow = pageNum * pageSize;
	}

	public Page(RowBounds rowBounds, int total) {
		super(rowBounds.getLimit());
		this.pageSize = rowBounds.getLimit();
		this.startRow = rowBounds.getOffset();
		// RowBounds方式默认不求count总数，如果想求count,可以修改这里为SQL_COUNT
		this.total = total;
		this.endRow = this.startRow + this.pageSize;
	}

	public boolean isPageCountGetFlag() {
		return pageCountGetFlag;
	}

	public void setPageCountGetFlag(boolean pageCountGetFlag) {
		this.pageCountGetFlag = pageCountGetFlag;
	}

	public List<E> getResult() {
		return this;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String toString() {
		return "Page{" + "pageNum=" + pageNum + ", pageSize=" + pageSize
				+ ", startRow=" + startRow + ", endRow=" + endRow + ", total="
				+ total + ", pages=" + pages + '}';
	}
}
