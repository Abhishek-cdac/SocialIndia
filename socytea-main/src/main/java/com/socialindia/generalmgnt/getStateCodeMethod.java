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


public class getStateCodeMethod extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int countryidkey;
	private String locIdStatetyp;
	  public List<StateMasterTblVo> statmstr = new ArrayList<StateMasterTblVo>();
	  public List<CityMasterTblVo> cityMstr = new ArrayList<CityMasterTblVo>();
	 
	  private Common commonObj = new CommonDao();
	  JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	 public String getStateDetail() {
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject locObjRecvdataJson = null;// Receive Data Json
		JSONObject locObjRspdataJson = null;// Response Data Json
		try {
			Commonutility.toWriteConsole("Step 1 : Get States [Start]");
		    //String ivrparams="ivrparams={\"servicecode\":\"SI1002\",\"countryid\":\""+countryidkey+"\"}";
			data.put("servicecode", "SI1002");
			data.put("countryid", String.valueOf(countryidkey));			
			String jsonTextFinal1 = data.toString();
			jsonTextFinal1 = EncDecrypt.encrypt(jsonTextFinal1);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal1);			
			String url = getText("staffmanagement.statelist.creation");
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;
			String response = commonObj.jsonRequest(url, temp);			
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
		    		JSONArray jsrstate= null;JSONObject jbstate=null;
					try {
						jsrstate = new JSONArray();
						for (int i = 0; i < jary.length(); i++) {
							JSONArray temmauto = jary.getJSONArray(i);
							int indx = (Integer) temmauto.get(0);
							String vl = (String) temmauto.get(1);
							jbstate = new JSONObject();
							jbstate.put("id", String.valueOf(indx));// jobj
							vl = vl.replace("'", "%27");
							jbstate.put("label", vl);// jobj
							jsrstate.put(jbstate);// jarray
						}
						locIdStatetyp = jsrstate.toString();						
					} catch (Exception e) {						
					} finally {
						jbstate = null;
						jsrstate = null;
					}
					for (int i = 0; i < jary.length(); i++) {
						JSONArray temm;
						try {
							temm = jary.getJSONArray(i);
							int indx = (Integer) temm.get(0);
							String vl = (String) temm.get(1);
							statmstr.add(new StateMasterTblVo(indx, vl));
							// fin+="<option value=\""+indx+"\">"+vl+"</option>";
							// System.out.println("<option value=\""+indx+"\">"+vl+"</option>");
						} catch (Exception e) {								
						} finally {

						}

					}
					ActionContext.getContext().getSession().put("stateList", statmstr);
				}

			}
		    Commonutility.toWriteConsole("Step 2 : Get States [End]");
		} catch (Exception ex) {
			 Commonutility.toWriteConsole("Step -1 : Get States [Exception] : "+ex);
		}
		return SUCCESS;
	}

	public String getStateDetailEdit(int staffCountryId) {
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject locObjRecvdataJson = null;// Receive Data Json
		JSONObject locObjRspdataJson = null;// Response Data Json
		    try {
		    	System.out.println("statelist loading....");		    	
		    	//String ivrparams="ivrparams={\"servicecode\":\"SI1002\",\"countryid\":\""+countryidkey+"\"}";
		    	data.put("servicecode", "SI1002");
		    	data.put("countryid", String.valueOf(staffCountryId));				
				String jsonTextFinal1 = data.toString();
				jsonTextFinal1 = EncDecrypt.encrypt(jsonTextFinal1);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal1);				
		    	String url=getText("staffmanagement.statelist.creation");
		    	String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	  String response = commonObj.jsonRequest(url, temp);
		    	  System.out.println("response-- "+response);
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
		    					statmstr.add(new StateMasterTblVo(indx,vl));		    					
		    				} catch (Exception e) {		    					
		    				}finally{		    				
		    				}		    							
		    			}	
		    			ActionContext.getContext().getSession().put("stateList", statmstr);
		    		  }		    		  
		    	  }
		    } catch (Exception ex) {
		      // TODO: handle exception
		      System.out.println("Exception in get state----->> " + ex);
		    }
		    return SUCCESS;
		  }
	public int getCountryidkey() {
		return countryidkey;
	}
	public void setCountryidkey(int countryidkey) {
		this.countryidkey = countryidkey;
	}
	public List<StateMasterTblVo> getStatmstr() {
		return statmstr;
	}
	public void setStatmstr(List<StateMasterTblVo> statmstr) {
		this.statmstr = statmstr;
	}
	public String getLocIdStatetyp() {
		return locIdStatetyp;
	}
	public void setLocIdStatetyp(String locIdStatetyp) {
		this.locIdStatetyp = locIdStatetyp;
	}
	 

}
