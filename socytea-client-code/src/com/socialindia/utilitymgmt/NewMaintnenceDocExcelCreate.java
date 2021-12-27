package com.socialindia.utilitymgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.utilitymgmt.persistance.DocumentManageTblVO;

public class NewMaintnenceDocExcelCreate extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List <File> maintexcelfile = new ArrayList <File> ();
	private String maintexcelfileFileName;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	commomServices commonSnippet=new commomServices();
	private Common commonObj = new CommonDao();
	private String societyId=null;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	public String execute(){
		Map sessionMap =null;
		try{
			System.out.println("societyId -----------------1------------- "+societyId);
			String sFileName = maintexcelfileFileName;
			if(sFileName!=null){
				sessionMap = ActionContext.getContext().getSession();
				String[] filename=null;
				data.put("servicecode", "SI6406");
				data.put("currentloginid", sessionMap.get("USERID"));				
				obj.put("userId", sessionMap.get("USERID"));
				obj.put("usrTyp", sessionMap.get("GROUPCODE"));	
				if(societyId!=null && !societyId.equalsIgnoreCase("null") && !societyId.equalsIgnoreCase("")){
					obj.put("societyCode", societyId);
				}else{
				obj.put("societyCode", sessionMap.get("sSoctyId"));
				}
				String uploaddetail = "";
				String uploadfileName = "";
				System.out.println("societyId --------------2---------------- "+societyId);
				if(sFileName.contains(",")){
				}else{
					sFileName=sFileName+",";
				}
				filename=sFileName.split(",");
				double totfilesize=0;
				byte imgBytlogo[]=new byte[2048];
				for(int f=0;f<maintexcelfile.size();f++){
					 imgBytlogo=new byte[2048];
					double filesiz=maintexcelfile.get(f).length();
					totfilesize+=filesiz;
					String fileName=filename[f];
					imgBytlogo=commonSnippet.toReadFiletoBytes(maintexcelfile.get(f));
					uploaddetail=commonSnippet.toByteAryToBase64EncodeStr(imgBytlogo);
					uploadfileName=fileName;
				}
				System.out.println("societyId --------------3---------------- "+societyId);
				obj.put("imageDetail",uploaddetail);
				obj.put("fileName",uploadfileName);
				obj.put("docSubFolder",1);
				data.put("data", obj);
				
				String jsonTextFinal = data.toString();	
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				System.out.println("societyId --------------4---------------- "+societyId);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);										
				String finalUrl = getText("socialindia.utility.addmaintenancedocexcelupload");								
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
				}else{
					alert.setCls("danger");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("Maintenance.doc.exlupload.error"));
					}					
					alertList.add(alert);
					return "input";
				}								
			}			
		}catch (Exception e){
			alert.setCls("danger");
			alert.setMsg(getText("Maintenance.doc.exlupload.error"));
			alertList.add(alert);
			return "input";
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
	public Common getCommonObj() {
		return commonObj;
	}
	public void setCommonObj(Common commonObj) {
		this.commonObj = commonObj;
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
	public String getSocietyId() {
		return societyId;
	}
	public void setSocietyId(String societyId) {
		this.societyId = societyId;
	}
	
	
}
