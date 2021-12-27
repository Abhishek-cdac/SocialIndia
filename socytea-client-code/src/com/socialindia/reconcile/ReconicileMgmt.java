package com.socialindia.reconcile;

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

public class ReconicileMgmt extends ActionSupport implements
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
	  private String fromdate;
	  private String townShipId;
	  private String societyId;
	  private String todate;
	  private String groupCode;
	  private String selectId;
	  private String startamount;
	  private String endamount;
	  private String transtype;
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  /**
	   * For merchant user records showing on data table.
	   */
	  public String execute() {
	    try {
	    	
	    	System.out.println("materialListDatatable  loading.......");
	      // HttpServletRequest request = ServletActionContext.getRequest();
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
	      String grpcode=null;
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
	      try {	       
	    	JSONObject dataJson=new JSONObject();
	  		dataJson.put("status", "0");// 0 - Active, 1- DeActive
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			 Map sessionMap = ActionContext.getContext().getSession();
			dataJson.put("sSoctyId", sessionMap.get("sSoctyId"));
			 String societycode=String.valueOf(sessionMap.get("sSoctyId"));
			 grpcode=String.valueOf(sessionMap.get("GROUPCODE"));
			if(todate!=null){
			dataJson.put("todate", todate);// search
			}else{
				dataJson.put("todate", "");// search
			}if(townShipId!=null){
				dataJson.put("townShipId", townShipId);
			}else{
				dataJson.put("townShipId", "");
			}if(societyId!=null && !societyId.equalsIgnoreCase("")&& !societyId.equalsIgnoreCase("null")){
				dataJson.put("societyId", societyId);
			}else{
				dataJson.put("societyId", societycode);
			}if(fromdate!=null){
				dataJson.put("fromdate", fromdate);
			}else{
				dataJson.put("fromdate", "");
			}
			if(transtype!=null){
				dataJson.put("transtype", transtype);
			}else{
				dataJson.put("transtype", "");
			}
			dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
			if(selectId!=null && !selectId.equalsIgnoreCase("null") && !selectId.equalsIgnoreCase("")){
				dataJson.put("selectId", selectId);
				dataJson.put("startamount", startamount);
				dataJson.put("endamount", endamount);	
			}
			
	  		JSONObject finaljj=new JSONObject();
	  		finaljj.put("servicecode", "SI0050");		
	  		finaljj.put("data", dataJson);	     
	        String jsonTextFinal = finaljj.toString();
	        System.out.println("============jsonTextFinal========="+jsonTextFinal);
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.reconicileMgmt.action");
			String response=commonObj.jsonRequest(finalUrl, temp);
			System.out.println("======response======="+response);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			 total =  json_data.getInt("InitCount");
			 ar = json_data.getJSONArray("paygatedetails");
			 JSONObject jsonList = new JSONObject();
			  int sno = 0;
			  String tnxmathstatus="";
			  String action="";
			  String transtype="";
				for (int i = 0; i < ar.length(); i++) {
					sno++;
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					String payId = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "paygateId");
					int lvrtranstype = Integer.parseInt((String) Commonutility.toHasChkJsonRtnValObj(jsonList, "transtype"));
					String filename = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "orderno");
					String lvrTransAmt = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "amount");
					String lvrTransdate = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "tnxdate");
					int tnxmatchstatus = Integer.parseInt((String) Commonutility.toHasChkJsonRtnValObj(jsonList, "tnxmatchstatus"));
					int tnxreconstatus = Integer.parseInt((String) Commonutility.toHasChkJsonRtnValObj(jsonList, "tnxreconstatus"));
					String lvrStscmdts = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "ststuscommand");
					if (tnxmatchstatus == 0) {
						tnxmathstatus = "Match";
					} else if (tnxmatchstatus == 1) {
						tnxmathstatus = "Unmatch";
					} else {
						tnxmathstatus = "Not Found";
					}
					
					if (tnxreconstatus == 0) {
						action = "Success";
					} else if (tnxreconstatus == 1) {
						action = "Failed";
					} else {
						action = "Error";
					}
					
					if (lvrtranstype == 2) {
						transtype = "Maintenance Payment";
					} else if (lvrtranstype == 1) {
						transtype = "Utility Payment";
					} else {
						transtype = "Error";
					}
				/*	if(tnxreconstatus==0){
						action += "<a class=\"left\" style=\"cursor: pointer;\" onclick=\"vieweventaction('" + payId+ "');\">"
				          		+ "<i class=\"tooltipped mdi-action-done left tinysmall\" data-tooltip=\"Success\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
					} else if(tnxreconstatus==1){
						 action += "<a class=\"left\" style=\"cursor: pointer;\" onclick=\"vieweventaction('" + payId+ "');\">"
					          		+ "<i class=\"tooltipped mdi-action-done left tinysmall\" data-tooltip=\"Failed\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
					} else {
						 action += "<a class=\"left\" style=\"cursor: pointer;\" onclick=\"vieweventaction('" + payId+ "');\">"
					          		+ "<i class=\"tooltipped mdi-action-done left tinysmall\" data-tooltip=\"Failed\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
					}*/
					JSONArray ja;
		            ja = new JSONArray();
					   ja.put(sno);
			            ja.put(filename);
			            ja.put(lvrTransAmt);
			            ja.put(transtype);
			            ja.put(lvrTransdate);
			            ja.put(tnxmathstatus+" - "+ action);
			            ja.put(lvrStscmdts);
			            array.put(ja);
			            tnxmathstatus = "";
			            action="";
				}			
			 totalAfterFilter =  json_data.getInt("countAfterFilter");
	      } catch (Exception ex) {
	        ex.printStackTrace();
	        System.out.println("Exception get totalcount---->> " + ex);
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

	
	public String getTownShipId() {
		return townShipId;
	}

	public void setTownShipId(String townShipId) {
		this.townShipId = townShipId;
	}

	public String getSocietyId() {
		return societyId;
	}

	public void setSocietyId(String societyId) {
		this.societyId = societyId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getSelectId() {
		return selectId;
	}

	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}

	public String getStartamount() {
		return startamount;
	}

	public void setStartamount(String startamount) {
		this.startamount = startamount;
	}

	public String getEndamount() {
		return endamount;
	}

	public void setEndamount(String endamount) {
		this.endamount = endamount;
	}

	public String getTranstype() {
		return transtype;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	
}
