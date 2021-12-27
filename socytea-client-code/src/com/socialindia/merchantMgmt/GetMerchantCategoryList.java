package com.socialindia.merchantMgmt;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class GetMerchantCategoryList  extends ActionSupport implements
SessionAware, ServletRequestAware, ServletResponseAware,
ServletContextAware{

	private static final long serialVersionUID = 1L;
	 JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	  private Common commonObj = new CommonDao();
	  
	  private String merchantCategoryval=null;
	  
	  
	public String execute() {
			 JSONObject locObjRecvJson = null;//Receive String to json	
				JSONObject locObjRecvdataJson = null;// Receive Data Json	
				String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	
				 try{
	    		  //country,city,state
	    		  	obj.put("servicecode", "SI6416");
	    			data.put("data", obj);
	    			String jsonTextFinal_Countryid = data.toString().trim();
	    			jsonTextFinal_Countryid=EncDecrypt.encrypt(jsonTextFinal_Countryid);
	    			String tempcountry = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Countryid);
	    			System.out.println("tempcountry-------------------"+tempcountry);
	    			String tempcountryUrl = getText("socialindia.merchantMng.getMerchantCategory");
	    	    	  String responsecountry = commonObj.jsonRequest(tempcountryUrl, tempcountry);
	    	    	  System.out.println("--response1-- "+responsecountry);
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
	    				 		
	    				 		merchantCategoryval=jsrcountry.toString();
	    				 		System.out.println("Merchant Category------- "+merchantCategoryval);
	    				 		}catch(Exception e){
	    				 			System.out.println("Exception e : "+e);
	    				 		}finally{
	    				 			 jbcountry=null;jsrcountry= null;
	    				 		}
	    	    	  }
	    	    	  }
				 }catch (Exception ex){
					 ex.printStackTrace();
				 }
			return SUCCESS;
			
	}
	
	
	
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	public String getMerchantCategoryval() {
		return merchantCategoryval;
	}
	public void setMerchantCategoryval(String merchantCategoryval) {
		this.merchantCategoryval = merchantCategoryval;
	}
}