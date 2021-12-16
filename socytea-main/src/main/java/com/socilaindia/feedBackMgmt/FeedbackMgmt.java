package com.socilaindia.feedBackMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import com.socilaindia.feedBackMgmt.persistance.FeedbackTblVO;

public class FeedbackMgmt extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware{

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
	  private String laborProfileImage="";
	  List<FeedbackTblVO> userList = new ArrayList<FeedbackTblVO>();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao();
	  private String searchval;
	  private String searchname;
	  private String slectfield;
	  private String society;
	  
	  /**
	   * For merchant user records showing on data table.
	   */
	  public String execute() {
		  JSONArray array =null; JSONArray ar =null;JSONObject dataJson=null;JSONObject finaljj=null;JSONObject json =null;JSONObject json_data=null;
		  JSONObject jsonList =null;
		  int amount = 10;int total = 0; int totalAfterFilter = 0;
	      int start = 0;
	      int echo = 0;
	      int col = 0;
	      String globalsearch = "";
	      String userfilter = "";String societycode=null;
	      String dir = "Desc"; String sAmount="";
	    try {
			System.out.println("FeedBack Datatable  loading.......");
			sdtEcho = request.getParameter("sEcho");
			sdtSearch = request.getParameter("sSearch");
			idtDisplayStart = request.getParameter("iDisplayStart");
			sAmount = request.getParameter("iDisplayLength");
		    Map sessionMap = ActionContext.getContext().getSession();
		    if (sdtSearch == null) {
		        sdtSearch = "";
		     }
		    array = new JSONArray();
		    if (idtDisplayStart != null) {
		    	start = Integer.parseInt(idtDisplayStart);
		    	if (start < 0) {
		    		start = 0;
		    	}
		    }else {
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
	      }if (sdtEcho != null) {
	        echo = Integer.parseInt(sdtEcho);
	      }if (idtSortCol0 != null) {
	        col = Integer.parseInt(idtSortCol0);
	        if (col < 0 || col > 3) {
	          col = 0;
	        }
	      }if (sdtSortDir0 != null) {
		        if (!sdtSortDir0.equals("asc")) {
		          dir = "desc";
		        }
	      }
			String dbkby_lname = null, feedbackdate = null, dbkbyto_fname = null, feedbacktotext = null, feedbacktoid = null, fdbkby_userid = null, feedbackrating = null, feedbacktoname = null, feed_group = null, lbrservicetoid = null;
			String feedbacttoid = null,temp = null,finalUrl = null,response = null;
			try { 
				societycode = String.valueOf(sessionMap.get("sSoctyId"));
				dataJson = new JSONObject();
				dataJson.put("fdbkstatus", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal // row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				// dataJson.put("currentloginid", // String.valueOf(sessionMap.get("USERID")));
				dataJson.put("fdbkforusergrp", String.valueOf(sessionMap.get("GROUPCODE")));
				dataJson.put("fdbkforuser", String.valueOf(sessionMap.get("USERID")));
				dataJson.put("fdbkforsocid", societycode);
				dataJson.put("laborfdbkflg", "");
				if (society == null || society.equalsIgnoreCase("")) {
					dataJson.put("society", societycode);
				} else {
					dataJson.put("society", society);
				}
				if (slectfield == null || slectfield.equalsIgnoreCase("")) {
					dataJson.put("slectfield", "");
				} else {
					dataJson.put("slectfield", slectfield);
				}
				if (searchname == null || searchname.equalsIgnoreCase("")) {
					dataJson.put("searchname", "");
					// dataJson.put("searchflg","");
				} else {
					dataJson.put("searchname", searchname);
				}
				dataJson.put("searchflg", searchval);
				finaljj = new JSONObject();
				finaljj.put("servicecode", "SI3005");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				finalUrl = getText("socialindia.feedbackmanagement.action");
				response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("feedbackdetails");
				String dbkby_fname = "";
				String dbkby_fname1 = "",feedbackid = "",feedbackusrid = "",lbr_rate = "",lbr_rating = "",feedbacktext = "",groupname = "",feedbackname = "",toName = "",lbrserviceid = "",fedbckid = "",action = "";
		        int sno = start+1;
				JSONArray ja;
				for (int i = 0; i < ar.length(); i++) {
	  				jsonList=null;
	  				jsonList = ar.getJSONObject(i);	
					dbkbyto_fname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "dbkby_fname");
					dbkby_lname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "dbkby_lname");
					feedbackdate = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "feedbackdate");
					feedbacktotext = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "feedbacktext");
					feedbacktoid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "feedbackid");
					feedbacttoid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "feedbacttoid");
					fdbkby_userid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "fdbkby_userid");
					feedbackrating = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "feedbackrating");
					feedbacktoname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "feedbacttoname");
					lbrservicetoid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbrserviceid");
					feed_group = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "dbkby_group");
	  			//	userList.add(new FeedbackTblVO(dbkby_lname, feedbackdate,dbkbyto_fname, feedbacktotext, feedbacktoid,fdbkby_userid,feedbackrating,feedbacktoname,feed_group,lbrservicetoid,feedbacttoid));					
					dbkby_fname1 = dbkbyto_fname;
					feedbackusrid = fdbkby_userid;
	 	            dbkby_fname="<a class=\"pointer tooltipped\" data-tooltip=\"Click to View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" target=\"_blank\" href=\"viewresidentdetails?deleteresidentid="+feedbackusrid+"\">"+dbkby_fname1+"</a>";
					feedbackid = feedbacktoid;
					fedbckid = feedbacttoid;
					feedbacktext = feedbacktotext;
					lbr_rate = feedbackrating;
					groupname = feed_group;
					toName = feedbacktoname;
					lbrserviceid = lbrservicetoid;	
					if (Commonutility.checkempty(toName) && Commonutility.checkempty(groupname)) {
						 if(groupname!=null && groupname.equalsIgnoreCase("labor")){
			 	            	feedbackname="<div><a class=\"pointer tooltipped\" data-tooltip=\"Click to View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" target=\"_blank\" href=\"viewlbrfeedbck?deletelaborid="+fedbckid+"&deletelaborserviceid="+lbrserviceid+"\" >"+toName+"["+groupname+"]"+"</a></div>";
			 	            } else{
			 	            	feedbackname="<div>"+toName+"["+groupname+"]"+"</div>";
			 	            }
					} else {
						feedbackname="N/A";
					}
	 	           
	 	            	 	           
	 	           if (lbr_rate.equalsIgnoreCase("0")) {
						lbr_rating="<i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Not Rating</div>";
					} else if (lbr_rate.equalsIgnoreCase("1")) {
   					/*verify_sts ="Verified";*/
						lbr_rating="<i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Very Bad</div>";
					} else if (lbr_rate.equalsIgnoreCase("2")) {
						/*verify_sts ="Verified";*/
						lbr_rating="<i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Bad</div>";
					} else if (lbr_rate.equalsIgnoreCase("3")) {
						/*verify_sts ="Verified";*/
						lbr_rating="<i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Not Bad</div>";
					} else if (lbr_rate.equalsIgnoreCase("4")) {
						lbr_rating="<i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Good</div>";
					} else if (lbr_rate.equalsIgnoreCase("5")) {
						lbr_rating="<i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Excellent</div>";
					}
	 	            
	 	            
	 	           action +="<div class=\"divactionicon\">"
					 		+ "<a style=\"cursor: pointer;\"   onclick=\"viewfeedback('" + feedbackid+ "');\" >"
					 				+ " <i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\" ></i></a></div>" ;
	 	          
	 	           action +="<div onclick=\"deletefeedbackaction('"+ feedbackid + "')\" class=\"divactionicon\">"
					 		+ "<i data-tooltip=\"Close\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\" class=\"tooltipped "+getText("button.color.close")+"\" ></i></div>";
		           
	 	          
	 	          /* action += "<a class=\"btn btn-default\" data-target=\"#myModal\" title=\"close\"  data-toggle=\"modal\" onclick=\"deleteeventaction('"+ feedbackid + "')\" role=\"button\" data-backdrop=\"static\" style=\"margin-right: 20px;\";\"><span class=\"deactivedoc\"></span></a>";
	 	            action += "<span style=\"margin-right: 20px;\" class=\"glyphicon glyphicon-trash btn btn-default\" title=\"Delete Labor\" "
	 	                + "  onclick=\"deleteeventaction('" + feedbackid + "');\"></span>";   */    	           	            	            	        
	 	          
					ja = null;
					ja = new JSONArray();
					if (feedbacktext == null) {
						feedbacktext = "";
					}
					if (feedbackname == null || feedbackname.equalsIgnoreCase("") || feedbackname.equalsIgnoreCase("[]")) {
						feedbackname = "";
					} else {
						feedbackname = feedbackname;
					}
					ja.put(sno);
					ja.put(dbkby_fname);
					ja.put(feedbacktext);
					ja.put(feedbackname);
					ja.put(lbr_rating);
					ja.put(action);
					array.put(ja);
	 	            action = "";
	 	            sno ++;
	  			}
	  			totalAfterFilter =  json_data.getInt("countAfterFilter");
			} catch (Exception ex) {
				System.out.println("Exception get totalcount :  " + ex);
			} finally {
				dbkby_lname = null;feedbackdate=null;dbkbyto_fname=null;feedbacktotext=null;feedbacktoid=null;fdbkby_userid=null;feedbackrating=null;feedbacktoname=null;feed_group=null;lbrservicetoid=null;
				feedbacttoid=null;  
	      }
	      result.put("sEcho", echo);
	   
	        result.put("iTotalRecords", total);
	        result.put("iTotalDisplayRecords", totalAfterFilter);
	        result.put("aaData", array);
	        getResponse();
	      } catch (Exception ex) {
	    	  System.out.println("step -1: Exception found in FeedbackMgmt.class 2: "+ex);
	      }
	     finally{
	    	 ar =null;
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
	public String getlaborProfileImage() {
		return laborProfileImage;
	}

	public void setlaborProfileImage(String laborProfileImage) {
		this.laborProfileImage = laborProfileImage;
	}

	public String getSearchval() {
		return searchval;
	}

	public void setSearchval(String searchval) {
		this.searchval = searchval;
	}

	public String getSearchname() {
		return searchname;
	}

	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}

	public String getSlectfield() {
		return slectfield;
	}

	public void setSlectfield(String slectfield) {
		this.slectfield = slectfield;
	}

	public String getSociety() {
		return society;
	}

	public void setSociety(String society) {
		this.society = society;
	}
	  
	  

}
