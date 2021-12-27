package com.socialindia.compaign;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class Donateservices extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String townshipName;
	private String townshipid;
	private String societyName;
	private String societyId;
	private String instituname;
	private String institucontact;
	private String instituaddress;
	private File image;
	private String imageFileName;
	private String donateId;
	private String staffProfileImage;
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	public String execute(){
		return SUCCESS;
		
	}
	public String createDonation(){
		JSONObject obj = null;
		JSONObject data = null;
		Map sessionMap = null;
		Log logwrite = null;
		JSONObject lvrRcvrespjsonobj = null;

		try {
			if (instituname!=null && (institucontact!=null && instituaddress!=null )){
			logwrite = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			sessionMap = ActionContext.getContext().getSession();
			obj.put("townshipid", townshipid);
			obj.put("instituname", instituname);
			obj.put("institucontact", institucontact);
			obj.put("instituaddress", instituaddress);
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			if(societyId!=null && !societyId.equalsIgnoreCase("null") && !societyId.equalsIgnoreCase("")){
				obj.put("societyId", societyId);
			}else{
				obj.put("societyId", String.valueOf(sessionMap.get("sSoctyId")));
			}
			
			if (image!=null) {
				obj.put("imgsrchpath", image.getAbsolutePath());
				obj.put("imageName", imageFileName);
			} else {
				obj.put("imgsrchpath", "");
				obj.put("imageName", "");
			}
			data.put("servicecode", "SI430001");
			data.put("servicefor", "1");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			Commonutility.toWriteConsole("Step 2 : "+jsonTextFinal);
			String finalUrl = getText("socialindia.Donate.createdonate");
			Commonutility.toWriteConsole("Step 3 : "+finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Step 4 : "+response);
			lvrRcvrespjsonobj = new JSONObject(response);
			String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
			String respCode = (String) lvrRcvrespjsonobj.get("respcode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(lvrRcvrespjsonobj, "message");
			JSONObject json_data = lvrRcvrespjsonobj.getJSONObject("data");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Donation created successfully"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg("Donation Not Created. Error.");
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			
				}
			} else {
				alert.setCls("danger");
				alert.setMsg("Donation Not Created. Error.");
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}

		} catch (Exception ex) {
			alert.setCls("danger");
			alert.setMsg(getText("Text.customerreg.key.error"));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally {
			obj = null; data = null;  sessionMap = null; logwrite = null; lvrRcvrespjsonobj = null;
			System.gc();
		}		
	} 
	public String editDonation(){
		  Log logwrite = null;
		  Map sessionMap = null;
		  JSONObject obj = null;
		  JSONObject data = null;
		  JSONObject lvrRcvrespjsonobj = null;
		  JSONObject json_data = null;
		  String temp = null,finalUrl=null,response=null;
		  try{
			  sessionMap = ActionContext.getContext().getSession();
			  if (donateId == null) {
					if ((donateId == null)&& sessionMap.get("locdonateId") != null) {
						String userunque = (String) sessionMap.get("locdonateId");
						donateId = userunque;
					}
				} else {
					sessionMap.put("locdonateId", String.valueOf(donateId));
				}
			  	logwrite = new Log();
				obj = new JSONObject();
				data = new JSONObject();
				
				logwrite.logMessage("Step 1 : FlashNews view Called [Start]", "info", Donateservices.class);	
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				obj.put("donateId", donateId);
				data.put("servicecode", "SI430003");
				data.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
				data.put("servicefor", "3");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				finalUrl = getText("socialindia.donate.viewdonate");
				response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrespjsonobj = new JSONObject(response);
				String lvrStscode = (String) lvrRcvrespjsonobj.get("statuscode");
				json_data = (JSONObject) Commonutility.toHasChkJsonRtnValObj(lvrRcvrespjsonobj, "data");
				if(Commonutility.checkempty(lvrStscode) && (lvrStscode.equalsIgnoreCase("00") || lvrStscode.equalsIgnoreCase("0"))) {
					JSONObject json = json_data.getJSONObject("donation");
					townshipid = (String)json.getString("townId");
					townshipName = (String)json.getString("townName");
					societyId = (String)json.getString("societyId");
					societyName = (String)json.getString("societyName");
					instituname = (String)json.getString("insName");
					institucontact = (String)json.getString("inscontact");
					instituaddress = (String)json.getString("insAdd");
					imageFileName = (String)json.getString("insimage");
					donateId = (String)json.getString("insId");
					ActionContext.getContext().getSession().put("staffProfileImage",imageFileName);
				}
			  
		  }catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : FlashNews view Called FlashNewsview.class Exception Found : " + ex);
			  logwrite.logMessage("Step -1 : FlashNews Create view Exception Found : "+ex, "error", Donateservices.class);
	          return "input";
		  }		  		 
	  return SUCCESS;
	  
}
	public String modifyDonateAction(){
		JSONObject obj = null;
		JSONObject data = null;
		Map sessionMap = null;
		Log logwrite = null;
		JSONObject lvrRcvrespjsonobj = null;
		try {
			logwrite = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			sessionMap = ActionContext.getContext().getSession();
			obj.put("donateId", donateId);
			obj.put("townshipid", townshipid);
			obj.put("instituname", instituname);
			obj.put("institucontact", institucontact);
			obj.put("instituaddress", instituaddress);
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			if(societyId!=null && !societyId.equalsIgnoreCase("null") && !societyId.equalsIgnoreCase("")){
				obj.put("societyId", societyId);
			}else{
				obj.put("societyId", String.valueOf(sessionMap.get("sSoctyId")));
			}
			
			if (image!=null) {
				obj.put("imgsrchpath", image.getAbsolutePath());
				obj.put("imageName", imageFileName);
			} else {
				obj.put("imgsrchpath", "");
				obj.put("imageName", "");
			}
			data.put("servicecode", "SI430001");
			data.put("servicefor", "2");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.Donate.modifydonate");
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRcvrespjsonobj = new JSONObject(response);
			String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
			String respCode = (String) lvrRcvrespjsonobj.get("respcode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(lvrRcvrespjsonobj, "message");
			JSONObject json_data = lvrRcvrespjsonobj.getJSONObject("data");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Donation updated successfully"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "success";

			} else {
				alert.setCls("danger");
				alert.setMsg("Donation Not Update. Error.");
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}

		} catch (Exception ex) {
			alert.setCls("danger");
			alert.setMsg(getText("Text.customerreg.key.error"));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally {
			obj = null; data = null;  sessionMap = null; logwrite = null; lvrRcvrespjsonobj = null;
			System.gc();
		}
	}
	public String deletedonateAction(){
		  Log logwrite = null;
		  Map sessionMap = null;
		  JSONObject obj = null;
		  JSONObject data = null;
		  JSONObject lvrRcvrespjsonobj = null;
		  JSONObject json_data = null;
		  String temp = null,finalUrl=null,response=null;
		  
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg=null;
			String ivrstatuscode=null;
			JSONObject locObjRecvJson = null;
			//JSONObject locObjRecvdataJson = null;
		  
		  try{
			  	logwrite = new Log();
				obj = new JSONObject();
				data = new JSONObject();
				sessionMap = ActionContext.getContext().getSession();
				logwrite.logMessage("Step 1 : FlashNews Delete Called [Start]", "info", Donateservices.class);	
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				obj.put("donateId", donateId);
				data.put("servicecode", "SI430004");
				data.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
				data.put("servicefor", "4");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				finalUrl = getText("socialindia.Donate.deletedonate");
				response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrespjsonobj = new JSONObject(response);
				json_data = (JSONObject) Commonutility.toHasChkJsonRtnValObj(lvrRcvrespjsonobj, "data");
				
				if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0) {
		    		  boolean ivIsJson = Commonutility.toCheckIsJSON(response);
		    		  if (ivIsJson) {
		    			  locObjRecvJson = new JSONObject(response);
				    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");
				    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
				    	  ivrmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
				    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
							if (ivrstatuscode.equalsIgnoreCase("00") || ivrstatuscode.equalsIgnoreCase("0")) {
								alert.setCls("success");
								if(Commonutility.checkempty(ivrmsg)){
									alert.setMsg("Donation deleted successfully.");
								} else {
									alert.setMsg("Donation deleted successfully.");
								}
								alertList.add(alert);
								return "success";
							} else {
								alert.setCls("danger");
								if(Commonutility.checkempty(ivrmsg)){
									alert.setMsg(ivrmsg);
								} else {
									alert.setMsg("Donation deletion error.");
								}
								alertList.add(alert);
								return "input";
							}
		    		  }
				 } else {
					    alert.setCls("danger");
						alert.setMsg("Donation deletion error.");
						alertList.add(alert);
						return "input";
				 }
			
		  }catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : FlashNews view Called FlashNewsview.class Exception Found : " + ex);
			  logwrite.logMessage("Step -1 : FlashNews Create view Exception Found : "+ex, "error", Donateservices.class);
	          return "input";
		  }		  		 
	  
	  
	  
	  return SUCCESS;
	  
}
	public String getTownshipName() {
		return townshipName;
	}
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}
	public String getTownshipid() {
		return townshipid;
	}
	public void setTownshipid(String townshipid) {
		this.townshipid = townshipid;
	}
	public String getSocietyName() {
		return societyName;
	}
	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}
	public String getSocietyId() {
		return societyId;
	}
	public void setSocietyId(String societyId) {
		this.societyId = societyId;
	}
	public String getInstituname() {
		return instituname;
	}
	public void setInstituname(String instituname) {
		this.instituname = instituname;
	}
	public String getInstitucontact() {
		return institucontact;
	}
	public void setInstitucontact(String institucontact) {
		this.institucontact = institucontact;
	}
	public String getInstituaddress() {
		return instituaddress;
	}
	public void setInstituaddress(String instituaddress) {
		this.instituaddress = instituaddress;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getDonateId() {
		return donateId;
	}
	public void setDonateId(String donateId) {
		this.donateId = donateId;
	}
	public String getStaffProfileImage() {
		return staffProfileImage;
	}
	public void setStaffProfileImage(String staffProfileImage) {
		this.staffProfileImage = staffProfileImage;
	}
	

}
