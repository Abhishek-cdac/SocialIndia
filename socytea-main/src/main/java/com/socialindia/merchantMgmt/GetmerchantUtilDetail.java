package com.socialindia.merchantMgmt;

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
import com.socialindia.vo.MerchantTblVO;
import com.socialindia.vo.MerchantUtilitiTblVO;

public class GetmerchantUtilDetail    extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	MerchantUtilitiTblVO merchantUtilobj=new MerchantUtilitiTblVO();
	MerchantTblVO merchantobj=new MerchantTblVO();
	private Integer ivrMrchutlid;
	public String execute() {
		Log logWrite = null;JSONObject dataobj = null;
		try{	
			logWrite = new Log();
			ivrMrchutlid = merchantUtilobj.getMerchantUtilId();
			Map sessionMap = ActionContext.getContext().getSession();
			Commonutility.toWriteConsole("Step 1 : Merchant Offer detail GetmerchantUtilDetail.calss called [Start]");
			logWrite.logMessage("Step 1 : Merchant Offer detail GetmerchantUtilDetail.calss called [Start]", "info", GetmerchantUtilDetail.class);
			
			if ((ivrMrchutlid == null || ivrMrchutlid == -1 || ivrMrchutlid == 0) && sessionMap.get("curntsessionMrchutilid") != null) {
					String stfiddd = (String) sessionMap.get("curntsessionMrchutilid");
					ivrMrchutlid = Integer.parseInt(stfiddd);
					merchantUtilobj.setMerchantUtilId(ivrMrchutlid);				
			} else {				
				sessionMap.put("curntsessionMrchutilid", String.valueOf(ivrMrchutlid));				
				merchantUtilobj.setMerchantUtilId(ivrMrchutlid);
			}
			Commonutility.toWriteConsole("Step 2 : Merchant Offer detail GetmerchantUtilDetail.calss Mercahnt Utild Id : "+ivrMrchutlid);	
			logWrite.logMessage("Step 2 : Merchant Offer detail GetmerchantUtilDetail.calss Mercahnt Utild Id : "+ivrMrchutlid, "info", GetmerchantUtilDetail.class);
			data.put("servicecode", "SI6419");
			data.put("currentloginid", sessionMap.get("USERID"));	
			obj.put("userId", sessionMap.get("USERID"));
			obj.put("usrTyp", sessionMap.get("GROUPCODE"));
			obj.put("merchantUtilId", ivrMrchutlid);
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			
			String finalUrl = getText("socialindia.mrchantMng.getmerchantutilviewdetails");	
			Commonutility.toWriteConsole("Step 3 : Merchant Offer detail GetmerchantUtilDetail.calss Service URL : " + finalUrl);
			logWrite.logMessage("Step 3 : Merchant Offer detail GetmerchantUtilDetail.calss Service URL : " + finalUrl, "info", GetmerchantUtilDetail.class);
			String response = commonObj.jsonRequest(finalUrl, temp);			
			JSONObject json = new JSONObject(response);
			Commonutility.toWriteConsole("Step 4 : Merchant Offer detail GetmerchantUtilDetail.calss Service Response: " + response);
			logWrite.logMessage("Step 4 : Merchant Offer detail GetmerchantUtilDetail.calss Service Response: " + response, "info", GetmerchantUtilDetail.class);
			dataobj = new JSONObject();
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json,"statuscode");				
			dataobj = (JSONObject) Commonutility.toHasChkJsonRtnValObj(json,"data");			
			String broucherName=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"broucherName");
			String offerName=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"offerName");
			String offerPrice=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"offerPrice");
			String actualPrice=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"actualPrice");
			String description=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"description");
			String offerValidFrom=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"offerValidFrom");
			String offerValidTo=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"offerValidTo");
			Integer mrchntId=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"mrchntId");
			
			merchantUtilobj.setBroucherName(broucherName);
			merchantUtilobj.setOfferName(offerName);
			merchantUtilobj.setOfferPrice(offerPrice);
			merchantUtilobj.setActualPrice(actualPrice);
			merchantUtilobj.setDescription(description);
			merchantUtilobj.setOfferValidFrom(offerValidFrom);
			merchantUtilobj.setOfferValidTo(offerValidTo);
			merchantUtilobj.setMrchntId(mrchntId);			
			if (statusCode.equalsIgnoreCase("error")) {
				alert.setCls("danger");
				alert.setMsg(getText("Merchant.view.error"));
				alertList.add(alert);
				return "input";
			} 
			json = null;
			dataobj = null;
			Commonutility.toWriteConsole("Step 5 : Merchant Offer detail GetmerchantUtilDetail.calss [End]");
			logWrite.logMessage("Step 5 : Merchant Offer detail GetmerchantUtilDetail.calss [End]", "info", GetmerchantUtilDetail.class);
		}catch (Exception e){
			Commonutility.toWriteConsole("Step -1 : Merchant Offer detail GetmerchantUtilDetail.calss [Exception] : "+e);
			logWrite.logMessage("Step -1 : Merchant Offer detail GetmerchantUtilDetail.calss [Exception] : "+e, "error", GetmerchantUtilDetail.class);
		}finally{
			obj = null;
			data = null; logWrite = null;
		}
	return SUCCESS;
	}
	
	public Integer getIvrMrchutlid() {
		return ivrMrchutlid;
	}

	public void setIvrMrchutlid(Integer ivrMrchutlid) {
		this.ivrMrchutlid = ivrMrchutlid;
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
	public MerchantUtilitiTblVO getMerchantUtilobj() {
		return merchantUtilobj;
	}
	public void setMerchantUtilobj(MerchantUtilitiTblVO merchantUtilobj) {
		this.merchantUtilobj = merchantUtilobj;
	}
	public MerchantTblVO getMerchantobj() {
		return merchantobj;
	}
	public void setMerchantobj(MerchantTblVO merchantobj) {
		this.merchantobj = merchantobj;
	}
	
	
}
