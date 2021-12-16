package com.socialindia.compaign;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class Donate extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware{

	  private static final long serialVersionUID = 1L;
	  private String idtDisplayStart;
	  private String idtDisplayLength;
	  private String sdtEcho;
	  private String idtSortCol0;
	  private String sdtSortDir0;
	  private String sdtSearch;
	  private JSONObject result = new JSONObject();
	  JSONArray aaData = new JSONArray();
	  private String idtTotalDisplayRecords = "";
	  private String idtTotalRecords = "";
	  private HttpServletResponse response;
	  private HttpServletRequest request = ServletActionContext.getRequest();
	  private String sqlQuery;
	  private String laborProfileImage="";	
	  private String searchWord;
	  private String townShipId;
	  private String societyId;
	  private String slectfield;
	  private Common commonObj = new CommonDao();
	  
	  /**
	   * For merchant user records showing on data table.
	   */
	public String execute() {
	
		Log logwrite = null;
		try {
			logwrite = new Log();			
			System.out.println("Step 1 : Donate Data will Fetched called [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Donate Data will Fetched called [Start]", "info", Donate.class);			
			sdtEcho = request.getParameter("sEcho");
			sdtSearch = request.getParameter("sSearch");
			idtDisplayStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			if (sdtSearch == null) {
				sdtSearch = "";
			}

			JSONArray array = new JSONArray();
			int amount = 10;
			int start = 0;
			int echo = 0;
			int col = 0;			
			String dir = "Desc";	     
			if (idtDisplayStart != null) {
				start = Integer.parseInt(idtDisplayStart);
				if (start < 0) {
					start = 0;
				}
			} else {
				if (request.getParameter("iDisplayStart") != null) {
					start = Integer.parseInt(request
							.getParameter("iDisplayStart"));
				}
			}

			if (idtDisplayLength != null) {
				amount = Integer.parseInt(idtDisplayLength);
				if (amount < 5 || amount > 100) {
					amount = 5;
				}
			} else {
				if (request.getParameter("iDisplayLength") != null) {
					amount = Integer.parseInt(request
							.getParameter("iDisplayLength"));
				}
			}
			if (sdtEcho != null) {
				echo = Integer.parseInt(sdtEcho);
			}

			if (idtSortCol0 != null) {
				col = Integer.parseInt(idtSortCol0);
				if (col < 0 || col > 3) {
					col = 0;
				}
			}
			if (sdtSortDir0 != null) {
				if (!sdtSortDir0.equals("asc")) {
					dir = "desc";
					System.out.println(dir);
				}
			}
			int total = 0;
			int totalAfterFilter = 0;
			JSONArray ar = null;
			JSONObject dataJson = null;
			JSONObject finaljj = null;
			JSONObject jsonList = null;
			JSONObject json = null;
			JSONObject json_data = null;
			JSONArray lvrDataJsonary = null;
			StringBuilder lvrSbldraction = null;
			Map sessionMap=null;
			try {       
				Commonutility.toWriteConsole("Step 1 : Donate select called");
		    	dataJson=new JSONObject();
		  		dataJson.put("eventstatus", "1");
		  		dataJson.put("countflg", "yes");
		  		dataJson.put("countfilterflg", "yes");
		  		dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				sessionMap = ActionContext.getContext().getSession();
				dataJson.put("sSoctyId", sessionMap.get("sSoctyId"));
				 String societycode=String.valueOf(sessionMap.get("sSoctyId"));
				if(searchWord!=null){
				dataJson.put("searchWord", searchWord);// search
				}else{
					dataJson.put("searchWord", "");// search
				}if(townShipId!=null){
					dataJson.put("townShipId", townShipId);
				}else{
					dataJson.put("townShipId", "");
				}if(societyId!=null && !societyId.equalsIgnoreCase("")&& !societyId.equalsIgnoreCase("null")){
					dataJson.put("societyId", societyId);
				}else{
					dataJson.put("societyId", societycode);
				}if(slectfield!=null){
					dataJson.put("selectfield", slectfield);
				}else{
					dataJson.put("selectfield", "");
				}
				dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
		  		finaljj=new JSONObject();
		  		finaljj.put("servicecode", "SI420001");		
		  		finaljj.put("data", dataJson);	     
		        String jsonTextFinal = finaljj.toString();
		        System.out.println("============jsonTextFinal=====conpaign===="+jsonTextFinal);
		        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
				String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);		
				String finalUrl = getText("socialindia.donate.donateaction");
				System.out.println("Step 2 : Donate Data will Fetched Service API : " + finalUrl);
				logwrite.logMessage("Step 2 : Donate Data will Fetched Service API : " + finalUrl, "info", Donate.class);	
				String response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				System.out.println("response -------- "+response);
				json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("donation");			
				jsonList = new JSONObject();
				String donateName =null,entryDate=null,donateImage=null, donateContact=null;
				int donateId;
				String imagepath = "";		
				int lvrSno = start+1;
				for (int i = 0; i < ar.length(); i++) {
				    imagepath="";
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					donateName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "donateName");
					donateContact = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "donateContact");	
					entryDate=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "entryDate");					
					 donateId=Integer.parseInt((String) Commonutility.toHasChkJsonRtnValObj(jsonList, "donateId"));					
					 donateImage=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "donateImage");					
					 laborProfileImage = donateImage;	
					 lvrDataJsonary = new JSONArray();				
					if (donateImage.length() > 0) {    	   
						imagepath  ="<img id=\"myImg_"+donateId+"\" name=\"LaborImageFileName\" class=\"webimagedatable hoverable tooltipped\" data-tooltip=\"Click to view\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" src=\"/externalPath/donate/mobile/"+donateId+"/"+donateImage+"\" onclick=\"toViewlargesizeimgwithsrc(this.id,'/externalPath/donate/web/"+donateId+"/"+donateImage+"');\">"; 
					} else {    	 
						imagepath  ="<img id=\"lbrimg_"+donateId+"\" name=\"laborimag\" class=\"webimagedatable hoverable tooltipped\" data-tooltip=\"Click to view\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" src=\""+getText("Resource.path")+"/images/social/profile-default-male.png\" onclick=\"toViewlargesizeimgwithsrc(this.id,'"+getText("Resource.path")+"/images/social/profile-default-male.png')\">";
					}
					lvrSbldraction =  new StringBuilder();
					
					lvrSbldraction.append("<a class=\"societystyle\" onclick=\"viewconpaign('" + donateId + "');\">"
							+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>");
					
					lvrSbldraction.append("<a class=\"societystyle\" onclick=\"editconpaign('"
			                			 + donateId + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>");
					
					lvrSbldraction.append("<i class=\"societystyle tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\""
							+ " onclick=\"deleteconpaign('" + donateId + "');\"></i>");
					
					lvrDataJsonary.put(lvrSno);
		            lvrDataJsonary.put(imagepath);
		            lvrDataJsonary.put(donateName);
		            lvrDataJsonary.put(donateContact);
		            lvrDataJsonary.put(entryDate);
		            lvrDataJsonary.put(lvrSbldraction.toString());
		            array.put(lvrDataJsonary);
		            lvrSbldraction =  null;lvrSno++;
				
				}
				totalAfterFilter =  json_data.getInt("countAfterFilter");
	      } catch (Exception ex) {	       
	        System.out.println("Exception get totalcount :  " + ex);
	      }	finally {
	    	   ar = null; dataJson = null; finaljj = null; jsonList = null; json = null; json_data = null; lvrSbldraction = null;
	      }
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
			System.out.println("Step 3 : Donate Data will Fetched called [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 3 : Donate Data will Fetched called [End]", "info", Donate.class);
			getResponse();	  
	    } catch (Exception ex) {
	    	System.out.println("Step -1 : Donate Data will Fetched Exception Found Donate.class : " + ex);
			logwrite.logMessage("Step -1 : Donate Data will Fetched Exception Found : "+ex, "error", Donate.class);
	    } finally {
	    	logwrite = null;
			System.gc();
			//System.out.println("::::After:::::");Commonutility.toGetJavaHeapMemory();
		}
	    return SUCCESS;
	  }

	  /**
	   * Response.
	   */
	public void getResponse() {
		PrintWriter out;
		try {
			out = response.getWriter();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			out.print(result);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			out = null;
		}

	}

	  public String getIdtDisplayStart() {
	    return idtDisplayStart;
	  }

	  public void setIdtDisplayStart(String idtDisplayStart) {
	    this.idtDisplayStart = idtDisplayStart;
	  }

	  public String getIdtDisplayLength() {
	    return idtDisplayLength;
	  }

	  public void setIdtDisplayLength(String idtDisplayLength) {
	    this.idtDisplayLength = idtDisplayLength;
	  }

	  public String getSdtEcho() {
	    return sdtEcho;
	  }

	  public void setSdtEcho(String sdtEcho) {
	    this.sdtEcho = sdtEcho;
	  }

	  public String getIdtSortCol0() {
	    return idtSortCol0;
	  }

	  public void setIdtSortCol0(String idtSortCol0) {
	    this.idtSortCol0 = idtSortCol0;
	  }

	  public String getSdtSortDir0() {
	    return sdtSortDir0;
	  }

	  public void setSdtSortDir0(String sdtSortDir0) {
	    this.sdtSortDir0 = sdtSortDir0;
	  }

	  public String getSdtSearch() {
	    return sdtSearch;
	  }

	  public void setSdtSearch(String sdtSearch) {
	    this.sdtSearch = sdtSearch;
	  }

	  public JSONObject getResult() {
	    return result;
	  }

	  public void setResult(JSONObject result) {
	    this.result = result;
	  }

	  public String getIdtTotalDisplayRecords() {
	    return idtTotalDisplayRecords;
	  }

	  public void setIdtTotalDisplayRecords(String idtTotalDisplayRecords) {
	    this.idtTotalDisplayRecords = idtTotalDisplayRecords;
	  }

	  public String getIdtTotalRecords() {
	    return idtTotalRecords;
	  }

	  public void setIdtTotalRecords(String idtTotalRecords) {
	    this.idtTotalRecords = idtTotalRecords;
	  }

	  public JSONArray getAaData() {
	    return aaData;
	  }

	  public void setAaData(JSONArray aaData) {
	    this.aaData = aaData;
	  }

	  public String getSqlQuery() {
	    return sqlQuery;
	  }

	  public void setSqlQuery(String sqlQuery) {
	    this.sqlQuery = sqlQuery;
	  }

	  @Override
	  public void setServletContext(ServletContext arg0) {
	    // TODO Auto-generated method stub

	  }

	  @Override
	  public void setServletResponse(HttpServletResponse arg0) {
	    // TODO Auto-generated method stub
	    response = arg0;
	  }

	  @Override
	  public void setServletRequest(HttpServletRequest arg0) {
	    // TODO Auto-generated method stub
	    request = arg0;
	  }

	  @Override
	  public void setSession(Map<String, Object> arg0) {
	    // TODO Auto-generated method stub

	  }
	public String getlaborProfileImage() {
		return laborProfileImage;
	}

	public void setlaborProfileImage(String laborProfileImage) {
		this.laborProfileImage = laborProfileImage;
	}
}
