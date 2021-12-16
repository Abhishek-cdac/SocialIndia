package com.socialindia.generalmgnt.persistance;

import java.util.Date;

public class StaffWrkTblVO {
	private Integer staffWrkId;
	private StaffMasterTblVo staffId;
	private String workDtl;
	private Date workStartDate;
	private Date workEndDate;
	private Integer status;
	private Integer entryBy;
	private Date entryDatetime;
	private Date modifyDatetime;

	
	public Integer getStaffWrkId() {
		return staffWrkId;
	}

	public void setStaffWrkId(Integer staffWrkId) {
		this.staffWrkId = staffWrkId;
	}

	

	public StaffMasterTblVo getStaffId() {
		return staffId;
	}

	public void setStaffId(StaffMasterTblVo staffId) {
		this.staffId = staffId;
	}

	public String getWorkDtl() {
		return this.workDtl;
	}

	public void setWorkDtl(String workDtl) {
		this.workDtl = workDtl;
	}

	public Date getWorkStartDate() {
		return this.workStartDate;
	}

	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}

	public Date getWorkEndDate() {
		return this.workEndDate;
	}

	public void setWorkEndDate(Date workEndDate) {
		this.workEndDate = workEndDate;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getEntryBy() {
		return this.entryBy;
	}

	public void setEntryBy(Integer entryBy) {
		this.entryBy = entryBy;
	}

	public Date getEntryDatetime() {
		return this.entryDatetime;
	}

	public void setEntryDatetime(Date entryDatetime) {
		this.entryDatetime = entryDatetime;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

}
