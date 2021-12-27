package com.socialindia.vo;

import java.util.Date;


public class DocumentShareTblVO {
	
	private int documentShareId;
	private int documentShareuserId;
	private int status;
	private Date entryDatetime;
	private Date modifyDatetime;
	private String documentShareFName;
	private String documentShareLName;
	private String documentShareEmail;
	
	
	public int getDocumentShareId() {
		return documentShareId;
	}
	public void setDocumentShareId(int documentShareId) {
		this.documentShareId = documentShareId;
	}
	public int getDocumentShareuserId() {
		return documentShareuserId;
	}
	public void setDocumentShareuserId(int documentShareuserId) {
		this.documentShareuserId = documentShareuserId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getEntryDatetime() {
		return entryDatetime;
	}
	public void setEntryDatetime(Date entryDatetime) {
		this.entryDatetime = entryDatetime;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public String getDocumentShareFName() {
		return documentShareFName;
	}
	public void setDocumentShareFName(String documentShareFName) {
		this.documentShareFName = documentShareFName;
	}
	public String getDocumentShareLName() {
		return documentShareLName;
	}
	public void setDocumentShareLName(String documentShareLName) {
		this.documentShareLName = documentShareLName;
	}
	public String getDocumentShareEmail() {
		return documentShareEmail;
	}
	public void setDocumentShareEmail(String documentShareEmail) {
		this.documentShareEmail = documentShareEmail;
	}
	
	

}
