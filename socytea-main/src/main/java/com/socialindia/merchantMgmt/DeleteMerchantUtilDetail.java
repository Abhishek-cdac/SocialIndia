package com.socialindia.merchantMgmt;

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
import com.socialindia.vo.MerchantTblVO;
import com.socialindia.vo.MerchantUtilitiImageTblVO;
import com.socialindia.vo.MerchantUtilitiTblVO;

public class DeleteMerchantUtilDetail    extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MerchantTblVO merchantobj=new MerchantTblVO();
	MerchantUtilitiTblVO merchantUtilobj=new MerchantUtilitiTblVO();
	MerchantUtilitiImageTblVO merchantutilimageObj=new MerchantUtilitiImageTblVO();
	private List <File> offerfile = new ArrayList <File> ();
	private String offerfileFileName;
	commomServices commonSnippet=new commomServices();
	private String isdeleteImage;
	
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private String restaruntFeatureDetail;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private String typeNameArray;
	private String quantityArray;
	private String powerArray;
	private String companyNameArray;

	public String execute() {	
		try{			
			Map sessionMap = ActionContext.getContext().getSession();
			data.put("servicecode", "SI6425");
			data.put("userId", sessionMap.get("USERID"));
			data.put("usrTyp", sessionMap.get("GROUPCODE"));
			Integer merchantUtilId= merchantUtilobj.getMerchantUtilId();			
			obj.put("mrchntId", merchantobj.getMrchntId());
			obj.put("merchantUtilId", merchantUtilId);
			obj.put("isdeleteImage", "yes");
			
		if( merchantobj.getMrchntId()!=null){			
			data.put("data", obj);			
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			
			String finalUrl = getText("socialindia.mrchantMng.deleteNewMerchantOffer");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			if(statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")){
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Merchant.offerdeleted.success"));
				}				
				alertList.add(alert);
			}else{
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Merchant.offerdeleted.error"));
				}
				
				alertList.add(alert);
				return "input";
			}
		}else{
			alert.setCls("danger");
			alert.setMsg(getText("Merchant.offerdeleted.error"));
			alertList.add(alert);
			return "input";
		}
			
		}catch (Exception e){
			alert.setCls("danger");
			alert.setMsg(getText("Merchant.offerdeleted.error"));
			alertList.add(alert);
			e.printStackTrace();
			return "input";
		}
		return SUCCESS;
	}
	public MerchantTblVO getMerchantobj() {
		return merchantobj;
	}
	public void setMerchantobj(MerchantTblVO merchantobj) {
		this.merchantobj = merchantobj;
	}
	public String getRestaruntFeatureDetail() {
		return restaruntFeatureDetail;
	}
	public void setRestaruntFeatureDetail(String restaruntFeatureDetail) {
		this.restaruntFeatureDetail = restaruntFeatureDetail;
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
	public String getTypeNameArray() {
		return typeNameArray;
	}
	public void setTypeNameArray(String typeNameArray) {
		this.typeNameArray = typeNameArray;
	}
	public String getQuantityArray() {
		return quantityArray;
	}
	public void setQuantityArray(String quantityArray) {
		this.quantityArray = quantityArray;
	}
	public String getPowerArray() {
		return powerArray;
	}
	public void setPowerArray(String powerArray) {
		this.powerArray = powerArray;
	}
	public String getCompanyNameArray() {
		return companyNameArray;
	}
	public void setCompanyNameArray(String companyNameArray) {
		this.companyNameArray = companyNameArray;
	}

	public MerchantUtilitiTblVO getMerchantUtilobj() {
		return merchantUtilobj;
	}
	public void setMerchantUtilobj(MerchantUtilitiTblVO merchantUtilobj) {
		this.merchantUtilobj = merchantUtilobj;
	}
	public MerchantUtilitiImageTblVO getMerchantutilimageObj() {
		return merchantutilimageObj;
	}
	public void setMerchantutilimageObj(
			MerchantUtilitiImageTblVO merchantutilimageObj) {
		this.merchantutilimageObj = merchantutilimageObj;
	}
	public List<File> getOfferfile() {
		return offerfile;
	}
	public void setOfferfile(List<File> offerfile) {
		this.offerfile = offerfile;
	}
	public String getOfferfileFileName() {
		return offerfileFileName;
	}
	public void setOfferfileFileName(String offerfileFileName) {
		this.offerfileFileName = offerfileFileName;
	}
	public String getIsdeleteImage() {
		return isdeleteImage;
	}
	public void setIsdeleteImage(String isdeleteImage) {
		this.isdeleteImage = isdeleteImage;
	}
	

}
