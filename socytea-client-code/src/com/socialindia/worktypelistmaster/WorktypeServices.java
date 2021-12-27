package com.socialindia.worktypelistmaster;

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


public class WorktypeServices extends ActionSupport{
	private String uniqueId;
	private String statusflg;
	private String iVOworktypename;
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

	public String worktypeCreateFun() throws Exception {
			System.out.println("worktypeCreateFun create  loading.......");
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj=null;
			JSONObject json =null;
			//String invite=null;
			String groupcode=null;String temp =null; JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null,msg=null;
			//String[] invitearr=null;
			try {	
			if (iVOworktypename != null && !iVOworktypename.equalsIgnoreCase("null") && !iVOworktypename.equalsIgnoreCase("")) {
				obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("worktypename",iVOworktypename);
				obj.put("status", "1");
				obj.put("ivrservicefor", "1");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				finaljj=new JSONObject();
				finaljj.put("servicecode", "SI20001");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				System.out.println("req::: "+jsonTextFinal);
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.worktypecreation.action");
				response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				msg= (String) json.get("message");
				statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					
					if (iVOworktypename == null) {
						addFieldError("groupmang.groupName", getText("Error.usercreate.groupname"));
					} else if (msg.equalsIgnoreCase("Work Type already exist.")) {
						iVOworktypename = "";
						alert.setCls("danger");
						alert.setMsg(getText("worktype.exits"));
						alertList.add(alert);
						sessionMap.put("alertList", alertList);
						return "input";
					} else {
						if (msg.equalsIgnoreCase("Work Type Created.")) {
							iVOworktypename = "";
							alert.setCls("success");
							alert.setMsg(getText("worktype.Create.Success"));
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "success";
						} else {
							alert.setCls("danger");
							alert.setMsg(getText("worktype.Create.Error"));
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "input";
						}

					}
					alert.setCls("success");
					alert.setMsg(getText("worktype.Create.Success"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("worktype.Create.Error"));
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
			obj.put("worktypeid", String.valueOf(uniqueId));
			obj.put("statusflg", statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI20002");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.worktype.deleteaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("worktype.Delete.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("worktype.Delete.Error"));
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
			obj.put("worktypeid", String.valueOf(uniqueId));
			obj.put("statusflg", statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI20003");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.worktype.activeaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("worktype.Active.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("worktype.Active.Error"));
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

public String getiVOworktypename() {
	return iVOworktypename;
}
public void setiVOworktypename(String iVOworktypename) {
	this.iVOworktypename = iVOworktypename;
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

