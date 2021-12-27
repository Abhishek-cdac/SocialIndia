package com.socialindia.complaintsmgmt;

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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.complaints.persistance.ComplaintsTblVO;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class complaintsmgmtMasterTable extends ActionSupport implements
		SessionAware, ServletRequestAware, ServletResponseAware,
		ServletContextAware {

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
	private String complaintProfileImage = "";
	List<ComplaintsTblVO> compltList = new ArrayList<ComplaintsTblVO>();
	// UamServices uamDeatils = new UamDaoServices();
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao();
	private String searchval;
	private String searchname;
	private String slectfield;
	private String society;
	private String townshipid;

	/**
	 * For merchant user records showing on data table.
	 */
	public String execute() {
		try {
			System.out.println("complaintDatatable  loading.......");
			// HttpServletRequest request = ServletActionContext.getRequest();
			Map sessionMap = ActionContext.getContext().getSession();
			String icol = "complaintsId";
			String table = "ComplaintsTblVO";
			String manualsearch = "";
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
			String lcsocitid = String.valueOf(sessionMap.get("sSoctyId"));
			try {
				JSONObject dataJson = new JSONObject();
				if (society == null || society.equalsIgnoreCase("")
						|| society.equalsIgnoreCase("undefined")) {// session
					if (lcsocitid != null && !lcsocitid.equalsIgnoreCase("-1")) {
						dataJson.put("society", lcsocitid);
					} else {
						dataJson.put("society", society);
					}
				} else {
					dataJson.put("society", society);
				}
				dataJson.put("complaintstatus", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");

				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// total row
				dataJson.put("srchdtsrch", sdtSearch);
				dataJson.put("crntusrloginid",
						String.valueOf(sessionMap.get("USERID")));// Current
																	// login id
				if (slectfield == null || slectfield.equalsIgnoreCase("")
						|| slectfield.equalsIgnoreCase("undefined")) {
					dataJson.put("slectfield", "");
				} else {
					dataJson.put("slectfield", slectfield);
				}
				if (searchname == null || searchname.equalsIgnoreCase("")) {
					dataJson.put("searchname", "");
				} else {
					dataJson.put("searchname", searchname);
					dataJson.put("searchflg", searchval);
				}
				if (slectfield != null && society.equalsIgnoreCase("")) {

				} else {

				}
				JSONObject finaljj = new JSONObject();
				finaljj.put("servicecode", "SI9006");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.complaintmgmt.complaintmgmtdatable");
				String response = commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("cmpltntdetails");
				JSONObject jsonList = new JSONObject();
				String cmpfromUsrName = null, complaintdesc = null, complainttttle = null, cmpltstatus = null, cmpltentryby = null, cmpltotheremail = null, complaintemailid = null, complaintid = null, complaint_to = null, action = "", cmplt_toemailid = "", cmplt_tomobno = "", cmplt_toname = "";
				int complaint_id;
				JSONArray ja;
				String townshipname = "", societyname = "", cmplttousrid = null, cmplttogrpid = null, cmpltfromgrpcode = null, cmpltfrmusrid = null, cmpltclosereason = null, statusflgstr = "", cmpltto = null, closereason = null, cmplt_frmmobno = "";
				int sno = start + 1;
				String lvrCmplttodst = null, lvrCmplttoflg = null, lvrmodifydate;
				for (int i = 0; i < ar.length(); i++) {
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					townshipname = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList,
									"cmplttownshipname");
					societyname = (String) Commonutility.toHasChkJsonRtnValObj(
							jsonList, "cmpltsocietyname");
					cmpfromUsrName = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmpltfrmusrname");
					complaintdesc = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmpltdesc");
					complainttttle = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmplttitle");
					complaintid = (String) Commonutility.toHasChkJsonRtnValObj(
							jsonList, "cmpltid");
					cmpltstatus = (String) Commonutility.toHasChkJsonRtnValObj(
							jsonList, "cmpltstatus");
					cmpltentryby = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmpltentryby");
					cmpltotheremail = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmpltotheremail");
					complaint_to = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList,
									"cmpltcommiteeemail");

					cmplttousrid = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmplttouserid");
					cmplttogrpid = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmplttogrpid");

					cmpltfromgrpcode = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList,
									"cmpltfrmusrgrpcode");
					cmpltfrmusrid = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmpltfrmusrid");

					cmpltclosereason = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmpltclosereason");
					cmplt_toemailid = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmplt_toemail");
					cmplt_tomobno = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmplt_tomobno");
					cmplt_toname = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmplt_toname");
					cmplt_frmmobno = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmpltfrmmobno");
					lvrCmplttodst = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmplttodest");
					lvrCmplttoflg = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmplttoflg");
					lvrmodifydate = (String) Commonutility
							.toHasChkJsonRtnValObj(jsonList, "cmpltmodifydate");

					// for()
					// compltList.add(new
					// ComplaintsTblVO(Integer.parseInt(complaintid),
					// complaintName, townshipname,societyname,
					// complaintdesc,complainttttle,Integer.parseInt(cmpltstatus),Integer.parseInt(cmpltentryby),cmpltotheremail,complaint_to,cmpltlabormercid,cmpltlabormercgrpid,cmpltfromgrpcode,cmpltfrmusrid,cmpltclosereason));
					if (cmpltstatus.equalsIgnoreCase("3")) {
						statusflgstr = "Resolved";
					} else if (cmpltstatus.equalsIgnoreCase("2")) {
						statusflgstr = "Declined";
					} else if (cmpltstatus.equalsIgnoreCase("1")) {
						statusflgstr = "Pending";
					} else if (cmpltstatus.equalsIgnoreCase("0")) {
						statusflgstr = "Delete";
					} else {
						statusflgstr = "Delete";
					}

					/*
					 * if (cmpltstatus.equalsIgnoreCase("3")) { statusflgstr =
					 * "Complete"; } else if (cmpltstatus.equalsIgnoreCase("2"))
					 * { statusflgstr = "Progress"; } else if
					 * (cmpltstatus.equalsIgnoreCase("1")) { statusflgstr =
					 * "Active"; } else if (cmpltstatus.equalsIgnoreCase("0")) {
					 * statusflgstr = "Deactivate"; } else { statusflgstr =
					 * "Deactivate"; }
					 */

					if (cmpltfromgrpcode.equalsIgnoreCase("Resident")) {
						cmpltto = "Committee";
					} else {
						cmpltto = "External [Out to Society]";
					}

					action += "<div class=\"divactionicon\"><a style=\"cursor: pointer;\"   onclick=\"viewcomplaint('"
							+ complaintid
							+ "');\" >"
							+ " <i class=\"tooltipped "
							+ getText("button.color.view")
							+ "\" data-tooltip=\"View\" data-delay=\""
							+ getText("material.tooltip.delay")
							+ "\" data-position=\"left\" ></i></a></div>";

					if ((statusflgstr != null && statusflgstr
							.equalsIgnoreCase("Resolved"))) {
						closereason = "<a  style=\"cursor: pointer;\"  class=\"tooltipped\"  data-tooltip=\"Reason\"   data-delay=\""
								+ getText("material.tooltip.delay")
								+ "\" onclick=\"showreasonFunct('"
								+ complaintid
								+ "','"
								+ cmpltclosereason
								+ "','" + lvrmodifydate + "');\">Resolve</a>";
					} else if ((statusflgstr != null && statusflgstr
							.equalsIgnoreCase("Declined"))) {
						closereason = "<a  style=\"cursor: pointer;\"  class=\"tooltipped\"  data-tooltip=\"Reason\"   data-delay=\""
								+ getText("material.tooltip.delay")
								+ "\" onclick=\"showreasonFunct('"
								+ complaintid
								+ "','"
								+ cmpltclosereason
								+ "','" + lvrmodifydate + "');\">Declined</a>";
					} else if (statusflgstr != null
							&& statusflgstr.equalsIgnoreCase("Delete")) {
						closereason = "<a    class=\"\"  data-tooltip=\"\"   data-delay=\""
								+ getText("material.tooltip.delay")
								+ "\">Delete</a>";
					} else {
						closereason = statusflgstr;
						action += "<div onclick=\"createclosereason('"
								+ complaintid
								+ "')\" class=\"divactionicon\">"
								+ "<i data-tooltip=\"Close\" data-delay=\""
								+ getText("material.tooltip.delay")
								+ "\" data-position=\"bottom\" class=\"tooltipped "
								+ getText("button.color.close")
								+ "\" ></i></div>";
					}

					if (statusflgstr != null
							&& !statusflgstr.equalsIgnoreCase("Delete")) {
						action += " <div onclick=\"deletecomplaintaction('"
								+ complaintid
								+ "');\" class=\"divactionicon\">"
								+ "<i data-tooltip=\"Delete\" data-delay=\""
								+ getText("material.tooltip.delay")
								+ "\" data-position=\"bottom\" class=\"tooltipped "
								+ getText("button.color.delete")
								+ " \" ></i></div> ";
					}
					
					int grp = (Integer) sessionMap.get("GROUPCODE");
					
					if(grp == 1){
						if (cmpltfromgrpcode.equalsIgnoreCase("Resident") && statusflgstr.equalsIgnoreCase("pending")) {
							action += "<div onclick=\"emailFunction('"
									+ complaintid
									+ "','"
									+ complaint_to
									+ "','"
									+ cmpltfromgrpcode
									+ "');\"  class=\"divactionicon\" >"
									+ "<a style=\"cursor: pointer;color: black;\"  href=\"#!\" > <i class=\"tooltipped "
									+ getText("button.color.email")
									+ "\" data-tooltip=\"Email\" data-delay=\""
									+ getText("material.tooltip.delay")
									+ "\" data-position=\"bottom\" ></i> </a><span title=\"\"></span></div>";
	
							action += "<div onclick=\"smsFunction('"
									+ complaintid
									+ "','"
									+ cmplt_frmmobno
									+ "','"
									+ cmpltfromgrpcode
									+ "');\" class=\"divactionicon\">"
									+ "<a style=\"cursor: pointer;color: black;\" href=\"#!\" > "
									+ "<i  data-tooltip=\"Sms\" data-delay=\""
									+ getText("material.tooltip.delay")
									+ "\" data-position=\"right\" class=\"tooltipped "
									+ getText("button.color.sms")
									+ "\"></i> </a><span title=\"\"></span></div>";
						} else {
	
						}
					}

					action += "</div>";
					ja = null;
					ja = new JSONArray();

					if (cmpfromUsrName == null) {
						cmpfromUsrName = "";
					}
					if (townshipname == null) {
						townshipname = "";
					}
					if (societyname == null) {
						societyname = "";
					}
					if (complainttttle == null) {
						complainttttle = "";
					}
					if (complaintdesc == null) {
						complaintdesc = "";
					}
					ja.put(sno);
					ja.put(townshipname);
					ja.put(societyname);
					ja.put(cmpfromUsrName);
					ja.put(complainttttle);
					ja.put(lvrCmplttodst);
					ja.put(complaintid);
					ja.put(closereason);
					ja.put(action);
					array.put(ja);
					action = "";
					sno++;
				}
				totalAfterFilter = json_data.getInt("countAfterFilter");
			} catch (Exception ex) {
				Commonutility
						.toWriteConsole("Step -1 : Exception found in complaintsmgmtMasterTable.class : "
								+ ex);
			} finally {
				ar = null;
			}

			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
			getResponse();

		} catch (Exception ex) {
			ex.printStackTrace();
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

	public String getcomplaintProfileImage() {
		return complaintProfileImage;
	}

	public void setcomplaintProfileImage(String complaintProfileImage) {
		this.complaintProfileImage = complaintProfileImage;
	}

	public String getSearchval() {
		return searchval;
	}

	public void setSearchval(String searchval) {
		this.searchval = searchval;
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

	public String getTownshipid() {
		return townshipid;
	}

	public void setTownshipid(String townshipid) {
		this.townshipid = townshipid;
	}

}
