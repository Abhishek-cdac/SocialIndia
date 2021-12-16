package com.socialindia.utilitymgmt;

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

public class BookingMasterDTable extends ActionSupport implements
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

	// UamServices uamDeatils = new UamDaoServices();
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao();

	/**
	 * For merchant user records showing on data table.
	 */
	public String execute() {
		Log logwrite = null;
		try {
			System.out.println("BookingMasterDTable  loading.......");
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
			JSONObject dataJson = null;
			JSONArray ja = null;
			JSONObject finaljj = null;
			JSONObject json = null;
			JSONObject json_data = null;
			try {
				dataJson = new JSONObject();
				dataJson.put("status", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal  row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				dataJson.put("booking_status", "");
				dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
				dataJson.put("society", String.valueOf(sessionMap.get("sSoctyId")));
				finaljj = new JSONObject();
				finaljj.put("servicecode", "SI00033");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				Commonutility.toWriteConsole("[RPK - Request Data] : "+jsonTextFinal);								
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.utility.bookingdatable");
				Commonutility.toWriteConsole("[RPK - Request Url] : "+finalUrl);
				String response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				json_data = json.getJSONObject("data");
				
				String totStr = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "InitCount");
				if(Commonutility.toCheckisNumeric(totStr)){
					total =  Integer.parseInt(totStr);
				} else{
					total = 0;
				}
				
				//total = json_data.getInt("InitCount");
				
				ar = json_data.getJSONArray("bookingdetails");
				JSONObject jsonList = new JSONObject();
				String bookingName = null,bookingplace=null,bookingmobno=null,bookingstartdate=null,bookingenddate=null,bookingusrname=null,bookingusrid=null;
				Integer bookingid, bookingstatus,bookingfcaid;
						
				String statusflg = "";
				String action = "";
				int sno = start + 1;
				for (int i = 0; i < ar.length(); i++) {				 
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					bookingName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "booking_name");
					bookingid=Integer.parseInt((String) Commonutility.toHasChkJsonRtnValObj(jsonList, "booking_id"));
					bookingstatus=Integer.parseInt((String) Commonutility.toHasChkJsonRtnValObj(jsonList, "booking_status"));
					
					bookingfcaid = Integer.parseInt((String) Commonutility.toHasChkJsonRtnValObj(jsonList, "booking_facid"));
					bookingplace=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "booking_place");
					bookingmobno=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "booking_mobno");
					bookingstartdate=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "booking_startdate");
					bookingenddate=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "booking_enddate");
					bookingusrname=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "profile_name");
					bookingusrid=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "booking_usrid");
					ja = null;
					ja = new JSONArray();	
					if(bookingName!=null){
						bookingName = new CommonUtilsDao().toFirstCharUpper(bookingName);
					}
					if(bookingusrname!=null){
						bookingusrname="<a class=\"pointer tooltipped\" data-tooltip=\"Click to View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" target=\"_blank\" href=\"viewresidentdetails?deleteresidentid="+bookingusrid+"\">"+bookingusrname+"</a>";
					}
					else
					{
						bookingusrname="";
					}
					
					 
		            
		            action += "<div class=\"booking\"><a class=\"left societystyle\" style=\"\" onclick=\"viewbookingaction('" + bookingid + "');\">"
			          		+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>";
		            
		           
		          if(bookingstatus == 1){
		        	  action += "<div class=\"left\" style=\"margin: 5px;\">Approved</div>";
		          }
		          else if(bookingstatus == 2){
		        	  action += "<div class=\"left\" style=\"margin: 5px;\">Declined</div>";
		          }
		          else
		          {
		        	  action += "<a class=\"left societystyle\" id=\"bookingappr_" + bookingid + "\" style=\"\" onclick=\"approvalbookingaction('"+ bookingid + "');\">"
			            		+ "<i class=\"tooltipped mdi-action-verified-user tinysmall blue-text text-lighten-2\" data-tooltip=\"Approve\" data-delay=\"10\" data-position=\"bottom\"></i></a>";

			            action += "<a class=\"left societystyle\" id=\"idcardiddecl_" + bookingid + "\" style=\";\" onclick=\"declinebookingaction('" + bookingid+ "');\">"
			            		+ "<i class=\"tooltipped mdi-action-highlight-remove  tinysmall red-text text-lighten-2\" data-tooltip=\"Decline\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
			            
			            action += "<div id=\"statusid_" + bookingid + "\" class=\"left\" style=\"margin: 5px;display:none;\"></div>";
		          }
					
					ja.put(sno);
					
					ja.put(bookingusrname);
					ja.put(bookingmobno);
					ja.put(bookingName);
					ja.put(bookingplace);
					ja.put(bookingstartdate);
					ja.put(bookingenddate);
					ja.put(action);
					array.put(ja);
					action = "";
					sno++;					
				}			
			String cnfltr =	(String)Commonutility.toHasChkJsonRtnValObj(json_data, "countAfterFilter");
			if(Commonutility.toCheckisNumeric(cnfltr)){
				totalAfterFilter =  Integer.parseInt(cnfltr);
			} else{
				totalAfterFilter = 0;
			}
			// totalAfterFilter =  json_data.getInt("countAfterFilter");
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Step -1 : Exception Found "+getClass().getSimpleName()+".class 1 : "+ex);
	    	    logwrite.logMessage("Step -1 : Exception Found : "+ex, "error", BookingMasterDTable.class);
			} finally {
				dataJson = null; ar = null; ja = null; finaljj = null; json = null; json_data = null;
			} 
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
	      System.out.println("Step 3 : bookingMasterDTable Manage Table [End]" + new SimpleDateFormat("yyyy-MM-dd hh:mm:SSS").format(new Date()));
	 	  logwrite.logMessage("Step 3 : bookingMasterDTable Table [End] ", "info", BookingMasterDTable.class);
	      getResponse();	     
		} catch (Exception ex) {
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName()+".class 2 : "+ex);
	  	  	logwrite.logMessage("Step -1 4: Exception Found : "+ex, "error", BookingMasterDTable.class);
		} finally {
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
