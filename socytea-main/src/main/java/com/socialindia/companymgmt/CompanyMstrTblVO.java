package com.socialindia.companymgmt;

import java.util.Date;



public class CompanyMstrTblVO {
	private Integer companyId;
	private String stateId;
	private String pstlId;
	private String cityId;
	private String countryId;
	private String companyName;
	private String companyUniqId;
	private String companyEmail;
	private String address1;
	private String address2;
	private String isdCode;
	private String mobileNo;
	private String cmpnyRegno;
	private String keyforSrch;
	private String verifyStatus;
	private String descr;
	private String imageNameMob;
	private String imageNameWeb;
	private Integer status;
	private Integer entryBy;
	private Date entryDatetime;
	private Date modifyDatetime;
	private String countryname;
	private String statename;
	private String cityname;
	private String pincodename;
	public CompanyMstrTblVO(int companyid2, String cmpyName, String cmpyMobno,
			String cmpyverify, String cmpyworktyp, String cmpy_img,
			String cmpyadd1, String cmpyemailid, String cmpycntyname) {
		// TODO Auto-generated constructor stub
		this.companyId =companyid2;
		this.companyName =cmpyName;
		this.mobileNo =cmpyMobno;
		this.verifyStatus=cmpyverify;
		this.cmpnyRegno=cmpyworktyp;
		this.imageNameWeb =cmpy_img;
		this.address1=cmpyadd1;
		this.companyEmail=cmpyemailid;
		this.countryname=cmpycntyname;
				
	}
	
	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getPincodename() {
		return pincodename;
	}

	public void setPincodename(String pincodename) {
		this.pincodename = pincodename;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getPstlId() {
		return pstlId;
	}
	public void setPstlId(String pstlId) {
		this.pstlId = pstlId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyUniqId() {
		return companyUniqId;
	}
	public void setCompanyUniqId(String companyUniqId) {
		this.companyUniqId = companyUniqId;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getIsdCode() {
		return isdCode;
	}
	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getCmpnyRegno() {
		return cmpnyRegno;
	}
	public void setCmpnyRegno(String cmpnyRegno) {
		this.cmpnyRegno = cmpnyRegno;
	}
	public String getKeyforSrch() {
		return keyforSrch;
	}
	public void setKeyforSrch(String keyforSrch) {
		this.keyforSrch = keyforSrch;
	}
	
	public String getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getImageNameMob() {
		return imageNameMob;
	}
	public void setImageNameMob(String imageNameMob) {
		this.imageNameMob = imageNameMob;
	}
	public String getImageNameWeb() {
		return imageNameWeb;
	}
	public void setImageNameWeb(String imageNameWeb) {
		this.imageNameWeb = imageNameWeb;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getEntryBy() {
		return entryBy;
	}
	public void setEntryBy(Integer entryBy) {
		this.entryBy = entryBy;
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
	
	
	
	
	
}
