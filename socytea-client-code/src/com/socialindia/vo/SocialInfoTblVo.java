package com.socialindia.vo;

import java.io.Serializable;

public class SocialInfoTblVo implements Serializable {

	  private static final long serialVersionUID = 1L;

	  private Integer socialuniqid;
	  private String userid;
	  private String email;
	  private String name;
	  private String picurl;
	  private String firstname;
	  private String lastname;
	  private String aboutme;
	  private String gender;
	  private Integer age;
	  private String permtaddrid;
	  private String permtaddr;
	  private String permtcity;
	  private String permtstate;
	  private String permtcountry;
	  private Integer permtzip;
	/*  private String viewurl;*/
	  private String pdfurl;
	  private String skilllinkedin;
	  private String skillname;
	  private String certificationid;
	  private String certificationname;
	  private String specialities;
	  private Integer socialtype;

	  public SocialInfoTblVo() {
	  }

	public Integer getSocialuniqid() {
		return socialuniqid;
	}

	public void setSocialuniqid(Integer socialuniqid) {
		this.socialuniqid = socialuniqid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAboutme() {
		return aboutme;
	}

	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPermtaddrid() {
		return permtaddrid;
	}

	public void setPermtaddrid(String permtaddrid) {
		this.permtaddrid = permtaddrid;
	}

	public String getPermtaddr() {
		return permtaddr;
	}

	public void setPermtaddr(String permtaddr) {
		this.permtaddr = permtaddr;
	}

	public String getPermtcity() {
		return permtcity;
	}

	public void setPermtcity(String permtcity) {
		this.permtcity = permtcity;
	}

	public String getPermtstate() {
		return permtstate;
	}

	public void setPermtstate(String permtstate) {
		this.permtstate = permtstate;
	}

	public String getPermtcountry() {
		return permtcountry;
	}

	public void setPermtcountry(String permtcountry) {
		this.permtcountry = permtcountry;
	}

	public Integer getPermtzip() {
		return permtzip;
	}

	public void setPermtzip(Integer permtzip) {
		this.permtzip = permtzip;
	}
/*
	public String getViewurl() {
		return viewurl;
	}

	public void setViewurl(String viewurl) {
		this.viewurl = viewurl;
	}*/

	public String getPdfurl() {
		return pdfurl;
	}

	public void setPdfurl(String pdfurl) {
		this.pdfurl = pdfurl;
	}

	public String getSkilllinkedin() {
		return skilllinkedin;
	}

	public void setSkilllinkedin(String skilllinkedin) {
		this.skilllinkedin = skilllinkedin;
	}

	public String getSkillname() {
		return skillname;
	}

	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}

	public String getCertificationid() {
		return certificationid;
	}

	public void setCertificationid(String certificationid) {
		this.certificationid = certificationid;
	}

	public String getCertificationname() {
		return certificationname;
	}

	public void setCertificationname(String certificationname) {
		this.certificationname = certificationname;
	}

	public String getSpecialities() {
		return specialities;
	}

	public void setSpecialities(String specialities) {
		this.specialities = specialities;
	}

	public Integer getSocialtype() {
		return socialtype;
	}

	public void setSocialtype(Integer socialtype) {
		this.socialtype = socialtype;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	}
