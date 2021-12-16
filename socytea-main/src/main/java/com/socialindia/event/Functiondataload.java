package com.socialindia.event;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class Functiondataload extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String function_List;
	private String functionid;
	private String functiontempl_list;
	private String faciltytempl_list;
	public String execute() {
		Log logWrite = null;
		JSONObject obj = null;
		JSONObject data = null;
		JSONObject locObjRecvJson = null;
		JSONObject locObjRecvdataJson = null;
		Common commonObj = null;
		JSONArray lvrJaryfinal = null;
		JSONObject lvrjobj = null;
		try {
			commonObj = new CommonDao();
			logWrite = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			data.put("servicecode", "SI0035");
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.functiondata.list");
			String response = commonObj.jsonRequest(finalUrl, temp);
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;	
			
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");
					ivrresponsecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
					ivrmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
					ivrstatuscode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
					locObjRecvdataJson = (JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "data");
					JSONArray jary = (JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "datalst");
					
					lvrJaryfinal = new JSONArray();
					for (int i = 0; i < jary.length(); i++) {
						JSONArray temm;
						temm = jary.getJSONArray(i);
						int indx = (Integer) temm.get(0);
						String vl = (String) temm.get(1);
						lvrjobj = new JSONObject();
						lvrjobj.put("id", String.valueOf(indx));// jobj
						vl = vl.replace("'", "%27");
						lvrjobj.put("label", vl);// jobj
						lvrJaryfinal.put(lvrjobj);// jarray
					}
					function_List = lvrJaryfinal.toString();
				} else{
					
				}
			} else{
				
			}
			
		} catch (Exception e) {
			Commonutility.toWriteConsole("Step -2: Exception found in "+getClass().getSimpleName()+".class : "+e);
		} finally {
			 obj = null;
			 data = null;
			 locObjRecvJson = null;
			 locObjRecvdataJson = null;
			 commonObj = null;
			 lvrJaryfinal = null;
			 lvrjobj = null;
		}

		return SUCCESS;
	}
	public String getFunction_List() {
		return function_List;
	}
	public void setFunction_List(String function_List) {
		this.function_List = function_List;
	}	
	public synchronized String getFunctionid() {
		return functionid;
	}
	public synchronized void setFunctionid(String functionid) {
		this.functionid = functionid;
	}
	public synchronized String getFunctiontempl_list() {
		return functiontempl_list;
	}
	public synchronized void setFunctiontempl_list(String functiontempl_list) {
		this.functiontempl_list = functiontempl_list;
	}
	
	public String getFuntionTempAuto() {
		
		Log logWrite = null;
		JSONObject obj = null;
		JSONObject data = null;
		JSONObject locObjRecvJson = null;
		JSONObject locObjRecvdataJson = null;
		Common commonObj = null;
		JSONArray lvrJaryfinal = null;
		JSONObject lvrjobj = null;
		try {
			if(functionid!=null && !functionid.equalsIgnoreCase("null") && !functionid.equalsIgnoreCase("")){
			commonObj = new CommonDao();
			logWrite = new Log();
			obj = new JSONObject();
			obj.put("functionmasterid", functionid);
			data = new JSONObject();
			data.put("servicecode", "SI1035");			
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.functiontemplatedata.list");
			String response = commonObj.jsonRequest(finalUrl, temp);
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;	
			
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");
					ivrresponsecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
					ivrmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
					ivrstatuscode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
					locObjRecvdataJson = (JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "data");
					JSONArray jary = (JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "datalst");
					
					lvrJaryfinal = new JSONArray();
					for (int i = 0; i < jary.length(); i++) {
						JSONArray temm;
						temm = jary.getJSONArray(i);
						int indx = (Integer) temm.get(0);
						String vl = (String) temm.get(1);
						lvrjobj = new JSONObject();
						lvrjobj.put("id", String.valueOf(indx));// jobj
						vl = vl.replace("'", "%27");
						lvrjobj.put("label", vl);// jobj
						lvrJaryfinal.put(lvrjobj);// jarray
					}
					functiontempl_list = lvrJaryfinal.toString();
				} else{
					
				}
			} else{
				
			}
			}
		} catch (Exception e) {
			Commonutility.toWriteConsole("Step -2: Exception found in "+getClass().getSimpleName()+".class getFuntionTempAuto : "+e);
			logWrite.logMessage("Step -2 : Exception Functiondataload.class getFuntionTempAuto() : "+e, "error", Functiondataload.class);
		} finally {
			 obj = null;
			 data = null;
			 locObjRecvJson = null;
			 locObjRecvdataJson = null;
			 commonObj = null;
			 lvrJaryfinal = null;
			 lvrjobj = null;logWrite = null;
		}
		return SUCCESS;
	}
	public String getFacilityTempAuto(){
		Log logWrite = null;
		JSONObject obj = null;
		JSONObject data = null;
		JSONObject locObjRecvJson = null;
		JSONObject locObjRecvdataJson = null;
		Common commonObj = null;
		JSONArray lvrJaryfinal = null;
		JSONObject lvrjobj = null;
		String ivrservicecode = null;
		String ivrresponsecode = null;
		String ivrmsg = null;
		String ivrstatuscode = null;
		try{
			commonObj = new CommonDao();
			logWrite = new Log();
			
			obj = new JSONObject();
			obj.put("currentloginid", String.valueOf(ActionContext.getContext().getSession().get("USERID")));
			
			data = new JSONObject();
			data.put("servicecode", "SI1036");
			data.put("data", obj);
			
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			System.out.println("jsonTextFinal -------- "+jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.event.eventfacility");
			String response = commonObj.jsonRequest(finalUrl, temp);
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");
					ivrresponsecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
					ivrmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
					ivrstatuscode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
					locObjRecvdataJson = (JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "data");
					JSONArray jary = (JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "datalst");
					
					lvrJaryfinal = new JSONArray();
					for (int i = 0; i < jary.length(); i++) {
						JSONArray temm;
						temm = jary.getJSONArray(i);
						int indx = (Integer) temm.get(0);
						String vl = (String) temm.get(1);
						String v2 = (String) temm.get(2);
						lvrjobj = new JSONObject();
						lvrjobj.put("id", String.valueOf(indx));// jobj
						vl = vl.replace("'", "%27");
						lvrjobj.put("label", vl+" "+v2);// jobj
						lvrJaryfinal.put(lvrjobj);// jarray
					}
					faciltytempl_list = lvrJaryfinal.toString();
					System.out.println("faciltytemp_list ------------   "+faciltytempl_list);
				} else{
					
				}
		}else{
			
		}
		}catch(Exception ex){
			Commonutility.toWriteConsole("Step -2: Exception found in "+getClass().getSimpleName()+".class getFacilityTempAuto : "+ex);
			logWrite.logMessage("Step -2 : Exception Functiondataload.class getFacilityTempAuto() : "+ex, "error", Functiondataload.class);	
		}finally{
			
		}
		System.out.println("faciltytemp_list ------------   "+faciltytempl_list);
		return SUCCESS;
	}
	public String getFaciltytempl_list() {
		return faciltytempl_list;
	}
	public void setFaciltytempl_list(String faciltytempl_list) {
		this.faciltytempl_list = faciltytempl_list;
	}
	
	
}
