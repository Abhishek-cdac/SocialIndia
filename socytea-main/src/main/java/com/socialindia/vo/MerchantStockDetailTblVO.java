package com.socialindia.vo;

import java.io.Serializable;

public class MerchantStockDetailTblVO implements Serializable{
	
	private Integer uniqId;
	private Integer mrchntId;
	private Integer mrchCategoryId;
	private String quantity;
	private String typeName;
	private String cuisines;
	private String power;
	private String companyName;
	private Integer entryBy;
	private String entryDatetime;
	private String modifyDatetime;
	public Integer getUniqId() {
		return uniqId;
	}
	public void setUniqId(Integer uniqId) {
		this.uniqId = uniqId;
	}
	public Integer getMrchntId() {
		return mrchntId;
	}
	public void setMrchntId(Integer mrchntId) {
		this.mrchntId = mrchntId;
	}
	public Integer getMrchCategoryId() {
		return mrchCategoryId;
	}
	public void setMrchCategoryId(Integer mrchCategoryId) {
		this.mrchCategoryId = mrchCategoryId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCuisines() {
		return cuisines;
	}
	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
