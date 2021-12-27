package com.socialindia.generalmgnt.persistance;

import java.util.Date;

import com.letspay.login.persistense.UserMasterTblVo;



public class StaffSlryTblVO {
	private Integer staffSlryId;
	private UserMasterTblVo entryBy;
	private StaffMasterTblVo staffID;
	//private Double monthlySalary;
	//private Double extraWages;
	private String monthlySalary;
	private String extraWages;
	private Integer status;
	private Date entryDatetime;
	private Date modifyDatetime;
	
	
	public Integer getStaffSlryId() {
		return this.staffSlryId;
	}

	public void setStaffSlryId(Integer staffSlryId) {
		this.staffSlryId = staffSlryId;
	}

	public UserMasterTblVo getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(UserMasterTblVo entryBy) {
		this.entryBy = entryBy;
	}
	public StaffMasterTblVo getStaffID() {
		return staffID;
	}

	public void setStaffID(StaffMasterTblVo staffID) {
		this.staffID = staffID;
	}

	
	public String getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(String monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public String getExtraWages() {
		return extraWages;
	}

	public void setExtraWages(String extraWages) {
		this.extraWages = extraWages;
	}

	/*

	public Double getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(Double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public Double getExtraWages() {
		return extraWages;
	}

	public void setExtraWages(Double extraWages) {
		this.extraWages = extraWages;
	}
*/
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
