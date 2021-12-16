package com.socialindia.socialmedia;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.MenuMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.login.EncDecrypt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Gettweetval extends ActionSupport {
  String twtid = null;
  String screenname = null;
  String name = null;
  String profileimg = null;
  String profileurl = null;
  String profiledesc = null;
  String tweetstatus = null;
  private List<MenuMasterTblVo> rightsList = new ArrayList<MenuMasterTblVo>();

  private Common commonObj = new CommonDao();
  JSONObject obj = new JSONObject();
  JSONObject data = new JSONObject();
  
public String getTweetstatus() {
	return tweetstatus;
}

public void setTweetstatus(String tweetstatus) {
	this.tweetstatus = tweetstatus;
}

public String getTwtid() {
    return twtid;
  }

  public void setTwtid(String twtid) {
    this.twtid = twtid;
  }

  public String getScreenname() {
    return screenname;
  }

  public void setScreenname(String screenname) {
    this.screenname = screenname;
  }

  public String getProfileimg() {
    return profileimg;
  }

  public void setProfileimg(String profileimg) {
    this.profileimg = profileimg;
  }

  public String getProfileurl() {
    return profileurl;
  }

  public void setProfileurl(String profileurl) {
    this.profileurl = profileurl;
  }

  public String getProfiledesc() {
    return profiledesc;
  }

  public void setProfiledesc(String profiledesc) {
    this.profiledesc = profiledesc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  

  @Override
  public String execute() throws Exception {

/*    System.out.println("id=========" + Twitterlogin.getMstrMrchtId());
    System.out.println("getMstr_mrcht_dbname=========" + Twitterlogin.getMstrMrchtDbname());
    System.out.println("getMstr_mrcht_dbpswd=========" + Twitterlogin.getMstrMrchtDbpswd());
    System.out.println("getMstr_mrcht_dbusr=========" + Twitterlogin.getMstrMrchtDbusr());
    System.out.println("getMstr_mrcht_name=========" + Twitterlogin.getMstrMrchtName());
    System.out.println("getMstr_mrcht_regid=========" + Twitterlogin.getMstrMrchtRegid());
    System.out.println("getMstr_mrcht_url=========" + Twitterlogin.getMstrMrchtUrl());
    Map session = ActionContext.getContext().getSession();
    session.put("MASTER_MERCHANT_ID", Twitterlogin.getMstrMrchtId());
    session.put("MASTER_MERCHANT_DBNAME", Twitterlogin.getMstrMrchtDbname());
    session.put("MASTER_MERCHANT_DB_USERNAME", Twitterlogin.getMstrMrchtDbusr());
    session.put("MASTER_MERCHANT_DB_PASSWORD", Twitterlogin.getMstrMrchtDbpswd());
    session.put("MASTER_MERCHANT_URL", Twitterlogin.getMstrMrchtUrl());
    session.put("MASTER_MERCHANT_FLAG", Twitterlogin.getMstrMrchtFlag());

    session.put("MASTER_MERCHANT_REG_ID", Twitterlogin.getMstrMrchtRegid());
    session.put("MASTER_MERCHANT_NAME", Twitterlogin.getMstrMrchtName());*/
    

    twtid = TwitterTokenRequest.getTwtid();
    System.out.println("twtid:::::::::::::::::=============== "+twtid);
    screenname = TwitterTokenRequest.getScreenname();
    name = TwitterTokenRequest.getName();
    profileimg = TwitterTokenRequest.getProfileimg();
    profileurl = TwitterTokenRequest.getProfileurl();
    profiledesc = TwitterTokenRequest.getProfiledesc();
    obj.put("servicecode", "SI0019");
    obj.put("twitteruserid", twtid);
    data.put("data", obj);
    String jsonTextFinal = data.toString();
     jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
	String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
	System.out.println("=====jsonText=====" + jsonTextFinal);
	
	String finalUrl = getText("login.twitter.check.action");
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
			System.out.println("====menuType==="+menuType);
			rightsList.add(new MenuMasterTblVo(menuID,menuDesc, menuPath, menuType, rootDesc,menuClass));
		}
		System.out.println("============rightsList==="+rightsList.size());
		ActionContext.getContext().getSession().put("RIGHTSLST", rightsList);
		return "SUCCESS";
	}else{
		return "input";
	}
  }

}
