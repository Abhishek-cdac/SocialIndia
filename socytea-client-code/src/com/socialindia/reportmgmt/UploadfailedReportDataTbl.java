package com.socialindia.reportmgmt;

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
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.townShipMgmt.townShipMgmtTable;

public class UploadfailedReportDataTbl extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware,
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
private String fromdate;
private String todate;

//List<TownshipMstTbl> townShipMgmtList = new ArrayList<TownshipMstTbl>();
//UamServices uamDeatils = new UamDaoServices();
JSONObject obj = new JSONObject();
private Common commonObj = new CommonDao();
/**
* For merchant user records showing on data table.
*/
public String execute() {
  Log logwrite = null;
try {
		logwrite = new Log();
		Commonutility.toWriteConsole("Step 1 : Township Manage Table [Start] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
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
				Commonutility.toWriteConsole(dir);
			}
		}
			int total = 0;
			int totalAfterFilter = 0;
			JSONArray ar = null;
			JSONObject lvrRqstdatajsnobj = null;
			JSONObject lvrRcvrspjson = null;
			JSONObject lvrRspdatajsnobj = null;
			JSONObject jsonList = null;
			try {
				Map sessionMap = ActionContext.getContext().getSession();
				obj.put("servicecode", "SI0030");       
				lvrRqstdatajsnobj = new JSONObject();       
				lvrRqstdatajsnobj.put("count1", "yes");
				lvrRqstdatajsnobj.put("countF1", "yes");
	  
				lvrRqstdatajsnobj.put("startrow", String.valueOf(start));// starting row
				lvrRqstdatajsnobj.put("totalrow", String.valueOf(amount));// overalltotal row
				lvrRqstdatajsnobj.put("srchdtsrch", sdtSearch);// overalltotal row
				lvrRqstdatajsnobj.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
		
				lvrRqstdatajsnobj.put("fromdate", fromdate);
				lvrRqstdatajsnobj.put("todate", todate);
				obj.put("data", lvrRqstdatajsnobj);       
				String jsonTextFinal = obj.toString();
				String temp = "ivrparams="+ URLEncoder.encode(jsonTextFinal);		
				String finalUrl = getText("socialindia.uploaderrreportMng.loadDataTable");
				Commonutility.toWriteConsole("Step 2 : Service Url : "+finalUrl);
				logwrite.logMessage("Step 2 : Service Url : "+finalUrl, "info", townShipMgmtTable.class);
				String response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrspjson = new JSONObject(response);
				lvrRspdatajsnobj = lvrRcvrspjson.getJSONObject("data");
				total =  lvrRspdatajsnobj.getInt("InitCount");
				ar = lvrRspdatajsnobj.getJSONArray("UplErrorDateMgmt");	
	 
				String activekey = "";
				int lvrSno = 1;
				JSONArray ja = null;  		
				for (int i = 0; i < ar.length(); i++) {			
					jsonList=null;
					jsonList=ar.getJSONObject(i);				
					int uplerrorid=jsonList.getInt("uniqueid");
					String filename=jsonList.getString("filename");
					String uploadusrid=jsonList.getString("uploadusrid");
					String errorrowno=jsonList.getString("errorrowno");
					errorrowno = errorrowno.substring(0, errorrowno.length()-1);
					String entrydate=jsonList.getString("entrydate");
					
			//townShipMgmtList.add(new TownshipMstTbl(uplerrorid,filename, uploadusrid,errorrowno,entrydate,cityname));		
					ja = null;
					ja = new JSONArray();
		        	        
		            ja.put(lvrSno);
		            ja.put(uploadusrid);
		            ja.put(filename);
		            ja.put("<div class='breakword'>Error Row No. : "+errorrowno+"</div>");
		            ja.put(entrydate);
		           
		            array.put(ja);
		            lvrSno ++;
					
				}

				totalAfterFilter = lvrRspdatajsnobj.getInt("countAfterFilter");	  	       
			} catch (Exception ex) {
					Commonutility.toWriteConsole("Step -1 : Exception Found : "+ex);
	  			logwrite.logMessage("Step -1 : Exception Found : "+ex, "error", townShipMgmtTable.class);
			} finally {
				lvrRqstdatajsnobj= null; lvrRcvrspjson = null; lvrRspdatajsnobj= null; jsonList = null;
			}   
 
			  result.put("sEcho", echo);
			  result.put("iTotalRecords", total);
			  result.put("iTotalDisplayRecords", totalAfterFilter);
			  result.put("aaData", array);
			  Commonutility.toWriteConsole("Step 3 : Township Manage Table [End] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			  logwrite.logMessage("Step 3 : Township Manage Table [End] ", "info", townShipMgmtTable.class);
			  getResponse();
	} catch (Exception ex) {
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


}
