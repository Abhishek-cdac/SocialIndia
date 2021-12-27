package com.socialindia.utilitymgmt;

import java.io.Serializable;
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
import com.socialindia.login.EncDecrypt;
import com.socialindia.utilitymgmt.persistance.DocumentManageTblVO;
import com.socialindia.vo.MaintenanceFeeTblVO;

public class AddnewShareFormmaintenanceNames   extends ActionSupport implements Serializable{

	private MaintenanceFeeTblVO maintanencefee=new MaintenanceFeeTblVO();
	private DocumentManageTblVO documentMng=new DocumentManageTblVO();
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	commomServices commonSnippet=new commomServices();
	
	public String execute(){
		try{
			Map sessionMap = ActionContext.getContext().getSession();
			data.put("servicecode", "SI6414");
			data.put("currentloginid", sessionMap.get("USERID"));
			
			obj.put("userId", sessionMap.get("USERID"));
			obj.put("userId", sessionMap.get("USERID"));
			obj.put("usrTyp", sessionMap.get("GROUPCODE"));
			obj.put("documentId",documentMng.getDocumentId());
			JSONArray ja = new JSONArray();
			String docshare=documentMng.getDocShareId();
			System.out.println("docshare----------------"+docshare);
			String[] documentshar=null;
			if(docshare!=null && docshare.contains(",") && docshare.length()>1){
				documentshar=docshare.split(",");
				for(int k=0;k<documentshar.length;k++){
					System.out.println("documentshar[k]-------------"+documentshar[k]);
					ja.put(documentshar[k]);
				}
			}
			obj.put("docShareId",ja);
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			System.out.println("===jsonTextFinal=data========"+jsonTextFinal);
			
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			System.out.println("=====jsonText=====" + jsonTextFinal);
			
			
			String finalUrl = getText("socialindia.utility.addShareFormmaintenanceNames");
			System.out.println("=======finalUrl====" + finalUrl);
			
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("=========response===" + response);
			JSONObject json = new JSONObject(response);
			
			String statusCode = json.getString("statuscode");
			System.out.println("statusCode----------"+statusCode);
		 if(statusCode.equalsIgnoreCase("0")){
				alert.setCls("success");
				alert.setMsg(getText("Maintenance.Document.Shared.success"));
				alertList.add(alert);
			}else{
				alert.setCls("danger");
				alert.setMsg(getText("Maintenance.Document.Shared.error"));
				alertList.add(alert);
				return "input";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public MaintenanceFeeTblVO getMaintanencefee() {
		return maintanencefee;
	}

	public void setMaintanencefee(MaintenanceFeeTblVO maintanencefee) {
		this.maintanencefee = maintanencefee;
	}

	public DocumentManageTblVO getDocumentMng() {
		return documentMng;
	}

	public void setDocumentMng(DocumentManageTblVO documentMng) {
		this.documentMng = documentMng;
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