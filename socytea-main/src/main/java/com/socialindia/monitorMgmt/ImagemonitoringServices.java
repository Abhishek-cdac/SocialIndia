package com.socialindia.monitorMgmt;


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
import com.socialindia.eNewsmgmt.EeNewsTblVO;
import com.socialindia.generalmgnt.persistance.CategoryMasterTblVO;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.IDCardMasterTblVO;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.profileUpdate;
import com.socialindia.tendermgmt.TenderDocTblVO;


public class ImagemonitoringServices extends ActionSupport{
	private String uniqueId;
	private String tableflg;
	private String blockrowid; 
	private String imgmontable;
	private String ivrMastertblid;
	JSONObject obj = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();
	CategoryMasterTblVO committeeobj = new CategoryMasterTblVO(0, null);
	private static final long serialVersionUID = 1L;

	public String execute() {
		return SUCCESS;
	}

	public String toBlockImage() {
		System.out.println("------- user image blocked Deactivation ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("imageid", String.valueOf(blockrowid));		
			obj.put("tableflg", tableflg);
			obj.put("tablename", imgmontable);
			obj.put("mastertblid", ivrMastertblid);
			obj.put("ivrservicefor", "4");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI10009");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.image.monitoring.dl");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("user image blocked"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("user image blocked error"));
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {			
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class deleteActionfun() Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:ss") +" : "+ex);
		} finally {

		}
		return SUCCESS;
	}

	public String activeImage() {//socialindia.feedtable.unblockaction
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("imageid", String.valueOf(blockrowid));
			obj.put("tableflg", tableflg);
			obj.put("tablename", imgmontable);
			obj.put("mastertblid", ivrMastertblid);
			obj.put("ivrservicefor", "3");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI10010");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.image.unblockaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("user image unblocked"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("user image unblocked error"));
				alertList.add(alert);
				return "input";
			}
		} catch (Exception ex) {
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class activeActionfun() Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:ss") +" : "+ex);
		} finally {

		}
		return SUCCESS;
	}

	
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
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



	public String getBlockrowid() {
		return blockrowid;
	}

	public void setBlockrowid(String blockrowid) {
		this.blockrowid = blockrowid;
	}

	public CategoryMasterTblVO getCategoryobj() {
		return committeeobj;
	}

	public void setCategoryobj(CategoryMasterTblVO committeeobj) {
		this.committeeobj = committeeobj;
	}

	public String getTableflg() {
		return tableflg;
	}

	public void setTableflg(String tableflg) {
		this.tableflg = tableflg;
	}

	public String getImgmontable() {
		return imgmontable;
	}

	public void setImgmontable(String imgmontable) {
		this.imgmontable = imgmontable;
	}

	public String getIvrMastertblid() {
		return ivrMastertblid;
	}
	
	public void setIvrMastertblid(String ivrMastertblid) {
		this.ivrMastertblid = ivrMastertblid;
	}

	
}

