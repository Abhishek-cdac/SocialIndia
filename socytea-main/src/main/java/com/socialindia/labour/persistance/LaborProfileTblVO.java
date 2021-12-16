package com.socialindia.labour.persistance;


import java.util.Date;
import java.util.List;

import com.letspay.uam.persistense.GroupMasterTblVo;





public class LaborProfileTblVO {
	private Integer ivrBnLBR_ID;
	private String ivrBnLBR_SERVICE_ID;
	private String ivrBnLBR_NAME;
	private String ivrBnLBR_EMAIL;	
	private String ivrBnLBR_PH_NO;
	private String ivrBnLBR_ISD_CODE;
	private Integer ivrBnID_CARD_TYP;
	private String ivrBnID_CARD_NO;
	private String ivrBnLBR_ADD_1;
	private String ivrBnLBR_ADD_2;
	
	private String ivrBnVERIFIED_STATUS;
	private String  ivrBnKEY_FOR_SEARCH;
	//private Integer ivrBnWORK_TYPE_ID;
	private String  ivrBnLBR_DESCP;
	
	private String ivrBnLBR_PSTL_ID;
	private String ivrBnLBR_CITY_ID;
	private String ivrBnLBR_STATE_ID;
	private String ivrBnLBR_COUNTRY_ID;
	private Integer ivrBnLBR_STS;
	private String  ivrBnLBR_KYC_NAME;
	private Double ivrBnLBR_RATING;
	private Integer ivrBnENTRY_BY;
	private Date ivrBnENTRY_DATETIME;
	
	private String wrktypId;
	
	private String wrktypname;
	
	private String skillslistname;
	private String skillslistids;
	
	private String imageNameWeb;
	
	private String lbrcntryName;
	private String lbrstateName;
	private String lbrcityName;
	private String lbrpincode;
	private String ivrBnID_CARD_TYPNAME;
	private String lbrgrpid;
	private String entryDatetime;
	private String modifyDatetime;
	//private LaborWrkTypMasterTblVO wrktypId;
	private String ivrBnLBR_EXPERIENCE;
	private String ivrBnLBR_COST;
	private String ivrBnLBR_COSTPER;
	
	private String cat_skillslistname;
	private String cat_skillslistids;
	
	public LaborProfileTblVO(){}
	//private GroupMasterTblVo ivrGrpcode;
	public LaborProfileTblVO(Integer ivrBnLBR_ID, String ivrBnLBR_NAME,String mobno, String ivrverify, String wrktypname,  String ivr_img, String catskillidname, String laboremailid, String lbrserviceid,Double ratingval,String lbrgrpid,String ivrBnLBR_ADD_1,String entryDatetime,String modifyDatetime) {
		super();
		this.ivrBnLBR_ID = ivrBnLBR_ID;
		this.ivrBnLBR_NAME = ivrBnLBR_NAME;
		this.ivrBnVERIFIED_STATUS= ivrverify;
		this.ivrBnLBR_PH_NO =mobno;
		this.wrktypname = wrktypname;
		this.imageNameWeb = ivr_img;
		this.skillslistname=catskillidname;
		this.ivrBnLBR_EMAIL =laboremailid;
		this.ivrBnLBR_SERVICE_ID=lbrserviceid;
		this.ivrBnLBR_RATING = ratingval;
		this.lbrgrpid=lbrgrpid;
		this.ivrBnLBR_ADD_1=ivrBnLBR_ADD_1;
		this.entryDatetime=entryDatetime;
		this.modifyDatetime=modifyDatetime;
		
	}
	
	
	public LaborProfileTblVO(String catskillidname, String catskillids,
			String cat_skillidname, String cat_skillids) {
		this.skillslistname=catskillidname;
		this.skillslistids =catskillids;
		this.cat_skillslistname=cat_skillidname;
		this.cat_skillslistids=cat_skillids;
		// TODO Auto-generated constructor stub
	}
	public String getIvrBnLBR_EXPERIENCE() {
		return ivrBnLBR_EXPERIENCE;
	}
	public void setIvrBnLBR_EXPERIENCE(String ivrBnLBR_EXPERIENCE) {
		this.ivrBnLBR_EXPERIENCE = ivrBnLBR_EXPERIENCE;
	}
	public String getIvrBnLBR_COST() {
		return ivrBnLBR_COST;
	}
	public void setIvrBnLBR_COST(String ivrBnLBR_COST) {
		this.ivrBnLBR_COST = ivrBnLBR_COST;
	}
	public String getIvrBnLBR_COSTPER() {
		return ivrBnLBR_COSTPER;
	}
	public void setIvrBnLBR_COSTPER(String ivrBnLBR_COSTPER) {
		this.ivrBnLBR_COSTPER = ivrBnLBR_COSTPER;
	}
	public String getLbrgrpid() {
		return lbrgrpid;
	}

	public void setLbrgrpid(String lbrgrpid) {
		this.lbrgrpid = lbrgrpid;
	}

	public String getSkillslistids() {
		return skillslistids;
	}

	public void setSkillslistids(String skillslistids) {
		this.skillslistids = skillslistids;
	}

	public String getIvrBnID_CARD_TYPNAME() {
		return ivrBnID_CARD_TYPNAME;
	}

	public void setIvrBnID_CARD_TYPNAME(String ivrBnID_CARD_TYPNAME) {
		this.ivrBnID_CARD_TYPNAME = ivrBnID_CARD_TYPNAME;
	}

	public String getLbrstateName() {
		return lbrstateName;
	}

	public void setLbrstateName(String lbrstateName) {
		this.lbrstateName = lbrstateName;
	}

	public String getLbrcityName() {
		return lbrcityName;
	}

	public void setLbrcityName(String lbrcityName) {
		this.lbrcityName = lbrcityName;
	}

	public String getLbrpincode() {
		return lbrpincode;
	}

	public void setLbrpincode(String lbrpincode) {
		this.lbrpincode = lbrpincode;
	}

	public String getLbrcntryName() {
		return lbrcntryName;
	}

	public void setLbrcntryName(String lbrcntryName) {
		this.lbrcntryName = lbrcntryName;
	}

	public String getWrktypname() {
		return wrktypname;
	}

	public void setWrktypname(String wrktypname) {
		this.wrktypname = wrktypname;
	}

	public Integer getIvrBnLBR_ID() {
		return ivrBnLBR_ID;
	}
	
	public void setIvrBnLBR_ID(Integer ivrBnLBR_ID) {
		this.ivrBnLBR_ID = ivrBnLBR_ID;
	}
	public String getIvrBnLBR_SERVICE_ID() {
		return ivrBnLBR_SERVICE_ID;
	}
	public void setIvrBnLBR_SERVICE_ID(String ivrBnLBR_SERVICE_ID) {
		this.ivrBnLBR_SERVICE_ID = ivrBnLBR_SERVICE_ID;
	}
	public String getIvrBnLBR_NAME() {
		return ivrBnLBR_NAME;
	}
	public void setIvrBnLBR_NAME(String ivrBnLBR_NAME) {
		this.ivrBnLBR_NAME = ivrBnLBR_NAME;
	}
	public String getIvrBnLBR_EMAIL() {
		return ivrBnLBR_EMAIL;
	}
	public void setIvrBnLBR_EMAIL(String ivrBnLBR_EMAIL) {
		this.ivrBnLBR_EMAIL = ivrBnLBR_EMAIL;
	}
	public String getIvrBnLBR_PH_NO() {
		return ivrBnLBR_PH_NO;
	}
	public void setIvrBnLBR_PH_NO(String ivrBnLBR_PH_NO) {
		this.ivrBnLBR_PH_NO = ivrBnLBR_PH_NO;
	}
	public Integer getIvrBnID_CARD_TYP() {
		return ivrBnID_CARD_TYP;
	}
	public void setIvrBnID_CARD_TYP(Integer ivrBnID_CARD_TYP) {
		this.ivrBnID_CARD_TYP = ivrBnID_CARD_TYP;
	}
	public String getIvrBnID_CARD_NO() {
		return ivrBnID_CARD_NO;
	}
	public void setIvrBnID_CARD_NO(String ivrBnID_CARD_NO) {
		this.ivrBnID_CARD_NO = ivrBnID_CARD_NO;
	}
	public String getIvrBnLBR_ADD_1() {
		return ivrBnLBR_ADD_1;
	}
	public void setIvrBnLBR_ADD_1(String ivrBnLBR_ADD_1) {
		this.ivrBnLBR_ADD_1 = ivrBnLBR_ADD_1;
	}
	public String getIvrBnLBR_ADD_2() {
		return ivrBnLBR_ADD_2;
	}
	public void setIvrBnLBR_ADD_2(String ivrBnLBR_ADD_2) {
		this.ivrBnLBR_ADD_2 = ivrBnLBR_ADD_2;
	}
	
	
	
	
	
	public String getIvrBnLBR_PSTL_ID() {
		return ivrBnLBR_PSTL_ID;
	}

	public String getIvrBnLBR_CITY_ID() {
		return ivrBnLBR_CITY_ID;
	}

	public void setIvrBnLBR_CITY_ID(String ivrBnLBR_CITY_ID) {
		this.ivrBnLBR_CITY_ID = ivrBnLBR_CITY_ID;
	}

	public String getIvrBnLBR_STATE_ID() {
		return ivrBnLBR_STATE_ID;
	}

	public void setIvrBnLBR_STATE_ID(String ivrBnLBR_STATE_ID) {
		this.ivrBnLBR_STATE_ID = ivrBnLBR_STATE_ID;
	}

	public String getIvrBnLBR_COUNTRY_ID() {
		return ivrBnLBR_COUNTRY_ID;
	}

	public void setIvrBnLBR_COUNTRY_ID(String ivrBnLBR_COUNTRY_ID) {
		this.ivrBnLBR_COUNTRY_ID = ivrBnLBR_COUNTRY_ID;
	}

	public void setIvrBnLBR_PSTL_ID(String ivrBnLBR_PSTL_ID) {
		this.ivrBnLBR_PSTL_ID = ivrBnLBR_PSTL_ID;
	}

	public Integer getIvrBnLBR_STS() {
		return ivrBnLBR_STS;
	}
	public void setIvrBnLBR_STS(Integer ivrBnLBR_STS) {
		this.ivrBnLBR_STS = ivrBnLBR_STS;
	}
	public String getIvrBnLBR_KYC_NAME() {
		return ivrBnLBR_KYC_NAME;
	}
	public void setIvrBnLBR_KYC_NAME(String ivrBnLBR_KYC_NAME) {
		this.ivrBnLBR_KYC_NAME = ivrBnLBR_KYC_NAME;
	}
	public Double getIvrBnLBR_RATING() {
		return ivrBnLBR_RATING;
	}
	public void setIvrBnLBR_RATING(Double ivrBnLBR_RATING) {
		this.ivrBnLBR_RATING = ivrBnLBR_RATING;
	}
	public Integer getIvrBnENTRY_BY() {
		return ivrBnENTRY_BY;
	}
	public void setIvrBnENTRY_BY(Integer ivrBnENTRY_BY) {
		this.ivrBnENTRY_BY = ivrBnENTRY_BY;
	}
	public Date getIvrBnENTRY_DATETIME() {
		return ivrBnENTRY_DATETIME;
	}
	public void setIvrBnENTRY_DATETIME(Date ivrBnENTRY_DATETIME) {
		this.ivrBnENTRY_DATETIME = ivrBnENTRY_DATETIME;
	}
	public String getIvrBnLBR_ISD_CODE() {
		return ivrBnLBR_ISD_CODE;
	}
	public void setIvrBnLBR_ISD_CODE(String ivrBnLBR_ISD_CODE) {
		this.ivrBnLBR_ISD_CODE = ivrBnLBR_ISD_CODE;
	}
	
	public String getIvrBnVERIFIED_STATUS() {
		return ivrBnVERIFIED_STATUS;
	}

	public void setIvrBnVERIFIED_STATUS(String ivrBnVERIFIED_STATUS) {
		this.ivrBnVERIFIED_STATUS = ivrBnVERIFIED_STATUS;
	}

	public String getIvrBnKEY_FOR_SEARCH() {
		return ivrBnKEY_FOR_SEARCH;
	}
	public void setIvrBnKEY_FOR_SEARCH(String ivrBnKEY_FOR_SEARCH) {
		this.ivrBnKEY_FOR_SEARCH = ivrBnKEY_FOR_SEARCH;
	}
	
	

	public String getWrktypId() {
		return wrktypId;
	}

	public void setWrktypId(String wrktypId) {
		this.wrktypId = wrktypId;
	}

	//	public Integer getIvrBnWORK_TYPE_ID() {
//		return ivrBnWORK_TYPE_ID;
//	}
//	public void setIvrBnWORK_TYPE_ID(Integer ivrBnWORK_TYPE_ID) {
//		this.ivrBnWORK_TYPE_ID = ivrBnWORK_TYPE_ID;
//	}
	public String getIvrBnLBR_DESCP() {
		return ivrBnLBR_DESCP;
	}
	public void setIvrBnLBR_DESCP(String ivrBnLBR_DESCP) {
		this.ivrBnLBR_DESCP = ivrBnLBR_DESCP;
	}
//	public LaborWrkTypMasterTblVO getWrktypId() {
//		return wrktypId;
//	}
//	public void setWrktypId(LaborWrkTypMasterTblVO wrktypId) {
//		this.wrktypId = wrktypId;
//	}
//	public GroupMasterTblVo getIvrGrpcode() {
//		return ivrGrpcode;
//	}
//	public void setIvrGrpcode(GroupMasterTblVo ivrGrpcode) {
//		this.ivrGrpcode = ivrGrpcode;
//	}

	public String getSkillslistname() {
		return skillslistname;
	}

	public void setSkillslistname(String skillslistname) {
		this.skillslistname = skillslistname;
	}

	public String getImageNameWeb() {
		return imageNameWeb;
	}

	public void setImageNameWeb(String imageNameWeb) {
		this.imageNameWeb = imageNameWeb;
	}

	public String getEntryDatetime() {
		return entryDatetime;
	}

	public void setEntryDatetime(String entryDatetime) {
		this.entryDatetime = entryDatetime;
	}

	public String getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(String modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public String getCat_skillslistname() {
		return cat_skillslistname;
	}
	public void setCat_skillslistname(String cat_skillslistname) {
		this.cat_skillslistname = cat_skillslistname;
	}
	public String getCat_skillslistids() {
		return cat_skillslistids;
	}
	public void setCat_skillslistids(String cat_skillslistids) {
		this.cat_skillslistids = cat_skillslistids;
	}

	
}
