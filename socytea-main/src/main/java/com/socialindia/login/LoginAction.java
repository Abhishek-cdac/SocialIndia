package com.socialindia.login;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.common.CommonDaoService;
import com.letspay.common.CommonService;
import com.letspay.uam.persistense.MenuMasterTblVo;
import com.letspay.uam.persistense.TownshipMstTbl;
import com.letspay.utils.CommonUtils;
import com.letspay.utils.CommonUtilsDao;
import com.letspay.vo.AlertVo;
import com.letspay.vo.CityMasterTblVo;
import com.letspay.vo.CountryMasterTblVo;
import com.letspay.vo.IsdcodeMasterTblVo;
import com.letspay.vo.StateMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.persistense.UserMasterTblVo;

public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware, SessionAware{
  private static final long serialVersionUID = 1L;
  private Map<String, Object> sessionMap;
  private String submitform;
  private AlertVo alert = new AlertVo();
  private List<AlertVo> alertList = new ArrayList<AlertVo>();
  private UserMasterTblVo userInfo = new UserMasterTblVo(null, null, null,null, null, null,null,null, 0, null);
  private List<MenuMasterTblVo> rightsList = new ArrayList<MenuMasterTblVo>();
  private List<TownshipMstTbl> townshiplist = new ArrayList<TownshipMstTbl>();

  List<CountryMasterTblVo> cntrymstr = new ArrayList<CountryMasterTblVo>();
  List<StateMasterTblVo> statmstr = new ArrayList<StateMasterTblVo>();
  List<CityMasterTblVo> ctymstr = new ArrayList<CityMasterTblVo>();
  List<IsdcodeMasterTblVo> isdmstr = new ArrayList<IsdcodeMasterTblVo>();
  JSONObject obj = null;
  CommonService cmnser = null;
  private Common commonObj = null; 
  int countryidkey;
  private String townshiplogoname="";
  private int usersocietyid;
  private String townshipiconame="";
  private String townshipcolourcode="";
  private String  townshipimprintname="";
  int stateidkey;
  private String  remember="",lvrPaswrdchangeflg = null;
  private String resetLogin = "false";
  private int selectSocietyId;
  protected HttpServletRequest servletRequest;
  protected HttpServletResponse servletResponse;
  /**
   * Login.
   */
  @Override
  public String execute() {
	Commonutility.toWriteConsole("Step 1 : LoginAction Called.");
    CommonUtils common = null;
    String locSocImpName=null, locSocIcoImg=null,locSocLogoImg=null,locSocClrCode=null;
    JSONObject json_data = new JSONObject();
	JSONObject jsonuser = new JSONObject();
	JSONArray ar =null;
	JSONArray ar1 =null;
	JSONArray ar2 =null;
	int userId = 0;  
	int groupcode =0;
	String response=null;
    try {    	
    	obj = new JSONObject();    	
    	cmnser = new CommonDaoService();    	
		if(response==null || response.equalsIgnoreCase("null") || response.equalsIgnoreCase("")){				
			if(userInfo!=null){    				
				commonObj = new CommonDao();
				common=new CommonUtilsDao(); 
				String passww=userInfo.getPassword();
				Commonutility.toWriteConsole("Step 2 : Password : "+common.stringToMD5(userInfo.getPassword()));       	    	       	    	
				userInfo.setPassword(common.stringToMD5(userInfo.getPassword()));
				obj.put("servicecode", "SI0000");
				obj.put("userName", userInfo.getUserName());
				obj.put("password", userInfo.getPassword());
//				obj.put("remember", remember);
				if (remember.length() > 0 && remember.equalsIgnoreCase("yes")) {
	            Cookie userCookie = new Cookie("custloginid",userInfo.getUserName());
	            Cookie passCookie = new Cookie("password",passww);
	            userCookie.setMaxAge(60 * 60 * 4);
	            passCookie.setMaxAge(60 * 60 * 4);
	            servletResponse.addCookie(userCookie);
	            servletResponse.addCookie(passCookie);
	          } else {
						Cookie[] cookies = servletRequest.getCookies();
						if (cookies != null) {
							for (int i = 0; i < cookies.length; i++) {
								Cookie cookie = cookies[i];
								if (cookie.getName().equalsIgnoreCase("custloginid")&&cookie.getName().equalsIgnoreCase("password")) {
									cookie.setMaxAge(0);
									servletResponse.addCookie(cookie);
								}
							}
						}
					}
				
				obj.put("societyId", selectSocietyId);
				obj.put("flag", 1);	
				
				obj.put("resetLogin", resetLogin);
				
				String jsonTextFinal = obj.toString();	
				jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
				String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);				
    			
    			//start: login prevent
				String finalUrl= getText("login.user.prevent.action");
    			Commonutility.toWriteConsole("Step 3 : Service Url : "+finalUrl);
    			
    			response=commonObj.jsonRequest(finalUrl, temp);
    			Commonutility.toWriteConsole("Step 4 : Prevent Login Service Response :"+response);
    			
    			JSONObject json = new JSONObject(response);
    			json_data = json.getJSONObject("data");
    			
//        		String online = (String) Commonutility.toHasChkJsonRtnValObj(json_data,"online_status");
//        		String loggedOut = (String) Commonutility.toHasChkJsonRtnValObj(json_data,"logged_out");
    			
    			Integer numOfLogins = (Integer) Commonutility.toHasChkJsonRtnValObj(json_data,"numOfLogins");
    			Integer currentLogins = (Integer) Commonutility.toHasChkJsonRtnValObj(json_data,"currentLogins");
    			String resetDatetime = (String) Commonutility.toHasChkJsonRtnValObj(json_data,"resetDatetime");
    			sessionMap.put("resetDatetime", resetDatetime);
    			Commonutility.toWriteConsole("Step 4 : Prevent Login numOfLogins:"+numOfLogins + " currentLogins:"+currentLogins + " resetDatetime:"+resetDatetime);
        		
        		if(numOfLogins!=null && currentLogins!=null && resetLogin!=null && resetLogin.equalsIgnoreCase("false")){
	        		if(currentLogins >= numOfLogins){//
	        				alert.setCls("danger");
							alert.setMsg("Number Of Logins Exceeded");
							alertList.add(alert);
							return "input";
	        		}
        		}
        		
//        		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        		String _resetDatetime = sdfr.format( resetDatetime );
        		
//        		servletRequest.setAttribute("resetDatetime", resetDatetime);
        		
//        		ActionContext.getContext().put("resetDatetime", resetDatetime);
        		
        		//clear all sessions for reset
//        		if(resetLogin!=null && resetLogin.equalsIgnoreCase("true")){
//        			Map sessionMap = ActionContext.getContext().getSession();
//        			ServletContext context = ServletActionContext.getServletContext();
//        			SessionMap session = (SessionMap) ActionContext.getContext().getSession();
//        	   		sessionMap.clear();
//        	   		if (session != null) {
//        	   			session.invalidate();
//        	   		}
//        	   		if (context.getAttribute("RIGHTSLST") != null) {
//        	   			context.setAttribute("RIGHTSLST", null);
//        	   		}
//        	   		if (sessionMap.get("groupMasterList") != null) {
//        	   			ActionContext.getContext().getSession().put("groupMasterList", null);
//        	   		}
//        		}
        			
//        		if(online !=null && online.equalsIgnoreCase("0")){//if not logged in
//    				response = null;
//    			}
//        		else if(online !=null && online.equalsIgnoreCase("1")){//if logged in
//        			if(loggedOut!=null && loggedOut.equalsIgnoreCase("0")){//not properly logged out - browser closed / session expired
//        				response = null;
//        			}
//    			} 
//        		else if(online !=null && online.equalsIgnoreCase("1")){//if logged in
//        			if(loggedOut!=null && loggedOut.equalsIgnoreCase("1")){//logged out properly
//        				alert.setCls("danger");
//						alert.setMsg("Already logged in from different workstation or browser.");
//						alertList.add(alert);
//						return "input";
//        			}
//        		}
    			//end: login prevent
    			
    			finalUrl= getText("login.user.check.action");
        		Commonutility.toWriteConsole("Step 3 : Service Url : "+finalUrl);
    			response=commonObj.jsonRequest(finalUrl, temp);
    			Commonutility.toWriteConsole("Step 4 : isUsrAlreadyLoggedIn Service Response :"+response);
			}else{				
				response =null;
			}
		}
		
    	if(response!=null && !response.equalsIgnoreCase("null") && !response.equalsIgnoreCase("")){
    		JSONObject json = new JSONObject(response);
    		String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json,"statuscode");
    		String message = (String) Commonutility.toHasChkJsonRtnValObj(json,"message");		
    		if(statusCode.equalsIgnoreCase("2")){
    			alert.setCls("danger");
    	  	  	alert.setMsg("You don't have the web access permission. Please try on mobile application.");
    	  	  	alertList.add(alert);
    	  	  	return "input";
    		}else{
    			
    		}    		
    		if (statusCode.equalsIgnoreCase("0000")) {
    			ActionContext.getContext().getSession().put("loginrsp", response);
    			json_data=json.getJSONObject("data");
    			ar = json_data.getJSONArray("menu");
    			ar1 = json_data.getJSONArray("country");
    			ar2 = json_data.getJSONArray("townshiptbl");		
    			JSONObject jsonList = new JSONObject();	
					if (ar != null) {
						for (int i = 0; i < ar.length(); i++) {
							jsonList = null;
							jsonList = ar.getJSONObject(i);
							String menuType = jsonList.getString("menuType");
							String rootDesc = jsonList.getString("rootDesc");
							String menuPath = jsonList.getString("menuPath");
							String menuDesc = jsonList.getString("menuDesc");
							int menuID = jsonList.getInt("menuID");
							String menuClass = "";
							if (jsonList.has("menuClass")) {
								menuClass = jsonList.getString("menuClass");
							} else {
								menuClass = "";
							}    				
							rightsList.add(new MenuMasterTblVo(menuID,menuDesc, menuPath, menuType, rootDesc,menuClass));
    			}
    		}
					if (ar1 != null) {
						for (int i = 0; i < ar1.length(); i++) {
		    				jsonList=null;
		    				jsonList=ar1.getJSONObject(i);
		    				int countryId=jsonList.getInt("countryID");
		    				String countryName=jsonList.getString("countryName");			
		    				cntrymstr.add(new CountryMasterTblVo(countryId,countryName));
						}
					}
					if (ar2 != null) {
						for (int i = 0; i < ar2.length(); i++) {	
		    				jsonList=null;
		    				jsonList=ar2.getJSONObject(i);
		    				int townshipuniid=jsonList.getInt("townshipuniid");
		    				String townshipname=jsonList.getString("townshipname");			
		    				townshiplist.add(new TownshipMstTbl(townshipuniid,townshipname,null,null,null,null));
						}
					}		
    			ActionContext.getContext().getSession().put("RIGHTSLST", rightsList);
    			ActionContext.getContext().getSession().put("TOWNSHIPLIST", townshiplist);
    			ActionContext.getContext().getSession().put("countryList", cntrymstr);				
    			jsonuser=(JSONObject)Commonutility.toHasChkJsonRtnValObj(json_data, "townshipdetail");
				if (jsonuser != null && jsonuser.length() > 0) {    			 
					 	Object usoctiObj=(Object)Commonutility.toHasChkJsonRtnValObj(jsonuser,"usersocietyid");
					 	lvrPaswrdchangeflg = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"passwordchgflg");
						if(usoctiObj!=null && Commonutility.toCheckisNumeric(String.valueOf(usoctiObj))){
							usersocietyid = (Integer) usoctiObj;
							ActionContext.getContext().getSession().put("sSoctyId", usersocietyid);
							ActionContext.getContext().getSession().put("sSoctyIdStr", String.valueOf(usoctiObj));
							
							String usersocietyname = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "usersocietyname");
							ActionContext.getContext().getSession().put("usersocietyname",usersocietyname);
						}else{
							ActionContext.getContext().getSession().put("sSoctyId", -1);
							ActionContext.getContext().getSession().put("sSoctyIdStr", "-1");
						}
    			 
						Object usridObj = (Object) Commonutility.toHasChkJsonRtnValObj(jsonuser,"userId");
						
						if (usridObj!=null && Commonutility.toCheckisNumeric(String.valueOf(usridObj))) {
							userId=(Integer) usridObj;    				 
						}else{
							userId=0;
						} 
    			 
		    			 Object grpcdObj=(Object)Commonutility.toHasChkJsonRtnValObj(jsonuser,"groupcode");
		    			 if(grpcdObj!=null && Commonutility.toCheckisNumeric(String.valueOf(grpcdObj))){
		    				 groupcode=(Integer) grpcdObj;    				 
		    			 }else{
		    				 groupcode=0;
		    			 }  
    			 
		    			 Object twnshpiidObj=(Object)Commonutility.toHasChkJsonRtnValObj(jsonuser,"townshipid");
		    			 int townshipid=0;
		    			 if(twnshpiidObj!=null && Commonutility.toCheckisNumeric(String.valueOf(twnshpiidObj))){
		    				 townshipid = (Integer) twnshpiidObj;    	
		    				 ActionContext.getContext().getSession().put("townshipId", twnshpiidObj);
		    				 ActionContext.getContext().getSession().put("townshipIdStr", String.valueOf(twnshpiidObj));
		    			 }else{
		    				 townshipid=0;
		    				 ActionContext.getContext().getSession().put("townshipId", -1);
		    				 ActionContext.getContext().getSession().put("townshipIdStr", "-1");
		    			 }      			
    			     			 
    			 townshiplogoname= (String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"townshiplogoname");
    			 townshipiconame= (String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"townshipiconame");
    			 String profileimagename=(String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"profileimagename");
    			 townshipimprintname= (String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"townshipimprintname");
    			 String username=(String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"userName");  
    			 String firstname=(String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"firstname");  
    			 String lastname=(String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"lastname");    			     			     		
    			 String mobileno=(String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"mobileno");  
    			 townshipcolourcode =(String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"townshipcolourcode");
    			 
    			 locSocImpName = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "societyimpname"); 
    			 locSocIcoImg = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "societyicoimg");
    			 locSocLogoImg = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "societylogoimg");
    			 locSocClrCode = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "societycolorcode");
    			 
    			 String cyberplatesdval=(String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"cyberplatesd");  
    			 String cyberplatapval=(String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"cyberplatap");  
    			 String cyberplatopval=(String) Commonutility.toHasChkJsonRtnValObj(jsonuser,"cyberplatop"); 
    			 
    			 Commonutility.toWriteConsole("townshiplogoname : "+townshiplogoname);
    			 Commonutility.toWriteConsole("townshipiconame : "+townshipiconame);
    			 Commonutility.toWriteConsole("townshipcolourcode : "+townshipcolourcode);
    			 Commonutility.toWriteConsole("townshipid : "+townshipid);
    			 Commonutility.toWriteConsole("locSocImpName : "+locSocImpName);
    			 Commonutility.toWriteConsole("locSocIcoImg : "+locSocIcoImg);
    			 Commonutility.toWriteConsole("locSocLogoImg : "+locSocLogoImg);
    			 Commonutility.toWriteConsole("locSocClrCode : "+locSocClrCode);
    			 System.out.println(cyberplatesdval+"------"+cyberplatapval+"--------------"+cyberplatopval);
    			 ActionContext.getContext().getSession().put("cyberplatesd", cyberplatesdval);
    			 ActionContext.getContext().getSession().put("cyberplatap", cyberplatapval);
    			 ActionContext.getContext().getSession().put("cyberplatop", cyberplatopval);
    			 
    			ActionContext.getContext().getSession().put("townshiplogoname", townshiplogoname);
    			ActionContext.getContext().getSession().put("userProfileImage", profileimagename);
    			ActionContext.getContext().getSession().put("townshipimprintname", townshipimprintname);
    			ActionContext.getContext().getSession().put("userName", username);
    			ActionContext.getContext().getSession().put("FirstName", firstname);
    			ActionContext.getContext().getSession().put("LastName", lastname);
    			ActionContext.getContext().getSession().put("MobileNo", mobileno);
    			
    			//biometric data: start
    			String biohostname = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "biohostname");
				String biousername = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "biousername");
				String biopassword = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "biopassword");
				String biodatabase = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser,	"biodatabase");
				String bioport = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "bioport");
    			
				ActionContext.getContext().getSession().put("biohostname", biohostname);
				ActionContext.getContext().getSession().put("bioport", bioport);
				ActionContext.getContext().getSession().put("biodatabase", biodatabase);
				ActionContext.getContext().getSession().put("biousername", biousername);
				ActionContext.getContext().getSession().put("biopassword", biopassword);
    			//biometric data: end
				
				//GST data: start
    			String gstNum = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "gstNum");
				String minGstAmt = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "minGstAmt");
				String minAmt = (String) Commonutility.toHasChkJsonRtnValObj(jsonuser, "minAmt");
    			
				ActionContext.getContext().getSession().put("gstNum", gstNum);
				ActionContext.getContext().getSession().put("minGstAmt", minGstAmt);
				ActionContext.getContext().getSession().put("minAmt", minAmt);
    			//GST data: end
    			
    			
    			String dispName = "";
    			if(firstname!=null && !firstname.equalsIgnoreCase("null") && !firstname.equalsIgnoreCase("")){
    				dispName = firstname+" ";
    			} else{
    				dispName = "";
    			}
    			if(lastname!=null && !lastname.equalsIgnoreCase("null") && !lastname.equalsIgnoreCase("")){
    				dispName += lastname;
    			} 
    			    			
    			if(dispName==null || dispName.equalsIgnoreCase("") || dispName.equalsIgnoreCase("null")){
    				dispName = mobileno;
    			}
    			
    			ActionContext.getContext().getSession().put("sDisplyname", dispName);    			
    			ActionContext.getContext().getSession().put("townshipiconame", townshipiconame);
    			ActionContext.getContext().getSession().put("townshipcolourcode", townshipcolourcode);    			
    			ActionContext.getContext().getSession().put("sSoctyimpname", locSocImpName);
    			ActionContext.getContext().getSession().put("sSoctyIcoimg", locSocIcoImg);
    			ActionContext.getContext().getSession().put("sSoctyLogoimg", locSocLogoImg);
    			ActionContext.getContext().getSession().put("sSoctyColorcode", locSocClrCode);
    			
    		}else{
    			 ActionContext.getContext().getSession().put("cyberplatesd", "");
    			 ActionContext.getContext().getSession().put("cyberplatap", "");
    			 ActionContext.getContext().getSession().put("cyberplatop", "");
    			ActionContext.getContext().getSession().put("townshiplogoname", townshiplogoname);
    			ActionContext.getContext().getSession().put("townshipId", -1);
    			 ActionContext.getContext().getSession().put("townshipIdStr", "-1");
    			ActionContext.getContext().getSession().put("userProfileImage", "");
    			ActionContext.getContext().getSession().put("userName", "");
    			ActionContext.getContext().getSession().put("FirstName", "");
    			ActionContext.getContext().getSession().put("LastName", "");
    			ActionContext.getContext().getSession().put("MobileNo", "");
    			ActionContext.getContext().getSession().put("sDisplyname", "");
    			ActionContext.getContext().getSession().put("townshipiconame", townshipiconame);
    			ActionContext.getContext().getSession().put("townshipcolourcode", townshipcolourcode);
    			
    			ActionContext.getContext().getSession().put("sSoctyId", -1);
    			ActionContext.getContext().getSession().put("sSoctyIdStr", "-1");
    			ActionContext.getContext().getSession().put("sSoctyimpname", "");
    			ActionContext.getContext().getSession().put("sSoctyIcoimg", "");
    			ActionContext.getContext().getSession().put("sSoctyLogoimg", "");
    			ActionContext.getContext().getSession().put("sSoctyColorcode", "");
    		}		 		
    		ActionContext.getContext().getSession().put("USERID", userId);   
    		ActionContext.getContext().getSession().put("GROUPCODE", groupcode);     		
				} else if (statusCode.equalsIgnoreCase("E0001")) {
						alert.setCls("danger");
						alert.setMsg("Invalid Mobile No./Email Or Password");
						alertList.add(alert);
						return "input";
				} else {
					alert.setCls("danger");
					alert.setMsg("Please Contact Admin");
					alertList.add(alert);
					return "input";
				}
			} else {
				alert.setCls("danger");
				alert.setMsg("Please Try Again.");
				alertList.add(alert);
				return "input";
			}    	    	          
    } catch (Exception ex) {
      Commonutility.toWriteConsole("Step -1 : [Database Connection Not Found] : "+ex);
      alert.setCls("danger");
      alert.setMsg("Please Check Connection");
      alertList.add(alert);
      return "input";
    }finally{
    	 json_data =null;
		 jsonuser = null;
		 ar =null;
		 ar1 =null;
		 ar2 =null;
		 userId = 0;
    }
    if(lvrPaswrdchangeflg!=null && !lvrPaswrdchangeflg.equalsIgnoreCase("null") && lvrPaswrdchangeflg.equalsIgnoreCase("2")){
    	 return SUCCESS;
    } else {
    	 return "changepassword";
    }
    
   // return SUCCESS;
  }
  /**
   * encrypt.
    
   * @param .
             
   * @return String
   * @throws Exception .
  
   */
  public String signupmethod() {
    
    Commonutility.toWriteConsole("Enter signup");

   /* cntrymstr = cmnser.commonCountyData();
    Commonutility.toWriteConsole("        " + cntrymstr);
    ActionContext.getContext().getSession().put("countryList", cntrymstr);*/
    return SUCCESS;
  }
  
  /**
   * encrypt.
   * @param .
  
   * @return String
   * @throws Exception .
  
   */
  public String stateValueGetMethod() {
    
    
    Commonutility.toWriteConsole("Enter state data get <id---" + countryidkey);

    statmstr = cmnser.commonStateData(countryidkey);
    Commonutility.toWriteConsole("statelist---" + statmstr);
    ActionContext.getContext().getSession().put("stateList", statmstr);
    return SUCCESS;
  }
  
  /**
   * encrypt.
    
   * @param .
           
   * @return String
   * @throws Exception .
  
   */
  
  public String cityValueGetMethod() {  
    Commonutility.toWriteConsole("Enter city data get <id---" + stateidkey);
    ctymstr = cmnser.commonCityData(stateidkey);
    Commonutility.toWriteConsole("citylist---" + ctymstr);
    ActionContext.getContext().getSession().put("cityList", ctymstr);
    return SUCCESS;
  }
  /**
   * encrypt.
    
   * @param .
           
   * @return String.
   * @throws Exception .
  
   */
  public String isdValueGetMethod() {
   
    Commonutility.toWriteConsole("Enter isd data get <country id---" + countryidkey);

    isdmstr = cmnser.commonIsdCodeData(countryidkey);
    Commonutility.toWriteConsole("citylist---" + isdmstr);
    ActionContext.getContext().getSession().put("isdList", isdmstr);
    return SUCCESS;
  }
  
  public String profile(){
	  return SUCCESS;
  }

  public String getSubmitform() {
    return submitform;
  }

  public void setSubmitform(String submitform) {
    this.submitform = submitform;
  }

  public AlertVo getAlert() {
    return alert;
  }

  public void setAlert(AlertVo alert) {
    this.alert = alert;
  }

  public List<AlertVo> getAlertList() {
    return alertList;
  }

  public void setAlertList(List<AlertVo> alertList) {
    this.alertList = alertList;
  }

  public UserMasterTblVo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserMasterTblVo userInfo) {
    this.userInfo = userInfo;
  }

 

  public List<MenuMasterTblVo> getRightsList() {
	return rightsList;
}
public void setRightsList(List<MenuMasterTblVo> rightsList) {
	this.rightsList = rightsList;
}
public int getCountryidkey() {
    return countryidkey;
  }

  public void setCountryidkey(int countryidkey) {
    this.countryidkey = countryidkey;
  }

  public int getStateidkey() {
    return stateidkey;
  }

  public void setStateidkey(int stateidkey) {
    this.stateidkey = stateidkey;
  }
public List<CityMasterTblVo> getCtymstr() {
	return ctymstr;
}
public void setCtymstr(List<CityMasterTblVo> ctymstr) {
	this.ctymstr = ctymstr;
}
public String getTownshiplogoname() {
	return townshiplogoname;
}
public void setTownshiplogoname(String townshiplogoname) {
	this.townshiplogoname = townshiplogoname;
}

public String getTownshipiconame() {
	return townshipiconame;
}
public void setTownshipiconame(String townshipiconame) {
	this.townshipiconame = townshipiconame;
}
public String getTownshipcolourcode() {
	return townshipcolourcode;
}
public void setTownshipcolourcode(String townshipcolourcode) {
	this.townshipcolourcode = townshipcolourcode;
}
public int getUsersocietyid() {
	return usersocietyid;
}
public void setUsersocietyid(int usersocietyid) {
	this.usersocietyid = usersocietyid;
}
public String getTownshipimprintname() {
	return townshipimprintname;
}
public void setTownshipimprintname(String townshipimprintname) {
	this.townshipimprintname = townshipimprintname;
}
public int getSelectSocietyId() {
	return selectSocietyId;
}
public void setSelectSocietyId(int selectSocietyId) {
	this.selectSocietyId = selectSocietyId;
}
public String getRemember() {
	return remember;
}
public void setRemember(String remember) {
	this.remember = remember;
}
@Override
public void setServletResponse(HttpServletResponse arg0) {
	// TODO Auto-generated method stub
	this.servletResponse = arg0;
}
@Override
public void setServletRequest(HttpServletRequest arg0) {
	// TODO Auto-generated method stub
	this.servletRequest = arg0;
}
public String getResetLogin() {
	return resetLogin;
}
public void setResetLogin(String resetLogin) {
	this.resetLogin = resetLogin;
}
@Override
public void setSession(Map<String, Object> sessionMap) {
	this.sessionMap = sessionMap;
	
}

}
