package com.socialindia.companymgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.profileUpdate;

public class CompanyMgmtAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CompanyMstrTblVO cmpyRegObj = new CompanyMstrTblVO(0, null, null, null,null, null, null, null, null);
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private File staffImage;
	private String staffImageFileName;
	private String staffProfileImage;
	private int staffsessID;
	private Integer deletecompanyid;
	private Integer companyuniqueId;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	
	private Common commonObj = new CommonDao();

	public String execute() {
		return SUCCESS;
	}

	public String companymgmtaddaction() {
		Log logWrite = null;
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			logWrite = new Log();
			logWrite.logMessage("Step 1 : Company create called [Start]", "info", CompanyMgmtAction.class);
			Commonutility.toWriteConsole("Step 1 : Company create called [Start]");			
			byte imgByt[] = null;
			obj.put("name", cmpyRegObj.getCompanyName());
			obj.put("emailid", cmpyRegObj.getCompanyEmail());
			obj.put("isdcode", cmpyRegObj.getIsdCode());
			obj.put("mobno", cmpyRegObj.getMobileNo());
			obj.put("cmpyregno", cmpyRegObj.getCmpnyRegno());
			obj.put("country", cmpyRegObj.getCountryId());
			obj.put("sate", cmpyRegObj.getStateId());
			obj.put("city", cmpyRegObj.getCityId());
			obj.put("add1", cmpyRegObj.getAddress1());
			obj.put("add2", cmpyRegObj.getAddress2());
			obj.put("keyforsrch", cmpyRegObj.getKeyforSrch());
			obj.put("verifystatus", cmpyRegObj.getVerifyStatus());
			obj.put("descrp", cmpyRegObj.getDescr());
			obj.put("postalcode",String.valueOf(cmpyRegObj.getPstlId()));
			//imgByt = profileUpdate.toReadFiletoBytes(staffImage);
			//obj.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
			if (staffImage!=null) {
				obj.put("imgsrchpath", staffImage.getAbsolutePath());
			} else {
				obj.put("imgsrchpath", "");
			}
			obj.put("imagename", staffImageFileName);				
			obj.put("status", String.valueOf("1"));
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			obj.put("currentloginid",String.valueOf(sessionMap.get("USERID")));
			data.put("servicecode", "SI6002");
			data.put("servicefor", "1");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.cmpymgmt.companyaddaction");
			logWrite.logMessage("Step 2 : Service Url called: "+finalUrl, "info", CompanyMgmtAction.class);
			Commonutility.toWriteConsole("Step 2 : Service Url called: "+finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			JSONObject json_data = json.getJSONObject("data");
			logWrite.logMessage("Step 3 : Response Received : "+response, "info", CompanyMgmtAction.class);
			Commonutility.toWriteConsole("Step 3 : Response Received : "+response);
			if (statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")) {	
				String lvrcmpid = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "cmpnyid");
				/*if(lvrcmpid!=null && !lvrcmpid.equalsIgnoreCase("null") && !lvrcmpid.equalsIgnoreCase("")){
					String jsonStringTextFinal ="";
					String dta ="";
					JSONObject dataobj = new JSONObject();
					JSONObject imgobj = new JSONObject();					
					dataobj.put("servicecode", "SI6433");
					dataobj.put("userId", sessionMap.get("USERID"));
					dataobj.put("usrTyp", sessionMap.get("GROUPCODE"));					
					imgobj.put("companyid", lvrcmpid);				
					imgobj.put("deleteimage", "0");// 1 - yes , 0 - no		
					imgByt = profileUpdate.toReadFiletoBytes(staffImage);
					imgobj.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
					imgobj.put("imgsrchpath", staffImage.getAbsolutePath());
					imgobj.put("imagename", staffImageFileName);
					dataobj.put("data", imgobj);
					jsonStringTextFinal = dataobj.toString();
					logWrite.logMessage("Step 4 : Company Image Upload called", "info", CompanyMgmtAction.class);
					Commonutility.toWriteConsole("Step 4 : Company Image Upload called");
					Commonutility.toWriteConsole("Rquest Data : " + jsonStringTextFinal);
					jsonStringTextFinal = EncDecrypt.encrypt(jsonStringTextFinal);
					dta = "ivrparams="+ URLEncoder.encode(jsonStringTextFinal);
					finalUrl = getText("socialindia.upload.compnayimageservice");
					String lvrImgupdrsp = commonObj.jsonRequest(finalUrl, dta);					
				} else {
					logWrite.logMessage("Step 4 : Company image not upload by user", "info", CompanyMgmtAction.class);
					Commonutility.toWriteConsole("Step 4 : Company image not upload by user");
				}*/
				logWrite.logMessage("Step 5 : Company create [End]", "info", CompanyMgmtAction.class);
				Commonutility.toWriteConsole("Step 5 : Company create [End]");
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("success.company.created"));
				}				
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "success";		
			} else {
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("error.company.created"));
				}						
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}
		} catch (Exception ex) {
			logWrite.logMessage("Step -1 : Exception Found : "+ex, "error", CompanyMgmtAction.class);
			Commonutility.toWriteConsole("Step -1 : Exception Found in CompanyMgmtAction.class : "+ex);
			alert.setCls("danger");			
			alert.setMsg(getText("error.company.created"));							
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally {
			logWrite = null;
		}		
	}

 public String deletecompanyActionFunction() {
	String result = "";
	try {
		Map sessionMap = ActionContext.getContext().getSession();
		if (deletecompanyid != 0 && deletecompanyid > 0) {
			JSONObject finaljj = new JSONObject();
			JSONObject dataJson = new JSONObject();
			dataJson.put("cmpyid", String.valueOf(deletecompanyid));
			dataJson.put("cmpystatus", "1");
			dataJson.put("currentloginid",String.valueOf(sessionMap.get("USERID")));
			finaljj.put("servicefor", "4");// delete
			finaljj.put("data", dataJson);
			String jsonTextFinal = finaljj.toString().trim();			
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.companymgmt.companydeleteaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject json_data = json.getJSONObject("data");

			result = json_data.getString("status");
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			String respCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "respcode");
			String lvrRspmsg =  (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			if ((statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("00")) && respCode.equalsIgnoreCase("R0099")) {
				if (result.equalsIgnoreCase("success")) {
					alert.setCls("success");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("sucess.company.deleted"));
					}					
					alertList.add(alert);
					return "success";
				} else {
					alert.setCls("danger");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("error.company.deleted"));
					}						
					alertList.add(alert);
					return "input";
				}
			} else {
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("error.company.deleted"));
				}	
				alertList.add(alert);
				return "input";
			}
		} else {
			
		}
	} catch (Exception ex) {
		alert.setCls("error");
		alert.setMsg(getText("Exception in error deletion."));
		alertList.add(alert);
		return "error";
	}
	return SUCCESS;
}

 public String viewcompanyActionFunction() {
	Log logWrite =null;
	JSONObject locObjRecvJson = null;// Receive String to json
	JSONObject locObjRecvdataJson = null;// Receive Data Json
	JSONObject locObjRspdataJson = null;// Response Data Json
	JSONObject lvrRqstdataJson = null; // Request Data Json
	try {
		logWrite = new Log();
		logWrite.logMessage("Step 1 : Company view called [Start]", "info", CompanyMgmtAction.class);
		Commonutility.toWriteConsole("Step 1 : Company view called [Start]");
		Map currentSession = ActionContext.getContext().getSession();
		if (deletecompanyid == null) {
			if ((deletecompanyid == null || deletecompanyid == -1 || deletecompanyid == 0) && currentSession.get("currentsession_deletecompanyid") != null) {
				String stfiddd = (String) currentSession.get("currentsession_deletecompanyid");
				deletecompanyid = Integer.parseInt(stfiddd);
			}
		} else {
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_deletecompanyid",String.valueOf(deletecompanyid));
		}
		Map sessionMap = ActionContext.getContext().getSession();		
		String idcardtypname = "";
		String verifysts = "";
		lvrRqstdataJson = new JSONObject();
		lvrRqstdataJson.put("cmpyid", String.valueOf(deletecompanyid));
		lvrRqstdataJson.put("cmpystatus", "1");
		lvrRqstdataJson.put("currentloginid",String.valueOf(sessionMap.get("USERID")));
		JSONObject finaljj = new JSONObject();
		finaljj.put("servicecode", "SI6004");
		finaljj.put("servicefor", "3");// select		
		finaljj.put("data", lvrRqstdataJson);
		/* String srvcUrl="socialindia.labormgmt.laborviewaction"; */
		String jsonTextFinal = finaljj.toString();
		jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
		String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
		String finalUrl = getText("socialindia.companymgmt.companyviewaction");
		logWrite.logMessage("Step 2 : Company view service Url : "+finalUrl, "info", CompanyMgmtAction.class);
		Commonutility.toWriteConsole("Step 2 : Company view service Url : "+finalUrl);
		String response = commonObj.jsonRequest(finalUrl, temp);
		if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
			boolean ivIsJson = Commonutility.toCheckIsJSON(response);
			if (ivIsJson) {
				locObjRecvJson = new JSONObject(response);
				locObjRecvdataJson = locObjRecvJson.getJSONObject("data");
				cmpyRegObj.setImageNameWeb(locObjRecvdataJson.getString("str_cmpy_webimage"));
				ActionContext.getContext().getSession().put("staffProfileImage",cmpyRegObj.getImageNameWeb());
				cmpyRegObj.setKeyforSrch((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"str_cmpy_keyfrsrch"));				
				cmpyRegObj.setDescr((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"str_cmpy_descp"));				
				cmpyRegObj.setMobileNo((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cmpy_phno"));
				cmpyRegObj.setIsdCode((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cmpy_isdcode"));				
				cmpyRegObj.setCountryId((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_cmpy_cntry"));				
				cmpyRegObj.setStateId((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_cmpy_stateid"));				
				cmpyRegObj.setCityId((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_cmpy_cityid"));
				cmpyRegObj.setPstlId((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_cmpy_pstlid"));
				String email =(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cmpy_email");
				if(!email.equalsIgnoreCase("")||!email.equalsIgnoreCase("null")||email != null)
				email =email.toLowerCase();
				cmpyRegObj.setCompanyEmail(email);
				String locvrfstsStr=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_cmpy_verifysts");
				cmpyRegObj.setVerifyStatus(locvrfstsStr);
				cmpyRegObj.setCompanyId((Integer)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_cmpy_id"));
				cmpyRegObj.setCompanyUniqId((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cmpy_serviceid"));
				cmpyRegObj.setAddress1((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cmpy_add1"));
				cmpyRegObj.setAddress2((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cmpy_add2"));
				cmpyRegObj.setCompanyName((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cmpy_name"));
				cmpyRegObj.setCountryname((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"cmpy_cntryName"));
				cmpyRegObj.setStatename((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"cmpy_stateName"));
				cmpyRegObj.setCityname((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"cmpy_cityName"));
				
				Integer pincode = (Integer)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"cmpy_pincodeName");
				cmpyRegObj.setPincodename(pincode+"");
				
				cmpyRegObj.setCmpnyRegno((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cmpy_regno"));
				
			}
		}	
		logWrite.logMessage("Step 3 : Company view called [End]", "info", CompanyMgmtAction.class);
		Commonutility.toWriteConsole("Step 3 : Company view called [End]");
	} catch (Exception ex) {
		ex.printStackTrace();
		logWrite.logMessage("Step -1 : Company view called [Exception] : "+ex, "error", CompanyMgmtAction.class);
		Commonutility.toWriteConsole("Step -1 : Company view called [Exception] : "+ex);
	} finally {
		logWrite = null; lvrRqstdataJson = null; locObjRecvdataJson = null; locObjRecvJson = null;
	}
	return SUCCESS;
	
}

 public String updatedcompanyActionFunction() {
	 byte imgByt[] = null;
	 Log logWrite = null;
	 JSONObject dataJson = null;
	 JSONObject finaljj = null;
	 JSONObject lvrRspjson = null;
	try {
		logWrite = new Log();
		int prt = ServletActionContext.getRequest().getServerPort();
		Commonutility.toWriteConsole("prt : "+prt);
		Commonutility.toWriteConsole("getLocalAddr : "+ServletActionContext.getRequest().getLocalAddr());
		Commonutility.toWriteConsole("getLocalName : "+ServletActionContext.getRequest().getLocalName());
		Commonutility.toWriteConsole("getLocalPort : "+ServletActionContext.getRequest().getLocalPort());
		logWrite.logMessage("Step 1 : Company update called [Start]", "info", CompanyMgmtAction.class);
		Commonutility.toWriteConsole("Step 1 : Company update called [Start]");			
		Map sessionMap = ActionContext.getContext().getSession();
		dataJson=new JSONObject();
		dataJson.put("cmpyid",String.valueOf(deletecompanyid));
		dataJson.put("name", cmpyRegObj.getCompanyName());
		dataJson.put("emailid", cmpyRegObj.getCompanyEmail());
		dataJson.put("isdcode", cmpyRegObj.getIsdCode());
		dataJson.put("mobno", cmpyRegObj.getMobileNo());
		dataJson.put("cmpyregno", cmpyRegObj.getCmpnyRegno());
		dataJson.put("country", cmpyRegObj.getCountryId());
		dataJson.put("sate", cmpyRegObj.getStateId());
		dataJson.put("city", cmpyRegObj.getCityId());
		dataJson.put("add1", cmpyRegObj.getAddress1());
		dataJson.put("add2", cmpyRegObj.getAddress2());
		dataJson.put("keyforsrch", cmpyRegObj.getKeyforSrch());
		dataJson.put("verifystatus", cmpyRegObj.getVerifyStatus());		
		dataJson.put("descrp", cmpyRegObj.getDescr());
		
		System.out.println("cmpyRegObj getPstlId >>>>>>>>>>>>>>>>>>>>>>>>>" + cmpyRegObj.getPstlId());
		Commonutility.toWriteConsole("cmpyRegObj getPstlId >>>>>>>>>>>>>>>>>>>>>>>>>" + cmpyRegObj.getPstlId());	
		
		dataJson.put("postalcode",String.valueOf(cmpyRegObj.getPstlId()));
		//dataJson.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
		// dataJson.put("img_mobile",staffImageFileName);
		dataJson.put("imagename", staffImageFileName);
		if (staffImage!=null) {
			dataJson.put("imgsrchpath", staffImage.getAbsolutePath());
		} else {
			dataJson.put("imgsrchpath", "");
		}
		dataJson.put("status", String.valueOf("1"));
		dataJson.put("entryby", String.valueOf(sessionMap.get("USERID")));		
		dataJson.put("currentloginid",String.valueOf(sessionMap.get("USERID")));						
		finaljj=new JSONObject();
		finaljj.put("servicecode", "SI6005");
		finaljj.put("servicefor", "2");// Edit
		finaljj.put("data", dataJson);			
		String jsonTextFinal = finaljj.toString().trim();
		jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
		String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
		String finalUrl = getText("socialindia.companymgmt.companyupdatedaction");
		logWrite.logMessage("Step 2 : Company update service url : " + finalUrl, "info", CompanyMgmtAction.class);
		Commonutility.toWriteConsole("Step 2 : Company update service url : " + finalUrl);	
		String response = commonObj.jsonRequest(finalUrl, temp);
		lvrRspjson = new JSONObject(response);	
		String statusCode = (String) lvrRspjson.get("statuscode");
		String respCode = (String) lvrRspjson.get("respcode");
		String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(lvrRspjson, "message");
		if ((statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")) && (respCode.equalsIgnoreCase("R0097"))) {			
			logWrite.logMessage("Step 3 : Company update success. statusCode : "+statusCode, "info", CompanyMgmtAction.class);
			Commonutility.toWriteConsole("Step 3 : Company update success. statusCode : "+statusCode);	
			/*if((staffImage!=null) && (staffImageFileName!=null && !staffImageFileName.equalsIgnoreCase("null") && !staffImageFileName.equalsIgnoreCase(""))){
				//imgByt = profileUpdate.toReadFiletoBytes(staffImage);
				//dataJson.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
				///dataJson.put("imagename", staffImageFileName);
					JSONObject dataobj = null; JSONObject imgobj = null;
					try {
					logWrite.logMessage("Step 4 : Company update company image will found.", "info", CompanyMgmtAction.class);
					Commonutility.toWriteConsole("Step 4 : Company update company image will found.");	
					String jsonStringTextFinal ="";
					String dta ="";
					dataobj = new JSONObject();
					imgobj = new JSONObject();					
					dataobj.put("servicecode", "SI6433");
					dataobj.put("userId", sessionMap.get("USERID"));
					dataobj.put("usrTyp", sessionMap.get("GROUPCODE"));
					
					imgobj.put("companyid", Commonutility.toCheckNullEmpty(deletecompanyid));
					imgobj.put("deleteimage", "1");// 1 - yes , 0 - no					
					imgByt = profileUpdate.toReadFiletoBytes(staffImage);
					imgobj.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
					imgobj.put("imagename", staffImageFileName);
					
					dataobj.put("data", imgobj);
					jsonStringTextFinal = dataobj.toString();
					Commonutility.toWriteConsole("Rquest Data : " + jsonStringTextFinal);
					jsonStringTextFinal = EncDecrypt.encrypt(jsonStringTextFinal);
					dta = "ivrparams="+ URLEncoder.encode(jsonStringTextFinal);
					finalUrl = getText("socialindia.upload.compnayimageservice");
					String lvrImgupdrsp = commonObj.jsonRequest(finalUrl, dta);
					logWrite.logMessage("Step 5 : Company update - company image uploaded.", "info", CompanyMgmtAction.class);
					Commonutility.toWriteConsole("Step 5 : Company update - company image uploaded.");	
					} catch (Exception e) {

					} finally {
						dataobj = null; imgobj = null;
					}
				}*/
				logWrite.logMessage("Step 6 : Company update called. [End]", "info", CompanyMgmtAction.class);
				Commonutility.toWriteConsole("Step 6 : Company update called. [End]");
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				}else{
					alert.setMsg(getText("sucess.company.updated"));
				}				
				alertList.add(alert);
				return "success";
			 
			} else {
				logWrite.logMessage("Step 3 : Company update error. statusCode : "+statusCode, "info", CompanyMgmtAction.class);
				Commonutility.toWriteConsole("Step 3 : Company update error. statusCode : "+statusCode);
				alert.setCls("error");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				}else{
					alert.setMsg(getText("error.company.updated"));
				}
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {
		logWrite.logMessage("Step -1 : Company update called. [Exception] : "+ex, "info", CompanyMgmtAction.class);
		Commonutility.toWriteConsole("Step -1 : Company update called. [Exception] : "+ex);
		alert.setCls("error");
		alert.setMsg(getText("error.company.updated"));
		alertList.add(alert);
		return "error";
		} finally {
			imgByt = null; logWrite = null; dataJson = null; lvrRspjson = null;
		}

	}

	public String UpdatecompanyActionFunction() {
		return SUCCESS;
	}

	public CompanyMstrTblVO getCmpyRegObj() {
		return cmpyRegObj;
	}

	public void setCmpyRegObj(CompanyMstrTblVO cmpyRegObj) {
		this.cmpyRegObj = cmpyRegObj;
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

	public File getStaffImage() {
		return staffImage;
	}

	public void setStaffImage(File staffImage) {
		this.staffImage = staffImage;
	}

	public String getStaffImageFileName() {
		return staffImageFileName;
	}

	public void setStaffImageFileName(String staffImageFileName) {
		this.staffImageFileName = staffImageFileName;
	}

	public String getStaffProfileImage() {
		return staffProfileImage;
	}

	public void setStaffProfileImage(String staffProfileImage) {
		this.staffProfileImage = staffProfileImage;
	}

	public int getStaffsessID() {
		return staffsessID;
	}

	public void setStaffsessID(int staffsessID) {
		this.staffsessID = staffsessID;
	}

	public Integer getDeletecompanyid() {
		return deletecompanyid;
	}

	public void setDeletecompanyid(Integer deletecompanyid) {
		this.deletecompanyid = deletecompanyid;
	}

	public Integer getCompanyuniqueId() {
		return companyuniqueId;
	}

	public void setCompanyuniqueId(Integer companyuniqueId) {
		this.companyuniqueId = companyuniqueId;
	}


}

