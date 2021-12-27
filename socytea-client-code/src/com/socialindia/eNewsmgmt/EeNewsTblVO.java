package com.socialindia.eNewsmgmt;

import java.io.Serializable;




public class EeNewsTblVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer ivrBnENEWS_ID;	
	private String ivrBnTITLE;	
	private String ivrBnDESCRIPTION;
	private String ivrBnSHRTDESCRIPTION;
	
	private String ivrBnSTATUS;	
	private String ivrBnENTRY_BY;	
	private String ivrBnENTRY_DATETIME;	
	private String ivrBnUAMObj;
	private String ivrBnGRPObj;
	private String societyname;
	private String filepath;
	private String filename;
	public EeNewsTblVO(String eNewstitle, String eNewsdesc,
			String eNewsposedby, Integer eNewsid, String eNewssocietyname,
			 String filepath,String filename) {
		this.ivrBnTITLE =eNewstitle;
		this.ivrBnDESCRIPTION =eNewsdesc;
		this.ivrBnENTRY_BY =eNewsposedby;
		this.ivrBnENEWS_ID =eNewsid;
		this.societyname =eNewssocietyname;
		this.filepath =filepath;
		this.filename =filename;
		// TODO Auto-generated constructor stub
	}
	
	
	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getSocietyname() {
		return societyname;
	}

	public void setSocietyname(String societyname) {
		this.societyname = societyname;
	}

	public Integer getIvrBnENEWS_ID() {
		return ivrBnENEWS_ID;
	}
	public void setIvrBnENEWS_ID(Integer ivrBnENEWS_ID) {
		this.ivrBnENEWS_ID = ivrBnENEWS_ID;
	}
	public String getIvrBnTITLE() {
		return ivrBnTITLE;
	}
	public void setIvrBnTITLE(String ivrBnTITLE) {
		this.ivrBnTITLE = ivrBnTITLE;
	}
	public String getIvrBnDESCRIPTION() {
		return ivrBnDESCRIPTION;
	}
	public void setIvrBnDESCRIPTION(String ivrBnDESCRIPTION) {
		this.ivrBnDESCRIPTION = ivrBnDESCRIPTION;
	}
	
	public String getIvrBnSHRTDESCRIPTION() {
		return ivrBnSHRTDESCRIPTION;
	}


	public void setIvrBnSHRTDESCRIPTION(String ivrBnSHRTDESCRIPTION) {
		this.ivrBnSHRTDESCRIPTION = ivrBnSHRTDESCRIPTION;
	}


	public String getIvrBnSTATUS() {
		return ivrBnSTATUS;
	}
	public void setIvrBnSTATUS(String ivrBnSTATUS) {
		this.ivrBnSTATUS = ivrBnSTATUS;
	}
	public String getIvrBnENTRY_BY() {
		return ivrBnENTRY_BY;
	}
	public void setIvrBnENTRY_BY(String ivrBnENTRY_BY) {
		this.ivrBnENTRY_BY = ivrBnENTRY_BY;
	}
	public String getIvrBnENTRY_DATETIME() {
		return ivrBnENTRY_DATETIME;
	}
	public void setIvrBnENTRY_DATETIME(String ivrBnENTRY_DATETIME) {
		this.ivrBnENTRY_DATETIME = ivrBnENTRY_DATETIME;
	}
	public String getIvrBnUAMObj() {
		return ivrBnUAMObj;
	}
	public void setIvrBnUAMObj(String ivrBnUAMObj) {
		this.ivrBnUAMObj = ivrBnUAMObj;
	}
	public String getIvrBnGRPObj() {
		return ivrBnGRPObj;
	}
	public void setIvrBnGRPObj(String ivrBnGRPObj) {
		this.ivrBnGRPObj = ivrBnGRPObj;
	}
	
	
	
}
