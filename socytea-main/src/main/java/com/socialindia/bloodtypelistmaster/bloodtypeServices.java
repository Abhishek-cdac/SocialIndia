package com.socialindia.bloodtypelistmaster;


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


public class bloodtypeServices extends ActionSupport{
	private String uniqueId;
	private String statusflg;
	private String iVObloodtypename;
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

	public String bloodtypeCreateFun() throws Exception {
		System.out.println("bloodtypeCreateFun create  loading.......");
		Map sessionMap = ActionContext.getContext().getSession();
		JSONObject finaljj = null;
		JSONObject json = null;		
		String groupcode=null;String temp =null; JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
		String statusCode =null,msg=null;		
		try {
			if (iVObloodtypename != null && !iVObloodtypename.equalsIgnoreCase("null") && !iVObloodtypename.equalsIgnoreCase("") ) {
				obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("bloodtypename",iVObloodtypename);
				obj.put("status", "1");
				obj.put("ivrservicefor", "1");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				finaljj = new JSONObject();
				finaljj.put("servicecode", "SI36001");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				System.out.println("req::: "+jsonTextFinal);
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.bloodtypecreation.action");
				response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				msg= (String) json.get("message");
				statusCode = (String) json.get("statuscode");
				if (statusCode!=null && (statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00"))) {
					
					if (iVObloodtypename == null) {
						addFieldError("groupmang.groupName", getText("Error.usercreate.groupname"));
					} else if (msg.equalsIgnoreCase("Blood type already exist.")) {
						iVObloodtypename = "";
						alert.setCls("danger");
						alert.setMsg(getText("bloodtype.exits"));
						alertList.add(alert);
						sessionMap.put("alertList", alertList);
						return "input";
					} else {
						if (msg.equalsIgnoreCase("Blood type created.")) {
							iVObloodtypename = "";
							alert.setCls("success");
                            alert.setMsg(getText("Blood group type added successfully"));
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "success";
						} else {
							alert.setCls("danger");
							alert.setMsg(getText("Blood group type not added ."));
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "input";
						}

					}
					alert.setCls("success");
					alert.setMsg(getText("Blood group type Created."));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Blood group type not created."));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "error";
				}

			} else {

			}
		} catch (Exception e) {
			Commonutility.toWriteConsole("Step -1 : Exception Found "+getClass().getSimpleName() +".class worktypeCreateFun() : "+e);			
		} finally {
			finaljj=null;json =null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
			statusCode =null;msg=null;
		}
		return "success";
	}
	
	public String deleteActionfun() {
		System.out.println("------- committee Deactivation ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("bloodtypeid", String.valueOf(uniqueId));
			obj.put("statusflg", statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI36002");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.bloodtype.deleteaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("bloodtype.Delete.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("bloodtype.Delete.Error"));
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
			obj.put("bloodtypeid", String.valueOf(uniqueId));
			obj.put("statusflg", statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI36003");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.bloodtype.activeaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("bloodtype.Active.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("bloodtype.Active.Error"));
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

public String getiVObloodtypename() {
	return iVObloodtypename;
}
public void setiVObloodtypename(String iVObloodtypename) {
	this.iVObloodtypename = iVObloodtypename;
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

