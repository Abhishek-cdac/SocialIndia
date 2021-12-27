package com.socialindia.residentmgmt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;


public class GetWingsblockdataload extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ivrSocietwingsoption = "";
	private List <GetWingsblockVo> ivrSocietwingsList;
	private String ivrSocietyid;
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
			ivrSocietwingsList =new ArrayList<GetWingsblockVo>();
			commonObj = new CommonDao();
			logWrite = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			obj.put("societyid", ivrSocietyid);
			data.put("servicecode", "SI0035");
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.societywinsblock.list");
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
					lvrjobj = new JSONObject();
					for (int i = 0; i < jary.length(); i++) {
						JSONArray temm;
						temm = jary.getJSONArray(i);
						int indx = (Integer) temm.get(0);
						String vl = (String) temm.get(1);						
						ivrSocietwingsoption += "<option value='"+indx+"'>"+vl+"</option>";
						ivrSocietwingsList.add(new GetWingsblockVo(String.valueOf(indx), vl, null));
						//lvrjobj.put("id", String.valueOf(indx));// jobj
						//vl = vl.replace("'", "%27");
						//lvrjobj.put("label", vl);// jobj
						lvrjobj.put(String.valueOf(indx), vl);
																										
					}
					lvrJaryfinal.put(lvrjobj);// jarray		
					//ivrSocietwingsList = lvrJaryfinal.toString();
				} else{
					
				}
			} else{
				
			}
			
		} catch (Exception ex) {
			logWrite.logMessage("Step -1 : Exception found in "+getClass().getSimpleName()+".class : " + ex+"" , "error", GetWingsblockdataload.class);
			Commonutility.toWriteConsole("Step -1 : Exception found in "+getClass().getSimpleName()+".class : " + ex+"");
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
	
	public String getIvrSocietyid() {
		return ivrSocietyid;
	}
	public void setIvrSocietyid(String ivrSocietyid) {
		this.ivrSocietyid = ivrSocietyid;
	}

	public List<GetWingsblockVo> getIvrSocietwingsList() {
		return ivrSocietwingsList;
	}

	public void setIvrSocietwingsList(List<GetWingsblockVo> ivrSocietwingsList) {
		this.ivrSocietwingsList = ivrSocietwingsList;
	}

	public String getIvrSocietwingsoption() {
		return ivrSocietwingsoption;
	}

	public void setIvrSocietwingsoption(String ivrSocietwingsoption) {
		this.ivrSocietwingsoption = ivrSocietwingsoption;
	}

	
	
}
