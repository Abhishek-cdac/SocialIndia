package com.socialindia.townShipMgmt;

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
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;

/**
 * For merchant user data table.
 */
public class townShipMgmtTable extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware,
    ServletContextAware {

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
  JSONObject obj = new JSONObject();
  private Common commonObj = new CommonDao();
  /**
   * For merchant user records showing on data table.
   */
  public String execute() {
	  Log logwrite = null;
    try {
    		logwrite = new Log();
			System.out.println("Step 1 : Township Manage Table [Start] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Township Manage Table [Start]", "info", townShipMgmtTable.class);			
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
		JSONObject lvrRqstdatajsnobj= null;
		JSONObject lvrRcvrspjson = null;
		JSONObject lvrRspdatajsnobj= null;
		JSONObject jsonList = null;
		try {       
    	  obj.put("servicecode", "SI0025");       
    	  lvrRqstdatajsnobj = new JSONObject();       
    	  lvrRqstdatajsnobj.put("count1", "yes");
    	  lvrRqstdatajsnobj.put("countF1", "yes");  
    	  lvrRqstdatajsnobj.put("srchdtsrch", sdtSearch);// overalltotal row
    	  lvrRqstdatajsnobj.put("startrow", String.valueOf(start));// starting row
    	  lvrRqstdatajsnobj.put("totalrow", String.valueOf(amount));// overalltotal row
    	  obj.put("data", lvrRqstdatajsnobj);       
    	  String jsonTextFinal = obj.toString();
    	  String temp = "ivrparams="+ URLEncoder.encode(jsonTextFinal);		
    	  String finalUrl = getText("townShipMgmt.management.view.action");
    	  System.out.println("Step 2 : Service Url : "+finalUrl);
    	  logwrite.logMessage("Step 2 : Service Url : "+finalUrl, "info", townShipMgmtTable.class);
    	  String response = commonObj.jsonRequest(finalUrl, temp);
    	  lvrRcvrspjson = new JSONObject(response);
    	  lvrRspdatajsnobj = lvrRcvrspjson.getJSONObject("data");
		  total =  lvrRspdatajsnobj.getInt("InitCount");
		  ar = lvrRspdatajsnobj.getJSONArray("townShipMgmt");	
		 
		  String activekey = "";	
		  String action = "";
		  int lvrSno = 1;
		  JSONArray ja = null;      		
			for (int i = 0; i < ar.length(); i++) {				
				jsonList=null;
				jsonList=ar.getJSONObject(i);				
				int townshipid=jsonList.getInt("townshipid");
				String townshipname=jsonList.getString("townshipname");
				String activationkey=jsonList.getString("activationkey");
				String countryname=jsonList.getString("countryname");
				String statename=jsonList.getString("statename");
				String cityname=jsonList.getString("cityname");	
				String landmark = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "landmark");
				String lvrTwnshipuniqueid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "townshipuniqueid");
				//townShipMgmtList.add(new TownshipMstTbl(townshipid,townshipname, activationkey,countryname,statename,cityname));
					String mapkey = getText("google.api.key");
					String mapPram = "";
				
					if(Commonutility.checkempty(landmark)){
						mapPram = landmark.replaceAll(" ", "+");
					}else{
					
						if(Commonutility.checkempty(cityname)){
						mapPram = cityname;
					}
					if(Commonutility.checkempty(statename)){
						mapPram += ","+statename;
					}
					
					
					if(Commonutility.checkempty(countryname)){
						mapPram += ","+countryname;
					}
					mapPram = mapPram.replaceAll(" ", "+");
				}							
				String mapUrl ="https://www.google.com/maps/embed/v1/search?key="+mapkey+"&q="+mapPram+"/";
				System.out.println("mapUrl :::::::::: >"+mapUrl);
				ja = null;
				ja = new JSONArray();
	        	String activekeymask=commomServices.maskActivaionKey(activationkey);
	        	activekey = "<span style=\"display:none;\" id=\"keyhidspn_"+townshipid+"\">" + activationkey + "</span><span id=\"newkey_"+townshipid+"\"  onclick=\"CopyClipboard('keyhidspn_" + townshipid  + "')\" class=\""+getText("activation.key.chip")+" pointer tooltipped\" data-tooltip=\"Click to copy\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"> " + activekeymask+ "</span>";           
	        	action += "<a class=\"townshipmasterstyle\" onclick=\"edittownship('"
	                   + townshipid + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";                                  	        
	            				
	            action += "<a class=\"townshipmasterstyle \" onclick=\"addsociety('" + townshipid+ "','"+townshipname+"');\">"
	            	   + "<i class=\"tooltipped "+getText("button.color.addsociety")+"\" data-tooltip=\"Create Society\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	           
	          /*  action += "<a class=\"townshipmasterstyle\" target=\"_blank\" href=\"https://www.google.co.in/maps/place/"+cityname+" ;\">"
	            		+ "<i class=\"tooltipped "+getText("button.color.communication")+"\" data-tooltip=\"Location\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";  
	           */ 
	            action += "<a class=\"townshipmasterstyle\"  onclick=\"toviewmap('"+mapUrl+"');\">"
	            		+ "<i class=\"tooltipped "+getText("button.color.communication")+"\" data-tooltip=\"Location\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	            //onclick=\"toViewlargesizeimgwithsrc(this.id,'https://www.google.co.in/maps/place/"+cityname+"');\"
	            
	            
	            action +=  "<a class=\"townshipmasterstyle\" onclick=\"generatekey('" + townshipid  + "','"+activationkey+"','"+townshipname+"');\"> "
	            		+ " <i class=\"tooltipped "+getText("button.color.key")+"\" data-tooltip=\"Activation Key\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	            ja.put(lvrSno);
	            ja.put(townshipname);
	            ja.put(lvrTwnshipuniqueid);
	            ja.put(activekey);
	            ja.put(countryname);
	            ja.put(statename);
	            ja.put(cityname);
	            ja.put(action);
	            array.put(ja);
	            action = "";
	            lvrSno ++;
						
			}
		 
  		    totalAfterFilter =  lvrRspdatajsnobj.getInt("countAfterFilter");		  	       
      } catch (Exception ex) {
    	  System.out.println("Step -1 : Exception Found : "+ex);
    	  logwrite.logMessage("Step -1 : Exception Found : "+ex, "error", townShipMgmtTable.class);
      } finally {
    	  lvrRqstdatajsnobj= null; lvrRcvrspjson = null; lvrRspdatajsnobj= null; jsonList = null;
      }     
     
	  result.put("sEcho", echo);
      result.put("iTotalRecords", total);
      result.put("iTotalDisplayRecords", totalAfterFilter);
      result.put("aaData", array);
      System.out.println("Step 3 : Township Manage Table [End] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
 	  logwrite.logMessage("Step 3 : Township Manage Table [End] ", "info", townShipMgmtTable.class);
      getResponse();
    } catch (Exception ex) {
    	System.out.println("Step -1 4: Exception Found : "+ex);
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

}
