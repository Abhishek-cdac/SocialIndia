package com.socialindia.material;

// default package
// Generated Aug 16, 2016 3:19:38 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;


/**
 * MvpMaterialTbl generated by hbm2java
 */
public class MvpMaterialTbl implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer materialId;
	private int societyId;
	private int materialCategoryId;
	private String materialName;
	private String materialDesc;
	private Integer materialQnty;
	private Integer totalQnty;
	private Integer usedQnty;
	private String materialPrice;
	private Date purchaseDate;
	private Integer status;
	private Integer entryBy;
	private Date entryDatetime;
	private Date modifyDatetime;
	
	private String categoryName;
	private String societyName;
	private String purDate;

	public MvpMaterialTbl() {
	}

	public MvpMaterialTbl(int materialId,
			String categoryName, String materialName,
			Integer materialQnty, Integer totalQnty,
			Integer usedQnty, String materialPrice) {
		this.materialId = materialId;
		this.categoryName = categoryName;
		this.materialName = materialName;
		this.materialQnty = materialQnty;
		this.totalQnty = totalQnty;
		this.usedQnty = usedQnty;
		this.materialPrice = materialPrice;
	}

	public Integer getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}


	

	public int getSocietyId() {
		return societyId;
	}

	public void setSocietyId(int societyId) {
		this.societyId = societyId;
	}

	public int getMaterialCategoryId() {
		return materialCategoryId;
	}

	public void setMaterialCategoryId(int materialCategoryId) {
		this.materialCategoryId = materialCategoryId;
	}

	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialDesc() {
		return this.materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public Integer getMaterialQnty() {
		return this.materialQnty;
	}

	public void setMaterialQnty(Integer materialQnty) {
		this.materialQnty = materialQnty;
	}

	public Integer getTotalQnty() {
		return this.totalQnty;
	}

	public void setTotalQnty(Integer totalQnty) {
		this.totalQnty = totalQnty;
	}

	public Integer getUsedQnty() {
		return this.usedQnty;
	}

	public void setUsedQnty(Integer usedQnty) {
		this.usedQnty = usedQnty;
	}


	public String getMaterialPrice() {
		return materialPrice;
	}

	public void setMaterialPrice(String materialPrice) {
		this.materialPrice = materialPrice;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
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

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPurDate() {
		return purDate;
	}

	public void setPurDate(String purDate) {
		this.purDate = purDate;
	}

}
