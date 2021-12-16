package com.socialindia.generalmgnt;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.StaffMasterTblVo;

/**
 * For merchant user data table.
 */
public class StaffMasterTable extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware {

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
  List<StaffMasterTblVo> userList = new ArrayList<StaffMasterTblVo>();
  JSONObject obj = new JSONObject();
  private Common commonObj = new CommonDao();
  /**
   * For merchant user records showing on data table.
   */
  public String execute() {
		Log logwrite = null;
		JSONArray array = null;
		JSONArray ja = null;
		try {
			logwrite = new Log();
			Commonutility.toWriteConsole("Staff Details fetch block StaffMasterTable.class [Start]");
			logwrite.logMessage("Step 1 : Staff Details fetch block StaffMasterTable.class [Start]", "info", StaffMasterTable.class);			
			sdtEcho = request.getParameter("sEcho");
			sdtSearch = request.getParameter("sSearch");
			if (sdtSearch == null) {
				sdtSearch = "";
			}
			array = new JSONArray();
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
					Commonutility.toWriteConsole(dir);
				}
			}
			int total = 0;
			int totalAfterFilter = 0;
			JSONArray ar = null;
			JSONObject lvRqstdatajson = null;
			JSONObject jsonList =null;
			JSONObject lvRspovrjsonobj = null;
			JSONObject lvRspjsondataobj = null;
			StringBuilder lvrSbldraction = null;
			try {
				obj.put("servicecode", "SI4001");
				lvRqstdatajson = new JSONObject();
				lvRqstdatajson.put("count1", "yes");
				lvRqstdatajson.put("countF1", "yes");
				lvRqstdatajson.put("staffstatus", "1");
				lvRqstdatajson.put("srchdtsrch", sdtSearch);// overalltotal row
				lvRqstdatajson.put("startrow", String.valueOf(start));// starting row
				lvRqstdatajson.put("totalrow", String.valueOf(amount));// overalltotal row
				obj.put("data", lvRqstdatajson);
				String jsonTextFinal = obj.toString();
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("staffmanagement.view.url");
				logwrite.logMessage("Step 2 : Service Url : "+finalUrl, "info", StaffMasterTable.class);
				String response = commonObj.jsonRequest(finalUrl, temp);
				lvRspovrjsonobj = new JSONObject(response);
				lvRspjsondataobj = lvRspovrjsonobj.getJSONObject("data");
				total = lvRspjsondataobj.getInt("InitCount");
				ar = lvRspjsondataobj.getJSONArray("userMgmt");
				jsonList = new JSONObject();
				int lvrSno = 1;				
				for (int i = 0; i < ar.length(); i++) {
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					String staffName = jsonList.getString("staffName");
					String staffMobno = jsonList.getString("staffMobno");
					String staffEmail = jsonList.getString("staffEmail");
					int staffID = jsonList.getInt("staffID");
					String staffAddr = jsonList.getString("staffAddr");
					//userList.add(new StaffMasterTblVo(staffName, staffMobno,staffEmail, staffID, staffAddr));									
					ja = null; 
					ja = new JSONArray();
					lvrSbldraction = null;
					lvrSbldraction = new StringBuilder();
					lvrSbldraction.append("<a class=\"societystyle\" onclick=\"viewstaff('" + staffID
			                + "');\"><i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>");
					lvrSbldraction.append("<a class=\"societystyle\" onclick=\"editstaff('"
			                + staffID + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"bottom\"></i></a>");
					lvrSbldraction.append("<a class=\"societystyle\" onclick=\"deletestaffaction('" + staffID+ "');\" ><i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\"10\" data-position=\"bottom\"\"></i></a>"); 
					lvrSbldraction.append("<a class=\"societystyle\" onclick=\"salaryuser('" + staffID + "');\" >"
//			            	+ "<i class=\"tooltipped "+getText("button.color.salary")+"\" data-tooltip=\"Salary\" data-delay=\"10\" data-position=\"bottom\"></i></a>");
					+ "<i title=\"Salary\" class=\"fa fa-inr\" style=\"font-size: 20px; margin-top: 10px;\" data-tooltip=\"Salary\" data-delay=\"10\" data-position=\"bottom\"></i></a>");
					lvrSbldraction.append("<a class=\"societystyle\" onclick=\"workalert('" + staffID  + "');\">"
//			            	+ "<i class=\"tooltipped "+getText("button.color.staffwork")+"\" data-tooltip=\"Work\" data-delay=\"10\" data-position=\"right\"></i></a>");
					+ "<i title=\"Work\" class=\"fa fa-briefcase\" style=\"font-size: 20px; margin-top: 10px; color:gray\" data-tooltip=\"Work\" data-delay=\"10\" data-position=\"right\"></i></a>");
					ja.put(lvrSno);
					ja.put(staffName);
					ja.put(staffEmail);
					/*ja.put(staffAddr);*/
					ja.put("<div class=\"breakword\">"+staffAddr+"</div>");
					ja.put(staffMobno);
					
					//Salary, Work: Start
					String salary = getSalary(staffID);
					ja.put(salary);
					
					String work = getWork(staffID);
					ja.put(work);
					//Salary, Work: end
					
					ja.put(lvrSbldraction.toString());
					array.put(ja);					
					lvrSbldraction = null;
					lvrSno++;										
				}
				totalAfterFilter = lvRspjsondataobj.getInt("countAfterFilter");
				logwrite.logMessage("Step 3 : Service Called and data fetched.", "info", StaffMasterTable.class);
			} catch (Exception ex) {
				Commonutility.toWriteConsole("Exception found StaffMasterTable.class 1 : " + ex);
				logwrite.logMessage("Step -1 : Exception found StaffMasterTable.class 1 : " + ex, "info", StaffMasterTable.class);
			}finally{
				ar = null;lvRqstdatajson = null;jsonList =null;
				lvRspjsondataobj = null; lvRspovrjsonobj = null;
			}
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
			getResponse();							
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Exception found StaffMasterTable.class 1 : " + ex);
			logwrite.logMessage("Step -1 : Exception found StaffMasterTable.class 1 : " + ex, "info", StaffMasterTable.class);
		} finally {
			ja = null;
			array = null;
		}
		return SUCCESS;
   }

	private String getWork(int staffID) {
		Commonutility.toWriteConsole("Step 1 : Fetching Staff Work.");
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject locRqstJsonObj = null;
		commonObj = new CommonDao();
		JSONObject obj = null;
		String work = "";
		try {
			obj = new JSONObject();
			locRqstJsonObj = new JSONObject();
			obj.put("staffid", staffID);
			obj.put("ivrDecissBlkflag", "3");
			locRqstJsonObj.put("servicecode", "SI4009");
			locRqstJsonObj.put("data", obj);
			String jsonTextFinal = locRqstJsonObj.toString().trim();
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String url = getText("staffmanagement.workalart.staffwork");
			String response = commonObj.jsonRequest(url, temp);
			Commonutility.toWriteConsole("Step 2 : Response : " + response);
			if (response != null && !response.equalsIgnoreCase("null")
					&& response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					JSONObject json_data = locObjRecvJson.getJSONObject("data");
					work = json_data.getString("Workdetails");
				}
			}
		} catch (Exception e) {
			Commonutility.toWriteConsole("ERROR: Fetching Staff Work."
					+ e.getMessage());
		}
		return work;
	}

	private String getSalary(int staffID) {

		Commonutility.toWriteConsole("Step 1 : Staff Salary Fetching.");

		JSONObject locObjRecvJson = null;
		JSONObject json_data = null;
		String salary = "";
		try {
			JSONObject obj = new JSONObject();
			obj.put("staffid", staffID);
			obj.put("ivrDecissBlkflag", "3");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI4007");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString().trim();
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			Commonutility.toWriteConsole("Final JSON : " + jsonTextFinal);
			String url = getText("staffmanagement.salary.staff");
			String response = commonObj.jsonRequest(url, temp);
			Commonutility.toWriteConsole("Response salary : " + response);
			if (response != null && !response.equalsIgnoreCase("null")
					&& response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					json_data = locObjRecvJson.getJSONObject("data");
					salary = (String) json_data.getString("monthlySalary");
				}
			}
		} catch (Exception e) {
			Commonutility.toWriteConsole("ERROR: Staff Salary Fetching."
					+ e.getMessage());
		}

		return salary;
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
