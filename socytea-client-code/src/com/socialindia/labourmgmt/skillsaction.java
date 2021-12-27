package com.socialindia.labourmgmt;

import java.net.URLEncoder;







import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.CityMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.CategoryMasterTblVO;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.vo.SkillMasterTblVO;

public class skillsaction extends ActionSupport implements
SessionAware, ServletRequestAware, ServletResponseAware,
ServletContextAware{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Common commonObj = new CommonDao();
	JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	  public List<CategoryMasterTblVO> categMstr = new ArrayList<CategoryMasterTblVO>();
	  public List<SkillMasterTblVO> skillMstr = new ArrayList<SkillMasterTblVO>();
	  
	  private String locIdskillstyp;
	  private String categoryidkey;
	  private String ivrLbrwrktyp; 
	public String execute() throws Exception {
		 JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json
			System.out.println("========================Skills Action Form================");
			String ivrservicecode=null;
	    	String ivrresponsecode=null;
	    	String ivrmsg=null;
	    	String ivrstatuscode=null;
  	  //skillset 
  	  obj.put("servicecode", "SI1001");
		
		data.put("data", obj);
		String jsonTextFinal_Skillid = data.toString().trim();
		jsonTextFinal_Skillid=EncDecrypt.encrypt(jsonTextFinal_Skillid);
		String tempskills = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Skillid);
	//	toCheckService(postparam+URLEncoder.encode(postparamval),srvcUrl);
		System.out.println("=====jsonText11=====" + jsonTextFinal_Skillid);
		String tempskillsUrl = getText("socialindia.category.skillsset");
		System.out.println("====finalUrl====" + tempskillsUrl);	
    	  String responseskills = commonObj.jsonRequest(tempskillsUrl, tempskills);
    	  System.out.println("--response1-- "+responseskills);
    	  if(responseskills!=null && !responseskills.equalsIgnoreCase("null") && responseskills.length()>0){
    		  boolean ivIsJsonCountry = Commonutility.toCheckIsJSON(responseskills);
    		  if (ivIsJsonCountry) {
    			  locObjRecvJson = new JSONObject(responseskills);

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
    					categMstr.add(new CategoryMasterTblVO(indx,vl));
			 			jbcountry=new JSONObject();
			 			jbcountry.put("id", String.valueOf(indx));//jobj
			 			vl =vl.replace("'", "%27");
			 			//vl =vl.replace("&", "%26");
			 			
			 			jbcountry.put("label", vl);//jobj
			 			jsrcountry.put(jbcountry);//jarray			 						 	
			 		}
			 		
			 		locIdskillstyp=jsrcountry.toString();
			 		System.out.println("locIdskillstyp-- "+locIdskillstyp);
			 		}catch(Exception e){
			 			System.out.println("Exception e : "+e);
			 		}finally{
			 			 jbcountry=null;jsrcountry= null;
			 		}
    			
				//ActionContext.getContext().getSession().put("skillssession", categMstr);
    					    	  
    	  }
    	  }
    	  return SUCCESS;
	}
	public String GetskillsMethod() throws Exception {
		 JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json		
			JSONObject locObjRspdataJson = null;// Response Data Json
			System.out.println("========================Skills Action Form================");
			String ivrservicecode=null;
	    	String ivrresponsecode=null;
	    	String ivrmsg=null;
	    	String ivrstatuscode=null;
  	  //skillset 
  	  obj.put("servicecode", "SI1009");
  	 obj.put("categoryid", categoryidkey);
		data.put("data", obj);
		String jsonTextFinal_Skillid = data.toString().trim();
		System.out.println("request:  "+jsonTextFinal_Skillid);
		jsonTextFinal_Skillid=EncDecrypt.encrypt(jsonTextFinal_Skillid);
		String tempskills = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Skillid);
	//	toCheckService(postparam+URLEncoder.encode(postparamval),srvcUrl);
		System.out.println("=====jsonText11=====" + jsonTextFinal_Skillid);
		String tempskillsUrl = getText("socialindia.skills.skillsset");
		System.out.println("====finalUrl====" + tempskillsUrl);	
    	  String responseskills = commonObj.jsonRequest(tempskillsUrl, tempskills);
    	  System.out.println("--response1-- "+responseskills);
    	  if(responseskills!=null && !responseskills.equalsIgnoreCase("null") && responseskills.length()>0){
    		  boolean ivIsJsonCountry = Commonutility.toCheckIsJSON(responseskills);
    		  if (ivIsJsonCountry) {
    			  locObjRecvJson = new JSONObject(responseskills);

		    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");
		    	  
		    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
		    	  
		    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
		    	  
		    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
				
				locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");
    		  
				JSONArray jary=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");		    	   	
				JSONArray jsrskills = null;
				JSONObject jbskill = null;
				try {
					jsrskills = new JSONArray();
					for (int i = 0; i < jary.length(); i++) {		 			
			 			JSONArray temmauto = jary.getJSONArray(i);    					
						String indx = String.valueOf((Integer)temmauto.get(0));    					
						String vl = (String)temmauto.get(1);  
						skillMstr.add(new SkillMasterTblVO(indx,vl));
						jbskill = new JSONObject();
			 			jbskill.put("id", String.valueOf(indx));//jobj
			 			vl =vl.replace("'", "%27");
			 			//vl =vl.replace("&", "%26");
			 			
			 			jbskill.put("label", vl);//jobj
			 			jsrskills.put(jbskill);//jarray			 						 	
			 		}
			 		
			 		locIdskillstyp=jsrskills.toString();
			 		System.out.println("locIdskillstyp-- "+locIdskillstyp);
			 		}catch(Exception e){
			 			System.out.println("Exception e : "+e);
			 		}finally{
			 			 jbskill=null;jsrskills= null;
			 		}
    			
				//ActionContext.getContext().getSession().put("skillssession", categMstr);
    					    	  
    	  }
    	  }
    	  return SUCCESS;
	}
	
	public String getWrktypelst(){
		JSONObject lvrRqstjson = null, lvrRqstdatajson = null;
		JSONObject locObjRecvJson = null, locObjRecvdataJson = null;
		String ivrservicecode = null, ivrresponsecode = null, ivrmsg = null, ivrstatuscode = null;
		try {
			lvrRqstjson = new JSONObject();
			lvrRqstdatajson = new JSONObject();			
			lvrRqstdatajson.put("status", "1");
			lvrRqstjson.put("data", lvrRqstdatajson);
			lvrRqstjson.put("servicecode", "SI20000");
			String jsonTextFinal = lvrRqstjson.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("labor.wrktype.lst");
			String lvrRspdata = commonObj.jsonRequest(finalUrl, temp);
			 if(lvrRspdata!=null && !lvrRspdata.equalsIgnoreCase("null") && lvrRspdata.length()>0){
	    		  boolean ivIsJsonwrktyplst = Commonutility.toCheckIsJSON(lvrRspdata);
	    		  if (ivIsJsonwrktyplst) {
	    			  locObjRecvJson = new JSONObject(lvrRspdata);
	    			  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");			    	  
			    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");			    	  
			    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");			    	  
			    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");					
			    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");	    		  
			    	  JSONArray jary = (JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");
			    	  JSONArray lvrWrktypjary = null;JSONObject lvrWrktypjson=null;
			    	  lvrWrktypjary = new JSONArray();
			    	  for (int i = 0; i < jary.length(); i++) {
						JSONArray temmauto = jary.getJSONArray(i);
						int indx = (Integer) temmauto.get(0);
						String vl = (String) temmauto.get(1);
						lvrWrktypjson = new JSONObject();
						lvrWrktypjson.put("id", String.valueOf(indx));// jobj
						vl = vl.replace("'", "%27");

						lvrWrktypjson.put("label", vl);// jobj
						lvrWrktypjary.put(lvrWrktypjson);// jarray
			    	  }
			    	  ivrLbrwrktyp = lvrWrktypjary.toString();
	    		  } else {
	    			  ivrLbrwrktyp = "";
	    		  }
			 }
			
		} catch (Exception e) {
			ivrLbrwrktyp = "";
		} finally {
			
		}
		return SUCCESS;
	}
	
	public String getLocIdskillstyp() {
		return locIdskillstyp;
	}
	public void setLocIdskillstyp(String locIdskillstyp) {
		this.locIdskillstyp = locIdskillstyp;
	}
	public List<CategoryMasterTblVO> getCategMstr() {
		return categMstr;
	}
	public void setCategMstr(List<CategoryMasterTblVO> categMstr) {
		this.categMstr = categMstr;
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
	public List<SkillMasterTblVO> getSkillMstr() {
		return skillMstr;
	}
	public void setSkillMstr(List<SkillMasterTblVO> skillMstr) {
		this.skillMstr = skillMstr;
	}
	public String getCategoryidkey() {
		return categoryidkey;
	}
	public void setCategoryidkey(String categoryidkey) {
		this.categoryidkey = categoryidkey;
	}
	public String getIvrLbrwrktyp() {
		return ivrLbrwrktyp;
	}
	public void setIvrLbrwrktyp(String ivrLbrwrktyp) {
		this.ivrLbrwrktyp = ivrLbrwrktyp;
	}
	
	
}
