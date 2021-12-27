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

public class NewMaintnenceDocImageCreate extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentManageTblVO documentMng=new DocumentManageTblVO();
	
	private List <File> maintimagefile = new ArrayList <File> ();
	private String maintimagefileFileName;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	commomServices commonSnippet=new commomServices();
	private Common commonObj = new CommonDao();	
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	
	public String execute(){
		try{
			String sFileName = maintimagefileFileName;
			if(sFileName!=null){
				Map sessionMap = ActionContext.getContext().getSession();
				String[] filename=null;
				data.put("servicecode", "SI6407");
				data.put("currentloginid", sessionMap.get("USERID"));
				
				obj.put("userId", sessionMap.get("USERID"));
				obj.put("usrTyp", sessionMap.get("GROUPCODE"));
				String docshare=documentMng.getDocShareId();
				
				String uploaddetail = "";
				String uploadfileName = "";
				
				if(sFileName.contains(",")){
				}else{
					sFileName=sFileName+",";
				}
				filename=sFileName.split(",");
				double totfilesize=0;
				byte imgBytlogo[]=new byte[2048];
				for(int f=0;f<maintimagefile.size();f++){
					 imgBytlogo=new byte[2048];
					double filesiz=maintimagefile.get(f).length();
					totfilesize+=filesiz;
					String fileName=filename[f];
					imgBytlogo=commonSnippet.toReadFiletoBytes(maintimagefile.get(f));
					uploaddetail=commonSnippet.toByteAryToBase64EncodeStr(imgBytlogo);
					uploadfileName=fileName;
				}
				obj.put("docTypId", 8);
				obj.put("subject",  getText("maintenance.subject"));
				obj.put("emailStatus",documentMng.getEmailStatus()); 
				obj.put("docSubFolder",1);
				obj.put("imageDetail",uploaddetail);
				obj.put("fileName",uploadfileName);								
				JSONArray ja = new JSONArray();
				String[] documentshar=null;
				if(docshare!=null && docshare.contains(",") && docshare.length()>1){
					obj.put("docFolder", 2);
					documentshar=docshare.split(",");
					for(int k=0;k<documentshar.length;k++){
						ja.put(documentshar[k]);
					}
				}else{
					obj.put("docFolder", 1);
				}
				obj.put("docShareId",ja);
				data.put("data", obj);
				String jsonTextFinal = data.toString();				
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);								
				String finalUrl = getText("socialindia.utility.addmaintenancedocimageupload");				
				String response = commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);				
				String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
				String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
				 if(statusCode!= null && statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")){
					 documentMng=null;
					alert.setCls("success");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("Maintenance.img.exlupload.success"));
					}					
					alertList.add(alert);
				}else{
					alert.setCls("danger");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("Maintenance.img.exlupload.error"));
					}									
					alertList.add(alert);
					return "input";
				}							
			}
			
		}catch (Exception e){
			alert.setCls("danger");
			alert.setMsg(getText("Maintenance.img.exlupload.error"));
			alertList.add(alert);
			return "input";
		}
		return SUCCESS;
	}

	public List<File> getMaintimagefile() {
		return maintimagefile;
	}
	public void setMaintimagefile(List<File> maintimagefile) {
		this.maintimagefile = maintimagefile;
	}

	public String getMaintimagefileFileName() {
		return maintimagefileFileName;
	}
	public void setMaintimagefileFileName(String maintimagefileFileName) {
		this.maintimagefileFileName = maintimagefileFileName;
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

	public DocumentManageTblVO getDocumentMng() {
		return documentMng;
	}

	public void setDocumentMng(DocumentManageTblVO documentMng) {
		this.documentMng = documentMng;
	}
	

}
