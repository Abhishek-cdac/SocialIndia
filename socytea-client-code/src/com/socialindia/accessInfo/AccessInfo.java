package com.socialindia.accessInfo;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;


public class AccessInfo extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private Common commonObj = new CommonDao();
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	String response;
	
	public String accesscontrolip(String ip, String clientHost, int clientIP, String language, String protocol, String method, String countryName,String serverTime,String gmtTime,String entryTime){
		Log log = new Log();
		JSONObject  lvrRqstdataobj = new JSONObject();
		try{
			lvrRqstdataobj.put("ipaddress",ip);
			lvrRqstdataobj.put("clientHost",clientHost);
			lvrRqstdataobj.put("clientIP",clientIP);
			lvrRqstdataobj.put("language",language);
			lvrRqstdataobj.put("protocol",protocol);
			lvrRqstdataobj.put("method",method);
			lvrRqstdataobj.put("countryName",countryName);
			lvrRqstdataobj.put("gmtTime",gmtTime);
			lvrRqstdataobj.put("entryTime",entryTime);
		
			data.put("servicecode", "SI10000");
			data.put("servicefor", "1");
			data.put("data", lvrRqstdataobj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);	
			String finalUrl =getText("login.user.access.action"); 
			response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);		
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			Map  sessobj = ActionContext.getContext().getSession();
			if (sessobj != null) {
				sessobj.put("IPACTION_STATUSFLG", statusCode);
			} else {
			}
		
		}catch(Exception ex){
			log.logMessage("Exception :socialindia-- accessinfo: "+ex, "error", AccessInfo.class);
		}
		return response;		
	}
	public static String togetGMTDateTime(){
		Log log = new Log();
		String rtnDtm=null;
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 format.setTimeZone(TimeZone.getTimeZone("UTC"));
			 Date date = new Date();
			 rtnDtm=format.format(date);
//			 String gmtTime=format.format(date);
		}catch(Exception e){
			log.logMessage("Exception foudn in Utilitymthds.togetGMTDateTime() :", "error", AccessInfo.class);
		}finally{
			
		}
		return rtnDtm;
}
	public static String togetServerDateTime(){
		Log log = new Log();
		String rtnDtm=null;
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			 Date date = new Date();
			 rtnDtm=format.format(date);
		}catch(Exception e){
			log.logMessage("Exception foudn in Utilitymthds.togetServerDateTime() accessinfo ---", "error", AccessInfo.class);
		}finally{
			
		}
		return rtnDtm;
	}
	public static String togetEntryDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = new Date();
//		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String rtnDtm1=dateFormat.format(date1);
		System.out.println("rtnDtm1 ------------"+rtnDtm1);
		return rtnDtm1;
		
	}
	public String accessUniqueId(String ip,String userid){
		Log log = new Log();
		try{
			obj.put("ipaddress",ip);
			obj.put("uniqueId",userid);
			data.put("servicecode", "SI10001");
			data.put("servicefor", "2");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			System.out.println("res:::  " + jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);	
			String finalUrl =getText("login.user.access.action"); 
			String response = commonObj.jsonRequest(finalUrl, temp);
			}catch(Exception ex){
				log.logMessage("Exception :accessUniqueId-- access Unique ---", "error", AccessInfo.class);
			}
		
		return ip;
	}
	
}
