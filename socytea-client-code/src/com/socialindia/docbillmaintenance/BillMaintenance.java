package com.socialindia.docbillmaintenance;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class BillMaintenance extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private List <File> maintexcelfile = new ArrayList<File>();
	private String maintexcelfileFileName;
	JSONObject obj = null;
	JSONObject data = null;
	commomServices commonSnippet = null;
	private Common commonObj = null;
	private String societyId = null;
	private AlertVo alert = null;
	private List<AlertVo> alertList = null;
	public String execute(){
		Map sessionMap = null;
		String sFileName = null;
		String[] filename=null;
	try{
		if(maintexcelfileFileName!=null){
		alertList = new ArrayList<AlertVo>();
		alert = new AlertVo();
		commonObj = new CommonDao();
		commonSnippet=new commomServices();
		data = new JSONObject();
		obj = new JSONObject();
		sessionMap = ActionContext.getContext().getSession();
		sFileName = maintexcelfileFileName;
		if(sFileName!=null){
			data.put("servicecode", "SI6406");
			data.put("currentloginid", sessionMap.get("USERID"));				
			obj.put("userId", sessionMap.get("USERID"));
			obj.put("usrTyp", sessionMap.get("GROUPCODE"));	
			if(societyId!=null && !societyId.equalsIgnoreCase("null") && !societyId.equalsIgnoreCase("")){
				obj.put("societyCode", Commonutility.toCheckNullEmpty(societyId));
			}else{
				obj.put("societyCode", Commonutility.toCheckNullEmpty(sessionMap.get("sSoctyId")));
			}
			String uploaddetail = "";
			String uploadfileName = "", lvrUploadApath = null;
			if(sFileName.contains(",")){
			}else{
				sFileName=sFileName+",";
			}
			filename=sFileName.split(",");
			double totfilesize=0;
			byte imgBytlogo[]=new byte[2048];
				for (int f = 0; f < maintexcelfile.size(); f++) {
				 imgBytlogo=new byte[2048];
				double filesiz=maintexcelfile.get(f).length();
				totfilesize+=filesiz;
				String fileName=filename[f];
				//imgBytlogo=commonSnippet.toReadFiletoBytes(maintexcelfile.get(f));
				//uploaddetail=commonSnippet.toByteAryToBase64EncodeStr(imgBytlogo);				
				uploadfileName=fileName;
				if (maintexcelfile.get(f)!=null) {
					lvrUploadApath = maintexcelfile.get(f).getAbsolutePath();
				} else {
					lvrUploadApath = "";
				}
				
			}
			obj.put("imageDetail",uploaddetail);
			obj.put("uploadsrchpath",lvrUploadApath);
			obj.put("fileName",uploadfileName);
			obj.put("docSubFolder",1);
			data.put("data", obj);
			
			String jsonTextFinal = data.toString();	
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);										
			String finalUrl = getText("socialindia.billmaitenance.addmaintenancedocexcelupload");								
			String response = commonObj.jsonRequest(finalUrl, temp);				
			JSONObject json = new JSONObject(response);
			
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			 if(statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")){
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Maintenance.doc.exlupload.success"));
				}
				
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return SUCCESS;
			}else{
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Maintenance.doc.exlupload.error"));
				}					
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}								
		}	
		}
	}catch(Exception ex){
		System.out.println("Exception------------------ "+ex);
	}
		return SUCCESS;
	}
	
	
	
	public List<File> getMaintexcelfile() {
		return maintexcelfile;
	}
	public void setMaintexcelfile(List<File> maintexcelfile) {
		this.maintexcelfile = maintexcelfile;
	}
	public String getMaintexcelfileFileName() {
		return maintexcelfileFileName;
	}
	public void setMaintexcelfileFileName(String maintexcelfileFileName) {
		this.maintexcelfileFileName = maintexcelfileFileName;
	}
	public String getSocietyId() {
		return societyId;
	}
	public void setSocietyId(String societyId) {
		this.societyId = societyId;
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

}
