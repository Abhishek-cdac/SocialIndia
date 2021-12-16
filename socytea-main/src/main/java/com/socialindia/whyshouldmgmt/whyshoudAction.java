package com.socialindia.whyshouldmgmt;

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
import com.socialindia.login.EncDecrypt;

public class whyshoudAction extends ActionSupport{
	private String whyshtdes;
	private String whydes;
	private String whyshouldId;
	private String whyshouldstatus;
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private static final long serialVersionUID = 1L;

	public String execute() {
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
			logwrite.logMessage("Step 1 : whyshoudAction Create Called [Start]", "info",whyshoudAction.class);
			obj.put("whyshtdes", whyshtdes);
			obj.put("whydes", whydes);
			obj.put("status", "1");
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			obj.put("societyId", String.valueOf(sessionMap.get("sSoctyId")));
			data.put("servicecode", "SI3700001");
			data.put("servicefor", "1");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.whyshould.newwhyshould");
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRcvrespjsonobj = new JSONObject(response);
			String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("whyshoudAction.Create.Success"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("whyshoudAction.Create.Error"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}

		} catch (Exception ex) {
			System.out.println("Step -1 : whyshoudAction Create Called Facilityadd.class Exception Found : "+ ex);
			logwrite.logMessage("Step -1 : whyshoudAction Create Called Exception Found : "+ ex, "error", whyshoudAction.class);
			alert.setCls("danger");
			alert.setMsg(getText("whyshoudAction.Create.Error"));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		}		
	}
	  public String editwhyshouldMethod(){
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
			logwrite.logMessage("Step 1 : whyshoudAction Select Called [Start]", "info",whyshoudAction.class);
			obj.put("uniqueid", whyshouldId);
			data.put("servicecode", "SI3700003");
			data.put("servicefor", "3");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.whyshould.selectwhyshould");
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRcvrespjsonobj = new JSONObject(response);
			String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
			JSONObject json_data = lvrRcvrespjsonobj.getJSONObject("data");
			JSONObject json = json_data.getJSONObject("whyshould");
			whyshouldId = json.getString("whyshouldId");
			whyshtdes = json.getString("whyshouldshrtdesc");
			whydes = json.getString("whyshoulddesc");
			whyshouldstatus = json.getString("whyshouldstatus");

		} catch (Exception ex) {
			System.out.println("Step -1 : whyshoudAction Select Called Facilityadd.class Exception Found : "+ ex);
			logwrite.logMessage("Step -1 : whyshoudAction Select Called Exception Found : "+ ex, "error", whyshoudAction.class);
		}

		return SUCCESS;
	}

	public String modifywhyshouldMethod() {
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
			logwrite.logMessage("Step 1 : whyshoudAction Create Called [Start]", "info",whyshoudAction.class);
			obj.put("whyshtdes", whyshtdes);
			obj.put("whydes", whydes);
			obj.put("whyshouldId", whyshouldId);
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			data.put("servicecode", "SI3700002");
			data.put("servicefor", "2");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.whyshould.modifywhyshould");
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response ------------- " + response);
			lvrRcvrespjsonobj = new JSONObject(response);
			String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("whyshoudAction.Update.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("whyshoudAction.Update.Error"));
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {
			System.out.println("Step -1 : whyshoudAction Create Called Facilityadd.class Exception Found : "+ ex);
			logwrite.logMessage("Step -1 : whyshoudAction Create Called Exception Found : "+ ex, "error", whyshoudAction.class);
		}

		return SUCCESS;
	}

	public String deactiveWhyshould() {

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
			obj.put("whyshouldId", whyshouldId);
			obj.put("whyshouldstatus", whyshouldstatus);
			data.put("servicecode", "SI370004");
			data.put("servicefor", "4");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.whyshould.whyshoulddeactive");
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("Response : deactive ::::::::::::::::"+ response);
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
			System.out.println("Exception ----deactive--" + ex);
		}
		return SUCCESS;

	}
	  
	public String getWhyshtdes() {
		return whyshtdes;
	}
	public void setWhyshtdes(String whyshtdes) {
		this.whyshtdes = whyshtdes;
	}
	public String getWhydes() {
		return whydes;
	}
	public void setWhydes(String whydes) {
		this.whydes = whydes;
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
	public String getWhyshouldId() {
		return whyshouldId;
	}
	public void setWhyshouldId(String whyshouldId) {
		this.whyshouldId = whyshouldId;
	}
	public String getWhyshouldstatus() {
		return whyshouldstatus;
	}
	public void setWhyshouldstatus(String whyshouldstatus) {
		this.whyshouldstatus = whyshouldstatus;
	}
	  

}
