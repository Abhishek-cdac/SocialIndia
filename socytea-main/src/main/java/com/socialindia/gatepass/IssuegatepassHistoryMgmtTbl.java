package com.socialindia.gatepass;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class IssuegatepassHistoryMgmtTbl extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String idtDisplayStart;
	private String idtDisplayLength;
	private String sdtEcho;
	private String idtSortCol0;
	private String sdtSortDir0;
	private String sdtSearch;
	private String societyid;
	private String sdtSearchff;
	String issuelist = "";

	private JSONObject result = new JSONObject();
	JSONArray aaData = new JSONArray();
	private String idtTotalDisplayRecords = "";
	private String idtTotalRecords = "";
	private HttpServletResponse response;
	private HttpServletRequest request = ServletActionContext.getRequest();
	//private String sqlQuery;
	//private String CmpyProfileImage = "";
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao();

	  public String execute() {
	    try {
			Commonutility.toWriteConsole("Gate pass ListDatatable  loading.......");

			String manualsearch = "";
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
			JSONArray lvrrtnjary = null;
			try {
				String lcsocitid = String.valueOf(sessionMap.get("sSoctyId"));
				JSONObject dataJson = new JSONObject();
				dataJson.put("status", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("startlimit", String.valueOf(start));
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal												// row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				//dataJson.put("rid", String.valueOf(sessionMap.get("USERID")));
				dataJson.put("society", lcsocitid);
				dataJson.put("rid", String.valueOf(sessionMap.get("USERID")));
				JSONObject finaljj = new JSONObject();
				/*finaljj.put("townshipid", "PVEKSRONJ5L5L2STNAXU3P9NN");
				finaljj.put("societykey", "SWY5351634YN1C4WKNLYW2PAK");	*/
				finaljj.put("servicecode", "0058");
				finaljj.put("is_mobile", "0");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.gatepass.issuegatepasshistorymgmttbl");
				String response = commonObj.jsonRequest(finalUrl, temp);
				Commonutility.toWriteConsole("response ------------- " + response);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = (JSONObject) Commonutility.toHasChkJsonRtnValObj(json, "data");
				String lvrtotalcnt =(String) Commonutility.toHasChkJsonRtnValObj(json_data,"InitCount");
				if(Commonutility.toCheckisNumeric(lvrtotalcnt)){
					total = Integer.parseInt(lvrtotalcnt);
				} else {
					total =0;
				}

				ar = (JSONArray) Commonutility.toHasChkJsonRtnValObj(json_data,"issuegatepasshistorydetails");
				JSONObject jsonList = new JSONObject();
				String visitor_in_issue_date = null, visitor_out_issue_date = null, action = "", visitor_pass_id = null,visitor_name = null,creator_name=null,visitor_mobile_no=null, block_name = null, townshipName = null, societyName = null, visitor_pass_no = null, status = null, fackey = null, imagewebpath = "";
				int sno = 1;
				String visit_in_date="";
				 String visit_out_date="";
				for (int i = 0; i < ar.length(); i++) {
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					visitor_pass_id = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "visitor_pass_id");
					visitor_pass_no = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "visitor_pass_no");
					visitor_in_issue_date = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "visitor_in_datetime");
					visitor_out_issue_date = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "visitor_out_datetime");

					 DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					 if(visitor_in_issue_date!=null && visitor_in_issue_date!="")
					 { Date d2 = df2.parse(visitor_in_issue_date);
					   visit_in_date=commonObj.getDateobjtoFomatDateStr(d2, "dd-MM-yyyy hh:mm:ss");
					 }else{
						 visit_in_date="";
					 }
					 if(visitor_out_issue_date!=null && !visitor_out_issue_date.equalsIgnoreCase(""))
					 {
						 Date d3 = df2.parse(visitor_out_issue_date);
					  visit_out_date=commonObj.getDateobjtoFomatDateStr(d3, "dd-MM-yyyy hh:mm:ss");
					 }else{
						 visit_out_date="";
					 }

					visitor_name=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "visitor_name");
					creator_name=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "creator_name");
					visitor_mobile_no=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "visitor_mobile_no");
					block_name=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "block_name");
					status=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "status");
					lvrrtnjary = new JSONArray();
					 action += "<div class=\"merchant_town\"><a class=\"left\" style=\"margin-right:4%;cursor: pointer;\" onclick=\"viewgatepassaction('" + visitor_pass_no + "');\">"
				          		+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>";

		            	/* action += "<a class=\"left\" style=\" margin-right: 4%;cursor: pointer;\" onclick=\"deletegatepassaction('" + visitor_pass_id+ "');\">"
				            		+ "<i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\"10\" data-position=\"bottom\"></i></a>";*/

					lvrrtnjary.put(sno);
					lvrrtnjary.put(visitor_name);
					lvrrtnjary.put(visitor_mobile_no);
					lvrrtnjary.put(visit_in_date);
					lvrrtnjary.put(visit_out_date);
					lvrrtnjary.put(action);
					array.put(lvrrtnjary);
					action = "";
					sno++;
					lvrrtnjary = null;
				}
			String lvrCntflter = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "countAfterFilter");
			if(Commonutility.toCheckisNumeric(lvrCntflter)){
				totalAfterFilter =  Integer.parseInt(lvrCntflter);
			} else {
				totalAfterFilter =  0;
			}
			} catch (Exception ex) {
				Commonutility.toWriteConsole("Excption found in "+getClass().getSimpleName()+".class : " + ex);
			} finally {
				ar = null; lvrrtnjary = null;
			}
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
			getResponse();

	    } catch (Exception ex) {
	    	Commonutility.toWriteConsole("Excption found in "+getClass().getSimpleName()+".class : " + ex);
	    }
	    return SUCCESS;
	  }

	  /**
	   * Response.
	   */
	public void getResponse() {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			out.print(result);
			out.close();
		} catch (IOException ex) {
			Commonutility.toWriteConsole("Excption found in "+getClass().getSimpleName()+".class Response : " + ex);
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




	public String getSdtSearchff() {
		return sdtSearchff;
	}

	public void setSdtSearchff(String sdtSearchff) {
		this.sdtSearchff = sdtSearchff;
	}

	public String getSocietyid() {
		return societyid;
	}

	public void setSocietyid(String societyid) {
		this.societyid = societyid;
	}


}

