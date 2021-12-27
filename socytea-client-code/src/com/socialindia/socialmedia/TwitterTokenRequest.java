package com.socialindia.socialmedia;

//import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/*import com.remittance.login.LoginDaoServices;
import com.remittance.login.LoginServices;
import com.remittance.vo.CustomerRegTblVo;*/
import com.socialindia.socialmedia.SocialAction;

public class TwitterTokenRequest extends ActionSupport {
  private static final long serialVersionUID = 1L;
  public static HttpServletResponse response;
  public static HttpServletRequest request;
  private HttpSession session;
  private String oauth_verifier;
 // LoginServices lgnservices = new LoginDaoServices();
  SocialAction socact = new SocialAction();
  // private CustomerRegTblVo custRegObj = new CustomerRegTblVo();

  public static String twtid = null;
  public static String screenname = null;
  public static String name = null;
  public static String profileimg = null;
  public static String profileurl = null;
  public static String profiledesc = null;
  public static String tweetstatus = null;
  String uservalues = "";

  @Override
  public String execute() {
   try {
    	System.out.println("=============twitter====================");
      Map sessionMaptwt = ActionContext.getContext().getSession();
      System.out.println("service===");
      // PrintWriter out = response.getWriter();
      OAuthService service = Twitterlogin.serviceNew;
      Token requestToken = Twitterlogin.requestTokenNew;
      JSONObject twtJson = new JSONObject();

      System.out.println("service===" + service);
      System.out.println("requestToken===" + requestToken);

      System.out.println("verifiers===" + oauth_verifier);
      Verifier verifier = new Verifier(oauth_verifier);
      Token accessToken = service.getAccessToken(requestToken, verifier);
      OAuthRequest oauthRequest = new OAuthRequest(Verb.GET,
          "https://api.twitter.com/1.1/account/verify_credentials.json");
      service.signRequest(accessToken, oauthRequest);
      Response respons = oauthRequest.send();
      String body = respons.getBody();
      System.out.println("body====" + body);

      twtJson = new JSONObject(respons.getBody());
      twtid = (String) twtJson.get("id_str");
      screenname = (String) twtJson.get("screen_name");
      name = (String) twtJson.get("name");
      profileimg = (String) twtJson.get("profile_image_url");
      profileurl = (String) twtJson.get("url");
      profiledesc = (String) twtJson.get("description");

      System.out.println("twt_id====" + twtid + "---screen_name---" + screenname + "--name--"
          + name + "--profile_img--" + profileimg + "---profile_url---" + profileurl
          + "---profile_desc---" + profiledesc);
      System.out.println("uservalues=====11=");
     // uservalues = lgnservices.checkSocialUserExists(twtid, 2);
      System.out.println("uservalues===22===");
      System.out.println("uservalues====== " + uservalues.length());
      if (uservalues.length() > 0) {
        System.out.println("uservalues====== " + uservalues.length());
        String[] val = uservalues.split("!_!");
        System.out.println("username====" + val[0] + "----psword------" + val[1]);
        // CustomerRegTblVo custRegObj = new CustomerRegTblVo();
        // custRegObj.setUsername(val[0]);
        // / custRegObj.setPassword(val[1]);
        sessionMaptwt.put("twtlogin_UserName", val[0]);
        sessionMaptwt.put("twtlogin_Password", val[1]);
        String rtnval = socact.socialUsersignin();
        System.out.println("uservalues====== " + uservalues.length());
         tweetstatus = rtnval;
      } else {
        System.out.println("uservalues====== " + uservalues.length());
        tweetstatus = "input";
        return "input";
      }

    } catch (Exception ex) {
      System.out.println("Exception in twitter login====" + ex);
      tweetstatus = "ERROR";
      return ERROR;
    }
    System.out.println("STATUS====" + tweetstatus);
    return SUCCESS;
  }

  public String getOauth_verifier() {
    return oauth_verifier;
  }

  public void setOauth_verifier(String oauth_verifier) {
    this.oauth_verifier = oauth_verifier;
  }

  public static String getTwtid() {
    return twtid;
  }

  public static void setTwtid(String twtid) {
    TwitterTokenRequest.twtid = twtid;
  }

  public static String getScreenname() {
    return screenname;
  }

  public static void setScreenname(String screenname) {
    TwitterTokenRequest.screenname = screenname;
  }

  public static String getProfileimg() {
    return profileimg;
  }

  public static void setProfileimg(String profileimg) {
    TwitterTokenRequest.profileimg = profileimg;
  }

  public static String getProfileurl() {
    return profileurl;
  }

  public static void setProfileurl(String profileurl) {
    TwitterTokenRequest.profileurl = profileurl;
  }

  public static String getProfiledesc() {
    return profiledesc;
  }

  public static void setProfiledesc(String profiledesc) {
    TwitterTokenRequest.profiledesc = profiledesc;
  }

  public SocialAction getSocact() {
    return socact;
  }

  public void setSocact(SocialAction socact) {
    this.socact = socact;
  }

  public String getUservalues() {
    return uservalues;
  }

  public void setUservalues(String uservalues) {
    this.uservalues = uservalues;
  }

  public static String getName() {
    return name;
  }

  public static void setName(String name) {
    TwitterTokenRequest.name = name;
  }

public static String getTweetstatus() {
	return tweetstatus;
}

public static void setTweetstatus(String tweetstatus) {
	TwitterTokenRequest.tweetstatus = tweetstatus;
}
  
  



}
