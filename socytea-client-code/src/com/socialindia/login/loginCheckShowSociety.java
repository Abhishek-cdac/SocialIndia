package com.socialindia.login;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.uam.persistense.SocietyMstTbl;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;

public class loginCheckShowSociety extends ActionSupport{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Common commonObj = new CommonDao();
	private String userName;
	private int uniqSocietyId;
	List<UserMasterTblVo> userList = new ArrayList<UserMasterTblVo>();
	public List<SocietyMstTbl> societyList = new ArrayList<SocietyMstTbl>();
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private String locIdrcty = null;
	private String locIdcountrytyp = null;
	private String locIdStatetyp = null;
	String username = null, password = null, activationkey = null, emailId = null, mobileNo = null, townshipName = null, societyName = null;
	private String locIdskillstyp = null;
	String flag = "";

	public String execute() throws Exception {
		JSONObject locObjRecvJson = null;//Receive String to json	
		JSONObject locObjRecvdataJson = null;// Receive Data Json		
		JSONObject locObjRspdataJson = null;// Response Data Json
		String ivrservicecode=null;
    	String ivrresponsecode=null;
    	String ivrmsg=null;
		String ivrstatuscode = null;
		try {
			data.put("servicecode", "SI1045");
			obj.put("userNameOrMobile", userName);
			data.put("data", obj);
    		String jsonTextFinal_Idcard = data.toString().trim();
    		jsonTextFinal_Idcard=EncDecrypt.encrypt(jsonTextFinal_Idcard);
    		String tempidcard = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Idcard);
    		String finalUrl_pcode = getText("login.user.check.society.action");
        	String response1 = commonObj.jsonRequest(finalUrl_pcode, tempidcard);        	
        	  if(response1!=null && !response1.equalsIgnoreCase("null") && response1.length()>0){
        		  boolean ivIsJson = Commonutility.toCheckIsJSON(response1);
        		  if (ivIsJson) {
        			  locObjRecvJson = new JSONObject(response1);
    		    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");		    	  
    		    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");		    	  
    		    	  ivrmsg=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");		    	  
    		    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");				
    		    	  locObjRecvdataJson =(JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");
    		    	  if(ivrstatuscode!=null && (ivrstatuscode.equalsIgnoreCase("0") || ivrstatuscode.equalsIgnoreCase("00"))){
    		    		  JSONArray jary=(JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"UserInfoDetail");
    		    		  JSONObject jsonList = new JSONObject();
						for (int i = 0; i < jary.length(); i++) {
							jsonList = null;
							jsonList = jary.getJSONObject(i);
							int societyid = jsonList.getInt("societyid");
    							String societyname=jsonList.getString("societyname");
    							societyList.add(new SocietyMstTbl(societyid,null,societyname,null,null,null,null,0,null));
    						}    		    		  
    				}

    			}
    		} else {
    			
    		}
    	} catch(Exception e){
    		Commonutility.toWriteConsole("Exception found in "+getClass().getSimpleName() +".class : "+e);
    	} finally {
    		 locObjRecvJson = null;//Receive String to json	
    		 locObjRecvdataJson = null;// Receive Data Json		
    		 locObjRspdataJson = null;// Response Data Json
    		 ivrservicecode=null;
        	 ivrresponsecode=null;
        	 ivrmsg=null;
    		 ivrstatuscode = null;
    	}    	

		return SUCCESS;
	}

	public String getLocIdrcty() {
		return locIdrcty;
	}

	public void setLocIdrcty(String locIdrcty) {
		this.locIdrcty = locIdrcty;
	}

	public String getLocIdcountrytyp() {
		return locIdcountrytyp;
	}

	public void setLocIdcountrytyp(String locIdcountrytyp) {
		this.locIdcountrytyp = locIdcountrytyp;
	}

	public String getLocIdStatetyp() {
		return locIdStatetyp;
	}

	public void setLocIdStatetyp(String locIdStatetyp) {
		this.locIdStatetyp = locIdStatetyp;
	}

	public String getLocIdskillstyp() {
		return locIdskillstyp;
	}

	public void setLocIdskillstyp(String locIdskillstyp) {
		this.locIdskillstyp = locIdskillstyp;
	}

	public int getUniqSocietyId() {
		return uniqSocietyId;
	}

	public void setUniqSocietyId(int uniqSocietyId) {
		this.uniqSocietyId = uniqSocietyId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivationkey() {
		return activationkey;
	}

	public void setActivationkey(String activationkey) {
		this.activationkey = activationkey;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getTownshipName() {
		return townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<SocietyMstTbl> getSocietyList() {
		return societyList;
	}

	public void setSocietyList(List<SocietyMstTbl> societyList) {
		this.societyList = societyList;
	}

}
