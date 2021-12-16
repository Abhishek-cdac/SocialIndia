package com.socialindia.societyMgmt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.SocietyMstTbl;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class societyViewAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private SocietyMstTbl societyMst = new SocietyMstTbl(0, null, null, null, null, null, null,0,null);
	private int uniqSocietyId;
	private String emailId;
	private String mobileNo;
	/**
	 * 
	 */
	private String isdcode;
	MvpSocietyAccTbl societyAcc = new MvpSocietyAccTbl();
	List<SocietyWingsDetailTbl> societyWingList = new ArrayList<SocietyWingsDetailTbl>();	
	JSONArray ar =null;
	public String execute(){
		Log log = null;
		JSONObject lvrResponrcvjson =  null;
		JSONObject json_data = null;
		try{			
			log = new Log();
			System.out.println("Step 1 : Society View will called.[Start] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			log.logMessage("Step 1 : Society View will called.[Start]", "info", societyViewAction.class);
			Map currentSession = ActionContext.getContext().getSession();
			if ((uniqSocietyId == 0) && currentSession.get("currentsession_uniqSocietyId") != null) {
				uniqSocietyId = (Integer) currentSession.get("currentsession_uniqSocietyId");
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_uniqSocietyId", uniqSocietyId);
			}		
			data.put("servicecode", "SI0032");
			obj.put("uniqSocietyId",uniqSocietyId);
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("society.management.edit.view.action");
			log.logMessage("Step 2 : Service Url : "+finalUrl, "info", societyViewAction.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrResponrcvjson = new JSONObject(response);
			String statusCode = lvrResponrcvjson.getString("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {			
				json_data = lvrResponrcvjson.getJSONObject("data");
				societyMst.setTownshipName(json_data.getString("townshipname"));
				societyMst.setActivationKey(json_data.getString("activationkey"));
		
				societyMst.setSocietyName(json_data.getString("societyname"));
				societyMst.setNoOfMembers(json_data.getInt("noofmember"));
				societyMst.setCountryName(json_data.getString("country"));
				societyMst.setStateName(json_data.getString("state"));
				societyMst.setCityName(json_data.getString("city"));
//				societyMst.setPinCode(json_data.getString("postalcode"));
				societyMst.setPinCode(json_data.getInt("postalcode")+"");
				societyMst.setGstin(json_data.getString("gstin"));
				societyMst.setHsn(json_data.getString("hsn"));
				
				societyMst.setRegisterNo(json_data.getString("registerno"));
				societyMst.setImprintName(json_data.getString("imprintname"));
				societyMst.setNoOfBlocksWings(json_data.getString("noofblockswings"));
				societyAcc.setBankAccNo((String) Commonutility.toHasChkJsonRtnValObj(json_data, "accountno"));
				societyAcc.setBankName(json_data.getString("bankname"));
				societyAcc.setBankAccName(json_data.getString("accountname"));
				societyAcc.setIfscCode(json_data.getString("ifsccode"));
				societyMst.setLogoImage((String)Commonutility.toHasChkJsonRtnValObj(json_data, "logoimage"));
				societyMst.setIcoImage((String)Commonutility.toHasChkJsonRtnValObj(json_data, "icoimage"));
				societyMst.setColourCode((String)Commonutility.toHasChkJsonRtnValObj(json_data, "colorcode"));
				societyMst.setSocietyId(uniqSocietyId);
				emailId=(String)Commonutility.toHasChkJsonRtnValObj(json_data, "emailid");
				mobileNo=(String)Commonutility.toHasChkJsonRtnValObj(json_data, "mobileno");
				isdcode=(String)Commonutility.toHasChkJsonRtnValObj(json_data, "isdCode");
				ar = json_data.getJSONArray("societyWingsName");
				 JSONObject jsonList = new JSONObject();
				 if(ar!=null && ar.length()>0){
					 for(int i=0;i<ar.length();i++) {	
						 jsonList=null;
						 jsonList=ar.getJSONObject(i);
						 if(jsonList.getString("societywingsname")!=null && !jsonList.getString("societywingsname").equalsIgnoreCase("")){
							 String societywingsname=jsonList.getString("societywingsname");
							 societyWingList.add(new SocietyWingsDetailTbl(societywingsname));
						 }				
					 }
				 }		
			}
			System.out.println("Step 3 : Society View.[End] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			log.logMessage("Step 3 : Society View.[End]", "info", societyViewAction.class);
		}catch(Exception e){
			System.out.println("Step -1 : Exception found societyViewAction.class : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss") +" - "+ e);
			log.logMessage("Step -1 : Exception found : "+e, "error", societyViewAction.class);
		}finally{
			log = null; lvrResponrcvjson = null; json_data = null;
		}

		return SUCCESS;
	}
	public SocietyMstTbl getSocietyMst() {
		return societyMst;
	}
	public void setSocietyMst(SocietyMstTbl societyMst) {
		this.societyMst = societyMst;
	}
	public int getUniqSocietyId() {
		return uniqSocietyId;
	}
	public void setUniqSocietyId(int uniqSocietyId) {
		this.uniqSocietyId = uniqSocietyId;
	}
	public List<SocietyWingsDetailTbl> getSocietyWingList() {
		return societyWingList;
	}
	public void setSocietyWingList(List<SocietyWingsDetailTbl> societyWingList) {
		this.societyWingList = societyWingList;
	}
	public MvpSocietyAccTbl getSocietyAcc() {
		return societyAcc;
	}
	public void setSocietyAcc(MvpSocietyAccTbl societyAcc) {
		this.societyAcc = societyAcc;
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
	public String getIsdcode() {
		return isdcode;
	}
	public void setIsdcode(String isdcode) {
		this.isdcode = isdcode;
	}
	
}
