package com.socialindia.issuemgmtbl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.login.EncDecrypt;

public class Issueservices extends ActionSupport implements
SessionAware, ServletRequestAware, ServletResponseAware,
ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uniqueId;
	  private String statusflg;
	  private String issuelist;
	  private String editissueid;
	  private String updateflg;
	  private String createissuelist;
	  JSONObject obj = new JSONObject();
	  private AlertVo alert = new AlertVo();
	  private List<AlertVo> alertList = new ArrayList<AlertVo>();
	  private Common commonObj = new CommonDao(); 
	  
		public String execute() {		
			Map sessionMap = null;
			JSONObject finaljj=null;
			JSONObject json =null;
			String temp =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null;
			try {	
				sessionMap = ActionContext.getContext().getSession();
				System.out.println("issuelist create  loading......sfg."+createissuelist);
					obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
					obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
					obj.put("issuetxt",createissuelist);
					obj.put("status", "1");
					obj.put("ivrservicefor", "1");
					finaljj=new JSONObject();
					finaljj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
					finaljj.put("servicecode", "SI3500001");		
					finaljj.put("data", obj);
					jsonTextFinal = finaljj.toString().trim();
					jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
					temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
					finalUrl = getText("socialindia.issue.createissue");
					response = commonObj.jsonRequest(finalUrl, temp);
					json = new JSONObject(response);
					statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Issuelist.Create.Success"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Issuelist.Create.Error"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}
		} catch (Exception e) {
			System.out.println("Exception--------------- " + e);
			alert.setCls("danger");
			alert.setMsg(getText("Issuelist.Create.Error"));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally {
				finaljj=null;json =null;temp =null;finalUrl=null;jsonTextFinal =null;response =null;
				statusCode =null;
		}
		}
	 public String modifyListtxt(){
		 JSONObject obj = null;
			JSONObject data = null;
			Common commonObj = null;
			JSONObject json=null;
			Map sessionMap =null;
			try{
				obj = new JSONObject();
				data = new JSONObject();
				commonObj = new CommonDao();
				sessionMap = ActionContext.getContext().getSession();
				obj.put("issueid", editissueid);
				obj.put("issuetxt", issuelist);
				obj.put("flg", updateflg);
				data.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));						
				data.put("servicecode", "SI350004");
				data.put("servicefor", "4");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
				String finalUrl = getText("socialindia.issue.deactiveissue");
				String response = commonObj.jsonRequest(finalUrl, temp);
				System.out.println("Response : deactive ::::::::::::::::" + response);
				json = new JSONObject(response);
				String statusCode = (String) json.get("statuscode");
				String respon = (String) json.get("message");
				if (statusCode.equalsIgnoreCase("0")) {
					alert.setCls("success");
					alert.setMsg(respon);
					alertList.add(alert);
					//sessionMap.put("alertList", alertList);
					return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Error ."));
					alertList.add(alert);
					//sessionMap.put("alertList", alertList);
					return "input";
				}
				
			}catch(Exception ex){
				System.out.println("Exception ----deactive--"+ex);
				alert.setCls("danger");
				alert.setMsg(getText("Error ."));
				alertList.add(alert);				
				return "input";
			}		 
	 }


	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getStatusflg() {
		return statusflg;
	}

	public void setStatusflg(String statusflg) {
		this.statusflg = statusflg;
	}

	public String getIssuelist() {
		return issuelist;
	}

	public void setIssuelist(String issuelist) {
		this.issuelist = issuelist;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
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

	public Common getCommonObj() {
		return commonObj;
	}

	public void setCommonObj(Common commonObj) {
		this.commonObj = commonObj;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public String getEditissueid() {
		return editissueid;
	}

	public void setEditissueid(String editissueid) {
		this.editissueid = editissueid;
	}

	public String getUpdateflg() {
		return updateflg;
	}

	public void setUpdateflg(String updateflg) {
		this.updateflg = updateflg;
	}

	public String getCreateissuelist() {
		return createissuelist;
	}

	public void setCreateissuelist(String createissuelist) {
		this.createissuelist = createissuelist;
	}


	
	
}
