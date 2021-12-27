package com.socialindia.monitorMgmt;

import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class MonitorMgmtServices extends ActionSupport {
	private Integer MaxMemory;
	private Integer FreeMemory;
	private Integer TotalMemory;
	private Integer UsedMemory;
	private String ramsize;
	private String freeramsize;
	private String usedramsize;
	private String virtualramsize;
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();

	public String execute() {
		return SUCCESS;
	}

	public String GetJavaHeapMemoryaction() {
		Map sessionMap = ActionContext.getContext().getSession();
		JSONObject finaljj = null;
		String temp = null;
		JSONArray ja = null;
		String finalUrl = null;
		String jsonTextFinal = null;
		String response = null;
		try {
			JSONObject locObjRecvJson = null;// Receive String to json

			obj.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
			obj.put("crntusrgrpid", String.valueOf(sessionMap.get("GROUPCODE")));
			obj.put("crntusrsocietyid",
					String.valueOf(sessionMap.get("sSoctyId")));
			finaljj = new JSONObject();
			finaljj.put("servicecode", "SI33000");
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			System.out.println("request== " + jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.monitoring.systemtracking");
			response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response -------- " + response);

			if (response != null && !response.equalsIgnoreCase("null")
					&& response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					JSONObject json_data = locObjRecvJson.getJSONObject("data");
					MaxMemory = (Integer) Commonutility.toHasChkJsonRtnValObj(json_data, "MaxMemory");
					FreeMemory = (Integer) Commonutility.toHasChkJsonRtnValObj(json_data, "FreeMemory");
					TotalMemory = (Integer) Commonutility.toHasChkJsonRtnValObj(json_data, "TotalMemory");
					UsedMemory = (Integer) Commonutility.toHasChkJsonRtnValObj(json_data, "UsedMemory");
					ramsize = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "Ramsize");									
					freeramsize=(String) Commonutility.toHasChkJsonRtnValObj(json_data, "freeramsize");
					usedramsize=(String) Commonutility.toHasChkJsonRtnValObj(json_data, "usedramsize");
					virtualramsize=(String) Commonutility.toHasChkJsonRtnValObj(json_data, "virtualramsize");
					
					
					/*
					 * eNewsobj.setIvrBnENEWS_ID(Integer.parseInt((String)
					 * Commonutility
					 * .toHasChkJsonRtnValObj(json_data,"enewsid")));
					 * eNewsobj.setIvrBnTITLE
					 * ((String)Commonutility.toHasChkJsonRtnValObj
					 * (json_data,"enewstitle"));
					 * eNewsobj.setIvrBnSHRTDESCRIPTION
					 * ((String)Commonutility.toHasChkJsonRtnValObj
					 * (json_data,"enewsshrtdesc"));
					 * eNewsobj.setIvrBnDESCRIPTION
					 * ((String)Commonutility.toHasChkJsonRtnValObj
					 * (json_data,"enewsdesc"));
					 */

				}
			}

		} catch (Exception Ex) {
			System.out.println("Exception system tracking view-------------- " + Ex);
		} finally {
			finaljj = null;
			response = null;
			finalUrl = null;
			temp = null;
			jsonTextFinal = null;
		}
		return SUCCESS;
	}

	
	public String getFreeramsize() {
		return freeramsize;
	}

	public void setFreeramsize(String freeramsize) {
		this.freeramsize = freeramsize;
	}

	public String getUsedramsize() {
		return usedramsize;
	}

	public void setUsedramsize(String usedramsize) {
		this.usedramsize = usedramsize;
	}

	public String getVirtualramsize() {
		return virtualramsize;
	}

	public void setVirtualramsize(String virtualramsize) {
		this.virtualramsize = virtualramsize;
	}

	public String getRamsize() {
		return ramsize;
	}

	public void setRamsize(String ramsize) {
		this.ramsize = ramsize;
	}

	public Integer getMaxMemory() {
		return MaxMemory;
	}

	public void setMaxMemory(Integer maxMemory) {
		MaxMemory = maxMemory;
	}

	public Integer getFreeMemory() {
		return FreeMemory;
	}

	public void setFreeMemory(Integer freeMemory) {
		FreeMemory = freeMemory;
	}

	public Integer getTotalMemory() {
		return TotalMemory;
	}

	public void setTotalMemory(Integer totalMemory) {
		TotalMemory = totalMemory;
	}

	public Integer getUsedMemory() {
		return UsedMemory;
	}

	public void setUsedMemory(Integer usedMemory) {
		UsedMemory = usedMemory;
	}

}
