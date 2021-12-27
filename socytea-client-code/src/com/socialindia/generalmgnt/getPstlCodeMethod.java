package com.socialindia.generalmgnt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.StateMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.IDCardMasterTblVO;
import com.socialindia.generalmgnt.persistance.PostalCodeMasterTblVO;
import com.socialindia.generalmgnt.persistance.StaffCategoryMasterTblVO;
import com.socialindia.login.EncDecrypt;

public class getPstlCodeMethod extends ActionSupport{
	private int cityidkey;
	private String getIdCardDetail;
	  public List<StateMasterTblVo> statmstr = new ArrayList<StateMasterTblVo>();
	  public List<StaffCategoryMasterTblVO> categoryMstr = new ArrayList<StaffCategoryMasterTblVO>();
	  public List<IDCardMasterTblVO> idcardMstr = new ArrayList<IDCardMasterTblVO>();
	  public List<PostalCodeMasterTblVO> pstlMstr = new ArrayList<PostalCodeMasterTblVO>();
	  private Common commonObj = new CommonDao();
	  JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	private String locIdpstlcodetyp;
	public String getPostalcodeDetail() {
		 JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json
		   
		    try {
		    	System.out.println("Idcardlist loading....");
		    	String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	  obj.put("servicecode", "SI1004");
				obj.put("cityid",cityidkey);
				data.put("data", obj);
				String jsonTextFinal_pcode = data.toString().trim();
				jsonTextFinal_pcode=EncDecrypt.encrypt(jsonTextFinal_pcode);
				String temppstlc = "ivrparams=" + URLEncoder.encode(jsonTextFinal_pcode);
			//	toCheckService(postparam+URLEncoder.encode(postparamval),srvcUrl);
				System.out.println("=====jsonText11=postal====" + jsonTextFinal_pcode);
				String finalUrl_pstlcode = getText("staffmanagement.pstlcodelist.creation");
				System.out.println("====finalUrl====" + finalUrl_pstlcode);	
		    	  String responsepstl = commonObj.jsonRequest(finalUrl_pstlcode, temppstlc);
		    	  System.out.println("--responsepstl-- "+responsepstl);
		    	  if(responsepstl!=null && !responsepstl.equalsIgnoreCase("null") && responsepstl.length()>0){
		    		  boolean ivIsJson = Commonutility.toCheckIsJSON(responsepstl);
		    		  if (ivIsJson) {
		    			  locObjRecvJson = new JSONObject(responsepstl);

				    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");
				    	  
				    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
				    	  
				    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
				    	  
				    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
						
				    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");
		    		  
					JSONArray jary=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");
				//	JSONArray jaryidprof=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst1");
		    		  String fin="";
		    		  JSONArray jsrpstl= null;JSONObject jbpstl=null;
	  					try{			 		
	  				 		 jsrpstl= new JSONArray(); 
	  				 		for(int i=0;i<jary.length();i++){			 			
	  				 			JSONArray temmauto = jary.getJSONArray(i);    					
	  	    					int indx=(Integer)temmauto.get(0);    					
	  	    					String vl=(String)temmauto.get(1);    					  				
	  				 			jbpstl=new JSONObject();
	  				 			jbpstl.put("id", String.valueOf(indx));//jobj
	  				 			vl =vl.replace("'", "%27");
	  				 			
	  				 			jbpstl.put("label", vl);//jobj
	  				 			jsrpstl.put(jbpstl);//jarray			 						 	
	  				 		}
	  				 		
	  				 		locIdpstlcodetyp=jsrpstl.toString();
	  				 		System.out.println("state-lllll::::- "+locIdpstlcodetyp);
	  				 		}catch(Exception e){
	  				 			System.out.println("Exception e : "+e);
	  				 		}finally{
	  				 			 jbpstl=null;jsrpstl= null;
	  				 		}
		    			for (int i = 0; i < jary.length(); i++) {
		    				JSONArray temm;			
		    				try {
		    					temm = jary.getJSONArray(i);
		    					
		    					int indx=(Integer)temm.get(0);
		    					
		    					String vl=(String)temm.get(1);
		    					
		    					pstlMstr.add(new PostalCodeMasterTblVO(indx,vl));
		    					//fin+="<option value=\""+indx+"\">"+vl+"</option>";
		    					//System.out.println("<option value=\""+indx+"\">"+vl+"</option>");	
		    				} catch (Exception e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}finally{
		    					
		    				}
		    								
		    			}	
		    			
		    					    	  
		    	  }
		    	  }
		    	  System.out.println("size== "+pstlMstr.size());
		    	  ActionContext.getContext().getSession().put("pstlcodeval", pstlMstr);
		    		  
		    		  
		    	  
		    	  

		    	  //{"message":"success","respcode":"0000","data":{"datalst":[]},"servicecode":"SI1003","statuscode":"0"}
		    	  //{"message":"success","respcode":"0000","data":{"cntrylist":[[63,"Andaman Nicobar"],[64,"Andhra Pradesh"],[65,"Arunachal Pradesh"],[66,"Assam"],[67,"Bihar"],[68,"Chandigarh"],[69,"Chattisgarh"],[70,"Dadra & Nagar Haveli"],[71,"Daman & Diu"],[72,"Delhi"],[73,"Goa"],[74,"Gujarat"],[75,"Haryana"],[76,"Himachal Pradesh"],[77,"Jammu & Kashmir"],[78,"Jharkhand"],[79,"Karnataka"],[80,"Kerala"],[81,"Lakshadweep"],[82,"Madhya Pradesh"],[83,"Maharashtra"],[84,"Manipur"],[85,"Meghalaya"],[86,"Mizoram"],[87,"Nagaland"],[88,"Orissa"],[89,"Puducherry"],[90,"Punjab"],[91,"Rajasthan"],[92,"Sikkim"],[93,"Tamil Nadu"],[94,"Tripura"],[95,"Uttar Pradesh"],[96,"Uttarakhand"],[97,"West Bengal"]]},"servicecode":"SI1002","statuscode":"0"}
		    

		      	
				
		    } catch (Exception ex) {
		      // TODO: handle exception
		      System.out.println("Exception in get state----->> " + ex);
		    }
		  
		    return SUCCESS;
		  }
	public String getPostalcodeDetailEdit(int staffCityId) {
		 JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json
		   
		    try {
		    	System.out.println("postalcode loading....");
		    	String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	  obj.put("servicecode", "SI1004");
				obj.put("cityid",staffCityId);
				data.put("data", obj);
				String jsonTextFinal_pcode = data.toString().trim();
				jsonTextFinal_pcode=EncDecrypt.encrypt(jsonTextFinal_pcode);
				String temppstlc = "ivrparams=" + URLEncoder.encode(jsonTextFinal_pcode);
			//	toCheckService(postparam+URLEncoder.encode(postparamval),srvcUrl);
				System.out.println("=====jsonText11=postal====" + jsonTextFinal_pcode);
				String finalUrl_pstlcode = getText("staffmanagement.pstlcodelist.creation");
				System.out.println("====finalUrl====" + finalUrl_pstlcode);	
		    	  String responsepstl = commonObj.jsonRequest(finalUrl_pstlcode, temppstlc);
		    	  System.out.println("--responsepstl-- "+responsepstl);
		    	  if(responsepstl!=null && !responsepstl.equalsIgnoreCase("null") && responsepstl.length()>0){
		    		  boolean ivIsJson = Commonutility.toCheckIsJSON(responsepstl);
		    		  if (ivIsJson) {
		    			  locObjRecvJson = new JSONObject(responsepstl);

				    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");
				    	  
				    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
				    	  
				    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
				    	  
				    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
						
				    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");
		    		  
					JSONArray jary=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");
				//	JSONArray jaryidprof=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst1");
		    		  String fin="";
		    			for (int i = 0; i < jary.length(); i++) {
		    				JSONArray temm;			
		    				try {
		    					temm = jary.getJSONArray(i);
		    					
		    					int indx=(Integer)temm.get(0);
		    					
		    					String vl=(String)temm.get(1);
		    					
		    					pstlMstr.add(new PostalCodeMasterTblVO(indx,vl));
		    					//fin+="<option value=\""+indx+"\">"+vl+"</option>";
		    					//System.out.println("<option value=\""+indx+"\">"+vl+"</option>");	
		    				} catch (Exception e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}finally{
		    					
		    				}
		    								
		    			}	
		    			
		    					    	  
		    	  }
		    	  }
		    	  System.out.println("size== "+pstlMstr.size());
		    	  ActionContext.getContext().getSession().put("pstlcodeval", pstlMstr);
		    		  
		    		  
		    	  
		    	  

		    	  //{"message":"success","respcode":"0000","data":{"datalst":[]},"servicecode":"SI1003","statuscode":"0"}
		    	  //{"message":"success","respcode":"0000","data":{"cntrylist":[[63,"Andaman Nicobar"],[64,"Andhra Pradesh"],[65,"Arunachal Pradesh"],[66,"Assam"],[67,"Bihar"],[68,"Chandigarh"],[69,"Chattisgarh"],[70,"Dadra & Nagar Haveli"],[71,"Daman & Diu"],[72,"Delhi"],[73,"Goa"],[74,"Gujarat"],[75,"Haryana"],[76,"Himachal Pradesh"],[77,"Jammu & Kashmir"],[78,"Jharkhand"],[79,"Karnataka"],[80,"Kerala"],[81,"Lakshadweep"],[82,"Madhya Pradesh"],[83,"Maharashtra"],[84,"Manipur"],[85,"Meghalaya"],[86,"Mizoram"],[87,"Nagaland"],[88,"Orissa"],[89,"Puducherry"],[90,"Punjab"],[91,"Rajasthan"],[92,"Sikkim"],[93,"Tamil Nadu"],[94,"Tripura"],[95,"Uttar Pradesh"],[96,"Uttarakhand"],[97,"West Bengal"]]},"servicecode":"SI1002","statuscode":"0"}
		    

		      	
				
		    } catch (Exception ex) {
		      // TODO: handle exception
		      System.out.println("Exception in get state----->> " + ex);
		    }
		  
		    return SUCCESS;
		  }
	public List<IDCardMasterTblVO> getIdcardMstr() {
		return idcardMstr;
	}

	public void setIdcardMstr(List<IDCardMasterTblVO> idcardMstr) {
		this.idcardMstr = idcardMstr;
	}

	public int getCityidkey() {
		return cityidkey;
	}

	public void setCityidkey(int cityidkey) {
		this.cityidkey = cityidkey;
	}

	public List<PostalCodeMasterTblVO> getPstlMstr() {
		return pstlMstr;
	}

	public void setPstlMstr(List<PostalCodeMasterTblVO> pstlMstr) {
		this.pstlMstr = pstlMstr;
	}
	public String getLocIdpstlcodetyp() {
		return locIdpstlcodetyp;
	}
	public void setLocIdpstlcodetyp(String locIdpstlcodetyp) {
		this.locIdpstlcodetyp = locIdpstlcodetyp;
	}
	
	
	
}
