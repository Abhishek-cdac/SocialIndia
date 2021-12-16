package com.socialindia.vo;

import java.util.Date;


public class AuditlogTblVO {
	
	private Integer auditId;
	private String operations;
	private String logCode;
	private Integer entryBy;
	private Date entryDatetime;
	private Date modifyDatetime;
	private Integer auditBy;
	private String auditByName;
	private String entryDatetimeformated;
	private String modifyDatetimeformated;
	
	public AuditlogTblVO(){}

	public AuditlogTblVO(Integer recentactid, String recentactdesc,
			Integer recentactentryby,String entryDatetimeformated) {
		this.auditBy=recentactid;
		this.operations=recentactdesc;
		this.entryBy=recentactentryby;
		this.entryDatetimeformated=entryDatetimeformated;
		// TODO Auto-generated constructor stub
	}

	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}

	public String getLogCode() {
		return logCode;
	}

	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}

	public Integer getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(Integer entryBy) {
		this.entryBy = entryBy;
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
	public Integer getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(Integer auditBy) {
		this.auditBy = auditBy;
	}

	public String getAuditByName() {
		return auditByName;
	}

	public void setAuditByName(String auditByName) {
		this.auditByName = auditByName;
	}

	public String getEntryDatetimeformated() {
		return entryDatetimeformated;
	}

	public void setEntryDatetimeformated(String entryDatetimeformated) {
		this.entryDatetimeformated = entryDatetimeformated;
	}

	public String getModifyDatetimeformated() {
		return modifyDatetimeformated;
	}

	public void setModifyDatetimeformated(String modifyDatetimeformated) {
		this.modifyDatetimeformated = modifyDatetimeformated;
	}
	

}
