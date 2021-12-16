package com.socialindia.material;

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

public class createMaterialAction  extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	JSONObject obj = new JSONObject();
	MvpMaterialTbl materialObj=new MvpMaterialTbl();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private int categoryType;
	private String purchaseDate;

	public String execute() {

		return SUCCESS;
	}
	
	public String materialCreateAction(){	
		Log logWrite = null;
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			Commonutility.toWriteConsole("[Client] Step 1 : Create Material Action Called.[Start]");
			logWrite =new Log();
			logWrite.logMessage("[Client] Step 1 : Create Material Action Called.[Start]", "info", createMaterialAction.class);			
			int societyval = (int) sessionMap.get("sSoctyId");
			if (societyval == -1) {
				obj.put("societyid", materialObj.getSocietyId());
			} else {
				obj.put("societyid", societyval);
			}
			data.put("servicecode", "SI0052");
			obj.put("categorytype", categoryType);
			obj.put("materialname", materialObj.getMaterialName());
			obj.put("totalqnty", materialObj.getTotalQnty());
			obj.put("usedqnty", materialObj.getUsedQnty());
			obj.put("materialqnty", materialObj.getMaterialQnty());
			obj.put("meterialprice", materialObj.getMaterialPrice().toString());
			obj.put("purchaseDate", purchaseDate);
			obj.put("materialdesc", materialObj.getMaterialDesc());
			data.put("data", obj);
			String jsonTextFinal = data.toString();			
			Commonutility.toWriteConsole("[Client] Step 2 : Service Url Called. Url - : "+jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("materialMgmt.management.create.Action");
			
			logWrite.logMessage("[Client] Step 2 : Service Url Called. Url - : "+finalUrl, "info", createMaterialAction.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("[Client] Step 3 : Service Response : "+response+"");
			logWrite.logMessage("[Client] Step 3 : Service Response : "+response, "info", createMaterialAction.class);
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;
			JSONObject locObjRecvJson = null;
			JSONObject locObjRecvdataJson = null;
				if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
					boolean ivIsJson = Commonutility.toCheckIsJSON(response);
					if (ivIsJson) {
						locObjRecvJson = new JSONObject(response);
						ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");		    	 
						ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");		    	  
						ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");		    	  
						ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");	
						if (ivrstatuscode!=null && (ivrstatuscode.equalsIgnoreCase("00") || ivrstatuscode.equalsIgnoreCase("0"))) {
							Commonutility.toWriteConsole("[Client] Step 4 :  Create Material Action.Success.[End]");
							logWrite.logMessage("[Client] Step 4 :  Create Material Action.Success.[End]", "info", createMaterialAction.class);
							alert.setCls("success");
							if(Commonutility.checkempty(ivrmsg)){
								alert.setMsg(ivrmsg);	
								
							} else {
								
								alert.setMsg("Material added successfully.");	
							}
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "success";
						} else {
							Commonutility.toWriteConsole("[Client] Step 4 :  Create Material Action.Error.[End]");
							logWrite.logMessage("[Client] Step 4 :  Create Material Action.Error.[End]", "info", createMaterialAction.class);
							alert.setCls("danger");
							if(Commonutility.checkempty(ivrmsg)){
								alert.setMsg(ivrmsg);						
							} else {
								alert.setMsg("Material not added. Error.");
							}						
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "input";
					}
    		
    		  }
			}
		} catch (Exception e) {
			Commonutility.toWriteConsole("[Client] Step -1 :  Create Material Action.[Exception] : "+e);
			logWrite.logMessage("[Client] Step -1 :  Create Material Action.[Exception] : "+e, "error", createMaterialAction.class);
			alert.setCls("danger");			
			alert.setMsg("Material not added. Error.");						
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally {
			logWrite = null;
		}
		return SUCCESS;
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

	public MvpMaterialTbl getMaterialObj() {
		return materialObj;
	}

	public void setMaterialObj(MvpMaterialTbl materialObj) {
		this.materialObj = materialObj;
	}

	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
