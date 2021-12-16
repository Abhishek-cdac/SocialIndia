package com.socialindia.vo;

import java.io.Serializable;
import java.util.Date;

public class ReportIssueTblVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer repId;
	private String userId;
	private String parentId;
	private String emailId;
	private String mobileNo;
	private String name;
	private String descr;
	private String reportTo;
	private String reportToType;
	private String issueContactus;	
	private String entryBy;
	private String status;
	private String entryDatetime;
	private Date modyDatetime;

	public ReportIssueTblVO() {
	}

	public Integer getRepId() {
		return repId;
	}

	public void setRepId(Integer repId) {
		this.repId = repId;
	}
	
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getReportTo() {
		return reportTo;
	}

	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}

	public String getReportToType() {
		return reportToType;
	}

	public void setReportToType(String reportToType) {
		this.reportToType = reportToType;
	}

	public String getIssueContactus() {
		return issueContactus;
	}

	public void setIssueContactus(String issueContactus) {
		this.issueContactus = issueContactus;
	}

	public String getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEntryDatetime() {
		return entryDatetime;
	}

	public void setEntryDatetime(String entryDatetime) {
		this.entryDatetime = entryDatetime;
	}

	public Date getModyDatetime() {
		return modyDatetime;
	}

	public void setModyDatetime(Date modyDatetime) {
		this.modyDatetime = modyDatetime;
	}
	
	

}
