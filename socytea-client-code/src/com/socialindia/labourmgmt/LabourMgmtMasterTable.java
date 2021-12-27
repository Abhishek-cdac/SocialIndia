package com.socialindia.labourmgmt;

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
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class LabourMgmtMasterTable extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware{

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
	  private Common commonObj = new CommonDao();
	  
	  /**
	   * For merchant user records showing on data table.
	   */
	public String execute() {
	
		Log logwrite = null;
		try {
			logwrite = new Log();			
			System.out.println("Step 1 : Labor Data will Fetched called [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Labor Data will Fetched called [Start]", "info", LabourMgmtMasterTable.class);			
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
			JSONObject dataJson = null;
			JSONObject finaljj = null;
			JSONObject jsonList = null;
			JSONObject json = null;
			JSONObject json_data = null;
			JSONArray lvrDataJsonary = null;
			StringBuilder lvrSbldraction = null;
			try {
				dataJson = new JSONObject();
				jsonList = new JSONObject();
				dataJson.put("lbrstatus", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal//
				dataJson.put("timestamp", "");// overalltotal//													// row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				finaljj = new JSONObject();
				finaljj.put("servicecode", "SI3005");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				System.out.println("Step 2 : Labor Data will Fetched Service API Request: " + jsonTextFinal);
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.labormgmtdatatableview.action");
				System.out.println("Step 2 : Labor Data will Fetched Service API : " + finalUrl);
				logwrite.logMessage("Step 2 : Labor Data will Fetched Service API : " + finalUrl, "info", LabourMgmtMasterTable.class);	
				String response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("labordetails");			
				jsonList = new JSONObject();
				String laborName =null,lvrLbrcmpnayname=null,laborMobno=null,laborworktyp=null,lbr_img=null,laborskills=null,laboreverify=null,laboremailid=null,lbrserviceid=null,lbrgrpcode=null;
				int laborid;
				double lbr_rating=0;
				JSONArray skills_category = new JSONArray();
				String[] skillsarr;
				List<String> skillslist = new ArrayList<String>();
				String catskillidname="", lbr_ratingdiv = "", imagepath = "", action = "",cat_skillname="";		
				int lvrSno = start+1;
				for (int i = 0; i < ar.length(); i++) {
				    catskillidname="";
				    imagepath="";
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					laborName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_name");					
					 laborMobno=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_mobno");					
					 laborworktyp=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_wrktypName");					
					 laborid=Integer.parseInt((String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_id"));					
					 lbr_img=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "str_lbr_webimage");					
					 laborskills=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_wrktypName");					
					 laboreverify=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_verifedstatus");					
					 skills_category =(JSONArray) Commonutility.toHasChkJsonRtnValObj(jsonList, "jArry_lbr_catg");					
					 laboremailid =(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_email");
					 lbrserviceid=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_serviceid");
					 String lvrratstr = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_rating");
					// lbr_rating = (Double) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_rating");
					 lbrgrpcode =(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_grpid");
					 lvrLbrcmpnayname =(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_cmpnyname");
					 laborProfileImage = lbr_img;	
					 int k=0; 
					if (skills_category.length() > 0) {	 
						 for(int j = 0; j < skills_category.length(); j++){
							 catskillidname +=  (String) Commonutility.toHasChkJsonRtnValObj(skills_category.getJSONObject(j),"categoryname");
							
							 cat_skillname +=  (String) Commonutility.toHasChkJsonRtnValObj(skills_category.getJSONObject(j),"skillsname")+"  ";
							// break;
						// }
							 //catskillidname +=  skills_category.getJSONObject(j).getString("categoryname")+", ";
							// cat_skillname +=  skills_category.getJSONObject(j).getString("skillsname")+", ";
						 } 						
					} else {
						 catskillidname="";cat_skillname="";
					}	
					if(lvrratstr!=null){
						lbr_rating = Double.parseDouble(lvrratstr);
					}
					//userList.add(new LaborProfileTblVO(laborid, laborName, laborMobno,laboreverify, laborworktyp, lbr_img,catskillidname,laboremailid,lbrserviceid,lbr_rating,lbrgrpcode,null,null,null));									
					lvrDataJsonary = new JSONArray();				
					if (lbr_rating <=0) {
						lbr_ratingdiv="<i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Not Rating</div>";
					} else if (lbr_rating>=1 && lbr_rating<2) {
    					/*verify_sts ="Verified";*/
						lbr_ratingdiv="<i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Very Bad</div>";
					} else if (lbr_rating>=2 && lbr_rating<3) {
						/*verify_sts ="Verified";*/
						lbr_ratingdiv="<i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Bad</div>";
					} else if (lbr_rating>=3 && lbr_rating<4) {
						/*verify_sts ="Verified";*/
						lbr_ratingdiv="<i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d\" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color:#dad5da \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Not Bad</div>";
					} else if (lbr_rating>=4 && lbr_rating<5) {
						lbr_ratingdiv="<i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color: #dad5da\" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Good</div>";
					} else if (lbr_rating>=5) {
						lbr_ratingdiv="<i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d\" class=\" "+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><i style=\"color:#ff528d \" class=\""+getText("button.color.ratting")+"\"></i><div style=\"margin-left:10px\">Excellent</div>";
					} else{
						
					}
      	          
					if (lbr_img.length() > 0) {    	   
						imagepath  ="<img id=\"myImg_"+laborid+"\" name=\"LaborImageFileName\" class=\"webimagedatable hoverable tooltipped\" data-tooltip=\"Click to view\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" src=\"/templogo/labor/mobile/"+laborid+"/"+lbr_img+"\" onclick=\"toViewlargesizeimgwithsrc(this.id,'/templogo/labor/web/"+laborid+"/"+lbr_img+"');\">"; 
					} else {    	 
						imagepath  ="<img id=\"lbrimg_"+laborid+"\" name=\"laborimag\" class=\"webimagedatable hoverable tooltipped\" data-tooltip=\"Click to view\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\" src=\""+getText("Resource.path")+"/images/social/profile-default-male.png\" onclick=\"toViewlargesizeimgwithsrc(this.id,'"+getText("Resource.path")+"/images/social/profile-default-male.png')\">";
					}
					lvrSbldraction =  new StringBuilder();
					
					lvrSbldraction.append("<a class=\"societystyle\" onclick=\"viewlabor('" + laborid + "','"+lbrserviceid+"');\">"
							+ "<i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>");
					
					lvrSbldraction.append("<a class=\"societystyle\" onclick=\"editlabor('"
			                			 + laborid + "','"+lbrserviceid+"');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>");
					
					lvrSbldraction.append("<i class=\"societystyle tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\""
							+ " onclick=\"deletelaboraction('" + laborid + "','"+lbrserviceid+"');\"></i>");
					
					lvrSbldraction.append("<a class=\"societystyle\" onclick=\"labourfeedback('" + laborid+ "','"+laborName+"','"+lbrgrpcode+"');\" >"
		            		+ "<i class=\"tooltipped "+getText("button.color.feedback")+"\" data-tooltip=\"Feedback\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>");					
		            
					
					lvrDataJsonary.put(lvrSno);
		            lvrDataJsonary.put(imagepath);
		            lvrDataJsonary.put(laborName);
		            lvrDataJsonary.put(laborMobno);
		            lvrDataJsonary.put(lvrLbrcmpnayname);
		           // lvrDataJsonary.put(laboremailid);
		           // lvrDataJsonary.put(laborworktyp);
		            
		            lvrDataJsonary.put(lbr_ratingdiv);
		            lvrDataJsonary.put(lvrSbldraction.toString());
		            array.put(lvrDataJsonary);
		            lvrSbldraction =  null;lvrSno++;
				
				}
				totalAfterFilter =  json_data.getInt("countAfterFilter");
	      } catch (Exception ex) {	       
	        System.out.println("Exception get totalcount :  " + ex);
	      }	finally {
	    	   ar = null; dataJson = null; finaljj = null; jsonList = null; json = null; json_data = null; lvrSbldraction = null;
	      }
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
			System.out.println("Step 3 : Labor Data will Fetched called [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 3 : Labor Data will Fetched called [End]", "info", LabourMgmtMasterTable.class);
			getResponse();	  
	    } catch (Exception ex) {
	    	System.out.println("Step -1 : Labor Data will Fetched Exception Found LabourMgmtMasterTable.class : " + ex);
			logwrite.logMessage("Step -1 : Labor Data will Fetched Exception Found : "+ex, "error", LabourMgmtMasterTable.class);
	    } finally {
	    	logwrite = null;
			System.gc();
			//System.out.println("::::After:::::");Commonutility.toGetJavaHeapMemory();
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
		} finally {
			out = null;
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
}
