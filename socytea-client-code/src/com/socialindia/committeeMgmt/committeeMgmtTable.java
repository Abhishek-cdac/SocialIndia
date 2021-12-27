package com.socialindia.committeeMgmt;

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
import com.letspay.utils.CommonUtilsDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

/**
 * For merchant user data table.
 */
public class committeeMgmtTable extends ActionSupport implements
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
  private String searchWord;
  private String townShipId;
  private String societyId;
  private String slectfield;

  List<UserMasterTblVo> userList = new ArrayList<UserMasterTblVo>();
  JSONObject obj = new JSONObject();
  private Common commonObj = new CommonDao();
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
			String globalsearch = "";
			String userfilter = "";
			String dir = "Desc";          
			String where;
			where = userfilter;
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
				}
			}
			int total = 0;
			int totalAfterFilter = 0;
			JSONArray ar = null;
			String statusflg = "";
		    String action = "";
		    int lvrSno = 1;
		    JSONArray lvrtnJsonary = null;		
		    JSONObject json = null;
	  		JSONObject json_data = null;
			try {    				
				obj.put("servicecode", "SI0006");       
	          	JSONObject jbb=new JSONObject();       
	          	jbb.put("count1", "yes");
	          	jbb.put("countF1", "yes");  
	          	jbb.put("startrow", String.valueOf(start));// starting row
	          	jbb.put("totalrow", String.valueOf(amount));// overalltotal		
	          	jbb.put("status", "1");
	          	jbb.put("srchdtsrch", sdtSearch);// overalltotal row
	          	Map sessionMap = ActionContext.getContext().getSession();
	          	if(searchWord!=null){
	          		jbb.put("searchWord", searchWord);// search
	    			}else{
	    				jbb.put("searchWord", "");// search
	    			}if(townShipId!=null){
	    				jbb.put("townShipId", townShipId);
	    			}else{
	    				jbb.put("townShipId", "");
	    			}if(societyId!=null && !societyId.equalsIgnoreCase("")&& !societyId.equalsIgnoreCase("null")){
	    				jbb.put("sSoctyId", societyId);
	    			}else{
	    				jbb.put("sSoctyId", String.valueOf(sessionMap.get("sSoctyId")));
	    			}if(slectfield!=null){
	    				jbb.put("slectfield", slectfield);
	    			}else{
	    				jbb.put("slectfield", "");
	    			}
	          	obj.put("data", jbb);          
	          	String jsonTextFinal = obj.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
	          	String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
	  		  	String finalUrl = getText("committee.management.view.Action");  		
	  		  	String response=commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				//Commonutility.toWriteConsole("response  : "+response);
				json_data = json.getJSONObject("data");
				total = json_data.getInt("InitCount");
				ar = json_data.getJSONArray("userMgmt");
				JSONObject jsonList = new JSONObject();
				String mobileNo = null, emailId = null, firstname = null, lname= null, fullname = null, committeerole = null, townshipname = null, societyname = null;
				int accesschannel = 0;
				String lvrTwnshipid = null, lvrSocityid=null;
				//Commonutility.toWriteConsole("ar.length() : "+ar.length());
				for (int i = 0; i < ar.length(); i++) {
					jsonList=null;
					jsonList = ar.getJSONObject(i);				
					int userid = jsonList.getInt("userId");
					mobileNo = jsonList.getString("mobileNo");
					emailId = jsonList.getString("emailId");
					firstname = jsonList.getString("firstname");
					committeerole = jsonList.getString("committeerole");
					townshipname = jsonList.getString("townshipname");
					societyname = jsonList.getString("societyname");
					accesschannel = jsonList.getInt("accesschannel");	
					lname = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lastname");
					fullname = firstname;
					if(lname!= null && !lname.equalsIgnoreCase("") && !lname.equalsIgnoreCase("null")){
						fullname += " " + lname ;
					}
					if(fullname!= null && !fullname.equalsIgnoreCase("") && !fullname.equalsIgnoreCase("null")){
						fullname = new CommonUtilsDao().toFirstCharUpper(fullname);
					}
					lvrTwnshipid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "townshipid");;
					lvrSocityid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "societyid");;
					lvrtnJsonary = new JSONArray();   					
					action += "<a class=\"pointer\"  style=\"margin-right:15px;\"   onclick=\"viewcommittee('" + userid + "');\"><i class=\"tooltipped "+getText("button.color.view")+"\" "
		                     + "data-tooltip=\"View\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"left\"></i></a>";           
		            action += "<a class=\"pointer\"  role=\"\" style=\"margin-right:15px;\" onclick=\"editcommittee('"+ userid + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" "
		                     + "data-tooltip=\"Edit\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></a>";
		            action += "<a class=\"pointer\"   role=\"\" style=\"margin-right:15px;\" onclick=\"addresident('"+ lvrTwnshipid + "','" + townshipname + "','" + lvrSocityid + "','" + societyname + "','" + userid + "');\"><i class=\"tooltipped "+getText("button.color.addresident")+"\" "
		                     + "data-tooltip=\"Create Resident\" data-delay=\""+getText("material.tooltip.delay")+"\" data-position=\"bottom\"></i></span></a>";          		            
		           
					if (emailId == null) {
						emailId = "";
					}
					if (mobileNo == null) {
						mobileNo = "";
					}
					if (statusflg == null) {
						statusflg = "";
					}
					String channel = "";
					if (accesschannel == 1) {
						channel = "Web";
					} else if (accesschannel == 2) {
						channel = "Mobile";
					} else {
						channel = "Both";
					}
		            lvrtnJsonary.put(lvrSno);
		           // lvrtnJsonary.put(emailId);
		            lvrtnJsonary.put(mobileNo);
		            lvrtnJsonary.put(fullname);            		          
		            lvrtnJsonary.put(townshipname);            
		            lvrtnJsonary.put(societyname);
		            lvrtnJsonary.put(channel);            
		            lvrtnJsonary.put(action);
		            array.put(lvrtnJsonary);
		            action = "";
		            lvrSno++;	
		            lvrtnJsonary = null;
				}		
				totalAfterFilter = json_data.getInt("countAfterFilter");
			} catch (Exception ex) {
				System.out.println("Step -1 : Exception Found in committeeMgmtTable : " + ex);
			} finally {
				lvrtnJsonary = null;json = null;
		  		json_data = null;
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
        String userName = "",firstname="",committeerole="",townshipname="";
        String userEmailId = "",societyname="";
        String userMobno = "";
        String userGroupName = "";
      //  String statusflg = "";
      //  String action = "";
        int accesschannel;
        try {         
          UserMasterTblVo userObj;
          int sno = 0;
          for (Iterator<UserMasterTblVo> it = userList.iterator(); it.hasNext();) {
            userObj = it.next();
            sno++;         
            JSONArray ja;
            ja = new JSONArray();
            uniId = Integer.toString(userObj.getUserId());
            firstname = userObj.getFirstName();
            userMobno=userObj.getMobileNo();
            userEmailId = userObj.getEmailId();
            committeerole = userObj.getCommitteeRole();
            townshipname = userObj.getTownshipName();
            societyname = userObj.getSocietyName();
            accesschannel=userObj.getAccessChannel();
            
            action += "<a class=\"\" title=\"\" style=\"margin-right:15px;cursor: pointer;\"   onclick=\"viewcommittee('" + uniId + "');\"><i class=\"tooltipped "+getText("button.color.view")+"\" "
                     + "data-tooltip=\"View\" data-delay=\"10\" data-position=\"left\"></i></a>";           
            action += "<a class=\"\" title=\"\"  role=\"\" style=\"margin-right:15px;cursor: pointer;\" onclick=\"editcommittee('"+ uniId + "');\"><i class=\"tooltipped "+getText("button.color.edit")+"\" "
                     + "data-tooltip=\"Edit\" data-delay=\"10\" data-position=\"bottom\"></i></a>";
            action += "<a class=\"\" title=\"\"  role=\"\" style=\"margin-right:15px;cursor: pointer;\" onclick=\"addresident('"+ uniId + "');\"><i class=\"tooltipped "+getText("button.color.addsociety")+"\" "
                     + "data-tooltip=\"Add Society\" data-delay=\"10\" data-position=\"bottom\"></i></span></a>";          
            if (uniId == null) {
              uniId = "";
            }
            if (userName == null) {
              userName = "";
            }
            if (userEmailId == null) {
              userEmailId = "";
            }
            if (userMobno == null) {
              userMobno = "";
            }
            if (userGroupName == null) {
              userGroupName = "";
            }
            if (statusflg == null) {
              statusflg = "";
            }
            String channel="";
            if(accesschannel==1){
            	channel="Web";
            }else if(accesschannel==2){
            	channel="Mobile";
            }else{
            	channel="Both";
            }
            ja.put(sno);
            ja.put(userEmailId);
            ja.put(userMobno);
            ja.put(firstname);                     
            ja.put(townshipname);            
            ja.put(societyname);
            ja.put(channel);            
            ja.put(action);
            array.put(ja);
            action = "";
          }

        } catch (Exception ex) {
          System.out.println("Step -2 : Exception found in committeeMgmtTable.class : "+ex);
        }finally{
        	
        }      
       
      } catch (Exception ex) {
    	  System.out.println("Step -3 : Exception found in committeeMgmtTable.class : "+ex);
      } */ 

    } catch (Exception ex) {
    	System.out.println("Step -4 : Exception found in committeeMgmtTable.class : "+ex);
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

public String getSlectfield() {
	return slectfield;
}

public void setSlectfield(String slectfield) {
	this.slectfield = slectfield;
}
  

}
