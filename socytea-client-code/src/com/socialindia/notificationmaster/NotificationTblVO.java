package com.socialindia.notificationmaster;

import java.io.Serializable;
import java.util.Date;



public class NotificationTblVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String notificationId;
	private String uniqueId;
	private String readStatus;
	private int statusFlag;
	private String descr;
	private Date entryDatetime;
	private Date modifyDatetime;
	private String userId;
	private String entryBy;
	private String tblrefFlag;
	private String tblrefID;
	private String NotifyentryDatetime;
	private String viewactionurl;
	
	public NotificationTblVO(){}
	

	public NotificationTblVO(String notificationid2, String notificationdesc,
			String notificationstatus, String ntfytblrefname, String ntfytblrefid,
			String ntfydatetime,String viewactionurl) {
		this.notificationId =notificationid2;
		this.descr = notificationdesc;
		this.readStatus=notificationstatus;
		this.tblrefFlag =ntfytblrefname;
		this.tblrefID = ntfytblrefid;
		this.NotifyentryDatetime=ntfydatetime;
		this.viewactionurl=viewactionurl;
	}

	public String getNotifyentryDatetime() {
		return NotifyentryDatetime;
	}


	public void setNotifyentryDatetime(String notifyentryDatetime) {
		NotifyentryDatetime = notifyentryDatetime;
	}


	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public int getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	public String getTblrefFlag() {
		return tblrefFlag;
	}

	public void setTblrefFlag(String tblrefFlag) {
		this.tblrefFlag = tblrefFlag;
	}

	public String getTblrefID() {
		return tblrefID;
	}

	public void setTblrefID(String tblrefID) {
		this.tblrefID = tblrefID;
	}


	public String getViewactionurl() {
		return viewactionurl;
	}


	public void setViewactionurl(String viewactionurl) {
		this.viewactionurl = viewactionurl;
	}

	
	
	

}
