package com.socialindia.committeeMgmt;

import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.TownshipMstTbl;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.signUpAction;

public class createCommittee extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Log log = null;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	TownshipMstTbl townShipMst =new TownshipMstTbl();
	private String township_List;
	private String committee_List;
	private int townshipid;
	private String ivrTwsipid;//New Committee creation via shortcut from society mgmt
	private String ivrTwsipname; //New Committee creation via shortcut from society mgmt
	private String ivrSwityid;//New Committee creation via shortcut from society mgmt
	private String ivrSwityname;//New Committee creation via shortcut from society mgmt
	public String execute(){		
		try{
			log=new Log();
			log.logMessage("Step 1 : Create committee will [start]", "info", createCommittee.class);
			data.put("servicecode", "SI0035");
			data.put("data", obj);
			String jsonTextFinal = data.toString();			
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("townShipMgmt.management.Master.List.Action");	
			log.logMessage("Service Url : "+finalUrl, "info", createCommittee.class);
			String response = commonObj.jsonRequest(finalUrl, temp);			
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;
	    	JSONObject locObjRecvJson = null;
	    	JSONObject locObjRecvdataJson = null;
			if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
	    		if (ivIsJson) {
	    			  log.logMessage("Step 2 : JSON Object Found", "info", createCommittee.class);
	    			  locObjRecvJson = new JSONObject(response);
			    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");			    	  
			    	  ivrresponsecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");			    	  
			    	  ivrmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");			    	  
			    	  ivrstatuscode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");					
			    	  locObjRecvdataJson = (JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");	    		  
			    	  JSONArray jary = (JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");
			    	  JSONArray jsrcountry= null;JSONObject jbcountry=null;
			    	  JSONArray temm = null;
			    	  try {
			    		  jsrcountry= new JSONArray(); 
			    		  for (int i = 0; i < jary.length(); i++) {
			    			temm = null;
							temm = jary.getJSONArray(i);
							int indx = (Integer) temm.get(0);
							String vl = (String) temm.get(1);
							jbcountry = new JSONObject();
							jbcountry.put("id", String.valueOf(indx));// jobj
							vl = vl.replace("'", "%27");
							jbcountry.put("label", vl);// jobj
							jsrcountry.put(jbcountry);// jarray
			    		  }
			    		  township_List=jsrcountry.toString();	    			
	    				} catch (Exception e) {	    					
	    					log.logMessage("step -1 : Exception in in get country flow emailFlow 1 :  " + e, "error",signUpAction.class);	    					
	    				}finally{
	    					jsrcountry= null;jbcountry=null; temm = null;
	    				}	    			
	    		  }
			 }
			Map currentSession = ActionContext.getContext().getSession();
			if(Commonutility.checkempty(ivrTwsipid)){
				currentSession.put("ivrTwsipid", ivrTwsipid);
			}
			if(Commonutility.checkempty(ivrTwsipname)){
				currentSession.put("ivrTwsipname", ivrTwsipname);
			}	
			if(Commonutility.checkempty(ivrSwityid)){
				currentSession.put("ivrSwityid", ivrSwityid);
			}
			if(Commonutility.checkempty(ivrSwityname)){
				currentSession.put("ivrSwityname", ivrSwityname);
			}	
		}catch (Exception e) {
			System.out.println("Exception found in createCommittee.class : "+e);
			log.logMessage("step -1 : Exception Found 2 :  " + e, "error",signUpAction.class);
		}		 
		 	JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			//JSONObject locObjRspdataJson = null;// Response Data Json		 
		    try {
		    	System.out.println("step 3 : Committee List Fetch");	
		    	log.logMessage("step 3 : Committee List Fetch", "info",signUpAction.class);
		    	data.put("servicecode", "SI0036");
				data.put("data", obj);
				String jsonTextFinal = data.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("committeeeMgmt.management.Master.List.Action");
				String response = commonObj.jsonRequest(finalUrl, temp);
		    	String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	String response1 = commonObj.jsonRequest(finalUrl, temp);
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
				    	  JSONArray jsrcountry = null;JSONObject jbcountry = null;JSONArray temm = null;	
				    	  try{
				    		  jsrcountry= new JSONArray(); 
				    		  for (int i = 0; i < jary.length(); i++) {
				    			  temm = null;
				    			  temm = jary.getJSONArray(i);		    					
				    			  int indx=(Integer)temm.get(0);		    					
				    			  String vl=(String)temm.get(1);
				    			  jbcountry=new JSONObject();
						 		  jbcountry.put("id", String.valueOf(indx));//jobj
						 		  vl =vl.replace("'", "%27");
						 		  jbcountry.put("label", vl);//jobj
						 		  jsrcountry.put(jbcountry);//jarray
				    		  }
				    		  committee_List=jsrcountry.toString();		    			
		    				} catch (Exception e) {		    					
		    				}finally{
		    					jsrcountry = null;jbcountry = null;temm = null;
		    				}		    										    		
		    		  }
		    		  
		    	  }
		    } catch (Exception ex) {		     
		    	System.out.println("Exception found in createCommittee.class : "+ex);
				log.logMessage("step -1 : Exception Found 3 :  " + ex, "error",signUpAction.class);
		    }finally{
		    	locObjRecvdataJson = null; locObjRecvJson = null;
		    }
		return SUCCESS;
	}
	public TownshipMstTbl getTownShipMst() {
		return townShipMst;
	}
	public void setTownShipMst(TownshipMstTbl townShipMst) {
		this.townShipMst = townShipMst;
	}
	public String getTownship_List() {
		return township_List;
	}
	public void setTownship_List(String township_List) {
		this.township_List = township_List;
	}
	public int getTownshipid() {
		return townshipid;
	}
	public void setTownshipid(int townshipid) {
		this.townshipid = townshipid;
	}
	public String getCommittee_List() {
		return committee_List;
	}
	public void setCommittee_List(String committee_List) {
		this.committee_List = committee_List;
	}
	
	public String getIvrTwsipid() {
		return ivrTwsipid;
	}
	public void setIvrTwsipid(String ivrTwsipid) {
		this.ivrTwsipid = ivrTwsipid;
	}
	public String getIvrTwsipname() {
		return ivrTwsipname;
	}
	public void setIvrTwsipname(String ivrTwsipname) {
		this.ivrTwsipname = ivrTwsipname;
	}
	public String getIvrSwityid() {
		return ivrSwityid;
	}
	public void setIvrSwityid(String ivrSwityid) {
		this.ivrSwityid = ivrSwityid;
	}
	public String getIvrSwityname() {
		return ivrSwityname;
	}
	public void setIvrSwityname(String ivrSwityname) {
		this.ivrSwityname = ivrSwityname;
	}

	
}
