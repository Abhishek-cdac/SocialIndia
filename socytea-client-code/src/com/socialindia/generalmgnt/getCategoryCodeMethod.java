package com.socialindia.generalmgnt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.GroupMasterTblVo;
import com.letspay.vo.StateMasterTblVo;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.IDCardMasterTblVO;
import com.socialindia.generalmgnt.persistance.PostalCodeMasterTblVO;
import com.socialindia.generalmgnt.persistance.StaffCategoryMasterTblVO;
import com.socialindia.login.EncDecrypt;

public class getCategoryCodeMethod extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int stateidkey;
	private String getIdCardDetail;
	  public List<StateMasterTblVo> statmstr = new ArrayList<StateMasterTblVo>();
	  public List<StaffCategoryMasterTblVO> categoryMstr = new ArrayList<StaffCategoryMasterTblVO>();
	  public List<IDCardMasterTblVO> idcardMstr = new ArrayList<IDCardMasterTblVO>();
	  public List<PostalCodeMasterTblVO> pstlMstr = new ArrayList<PostalCodeMasterTblVO>();
	  public List<GroupMasterTblVo> groupMstList = new ArrayList<GroupMasterTblVo>();
	  private Common commonObj = new CommonDao();
	  JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	  
	  JSONObject obj_idcar = new JSONObject();
	  JSONObject data_idcar = new JSONObject();	  
	  private String locIdCategory=null;
	  private String locIdCompany =null;
	  private String locIdgrouptyp;
	  private String uniqueId;
	  private String groupCode;
	 public String getCategoryDetail() {
		   JSONObject locObjRecvJson = null;//Receive String to json	
		   JSONObject locObjRecvdataJson = null;// Receive Data Json		
		   JSONObject locObjRspdataJson = null;// Response Data Json
		   JSONArray jsrcategory= null;JSONObject jbcategory=null;
			    try {
			    	System.out.println("categorylist loading....");			    	
			    	String ivrservicecode=null;
			    	String ivrresponsecode=null;
			    	String ivrmsg=null;
			    	String ivrstatuscode=null;
			    	obj.put("servicecode", "SI1011");					
					data.put("data", obj);
					String jsonTextFinal = data.toString().trim();
					jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
					String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				//	toCheckService(postparam+URLEncoder.encode(postparamval),srvcUrl);
					String finalUrl = getText("staffmanagement.categorylist.creation");
			    	  String response = commonObj.jsonRequest(finalUrl, temp);
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
					    	  jsrcategory=new JSONArray();
					    	  for (int i = 0; i < jary.length(); i++) {
					    		  JSONArray temm=null;			
			    				try {
			    					temm = jary.getJSONArray(i);			    					
			    					int indx=(Integer)temm.get(0);			    					
			    					String vl=(String)temm.get(1);			    					
			    					jbcategory=new JSONObject();
			    					jbcategory.put("id", String.valueOf(indx));//jobj
	    				 			vl =vl.replace("'", "%27");	    				 		
	    				 			jbcategory.put("label", vl);//jobj
	    				 			jsrcategory.put(jbcategory);//jarray				    					
	    				 			jbcategory=null;
			    				}			    				
			    				catch (Exception e) {			    					
			    				}finally{}			    						
			    			}		
			    			locIdCategory=jsrcategory.toString();
			    	  }
			    	}
				    	obj_idcar.put("servicecode", "SI1005");
						data_idcar.put("data", obj_idcar);
						String jsonTextFinal_Idcard = data_idcar.toString().trim();
						jsonTextFinal_Idcard=EncDecrypt.encrypt(jsonTextFinal_Idcard);
						String tempidcard = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Idcard);
					//	toCheckService(postparam+URLEncoder.encode(postparamval),srvcUrl);
						String finalUrl_pcode = getText("staffmanagement.idcardlist.creation");
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
				    		  String fin="";
				    			for (int i = 0; i < jary.length(); i++) {
				    				JSONArray temm;			
				    				try {
				    					temm = jary.getJSONArray(i);
				    					int indx=(Integer)temm.get(0);
				    					String vl=(String)temm.get(1);
				    					idcardMstr.add(new IDCardMasterTblVO(indx,vl));
				    				} catch (Exception e) {
				    					e.printStackTrace();
				    				}finally{
				    					
				    				}				    							
				    			}	
				    		}
				    	 }
				    	
			    	}
			    catch (Exception ex) {					    
					System.out.println("Exception in get state----->> " + ex);
			    }finally{
					 jsrcategory=null;
			    }		    
		    return SUCCESS;
		  }
	 public String getcompanylistdetail()
	 {
		 JSONObject locObjRecvJson = null;//Receive String to json	
		   JSONObject locObjRecvdataJson = null;// Receive Data Json		
		   JSONObject locObjRspdataJson = null;// Response Data Json
		   JSONArray jsrcategory= null;JSONObject jbcategory=null;
			    try {
			    	System.out.println("companylist loading....");			    	
			    	String ivrservicecode=null;
			    	String ivrresponsecode=null;
			    	String ivrmsg=null;
			    	String ivrstatuscode=null;
			    	obj.put("servicecode", "SI1013");					
					data.put("data", obj);
					String jsonTextFinal = data.toString().trim();
					jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
					String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				//	toCheckService(postparam+URLEncoder.encode(postparamval),srvcUrl);
					String finalUrl = getText("socialindia.companymgmt.companylistauto");
			    	  String response = commonObj.jsonRequest(finalUrl, temp);
			    	 System.out.println("_Response::: "+response);
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
					    	  jsrcategory=new JSONArray();
					    	  for (int i = 0; i < jary.length(); i++) {
					    		  JSONArray temm=null;			
			    				try {
			    					temm = jary.getJSONArray(i);			    					
			    					int indx=(Integer)temm.get(0);			    					
			    					String vl=(String)temm.get(1);			    					
			    					jbcategory=new JSONObject();
			    					jbcategory.put("id", String.valueOf(indx));//jobj
	    				 			vl =vl.replace("'", "%27");	    				 		
	    				 			jbcategory.put("label", vl);//jobj
	    				 			jsrcategory.put(jbcategory);//jarray				    					
	    				 			jbcategory=null;
			    				}			    				
			    				catch (Exception e) {			    					
			    				}finally{}			    						
			    			}		
			    			locIdCompany=jsrcategory.toString();
			    	  }
			    	}
				    	
			    	}
			    catch (Exception ex) {					    
					System.out.println("Exception in get state----->> " + ex);
			    }finally{
					 jsrcategory=null;
			    }		    
		    return SUCCESS;
	 }
	 public String groupType() throws Exception{
		  JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json
			System.out.println("========================groupType================");
			String ivrservicecode=null;
	    	String ivrresponsecode=null;
	    	String ivrmsg=null;
	    	String ivrstatuscode=null;	    
	    		  	obj.put("servicecode", "SI1014");	    				    			
	    			obj.put("grpid", uniqueId);
	    			data.put("data", obj);
	    			String jsonTextFinal_Countryid = data.toString().trim();
	    			jsonTextFinal_Countryid=EncDecrypt.encrypt(jsonTextFinal_Countryid);
	    			String tempcountry = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Countryid);	    			
	    			String tempcountryUrl = getText("socialindia.grouplist.creation");
	    			System.out.println("====finalUrl====" + tempcountryUrl);	
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
	    			//	JSONArray jaryidprof=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst1");    		
	    	    		  JSONArray jsrgroup= null;
	    	    		  JSONObject jbgroup=null;
	    	    		  int indx = 0;
	    	    		  String vl = null;
	    					try{			 		
	    						jsrgroup= new JSONArray(); 
	    				 		for(int i=0;i<jary.length();i++){			 			
	    				 			JSONArray temmauto = jary.getJSONArray(i);    					
	    	    					 indx=(Integer)temmauto.get(0);  
	    	    					 vl=(String)temmauto.get(1); 
	    	    					jbgroup=new JSONObject();
	    	    					jbgroup.put("id", String.valueOf(indx));//jobj
	    				 			vl =vl.replace("'", "%27");
//	    				 			Commonutility.toWriteConsole("groupname >>>>>>>>>>>>>>>>> "+vl);
//	    							if(vl!=null && vl.equalsIgnoreCase("Resident")){
//	    								continue;
//	    							}
	    				 			jbgroup.put("label", vl);//jobj
	    				 			jsrgroup.put(jbgroup);//jarray		
	    				 			groupMstList.add(new GroupMasterTblVo(vl, null, null, null,indx));
	    				 		}
	    				 		System.out.println("Group type-- "+groupMstList.size());
	    				 		}catch(Exception e){
	    				 			System.out.println("Exception e : "+e);
	    				 		}finally{
	    				 			jbgroup=null;jsrgroup= null;
	    				 		}
	    	    		  }
	    	    	  }
			return SUCCESS;		
	}
	 	
	public String getGetIdCardDetail() {
		return getIdCardDetail;
	}
	public void setGetIdCardDetail(String getIdCardDetail) {
		this.getIdCardDetail = getIdCardDetail;
	}
	public List<StaffCategoryMasterTblVO> getCategoryMstr() {
		return categoryMstr;
	}
	public void setCategoryMstr(List<StaffCategoryMasterTblVO> categoryMstr) {
		this.categoryMstr = categoryMstr;
	}
	public String getLocIdCategory() {
		return locIdCategory;
	}
	public void setLocIdCategory(String locIdCategory) {
		this.locIdCategory = locIdCategory;
	}

	public String getLocIdgrouptyp() {
		return locIdgrouptyp;
	}

	public void setLocIdgrouptyp(String locIdgrouptyp) {
		this.locIdgrouptyp = locIdgrouptyp;
	}

	public List<GroupMasterTblVo> getGroupMstList() {
		return groupMstList;
	}

	public void setGroupMstList(List<GroupMasterTblVo> groupMstList) {
		this.groupMstList = groupMstList;
	}
	
	public String getLocIdCompany() {
		return locIdCompany;
	}
	public void setLocIdCompany(String locIdCompany) {
		this.locIdCompany = locIdCompany;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	
}
