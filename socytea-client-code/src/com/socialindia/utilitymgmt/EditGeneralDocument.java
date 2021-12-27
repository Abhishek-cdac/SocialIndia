package com.socialindia.utilitymgmt;

import java.io.File;
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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.utilitymgmt.persistance.DocumentManageTblVO;

public class EditGeneralDocument  extends ActionSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DocumentManageTblVO documentMng=new DocumentManageTblVO();
	private File documentfile;
	private String documentfileFileName;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	commomServices commonSnippet=new commomServices();
	private Common commonObj = new CommonDao();
	
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	
	public String execute(){
		try{
			String sFileName = documentfileFileName;					
				Map sessionMap = ActionContext.getContext().getSession();								
				data.put("servicecode", "SI6409");
				data.put("currentloginid", sessionMap.get("USERID"));				
				obj.put("userId", sessionMap.get("USERID"));
				obj.put("usrTyp", sessionMap.get("GROUPCODE"));
				obj.put("documentId", documentMng.getDocumentId());
				obj.put("docTypId", documentMng.getDocTypId());
				obj.put("docFolder", documentMng.getDocFolder());
				obj.put("subject", documentMng.getSubject());
				obj.put("descr",documentMng.getDescr());
				obj.put("emailStatus",documentMng.getEmailStatus());				
				JSONArray ja = new JSONArray();
				String docshare=documentMng.getDocShareId();
				String[] documentshar=null;
				if(docshare!=null && docshare.contains(",")){
					documentshar=docshare.split(",");
					for(int k=0;k<documentshar.length;k++){						
						ja.put(documentshar[k]);
						
					}
				}
				obj.put("docShareId",ja);
				String imagearray ="";
				String fileNamearray = "";							
				if(sFileName!=null){
					double totfilesize=0;
					byte imgBytlogo[]=new byte[2048];
					 imgBytlogo=new byte[2048];
						double filesiz=documentfile.length();						
						String fileName=sFileName;
						imgBytlogo=commonSnippet.toReadFiletoBytes(documentfile);
						imagearray=commonSnippet.toByteAryToBase64EncodeStr(imgBytlogo);
						fileNamearray=fileName;
				}else{
					sFileName="";
				}
				obj.put("imageDetail",imagearray);
				obj.put("fileName",fileNamearray);
				obj.put("docSubFolder",2);				
				data.put("data", obj);
				String jsonTextFinal = data.toString();				
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.utility.editgeneraldocument");				
				String response = commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);				
				String statusCode = json.getString("statuscode");
				String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
				if(statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")){
					documentMng=null;
					alert.setCls("success");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("Maintenance.Document.update.success"));
					}					
					alertList.add(alert);
				}else{
					alert.setCls("danger");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("Maintenance.Document.update.error"));
					}
					
					alertList.add(alert);
					return "input";
				}
				
		}catch (Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public DocumentManageTblVO getDocumentMng() {
		return documentMng;
	}

	public void setDocumentMng(DocumentManageTblVO documentMng) {
		this.documentMng = documentMng;
	}

	public File getDocumentfile() {
		return documentfile;
	}

	public void setDocumentfile(File documentfile) {
		this.documentfile = documentfile;
	}

	public String getDocumentfileFileName() {
		return documentfileFileName;
	}

	public void setDocumentfileFileName(String documentfileFileName) {
		this.documentfileFileName = documentfileFileName;
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