package com.socialindia.merchantMgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.profileUpdate;
import com.socialindia.vo.MerchantStockDetailTblVO;
import com.socialindia.vo.MerchantTblVO;

public class AddNewMerchant extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MerchantTblVO merchantobj=new MerchantTblVO();
	MerchantStockDetailTblVO mrchStockobj=new MerchantStockDetailTblVO();
	
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
	private String issuelistbox;
	private File MrchImage;
	private String MrchImageFileName;
	private String MrchImageContentType;	
	private String offertxt;
	private String offercheck;

	public String execute() {		
		byte imgByt[] = null;
		Log logWrite = null;
		JSONObject lvrRspjsonobj = null;
		Map sessionMap = ActionContext.getContext().getSession();
		try{	
			logWrite = new Log();
			logWrite.logMessage("Step 1 : Add merchant called [Start]", "info", AddNewMerchant.class);
			Commonutility.toWriteConsole("Step 1 : Add merchant called [Start]");			
			//imgByt = profileUpdate.toReadFiletoBytes(MrchImage);			
			data.put("servicecode", "SI6417");
			data.put("userId", sessionMap.get("USERID"));
			data.put("usrTyp", sessionMap.get("GROUPCODE"));		
			obj.put("merchantCategoryId", merchantobj.getMerchantCategoryId());
			obj.put("mrchntName", merchantobj.getMrchntName());
			obj.put("mrchntEmail", merchantobj.getMrchntEmail());
			obj.put("mrchntIsdCode", merchantobj.getMrchntIsdCode());
			obj.put("mrchntPhNo", merchantobj.getMrchntPhNo());
			obj.put("keyForSearch", merchantobj.getKeyForSearch());
			obj.put("storeLocation", merchantobj.getStoreLocation());
			obj.put("storeOpentime", merchantobj.getStoreOpentime());
			obj.put("storeClosetime", merchantobj.getStoreClosetime());
			obj.put("mrchntAdd1", merchantobj.getMrchntAdd1());
			obj.put("mrchntAdd2", merchantobj.getMrchntAdd2());
			obj.put("countryId", merchantobj.getCountryId());
			obj.put("stateId", merchantobj.getStateId());
			obj.put("cityId", merchantobj.getCityId());
			obj.put("pstlId", merchantobj.getPstlcode());
			obj.put("merchDescription", merchantobj.getMerchDescription());
			obj.put("website", merchantobj.getWebsite());
			obj.put("shopName", merchantobj.getShopName());
			//obj.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
			if (MrchImage!=null) {
				obj.put("mrchimgsrchpth", MrchImage.getAbsolutePath());										
			} else {
				obj.put("mrchimgsrchpth", "");	
			}
			obj.put("imagename", MrchImageFileName);
			if( merchantobj.getMerchantCategoryId()==1){
				obj.put("typeName", typeNameArray);
				obj.put("quantity", quantityArray);
			}else if( merchantobj.getMerchantCategoryId()==2){
				obj.put("typeName", typeNameArray);
				obj.put("power", powerArray);
				obj.put("companyName", companyNameArray);
				obj.put("quantity", quantityArray);
			}else if( merchantobj.getMerchantCategoryId()==3){
				obj.put("typeName", typeNameArray);
				obj.put("quantity", quantityArray);
			}else if( merchantobj.getMerchantCategoryId()==4){
				obj.put("cuisines", mrchStockobj.getCuisines());				
				obj.put("typeName",restaruntFeatureDetail);
			} else {
				
			}	
			if(issuelistbox!=null && offercheck!=null){
			String issuedata=issuelistbox+","+offercheck;
			obj.put("offerisstxt", issuedata);
			} else{
			obj.put("offerisstxt", "");
			}
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.mrchantMng.createMerchant");
			logWrite.logMessage("Step 2 : Add merchant service called URL : "+finalUrl, "info", AddNewMerchant.class);
			Commonutility.toWriteConsole("Step 2 : Add merchant service called URL : "+finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRspjsonobj = new JSONObject(response);
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(lvrRspjsonobj, "statuscode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(lvrRspjsonobj, "message");
			if(statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")){
				logWrite.logMessage("Step 3 : Add merchant response statusCode : "+statusCode, "info", AddNewMerchant.class);
				Commonutility.toWriteConsole("Step 3 : Add merchant response statusCode : "+statusCode);
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Merchant.added.success"));
				}				
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
			}else{
				logWrite.logMessage("Step 3 : Add merchant response statusCode : "+statusCode +", Merchant Not Created.", "info", AddNewMerchant.class);
				Commonutility.toWriteConsole("Step 3 : Add merchant response statusCode : "+statusCode+", Merchant Not Created.");
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Merchant.added.error"));
				}					
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}	
		}catch (Exception e){
			logWrite.logMessage("Step -1 : Add merchant - Excepton : "+e+" ,  Merchant Not Created.", "info", AddNewMerchant.class);
			Commonutility.toWriteConsole("Step -1 : Add merchant - Excepton : "+e+", Merchant Not Created.");
			alert.setCls("danger");
			alert.setMsg(getText("Merchant.added.error"));				
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally{
			logWrite = null; lvrRspjsonobj = null;
		}
		return SUCCESS;
	}
	public MerchantTblVO getMerchantobj() {
		return merchantobj;
	}
	public void setMerchantobj(MerchantTblVO merchantobj) {
		this.merchantobj = merchantobj;
	}
	public MerchantStockDetailTblVO getMrchStockobj() {
		return mrchStockobj;
	}
	public void setMrchStockobj(MerchantStockDetailTblVO mrchStockobj) {
		this.mrchStockobj = mrchStockobj;
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
	public File getMrchImage() {
		return MrchImage;
	}
	public void setMrchImage(File mrchImage) {
		MrchImage = mrchImage;
	}
	public String getMrchImageFileName() {
		return MrchImageFileName;
	}
	public void setMrchImageFileName(String mrchImageFileName) {
		MrchImageFileName = mrchImageFileName;
	}
	public String getMrchImageContentType() {
		return MrchImageContentType;
	}
	public void setMrchImageContentType(String mrchImageContentType) {
		MrchImageContentType = mrchImageContentType;
	}
	public String getOffertxt() {
		return offertxt;
	}
	public void setOffertxt(String offertxt) {
		this.offertxt = offertxt;
	}
	public String getOffercheck() {
		return offercheck;
	}
	public void setOffercheck(String offercheck) {
		this.offercheck = offercheck;
	}
	public String getIssuelistbox() {
		return issuelistbox;
	}
	public void setIssuelistbox(String issuelistbox) {
		this.issuelistbox = issuelistbox;
	}
	
	

}
