package com.socialindia.utilitymgmt;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport{
	private InputStream fileInputStream;
	String fileName;
	 private String filem;
	 private AlertVo alert = new AlertVo();
		private List<AlertVo> alertList = new ArrayList<AlertVo>();
	

	public String execute() throws Exception {
		try{
			System.out.println("fileName------------------"+fileName);
			File fileToDownload = new File(fileName);
			fileInputStream = new FileInputStream(new File(fileName));
			filem = fileToDownload.getName();
			
			}catch(Exception e){
				e.printStackTrace();
				alert.setCls("danger");
				alert.setMsg(getText("Error.file.not.found"));
				alertList.add(alert);
			 return "input";
		}
	    return SUCCESS;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilem() {
		return filem;
	}

	public void setFilem(String filem) {
		this.filem = filem;
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
