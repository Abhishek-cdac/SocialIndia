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

public class ImagemonitoringDTable extends ActionSupport implements
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
			System.out.println("ImagemonitoringDTable  loading.......");
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
				finaljj.put("servicecode", "SI10008");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.monitoring.imagemonitoringdatable");
				String response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("imgmontdetails");
				jsonList = new JSONObject();
				String imgmontName = null,imgmontusrname=null,imgmontentrytime=null,imgmonttblflag=null,checkedsts1=null,checkedsts2=null;
				Integer imgmontid, imgmontstatus,imgmontusr_id,imgmontusr_sts,imgmontfeed_id,filetype;						
				String statusflg = "";
				String action = "",imagepath="";
				int sno = start + 1;
				for (int i = 0; i < ar.length(); i++) {		
					 imagepath="";
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					imgmontName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "imgmont_attachname");
					imgmontid=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "imgmont_id");
					imgmontstatus = (Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "imgmont_status");
					imgmontusrname=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "imgmont_usrname");
					imgmontentrytime=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "imgmont_entrydate");
					imgmonttblflag=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "imgmont_tblflag");
					imgmontfeed_id=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "imgmont_feedid");
					imgmontusr_id=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "imgmont_usrid");
					imgmontusr_sts = (Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "imgmontusr_status");
					filetype = (Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "imgmont_filetype");
					imgmontentrytime=imgmontentrytime.substring(0, 10);
					ja = null;
					ja = new JSONArray();
															
					if (imgmontName.length() > 0) {    	   
						imagepath+="<img id=\"myImg_"+imgmontid+"\" name=\"ImageFileName\" class=\"webimagedatable\" "
							+ "src=\"/templogo/feed/mobile/"+imgmontfeed_id+"/"+imgmontName+"\" onclick=\"toViewlargesizeimgwithsrc(this.id,'/templogo/feed/web/"+imgmontfeed_id+"/"+imgmontName+"');\">"; 
					} else {    	 
						imagepath +=""; 
					}
					if (imgmontstatus == 0) {
						checkedsts1 ="checked";
						action +="<p><input type=\"checkbox\"  checked class=\"filled-in\"  id=\"imgmontid_"+ imgmontid +"\"  onclick=\"activeactioncontent('" + imgmontid + "','"+imgmonttblflag+"');\">"
								 + "<label for=\"imgmontid_"+ imgmontid +"\" id=\"imgmontcnt_lbl_"+ imgmontid +"\"> &nbsp;&nbsp;UnBlock Image </label></p>";
					}else{
						action +="<p><input type=\"checkbox\"  class=\"filled-in\"  id=\"imgmontid_"+ imgmontid +"\"  onclick=\"deleteactioncontent('" + imgmontid + "','"+imgmonttblflag+"');\">"
								 + "<label for=\"imgmontid_"+ imgmontid +"\" id=\"imgmontcnt_lbl_"+ imgmontid +"\"> &nbsp;&nbsp;Block Image </label></p>";
					}
					
					if(imgmontusr_sts == 0){
						action += "<p><input type=\"checkbox\" checked class=\"filled-in\"  id=\"imgmontusr"+ imgmontid +"\" onclick=\"activeactionUser('" + imgmontusr_id + "','"+imgmontid+"');\">"
							  	 + "<label for=\"imgmontusr"+ imgmontid +"\" id=\"imgmontusr_lbl_"+ imgmontid +"\"> &nbsp;&nbsp;UnBlock User</label></p>";
					}else{
						action += "<p><input type=\"checkbox\"  class=\"filled-in\"  id=\"imgmontusr"+ imgmontid +"\" onclick=\"deleteactionUser('" + imgmontusr_id + "','"+imgmontid+"');\">"
							  	 + "<label for=\"imgmontusr"+ imgmontid +"\" id=\"imgmontusr_lbl_"+ imgmontid +"\"> &nbsp;&nbsp;Block User</label></p>";
					}									
					imgmontusrname = "<div id=\"statusval_" + imgmontid+ "\" >Resident Name : <a href=\"viewresidentdetails?deleteresidentid="+imgmontusr_id+"\" target=\"_blank\" data-position=\"bottom\" data-delay=\"10\" data-tooltip=\"Click to View\" class=\"pointer tooltipped\">"+imgmontusrname+"</a></div><div>Posted Date : "+imgmontentrytime+" </div>";
					
					if (imgmontName == null) {
						imgmontName = "";
					}		            
		            ja.put(sno);
		            ja.put(imagepath);
		            ja.put(imgmontusrname);
		            ja.put(action);
		            array.put(ja);
		            action = "";
		            sno ++;					
				}			
			 totalAfterFilter =  json_data.getInt("countAfterFilter");
			} catch (Exception ex) {
	    	    System.out.println("Step -1 : Exception Found "+getClass().getSimpleName()+" 1 : "+ex);
	    	    logwrite.logMessage("Step -1 : Exception Found : "+ex, "error", ImagemonitoringDTable.class);
			} finally {
	    	  ar = null; dataJson = null; finaljj = null; json = null; json_data = null; jsonList = null; ja = null;
			}
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
		    System.out.println("Step 3 : imgmont MasterDTable Manage Table [End]"+new SimpleDateFormat("yyyy-MM-dd hh:mm:SSS").format(new Date()));
		 	logwrite.logMessage("Step 3 : imgmont MasterDTable Table [End] ", "info", ImagemonitoringDTable.class);
		    getResponse();	   
	    } catch (Exception ex) {
	    	 System.out.println("Step -1 : Exception Found "+getClass().getSimpleName()+" 2 : "+ex);
	  	  	 logwrite.logMessage("Step -1 4: Exception Found : "+ex, "error", ImagemonitoringDTable.class);
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
