package com.socialindia.merchantMgmt;

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
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.event.EventMstrTblVO;
import com.socialindia.login.EncDecrypt;
import com.socialindia.vo.MerchantTblVO;

public class MerchantmgmtDataTbl extends ActionSupport implements
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
	  
	  private JSONObject result = new JSONObject();
	  JSONArray aaData = new JSONArray();
	  private String idtTotalDisplayRecords = "";
	  private String idtTotalRecords = "";
	  private HttpServletResponse response;
	  private HttpServletRequest request = ServletActionContext.getRequest();
	  private String sqlQuery;
	  private String CmpyProfileImage="";
	  List<MerchantTblVO> merchantList = new ArrayList<MerchantTblVO>();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  public String execute() {
	    try {
	      System.out.println("merchantListDatatable  loading.......");
	      String icol = "mrchntId";
	      String table = "MerchantTblVO";
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
	    	JSONObject dataJson=new JSONObject();
	  		dataJson.put("eventstatus", "1");
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
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
	  		finaljj.put("servicecode", "SI6418");		
	  		finaljj.put("data", dataJson);	     
	        String jsonTextFinal = finaljj.toString();
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.mrchantMng.loadDataTable");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			total =  json_data.getInt("InitCount");
			ar = json_data.getJSONArray("eventdetails");
			JSONObject jsonList = new JSONObject();
			MerchantTblVO merchantobj=new MerchantTblVO();
			String mrchntEmail =null,shopName=null,mrchntPhNo=null,countryName=null,stateName=null,mrchntName="",cityName="",mrchCategoryName="";
			int mrchntId=0;
			for(int i=0;i<ar.length();i++){	
				merchantobj=new MerchantTblVO();
				jsonList=null;
				jsonList=ar.getJSONObject(i);
				mrchntId=jsonList.getInt("mrchntId");
				mrchntName=jsonList.getString("mrchntName");
				mrchntEmail=jsonList.getString("mrchntEmail");
				shopName=jsonList.getString("shopName");
				mrchntPhNo=jsonList.getString("mrchntPhNo");
				countryName=jsonList.getString("countryName");
				stateName=jsonList.getString("stateName");
				cityName=jsonList.getString("cityName");
				mrchCategoryName=jsonList.getString("mrchCategoryName");
				
				merchantobj.setMrchntId(mrchntId);
				merchantobj.setMrchntName(mrchntName);
				merchantobj.setMrchntEmail(mrchntEmail);
				merchantobj.setShopName(shopName);
				merchantobj.setMrchntPhNo(mrchntPhNo);
				merchantobj.setCountryName(countryName);
				merchantobj.setStateName(stateName);
				merchantobj.setCityName(cityName);
				merchantobj.setMrchCategoryName(mrchCategoryName);
				
				merchantList.add(merchantobj);
				merchantobj=null;
				}			
			 totalAfterFilter =  json_data.getInt("countAfterFilter");
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
	        Integer mrchntId = 0;
	        String evestartdate = "";
	        String evestarttime = "";
	        String eveenddate = "";
	        String eveendtime = "";
	        String evecurntusrid="";
	        String action = "";
	       
	        try {
	        	MerchantTblVO userObj;
	          int sno = 0;
	          for (Iterator<MerchantTblVO> it = merchantList.iterator(); it.hasNext();) {
	            userObj = it.next();
	            sno++;
	            JSONArray ja;
	            ja = new JSONArray();
	            mrchntId=userObj.getMrchntId();
	            
	            
	          action += "<div class=\"merchant_town\"><a class=\"left\" style=\"margin-right:4%;cursor: pointer;\" onclick=\"viewMerchantDetail('" + mrchntId + "');\">"
	          		+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>";
	           
	          action += "<a class=\"left\" style=\"margin-right: 4%;cursor: pointer;\" onclick=\"editMerchantDetail('"
	                + mrchntId + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
	           
	          action += "<a class=\"left\" style=\"margin-right: 4%;cursor: pointer;\" "
	                    + " onclick=\"addOfferToMerchant('" + mrchntId + "');\"><i class=\"tooltipped "+getText("button.color.offer")+"\" data-tooltip=\"Offers\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
	          action += "<a class=\"left\" style=\"cursor: pointer;\" onclick=\"deleteMerchantDetail('"
		                + mrchntId + "');\"><i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\"10\" data-position=\"right\"></i></a>";
	          action +="</div>";
	            ja.put(sno);
	            ja.put(userObj.getMrchntName());
	            ja.put(userObj.getMrchntEmail());
	            ja.put(userObj.getMrchntPhNo());
	            ja.put(userObj.getShopName());
	            /*ja.put(userObj.getCountryName());
	            ja.put(userObj.getStateName());
	            ja.put(userObj.getCityName());*/
	            ja.put(userObj.getMrchCategoryName());
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

	
	
}