package com.socialindia.utilitymgmt;

import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class CheckpdfGenerate extends ActionSupport{
	private static final long serialVersionUID = 1L;
	 private Common commonObj = new CommonDao(); 
	 private String checkcount;
	 public String execute() {
		Map currentSession = ActionContext.getContext().getSession();		
		JSONObject obj=new JSONObject();
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject lvrfinaljsonobj = null;
		try{		
			obj.put("crntusrloginid", String.valueOf(currentSession.get("USERID")));
			lvrfinaljsonobj = new JSONObject();
			lvrfinaljsonobj.put("data", obj);
			lvrfinaljsonobj.put("servicefor", "3");
			String jsonTextFinal = lvrfinaljsonobj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.pdfgeneratebill.action");
			System.out.println("jsonTextFinal : "+jsonTextFinal);
			String response = commonObj.jsonRequest(finalUrl, temp);	
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					JSONObject json_data = locObjRecvJson.getJSONObject("data");
					checkcount=json_data.getString("countFilter");
				}
			}
		} catch(Exception Ex) {
			System.out.println("Exception Found in String checkcount.class execute() : "+Ex);
		} finally {
			lvrfinaljsonobj = null; locObjRecvJson = null;
		}
		
		return SUCCESS;
	
	}
	public String getCheckcount() {
		return checkcount;
	}
	public void setCheckcount(String checkcount) {
		this.checkcount = checkcount;
	}
	

}
