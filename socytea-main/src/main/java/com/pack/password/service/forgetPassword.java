package com.pack.password.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.login.persistense.UserMasterTblVo;

public class forgetPassword extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emailrMobile;
	private String statusscode;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	 private Common commonObj = new CommonDao();
	 private String retypepassword;
	 private UserMasterTblVo custRegObj = new UserMasterTblVo(null, null, null,null, null, null,null,null, 0, null);

	public String execute() {
		try{		
			data.put("servicecode", "SI2001");
			obj.put("emailid", emailrMobile);
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("user.forget.password.action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Forget password response : " + response);
			JSONObject json = new JSONObject(response);
		// JSONObject json_data = json.getJSONObject("data");
			statusscode = json.getString("statuscode");
		/*if(statusCode.equalsIgnoreCase("0")){
			alert.setCls("success");
			alert.setMsg(getText("forget.password.email.send"));
			alertList.add(alert);
		}else if(statusCode.equalsIgnoreCase("4")){
			alert.setCls("danger");
			alert.setMsg(getText("forget.password.email.already.exist"));
			alertList.add(alert);
		}*/
		}catch(Exception ex){
			Commonutility.toWriteConsole("Forget forgetPassword.class password Exception : " + ex);
		}
		return SUCCESS;
	}
	
	public String changePasswordAction() {

		try {		
		data.put("servicecode", "SI2002");
		Map sessionMap = ActionContext.getContext().getSession();		
		data.put("servicecode", "SI2001");
		obj.put("userid", sessionMap.get("USERID"));
		obj.put("oldpassword", custRegObj.getUserName());
		obj.put("newpassword", custRegObj.getPassword());
		obj.put("retypepassword", retypepassword);
		data.put("data", obj);
		String jsonTextFinal = data.toString();
		jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
		String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);

		String finalUrl = getText("change.password.action");
		String response = commonObj.jsonRequest(finalUrl, temp);
		JSONObject json = new JSONObject(response);		
		String statusCode = json.getString("statuscode");
		if(statusCode.equalsIgnoreCase("0")){
			alert.setCls("success");
			alert.setMsg(getText("password.changed.success"));
			alertList.add(alert);
			return "success";
		} else if(statusCode.equalsIgnoreCase("4")){
			alert.setCls("danger");
			alert.setMsg(getText("old.password.wrong"));
			alertList.add(alert);
			return "input";
		}
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Change password Exception in forgetPassword.class : " + ex);
		}
		return SUCCESS;
	}


	public String getEmailrMobile() {
		return emailrMobile;
	}

	public void setEmailrMobile(String emailrMobile) {
		this.emailrMobile = emailrMobile;
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


	public String getRetypepassword() {
		return retypepassword;
	}


	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}


	public UserMasterTblVo getCustRegObj() {
		return custRegObj;
	}


	public void setCustRegObj(UserMasterTblVo custRegObj) {
		this.custRegObj = custRegObj;
	}


	public String getStatusscode() {
		return statusscode;
	}


	public void setStatusscode(String statusscode) {
		this.statusscode = statusscode;
	}
	

}
