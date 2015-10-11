package doc.beo;

import doc.db.dao.DocumentDao;

public class TablePage 
{
	private int count; // 记录总数
	private int pageSize; // 每页显示记录数
	private int pageCount; // 总页数
	private int page; // 当前页数

	public int getCount() {
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
