package com.socialindia.socialmedia;

//import com.opensymphony.xwork2.ActionContext;
import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.uam.persistense.MenuMasterTblVo;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.login.EncDecrypt;
import com.socialindia.vo.SocialInfoTblVo;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SocialAction extends ActionSupport {

 // Log log = new Log();
  private static final long serialVersionUID = 1L;
private UserMasterTblVo custRegObj=new UserMasterTblVo(null, null, null, null, null, null, null, null, 1, null, null, null, null, 0,null);
private SocialInfoTblVo socInfoObj=new SocialInfoTblVo();
private String societyKey;
private String googleuserId;
private AlertVo alert = new AlertVo();
private List<AlertVo> alertList = new ArrayList<AlertVo>();
private List<MenuMasterTblVo> rightsList = new ArrayList<MenuMasterTblVo>();
  public static HttpServletResponse response;
  // public static HttpServletRequest request;
  HttpServletRequest request = ServletActionContext.getRequest();
  JSONObject obj = new JSONObject();
  JSONObject json = new JSONObject();
  private Common commonObj = new CommonDao();
  String fbuserId;
  /*String soctype;*/
  String uservalues;
  String pswrdchange;
  String mpinchange;
  String passwordsaved;
  public String mobileFlg;
  int custuid;
  
  private Integer socialuniqid;
  private String userid;
  private String email;
  private String name;
  private String picurl;
  private String userName;
  private String lastname;
  private String aboutme;
  private String gender;
  private Integer age;
  private String permtaddrid;
  private String permtaddr;
  private String permtcity;
  private String permtstate;
  private String permtcountry;
  private Integer permtzip;
  private String viewurl;
  private String pdfurl;
  private String skilllinkedin;
  private String skillname;
  private String certificationid;
  private String certificationname;
  private String specialities;
  private Integer socialtype;
  private String password;
  private String mobileNo;
  
  
  
public int getCustuid() {
	return custuid;
}

public void setCustuid(int custuid) {
	this.custuid = custuid;
}

int soctype;

  @Override
  public String execute() {
    System.out.println("Enter to login page");
    String ruleid = "100";
    return SUCCESS;
  }

  /**
   * New user via Social Media Registration.
   * @return Success flag
 * @throws JSONException 
   */
  public String socialuserReg() throws JSONException {
	  try{
	  System.out.println("custRegObj ===================== "+socInfoObj.getUserid());
	  System.out.println("custRegObj ========mob============= "+custRegObj.getMobileNo());
	  System.out.println("custRegObj ==========e22222222222222222mail=========== "+custRegObj.getEmailId());
	  System.out.println("custRegObj =========societyKey============ "+societyKey);
	  System.out.println("=======================custRegObj.getEmailId()=========="+custRegObj.getEmailId());
		obj.put("userid", socInfoObj.getUserid());
		obj.put("email", custRegObj.getEmailId());
		obj.put("name", socInfoObj.getName());
		obj.put("picurl", socInfoObj.getPicurl());
		obj.put("firstname", custRegObj.getUserName());
		obj.put("lastname", custRegObj.getLastName());
		obj.put("aboutme", socInfoObj.getAboutme());
		obj.put("gender", socInfoObj.getGender());
		obj.put("age", socInfoObj.getAge());
		obj.put("permtaddrid", socInfoObj.getPermtaddrid());
		obj.put("groupcode", "2");
		
		obj.put("permtaddr", socInfoObj.getPermtaddr());
		obj.put("permtcity", socInfoObj.getPermtcity());
		obj.put("permtstate", socInfoObj.getPermtstate());
		obj.put("permtcountry", socInfoObj.getPermtcountry());
		obj.put("permtzip", socInfoObj.getPermtzip());
		obj.put("pdfurl", socInfoObj.getPdfurl());
		obj.put("skilllinkedin", socInfoObj.getSkilllinkedin());
		obj.put("skillname", socInfoObj.getSkillname());
		obj.put("certificationid", socInfoObj.getCertificationid());
		obj.put("certificationname", socInfoObj.getCertificationname());
		obj.put("socialtype", socInfoObj.getSocialtype());
		obj.put("mobileNo", custRegObj.getMobileNo());
		obj.put("societyKey", societyKey);
		json.put("data", obj);
		json.put("servicecode", "SI0022");
		String jsonTextFinal = json.toString();
		  jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
		String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
		System.out.println("=====jsonText=====" + jsonTextFinal);
		String finalUrl = getText("login.facebook.insert.action");
		System.out.println("=======finalUrl====" + finalUrl);
		String response = commonObj.jsonRequest(finalUrl, temp);
		System.out.println("=========response===" + response);
		JSONObject json = new JSONObject(response);
		//JSONObject json_data = json.getJSONObject("data");
		String statusCode = json.getString("statuscode");
		if(statusCode.equalsIgnoreCase("0")){
			JSONObject json_data = new JSONObject();
			JSONArray ar =null;
			json_data=json.getJSONObject("data");
			ar = json_data.getJSONArray("menu");
			System.out.println("==============ar==="+ar.length());
			JSONObject jsonList = new JSONObject();
			
			for(int i=0;i<ar.length();i++)
			{	
				jsonList=null;
				jsonList=ar.getJSONObject(i);
				String menuType=jsonList.getString("menuType");
				String rootDesc=jsonList.getString("rootDesc");
				String menuPath=jsonList.getString("menuPath");
				String menuDesc=jsonList.getString("menuDesc");
				int menuID=jsonList.getInt("menuID");
				String menuClass = "";
				if(jsonList.has("menuClass")){
					menuClass = jsonList.getString("menuClass");
				}else{
					menuClass = "";
				} 
				rightsList.add(new MenuMasterTblVo(menuID,menuDesc, menuPath, menuType, rootDesc,menuClass));
			}
			System.out.println("============rightsList==="+rightsList.size());
			ActionContext.getContext().getSession().put("RIGHTSLST", rightsList);
			 return "success";
		}else if(statusCode.equalsIgnoreCase("2")){
			alert.setCls("danger");
			alert.setMsg(getText("Society Key Wrong"));
			alertList.add(alert);
			return "input";
		}
	  }catch(Exception ex){
		  System.out.println("====Exception=====socialuserReg======"+ex);
	  }
      return "input";
  }

  /**
   * Verify Social Media User.
   * @return SUCCESS flag
 * @throws Exception 
   */
  public String socialuserVerify() throws Exception {
    String randompwd = "";

    boolean result = false;
   // System.out.println("Test ===================================="+custRegObj.getUserName());
	  System.out.println("firstname ========"+custRegObj.getEmailId());
	  System.out.println("firstname ========"+custRegObj.getMobileNo());
	  System.out.println("email================= "+socInfoObj.getGender());
	  System.out.println("custRegObj ===================== "+socInfoObj.getUserid());
	  System.out.println("custRegObj.getMobileNo() ===================== "+custRegObj.getUserName());
	  System.out.println("socInfoObj.getName() ===================== "+custRegObj.getLastName());
		obj.put("servicecode", "SI0020");
	  	obj.put("userid", socInfoObj.getUserid());
		obj.put("email", socInfoObj.getEmail());
		obj.put("name", socInfoObj.getName());
		obj.put("picurl", socInfoObj.getPicurl());
		obj.put("firstname", custRegObj.getUserName());
		obj.put("lastname", custRegObj.getLastName());
		obj.put("aboutme", socInfoObj.getAboutme());
		obj.put("gender", socInfoObj.getGender());
		obj.put("age", socInfoObj.getAge());
		obj.put("permtaddrid", socInfoObj.getPermtaddrid());
		
		obj.put("permtaddr", socInfoObj.getPermtaddr());
		obj.put("permtcity", socInfoObj.getPermtcity());
		obj.put("permtstate", socInfoObj.getPermtstate());
		obj.put("permtcountry", socInfoObj.getPermtcountry());
		obj.put("permtzip", socInfoObj.getPermtzip());
		obj.put("pdfurl", socInfoObj.getPdfurl());
		obj.put("skilllinkedin", socInfoObj.getSkilllinkedin());
		obj.put("skillname", socInfoObj.getSkillname());
		obj.put("certificationid", socInfoObj.getCertificationid());
		obj.put("certificationname", socInfoObj.getCertificationname());
		obj.put("socialtype", socInfoObj.getSocialtype());
		obj.put("emailid", custRegObj.getEmailId());
		obj.put("mobileNo", custRegObj.getMobileNo());
		obj.put("password", custRegObj.getPassword());
		json.put("data", obj);
		String jsonTextFinal = json.toString();
		  jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
		String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
		System.out.println("=====jsonText==twitter===" + jsonTextFinal);
		String finalUrl = getText("login.twitter.login.insert.action");
		System.out.println("=======finalUrl====" + finalUrl);
		String response = commonObj.jsonRequest(finalUrl, temp);
		System.out.println("=========response===" + response);
		JSONObject json = new JSONObject(response);
		//JSONObject json_data = json.getJSONObject("data");
		String statusCode = json.getString("statuscode");
		
		if(statusCode.equalsIgnoreCase("0")){
			JSONObject json_data = new JSONObject();
			JSONArray ar =null;
			json_data=json.getJSONObject("data");
			ar = json_data.getJSONArray("menu");
			System.out.println("==============ar==="+ar.length());
			JSONObject jsonList = new JSONObject();
			
			for(int i=0;i<ar.length();i++)
			{	
				jsonList=null;
				jsonList=ar.getJSONObject(i);
				String menuType=jsonList.getString("menuType");
				String rootDesc=jsonList.getString("rootDesc");
				String menuPath=jsonList.getString("menuPath");
				String menuDesc=jsonList.getString("menuDesc");
				int menuID=jsonList.getInt("menuID");
				String menuClass = "";
				if(jsonList.has("menuClass")){
					menuClass = jsonList.getString("menuClass");
				}else{
					menuClass = "";
				} 
				System.out.println("====menuType==="+menuType);
				rightsList.add(new MenuMasterTblVo(menuID,menuDesc, menuPath, menuType, rootDesc,menuClass));
			}
			System.out.println("============rightsList==="+rightsList.size());
			ActionContext.getContext().getSession().put("RIGHTSLST", rightsList);
			return "success";
		}else{
			return "input";
		}
 }

  /**
   * To check Social media User Exist or Not.
   * @return SUCCESS flag
 * @throws JSONException 
   */
  public String socialuserExist() {
	  String result="";
	  try{
		
	  System.out.println("socInfoObj.getSocialtype() ======================= "+fbuserId);
	  System.out.println("socInfoObj.getSocialtype() ======================= "+soctype); 	  
	 System.out.println("==================googleuserId============="+googleuserId);
	if(soctype==1){
	 obj.put("socialtype", soctype);
	  obj.put("fbuserid", fbuserId);
	}else{
		 obj.put("socialtype", soctype);
		 obj.put("googleuserId", googleuserId);
	}
	  json.put("data", obj);
	  json.put("servicecode", "SI0021");
	  System.out.println("==f=gd=gdfg===");
	  String jsonTextFinal = json.toString();
	  System.out.println("=========jsonTextFinal===="+jsonTextFinal);
	  jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
		String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
		System.out.println("=====jsonText=====" + jsonTextFinal);
		String finalUrl = getText("login.facebook.check.action");
		System.out.println("=======finalUrl====" + finalUrl);
		String response = commonObj.jsonRequest(finalUrl, temp);
		System.out.println("=========response===" + response);
		JSONObject json = new JSONObject(response);
		//JSONObject json_data = json.getJSONObject("data");
		String statusCode = json.getString("statuscode");
		System.out.println("============statusCode========================="+statusCode);
		
		if(statusCode.equalsIgnoreCase("0")){
			JSONObject json_data = new JSONObject();
			JSONArray ar =null;
			json_data=json.getJSONObject("data");
			ar = json_data.getJSONArray("menu");
			System.out.println("==============ar==="+ar.length());
			JSONObject jsonList = new JSONObject();
			
			for(int i=0;i<ar.length();i++)
			{	
				jsonList=null;
				jsonList=ar.getJSONObject(i);
				String menuType=jsonList.getString("menuType");
				String rootDesc=jsonList.getString("rootDesc");
				String menuPath=jsonList.getString("menuPath");
				String menuDesc=jsonList.getString("menuDesc");
				int menuID=jsonList.getInt("menuID");
				String menuClass = "";
				if(jsonList.has("menuClass")){
					menuClass = jsonList.getString("menuClass");
				}else{
					menuClass = "";
				} 
				rightsList.add(new MenuMasterTblVo(menuID,menuDesc, menuPath, menuType, rootDesc, menuClass));
			}
			System.out.println("============rightsList==="+rightsList.size());
			ActionContext.getContext().getSession().put("RIGHTSLST", rightsList);
			result="success";
		}else{
			result="input";
			System.out.println("===========input==============");
		}
	  }catch(Exception ex){
		  System.out.println("======socialuserExist========"+ex);
	  }
	  
	 return result;
  }

	
/**
   * Social media User Signin.
   * @return Success flag
   */
  public String socialUsersignin() {
    try {
      System.out.println("Enter to after login page thru social login");

      String ruleid = "100";
        String emailregex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String phregex = "/[0-9]/";
        
      Date nxtpswrdchangeLgn = null;
      Date nxtmpinchangeLgn = null;
      String mobilenoLgn = "";
      String emailidLgn = "";
      String strnxtpswrdchangeLgn =  null;
      String strnxtmpinchangeLgn = null;
      
      
      System.out.println("nxtpswrdchangeLgn11---" + nxtpswrdchangeLgn);
      System.out.println("nxtmpinchangeLgn22---" + nxtmpinchangeLgn);
    //  System.out.println("isvalidatedemailLgn---" + isvalidatedemailLgn);
    //  System.out.println("isvalidatedmobileLgn---" + isvalidatedmobileLgn);
      System.out.println("mobilenoLgn---" + mobilenoLgn);
      System.out.println("emailidLgn---" + emailidLgn);

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      
      System.out.println("nxtpswrdchangeLgn-----------------------------" + nxtpswrdchangeLgn);
      System.out.println("nxtmpinchangeLgn-----------------------------" + nxtmpinchangeLgn);

      
      System.out.println("111111111111");
      
              System.out.println("password - after");
              pswrdchange = "change";
	    } catch (Exception ex) {
	     // log.logMessage("Generate OTP exception--->" + ex, "error", LoginAction.class);
	     // return false;
	    }
	   return "success";
  }
  
  public boolean optgeneratemethod() {
  //  CommonUtils common1 = new CommonUtilsDao();
    boolean rslt = false;
    String getOtpValue = "";
    try {
   
          System.out.println("generate random otp value----" + getOtpValue);
          if (getOtpValue != "" && getOtpValue.length() > 0) {
       
            Calendar cal = Calendar.getInstance();
         
            return rslt;
          }
     

    } catch (Exception ex) {
    //  log.logMessage("Generate OTP exception--->" + ex, "error", LoginAction.class);
      return false;
    }
    return true;
  }

  public SocialAction() {

  }

/*  public int getSoctype() {
    return soctype;
  }

  public void setSoctype(int soctype) {
    this.soctype = soctype;
  }*/

  public String getUservalues() {
    return uservalues;
  }

  public void setUservalues(String uservalues) {
    this.uservalues = uservalues;
  }

  public String getPswrdchange() {
    return pswrdchange;
  }

  public void setPswrdchange(String pswrdchange) {
    this.pswrdchange = pswrdchange;
  }

  public String getMpinchange() {
    return mpinchange;
  }

  public void setMpinchange(String mpinchange) {
    this.mpinchange = mpinchange;
  }

  public String getPasswordsaved() {
    return passwordsaved;
  }

  public void setPasswordsaved(String passwordsaved) {
    this.passwordsaved = passwordsaved;
  }

public String getMobileFlg() {
	return mobileFlg;
}

public void setMobileFlg(String mobileFlg) {
	this.mobileFlg = mobileFlg;
}


/*public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}
*/
/*public SocialInfoTblVo getCustRegObj() {
	return custRegObj;
}

public void setCustRegObj(SocialInfoTblVo custRegObj) {
	this.custRegObj = custRegObj;
}*/

public Integer getSocialuniqid() {
	return socialuniqid;
}

public void setSocialuniqid(Integer socialuniqid) {
	this.socialuniqid = socialuniqid;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPicurl() {
	return picurl;
}

public void setPicurl(String picurl) {
	this.picurl = picurl;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getAboutme() {
	return aboutme;
}

public void setAboutme(String aboutme) {
	this.aboutme = aboutme;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public Integer getAge() {
	return age;
}

public void setAge(Integer age) {
	this.age = age;
}

public String getPermtaddrid() {
	return permtaddrid;
}

public void setPermtaddrid(String permtaddrid) {
	this.permtaddrid = permtaddrid;
}

public String getPermtaddr() {
	return permtaddr;
}

public void setPermtaddr(String permtaddr) {
	this.permtaddr = permtaddr;
}

public String getPermtcity() {
	return permtcity;
}

public void setPermtcity(String permtcity) {
	this.permtcity = permtcity;
}

public String getPermtstate() {
	return permtstate;
}

public void setPermtstate(String permtstate) {
	this.permtstate = permtstate;
}

public String getPermtcountry() {
	return permtcountry;
}

public void setPermtcountry(String permtcountry) {
	this.permtcountry = permtcountry;
}

public Integer getPermtzip() {
	return permtzip;
}

public void setPermtzip(Integer permtzip) {
	this.permtzip = permtzip;
}

public String getViewurl() {
	return viewurl;
}

public void setViewurl(String viewurl) {
	this.viewurl = viewurl;
}

public String getPdfurl() {
	return pdfurl;
}

public void setPdfurl(String pdfurl) {
	this.pdfurl = pdfurl;
}

public String getSkilllinkedin() {
	return skilllinkedin;
}

public void setSkilllinkedin(String skilllinkedin) {
	this.skilllinkedin = skilllinkedin;
}

public String getSkillname() {
	return skillname;
}

public void setSkillname(String skillname) {
	this.skillname = skillname;
}

public String getSpecialities() {
	return specialities;
}

public void setSpecialities(String specialities) {
	this.specialities = specialities;
}

public Integer getSocialtype() {
	return socialtype;
}

public void setSocialtype(Integer socialtype) {
	this.socialtype = socialtype;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public SocialInfoTblVo getSocInfoObj() {
	return socInfoObj;
}

public void setSocInfoObj(SocialInfoTblVo socInfoObj) {
	this.socInfoObj = socInfoObj;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getMobileNo() {
	return mobileNo;
}

public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}

public UserMasterTblVo getCustRegObj() {
	return custRegObj;
}

public void setCustRegObj(UserMasterTblVo custRegObj) {
	this.custRegObj = custRegObj;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getFbuserId() {
	return fbuserId;
}

public void setFbuserId(String fbuserId) {
	this.fbuserId = fbuserId;
}

public int getSoctype() {
	return soctype;
}

public void setSoctype(int soctype) {
	this.soctype = soctype;
}

public static String jsonRequest(String URL, String data) {
	StringBuffer response = null;	
	try {
		URL getpseudo = new URL(URL);
		HttpURLConnection con = (HttpURLConnection) getpseudo
				.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setDoInput(true);
		OutputStream locvOutStrmObj = null;
		locvOutStrmObj = con.getOutputStream();
		locvOutStrmObj.write(data.getBytes());
		locvOutStrmObj.close();
		locvOutStrmObj = null;
		int responsecode = con.getResponseCode();
		BufferedReader bfreader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		response = new StringBuffer();
		String ipline;
		while ((ipline = bfreader.readLine()) != null) {
			response.append(ipline);
		}
		bfreader.close();		

	} catch (Exception ex) {
		System.out.println("Exception Found SocialAction.jsonRequest() : " + ex);
	}
	return String.valueOf(response);

}

public String getSocietyKey() {
	return societyKey;
}

public void setSocietyKey(String societyKey) {
	this.societyKey = societyKey;
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

public String getGoogleuserId() {
	return googleuserId;
}

public void setGoogleuserId(String googleuserId) {
	this.googleuserId = googleuserId;
}


}
