package com.socialindia.committeeMgmt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class committeeUpdateAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	UserMasterTblVo usercreateObj = new UserMasterTblVo(null, null, null, null, null, null, null, null, 0, null, null, null, null, 0,null);		
	private int townshipId;
	private int societyId;
	private int committeeid;	
	private AlertVo alert = new AlertVo();
	private Common commonObj = new CommonDao();	
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	public String execute(){
		Log log = null;
		JSONObject lvrdataJsonobj = null;
		JSONObject lvrFrqstJsonObj = null;
		Map sessionMap = null;
	try{
			lvrdataJsonobj = new JSONObject();
			lvrFrqstJsonObj = new JSONObject();
			log = new Log();
			System.out.println("Step 1 : Committee Update Action Called [Start]");		
			log.logMessage("Step 1 : Committee Update Action Called [Start]", "info", committeeUpdateAction.class);
			lvrdataJsonobj.put("userId", usercreateObj.getUserId());
			lvrdataJsonobj.put("emailid", usercreateObj.getEmailId());
			lvrdataJsonobj.put("firstName", usercreateObj.getFirstName());
			lvrdataJsonobj.put("lastName", usercreateObj.getLastName());
			lvrdataJsonobj.put("dob", usercreateObj.getDob());
			lvrdataJsonobj.put("gender", usercreateObj.getGender());
			sessionMap = ActionContext.getContext().getSession();
			int societyval = (int) sessionMap.get("sSoctyId");
			if (societyval == -1) {
				lvrdataJsonobj.put("societyId", societyId);
			} else {
				lvrdataJsonobj.put("societyId", societyval);
			}
			lvrdataJsonobj.put("townshipId", townshipId);
			lvrdataJsonobj.put("societyId", societyId);
			lvrdataJsonobj.put("committeerole", committeeid);
			lvrdataJsonobj.put("accesschannel", usercreateObj.getAccessChannel());
			
			lvrFrqstJsonObj.put("servicecode", "SI0040");
			lvrFrqstJsonObj.put("data", lvrdataJsonobj);
			String jsonTextFinal = lvrFrqstJsonObj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("committee.management.update.action");;
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = json.getString("statuscode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			if (statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")) {
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("success.update.committee"));
				}				
				alertList.add(alert);
			} else {
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("error.update.committee"));
				}
				
				alertList.add(alert);
			}
		} catch (Exception e) {
			
			log.logMessage("Step -1 : committeeUpdateAction Exception : "+e, "error", committeeUpdateAction.class);
			Commonutility.toWriteConsole("Step -1 : committeeUpdateAction Exception : "+e);
			alert.setCls("danger");			
			alert.setMsg(getText("error.update.committee"));						
			alertList.add(alert);
		} finally {
			log = null;
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

	public int getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(int townshipId) {
		this.townshipId = townshipId;
	}

	public int getSocietyId() {
		return societyId;
	}

	public void setSocietyId(int societyId) {
		this.societyId = societyId;
	}

	public UserMasterTblVo getUsercreateObj() {
		return usercreateObj;
	}

	public void setUsercreateObj(UserMasterTblVo usercreateObj) {
		this.usercreateObj = usercreateObj;
	}

	public int getCommitteeid() {
		return committeeid;
	}

	public void setCommitteeid(int committeeid) {
		this.committeeid = committeeid;
	}

}
