package com.socialindia.material;

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
import com.letspay.login.persistense.UserMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.companymgmt.CompanyMstrTblVO;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class materialMgmt extends ActionSupport implements
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
	  private String searchWord;
	  private String townShipId;
	  private String societyId;
	  private String groupCode;
	  private String CmpyProfileImage="";
	  List<MvpMaterialTbl> materialMstList = new ArrayList<MvpMaterialTbl>();
	  //UamServices uamDeatils = new UamDaoServices();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  /**
	   * For merchant user records showing on data table.
	   */
	  public String execute() {
	    try {
	    	
	    	System.out.println("materialListDatatable  loading.......");
	      // HttpServletRequest request = ServletActionContext.getRequest();
	      String icol = "materialId";
	      String table = "MvpMaterialTbl";
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
	  		dataJson.put("eventstatus", "1");
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			 Map sessionMap = ActionContext.getContext().getSession();
			dataJson.put("sSoctyId", sessionMap.get("sSoctyId"));
			 String societycode=String.valueOf(sessionMap.get("sSoctyId"));
			 grpcode=String.valueOf(sessionMap.get("GROUPCODE"));
			if(searchWord!=null){
			dataJson.put("searchWord", searchWord);// search
			}else{
				dataJson.put("searchWord", "");// search
			}if(townShipId!=null){
				dataJson.put("townShipId", townShipId);
			}else{
				dataJson.put("townShipId", "");
			}if(societyId!=null && !societyId.equalsIgnoreCase("")&& !societyId.equalsIgnoreCase("null")){
				dataJson.put("societyId", societyId);
			}else{
				dataJson.put("societyId", societycode);
			}if(groupCode!=null){
				dataJson.put("groupCode", groupCode);
			}else{
				dataJson.put("groupCode", "");
			}
			dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
	  		JSONObject finaljj=new JSONObject();
	  		finaljj.put("servicecode", "SI0050");		
	  		finaljj.put("data", dataJson);	     
	        String jsonTextFinal = finaljj.toString();
	        System.out.println("============jsonTextFinal========="+jsonTextFinal);
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.material.management.action.url");
			String response=commonObj.jsonRequest(finalUrl, temp);
			System.out.println("======response======="+response);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			 total =  json_data.getInt("InitCount");
			 ar = json_data.getJSONArray("materialdetails");
			 JSONObject jsonList = new JSONObject();
			/* String evetitle =null,evestartdate=null,evestarttime=null,eveenddate=null,eveendtime=null,eventid="",action="";*/
			 for(int i=0;i<ar.length();i++)
				{	
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					int materialid=jsonList.getInt("materialid");
					String societyname=jsonList.getString("societyname");
					String materialname=jsonList.getString("materialname");
					String materialcategory=jsonList.getString("materialcategory");
					String materialdesc=jsonList.getString("materialdesc");
					int materialqnty=jsonList.getInt("materialqnty");
					int totalqnty=jsonList.getInt("totalqnty");
					int usedqnty=jsonList.getInt("usedqnty");
					String materialprice=jsonList.getString("materialprice");				
					String purchasedate=jsonList.getString("purchasedate");					
					materialMstList.add(new MvpMaterialTbl(materialid,materialcategory,materialname, materialqnty, totalqnty,usedqnty,materialprice));
					
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
	       
	        int materialid = 0;
	        String categoryname ="";
	        int materialqnty = 0;
	        
	        int totalqnty = 0;
	        int userqnty = 0;
	        String materialprice="";
	        
	        String materialname="";
	        String action = "";
	       
	        try {
	        	MvpMaterialTbl materialObj;
	          int sno = 0;
	          System.out.println("=========forumTopicsList====="+materialMstList.size());
	          for (Iterator<MvpMaterialTbl> it = materialMstList.iterator(); it.hasNext();) {
	        	  materialObj = it.next();
	            sno++;
	            JSONArray ja;
	            ja = new JSONArray();
	            materialid=materialObj.getMaterialId();
	            categoryname=materialObj.getCategoryName();
	            materialname=materialObj.getMaterialName();
	            materialqnty=materialObj.getMaterialQnty();
	            totalqnty=materialObj.getTotalQnty();
	            userqnty=materialObj.getUsedQnty();
	            materialprice=materialObj.getMaterialPrice();
	           
	        //    int curuserId=(int) sessionMap.get("USERID");
	            
	          action += "<div class=\"vieweventaction\"><a class=\"\" title=\"\"  style=\"margin-left: 10px;cursor: pointer;\" onclick=\"viewmaterial('" + materialid + "');\"><i class=\"tooltipped "+getText("button.color.view")+"\" "
                     + "data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>";
	          if(!grpcode.equalsIgnoreCase("0") && !grpcode.equalsIgnoreCase("1") && !grpcode.equalsIgnoreCase("2")){
	          action += "<a class=\"\" title=\"\"  role=\"button\" style=\"margin-left: 10px;cursor: pointer;\" onclick=\"editmaterial('"
                      + materialid + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" "
                     + "data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
	          }
	            action +=" <span class=\"\" style=\"margin-left: 10px;cursor: pointer;\" title=\"\" "
	                    + "  onclick=\"deletematerial('" + materialid
	                    + "');\"><i class=\"tooltipped "+getText("button.color.delete")+"\" "
                     + "data-tooltip=\"Delete\" data-delay=\"10\" data-position=\"bottom\"></i></span>";
				
	            	 
	            ja.put(sno);
	            ja.put(categoryname);
	            ja.put(materialname);
	            ja.put(totalqnty);
	            ja.put(userqnty);
	            ja.put(materialqnty);
	            ja.put(materialprice);
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

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
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

	
}
