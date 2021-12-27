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

public class fourmCreateAction extends  ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AlertVo alert =  new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	private String township_List;
	private String forumDate;
	MvpFourmTopicsTblVO forumTopicData = new MvpFourmTopicsTblVO();

	public String execute() {
		Log log = null;
		JSONObject obj = null;
		JSONObject data = null;
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			log = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			
			if(forumTopicData!=null){
				Commonutility.toWriteConsole("Step 1 : Fourm creation called [Start]");
				log.logMessage("Step 1 : Fourm creation called [Start]", "info", fourmCreateAction.class);
							
				data.put("servicecode", "SI0045");
				obj.put("useruniqueId", sessionMap.get("USERID"));
				obj.put("groupcode", sessionMap.get("GROUPCODE"));
				obj.put("topicname", forumTopicData.getTopicsName());
				obj.put("keysearch", forumTopicData.getKeyForSearch());
				obj.put("forumdate", forumDate);
				obj.put("topicdesc", forumTopicData.getTopicsDesc());
				data.put("data", obj);
				String jsonTextFinal = data.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
				String finalUrl = getText("forumMgmt.management.create.Action");
				Commonutility.toWriteConsole("Step 2 : Fourm creation service url : "+finalUrl);
				log.logMessage("Step 2 : Fourm creation service url : "+finalUrl, "info", fourmCreateAction.class);
				String response = commonObj.jsonRequest(finalUrl, temp);
				Commonutility.toWriteConsole("Step 3 : Fourm creation service response : "+response);
				log.logMessage("Step 3 : Fourm creation service response : "+response, "info", fourmCreateAction.class);
				String ivrservicecode = null;
				String ivrresponsecode = null;
				String ivrmsg = null;
				String ivrstatuscode = null;
				JSONObject locObjRecvJson = null;
				forumTopicData = null;
				if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
					boolean ivIsJson = Commonutility.toCheckIsJSON(response);
					if (ivIsJson) {
						Commonutility.toWriteConsole("Step 4 : Fourm creation - Success.");
						log.logMessage("Step 4 : Fourm creation - Success.", "info", fourmCreateAction.class);
	    			  	locObjRecvJson = new JSONObject(response);
			    	  	ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");		    	 
			    	  	ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");		    	  
			    	  	ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");		    	  
						ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");				    		  
						if (ivrstatuscode.equalsIgnoreCase("00") || ivrstatuscode.equalsIgnoreCase("0")) {
							Commonutility.toWriteConsole("Step 5 : Fourm creation - Success. [End]");
							log.logMessage("Step 5 : Fourm creation - Success. [End]", "info", fourmCreateAction.class);
			    		  alert.setCls("success");
			    		  if(Commonutility.checkempty(ivrmsg)){
			    			  alert.setMsg(ivrmsg);					
			    		  } else {
			    			  alert.setMsg("Topics created successfully.");	
			    		  }
			    		  alertList.add(alert);
			    		  sessionMap.put("alertList", alertList);
			    		  return "success";
						} else {
							Commonutility.toWriteConsole("Step 5 : Fourm creation - Error. [End]");
							log.logMessage("Step 5 : Fourm creation - Error. [End]", "info", fourmCreateAction.class);
							alert.setCls("danger");
							if(Commonutility.checkempty(ivrmsg)){
								alert.setMsg(ivrmsg);					
							} else {
								alert.setMsg(getText("Topics creation error."));
							}
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "input";
						}
					}
				} else {
					Commonutility.toWriteConsole("Step 4 : Fourm creation - Error not created.");
					log.logMessage("Step 4 : Fourm creation - Error not created.", "info", fourmCreateAction.class);
					alert.setCls("danger");
					alert.setMsg(getText("Topics creation error."));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "input";
				}
			} else{
				
			}
			
		}catch (Exception e) {
			Commonutility.toWriteConsole("Step -1 : Fourm creation - Error not created. [Exception] : "+e);
			log.logMessage("Step -1 : Fourm creation - Error not created. [Exception] : "+e, "error", fourmCreateAction.class);
			alert.setCls("danger");
			alert.setMsg(getText("Topics creation error."));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally  {
			log = null;
			obj = null;
			data = null; System.gc();
		}
		return SUCCESS;
	}


	public String getTownship_List() {
		return township_List;
	}

	public void setTownship_List(String township_List) {
		this.township_List = township_List;
	}

	public MvpFourmTopicsTblVO getForumTopicData() {
		return forumTopicData;
	}

	public void setForumTopicData(MvpFourmTopicsTblVO forumTopicData) {
		this.forumTopicData = forumTopicData;
	}

	public String getForumDate() {
		return forumDate;
	}

	public void setForumDate(String forumDate) {
		this.forumDate = forumDate;
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
