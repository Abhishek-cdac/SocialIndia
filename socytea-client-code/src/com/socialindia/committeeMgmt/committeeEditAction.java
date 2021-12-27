package com.socialindia.committeeMgmt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.login.EncDecrypt;

public class committeeEditAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	UserMasterTblVo usercreateObj = new UserMasterTblVo(null, null, null, null,
			null, null, null, null, 0, null, null, null, null, 0,null);
	private int uniqcommitteeId;
	private int townshipId;
	private Integer societyId;
	private Integer committeeid;
	private String township_List;
	Log log=new Log();
	public String execute(){
	try{
		System.out.println("Committee Edit Called.");
		Map currentSession = ActionContext.getContext().getSession();			
		if((uniqcommitteeId==0 )  && currentSession.get("currentsession_uniqcommitteeId")!=null){			
			uniqcommitteeId=(Integer)currentSession.get("currentsession_uniqcommitteeId");
		}else{
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_uniqcommitteeId", uniqcommitteeId);					
		}		
		data.put("servicecode", "SI0039");
		obj.put("uniqcommitteeId",uniqcommitteeId);
		data.put("data", obj);
		String jsonTextFinal = data.toString();
		jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
		String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
		String finalUrl = getText("committee.management.edit.modify.view.action");
		String response = commonObj.jsonRequest(finalUrl, temp);
		System.out.println("committeeEditaction response : " + response);
		JSONObject json = new JSONObject(response);
		String statusCode = json.getString("statuscode");
		if (statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")) {
			
			JSONObject json_data = json.getJSONObject("data");
			usercreateObj.setUserId(json_data.getInt("userId"));
			usercreateObj.setUserName(json_data.getString("userName"));
			usercreateObj.setEmailId(json_data.getString("emailId"));
			usercreateObj.setMobileNo(json_data.getString("mobileNo"));
			usercreateObj.setTownshipName(json_data.getString("townshipname"));
			usercreateObj.setSocietyName(json_data.getString("societyname"));
			usercreateObj.setCommitteeRole(json_data.getString("committeerole"));
			usercreateObj.setFirstName(json_data.getString("fName"));
			usercreateObj.setLastName(json_data.getString("lName"));
			usercreateObj.setDob(json_data.getString("dob"));
			usercreateObj.setGender(json_data.getString("gender"));
			usercreateObj.setAccessChannel(json_data.getInt("accesschannel"));
			townshipId=json_data.getInt("townshipId");
			societyId=json_data.getInt("societyId");
			committeeid=json_data.getInt("committeeid");
			String societynamecur=json_data.getString("societyname");
			String townshipname=json_data.getString("townshipname");		    							
		}
		
		}catch(Exception e){
			System.out.println("Exception Found societyEditAction : "+e);
			log.logMessage("societyEditAction Exception : "+e, "error", committeeEditAction.class);
		}

		return SUCCESS;
	}
	
	public int getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(int townshipId) {
		this.townshipId = townshipId;
	}
	public String getTownship_List() {
		return township_List;
	}
	public void setTownship_List(String township_List) {
		this.township_List = township_List;
	}
	public int getUniqcommitteeId() {
		return uniqcommitteeId;
	}
	public void setUniqcommitteeId(int uniqcommitteeId) {
		this.uniqcommitteeId = uniqcommitteeId;
	}

	public UserMasterTblVo getUsercreateObj() {
		return usercreateObj;
	}

	public void setUsercreateObj(UserMasterTblVo usercreateObj) {
		this.usercreateObj = usercreateObj;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
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

	public Integer getCommitteeid() {
		return committeeid;
	}

	public void setCommitteeid(Integer committeeid) {
		this.committeeid = committeeid;
	}
	
	
}
