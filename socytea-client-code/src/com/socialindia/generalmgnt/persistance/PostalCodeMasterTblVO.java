package com.socialindia.generalmgnt.persistance;

import java.io.Serializable;

import com.letspay.vo.CityMasterTblVo;

public class PostalCodeMasterTblVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer pstlId;
	private CityMasterTblVo cityId;
	private String pstlCode;
	
	public PostalCodeMasterTblVO(int indx, String vl) {
		// TODO Auto-generated constructor stub
		this.pstlId =indx;
		this.pstlCode=vl;
	}
	
	
	public Integer getPstlId() {
		return pstlId;
	}


	public void setPstlId(Integer pstlId) {
		this.pstlId = pstlId;
	}


	public CityMasterTblVo getCityId() {
		return cityId;
	}
	public void setCityId(CityMasterTblVo cityId) {
		this.cityId = cityId;
	}
	public String getPstlCode() {
		return pstlCode;
	}
	public void setPstlCode(String pstlCode) {
		this.pstlCode = pstlCode;
	}
	
}
