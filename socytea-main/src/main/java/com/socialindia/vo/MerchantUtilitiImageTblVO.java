package com.socialindia.vo;

import java.util.Date;


public class MerchantUtilitiImageTblVO {
	
	private Integer merchantImageId;
	private String imageName;
	private Integer statusFlag;
	private String entryDatetime;
	private String modifyDatetime;
	private Integer merchantUtilId;
	private String docDateFolderName;
	private String imagePath;
	
	public MerchantUtilitiImageTblVO(){}

	public Integer getMerchantImageId() {
		return merchantImageId;
	}

	public void setMerchantImageId(Integer merchantImageId) {
		this.merchantImageId = merchantImageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
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

	public Integer getMerchantUtilId() {
		return merchantUtilId;
	}

	public void setMerchantUtilId(Integer merchantUtilId) {
		this.merchantUtilId = merchantUtilId;
	}

	public String getDocDateFolderName() {
		return docDateFolderName;
	}

	public void setDocDateFolderName(String docDateFolderName) {
		this.docDateFolderName = docDateFolderName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	

}
