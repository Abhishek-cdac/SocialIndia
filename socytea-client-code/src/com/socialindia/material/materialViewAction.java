package com.socialindia.material;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class materialViewAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Log log = new Log();
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private int materialId;
	private String  townshipname;

	private MvpMaterialTbl materialMst = new MvpMaterialTbl();

	public String execute() {
		System.out.println("=======view Forum=======topicName=========");
		try {
			Map currentSession = ActionContext.getContext().getSession();
			if ((materialId == 0)
					&& currentSession.get("currentsession_materialId") != null) {
				materialId = (Integer) currentSession
						.get("currentsession_materialId");
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_materialId", materialId);
			}
		
			data.put("servicecode", "SI0053");
			obj.put("materialId", materialId);
	
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			System.out.println("===jsonTextFinal=data========" + jsonTextFinal);

			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			System.out.println("=====jsonText=====" + jsonTextFinal);

			String finalUrl = getText("materialMgmt.management.view.Action");
			System.out.println("=======finalUrl====" + finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("=========response===" + response);
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
					locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");    
	    	  
					String categoryname=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "categoryname");
					townshipname=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "townshipname");
					String  societyname=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "societyname");
					String  materialname=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "materialname");
					int materialqnty=(Integer) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "materialqnty");
					int totalqnty=(Integer) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "totalqnty");
					int usedqnty=(Integer) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "usedqnty");
					String materialprice=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "materialprice");
					String purchasedate=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "purchasedate");
					String materialdesc=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "materialdesc");
					if (ivrstatuscode.equalsIgnoreCase("0")) {
						materialMst.setSocietyName(societyname);
						materialMst.setCategoryName(categoryname);
						materialMst.setMaterialName(materialname);
						materialMst.setMaterialQnty(materialqnty);
						materialMst.setTotalQnty(totalqnty);
						materialMst.setUsedQnty(usedqnty);
						materialMst.setMaterialPrice(materialprice);
						SimpleDateFormat df = new SimpleDateFormat(getText("calander.format.date"));//dd-MM-yyyy - User Format
						SimpleDateFormat dbfrmt = new SimpleDateFormat("yyyy-MM-dd");//DataBase Format	  		
						materialMst.setPurDate(df.format(dbfrmt.parse(purchasedate)));
						materialMst.setMaterialDesc(materialdesc);
					}
			}		 
	 }
								
		} catch (Exception e) {
			System.out.println("===================" + e);
		}
		return SUCCESS;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public MvpMaterialTbl getMaterialMst() {
		return materialMst;
	}

	public void setMaterialMst(MvpMaterialTbl materialMst) {
		this.materialMst = materialMst;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public String getTownshipname() {
		return townshipname;
	}

	public void setTownshipname(String townshipname) {
		this.townshipname = townshipname;
	}
	
}
