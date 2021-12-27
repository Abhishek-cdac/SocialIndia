package com.socialindia.generalmgnt.persistance;

import java.io.Serializable;

public class StaffCategoryMasterTblVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer iVOstaffcategid;
	private String iVOstaffcategory;
	private Integer iVOstaffcategsts;
	public StaffCategoryMasterTblVO(int indx, String vl) {
		this.iVOstaffcategid = indx;
		this.iVOstaffcategory =vl;
		// TODO Auto-generated constructor stub
	}
	public StaffCategoryMasterTblVO(Integer categoryid, String categoryName,
			Integer categorystatus) {
		this.iVOstaffcategid = categoryid;
		this.iVOstaffcategory =categoryName;
		this.iVOstaffcategsts=categorystatus;
		// TODO Auto-generated constructor stub
	}
	public Integer getiVOstaffcategid() {
		return iVOstaffcategid;
	}
	public void setiVOstaffcategid(Integer iVOstaffcategid) {
		this.iVOstaffcategid = iVOstaffcategid;
	}
	public String getiVOstaffcategory() {
		return iVOstaffcategory;
	}
	public void setiVOstaffcategory(String iVOstaffcategory) {
		this.iVOstaffcategory = iVOstaffcategory;
	}
	public Integer getiVOstaffcategsts() {
		return iVOstaffcategsts;
	}
	public void setiVOstaffcategsts(Integer iVOstaffcategsts) {
		this.iVOstaffcategsts = iVOstaffcategsts;
	}
	
}
