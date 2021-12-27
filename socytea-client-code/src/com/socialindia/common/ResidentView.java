package com.socialindia.common;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.login.persistense.MvpFamilymbrTbl;
import com.socialindia.residentmgmt.FlatMasterTblVO;

public class ResidentView extends ActionSupport{
	private static final long serialVersionUID = 1L;
	UserMasterTblVo restRegObj = new UserMasterTblVo(null,null,null,null,null,null,null,null,0,null,null,null,null,0,null);
	List<FlatMasterTblVO> FlatList = new ArrayList<FlatMasterTblVO>();
	private List<MvpFamilymbrTbl> userFamilyList = new ArrayList<MvpFamilymbrTbl>();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();	
	private Common commonObj = new CommonDao();
	private String township_List;
	private String[] mySelect;
	private String[] myselect1;
	private String famName;
	private String famisdcode;
	private String famMobileNo;
	private String famEmailId;
	private Integer deleteresidentid;
	private String townshipID;
	private String societyID;
	private String userStatus_id;
	private String fammemtype;
	private String famprfaccess;
	private String memtypechk;
	private String fmbrMtype_str;
	private String famprfaccess_str;
	private String imageName;
	public String execute(){
		Log logWrtie = null;
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject dataJson=null;	JSONObject finaljj=null;String response =null;
		String jsonTextFinal = null;String temp =null;String wings_name="";	String flat_name="";String finalUrl=null;
		JSONObject json_data =null;JSONArray blacksnamearr=null;JSONArray ar1=null;String bloodgroup=null;String gender_txt=null;
		String countrycode=null;String statecode=null;String citycode=null;	
		try {
			logWrtie= new Log();
			System.out.println("Step 1 : Resident Deatils View [Start]. Page : "+getClass().getSimpleName()+".class");
			logWrtie.logMessage("Step 1 : Resident Deatils View [Start].", "info", ResidentView.class);
			Map currentSession = ActionContext.getContext().getSession();
			if (deleteresidentid == null) {
				if ((deleteresidentid == null || deleteresidentid == -1 || deleteresidentid == 0)
						&& currentSession.get("currentsession_deleteresidentid") != null) {
					String stfiddd = (String) currentSession.get("currentsession_deleteresidentid");

					deleteresidentid = Integer.parseInt(stfiddd);
				}
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_deleteresidentid",String.valueOf(deleteresidentid));
			}
			dataJson = new JSONObject();
			dataJson.put("restid", String.valueOf(deleteresidentid));
			dataJson.put("reststatus", "1");
			finaljj = new JSONObject();
			finaljj.put("servicecode", "SI7003");
			finaljj.put("servicefor", "3");// select
			finaljj.put("data", dataJson);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.resident.residentviewaction");
			response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("Step 2 : Resident Deatils View service : "+finalUrl);
			logWrtie.logMessage("Step 2 : Resident Deatils View service : "+finalUrl, "info", ResidentView.class);

			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				System.out.println("Step 3 : Resident Deatils View service Response : "+response);
				logWrtie.logMessage("Step 3 : Resident Deatils View service Response: "+response, "info", ResidentView.class);
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					json_data = locObjRecvJson.getJSONObject("data");
					blacksnamearr = new JSONArray();
					ar1 = new JSONArray();
					blacksnamearr = (JSONArray) Commonutility .toHasChkJsonRtnValObj(json_data, "jArry_wing_flat");
					if(blacksnamearr != null && blacksnamearr.length() > 0) {
						for(int j = 0; j < blacksnamearr.length(); j++) {
							wings_name=(String)Commonutility.toHasChkJsonRtnValObj(blacksnamearr.getJSONObject(j), "wings_name");
							flat_name =(String)Commonutility.toHasChkJsonRtnValObj(blacksnamearr.getJSONObject(j), "flat_no");
							FlatList.add(new FlatMasterTblVO(wings_name,flat_name));
						}

					}
					ar1 = json_data.getJSONArray("userfamilydetail");
					if(ar1!=null && ar1.length()>0){
						for(int i=0;i<ar1.length();i++) {							
							String childmobile=(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "childmobile");
							String childemail=(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "childemail");
							String childname=(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "childname");
							String childisd =(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbrisd");
							String fmbrmembertype =String.valueOf((Integer)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbrmemtype"));
							String fmbrprfaccess =String.valueOf((Integer)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbrprfaccess"));
							String fmbrUniqid = (String) Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbruniqid");
							userFamilyList.add(new MvpFamilymbrTbl(childname,childemail,childmobile,childisd,fmbrmembertype,fmbrprfaccess,fmbrUniqid));
							//listCount=societyWingList.size();
						}
					}

					restRegObj.setTownshipName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"townshipname"));
					townshipID =(String)Commonutility.toHasChkJsonRtnValObj(json_data,"township_id");
					restRegObj.setSocietyName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"societyname"));
					societyID =(String)Commonutility.toHasChkJsonRtnValObj(json_data,"society_id");
					
					restRegObj.setStatusFlag((Integer)Commonutility.toHasChkJsonRtnValObj(json_data,"status"));
					//userStatus_id =(String)Commonutility.toHasChkJsonRtnValObj(json_data,"userStatus_id");
					
					System.out.println("-----------------------------------------------------Raj----------"+restRegObj.getStatusFlag());
					
					restRegObj.setFirstName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"firstname"));
					restRegObj.setLastName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"lastname"));
					restRegObj.setEmailId((String)Commonutility.toHasChkJsonRtnValObj(json_data,"email"));
					restRegObj.setIsdCode((String)Commonutility.toHasChkJsonRtnValObj(json_data,"isd"));
					restRegObj.setMobileNo((String)Commonutility.toHasChkJsonRtnValObj(json_data,"mobno"));
					restRegObj.setIdCardTypeName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"idcard_typname"));
					String idcard=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"idcard_typ");
					if (!idcard.equalsIgnoreCase("")) {
						restRegObj.setIdCardType(Integer.parseInt(idcard));
					}

					restRegObj.setIdProofNo((String)Commonutility.toHasChkJsonRtnValObj(json_data,"idproofno"));
					gender_txt=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"gender");
					if (gender_txt.equalsIgnoreCase("1")) {
						restRegObj.setGender_txt("Male");
					} else if (gender_txt.equalsIgnoreCase("2")) {
						restRegObj.setGender_txt("FeMale");
					} else if (gender_txt.equalsIgnoreCase("3")) {
						restRegObj.setGender_txt("Others");
					} else {
						restRegObj.setGender_txt("");
					}
					restRegObj.setGender((String)Commonutility.toHasChkJsonRtnValObj(json_data,"gender"));
					restRegObj.setDob((String)Commonutility.toHasChkJsonRtnValObj(json_data,"dob"));
					bloodgroup=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"bloodgroup");
					restRegObj.setBloodType(bloodgroup);
					restRegObj.setAccessChannel(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"accesschennel")));
					restRegObj.setAddress1((String)Commonutility.toHasChkJsonRtnValObj(json_data,"address1"));
					restRegObj.setNoOfBlocks((String)Commonutility.toHasChkJsonRtnValObj(json_data,"noofblocks"));
					restRegObj.setNoofFlats((String)Commonutility.toHasChkJsonRtnValObj(json_data,"noofflats"));
					restRegObj.setAddress2((String)Commonutility.toHasChkJsonRtnValObj(json_data,"address2"));
					restRegObj.setCountryname((String)Commonutility.toHasChkJsonRtnValObj(json_data,"rest_cntryName"));
					restRegObj.setCityname((String)Commonutility.toHasChkJsonRtnValObj(json_data,"rest_cityName"));
					restRegObj.setStatename((String)Commonutility.toHasChkJsonRtnValObj(json_data,"rest_stateName"));
					restRegObj.setPstlcodename((String)Commonutility.toHasChkJsonRtnValObj(json_data,"rest_pincodeName"));
					restRegObj.setOccupation((String)Commonutility.toHasChkJsonRtnValObj(json_data,"occupation"));
					restRegObj.setMembersInFamily(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"member")));
					countrycode = (String)Commonutility.toHasChkJsonRtnValObj(json_data,"intv_rest_cntry");
					imageName = (String) Commonutility.toHasChkJsonRtnValObj(json_data,"userImage");
					restRegObj.setUserImage(imageName);
					restRegObj.setUserId((Integer)Commonutility.toHasChkJsonRtnValObj(json_data,"usrid"));
					ActionContext.getContext().getSession()	.put("userImageName", json_data.getString("userImage"));
					if(!countrycode.equalsIgnoreCase("")) {	
						restRegObj.setCountryCode(Integer.parseInt(countrycode));}
					statecode=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"intv_rest_stateid");
					if (!statecode.equalsIgnoreCase("")) {
						restRegObj.setStateId(Integer.parseInt(statecode));	
					}
					citycode=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"intv_rest_cityid");
					if(!citycode.equalsIgnoreCase("")) {
						restRegObj.setCityId(Integer.parseInt(citycode));
					}
					restRegObj.setPinCode((String)Commonutility.toHasChkJsonRtnValObj(json_data,"intv_rest_pstlid"));
					if (ar1 != null) {
						restRegObj.setMembersInFamily(ar1.length());
					}
				}
			} else {
				System.out.println("Step 3 : Resident Deatils View service Response : "+response);
				logWrtie.logMessage("Step 3 : Resident Deatils View service Response: "+response, "info", ResidentView.class);
			}
			System.out.println("Step 4 : Resident Deatils View [End]");
			logWrtie.logMessage("Step 4 : Resident Deatils View [End]", "info", ResidentView.class);
			ActionContext.getContext().getSession()	.put("userID", deleteresidentid);
		} catch (Exception ex) {
			System.out.println("Step -1 : Resident Deatils View [Exception] : "+ex);
			logWrtie.logMessage("Step -1 : Resident Deatils View [Exception] : "+ex, "info", ResidentView.class);
		} finally {
			locObjRecvJson = null;
			dataJson=null;finaljj=null;response =null;
			jsonTextFinal = null;temp =null;wings_name="";flat_name="";finalUrl=null;
			json_data =null;blacksnamearr=null;ar1=null;bloodgroup=null;gender_txt=null;
			countrycode=null;statecode=null;citycode=null;
		}
		return SUCCESS;

	}

	public UserMasterTblVo getRestRegObj() {
		return restRegObj;
	}

	public void setRestRegObj(UserMasterTblVo restRegObj) {
		this.restRegObj = restRegObj;
	}

	public AlertVo getAlert() {
		return alert;
	}

	public void setAlert(AlertVo alert) {
		this.alert = alert;
	}

	public List<AlertVo> getAlertList() {
		return alertList;
	}

	public void setAlertList(List<AlertVo> alertList) {
		this.alertList = alertList;
	}

	public String getTownship_List() {
		return township_List;
	}

	public void setTownship_List(String township_List) {
		this.township_List = township_List;
	}

	public String[] getMySelect() {
		return mySelect;
	}

	public void setMySelect(String[] mySelect) {
		this.mySelect = mySelect;
	}

	public String[] getMyselect1() {
		return myselect1;
	}

	public void setMyselect1(String[] myselect1) {
		this.myselect1 = myselect1;
	}

	public Integer getDeleteresidentid() {
		return deleteresidentid;
	}

	public void setDeleteresidentid(Integer deleteresidentid) {
		this.deleteresidentid = deleteresidentid;
	}

	public List<FlatMasterTblVO> getFlatList() {
		return FlatList;
	}

	public void setFlatList(List<FlatMasterTblVO> flatList) {
		FlatList = flatList;
	}

	public String getTownshipID() {
		return townshipID;
	}

	public void setTownshipID(String townshipID) {
		this.townshipID = townshipID;
	}

	public String getSocietyID() {
		return societyID;
	}

	public void setSocietyID(String societyID) {
		this.societyID = societyID;
	}

	public String getFamName() {
		return famName;
	}

	public void setFamName(String famName) {
		this.famName = famName;
	}

	public String getFamMobileNo() {
		return famMobileNo;
	}

	public void setFamMobileNo(String famMobileNo) {
		this.famMobileNo = famMobileNo;
	}

	public String getFamEmailId() {
		return famEmailId;
	}

	public void setFamEmailId(String famEmailId) {
		this.famEmailId = famEmailId;
	}

	public List<MvpFamilymbrTbl> getUserFamilyList() {
		return userFamilyList;
	}

	public void setUserFamilyList(List<MvpFamilymbrTbl> userFamilyList) {
		this.userFamilyList = userFamilyList;
	}

	public String getFamisdcode() {
		return famisdcode;
	}

	public void setFamisdcode(String famisdcode) {
		this.famisdcode = famisdcode;
	}

	public String getFammemtype() {
		return fammemtype;
	}

	public void setFammemtype(String fammemtype) {
		this.fammemtype = fammemtype;
	}

	public String getFamprfaccess() {
		return famprfaccess;
	}

	public void setFamprfaccess(String famprfaccess) {
		this.famprfaccess = famprfaccess;
	}

	public String getMemtypechk() {
		return memtypechk;
	}

	public void setMemtypechk(String memtypechk) {
		this.memtypechk = memtypechk;
	}

	public String getFamprfaccess_str() {
		return famprfaccess_str;
	}

	public void setFamprfaccess_str(String famprfaccess_str) {
		this.famprfaccess_str = famprfaccess_str;
	}

	public String getFmbrMtype_str() {
		return fmbrMtype_str;
	}

	public void setFmbrMtype_str(String fmbrMtype_str) {
		this.fmbrMtype_str = fmbrMtype_str;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
