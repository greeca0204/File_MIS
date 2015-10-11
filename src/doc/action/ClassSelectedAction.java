package doc.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.limit.Filter;
import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.Sort;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.ExtremeTablePage;
import doc.beo.JnuDocument;
import doc.beo.TablePage;
import doc.commons.Constant;
import doc.commons.TableConstant;
import doc.db.dao.ClassDao;
import doc.db.dao.DocumentDao;

public class ClassSelectedAction extends ActionSupport implements SessionAware,  
ServletRequestAware, ServletResponseAware
{

	private int nodeId;
	private List<JnuDocument> doclist;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private int maxExportRows=2000;
	private int currentPage;
	
	private String message; 
	private Map att; 

	public int getNodeId()
	{
		return nodeId;
	}

	public void setNodeId(int nodeId)
	{
		this.nodeId = nodeId;
	}

	public String getNodeName()
	{
		ClassDao dao = new ClassDao();
		return dao.getClassName(nodeId);
	}
		
	public List<JnuDocument> getDoclist() 
	{
		return this.doclist;
	}

	public void setDoclist(List<JnuDocument> doclist) {
		this.doclist = doclist;
	}
	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map getAtt() {
		return att;
	}

	public void setAtt(Map att) {
		this.att = att;
	}
	

	public int getMaxExportRows() {
		return maxExportRows;
	}

	public void setMaxExportRows(int maxExportRows) {
		this.maxExportRows = maxExportRows;
	}
	
	public void setSession(Map att) {
		this.att = att;
		
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	//	添加filter到sql语句
    private String filterQuery(FilterSet filterSet, String sql)
    {
    	//SelectedDocsUtils.saveSelectedDocsIDs(request);
    	DocumentDao docDao = new DocumentDao();
        if (!filterSet.isFiltered() || filterSet.isCleared()) 
        {
            return sql;
        }       
        Filter filters[] = filterSet.getFilters();
        for (int i = 0; i < filters.length; i++)
        {
            Filter filter = filters[i];
            String property = filter.getProperty();
            String value = filter.getValue();
            sql = docDao.filterQuery(sql, property, value);
        }
        return sql;
    }
    
//    添加sort到sql语句
    private String sortQuery(Sort sort, String sql) 
    {
    	//SelectedDocsUtils.saveSelectedDocsIDs(request);
    	DocumentDao docDao = new DocumentDao();
        if (!sort.isSorted()) {
            String defaultSortOrder = docDao.getDefaultSortOrder();
            if (StringUtils.isNotBlank(defaultSortOrder)) {
                return sql + defaultSortOrder;
            }           
            return sql;
        }
        String property = sort.getProperty();
        String sortOrder = sort.getSortOrder();       
        return docDao.sortQuery(sql, property, sortOrder);
    }

// 获取记录总数
    public int getTotalRows(int nodeId,FilterSet filterSet, boolean isExported)
    {
    	DocumentDao docDao = new DocumentDao();
    	String modSql = docDao.getTotalSql();
    	if(nodeId != 1)
    	{
    		modSql = docDao.classQuery(modSql, nodeId);
    	}   
    	modSql = this.filterQuery(filterSet, modSql);
    	int totalRows = docDao.getTotalRows(modSql);
    	if (isExported && totalRows > maxExportRows) {
            totalRows = maxExportRows;
        }
    	return totalRows;
    }

//    获取文档列表
    public List<JnuDocument> getDocList(FilterSet filterSet,Sort sort,int rowEnd,int class_id)
    {
    	DocumentDao docDao = new DocumentDao();
    	String sql = docDao.getSql();
    	String modSql = sql;
    	if(nodeId != 1)
    	{
    		modSql = docDao.classQuery(sql, nodeId);
    	}
    	modSql = filterQuery(filterSet, modSql);
    	modSql = sortQuery(sort, modSql);
    	modSql = docDao.limitQuery(rowEnd, modSql);
    	return docDao.getDocList(modSql);
    }
    
	public String execute()
	{
    	DocumentDao docDao = new DocumentDao();
    	
   	
    	//SelectedDocsUtils.saveSelectedDocsIDs(request);
    	

    	
		Context context = new HttpServletRequestContext(request);
		LimitFactory limitFactory = new TableLimitFactory(context);
		Limit limit = new TableLimit(limitFactory);

		int totalRows = this.getTotalRows(nodeId, limit.getFilterSet(), limit.isExported());
		limit.setRowAttributes(totalRows, TableConstant.DEFAULT_ROWS_DISPLAYED);
		
		doclist = getDocList(limit.getFilterSet(), limit.getSort(), limit.getRowEnd(), nodeId);
		currentPage = limit.getPage();
		
		//request.removeAttribute(Constant.SELECTED_PRESIDENTS);
		
		request.setAttribute("doclist", doclist);
		request.setAttribute("totalRows", totalRows);
		
		
//		DocumentDao dd = new DocumentDao();
//		limit.setRowAttributes(dd.getTotalRows(nodeId), TableConstant.DEFAULT_ROWS_DISPLAYED);
//		
//		TablePage page = new TablePage();
//		page.setPageSize(limit.getCurrentRowsDisplayed());
//		page.setPage(limit.getPage());
//		
//
//		List<JnuDocument> doclist = dd.getDocList(nodeId, page);
//		page.setCount(dd.getTotalRows(nodeId));
//
//		request.setAttribute("doclist", doclist);
//		request.setAttribute("totalRows", page.getCount());
//	
//		request.setAttribute("nodeId", nodeId);


		return SUCCESS;
	}


	

}
