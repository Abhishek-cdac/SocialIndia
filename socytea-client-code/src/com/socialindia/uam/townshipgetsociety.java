package com.socialindia.uam;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.SocietyMstTbl;
import com.letspay.vo.CityMasterTblVo;
import com.letspay.vo.StateMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;


public class townshipgetsociety extends ActionSupport{
	private int townshipid;
	private String society_List;
	private String clfor;
	String rtnSucc=null;
	  public List<SocietyMstTbl> societylist = new ArrayList<SocietyMstTbl>();
	 
	  private Common commonObj = new CommonDao();
	  JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	 public String execute() {		 
		 JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json

		 //Map sessionMap = ActionContext.getContext().getSession();
		    try {
		    	System.out.println("statelist loading....");		    	
		    	String ivrparams="ivrparams={\"servicecode\":\"SI0021\",\"townshipid\":\""+townshipid+"\"}";		    	
		    	String url=getText("society.table.value.action");
		    	String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	  String response = commonObj.jsonRequest(url, ivrparams);
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
		    		  System.out.println(jary);
		    		  JSONArray jsrcountry= null;JSONObject jbcountry=null;
		    		  try{
		    		  jsrcountry= new JSONArray(); 
		    			for (int i = 0; i < jary.length(); i++) {
		    				JSONArray temm;	temm = jary.getJSONArray(i);
		    					
		    					int indx=(Integer)temm.get(0);
		    					
		    					String vl=(String)temm.get(1);
		    					societylist.add(new SocietyMstTbl(indx,vl));
		    					jbcountry=new JSONObject();
						 			jbcountry.put("id", String.valueOf(indx));//jobj
						 			vl =vl.replace("'", "%27");
						 			jbcountry.put("label", vl);//jobj
						 			jsrcountry.put(jbcountry);//jarray
		    			}	
		    			society_List=jsrcountry.toString();
		    				} catch (Exception e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}finally{
		    					
		    				}
		    						if(clfor!=null && clfor.equalsIgnoreCase("autocomp")){
		    							 rtnSucc="successauto";
		    						}else{
		    							 ActionContext.getContext().getSession().put("societylist", societylist);
		    							 rtnSucc="success";
		    						}		    		 
		    						
		    		  }
		    		  
		    	  }
		    } catch (Exception ex) {
		      // TODO: handle exception
		      System.out.println("Exception in get state----->> " + ex);
		    }
		    return rtnSucc;
		  }
	
	 
	public String getClfor() {
		return clfor;
	}


	public void setClfor(String clfor) {
		this.clfor = clfor;
	}


	public List<SocietyMstTbl> getSocietylist() {
		return societylist;
	}
	public void setSocietylist(List<SocietyMstTbl> societylist) {
		this.societylist = societylist;
	}

	public int getTownshipid() {
		return townshipid;
	}

	public void setTownshipid(int townshipid) {
		this.townshipid = townshipid;
	}

	public String getSociety_List() {
		return society_List;
	}

	public void setSociety_List(String society_List) {
		this.society_List = society_List;
	}
	 

}
