package com.socialindia.login.persistense;

// default package
// Generated Jul 20, 2016 4:29:06 PM by Hibernate Tools 3.4.0.CR1

/**
 * MvpFamilymbrTbl generated by hbm2java
 */
public class MvpFamilymbrTbl implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fmbrName;
	private String fmbrEmail;
	private String fmbrPhNo;
	private String fmbrISD;
	private String fmbrMtype;
	private String fmbrPrfAccess;
	private String fmbruniqueid;

	public MvpFamilymbrTbl() {
	}

	public MvpFamilymbrTbl(String fmbrName, String fmbrEmail, String fmbrPhNo,
			String fmbrISD, String fmbrMtype, String fmbrPrfAccess,
			String fmbruniqueid) {
		this.fmbrName = fmbrName;
		this.fmbrEmail = fmbrEmail;
		this.fmbrPhNo = fmbrPhNo;
		this.fmbrISD = fmbrISD;
		this.fmbrMtype = fmbrMtype;
		this.fmbrPrfAccess = fmbrPrfAccess;
		this.fmbruniqueid = fmbruniqueid;
	}

	public String getFmbruniqueid() {
		return fmbruniqueid;
	}

	public  void setFmbruniqueid(String fmbruniqueid) {
		this.fmbruniqueid = fmbruniqueid;
	}

	public String getFmbrName() {
		return this.fmbrName;
	}

	public void setFmbrName(String fmbrName) {
		this.fmbrName = fmbrName;
	}

	public String getFmbrEmail() {
		return this.fmbrEmail;
	}

	public void setFmbrEmail(String fmbrEmail) {
		this.fmbrEmail = fmbrEmail;
	}

	public String getFmbrPhNo() {
		return this.fmbrPhNo;
	}

	public void setFmbrPhNo(String fmbrPhNo) {
		this.fmbrPhNo = fmbrPhNo;
	}

	public String getFmbrISD() {
		return fmbrISD;
	}

	public void setFmbrISD(String fmbrISD) {
		this.fmbrISD = fmbrISD;
	}

	public String getFmbrMtype() {
		return fmbrMtype;
	}

	public void setFmbrMtype(String fmbrMtype) {
		this.fmbrMtype = fmbrMtype;
	}

	public String getFmbrPrfAccess() {
		return fmbrPrfAccess;
	}

	public void setFmbrPrfAccess(String fmbrPrfAccess) {
		this.fmbrPrfAccess = fmbrPrfAccess;
	}

}