package com.letspay.utils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.load.LoadDaoServices;
import com.letspay.load.LoadServices;
import com.letspay.uam.persistense.RightsMasterTblVo;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.login.EncDecrypt;

public class LogOut extends ActionSupport implements ServletRequestAware{
  private static final long serialVersionUID = 1L;
  private static final Logger log = Logger.getLogger(LogOut.class);
  private AlertVo alert = new AlertVo();
  LoadServices loadservic = new LoadDaoServices();
  private List<AlertVo> alertList = new ArrayList<AlertVo>();
  private List<RightsMasterTblVo> rightsList = new ArrayList<RightsMasterTblVo>();
  private String newLogin;
  protected HttpServletRequest servletRequest;
  
	private Common commonObj = new CommonDao();
  /**
   * doLogout.
   * 
   * @return String.
   */
  public String doLogout() {
    System.out.println("Logout successfully. All Session value cleared.");
    
    
    ServletContext context = ServletActionContext.getServletContext();
    Map sessionMap = ActionContext.getContext().getSession();

	JSONObject finaljj=null;
	String  temp =null; JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
	
	JSONObject obj = new JSONObject();
		
    try {
    	obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
		obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
		obj.put("offlinestatus", "0");
		obj.put("flag", "false");

		finaljj=new JSONObject();
		finaljj.put("servicecode", "SI00011");		
		finaljj.put("data", obj);
		
		jsonTextFinal = finaljj.toString().trim();
		System.out.println("requser offline update::: "+jsonTextFinal);
		
		jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
		temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
		
		finalUrl = getText("socialindia.useroffline.statusaction");
		response = commonObj.jsonRequest(finalUrl, temp);
		
		SessionMap session = (SessionMap) ActionContext.getContext().getSession();
    	sessionMap.clear();
    	if (session != null) {
    		session.clear();
    		session.invalidate();
    	}                 
    	if (sessionMap.get("RIGHTSLST") == null) {
    		ActionContext.getContext().getSession().put("RIGHTSLST", null);
    	}
    	if(sessionMap.get("IPACTION_STATUSFLG")==null){
    		ActionContext.getContext().getSession().put("IPACCESS_STATUSFLG", "0");
    	} 
      
    } catch (Exception ex) {
      log.error("Exception in dologout  ::Exception::  " + ex);
    }

    if (newLogin == null) {
      alert.setCls("success");
      alert.setMsg("User Logout Successfully");
      alertList.add(alert);
    }
    return SUCCESS;
  }

  /**
   * sessionExpired.
   * 
   * @return String.
   */
  public String sessionExpired() {
	  
    ServletContext context = ServletActionContext.getServletContext();
    Map sessionMap = ActionContext.getContext().getSession();
    try {
    	JSONObject finaljj=null;
    	String  temp =null; JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
    	JSONObject obj = new JSONObject();
    		
        obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
    	obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
    	obj.put("offlinestatus", "0");
    	
    	System.out.println("Step 1 : sessionExpired  => " + servletRequest.getParameter("flag"));
    	if(servletRequest.getParameter("flag")!=null){
    		obj.put("flag", servletRequest.getParameter("flag"));
    	}
    	else{
    		obj.put("flag", "false");
    	}
    	
    	finaljj=new JSONObject();
    	finaljj.put("servicecode", "SI00011");		
    	finaljj.put("data", obj);
    
    	jsonTextFinal = finaljj.toString().trim();
    	System.out.println("requser offline update::: "+jsonTextFinal);
   
    	jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
    	temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
   		finalUrl = getText("socialindia.useroffline.statusaction");
   		response = commonObj.jsonRequest(finalUrl, temp);
    		
   		SessionMap session = (SessionMap) ActionContext.getContext().getSession();
   		sessionMap.clear();
   		if (session != null) {
   			session.invalidate();
   		}
     /* if (context.getAttribute("MENUTYP") != null) {
        context.setAttribute("MENUTYP", null);
      }*/
   		if (context.getAttribute("RIGHTSLST") != null) {
   			context.setAttribute("RIGHTSLST", null);
   		}
   		if (sessionMap.get("groupMasterList") != null) {
   			ActionContext.getContext().getSession().put("groupMasterList", null);
   		}
    } catch (Exception ex) {
      log.error("Exception in dologout  ::Exception::  " + ex);
    }
    return SUCCESS;
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

  public static Logger getLog() {
    return log;
  }

  public String getNewLogin() {
    return newLogin;
  }

  public void setNewLogin(String newLogin) {
    this.newLogin = newLogin;
  }

@Override
public void setServletRequest(HttpServletRequest arg0) {
	this.servletRequest = arg0;
}

}
