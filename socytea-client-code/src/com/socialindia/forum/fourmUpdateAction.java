package com.socialindia.forum;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class fourmUpdateAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	private int topicsId;
	MvpFourmTopicsTblVO forumTopicData=new MvpFourmTopicsTblVO();
	
	public String execute() {
		JSONObject obj = null;
		JSONObject data = null;
		Log log = null;
		try {
			obj = new JSONObject();
			data = new JSONObject();
			log = new Log();
			Commonutility.toWriteConsole("Step 1 : Topic update called [Start]");
			log.logMessage("Step 1 : Topic update called [Start]", "info", fourmUpdateAction.class);
			data.put("servicecode", "SI0050");
			obj.put("topicsid", forumTopicData.getTopicsId());
			obj.put("topicsname", forumTopicData.getTopicsName());
			obj.put("keysearch", forumTopicData.getKeyForSearch());
			obj.put("validdate", forumTopicData.getFvalidDate());
			obj.put("description", forumTopicData.getTopicsDesc());	
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("forumMgmt.management.update.Action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;
			JSONObject locObjRecvJson = null;
			JSONObject locObjRecvdataJson = null;
			if (response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					
					locObjRecvJson = new JSONObject(response);
			    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");			    	 
			    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");			    	  
			    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");			    	  
			    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");					
			    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");    	    	  
					if (ivrstatuscode.equalsIgnoreCase("00") || ivrstatuscode.equalsIgnoreCase("0")) {
						Commonutility.toWriteConsole("Step 2 : Topic update response : success [End]");
						log.logMessage("Step 2 : Topic update response : success [End]", "info", fourmUpdateAction.class);
						alert.setCls("success");
						if(Commonutility.checkempty(ivrmsg)){
							alert.setMsg(ivrmsg);
						} else {
							alert.setMsg(getText("Topics updated successfully"));
						}
						alertList.add(alert);						
						return SUCCESS;
					} else {
						Commonutility.toWriteConsole("Step 2 : Topic update response : not update [End]");
						log.logMessage("Step 2 : Topic update response :  not update [End]", "info", fourmUpdateAction.class);
						alert.setCls("danger");
						if(Commonutility.checkempty(ivrmsg)){
							alert.setMsg(ivrmsg);
						} else {
							alert.setMsg("Topics updation error");
						}
						alertList.add(alert);
						return INPUT;
					}		
				} else {
					Commonutility.toWriteConsole("Step 2 : Topic update response : not update [End]");
					log.logMessage("Step 2 : Topic update response :  not update [End]", "info", fourmUpdateAction.class);
				}
			}
		} catch (Exception e) {
		
			Commonutility.toWriteConsole("Step -1 : Topic update exception : "+e);
			log.logMessage("Step -1 : Topic update exception : "+e, "error", fourmUpdateAction.class);
		 	alert.setCls("danger");			
			alert.setMsg("Topics updation error");			
			alertList.add(alert);
			return INPUT;
	} finally{
		obj = null;
		data = null;
		log = null;
	}
		
	return SUCCESS;
}
	public int getTopicsId() {
		return topicsId;
	}
	public void setTopicsId(int topicsId) {
		this.topicsId = topicsId;
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
	public MvpFourmTopicsTblVO getForumTopicData() {
		return forumTopicData;
	}
	public void setForumTopicData(MvpFourmTopicsTblVO forumTopicData) {
		this.forumTopicData = forumTopicData;
	}
}
