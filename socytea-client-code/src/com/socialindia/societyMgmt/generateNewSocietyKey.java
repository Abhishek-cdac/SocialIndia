package com.socialindia.societyMgmt;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.getCategoryCodeMethod;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.IDCardMasterTblVO;
import com.socialindia.login.EncDecrypt;

public class generateNewSocietyKey extends ActionSupport{
	 private Common commonObj = new CommonDao();
	  public List<IDCardMasterTblVO> idcardMstr = new ArrayList<IDCardMasterTblVO>();
	 JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	  private int uniqSocietyIdEdit;
	  private String newActivationKey;
	  public String execute() throws Exception {
		JSONObject locObjRecvJson = null;//Receive String to json	
		JSONObject locObjRecvdataJson = null;// Receive Data Json		
		JSONObject locObjRspdataJson = null;// Response Data Json
		String ivrservicecode=null;
    	String ivrresponsecode=null;
    	String ivrmsg=null;
    	String ivrstatuscode=null;
    	
		data.put("servicecode", "SI0042");
		obj.put("uniqSocietyIdEdit", uniqSocietyIdEdit);
		data.put("data", obj);
		String jsonTextFinal_Idcard = data.toString().trim();
		jsonTextFinal_Idcard=EncDecrypt.encrypt(jsonTextFinal_Idcard);
		String tempidcard = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Idcard);
		String finalUrl_pcode = getText("society.management.key.generate.action");	
    	  String response1 = commonObj.jsonRequest(finalUrl_pcode, tempidcard);
    	  System.out.println("--response1-- "+response1);
    	  if(response1!=null && !response1.equalsIgnoreCase("null") && response1.length()>0){
    		  boolean ivIsJson = Commonutility.toCheckIsJSON(response1);
    		  if (ivIsJson) {
    			  locObjRecvJson = new JSONObject(response1);
		    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");		    	  
		    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");		    	  
		    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");		    	  
		    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");				
		    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");    		  
		    	  newActivationKey=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"newactivationkey");
    					    	  
    		  }
    	  }    		  
    		  
    	    
		return SUCCESS;
	}
	public String getNewActivationKey() {
		return newActivationKey;
	}
	public void setNewActivationKey(String newActivationKey) {
		this.newActivationKey = newActivationKey;
	}
	public int getUniqSocietyIdEdit() {
		return uniqSocietyIdEdit;
	}
	public void setUniqSocietyIdEdit(int uniqSocietyIdEdit) {
		this.uniqSocietyIdEdit = uniqSocietyIdEdit;
	}
	

}
