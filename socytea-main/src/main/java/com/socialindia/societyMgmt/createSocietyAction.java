package com.socialindia.societyMgmt;

import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.TownshipMstTbl;
import com.letspay.utils.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class createSocietyAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uniqTownShipIdEdit;
	private String ivrTwsipid;//New Society creation via shortcut
	private String ivrTwsipname; //New Society creation via shortcut
	private Common commonObj = new CommonDao();
	TownshipMstTbl townShipMst = new TownshipMstTbl();
	MvpSocietyAccTbl societyAcc = new MvpSocietyAccTbl();
	private String township_List;

	public String execute() {
		Log log = null;
		JSONObject obj = null;
		JSONObject data = null;
		JSONObject locObjRecvJson = null;
		JSONObject locObjRecvdataJson = null;
		try {
			log = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			data.put("servicecode", "SI0035");
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("townShipMgmt.management.Master.List.Action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg = null;
			String ivrstatuscode = null;			
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");
					ivrresponsecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
					ivrmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
					ivrstatuscode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
					locObjRecvdataJson = (JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "data");
					JSONArray jary = (JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "datalst");
					String fin = "";
					JSONArray jsrcountry = null;
					JSONObject jbcountry = null;
					try {
							jsrcountry = new JSONArray();
						for (int i = 0; i < jary.length(); i++) {
							JSONArray temm;
							temm = jary.getJSONArray(i);
							int indx = (Integer) temm.get(0);
							String vl = (String) temm.get(1);
							jbcountry = new JSONObject();
							jbcountry.put("id", String.valueOf(indx));// jobj
							vl = vl.replace("'", "%27");
							jbcountry.put("label", vl);// jobj
							jsrcountry.put(jbcountry);// jarray
						}
						township_List = jsrcountry.toString();
					} catch (Exception e) {
						log.logMessage("Exception found : " + e, "error",createSocietyAction.class);
					} finally {
						jsrcountry = null;
						jbcountry = null;
					}
				}
			}
			Map currentSession = ActionContext.getContext().getSession();
			if(Commonutility.checkempty(ivrTwsipid)){
				currentSession.put("ivrTwsipid", ivrTwsipid);
			}
			if(Commonutility.checkempty(ivrTwsipname)){
				currentSession.put("ivrTwsipname", ivrTwsipname);
			}
		} catch (Exception e) {
			 log.logMessage("Exception found : " + e, "error", createSocietyAction.class);
		} finally {
			 log = null; obj = null; data = null; locObjRecvJson = null; locObjRecvdataJson = null;
		}
		return SUCCESS;
	}

	public String getTownshipData() {
		Log log = null;
		JSONObject obj = null;
		JSONObject data = null;
		try {
			log = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			data.put("servicecode", "SI0027");
			obj.put("uniqTownShipIdEdit", uniqTownShipIdEdit);
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("townShipMgmt.management.edit.view.action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = json.getString("statuscode");
			if (statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")) {	
				JSONObject json_data = json.getJSONObject("data");
				townShipMst.setCountryName(json_data.getString("country"));
				townShipMst.setStateName(json_data.getString("state"));
				townShipMst.setCityName(json_data.getString("city"));
				townShipMst.setPinCode(json_data.getInt("pincode")+"");
				townShipMst.setAddress(json_data.getString("address"));
				
				townShipMst.setInvrCountryid(json_data.getString("countryid"));
				townShipMst.setInvrStateid(json_data.getString("stateid"));
				townShipMst.setInvrCityid(json_data.getString("cityid"));
//				townShipMst.setInvrPincodeid(json_data.getString("pincodeid"));
			}
		} catch (Exception e) {			
			log.logMessage("Exception found in getTownshipData Exception : "+ e, "error", createSocietyAction.class);
		} finally {
			log = null; obj = null; data = null;
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

	public String getTownship_List() {
		return township_List;
	}

	public void setTownship_List(String township_List) {
		this.township_List = township_List;
	}

	public MvpSocietyAccTbl getSocietyAcc() {
		return societyAcc;
	}

	public void setSocietyAcc(MvpSocietyAccTbl societyAcc) {
		this.societyAcc = societyAcc;
	}

	/**
	 * @return the ivrTwsipid
	 */
	public String getIvrTwsipid() {
		return ivrTwsipid;
	}

	/**
	 * @param ivrTwsipid the ivrTwsipid to set
	 */
	public void setIvrTwsipid(String ivrTwsipid) {
		this.ivrTwsipid = ivrTwsipid;
	}

	/**
	 * @return the ivrTwsipname
	 */
	public String getIvrTwsipname() {
		return ivrTwsipname;
	}

	/**
	 * @param ivrTwsipname the ivrTwsipname to set
	 */
	public void setIvrTwsipname(String ivrTwsipname) {
		this.ivrTwsipname = ivrTwsipname;
	}

}
