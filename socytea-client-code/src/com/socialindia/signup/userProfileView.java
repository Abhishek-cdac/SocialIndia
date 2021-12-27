package com.socialindia.signup;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;



import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.uam.persistense.SocietyMstTbl;
import com.letspay.uam.persistense.TownshipMstTbl;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.login.persistense.MvpFamilymbrTbl;
import com.socialindia.residentmgmt.FlatMasterTblVO;

public class userProfileView  extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserMasterTblVo usercreateObj = new UserMasterTblVo(null, null, null, null,
			null, null, null, null, 0, null, null, null, null, 0,null);
	List<FlatMasterTblVO> FlatList = new ArrayList<FlatMasterTblVO>();
	JSONObject obj = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	private String date;
	private Integer useruniqueId;
	private Integer deleteusrid;
	private int societyId;	
	private int townshipid;
	private Integer groupCode;
	private Integer accessChannel;
	private String townshipname;
	private String societynamecur;
	private String idcardname;
	private String pinCodeName;
	private String cityName;
	private String stateName;
	private String countryName;	
	private String selectval;
	private Integer selectId;
	private File profileImage;
	private String profileImageContentType;
	private String profileImageFileName;
	private String userProfileImage;
	private List<UserMasterTblVo> userList = new ArrayList<UserMasterTblVo>();
	private List<MvpFamilymbrTbl> userFamilyList = new ArrayList<MvpFamilymbrTbl>();
	private List<SocietyMstTbl> societyList = new ArrayList<SocietyMstTbl>();
	private TownshipMstTbl townshipmaster=new TownshipMstTbl();
	private List<TownshipMstTbl> townshiplist = new ArrayList<TownshipMstTbl>();
	
	public String execute(){
		try {			
			Map currentSession = ActionContext.getContext().getSession();			
			if(useruniqueId==null ){					
				if((useruniqueId==null || useruniqueId==-1 || useruniqueId==0)  && currentSession.get("currentsession_useruniqueId")!=null){							
					String userunque=(String)currentSession.get("currentsession_useruniqueId");
					useruniqueId=Integer.parseInt(userunque);
				}
			}else{
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_useruniqueId", String.valueOf(useruniqueId));					
			}			
			obj.put("servicecode", "SI0041");
			Map sessionMap = ActionContext.getContext().getSession();
			obj.put("useruniqueId", sessionMap.get("USERID"));
			String jsonTextFinal = obj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("usermanagement.edit.user.except.admin");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = json.getString("statuscode");
			if(statusCode.equalsIgnoreCase("0")){
				JSONObject json_data = json.getJSONObject("data");			
				usercreateObj.setUserId(json_data.getInt("userId"));
				usercreateObj.setUserName(json_data.getString("userName"));
				usercreateObj.setFirstName(json_data.getString("fName"));
				usercreateObj.setLastName(json_data.getString("lName"));
				usercreateObj.setEmailId(json_data.getString("emailId"));
				usercreateObj.setIsdCode(json_data.getString("isdNo"));
				usercreateObj.setMobileNo(json_data.getString("mobileNo"));
				idcardname=json_data.getString("idCardtype");
				usercreateObj.setIdCardType(json_data.getInt("idCardNo"));
				usercreateObj.setIdProofNo(json_data.getString("idCardtypeNo"));
				usercreateObj.setAddress1(json_data.getString("address1"));
				usercreateObj.setAddress2(json_data.getString("address2"));
				usercreateObj.setGender(json_data.getString("gender"));
				usercreateObj.setDob(json_data.getString("dob"));
				usercreateObj.setNoofFlats(json_data.getString("noofFlats"));
				usercreateObj.setFlatNo(json_data.getString("flatNo"));
				usercreateObj.setNoOfBlocks(json_data.getString("noofwings"));
				usercreateObj.setCountryCode(json_data.getInt("contryCode"));
				countryName=json_data.getString("contryName");
				usercreateObj.setStateId(json_data.getInt("stateId"));
				stateName=json_data.getString("stateName");
				usercreateObj.setCityId(json_data.getInt("cityId"));
				cityName=json_data.getString("cityName");
				usercreateObj.setPostalid(json_data.getInt("pincode"));
				pinCodeName=json_data.getString("pincodeName");
				usercreateObj.setBloodType(json_data.getString("bloodType"));			
				usercreateObj.setOccupation(json_data.getString("occupation"));
				usercreateObj.setMembersInFamily(json_data.getInt("memberofFamily"));
				accessChannel=json_data.getInt("accesschannal");
				townshipid=json_data.getInt("townshipId");
				societyId=json_data.getInt("societyId");
				societynamecur=json_data.getString("societyname");
				townshipname=json_data.getString("townshipname");	
				groupCode=json_data.getInt("groupCodeType");	
			/*userProfileImage=json_data.getString("webimage");
			ActionContext.getContext().getSession().put("userProfileImage", userProfileImage);*/
			 JSONArray ar =null;
			 JSONArray ar1 =null;
			 ar = json_data.getJSONArray("userflatdetail");
			 JSONObject jsonList = new JSONObject();
			 if(ar!=null && ar.length()>0){
			 for(int i=0;i<ar.length();i++) {	
					jsonList=null;
					jsonList=ar.getJSONObject(i);					
					String wings_name =jsonList.getString("userwingsname");
					String flat_name = jsonList.getString("userflatno");
					FlatList.add(new FlatMasterTblVO(wings_name,flat_name));
					//listCount=societyWingList.size();
				}
			 }
			 
				ar1 = json_data.getJSONArray("userfamilydetail");
				if(ar1!=null){
					usercreateObj.setMembersInFamily(ar1.length());
				}				
				JSONObject jsonList1 = new JSONObject();
				if (ar1 != null && ar1.length() > 0) {
					for (int i = 0; i < ar1.length(); i++) {
						jsonList1 = null;
						jsonList1 = ar1.getJSONObject(i);
						String childmobile = jsonList1.getString("childmobile");
						String childemail = jsonList1.getString("childemail");
						String childname = jsonList1.getString("childname");
						String childisd = jsonList1.getString("fmbrisd");
						int fmbrmembertype = jsonList1.getInt("fmbrmemtype");
						int fmbrprfaccess = jsonList1.getInt("fmbrprfaccess");
						String fmbrUniqid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList1, "fmbruniqid");
						userFamilyList.add(new MvpFamilymbrTbl(childname,childemail,childmobile,childisd,String.valueOf(fmbrmembertype),String.valueOf(fmbrprfaccess),fmbrUniqid));
					}
				}
			 
				ActionContext.getContext().getSession().put("TOWNSHIPLIST", townshiplist);
				ActionContext.getContext().getSession().put("societyList", societyList);
		     
			}
		} catch (Exception ex) {
			System.out.println("=====editUser====Exception==========" + ex);
		}
		return SUCCESS;
	}

	public UserMasterTblVo getUsercreateObj() {
		return usercreateObj;
	}

	public void setUsercreateObj(UserMasterTblVo usercreateObj) {
		this.usercreateObj = usercreateObj;
	}

	public List<FlatMasterTblVO> getFlatList() {
		return FlatList;
	}

	public void setFlatList(List<FlatMasterTblVO> flatList) {
		FlatList = flatList;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
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

	public Common getCommonObj() {
		return commonObj;
	}

	public void setCommonObj(Common commonObj) {
		this.commonObj = commonObj;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getUseruniqueId() {
		return useruniqueId;
	}

	public void setUseruniqueId(Integer useruniqueId) {
		this.useruniqueId = useruniqueId;
	}

	public Integer getDeleteusrid() {
		return deleteusrid;
	}

	public void setDeleteusrid(Integer deleteusrid) {
		this.deleteusrid = deleteusrid;
	}

	public int getSocietyId() {
		return societyId;
	}

	public void setSocietyId(int societyId) {
		this.societyId = societyId;
	}

	public int getTownshipid() {
		return townshipid;
	}

	public void setTownshipid(int townshipid) {
		this.townshipid = townshipid;
	}

	public Integer getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(Integer groupCode) {
		this.groupCode = groupCode;
	}

	public Integer getAccessChannel() {
		return accessChannel;
	}

	public void setAccessChannel(Integer accessChannel) {
		this.accessChannel = accessChannel;
	}

	public String getTownshipname() {
		return townshipname;
	}

	public void setTownshipname(String townshipname) {
		this.townshipname = townshipname;
	}

	public String getSocietynamecur() {
		return societynamecur;
	}

	public void setSocietynamecur(String societynamecur) {
		this.societynamecur = societynamecur;
	}

	public String getIdcardname() {
		return idcardname;
	}

	public void setIdcardname(String idcardname) {
		this.idcardname = idcardname;
	}

	public String getPinCodeName() {
		return pinCodeName;
	}

	public void setPinCodeName(String pinCodeName) {
		this.pinCodeName = pinCodeName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getSelectval() {
		return selectval;
	}

	public void setSelectval(String selectval) {
		this.selectval = selectval;
	}

	public Integer getSelectId() {
		return selectId;
	}

	public void setSelectId(Integer selectId) {
		this.selectId = selectId;
	}

	public List<UserMasterTblVo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserMasterTblVo> userList) {
		this.userList = userList;
	}

	public List<MvpFamilymbrTbl> getUserFamilyList() {
		return userFamilyList;
	}

	public void setUserFamilyList(List<MvpFamilymbrTbl> userFamilyList) {
		this.userFamilyList = userFamilyList;
	}

	public List<SocietyMstTbl> getSocietyList() {
		return societyList;
	}

	public void setSocietyList(List<SocietyMstTbl> societyList) {
		this.societyList = societyList;
	}

	public TownshipMstTbl getTownshipmaster() {
		return townshipmaster;
	}

	public void setTownshipmaster(TownshipMstTbl townshipmaster) {
		this.townshipmaster = townshipmaster;
	}

	public List<TownshipMstTbl> getTownshiplist() {
		return townshiplist;
	}

	public void setTownshiplist(List<TownshipMstTbl> townshiplist) {
		this.townshiplist = townshiplist;
	}

	public File getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(File profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImageContentType() {
		return profileImageContentType;
	}

	public void setProfileImageContentType(String profileImageContentType) {
		this.profileImageContentType = profileImageContentType;
	}

	public String getProfileImageFileName() {
		return profileImageFileName;
	}

	public void setProfileImageFileName(String profileImageFileName) {
		this.profileImageFileName = profileImageFileName;
	}

	public String getUserProfileImage() {
		return userProfileImage;
	}

	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}
}
