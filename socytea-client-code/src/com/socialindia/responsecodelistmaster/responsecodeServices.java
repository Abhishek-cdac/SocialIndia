package com.socialindia.responsecodelistmaster;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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


public class responsecodeServices extends ActionSupport{
	private String uniqueId;
	private String statusflg;
	private String iVOresponsename;
	private String responsehiddenid;
	private String Responsemessage;
	private String statustype;
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

	public String responseCreateFun() throws Exception {
			System.out.println("responseCreateFun create  loading.......");
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj=null;
			JSONObject json =null;
			String invite=null;
			String groupcode=null;String temp =null; JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null,msg=null;
			String[] invitearr=null;
			try {	
			if (iVOresponsename != null && !iVOresponsename.equalsIgnoreCase("null") && !iVOresponsename.equalsIgnoreCase("")) {
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("responsemsg",iVOresponsename);
				obj.put("statustype", statustype);
				obj.put("ivrservicefor", "1");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				finaljj=new JSONObject();
				finaljj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				finaljj.put("servicecode", "SI37001");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				System.out.println("req::: "+jsonTextFinal);
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.config.createresponse");
				response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				msg= (String) json.get("message");
				statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
				 if (msg.equalsIgnoreCase("Response Message already exist.")) {
						iVOresponsename = "";
						alert.setCls("danger");
						alert.setMsg(getText("Response message already exists"));
						alertList.add(alert);
						return "input";
					} else {
						if (msg.equalsIgnoreCase("Response Message Created.")) {
							iVOresponsename = "";
							alert.setCls("success");
							alert.setMsg(getText("Response message add successfully"));
							alertList.add(alert);
							 sessionMap.put("alertList", alertList);
							return "success";
						} else {
							alert.setCls("danger");
							alert.setMsg(getText("Response message not created ."));
							alertList.add(alert);
							 sessionMap.put("alertList", alertList);
							return "input";
						}

					}
					//alert.setCls("success");
					//alert.setMsg(getText("Response Message Created."));
					//alertList.add(alert);
					//return "success";
				} else {
					System.out.println("vbbb=== "+statusCode);
					alert.setCls("danger");
					alert.setMsg(getText("Response Message not created."));
					alertList.add(alert);
					 sessionMap.put("alertList", alertList);
					return "error";
				}

			} else {
				alert.setCls("danger");
				alert.setMsg("Response Message not created.");
				alertList.add(alert);
				 sessionMap.put("alertList", alertList);
				return "input";
			}
		} catch (Exception e) {
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class worktypeCreateFun() : "+e);
			alert.setCls("danger");
			alert.setMsg("Response Message not created.");
			alertList.add(alert);
			 sessionMap.put("alertList", alertList);
			return "input";
		} finally {
			finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
			statusCode =null;invitearr=null;msg=null;
		}
		//return /"success";
	}
	
	public String deleteActionfun() {
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("responseid", String.valueOf(uniqueId));
			obj.put("statusflg", statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("crntusrloginid", useridval);
			finaljj.put("servicecode", "SI37003");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.config.responsecodedeleteaction");
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
			obj.put("responseid", String.valueOf(uniqueId));
			obj.put("statusflg", statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("crntusrloginid", useridval);
			finaljj.put("servicecode", "SI37005");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.config.responsecodeactiveaction");
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
	
	 public String editResponseMsgfun() {
		    boolean result = false;
		    try {

		      Date date;
		    /*  userDetail = (UserMasterTblVo) ActionContext.getContext().getSession()
		          .get("USERINFO");*/
		      if (Responsemessage == null) {
		        addFieldError("Responsemessage",
		            getText("Response message empty"));
		      } else {
		        if (Responsemessage != null
		            && Responsemessage.length() > 0) {
		        	
		        	obj.put("editresponseId", responsehiddenid);
		        	obj.put("Responsemsg", Responsemessage);
		        	JSONObject finaljj = new JSONObject();
					finaljj.put("servicecode", "SI37002");
					finaljj.put("data", obj);
			        String jsonTextFinal = finaljj.toString();
			    	jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
					String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
					String finalUrl = getText("socialindia.config.responsemsgedit");
					String response=commonObj.jsonRequest(finalUrl, temp);
					JSONObject json = new JSONObject(response);
					JSONObject json_data=json.getJSONObject("data");
					String statusCode = (String) json.get("statuscode");
					System.out.println("statusCode======== "+statusCode);
					if (statusCode.equalsIgnoreCase("0")) {
						alert.setCls("success");
						alert.setMsg(getText("Response message updated successfully"));
						alertList.add(alert);
						return "success";

					} else {
						alert.setCls("danger");
						alert.setMsg(getText("Error while updating in Response Message."));
						alertList.add(alert);
						return "input";
					}
							
		        }
		      }
		    } catch (Exception ex) {
		      alert.setCls("danger");
		      alert.setMsg(getText("Error while updating in group."));
		      alertList.add(alert);
		      return "error";
		    }
		    return "success";
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


public String getiVOresponsename() {
	return iVOresponsename;
}

public void setiVOresponsename(String iVOresponsename) {
	this.iVOresponsename = iVOresponsename;
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


public String getResponsehiddenid() {
	return responsehiddenid;
}

public void setResponsehiddenid(String responsehiddenid) {
	this.responsehiddenid = responsehiddenid;
}

public String getResponsemessage() {
	return Responsemessage;
}

public void setResponsemessage(String responsemessage) {
	Responsemessage = responsemessage;
}

public String getStatustype() {
	return statustype;
}

public void setStatustype(String statustype) {
	this.statustype = statustype;
}












}

