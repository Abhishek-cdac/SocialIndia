package com.socialindia.forum;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class forumViewAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Common commonObj = new CommonDao();
	private int topicsId;
	private String topicName;
	private String topicsDesc;
	private String firstName;
	private String lastName;
	private String topicFirstName;
	private String userMobileNo;
	private int topicsDescLen;
	private String topicsDescMin;

	private List<MvpFourmDiscussTblVO> forumdiscussList = new ArrayList<MvpFourmDiscussTblVO>();

	public String execute() {
		Log log = null;
		JSONObject obj = null;
		JSONObject data = null;
		try {
			log = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			Map currentSession = ActionContext.getContext().getSession();
			if ((topicsId == 0) && currentSession.get("currentsession_topicsId") != null) {
				topicsId = (Integer) currentSession.get("currentsession_topicsId");
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_topicsId", topicsId);
			}		
			data.put("servicecode", "SI0047");
			obj.put("topicsId", topicsId);
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("forumMgmt.management.view.Action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;
			JSONObject locObjRecvJson = null;
			JSONObject locObjRecvdataJson = null;
			if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");	    	  
					ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");	    	  
					ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");	    	  
					ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");			
					locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");    	    	  
					topicsDesc=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "topicdesc");
					topicsDescLen=topicsDesc.length();
					if(topicsDescLen>=150){
						topicsDescMin=topicsDesc.substring(0, 150);
					}
					topicName=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "topicname");
					topicsId=(Integer) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "topicsid");
					firstName=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "loginuserfirstname");
					lastName=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "loginuserlastname");
					userMobileNo=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "loginusermobileno");
					JSONArray ar=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"forumdiscussedetails");	
					JSONObject jsonList = new JSONObject();
					if (ar != null && ar.length() > 0) {
						for (int i = 0; i < ar.length(); i++) {
							jsonList=null;
							jsonList=ar.getJSONObject(i);
							String firstname=jsonList.getString("userfirstname");
							String userlastname=jsonList.getString("userlastname");
							String usermobileno=jsonList.getString("usermobileno");
							int userid=jsonList.getInt("userid");
							String imagename=jsonList.getString("imagename");
							String postcomments=jsonList.getString("postcomments");
							int commentslen=postcomments.length();
							String commentsmin="";
							if(commentslen>=150){
							 commentsmin=postcomments.substring(0, 150);
							}
							String entrydate = jsonList.getString("entrydate");
							String[] spl = entrydate.split(" ");
							String spldate=spl[0];
							String spltime=spl[1];
							spltime=spltime.replace(".0", "");
							SimpleDateFormat lvrRcfmt = new SimpleDateFormat("yyyy-MM-dd");
							SimpleDateFormat lvrCfrtfmt = new SimpleDateFormat("dd-MM-yyyy");
							spldate = lvrCfrtfmt.format(lvrRcfmt.parse(spldate));
							forumdiscussList.add(new MvpFourmDiscussTblVO(userid,firstname,postcomments,imagename,entrydate,spldate,spltime,userlastname,usermobileno,commentslen,commentsmin));							
						}
					}	
		
		  }
	 }
								
	}catch (Exception e) {
		Commonutility.toWriteConsole("Step 1 : Fourm view Exception forumViewAction.class : "+e);
	}
	return SUCCESS;
}
	public int getTopicsId() {
		return topicsId;
	}
	public void setTopicsId(int topicsId) {
		this.topicsId = topicsId;
	}
	public List<MvpFourmDiscussTblVO> getForumdiscussList() {
		return forumdiscussList;
	}
	public void setForumdiscussList(List<MvpFourmDiscussTblVO> forumdiscussList) {
		this.forumdiscussList = forumdiscussList;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTopicFirstName() {
		return topicFirstName;
	}
	public void setTopicFirstName(String topicFirstName) {
		this.topicFirstName = topicFirstName;
	}
	public String getUserMobileNo() {
		return userMobileNo;
	}
	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}
	public String getTopicsDesc() {
		return topicsDesc;
	}
	public void setTopicsDesc(String topicsDesc) {
		this.topicsDesc = topicsDesc;
	}
	public int getTopicsDescLen() {
		return topicsDescLen;
	}
	public void setTopicsDescLen(int topicsDescLen) {
		this.topicsDescLen = topicsDescLen;
	}
	public String getTopicsDescMin() {
		return topicsDescMin;
	}
	public void setTopicsDescMin(String topicsDescMin) {
		this.topicsDescMin = topicsDescMin;
	}
}
