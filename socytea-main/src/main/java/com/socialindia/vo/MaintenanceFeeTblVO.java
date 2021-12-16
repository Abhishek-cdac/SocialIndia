package com.socialindia.vo;

import java.io.Serializable;
import java.util.Date;

public class MaintenanceFeeTblVO implements Serializable{
	
	private String serviceCharge;
	private String billDate;
	private String municipalTax;
	private String penalty;
	private String waterCharge;
	private String nonOccupancyCharge;
	private String culturalCharge;
	private String sinkingFund;
	private String repairFund;
	private String insuranceCharges;
	private String playZoneCharge;
	private String majorRepairFund;
	private String uploadFileName;
	private String interest;
	private Integer userId;
	private String docShareId;
	private Integer maintenanceId;
	private Integer dataUploadFrom;
	
	public MaintenanceFeeTblVO(){}

	public String getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getMunicipalTax() {
		return municipalTax;
	}

	public void setMunicipalTax(String municipalTax) {
		this.municipalTax = municipalTax;
	}

	public String getPenalty() {
		return penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}

	public String getWaterCharge() {
		return waterCharge;
	}

	public void setWaterCharge(String waterCharge) {
		this.waterCharge = waterCharge;
	}

	public String getNonOccupancyCharge() {
		return nonOccupancyCharge;
	}

	public void setNonOccupancyCharge(String nonOccupancyCharge) {
		this.nonOccupancyCharge = nonOccupancyCharge;
	}

	public String getCulturalCharge() {
		return culturalCharge;
	}

	public void setCulturalCharge(String culturalCharge) {
		this.culturalCharge = culturalCharge;
	}

	public String getSinkingFund() {
		return sinkingFund;
	}

	public void setSinkingFund(String sinkingFund) {
		this.sinkingFund = sinkingFund;
	}

	public String getRepairFund() {
		return repairFund;
	}

	public void setRepairFund(String repairFund) {
		this.repairFund = repairFund;
	}

	public String getInsuranceCharges() {
		return insuranceCharges;
	}

	public void setInsuranceCharges(String insuranceCharges) {
		this.insuranceCharges = insuranceCharges;
	}

	public String getPlayZoneCharge() {
		return playZoneCharge;
	}

	public void setPlayZoneCharge(String playZoneCharge) {
		this.playZoneCharge = playZoneCharge;
	}

	public String getMajorRepairFund() {
		return majorRepairFund;
	}

	public void setMajorRepairFund(String majorRepairFund) {
		this.majorRepairFund = majorRepairFund;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDocShareId() {
		return docShareId;
	}

	public void setDocShareId(String docShareId) {
		this.docShareId = docShareId;
	}

	public Integer getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public Integer getDataUploadFrom() {
		return dataUploadFrom;
	}
	public void setDataUploadFrom(Integer dataUploadFrom) {
		this.dataUploadFrom = dataUploadFrom;
	}

}
