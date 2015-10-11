package doc.db.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import doc.beo.JnuDocument;

public class ExcelDao
{
	//从Excel文件中获取数据
	public List<JnuDocument> getDocData(File file)
	{
		List<JnuDocument> dataList = new ArrayList<JnuDocument>();

		try
		{
			Workbook wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			for(int i=1;i<sheet.getRows();i++)
			{
				JnuDocument doc = new JnuDocument();
				int cols = sheet.getColumns();
				for(int j=0;j<cols;j++)
				{
					System.out.println(sheet.getCell(j, i).getContents());
				}
				if(cols > 0)
				{
					doc.setNumber(sheet.getCell(0, i).getContents());
				}
				if(cols > 1)
				{
					doc.setName(sheet.getCell(1, i).getContents());
				}
				if(cols > 2)
				{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					DateCell dc = (DateCell) sheet.getCell(2, i);
					String myDate = format.format(dc.getDate());
					System.out.println(myDate);
					doc.setDoc_time(Date.valueOf(myDate));
				}
				if(cols > 3)
				{
					doc.setClass_id(Integer.parseInt(sheet.getCell(3, i).getContents()));
				}
				if(cols > 4)
				{
					doc.setSubclass_id(Integer.parseInt(sheet.getCell(4, i).getContents()));
				}
				if(cols > 5)
				{
					doc.setKeywords(sheet.getCell(5, i).getContents());
				}
				if(cols > 6)
				{
					doc.setDescription(sheet.getCell(6, i).getContents());
				}
					
				dataList.add(doc);
			}			
		} catch (BiffException e)
		{
			System.out.println("获取数据流错误：" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e)
		{
			System.out.println("数据IO错误：" + e.getMessage());
			e.printStackTrace();
		}
		return dataList;		
	}
	
	//数据导入
	public boolean importDoc(File file)
	{
		boolean result = true;
		List<JnuDocument> docList = this.getDocData(file);
		for(JnuDocument doc : docList)
		{
			DocumentDao dd = new DocumentDao();
			if(!dd.addDocument(doc))
			{
				result = false;
				System.out.println("导入文档错误：" + doc.getName());
			}			
		}
		return result;		
	}
	
	
	public static void main(String[] args)
	{
		ExcelDao test = new ExcelDao();
		test.getDocData((new File("E:\\document.xls")));
	}

}
