package com.socilaindia.facilityMgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class Facilityservices extends ActionSupport{
	private String facilityId;
	private String townshipId;
	private String societyId;
	private String townshipName;
	private String societyName;
	private String facname;
	private String place;
	private String facdesc;
	private String activeKey;
	private File facImage;
	private String facImageFileName;
	private String facProfileImage;
	private String status;
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	
	private static final long serialVersionUID = 1L;

	public String execute() {
		return SUCCESS;
	}

	public String createFacility() {
		 // byte imgByt[] = null;
		  Log logwrite = null;
		  Map sessionMap = null;
		  JSONObject obj = null;
		  JSONObject data = null;
		  JSONObject lvrRcvrespjsonobj = null;
		  try{
			  	logwrite = new Log();
				obj = new JSONObject();
				data = new JSONObject();
				sessionMap = ActionContext.getContext().getSession();
				logwrite.logMessage("Step 1 : facility Create Called [Start]", "info", Facilityservices.class);	
				//imgByt = profileUpdate.toReadFiletoBytes(facImage);
				//obj.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
				if(facImage!=null){
					obj.put("imgsrchpth", facImage.getAbsolutePath());
				} else {
					obj.put("imgsrchpth", "");
				}
				obj.put("imagename", facImageFileName);							
				obj.put("status", "1");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				if(societyId!=null && !societyId.equalsIgnoreCase("")&& !societyId.equalsIgnoreCase("-1")){
					obj.put("societyId", societyId);
				} else {
					obj.put("societyId", String.valueOf(sessionMap.get("sSoctyId")));
				}
				obj.put("facname", facname );
				obj.put("place", place );
				obj.put("facdesc", facdesc );
				data.put("servicecode", "SI3600001");
				data.put("servicefor", "1");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				String finalUrl = getText("socialindia.facility.newfacility");
				String response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrespjsonobj = new JSONObject(response);
				String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
		            alert.setCls("success");
		            alert.setMsg(getText("facility.Create.Success"));
		            alertList.add(alert);
		            sessionMap.put("alertList", alertList);
		            return "success";
				} else {
		            alert.setCls("danger");
		            alert.setMsg(getText("facility.Create.Error"));
		            alertList.add(alert);
		            sessionMap.put("alertList", alertList);
		            return "input";
		          }
				
			  
		  }catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : Facility Create Called Facilityadd.class Exception Found : " + ex);
			  logwrite.logMessage("Step -1 : Facility Create Called Exception Found : "+ex, "error", Facilityservices.class);
			  alert.setCls("danger");
	          alert.setMsg(getText("facility.Create.Error"));
	          alertList.add(alert);
	          sessionMap.put("alertList", alertList);
	          return "input";
		  }		  		 
	  }

	public String editFacilitymethod() {
		Log logwrite = null;
		Map sessionMap = null;
		JSONObject obj = null;
		JSONObject data = null;
		JSONObject lvrRcvrespjsonobj = null;
		JSONArray ar = null;
		try {
			logwrite = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			sessionMap = ActionContext.getContext().getSession();
			if (facilityId == null) {
				if ((facilityId == null)&& sessionMap.get("svrFacilityid") != null) {
					String userunque = (String) sessionMap.get("svrFacilityid");
					facilityId = userunque;
				}
			} else {
				sessionMap.put("svrFacilityid", String.valueOf(facilityId));
			}
			if (activeKey == null) {
				if ((activeKey == null)&& sessionMap.get("svrActiveKey") != null) {
					String ackey = (String) sessionMap.get("svrActiveKey");
					activeKey = ackey;
				}
			} else {
				sessionMap.put("svrActiveKey", String.valueOf(activeKey));
			}
			logwrite.logMessage("Step 1 : facility edit Called [Start]", "info", Facilityservices.class);	
			obj.put("facilityId", facilityId);
			obj.put("activeKey", activeKey);
			data.put("servicecode", "SI3600003");
			data.put("servicefor", "3");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.facility.editfacility");
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRcvrespjsonobj = new JSONObject(response);
			JSONObject json_data = lvrRcvrespjsonobj.getJSONObject("data");
			JSONObject json = json_data.getJSONObject("facilitydetails");
			townshipId = json.getString("townshipId");
			townshipName = json.getString("townshipname");
			societyId = json.getString("societyId");
			societyName = json.getString("societyName");
			String imagePath = json.getString("imagePath");
			String imagewebPath = json.getString("imagewebPath");
			JSONArray functiontext = json.getJSONArray("societyidss");
			String facImageFile = "";
			for (int j = 0; j < functiontext.length(); j++) {
				JSONArray temm = null;
				temm = functiontext.getJSONArray(j);
				facname = (String) temm.get(0);
				place = (String) temm.get(1);
				facdesc = (String) temm.get(2);
				facImageFileName = (String) temm.get(3);
				facilityId = String.valueOf(temm.get(5));
			}
				//facImageFileName=imagewebPath+""+facilityId+"/"+facImageFile;
			ActionContext.getContext().getSession().put("facilityImage", facImageFileName);
		  } catch(Exception ex){
			 Commonutility.toWriteConsole("Step -1 : Facility update Called Facilityedit.class Exception Found : " + ex);
			 logwrite.logMessage("Step -1 : Facility update Called Exception Found : "+ex, "error", Facilityservices.class); 
		}

		return SUCCESS;
	}
	public String updateFacilitymethod() {

		//byte imgByt[] = null;
		Log logwrite = null;
		Map sessionMap = null;
		JSONObject obj = null;
		JSONObject data = null;
		JSONObject lvrRcvrespjsonobj = null;
		try {
			logwrite = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			sessionMap = ActionContext.getContext().getSession();
			logwrite.logMessage("Step 1 : facility Create Called [Start]","info", Facilityservices.class);
			//imgByt = profileUpdate.toReadFiletoBytes(facImage);
			//obj.put("imgencdata",profileUpdate.toByteAryToBase64EncodeStr(imgByt));
			if(facImage!=null){
				obj.put("imgsrchpth", facImage.getAbsolutePath());
			} else {
				obj.put("imgsrchpth", "");
			}					
			obj.put("imagename", facImageFileName);
			obj.put("facilityId", facilityId);
			obj.put("status", "1");
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			if (societyId != null && !societyId.equalsIgnoreCase("")&& !societyId.equalsIgnoreCase("-1")) {
				obj.put("societyId", societyId);
			} else {
				obj.put("societyId", String.valueOf(sessionMap.get("sSoctyId")));
			}
			obj.put("facname", facname);
			obj.put("place", place);
			obj.put("facdesc", facdesc);
			data.put("servicecode", "SI3600002");
			data.put("servicefor", "2");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.facility.updatefacility");
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRcvrespjsonobj = new JSONObject(response);
			String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("facility.update.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("facility.update.Error"));
				alertList.add(alert);
				return "input";
			}
				
			  
		  }catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : Facility Create Called Facilityadd.class Exception Found : " + ex);
				logwrite.logMessage("Step -1 : Facility Create Called Exception Found : "+ex, "error", Facilityservices.class); 
		  }
		  return SUCCESS;
	}

	public String deActivefacility() {
		JSONObject obj = null;
		JSONObject data = null;
		Common commonObj = null;
		JSONObject json = null;
		Map sessionMap = null;
		try {
			obj = new JSONObject();
			data = new JSONObject();
			commonObj = new CommonDao();
			sessionMap = ActionContext.getContext().getSession();
			obj.put("facilityId", facilityId);
			obj.put("statusflg", status);
			obj.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
			data.put("servicecode", "SI360004");
			data.put("servicefor", "4");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.facility.facilitydeactive");
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Response : deactive ::::::::::::::::"+ response);
			json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Deactive successfully."));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Error ."));
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {
			Commonutility.toWriteConsole("Exception ----deactive--" + ex);
		}
		return SUCCESS;

	}
	public String getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(String townshipId) {
		this.townshipId = townshipId;
	}
	public String getSocietyId() {
		return societyId;
	}
	public void setSocietyId(String societyId) {
		this.societyId = societyId;
	}
	public String getFacname() {
		return facname;
	}
	public void setFacname(String facname) {
		this.facname = facname;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getFacdesc() {
		return facdesc;
	}
	public void setFacdesc(String facdesc) {
		this.facdesc = facdesc;
	}
	public File getFacImage() {
		return facImage;
	}
	public void setFacImage(File facImage) {
		this.facImage = facImage;
	}
	public String getFacImageFileName() {
		return facImageFileName;
	}
	public void setFacImageFileName(String facImageFileName) {
		this.facImageFileName = facImageFileName;
	}
	public String getFacProfileImage() {
		return facProfileImage;
	}
	public void setFacProfileImage(String facProfileImage) {
		this.facProfileImage = facProfileImage;
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
	public String getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	public String getActiveKey() {
		return activeKey;
	}
	public void setActiveKey(String activeKey) {
		this.activeKey = activeKey;
	}
	public String getTownshipName() {
		return townshipName;
	}
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}
	public String getSocietyName() {
		return societyName;
	}
	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	  

}
