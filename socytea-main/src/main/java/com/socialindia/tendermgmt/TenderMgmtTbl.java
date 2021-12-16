package com.socialindia.tendermgmt;

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

public class TenderMgmtTbl extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware{
	private static final long serialVersionUID = 1L;
	private String idtDisplayStart;
	private String idtDisplayLength;
	private String sdtEcho;
	private String idtSortCol0;
	private String sdtSortDir0;
	private String sdtSearch;
	private String usersearchname;
	private String society;
	private String searchval;
	private String slectfield;
	private JSONObject result = new JSONObject();
	JSONArray aaData = new JSONArray();
	private String idtTotalDisplayRecords = "";
	private String idtTotalRecords = "";
	private HttpServletResponse response;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private String sqlQuery;
	private String CmpyProfileImage="";
	List<TenderTblVO> tenderList = new ArrayList<TenderTblVO>();
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao(); 
	public String execute() {
		JSONArray lvrArray = null;
	    try {
	    	System.out.println("slectfield ----"+slectfield);
	      System.out.println("TenderMgmtTbl.class Called for Datatable.");
	      String icol = "tenderId";
	      String table = "TenderTblVO";
	      String manualsearch = "";
	      sdtEcho = request.getParameter("sEcho");
	      sdtSearch = request.getParameter("sSearch");
	      idtDisplayStart = request.getParameter("iDisplayStart");
	      String sAmount = request.getParameter("iDisplayLength");
	      Map sessionMap = ActionContext.getContext().getSession();
	      if (sdtSearch == null) {
	          sdtSearch = "";
	      }
	      lvrArray = new JSONArray();
	      int amount = 10, start = 0, echo = 0, col = 0;
	      String globalsearch = "";
	      String userfilter = "";
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
	        if (!sdtSortDir0.equals("asc")) { dir = "desc";}
	      }
	      int total = 0;
	      int totalAfterFilter = 0;
	      JSONArray ar =null;
	      JSONObject dataJson = null;
	      JSONObject lvrFinjsonObj = null;
	      String lcsocitid=String.valueOf(sessionMap.get("sSoctyId"));
	      String grpcode=String.valueOf(sessionMap.get("GROUPCODE"));
	      try {	       
	    	dataJson=new JSONObject();
	    	if (society==null || society.equalsIgnoreCase("") || society.equalsIgnoreCase("undefined")){// session
	  			if(lcsocitid != null && !lcsocitid.equalsIgnoreCase("-1") ){
		  			dataJson.put("society", lcsocitid);
		  		}else{
		  			dataJson.put("society", society);
		  		}
	  		}else{//
	  			dataJson.put("society", society);
	  		}	  
	  		dataJson.put("tenderstatus", "1");
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
			
							
			if(usersearchname!=null){
				dataJson.put("srchFieldval", usersearchname);	
			}else{
				dataJson.put("srchFieldval", "");
			}
			
			if(slectfield!=null){
				dataJson.put("slectfield", slectfield);
			}else{
				dataJson.put("slectfield", "");
			}			
			lvrFinjsonObj = new JSONObject();
			lvrFinjsonObj.put("servicecode", "SI12006");		
			lvrFinjsonObj.put("data", dataJson);	     
	        String jsonTextFinal = lvrFinjsonObj.toString();	       
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.tendermgmtselectall.action");
			String response=commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response : "+response);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			total =  json_data.getInt("InitCount");
			ar = json_data.getJSONArray("tenderdetails");
			JSONObject jsonList = new JSONObject();
			String tendertitle =null,tenderstartdate=null,tenderdesc=null,tenderenddate=null,tenderposedby=null,tenderid="",action="",tendersocietyname=null;
			for(int i=0;i<ar.length();i++){	
				jsonList = null;
				jsonList = ar.getJSONObject(i);
				tenderid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "tenderid");
				tendertitle = (String) Commonutility.toHasChkJsonRtnValObj(jsonList,"tendertitle");
				tenderstartdate = (String) Commonutility.toHasChkJsonRtnValObj(jsonList,"tenderstartdate");
				tenderdesc = (String) Commonutility.toHasChkJsonRtnValObj(jsonList,"tenderdesc");			
				tenderposedby = (String) Commonutility.toHasChkJsonRtnValObj(jsonList,"tenderusername");
				tendersocietyname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList,"tendersocietyname");
				tenderList.add(new TenderTblVO(tendertitle, tenderstartdate, tenderdesc, tenderposedby,Integer.parseInt(tenderid),tendersocietyname));
				}			
			    totalAfterFilter =  json_data.getInt("countAfterFilter");
	      } catch (Exception ex) {	      
	    	  System.out.println("Exception Found in TenderMgmtTbl.class : " + ex);
	      }finally{
	    	  dataJson = null; ar = null; lvrFinjsonObj = null;
	      }	     
	      result.put("sEcho", echo);
	      try {	       	       
	        sqlQuery = globalsearch;
	        ActionContext.getContext().getSession().put("globquery", userfilter);	        	      	       
	        String tendertitle = "";
	        String tenderstartdate = "";
	        String tenderdetails = "";
	        String tendersoc_name = "";
	        String tenderposedby = "";
	        String tendercurntusrid="";
	        String action = "";
	        TenderTblVO userObj;
	        JSONArray lvrJaArray = null;
	        try {
	       
	          int sno = 0;
	          System.out.println("Tender List Size : "+tenderList.size());
	          for (Iterator<TenderTblVO> it = tenderList.iterator(); it.hasNext();) {
	            userObj = it.next();
	            sno++;	            
	            lvrJaArray = new JSONArray();
	            tendercurntusrid=String.valueOf(userObj.getTenderId());
	            tendertitle=userObj.getTenderName();
	            tenderstartdate=userObj.getTenderDate();
	            tenderdetails=userObj.getTenderDetails();
	            tendersoc_name=userObj.getSocietyname();
	            tenderposedby=userObj.getEntryBy();	            	           
	        
	            
	            action += "<div class=\"vieweventaction\"><a class=\"\" title=\"\"  style=\"margin-right: 10px;cursor: pointer;\"  onclick=\"viewtenderaction('" + tendercurntusrid + "');\"><i class=\"tooltipped "+getText("button.color.view")+"\" "
	                     + "data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>";
	            
	            if(!grpcode.equalsIgnoreCase("0") && !grpcode.equalsIgnoreCase("1") && !grpcode.equalsIgnoreCase("2")){
	            action += "<a class=\"\" style=\"margin-right: 10px; cursor: pointer;\" onclick=\"edittenderaction('"
	                + tendercurntusrid + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" "
                     + "data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
	            }	          
	           
	            action += "<a class=\"\" style=\"margin-right: 10px; cursor: pointer;\" role=\"button\" "
	                    + " title=\"\" onclick=\"deletetenderaction('" + tendercurntusrid
	                    + "');\"><i class=\"tooltipped "+getText("button.color.delete")+"\" "
                     + "data-tooltip=\"Delete\" data-delay=\"10\" data-position=\"right\"></i></a>";
	            lvrJaArray.put(sno);
	            lvrJaArray.put(tendersoc_name);
	            lvrJaArray.put(tendertitle);
	            lvrJaArray.put(tenderstartdate);
	            lvrJaArray.put(tenderposedby);
	            lvrJaArray.put(action);
	            lvrArray.put(lvrJaArray);
	            lvrJaArray = null;
	            action = "";
	          }
	        } catch (Exception ex) {
	        	System.out.println("Inner 1 - Exception found in TenderMgmt.class : "+ex);
	        }finally{
	        	userObj = null;
	        }	        
	        result.put("iTotalRecords", total);
	        result.put("iTotalDisplayRecords", totalAfterFilter);
	        result.put("aaData", lvrArray);
	        getResponse();
	      } catch (Exception ex) {
	        System.out.println("Inner 2 - Exception found in TenderMgmt.class : "+ex);
	      }finally{
	    	  
	      }
	    } catch (Exception ex) {
	    	 System.out.println("outter - Exception found in TenderMgmt.class : "+ex);
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

	
	public String getUsersearchname() {
		return usersearchname;
	}

	public void setUsersearchname(String usersearchname) {
		this.usersearchname = usersearchname;
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

	public String getSlectfield() {
		return slectfield;
	}

	public void setSlectfield(String slectfield) {
		this.slectfield = slectfield;
	}

	
	
}
