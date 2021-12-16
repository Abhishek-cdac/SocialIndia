package com.socialindia.forum;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class forumEditAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Log log=new Log();
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private int topicsId;
	private String topicName;
	private String topicsDesc;	
	MvpFourmTopicsTblVO forumTopicData=new MvpFourmTopicsTblVO();
	
	private List<MvpFourmDiscussTblVO> forumdiscussList=new ArrayList<MvpFourmDiscussTblVO>();

	public String execute() {
		ResourceBundle ivrRbuilder = ResourceBundle.getBundle("applicationResources");
		try {
			Map currentSession = ActionContext.getContext().getSession();
			if ((topicsId == 0) && currentSession.get("currentsession_topicsId") != null) {
				topicsId = (Integer) currentSession.get("currentsession_topicsId");
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_topicsId", topicsId);
			}
		
			data.put("servicecode", "SI0049");
			obj.put("topicsId", topicsId);
			data.put("data", obj);
			String jsonTextFinal = data.toString();	
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("forumMgmt.management.edit.Action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;
			JSONObject locObjRecvJson = null;
			JSONObject locObjRecvdataJson = null;
			if (response!=null && !response.equalsIgnoreCase("null") && response.length()>0){
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
	    	  		ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");	    	  
		    	    ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");		    	  
		    	    ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");		    	  
		    	    ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");				
		    	    locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data"); 
					topicsId=(Integer) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "topicsid");
					topicName=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "topicname");
					topicsDesc=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "topicdesc");	    	  
					String  keysearch=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "keysearch");
					String  validdate=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "validdate");
					forumTopicData.setTopicsId(topicsId);
					forumTopicData.setTopicsName(topicName);
					forumTopicData.setTopicsDesc(topicsDesc);
					forumTopicData.setKeyForSearch(keysearch);
			    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    	Date startDate = df.parse(validdate);
			    	SimpleDateFormat df1 = new SimpleDateFormat(ivrRbuilder.getString("calander.format.date"));
			    	validdate=df1.format(startDate);
			    	forumTopicData.setFvalidDate(validdate);
		
				}
			}
								
		} catch (Exception e) {

		} finally {

		}
		return SUCCESS;
	}
	public int getTopicsId() {
		return topicsId;
	}
	public void setTopicsId(int topicsId) {
		this.topicsId = topicsId;
	}
	public MvpFourmTopicsTblVO getForumTopicData() {
		return forumTopicData;
	}
	public void setForumTopicData(MvpFourmTopicsTblVO forumTopicData) {
		this.forumTopicData = forumTopicData;
	}
}
