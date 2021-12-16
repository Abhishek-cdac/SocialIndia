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
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class societyEditAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	
	private Common commonObj = new CommonDao();
	private SocietyMstTbl societyMst = new SocietyMstTbl(0, null, null, null,null, null, null,0,null);
	List<SocietyWingsDetailTbl> societyWingList=new ArrayList<SocietyWingsDetailTbl>();
	MvpSocietyAccTbl societyAcc=new MvpSocietyAccTbl();
	private int uniqSocietyId;
	private int townshipId;
	private int listCount;
	private String emailId;
	private String isdCode;
	private String mobileNo;
	private String township_List;
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private AlertVo alert = new AlertVo();
	
	public String execute() {
		Log log = null;
		JSONObject lvrRqstjsonobj = null;	
		JSONObject lvrRespjsonobj = null;
		JSONObject json_data = null;
		JSONArray ar = null;
		try {
			lvrRqstjsonobj = new JSONObject();	
			log = new Log();
			
			System.out.println("Step 1 : Society single select for edit will called.[Start] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			log.logMessage("Step 1 : Society single select for edit will called.[Start]", "info", societyEditAction.class);
			Map currentSession = ActionContext.getContext().getSession();
			if ((uniqSocietyId == 0)&& currentSession.get("currentsession_uniqSocietyId") != null) {
				uniqSocietyId = (Integer) currentSession.get("currentsession_uniqSocietyId");
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_uniqSocietyId",uniqSocietyId);
			}	
			lvrRqstjsonobj.put("servicecode", "SI0033");
			obj.put("uniqSocietyId", uniqSocietyId);
			lvrRqstjsonobj.put("data", obj);
			String jsonTextFinal = lvrRqstjsonobj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("society.management.edit.modify.view.action");
			log.logMessage("Step 2 : Service Url: "+ finalUrl, "info", societyEditAction.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Return response : "+response);
			lvrRespjsonobj = new JSONObject(response);		
			String statusCode = lvrRespjsonobj.getString("statuscode");
			if (statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")) {
				json_data = lvrRespjsonobj.getJSONObject("data");
				societyMst.setTownshipName(json_data.getString("townshipname"));
				societyMst.setActivationKey(json_data.getString("activationkey"));
				townshipId = json_data.getInt("townshipId");
				societyMst.setSocietyName(json_data.getString("societyname"));
				societyMst.setNoOfMembers(json_data.getInt("noofmember"));
				societyMst.setCountryName(json_data.getString("country"));
				societyMst.setStateName(json_data.getString("state"));
				societyMst.setCityName(json_data.getString("city"));
				societyMst.setPinCode(json_data.getInt("postalcode")+"");
				societyMst.setAddress(json_data.getString("address"));
				societyMst.setSocietyId(json_data.getInt("societyid"));
				societyMst.setNoOfBlocksWings(json_data.getString("noofblockswings"));
				societyMst.setRegisterNo(json_data.getString("registerno"));
				societyMst.setColourCode(json_data.getString("societycolourcode"));
				societyMst.setLogoImage(json_data.getString("societylogoimage"));
				societyMst.setIcoImage(json_data.getString("societyicoimage"));
				societyMst.setImprintName(json_data.getString("imprintname"));
				societyAcc.setBankAccNo(json_data.getString("accountno"));
				societyAcc.setBankName(json_data.getString("bankname"));
				societyAcc.setBankAccName(json_data.getString("accountname"));
				societyAcc.setIfscCode(json_data.getString("ifsccode"));
				
				societyMst.setGstin(json_data.getString("gstin"));
				societyMst.setHsn(json_data.getString("hsn"));
				
				emailId = json_data.getString("emailid");
				isdCode = json_data.getString("isdcode");
				mobileNo = json_data.getString("mobileno");
				Commonutility.toWriteConsole("mobileNo" + mobileNo);
				Commonutility.toWriteConsole("isdCode" + isdCode);
				societyMst.setIvrSmobnum(json_data.getString("mobileno"));
				ar = json_data.getJSONArray("societyWingsName");
				JSONObject jsonList = new JSONObject();
				if (ar != null && ar.length() > 0) {
					for (int i = 0; i < ar.length(); i++) {
						jsonList = null;
						jsonList = ar.getJSONObject(i);
						String societywingsname = jsonList.getString("societywingsname");
						societyWingList.add(new SocietyWingsDetailTbl(societywingsname));
						listCount = societyWingList.size();
					}
				}
			}
			System.out.println("Step 3 : Society single select for edit.[End] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			log.logMessage("Step 3 : Society single select for edit.[End]", "info", societyEditAction.class);
		} catch (Exception e) {
			System.out.println("Step -1 : Exception found societyEditAction.class : "+ e);
			log.logMessage("Step -1 : Exception found : " + e, "error", societyEditAction.class);
		} finally {
			lvrRqstjsonobj = null; lvrRespjsonobj = null; json_data = null; ar = null;
		}
		return SUCCESS;
	}
	
	public String delete() {		
		int result = -1;
		JSONObject obj = null;
		try {
			
			System.out.println("Step 1 : Society delete called.[Start]");
			Map currentSession = ActionContext.getContext().getSession();
			if ((uniqSocietyId == 0)&& currentSession.get("currentsession_uniqSocietyId") != null) {
				uniqSocietyId = (Integer) currentSession.get("currentsession_uniqSocietyId");
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_uniqSocietyId",uniqSocietyId);
			}
			
			if (uniqSocietyId != 0 && uniqSocietyId > 0) {
				
				Map sessionMap = ActionContext.getContext().getSession();				
				
				JSONObject finaljj =new JSONObject();
				finaljj.put("servicecode", "SI4005");
				
				obj = new JSONObject();
				obj.put("uniqSocietyId", uniqSocietyId+"");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				
				finaljj.put("data", obj);
				
				String jsonTextFinal = finaljj.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socyteamanagement.delete.socytea");
				
				String response = commonObj.jsonRequest(finalUrl, temp);
				
				JSONObject json = new JSONObject(response);
				JSONObject json_data = json.getJSONObject("data");				
				result = json_data.getInt("resultFlag");

				String statusCode = (String) json.get("statuscode");
				String lvrRspmsg = (String)Commonutility.toHasChkJsonRtnValObj(json, "message");
				if ((statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00"))) {
					if (result < 0) {
						alert.setCls("danger");
						if(Commonutility.checkempty(lvrRspmsg)){
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg("Socytea not deactivated. Error");
						}
						alertList.add(alert);
						return "input";
					} else {
						alert.setCls("success");
						if(Commonutility.checkempty(lvrRspmsg)){
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg("Socytea deactivated successfully.");
						}						
						alertList.add(alert);
						return "success";
					}
				} else {
					alert.setCls("danger");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("Text.customerreg.key.error"));
					}					
					alertList.add(alert);
					return "input";
				}
			}
		} catch (Exception ex) {
			alert.setCls("danger");
			alert.setMsg("Socytea not deactivated. Error");
			alertList.add(alert);
			return "error";
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

	public List<SocietyWingsDetailTbl> getSocietyWingList() {
		return societyWingList;
	}
	public void setSocietyWingList(List<SocietyWingsDetailTbl> societyWingList) {
		this.societyWingList = societyWingList;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
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

	public String getIsdCode() {
		return isdCode;
	}

	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
