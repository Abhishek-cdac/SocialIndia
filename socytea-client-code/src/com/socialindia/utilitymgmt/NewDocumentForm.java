package com.socialindia.utilitymgmt;

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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class NewDocumentForm extends ActionSupport implements
SessionAware, ServletRequestAware, ServletResponseAware,
ServletContextAware{
	private static final long serialVersionUID = 1L;
	 JSONObject obj = new JSONObject();
	  JSONObject data = new JSONObject();
	  private Common commonObj = new CommonDao();	  
	  private String documenttypeval=null;
	  private String userdetail=null;	  
	  private String searchval;
	  private String searchname;
	  private String slectfield;
	  private String society;
	  private String townshipid;	  	  
	public String execute() {
			 JSONObject locObjRecvJson = null;//Receive String to json	
				JSONObject locObjRecvdataJson = null;// Receive Data Json	
				String ivrservicecode=null;
		    	String ivrresponsecode=null;
		    	String ivrmsg=null;
		    	String ivrstatuscode=null;
		    	
				 try{
	    		  //country,city,state
	    		  	obj.put("servicecode", "SI1010");
	    			data.put("data", obj);
	    			String jsonTextFinal_Countryid = data.toString().trim();
	    			jsonTextFinal_Countryid=EncDecrypt.encrypt(jsonTextFinal_Countryid);
	    			String tempcountry = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Countryid);	    			
	    			String tempcountryUrl = getText("socialindia.utility.generaldocumenttype");
	    	    	  String responsecountry = commonObj.jsonRequest(tempcountryUrl, tempcountry);
	    	    	  System.out.println("--response1-- "+responsecountry);
			if (responsecountry!=null && !responsecountry.equalsIgnoreCase("null") && responsecountry.length()>0){
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
					try {		 		
						jsrcountry = new JSONArray();
						for (int i = 0; i < jary.length(); i++) {		 			
							JSONArray temmauto = jary.getJSONArray(i);
							int indx = (Integer) temmauto.get(0);
							String vl = (String) temmauto.get(1);
							jbcountry = new JSONObject();
							jbcountry.put("id", String.valueOf(indx));// jobj
							vl = vl.replace("'", "%27");
							jbcountry.put("label", vl);// jobj
							jsrcountry.put(jbcountry);// jarray
						}	    				 		
						documenttypeval = jsrcountry.toString();
					} catch (Exception e) {
						System.out.println("Exception e : " + e);
					} finally {
						jbcountry = null;
						jsrcountry = null;
					}
	    	    			
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return SUCCESS;
			
	}
	
	public String getUserDetail() {
		Map sessionMap = ActionContext.getContext().getSession();
		String lcsocitid = String.valueOf(sessionMap.get("sSoctyId"));
		try {
			obj.put("servicecode", "SI0006");
			JSONObject jbb = new JSONObject();
			jbb.put("count1", "yes");
			jbb.put("countF1", "yes");
			jbb.put("startrow", "1");
			jbb.put("totalrow", "1000");
			jbb.put("pagefor", "fbauto");
			jbb.put("society", lcsocitid);
			if (slectfield == null) {
				jbb.put("slectfield", "");
			} else {
				jbb.put("slectfield", slectfield);
			}
			if (searchname == null) {
				jbb.put("searchname", "");
			} else {
				jbb.put("searchname", searchname);
			}
			if (searchname == null) {
				jbb.put("searchflg", "");
			} else {
				jbb.put("searchflg", searchval);
			}
			obj.put("data", jbb);
			String jsonTextFinal = obj.toString();
			// jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("usermanagement.view.url");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject json_data = json.getJSONObject("data");
			JSONArray ar = null;
			ar = json_data.getJSONArray("userMgmt");
			JSONObject jsonList = new JSONObject();
			JSONObject retval = new JSONObject();
			JSONArray jArray = new JSONArray();
			for (int i = 0; i < ar.length(); i++) {
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					retval = new JSONObject();
					String firstName=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "fname");
					String lastName=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lastName");
					String flatNo=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "flatNo");
					String emailId=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "emailId");
					String mobileNo=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "mobileNo");
					int userid=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "userId");
					String imageNameMobile=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "imageNameMobile");
					if(imageNameMobile!=null && imageNameMobile.length()>0){
						imageNameMobile="/templogo/profile/mobile/"+userid+"/"+imageNameMobile;
					}else{
						imageNameMobile=getText("Resource.path")+"/images/social/profile-default-male.png";
					}
					//System.out.println("imageNameMobile-------------------"+imageNameMobile);
					String fName="";
					String lName="";
					String mobilNum="";
					if(flatNo==null){
						flatNo="";
					}
					String searchfield="";
					
					if(firstName!=null && firstName.length()>0){
						searchfield=firstName;
						fName=firstName;
					}
					
					if(lastName!=null && lastName.length()>0){
						searchfield+=" "+lastName;
						lName=lastName;
					}
			
					if(mobileNo!=null && mobileNo.length()>0){
						searchfield+=" "+mobileNo;
						mobilNum=mobileNo;
					}
					String serchupnd="";
					if(flatNo!=null && flatNo.length()>0){
						serchupnd+=" "+"(F.No: "+flatNo+")";
						flatNo=" "+"(F.No: "+flatNo+")";
					}
					searchfield+=" "+serchupnd;				
					retval.put("searchkey", searchfield);
					retval.put("fName", fName);
					retval.put("lName", lName);
					retval.put("mobilNum", mobilNum);
					retval.put("flatNo", flatNo);
					retval.put("searchvalue", userid);
					retval.put("imageNameMobile", imageNameMobile);
					retval.put("userid", userid);
					jArray.put(retval);
				}
			 
			 userdetail=jArray.toString();
		}catch (Exception ex){
			ex.printStackTrace();
		} finally {
			
		}
		return SUCCESS;
	}
	
	public String getpdfgenerate() {
		 JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json	
			String ivrservicecode=null;
	    	String ivrresponsecode=null;
	    	String ivrmsg=null;
	    	String ivrstatuscode=null;
			 try{
				 System.out.println("test ----------------------");
   		  //country,city,state
   		  	obj.put("servicecode", "SI10111");
   			data.put("data", obj);
   			String jsonTextFinal_Countryid = data.toString().trim();
   			jsonTextFinal_Countryid=EncDecrypt.encrypt(jsonTextFinal_Countryid);
   			String tempcountry = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Countryid);	    			
   			String tempcountryUrl = getText("socialindia.utility.pdfgenerate");
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
   	    		  JSONArray jsrcountry= null;JSONObject jbcountry=null;
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


	public String getDocumenttypeval() {
		return documenttypeval;
	}


	public void setDocumenttypeval(String documenttypeval) {
		this.documenttypeval = documenttypeval;
	}

	public String getUserdetail() {
		return userdetail;
	}

	public void setUserdetail(String userdetail) {
		this.userdetail = userdetail;
	}

	public String getSearchval() {
		return searchval;
	}

	public void setSearchval(String searchval) {
		this.searchval = searchval;
	}

	public String getSearchname() {
		return searchname;
	}

	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}

	public String getSlectfield() {
		return slectfield;
	}

	public void setSlectfield(String slectfield) {
		this.slectfield = slectfield;
	}

	public String getSociety() {
		return society;
	}

	public void setSociety(String society) {
		this.society = society;
	}

	public String getTownshipid() {
		return townshipid;
	}

	public void setTownshipid(String townshipid) {
		this.townshipid = townshipid;
	}
	
	
}
