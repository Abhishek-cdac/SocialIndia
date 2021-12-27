package com.socialindia.merchantMgmt;

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
import com.socialindia.vo.MerchantCategoryDetail;
import com.socialindia.vo.MerchantStockDetailTblVO;
import com.socialindia.vo.MerchantTblVO;

public class DeleteMerchantDetail extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	MerchantTblVO merchantobj=new MerchantTblVO();
	MerchantCategoryDetail merchantcatdetobj=new MerchantCategoryDetail();
	MerchantStockDetailTblVO merchantStockobj=new MerchantStockDetailTblVO();
	private List<MerchantStockDetailTblVO> merchantStockobjList = new ArrayList<MerchantStockDetailTblVO>();
	private List<MerchantCategoryDetail> merchantcatdetList = new ArrayList<MerchantCategoryDetail>();
	
	public String execute() {
		try{
			
			Map sessionMap = ActionContext.getContext().getSession();
			data.put("servicecode", "SI6421");
			data.put("currentloginid", sessionMap.get("USERID"));			
			obj.put("userId", sessionMap.get("USERID"));
			obj.put("usrTyp", sessionMap.get("GROUPCODE"));
			obj.put("mrchntId",merchantobj.getMrchntId());
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.mrchantMng.deletemerchantdetail");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject dataobj=new JSONObject();
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json,"statuscode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json,"message");
			json=null;
			dataobj=null;
			 if(statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")){
				 	alert.setCls("success");
				 	if(Commonutility.checkempty(lvrRspmsg)){
				 		alert.setMsg(lvrRspmsg);
				 	} else {
				 		alert.setMsg(getText("Merchant.deleted.success"));
				 	}
					alertList.add(alert);
					return "success";
			}else{
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
			 		alert.setMsg(lvrRspmsg);
			 	} else {
			 		alert.setMsg(getText("Merchant.view.error"));
			 	}				
				alertList.add(alert);
				return "input";
			}
			 
		}catch (Exception e){
			e.printStackTrace();
			alert.setCls("danger");
			alert.setMsg(getText("Merchant.view.error"));
			alertList.add(alert);
			return "input";
		}finally{
			obj=null;
			data=null;
		}
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
	public MerchantTblVO getMerchantobj() {
		return merchantobj;
	}
	public void setMerchantobj(MerchantTblVO merchantobj) {
		this.merchantobj = merchantobj;
	}
	public MerchantCategoryDetail getMerchantcatdetobj() {
		return merchantcatdetobj;
	}
	public void setMerchantcatdetobj(MerchantCategoryDetail merchantcatdetobj) {
		this.merchantcatdetobj = merchantcatdetobj;
	}
	public MerchantStockDetailTblVO getMerchantStockobj() {
		return merchantStockobj;
	}
	public void setMerchantStockobj(MerchantStockDetailTblVO merchantStockobj) {
		this.merchantStockobj = merchantStockobj;
	}
	public List<MerchantStockDetailTblVO> getMerchantStockobjList() {
		return merchantStockobjList;
	}
	public void setMerchantStockobjList(
			List<MerchantStockDetailTblVO> merchantStockobjList) {
		this.merchantStockobjList = merchantStockobjList;
	}
	public List<MerchantCategoryDetail> getMerchantcatdetList() {
		return merchantcatdetList;
	}
	public void setMerchantcatdetList(
			List<MerchantCategoryDetail> merchantcatdetList) {
		this.merchantcatdetList = merchantcatdetList;
	}
	
	
	
}