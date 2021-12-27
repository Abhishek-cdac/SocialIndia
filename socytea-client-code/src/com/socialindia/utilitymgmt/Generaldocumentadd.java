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
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.utilitymgmt.persistance.DocumentManageTblVO;

public class Generaldocumentadd extends ActionSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DocumentManageTblVO documentMng=new DocumentManageTblVO();
	private List <File> documentfile = new ArrayList <File> ();
	private String documentfileFileName;
	
	commomServices commonSnippet=new commomServices();
	private Common commonObj = new CommonDao();
	
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	
	public String execute(){
		Log logWrite = null;
		JSONObject obj = null;
		JSONObject data = null;
		Map sessionMap = null;
		try{
			sessionMap = ActionContext.getContext().getSession();
			logWrite = new Log();
			logWrite.logMessage("Step 1 : General Document Upload Called [Start]", "info", Generaldocumentadd.class);
			Commonutility.toWriteConsole("Step 1 : General Document Upload Called [Start]");
			String sFileName = documentfileFileName;			
			String[] filename=null;
			if (sFileName != null) {								
				/*data.put("servicecode", "SI6403");
				data.put("currentloginid", sessionMap.get("USERID"));				
				obj.put("userId", sessionMap.get("USERID"));
				obj.put("usrTyp", sessionMap.get("GROUPCODE"));
				obj.put("docTypId", documentMng.getDocTypId());
				obj.put("docFolder", documentMng.getDocFolder());// public or private
				obj.put("subject", documentMng.getSubject());
				obj.put("descr",documentMng.getDescr());
				obj.put("emailStatus",documentMng.getEmailStatus());				
				JSONArray ja = new JSONArray();
				String docshare = documentMng.getDocShareId();
				String[] documentshar=null;
				if(docshare!=null && docshare.contains(",")){
					documentshar = docshare.split(",");
					for(int k=0;k<documentshar.length;k++){
						ja.put(documentshar[k]);						
					}
				}
				obj.put("docShareId",ja);				
				JSONArray imagearray = new JSONArray();
				JSONArray fileNamearray = new JSONArray();															
				double totfilesize = 0;
				byte imgBytlogo[] = new byte[2048];
				for (int f = 0; f < documentfile.size(); f++) {
					imgBytlogo = new byte[2048];
					double filesiz = documentfile.get(f).length();
					totfilesize += filesiz;
					String fileName = filename[f];					
					
					imgBytlogo = commonSnippet.toReadFiletoBytes(documentfile.get(f));
					imagearray.put(commonSnippet.toByteAryToBase64EncodeStr(imgBytlogo));					
					fileNamearray.put(fileName);
				}
				obj.put("imageDetail",imagearray);
				obj.put("fileName",fileNamearray);				
				obj.put("docSubFolder",2);				
				data.put("data", obj);
				
				String jsonTextFinal = data.toString();								
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);								
				String finalUrl = getText("socialindia.utility.adddocument");				
				String response = commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);				
				String statusCode = json.getString("statuscode");*/				
				if(sFileName.contains(",")){
				}else{
					sFileName=sFileName+",";
				}
				filename = sFileName.split(",");
				// Share to Resident
				JSONArray ja = new JSONArray();
				String docshare = documentMng.getDocShareId();
				String[] documentshar=null;
				if(docshare!=null && docshare.contains(",")){
					documentshar = docshare.split(",");
					for(int k=0;k<documentshar.length;k++){
						ja.put(documentshar[k]);						
					}
				}								
				JSONArray lvrimagearray = null;
				JSONArray lvrfileNamearray = null;
				byte lvrimgBytlogo[] = null;
				String statusCode = null, lvrRspmsg = null;
				for (int f = 0; f < documentfile.size(); f++) {
					obj = new JSONObject();
					data = new JSONObject();
					lvrimagearray = new JSONArray();
					lvrfileNamearray = new JSONArray();
					logWrite.logMessage("Step 2 : General Document Upload Started.", "info", Generaldocumentadd.class);
					Commonutility.toWriteConsole("Step 2 : General Document Upload Started.");				
					data.put("servicecode", "SI6403");
					data.put("currentloginid", sessionMap.get("USERID"));				
					obj.put("userId", sessionMap.get("USERID"));
					obj.put("usrTyp", sessionMap.get("GROUPCODE"));
					obj.put("docTypId", documentMng.getDocTypId());
					obj.put("docFolder", documentMng.getDocFolder());// public or private
					obj.put("subject", documentMng.getSubject());
					obj.put("descr",documentMng.getDescr());
					obj.put("emailStatus",documentMng.getEmailStatus());									
					obj.put("docShareId",ja);										
					String fileName = filename[f];					
				//	lvrimgBytlogo = commonSnippet.toReadFiletoBytes(documentfile.get(f));
					//lvrimagearray.put(commonSnippet.toByteAryToBase64EncodeStr(lvrimgBytlogo));										
					//obj.put("imageDetail",lvrimagearray);					
					if(documentfile.get(f)!=null){
						lvrimagearray.put(documentfile.get(f).getAbsolutePath());						
						lvrfileNamearray.put(fileName);							
					} else {						
					}	
					
					obj.put("imageDetail",lvrimagearray);
					obj.put("fileName",lvrfileNamearray);
					obj.put("docSubFolder",2);														
					data.put("data", obj);
					
					String lvrjsonTextFinal = data.toString();								
					lvrjsonTextFinal = EncDecrypt.encrypt(lvrjsonTextFinal);
					String temp1 = "ivrparams=" + URLEncoder.encode(lvrjsonTextFinal);								
					String finalUrl = getText("socialindia.utility.adddocument");
					logWrite.logMessage("Step 3 : General Document Upload fileName : "+fileName, "info", Generaldocumentadd.class);
					Commonutility.toWriteConsole("Step 3 : General Document Upload fileName : "+fileName);
					String response = commonObj.jsonRequest(finalUrl, temp1);
					JSONObject json = new JSONObject(response);				
					statusCode = json.getString("statuscode");
					lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
					lvrimgBytlogo = null;
					logWrite.logMessage("Step 4 : General Document Upload End Status Code : "+statusCode, "info", Generaldocumentadd.class);
					Commonutility.toWriteConsole("Step 4 : General Document Upload End Status Code : "+statusCode);
					lvrimagearray = null; lvrfileNamearray =  null; obj = null; data = null;
					//Commonutility.toWriteConsole("S.No : "+f + "File name : "+fileName+":  statusCode "+statusCode);					
				}										
				if(statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")){									
					documentMng=null;
					alert.setCls("success");
					if(lvrRspmsg!=null){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("Document.created.success"));
					}					
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
				}else{
					alert.setCls("danger");
					alert.setMsg(getText("Document.created.error"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "input";
				}
				
			}						
		}catch (Exception e){
			Commonutility.toWriteConsole("Exception Found : "+e);
			alert.setCls("danger");
			alert.setMsg(getText("Document.created.error"));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally {
			
		}
		return SUCCESS;
	}

	public DocumentManageTblVO getDocumentMng() {
		return documentMng;
	}

	public void setDocumentMng(DocumentManageTblVO documentMng) {
		this.documentMng = documentMng;
	}

	public List<File> getDocumentfile() {
		return documentfile;
	}

	public void setDocumentfile(List<File> documentfile) {
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
