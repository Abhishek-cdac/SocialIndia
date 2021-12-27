package com.socialindia.generalmgnt.persistance;

import java.io.Serializable;

public class CategoryMasterTblVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer iVOCATEGORY_ID;
	private String iVOCATEGORY_NAME;
	private Integer iVOACT_STAT;
	private String iVOCTG_SHTFORM;
	private String imageNameWeb;
	
	public CategoryMasterTblVO(int indx, String vl) {
		this.iVOCATEGORY_ID =indx;
		this.iVOCATEGORY_NAME=vl;
		// TODO Auto-generated constructor stub
	}
	
	public String getImageNameWeb() {
		return imageNameWeb;
	}

	public void setImageNameWeb(String imageNameWeb) {
		this.imageNameWeb = imageNameWeb;
	}

	public CategoryMasterTblVO(Integer categoryid, String categoryName,
			Integer categorystatus) {
		// TODO Auto-generated constructor stub
		this.iVOCATEGORY_ID =categoryid;
		this.iVOCATEGORY_NAME=categoryName;
		this.iVOACT_STAT=categorystatus;
	}

	public Integer getiVOCATEGORY_ID() {
		return iVOCATEGORY_ID;
	}
	public void setiVOCATEGORY_ID(Integer iVOCATEGORY_ID) {
		this.iVOCATEGORY_ID = iVOCATEGORY_ID;
	}
	public String getiVOCATEGORY_NAME() {
		return iVOCATEGORY_NAME;
	}
	public void setiVOCATEGORY_NAME(String iVOCATEGORY_NAME) {
		this.iVOCATEGORY_NAME = iVOCATEGORY_NAME;
	}
	public Integer getiVOACT_STAT() {
		return iVOACT_STAT;
	}
	public void setiVOACT_STAT(Integer iVOACT_STAT) {
		this.iVOACT_STAT = iVOACT_STAT;
	}
	public String getiVOCTG_SHTFORM() {
		return iVOCTG_SHTFORM;
	}
	public void setiVOCTG_SHTFORM(String iVOCTG_SHTFORM) {
		this.iVOCTG_SHTFORM = iVOCTG_SHTFORM;
	}
	
	
}
