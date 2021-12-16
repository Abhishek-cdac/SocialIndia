package com.socialindia.emailconfig;

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


public class Emailconfigmgmt extends ActionSupport{
	 private String uniqueId;
	 private String proto;
	 private String nofet;
	 private String host;
	 private String port;
	 private String passwd;
	 private String email;
	 private String localert;
	 private AlertVo alert = new AlertVo();
	 private List<AlertVo> alertList = new ArrayList<AlertVo>();
	 
	private static final long serialVersionUID = 1L;
	private Common commonObj = new CommonDao();
	public String execute(){
		System.out.println("------- Email Config View ------------------------");
		Map sessionMap = null;JSONObject finaljj=null;String jsonTextFinal=null;String finalUrl =null;String temp=null;String response=null;
		JSONObject json =null;JSONObject json_data =null;
		 JSONObject obj = new JSONObject();
		 Map currentSession = ActionContext.getContext().getSession();
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
			finaljj.put("servicecode", "SI22001");	
			finaljj.put("servicefor", "3");	
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.emailmgmt.emailmgmtviewaction");
			response=commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			json_data = json.getJSONObject("data");
			 email=(String)json_data.getString("emailid");
			 passwd=(String)json_data.getString("password");
			 host=(String)json_data.getString("hostname");
			 port=(String)json_data.getString("portno");
			 proto=(String)json_data.getString("protocol");
			 nofet=(String)json_data.getString("nofetch");
			}catch(Exception ex){
				System.out.println("Exception ------------------- "+ex);	
			}finally{
				finaljj=null;jsonTextFinal=null;finalUrl =null;temp=null;response=null;
				json =null;json_data =null;
			}
		return SUCCESS;
	
	}public String emailUpdate(){
		System.out.println(" --emailUpdate---");
		JSONObject finaljj=null;JSONObject obj = null;String jsonTextFinal=null;String temp=null;
		String finalUrl=null;String response=null;
		 Map currentSession = ActionContext.getContext().getSession();
		 Map sessionMap = ActionContext.getContext().getSession();	
		obj=new JSONObject();JSONObject json =null;String statusCode=null;
		String userid=String.valueOf(sessionMap.get("USERID"));	
		if(uniqueId==null ){					
			if((uniqueId==null)  && currentSession.get("currentsession_useruniqueId")!=null){							
				String userunque=(String)currentSession.get("currentsession_useruniqueId");
				uniqueId=userunque;
			}
		}else{
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_useruniqueId", String.valueOf(userid));					
		}
		try{
			obj.put("email",email.trim());
			obj.put("passwd",passwd);
			obj.put("port",port);
			obj.put("host",host);
			obj.put("proto",proto);
			obj.put("nofet",nofet);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI22002");	
			finaljj.put("servicefor", "2");	
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.emailmgmt.emailmgmtviewaction");
			response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			localert = (String) json.get("statuscode");
			/*statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Email Configration Change successfully."));
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
		}
	return SUCCESS;	
	}public String emailEnginStart(){
		System.out.println(" --emailUpdate---");
		JSONObject finaljj=null;JSONObject obj = null;String jsonTextFinal=null;String temp=null;
		String finalUrl=null;String response=null;
		obj=new JSONObject();JSONObject json =null;Map sessionMap = null;
		sessionMap = ActionContext.getContext().getSession();	
		String userid=String.valueOf(sessionMap.get("USERID"));	
		try{			
			obj.put("crntusrloginid", userid);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI22003");	
			finaljj.put("servicefor", "4");	
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.emailmgmt.emailmgmtviewaction");
			response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			localert = (String) json.get("statuscode");
		}catch(Exception ex){
			System.out.println("Exception update-------"+ex);
		}finally{
			finaljj=null;obj = null;jsonTextFinal=null;temp=null;
			finalUrl=null;response=null;
		}
		return SUCCESS;
	}public String emailEnginStop(){
		System.out.println("--emailUpdate---");
		JSONObject finaljj=null;JSONObject obj = null;String jsonTextFinal=null;String temp=null;
		String finalUrl=null;String response=null;
		obj=new JSONObject();JSONObject json =null;Map sessionMap = null;
		sessionMap = ActionContext.getContext().getSession();	
		String userid=String.valueOf(sessionMap.get("USERID"));	
		try{			
			obj.put("crntusrloginid", userid);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI22004");	
			finaljj.put("servicefor", "5");	
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.emailmgmt.emailmgmtviewaction");
			response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			localert = (String) json.get("statuscode");
		}catch(Exception ex){
			System.out.println("Exception update-------"+ex);
		}finally{
			finaljj=null;obj = null;jsonTextFinal=null;temp=null;
			finalUrl=null;response=null;
		}
	return SUCCESS;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getProto() {
		return proto;
	}
	public void setProto(String proto) {
		this.proto = proto;
	}
	public String getNofet() {
		return nofet;
	}
	public void setNofet(String nofet) {
		this.nofet = nofet;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Common getCommonObj() {
		return commonObj;
	}
	public void setCommonObj(Common commonObj) {
		this.commonObj = commonObj;
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

