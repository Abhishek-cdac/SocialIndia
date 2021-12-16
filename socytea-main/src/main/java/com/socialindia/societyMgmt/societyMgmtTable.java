package com.socialindia.societyMgmt;

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
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

/**
 * For merchant user data table.
 */
public class societyMgmtTable extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware {

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

  //List<SocietyMstTbl> societyMgmtList = new ArrayList<SocietyMstTbl>();
  JSONObject obj = new JSONObject();
  private Common commonObj = new CommonDao();
  /**
   * For merchant user records showing on data table.
   */
  public String execute() {
	  Log logwrite = null;
    try { 
    	logwrite = new Log();
    	System.out.println("Step 1 : societyMgmtTable [start] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss")); 
    	logwrite.logMessage("Step 1 : societyMgmtTable [start]", "info", societyMgmtTable.class);
    	String icol = "societyId";
    	String table = "SocietyMstTbl";
    	sdtEcho = request.getParameter("sEcho");
    	sdtSearch = request.getParameter("sSearch");
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
			JSONObject lvrRqstdatajsonobj = null;
			JSONObject lvrRcvresponsejsonobj = null;
			JSONObject lvrRcvdaatajsonobj = null;
			JSONArray lvrjsonaryobj = null;
      try {      
    	  	obj.put("servicecode", "SI0030");       
    	  	lvrRqstdatajsonobj = new JSONObject();       
    	  	lvrRqstdatajsonobj.put("count1", "yes");
    	  	lvrRqstdatajsonobj.put("countF1", "yes");
    	  	lvrRqstdatajsonobj.put("societystatus", "1");
    	  	lvrRqstdatajsonobj.put("srchdtsrch", sdtSearch);// overalltotal row
    	  	lvrRqstdatajsonobj.put("startrow", String.valueOf(start));// starting row
    	  	lvrRqstdatajsonobj.put("totalrow", String.valueOf(amount));// overalltotal row
    	  	Map sessionMap = ActionContext.getContext().getSession();
    	  	lvrRqstdatajsonobj.put("sSoctyId", sessionMap.get("sSoctyId"));
    	  	obj.put("data", lvrRqstdatajsonobj);
    	  	String jsonTextFinal = obj.toString();
        	jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal); 
        	String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);        	
        	String finalUrl = getText("societyMgmt.management.view.action");
        	System.out.println("Step 2 : Service Url : " + finalUrl); 
        	logwrite.logMessage("Step 2 : Service Url : " + finalUrl, "info", societyMgmtTable.class);
        	String response=commonObj.jsonRequest(finalUrl, temp);		
        	lvrRcvresponsejsonobj = new JSONObject(response);
        	lvrRcvdaatajsonobj = lvrRcvresponsejsonobj.getJSONObject("data");
        	total =  lvrRcvdaatajsonobj.getInt("InitCount");
        	ar = lvrRcvdaatajsonobj.getJSONArray("societyMgmt");
        	JSONObject jsonList = new JSONObject();
        	String activekey="";
        	String action = "";
        	int lvSno = 1;
        	
        	for(int i=0;i<ar.length();i++) {	
				jsonList=null;
				jsonList=ar.getJSONObject(i);
				
				int societyid=jsonList.getInt("societyid");
				String townshipname=jsonList.getString("townshipname");
				String societyname=jsonList.getString("societyname");
				String activationkey=jsonList.getString("activationkey");
				String countryname=jsonList.getString("countryname");
				String statename=jsonList.getString("statename");
				String cityname=jsonList.getString("cityname");
				int groupcode=jsonList.getInt("groupcode");	
				String emailid=jsonList.getString("emailid");
				String lvrTownshipid = jsonList.getString("townshipid");
				String lvrSocietyuniqueid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "societyuniqueid");
				//societyMgmtList.add(new SocietyMstTbl(societyid,townshipname,societyname, activationkey,countryname,statename,cityname,groupcode,emailid));
				
				lvrjsonaryobj = null;
				lvrjsonaryobj = new JSONArray(); 
	            String activekeymask=commomServices.maskActivaionKey(activationkey);
	            
	            activekey = "<span style=\"display:none;\" id=\"keyhidspn_"+societyid+"\">" + activationkey + "</span><span id=\"newkey_"+societyid+"\" onclick=\"CopyClipboard('keyhidspn_" + societyid  + "')\" class=\""+getText("activation.key.chip")+" pointer tooltipped\" data-tooltip=\"Click to copy\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"> " + activekeymask+ "</span>";
	            
	            action += "<a class=\"societystyle\" onclick=\"viewsociety('" + societyid
	                    + "');\"><i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	             action += "<a  class=\"societystyle\" onclick=\"editsociety('"
	                 + societyid + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	             action += "<a class=\"societystyle\" onclick=\"addcommittemember('" + lvrTownshipid+ "','" + townshipname+ "','" + societyid+ "','" + societyname+ "');\" >"
	             		+ "<i class=\"tooltipped "+getText("button.color.addcommittee")+"\" data-tooltip=\"Create Committee\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i>"
	             						+ "</a>";
	             action +=  "<a class=\"societystyle\" onclick=\"generatekey('" + societyid  + "','"+activationkey+"','"+societyname+"');\">"
	             		+ "<i class=\"tooltipped "+getText("button.color.key")+"\" data-tooltip=\"Activation Key\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>"; 
	             action +=  "<a class=\"societystyle\" onclick=\"printData('" + societyid  + "','"+groupcode+"');\">"
	             		+ "<i class=\"tooltipped "+getText("button.color.print")+"\" data-tooltip=\"Print\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	             if(emailid!=null && !emailid.equalsIgnoreCase("") && emailid!=""){
	                 action +=  "<a style=\"cursor: pointer;\" onclick=\"emailFunction('" + societyid  + "','"+groupcode+"');\">"
	                 		+ "<i class=\"tooltipped "+getText("button.color.email")+"\" data-tooltip=\"Email\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	              }
	             
	             //delete
	             action = action + "<a class=\"societystyle\" onclick=\"deletesocyteaction('" + societyid+ "');\" ><i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\"10\" data-position=\"bottom\"\"></i></a>";
	             
	             action +="</div>";
	             
	             lvrjsonaryobj.put(lvSno);
	             lvrjsonaryobj.put(societyname);
	             lvrjsonaryobj.put(lvrSocietyuniqueid);
	             lvrjsonaryobj.put(townshipname);
	             lvrjsonaryobj.put(activekey);
	             lvrjsonaryobj.put(countryname);
	             lvrjsonaryobj.put(statename);
	             lvrjsonaryobj.put(cityname);
	             lvrjsonaryobj.put(action);
	             array.put(lvrjsonaryobj);
	             action = "";
	             lvSno++;        	
        	}		
        	totalAfterFilter =  lvrRcvdaatajsonobj.getInt("countAfterFilter");
        	result.put("sEcho", echo);
        	result.put("iTotalRecords", total);
            result.put("iTotalDisplayRecords", totalAfterFilter);
            result.put("aaData", array);
            System.out.println("Step 3 : societyMgmtTable [End] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss")); 
        	logwrite.logMessage("Step 3 : societyMgmtTable [End]", "info", societyMgmtTable.class);
            getResponse();
      } catch (Exception ex) {
    	  System.out.println("Step -1 : Exception found in societyMgmtTable.class : " + ex); 
      	logwrite.logMessage("Step -1 : Exception found : " + ex, "error", societyMgmtTable.class);
      } finally {
    	  lvrRqstdatajsonobj = null; lvrRcvresponsejsonobj = null; lvrRcvdaatajsonobj = null; lvrjsonaryobj =null;
      }           	     
    } catch (Exception ex) {
    	 System.out.println("Step -2 : Exception found in societyMgmtTable.class : " + ex); 
       	 logwrite.logMessage("Step -2 : Exception found : " + ex, "info", societyMgmtTable.class);
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

}
