package com.socialindia.reportmgmt;

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
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.townShipMgmt.townShipMgmtTable;
import com.socialindia.vo.AuditlogTblVO;

public class ReportissuesDataTbl extends ActionSupport implements
SessionAware, ServletRequestAware, ServletResponseAware,
ServletContextAware{
	private static final long serialVersionUID = 1L;
	  private String idtDisplayStart;
	  private String idtDisplayLength;
	  private String sdtEcho;
	  private String idtSortCol0;
	  private String sdtSortDir0;
	  private String sdtSearch;
	  
	  private String searchfield;
	  private String usersearchname;
	  private String sdtSearchff;
	  private String fromdate;
	  private String todate;
	  private Integer sSoctyId;
	  private Integer sTowshipId;
	  
	  private JSONObject result = new JSONObject();
	  JSONArray aaData = new JSONArray();
	  private String idtTotalDisplayRecords = "";
	  private String idtTotalRecords = "";
	  private HttpServletResponse response;
	  private HttpServletRequest request = ServletActionContext.getRequest();
	  private String sqlQuery;
	  private String CmpyProfileImage="";
	  List<AuditlogTblVO> auditList = new ArrayList<AuditlogTblVO>();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  public String execute() {
		  Map sessionMap = null;
		  Log logwrite = null;
	    try {
	    	logwrite = new Log();
	      System.out.println("AuditTrialReportDataTbl  loading.......");
	      sdtEcho = request.getParameter("sEcho");
	      sdtSearch = request.getParameter("sSearch");
	      idtDisplayStart = request.getParameter("iDisplayStart");
	      String sAmount = request.getParameter("iDisplayLength");
	      sessionMap = ActionContext.getContext().getSession();
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
	      int total = 0,startcnt=0;
	      int totalAfterFilter = 0;
	      JSONArray ar =null;
	      JSONObject json_data=null;
	      JSONObject json =null;
	      try {	       
	    	JSONObject dataJson=new JSONObject();
	  		dataJson.put("eventstatus", "1");
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
			dataJson.put("fromdate", fromdate);
			dataJson.put("todate", todate);
			dataJson.put("sSoctyId", sSoctyId);
			dataJson.put("sTowshipId", sTowshipId);
			if(searchfield!=null){
				dataJson.put("srchField", searchfield);				
			}else{
				dataJson.put("srchField", "");				
			}if(usersearchname!=null){
				dataJson.put("srchFieldval", usersearchname);	
			}else{
				dataJson.put("srchFieldval", "");
			}
			if(sdtSearchff!=null){
				dataJson.put("srchflg", sdtSearchff);
			}else{
				dataJson.put("srchflg", "");
			}
	  		JSONObject finaljj=new JSONObject();
	  		finaljj.put("servicecode", "SI00070");		
	  		finaljj.put("data", dataJson);	     
	        String jsonTextFinal = finaljj.toString();
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.reportissuesreportMng.loadDataTable");
			System.out.println("finalUrl=----------------"+finalUrl);
			String response=commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response----------------"+response);
			 json = new JSONObject(response);
			 json_data=json.getJSONObject("data");
			total =  json_data.getInt("InitCount");
			ar = json_data.getJSONArray("RepIssueDataMgmt");
			
			JSONObject jsonList = new JSONObject();
			String action = "";
			int lvrSno = 1;
			JSONArray ja = null;  	
			for (int i = 0; i < ar.length(); i++) {			
				jsonList=null;
				jsonList=ar.getJSONObject(i);				
				int uplerrorid=jsonList.getInt("uniqueid");
				String usrname=jsonList.getString("usrname");
				String mobno=jsonList.getString("mobno");
				String emailid=jsonList.getString("emailid");
				
				String entrydate=jsonList.getString("entrydate");
				String statusflg=jsonList.getString("status");
				
				 action += "<div class=\"booking\"><a class=\"left societystyle\" style=\"margin-right:4%;cursor: pointer;\" onclick=\"viewreportissueaction('" + uplerrorid + "');\">"
			          		+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>";
		        	if(statusflg.equalsIgnoreCase("0"))
		        	{
		        		 action += "<div id=\"statusflgid_" + uplerrorid + "\" class=\"left\" style=\"margin: 5px;\">Solved</div>";
		        	}
		        	else
		        	{
		        		
		        		 
		        		action += "<a class=\"left societystyle\" id=\"bookingappr_" + uplerrorid + "\" style=\"margin-right: 4%;cursor: pointer;\" onclick=\"solveactioncontent('"+ uplerrorid + "','"+mobno+"');\">"
			            		+ "<i class=\"tooltipped mdi-action-verified-user tinysmall blue-text text-lighten-2\" data-tooltip=\"Solve\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
			            action += "<div id=\"statusid_" + uplerrorid + "\" class=\"left\" style=\"margin: 5px;display:none;\"></div>";
		        	}
				
		         
					
		//townShipMgmtList.add(new TownshipMstTbl(uplerrorid,filename, uploadusrid,errorrowno,entrydate,cityname));		
				ja = null;
				ja = new JSONArray();
	        	        
	            ja.put(lvrSno);
	            ja.put(usrname);
	            ja.put(emailid);
	            ja.put(mobno);
	            ja.put(entrydate);
	            ja.put(action);
	            array.put(ja);
	            lvrSno ++;
	            action = "";
			}

			 totalAfterFilter =  json_data.getInt("countAfterFilter");
	      } catch (Exception ex) {
	        ex.printStackTrace();
	        System.out.println("Exception get totalcount---->> " + ex);
	      }
	      finally {
	    	  json_data=null;json=null;
			}  
	     
	      result.put("sEcho", echo);
		  result.put("iTotalRecords", total);
		  result.put("iTotalDisplayRecords", totalAfterFilter);
		  result.put("aaData", array);
		  Commonutility.toWriteConsole("Step 3 : Township Manage Table [End] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
		  logwrite.logMessage("Step 3 : Township Manage Table [End] ", "info", townShipMgmtTable.class);
		  getResponse();
	    }catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 4: Exception Found : "+ex);
		  	logwrite.logMessage("Step -1 4: Exception Found : "+ex, "error", townShipMgmtTable.class);
		} finally{
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

	public String getCmpyProfileImage() {
		return CmpyProfileImage;
	}

	public void setCmpyProfileImage(String CmpyProfileImage) {
		this.CmpyProfileImage = CmpyProfileImage;
	}

	public String getSearchfield() {
		return searchfield;
	}

	public void setSearchfield(String searchfield) {
		this.searchfield = searchfield;
	}

	public String getUsersearchname() {
		return usersearchname;
	}

	public void setUsersearchname(String usersearchname) {
		this.usersearchname = usersearchname;
	}

	public String getSdtSearchff() {
		return sdtSearchff;
	}

	public void setSdtSearchff(String sdtSearchff) {
		this.sdtSearchff = sdtSearchff;
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

	public Integer getsSoctyId() {
		return sSoctyId;
	}

	public void setsSoctyId(Integer sSoctyId) {
		this.sSoctyId = sSoctyId;
	}

	public Integer getsTowshipId() {
		return sTowshipId;
	}

	public void setsTowshipId(Integer sTowshipId) {
		this.sTowshipId = sTowshipId;
	}

	
	
}