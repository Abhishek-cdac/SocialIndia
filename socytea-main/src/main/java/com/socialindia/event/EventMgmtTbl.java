package com.socialindia.event;

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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class EventMgmtTbl extends ActionSupport implements
SessionAware, ServletRequestAware, ServletResponseAware,
ServletContextAware{
	private static final long serialVersionUID = 1L;
	  private String idtDisplayStart;
	  private String idtDisplayLength;
	  private String sdtEcho;
	  private String idtSortCol0;
	  private String sdtSortDir0;
	  private String sdtSearch;
	  
	  private String societyid;
	  private String searchfield;
	  private String usersearchname;
	  private String sdtSearchff;
	  
	  private JSONObject result = new JSONObject();
	  JSONArray aaData = new JSONArray();
	  private String idtTotalDisplayRecords = "";
	  private String idtTotalRecords = "";
	  private HttpServletResponse response;
	  private HttpServletRequest request = ServletActionContext.getRequest();
	  private String sqlQuery;
	  private String CmpyProfileImage="";
	  List<EventMstrTblVO> eventList = new ArrayList<EventMstrTblVO>();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  public String execute() {
		  JSONArray lvrDtbljary = null;
		  
	    try {
	      System.out.println("eventListDatatable  loading......."+societyid);
	      String icol = "eventId";
	      String table = "EventMstrTblVO";
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
	    	String lcsocitid = String.valueOf(sessionMap.get("sSoctyId"));
	    	JSONObject dataJson=new JSONObject();
	  		dataJson.put("eventstatus", "1");
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
			if(societyid!=null && !societyid.equalsIgnoreCase("") ){
				dataJson.put("societyid", societyid);	
			}else{
				dataJson.put("societyid", lcsocitid);	
			}
			if(searchfield!=null){
				dataJson.put("srchField", searchfield);				
			}else{
				dataJson.put("srchField", searchfield);				
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
	  		finaljj.put("servicecode", "SI8006");		
	  		finaljj.put("data", dataJson);	     
	        String jsonTextFinal = finaljj.toString();
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.eventmanagement.action.url");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			total =  json_data.getInt("InitCount");
			ar = json_data.getJSONArray("eventdetails");
			JSONObject jsonList = new JSONObject();
			String evetitle =null,evestartdate=null,evestarttime=null,eveenddate=null,eveendtime=null,eventid="",action="", lvrEventfuntxt = null, lvrEventfunid = null;
			String lvrEvntcrntusrid = null;
			int lvrSno = 1;
		String curnLogid=String.valueOf(sessionMap.get("USERID"));
			for(int i=0;i<ar.length();i++){	
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					eventid = jsonList.getString("eventid");
					evetitle = jsonList.getString("evetitle");
					evestartdate = jsonList.getString("evestartdate");
					evestarttime = jsonList.getString("evestarttime");
					eveenddate = jsonList.getString("eveenddate");
					eveendtime = jsonList.getString("eveendtime");
					lvrEventfuntxt = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "eventfuntxt");
					lvrEventfunid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "eventfunid");	
					lvrEvntcrntusrid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "evecurntusrid");
					//Commonutility.toWriteConsole("lvrEvntcrntusrid : " +lvrEvntcrntusrid);
					//Commonutility.toWriteConsole("curnLogid : " +curnLogid);
					lvrDtbljary = new JSONArray();					
					 action += "<div class=\"merchant_town\"><a class=\"left\" style=\"margin-right:4%;cursor: pointer;\" onclick=\"vieweventaction('" + eventid + "');\">"
				          		+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>";
			            
			        if((curnLogid!=null && lvrEvntcrntusrid!=null ) && lvrEvntcrntusrid.equalsIgnoreCase(curnLogid)){
			        	action += "<a class=\"left\" style=\"margin-right: 4%;cursor: pointer;\" onclick=\"editeventaction('"+ eventid + "');\">"
			            		+ "<i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
			        }
					 

			         action += "<a class=\"left\" style=\" margin-right: 4%;cursor: pointer;\" onclick=\"deleteeventaction('" + eventid+ "');\">"
			            		+ "<i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
			            
			          
			        action += "<a class=\"left\" style=\"cursor: pointer;\" onclick=\"vieweventaction('" + eventid+ "');\">"
			          		+ "<i class=\"tooltipped mdi-social-person-add left tinysmall\" data-tooltip=\"Invite Member\" data-delay=\"10\" data-position=\"bottom\"></i></a>";			     
					lvrDtbljary.put(lvrSno);
					lvrDtbljary.put(lvrEventfuntxt);
					lvrDtbljary.put(evetitle);
			            if(evestartdate!=null && !evestartdate.equalsIgnoreCase("") && evestarttime!=null && !evestarttime.equalsIgnoreCase("") ){
			            	lvrDtbljary.put(evestartdate+" ["+evestarttime+"]");	
			            }else{
			            	lvrDtbljary.put(evestartdate);	
			            }
			            if(eveenddate!=null && !eveenddate.equalsIgnoreCase("") && eveendtime!=null && !eveendtime.equalsIgnoreCase("")){
			            	lvrDtbljary.put(eveenddate+" ["+eveendtime+"]");
			            }else{
			            	lvrDtbljary.put(eveenddate);
			            }
			        lvrDtbljary.put(action);
			        array.put(lvrDtbljary);
			        action = "";
			        lvrDtbljary = null;
					lvrSno++;
			
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
	      
	      /* try {	      
	        ActionContext.getContext().getSession().put("globquery", userfilter);	        	               
	        String evetitle = "";
	        String evestartdate = "";
	        String evestarttime = "";
	        String eveenddate = "";
	        String eveendtime = "";
	        String evecurntusrid="";
	        String action = "";
	       
	        try {
	        	EventMstrTblVO userObj;
	          int sno = 0;
	          for (Iterator<EventMstrTblVO> it = eventList.iterator(); it.hasNext();) {
	            userObj = it.next();
	            sno++;
	            JSONArray ja;
	            ja = new JSONArray();
	            evecurntusrid=userObj.getEventid();
	            evetitle=userObj.getEvetitle();
	            evestartdate=userObj.getEvestartdate();
	            evestarttime=userObj.getEvestarttime();
	            eveenddate=userObj.getEveenddate();
	            eveendtime=userObj.getEveendtime();
	            
	            action += "<div class=\"merchant_town\"><a class=\"left\" style=\"margin-right:4%;cursor: pointer;\" onclick=\"vieweventaction('" + evecurntusrid + "');\">"
		          		+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>";
	            
	            action += "<a class=\"left\" style=\"margin-right: 4%;cursor: pointer;\" onclick=\"editeventaction('"+ evecurntusrid + "');\">"
	            		+ "<i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"bottom\"></i></a>";

	            action += "<a class=\"left\" style=\" margin-right: 4%;cursor: pointer;\" onclick=\"deleteeventaction('" + evecurntusrid+ "');\">"
	            		+ "<i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
	            
	          
	            action += "<a class=\"left\" style=\"cursor: pointer;\" onclick=\"vieweventaction('" + evecurntusrid+ "');\">"
	          		+ "<i class=\"tooltipped mdi-social-person-add left tinysmall\" data-tooltip=\"Invite Member\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
	            
	            ja.put(sno);
	            ja.put(evetitle);
	            if(evestartdate!=null && !evestartdate.equalsIgnoreCase("") && evestarttime!=null && !evestarttime.equalsIgnoreCase("") ){
	            	ja.put(evestartdate+" ["+evestarttime+"]");	
	            }else{
	            	ja.put(evestartdate);	
	            }
	            if(eveenddate!=null && !eveenddate.equalsIgnoreCase("") && eveendtime!=null && !eveendtime.equalsIgnoreCase("")){
	            	ja.put(eveenddate+" ["+eveendtime+"]");
	            }else{
	            	ja.put(eveenddate);
	            }
	            ja.put(action);
	            array.put(ja);
	            action = "";
	          }
	        } catch (Exception ex) {
	          ex.printStackTrace();
	        }
	        String sql2 = "SELECT count(" + icol + ") FROM " + table + " as pr ";
	        sql2 += where;
	        result.put("iTotalRecords", total);
	        result.put("iTotalDisplayRecords", totalAfterFilter);
	        result.put("aaData", array);
	        getResponse();
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }*/
	    } catch (Exception ex) {
	      Commonutility.toWriteConsole("Step -1 : Exception Found in "+getClass().getSimpleName()+".class : "+ex);
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

	public String getSocietyid() {
		return societyid;
	}

	public void setSocietyid(String societyid) {
		this.societyid = societyid;
	}

	
	
}
