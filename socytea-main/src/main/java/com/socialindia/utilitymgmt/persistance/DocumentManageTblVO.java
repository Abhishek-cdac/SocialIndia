package com.socialindia.utilitymgmt.persistance;

import java.io.Serializable;
import java.util.Date;

public class DocumentManageTblVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer documentId;
	private Integer userId;
	private Integer usrTyp;
	private Integer docTypId;
	private String docTypName;
	private Integer maintenanceId;
	private int docFolder;
	private Integer docSubFolder;
	private String docDateFolderName;
	private String docFileName;
	private String subject;
	private String descr;
	private int emailStatus;
	private Integer entryBy;
	private Date entryDatetime;
	private Date modifyDatetime;
	private Integer status;
	
	private String docShareId;
	private String imageDetail;
	private String fullFilePath;
	private String mobileImagePath;
	private String downloadImagePath;
	private String emailStatusdet;
	
	public DocumentManageTblVO(){}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUsrTyp() {
		return usrTyp;
	}
	public void setUsrTyp(Integer usrTyp) {
		this.usrTyp = usrTyp;
	}
	public Integer getDocTypId() {
		return docTypId;
	}
	public void setDocTypId(Integer docTypId) {
		this.docTypId = docTypId;
	}
	public Integer getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public int getDocFolder() {
		return docFolder;
	}
	public void setDocFolder(int docFolder) {
		this.docFolder = docFolder;
	}
	public Integer getDocSubFolder() {
		return docSubFolder;
	}
	public void setDocSubFolder(Integer docSubFolder) {
		this.docSubFolder = docSubFolder;
	}
	public String getDocDateFolderName() {
		return docDateFolderName;
	}
	public void setDocDateFolderName(String docDateFolderName) {
		this.docDateFolderName = docDateFolderName;
	}
	public String getDocFileName() {
		return docFileName;
	}
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public int getEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(int emailStatus) {
		this.emailStatus = emailStatus;
	}
	public Integer getEntryBy() {
		return entryBy;
	}
	public void setEntryBy(Integer entryBy) {
		this.entryBy = entryBy;
	}
	public Date getEntryDatetime() {
		return entryDatetime;
	}
	public void setEntryDatetime(Date entryDatetime) {
		this.entryDatetime = entryDatetime;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public String getDocShareId() {
		return docShareId;
	}

	public void setDocShareId(String docShareId) {
		this.docShareId = docShareId;
	}

	public String getImageDetail() {
		return imageDetail;
	}

	public void setImageDetail(String imageDetail) {
		this.imageDetail = imageDetail;
	}

	public Integer getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDocTypName() {
		return docTypName;
	}

	public void setDocTypName(String docTypName) {
		this.docTypName = docTypName;
	}

	public String getFullFilePath() {
		return fullFilePath;
	}

	public void setFullFilePath(String fullFilePath) {
		this.fullFilePath = fullFilePath;
	}

	public String getMobileImagePath() {
		return mobileImagePath;
	}

	public void setMobileImagePath(String mobileImagePath) {
		this.mobileImagePath = mobileImagePath;
	}

	public String getDownloadImagePath() {
		return downloadImagePath;
	}

	public void setDownloadImagePath(String downloadImagePath) {
		this.downloadImagePath = downloadImagePath;
	}

	public String getEmailStatusdet() {
		if(emailStatus==1){
			emailStatusdet="YES";
		}else{
			emailStatusdet="NO";
		}
		return emailStatusdet;
	}

	public void setEmailStatusdet(String emailStatusdet) {
	
		this.emailStatusdet = emailStatusdet;
	}
	
	
	
}
