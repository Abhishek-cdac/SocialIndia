package com.socialindia.reportmgmt;

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
import com.socialindia.labour.persistance.LaborProfileTblVO;
import com.socialindia.login.EncDecrypt;

public class LabourMgmtReportDataTbl extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware{

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
	  List<LaborProfileTblVO> userList = new ArrayList<LaborProfileTblVO>();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao();
	  private String fromdate;
	  private String todate;
	  
	  /**
	   * For merchant user records showing on data table.
	   */
	  public String execute() {
		  Map sessionMap = null;
	    try {
			System.out.println("Step 1 : Labor Select All Called.");
			sessionMap = ActionContext.getContext().getSession();
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
	      int total = 0,startcnt=0;
	      int totalAfterFilter = 0;
	      JSONArray ar = null;
	      JSONObject dataJson = null;
	      JSONObject finaljj = null;
	      JSONArray lvrfinaljaryobj = null;
	      int lvrInc = 1;
	      try {	        
	    	dataJson=new JSONObject();
	  		dataJson.put("lbrstatus", "1");
	  		dataJson.put("countflg", "yes");
	  		dataJson.put("countfilterflg", "yes");
	  		dataJson.put("startrow", String.valueOf(start));// starting row
			dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
			dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
			dataJson.put("fromdate", fromdate);
			dataJson.put("todate", todate);
			dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
	  		finaljj=new JSONObject();
	  		finaljj.put("servicecode", "SI3005");		
	  		finaljj.put("data", dataJson);	      
	        String jsonTextFinal = finaljj.toString();
	        System.out.println("jsonTextFinal---------------------46546566"+jsonTextFinal);
	        jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.labormgmtdatatablereport.action");					
			String response=commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("labordetails");
				String sttcont = json_data.getString("rowstart");
				if(sttcont!=null){
					startcnt=Integer.parseInt(sttcont);
				}
			
				JSONObject jsonList = new JSONObject();
				String laborName =null,lvrLbrcmpnyname=null,laborMobno=null,laborworktyp=null,lbr_img=null,laborskills=null,laboreverify=null,laboremailid=null,lbrserviceid=null,lbrgrpcode=null,addres=null,entryDate=null,modifyDate=null;
				int laborid;
				double lbr_rating=0;
				JSONArray skills_category = new JSONArray();
				String[] skillsarr;
				List<String> skillslist = new ArrayList<String>();
				String catskillidname="";		
				LaborProfileTblVO labprofObj=new LaborProfileTblVO();
				for (int i = 0; i < ar.length(); i++) {
				    catskillidname="";lvrLbrcmpnyname = "";
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					laborName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_name");					
					 laborMobno=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_mobno");					
					 laborworktyp=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_wrktypName");					
					 laborid=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_id");					
					 lbr_img=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "str_lbr_webimage");					
					 laborskills=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_wrktypName");					
					 laboreverify=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_verifedstatus");					
					 skills_category =(JSONArray) Commonutility.toHasChkJsonRtnValObj(jsonList, "jArry_lbr_catg");					
					 laboremailid =(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_email");
					 lbrserviceid=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_serviceid");
					 String lvrratstr = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_rating");
					 if(lvrratstr!=null){
							lbr_rating = Double.parseDouble(lvrratstr);
					 } else{
						 lbr_rating = 0.0;
					 }
					 lbrgrpcode= (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_grpid");
					 addres=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_address1");
					 entryDate=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "entryDatetime");
					 modifyDate=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "modifyDatetime");
					 
					if (skills_category.length() > 0) {	 
						 for(int j = 0; j < skills_category.length(); j++){
							 catskillidname +=  skills_category.getJSONObject(j).getString("categoryname")+", ";						    
						 } 						
					} else {
						catskillidname = "";
					}		
					lvrLbrcmpnyname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_cmpnyname");
					lvrfinaljaryobj = new JSONArray();
					lvrfinaljaryobj.put(lvrInc);
					lvrfinaljaryobj.put(laborName);
					lvrfinaljaryobj.put(lvrLbrcmpnyname);
					lvrfinaljaryobj.put(laborMobno);
					lvrfinaljaryobj.put(laboremailid);
					lvrfinaljaryobj.put(catskillidname);
					lvrfinaljaryobj.put("<div class=\"breakword\">" + addres + "</div>");
					lvrfinaljaryobj.put(entryDate);
					array.put(lvrfinaljaryobj);
					lvrInc++;
					lvrLbrcmpnyname = "";
				}
			 totalAfterFilter =  json_data.getInt("countAfterFilter");	       
	      } catch (Exception ex) {	       
	        System.out.println("Step -1 : Exception Found in "+getClass().getSimpleName()+".class : " + ex);
	      }	     
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
			getResponse();
	      
	     /* try {
	        sqlQuery = globalsearch;
	        ActionContext.getContext().getSession().put("globquery", userfilter);	        	       
	        String uniId = "";
	        String laborName = "";
	        String userEmailId = "";
	        String userMobno = "";
	        String workcategory = "";
	        String statusflg = "";
	        String action = "";
	        String laborservice_id="", skillsar,verify_sts="",  lbr_rate="",  lbr_grpcode="",modifyDatetime=null,entryDatetime=null,addr=null;
	        LaborProfileTblVO userObj;
	        try {	       	        		
	        		int sno = start;
	        		for (Iterator<LaborProfileTblVO> it = userList.iterator(); it.hasNext();) {
	        				userObj = it.next();
	        				sno++;	         
	        				JSONArray ja;
	        				ja = new JSONArray();
	        				uniId = Integer.toString(userObj.getIvrBnLBR_ID());
	        				laborName = userObj.getIvrBnLBR_NAME();
	        				userEmailId = userObj.getIvrBnLBR_EMAIL();
	        				userMobno = userObj.getIvrBnLBR_PH_NO();
	        				workcategory = userObj.getWrktypname();
	        				skillsar =userObj.getSkillslistname().trim();
	        				laborservice_id=userObj.getIvrBnLBR_SERVICE_ID();
	        				lbr_grpcode=userObj.getLbrgrpid();
//	           lbr_rating=;
	        				lbr_rate=String.valueOf(userObj.getIvrBnLBR_RATING());
	        				if (skillsar.length() > 0) {
	        					skillsar = skillsar.substring(0, skillsar.length() - 1);
	        				}	           
	        				verify_sts=userObj.getIvrBnVERIFIED_STATUS();
	        				entryDatetime=userObj.getEntryDatetime();
	        				modifyDatetime=userObj.getModifyDatetime();
	        				addr=userObj.getIvrBnLBR_ADD_1();
	        	
						if (laborName == null) {
							laborName = "";
						}
						if (userMobno == null) {
							userMobno = "";
						}
						if (userEmailId == null) {
							userEmailId = "";
						}
						if (workcategory == null) {
							workcategory = "";
						}
						if (verify_sts == null) {
							verify_sts = "";
						}
						ja.put(sno);
						ja.put(laborName);
						ja.put(userMobno);
						ja.put(userEmailId);
						ja.put(workcategory);
						ja.put("<div class=\"breakword\">" + addr + "</div>");
						ja.put(entryDatetime);
						ja.put(modifyDatetime);
						array.put(ja);
						action = "";
	          }

	        } catch (Exception ex) {
	          ex.printStackTrace();
	        }     
	        result.put("iTotalRecords", total);
	        result.put("iTotalDisplayRecords", totalAfterFilter);
	        result.put("aaData", array);

	        getResponse();
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }*/

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