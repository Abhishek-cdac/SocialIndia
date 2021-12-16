package com.socialindia.notificationmaster;

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
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class NotificationMasterDTable extends ActionSupport implements
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
	    	System.out.println("NotificationTblVO  loading.......");
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
	        }
	      }
	      int total = 0;
	      int totalAfterFilter = 0;
	      JSONArray ar =null;
	      JSONObject dataJson=new JSONObject();
	      try {	        
	  		dataJson.put("status", "1");
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
	  		JSONObject finaljj=new JSONObject();
	  		finaljj.put("servicecode", "SI25000");		
	  		finaljj.put("data", dataJson);	     
	        String jsonTextFinal = finaljj.toString();
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.notification.notificationdatable");			
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			 total =  json_data.getInt("InitCount");
			 ar = json_data.getJSONArray("notificationdetails");
			 JSONObject jsonList = new JSONObject();
			 String notifyName =null,notifytblrefName=null,notifytblrefId=null,notifyentryTime=null;
			 Integer notifyid,notifystatus;
			  JSONArray ja;
			  String uniId = "";		       
		        String statusflg = "";
		        String viewactionurl = "",notificationdetails="";
		        int sno = start+1;
		        String imgpath ="";
		        String lvrLtr = "", lvrLtrCls = "";
		        String lvrResourcepath = getText("Resource.path"); 
				for (int i = 0; i < ar.length(); i++) {				 
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					notifyName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "notification_name");
					notifyid=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "notification_id");
					notifystatus=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "notification_status");
					notifytblrefName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "notification_tblrefname");
					notifytblrefId = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "notification_tblrefid");
					notifyentryTime = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "notification_time");
					ja = null;
					ja = new JSONArray();
					if (notifytblrefName.equalsIgnoreCase("Events")) {
						viewactionurl = "<a href=\"viewEventAction?uniqueId=" + notifytblrefId + "\">";
						 lvrLtr = "E";lvrLtrCls ="circle red lighten-1";
						 imgpath = lvrResourcepath +"/images/avatar.jpg";
					} else if (notifytblrefName.equalsIgnoreCase("Complaints")) {
						viewactionurl = "<a href=\"viewcomplaintform?deletecomplaintid=" + notifytblrefId + "\">";
						lvrLtr = "C";lvrLtrCls ="circle indigo lighten-1";
						imgpath = lvrResourcepath +"/images/avatar.jpg";
					} else if (notifytblrefName.equalsIgnoreCase("Feedback")) {
						viewactionurl = "<a href=\"viewfeedBack?uniqueId=" + notifytblrefId + "\">";
						lvrLtr = "F";lvrLtrCls ="circle green lighten-1";
						imgpath = lvrResourcepath +"/images/avatar.jpg";
					}else if (notifytblrefName.equalsIgnoreCase("Feedbacks")) {
						viewactionurl = "<a href=\"viewfeedBack?uniqueId=" + notifytblrefId + "\">";
						lvrLtr = "F";lvrLtrCls ="circle green lighten-1";
						imgpath = lvrResourcepath +"/images/avatar.jpg";
					} else if (notifytblrefName.equalsIgnoreCase("Forum")) {
						viewactionurl = "<a href=\"forumPostComments?uniqueId=" + notifytblrefId + "\">";
						lvrLtr = "F";lvrLtrCls ="circle lime lighten-1";
						imgpath = lvrResourcepath +"/images/avatar.jpg";
					} else if (notifytblrefName.equalsIgnoreCase("Tender")) {
						viewactionurl = "<a href=\"viewtenderform?uniqueId=" + notifytblrefId + "\">";
						lvrLtr = "T";lvrLtrCls ="circle orange lighten-1";
						imgpath = lvrResourcepath +"/images/avatar.jpg";
					} else if (notifytblrefName.equalsIgnoreCase("Expenses")) {
						viewactionurl = "<a href=\"viewexpenceform?uniqueId=" + notifytblrefId + "\">";
						lvrLtr = "E";lvrLtrCls ="circle deep-orange accent-4";
						imgpath = lvrResourcepath +"/images/avatar.jpg";
					} else if (notifytblrefName.equalsIgnoreCase("Documents") || notifytblrefName.equalsIgnoreCase("Document")) {
						viewactionurl = "<a href=\"utititymgmt?uniqueId=" + notifytblrefId + "\">";
						lvrLtr = "D";lvrLtrCls ="circle brown lighten-1";
						imgpath = lvrResourcepath +"/images/avatar.jpg";
					}  else if (notifytblrefName.equalsIgnoreCase("Facility")) {
						viewactionurl = "<a href=\"bookingmstview?iVObookingid=" + notifytblrefId + "\">";
						lvrLtr = "F";lvrLtrCls ="circle green lighten-1";
						imgpath = lvrResourcepath +"/images/avatar.jpg";
					} else {
						viewactionurl = "<a>";
						lvrLtr = "N";lvrLtrCls ="circle grey darken-1";
						imgpath = lvrResourcepath +"/images/avatar.jpg";
					}
					
					//<span class=\"circle red lighten-1\" style=\" text-align: center;color: #fff;\"><div >E</div></span>
					notificationdetails="<div class=\"collection\" style=\"border: 0px none; margin-top:5px; margin-bottom:10px; cursor : default;\"><div class=\"collection-item avatar email-unread  col s12\" style=\"padding-top: 10px;\"><div class=\"col s10\" style=\"border: 0px none;\" >"
									//+ " <img class=\"circle\" alt=\"\" src=\""+imgpath+"\"> " 
									+ " <span class=\""+lvrLtrCls+"\" style=\"text-align: center;color: #fff;\"><div>"+lvrLtr+"</div></span>" 
									+ "<span class=\"email-title\">"+notifytblrefName+"</span>"
									+ "<p class=\"truncate grey-text ultra-small\">"+notifyName+"</p>"
									+ "</div>"
					
									+ "<div class=\"col s2\"><span class=\"blue-text ultra-small\">"+notifyentryTime+"</span></br>"
									+ "</br>"+viewactionurl+"<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\" style=\"margin-right: 10px;\"></i></a>" 
									+ "<i class=\"tooltipped "+getText("button.color.delete")+" \" data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\" onclick=\"deleteaction("+notifyid+");\"></i></a></div></div></div>";
					if (notifystatus == 0) {
			        	   statusflg ="<div id=\"statusval_"+ notifyid + "\" >Read</div>";
					} else if (notifystatus == 1) {
			        	   statusflg ="<div id=\"statusval_"+ notifyid + "\">Un Read</div>";
					}
		          
		            if (notifyName == null) {
		              notifyName = "";
		            }
		            
		            ja.put(notificationdetails);
		            /*ja.put(statusflg);*/
		            //ja.put(action);
		            array.put(ja);
		           
		            sno ++;
					
				}			
			 totalAfterFilter =  json_data.getInt("countAfterFilter");
	      } catch (Exception ex) {
	        ex.printStackTrace();
	        System.out.println("Step -1 : Exception Found : "+ex);
	    	  logwrite.logMessage("Step -1 : Exception Found : "+ex, "error", NotificationMasterDTable.class);
	      }
	      finally{
	    	  dataJson = null; ar = null; 
	      }	   
	      result.put("sEcho", echo);
	      result.put("iTotalRecords", total);
	      result.put("iTotalDisplayRecords", totalAfterFilter);
	      result.put("aaData", array);
	      System.out.println("Step 3 : notify MasterDTable Manage Table [End]"+new SimpleDateFormat("yyyy-MM-dd hh:mm:SSS").format(new Date()));
	 	  logwrite.logMessage("Step 3 : Tnotify MasterDTable Table [End] ", "info", NotificationMasterDTable.class);
	      getResponse();
	     

	    } catch (Exception ex) {
	    	System.out.println("Step -1 4: Exception Found : "+ex);
	  	  	logwrite.logMessage("Step -1 4: Exception Found : "+ex, "error", NotificationMasterDTable.class);
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
