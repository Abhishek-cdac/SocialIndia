package com.socialindia.uam;

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
import com.letspay.login.persistense.UserMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;

/**
 * For merchant user data table.
 */
public class userMasterTable extends ActionSupport implements
    SessionAware, ServletRequestAware, ServletResponseAware,
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
	List<UserMasterTblVo> userList = new ArrayList<UserMasterTblVo>();
	JSONObject obj = new JSONObject();
	private Common commonObj = new CommonDao();
	private String searchval;
	private String searchname;
	private String slectfield;
	private String society;
	private String townshipid;

  /**
   * For merchant user records showing on data table.
   */
  public String execute() {
    try {        
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
					Commonutility.toWriteConsole(dir);
				}
			}
			int total = 0;
			int totalAfterFilter = 0;
			JSONArray ar = null;
			JSONArray ja = null;
			String action = "";
			String userGroupName = "";
			String statusflg = "";
			int lvrSno = 1;
			Map sessionMap = ActionContext.getContext().getSession();
			String lcsocitid = String.valueOf(sessionMap.get("sSoctyId"));
      
			try {
				obj.put("servicecode", "SI0006");
				JSONObject jbb = new JSONObject();
				jbb.put("count1", "yes");
				jbb.put("countF1", "yes");
				jbb.put("startrow", String.valueOf(start));// starting row
				jbb.put("totalrow", String.valueOf(amount));// overalltotal row
				if(society==null ||society.equalsIgnoreCase("")){
					jbb.put("society", lcsocitid);
					jbb.put("searchflg", "");
				}else{
					jbb.put("society", society);
					jbb.put("searchflg",searchval);
				}
          
				if (slectfield == null || slectfield.equalsIgnoreCase("")) {
					jbb.put("slectfield", "");
				} else {
					jbb.put("slectfield", slectfield);
				}
          
				if (searchname == null || searchname.equalsIgnoreCase("")) {
					jbb.put("searchname", "");
				} else {
					jbb.put("searchname", searchname);
					jbb.put("searchflg", searchval);
				}
				if (slectfield != null && society.equalsIgnoreCase("")) {

				} else {

				}
          		jbb.put("srchdtsrch", sdtSearch);// overalltotal row          		
				obj.put("data", jbb);
				String jsonTextFinal = obj.toString();
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("usermanagement.view.url");
				String response = commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("userMgmt");
				JSONObject jsonList = null;
				String lvrGrpcodestr = null;
				for (int i = 0; i < ar.length(); i++) {
					jsonList=null;
					jsonList = ar.getJSONObject(i);				
					String userName =jsonList.getString("fname");
					String lastName =jsonList.getString("lastName");
					String mobileNo = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "mobileNo");
					String emailId = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "emailId");
					String lvrSocietyname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "societyname");
					String lvrTownshipname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "townshipname");
					int userid=jsonList.getInt("userId");
					
					String groupname=jsonList.getString("groupName");
					
					int access=jsonList.getInt("accessMedia");		
					lvrGrpcodestr = jsonList.getString("groupidstring");
					userList.add(new UserMasterTblVo(userName,userName,userName,lastName,userName,mobileNo,mobileNo, emailId,userid,groupname, groupname, groupname, groupname, access, lvrGrpcodestr));
								
		            ja = new JSONArray();
		            if(lvrGrpcodestr!=null && lvrGrpcodestr.equalsIgnoreCase("1")) {//super admin
		            	userGroupName = groupname;
		            	action+="<div>[Super Admin User]</div>";            	
		            } else {
		            	if(!groupname.equalsIgnoreCase("")&&!groupname.equalsIgnoreCase("null") && groupname!=null){
		            		userGroupName= "<div class=\"grptypchange\"><div class=\"form-group\"><div id=\"lableblockdivid_" +userid+"\"><div id=\"labelGrpTyid_"+userid+"\" class=\"labledivGrp left\" style=\"\">"+groupname+"</div>"
		            				+ "<div id=\"editiconid_" +userid+"\" style=\"margin:1px 0 0 10px;cursor: pointer;\" class=\"\"   title=\"\" onclick=\"editGroup(" + userid
	                 				+ ");\"><i class=\"tooltipped mdi-editor-mode-edit tinysmallsmall blue-text text-accent-4\" "
	                 				+ "data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"right\"></i></div></div><div id=\"editblockdivid_"+userid+"\" style=\"display:none;\"><div id=\"selectboxdiv" + userid+"\" style=\"float:left;\"></div><div id=\"saveiconGrpTyid_"+userid+"\" "
	                 				+ "class=\"\" title=\"\" style=\"cursor: pointer;float: left;\" onclick=\"saveEditGrptyp("+userid+")\"><i class=\"mdi-content-save tinysmall tooltipped green-text text-accent-4\" data-tooltip=\"Save\" data-delay=\"10\" data-position=\"right\"></i></div>"
	                 				+ "<div id=\"canceliconGrpTypid_" +userid+"\" class=\"cursor: pointer;\" title=\"\" style=\"cursor: pointer;\""
	                 				+ " onclick=\"calcelEditGrptyp("+userid+")\"><i class=\"mdi-navigation-close tinysmall tooltipped red-text text-accent-4\" data-tooltip=\"Cancel\" data-delay=\"10\" data-position=\"right\"></i></div></div></div></div>";
		            	}else{
		            		userGroupName= "<div class=\"grptypchange\"><div class=\"form-group\"><div id=\"lableblockdivid_" +userid+"\"><div id=\"labelGrpTyid_"+userid+"\" class=\"labledivGrp left\">Select Group</div>"
		            				+ "<div id=\"editiconid_" +userid+"\" style=\"margin:1px 0 0 10px;cursor: pointer;\" class=\"\"  title=\"\" onclick=\"editGroup(" + userid
		            				+ ");\"><i class=\"tooltipped mdi-editor-mode-edit tinysmallsmall blue-text text-accent-4\" "
		            				+ "data-tooltip=\"Save\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\"></i></div></div><div id=\"editblockdivid_"+userid+"\" style=\"display:none;\"><div id=\"selectboxdiv" + userid+"\" style=\"float:left;\">"
		            				+"</div><div id=\"saveiconGrpTyid_"+userid+"\" "
		            				+ "class=\"\" title=\"\" style=\"cursor: pointer;float: left;\" onclick=\"saveEditGrptyp("+userid+")\"><i class=\"mdi-content-save tinysmall green-text text-accent-4\"></i></div>"
		            				+ "<div id=\"canceliconGrpTypid_"+userid+"\" class=\"cursor: pointer;\" title=\"\" style=\"cursor: pointer;\""
		            				+ " onclick=\"calcelEditGrptyp("+userid+")\"><i class=\"mdi-navigation-close tinysmall tooltipped red-text text-accent-4\"  data-tooltip=\"Cancel\" data-delay=\"10\" data-position=\"right\"></i></div></div></div></div>";
						}
	            	 
						if (lvrGrpcodestr!=null && lvrGrpcodestr.equalsIgnoreCase("2")){
		            		  action+="<div>[Social India Admin]</div>"; 
						} else {
							action  += "<a   title=\"\" style=\"margin-right:15px;cursor: pointer;\" role=\"\" onclick=\"edituser('"
									+ userid + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" "
									+ "data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>";
							action  += "<a style=\"cursor: pointer;\" role=\"button\" "
									+ " title=\"\" onclick=\"deleteuser('" + userid
									+ "');\"><i class=\"tooltipped "+getText("button.color.delete")+"\" "
									+ "data-tooltip=\"Delete\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"right\"></i>"
									+ "</a>";
	            	 }
	            }                      
	            
					if (userName == null) {
						userName = "";
					}
					if (emailId == null) {
						emailId = "";
					}
					if (mobileNo == null) {
						mobileNo = "";
					}
					if (userGroupName == null) {
						userGroupName = "";
					}
					if (statusflg == null) {
						statusflg = "";
					}
					String channel = "";
					if (access == 1) {
						channel = "Web";
					} else if (access == 2) {
						channel = "Mobile";
					} else {
						channel = "Both";
					}
					ja.put(lvrSno);
					ja.put(userName + " " + lastName);
					ja.put(mobileNo);
					ja.put(emailId);	
					ja.put(lvrTownshipname);
					ja.put(lvrSocietyname);					
					ja.put(userGroupName);
					ja.put(channel);
					ja.put(action);
					array.put(ja);
					action = "";
					lvrSno++;		 		
				}
		 		totalAfterFilter =  json_data.getInt("countAfterFilter");       
        } catch (Exception ex) {         
          Commonutility.toWriteConsole("Exception Found in userMasterTable.class 1 [inner]: " + ex);
        } finally {
        	
        }
			result.put("sEcho", echo);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
			getResponse();
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Exception Found in userMasterTable.class 2 [outer] : " + ex);
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

public String getTownshipid() {
	return townshipid;
}

public void setTownshipid(String townshipid) {
	this.townshipid = townshipid;
}

public String getSearchval() {
	return searchval;
}

public void setSearchval(String searchval) {
	this.searchval = searchval;
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
