package com.socialindia.forum;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.TownshipMstTbl;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class postCommentAction extends  ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uniqTownShipIdEdit;
	Log log = new Log();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	TownshipMstTbl townShipMst = new TownshipMstTbl();
	MvpFourmTopicsTblVO forumTopicData = new MvpFourmTopicsTblVO();
	private String postComment;
	private int topicsId;

	public String execute() {	
		try {
			Map sessionMap = ActionContext.getContext().getSession();
			Map currentSession = ActionContext.getContext().getSession();
			if ((topicsId == 0)
					&& currentSession.get("currentsession_topicsId") != null) {
				topicsId = (Integer) currentSession
						.get("currentsession_topicsId");
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_topicsId",
						topicsId);
			}
			data.put("servicecode", "SI0048");
			obj.put("useruniqueId", sessionMap.get("USERID"));
			obj.put("postcomment", postComment);
			obj.put("topicsId", topicsId);
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("forumMgmt.management.post.comment.Action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			String ivrservicecode=null;
			String ivrresponsecode=null;
			String ivrmsg=null;
			String ivrstatuscode=null;
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
					if (ivrstatuscode.equalsIgnoreCase("00") || ivrstatuscode.equalsIgnoreCase("0")) {
						alert.setCls("success");
						alert.setMsg(getText("Comment saved successfully."));
						alertList.add(alert);
						return "success";
					} else {
						alert.setCls("danger");
						alert.setMsg(getText("Forum creation error."));
						alertList.add(alert);
						return "input";
					}
    		
    		  }
		 }
    								
		} catch (Exception e) {
			
		} finally {
			
		}
		return SUCCESS;
	}


	public String getUniqTownShipIdEdit() {
		return uniqTownShipIdEdit;
	}

	public void setUniqTownShipIdEdit(String uniqTownShipIdEdit) {
		this.uniqTownShipIdEdit = uniqTownShipIdEdit;
	}

	public TownshipMstTbl getTownShipMst() {
		return townShipMst;
	}

	public void setTownShipMst(TownshipMstTbl townShipMst) {
		this.townShipMst = townShipMst;
	}

	

	public String getPostComment() {
		return postComment;
	}


	public void setPostComment(String postComment) {
		this.postComment = postComment;
	}


	public MvpFourmTopicsTblVO getForumTopicData() {
		return forumTopicData;
	}

	public void setForumTopicData(MvpFourmTopicsTblVO forumTopicData) {
		this.forumTopicData = forumTopicData;
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


	public int getTopicsId() {
		return topicsId;
	}


	public void setTopicsId(int topicsId) {
		this.topicsId = topicsId;
	}

	
	
}
