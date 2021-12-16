package com.socialindia.worktypelistmaster;

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

public class WorktypeMasterDTable extends ActionSupport implements
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
			System.out.println("WorktypeMasterDTable  loading.......");
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
				dataJson.put("status", "all");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal // row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
				finaljj = new JSONObject();
				finaljj.put("servicecode", "SI20000");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.configmgmt.worktypedatable");
				String response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("worktypedetails");
				jsonList = new JSONObject();
				String worktypeName = null;
				Integer worktypeid, worktypestatus;							
				String statusflg = "";
				String action = "";
				int sno = start + 1;
				for (int i = 0; i < ar.length(); i++) {				 
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					worktypeName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "worktype_name");
					worktypeid=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "worktype_id");
					worktypestatus=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "worktype_status");
					ja = null;
					ja = new JSONArray();
					if(worktypeName!=null){
						worktypeName = new CommonUtilsDao().toFirstCharUpper(worktypeName);
					}       
					if (worktypestatus == 0) {
		            	   action +=" <a id=\"worktypeid_"+ worktypeid +"\" onclick=\"activeaction('" + worktypeid + "','"+worktypestatus+"');\" "
				            	  + " class=\"pointer\" style=\"margin-right: 10px;\">"
				            	  + " <i id=\"worktypeid_deacticon_"+ worktypeid +"\" class=\"tooltipped "+getText("button.color.deactive")+"\" data-tooltip=\"Click to Active\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\"></i>"
			            		  + " <i id=\"worktypeid_activeicon_"+ worktypeid +"\" style=\"display:none;\" class=\"tooltipped "+getText("button.color.active")+"\" data-tooltip=\"Click to Deactivate\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\"></i>"
		            			  + " </a>";
					} else if (worktypestatus == 1) {
			        	   action +=" <a id=\"worktypeid_" + worktypeid + "\" onclick=\"deleteaction('" + worktypeid + "','"+worktypestatus+"');\" "
				            	  + " class=\"pointer\" style=\"margin-right: 10px;\">"
				            	  + " <i id=\"worktypeid_activeicon_"+ worktypeid +"\" class=\"tooltipped "+getText("button.color.active")+"\" data-tooltip=\"Click to Deactivate\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\"></i>"
								  + " <i id=\"worktypeid_deacticon_"+ worktypeid +"\" style=\"display:none;\" class=\"tooltipped "+getText("button.color.deactive")+"\" data-tooltip=\"Click to Active\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\"></i>"
				            	  + " </a>";
			        	  
					}
					if (worktypestatus == 0) {
						statusflg = "<div id=\"statusval_" + worktypeid
								+ "\" >Deactive</div>";
					} else if (worktypestatus == 1) {
						statusflg = "<div id=\"statusval_" + worktypeid
								+ "\">Active</div>";
					}
					if (worktypeName == null) {
						worktypeName = "";
					}
		            
		            ja.put(sno);
		            ja.put(worktypeName);
		            ja.put(statusflg);
		            ja.put(action);
		            array.put(ja);
		            action = "";
		            sno ++;					
				}			
			 totalAfterFilter =  json_data.getInt("countAfterFilter");
			} catch (Exception ex) {
	    	    System.out.println("Step -1 : Exception Found "+getClass().getSimpleName()+" 1 : "+ex);
	    	    logwrite.logMessage("Step -1 : Exception Found : "+ex, "error", WorktypeMasterDTable.class);
			} finally {
	    	  ar = null; dataJson = null; finaljj = null; json = null; json_data = null; jsonList = null; ja = null;
			}
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
		    System.out.println("Step 3 : Worktype MasterDTable Manage Table [End]"+new SimpleDateFormat("yyyy-MM-dd hh:mm:SSS").format(new Date()));
		 	logwrite.logMessage("Step 3 : Worktype MasterDTable Table [End] ", "info", WorktypeMasterDTable.class);
		    getResponse();	   
	    } catch (Exception ex) {
	    	 System.out.println("Step -1 : Exception Found "+getClass().getSimpleName()+" 2 : "+ex);
	  	  	 logwrite.logMessage("Step -1 4: Exception Found : "+ex, "error", WorktypeMasterDTable.class);
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
