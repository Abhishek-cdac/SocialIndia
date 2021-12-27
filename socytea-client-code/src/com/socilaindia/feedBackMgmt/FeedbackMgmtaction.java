package com.socilaindia.feedBackMgmt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socilaindia.feedBackMgmt.persistance.FeedbackTblVO;

public class FeedbackMgmtaction extends ActionSupport {
	private String uniqueId;
	private String userid;
	private String feedbackid;
	private String feedbacktext;
	private String feedbackforusrtyp;
	private String feedbackrating;
	private String feedbackdeate;
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao(); 
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	FeedbackTblVO feedback=new FeedbackTblVO(null, null, null, null, null, null, null, null, null,null,null);
	private static final long serialVersionUID = 1L;

	public String viewfeedBackform() {
		String useridval=null;JSONObject finaljj=null;String jsonTextFinal=null;String finalUrl =null;String temp=null;String response=null,dbkby_fname=null;		
		String group=null;String usertoname=null;
		JSONObject json =null;JSONObject json_data =null;
		Map currentSession = ActionContext.getContext().getSession();
		if(uniqueId==null ) {					
			if ((uniqueId == null)  && currentSession.get("currentsession_useruniqueId")!=null){							
				String userunque=(String)currentSession.get("currentsession_useruniqueId");
				uniqueId = userunque;
			}
		} else {
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_useruniqueId", String.valueOf(uniqueId));					
		}
		try {
			useridval=String.valueOf(currentSession.get("USERID"));
			obj.put("feedbckid", uniqueId);
			obj.put("ivrservicefor", "3");
			obj.put("currentloginid", useridval);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI5003");		
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.viewfeedback.action");
			response=commonObj.jsonRequest(finalUrl, temp);	
			json = new JSONObject(response);
			json_data = json.getJSONObject("data");
			userid=(String)json_data.getString("feedbackforusrid");
			feedbackid=(String)json_data.getString("feedbackid");
			feedbackforusrtyp=(String)json_data.getString("feedbackforusrtyp");
			feedbacktext=(String)json_data.getString("feedbacktext");
			feedbackrating=(String)json_data.getString("feedbackrating");	
			feedbackdeate=(String)json_data.getString("feedbackdate");
			dbkby_fname=(String)json_data.getString("dbkby_fname");
			usertoname=(String)json_data.getString("feedbacttoname");
			group=(String)json_data.getString("dbkby_group");
			feedback.setFdbkby_userid(userid);
			feedback.setFeedbackid(feedbackid);			
			feedback.setFeedbackforusrtyp(feedbackforusrtyp);
			feedback.setFeedbacktext(feedbacktext);
			feedback.setFeedbackrating(feedbackrating);
			feedback.setDbkby_fname(dbkby_fname);
			feedback.setFeed_group(group);
			feedback.setFeedbacktoname(usertoname);
			//feedback.setFeedbackdate((String)json_data.getString(""));
			
		}catch(Exception ex){
			System.out.println("step -1: Exception found in FeedbackMgmtaction.class viewfeedBackform() : "+ex);
		}finally{
			useridval=null;finaljj=null;jsonTextFinal=null;finalUrl =null;temp=null;response=null;dbkby_fname=null;		
			group=null;usertoname=null;
			json =null;json_data =null;
		}
		return SUCCESS;
	}
	public String feedbackDelete(){
		JSONObject finaljj=null;String jsonTextFinal=null;String temp=null;String finalUrl=null;String response=null;
		JSONObject json=null;String statusCode=null, lvrRspmsg = null;
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("currentloginid",useridval);
			obj.put("ivrservicefor","4");
			obj.put("feedbckid", uniqueId);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI5004");		
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.deletefeedback.action");
			response=commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			statusCode = (String) json.get("statuscode");
			lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			if (statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)) {
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg("Feedback Deactive successfully.");
				}				
				alertList.add(alert);
			return "success";
			} else {
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)) {
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg("Feedback Deactive  Error.");
				}
				alertList.add(alert);
				return "input";
			}
		
		}catch(Exception ex){
			System.out.println("step -1: Exception found in FeedbackMgmtaction.class feedbackDelete() : "+ex);
			alert.setCls("danger");
			alert.setMsg("Feedback Deactive  Error.");			
			alertList.add(alert);
			return "input";
			
		} finally {
			finaljj=null;jsonTextFinal=null;temp=null;finalUrl=null;response=null;json=null;statusCode=null;
		}
		
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
	}
	public String getFeedbacktext() {
		return feedbacktext;
	}
	public void setFeedbacktext(String feedbacktext) {
		this.feedbacktext = feedbacktext;
	}
	public String getFeedbackforusrtyp() {
		return feedbackforusrtyp;
	}
	public void setFeedbackforusrtyp(String feedbackforusrtyp) {
		this.feedbackforusrtyp = feedbackforusrtyp;
	}
	public String getFeedbackrating() {
		return feedbackrating;
	}
	public void setFeedbackrating(String feedbackrating) {
		this.feedbackrating = feedbackrating;
	}
	public JSONObject getObj() {
		return obj;
	}
	public void setObj(JSONObject obj) {
		this.obj = obj;
	}
	public FeedbackTblVO getFeedback() {
		return feedback;
	}
	public void setFeedback(FeedbackTblVO feedback) {
		this.feedback = feedback;
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
	public String getFeedbackdeate() {
		return feedbackdeate;
	}
	public void setFeedbackdeate(String feedbackdeate) {
		this.feedbackdeate = feedbackdeate;
	}
	
	
}