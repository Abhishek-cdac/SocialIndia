package com.socialindia.common;

import java.net.URLEncoder;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.login.EncDecrypt;

public class Appdbquery extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Common commonObj = new CommonDao();
	private String lvrRspmsg;
	public String execute(){
		JSONObject dataJson=null;JSONObject finaljj=null;String response =null;
		String jsonTextFinal = null;String temp =null;String finalUrl=null;JSONObject json =null;
		try{
		dataJson = new JSONObject();
		dataJson.put("reststatus", "1");
		finaljj = new JSONObject();
		finaljj.put("servicecode", "SI24000");
		finaljj.put("servicefor", "3");// select
		finaljj.put("data", dataJson);
		jsonTextFinal = finaljj.toString();
		jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
		temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
		finalUrl = getText("url.appdbquery.wallet");
		response = commonObj.jsonRequest(finalUrl, temp);
		JSONObject json_data = new JSONObject(response);
		lvrRspmsg = (String) json_data.get("statuscode");
		System.out.println("lvrRspmsg -------- "+lvrRspmsg);
		
		}catch(Exception ex){
			System.out.println("Ex--------"+ex);
		}
		return SUCCESS;
	}
	public String getLvrRspmsg() {
		return lvrRspmsg;
	}
	public void setLvrRspmsg(String lvrRspmsg) {
		this.lvrRspmsg = lvrRspmsg;
	}

	

}
