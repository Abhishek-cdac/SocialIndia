package com.socialindia.committeeMgmt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class committeeCreation extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	UserMasterTblVo usercreateObj = new UserMasterTblVo(null, null, null, null, null, null, null, null, 0, null, null, null, null, 0,null);
	private Common commonObj = new CommonDao();
	private Integer societyId;
	private Integer townshipId;
	private Integer committeeRole;
	private Integer accessMedia;
	
	public String execute(){	
	Map sessionMap = ActionContext.getContext().getSession();
	JSONObject obj = null;
	JSONObject data = null;
	try {		
		obj = new JSONObject();
		data = new JSONObject();
		obj.put("servicecode", "SI0037");
		data.put("firstName", usercreateObj.getFirstName());
		data.put("lastName", usercreateObj.getLastName());
		data.put("committeeRole", committeeRole);
		data.put("emailId", usercreateObj.getEmailId());
		data.put("mobileNo", usercreateObj.getMobileNo());
		data.put("isdcode", usercreateObj.getIsdCode());		
		int societyval=(int) sessionMap.get("sSoctyId");
		if(societyval==-1){
			data.put("societyId", societyId);
		}else{
			data.put("societyId", societyval);
		}
		data.put("noofblocks", usercreateObj.getNoOfBlocks());
		data.put("accesschannel", accessMedia);
		data.put("dob", usercreateObj.getDob());
		data.put("gender", usercreateObj.getGender());
		obj.put("data", data);
		String jsonTextFinal = obj.toString().trim();
		jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
		String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
		String finalUrl = getText("committeemanagement.creation.Action");		
		String response = commonObj.jsonRequest(finalUrl, temp);
		Commonutility.toWriteConsole("committeeCreation.class response : " + response);
		JSONObject json = new JSONObject(response);
		String statusCode = (String) json.get("statuscode");
		String message = (String) json.get("message");		
		 if (statusCode!=null && (statusCode.equalsIgnoreCase("03") ||  message.equalsIgnoreCase("mobnoExist"))) {
			addFieldError("usercreateObj.mobileNo", getText("user.mobile.no.already.register"));
			return "input";
		} else  if (statusCode.equalsIgnoreCase("00")) {
			alert.setCls("success");
			if(Commonutility.checkempty(message)){
				alert.setMsg(message);
			} else{
				alert.setMsg(getText("success.create.committee"));
			}			
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			// while shortcut townshipmgmt via create Society
			if( (String) sessionMap.get("ivrTwsipid")!=null){
				sessionMap.remove("ivrTwsipid");
			}
			if( (String) sessionMap.get("ivrTwsipname")!=null){
				sessionMap.remove("ivrTwsipname");	
			}		
			if( (String) sessionMap.get("ivrSwityid")!=null){
				sessionMap.remove("ivrSwityid");
			}
			if( (String) sessionMap.get("ivrSwityname")!=null){
				sessionMap.remove("ivrSwityname");	
			}				
			return "success";
		} else {
			alert.setCls("danger");
			if(Commonutility.checkempty(message)){
				alert.setMsg(message);
			} else{
				alert.setMsg(getText("error.create.committee"));
			}			
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		}
	} catch (Exception ex) {
		alert.setCls("danger");		
		alert.setMsg(getText("error.create.committee"));		
		alertList.add(alert);		
		sessionMap.put("alertList", alertList);
		Commonutility.toWriteConsole("Exception found committeeCreation.class :  " + ex);
		return "input";
	} finally {
		obj = null;
		data = null;
	}
	//return SUCCESS;
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
	public UserMasterTblVo getUsercreateObj() {
		return usercreateObj;
	}
	public void setUsercreateObj(UserMasterTblVo usercreateObj) {
		this.usercreateObj = usercreateObj;
	}
	public Integer getSocietyId() {
		return societyId;
	}
	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}
	public Integer getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(Integer townshipId) {
		this.townshipId = townshipId;
	}
	public Integer getCommitteeRole() {
		return committeeRole;
	}
	public void setCommitteeRole(Integer committeeRole) {
		this.committeeRole = committeeRole;
	}
	public Integer getAccessMedia() {
		return accessMedia;
	}
	public void setAccessMedia(Integer accessMedia) {
		this.accessMedia = accessMedia;
	}
	
}
