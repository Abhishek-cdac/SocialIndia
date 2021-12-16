package com.socialindia.generalmgnt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.CityMasterTblVo;
import com.letspay.vo.StateMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
public class getCityCodeMethod extends ActionSupport{	
	private static final long serialVersionUID = 1L;
	private int stateidkey;
	public List<StateMasterTblVo> statmstr = new ArrayList<StateMasterTblVo>();
	public List<CityMasterTblVo> cityMstr = new ArrayList<CityMasterTblVo>();
	private String locIdCitytyp;
	private Common commonObj = new CommonDao();
	  JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	 public String getCityDetail() {		   
		    JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json
		    try {
		    	System.out.println("citylist loading....");		    	
		    	//String ivrparams="ivrparams={\"servicecode\":\"SI1003\",\"stateid\":\""+stateidkey+"\"}";
		    	data.put("servicecode", "SI1003");
		    	data.put("stateid", String.valueOf(stateidkey));				
				String jsonTextFinal1 = data.toString();
				jsonTextFinal1 = EncDecrypt.encrypt(jsonTextFinal1);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal1);						    	
		    	String url=getText("staffmanagement.citylist.creation");
		    	String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	  String response = commonObj.jsonRequest(url, temp);
		    	  System.out.println("city Response--- "+response);
		    	  if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
		    		  boolean ivIsJson = Commonutility.toCheckIsJSON(response);
		    		  if (ivIsJson) {
		    			  locObjRecvJson = new JSONObject(response);
				    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");				    	  
				    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");				    	  
				    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");				    	  
				    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");						
				    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");		    		  
				    	  JSONArray jary=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");		    		  
				    	  String fin="";
				    	  JSONArray jsrcity= null;JSONObject jbcity=null;
	  						try{			 		
	  							jsrcity= new JSONArray(); 
	  							for(int i=0;i<jary.length();i++){			 			
	  								JSONArray temmauto = jary.getJSONArray(i);    					
	  								int indx=(Integer)temmauto.get(0);    					
	  								String vl=(String)temmauto.get(1);    					  				
	  								jbcity=new JSONObject();
	  								jbcity.put("id", String.valueOf(indx));//jobj
	  								vl =vl.replace("'", "%27");	  				 			
	  								jbcity.put("label", vl);//jobj
	  								jsrcity.put(jbcity);//jarray			 						 	
	  							}	  				 		
	  							locIdCitytyp=jsrcity.toString();	  							
	  				 		}catch(Exception e){
	  				 			System.out.println("Exception Found getCityCodeMethod getCityDetail() : "+e);
	  				 		}finally{
	  				 			 jbcity=null;jsrcity= null;
	  				 		}
	  					for (int i = 0; i < jary.length(); i++) {
		    				JSONArray temm;			
		    				try {
		    					temm = jary.getJSONArray(i);		    					
		    					int indx=(Integer)temm.get(0);		    					
		    					String vl=(String)temm.get(1);		    					
		    					cityMstr.add(new CityMasterTblVo(indx,vl));		    					
		    				} catch (Exception e) {		    					
		    				}finally{}		    								
		    			}	
		    			ActionContext.getContext().getSession().put("cityList", cityMstr);
		    		  }		    		  
		    	  }		    	  
		    	  //{"message":"success","respcode":"0000","data":{"datalst":[]},"servicecode":"SI1003","statuscode":"0"}
		    	  //{"message":"success","respcode":"0000","data":{"cntrylist":[[63,"Andaman Nicobar"],[64,"Andhra Pradesh"],[65,"Arunachal Pradesh"],[66,"Assam"],[67,"Bihar"],[68,"Chandigarh"],[69,"Chattisgarh"],[70,"Dadra & Nagar Haveli"],[71,"Daman & Diu"],[72,"Delhi"],[73,"Goa"],[74,"Gujarat"],[75,"Haryana"],[76,"Himachal Pradesh"],[77,"Jammu & Kashmir"],[78,"Jharkhand"],[79,"Karnataka"],[80,"Kerala"],[81,"Lakshadweep"],[82,"Madhya Pradesh"],[83,"Maharashtra"],[84,"Manipur"],[85,"Meghalaya"],[86,"Mizoram"],[87,"Nagaland"],[88,"Orissa"],[89,"Puducherry"],[90,"Punjab"],[91,"Rajasthan"],[92,"Sikkim"],[93,"Tamil Nadu"],[94,"Tripura"],[95,"Uttar Pradesh"],[96,"Uttarakhand"],[97,"West Bengal"]]},"servicecode":"SI1002","statuscode":"0"}		    		    				
		    } catch (Exception ex) {
		      System.out.println("Exception Found getCityCodeMethod getCityDetail() : " + ex);
		    }finally{}
		    return SUCCESS;
		  }
 public String getCityDetailEdit(int staffCityId) {		   
		    JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json
		 //Map sessionMap = ActionContext.getContext().getSession();
		    try {
		    	System.out.println("citylist loading....");		    	
		    	//String ivrparams="ivrparams={\"servicecode\":\"SI1003\",\"stateid\":\""+stateidkey+"\"}";
		    	data.put("servicecode", "SI1003");
		    	data.put("stateid", String.valueOf(staffCityId));				
				String jsonTextFinal1 = data.toString();
				jsonTextFinal1 = EncDecrypt.encrypt(jsonTextFinal1);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal1);
				System.out.println("=====jsonText=====" + jsonTextFinal1);
		    	
		    	String url=getText("staffmanagement.citylist.creation");
		    	String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	  String response = commonObj.jsonRequest(url, temp);
		    	  System.out.println("city Response--- "+response);
		    	  if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
		    		  boolean ivIsJson = Commonutility.toCheckIsJSON(response);
		    		  if (ivIsJson) {
		    			  locObjRecvJson = new JSONObject(response);
				    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");				    	  
				    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");				    	  
				    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");				    	  
				    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");						
				    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");		    		  
					      JSONArray jary=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");		    		  
					      String fin="";
					      for (int i = 0; i < jary.length(); i++) {
					    	  JSONArray temm;			
					    	  try {
		    					temm = jary.getJSONArray(i);		    					
		    					int indx=(Integer)temm.get(0);		    					
		    					String vl=(String)temm.get(1);		    					
		    					cityMstr.add(new CityMasterTblVo(indx,vl));		    					
		    				} catch (Exception e) {		    					
		    				}finally{}		    							
					      }	
		    			ActionContext.getContext().getSession().put("cityList", cityMstr);
		    		  }		    		  
		    	  }		    	 		    	 		    		      	
		    } catch (Exception ex) {
		    	 System.out.println("Exception Found getCityCodeMethod getCityDetailEdit() : " + ex);
		    }
		    return SUCCESS;
		  }
	public int getStateidkey() {
		return stateidkey;
	}

	public void setStateidkey(int stateidkey) {
		this.stateidkey = stateidkey;
	}

	public List<CityMasterTblVo> getCityMstr() {
		return cityMstr;
	}

	public void setCityMstr(List<CityMasterTblVo> cityMstr) {
		this.cityMstr = cityMstr;
	}

	public List<StateMasterTblVo> getStatmstr() {
		return statmstr;
	}
	public void setStatmstr(List<StateMasterTblVo> statmstr) {
		this.statmstr = statmstr;
	}
	public String getLocIdCitytyp() {
		return locIdCitytyp;
	}
	public void setLocIdCitytyp(String locIdCitytyp) {
		this.locIdCitytyp = locIdCitytyp;
	}
	 

}
