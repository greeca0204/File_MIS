package doc.beo;

import java.util.List;

import doc.db.dao.ClassDao;

public class ClassList 
{
	//获取一级分类
	public static List<ClassNodes> getList()
	{
		ClassDao cd = new ClassDao();
		List<ClassNodes> list = cd.getAllClassNodes();
		
		return list;
	}

}
