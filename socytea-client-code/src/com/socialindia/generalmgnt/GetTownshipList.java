package com.socialindia.generalmgnt;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class GetTownshipList extends ActionSupport{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	  private String locIdTownship =null;
	  private Common commonObj = new CommonDao();
	  
	public String execute(){
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject locObjRecvdataJson = null;// Receive Data Json
		JSONObject locObjRspdataJson = null;// Response Data Json
		JSONArray jsrcategory = null;
		JSONObject jbcategory = null;
		try {
			System.out.println("TownshipList loading....");
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;
			obj.put("servicecode", "SI6432");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				//	toCheckService(postparam+URLEncoder.encode(postparamval),srvcUrl);
			String finalUrl = getText("socialindia.companymgmt.townshiplistauto");
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("_Response::: " + response);
			if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
			    boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
			    	locObjRecvJson = new JSONObject(response);
					ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");					    	  
					ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");					    	  
					ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");					    	  
					ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");							
					locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");			    		 
					JSONArray jary=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");									
					jsrcategory = new JSONArray();
					for (int i = 0; i < jary.length(); i++) {
						JSONArray temm = null;
						try {
							temm = jary.getJSONArray(i);
							int indx = (Integer) temm.get(0);
							String vl = (String) temm.get(1);
							jbcategory = new JSONObject();
							jbcategory.put("id", String.valueOf(indx));// jobj
							vl = vl.replace("'", "%27");
							jbcategory.put("label", vl);// jobj
							jsrcategory.put(jbcategory);// jarray
							jbcategory = null;
						} catch (Exception e) {
							
						} finally {
						}
					}
					locIdTownship = jsrcategory.toString();					
				}
			}

		} catch (Exception ex) {
			System.out.println("Exception Found GetTownshipList.class : " + ex);
		} finally {
			jsrcategory = null;
		}
		return SUCCESS;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public String getLocIdTownship() {
		return locIdTownship;
	}

	public void setLocIdTownship(String locIdTownship) {
		this.locIdTownship = locIdTownship;
	}

	public Common getCommonObj() {
		return commonObj;
	}

	public void setCommonObj(Common commonObj) {
		this.commonObj = commonObj;
	}

}
