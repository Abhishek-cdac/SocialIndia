package com.socialindia.companymgmt;

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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class companyMgmtMasterTable extends ActionSupport implements
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
	  private String CmpyProfileImage="";	
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  /**
	   * For merchant user records showing on data table.
	   */
	  public String execute() {
		JSONArray array = null;
		try {
			Commonutility.toWriteConsole("Step 1 :Company data table called [Start]");	       
			sdtEcho = request.getParameter("sEcho");
			sdtSearch = request.getParameter("sSearch");
			idtDisplayStart = request.getParameter("iDisplayStart");	     
			String sAmount = request.getParameter("iDisplayLength");
			Map sessionMap = ActionContext.getContext().getSession();	     
			if (sdtSearch == null) {
				sdtSearch = "";
			}
			array = new JSONArray();
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
					System.out.println(dir);
				}
			}
			int total = 0;
			int totalAfterFilter = 0;
			JSONArray ar = null;
			JSONArray lvrDataJryobj = null;
			JSONObject jsonList = null;
			try {
				JSONObject dataJson = new JSONObject();
				dataJson.put("lbrstatus", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal // row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
				JSONObject finaljj = new JSONObject();
				finaljj.put("servicecode", "SI6001");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.companymgmt.companymgmtdatable");
				String response = commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("companydetails");
				jsonList = new JSONObject();
				String CmpyName = null, CmpyMobno = null, cmpyreg = null, cmpy_img = null, cmpyadd1 = null, cmpyverify = null, cmpyemailid = null, cmpycntyname = null;
				int companyid;				
				String action = "", imagepath = "";
				int lvrInc = 1;
				for (int i = 0; i < ar.length(); i++) {					
					jsonList = null;
					jsonList = ar.getJSONObject(i);					
					CmpyName = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "cmpy_name");					
					CmpyMobno=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "cmpy_mobno");					
					cmpyreg=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "cmpy_regno");					
					companyid=(Integer) Commonutility.toHasChkJsonRtnValObj(jsonList, "cmpy_id");					
					cmpy_img=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "str_cmpy_webimage");					
					cmpyadd1=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "cmpy_address1");					
					cmpyverify=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "cmpy_verifedstatus");					
					//skills_category =(JSONArray) Commonutility.toHasChkJsonRtnValObj(jsonList, "jArry_cmpy_catg");					
					cmpyemailid =(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "cmpy_email");
					cmpycntyname=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "cmpy_cntryName");

					//companyList.add(new CompanyMstrTblVO(companyid, CmpyName, CmpyMobno,cmpyverify, cmpyreg, cmpy_img,cmpyadd1,cmpyemailid,cmpycntyname));									
					lvrDataJryobj = new JSONArray();
					if (cmpyverify==null || cmpyverify.equalsIgnoreCase("0")) {
						cmpyverify ="<i class=\"mdi-alert-warning red-text text-lighten-1 minitinysmall\"></i><span class=\"red-text text-accent-3\">Unverified</span>";
					} else if (cmpyverify.equalsIgnoreCase("1")) {
						cmpyverify ="<i class=\"mdi-action-verified-user green-text text-lighten-1 minitinysmall\"></i><span class=\"green-text text-accent-3\">Verified</span>";
					} else {
						cmpyverify ="<i class=\"mdi-alert-warning red-text text-lighten-1 minitinysmall\"></i><span class=\"red-text text-accent-3\">Unverified</span>";
					}					
					cmpy_img = Commonutility.toCheckNullEmpty(cmpy_img);
					
					if (cmpy_img.length() > 0) {
						imagepath+="<img style=\"cursor: pointer;width:60px;\" id=\"myImg_"+companyid+"\" name=\"LaborImageFileName\" class=\"webimagedatable hoverable\" " 
								 + "src=\"/templogo/company/mobile/"+companyid+"/"+cmpy_img+"\" onclick=\"toViewlargesizeimgwithsrc(this.id,'/templogo/company/web/"+companyid+"/"+cmpy_img+"');\">"; 
					} else {
						imagepath +=""; 
					}
					action +=" <div class=\"\"><a class=\"\" title=\"\"  style=\"margin-right:15px;cursor: pointer;\" onclick=\"viewcompany('" + companyid + "');\">"
							+" <i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>";
           
					action +=" <a class=\"\" title=\"\"  role=\"button\" style=\"margin-right: 15px;cursor: pointer;\" onclick=\"editcompany('" + companyid + "');\">"
							+" <i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
            
                    action +=" <a onclick=\"deletecompanyaction('" + companyid + "');\" style=\"margin-right: 15px;cursor: pointer;\">"
            		        +" <i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
            
                    action +=" <a onclick=\"staffcreation();\" style=\"margin-right: 15px;cursor: pointer;\" title=\"\" class=\"\">"
                    		+" <i class=\"tooltipped "+getText("button.color.addresident")+"\" data-tooltip=\"Create Staff\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";	           
					
					CmpyName = Commonutility.toCheckNullEmpty(CmpyName);					
					cmpyreg = Commonutility.toCheckNullEmpty(cmpyreg);
					CmpyMobno = Commonutility.toCheckNullEmpty(CmpyMobno);
					lvrDataJryobj.put(lvrInc);
					lvrDataJryobj.put(imagepath);
					lvrDataJryobj.put(CmpyName);
					lvrDataJryobj.put(cmpyreg);
					lvrDataJryobj.put(CmpyMobno);
					lvrDataJryobj.put(cmpyverify);
					lvrDataJryobj.put(action);
					array.put(lvrDataJryobj);
					action = "";
					imagepath = "";
					lvrDataJryobj = null;
					lvrInc++;
				}			
				totalAfterFilter = json_data.getInt("countAfterFilter");
			} catch (Exception ex) {
				Commonutility.toWriteConsole("Step -1 : Exception found in "+getClass().getSimpleName() +".class : "+ex);
			} finally {
				lvrDataJryobj =null;
			}	     
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
	        result.put("iTotalDisplayRecords", totalAfterFilter);
	        result.put("aaData", array);
	        getResponse();
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -2 : Exception found in "+getClass().getSimpleName() +".class : "+ex);
		} finally {
			
		}
		return SUCCESS;
	}
			
	   /*  try {	        

	        ActionContext.getContext().getSession().put("globquery", userfilter);	        
	        String uniId = "";
	        String CmpyName = "";
	        String cmpyemailid = "";
	        String userMobno = "";
	        String workcategory = "";
	        String statusflg = "";
	        String action = "";
	        String cmpyregno="";
	        String skillsar;
	        String verify_sts="";
	        String imagepath="";
	        String imagewebname="";
	        try {
					CompanyMstrTblVO userObj;
					int sno = start;
					for (Iterator<CompanyMstrTblVO> it = companyList.iterator(); it.hasNext();) {
						imagepath="";
						userObj = it.next();
						sno++;
						// System.out.println(sno);
						JSONArray ja;
						ja = new JSONArray();
						uniId = Integer.toString(userObj.getCompanyId());
						CmpyName = userObj.getCompanyName();
						cmpyregno = userObj.getCmpnyRegno();
						cmpyemailid = userObj.getCompanyEmail();
						userMobno = userObj.getMobileNo();
						CmpyProfileImage = userObj.getImageNameWeb();
						verify_sts = userObj.getVerifyStatus();
						if (verify_sts==null || verify_sts.equalsIgnoreCase("0")) {
							verify_sts ="<i class=\"mdi-alert-warning red-text text-lighten-1 minitinysmall\"></i><span class=\"red-text text-accent-3\">Unverified</span>";
						} else if (verify_sts.equalsIgnoreCase("1")) {
							verify_sts ="<i class=\"mdi-action-verified-user green-text text-lighten-1 minitinysmall\"></i><span class=\"green-text text-accent-3\">Verified</span>";
						} else {
							verify_sts ="<i class=\"mdi-alert-warning red-text text-lighten-1 minitinysmall\"></i><span class=\"red-text text-accent-3\">Unverified</span>";
						}
						if (CmpyProfileImage == null) {
							CmpyProfileImage = "";
						}
	          
						if (CmpyProfileImage.length() > 0) {
							imagepath+="<img style=\"cursor: pointer;width:60px;\" id=\"myImg_"+uniId+"\" name=\"LaborImageFileName\" class=\"webimagedatable hoverable\" " 
									 + "src=\"/templogo/company/mobile/"+uniId+"/"+CmpyProfileImage+"\" onclick=\"toViewlargesizeimgwithsrc(this.id,'/templogo/company/web/"+uniId+"/"+CmpyProfileImage+"');\">"; 
						} else {
							imagepath +=""; 
						}
						action +=" <div class=\"\"><a class=\"\" title=\"\"  style=\"margin-right:15px;cursor: pointer;\" onclick=\"viewcompany('" + uniId + "');\">"
								+" <i class=\"tooltipped "+getText("button.color.view")+"\" data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>";
	           
						action +=" <a class=\"\" title=\"\"  role=\"button\" style=\"margin-right: 15px;cursor: pointer;\" onclick=\"editcompany('" + uniId + "');\">"
								+" <i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	            
	                    action +=" <a onclick=\"deletecompanyaction('" + uniId + "');\" style=\"margin-right: 15px;cursor: pointer;\">"
	            		        +" <i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
	            
	                    action +=" <a onclick=\"staffcreation();\" style=\"margin-right: 15px;cursor: pointer;\" title=\"\" class=\"\">"
	                    		+" <i class=\"tooltipped "+getText("button.color.addresident")+"\" data-tooltip=\"Create Staff\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";	           
						if (CmpyName == null) {
							CmpyName = "";
						}
						if (userMobno == null) {
							userMobno = "";
						}
						if (cmpyregno == null) {
							cmpyregno = "";
						}
						if (workcategory == null) {
							workcategory = "";
						}

						ja.put(sno);
						ja.put(imagepath);
						ja.put(CmpyName);
						ja.put(cmpyregno);
						ja.put(userMobno);
						ja.put(verify_sts);
						ja.put(action);
						array.put(ja);
						action = "";
					}

	        } catch (Exception ex) {
	          ex.printStackTrace();
	        }	       
	        
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }*/

	   

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
}
