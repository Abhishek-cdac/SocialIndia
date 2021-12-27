package com.socialindia.residentmgmt;

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
import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.CommonUtilsDao;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class residentMgmtMasterTable extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware{
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
	private String RestProfileImage = "";
	private String searchWord;
	private String townShipId;
	private String societyId;
	private String slectfield;
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao();

	/**
	 * For merchant user records showing on data table.
	 */
	public String execute() {
		Log logwrite = null;
		Map sessionMap = null;
		try {
			logwrite = new Log();
			Commonutility.toWriteConsole("Step 1 : Resident Data Fetched called [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Resident Data Fetched called [Start]", "info", residentMgmtMasterTable.class);
			sdtEcho = request.getParameter("sEcho");
			sdtSearch = request.getParameter("sSearch");
			idtDisplayStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			sessionMap = ActionContext.getContext().getSession();			
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
					start = Integer.parseInt(request.getParameter("iDisplayStart"));
				}
			}

			if (idtDisplayLength != null) {
				amount = Integer.parseInt(idtDisplayLength);
				if (amount < 5 || amount > 100) {
					amount = 5;
				}
			} else {
				if (request.getParameter("iDisplayLength") != null) {
					amount = Integer.parseInt(request.getParameter("iDisplayLength"));
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
				}
			}
			int total = 0;
			int totalAfterFilter = 0;
			JSONArray ar = null;
			JSONArray lvrRtnjary = null;
			int lvrSno = 1;
			StringBuilder lvrSblraction = null;
			JSONObject dataJson = null;
			JSONObject finaljj =  null;
			JSONObject json = null;
			JSONObject json_data = null;
			try {				
				dataJson = new JSONObject();
				
				dataJson.put("rsdtstatus", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal												
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
				/*dataJson.put("currentloginsocietyid", String.valueOf(sessionMap.get("sSoctyId")));*/
				if(searchWord!=null){
					dataJson.put("searchWord", searchWord);// search
	    		}else{
	    				dataJson.put("searchWord", "");// search
	    			}if(townShipId!=null){
	    				dataJson.put("townShipId", townShipId);
	    			}else{
	    				dataJson.put("townShipId", "");
	    			}if(societyId!=null && !societyId.equalsIgnoreCase("")&& !societyId.equalsIgnoreCase("null")){
	    				dataJson.put("currentloginsocietyid", societyId);
	    			}else{
	    				dataJson.put("currentloginsocietyid", String.valueOf(sessionMap.get("sSoctyId")));
	    			}if(slectfield!=null){
	    				dataJson.put("slectfield", slectfield);
	    			}else{
	    				dataJson.put("slectfield", "");
	    			}
	    			dataJson.put("status", "1");
				finaljj = new JSONObject();
				finaljj.put("servicecode", "RSSI0000");
				finaljj.put("data", dataJson);

				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.companymgmt.residentmgmtdatable");
				Commonutility.toWriteConsole("Step 2 : Resident Data Fetched Service Url : " + finalUrl);
				logwrite.logMessage("Step 2 : Resident Data Fetched Service Url : " + finalUrl, "info", residentMgmtMasterTable.class);
				String response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("residentdetails");
				JSONObject jsonList = new JSONObject();
				String RestName =null,RestMobno=null,frstname=null,rest_img=null,restadd1=null,status=null,emailid=null, lvrActsts=null;
				String lvrLname = null, lvrFulName = null, lvrSocietyname = null, lvrTownshipname = null;
				int rest_id;		
				for (int i = 0; i < ar.length(); i++) {				
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					RestName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "rest_name");					
					RestMobno = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "rest_mobno");					
					frstname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "rest_firstname");					
					rest_id = (Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "rest_id");															
					restadd1 = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "rest_address1");					
					status = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "status");				
					emailid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "rest_email");
					//dob = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "rest_dob");
					//rest_idno = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "rest_idprfno");
					lvrLname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "rest_lastname");
					lvrFulName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "rest_fulname");
					lvrSocietyname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "societyname");
					lvrTownshipname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "townshipname");
					
					if(lvrFulName!= null && !lvrFulName.equalsIgnoreCase("null")){
						lvrFulName = new CommonUtilsDao().toFirstCharUpper(lvrFulName);
					}
					if(lvrLname!= null && !lvrLname.equalsIgnoreCase("null")){
						lvrLname = new CommonUtilsDao().toFirstCharUpper(lvrLname);
					}
					if(frstname!= null && !frstname.equalsIgnoreCase("null")){
						frstname = new CommonUtilsDao().toFirstCharUpper(frstname);
					}
					if(lvrLname!=null && !lvrLname.equalsIgnoreCase("null") && !lvrLname.equalsIgnoreCase("")){
						frstname +=" "+lvrLname;
					}	
					
					if (Commonutility.checkempty(status) && status.equalsIgnoreCase("0")) {
						lvrActsts = "<span class='red-text text-darken-1'> DeActive </span>";
					} else if (Commonutility.checkempty(status) && status.equalsIgnoreCase("1")) {
						lvrActsts = "<span class='green-text text-darken-2'> Active </span>";
					} else if (Commonutility.checkempty(status) && status.equalsIgnoreCase("2")) {
						lvrActsts = "<span class='red-text text-darken-4'> Blocked </span>";
					} else if (Commonutility.checkempty(status) && status.equalsIgnoreCase("3")) {
						lvrActsts = "<span class='red-text text-accent-4'> Deleted </span>";
					} else {
						lvrActsts = "<span class='red-text text-darken-1'> DeActive </span>";
					}
					 lvrRtnjary = new JSONArray();
					 lvrSblraction = new StringBuilder();
					 lvrSblraction.append("<a class=\"townshipmasterstyle\" onclick=\"viewresident('" + rest_id + "');\">"
					 		+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>");
					 lvrSblraction.append("<a class=\"townshipmasterstyle\" onclick=\"editresident('"
							   + rest_id + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>");
					 lvrSblraction.append("<i class=\"townshipmasterstyle tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" onclick=\"deleteresidentaction('" + rest_id+ "');\"></i>");					 
					lvrRtnjary.put(lvrSno);	            
					lvrRtnjary.put(frstname);
					lvrRtnjary.put(RestMobno);
					lvrRtnjary.put(emailid);					
					lvrRtnjary.put(lvrTownshipname);
					lvrRtnjary.put(lvrSocietyname);			
					lvrRtnjary.put(lvrActsts);			
					lvrRtnjary.put(lvrSblraction.toString());
		            array.put(lvrRtnjary);
		           lvrSblraction = null;
		            lvrSno++;
		            lvrRtnjary = null;
				}
				totalAfterFilter =  json_data.getInt("countAfterFilter");	       			
			} catch (Exception ex) {
				Commonutility.toWriteConsole("Step -1 : Resident Data Fetched Exception Found : " + ex);
				logwrite.logMessage("Step -1 : Resident Data Fetched Service Url : " + ex, "info", residentMgmtMasterTable.class);
			}finally{
				//Commonutility.toWriteConsole("::::Before:::::"); Commonutility.toGetJavaHeapMemory();
				 ar = null;				 
				 lvrSno = 1; lvrRtnjary = null; dataJson = null; finaljj =  null; json = null; json_data = null; lvrSblraction = null;
			}
			Commonutility.toWriteConsole("Step 3 : Resident Data Fetched called [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 3 : Resident Data Fetched called [End]", "info", residentMgmtMasterTable.class);
			result.put("sEcho", echo);
	        result.put("iTotalRecords", total);
	        result.put("iTotalDisplayRecords", totalAfterFilter);
	        result.put("aaData", array);
	       
	        getResponse();			
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -2 : Resident Data Fetched Exception Found : " + ex);
			logwrite.logMessage("Step -2 : Resident Data Fetched Service Url : " + ex, "info", residentMgmtMasterTable.class);
		} finally {
			 logwrite = null;			 			 
			 System.gc();
			// Commonutility.toWriteConsole("::::After:::::");Commonutility.toGetJavaHeapMemory();
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
			// TODO Auto-generated catch block
			ex.printStackTrace();
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

	public String getRestProfileImage() {
		return RestProfileImage;
	}

	public void setRestProfileImage(String RestProfileImage) {
		this.RestProfileImage = RestProfileImage;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getTownShipId() {
		return townShipId;
	}

	public void setTownShipId(String townShipId) {
		this.townShipId = townShipId;
	}

	public String getSocietyId() {
		return societyId;
	}

	public void setSocietyId(String societyId) {
		this.societyId = societyId;
	}

	public String getSlectfield() {
		return slectfield;
	}

	public void setSlectfield(String slectfield) {
		this.slectfield = slectfield;
	}
	
	
}
