package com.socialindia.titlelistmaster;


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
import com.socialindia.generalmgnt.persistance.CategoryMasterTblVO;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;


public class titleServices extends ActionSupport{
	private String uniqueId;
	private String statusflg;
	private String iVOtitlename;
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

	public String titleCreateFun() throws Exception {
			System.out.println("titleCreateFun create  loading.......");
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj=null;
			JSONObject json =null;
			String invite=null;
			String groupcode=null;String temp =null; JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null,msg=null;
			String[] invitearr=null;
			try {	
			if (iVOtitlename != null && !iVOtitlename.equalsIgnoreCase("null") && !iVOtitlename.equalsIgnoreCase("")) {
				obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("titlename",iVOtitlename);
				obj.put("status", "1");
				obj.put("ivrservicefor", "1");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				finaljj=new JSONObject();
				finaljj.put("servicecode", "SI37001");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				System.out.println("req::: "+jsonTextFinal);
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.titlecreation.action");
				response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				msg= (String) json.get("message");
				statusCode = (String) json.get("statuscode");
				if (statusCode!=null && statusCode.equalsIgnoreCase("0")) {
					
					if (iVOtitlename == null) {
						addFieldError("groupmang.groupName", getText("Error.usercreate.groupname"));
					} else if (msg.equalsIgnoreCase("Title already exist.")) {
						iVOtitlename = "";
						alert.setCls("danger");
						alert.setMsg(getText("title.exits"));
						alertList.add(alert);
						sessionMap.put("alertList", alertList);
						return "input";
					} else {
						if (msg.equalsIgnoreCase("Title Created.")) {
							iVOtitlename = "";
							alert.setCls("success");
							alert.setMsg(getText("Title added successfully"));
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "success";
						} else {
							alert.setCls("danger");
							alert.setMsg(getText("Title not created ."));
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "input";
						}

					}
					alert.setCls("success");
					alert.setMsg(getText("Title Created."));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Title not created."));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "error";
				}

			} else {

			}
		} catch (Exception e) {
			Commonutility.toWriteConsole("Step -1 : Exception Found "+getClass().getSimpleName() +".class worktypeCreateFun() : "+e);			
		} finally {
			finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
			statusCode =null;invitearr=null;msg=null;
		}
		return "success";
	}
	
	public String deleteActionfun() {
		System.out.println("------- committee Deactivation ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("titleid", String.valueOf(uniqueId));
			obj.put("statusflg", statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI37002");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.title.deleteaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("title.Delete.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("title.Delete.Error"));
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {			
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class deleteActionfun() Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:ss") +" : "+ex);
		} finally {

		}
		return SUCCESS;
	}

	public String activeActionfun() {
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("titleid", String.valueOf(uniqueId));
			obj.put("statusflg", statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI37003");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.title.activeaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("title.Active.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("title.Active.Error"));
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

public String getiVOtitlename() {
	return iVOtitlename;
}
public void setiVOtitlename(String iVOtitlename) {
	this.iVOtitlename = iVOtitlename;
}
public CategoryMasterTblVO getCategoryobj() {
	return committeeobj;
}
public void setCategoryobj(CategoryMasterTblVO committeeobj) {
	this.committeeobj = committeeobj;
}
public String getStatusflg() {
	return statusflg;
}
public void setStatusflg(String statusflg) {
	this.statusflg = statusflg;
}












}

