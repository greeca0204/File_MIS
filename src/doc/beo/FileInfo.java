package doc.beo;

import java.sql.Date;
import java.sql.Timestamp;


public class FileInfo 
{
	private int id;	
	private String name;	
	private String fileName;	
	private long size;	
	private String type;
	private Timestamp upload_time;


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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {

		this.fileName = fileName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Timestamp upload_time) {
		this.upload_time = upload_time;
	}

	
	

	
}
