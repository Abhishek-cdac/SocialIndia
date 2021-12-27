package com.socialindia.residentmgmt;

public class GetWingsblockVo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wingsid;
	private String wingsname;
	private String subcontent;
	public GetWingsblockVo(String wingsidd, String wingsname, String subcontent){
		this.wingsid = wingsidd;
		this.wingsname = wingsname;
		this.subcontent = subcontent;
	}
	public String getWingsid() {
		return wingsid;
	}
	public void setWingsid(String wingsid) {
		this.wingsid = wingsid;
	}
	public String getWingsname() {
		return wingsname;
	}
	public void setWingsname(String wingsname) {
		this.wingsname = wingsname;
	}
	public String getSubcontent() {
		return subcontent;
	}
	public void setSubcontent(String subcontent) {
		this.subcontent = subcontent;
	}
	
}
