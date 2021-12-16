package com.socialindia.generalmgnt;

import java.io.File;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.letspay.vo.CityMasterTblVo;
import com.letspay.vo.StateMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.IDCardMasterTblVO;
import com.socialindia.generalmgnt.persistance.PostalCodeMasterTblVO;
import com.socialindia.generalmgnt.persistance.StaffCategoryMasterTblVO;
import com.socialindia.generalmgnt.persistance.StaffMasterTblVo;
import com.socialindia.generalmgnt.persistance.StaffSlryTblVO;
import com.socialindia.generalmgnt.persistance.StaffWrkTblVO;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.profileUpdate;

public class staffcreation extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StaffMasterTblVo staffRegObj = new StaffMasterTblVo(null, null, null, 0, null);
	StaffCategoryMasterTblVO staffCategoryObj =new StaffCategoryMasterTblVO(0, null);
	IDCardMasterTblVO idcardObj = new IDCardMasterTblVO(0,null);
	PostalCodeMasterTblVO pstlcodeObj = new PostalCodeMasterTblVO(0,null);
	StaffSlryTblVO staffSalObj = new StaffSlryTblVO();
	StaffWrkTblVO staffWorkObj = new StaffWrkTblVO();
	StateMasterTblVo stateData = new StateMasterTblVo(0, null);
	CityMasterTblVo cityData = new CityMasterTblVo(0, null);
	
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	private String date;
	private Integer StaffuniqueId;
	private Integer deletestaffid;
	private String staffCountry;
	private String staffState;
	private String staffCity;
	private String staffIdcardtypname;
	private String staffcategoryname;
	private String staffpincode;
	private Integer staffCityId;
	private Integer staffStateId;
	private String staffCountryId;	
	private Integer staffpincodeid;
	private Double staffExtrawages;
	private String staffSalary_str;
	private String extraWages;
	private String workdetails;
	Date workStartDate;
	Date workEndDate;
	
	String workStartDateStr;
	String workEndDateStr;
	
	private File staffImage;
	private String staffImageFileName;
	private String staffProfileImage;
	private int staffsessID;
	private int iVOstaffcategid;
	private int pstlId;
	private String ID_CARD_TYP; // id card type id
	private String locIdCategoryid; // staff category
	private String Country_id;
	private String State_id;
	private String City_id;	
	private String pstlcode_id;
	//private Integer staff_cat_id;
	private String Staffcatidval;
	private String Staffcateidname;
	private String staffIdcardtypeval;
	private String staffCountryname;
	private String staffStateino;
	private String staffcityno;
	private String staffpinno;
	
	private String staffstatename;
	private String staffCityname;
	private String staffpincodename;
	private String Weeklyoff_day;
	byte imgByt[]=null;
	public String execute() {			
		return SUCCESS;
	}
	public String creationForm() {
		Log logwrite = null;
		JSONObject obj = null;
		JSONObject data = null;
		JSONObject json_data = null;
		JSONObject json = null;
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			obj = new JSONObject();
			data = new JSONObject();			
			logwrite = new Log();
			Commonutility.toWriteConsole("Step 1 : Staff Creation Called [Start]." +Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Staff Creation Called [Start].", "info", staffcreation.class);			
			//imgByt = profileUpdate.toReadFiletoBytes(staffImage);
			obj.put("servicecode", "SI4002");		
			obj.put("userName", staffRegObj.getStaffName());
			obj.put("staffEmail", staffRegObj.getStaffEmail());
			obj.put("ISDcode", staffRegObj.getISDcode());
			obj.put("staffMobno", staffRegObj.getStaffMobno());
			obj.put("staffGender", staffRegObj.getStaffGender());
			if (locIdCategoryid!=null && !locIdCategoryid.equalsIgnoreCase("null") && !locIdCategoryid.equalsIgnoreCase("")) {
				obj.put("staffCategory", String.valueOf(locIdCategoryid));
			} else {
				obj.put("staffCategory", "");
			}
			
			obj.put("staffWorkhours", String.valueOf(staffRegObj.getWorkinghours()));
			if(Commonutility.checkempty(ID_CARD_TYP) ){
				obj.put("staffIdtype", String.valueOf(ID_CARD_TYP)); // id card  type id
			} else {
				obj.put("staffIdtype", "");
			}
			
			obj.put("staffIdno", staffRegObj.getStaffIdcardno());
			obj.put("staffCountry", String.valueOf(Country_id));
			obj.put("staffState", String.valueOf(State_id));
			obj.put("staffCity", String.valueOf(City_id));
			obj.put("staffAddr", staffRegObj.getStaffAddr());
			obj.put("Postalcode", String.valueOf(pstlcode_id));
			//obj.put("imageweb", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
			if (staffImage!=null) {
				obj.put("staffimgsrchpath", staffImage.getAbsolutePath());
			} else {
				obj.put("staffimgsrchpath", "");
			}
			obj.put("staffImageFileName", staffImageFileName);
			obj.put("status", 1);
			obj.put("entrybyid", String.valueOf(sessionMap.get("USERID")));
			obj.put("groupCodeType", 2);
			obj.put("company_id", String.valueOf(staffRegObj.getStaffcompany_hidd_txt()));
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("staffmanagement.user.creation");
			Commonutility.toWriteConsole("Step 2 : Staff Creation Called Service API : "+finalUrl);
			logwrite.logMessage("Step 2 : Staff Creation Called Service API : "+finalUrl, "info", staffcreation.class);
			String response = commonObj.jsonRequest(finalUrl, temp);			
			json = new JSONObject(response);
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			String respCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "respcode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			json_data = (JSONObject) Commonutility.toHasChkJsonRtnValObj(json, "data");
			if ((statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("01")) && respCode.equalsIgnoreCase("R0107")) {
				Commonutility.toWriteConsole("Step 3 : Staff Creation Called [End]. Success");
				logwrite.logMessage("Step 3 : Staff Creation Called [End]. Success", "info", staffcreation.class);
				staffProfileImage=json_data.getString("staffimage");
				staffsessID =json_data.getInt("staffid");			
				 if(staffImage!=null){
					 ActionContext.getContext().getSession().put("staffProfileImage", staffProfileImage);
				 }
				 	ActionContext.getContext().getSession().put("staffSessID", staffsessID);
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else{
					alert.setMsg(getText("Staff created successfully"));
				}				
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "success";
				
			} else if ((statusCode.equalsIgnoreCase("02")) && respCode.equalsIgnoreCase("R0108")) { 				
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else{
					alert.setMsg(getText("Error.staffreg.exists"));
				}						
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "exists";
			} else {
				Commonutility.toWriteConsole("Step 3 : Staff Creation Called [End]. API return Not Success : " +  Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : Staff Creation Called [End]. API return Not Success", "info", staffcreation.class);
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else{
					alert.setMsg(getText("Error.staffreg.catch"));
				}						
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}
		} catch (Exception ex) {			
			Commonutility.toWriteConsole("Step -1 : Exception Found in StaffCreate.creationForm(): " + ex);
			logwrite.logMessage("Step -1 : Exception Found in StaffCreate.creationForm(): " + ex, "info", staffcreation.class);
			alert.setCls("danger");			
			alert.setMsg(getText("Error.staffreg.catch"));							
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally {
			 logwrite = null;
			 obj = null;
			 data = null; json_data = null; json = null;
		}		
	}
	
	public String editStaff() {
		JSONObject obj = null;
		JSONObject locObjRecvJson = null;// Receive String to json
		Log logwrite = null;
		JSONObject finaljj = null;
		try {
			obj = new JSONObject();
			finaljj = new JSONObject();
			logwrite = new Log();
			Commonutility.toWriteConsole("Step 1 : Staff Edit Sigle select Called [Start]." +Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Staff Edit Sigle select Called [Start].", "info", staffcreation.class);
		    Map currentSession = ActionContext.getContext().getSession();			
			if(StaffuniqueId==null ){					
				if((StaffuniqueId==null || StaffuniqueId==-1 || StaffuniqueId==0)  && currentSession.get("currentsession_StaffuniqueId")!=null){							
					String stfiddd=(String)currentSession.get("currentsession_StaffuniqueId");
					StaffuniqueId=Integer.parseInt(stfiddd);
				}
			}else{
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_StaffuniqueId", String.valueOf(StaffuniqueId));					
			}			    			    		    				
			obj.put("staffid", StaffuniqueId);
			obj.put("ivrDecissBlkflag", "3");
			
			finaljj.put("servicecode", "SI4003");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString().trim();
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String url = getText("staffmanagement.staff.updated");
			
			Commonutility.toWriteConsole("Step 2 : Staff Edit Sigle select Service Api : " + url);
			logwrite.logMessage("Step 2 : Staff Edit Sigle select  Service Api : " + url, "info", staffcreation.class);
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;
		    String response = commonObj.jsonRequest(url, temp);		    
		    if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
		    	boolean ivIsJson = Commonutility.toCheckIsJSON(response);
		    	if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					JSONObject json_data = locObjRecvJson.getJSONObject("data");
		    		staffRegObj.setStaffID((Integer)Commonutility.toHasChkJsonRtnValObj(json_data,"staffid"));
		    		staffRegObj.setStaffName((String)Commonutility.toHasChkJsonRtnValObj(json_data, "staffName"));
		    		staffRegObj.setStaffEmail((String)Commonutility.toHasChkJsonRtnValObj(json_data, "staffEmail"));
		    		staffRegObj.setISDcode((String)Commonutility.toHasChkJsonRtnValObj(json_data, "staffIsdcode"));
		    		staffRegObj.setStaffMobno((String)Commonutility.toHasChkJsonRtnValObj(json_data, "staffMobno"));
		    		staffRegObj.setStaffGender((String)Commonutility.toHasChkJsonRtnValObj(json_data, "staffGender"));
		    		staffRegObj.setWorkinghours((Integer)Commonutility.toHasChkJsonRtnValObj(json_data,"workinghours"));//no use
		    		if((Integer)Commonutility.toHasChkJsonRtnValObj(json_data, "workinghours")==0){
		    			staffRegObj.setWrkhourstr("");
		    		}else{
		    			staffRegObj.setWrkhourstr(String.valueOf(Commonutility.toHasChkJsonRtnValObj(json_data, "workinghours")));
		    		}
		    		idcardObj.setiVOcradid(json_data.getInt("staffIdcardtype"));		    				
		    		staffIdcardtypname= json_data.getString("staffIdcardno");
		    		int staffIdcardtypno=json_data.getInt("staffIdcardtype");
		    		staffIdcardtypeval = String.valueOf(staffIdcardtypno);
		    		staffRegObj.setStaffIdcardno(json_data.getString("idproofno"));
		    		staffRegObj.setStaffAddr(json_data.getString("staffAddr"));	
		    		Staffcateidname =json_data.getString("staffcategory");
		    		Staffcatidval = json_data.getString("staffcategoryid");	    	
		    				//Staffcatidval = String.valueOf(staff_cat_id);		    				
		    		staffRegObj.setImageNameWeb(json_data.getString("staffimage"));		    		
		    		staffProfileImage=json_data.getString("staffimage");
					if (staffProfileImage != null) {
		    			ActionContext.getContext().getSession().put("staffProfileImage", staffRegObj.getImageNameWeb());
					} else {
		    			ActionContext.getContext().getSession().put("staffProfileImage", "");
		    		}		
		    		int staffStateIdno=json_data.getInt("staffStateId");
		    		staffStateino =String.valueOf(staffStateIdno);
		    		staffstatename=json_data.getString("staffState");
		    		int staffCityIdno=json_data.getInt("staffCityId");
		    		staffcityno =String.valueOf(staffCityIdno);
		    		staffCityname=json_data.getString("staffCity");
//		    		int staffpincodeidno=json_data.getInt("pincodeid");
//		    		staffpinno =String.valueOf(staffpincodeidno);	
		    		
		    		int pin = json_data.getInt("pincode");
		    		if(pin ==0 ){
		    			staffpinno="";
		    		}
		    		else{
		    			staffpinno=pin+"";
		    		}
		    		
		    		int staffCountryIdno=json_data.getInt("staffCountryId");
		    		staffCountryId =String.valueOf(staffCountryIdno);
		    		staffCountryname=json_data.getString("staffCountry");
		    		stateData.setStateId(json_data.getInt("staffStateId"));
		    		cityData.setCityId(json_data.getInt("staffCityId"));
		    				//staffCategoryObj.setiVOstaffcategid(json_data.getInt("staffcategoryid"));
		    		staffRegObj.setStaffState(json_data.getInt("staffStateId"));		    				
//		    		pstlcodeObj.setPstlId(json_data.getInt("pincodeid"));		    				
		    		staffRegObj.setStaffcompanyname(json_data.getString("companyname"));
		    		staffRegObj.setStaffcompany_hidd_txt(json_data.getString("companyid"));	
		    	} 
		    }			    	 
		    	ActionContext.getContext().getSession().put("staffSessID", staffRegObj.getStaffID());
		    	Commonutility.toWriteConsole("Step 3 : Staff Edit Sigle select [End] :  "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : Staff Edit Sigle select [End].", "info", staffcreation.class);
			} catch (Exception ex) {
		    	Commonutility.toWriteConsole("Step -1 : Staff Edit Sigle select  Exception Found : "+ ex +" Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step -1 : Staff Edit Sigle select  Exception Found : "+ ex, "info", staffcreation.class);
		    }finally{
		    	obj = null;
		    	locObjRecvJson = null;// Receive String to json
				logwrite = null;
				finaljj = null;
		    }
			
		    return SUCCESS;
		  	
	}

	public String editstaffformFunction() {
		JSONObject obj = null;		
		Log logwrite = null;
		JSONObject lvrRspjsonobj = null;
		JSONObject lvrRspdatajsonobj = null;
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			obj = new JSONObject();
			logwrite = new Log();
			Commonutility.toWriteConsole("Step 1 : Staff Edit Bock editstaffformFunction() [Start] :  "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Staff Edit Block editstaffformFunction() [Start].", "info", staffcreation.class);															
			if(staffCountryId == null || staffCountryId.equalsIgnoreCase("0")  || staffCountryId.equalsIgnoreCase("")){
				staffCountryId ="null";
			} else{
				staffCountryId=staffCountryId;
			}
			Commonutility.toWriteConsole("Test : 2 "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			if(staffStateino == null || staffStateino.equalsIgnoreCase("0") || staffStateino.equalsIgnoreCase("")){
				staffStateino ="null";
			} else{
				staffStateino=staffStateino;
			}
			Commonutility.toWriteConsole("Test : 3 "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			if(staffcityno==null || staffcityno.equalsIgnoreCase("0") || staffcityno.equalsIgnoreCase("")){
				staffcityno ="null";
			}else{
				staffcityno=staffcityno;
			}
			Commonutility.toWriteConsole("Test : 4 "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			if(staffpinno == null || staffpinno.equalsIgnoreCase("0") || staffpinno.equalsIgnoreCase("")){
				staffpinno ="null";
			}else{
				staffpinno=staffpinno;
			}				
			//imgByt=profileUpdate.toReadFiletoBytes(staffImage);
			obj.put("ivrDecissBlkflag","2");
			obj.put("staffid",StaffuniqueId);			
			obj.put("staffName", staffRegObj.getStaffName());			
			obj.put("staffEmail", staffRegObj.getStaffEmail());			
			obj.put("staffIsdcode", staffRegObj.getISDcode());			
			obj.put("staffMobno", staffRegObj.getStaffMobno());			
			obj.put("staffGender", staffRegObj.getStaffGender());			
			obj.put("staffIdcardtype",staffIdcardtypeval);			
			obj.put("staffcategory", Staffcatidval);
			obj.put("companyid",staffRegObj.getStaffcompany_hidd_txt());			
			obj.put("staffworkinghours",staffRegObj.getWrkhourstr());			
			obj.put("staffIdcardno", staffRegObj.getStaffIdcardno());
			obj.put("staffAddr", staffRegObj.getStaffAddr());
			obj.put("staffCountry",staffCountryId);
			obj.put("staffState", staffStateino);
			obj.put("staffCity", staffcityno);
			obj.put("staffpincode", staffpinno);		
			
			Commonutility.toWriteConsole("staffpincode >>>>>>>>>>>>>>>>>>>>>>>>>>>>"+staffpinno);
			
			//obj.put("imageweb", profileUpdate.toByteAryToBase64EncodeStr(imgByt));			
			if(staffImage!=null) {
				obj.put("staffimgsrchpath", staffImage.getAbsolutePath());
			} else {
				obj.put("staffimgsrchpath", "");
			}
			Commonutility.toWriteConsole("Test : 5 "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			obj.put("staffImageFileName", staffImageFileName);			
			
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI4004");
			finaljj.put("data",obj);
			String jsonTextFinal = finaljj.toString().trim();
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			Commonutility.toWriteConsole("Test : 16 "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			String finalUrl = getText("staffmanagement.updateaction.staff");
			
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Test : 17 "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			lvrRspjsonobj = new JSONObject(response);
			lvrRspdatajsonobj = (JSONObject) Commonutility.toHasChkJsonRtnValObj(lvrRspjsonobj, "data");
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(lvrRspjsonobj, "statuscode");
			String respCode = (String) Commonutility.toHasChkJsonRtnValObj(lvrRspjsonobj, "respcode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(lvrRspjsonobj, "message");
			if ((statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")) && respCode.equalsIgnoreCase("R0109")) {
				Commonutility.toWriteConsole("Step 3 : Staff Edit Block editstaffformFunction() [End] - Success:  "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : Staff Edit Block editstaffformFunction() [End]. - Success", "info", staffcreation.class);
				staffProfileImage = lvrRspdatajsonobj.getString("staffimage");
				staffsessID = lvrRspdatajsonobj.getInt("staffid");
				ActionContext.getContext().getSession().put("staffSessID", staffsessID);
				if (staffImage != null) {
					ActionContext.getContext().getSession() .put("staffProfileImage", staffProfileImage);
					alert.setCls("success");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("Staff updated successfully"));
					}					
					alertList.add(alert);
					return "success";
				} else {
					ActionContext.getContext().getSession().put("staffProfileImage", "");
					alert.setCls("success");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("Staff updated successfully"));
					}
					alertList.add(alert);
					return "success";
				}				 				
			} else if ((statusCode.equalsIgnoreCase("02")) && respCode.equalsIgnoreCase("R0110")) { 
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Error.staffreg.exists"));
				}				
				alertList.add(alert);
				return "exists";
			} else {
				Commonutility.toWriteConsole("Step 3 : Staff Edit Block editstaffformFunction() [End] - Not SUccess:  "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : Staff Edit Block editstaffformFunction() [End] - Not SUccess", "info", staffcreation.class);
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Staff not updated.Error."));
				}				
				alertList.add(alert);
				return "input";
			}
			
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Staff Edit Block editstaffformFunction() [Exception] :  "+ex+" Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step -1 : Staff Edit Block editstaffformFunction() [Exception] : "+ex, "error", staffcreation.class);
			alert.setCls("error");			
			alert.setMsg(getText("Staff not updated.Error."));			
			alertList.add(alert);
			return "error";
		} finally {
			obj = null;		
			logwrite = null;
			lvrRspjsonobj = null;
			lvrRspdatajsonobj = null;
			
		}

		
	}
	public String deleteStaffActionFunction() {		
		boolean result = false;
		JSONObject obj = null;
		try {
			if (deletestaffid != 0 && deletestaffid > 0) {
				obj = new JSONObject();
				JSONObject finaljj =new JSONObject();
				Map sessionMap = ActionContext.getContext().getSession();				
				finaljj.put("servicecode", "SI4005");
				obj.put("ivrDecissBlkflag","5");
				obj.put("staffid", deletestaffid);
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				finaljj.put("data", obj);
				String jsonTextFinal = finaljj.toString().trim();
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("staffmanagement.delete.staff");
				String response = commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");				
				result = json_data.getBoolean("resultFlag");
				// result = uam.deleteUserDetials(deleteusrid);
				String statusCode = (String) json.get("statuscode");
				String respCode = (String) json.get("respcode");
				String lvrRspmsg = (String)Commonutility.toHasChkJsonRtnValObj(json, "message");
				if ((statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")) && respCode.equalsIgnoreCase("R0112")) {
					if (result == false) {
						alert.setCls("danger");
						if(Commonutility.checkempty(lvrRspmsg)){
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg("Staff profile not deactivate. Error");
						}
						alertList.add(alert);
						return "input";
					} else {
						alert.setCls("success");
						if(Commonutility.checkempty(lvrRspmsg)){
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg("Staff profile deactivate successfully");
						}						
						alertList.add(alert);
						return "success";
					}
				} else {
					alert.setCls("danger");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("Text.customerreg.key.error"));
					}					
					alertList.add(alert);
					return "input";
				}
			}
		} catch (Exception ex) {
			alert.setCls("danger");
			alert.setMsg("Staff profile not deactivate. Error");
			alertList.add(alert);
			return "error";
		}
		return SUCCESS;
	}
	
	public String viewStaffActionFunction() {
		 JSONObject locObjRecvJson = null;//Receive String to json	
		 JSONObject locObjRecvdataJson = null;// Receive Data Json		
		 JSONObject locObjRspdataJson = null;// Response Data Json
		 JSONObject obj = null;
		    try {
		    	Map currentSession = ActionContext.getContext().getSession();	
		    	if(StaffuniqueId==null ){					
					if((StaffuniqueId==null || StaffuniqueId==-1 || StaffuniqueId==0)  && currentSession.get("currentsession_StaffuniqueId")!=null){							
						String stfiddd=(String)currentSession.get("currentsession_StaffuniqueId");
						StaffuniqueId=Integer.parseInt(stfiddd);
					}
				}else{
					currentSession = ActionContext.getContext().getSession();
					currentSession.put("currentsession_StaffuniqueId", String.valueOf(StaffuniqueId));					
				}		
			Commonutility.toWriteConsole("Step 1: Staff View Called [Start]");
			obj = new JSONObject();
			obj.put("staffid", StaffuniqueId);
			obj.put("ivrDecissBlkflag", "3");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI4006");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString().trim();
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String url = getText("staffmanagement.staff.updated");
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;
			String response = commonObj.jsonRequest(url, temp);
			System.out.println("response_______________________________"+response);
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					locObjRecvdataJson = locObjRecvJson.getJSONObject("data");
					staffRegObj.setStaffID(locObjRecvdataJson.getInt("staffid"));
					staffRegObj.setStaffName(locObjRecvdataJson.getString("staffName"));
					staffRegObj.setStaffEmail(locObjRecvdataJson.getString("staffEmail"));
					staffRegObj.setStaffMobno(locObjRecvdataJson.getString("staffMobno"));
					staffRegObj.setISDcode(locObjRecvdataJson.getString("staffIsdcode"));
					String gendername = locObjRecvdataJson.getString("staffGender");
					if (gendername.equalsIgnoreCase("1")) {
						gendername = "Male";
					} else if (gendername.equalsIgnoreCase("2")) {
						gendername = "Female";
					} else {
						gendername = "Other";
					}
					staffRegObj.setStaffGender(gendername);
					staffIdcardtypname = locObjRecvdataJson.getString("staffIdcardno");
					staffRegObj.setStaffAddr(locObjRecvdataJson.getString("staffAddr"));
					staffCountry = locObjRecvdataJson.getString("staffCountry");
					staffState = locObjRecvdataJson.getString("staffState");
					staffCity = locObjRecvdataJson.getString("staffCity");
					staffcategoryname = locObjRecvdataJson.getString("staffcategory");
					staffRegObj.setWorkinghours(locObjRecvdataJson.getInt("workinghours"));
					staffRegObj.setStaffIdcardno(locObjRecvdataJson.getString("idproofno"));
					staffpincode = locObjRecvdataJson.getString("pincode");
					staffRegObj.setStaffcompanyname(locObjRecvdataJson.getString("companyname"));
					staffRegObj.setImageNameWeb(locObjRecvdataJson.getString("staffimage"));	    				
				}

			}		    	
		    	  ActionContext.getContext().getSession().put("staffProfileImage", staffRegObj.getImageNameWeb());
		    	  ActionContext.getContext().getSession().put("staffSessID", staffRegObj.getStaffID());		    		  		    	  
		    } catch (Exception ex) {		     
		      Commonutility.toWriteConsole("Exception in get state----->> " + ex);
		    }
		    return SUCCESS;
		  }
	
	public String salaryStaffActionFunction() {//select
		    JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json
			JSONObject obj = null;
		    try {
		    	obj = new JSONObject();
		    	Commonutility.toWriteConsole("Step 1 : Staff Salary Fetching.");		
		    	Map currentSession = ActionContext.getContext().getSession();			
				if(StaffuniqueId==null ){					
					if((StaffuniqueId==null || StaffuniqueId==-1 || StaffuniqueId==0)  && currentSession.get("currentsession_StaffuniqueId")!=null){							
						String stfiddd=(String)currentSession.get("currentsession_StaffuniqueId");
						StaffuniqueId=Integer.parseInt(stfiddd);
					}
				}else{
					currentSession = ActionContext.getContext().getSession();
					currentSession.put("currentsession_StaffuniqueId", String.valueOf(StaffuniqueId));					
				}	
		    	obj.put("staffid", StaffuniqueId);
		    	obj.put("ivrDecissBlkflag", "3");		    	
				JSONObject finaljj=new JSONObject();
				finaljj.put("servicecode", "SI4007");
				finaljj.put("data", obj);			    	
				String jsonTextFinal = finaljj.toString().trim();
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
				Commonutility.toWriteConsole("Final JSON : " + jsonTextFinal);
		    	String url=getText("staffmanagement.salary.staff");
		    	String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	 String response = commonObj.jsonRequest(url, temp);	
		    	 Commonutility.toWriteConsole("Response salary : " +response);
		    	  if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
		    		  boolean ivIsJson = Commonutility.toCheckIsJSON(response);
		    		  if (ivIsJson) {
		    			  locObjRecvJson = new JSONObject(response);
		    			  JSONObject json_data = locObjRecvJson.getJSONObject("data");		    			
		    				int staffid= json_data.getInt("staffid");		    						    			
		    				staffRegObj.setStaffID(json_data.getInt("staffid"));
		    				staffRegObj.setStaffName(json_data.getString("staffName"));
		    				staffRegObj.setStaffEmail(json_data.getString("staffEmail"));
		    				staffRegObj.setStaffMobno(json_data.getString("staffMobno"));
		    				String gendername=json_data.getString("staffGender");
					if (gendername.equalsIgnoreCase("1")) {
						gendername = "Male";
					} else if (gendername.equalsIgnoreCase("2")) {
						gendername = "Female";
					} else {
						gendername = "Other";
					}
					staffRegObj.setStaffGender(gendername);
					staffSalary_str = (String) json_data.getString("monthlySalary");
					if (staffSalary_str != null && !staffSalary_str.equalsIgnoreCase("null") && !staffSalary_str.equalsIgnoreCase("")) {
						staffSalObj.setMonthlySalary(staffSalary_str);

					} else {
						staffSalObj.setMonthlySalary("");
					}
					String tt = json_data.getString("extraWages");
					if (tt != null && !tt.equalsIgnoreCase("null") && !tt.equalsIgnoreCase("")) {
						extraWages = (String) json_data.getString("extraWages");
						staffSalObj.setExtraWages(extraWages);
					} else {
						staffSalObj.setExtraWages("");
					}
				}
			}    	  
		    } catch (Exception ex) {
		    	Commonutility.toWriteConsole("Exception found in staffcreation.class salaryStaffActionFunction() : "+ex);
		    }finally{
		    	 locObjRecvJson = null;	
				 locObjRecvdataJson = null;
				 locObjRspdataJson = null;
		    }
		    return SUCCESS;
		  }
	
	public String StaffSalaryCreateUpdateAction() {//update
			Log log =null;
		    JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json
			JSONObject obj = null;
			JSONObject data = null;
		    try {
		    	obj = new JSONObject();
		    	data =  new JSONObject();
		    	 log=new Log();
		         Commonutility.toWriteConsole("Step 1 : Salary Update StaffSalaryCreateUpdateAction() : "+extraWages);						    	        
				obj.put("staffid", staffRegObj.getStaffID());
				obj.put("monthlySalary",Commonutility.toCheckNullEmpty(staffSalary_str));
				obj.put("extraWages", Commonutility.toCheckNullEmpty(extraWages));
				obj.put("ivrDecissBlkflag", "1");
				Map sessionMap = ActionContext.getContext().getSession();			
				obj.put("entryby", Commonutility.toCheckNullEmpty(sessionMap.get("USERID")));				
				data.put("servicecode", "SI4008");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();				
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);							
				String finalUrl = getText("staffmanagement.salary.createupdate");	
				Commonutility.toWriteConsole("Step 2 : Service Called : "+finalUrl);
				String response = commonObj.jsonRequest(finalUrl, temp);
				Commonutility.toWriteConsole("Step 3 : JSON Rsp : " + response);
				locObjRecvdataJson = new JSONObject(response);
				String statusCode = (String) locObjRecvdataJson.get("statuscode");
				String respCode = (String) locObjRecvdataJson.get("respcode");
				locObjRspdataJson = locObjRecvdataJson.getJSONObject("data");
				String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "message");
				if ((statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")) && respCode.equalsIgnoreCase("R0116")) {	
					Commonutility.toWriteConsole("Step 4 :Salary Update Success");
					alert.setCls("success");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg("Salary updated successfully");
					}					
					alertList.add(alert);
					return "success";		    	  
				}else{
					Commonutility.toWriteConsole("Step 4 :Salary Update error");
					alert.setCls("error");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg("Salary not updated.");
					}
					alertList.add(alert);
					return "success";
				}
		    }catch (Exception ex) {
		     Commonutility.toWriteConsole("Exception found in StaffSalaryCreateUpdateAction () : "+ex);
		     log.logMessage("Exception found in StaffSalaryCreateUpdateAction () : "+ex, "error", staffcreation.class);
		     	alert.setCls("error");			
				alert.setMsg("Salary not updated.");				
				alertList.add(alert);
				 return SUCCESS;
		    }finally{
		    	   locObjRecvJson = null;//Receive String to json	
					 locObjRecvdataJson = null;// Receive Data Json		
					 locObjRspdataJson = null;// Response Data Json
		    }
		    
		  }

	public String Staffworkalertform() {// Work Select
			ResourceBundle ivrRbuilder = ResourceBundle.getBundle("applicationResources");
		    JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json
			JSONObject locRqstJsonObj=null;
			Log log=null;
			commonObj = new CommonDao();
			JSONObject obj = null;
		    try {
		    	obj = new JSONObject();
		    	log=new Log();
		    	Map currentSession = ActionContext.getContext().getSession();			
				if(StaffuniqueId==null ){					
					if((StaffuniqueId==null || StaffuniqueId==-1 || StaffuniqueId==0)  && currentSession.get("currentsession_StaffuniqueId")!=null){							
						String stfiddd=(String)currentSession.get("currentsession_StaffuniqueId");
						StaffuniqueId=Integer.parseInt(stfiddd);
					}
				}else{
					currentSession = ActionContext.getContext().getSession();
					currentSession.put("currentsession_StaffuniqueId", String.valueOf(StaffuniqueId));					
				}			    	
		    	locRqstJsonObj=new JSONObject();
		    	Commonutility.toWriteConsole("Step 1 : Work Alert will call.");
		    	//log.logMessage("Step 1 : Work Alert will call.", "info", staffcreation.class);
		    	obj.put("staffid", StaffuniqueId);
		    	obj.put("ivrDecissBlkflag", "3");							
		    	locRqstJsonObj.put("servicecode", "SI4009");
		    	locRqstJsonObj.put("data", obj);			    	
				String jsonTextFinal = locRqstJsonObj.toString().trim();
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
		    	String url=getText("staffmanagement.workalart.staffwork");
		    	String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	String response = commonObj.jsonRequest(url, temp);
		    	Commonutility.toWriteConsole("Step 2 : Response : "+response);
		    	  //log.logMessage("Step 2 : Work Alert will call.", "info", staffcreation.class);
		    	  if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
		    		  boolean ivIsJson = Commonutility.toCheckIsJSON(response);
		    		  if (ivIsJson) {
		    			  locObjRecvJson = new JSONObject(response);
		    			  JSONObject json_data = locObjRecvJson.getJSONObject("data");		    				
		    				staffRegObj.setStaffID(json_data.getInt("staffid"));
		    				staffRegObj.setStaffName(json_data.getString("staffName"));
		    				staffRegObj.setStaffEmail(json_data.getString("staffEmail"));
		    				staffRegObj.setStaffMobno(json_data.getString("staffMobno"));
		    				workdetails = json_data.getString("Workdetails");
		    				String work1 =json_data.getString("WorkStartdate");
		    				String work2 =json_data.getString("Workenddate");
		    				Weeklyoff_day =json_data.getString("weeklyoff");		    				
		    				DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		    				SimpleDateFormat inputFormat1 = new SimpleDateFormat(ivrRbuilder.getString("calander.format.date"));
		    				if(work1!=null && !work1.equalsIgnoreCase("") && !work1.equalsIgnoreCase("null")){
		    					 Date Startdate = inputFormat.parse(work1);
		    					 workStartDate = Startdate;		
		    					 workStartDateStr =  inputFormat1.format(Startdate);
		    				}else{
		    					 workStartDateStr =  "";
		    				}
		    				 if(work2!=null && !work2.equalsIgnoreCase("") && !work2.equalsIgnoreCase("null")){
		    					 Date Enddate = inputFormat.parse(work2);    			    				 
			    				 workEndDate = Enddate;	
			    				 workEndDateStr =  inputFormat1.format(Enddate);
		    				 }else{		    					 
			    				 workEndDateStr =  "";
		    				 }		    				 
		    				} 		    							
		    			}			    				    	
		    } catch (Exception ex) {		      
		      Commonutility.toWriteConsole("Exception foundstaffcreation.Staffworkalertform() : "+ex);
		    }
		    return SUCCESS;
		  }
	
	//work alert create and update
	public String StaffWrkAlertCreateUpdateAction() {// Work Create And Update
		JSONObject locObjRecvJson = null;//Receive String to json	
		JSONObject locObjRecvdataJson = null;// Receive Data Json		
		JSONObject locObjRspdataJson = null;// Response Data Json	
		Log log=null;
		Map sessionMap = null;	
		JSONObject obj = null;
		JSONObject data = null;
	    try {
	    	obj = new JSONObject();
	    	data = new JSONObject();
		    sessionMap = ActionContext.getContext().getSession();	
			log = new Log();
			obj.put("staffid", staffRegObj.getStaffID());
			obj.put("Workdetails", workdetails);
			Commonutility.toWriteConsole("workStartDateStr --- " + workStartDateStr);
			obj.put("WorkStartdate", workStartDateStr);
			obj.put("Workenddate", workEndDateStr);
			obj.put("weeklyoff", Weeklyoff_day);
			obj.put("ivrDecissBlkflag", "1");
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			data.put("servicecode", "SI4010");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();					
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("staffmanagement.workalert.createupdate");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			JSONObject json_data = json.getJSONObject("data");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			if ((statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")) && respCode.equalsIgnoreCase("R0114")) {									
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg("Work alert updated successfully");
				}				
				alertList.add(alert);
				return "success";	    	 
			}
		    }catch (Exception ex) {
		     Commonutility.toWriteConsole("Exception Found in staffcreation.StaffWrkAlertCreateUpdateAction() : "+ex);
		     log.logMessage("Exception StaffWrkAlertCreateUpdateAction() : "+ex, "error", staffcreation.class);
		     alert.setCls("danger");				
			 alert.setMsg("Work alert not updated. Error.");						
			 alertList.add(alert);
				return "input";	
		    } finally {
		    	
		    }
		    return SUCCESS;
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

	public StaffMasterTblVo getStaffRegObj() {
		return staffRegObj;
	}

	public void setStaffRegObj(StaffMasterTblVo staffRegObj) {
		this.staffRegObj = staffRegObj;
	}

	public Integer getStaffuniqueId() {
		return StaffuniqueId;
	}

	public void setStaffuniqueId(Integer staffuniqueId) {
		StaffuniqueId = staffuniqueId;
	}

	public Integer getDeletestaffid() {
		return deletestaffid;
	}

	public void setDeletestaffid(Integer deletestaffid) {
		this.deletestaffid = deletestaffid;
	}

	public String getStaffCountry() {
		return staffCountry;
	}

	public void setStaffCountry(String staffCountry) {
		this.staffCountry = staffCountry;
	}

	public String getStaffState() {
		return staffState;
	}

	public void setStaffState(String staffState) {
		this.staffState = staffState;
	}

	public String getStaffCity() {
		return staffCity;
	}

	public void setStaffCity(String staffCity) {
		this.staffCity = staffCity;
	}

	public Integer getStaffCityId() {
		return staffCityId;
	}

	public void setStaffCityId(Integer staffCityId) {
		this.staffCityId = staffCityId;
	}

	public Integer getStaffStateId() {
		return staffStateId;
	}

	public void setStaffStateId(Integer staffStateId) {
		this.staffStateId = staffStateId;
	}

	/*
	 * public Integer getStaffCountryId() { return staffCountryId; } public void
	 * setStaffCountryId(Integer staffCountryId) { this.staffCountryId =
	 * staffCountryId; }
	 */
	public StaffSlryTblVO getStaffSalObj() {
		return staffSalObj;
	}

	public void setStaffSalObj(StaffSlryTblVO staffSalObj) {
		this.staffSalObj = staffSalObj;
	}

	public Double getStaffExtrawages() {
		return staffExtrawages;
	}

	public void setStaffExtrawages(Double staffExtrawages) {
		this.staffExtrawages = staffExtrawages;
	}

	public String getStaffSalary_str() {
		return staffSalary_str;
	}

	public void setStaffSalary_str(String staffSalary_str) {
		this.staffSalary_str = staffSalary_str;
	}

	public String getWorkdetails() {
		return workdetails;
	}

	public void setWorkdetails(String workdetails) {
		this.workdetails = workdetails;
	}

	public Date getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}

	public Date getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(Date workEndDate) {
		this.workEndDate = workEndDate;
	}

	public StaffCategoryMasterTblVO getStaffCategoryObj() {
		return staffCategoryObj;
	}

	public void setStaffCategoryObj(StaffCategoryMasterTblVO staffCategoryObj) {
		this.staffCategoryObj = staffCategoryObj;
	}

	public IDCardMasterTblVO getIdcardObj() {
		return idcardObj;
	}

	public void setIdcardObj(IDCardMasterTblVO idcardObj) {
		this.idcardObj = idcardObj;
	}

	public PostalCodeMasterTblVO getPstlcodeObj() {
		return pstlcodeObj;
	}

	public void setPstlcodeObj(PostalCodeMasterTblVO pstlcodeObj) {
		this.pstlcodeObj = pstlcodeObj;
	}

	public String getStaffIdcardtypname() {
		return staffIdcardtypname;
	}

	public void setStaffIdcardtypname(String staffIdcardtypname) {
		this.staffIdcardtypname = staffIdcardtypname;
	}

	public String getStaffcategoryname() {
		return staffcategoryname;
	}

	public void setStaffcategoryname(String staffcategoryname) {
		this.staffcategoryname = staffcategoryname;
	}

	public String getStaffpincode() {
		return staffpincode;
	}

	public void setStaffpincode(String staffpincode) {
		this.staffpincode = staffpincode;
	}

	public StateMasterTblVo getStateData() {
		return stateData;
	}

	public void setStateData(StateMasterTblVo stateData) {
		this.stateData = stateData;
	}

	public CityMasterTblVo getCityData() {
		return cityData;
	}

	public void setCityData(CityMasterTblVo cityData) {
		this.cityData = cityData;
	}

	public String getStaffImageFileName() {
		return staffImageFileName;
	}

	public void setStaffImageFileName(String staffImageFileName) {
		this.staffImageFileName = staffImageFileName;
	}

	public File getStaffImage() {
		return staffImage;
	}

	public void setStaffImage(File staffImage) {
		this.staffImage = staffImage;
	}

	public String getStaffProfileImage() {
		return staffProfileImage;
	}

	public void setStaffProfileImage(String staffProfileImage) {
		this.staffProfileImage = staffProfileImage;
	}

	public int getStaffsessID() {
		return staffsessID;
	}

	public void setStaffsessID(int staffsessID) {
		this.staffsessID = staffsessID;
	}

	public int getiVOstaffcategid() {
		return iVOstaffcategid;
	}

	public void setiVOstaffcategid(int iVOstaffcategid) {
		this.iVOstaffcategid = iVOstaffcategid;
	}

	public int getPstlId() {
		return pstlId;
	}

	public void setPstlId(int pstlId) {
		this.pstlId = pstlId;
	}

	public String getID_CARD_TYP() {
		return ID_CARD_TYP;
	}

	public void setID_CARD_TYP(String iD_CARD_TYP) {
		ID_CARD_TYP = iD_CARD_TYP;
	}

	public String getLocIdCategoryid() {
		return locIdCategoryid;
	}

	public void setLocIdCategoryid(String locIdCategoryid) {
		this.locIdCategoryid = locIdCategoryid;
	}

	public String getCountry_id() {
		return Country_id;
	}

	public void setCountry_id(String country_id) {
		Country_id = country_id;
	}

	public String getState_id() {
		return State_id;
	}

	public void setState_id(String state_id) {
		State_id = state_id;
	}

	public String getCity_id() {
		return City_id;
	}

	public void setCity_id(String city_id) {
		City_id = city_id;
	}

	public String getPstlcode_id() {
		return pstlcode_id;
	}

	public void setPstlcode_id(String pstlcode_id) {
		this.pstlcode_id = pstlcode_id;
	}

/*	public Integer getStaff_cat_id() {
		return staff_cat_id;
	}

	public void setStaff_cat_id(Integer staff_cat_id) {
		this.staff_cat_id = staff_cat_id;
	}*/

	public String getStaffcatidval() {
		return Staffcatidval;
	}

	public void setStaffcatidval(String staffcatidval) {
		Staffcatidval = staffcatidval;
	}

	public String getStaffcateidname() {
		return Staffcateidname;
	}

	public void setStaffcateidname(String staffcateidname) {
		Staffcateidname = staffcateidname;
	}

	public String getStaffCountryname() {
		return staffCountryname;
	}

	public void setStaffCountryname(String staffCountryname) {
		this.staffCountryname = staffCountryname;
	}

	public String getStaffstatename() {
		return staffstatename;
	}

	public void setStaffstatename(String staffstatename) {
		this.staffstatename = staffstatename;
	}

	public String getStaffCityname() {
		return staffCityname;
	}

	public void setStaffCityname(String staffCityname) {
		this.staffCityname = staffCityname;
	}

	public Integer getStaffpincodeid() {
		return staffpincodeid;
	}

	public void setStaffpincodeid(Integer staffpincodeid) {
		this.staffpincodeid = staffpincodeid;
	}

	public String getStaffpincodename() {
		return staffpincodename;
	}

	public void setStaffpincodename(String staffpincodename) {
		this.staffpincodename = staffpincodename;
	}

	public String getStaffIdcardtypeval() {
		return staffIdcardtypeval;
	}

	public void setStaffIdcardtypeval(String staffIdcardtypeval) {
		this.staffIdcardtypeval = staffIdcardtypeval;
	}

	public String getStaffCountryId() {
		return staffCountryId;
	}

	public void setStaffCountryId(String staffCountryId) {
		this.staffCountryId = staffCountryId;
	}

	public String getStaffStateino() {
		return staffStateino;
	}

	public void setStaffStateino(String staffStateino) {
		this.staffStateino = staffStateino;
	}

	public String getStaffcityno() {
		return staffcityno;
	}

	public void setStaffcityno(String staffcityno) {
		this.staffcityno = staffcityno;
	}

	public String getStaffpinno() {
		return staffpinno;
	}

	public void setStaffpinno(String staffpinno) {
		this.staffpinno = staffpinno;
	}

	public String getExtraWages() {
		return extraWages;
	}

	public void setExtraWages(String extraWages) {
		this.extraWages = extraWages;
	}

	public String getWeeklyoff_day() {
		return Weeklyoff_day;
	}

	public void setWeeklyoff_day(String weeklyoff_day) {
		Weeklyoff_day = weeklyoff_day;
	}

	public String getWorkStartDateStr() {
		return workStartDateStr;
	}

	public void setWorkStartDateStr(String workStartDateStr) {
		this.workStartDateStr = workStartDateStr;
	}

	public String getWorkEndDateStr() {
		return workEndDateStr;
	}

	public void setWorkEndDateStr(String workEndDateStr) {
		this.workEndDateStr = workEndDateStr;
	}
}
