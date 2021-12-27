package com.socialindia.vo;

import java.io.Serializable;

public class MerchantCategoryTblVO implements Serializable{
	
	private Integer mrchCategoryId;
	private String mrchCategoryName;
	private Integer status;
	private Integer entryBy;
	private String entryDatetime;
	private String modifyDatetime;
	private String imagename;
	private String desc;
	
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getMrchCategoryId() {
		return mrchCategoryId;
	}
	public void setMrchCategoryId(Integer mrchCategoryId) {
		this.mrchCategoryId = mrchCategoryId;
	}
	public String getMrchCategoryName() {
		return mrchCategoryName;
	}
	public void setMrchCategoryName(String mrchCategoryName) {
		this.mrchCategoryName = mrchCategoryName;
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
	public String getEntryDatetime() {
		return entryDatetime;
	}
	public void setEntryDatetime(String entryDatetime) {
		this.entryDatetime = entryDatetime;
	}
	public String getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(String modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	
}
