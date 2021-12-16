package com.socialindia.vo;

import java.io.Serializable;

public class SkillMasterTblVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ivrBnSKILL_ID;
	private String ivrBnSKILL_NAME;
	private String ivrBnACT_STAT;
	private String ivrBnCategoryid;
	public SkillMasterTblVO(String indx, String vl) {
		// TODO Auto-generated constructor stub
		this.ivrBnSKILL_ID =indx;
		this.ivrBnSKILL_NAME=vl;
	}
	public String getIvrBnSKILL_ID() {
		return ivrBnSKILL_ID;
	}
	public void setIvrBnSKILL_ID(String ivrBnSKILL_ID) {
		this.ivrBnSKILL_ID = ivrBnSKILL_ID;
	}
	public String getIvrBnSKILL_NAME() {
		return ivrBnSKILL_NAME;
	}
	public void setIvrBnSKILL_NAME(String ivrBnSKILL_NAME) {
		this.ivrBnSKILL_NAME = ivrBnSKILL_NAME;
	}
	public String getIvrBnACT_STAT() {
		return ivrBnACT_STAT;
	}
	public void setIvrBnACT_STAT(String ivrBnACT_STAT) {
		this.ivrBnACT_STAT = ivrBnACT_STAT;
	}
	public String getIvrBnCategoryid() {
		return ivrBnCategoryid;
	}
	public void setIvrBnCategoryid(String ivrBnCategoryid) {
		this.ivrBnCategoryid = ivrBnCategoryid;
	}
	
	
	
	
}
