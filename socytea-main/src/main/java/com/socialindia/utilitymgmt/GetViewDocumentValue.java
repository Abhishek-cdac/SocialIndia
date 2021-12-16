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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.utilitymgmt.persistance.DocumentManageTblVO;
import com.socialindia.vo.MaintenanceFeeTblVO;

public class GetViewDocumentValue  extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int docId;
	private String sdocShareUsrId;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	DocumentManageTblVO documentmng=new DocumentManageTblVO();
	MaintenanceFeeTblVO maintanencefee=new MaintenanceFeeTblVO();
	private String total;
	public String execute() {
		try{
			
			Map sessionMap = ActionContext.getContext().getSession();
			data.put("servicecode", "SI6412");
			data.put("currentloginid", sessionMap.get("USERID"));
			
			obj.put("userId", sessionMap.get("USERID"));
			obj.put("usrTyp", sessionMap.get("GROUPCODE"));
			obj.put("documentId", docId);
			obj.put("sdocShareUsrId",sdocShareUsrId);
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.utility.getViewDocumentValues");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject dataobj=new JSONObject();
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json,"statuscode");
			
			dataobj=(JSONObject) Commonutility.toHasChkJsonRtnValObj(json,"data");
			Integer documentId=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmdocumentId");
			Integer docTypId=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmdocTypId");
			String docTypName=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmdocTypName");
			Integer dmdocFolder=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmdocFolder");
			Integer docSubFolder=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmdocSubFolder");
			String dmdocDateFolder=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmdocDateFolder");
			String dmdocFileName=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmdocFileName");
			String subject=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmsubject");
			String descr=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmdescr");
			int emailStatus=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmemailStatus");
			String extImagePath=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"extImagePath");
			String mobileImagePath=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mobileImagePath");
			String downloadImagePath=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"downloadImagePath");			
			documentmng.setDocumentId(documentId);
			documentmng.setDocTypId(docTypId);
			documentmng.setDocTypName(docTypName);
			documentmng.setDocFolder(dmdocFolder);
			documentmng.setDocSubFolder(docSubFolder);
			documentmng.setDocDateFolderName(dmdocDateFolder);
			documentmng.setDocFileName(dmdocFileName);
			documentmng.setSubject(subject);
			documentmng.setDescr(descr);
			documentmng.setEmailStatus(emailStatus);
			documentmng.setFullFilePath(extImagePath);
			documentmng.setMobileImagePath(mobileImagePath);
			documentmng.setDownloadImagePath(downloadImagePath);
			
			if(	documentmng.getDocSubFolder()==1){
				Integer maintenanceId=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"dmmaintenanceId");
				String serviceCharge=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtserviceCharge");
				String billDate=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtbillDate");
				if(billDate==null){
					billDate="";
				}
				String municipalTax=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtmunicipalTax");
				String penalty=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtpenalty");
				String waterCharge=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtwaterCharge");
				String nonOccupancyCharge=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtnonOccupancyCharge");
				String culturalCharge=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtculturalCharge");
				String sinkingFund=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtsinkingFund");
				String repairFund=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtrepairFund");
				String insuranceCharges=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtinsuranceCharges");
				String playZoneCharge=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtplayZoneCharge");
				String majorRepairFund=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtmajorRepairFund");
				String interest=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtinterest");
				total=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"total");
				Integer userId=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"mtuserId");
				Integer dataUploadFrom=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"dataUploadFrom");
				
				
				documentmng.setMaintenanceId(maintenanceId);
				maintanencefee.setMaintenanceId(maintenanceId);
				maintanencefee.setServiceCharge(serviceCharge);
				maintanencefee.setBillDate(billDate);
				maintanencefee.setMunicipalTax(municipalTax);
				maintanencefee.setPenalty(penalty);
				maintanencefee.setWaterCharge(waterCharge);
				maintanencefee.setNonOccupancyCharge(nonOccupancyCharge);
				maintanencefee.setCulturalCharge(culturalCharge);
				maintanencefee.setSinkingFund(sinkingFund);
				maintanencefee.setRepairFund(repairFund);
				maintanencefee.setInsuranceCharges(insuranceCharges);
				maintanencefee.setPlayZoneCharge(playZoneCharge);
				maintanencefee.setMajorRepairFund(majorRepairFund);
				maintanencefee.setInterest(interest);
				maintanencefee.setUserId(userId);
				maintanencefee.setDataUploadFrom(dataUploadFrom);
			}
			
			
			 if(statusCode!=null && (statusCode.equalsIgnoreCase("01") || statusCode.equalsIgnoreCase("02") || statusCode.equalsIgnoreCase("error"))){
				alert.setCls("danger");
				alert.setMsg(getText("Document.edit.data.error"));
				alertList.add(alert);
				return "input";
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
	return SUCCESS;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
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
	public DocumentManageTblVO getDocumentmng() {
		return documentmng;
	}
	public void setDocumentmng(DocumentManageTblVO documentmng) {
		this.documentmng = documentmng;
	}
	public MaintenanceFeeTblVO getMaintanencefee() {
		return maintanencefee;
	}
	public void setMaintanencefee(MaintenanceFeeTblVO maintanencefee) {
		this.maintanencefee = maintanencefee;
	}
	public String getSdocShareUsrId() {
		return sdocShareUsrId;
	}
	public void setSdocShareUsrId(String sdocShareUsrId) {
		this.sdocShareUsrId = sdocShareUsrId;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	
}
