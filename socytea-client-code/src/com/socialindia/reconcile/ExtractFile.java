package com.socialindia.reconcile;

import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class ExtractFile extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reconRetmsg;
	
	public String execute(){
		Commonutility.toWriteConsole("------- ExtractFile ------------------------");
		Map sessionMap = null;
		JSONObject finaljj=null;JSONObject json = null;
		JSONObject obj =null;
		Common commonObj = null;
		String response=null;
		String temp=null;String finalUrl=null;
		try{
			finaljj=new JSONObject();
			obj=new JSONObject();
			commonObj = new CommonDao();
			sessionMap = ActionContext.getContext().getSession();
			obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.reconicile.returnval");
			response=commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			reconRetmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			
	} catch (Exception ex) {
		Commonutility.toWriteConsole("Step -1: Exception found in "+getClass().getSimpleName()+".calss  : "+ex);
	}
	
return SUCCESS;	
}


	public String getReconRetmsg() {
		return reconRetmsg;
	}


	public void setReconRetmsg(String reconRetmsg) {
		this.reconRetmsg = reconRetmsg;
	}
	
}
