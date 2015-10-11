package doc.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.Department;
import doc.beo.Role;
import doc.beo.Sys_users;
import doc.commons.CommonMethod;
import doc.db.dao.DepartmentDao;
import doc.db.dao.RoleDao;
import doc.db.dao.Sys_usersDAO;

public class UserAdminAction extends ActionSupport {
	/**
	 * 增加
	 * @return
	 * @throws Exception
	 */
	public String addUserPre() throws Exception {
		DepartmentDao deptdao = new DepartmentDao();
		RoleDao roledao = new RoleDao();
		deptList = deptdao.getAll();
		roleList = roledao.getAll();
		return SUCCESS;
	}

	public String addUser() throws Exception {
		Sys_usersDAO userdao = new Sys_usersDAO();
		PrintWriter out = CommonMethod.getWriter();
		if(userdao.addUser(user)){
			out.print("<script>alert('增加成功！')</script>");
			out.print("<script>window.location='" + CommonMethod.getBasePath() + "admin/userList.action'</script>");
			out.close();
		}else{
			out.print("<script>alert('增加失败！')</script>");
			out.print("<script>history.go(-1);</script>");
			out.close();
		}
		return NONE;
	}
	
	/**
	 * 列表
	 * @return
	 * @throws Exception
	 */
	public String showUserList() throws Exception {
		Sys_usersDAO userdao = new Sys_usersDAO();
		row = userdao.countOfUser(type, keyword);
		curPage = curPage<1 ? 1:curPage;
		totalPage = row%pageSize==0? row/pageSize : 1+row/pageSize;
		curPage = curPage >= totalPage? totalPage : curPage;
		userList = userdao.searchUser(type, keyword, pageSize, curPage);
		return SUCCESS;
	}
	
	
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String editUserPre() throws Exception {
		DepartmentDao deptdao = new DepartmentDao();
		RoleDao roledao = new RoleDao();
		deptList = deptdao.getAll();
		roleList = roledao.getAll();
		Sys_usersDAO userdao = new Sys_usersDAO();
		user = userdao.getUserById(id);
		return SUCCESS;
	}
	public String editUser() throws Exception {
		Sys_usersDAO userdao = new Sys_usersDAO();
		PrintWriter out = CommonMethod.getWriter();
		if(userdao.updateUser(user)){
			out.print("<script>alert('修改成功！')</script>");
			out.print("<script>window.location='" + CommonMethod.getBasePath() + "admin/userList.action?type=" + type + "&keyword=" + keyword + "&curPage=" + curPage + "'</script>");
			out.close();
		}else{
			out.print("<script>alert('修改失败！')</script>");
			out.print("<script>history.go(-1);</script>");
			out.close();
		}
		return NONE;
	}
	
	public String editUserPwd() throws Exception {
		Sys_usersDAO userdao = new Sys_usersDAO();
		PrintWriter out = CommonMethod.getWriter();
		if(userdao.updatePWD(id, password)){
			out.print("<script>alert('修改成功！')</script>");
			out.print("<script>window.location='" + CommonMethod.getBasePath() + "admin/userList.action?type=" + type + "&keyword=" + keyword + "&curPage=" + curPage + "'</script>");
			out.close();
		}else{
			out.print("<script>alert('修改失败！')</script>");
			out.print("<script>history.go(-1);</script>");
			out.close();
		}
		return NONE;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delUser() throws Exception {
		Sys_usersDAO userdao = new Sys_usersDAO();
		user = userdao.getUserById(id);
		PrintWriter out = CommonMethod.getWriter();
		if(userdao.delUser(id)){
			out.print("<script>alert('删除成功！')</script>");
			out.print("<script>window.location='" + CommonMethod.getBasePath() + "admin/userList.action?type=" + type + "&keyword=" + keyword + "&curPage=" + curPage + "'</script>");
			out.close();
		}else{
			out.print("<script>alert('删除失败！')</script>");
			out.print("<script>history.go(-1);</script>");
			out.close();
		}
		return NONE;
	}
	
	/**
	 * 判断用户名是否可以注册
	 * @return
	 * @throws Exception
	 */
	public String checkAccname() throws Exception {
		Sys_usersDAO userdao = new Sys_usersDAO();
		System.out.println(userdao.getUserByAccname(accname));
		if(null != userdao.getUserByAccname(accname)){
			inputStream = new ByteArrayInputStream("false".getBytes());
		}else{
			inputStream = new ByteArrayInputStream("true".getBytes());
		}
		return SUCCESS;	
	}
	
	private Sys_users user;
	private List<Sys_users> userList;
	private String type="";
	private String keyword="";
	private int pageSize=15;
	private int curPage;
	private int totalPage;
	private int row;
	private int id;	
	private List<Department> deptList;
	private List<Role> roleList;
	private String accname;
	private InputStream inputStream;
	private String password;
	
	public Sys_users getUser() {
		return user;
	}

	public void setUser(Sys_users user) {
		this.user = user;
	}

	public List<Sys_users> getUserList() {
		return userList;
	}

	public void setUserList(List<Sys_users> userList) {
		this.userList = userList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Department> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getAccname() {
		return accname;
	}

	public void setAccname(String accname) {
		this.accname = accname;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
