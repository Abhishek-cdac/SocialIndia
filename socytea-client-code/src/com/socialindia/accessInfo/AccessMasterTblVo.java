package com.socialindia.accessInfo;

import java.io.Serializable;

public class AccessMasterTblVo implements Serializable{
	//private Integer usr_Id;
	private String usr_Id;
	private String ipAddress;
	private Integer accesscount;
	private String hostName;
	private String language;
	private String port_no;
	private String entryTime;
	private String gmtTime;
	private String updateTime;
	
	private static final long serialVersionUID = 1L;

	
	public AccessMasterTblVo(String usr_Id, String ipAddress, int accesscount,
			String language, String updateTime, String entryTime,
			String gmtTime) {
		// TODO Auto-generated constructor stub
		this.usr_Id =usr_Id;
		this.ipAddress =ipAddress;		
		this.accesscount=accesscount;
		this.language=language;
		//this.port_no =port_no;
		this.entryTime=entryTime;
		this.gmtTime=gmtTime;
		this.updateTime =updateTime;
				
	}
	
	

	public String getUsr_Id() {
		return usr_Id;
	}



	public void setUsr_Id(String usr_Id) {
		this.usr_Id = usr_Id;
	}



	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getAccesscount() {
		return accesscount;
	}

	public void setAccesscount(Integer accesscount) {
		this.accesscount = accesscount;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPort_no() {
		return port_no;
	}

	public void setPort_no(String port_no) {
		this.port_no = port_no;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getGmtTime() {
		return gmtTime;
	}

	public void setGmtTime(String gmtTime) {
		this.gmtTime = gmtTime;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	

	
	
}
