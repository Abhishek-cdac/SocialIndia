package com.socialindia.committeeMgmt;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class getCommitteeOnload extends  ActionSupport {
	Log log=new Log();
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private String committee_List;
	String ivrservicecode=null;
	String ivrresponsecode=null;
	public String execute(){
	
	   try {
	    	System.out.println("committee list loading....");
	    	data.put("servicecode", "SI0036");

			data.put("data", obj);
			String jsonTextFinal = data.toString();
			System.out.println("===jsonTextFinal=data========" + jsonTextFinal);

			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			System.out.println("=====jsonText=====" + jsonTextFinal);

			String finalUrl = getText("committeeeMgmt.management.Master.List.Action");
			System.out.println("=======finalUrl====" + finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);
	    	String ivrservicecode=null;
	    	String ivrresponsecode=null;
	    	String ivrmsg=null;
	    	String ivrstatuscode=null;
	    	 JSONObject locObjRecvJson = null;
	    	 JSONObject locObjRecvdataJson = null;
	    	  String response1 = commonObj.jsonRequest(finalUrl, temp);
	    	  System.out.println("response-- "+response);
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
	    		  System.out.println(jary);
	    		  JSONArray jsrcountry= null;JSONObject jbcountry=null;
	    		  try{
						jsrcountry = new JSONArray();
						for (int i = 0; i < jary.length(); i++) {
							JSONArray temm;
							temm = jary.getJSONArray(i);
							int indx = (Integer) temm.get(0);
							String vl = (String) temm.get(1);
							jbcountry = new JSONObject();
							jbcountry.put("id", String.valueOf(indx));// jobj
							vl = vl.replace("'", "%27");
							jbcountry.put("label", vl);// jobj
							jsrcountry.put(jbcountry);// jarray
						}
						committee_List=jsrcountry.toString();	    		
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {

					}
	    								
	    			
	    		  }
	    		  
	    	  }
	    } catch (Exception ex) {
	      // TODO: handle exception
	      System.out.println("Exception in get state----->> " + ex);
	    }
	   return SUCCESS;
}
	public String getCommittee_List() {
		return committee_List;
	}
	public void setCommittee_List(String committee_List) {
		this.committee_List = committee_List;
	}
}
