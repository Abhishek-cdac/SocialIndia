package com.socialindia.utilitymgmt;

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

public class DocumentModification extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	commomServices commonSnippet=new commomServices();
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Integer sdocumentId;
	private Integer sdocSubFolder;
	private String docShareUsrId;
	public String execute(){
		try{
			Map sessionMap = ActionContext.getContext().getSession();
			System.out.println("======DocumentModification USERID====" + sessionMap.get("USERID"));
			data.put("servicecode", "SI6405");
			data.put("currentloginid", sessionMap.get("USERID"));
			
			obj.put("userId", sessionMap.get("USERID"));
			obj.put("usrTyp", sessionMap.get("GROUPCODE"));
			obj.put("documentId", sdocumentId);
			obj.put("docSubFolder", sdocSubFolder);
			System.out.println("docShareUsrId::::::::::::"+docShareUsrId);
			obj.put("docShareUsrId", Commonutility.toCheckNullEmpty(docShareUsrId));
			
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.utility.documentDelete");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = json.getString("statuscode");
			System.out.println("statusCode----------"+statusCode);
			 if(statusCode.equalsIgnoreCase("0")){
				alert.setCls("success");
				alert.setMsg(getText("Document.deleted.success"));
				alertList.add(alert);
			}else{
				alert.setCls("danger");
				alert.setMsg(getText("Document.delete.error"));
				alertList.add(alert);
				return "input";
			}
			
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	public String getDocShareUsrId() {
		return docShareUsrId;
	}

	public void setDocShareUsrId(String docShareUsrId) {
		this.docShareUsrId = docShareUsrId;
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
	public Integer getSdocumentId() {
		return sdocumentId;
	}
	public void setSdocumentId(Integer sdocumentId) {
		this.sdocumentId = sdocumentId;
	}
	public Integer getSdocSubFolder() {
		return sdocSubFolder;
	}
	public void setSdocSubFolder(Integer sdocSubFolder) {
		this.sdocSubFolder = sdocSubFolder;
	}
	
	

}
