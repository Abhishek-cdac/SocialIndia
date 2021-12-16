package com.socialindia.biometric;

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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

/**
 * For merchant user data table.
 */
public class biouserMasterTable extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware, ServletContextAware {

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
	private String searchval;
	private String searchname;
	private String slectfield;
	private String society;

	/**
	 * For merchant user records showing on data table.
	 */
	public String execute() {
		try {
			sdtEcho = request.getParameter("sEcho");
			sdtSearch = request.getParameter("sSearch");
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
					Commonutility.toWriteConsole(dir);
				}
			}
			int total = 0;
			int totalAfterFilter = 0;
			JSONArray ar = null;
			JSONArray ja = null;
			int lvrSno = 1;
			Map sessionMap = ActionContext.getContext().getSession();
			String lcsocitid = String.valueOf(sessionMap.get("sSoctyId"));
			society = lcsocitid;

			try {
				obj.put("servicecode", "SI0006");
				JSONObject jbb = new JSONObject();
				jbb.put("count1", "yes");
				jbb.put("countF1", "yes");
				jbb.put("startrow", String.valueOf(start));// starting row
				jbb.put("totalrow", String.valueOf(amount));// overalltotal row
				if (society == null || society.equalsIgnoreCase("")) {
					jbb.put("society", lcsocitid);
					jbb.put("searchflg", "");
				} else {
					jbb.put("society", society);
					jbb.put("searchflg", searchval);
				}

				if (slectfield == null || slectfield.equalsIgnoreCase("")) {
					jbb.put("slectfield", "");
				} else {
					jbb.put("slectfield", slectfield);
				}

				if (searchname == null || searchname.equalsIgnoreCase("")) {
					jbb.put("searchname", "");
				} else {
					jbb.put("searchname", searchname);
					jbb.put("searchflg", searchval);
				}
				if (slectfield != null && society.equalsIgnoreCase("")) {

				} else {

				}
				jbb.put("srchdtsrch", sdtSearch);// overalltotal row
				
				obj.put("data", jbb);
				String jsonTextFinal = obj.toString();
				
				Commonutility.toWriteConsole("jsonTextFinal:"	+ jsonTextFinal);
				
				jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("biousermanagement.view.url");
				String response = commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("userMgmt");
				JSONObject jsonList = null;
				for (int i = 0; i < ar.length(); i++) {
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					
					String bioResidentId = jsonList.getString("bioResidentId");
					bioResidentId = bioResidentId == null ? "" : bioResidentId;
					
					String bioResidentName = jsonList.getString("bioResidentName");
					bioResidentName = bioResidentName == null ? "" : bioResidentName;
					
					String bioMobileNo = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "bioMobileNo");
					bioMobileNo = bioMobileNo == null ? "" : bioMobileNo;
					
					String bioEmail = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "bioEmail");
					bioEmail = bioEmail == null ? "" : bioEmail;
					
					String bioRecordStatus = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "bioRecordStatus");
					bioRecordStatus = bioRecordStatus == null ? "" : bioRecordStatus;
					
					String bioIsSendPushNotification = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "bioIsSendPushNotification");
					bioIsSendPushNotification = bioIsSendPushNotification == null ? "" : bioIsSendPushNotification;
					
					String bioLocation = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "bioLocation");
					bioLocation = bioLocation == null ? "" : bioLocation;
					
					
					String bioDeviceLogId = jsonList.getString("bioDeviceLogId");
					bioDeviceLogId = bioDeviceLogId == null ? "" : bioDeviceLogId;
					
					String bioDeviceId = jsonList.getString("bioDeviceId");
					bioDeviceId = bioDeviceId == null ? "" : bioDeviceId;
					
					String bioUserId = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "bioUserId");
					bioUserId = bioUserId == null ? "" : bioUserId;
					
					String bioLogDate = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "bioLogDate");
					bioLogDate = bioLogDate == null ? "" : bioLogDate;
					
					String bioStatusCode = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "bioStatusCode");
					bioStatusCode = bioStatusCode == null ? "" : bioStatusCode;
					
					String bioDuration = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "bioDuration");
					bioDuration = bioDuration == null ? "" : bioDuration;
					
					String bioVerificationMode = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "bioVerificationMode");
					bioVerificationMode = bioVerificationMode == null ? "" : bioVerificationMode;
					

					ja = new JSONArray();

					ja.put(lvrSno);
					ja.put(bioResidentId);
					ja.put(bioResidentName);
					ja.put(bioMobileNo);
					ja.put(bioEmail);
					ja.put(bioRecordStatus);
					ja.put(bioIsSendPushNotification);
					ja.put(bioLocation);
					
					
					ja.put(bioDeviceLogId);
					ja.put(bioDeviceId);
					ja.put(bioUserId);
					ja.put(bioLogDate);
					ja.put(bioStatusCode);
					ja.put(bioDuration);
					ja.put(bioVerificationMode);
					
					array.put(ja);
					lvrSno++;
				}
				totalAfterFilter = json_data.getInt("countAfterFilter");
			} catch (Exception ex) {
				StackTraceElement[] elements = ex.getStackTrace();
				StringBuilder builder = new StringBuilder(); 
				for (int i = 0; i < elements.length; i++) {
					builder.append(elements[i].toString());
				}
				Commonutility.toWriteConsole("Exception Found in userMasterTable.class 1 [inner]: "	+ builder.toString());
			} finally {

			}
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
			getResponse();
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Exception Found in userMasterTable.class 2 [outer] : "+ ex);
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


	public String getSearchval() {
		return searchval;
	}

	public void setSearchval(String searchval) {
		this.searchval = searchval;
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
