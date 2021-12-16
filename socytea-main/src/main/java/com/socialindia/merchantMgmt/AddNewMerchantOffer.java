package com.socialindia.merchantMgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
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

public class AddNewMerchantOffer  extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MerchantTblVO merchantobj = new MerchantTblVO();
	MerchantUtilitiTblVO merchantutilObj=new MerchantUtilitiTblVO();
	MerchantUtilitiImageTblVO merchantutilimageObj=new MerchantUtilitiImageTblVO();
	private List <File> offerfile = new ArrayList <File> ();
	private String offerfileFileName;
	commomServices commonSnippet=new commomServices();
	
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
		Log logwrite = null;
		String supportfile = getText("Text.image.extensions");
		String lvrUnsprtfillst = "";
		boolean lvrUnsfileflag= false;
		try {
			logwrite =  new Log();
			Commonutility.toWriteConsole("Step 1 : Offer create / Add called [Start]");
			logwrite.logMessage("Step 1 : Offer create / Add called [Start]", "info", AddNewMerchantOffer.class);
			String sFileName = offerfileFileName;
			String[] filename = null;
			Map sessionMap = ActionContext.getContext().getSession();
			Integer mrchntUniqId = merchantobj.getMrchntId();
			if (mrchntUniqId == null) {
				if ((mrchntUniqId == null || mrchntUniqId == -1 || mrchntUniqId == 0) && sessionMap.get("svrMrchuniqueid") != null) {
					String stfiddd = (String) sessionMap.get("svrMrchuniqueid");
					mrchntUniqId = Integer.parseInt(stfiddd);	
					merchantobj.setMrchntId(mrchntUniqId);
				}
			} else {			
				sessionMap.put("svrMrchuniqueid", String.valueOf(mrchntUniqId));								
			}
			
			data.put("servicecode", "SI6422");
			data.put("userId", sessionMap.get("USERID"));
			data.put("usrTyp", sessionMap.get("GROUPCODE"));			
			obj.put("mrchntId", merchantobj.getMrchntId());
			obj.put("broucherName", merchantutilObj.getBroucherName());
			obj.put("offerName", merchantutilObj.getOfferName());
			obj.put("offerPrice", merchantutilObj.getOfferPrice());
			obj.put("actualPrice", merchantutilObj.getActualPrice());
			obj.put("offerValidFrom", merchantutilObj.getOfferValidFrom());
			obj.put("offerValidTo", merchantutilObj.getOfferValidTo());
			obj.put("description", merchantutilObj.getDescription());
			if ( merchantobj.getMrchntId()!=null){
				JSONArray imagearray = new JSONArray();
				JSONArray fileNamearray = new JSONArray();
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
						String finalUrl = getText("socialindia.mrchantMng.createNewMerchantOffer");
						Commonutility.toWriteConsole("Step 2 : Offer create / Add offer service called url : "+finalUrl);
						logwrite.logMessage("Step 2 : Offer create / Add offer service called url : "+finalUrl, "info", AddNewMerchantOffer.class);
						String response = commonObj.jsonRequest(finalUrl, temp);
						Commonutility.toWriteConsole("Step 3 : Offer create / Add offer service response : "+response);
						logwrite.logMessage("Step 3 : Offer create / Add offer service response : "+response, "info", AddNewMerchantOffer.class);
						JSONObject json = new JSONObject(response);
						String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
						String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
						double totfilesize = 0;
						
						//byte imgBytlogo[] = new byte[2048];
						if(statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")){
							JSONObject dataobj=new JSONObject();
							dataobj =(JSONObject) Commonutility.toHasChkJsonRtnValObj(json,"data");
							Integer merchantUtilId=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"merchantUtilId");
							data = new JSONObject();
							data.put("servicecode", "SI6423");
							data.put("userId", sessionMap.get("USERID"));
							data.put("usrTyp", sessionMap.get("GROUPCODE"));
							obj.put("merchantUtilId", merchantUtilId);
							Commonutility.toWriteConsole("Step 4 : Offer create / Add offer image upload start");
							logwrite.logMessage("Step 4 : Offer create / Add offer image upload start", "info", AddNewMerchantOffer.class);
							for (int fa=0; fa < offerfile.size(); fa++){
								try {
									String imagefileName = filename[fa];
									if (imagefileName!=null && imagefileName.length()>0){
										imagefileName=imagefileName.trim();
									}
									//String lvvFextn = FilenameUtils.getExtension(imagefileName);
									//Commonutility.toWriteConsole("File Extension : "+lvvFextn);					
									//if (supportfile!=null && lvvFextn!=null && supportfile.toUpperCase().contains(lvvFextn.toUpperCase())) {
										obj.put("fileName",imagefileName);
										//obj.put("imageDetail",uploaddetail);
										if (offerfile.get(fa)!=null) {
											obj.put("imgsrthpath", offerfile.get(fa).getAbsolutePath());
										} else {
											obj.put("imgsrthpath", "");
										}							
										obj.put("isdeleteImage","no");//default
										data.put("data", obj);
										jsonTextFinal = data.toString();
										jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
										temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
										finalUrl = getText("socialindia.mrchantMng.addNewMerchantOfferImages");
										response = commonObj.jsonRequest(finalUrl, temp);
									//} else {
										//lvrUnsprtfillst += imagefileName + ", ";
									//}							
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
							Commonutility.toWriteConsole("Step 5 : Offer create / Add offer called [End]");
							logwrite.logMessage("Step 5 : Offer create / Add offer called [End]", "info", AddNewMerchantOffer.class);
							alert.setCls("success");
							if (Commonutility.checkempty(lvrRspmsg)) {
								//if (lvrUnsprtfillst!=null && lvrUnsprtfillst.endsWith(", ")) {
									//lvrUnsprtfillst = lvrUnsprtfillst.substring(0, lvrUnsprtfillst.length()-2);
									//alert.setMsg(lvrRspmsg +", Unsupport Files are not uploaded. ["+ lvrUnsprtfillst+"]");
								//} else {alert.setMsg(lvrRspmsg);						
								//}
								alert.setMsg(lvrRspmsg);
							} else {
								alert.setMsg(getText("Merchant.offeradded.success"));
							}
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
					} else {
						Commonutility.toWriteConsole("Step 4 : Offer create / Add offer called [End] -  Error update");
						logwrite.logMessage("Step 4 : Offer create / Add offer called [End] -  Error update", "info", AddNewMerchantOffer.class);
						alert.setCls("danger");
						if (Commonutility.checkempty(lvrRspmsg)) {
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg(getText("Merchant.offeradded.error"));
						}				
						alertList.add(alert);
						sessionMap.put("alertList", alertList);
						return "input";
					}
				} else {					
					alert.setCls("danger");
					if (lvrUnsprtfillst!=null && lvrUnsprtfillst.endsWith(", ")) {
						lvrUnsprtfillst = lvrUnsprtfillst.substring(0, lvrUnsprtfillst.length()-2);
						Commonutility.toWriteConsole("Step 4 : Offer create / Add offer called [End] IF -  Un supported file found");
						logwrite.logMessage("Step 4 : Offer create / Add offer called [End] IF -  Un supported file found", "info", AddNewMerchantOffer.class);
						alert.setMsg(getText("Merchant.offeradded.error") +", Unsupport Files are not uploaded. ["+ lvrUnsprtfillst+"]");
						
					}else{
						Commonutility.toWriteConsole("Step 4 : Offer create / Add offer called [End] ELSE-  Un supported file found");
						logwrite.logMessage("Step 4 : Offer create / Add offer called [End] ELSE -  Un supported file found", "info", AddNewMerchantOffer.class);
						alert.setMsg(getText("Merchant.offeradded.error"));
					}
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "failed";
				}
				
			} else {
				Commonutility.toWriteConsole("Step 4 : Offer create / Add offer called [End] -  Merchant id not found");
				logwrite.logMessage("Step 4 : Offer create / Add offer called [End] -  Merchant id not found", "info", AddNewMerchantOffer.class);
				alert.setCls("danger");
				alert.setMsg(getText("Merchant.offeradded.error"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
		}			
		} catch (Exception e) {
			Commonutility.toWriteConsole("Step -1 : Offer create / Add offer called [Excepton] : "+e);
			logwrite.logMessage("Step -1 : Offer create / Add offer called  [Excepton] : "+e, "info", AddNewMerchantOffer.class);
			alert.setCls("danger");
			alert.setMsg(getText("Merchant.offeradded.error"));
			alertList.add(alert);
			e.printStackTrace();
			return "input";
		} finally {
			
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
	public MerchantUtilitiTblVO getMerchantutilObj() {
		return merchantutilObj;
	}
	public void setMerchantutilObj(MerchantUtilitiTblVO merchantutilObj) {
		this.merchantutilObj = merchantutilObj;
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
	

}
