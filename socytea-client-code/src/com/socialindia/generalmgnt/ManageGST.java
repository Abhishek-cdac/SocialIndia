package com.socialindia.generalmgnt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.vo.ManageGSTTblVO;

public class ManageGST extends ActionSupport implements SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ManageGSTTblVO manageGST = new ManageGSTTblVO();
	Map<String, Object> session;
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	JSONObject obj = new JSONObject();

	@Override
	public String execute() throws Exception {
		
		String societycode = String.valueOf(session.get("sSoctyId"));
				
		Commonutility.toWriteConsole("Step 1 : getGstNum........ " + manageGST.getGstNum());
		Commonutility.toWriteConsole("Step 1 : getMinGstAmt........ " + manageGST.getMinGstAmt());
		Commonutility.toWriteConsole("Step 1 : getMinAmt........ " + manageGST.getMinAmt());
		

		Log logwrite = null;
		JSONObject obj = null;
		JSONObject data = null;
		JSONObject json_data = null;
		JSONObject json = null;
		try {
			obj = new JSONObject();
			data = new JSONObject();			
			logwrite = new Log();
			Commonutility.toWriteConsole("Step 1 : ManageGST Creation Called [Start]." +Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : ManageGST Creation Called [Start].", "info", ManageGST.class);			
			obj.put("servicecode", "SI4002");		
			obj.put("gstNum", manageGST.getGstNum());
			obj.put("minGstAmt", manageGST.getMinGstAmt());
			obj.put("minAmt", manageGST.getMinAmt());
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			
			String finalUrl = getText("gst.managegst.creation");
			Commonutility.toWriteConsole("Step 2 : ManageGST  Creation Called Service API : "+finalUrl);
			logwrite.logMessage("Step 2 : ManageGST  Creation Called Service API : "+finalUrl, "info", ManageGST.class);
			String response = commonObj.jsonRequest(finalUrl, temp);			
			
			json = new JSONObject(response);
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			String respCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "respcode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			json_data = (JSONObject) Commonutility.toHasChkJsonRtnValObj(json, "data");
			if (statusCode.equalsIgnoreCase("00")) {
				Commonutility.toWriteConsole("Step 3 :  ManageGST  Creation Called [End]. Success");
				logwrite.logMessage("Step 3 : ManageGST  Creation Called [End]. Success", "info", ManageGST.class);
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else{
					alert.setMsg(getText("ManageGST  Info created successfully"));
				}				
				alertList.add(alert);
				session.put("alertList", alertList);
				
				session.put("gstNum", manageGST.getGstNum());
				session.put("minGstAmt", manageGST.getMinGstAmt());
				session.put("minAmt", manageGST.getMinAmt());
				return "success";
				
			} else {
				Commonutility.toWriteConsole("Step 3 : ManageGST  Creation Called [End]. API return Error : " +  Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : ManageGST  Creation Called [End]. API return Error", "info", ManageGST.class);
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else{
					alert.setMsg(getText("Error.staffreg.catch"));
				}						
				alertList.add(alert);
				session.put("alertList", alertList);
				return "input";
			}
		} catch (Exception ex) {			
			Commonutility.toWriteConsole("Step -1 : Exception Found in StaffCreate.creationForm(): " + ex);
			logwrite.logMessage("Step -1 : Exception Found in StaffCreate.creationForm(): " + ex, "info", ManageGST.class);
			alert.setCls("danger");			
			alert.setMsg(getText("Error.staffreg.catch"));							
			alertList.add(alert);
			session.put("alertList", alertList);
			return "input";
		} finally {
			 logwrite = null;
			 obj = null;
			 data = null; json_data = null; json = null;
		}		
	
	}
	
	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public AlertVo getAlert() {
		return alert;
	}
	
	public List<AlertVo> getAlertList() {
		return alertList;
	}

	public void setAlertList(List<AlertVo> alertList) {
		this.alertList = alertList;
	}

	public void setAlert(AlertVo alert) {
		this.alert = alert;
	}

	public ManageGSTTblVO getManageGST() {
		return manageGST;
	}

	public void setManageGST(ManageGSTTblVO manageGST) {
		this.manageGST = manageGST;
	}
}