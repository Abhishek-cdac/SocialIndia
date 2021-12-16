package com.socialindia.committeeMgmt;

import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class committeeViewAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer uniqcommitteeId;	
	private Common commonObj = new CommonDao();
	UserMasterTblVo usercreateObj = new UserMasterTblVo(null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, null);
	private String townshipname;
	private String accessChennel;
	private String societyname;
	private String committeeRole;
	
	public String execute(){
		Log log = null;
		JSONObject lvrRspjsonobj = null;
		JSONObject lvrdataJsonobj = null;
		JSONObject lvrFrqstJsonObj = null;
		try{log=new Log();
			lvrdataJsonobj = new JSONObject();
			lvrFrqstJsonObj = new JSONObject();
			log.logMessage("Step 1 : committeeViewAction [Start]", "info", committeeViewAction.class);	
			
			Map currentSession = ActionContext.getContext().getSession();			
			if(uniqcommitteeId==null || uniqcommitteeId==-1){					
				if((uniqcommitteeId==null)  && currentSession.get("cruntCommitteeidsession")!=null){							
					String userunque=(String)currentSession.get("cruntCommitteeidsession");
					if(Commonutility.toCheckisNumeric(userunque)){
						uniqcommitteeId = Integer.parseInt(userunque);
					}
				}
			}else{
				
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("cruntCommitteeidsession", String.valueOf(uniqcommitteeId));					
			}						
			lvrdataJsonobj.put("uniqcommitteeId",uniqcommitteeId);
			lvrFrqstJsonObj.put("servicecode", "SI0038");
			lvrFrqstJsonObj.put("data", lvrdataJsonobj);
			String jsonTextFinal = lvrFrqstJsonObj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("committeeMgmt.management.view.action");	
			log.logMessage("Step 2 : committeeViewAction Service Url : "+finalUrl, "info", committeeViewAction.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response____________________________res..."+response);
			lvrRspjsonobj = new JSONObject(response);
			String statusCode = lvrRspjsonobj.getString("statuscode");
			if (statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")) {				
				JSONObject json_data=lvrRspjsonobj.getJSONObject("data");
				townshipname=json_data.getString("townshipname");
				societyname=json_data.getString("societyname");
				committeeRole=json_data.getString("committeeRole");
				usercreateObj.setUserName(json_data.getString("username"));
				usercreateObj.setFirstName(json_data.getString("firstname"));
				usercreateObj.setLastName(json_data.getString("lastname"));
				usercreateObj.setEmailId(json_data.getString("emailid"));
				usercreateObj.setMobileNo(json_data.getString("mobileno"));
				usercreateObj.setIsdCode(json_data.getString("isdcode"));
				usercreateObj.setDob(json_data.getString("dob"));
				usercreateObj.setAccessChannel(json_data.getInt("accesschannel"));
				if(usercreateObj.getAccessChannel()==1){
					accessChennel="Web";
				}else if(usercreateObj.getAccessChannel()==2){
					accessChennel="Mobile";
				}else if(usercreateObj.getAccessChannel()==3){
					accessChennel="Both";
				}else{
					accessChennel="";
				}
				String tempgender = "";
				String gen = json_data.getString("gender");
				if (gen.equalsIgnoreCase("1")) {
					tempgender = "Male";
				} else if (gen.equalsIgnoreCase("2")) {
					tempgender = "Female";
				} else {
					tempgender = "Others";
				}
				usercreateObj.setGender(tempgender);
			} else {
				
			}
			log.logMessage("Step 3 : committeeViewAction [End]", "info", committeeViewAction.class);	
			}catch(Exception e){
				System.out.println("step -1 : committeeViewAction Exception : "+e);
				log.logMessage("step -1 : committeeViewAction Exception : "+e, "error", committeeViewAction.class);
			}finally{
				lvrRspjsonobj = null; lvrdataJsonobj = null; lvrFrqstJsonObj = null;
			}
		
		return SUCCESS;
	}
	public UserMasterTblVo getUsercreateObj() {
		return usercreateObj;
	}
	public void setUsercreateObj(UserMasterTblVo usercreateObj) {
		this.usercreateObj = usercreateObj;
	}
	public String getTownshipname() {
		return townshipname;
	}
	public void setTownshipname(String townshipname) {
		this.townshipname = townshipname;
	}
	public String getSocietyname() {
		return societyname;
	}
	public void setSocietyname(String societyname) {
		this.societyname = societyname;
	}
	public String getCommitteeRole() {
		return committeeRole;
	}
	public void setCommitteeRole(String committeeRole) {
		this.committeeRole = committeeRole;
	}
	public int getUniqcommitteeId() {
		return uniqcommitteeId;
	}
	public void setUniqcommitteeId(int uniqcommitteeId) {
		this.uniqcommitteeId = uniqcommitteeId;
	}
	public String getAccessChennel() {
		return accessChennel;
	}
	public void setAccessChennel(String accessChennel) {
		this.accessChennel = accessChennel;
	}
	
}
