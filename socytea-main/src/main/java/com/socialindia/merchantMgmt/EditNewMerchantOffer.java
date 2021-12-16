package com.socialindia.merchantMgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.vo.MerchantTblVO;
import com.socialindia.vo.MerchantUtilitiImageTblVO;
import com.socialindia.vo.MerchantUtilitiTblVO;

public class EditNewMerchantOffer   extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MerchantTblVO merchantobj = new MerchantTblVO();
	MerchantUtilitiTblVO merchantUtilobj = new MerchantUtilitiTblVO();
	MerchantUtilitiImageTblVO merchantutilimageObj = new MerchantUtilitiImageTblVO();
	private List<File> offerfile = new ArrayList<File>();
	private String offerfileFileName;
	commomServices commonSnippet = new commomServices();
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
	  
	  
	public String execute(){
		String[] filename = null;
		Log logWrite = null;
		String supportfile = getText("Text.image.extensions");
		String lvrUnsprtfillst = "";
		boolean lvrUnsfileflag= false;
		try {
			logWrite = new Log();
			String sFileName = offerfileFileName;			
			logWrite.logMessage("Step 1 : Offer modify called [Start] ", "info", EditNewMerchantOffer.class);
			Commonutility.toWriteConsole("Step 1 : Offer modify called [Start] ");
			Map sessionMap = ActionContext.getContext().getSession();									
			data.put("servicecode", "SI6424");
			data.put("userId", sessionMap.get("USERID"));
			data.put("usrTyp", sessionMap.get("GROUPCODE"));
			Integer merchantUtilId = merchantUtilobj.getMerchantUtilId();			
			obj.put("mrchntId", merchantobj.getMrchntId());
			obj.put("merchantUtilId", merchantUtilId);
			obj.put("broucherName", merchantUtilobj.getBroucherName());
			obj.put("offerName", merchantUtilobj.getOfferName());
			obj.put("offerPrice", merchantUtilobj.getOfferPrice());
			obj.put("actualPrice", merchantUtilobj.getActualPrice());
			obj.put("offerValidFrom", merchantUtilobj.getOfferValidFrom());
			obj.put("offerValidTo", merchantUtilobj.getOfferValidTo());
			obj.put("description", merchantUtilobj.getDescription());			
			if( merchantobj.getMrchntId()!=null){				
				if (offerfile.size() > 0) {
					if (sFileName.contains(",")) {
					} else {
						sFileName = sFileName + ",";
					}
					filename = sFileName.split(",");
				}
				for (int fa=0; fa < offerfile.size(); fa++){
					String imagefileName = filename[fa];
					if (imagefileName!=null && imagefileName.length()>0){
						imagefileName=imagefileName.trim();
					}
					String lvvFextn = FilenameUtils.getExtension(imagefileName);
					if (supportfile!=null && lvvFextn!=null && supportfile.toUpperCase().contains(lvvFextn.toUpperCase())) {
						
					} else {
						lvrUnsprtfillst += imagefileName + ", ";lvrUnsfileflag = true;
					}
				}
				if (!lvrUnsfileflag) {
					data.put("data", obj);
					String jsonTextFinal = data.toString();
					jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
					String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
					String finalUrl = getText("socialindia.mrchantMng.editNewMerchantOffer");
					logWrite.logMessage("Step 2 : Offer modify service url : "+finalUrl, "info", EditNewMerchantOffer.class);
					Commonutility.toWriteConsole("Step 2 : Offer modify service url : "+finalUrl);
					String response = commonObj.jsonRequest(finalUrl, temp);
					logWrite.logMessage("Step 3 : Offer modify service Response : "+response, "info", EditNewMerchantOffer.class);
					Commonutility.toWriteConsole("Step 3 : Offer modify service response : "+response);
					JSONObject json = new JSONObject(response);
					String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
					String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
					if (isdeleteImage != null) {
					} else {
						isdeleteImage = "no";
					}			
					double totfilesize = 0;
					byte imgBytlogo[] = new byte[2048];
					if (merchantUtilId != null) {
						if (offerfile.size() > 0) {
							if (sFileName.contains(",")) {
							} else {
								sFileName = sFileName + ",";
							}
							filename = sFileName.split(",");
						}					
						data = new JSONObject();
						data.put("servicecode", "SI6423");
						data.put("userId", sessionMap.get("USERID"));
						data.put("usrTyp", sessionMap.get("GROUPCODE"));
						obj.put("merchantUtilId", merchantUtilId);
						obj.put("isdeleteImage", isdeleteImage);
						for (int fa = 0; fa < offerfile.size(); fa++) {
							try {
								String imagefileName = filename[fa];
								if (imagefileName != null && imagefileName.length() > 0) {
									imagefileName = imagefileName.trim();
								}
								imgBytlogo = commonSnippet.toReadFiletoBytes(offerfile.get(fa));
								//String uploaddetail=commonSnippet.toByteAryToBase64EncodeStr(imgBytlogo);
								obj.put("fileName",imagefileName);
								//obj.put("imageDetail",uploaddetail);
								if(offerfile.get(fa)!=null) {
									obj.put("imgsrthpath",offerfile.get(fa).getAbsolutePath());
								} else {
									obj.put("imgsrthpath","");
								}
								
								obj.put("isdeleteImage",isdeleteImage);
								data.put("data", obj);
								jsonTextFinal = data.toString();
								jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
								temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
								finalUrl = getText("socialindia.mrchantMng.addNewMerchantOfferImages");
								response = commonObj.jsonRequest(finalUrl, temp);
								} catch (Exception ex) {
								}
							isdeleteImage="no";
						}		
						logWrite.logMessage("Step 4 : Offer modify called success [End]", "info", EditNewMerchantOffer.class);
						Commonutility.toWriteConsole("Step 4 : Offer modify called success [End]");
						alert.setCls("success");
						if (Commonutility.checkempty(lvrRspmsg)) {
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg(getText("Merchant.offerupdate.success"));
						}
						alertList.add(alert);
					} else {
						logWrite.logMessage("Step 4 : Offer modify called Error. [End]", "info", EditNewMerchantOffer.class);
						Commonutility.toWriteConsole("Step 4 : Offer modify called Error. [End]");
						alert.setCls("danger");
						if (Commonutility.checkempty(lvrRspmsg)) {
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg(getText("Merchant.offerupdate.error"));
						}
						alertList.add(alert);
						return "input";
					}
				} else {					
					alert.setCls("danger");
					if (lvrUnsprtfillst!=null && lvrUnsprtfillst.endsWith(", ")) {
						lvrUnsprtfillst = lvrUnsprtfillst.substring(0, lvrUnsprtfillst.length()-2);
						logWrite.logMessage("Step 4 : Offer modify called Error. [End] IF -  Un supported file found", "info", EditNewMerchantOffer.class);
						Commonutility.toWriteConsole("Step 4 : Offer modify called Error. [End] IF -  Un supported file found");
						alert.setMsg(getText("Merchant.offerupdate.error")  +", Unsupport Files are not uploaded. ["+ lvrUnsprtfillst+"]");					
					} else {
						logWrite.logMessage("Step 4 : Offer modify called Error. [End] else -  Un supported file found", "info", EditNewMerchantOffer.class);
						Commonutility.toWriteConsole("Step 4 : Offer modify called Error. [End] else -  Un supported file found");
						alert.setMsg(getText("Merchant.offerupdate.error"));
					}
					alertList.add(alert);
					return "failed";
				}													
			} else {
				logWrite.logMessage("Step 4 : Offer modify called Error. [End] ", "info", EditNewMerchantOffer.class);
				Commonutility.toWriteConsole("Step 4 : Offer modify called Error. [End]");
				alert.setCls("danger");
				alert.setMsg(getText("Merchant.offerupdate.error"));
				alertList.add(alert);
				return "input";
			}
			
		}catch (Exception e){
			logWrite.logMessage("Step -1 : Offer modify called Error. [Exception]", "error", EditNewMerchantOffer.class);
			Commonutility.toWriteConsole("Step -1 : Offer modify called Error. [Exception]");
			alert.setCls("danger");
			alert.setMsg(getText("Merchant.offerupdate.error"));
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
