package com.socialindia.biometric;

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
import com.socialindia.vo.BiometricDatabaseInfoTblVO;

public class CreateDatabase extends ActionSupport implements SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BiometricDatabaseInfoTblVO biodatabaseinfo = new BiometricDatabaseInfoTblVO();
	Map<String, Object> session;
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	JSONObject obj = new JSONObject();

	@Override
	public String execute() throws Exception {
		
		String societycode = String.valueOf(session.get("sSoctyId"));
				
		Commonutility.toWriteConsole("Step 1 : create database host name........ " + biodatabaseinfo.getBioDatabase() + " societycode:"+societycode);
		Commonutility.toWriteConsole("Step 1 : create database host name........ " + biodatabaseinfo.getBioHostname());
		Commonutility.toWriteConsole("Step 1 : create database host name........ " + biodatabaseinfo.getBioPassword());
		Commonutility.toWriteConsole("Step 1 : create database host name........ " + biodatabaseinfo.getBioPort());
		Commonutility.toWriteConsole("Step 1 : create database host name........ " + biodatabaseinfo.getBioUsername());
		

		Log logwrite = null;
		JSONObject obj = null;
		JSONObject data = null;
		JSONObject json_data = null;
		JSONObject json = null;
		try {
			obj = new JSONObject();
			data = new JSONObject();			
			logwrite = new Log();
			Commonutility.toWriteConsole("Step 1 : Staff Creation Called [Start]." +Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Staff Creation Called [Start].", "info", CreateDatabase.class);			
			obj.put("servicecode", "SI4002");		
			obj.put("hostname", biodatabaseinfo.getBioHostname());
			obj.put("username", biodatabaseinfo.getBioUsername());
			obj.put("password", biodatabaseinfo.getBioPassword());
			obj.put("database", biodatabaseinfo.getBioDatabase());
			obj.put("port", biodatabaseinfo.getBioPort());
			obj.put("sSoctyId", societycode);
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			
			String finalUrl = getText("biometric.databaseinfo.creation");
			Commonutility.toWriteConsole("Step 2 : Biometric Database Creation Called Service API : "+finalUrl);
			logwrite.logMessage("Step 2 : Biometric Database Creation Called Service API : "+finalUrl, "info", CreateDatabase.class);
			String response = commonObj.jsonRequest(finalUrl, temp);			
			
			json = new JSONObject(response);
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			String respCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "respcode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			json_data = (JSONObject) Commonutility.toHasChkJsonRtnValObj(json, "data");
			if (statusCode.equalsIgnoreCase("00")) {
				Commonutility.toWriteConsole("Step 3 :  Biometric Database Creation Called [End]. Success");
				logwrite.logMessage("Step 3 : Biometric Database Creation Called [End]. Success", "info", CreateDatabase.class);
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else{
					alert.setMsg(getText("Biometric Database Info created successfully"));
				}				
				alertList.add(alert);
				session.put("alertList", alertList);
				
				session.put("biohostname", biodatabaseinfo.getBioHostname());
				session.put("bioport", biodatabaseinfo.getBioPort());
				session.put("biodatabase", biodatabaseinfo.getBioDatabase());
				session.put("biousername", biodatabaseinfo.getBioUsername());
				session.put("biopassword", biodatabaseinfo.getBioPassword());
				return "success";
				
			} else {
				Commonutility.toWriteConsole("Step 3 : Biometric Database Creation Called [End]. API return Not Success : " +  Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : Biometric Database Creation Called [End]. API return Not Success", "info", CreateDatabase.class);
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
			logwrite.logMessage("Step -1 : Exception Found in StaffCreate.creationForm(): " + ex, "info", CreateDatabase.class);
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
	
	
	public BiometricDatabaseInfoTblVO getBiodatabaseinfo() {
		return biodatabaseinfo;
	}

	public void setBiodatabaseinfo(BiometricDatabaseInfoTblVO biodatabaseinfo) {
		this.biodatabaseinfo = biodatabaseinfo;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}