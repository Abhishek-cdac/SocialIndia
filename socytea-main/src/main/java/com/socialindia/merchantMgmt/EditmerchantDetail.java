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
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.profileUpdate;
import com.socialindia.vo.MerchantStockDetailTblVO;
import com.socialindia.vo.MerchantTblVO;

public class EditmerchantDetail extends ActionSupport{
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
	private File MrchImage;
	private String MrchImageFileName;
	private String[] offertxt;
	private String issuelistbox;
	private String offercheck;
	  
	public String execute(){		   
		//byte imgByt[] = null;
		Log logWrite = null;
		try {
			logWrite = new Log();
			logWrite.logMessage("Step 1 : Modify merchant called [Start]", "info", EditmerchantDetail.class);
			Commonutility.toWriteConsole("Step 1 : Modify merchant called [Start]");
			String compval=offercheck+","+issuelistbox;
            Map sessionMap = ActionContext.getContext().getSession();
			//imgByt = profileUpdate.toReadFiletoBytes(MrchImage);
			data.put("servicecode", "SI6420");
			data.put("userId", sessionMap.get("USERID"));
			data.put("usrTyp", sessionMap.get("GROUPCODE"));
			
			obj.put("mrchntId", merchantobj.getMrchntId());
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
			/*obj.put("issueid", offercheck);
			obj.put("issuetxt", offertxt);*/
			int count=0;
			String[] functiontxt = offertxt[0].split("!_!");
			String[] functiontxtid = compval.split(",");
			JSONArray functiontext = new JSONArray();
			for (int i = 0; i < functiontxt.length; i++) {
				functiontext.put(Commonutility.toCheckNullEmpty(functiontxt[i]));
			}
			functiontxt = null;
			obj.put("issueid", functiontext);
			
			JSONArray functiontxttext = new JSONArray();
			for (int i = 0; i < functiontxtid.length; i++) {
				functiontxttext.put(functiontxtid[i]);
				count=i;
			}
			obj.put("issuetxt", functiontxttext);
			obj.put("count", count);
			functiontxtid = null;
			obj.put("merchDescription", merchantobj.getMerchDescription());
			obj.put("website", merchantobj.getWebsite());
			obj.put("shopName", merchantobj.getShopName());
			obj.put("imagename", MrchImageFileName);		
			//obj.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));// encrypted string on image
			if(MrchImage!=null) {
				obj.put("mrchimgsrchpth", MrchImage.getAbsolutePath());
			} else {
				obj.put("mrchimgsrchpth", "");
			}
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
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.mrchantMng.editMerchant");
			logWrite.logMessage("Step 2 : Modify merchant service called URL : "+finalUrl, "info", EditmerchantDetail.class);
			Commonutility.toWriteConsole("Step 2 : Modify merchant service called URL : "+finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = json.getString("statuscode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			if(statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")){
				logWrite.logMessage("Step 3 : Modify merchant response statusCode : "+statusCode, "info", EditmerchantDetail.class);
				Commonutility.toWriteConsole("Step 3 : Modify merchant response statusCode : "+statusCode);
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Merchant.update.success"));
				}				
				alertList.add(alert);
			}else{
				logWrite.logMessage("Step 3 : Modify merchant response statusCode : "+statusCode +", Merchant Not Modified.", "info", EditmerchantDetail.class);
				Commonutility.toWriteConsole("Step 3 : Modify merchant response statusCode : "+statusCode+", Merchant Not Modified.");
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Merchant.update.error"));
				}					
				alertList.add(alert);
				return "input";
			}
			
			
		}catch (Exception e){
			logWrite.logMessage("Step -1 : Add merchant - Excepton : "+e+" ,  Merchant Not Modified.", "error", EditmerchantDetail.class);
			Commonutility.toWriteConsole("Step -1 : Add merchant - Excepton : "+e+", Merchant Not Modified.");
			alert.setCls("danger");
			alert.setMsg(getText("Merchant.update.error"));
			alertList.add(alert);
			e.printStackTrace();
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

	public String[] getOffertxt() {
		return offertxt;
	}
	public void setOffertxt(String[] offertxt) {
		this.offertxt = offertxt;
	}
	public String getIssuelistbox() {
		return issuelistbox;
	}
	public void setIssuelistbox(String issuelistbox) {
		this.issuelistbox = issuelistbox;
	}
	public String getOffercheck() {
		return offercheck;
	}
	public void setOffercheck(String offercheck) {
		this.offercheck = offercheck;
	}

	
	

}