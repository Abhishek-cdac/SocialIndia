package com.socialindia.flashnews;

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
import com.socialindia.login.EncDecrypt;

public class FlashNewsMgmt  extends ActionSupport implements
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
	  private String slectfield;
	  private String groupCode;
	  //UamServices uamDeatils = new UamDaoServices();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  /**
	   * For merchant user records showing on data table.
	   */
	  public String execute() {
	    try {
	    	
	    	System.out.println("FlashNewsMgmt  loading.......");
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
	      JSONObject dataJson=null;
	      Map sessionMap = null;
	      try {	       
	    	dataJson=new JSONObject();
	  		dataJson.put("eventstatus", "1");
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			sessionMap = ActionContext.getContext().getSession();
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
			}if(slectfield!=null){
				dataJson.put("selectfield", slectfield);
			}else{
				dataJson.put("selectfield", "");
			}
			dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
	  		JSONObject finaljj=new JSONObject();
	  		finaljj.put("servicecode", "SI0050");		
	  		finaljj.put("data", dataJson);	     
	        String jsonTextFinal = finaljj.toString();
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.flashnewsmgmt.action");
			String response=commonObj.jsonRequest(finalUrl, temp);
			System.out.println("======response======="+response);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			 total =  json_data.getInt("InitCount");
			 ar = json_data.getJSONArray("flashnewscontents");
			 JSONObject jsonList = new JSONObject();
			  int sno = 0;
			  String action="";
			  String imagepath = "";		
			 for(int i=0;i<ar.length();i++){	
				 sno++;
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					String flashnewsId=jsonList.getString("flashnewsId");
					String flashnewcontent=jsonList.getString("flashnewcontent");
					String flashnewsImage=jsonList.getString("flashnewsImage");
					String flashnewstitle=jsonList.getString("flashnewstitle");
					String entrydate=jsonList.getString("entrydate");
					if (flashnewsImage.length() > 0) {    	   
						imagepath  ="<img id=\"myImg_"+flashnewsId+"\" name=\"LaborImageFileName\" class=\"webimagedatable hoverable tooltipped\" data-tooltip=\"Click to view\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" src=\"/templogo/flashnews/mobile/"+flashnewsId+"/"+flashnewsImage+"\" onclick=\"toViewlargesizeimgwithsrc(this.id,'/templogo/flashnews/web/"+flashnewsId+"/"+flashnewsImage+"');\">"; 
					} else {    	 
						imagepath  ="<img id=\"lbrimg_"+flashnewsId+"\" name=\"laborimag\" class=\"webimagedatable hoverable tooltipped\" data-tooltip=\"Click to view\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" src=\""+getText("Resource.path")+"/images/social/profile-default-male.png\" onclick=\"toViewlargesizeimgwithsrc(this.id,'"+getText("Resource.path")+"/images/social/profile-default-male.png')\">";
					}
					  action += "<a class=\"townshipmasterstyle\" onclick=\"viewflashnews('" + flashnewsId + "');\">"
					 		+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>";
					 action += "<a class=\"townshipmasterstyle\" onclick=\"editflashnews('"
							   + flashnewsId + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Re-Book\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
					 action +="<i class=\"townshipmasterstyle tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" onclick=\"deleteflashnews('" + flashnewsId+ "');\"></i>";
					 	
				 JSONArray ja;
		            ja = new JSONArray();
					   ja.put(sno);
					   	ja.put(imagepath);
			            ja.put(flashnewstitle);
			            ja.put(entrydate);
			            ja.put(action);
			            array.put(ja);
			            action = "";
			       
				}			
			 totalAfterFilter =  json_data.getInt("countAfterFilter");
	      } catch (Exception ex) {
	        ex.printStackTrace();
	        System.out.println("Exception get totalcount---FlashNewsMgmt->> " + ex);
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

	public String getSlectfield() {
		return slectfield;
	}

	public void setSlectfield(String slectfield) {
		this.slectfield = slectfield;
	}
}
