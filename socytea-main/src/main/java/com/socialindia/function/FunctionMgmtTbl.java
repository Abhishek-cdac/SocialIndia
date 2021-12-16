package com.socialindia.function;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.socialindia.login.EncDecrypt;

public class FunctionMgmtTbl extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware,ServletContextAware{

	private static final long serialVersionUID = 1L;
	private String idtDisplayStart;
	private String idtDisplayLength;
	private String sdtEcho;
	private String idtSortCol0;
	private String sdtSortDir0;
	private String sdtSearch;
	private String societyid;
	private String sdtSearchff;
	private JSONObject result = new JSONObject();
	JSONArray aaData = new JSONArray();
	private String idtTotalDisplayRecords = "";
	private String idtTotalRecords = "";
	private HttpServletResponse response;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private String sqlQuery;
	private String CmpyProfileImage = "";
	List<FunctionMgmtTblVO> funList = new ArrayList<FunctionMgmtTblVO>();
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao();

	public String execute() {
		try {
			System.out.println("Function ListDatatable  loading.......");
			String icol = "FUNCTION_ID";
			String table = "FunctionMgmtTblVO";
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
			try {	 
				String lcsocitid = String.valueOf(sessionMap.get("sSoctyId"));
				JSONObject dataJson = new JSONObject();
				dataJson.put("status", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				if (societyid != null && !societyid.equalsIgnoreCase("")) {
					dataJson.put("societyid", societyid);
				} else {
					dataJson.put("societyid", lcsocitid);
				}
				JSONObject finaljj = new JSONObject();
				finaljj.put("servicecode", "SI310001");
				finaljj.put("currentloginid",String.valueOf(sessionMap.get("USERID")));
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.function.functionmgmt");
				String response = commonObj.jsonRequest(finalUrl, temp);
				System.out.println("response ---------- " + response);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("functiondetails");
				JSONObject jsonList = new JSONObject();
				String functionname = null, functionid = "", action = "", splitval = "", status = null, funtype = null;
				JSONArray functiontext = null;
				for (int i = 0; i < ar.length(); i++) {
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					functionid = jsonList.getString("functionid");
					functionname = jsonList.getString("functionname");
					status = jsonList.getString("status");
					funtype = jsonList.getString("functiontype");
					functiontext = jsonList.getJSONArray("eventemp");
					for (int j = 0; j < functiontext.length(); j++) {
						splitval += functiontext.get(j) + ", ";
					}
					funList.add(new FunctionMgmtTblVO(functionid, functionname,
							status, splitval, 0, funtype));
					splitval = "";
				}			
				totalAfterFilter = json_data.getInt("countAfterFilter");
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception get totalcount---->> " + ex);
			}
			result.put("sEcho", echo);
			try {
				String sql;
				sql = "FROM " + table + " ";
				where = userfilter;
				sqlQuery = globalsearch;

				ActionContext.getContext().getSession().put("globquery", userfilter);
				sql += where;
				String funid = "";
				String funname = "";
				String status = "";
				String funtext = "";
				String funtype = "";
				String action = "";
				try {
					FunctionMgmtTblVO userObj;
					int sno = start + 1;
					for (Iterator<FunctionMgmtTblVO> it = funList.iterator(); it.hasNext();) {
						userObj = it.next();
						JSONArray ja;
						ja = new JSONArray();
						funid = userObj.getFunctionid();
						funname = userObj.getFunctionname();
						String statusflg = userObj.getStatus();
						funtype = userObj.getFunctype();
						/*
						 * if(stscode.equalsIgnoreCase("1") && stscode!=null &&!stscode.equalsIgnoreCase("null")){
						 *  status="Active";
						 * }else{ 
						 * status="Deactive"; }
						 */
						funtext = userObj.getFunctiontext();
						if (statusflg.equalsIgnoreCase("0")) {
							action += "<a id=\"ideditid_" + funid + "\" class=\"left\" style=\"margin-right: 10px;cursor: pointer;\" onclick=\"editfunctionaction('" + funid + "','"+statusflg+"');\">"
		            		+ "<i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"bottom\"></i></a>";

							action +=" <a id=\"idcardid_"+ funid +"\" onclick=\"activeaction('" + funid + "','"+statusflg+"');\" "
	            		   + "class=\"pointer\" style=\"margin-right: 10px;\">"
	            		   + " <i id=\"idcardi_deacticon_"+ funid +"\" class=\"tooltipped "+getText("button.color.deactive")+"\" data-tooltip=\"Click to Active\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i>"
						   + " <i id=\"idcardi_activeicon_"+ funid +"\" style=\"display:none;\" class=\"tooltipped "+getText("button.color.active")+"\" data-tooltip=\"Click to Deactivate\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i>" 
	            		   + "</a>";
						} else if (statusflg.equalsIgnoreCase("1")) {
							action += "<a id=\"ideditid_" + funid + "\" class=\"left\" style=\"margin-right: 10px;cursor: pointer;\" onclick=\"editfunctionaction('" + funid + "','"+statusflg+"');\">"
		            		+ "<i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"bottom\"></i></a>";

							action +=" <a id=\"idcardid_" + funid + "\" onclick=\"deleteaction('" + funid + "','"+statusflg+"');\" "
	            		   + " class=\"pointer\" style=\"margin-right: 10px;\">"
						   +"  <i id=\"idcardi_activeicon_"+ funid +"\" class=\"tooltipped "+getText("button.color.active")+"\" data-tooltip=\"Click to Deactivate\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i>"
						   + " <i id=\"idcardi_deacticon_"+ funid +"\" style=\"display:none;\" class=\"tooltipped "+getText("button.color.deactive")+"\" data-tooltip=\"Click to Active\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i>"
						   +"</a>";
        	  
						}
						if (statusflg.equalsIgnoreCase("0")) {
							statusflg = "<div id=\"statusval_" + funid+ "\" >Deactive</div>";
						} else if (statusflg.equalsIgnoreCase("1")) {
							statusflg = "<div id=\"statusval_" + funid+ "\">Active</div>";
						}
						if (funtype.equalsIgnoreCase("1")) {
							funtype = "<div id=\"statusval1_" + funid+ "\" >Personal</div>";
						} else if (funtype.equalsIgnoreCase("2")) {
							funtype = "<div id=\"statusval1_" + funid+ "\">Society</div>";
						} else if (funtype.equalsIgnoreCase("3")) {
							funtype = "<div id=\"statusval1_" + funid+ "\">Commitee</div>";
						}
	        	
	           /* action += "<a class=\"left\" style=\"margin-right: 4%;cursor: pointer;\" onclick=\"editfunctionaction('"+ funid + "');\">"
	            		+ "<i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"bottom\"></i></a>";

	            action += "<a class=\"left\" style=\" margin-right: 4%;cursor: pointer;\" onclick=\"deleteeventaction('" + funid+"','"+stscode+"');\">"
	            		+ "<i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
	            */
						ja.put(sno);
						ja.put(funname);
						ja.put(funtext);
						ja.put(funtype);
						ja.put(statusflg);
						ja.put(action);
						array.put(ja);
						action = "";
						sno++;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				String sql2 = "SELECT count(" + icol + ") FROM " + table+ " as pr ";
				sql2 += where;
				result.put("iTotalRecords", total);
				result.put("iTotalDisplayRecords", totalAfterFilter);
				result.put("aaData", array);
				getResponse();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
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


	public String getCmpyProfileImage() {
		return CmpyProfileImage;
	}

	public void setCmpyProfileImage(String CmpyProfileImage) {
		this.CmpyProfileImage = CmpyProfileImage;
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

	/*public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	*/
	

}
