package com.socialindia.generalmgnt;

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

public class getCountrycodeMethod extends ActionSupport{
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
	    	
	    		  //country,city,state
	    		  	obj.put("servicecode", "SI1001");
	    			
	    			data.put("data", obj);
	    			String jsonTextFinal_Countryid = data.toString().trim();
	    			jsonTextFinal_Countryid=EncDecrypt.encrypt(jsonTextFinal_Countryid);
	    			String tempcountry = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Countryid);
	    		//	toCheckService(postparam+URLEncoder.encode(postparamval),srvcUrl);	    			
	    			String tempcountryUrl = getText("socialindia.countrylist.creation");
	    			System.out.println("finalUrl : " + tempcountryUrl);	
	    	    	  String responsecountry = commonObj.jsonRequest(tempcountryUrl, tempcountry);
	    	    	  System.out.println("Response : "+responsecountry);
	    	    	  if(responsecountry!=null && !responsecountry.equalsIgnoreCase("null") && responsecountry.length()>0){
	    	    		  boolean ivIsJsonCountry = Commonutility.toCheckIsJSON(responsecountry);
	    	    		  if (ivIsJsonCountry) {
	    	    			  locObjRecvJson = new JSONObject(responsecountry);

	    			    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");
	    			    	  
	    			    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
	    			    	  
	    			    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
	    			    	  
	    			    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
	    					
	    			    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");
	    	    		  
	    				JSONArray jary=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");
	    			//	JSONArray jaryidprof=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst1");    		
	    	    		  JSONArray jsrcountry= null;JSONObject jbcountry=null;
	    					try{			 		
	    				 		 jsrcountry= new JSONArray(); 
	    				 		for(int i=0;i<jary.length();i++){			 			
	    				 			JSONArray temmauto = jary.getJSONArray(i);    					
	    	    					int indx=(Integer)temmauto.get(0);    					
	    	    					String vl=(String)temmauto.get(1);    					  				
	    				 			jbcountry=new JSONObject();
	    				 			jbcountry.put("id", String.valueOf(indx));//jobj
	    				 			vl =vl.replace("'", "%27");
	    				 			
	    				 			jbcountry.put("label", vl);//jobj
	    				 			jsrcountry.put(jbcountry);//jarray			 						 	
	    				 		}
	    				 		
	    				 		locIdcountrytyp=jsrcountry.toString();
	    				 		System.out.println("country-- "+locIdcountrytyp);
	    				 		}catch(Exception e){
	    				 			System.out.println("Exception e : "+e);
	    				 		}finally{
	    				 			 jbcountry=null;jsrcountry= null;
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
