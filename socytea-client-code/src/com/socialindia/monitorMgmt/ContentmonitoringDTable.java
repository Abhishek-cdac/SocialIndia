package com.socialindia.monitorMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class ContentmonitoringDTable extends ActionSupport implements
SessionAware, ServletRequestAware, ServletResponseAware,
ServletContextAware{
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
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  /**
	   * For merchant user records showing on data table.
	   */
	public String execute() {
		Log logwrite = null;
		try {
			System.out.println("ContentmonitoringDTable  loading.......");
			logwrite = new Log();
			sdtEcho = request.getParameter("sEcho");
			sdtSearch = request.getParameter("sSearch");
			idtDisplayStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			Map sessionMap = ActionContext.getContext().getSession();
			if (sdtSearch == null) {
				sdtSearch = "";
			}

			JSONArray array = new JSONArray();
			int amount = 10;
			int start = 0;
			int echo = 0;
			int col = 0;
			
			String userfilter = "";
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
				}
			}
			int total = 0;
			int totalAfterFilter = 0;
			JSONArray ar = null;
			JSONObject dataJson = null, finaljj = null, json = null, json_data = null, jsonList = null;
			JSONArray ja = null;
			try {
				dataJson = new JSONObject();
				dataJson.put("status", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal // row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
				finaljj = new JSONObject();
				finaljj.put("servicecode", "SI10003");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.monitoring.contentmonitoringdatable");
				String response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = (JSONArray) Commonutility.toHasChkJsonRtnValObj(json_data,"feeddetails");
				jsonList = new JSONObject();
				String feedName = null,feedusrname=null,feedentrytime=null,feedtblflag=null,checkedsts1=null,checkedsts2=null;
				Integer feedid, feedstatus,feedusr_id,feedusr_sts;							
				String statusflg = "";
				String action = "";
				int sno = start + 1;
				for (int i = 0; i < ar.length(); i++) {				 
					jsonList=null;
					jsonList = ar.getJSONObject(i);
					feedName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "feed_text");
					feedid=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "feed_id");
					feedstatus = (Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "feed_status");
					feedusrname=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "feed_usrname");
					feedentrytime=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "feed_entrydate");
					feedtblflag=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "feed_tblflag");
					feedusr_id=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "feed_usrid");
					feedusr_sts = (Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "feedusr_status");
					feedentrytime=feedentrytime.substring(0, 10);
					ja = null;
					ja = new JSONArray();
					if(feedName!=null){
						feedName = new CommonUtilsDao().toFirstCharUpper(feedName);
					} 
					if (feedstatus == 0) {
						checkedsts1 ="checked";
						action +="<p><input type=\"checkbox\"  checked class=\"filled-in\"  id=\"feedid_"+ feedid +"\"  onclick=\"activeactioncontent('" + feedid + "','"+feedtblflag+"');\">"
								 + "<label for=\"feedid_"+ feedid +"\" id=\"feedcnt_lbl_"+ feedid +"\"> &nbsp;&nbsp;UnBlock Content </label></p>";
					}else{
						action +="<p><input type=\"checkbox\"  class=\"filled-in\"  id=\"feedid_"+ feedid +"\"  onclick=\"deleteactioncontent('" + feedid + "','"+feedtblflag+"');\">"
								 + "<label for=\"feedid_"+ feedid +"\" id=\"feedcnt_lbl_"+ feedid +"\"> &nbsp;&nbsp;Block Content </label></p>";
					}
					
					if(feedusr_sts == 0){
						action += "<p><input type=\"checkbox\" checked class=\"filled-in\"  id=\"feedusr"+ feedid +"\" onclick=\"activeactionUser('" + feedusr_id + "','"+feedid+"');\">"
							  	 + "<label for=\"feedusr"+ feedid +"\" id=\"feedusr_lbl_"+ feedid +"\"> &nbsp;&nbsp;UnBlock User</label></p>";
					}else{
						action += "<p><input type=\"checkbox\"  class=\"filled-in\"  id=\"feedusr"+ feedid +"\" onclick=\"deleteactionUser('" + feedusr_id + "','"+feedid+"');\">"
							  	 + "<label for=\"feedusr"+ feedid +"\" id=\"feedusr_lbl_"+ feedid +"\"> &nbsp;&nbsp;Block User</label></p>";
					}			
					
					feedusrname = "<div id=\"statusval_" + feedid+ "\" >Resident Name : <a href=\"viewresidentdetails?deleteresidentid="+feedusr_id+"\" target=\"_blank\" data-position=\"bottom\" data-delay=\"10\" data-tooltip=\"Click to View\" class=\"pointer tooltipped\">"+feedusrname+"</a></div><div>Posted Date : "+feedentrytime+" </div>";
					
					if (feedName == null) {
						feedName = "";
					}
		            
		            ja.put(sno);
		            ja.put(feedName);
		            ja.put(feedusrname);
		            ja.put(action);
		            array.put(ja);
		            action = "";
		            sno ++;					
				}			
			 totalAfterFilter =  json_data.getInt("countAfterFilter");
			} catch (Exception ex) {
	    	    System.out.println("Step -1 : Exception Found "+getClass().getSimpleName()+" 1 : "+ex);
	    	    logwrite.logMessage("Step -1 : Exception Found : "+ex, "error", ContentmonitoringDTable.class);
			} finally {
	    	  ar = null; dataJson = null; finaljj = null; json = null; json_data = null; jsonList = null; ja = null;
			}
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);			
		    System.out.println("Step 3 : feed MasterDTable Manage Table [End]"+new SimpleDateFormat("yyyy-MM-dd hh:mm:SSS").format(new Date()));
		 	logwrite.logMessage("Step 3 : feed MasterDTable Table [End] ", "info", ContentmonitoringDTable.class);
		    getResponse();	   
	    } catch (Exception ex) {
	    	 System.out.println("Step -1 : Exception Found "+getClass().getSimpleName()+" 2 : "+ex);
	  	  	 logwrite.logMessage("Step -1 4: Exception Found : "+ex, "error", ContentmonitoringDTable.class);
	    }
	    finally{
	    	logwrite = null;
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
	
}
