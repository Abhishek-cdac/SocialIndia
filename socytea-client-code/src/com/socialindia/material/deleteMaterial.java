package com.socialindia.material;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class deleteMaterial extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Log log = new Log();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private int materialId;
	public String execute(){
		
		try{
			Map sessionMap = ActionContext.getContext().getSession();			
			data.put("servicecode", "SI0054");
			obj.put("materialId", materialId);
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("materialMgmt.management.delete.Action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;
			JSONObject locObjRecvJson = null;
			JSONObject locObjRecvdataJson = null;
			if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
    			  locObjRecvJson = new JSONObject(response);
		    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");		    	  
		    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");		    	  
		    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");		    	  
		    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");				    		  
					if (ivrstatuscode != null && (ivrstatuscode.equalsIgnoreCase("00") || ivrstatuscode.equalsIgnoreCase("0"))){
						alert.setCls("success");
						if(Commonutility.checkempty(ivrmsg)){
							alert.setMsg(ivrmsg);						
						} else {
							alert.setMsg("Material deleted successfully.");
						}
						alertList.add(alert);
						return "success";
					} else {
						alert.setCls("danger");
						if(Commonutility.checkempty(ivrmsg)){
							alert.setMsg(ivrmsg);
						} else {
							alert.setMsg("Material not deleted. Error.");
						}
						alertList.add(alert);
						return "input";
					}

				}
			}
    								
		}catch (Exception e) {
			Commonutility.toWriteConsole("Step : Exception found in deleteMaterial.class : "+e);
			alert.setCls("danger");		
			alert.setMsg("Material not deleted. Error.");
			alertList.add(alert);
			return "input";
		}
		return SUCCESS;
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
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
}
