package doc.beo;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import doc.db.dao.ClassDao;


public class JnuDocument 
{
	private int id;
	private String name;
	private Timestamp add_time;
	private String description;
	private int file_id;
	private String number;
	private Date doc_time;
	private int class_id;
	private String keywords;
	private String class_name;
	private int subclass_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getDoc_time() {
		return doc_time;
	}
	public void setDoc_time(Date doc_time) {
		this.doc_time = doc_time;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getKeywords()
	{
		return keywords;
	}
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public int getSubclass_id() {
		return subclass_id;
	}
	public void setSubclass_id(int subclassId) {
		subclass_id = subclassId;
	}
	
	

}
