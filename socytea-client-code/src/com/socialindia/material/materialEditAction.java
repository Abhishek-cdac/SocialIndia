package com.socialindia.material;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
import com.socialindia.societyMgmt.SocietyWingsDetailTbl;

public class materialEditAction extends ActionSupport{
	Log log=new Log();
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private int materialId;
	private MvpMaterialTbl materialObj=new MvpMaterialTbl();

	
	public String execute()
	{
		ResourceBundle ivrRbuilder = ResourceBundle.getBundle("applicationResources");
	System.out.println("=======edit Forum=======materialId========="+materialId);
	try{
		Map currentSession = ActionContext.getContext().getSession();
		if ((materialId == 0)
				&& currentSession.get("currentsession_materialId") != null) {
			materialId = (Integer) currentSession
					.get("currentsession_materialId");
		} else {
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_materialId",
					materialId);
		}
		
	data.put("servicecode", "SI0055");
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
	String ivrservicecode=null;
	String ivrresponsecode=null;
	String ivrmsg=null;
	String ivrstatuscode=null;
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
	    	  int categoryid=(Integer) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "categoryid");
	    	  int materialid=(Integer) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "materialid");
	    	  String  societyname=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "societyname");
	    	  String  materialname=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "materialname");
	    	  int materialqnty=(Integer) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "materialqnty");
	    	  int totalqnty=(Integer) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "totalqnty");
	    	  int usedqnty=(Integer) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "usedqnty");
	    	  String materialprice=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "materialprice");
	    	  String purchasedate=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "purchasedate");
	    	  String materialdesc=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "materialdesc");
	    	  if(ivrstatuscode.equalsIgnoreCase("0")){
	    		  materialObj.setMaterialId(materialid);
	    		  materialObj.setSocietyName(societyname);
	    		  materialObj.setCategoryName(categoryname);
	    		  materialObj.setMaterialName(materialname);
	    		  materialObj.setMaterialQnty(materialqnty);
	    		  materialObj.setTotalQnty(totalqnty);
	    		  materialObj.setUsedQnty(usedqnty);
	    		  materialObj.setMaterialPrice(materialprice);
	    		  materialObj.setMaterialDesc(materialdesc);
	    		  materialObj.setMaterialCategoryId(categoryid);
	    		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    		  Date startDate = df.parse(purchasedate);
	    		  SimpleDateFormat df1 = new SimpleDateFormat(ivrRbuilder.getString("calander.format.date"));
	    		  purchasedate=df1.format(startDate);
	    		  materialObj.setPurDate(purchasedate);
		
		  }
	 }
		
	 }
	}catch (Exception e) {
		System.out.println("==================="+e);
	}
	return SUCCESS;
	}


	public int getMaterialId() {
		return materialId;
	}


	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}


	public MvpMaterialTbl getMaterialObj() {
		return materialObj;
	}


	public void setMaterialObj(MvpMaterialTbl materialObj) {
		this.materialObj = materialObj;
	}


	
}
