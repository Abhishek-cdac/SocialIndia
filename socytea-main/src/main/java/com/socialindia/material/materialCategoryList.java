package com.socialindia.material;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.IDCardMasterTblVO;
import com.socialindia.login.EncDecrypt;

public class materialCategoryList extends ActionSupport{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Common commonObj = new CommonDao();
	  public List<IDCardMasterTblVO> idcardMstr = new ArrayList<IDCardMasterTblVO>();
	 JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	  private String locIdrcty=null;
	  private String locIdcountrytyp=null;
	  private String locIdStatetyp=null;
	  private String locIdskillstyp=null;
	  public String execute() throws Exception {
	  JSONObject locObjRecvJson = null;//Receive String to json	
		JSONObject locObjRecvdataJson = null;// Receive Data Json		
		JSONObject locObjRspdataJson = null;// Response Data Json		
		String ivrservicecode=null;
    	String ivrresponsecode=null;
    	String ivrmsg=null;
    	String ivrstatuscode=null;    	
		obj.put("servicecode", "SI0051");		
		data.put("data", obj);
		String jsonTextFinal_Idcard = data.toString().trim();
		jsonTextFinal_Idcard=EncDecrypt.encrypt(jsonTextFinal_Idcard);
		String tempidcard = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Idcard);			
		String finalUrl_pcode = getText("materialmanagement.category.List");		
    	  String response1 = commonObj.jsonRequest(finalUrl_pcode, tempidcard);    	  
    	  if(response1!=null && !response1.equalsIgnoreCase("null") && response1.length()>0){
    		  boolean ivIsJson = Commonutility.toCheckIsJSON(response1);
    		  if (ivIsJson) {
    			  locObjRecvJson = new JSONObject(response1);
		    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");		    	  
		    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");		    	  
		    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");		    	  
		    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");				
		    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");    		  
		    	  JSONArray jary=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");	    	
		    	  JSONArray jsr= null;JSONObject jb=null;
				try {
					jsr = new JSONArray();
					for (int i = 0; i < jary.length(); i++) {
						JSONArray temmauto = jary.getJSONArray(i);
						int indx = (Integer) temmauto.get(0);
						String vl = (String) temmauto.get(1);
						jb = new JSONObject();
						jb.put("id", String.valueOf(indx));// jobj
						jb.put("label", vl);// jobj
						jsr.put(jb);// jarray
					}
					locIdrcty = jsr.toString();
				} catch (Exception e) {
					System.out.println("Exception e : " + e);
				} finally {
					jb = null; jsr = null; jary = null;
				}   			  	 
			}
		}
		return SUCCESS;
	}
public String getLocIdrcty() {
	return locIdrcty;
}
public void setLocIdrcty(String locIdrcty) {
	this.locIdrcty = locIdrcty;
}
public String getLocIdcountrytyp() {
	return locIdcountrytyp;
}
public void setLocIdcountrytyp(String locIdcountrytyp) {
	this.locIdcountrytyp = locIdcountrytyp;
}
public String getLocIdStatetyp() {
	return locIdStatetyp;
}
public void setLocIdStatetyp(String locIdStatetyp) {
	this.locIdStatetyp = locIdStatetyp;
}
public String getLocIdskillstyp() {
	return locIdskillstyp;
}
public void setLocIdskillstyp(String locIdskillstyp) {
	this.locIdskillstyp = locIdskillstyp;
}
	

}
