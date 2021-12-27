package com.socialindia.SMSconfig;

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
import com.socialindia.login.EncDecrypt;

public class SMSconfigmgmt extends ActionSupport{
	 private String uniqueId;
	 private String httpurl;
	 private String httploginid;
	 private String httppass;
	 private String cdmaname;
	 private String senderName;
	 private String providerName;
	 private Integer fetchRow;
	 private Integer bulkFetch;
	 private String localert;
	 private AlertVo alert = new AlertVo();
	 private List<AlertVo> alertList = new ArrayList<AlertVo>();
	 private Common commonObj = new CommonDao();
	public String execute(){
		System.out.println("------- SMS Config View ------------------------");
		Map sessionMap = null;JSONObject finaljj=null;String jsonTextFinal=null;String finalUrl =null;String temp=null;String response=null;
		String statusCode=null;
		JSONObject json =null;JSONObject json_data =null;
		Map currentSession =null;
		 JSONObject obj = new JSONObject();
		currentSession = ActionContext.getContext().getSession();
		sessionMap = ActionContext.getContext().getSession();	
		String userid=String.valueOf(sessionMap.get("USERID"));		
		if(uniqueId==null ){					
			if((uniqueId==null)  && currentSession.get("currentsession_useruniqueId")!=null){							
				String userunque=(String)currentSession.get("currentsession_useruniqueId");
				uniqueId=userunque;
			}
		}else{
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_useruniqueId", String.valueOf(uniqueId));					
		}
		try{
			obj.put("crntusrloginid", userid);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI23001");	
			finaljj.put("servicefor", "3");	
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.smsmgmt.smsmgmtviewaction");
			response=commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			json_data = json.getJSONObject("data");
			httpurl=(String)json_data.getString("httpurl").trim();
			httploginid=(String)json_data.getString("usrName");
			httppass=(String)json_data.getString("password");
			cdmaname=(String)json_data.getString("cdmaname");
			senderName=(String)json_data.getString("sender");
			providerName=(String)json_data.getString("proname");
			fetchRow=(Integer)json_data.getInt("fetchrow");
			bulkFetch=(Integer)json_data.getInt("blkfetchrow");
			}catch(Exception ex){
				System.out.println("Exception ------------------- "+ex);	
			}finally{
				finaljj=null;jsonTextFinal=null;finalUrl =null;temp=null;response=null;
				json =null;json_data =null;
			}
		return SUCCESS;
	}
	public String updateSMS(){
		System.out.println(" --SMS UPDATE---");
		JSONObject finaljj=null;JSONObject obj = null;String jsonTextFinal=null;String temp=null;
		String finalUrl=null;String response=null;
		obj=new JSONObject();JSONObject json =null;String statusCode=null;
		try{
			obj.put("httpurl",httpurl.trim());
			obj.put("httploginid",httploginid);
			obj.put("httppass",httppass);
			obj.put("cdmaname",cdmaname);
			obj.put("senderName",senderName);
			obj.put("providerName",providerName);
			obj.put("fetchRow",fetchRow);
			obj.put("bulkFetch",bulkFetch);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI23002");	
			finaljj.put("servicefor", "2");	
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.smsmgmt.smsmgmtviewaction");
			response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("statusCode --"+response);
			json = new JSONObject(response);
			localert=(String)json.getString("statuscode");
			/*statusCode=(String)json.getString("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("SMS Configration Change successfully."));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Error ."));
				alertList.add(alert);
				return "input";
			}*/
		}catch(Exception ex){
			System.out.println("Exception update-------"+ex);
		}finally{
			finaljj=null;obj = null;jsonTextFinal=null;temp=null;
			finalUrl=null;response=null;
		}
	return SUCCESS;	
	
	}
	public String smsEnginStart(){
		System.out.println("Test --SMS Engine Start---");
		JSONObject finaljj=null;JSONObject obj = null;String jsonTextFinal=null;String temp=null;
		String finalUrl=null;String response=null;
		obj=new JSONObject();JSONObject json =null; Map sessionMap = null;
		sessionMap = ActionContext.getContext().getSession();	
		String userid=String.valueOf(sessionMap.get("USERID"));	
		try{			
			obj.put("crntusrloginid", userid);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI23003");	
			finaljj.put("servicefor", "4");	
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.smsmgmt.smsmgmtviewaction");
			response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			localert = (String)json.get("statuscode");
		}catch(Exception ex){
			System.out.println("Exception Found in SMSconfigmgmt smsEnginStart() : "+ex);
		}finally{
			finaljj=null;obj = null;jsonTextFinal=null;temp=null;
			finalUrl=null;response=null;
		}
		return SUCCESS;
	}
	public String smsEnginStop(){

		System.out.println(" --SMS Engine Stop---");
		JSONObject finaljj=null;JSONObject obj = null;String jsonTextFinal=null;String temp=null;
		String finalUrl=null;String response=null;
		obj=new JSONObject();JSONObject json =null;Map sessionMap = null;
		sessionMap = ActionContext.getContext().getSession();	
		String userid=String.valueOf(sessionMap.get("USERID"));	
		try{			
			obj.put("crntusrloginid", userid);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI23003");	
			finaljj.put("servicefor", "5");	
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.smsmgmt.smsmgmtviewaction");
			response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			localert = (String)json.get("statuscode");
		}catch(Exception ex){
			System.out.println("Exception Found in SMSconfigmgmt smsEnginStop() : "+ex);
		}finally{
			finaljj=null;obj = null;jsonTextFinal=null;temp=null;
			finalUrl=null;response=null;
			json =null;
		}
		return SUCCESS;
		
	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getHttpurl() {
		return httpurl;
	}
	public void setHttpurl(String httpurl) {
		this.httpurl = httpurl;
	}
	public String getHttploginid() {
		return httploginid;
	}
	public void setHttploginid(String httploginid) {
		this.httploginid = httploginid;
	}
	public String getHttppass() {
		return httppass;
	}
	public void setHttppass(String httppass) {
		this.httppass = httppass;
	}
	public String getCdmaname() {
		return cdmaname;
	}
	public void setCdmaname(String cdmaname) {
		this.cdmaname = cdmaname;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public Integer getFetchRow() {
		return fetchRow;
	}
	public void setFetchRow(Integer fetchRow) {
		this.fetchRow = fetchRow;
	}
	public Integer getBulkFetch() {
		return bulkFetch;
	}
	public void setBulkFetch(Integer bulkFetch) {
		this.bulkFetch = bulkFetch;
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
	public String getLocalert() {
		return localert;
	}
	public void setLocalert(String localert) {
		this.localert = localert;
	}
	
}
