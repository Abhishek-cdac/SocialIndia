package com.socialindia.emailtemplate;

public class Emailtemplatevo implements java.io.Serializable{
	private Integer tempid;
	private String subject;
	private String subcontent;
	public Emailtemplatevo(int tempid,String subject,String subcontent) {
		this.tempid = tempid;
		this.subject = subject;
		this.subcontent = subcontent;
	
	}
	
	public Integer getTempid() {
		return tempid;
	}
	public void setTempid(Integer tempid) {
		this.tempid = tempid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubcontent() {
		return subcontent;
	}
	public void setSubcontent(String subcontent) {
		this.subcontent = subcontent;
	}

	
}
