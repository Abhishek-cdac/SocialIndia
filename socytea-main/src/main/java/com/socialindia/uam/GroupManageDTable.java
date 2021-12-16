package com.socialindia.uam;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.letspay.uam.persistense.GroupMasterTblVo;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;

/**
 * Group management data table.
 */
public class GroupManageDTable extends ActionSupport implements SessionAware,
    ServletRequestAware, ServletResponseAware, ServletContextAware {

  private static final long serialVersionUID = 1L;
  private String idtDisplayStart;
  private String idtDisplayLength;
  private String sdtEcho;
  private String idtSortCol0;
  private String sdtSortDir0;
  private String sdtSearch;
  private JSONObject result = new JSONObject();
 
  private String idtTotalDisplayRecords = "";
  private String idtTotalRecords = "";
  JSONArray aaData = new JSONArray();
  private HttpServletResponse response;
  private HttpServletRequest request = ServletActionContext.getRequest();
  private String sqlQuery;
  private Common commonObj = new CommonDao();
  List<GroupMasterTblVo> groupList = new ArrayList<GroupMasterTblVo>();
  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
  /**
   * For group records showing on data table.
   */
  public String execute() {	 
	  JSONObject obj = null, lvrRspjsonobj = null, lvrRspdatajsonobj =  null;
	 // JSONArray array = null;
	  Log logwrite = null;	  
	  try {
    		logwrite = new Log();
    	    Commonutility.toWriteConsole("Step 1 : Group Manage Called.[Start]");
    	    logwrite.logMessage("Step 1 : Group Manage Called.[Start]", "info", GroupManageDTable.class);
			obj = new JSONObject();		
			String icol = "groupCode";
			String table = "GroupMasterTblVo";
			String manualsearch = "";
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
			if (sdtSearch != null && sdtSearch.length() > 0) {
				if (manualsearch.equalsIgnoreCase("")) {
					globalsearch = " where statusFlag = 1 and  (" + "groupName like ('%" + sdtSearch
								+ "%') or entryDatetime like ('%" + sdtSearch + "%') )";
				} else {
					globalsearch = " statusFlag = 1  and (" + "groupName like ('%" + sdtSearch
									+ "%') or entryDatetime like ('%" + sdtSearch + "%') )";
				}

      } else {
        globalsearch = "where statusFlag = 1";
      }
      if (manualsearch.equalsIgnoreCase("") && globalsearch.equalsIgnoreCase("")) {
        /**
         * search is empty.
         */
      } else {
    	  userfilter = manualsearch + globalsearch;
      }      
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
         // System.out.println(dir);
        }
      }
      int total = 0;
      int totalAfterFilter = 0;
      String where;
      where = userfilter;
      StringBuilder action = null;
      Integer lvrSno = 1;
      JSONArray ja = null;
      try {
        
        JSONArray ar = null;
        String sql = "SELECT count(" + icol + ") FROM " + table +" where statusFlag = 1 " ;
        String sql2 = "SELECT count(" + icol + ") FROM " + table + " as pr " + where + " ";      
        obj.put("servicecode", "SI0011");
        obj.put("sqlQuery", sql);        
        obj.put("sqlQueryFilter", sql2);
        obj.put("sdtSearch", sdtSearch);// search string
        String jsonTextFinal = obj.toString();
        String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
        String finalUrl = getText("groupmanagement.view.url");
        Commonutility.toWriteConsole("Step 2 : Service Called : "+finalUrl);
	    logwrite.logMessage("Step 2 : Service Called : "+finalUrl, "info", GroupManageDTable.class);
        String response = commonObj.jsonRequest(finalUrl, temp);   
        Commonutility.toWriteConsole("Step 3 : Service Response : "+response);
	    logwrite.logMessage("Step 3 : Service Response : "+response, "info", GroupManageDTable.class);
        lvrRspjsonobj = new JSONObject(response);
        lvrRspdatajsonobj = (JSONObject) Commonutility.toHasChkJsonRtnValObj(lvrRspjsonobj, "data");
        
				total = lvrRspdatajsonobj.getInt("InitCount");
				ar = lvrRspdatajsonobj.getJSONArray("groupMgmt");
				JSONObject jsonList = new JSONObject();
				Date date = null;
				for (int i = 0; i < ar.length(); i++) {
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					String groupname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "groupname");
					String groupcode = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "groupcode");
					String status = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "statusflg");
					String entrydate = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "entrydate");	
					String lvrMemebrcnt = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "grpmembercnt");
					date = format.parse(entrydate);
					String fmtentrydate = format.format(date);
         
					ja = new JSONArray();
					if (status.equalsIgnoreCase("1")) {
						status = "Active";
					} else if (status.equalsIgnoreCase("2")) {
						status = "DeActive";
					}
					action = null;
					action = new StringBuilder();
					action.append("<a class=\"\"  style=\"margin-right:15px;cursor: pointer;\" role=\"\" id='editgroupnameId'"
							+ "onclick=\"editGroupFunct('" + groupcode + "','" + groupname + "');\" ><i class=\"tooltipped "+getText("button.color.edit")+"\" "
							+ "data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>");
					if (!Commonutility.checkempty(lvrMemebrcnt) || lvrMemebrcnt.equalsIgnoreCase("0")) {
						action.append("<a class=\"\" style=\"margin-right:15px;cursor: pointer;\" role=\"\" "
	                    + " onclick=\"deleteuser('" + groupcode
	                    + "');\"><i class=\"tooltipped "+getText("button.color.delete")+"\" "
	                    + "data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\"></i></a>");
					} else {
						
					}
             if (groupcode == null) {
            	 groupcode = "";
             }
             if (groupname == null) {
            	 groupname = "";
             }
             ja.put(lvrSno);
             ja.put(groupname);
             ja.put(lvrMemebrcnt);
             ja.put(status);
             ja.put(fmtentrydate);
             ja.put(action.toString());
             array.put(ja);
             action = null;
             lvrSno++;                        
        }    
				totalAfterFilter = lvrRspdatajsonobj.getInt("countAfterFilter");
				result.put("sEcho", echo);
				result.put("iTotalRecords", total);
				result.put("iTotalDisplayRecords", totalAfterFilter);
				result.put("aaData", array);
				Commonutility.toWriteConsole("Step 4 :Group Manage Called.[End]");
			    logwrite.logMessage("Step 4 :Group Manage Called.[End]", "info", GroupManageDTable.class);
				getResponse();
			} catch (Exception ex) {
				Commonutility.toWriteConsole("Step -1 : Exception found : "+ex);
				logwrite.logMessage("Step -1 : Exception found : "+ex, "error", GroupManageDTable.class);
			} finally {
				action =null; ja = null;
			}

		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Exception found : "+ex);
			logwrite.logMessage("Step -1 : Exception found : "+ex, "error", GroupManageDTable.class);
		} finally {
			obj = null; logwrite = null; lvrRspjsonobj = null; lvrRspdatajsonobj =  null;
		}
      return SUCCESS;
    }
        //ActionContext.getContext().getSession().put("globquery", userfilter);      
        
        
     
     
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

  public JSONObject getResult() {
    return result;
  }

  public void setResult(JSONObject result) {
    this.result = result;
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