package com.socialindia.eNewsmgmt;

import java.io.Serializable;
import java.util.Date;


public class EeNewsDocTblVO implements Serializable {

	private Integer EnewuniqId;
	private Integer Enewid;
	private String imgname;
	private Integer status;
	private Integer entryBy;
	private String entryDatetime;
	private String filepath;
	
	public EeNewsDocTblVO(String filepath, String filename) {
		// TODO Auto-generated constructor stub
		this.filepath=filepath;
		this.imgname=filename;
		
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Integer getEnewuniqId() {
		return EnewuniqId;
	}
	public void setEnewuniqId(Integer enewuniqId) {
		EnewuniqId = enewuniqId;
	}
	
	
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getEntryBy() {
		return entryBy;
	}
	public void setEntryBy(Integer entryBy) {
		this.entryBy = entryBy;
	}
	public Integer getEnewid() {
		return Enewid;
	}
	public void setEnewid(Integer enewid) {
		Enewid = enewid;
	}
	public String getEntryDatetime() {
		return entryDatetime;
	}
	public void setEntryDatetime(String entryDatetime) {
		this.entryDatetime = entryDatetime;
	}
	
	
	
	
}
