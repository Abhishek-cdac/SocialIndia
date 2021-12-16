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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.login.persistense.UserMasterTblVo;

public class ResidentMgmtReportDataTbl extends ActionSupport implements
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
	  List<UserMasterTblVo> userList = new ArrayList<UserMasterTblVo>();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  public String execute() {
	    try {
	      System.out.println("residentmgmtReportTbldata  loading.......");
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
	      int total = 0,startcnt=0;
	      int totalAfterFilter = 0;
	      JSONArray ar =null;
	      try {	      
	    	JSONObject dataJson=new JSONObject();
	  		dataJson.put("eventstatus", "1");
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
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
	  		finaljj.put("servicecode", "SI6429");		
	  		finaljj.put("data", dataJson);	     
	        String jsonTextFinal = finaljj.toString();
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.residentreportMng.loadDataTable");
			System.out.println("finalUrl=----------------"+finalUrl);
			String response=commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response----------------"+response);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			total =  json_data.getInt("InitCount");
			String sttcont =  json_data.getString("rowstart");
			ar = json_data.getJSONArray("eventdetails");
			if(sttcont!=null){
			startcnt=Integer.parseInt(sttcont);
			}
			
				JSONObject jsonList = new JSONObject();
				UserMasterTblVo residentobj = new UserMasterTblVo(null, null, null,null, null, null,null,null, 0, null);
				String userName="",lvrTownshipname=null, lvrTownshipuniquid=null, lvrSocietynamme=null, lvrSocietUniid=null,dob=null,emailId =null,mobileNo=null,countryName=null,stateName=null,cityName="",entryDatetime="",modifyDatetime="",lastName=null,firstName=null;
				int userId = 0, lvrSno = 0;
				for (int i = 0; i < ar.length(); i++) {
					residentobj=new UserMasterTblVo(null, null, null,null, null, null,null,null, 0, null);
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					userId=jsonList.getInt("userId");
					userName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "userName");
					firstName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "firstName");
					lastName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lastName");
					dob = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "dob");
					emailId = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "emailId");
					mobileNo = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "mobileNo");
					countryName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "countryName");
					stateName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "stateName");
					cityName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "cityName");
					entryDatetime = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "entryDatetime");
					modifyDatetime = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "modifyDatetime");
					lvrSocietynamme = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "societyname");
					lvrTownshipname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "townshipname");
				
					JSONArray ja = null;
					ja = new JSONArray();
					lvrSno++;
					ja.put(lvrSno);	          
		            ja.put(firstName+" "+lastName);
		            ja.put(lvrTownshipname);
		            ja.put(lvrSocietynamme);
		            ja.put(emailId);
		            ja.put(mobileNo);
		          
		            ja.put(countryName);
		            ja.put(stateName);
		            ja.put(cityName);
		            ja.put(entryDatetime);
		            array.put(ja);
				
												
				}			
			 totalAfterFilter =  json_data.getInt("countAfterFilter");
	      } catch (Exception ex) {
	        
	        System.out.println("Exception found in ResidentMgmtReport.calss :  " + ex);
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