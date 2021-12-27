package com.socialindia.monitorMgmt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class SignonFailureServices extends ActionSupport {
	private String deleteid;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();

	public String execute() {
		return SUCCESS;
	}

	public String toDeletesignonfailure() {
		Map sessionMap = ActionContext.getContext().getSession();
		JSONObject finaljj = null;
		String temp = null;
		JSONArray ja = null;
		String finalUrl = null;
		String jsonTextFinal = null;
		String response = null;
		try {
			JSONObject locObjRecvJson = null;// Receive String to json

			obj.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
			obj.put("crntusrgrpid", String.valueOf(sessionMap.get("GROUPCODE")));
			obj.put("sigonfailureid", deleteid);
			obj.put("crntusrsocietyid",
					String.valueOf(sessionMap.get("sSoctyId")));
			finaljj = new JSONObject();
			finaljj.put("servicecode", "SI10012");
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			System.out.println("request== " + jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.signonfailure.delete");
			response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response -------- " + response);
			JSONObject json = new JSONObject(response);
			JSONObject json_data = json.getJSONObject("data");
			String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			if (statusCode.equalsIgnoreCase("0")) {
					alert.setCls("success");
					alert.setMsg(getText("Signon failure report deleted successfully"));
					alertList.add(alert);
					return "success";
				} else {
					alert.setCls("error");
					alert.setMsg(getText("Signon Failure Report Deleted Error"));
					alertList.add(alert);
					return "input";
				}
			

				
			

		} catch (Exception Ex) {
			System.out.println("Exception system tracking view-------------- " + Ex);
		} finally {
			finaljj = null;
			response = null;
			finalUrl = null;
			temp = null;
			jsonTextFinal = null;
		}
		return SUCCESS;
	}

	public String getDeleteid() {
		return deleteid;
	}

	public void setDeleteid(String deleteid) {
		this.deleteid = deleteid;
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

	
	

}
