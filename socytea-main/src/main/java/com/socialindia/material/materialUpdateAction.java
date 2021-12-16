package com.socialindia.material;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.signUpAction;
import com.socialindia.societyMgmt.SocietyWingsDetailTbl;

public class materialUpdateAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	private int topicsId;
	private MvpMaterialTbl materialObj=new MvpMaterialTbl();
	
	public String execute() {
		JSONObject obj = null;
		JSONObject data = null;
		String ivrservicecode = null;
		String ivrresponsecode = null;
		String ivrmsg = null;
		String ivrstatuscode = null;
		JSONObject locObjRecvJson = null;
		JSONObject locObjRecvdataJson = null;
		Log log = null;
		try {
			log = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			log.logMessage("Step 1 : Material Update Called [Start] ", "info", materialUpdateAction.class);
			Commonutility.toWriteConsole("Step 1 : Material Update Called [Start] ");
			data.put("servicecode", "SI0056");
			obj.put("materialid", materialObj.getMaterialId());
			obj.put("categoryid", materialObj.getMaterialCategoryId());
			obj.put("materialname", materialObj.getMaterialName());
			obj.put("totalqnty", materialObj.getTotalQnty());
			obj.put("usedqnty", materialObj.getUsedQnty());
			obj.put("materialqnty", materialObj.getMaterialQnty());
			obj.put("materialprice", materialObj.getMaterialPrice());
			obj.put("purchasedate", materialObj.getPurDate());
			obj.put("materialdesc", materialObj.getMaterialDesc());			
	
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("materialMgmt.management.update.Action");
			log.logMessage("Step 2 : Material Update Service Url : "+finalUrl, "info", materialUpdateAction.class);
			Commonutility.toWriteConsole("Step 2 : Material Update Service Url : "+finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);			
			if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0) {
				log.logMessage("Step 3 : Material Update Service response received ", "info", materialUpdateAction.class);
				Commonutility.toWriteConsole("Step 3 : Material Update Service response received ");
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");	    	  
					ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");	    	  
					ivrmsg =(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");	    	  
					ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");			
					locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");    	    	  
					if(ivrstatuscode!= null && (ivrstatuscode.equalsIgnoreCase("00") || ivrstatuscode.equalsIgnoreCase("0"))){
						log.logMessage("Step 4 : Material Update Successfully [End]", "info", materialUpdateAction.class);
						Commonutility.toWriteConsole("Step 4 : Material Update Successfully [End]");
						alert.setCls("success");
						if(Commonutility.checkempty(ivrmsg)){
							alert.setMsg(ivrmsg);
						} else {
							alert.setMsg("Material updated successfully");
						}
						alertList.add(alert);
						return SUCCESS;
					} else {
						log.logMessage("Step 4 : Material not update. [End]", "info", materialUpdateAction.class);
						Commonutility.toWriteConsole("Step 4 : Material not update. [End]");
						alert.setCls("danger");
						if(Commonutility.checkempty(ivrmsg)){
							alert.setMsg(ivrmsg);
						} else {
							alert.setMsg("Material updated. error");
						}						
						alertList.add(alert);
						return INPUT;
					}	
				} 
			} else {
				log.logMessage("Step 3 : Material Update Service response not received [End]", "info", materialUpdateAction.class);
				Commonutility.toWriteConsole("Step 3 : Material Update Service response not received [End]");
				alert.setCls("danger");
				alert.setMsg(getText("Material updation error"));
				alertList.add(alert);
				return INPUT;
			}
								
	} catch (Exception e) {		
		log.logMessage("Setp -1 : Material Update materialUpdateAction.class Exception [End] : " + e, "error",materialUpdateAction.class);
		alert.setCls("danger");
		alert.setMsg(getText("Material updation error"));
		alertList.add(alert);
		return INPUT;
	} finally {
		 ivrservicecode = null;
		 ivrresponsecode = null;
		 ivrmsg = null;
		 ivrstatuscode = null;
		 locObjRecvJson = null;
		 locObjRecvdataJson = null; obj = null;
			 data = null; log = null;
	}
	return SUCCESS;
}
	public int getTopicsId() {
		return topicsId;
	}
	public void setTopicsId(int topicsId) {
		this.topicsId = topicsId;
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
	public MvpMaterialTbl getMaterialObj() {
		return materialObj;
	}
	public void setMaterialObj(MvpMaterialTbl materialObj) {
		this.materialObj = materialObj;
	}
	
}
