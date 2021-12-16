package com.socialindia.notificationmaster;


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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;


public class NotificationServices extends ActionSupport{
	  private String uniqueId;
	  JSONObject obj = new JSONObject();
	  private AlertVo alert = new AlertVo();
	  private List<AlertVo> alertList = new ArrayList<AlertVo>();
	  List<NotificationTblVO> NotificationList = new ArrayList<NotificationTblVO>();
	  Integer lvrNotificnt = 0; 
	  private Common commonObj = new CommonDao(); 
	  commomServices commonSnippet=new commomServices();
	  NotificationTblVO Notifyobj=new NotificationTblVO();
		private Integer notificationid;
		private String notificationdesc;
		private Integer notificationstatus;
		private String ntfytblrefname;
		private String ntfytblrefid;
		private String ntfydatetime;
		private String notificationIdval;
		private String viewactionurl;
	private static final long serialVersionUID = 1L;

	public String execute() {		
		return SUCCESS;
	}
	public String notificationCreateFun() throws Exception{
			
		return "success";
	}
	public String notificationForm() {		
		return SUCCESS;
	}

	public String getnotification() {
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject locObjRecvdataJson = null;// Receive Data Json
		JSONObject locObjRspdataJson = null;// Response Data Json
		JSONArray jsonarr = new JSONArray();
		JSONArray array = new JSONArray();
		JSONObject finaljj = new JSONObject();
		JSONObject dataJson = new JSONObject();
		String temp = null;
		String jsonTextFinal = null;
		String finalUrl = null;
		String response = null;

		try {
			Map sessionMap = ActionContext.getContext().getSession();
			String sdtSearch="";
			dataJson.put("fdbkstatus", "1");
			dataJson.put("countflg", "yes");
			dataJson.put("countfilterflg", "yes");
			dataJson.put("startrow", "0");// starting row
			dataJson.put("totalrow", "10");// total row
			dataJson.put("srchdtsrch", sdtSearch);
			dataJson.put("searchflg", "");
			dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));// Current login id
			dataJson.put("fdbkforsocid", String.valueOf(sessionMap.get("sSoctyId")));// Current society id
			finaljj.put("servicecode", "SI25003");		
			finaljj.put("data", dataJson);
			jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.notification.notificationalert");
			response = commonObj.jsonRequest(finalUrl, temp);
			int notifid=0;
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					JSONObject json_data = locObjRecvJson.getJSONObject("data");
					jsonarr =(JSONArray) Commonutility.toHasChkJsonRtnValObj(json_data,"notifyalertdetails");
					
					lvrNotificnt = (Integer) Commonutility.toHasChkJsonRtnValObj(json_data,"countAfterFilter");
					if (jsonarr.length() > 0) {						
						for(int j = 0; j < jsonarr.length(); j++){
							notificationid = jsonarr.getJSONObject(j).getInt("notification_id");
							notificationdesc =jsonarr.getJSONObject(j).getString("notification_name");
							notificationstatus =jsonarr.getJSONObject(j).getInt("notification_status");
							ntfytblrefname =jsonarr.getJSONObject(j).getString("notification_tblrefname");
							ntfytblrefid =jsonarr.getJSONObject(j).getString("notification_tblrefid");
							ntfydatetime =jsonarr.getJSONObject(j).getString("notification_time");
							if (ntfytblrefname.equalsIgnoreCase("Events")) {
								viewactionurl = "viewEventAction?uniqueId="+ ntfytblrefid + "";
							} else if (ntfytblrefname.equalsIgnoreCase("Complaints")) {
								viewactionurl = "viewcomplaintform?deletecomplaintid="+ ntfytblrefid + "";
							} else if (ntfytblrefname.equalsIgnoreCase("Feedbacks")) {
								viewactionurl = "viewfeedBack?uniqueId="+ ntfytblrefid + "";
							} else if (ntfytblrefname.equalsIgnoreCase("Feedback")) {
								viewactionurl = "viewfeedBack?uniqueId="+ ntfytblrefid + "";
							} else if (ntfytblrefname.equalsIgnoreCase("Forum")) {
								viewactionurl = "forumPostComments?topicsid="+ ntfytblrefid + "";
							} else if (ntfytblrefname.equalsIgnoreCase("Tender")) {
								viewactionurl = "viewtenderform?uniqueId=" + ntfytblrefid + "";
							} else if (ntfytblrefname.equalsIgnoreCase("Expenses")) {
								viewactionurl = "viewexpenceform?uniqueId="+ ntfytblrefid + "";
							} else if (ntfytblrefname.equalsIgnoreCase("Documents")  || ntfytblrefname.equalsIgnoreCase("Document")) {
								viewactionurl = "utititymgmt?calfr=nflg&dcied="+ ntfytblrefid + "&susried="+String.valueOf(sessionMap.get("USERID"));
							} else if (ntfytblrefname.equalsIgnoreCase("Facility")) {
								viewactionurl = "bookingmstview?iVObookingid="+ ntfytblrefid+"";
							} else {
								
							}
							NotificationList.add(new NotificationTblVO(String.valueOf(notificationid),notificationdesc, String.valueOf(notificationstatus),ntfytblrefname,ntfytblrefid,ntfydatetime,viewactionurl));
						}
						
					} else {

					}
				}
			}
		} catch (Exception ex) {
			alert.setCls("error");
			alert.setMsg("Error in Notification");
			alertList.add(alert);
			return "error";
		} finally {
			finaljj=null;dataJson =null;temp =null;finalUrl=null;jsonTextFinal =null;response =null;
			
		}
	   return SUCCESS;
	}
	
	public String notificationReadForm(){		
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			obj.put("notificationid", notificationIdval);
			obj.put("ivrservicefor", "4");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI25004");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.notification.notificationRead");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				getnotification();
				alert.setCls("success");
				alert.setMsg("Notification Read Successfully");
				alertList.add(alert);
			return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg("Notification Not Read ");
				alertList.add(alert);
				return "input";
			}
		
		}catch(Exception ex){
			System.out.println("Exception ----- " + ex);
		} finally {

		}
		return SUCCESS;
	}
	
	public String notificationDeleteForm(){
		System.out.println("------- notificationDeleteForm  ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			obj.put("notificationid", String.valueOf(uniqueId));
			obj.put("ivrservicefor", "4");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI25005");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.notification.notificationDeactive");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("notification.Delete.Success"));
				alertList.add(alert);
			return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("notification.Delete.Error"));
				alertList.add(alert);
				return "input";
			}
		
		}catch(Exception ex){
		System.out.println("Exception ----- "+ex);
	}
		finally{
			
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


public Integer getLvrNotificnt() {
	return lvrNotificnt;
}
public void setLvrNotificnt(Integer lvrNotificnt) {
	this.lvrNotificnt = lvrNotificnt;
}
public List<NotificationTblVO> getNotificationList() {
	return NotificationList;
}
public void setNotificationList(List<NotificationTblVO> notificationList) {
	NotificationList = notificationList;
}

public String getNotificationdesc() {
	return notificationdesc;
}
public void setNotificationdesc(String notificationdesc) {
	this.notificationdesc = notificationdesc;
}
public Integer getNotificationid() {
	return notificationid;
}
public void setNotificationid(Integer notificationid) {
	this.notificationid = notificationid;
}
public Integer getNotificationstatus() {
	return notificationstatus;
}
public void setNotificationstatus(Integer notificationstatus) {
	this.notificationstatus = notificationstatus;
}
public String getNtfytblrefname() {
	return ntfytblrefname;
}
public void setNtfytblrefname(String ntfytblrefname) {
	this.ntfytblrefname = ntfytblrefname;
}
public String getNtfytblrefid() {
	return ntfytblrefid;
}
public void setNtfytblrefid(String ntfytblrefid) {
	this.ntfytblrefid = ntfytblrefid;
}
public String getNtfydatetime() {
	return ntfydatetime;
}
public void setNtfydatetime(String ntfydatetime) {
	this.ntfydatetime = ntfydatetime;
}
public NotificationTblVO getNotifyobj() {
	return Notifyobj;
}
public void setNotifyobj(NotificationTblVO notifyobj) {
	Notifyobj = notifyobj;
}
public String getNotificationIdval() {
	return notificationIdval;
}
public void setNotificationIdval(String notificationIdval) {
	this.notificationIdval = notificationIdval;
}
public String getViewactionurl() {
	return viewactionurl;
}
public void setViewactionurl(String viewactionurl) {
	this.viewactionurl = viewactionurl;
}





}

