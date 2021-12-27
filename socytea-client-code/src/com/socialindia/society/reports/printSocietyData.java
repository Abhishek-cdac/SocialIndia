package com.socialindia.society.reports;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.uam.persistense.MenuMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.getCategoryCodeMethod;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.IDCardMasterTblVO;
import com.socialindia.login.EncDecrypt;

public class printSocietyData extends ActionSupport{
	private Common commonObj = new CommonDao();
	private int uniqSocietyId;
	private int groupcode;
	List<UserMasterTblVo> userList = new ArrayList<UserMasterTblVo>();
	public List<IDCardMasterTblVO> idcardMstr = new ArrayList<IDCardMasterTblVO>();
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private String locIdrcty = null;
	private String locIdcountrytyp = null;
	private String locIdStatetyp = null;
	String username = null, password = null, activationkey = null,
			emailId = null, mobileNo = null, townshipName = null,
			societyName = null;
	private String locIdskillstyp = null;
	String flag = "";
	private String ivrScietyid;
	private String ivrScietyimgname;
	private String ivrTwnshipid;
	private String ivrTwnshipimgname;
public String execute() throws Exception {
	  JSONObject locObjRecvJson = null;//Receive String to json	
		JSONObject locObjRecvdataJson = null;// Receive Data Json		
		JSONObject locObjRspdataJson = null;// Response Data Json
		System.out.println("========================Society Report form======uniqSocietyId=========="+uniqSocietyId);
		String ivrservicecode=null;
    	String ivrresponsecode=null;
    	String ivrmsg=null;
    	String ivrstatuscode=null;
    	
    	
    	data.put("servicecode", "SI1043");
		obj.put("uniqSocietyId", uniqSocietyId);
    	obj.put("groupcode", groupcode);
		data.put("data", obj);
		String jsonTextFinal_Idcard = data.toString().trim();
		System.out.println("===========jsonTextFinal_Idcard======"+jsonTextFinal_Idcard);
		jsonTextFinal_Idcard=EncDecrypt.encrypt(jsonTextFinal_Idcard);
		String tempidcard = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Idcard);
	//	toCheckService(postparam+URLEncoder.encode(postparamval),srvcUrl);		
		String finalUrl_pcode = getText("society.management.print.report.action");		
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
				if (ivrstatuscode!=null && ivrstatuscode.equalsIgnoreCase("0")){
					username =(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"username");
			    	password=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"password");
			    	activationkey=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"activationkey");  
			    	townshipName=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"townshipname");  
			    	societyName=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"societyname");  
			    	emailId=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"emailid");  
			    	mobileNo=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"mobileno");  
			    	  
					ivrScietyid = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"societyidd");
			    	ivrScietyimgname = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"societyimagename");
			    	ivrTwnshipid = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"twnshipidd");
			    	ivrTwnshipimgname = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"twnshipimagename");
			    	  
			    	  flag="success";
		    	  }else{
		    		  flag="notdata";
		    	  }
		    	 
		    	  
    					    	  
    	  }
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
public int getGroupcode() {
	return groupcode;
}
public void setGroupcode(int groupcode) {
	this.groupcode = groupcode;
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
public String getIvrScietyid() {
	return ivrScietyid;
}
public void setIvrScietyid(String ivrScietyid) {
	this.ivrScietyid = ivrScietyid;
}
public String getIvrScietyimgname() {
	return ivrScietyimgname;
}
public void setIvrScietyimgname(String ivrScietyimgname) {
	this.ivrScietyimgname = ivrScietyimgname;
}
public String getIvrTwnshipid() {
	return ivrTwnshipid;
}
public void setIvrTwnshipid(String ivrTwnshipid) {
	this.ivrTwnshipid = ivrTwnshipid;
}
public String getIvrTwnshipimgname() {
	return ivrTwnshipimgname;
}
public void setIvrTwnshipimgname(String ivrTwnshipimgname) {
	this.ivrTwnshipimgname = ivrTwnshipimgname;
}

}
