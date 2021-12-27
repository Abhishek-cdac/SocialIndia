package com.socialindia.uam;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONException;
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
import com.socialindia.login.persistense.MvpFamilymbrTbl;
import com.socialindia.residentmgmt.FlatMasterTblVO;

public class userCreation extends ActionSupport {
	/**
	 * 
	 */  
	private static final long serialVersionUID = 1L;
	UserMasterTblVo usercreateObj = new UserMasterTblVo(null, null, null, null, null, null, null, null, 0, null, null, null, null, 0,null);
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
	private String locgroup=null;
	private List<UserMasterTblVo> userList = new ArrayList<UserMasterTblVo>();
	private List<MvpFamilymbrTbl> userFamilyList = new ArrayList<MvpFamilymbrTbl>();
	private List<SocietyMstTbl> societyList = new ArrayList<SocietyMstTbl>();
	private TownshipMstTbl townshipmaster=new TownshipMstTbl();
	private List<TownshipMstTbl> townshiplist = new ArrayList<TownshipMstTbl>();
	private SocietyMstTbl societydata=new SocietyMstTbl(0, null, null, null, null, null, null, 0,null);
	private String blockNameList;
	private String flatNameList;
	private String famName;
	private String famMobileNo;
	private String famEmailId;
	private String famisdcode;
	private String fammemtype_user;
	private String famprfaccess_user;
	private String fmbrMtype_str;
	private String famprfaccess_str;
	private String fmemberuniqueid;
	private String flg;

	public String execute() {		
		return SUCCESS;
	}

	public String creationForm() {
		Map sessionMap = ActionContext.getContext().getSession();
		try {			
			obj.put("servicecode", "SI0007");
			obj.put("townshipid", townshipid);
			obj.put("societyId", societyId);
			obj.put("emailId", usercreateObj.getEmailId());
			obj.put("isdno", usercreateObj.getIsdCode());
			obj.put("mobileNo", usercreateObj.getMobileNo());
			obj.put("idcardtype", usercreateObj.getIdCardType());
			obj.put("idcardno", usercreateObj.getIdProofNo());						
			obj.put("firstName", usercreateObj.getFirstName());
			obj.put("lastName", usercreateObj.getLastName());
			System.out.println("usercreateObj.getGender() : "+usercreateObj.getGender());
			if(usercreateObj.getGender()==null){
				obj.put("gender", "");
			} else {
				obj.put("gender", usercreateObj.getGender());
			}
			
			obj.put("dob", usercreateObj.getDob());
			obj.put("noofflats", usercreateObj.getNoofFlats());
			obj.put("numOfLogins", usercreateObj.getNumOfLogins());
			obj.put("noofwings", usercreateObj.getNoOfBlocks());
			obj.put("address1",usercreateObj.getAddress1());
			obj.put("address2", usercreateObj.getAddress2());
			obj.put("countryid",usercreateObj.getCountryCode());
			obj.put("stateid",usercreateObj.getStateId());
			obj.put("cityid",usercreateObj.getCityId());
			
			if(usercreateObj.getPostalid() == null){
				obj.put("pinid",0);
			}
			else{
				obj.put("pinid",usercreateObj.getPostalid());
			}
			
			
			obj.put("occupation",usercreateObj.getOccupation());
			obj.put("familyno",usercreateObj.getMembersInFamily());
			obj.put("bloodgroup",usercreateObj.getBloodType());
			obj.put("accesschannel",accessChannel);
			obj.put("groupCode",0);
			
			obj.put("blockNameList",blockNameList);
			obj.put("flatNameList",flatNameList);
			obj.put("famName",famName);
			obj.put("famMobileNo",famMobileNo);
			obj.put("famEmailId",famEmailId);
			obj.put("famisdcode",famisdcode);
			obj.put("fammemtype",fammemtype_user);
			obj.put("famprfaccess",famprfaccess_user);
			obj.put("numOfLogins", usercreateObj.getNumOfLogins());
			
			String jsonTextFinal = obj.toString().trim();
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("usermanagement.user.creation");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String)  Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			String respCode = (String)  Commonutility.toHasChkJsonRtnValObj(json, "respcode");

			String message = (String) json.get("message");
			if (message.equalsIgnoreCase("userExist")) {
				addFieldError("usercreateObj.userName", "User name already exist");
				return "input";
			} else if (message.equalsIgnoreCase("EmailExist")) {
				addFieldError("usercreateObj.emailId", getText("user.email.id.already.register"));
				return "input";
			} else if (message.equalsIgnoreCase("mobnoExist")) {
				addFieldError("usercreateObj.mobileNo", getText("user.mobile.no.already.register"));
				return "input";
			}

			if (statusCode.equalsIgnoreCase("0") && respCode.equalsIgnoreCase("0000")) {
				alert.setCls("success");
				alert.setMsg(getText("User created successfully."));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Text.customerreg.key.error"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("========userCreation======Exception======" + ex);
			alert.setCls("danger");
			alert.setMsg(getText("Text.customerreg.key.error"));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		}
		//return SUCCESS;
	}
	
	public String siUserformFunction() {
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			System.out.println("Step  : Enter siUserformFunction Block [start]"	+ usercreateObj.getIdCardType());
			obj.put("servicecode", "SI0007");
			obj.put("townshipid", townshipid);
			obj.put("societyId", societyId);
			obj.put("emailId", usercreateObj.getEmailId());
			obj.put("isdno", usercreateObj.getIsdCode());
			obj.put("mobileNo", usercreateObj.getMobileNo());
			obj.put("idcardtype", usercreateObj.getIdCardType());
			obj.put("idcardno", usercreateObj.getIdProofNo());						
			obj.put("firstName", usercreateObj.getFirstName());
			obj.put("lastName", usercreateObj.getLastName());
			obj.put("numOfLogins", usercreateObj.getNumOfLogins());
			
			System.out.println("usercreateObj.getGender() : "+usercreateObj.getGender());
			if(usercreateObj.getGender()==null){
				obj.put("gender", "");
			} else {
				obj.put("gender", usercreateObj.getGender());
			}
			
			obj.put("dob", usercreateObj.getDob());
			if(usercreateObj.getNoofFlats()==null){
				obj.put("noofflats", "");
				obj.put("flatno", "");	
			}else{
				obj.put("noofflats", usercreateObj.getNoofFlats());
				obj.put("flatno", usercreateObj.getFlatNo());
			}
			obj.put("address1",usercreateObj.getAddress1());
			obj.put("address2", usercreateObj.getAddress2());
			obj.put("countryid",usercreateObj.getCountryCode());
			obj.put("stateid",usercreateObj.getStateId());
			obj.put("cityid",usercreateObj.getCityId());
//			obj.put("pinid",usercreateObj.getPostalid());
			if(usercreateObj.getPostalid() == null){
				obj.put("pinid",0);
			}
			else{
				obj.put("pinid",usercreateObj.getPostalid());
			}
			obj.put("occupation",usercreateObj.getOccupation());
			obj.put("familyno",usercreateObj.getMembersInFamily());
			obj.put("bloodgroup",usercreateObj.getBloodType());
			obj.put("accesschannel",accessChannel);
			obj.put("groupCode",2);
			String jsonTextFinal = obj.toString().trim();
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("usermanagement.user.creation");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String)  Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			String respCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "respcode");

			String message = (String) json.get("message");
			if (message.equalsIgnoreCase("userExist")) {
				addFieldError("usercreateObj.userName", "User name already exist");
				return "input";
			} else if (message.equalsIgnoreCase("EmailExist")) {
				addFieldError("usercreateObj.emailId", getText("user.email.id.already.register"));
				return "input";
			} else if (message.equalsIgnoreCase("mobnoExist")) {
				addFieldError("usercreateObj.mobileNo", getText("user.mobile.no.already.register"));
				return "input";
			}
			if (statusCode.equalsIgnoreCase("0") && respCode.equalsIgnoreCase("0000")) {
				System.out.println("Step  : Enter siUserformFunction Block statusCode : "+statusCode+", [End]");
				alert.setCls("success");
				alert.setMsg(getText("SI User created successfully."));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "success";
			} else {
				System.out.println("Step  : Enter siUserformFunction Block statusCode : "+statusCode+", [End]");
				alert.setCls("danger");
				alert.setMsg(getText("Text.customerreg.key.error"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}
			
		} catch (Exception ex) {
			System.out.println("Exception Found in userCreation.class siUserformFunction() : "+ex);
			alert.setCls("danger");
			alert.setMsg(getText("Text.customerreg.key.error"));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		}finally{
			
		}		
	}

	public String deleteUserActionFunction() {
		boolean result = false;
		try {
			if (deleteusrid != 0 && deleteusrid > 0) {
				obj.put("servicecode", "SI0010");
				obj.put("deleteuserid", deleteusrid);
				String jsonTextFinal = obj.toString();
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("usermanagement.delete.user");
				String response = commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");
				result = json_data.getBoolean("resultFlag");
				// result = uam.deleteUserDetials(deleteusrid);
				String statusCode = (String) json.get("statuscode");
				String respCode = (String) json.get("respcode");
				if (statusCode.equalsIgnoreCase("0")
						&& respCode.equalsIgnoreCase("0000")) {
					if (result == false) {
						alert.setCls("error");
						alert.setMsg(getText("Error in user delete."));
						alertList.add(alert);
						return "input";
					} else {
						alert.setCls("success");
						alert.setMsg(getText("User deleted successfully."));
						alertList.add(alert);
						return "success";
					}
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Text.customerreg.key.error"));
					alertList.add(alert);
					return "input";
				}
			}
		} catch (Exception ex) {
			alert.setCls("error");
			alert.setMsg(getText("Exception in error deletion."));
			alertList.add(alert);
			return "error";
		}
		return SUCCESS;
	}

	public String editUser() {
		System.out.println("Step 1 : Edit User Block Enter" );
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
			obj.put("servicecode", "SI0008");
			obj.put("useruniqueId", useruniqueId);
			String jsonTextFinal = obj.toString();
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("usermanagement.edit.user");
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response  "+response);
			JSONObject json = new JSONObject(response);
			JSONObject json_data = json.getJSONObject("data");			
			usercreateObj.setUserId(json_data.getInt("userId"));
			usercreateObj.setUserName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"userName"));
			usercreateObj.setFirstName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"fName"));
			usercreateObj.setLastName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"lName"));
			usercreateObj.setEmailId((String)Commonutility.toHasChkJsonRtnValObj(json_data,"emailId"));
			usercreateObj.setIsdCode((String)Commonutility.toHasChkJsonRtnValObj(json_data,"isdNo"));
			usercreateObj.setMobileNo((String)Commonutility.toHasChkJsonRtnValObj(json_data,"mobileNo"));
			idcardname=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"idCardtype");
			usercreateObj.setIdCardType(json_data.getInt("idCardNo"));
			usercreateObj.setIdProofNo((String)Commonutility.toHasChkJsonRtnValObj(json_data,"idCardtypeNo"));
			usercreateObj.setAddress1((String)Commonutility.toHasChkJsonRtnValObj(json_data,"address1"));
			usercreateObj.setAddress2((String)Commonutility.toHasChkJsonRtnValObj(json_data,"address2"));
			usercreateObj.setGender((String)Commonutility.toHasChkJsonRtnValObj(json_data,"gender"));
			usercreateObj.setDob((String)Commonutility.toHasChkJsonRtnValObj(json_data,"dob"));
			usercreateObj.setNoofFlats(String.valueOf(json_data.getInt("noofFlats")));
			usercreateObj.setNumOfLogins(json_data.getInt("numOfLogins"));
			
			usercreateObj.setFlatNo(String.valueOf(json_data.getInt("flatNo")));
			usercreateObj.setNoOfBlocks((String)Commonutility.toHasChkJsonRtnValObj(json_data,"noofwings"));
			usercreateObj.setCountryCode(json_data.getInt("contryCode"));
			countryName=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"contryName");
			usercreateObj.setStateId(json_data.getInt("stateId"));
			stateName=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"stateName");
			usercreateObj.setCityId(json_data.getInt("cityId"));
			cityName=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"cityName");

			Integer pin = json_data.getInt("pincode");
			if(pin == 0){
				usercreateObj.setPostalid(null);
			}
			else{
				usercreateObj.setPostalid(pin);
			}
//			pinCodeName=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"pincodeName");
			usercreateObj.setBloodType((String)Commonutility.toHasChkJsonRtnValObj(json_data,"bloodType"));			
			usercreateObj.setOccupation((String)Commonutility.toHasChkJsonRtnValObj(json_data,"occupation"));
			usercreateObj.setMembersInFamily(json_data.getInt("memberofFamily"));
			 accessChannel=json_data.getInt("accesschannal");
			townshipid=json_data.getInt("townshipId");
			societyId=json_data.getInt("societyId");
			societynamecur=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"societyname");
			townshipname=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"townshipname");	
			groupCode=json_data.getInt("groupCodeType");	
			 JSONArray ar =null;
			 JSONArray ar1 =null;
			 ar = json_data.getJSONArray("userflatdetail");
			 //JSONObject jsonList = new JSONObject();
			 if(ar!=null && ar.length()>0){
				for (int i = 0; i < ar.length(); i++) {
					/*jsonList=null;
					jsonList=ar.getJSONObject(i);	*/			
					String wings_name=(String)Commonutility.toHasChkJsonRtnValObj(ar.getJSONObject(i),"userwingsname");
					String flat_name=(String)Commonutility.toHasChkJsonRtnValObj(ar.getJSONObject(i),"userflatno");
					FlatList.add(new FlatMasterTblVO(wings_name,flat_name));
					//listCount=societyWingList.size();
				}
			}
			ar1 = json_data.getJSONArray("userfamilydetail");
			if (ar1 != null) {
				usercreateObj.setMembersInFamily(ar1.length());
			}		 			
			if (ar1 != null && ar1.length() > 0) {
				for (int i = 0; i < ar1.length(); i++) {
					/*jsonList1=null;
					jsonList1=ar1.getJSONObject(i);			*/		
					String childmobile=(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "childmobile");
					String childemail=(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "childemail");
					String childname=(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "childname");
					String childisd =(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbrisd");
					String fmbrmembertype =String.valueOf((Integer)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbrmemtype"));
					String fmbrprfaccess =String.valueOf((Integer)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbrprfaccess"));
					String fmbrUniqid = (String) Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbruniqid");
					userFamilyList.add(new MvpFamilymbrTbl(childname,childemail,childmobile,childisd,String.valueOf(fmbrmembertype),String.valueOf(fmbrprfaccess),fmbrUniqid));
					//listCount=societyWingList.size();
				}
			}			 
			ActionContext.getContext().getSession().put("TOWNSHIPLIST", townshiplist);
		    ActionContext.getContext().getSession().put("societyList", societyList);
		     System.out.println("Step 2 : Edit User Block Completed" );
		} catch (Exception ex) {
			System.out.println("Exception editUser : " + ex);
		}
		return SUCCESS;
	}

	public String edituserformFunction() {
		try {				
			Commonutility.toWriteConsole("Step : User / Member / Resident [UAM] Update Called [Start]");
			boolean result = false;
			obj.put("servicecode", "SI0009");
			obj.put("userId", usercreateObj.getUserId());
			if (usercreateObj.getUserName() == null) {
				obj.put("userName", "");
			} else {
				obj.put("userName", "");
			}
			obj.put("townshipId", townshipid);
			obj.put("societyId", societyId);
			obj.put("societyname", societynamecur);
			obj.put("emailId",usercreateObj.getEmailId());
			obj.put("isdno", usercreateObj.getIsdCode());
			obj.put("mobileNo", usercreateObj.getMobileNo());
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
			
			if(usercreateObj.getPostalid() == null){
				obj.put("pinid",0);
			}
			else{
				obj.put("pinid",usercreateObj.getPostalid());
			}
			
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
			obj.put("numOfLogins",usercreateObj.getNumOfLogins());
			
			String jsonTextFinal = obj.toString();
			System.out.println("jsonTextFinal : "+jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("usermanagement.update.user");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);

			String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			String message = (String) json.get("message");			
			 if (message.equalsIgnoreCase("EmailExist")) {
				addFieldError("usercreateObj.emailId", getText("user.email.id.already.register"));
				return "input";
			} else if (message.equalsIgnoreCase("mobnoExist")) {
				addFieldError("usercreateObj.mobileNo", getText("user.mobile.no.already.register"));
				return "input";
			}

			if (statusCode.equalsIgnoreCase("0") && respCode.equalsIgnoreCase("0000")) {
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
			System.out.println("edituserformFunction Exception : "+ ex);
			alert.setCls("danger");
			alert.setMsg(getText("Error in user updating"));
			alertList.add(alert);
			return "error";
		}

	}
	
	public String groupTypeUpdate() throws JSONException {
		try {
		System.out.println("Step 1 : Update Group Enter." );
		obj.put("servicecode", "SI0008");
		obj.put("groupcode", selectval);
		if(flg!=null && !flg.equalsIgnoreCase("null") && flg.equalsIgnoreCase("")){
			obj.put("flg", flg);
		}else{
			obj.put("flg", "");
		}
		obj.put("userId", selectId);
		String jsonTextFinal = obj.toString();
		String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
		String finalUrl = getText("usermanagement.groupnameUpdated.adminss");
		String response = commonObj.jsonRequest(finalUrl, temp);		
		JSONObject json = new JSONObject(response);		
	} catch (Exception ex) {
		alert.setCls("error");
		alert.setMsg(getText("Error in user updating"));
		alertList.add(alert);
		return "error";
	}
		return "success";
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<UserMasterTblVo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserMasterTblVo> userList) {
		this.userList = userList;
	}

	public Integer getDeleteusrid() {
		return deleteusrid;
	}

	public void setDeleteusrid(Integer deleteusrid) {
		this.deleteusrid = deleteusrid;
	}

	public Integer getUseruniqueId() {
		return useruniqueId;
	}

	public void setUseruniqueId(Integer useruniqueId) {
		this.useruniqueId = useruniqueId;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public Integer getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(Integer groupCode) {
		this.groupCode = groupCode;
	}

	public Integer getTownshipid() {
		return townshipid;
	}

	public void setTownshipid(Integer townshipid) {
		this.townshipid = townshipid;
	}

	public TownshipMstTbl getTownshipmaster() {
		return townshipmaster;
	}

	public void setTownshipmaster(TownshipMstTbl townshipmaster) {
		this.townshipmaster = townshipmaster;
	}

	public List<SocietyMstTbl> getSocietyList() {
		return societyList;
	}

	public void setSocietyList(List<SocietyMstTbl> societyList) {
		this.societyList = societyList;
	}

	public SocietyMstTbl getSocietydata() {
		return societydata;
	}

	public void setSocietydata(SocietyMstTbl societydata) {
		this.societydata = societydata;
	}

	public List<TownshipMstTbl> getTownshiplist() {
		return townshiplist;
	}

	public void setTownshiplist(List<TownshipMstTbl> townshiplist) {
		this.townshiplist = townshiplist;
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

	public String getLocgroup() {
		return locgroup;
	}

	public void setLocgroup(String locgroup) {
		this.locgroup = locgroup;
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

	public String getFamEmailId() {
		return famEmailId;
	}

	public void setFamEmailId(String famEmailId) {
		this.famEmailId = famEmailId;
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

	public List<FlatMasterTblVO> getFlatList() {
		return FlatList;
	}

	public void setFlatList(List<FlatMasterTblVO> flatList) {
		FlatList = flatList;
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

	public String getFammemtype_user() {
		return fammemtype_user;
	}

	public void setFammemtype_user(String fammemtype_user) {
		this.fammemtype_user = fammemtype_user;
	}

	public String getFamprfaccess_user() {
		return famprfaccess_user;
	}

	public void setFamprfaccess_user(String famprfaccess_user) {
		this.famprfaccess_user = famprfaccess_user;
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

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}
	
}
