package com.socialindia.labour.persistance;

import java.io.Serializable;
import java.util.Date;

public class FeedbackTblVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer ivrBnFEEDBK_ID;
	// private Integer ivrBnUSR_ID;
	private String ivrBnFEEDBK_TXT;
	private Integer ivrBnFEEDBK_FOR_USR_ID;
	private Integer ivrBnFEEDBK_FOR_USR_TYP;
	private Integer ivrBnRATING;
	private Integer ivrBnFEEDBK_STATUS;
	private Date ivrBnENTRY_DATETIME;
	private String ivrFEEDBK_FNAME;
	private String ivrFEEDBK_LNAME;
	private String ivrFEEDBK_PRO_IMG;
	private String ivrFEEDBK_PRO_MobIMG;
	private String ivrFEEDBK_DATE;

	public FeedbackTblVO(String feedbkby_fname, String feedbkby_fname2,
			String feedbackimgweb, String feedbackimgmob,
			String feedbkby_lname, Integer feedbackrating, String feedbacktext,
			Integer feedback_id, Integer feedback_userid) {
		this.ivrFEEDBK_FNAME = feedbkby_fname;
		this.ivrFEEDBK_DATE = feedbkby_fname2;
		this.ivrFEEDBK_PRO_IMG = feedbackimgweb;
		this.ivrFEEDBK_PRO_MobIMG = feedbackimgmob;
		this.ivrFEEDBK_LNAME = feedbkby_lname;
		this.ivrBnRATING = feedbackrating;
		this.ivrBnFEEDBK_TXT = feedbacktext;
		this.ivrBnFEEDBK_ID = feedback_id;
		this.ivrBnFEEDBK_FOR_USR_TYP = feedback_userid;
		// TODO Auto-generated constructor stub
	}

	public String getIvrFEEDBK_FNAME() {
		return ivrFEEDBK_FNAME;
	}

	public void setIvrFEEDBK_FNAME(String ivrFEEDBK_FNAME) {
		this.ivrFEEDBK_FNAME = ivrFEEDBK_FNAME;
	}

	public String getIvrFEEDBK_LNAME() {
		return ivrFEEDBK_LNAME;
	}

	public void setIvrFEEDBK_LNAME(String ivrFEEDBK_LNAME) {
		this.ivrFEEDBK_LNAME = ivrFEEDBK_LNAME;
	}

	public String getIvrFEEDBK_PRO_IMG() {
		return ivrFEEDBK_PRO_IMG;
	}

	public void setIvrFEEDBK_PRO_IMG(String ivrFEEDBK_PRO_IMG) {
		this.ivrFEEDBK_PRO_IMG = ivrFEEDBK_PRO_IMG;
	}

	public String getIvrFEEDBK_DATE() {
		return ivrFEEDBK_DATE;
	}

	public void setIvrFEEDBK_DATE(String ivrFEEDBK_DATE) {
		this.ivrFEEDBK_DATE = ivrFEEDBK_DATE;
	}

	public Integer getIvrBnFEEDBK_ID() {
		return ivrBnFEEDBK_ID;
	}

	public void setIvrBnFEEDBK_ID(Integer ivrBnFEEDBK_ID) {
		this.ivrBnFEEDBK_ID = ivrBnFEEDBK_ID;
	}

	// public Integer getIvrBnUSR_ID() {
	// return ivrBnUSR_ID;
	// }
	// public void setIvrBnUSR_ID(Integer ivrBnUSR_ID) {
	// this.ivrBnUSR_ID = ivrBnUSR_ID;
	// }
	public String getIvrBnFEEDBK_TXT() {
		return ivrBnFEEDBK_TXT;
	}

	public void setIvrBnFEEDBK_TXT(String ivrBnFEEDBK_TXT) {
		this.ivrBnFEEDBK_TXT = ivrBnFEEDBK_TXT;
	}

	public Integer getIvrBnFEEDBK_FOR_USR_ID() {
		return ivrBnFEEDBK_FOR_USR_ID;
	}

	public void setIvrBnFEEDBK_FOR_USR_ID(Integer ivrBnFEEDBK_FOR_USR_ID) {
		this.ivrBnFEEDBK_FOR_USR_ID = ivrBnFEEDBK_FOR_USR_ID;
	}

	public Integer getIvrBnFEEDBK_FOR_USR_TYP() {
		return ivrBnFEEDBK_FOR_USR_TYP;
	}

	public void setIvrBnFEEDBK_FOR_USR_TYP(Integer ivrBnFEEDBK_FOR_USR_TYP) {
		this.ivrBnFEEDBK_FOR_USR_TYP = ivrBnFEEDBK_FOR_USR_TYP;
	}

	public Integer getIvrBnRATING() {
		return ivrBnRATING;
	}

	public void setIvrBnRATING(Integer ivrBnRATING) {
		this.ivrBnRATING = ivrBnRATING;
	}

	public Integer getIvrBnFEEDBK_STATUS() {
		return ivrBnFEEDBK_STATUS;
	}

	public void setIvrBnFEEDBK_STATUS(Integer ivrBnFEEDBK_STATUS) {
		this.ivrBnFEEDBK_STATUS = ivrBnFEEDBK_STATUS;
	}

	public Date getIvrBnENTRY_DATETIME() {
		return ivrBnENTRY_DATETIME;
	}

	public void setIvrBnENTRY_DATETIME(Date ivrBnENTRY_DATETIME) {
		this.ivrBnENTRY_DATETIME = ivrBnENTRY_DATETIME;
	}

	public String getIvrFEEDBK_PRO_MobIMG() {
		return ivrFEEDBK_PRO_MobIMG;
	}

	public void setIvrFEEDBK_PRO_MobIMG(String ivrFEEDBK_PRO_MobIMG) {
		this.ivrFEEDBK_PRO_MobIMG = ivrFEEDBK_PRO_MobIMG;
	}

	
}
