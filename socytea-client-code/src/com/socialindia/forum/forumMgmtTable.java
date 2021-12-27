package com.socialindia.forum;

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
import com.socialindia.login.EncDecrypt;

public class forumMgmtTable extends ActionSupport implements
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
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  /**
	   * For merchant user records showing on data table.
	   */
	  public String execute() {
		  Log logWrite = null;
		try {
			logWrite = new Log();
			System.out.println("Step 1 : Fourm Mgmt Data Table Fetch [Start]");	
			logWrite.logMessage("Step 1 : Fourm Mgmt Data Table Fetch [Start]", "info", forumMgmtTable.class);
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
			JSONArray ar = null;JSONArray lvrRtnjary = null;
			int lvrSno = 1;
			String finalName = "";
		    int topicsid = 0, createby = 0, topicscount = 0, userid = 0;
			String firstname = null, groupname = null, topicsname = null, entrydate = null, lastname = null, mobileno = "", action = "";
			try {       
				String lcsocitid = String.valueOf(sessionMap.get("sSoctyId"));
				JSONObject dataJson = new JSONObject();
				dataJson.put("eventstatus", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal
																	// row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				if (searchWord != null) {
					dataJson.put("searchWord", searchWord);// search
				} else {
					dataJson.put("searchWord", "");// search
				}
				if (townShipId != null) {
					dataJson.put("townShipId", townShipId);
				} else {
					dataJson.put("townShipId", "");
				}
				if(societyId!=null && !societyId.equalsIgnoreCase("null") && !societyId.equalsIgnoreCase("") && !societyId.equalsIgnoreCase("undefined")){
					dataJson.put("societyId", societyId);
					System.out.println("societyId if : "+societyId);
				}else{
					if(lcsocitid != null && !lcsocitid.equalsIgnoreCase("") && !lcsocitid.equalsIgnoreCase("null")){
		  			dataJson.put("societyId", lcsocitid);
		  		}else{
		  			System.out.println("societyId else with else : "+"empty");
		  			dataJson.put("societyId", "");
		  		}				
			}
				groupCode = String.valueOf(sessionMap.get("GROUPCODE"));
				if (groupCode != null) {
					dataJson.put("groupCode", groupCode);
				} else {
					dataJson.put("groupCode", "");
				}
				dataJson.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				JSONObject finaljj = new JSONObject();
				finaljj.put("servicecode", "SI8006");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.forum.management.action.url");
				String response = commonObj.jsonRequest(finalUrl, temp);
				System.out.println("Step 2 : Service called");	
				logWrite.logMessage("Step 2 : Service called", "info", forumMgmtTable.class);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("forumdetails");
				JSONObject jsonList = new JSONObject();
				
				for (int i = 0; i < ar.length(); i++) {
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					topicsid = jsonList.getInt("topicsid");
					firstname = jsonList.getString("firstname");
					groupname = jsonList.getString("groupname");
					topicsname = jsonList.getString("topicsname");
					createby = jsonList.getInt("createby");
					entrydate = jsonList.getString("entrydate");
					entrydate = entrydate.replace(".0", "");
					lastname = jsonList.getString("lastname");
					mobileno = jsonList.getString("mobileno");
					topicscount = jsonList.getInt("topicscounts");
					userid = jsonList.getInt("userid");					
					lvrRtnjary = null;
					lvrRtnjary = new JSONArray();					
					int curuserId=(int) sessionMap.get("USERID");
			            action += "<div class=\"vieweventaction \"><a class=\"left pointer\" style=\"margin-right:4%;\" onclick=\"viewforumaction('" + topicsid + "');\">"
				          	   +  "<i class=\"tooltipped pointer "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
			            
			            action += "<a class=\"left pointer\" onclick=\"deleteforumaction('" + topicsid+ "');\">"
			          		   +  "<i class=\"tooltipped pointer "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
			          
			            if(curuserId==userid){
			            	action += "<a class=\"left pointer\" style=\"margin-right: 4%;\" onclick=\"editforum('"+ topicsid + "');\">"
			            		   +  "<i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
			            }
			            action +="</div>";
			            lvrRtnjary.put(lvrSno);
			            lvrRtnjary.put("<div class=\"tooltipped pointer breakword\" data-tooltip=\"View Discuss\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" style=\"color: lightseagreen;float:left;width:auto;cursor:pointer;\"  onclick=\"viewforumaction('" + topicsid + "');\">("+topicscount+")</div>"
			            		+ "<div class=\"breakword\" style=\"float:left;width:87%;margin-left: 1%;\">"+topicsname+"</div>");
			           if(groupname.equalsIgnoreCase("Admin")|| groupname.equalsIgnoreCase("Super Admin")){
			        	   if(firstname!=null && !firstname.equalsIgnoreCase("")){
				            	finalName=firstname+" ("+groupname+")";
				            	lvrRtnjary.put(finalName);
				            }else if(firstname==null && lastname!=null && !lastname.equalsIgnoreCase("")){
				            	finalName=lastname+" ("+groupname+")";
				            	lvrRtnjary.put(finalName);
				            }else{
				            	finalName=mobileno+" ("+groupname+")";
				            	lvrRtnjary.put(finalName);
				            }
			           }else{

				            if(firstname!=null && !firstname.equalsIgnoreCase("")){
				            	finalName="<a target=\"_blank\" href=\"viewresidentdetails?deleteresidentid="+userid+"\" class=\"tooltipped pointer\" data-tooltip=\"View Detail\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" >"+firstname+" ("+groupname+")"+"</a>";
				            	lvrRtnjary.put(finalName);
				            }else if(firstname==null && lastname!=null && !lastname.equalsIgnoreCase("")){
				            	finalName="<a target=\"_blank\" href=\"viewresidentdetails?deleteresidentid="+userid+"\" class=\"tooltipped pointer\" data-tooltip=\"View Detail\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" >"+lastname+" ("+groupname+")"+"</a>";
				            	lvrRtnjary.put(finalName);
				            }else{
				            	finalName="<a target=\"_blank\" href=\"viewresidentdetails?deleteresidentid="+userid+"\" class=\"tooltipped pointer\" data-tooltip=\"View Detail\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" >"+mobileno+"("+groupname+")"+"</a>";
				            	lvrRtnjary.put(finalName);
				            }				           
			           }
			           
					lvrRtnjary.put(entrydate);
					lvrRtnjary.put(action);
					array.put(lvrRtnjary);
					action = "";
					lvrSno++;
					lvrRtnjary = null;
				}			
				totalAfterFilter = json_data.getInt("countAfterFilter");
				System.out.println("Step 3 : Fourm Mgmt Data Table Fetch [End]");	
				logWrite.logMessage("Step 3 : Fourm Mgmt Data Table Fetch [End]", "info", forumMgmtTable.class);
			} catch (Exception ex) {
				System.out.println("Step -1 : Exception Found in "+getClass().getSimpleName()+".class 1 : " + ex);				
				logWrite.logMessage("Step 1 : Fourm Mgmt Data Table Fetch [Start]", "error", forumMgmtTable.class);
			} finally {
				lvrRtnjary = null;
				 topicsid = 0; createby = 0; topicscount = 0; userid = 0;
				firstname = null; groupname = null; topicsname = null; entrydate = null; lastname = null; mobileno = ""; action = "";
			}
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
	        result.put("iTotalDisplayRecords", totalAfterFilter);
	        result.put("aaData", array);
	        getResponse();
		} catch (Exception ex) {
			System.out.println("Step -2 : Exception Found in "+getClass().getSimpleName()+".class 2 : " + ex);					
			logWrite.logMessage("Step -2 : Exception Found : "+ex, "error", forumMgmtTable.class);
		} finally {
			
		}
		return SUCCESS;
	}
	    /*    
	      try {
	        String sql;
	        sql = "FROM " + table + " ";
	        where = userfilter;
	        sqlQuery = globalsearch;

	        ActionContext.getContext().getSession().put("globquery", userfilter);	        
	        sql += where;	        
	        String evetitle = "";
	        String evestartdate = "";
	        String evestarttime = "";
	        String eveenddate = "";
	        String eveendtime = "";
	        String evecurntusrid="";
	        int topicsId = 0;
	        String firstname="";
	        String groupname="";
	        String action = "";
	        int entryby;
	        String topicname="";
	        String entrydate="";
	        String lastName="";
	        String mobileNo="";
	        String finalName="";
	        String finalval="";
	        int topicsCount = 0;
	        int userid;	        	       
	        try {
	        	MvpFourmTopicsTblVO userObj;
	          int sno = 0;
	          for (Iterator<MvpFourmTopicsTblVO> it = forumTopicsList.iterator(); it.hasNext();) {
	            userObj = it.next();
	            sno++;
	            JSONArray ja;
	            ja = new JSONArray();
	            topicsId=userObj.getTopicsId();
	            firstname=userObj.getFirstName();
	            groupname=userObj.getGroupName();
	            topicname=userObj.getTopicsName();
	            entryby=userObj.getEntryBy();
	            entrydate=userObj.getEntryDate();
	            lastName=userObj.getLastName();
	            mobileNo=userObj.getMobileNo();
	            topicsCount=userObj.getTopicsCount();
	            userid=userObj.getUserId();
	            
	            
	            int curuserId=(int) sessionMap.get("USERID");
	            action += "<div class=\"vieweventaction\"><a class=\"left\" style=\"margin-right:4%;cursor: pointer;\" onclick=\"viewforumaction('" + topicsId + "');\">"
		          		+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>";
	            
	          action += "<a class=\"left\" style=\"cursor: pointer;\" onclick=\"deleteforumaction('" + topicsId+ "');\">"
	          		+ "<i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\"10\" data-position=\"left\"></i></a>";
	          
	            if(curuserId==userid){
	            	action += "<a class=\"left\" style=\"margin-right: 4%;cursor: pointer;\" onclick=\"editforum('"+ topicsId + "');\">"
	            			+ "<i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"left\"></i></a>";
	            }
	            action +="</div>";
	            ja.put(sno);
	            ja.put("<div class=\"tooltipped\" data-tooltip=\"View Discuss\" data-delay=\"10\" data-position=\"left\" style=\"color: lightseagreen;float:left;width:auto;cursor:pointer;\"  onclick=\"viewforumaction('" + topicsId + "');\">("+topicsCount+")</div>"
	            		+ "<div style=\"float:left;width:87%;margin-left: 1%;\">"+topicname+"</div>");
	           if(groupname.equalsIgnoreCase("Admin")|| groupname.equalsIgnoreCase("Super Admin")){
	        	   if(firstname!=null && !firstname.equalsIgnoreCase("")){
		            	finalName=firstname+" ("+groupname+")";
		            	ja.put(finalName);
		            }else if(firstname==null && lastName!=null && !lastName.equalsIgnoreCase("")){
		            	finalName=lastName+" ("+groupname+")";
		            	ja.put(finalName);
		            }else{
		            	finalName=mobileNo+" ("+groupname+")";
		            	ja.put(finalName);
		            }
	           }else{

		            if(firstname!=null && !firstname.equalsIgnoreCase("")){
//		            	finalName=firstname+" ("+groupname+")";
		            	finalName="<a target=\"_blank\" href=\"viewresidentdetails?deleteresidentid="+userid+" class=\"tooltipped\" data-tooltip=\"View Discuss\" data-delay=\"10\" data-position=\"left\" style=\"cursor: pointer;\">"+firstname+" ("+groupname+")"+"</a>";
		            	ja.put(finalName);
		            }else if(firstname==null && lastName!=null && !lastName.equalsIgnoreCase("")){
		            	finalName="<a target=\"_blank\" href=\"viewresidentdetails?deleteresidentid="+userid+" class=\"tooltipped\" data-tooltip=\"View Discuss\" data-delay=\"10\" data-position=\"left\" style=\"cursor: pointer;\">"+lastName+" ("+groupname+")"+"</a>";
		            	ja.put(finalName);
		            }else{
		            	finalName="<a target=\"_blank\" href=\"viewresidentdetails?deleteresidentid="+userid+" class=\"tooltipped\" data-tooltip=\"View Discuss\" data-delay=\"10\" data-position=\"left\" style=\"cursor: pointer;\">"+mobileNo+"("+groupname+")"+"</a>";
		            	ja.put(finalName);
		            }
		           
	           }
	           
	            ja.put(entrydate);
	            ja.put(action);
	            array.put(ja);
	            action = "";
	          }

	        } catch (Exception ex) {
	          ex.printStackTrace();
	        }
	        
	       
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }
 */
	    

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
