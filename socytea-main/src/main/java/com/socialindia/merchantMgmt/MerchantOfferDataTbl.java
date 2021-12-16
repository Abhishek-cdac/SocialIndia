package com.socialindia.merchantMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.vo.MerchantTblVO;
import com.socialindia.vo.MerchantUtilitiTblVO;

public class MerchantOfferDataTbl  extends ActionSupport implements
SessionAware, ServletRequestAware, ServletResponseAware,
ServletContextAware{
	  private static final long serialVersionUID = 1L;
	  private String idtDisplayStart;
	  private String idtDisplayLength;
	  private String sdtEcho;
	  private String idtSortCol0;
	  private String sdtSortDir0;
	  private String sdtSearch;
	  
	  private String searchfield;
	  private String usersearchname;
	  private String sdtSearchff;
	  
	  private JSONObject result = new JSONObject();
	  JSONArray aaData = new JSONArray();
	  private String idtTotalDisplayRecords = "";
	  private String idtTotalRecords = "";
	  private HttpServletResponse response;
	  private HttpServletRequest request = ServletActionContext.getRequest();
	  private String sqlQuery;
	  private String CmpyProfileImage="";
	  List<MerchantUtilitiTblVO> merchantList = new ArrayList<MerchantUtilitiTblVO>();
	  MerchantTblVO merchantobj=new MerchantTblVO();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao(); 
	  ResourceBundle rb = ResourceBundle.getBundle("applicationResources");
	  private Integer mrchntUniqId;
	  public String execute() {
		  Log logWrite = null;
		  Map sessionMap = null;
		  JSONArray array = null;
		try {
			logWrite = new Log();
			Commonutility.toWriteConsole("Step 1 : Mercahnt offer datatable called. [Start]");
			sdtEcho = request.getParameter("sEcho");
			sdtSearch = request.getParameter("sSearch");
			idtDisplayStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			sessionMap = ActionContext.getContext().getSession();
			if (sdtSearch == null) {
				sdtSearch = "";
			}
			array = new JSONArray();
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
			JSONArray ja = null;
			StringBuilder lvrStblobj = null;
			JSONObject jsonList = null;
			JSONObject dataJson = null;
			try {
				jsonList = new JSONObject();
				dataJson = new JSONObject();
				if (mrchntUniqId == null) {
					if ((mrchntUniqId == null || mrchntUniqId == -1 || mrchntUniqId == 0) && sessionMap.get("cruntsessionmerchantuniqid") != null) {
					String stfiddd = (String) sessionMap.get("cruntsessionmerchantuniqid");
					mrchntUniqId = Integer.parseInt(stfiddd);					
					}
				} else {			
					sessionMap.put("cruntsessionmerchantuniqid", String.valueOf(mrchntUniqId));								
				}	    		    		   
				dataJson.put("eventstatus", "1");
				dataJson.put("countflg", "yes");
				dataJson.put("countfilterflg", "yes");
				dataJson.put("startrow", String.valueOf(start));// starting row
				dataJson.put("totalrow", String.valueOf(amount));// overalltotal  row
				dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
				dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));
				dataJson.put("mrchntId",mrchntUniqId);
				if (searchfield != null) {
					dataJson.put("srchField", searchfield);
				} else {
					dataJson.put("srchField", "");
				}
				if (usersearchname != null) {
					dataJson.put("srchFieldval", usersearchname);
				} else {
					dataJson.put("srchFieldval", "");
				}
				if (sdtSearchff != null) {
					dataJson.put("srchflg", sdtSearchff);
				} else {
					dataJson.put("srchflg", "");
				}
				JSONObject finaljj = new JSONObject();
				finaljj.put("servicecode", "SI6423");
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.mrchantMng.loadOferDataTable");
				String response = commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("eventdetails");
			
				MerchantUtilitiTblVO merchantobj=new MerchantUtilitiTblVO();
				String broucherName = null, offerName = null, actualPrice = null, offerPrice = null, offerValidFrom = "", offerValidTo = "", imageName = "", imagePath = "";
				String imagewebpath = null;
				int merchantUtilId = 0;
				String PriceDetail = "", action = "";
				int lvrSno = 1;
				String imagetag = "";
				for (int i = 0; i < ar.length(); i++) {
					merchantobj = new MerchantUtilitiTblVO();
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					merchantUtilId = jsonList.getInt("merchantUtilId");
					broucherName = jsonList.getString("broucherName");
					offerName = jsonList.getString("offerName");
					actualPrice = jsonList.getString("actualPrice");
					offerPrice = jsonList.getString("offerPrice");
					offerValidFrom = jsonList.getString("offerValidFrom");
					offerValidTo = jsonList.getString("offerValidTo");
					imageName = jsonList.getString("imageName");
					imagePath = jsonList.getString("imagePath");
					imagewebpath = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "imagewebPath");				
				 	ja = null;
		            ja = new JSONArray();
		            lvrStblobj = null;
		            lvrStblobj = new StringBuilder();
		            lvrStblobj.append("<a class=\"left pointer\"  role=\"button\" style=\"margin-right: 4%;\" onclick=\"editMerchantDetail('"+ merchantUtilId + "');\" ><i class=\"tooltipped "+getText("button.color.edit")+"\" data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>");
		            lvrStblobj.append("<a class=\"left\" style=\"margin-right: 4%;cursor: pointer;\" title=\"Delete\"  role=\"button\"  onclick=\"deleteMerchantDetail('"+ merchantUtilId + "');\"><i class=\"tooltipped "+getText("button.color.delete")+"\" data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i>	</a>");
		            lvrStblobj.append("</div>");		            		          					
					imagetag = "<div class=\"offerimgdatadbl pointer hoverable\"><img id=\"offimgid_"+merchantUtilId+"\" width=\"100%\" height=\"100%\" alt=\"Offer\" "
          				     + " src=\""+imagePath+"\" onclick=\"toViewlargesizeimgwithsrc(this.id,'"+imagewebpath+"');\"></div>";
          
					PriceDetail = "<div class=\"usermgmt1\"><div> Actual Price<span> : "+actualPrice+" Rs </span></div>"
								+ "<div> Offer Price<span> : "+offerPrice+" Rs </span></div><div>  Valid From <span> : "+offerValidFrom+" </span></div>"
								+ "<div>  Valid To <span> : "+offerValidTo+" </span></div>";
					ja.put(lvrSno);
					ja.put(imagetag);
					ja.put(offerName);
					ja.put(broucherName);
					ja.put(PriceDetail);
					ja.put(lvrStblobj.toString());
					array.put(ja);
					lvrStblobj = null;
					PriceDetail = null;imagetag = null;
					lvrSno++;					
				}			
			 	totalAfterFilter =  json_data.getInt("countAfterFilter");
	      } catch (Exception ex) {
	    	  System.out.println("Exception Found in "+getClass().getSimpleName()+".class : " + ex);
	      } finally {
	    	   ar = null; ja = null; lvrStblobj = null; jsonList = null; dataJson = null; 
	      }	     
	      result.put("sEcho", echo);
	      result.put("iTotalRecords", total);
	      result.put("iTotalDisplayRecords", totalAfterFilter);
	      result.put("aaData", array);
	      getResponse();	        	     
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Exception Found in "+getClass().getSimpleName()+".class : "+ex);
		} finally {

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

	public String getCmpyProfileImage() {
		return CmpyProfileImage;
	}

	public void setCmpyProfileImage(String CmpyProfileImage) {
		this.CmpyProfileImage = CmpyProfileImage;
	}

	public String getSearchfield() {
		return searchfield;
	}

	public void setSearchfield(String searchfield) {
		this.searchfield = searchfield;
	}

	public String getUsersearchname() {
		return usersearchname;
	}

	public void setUsersearchname(String usersearchname) {
		this.usersearchname = usersearchname;
	}

	public String getSdtSearchff() {
		return sdtSearchff;
	}

	public void setSdtSearchff(String sdtSearchff) {
		this.sdtSearchff = sdtSearchff;
	}

	public MerchantTblVO getMerchantobj() {
		return merchantobj;
	}

	public void setMerchantobj(MerchantTblVO merchantobj) {
		this.merchantobj = merchantobj;
	}

	public Integer getMrchntUniqId() {
		return mrchntUniqId;
	}

	public void setMrchntUniqId(Integer mrchntUniqId) {
		this.mrchntUniqId = mrchntUniqId;
	}

	
	
}