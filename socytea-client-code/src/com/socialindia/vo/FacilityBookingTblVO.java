package com.socialindia.vo;

import java.util.Date;



public class FacilityBookingTblVO {
	
	private int bookingId;
	private Integer bookingFacId;
	private String title;
	private String place;
	private String description;
	private String startTime;
	private String endTime;
	private String contactNo;
	private int statusFlag;
	private int bookingStatus;
	private Date entryDatetime;
	private Date modifyDatetime;
	private Integer facilityId;
	private Integer entryBy;
	private Integer updatedBy;
	private String bookedBy;
	private String facTitle;
	private String facdesc;
	private String facplace;
	private String commiteecomment;
	
	private String bookedusrid;
	public FacilityBookingTblVO(){}

	
	


	public String getBookedusrid() {
		return bookedusrid;
	}





	public void setBookedusrid(String bookedusrid) {
		this.bookedusrid = bookedusrid;
	}





	public String getCommiteecomment() {
		return commiteecomment;
	}



	public void setCommiteecomment(String commiteecomment) {
		this.commiteecomment = commiteecomment;
	}



	public String getFacplace() {
		return facplace;
	}



	public void setFacplace(String facplace) {
		this.facplace = facplace;
	}



	public String getFacTitle() {
		return facTitle;
	}



	public void setFacTitle(String facTitle) {
		this.facTitle = facTitle;
	}



	public String getFacdesc() {
		return facdesc;
	}



	public void setFacdesc(String facdesc) {
		this.facdesc = facdesc;
	}



	public Integer getBookingFacId() {
		return bookingFacId;
	}



	public void setBookingFacId(Integer bookingFacId) {
		this.bookingFacId = bookingFacId;
	}



	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(int bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public int getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
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

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

	public Integer getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(Integer entryBy) {
		this.entryBy = entryBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

	
	

}
