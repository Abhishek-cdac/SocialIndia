package com.socialindia.signup;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
import com.socialindia.residentmgmt.FlatMasterTblVO;

public class userProfileViewUpdate  extends  ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserMasterTblVo usercreateObj = new UserMasterTblVo(null, null, null, null, null, null, null, null, 0, null, null, null, null, 0,null);
	List<FlatMasterTblVO> FlatList = new ArrayList<FlatMasterTblVO>();
	private String blockNameList;
	private String flatNameList;
	private String famName;
	private String famMobileNo;
	private String famEmailId;
	private Integer accessChannel;
	private File profileImage;
	private String profileImageContentType;
	private String profileImageFileName;
	private String famisdcode;
	private String fmbrMtype_str;
	private String famprfaccess_str;
	private String fmemberuniqueid;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	JSONObject obj = new JSONObject();
	byte imgByt[] = null;
	Log log = new Log();
	public String execute(){
		
	try {
			boolean result = false;
			obj.put("servicecode", "SI0042");
			obj.put("userId", usercreateObj.getUserId());
			if(usercreateObj.getUserName()!=null){
				obj.put("userName", usercreateObj.getUserName());
			}else{
				obj.put("userName", "");
			}
			/*obj.put("townshipId", townshipid);
			obj.put("societyId", societyId);
			obj.put("societyname", societynamecur);
			obj.put("emailId",usercreateObj.getEmailId());
			obj.put("isdno", usercreateObj.getIsdCode());
			obj.put("mobileNo", usercreateObj.getMobileNo());*/
			obj.put("emailId",usercreateObj.getEmailId());							
			obj.put("idcardtype", usercreateObj.getIdCardType());
			obj.put("idcardno", usercreateObj.getIdProofNo());	
			obj.put("firstName", usercreateObj.getFirstName());
			obj.put("lastName", usercreateObj.getLastName());
			obj.put("gender", usercreateObj.getGender());
			obj.put("dob", usercreateObj.getDob());
			obj.put("noofflats", usercreateObj.getNoofFlats());
			obj.put("noofwings", usercreateObj.getNoOfBlocks());
			obj.put("address1",usercreateObj.getAddress1());
			obj.put("address2", usercreateObj.getAddress2());
			obj.put("countryid",usercreateObj.getCountryCode());
			obj.put("stateid",usercreateObj.getStateId());
			obj.put("cityid",usercreateObj.getCityId());
			obj.put("pinid",usercreateObj.getPostalid());
			obj.put("occupation",usercreateObj.getOccupation());
			obj.put("familyno",usercreateObj.getMembersInFamily());
			obj.put("bloodgroup",usercreateObj.getBloodType());
			obj.put("accesschannel",accessChannel);
			obj.put("blockNameList",blockNameList);
			obj.put("flatNameList",flatNameList);
			obj.put("famName",famName);
			obj.put("famMobileNo",famMobileNo);
			obj.put("famEmailId",famEmailId);
			obj.put("famisdcode",famisdcode);
			obj.put("fammemtype",fmbrMtype_str);
			obj.put("famprfaccess",famprfaccess_str);
			obj.put("fmemberuniqueid", fmemberuniqueid);//family member unique id	
			//imgByt = commomServices.toReadFiletoBytes(profileImage);
			//obj.put("imageweb", commomServices.toByteAryToBase64EncodeStr(imgByt));
			if(profileImage!=null){
				obj.put("imagewebsrchpath", profileImage.getAbsolutePath());
			} else {
				obj.put("imagewebsrchpath", "");
			}					
			obj.put("profileImageFileName", profileImageFileName);						
			String jsonTextFinal = obj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("user.profile.except.admin.update.action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			String respCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "respcode");
			String message = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			if (statusCode.equalsIgnoreCase("0") && respCode.equalsIgnoreCase("0000")) {
				JSONObject json_data = json.getJSONObject("data");
				if(profileImage!=null){
					String profileimage = (String) json_data.get("profileimage");
					ActionContext.getContext().getSession().put("userProfileImage", profileimage);
				}
				String dispName = "", firstname = usercreateObj.getFirstName(), lastname = usercreateObj.getLastName(), mobileno = usercreateObj.getMobileNo();
    			if(firstname!=null && !firstname.equalsIgnoreCase("null") && !firstname.equalsIgnoreCase("")){
    				dispName = firstname+" ";
    			} else{
    				dispName = "";
    			}
    			if(lastname!=null && !lastname.equalsIgnoreCase("null") && !lastname.equalsIgnoreCase("")){
    				dispName += lastname;
    			} 
    			    			
    			if(dispName==null || dispName.equalsIgnoreCase("") || dispName.equalsIgnoreCase("null")){
    				dispName = mobileno;
    			}
				ActionContext.getContext().getSession().put("sDisplyname", dispName);    	
				ActionContext.getContext().getSession().put("FirstName", usercreateObj.getFirstName());
    			ActionContext.getContext().getSession().put("LastName", usercreateObj.getLastName());
				alert.setCls("success");
				alert.setMsg(getText("User updated successfully."));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Text.customerreg.key.error"));
				alertList.add(alert);
				return "input";
			}
						
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Exception Found in userProfileViewUpdate : "+ex);
			alert.setCls("error");
			alert.setMsg(getText("Error in user updating"));
			alertList.add(alert);
			return "error";
		}
		
	}
	
	

	public String getFmemberuniqueid() {
		return fmemberuniqueid;
	}



	public void setFmemberuniqueid(String fmemberuniqueid) {
		this.fmemberuniqueid = fmemberuniqueid;
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
	public String getBlockNameList() {
		return blockNameList;
	}
	public void setBlockNameList(String blockNameList) {
		this.blockNameList = blockNameList;
	}
	public String getFlatNameList() {
		return flatNameList;
	}
	public void setFlatNameList(String flatNameList) {
		this.flatNameList = flatNameList;
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
	public Integer getAccessChannel() {
		return accessChannel;
	}
	public void setAccessChannel(Integer accessChannel) {
		this.accessChannel = accessChannel;
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
	public String getFamisdcode() {
		return famisdcode;
	}
	public void setFamisdcode(String famisdcode) {
		this.famisdcode = famisdcode;
	}
	public String getFmbrMtype_str() {
		return fmbrMtype_str;
	}
	public void setFmbrMtype_str(String fmbrMtype_str) {
		this.fmbrMtype_str = fmbrMtype_str;
	}
	public String getFamprfaccess_str() {
		return famprfaccess_str;
	}
	public void setFamprfaccess_str(String famprfaccess_str) {
		this.famprfaccess_str = famprfaccess_str;
	}
	
	
}
