package com.socialindia.residentmgmt;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FlatMasterTblVO  implements Serializable{
	
	private Integer flat_id;
	private Integer usrid;
	private String wingsname;
	private String flatno;
	private Integer status;
	private Integer entryby;
	private Date entryDatetime;
	private Date modifyDatetime;
	//private List <GetWingsblockVo> ivrSocietwingsList;
	public FlatMasterTblVO(String wings_name, String flat_name) {
		this.wingsname=wings_name;
		this.flatno =flat_name;
		//this.ivrSocietwingsList =ivrSocietwingsList;
		// TODO Auto-generated constructor stub
	}
	public Integer getFlat_id() {
		return flat_id;
	}
	public void setFlat_id(Integer flat_id) {
		this.flat_id = flat_id;
	}
	public Integer getUsrid() {
		return usrid;
	}
	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}
	public String getWingsname() {
		return wingsname;
	}
	public void setWingsname(String wingsname) {
		this.wingsname = wingsname;
	}
	public String getFlatno() {
		return flatno;
	}
	public void setFlatno(String flatno) {
		this.flatno = flatno;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getEntryby() {
		return entryby;
	}
	public void setEntryby(Integer entryby) {
		this.entryby = entryby;
	}
	public Date getEntryDatetime() {
		return entryDatetime;
	}
	public void setEntryDatetime(Date entryDatetime) {
		this.entryDatetime = entryDatetime;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
//	public List<GetWingsblockVo> getIvrSocietwingsList() {
//		return ivrSocietwingsList;
//	}
//	public void setIvrSocietwingsList(List<GetWingsblockVo> ivrSocietwingsList) {
//		this.ivrSocietwingsList = ivrSocietwingsList;
//	}
	

}
