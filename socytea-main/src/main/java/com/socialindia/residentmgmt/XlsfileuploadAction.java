package com.socialindia.residentmgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.profileUpdate;

public class XlsfileuploadAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//byte fileByt[] = new byte[1024];
	private File datafile;
	private String datafileFileName;
	private Common commonObj = new CommonDao();

	public String execute() {
		try {
			// boolean isMultipart =
			// ServletFileUpload.isMultipartContent(request);
			// if (isMultipart) {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
	        JSONObject dataJson=new JSONObject();
			Map sessionMap = ActionContext.getContext().getSession();
			//fileByt = profileUpdate.toReadFiletoBytes(datafile);
			//dataJson.put("fileencdata",profileUpdate.toByteAryToBase64EncodeStr(fileByt));
			if (datafile!=null) {
				dataJson.put("filesrchpath",datafile.getAbsolutePath());
			} else {
				dataJson.put("filesrchpath","");
			}
			dataJson.put("filename", datafileFileName);
			dataJson.put("entryby", String.valueOf(sessionMap.get("USERID")));
			dataJson.put("status", String.valueOf("1"));
			dataJson.put("currentloginid",String.valueOf(sessionMap.get("USERID")));
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI7005");
			finaljj.put("servicefor", "7");
			finaljj.put("data", dataJson);
			String jsonTextFinal = finaljj.toString().trim();
			Commonutility.toWriteConsole("request::  "+jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.xlsfile.uploadaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("xlsuploadresponse:: "+response);
			
			/*String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			JSONObject json_data = json.getJSONObject("data");*/
		} catch (Exception e) {			
			Commonutility.toWriteConsole("exception === fileupload- "+e);
		}			
		return SUCCESS;
	}
	
	public File getDatafile() {
		return datafile;
	}
	public void setDatafile(File datafile) {
		this.datafile = datafile;
	}
	public String getDatafileFileName() {
		return datafileFileName;
	}
	public void setDatafileFileName(String datafileFileName) {
		this.datafileFileName = datafileFileName;
	}
	
	
}