package com.socialindia.forum;

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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class createForum extends  ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();	
	private Common commonObj = new CommonDao();
	private int topicsId;
	
	public String execute(){		
		return SUCCESS;
	}
	
	public String deleteForum(){
		Log log = null;
		JSONObject obj = null;
		JSONObject data = null;
		try{
			obj = new JSONObject();
			data = new JSONObject();
			log = new Log();
			log.logMessage("Step 1 : Delete Fourm [Start] ", "info", getClass().getSimpleName().getClass());
			Commonutility.toWriteConsole("Step 1 : Delete Fourm [Start]. Time :"+Commonutility.toGetCurrentDatetime("yyyy-MM-dd [hh:ss]"));
			Map sessionMap = ActionContext.getContext().getSession();
			data.put("servicecode", "SI0046");
			obj.put("topicsId", topicsId);		
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("forumMgmt.management.delete.Action");			
			String response = commonObj.jsonRequest(finalUrl, temp);	
			log.logMessage("Step 2 : Service Called.", "info", getClass().getSimpleName().getClass());
			Commonutility.toWriteConsole("Step 2 : Step 2 : Service Called. Time :"+Commonutility.toGetCurrentDatetime("yyyy-MM-dd [hh:ss]"));
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg=null;
			String ivrstatuscode=null;
			JSONObject locObjRecvJson = null;
			JSONObject locObjRecvdataJson = null;
			if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0) {
    		  boolean ivIsJson = Commonutility.toCheckIsJSON(response);
    		  if (ivIsJson) {
    			  locObjRecvJson = new JSONObject(response);
		    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");		    	  
		    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");		    	  
		    	  ivrmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");		    	  
		    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");				    		  
					if (ivrstatuscode.equalsIgnoreCase("00") || ivrstatuscode.equalsIgnoreCase("0")) {
						alert.setCls("success");						
						if(Commonutility.checkempty(ivrmsg)){
							alert.setMsg(ivrmsg);
						} else {
							alert.setMsg("Topics deleted successfully.");
						}
						alertList.add(alert);
						return "success";
					} else {
						alert.setCls("danger");
						if(Commonutility.checkempty(ivrmsg)){
							alert.setMsg(ivrmsg);
						} else {
							alert.setMsg("Topics deletion error.");
						}
						alertList.add(alert);
						return "input";
					}
    		  }
		 } else {
			    alert.setCls("danger");			
				alert.setMsg("Topics deletion error.");			
				alertList.add(alert);
				return "input";
		 }
		 	log.logMessage("Step 3 : Delete Fourm [End].", "info", getClass().getSimpleName().getClass());
			Commonutility.toWriteConsole("Step 2 : Delete Fourm [End]. Time :"+Commonutility.toGetCurrentDatetime("yyyy-MM-dd [hh:ss]"));						
		}catch (Exception e) {
			Commonutility.toWriteConsole("Step -1 : Exception Found in "+getClass().getSimpleName()+".class deleteForum(): "+e);
			log.logMessage("Step -1 : Exception : "+e, "info", getClass().getSimpleName().getClass());
			alert.setCls("danger");			
			alert.setMsg("Topics deletion error.");			
			alertList.add(alert);
			return "input";
		} finally {
			log = null; obj = null; data = null;
		}
		return SUCCESS;
	}
	
	public String forumCreateComments(){
				
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

	public int getTopicsId() {
		return topicsId;
	}

	public void setTopicsId(int topicsId) {
		this.topicsId = topicsId;
	}
	
}
