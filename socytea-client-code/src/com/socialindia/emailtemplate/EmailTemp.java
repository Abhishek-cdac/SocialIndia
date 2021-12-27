package com.socialindia.emailtemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class EmailTemp extends ActionSupport{
	/**
	 * 
	 */
	private String templateid;
	private String emailsub;
	private String emailcontent;
	private String rightmenu;

	private Common commonObj = new CommonDao();	
	 public List<Emailtemplatevo> emailtemp = new ArrayList<Emailtemplatevo>();
	 private AlertVo alert = new AlertVo();
		private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private static final long serialVersionUID = 1L;
	public String emailTemplatesub(){
		JSONObject finaljj=null;
		JSONObject json =null;
		JSONObject locObjRecvJson = null;//Receive String to json	
		   JSONObject locObjRecvdataJson = null;// Receive Data Json		
		   try{	
			json = new JSONObject();
			finaljj = new JSONObject();
			json.put("ivrservicefor","3");
			finaljj.put("servicecode", "SI300001");		
			finaljj.put("data", json);
			String jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.emailfetch.action.value");
			String response = commonObj.jsonRequest(finalUrl, temp);
			 if (response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
	    		  boolean ivIsJson = Commonutility.toCheckIsJSON(response);
	    		  if (ivIsJson) {
	    			  locObjRecvJson = new JSONObject(response);
			    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");			    		 
			    	  JSONArray jary=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");									
			    	  for (int i = 0; i < jary.length(); i++) {
			    		  JSONArray temm=null;			
	    				try {
	    					temm = jary.getJSONArray(i);			    					
	    					int indx = (Integer)temm.get(0);			    					
	    					String vl = (String)temm.get(1);			    					
	    					emailtemp.add(new Emailtemplatevo(indx,vl,null));
	    				}			    				
	    				catch (Exception e) {			    					
	    				}finally{}			    						
	    			}		
	    	  }
	    	}
		}catch(Exception ex){
			System.out.println("Exception ------- "+ex);
		}finally{
			finaljj=null;json =null;locObjRecvJson = null;
			locObjRecvdataJson = null;
		}
		return SUCCESS;
	}
	public String templatesubjectadcontent(){
		JSONObject finaljj=null;
		JSONObject json =null;
		JSONObject lvrRcvresponsejsonobj = null;
		JSONObject lvrRcvdaatajsonobj = null;
		String response=null;String finalUrl=null;String temp=null;String jsonTextFinal=null;
		try{	
			json = new JSONObject();
			finaljj = new JSONObject();
			json.put("templateid",templateid);
			finaljj.put("servicecode", "SI300001");	
			finaljj.put("ivrservicefor","3");
			finaljj.put("data", json);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.emailfetch.subandcontent");
			response = commonObj.jsonRequest(finalUrl, temp);
			lvrRcvresponsejsonobj = new JSONObject(response);	
			Commonutility.toWriteConsole("response : "+response);
	        lvrRcvdaatajsonobj = lvrRcvresponsejsonobj.getJSONObject("data");
	        emailsub =  lvrRcvdaatajsonobj.getString("emailsub");
	       emailcontent = lvrRcvdaatajsonobj.getString("emailcontent");
	       rightmenu = lvrRcvdaatajsonobj.getString("emailcolum");
		}catch(Exception ex){
			System.out.println("Exception ------- "+ex);
		}finally{
			finaljj=null;json =null;lvrRcvresponsejsonobj = null;
			lvrRcvdaatajsonobj = null;
			response=null;finalUrl=null;temp=null;jsonTextFinal=null;	
		}
		return SUCCESS;
	}
	public String tempUpdateAction(){
		JSONObject finaljj=null;
		JSONObject json =null;
		JSONObject jsonresponse = null;
		String jsonTextFinal=null;String temp=null;String finalUrl=null;String response=null;String statusCode=null;String respCode=null;
		try{	
			json = new JSONObject();
			finaljj = new JSONObject();
			json.put("templateid",templateid);
			json.put("subject",emailsub);
			json.put("emailcontent",emailcontent);
			finaljj.put("servicecode", "SI300002");	
			finaljj.put("ivrservicefor","2");
			finaljj.put("data", json);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);		
			finalUrl =getText("socialindia.emailfetch.subandcontentUpdate");
			response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response -------response-- "+response);
			jsonresponse = new JSONObject(response);
			statusCode = (String)  Commonutility.toHasChkJsonRtnValObj(jsonresponse, "statuscode");
			respCode = (String)  Commonutility.toHasChkJsonRtnValObj(jsonresponse, "respcode");
			if (statusCode.equalsIgnoreCase("0")&& respCode.equalsIgnoreCase("0000")) {
				alert.setCls("success");
				alert.setMsg(getText("update.emailtemplate.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("update.emailtemplate.error"));
				alertList.add(alert);
				return "input";
			}
			
		}catch(Exception ex){
			System.out.println("Exception ------- "+ex);
		}finally{
			finaljj=null;json =null;jsonresponse = null;
			jsonTextFinal=null;temp=null;finalUrl=null;response=null;
		}
		return SUCCESS;
		
	}
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	public String getEmailsub() {
		return emailsub;
	}
	public void setEmailsub(String emailsub) {
		this.emailsub = emailsub;
	}
	public String getEmailcontent() {
		return emailcontent;
	}
	public void setEmailcontent(String emailcontent) {
		this.emailcontent = emailcontent;
	}
	public String getRightmenu() {
		return rightmenu;
	}
	public void setRightmenu(String rightmenu) {
		this.rightmenu = rightmenu;
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
