package com.socialindia.socialmedia;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





public class Twitterlogin extends ActionSupport implements SessionAware, ServletRequestAware,
    ServletResponseAware, ServletContextAware {

  private static final long serialVersionUID = 1L;
  public static OAuthService serviceNew = null;

  public static Token requestTokenNew = null;
  public static HttpServletResponse response;
  public static HttpServletRequest request;

  String authUrl = "";
  public static String mstrMrchtId = null;
  public static String mstrMrchtDbname = null;
  public static String mstrMrchtDbusr = null;
  public static String mstrMrchtDbpswd = null;
  public static String mstrMrchtUrl = null;
  public static String mstrMrchtFlag = null;
  public static String mstrMrchtRegid = null;
  public static String mstrMrchtName = null;

  @Override
  public String execute() {
    // TODO Auto-generated method stub
    Map session = ActionContext.getContext().getSession();
    try {
      // response.setContentType("text/html;charset=UTF-8");
      mstrMrchtId = String.valueOf(session.get("MASTER_MERCHANT_ID"));
      mstrMrchtDbname = String.valueOf(session.get("MASTER_MERCHANT_DBNAME"));
      mstrMrchtDbusr = String.valueOf(session.get("MASTER_MERCHANT_DB_USERNAME"));
      mstrMrchtDbpswd = String.valueOf(session.get("MASTER_MERCHANT_DB_PASSWORD"));
      mstrMrchtUrl = String.valueOf(session.get("MASTER_MERCHANT_URL"));
      mstrMrchtFlag = String.valueOf(session.get("MASTER_MERCHANT_FLAG"));
      mstrMrchtRegid = String.valueOf(session.get("MASTER_MERCHANT_REG_ID"));
      mstrMrchtName = String.valueOf(session.get("MASTER_MERCHANT_NAME"));

      System.out.println("requestToken_new===");
      serviceNew = new ServiceBuilder().provider(TwitterApi.class)
          .apiKey("WSE7lWVS39JxBVYYV4aLw")
          .callback("http://192.168.1.48:8080/socialindia/twitterTokenRequest")
          .apiSecret("j035Oh340k3pcSnpduiDvY4zXYRbCU2Lo48ITYYD7c0").build();
      // service_new = new
      // ServiceBuilder().provider(TwitterApi.class).apiKey("LnyJjYkAD5m5t0oGQ0mx7dxpc").callback("http://192.168.1.37:8080/Dahabshiil/Twitterlogin").apiSecret("yInua8nTq0d96NQ7UGNe65K95rXtqAD67qNEZcpnnUEDX1J3V4").build();
      requestTokenNew = serviceNew.getRequestToken();
      System.out.println("requestToken_new===" + requestTokenNew);
      authUrl = serviceNew.getAuthorizationUrl(requestTokenNew);
      // session = request.getSession();
      // session.setAttribute("Service", service_new);
      // session.setAttribute("Token", requestToken_new);
      System.out.println("authUrl===" + authUrl);

    } catch (Exception ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
      System.out.println("errorrrr====" + ex);
    }
    return SUCCESS;
  }

  public static OAuthService getServiceNew() {
    return serviceNew;
  }

  public static void setServiceNew(OAuthService serviceNew) {
    Twitterlogin.serviceNew = serviceNew;
  }

  public static Token getRequestTokenNew() {
    return requestTokenNew;
  }

  public static void setRequestTokenNew(Token requestTokenNew) {
    Twitterlogin.requestTokenNew = requestTokenNew;
  }

  public static String getMstrMrchtId() {
    return mstrMrchtId;
  }

  public static void setMstrMrchtId(String mstrMrchtId) {
    Twitterlogin.mstrMrchtId = mstrMrchtId;
  }

  public static String getMstrMrchtDbname() {
    return mstrMrchtDbname;
  }

  public static void setMstrMrchtDbname(String mstrMrchtDbname) {
    Twitterlogin.mstrMrchtDbname = mstrMrchtDbname;
  }

  public static String getMstrMrchtDbusr() {
    return mstrMrchtDbusr;
  }

  public static void setMstrMrchtDbusr(String mstrMrchtDbusr) {
    Twitterlogin.mstrMrchtDbusr = mstrMrchtDbusr;
  }

  public static String getMstrMrchtDbpswd() {
    return mstrMrchtDbpswd;
  }

  public static void setMstrMrchtDbpswd(String mstrMrchtDbpswd) {
    Twitterlogin.mstrMrchtDbpswd = mstrMrchtDbpswd;
  }

  public static String getMstrMrchtUrl() {
    return mstrMrchtUrl;
  }

  public static void setMstrMrchtUrl(String mstrMrchtUrl) {
    Twitterlogin.mstrMrchtUrl = mstrMrchtUrl;
  }

  public static String getMstrMrchtFlag() {
    return mstrMrchtFlag;
  }

  public static void setMstrMrchtFlag(String mstrMrchtFlag) {
    Twitterlogin.mstrMrchtFlag = mstrMrchtFlag;
  }

  public static String getMstrMrchtRegid() {
    return mstrMrchtRegid;
  }

  public static void setMstrMrchtRegid(String mstrMrchtRegid) {
    Twitterlogin.mstrMrchtRegid = mstrMrchtRegid;
  }

  public static String getMstrMrchtName() {
    return mstrMrchtName;
  }

  public static void setMstrMrchtName(String mstrMrchtName) {
    Twitterlogin.mstrMrchtName = mstrMrchtName;
  }

  @Override
  public void setServletContext(ServletContext arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setServletResponse(HttpServletResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setServletRequest(HttpServletRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setSession(Map<String, Object> arg0) {
    // TODO Auto-generated method stub

  }

  public static HttpServletResponse getResponse() {
    return response;
  }

  public static void setResponse(HttpServletResponse response) {
    Twitterlogin.response = response;
  }

  public static HttpServletRequest getRequest() {
    return request;
  }

  public static void setRequest(HttpServletRequest request) {
    Twitterlogin.request = request;
  }

 

  public String getAuthUrl() {
    return authUrl;
  }

  public void setAuthUrl(String authUrl) {
    this.authUrl = authUrl;
  }



}
