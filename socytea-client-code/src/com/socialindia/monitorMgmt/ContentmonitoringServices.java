package com.socialindia.monitorMgmt;


import java.io.File;
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
import com.socialindia.eNewsmgmt.EeNewsTblVO;
import com.socialindia.generalmgnt.persistance.CategoryMasterTblVO;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.IDCardMasterTblVO;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.profileUpdate;
import com.socialindia.tendermgmt.TenderDocTblVO;


public class ContentmonitoringServices extends ActionSupport{
	private String uniqueId;
	private String tableflg;
	private String blockrowid;
	private String tblname;
	JSONObject obj = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();
	CategoryMasterTblVO committeeobj = new CategoryMasterTblVO(0, null);
	private static final long serialVersionUID = 1L;

	public String execute() {
		return SUCCESS;
	}

	public String toBlockContent() {
		System.out.println("------- user content blocked Deactivation ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("contentid", String.valueOf(blockrowid));		
			obj.put("tableflg", tableflg);
			obj.put("tablename", tblname);
			obj.put("ivrservicefor", "4");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI10004");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.content.monitoring.dl");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("user content blocked"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("user content blocked error"));
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {			
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class deleteActionfun() Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:ss") +" : "+ex);
		} finally {

		}
		return SUCCESS;
	}

	public String activeContents() {//socialindia.feedtable.unblockaction
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("contentid", String.valueOf(blockrowid));
			obj.put("tableflg", tableflg);
			obj.put("tablename", tblname);
			obj.put("ivrservicefor", "3");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI10005");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.cntent.unblockaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("user content unblocked"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("user content unblocked error"));
				alertList.add(alert);
				return "input";
			}
		} catch (Exception ex) {
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class activeActionfun() Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:ss") +" : "+ex);
		} finally {

		}
		return SUCCESS;
	}

	public String toactiviateUser() { // user active
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("usrid", String.valueOf(uniqueId));
			obj.put("tableflg", tableflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI10006");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.monitoring.activateuser");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("user Id Activated"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("user Id Activated error"));
				alertList.add(alert);
				return "input";
			}
		} catch (Exception ex) {
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class activeActionfun() Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:ss") +" : "+ex);
		} finally {

		}
		return SUCCESS;
	}
	
	
	public String deactiveUser() { // user active
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("usrid", String.valueOf(uniqueId));
			obj.put("tableflg", tableflg);
			obj.put("ivrservicefor", "6");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI10006");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.monitoring.deactivateuser");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("user Id Activated"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("user Id Activated error"));
				alertList.add(alert);
				return "input";
			}
		} catch (Exception ex) {
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class activeActionfun() Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:ss") +" : "+ex);
		} finally {

		}
		return SUCCESS;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
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



	public String getBlockrowid() {
		return blockrowid;
	}

	public void setBlockrowid(String blockrowid) {
		this.blockrowid = blockrowid;
	}

	public CategoryMasterTblVO getCategoryobj() {
		return committeeobj;
	}

	public void setCategoryobj(CategoryMasterTblVO committeeobj) {
		this.committeeobj = committeeobj;
	}

	public String getTableflg() {
		return tableflg;
	}

	public void setTableflg(String tableflg) {
		this.tableflg = tableflg;
	}

	public String getTblname() {
		return tblname;
	}

	public void setTblname(String tblname) {
		this.tblname = tblname;
	}
	

	

}

