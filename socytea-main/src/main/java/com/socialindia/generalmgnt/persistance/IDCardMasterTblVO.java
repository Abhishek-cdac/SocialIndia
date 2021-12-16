package com.socialindia.generalmgnt.persistance;

import java.io.Serializable;

public class IDCardMasterTblVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer iVOcradid;
	private String iVOcradname;
	private Integer iVOcardstatus;
	
	public IDCardMasterTblVO(int indx, String vl) {
		this.iVOcradid = indx;
		this.iVOcradname =vl;
		// TODO Auto-generated constructor stub
	}
	public IDCardMasterTblVO(Integer idcardid, String idcardName,
			Integer idcardstatus) {
		this.iVOcradid = idcardid;
		this.iVOcradname =idcardName;
		this.iVOcardstatus =idcardstatus;
		// TODO Auto-generated constructor stub
	}
	public Integer getiVOcradid() {
		return iVOcradid;
	}
	public void setiVOcradid(Integer iVOcradid) {
		this.iVOcradid = iVOcradid;
	}
	public String getiVOcradname() {
		return iVOcradname;
	}
	public void setiVOcradname(String iVOcradname) {
		this.iVOcradname = iVOcradname;
	}
	public Integer getiVOcardstatus() {
		return iVOcardstatus;
	}
	public void setiVOcardstatus(Integer iVOcardstatus) {
		this.iVOcardstatus = iVOcardstatus;
	}
	
	
}
