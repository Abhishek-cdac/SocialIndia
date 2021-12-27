package com.socialindia.generalmgnt.persistance;

import java.util.Date;

import com.letspay.vo.CityMasterTblVo;


public class StaffMasterTblVo {
	private int staffID;
	  private String staffName;
	  private String staffEmail;
	  private String staffGender;
	  private String staffage;
	  private String staffMobno;
	  private String staffAddr;
	  private Integer staffCountry;
	  private Integer staffState;
	  private Integer staffCity;
	  private IDCardMasterTblVO iVOcradid;
	  private String staffIdcardno;
	  private int status;
	  private int entryby;
	  private Date entryDatetime;
	  private Date modifyDatetime;
	  private CityMasterTblVo cityId;
	  private String ISDcode;
	  private Integer Workinghours=0;
	  private String wrkhourstr;
	
		 private StaffCategoryMasterTblVO iVOstaffcategid;
		
		 private PostalCodeMasterTblVO pstlId;
		 private String imageNameWeb;
		  private String imageNameMobile;
		  private String staffcompanyname;
		  private String staffcompany_hidd_txt;
		  private String entryDatetimeFormated;
		  private String modifyDatetimeFormated;
	public StaffMasterTblVo(String staffName2, String staffMobno, String staffEmail,
			int staffID, String staffAddr) {
		
		this.staffName =staffName2;
		this.staffID =staffID;
		this.staffEmail=staffEmail;
		this.staffMobno =staffMobno;
		this.staffAddr = staffAddr;
		// TODO Auto-generated constructor stub
	}
	
	public StaffMasterTblVo(){}
	
	public String getStaffcompany_hidd_txt() {
		return staffcompany_hidd_txt;
	}

	public void setStaffcompany_hidd_txt(String staffcompany_hidd_txt) {
		this.staffcompany_hidd_txt = staffcompany_hidd_txt;
	}

	public String getStaffcompanyname() {
		return staffcompanyname;
	}

	public void setStaffcompanyname(String staffcompanyname) {
		this.staffcompanyname = staffcompanyname;
	}

	public int getEntryby() {
		return entryby;
	}
	public void setEntryby(int entryby) {
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
	public int getStaffID() {
		return staffID;
	}
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffEmail() {
		return staffEmail;
	}
	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}
	public String getStaffGender() {
		return staffGender;
	}
	public void setStaffGender(String staffGender) {
		this.staffGender = staffGender;
	}
	public String getStaffage() {
		return staffage;
	}
	public void setStaffage(String staffage) {
		this.staffage = staffage;
	}
	public String getStaffMobno() {
		return staffMobno;
	}
	public void setStaffMobno(String staffMobno) {
		this.staffMobno = staffMobno;
	}
	public String getStaffAddr() {
		return staffAddr;
	}
	public void setStaffAddr(String staffAddr) {
		this.staffAddr = staffAddr;
	}
	
	
	public Integer getStaffCountry() {
		return staffCountry;
	}

	public void setStaffCountry(Integer staffCountry) {
		this.staffCountry = staffCountry;
	}

	public Integer getStaffState() {
		return staffState;
	}

	public void setStaffState(Integer staffState) {
		this.staffState = staffState;
	}

	public Integer getStaffCity() {
		return staffCity;
	}

	public void setStaffCity(Integer staffCity) {
		this.staffCity = staffCity;
	}

	public String getStaffIdcardno() {
		return staffIdcardno;
	}
	public void setStaffIdcardno(String staffIdcardno) {
		this.staffIdcardno = staffIdcardno;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public CityMasterTblVo getCityId() {
		return cityId;
	}

	public void setCityId(CityMasterTblVo cityId) {
		this.cityId = cityId;
	}

	
	public String getISDcode() {
		return ISDcode;
	}

	public void setISDcode(String iSDcode) {
		ISDcode = iSDcode;
	}

	

	

	

	public Integer getWorkinghours() {
		return Workinghours;
	}

	public void setWorkinghours(Integer workinghours) {
		Workinghours = workinghours;
	}

	public StaffCategoryMasterTblVO getiVOstaffcategid() {
		return iVOstaffcategid;
	}

	public void setiVOstaffcategid(StaffCategoryMasterTblVO iVOstaffcategid) {
		this.iVOstaffcategid = iVOstaffcategid;
	}

	

	public PostalCodeMasterTblVO getPstlId() {
		return pstlId;
	}

	public void setPstlId(PostalCodeMasterTblVO pstlId) {
		this.pstlId = pstlId;
	}

	public IDCardMasterTblVO getiVOcradid() {
		return iVOcradid;
	}

	public void setiVOcradid(IDCardMasterTblVO iVOcradid) {
		this.iVOcradid = iVOcradid;
	}

	public String getImageNameWeb() {
		return imageNameWeb;
	}

	public void setImageNameWeb(String imageNameWeb) {
		this.imageNameWeb = imageNameWeb;
	}

	public String getImageNameMobile() {
		return imageNameMobile;
	}

	public void setImageNameMobile(String imageNameMobile) {
		this.imageNameMobile = imageNameMobile;
	}

	public String getWrkhourstr() {
		return wrkhourstr;
	}

	public void setWrkhourstr(String wrkhourstr) {
		this.wrkhourstr = wrkhourstr;
	}

	public String getEntryDatetimeFormated() {
		return entryDatetimeFormated;
	}

	public void setEntryDatetimeFormated(String entryDatetimeFormated) {
		this.entryDatetimeFormated = entryDatetimeFormated;
	}

	public String getModifyDatetimeFormated() {
		return modifyDatetimeFormated;
	}

	public void setModifyDatetimeFormated(String modifyDatetimeFormated) {
		this.modifyDatetimeFormated = modifyDatetimeFormated;
	}  
	  
}
