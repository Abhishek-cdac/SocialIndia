package com.socialindia.disputemgmtToMerchant;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.townShipMgmt.townShipMgmtTable;

public class DisputemgmtTomerchantTbl extends ActionSupport implements
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
	  //UamServices uamDeatils = new UamDaoServices();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao();
	  /**
	   * For merchant user records showing on data table.
	   */
	  public String execute() {
		  Log logwrite = null;
	    try {
	    	System.out.println("DisputemgmtTomerchantTbl  loading.......");
	    	logwrite = new Log();
	      // HttpServletRequest request = ServletActionContext.getRequest();
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
	      String globalsearch = "";
	      String userfilter = "";
	      String dir = "Desc";
	      String where;
	      where = userfilter;
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
	          System.out.println(dir);
	        }
	      }
	      int total = 0;
	      int totalAfterFilter = 0;
	      JSONArray ar =null;
	      JSONObject dataJson=new JSONObject();
	      String lcsocitid=String.valueOf(sessionMap.get("sSoctyId"));
	      try {

	    	dataJson.put("societyid", lcsocitid);
	  		dataJson.put("status", "1");
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
			dataJson.put("grpcode", "merchant_report");//7-labor 8-merchant
	  		JSONObject finaljj=new JSONObject();
	  		finaljj.put("servicecode", "SI30000");
	  		finaljj.put("data", dataJson);
	        String jsonTextFinal = finaljj.toString();
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
			String finalUrl =getText("socialindia.disputemgmt.tomerchantdatable");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			 total =  json_data.getInt("InitCount");
			 ar = json_data.getJSONArray("disputemerchant_labourdetails");
			 JSONObject jsonList = new JSONObject();
			 String disp_merchantName =null,disp_sctyname=null,disp_twnshpname=null,disp_desc=null,disp_issuetypdesc=null,disp_id=null,disp_toid=null,disp_usrname=null,disp_tomerchantname=null,disp_usrid=null;
			 Integer disp_merchantid;
			  JSONArray ja;
			  String uniId = "";

		        String statusflg = "";
		        String action = "";
		        int sno = start+1;
			 for(int i=0;i<ar.length();i++)
				{

					jsonList=null;
					jsonList=ar.getJSONObject(i);
					disp_id = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbrrepiss_id");
					disp_toid=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbrrepiss_mrchtorlaborid");
					disp_tomerchantname=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "disp_tomerchantname");
					disp_sctyname=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "disp_societyname");
					disp_twnshpname=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "disp_townshipname");
					disp_desc=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "disp_desc");
					disp_issuetypdesc=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "disp_issuetypdesc");
					disp_usrname=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "disp_usrname");
					disp_usrid=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbrrepiss__raisedby");
					if(String.valueOf(sessionMap.get("GROUPCODE")).equalsIgnoreCase("5"))
					{
					 action +="<div class=\"divactionicon\">"
						 		+ "<a style=\"cursor: pointer;\"   href=\"Merchantcomplaintview?uniqueId="+disp_id+"\" >"
						 				+ " <i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\" ></i></a></div>" ;
						 action +="<div onclick=\"closecompliant("+disp_id+");\" class=\"divactionicon\">"
						 		+ "<i data-tooltip=\"Close\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" class=\"tooltipped "+getText("button.color.close")+"\" ></i></div>";
						 action +=" <div onclick=\"actionDelete("+disp_id+");\" class=\"divactionicon\">"
						 		+ "<i data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" class=\"tooltipped "+getText("button.color.delete")+" \" ></i></div> ";
						 action +="<div onclick=\"compliantsendemail("+disp_id+","+disp_toid+","+disp_usrid+");\"  class=\"divactionicon\" >"
						 		+ "<a style=\"cursor: pointer;color: black;\"  href=\"#!\" > <i class=\"tooltipped "+getText("button.color.email")+"\" data-tooltip=\"Email\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" ></i> </a><span title=\"\"></span></div>";
						 action +="<div onclick=\"sendsmstoadmin("+disp_id+","+disp_toid+","+disp_usrid+");\" class=\"divactionicon\">"
						 		+ "<a style=\"cursor: pointer;color: black;\" href=\"#!\" > "
						 		+ "<i  data-tooltip=\"Sms\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\" class=\"tooltipped "+getText("button.color.sms")+"\"></i> </a><span title=\"\"></span></div>";
					}
					else
					{
						 action +="<div class=\"divactionicon\">"
							 		+ "<a style=\"cursor: pointer;\"   href=\"Merchantcomplaintview?uniqueId="+disp_id+"\" >"
							 				+ " <i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\" ></i></a></div>" ;

							 action +="<div onclick=\"compliantsendemail("+disp_id+","+disp_toid+","+disp_usrid+");\"  class=\"divactionicon\" >"
							 		+ "<a style=\"cursor: pointer;color: black;\"  href=\"#!\" > <i class=\"tooltipped "+getText("button.color.email")+"\" data-tooltip=\"Email\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" ></i> </a><span title=\"\"></span></div>";
							 action +="<div onclick=\"sendsmstoadmin("+disp_id+","+disp_toid+","+disp_usrid+");\" class=\"divactionicon\">"
							 		+ "<a style=\"cursor: pointer;color: black;\" href=\"#!\" > "
							 		+ "<i  data-tooltip=\"Sms\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\" class=\"tooltipped "+getText("button.color.sms")+"\"></i> </a><span title=\"\"></span></div>";
					}
					ja = null;
					ja = new JSONArray();


		            if (disp_twnshpname == null) {
		            	disp_twnshpname = "";
		            }
		            if (disp_sctyname == null) {
		            	disp_sctyname = "";
		            }
		            if (disp_issuetypdesc == null) {
		            	disp_issuetypdesc = "";
		            }
		            disp_usrname = "<a class=\"pointer tooltipped\" data-tooltip=\"Click to View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" target=\"_blank\" href=\"viewresidentdetails?deleteresidentid="+disp_usrid+"\">"+disp_usrname+"</a>";
		            disp_tomerchantname = "<a class=\"pointer tooltipped\" data-tooltip=\"Click to View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" target=\"_blank\" href=\"viewMerchantDetail?merchantid="+disp_toid+"&merhcnatIdval="+disp_toid+"\">"+disp_tomerchantname+"</a>";
		            ja.put(sno);
		            ja.put(disp_twnshpname);
		            ja.put(disp_sctyname);
		            ja.put(disp_usrname);
		            ja.put(disp_tomerchantname);
		            ja.put(disp_issuetypdesc);
		            ja.put(action);
		            array.put(ja);
		            action = "";
		            sno ++;

				}
			 totalAfterFilter =  json_data.getInt("countAfterFilter");
	      } catch (Exception ex) {
	        ex.printStackTrace();
	        System.out.println("Step -1 : Exception Found : "+ex);
	    	  logwrite.logMessage("Step -1 : Exception Found : "+ex, "error", DisputemgmtTomerchantTbl.class);
	      }
	      finally{
	    	  dataJson = null; ar = null;
	      }
	      result.put("sEcho", echo);
	      result.put("iTotalRecords", total);
	      result.put("iTotalDisplayRecords", totalAfterFilter);
	      result.put("aaData", array);
	      System.out.println("Step 3 : Worktype MasterDTable Manage Table [End]"+new SimpleDateFormat("yyyy-MM-dd hh:mm:SSS").format(new Date()));
	 	  logwrite.logMessage("Step 3 : Worktype MasterDTable Table [End] ", "info", DisputemgmtTomerchantTbl.class);
	      getResponse();


	    } catch (Exception ex) {
	    	System.out.println("Step -1 4: Exception Found : "+ex);
	  	  	logwrite.logMessage("Step -1 4: Exception Found : "+ex, "error", DisputemgmtTomerchantTbl.class);
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
