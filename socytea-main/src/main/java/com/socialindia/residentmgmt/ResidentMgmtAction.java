package com.socialindia.residentmgmt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.login.persistense.MvpFamilymbrTbl;

public class ResidentMgmtAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserMasterTblVo restRegObj = new UserMasterTblVo(null,null,null,null,null,null,null,null,0,null,null,null,null,0,null);
	List<FlatMasterTblVO> FlatList = new ArrayList<FlatMasterTblVO>();
	private List<MvpFamilymbrTbl> userFamilyList = new ArrayList<MvpFamilymbrTbl>();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();	
	private String township_List;
	private String[] mySelect;
	private String[] myselect1;
	private String famName;
	private String famisdcode;
	private String famMobileNo;
	private String famEmailId;
	private Integer deleteresidentid;
	private String townshipID;
	private String societyID;
	private String fammemtype;
	private String famprfaccess;
	private String memtypechk;
	private String prfaccesschk;
	private String fmbrMtype_str;
	private String famprfaccess_str;
	private String fmemberuniqueid;	
	private String ivrMobileno;
	private String ivrMobcheckflag;
	
	private String ivrTwsipid;//New Committee creation via shortcut from society mgmt
	private String ivrTwsipname; //New Committee creation via shortcut from society mgmt
	private String ivrSwityid;//New Committee creation via shortcut from society mgmt
	private String ivrSwityname;//New Committee creation via shortcut from society mgmt
	public String getPrfaccesschk() {
		return prfaccesschk;
	}
	public void setPrfaccesschk(String prfaccesschk) {
		this.prfaccesschk = prfaccesschk;
	}	
	public String execute(){
		
		Map currentSession = ActionContext.getContext().getSession();
		if(Commonutility.checkempty(ivrTwsipid)){
			currentSession.put("ivrTwsipid", ivrTwsipid);
		}
		if(Commonutility.checkempty(ivrTwsipname)){
			currentSession.put("ivrTwsipname", ivrTwsipname);
		}	
		if(Commonutility.checkempty(ivrSwityid)){
			currentSession.put("ivrSwityid", ivrSwityid);
		}
		if(Commonutility.checkempty(ivrSwityname)){
			currentSession.put("ivrSwityname", ivrSwityname);
		}	
		
		return SUCCESS;
	}
	
	public String townshiplist() {
		Log log = null;
		JSONObject obj = null;
		JSONObject data = null;
		Common commonObj = null;
		JSONObject locObjRecvJson = null;
		JSONObject locObjRecvdataJson = null;
		try {
			obj = new JSONObject();
			data = new JSONObject();
			log = new Log();
			commonObj = new CommonDao();
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
		    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
		    	  ivrmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
		    	  ivrstatuscode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
		    	  locObjRecvdataJson = (JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");
		    	  JSONArray jary = (JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"datalst");			
		    	  String fin="";
		    	  JSONArray jsrcountry= null;JSONObject jbcountry=null;
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
						log.logMessage("Exception found : "+ e, "error", ResidentMgmtAction.class);
					} finally {
						 jsrcountry= null; jbcountry=null;
					}    			
    		  }
		 }
    								
		}catch (Exception e) {
			log.logMessage("Exception found : "+ e, "error", ResidentMgmtAction.class);
		} finally {
			log = null; obj = null; data = null; commonObj = null; locObjRecvJson = null; locObjRecvdataJson = null;
		}
		return SUCCESS;
	}

	public String residentmgmtaddaction() {
		Log log = null;
		JSONObject obj = null;
		JSONObject data = null;
		Common commonObj = null;
		JSONArray jaflat = null;
		JSONArray lvrblcknamejary = null;
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			obj = new JSONObject();
			data = new JSONObject();
			log = new Log();
			commonObj = new CommonDao();
			log.logMessage("Step 1 : Resident Add Called [Start]", "info", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step 1 : Resident Add Called [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			
			obj.put("emailid", restRegObj.getEmailId());
			obj.put("isdcode", restRegObj.getIsdCode());
			obj.put("mobno", String.valueOf(restRegObj.getMobileNo()));
			/*obj.put("townshipname", restRegObj.getTownshipName());
			obj.put("societyname", restRegObj.getSocietyName());*/
			Integer townval = (int) sessionMap.get("townshipId");
			if(townval==null){
				obj.put("townshipname", restRegObj.getTownshipName());	
			}else{
				obj.put("townshipname", "");
			}
			int societyval = (int) sessionMap.get("sSoctyId");
			if(societyval==-1){
				obj.put("societyname", restRegObj.getSocietyName());
			}else{
				obj.put("societyname", String.valueOf(societyval));
			}
			obj.put("idcardtyp", String.valueOf(restRegObj.getIdCardType()));
			obj.put("idcardno", restRegObj.getIdProofNo());
			obj.put("city", String.valueOf(restRegObj.getCityId()));
			obj.put("sate", String.valueOf(restRegObj.getStateId()));
			obj.put("country", String.valueOf(restRegObj.getCountryCode()));
			obj.put("pstlid", String.valueOf(restRegObj.getPinCode()));
			obj.put("occupation", restRegObj.getOccupation());
			obj.put("familymem", String.valueOf(restRegObj.getMembersInFamily()));
			obj.put("bloodtyp", restRegObj.getBloodType());
			obj.put("add1", restRegObj.getAddress1());
			obj.put("add2", restRegObj.getAddress2());
			obj.put("frstname", restRegObj.getFirstName());
			obj.put("lastname", restRegObj.getLastName());
			obj.put("gender", restRegObj.getGender());
			obj.put("dob", String.valueOf(restRegObj.getDob()));
			obj.put("accesschannel", String.valueOf(restRegObj.getAccessChannel()));
			obj.put("noofblocks", String.valueOf(restRegObj.getNoOfBlocks()));
			obj.put("noofflats", String.valueOf(restRegObj.getNoofFlats()));
			obj.put("status", String.valueOf("1"));
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			obj.put("currentloginid", String.valueOf(sessionMap.get("USERID")));			
			String[] lvrBlcknmestrary = mySelect[0].split("!_!");
			lvrblcknamejary = new JSONArray();
			for (int i = 0; i < lvrBlcknmestrary.length; i++) {
				lvrblcknamejary.put(lvrBlcknmestrary[i]);
			}
			lvrBlcknmestrary = null;
			obj.put("blacknameJary", lvrblcknamejary);
			
			String[] lvrFlatnameary = myselect1[0].split("!_!");
			jaflat = new JSONArray();
			for (int j = 0; j < lvrFlatnameary.length; j++) {
				jaflat.put(lvrFlatnameary[j]);
			}
			lvrFlatnameary = null;
			
			obj.put("flatnameJary", jaflat);
			obj.put("famName", famName);
			obj.put("famMobileNo", famMobileNo);
			obj.put("famEmailId", famEmailId);
			obj.put("famisdcode", famisdcode);
			int fmembercnt = restRegObj.getMembersInFamily();
			obj.put("fammemtype", fammemtype);
			obj.put("famprfaccess", famprfaccess);
			data.put("servicecode", "SI7001");
			data.put("servicefor", "1");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.resident.residentaddaction");
			log.logMessage("Step 2 : Resident Add service url : " + finalUrl, "info", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step 2 : Resident Add service url : " + finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Response : " + response);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			String respCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "respcode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			//JSONObject json_data = json.getJSONObject("data");
			log.logMessage("Step 3 : Resident Add Finished [End]", "info", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step 3 : Resident Add Finished [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			if (statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00") && respCode.equalsIgnoreCase("R0101")) {
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("success.resident.created"));
				}				
				alertList.add(alert);
				// while shortcut townshipmgmt via create Society
				if( (String) sessionMap.get("ivrTwsipid")!=null){
					sessionMap.remove("ivrTwsipid");
				}
				if( (String) sessionMap.get("ivrTwsipname")!=null){
					sessionMap.remove("ivrTwsipname");	
				}		
				if( (String) sessionMap.get("ivrSwityid")!=null){
					sessionMap.remove("ivrSwityid");
				}
				if( (String) sessionMap.get("ivrSwityname")!=null){
					sessionMap.remove("ivrSwityname");	
				}				
				sessionMap.put("alertList", alertList);				
				return "success";
			} else {
				addFieldError("usercreateObj.mobileNo", getText("user.mobile.no.already.register"));
				//alert.setCls("danger");
				//alert.setMsg(getText("error.resident.created"));
				//alertList.add(alert);
				return "input";
			}
		} catch (Exception ex) {
			log.logMessage("Step -1 : Resident Add Exception found : " + ex, "error", ResidentMgmtAction.class);
			log.logMessage("Step -1 : Data " + data, "error", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step -1 : Resident Add Exception found ResidentMgmtAction.residentmgmtaddaction() : " + ex);
			alert.setCls("danger");
			alert.setMsg(getText("error.resident.created"));					
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally {
			 log = null; obj = null;  data = null; commonObj = null; jaflat = null; lvrblcknamejary = null;
		}
				
	}

	public String deleteResidentActionFunction() {		
		String result = "";
		CommonDao commonObj = null;
		JSONObject finaljj = null;
		JSONObject json = null;
		JSONObject json_data = null;
		Log logwrite = null;
		try {
			logwrite = new Log();
			logwrite.logMessage("Step 1 : Resident Delete Called [Start]", "info", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step 1 : Resident Delete Called [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			if (deleteresidentid != 0 && deleteresidentid > 0) {
				commonObj = new CommonDao();
				finaljj = new JSONObject();
				Map sessionMap = ActionContext.getContext().getSession();			
				JSONObject dataJson = new JSONObject();
				dataJson.put("restid", String.valueOf(deleteresidentid));
				dataJson.put("reststatus", "1");

				finaljj.put("servicecode", "SI7002");
				finaljj.put("servicefor", "4");// deactivate
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);

				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.resident.residentdeleteaction");
				logwrite.logMessage("Step 2 : Resident Delete Called Service Url : "+finalUrl, "info", ResidentMgmtAction.class);
				Commonutility.toWriteConsole("Step 2 : Resident Delete Called Service Url : "+finalUrl);
				String response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				json_data = json.getJSONObject("data");
				result = json_data.getString("status");
				String statusCode = (String)Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
				String respCode = (String)Commonutility.toHasChkJsonRtnValObj(json, "respcode");
				String lvrRspmsg = (String)Commonutility.toHasChkJsonRtnValObj(json, "message");
				logwrite.logMessage("Step 3 : Resident Delete Called [End]", "info", ResidentMgmtAction.class);
				Commonutility.toWriteConsole("Step 3 : Resident Delete Called [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				if ((statusCode.equalsIgnoreCase("00") ||  statusCode.equalsIgnoreCase("00")) && respCode.equalsIgnoreCase("R0105")) {
					if (result.equalsIgnoreCase("success")) {
						alert.setCls("success");
						if(Commonutility.checkempty(lvrRspmsg)){
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg(getText("success.resident.deleted"));
						}						
						alertList.add(alert);
						return "success";
					} else {
						alert.setCls("error");						
						if(Commonutility.checkempty(lvrRspmsg)){
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg(getText("error.resident.deleted"));
						}
						alertList.add(alert);
						return "input";
					}
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Text.customerreg.key.error"));
					alertList.add(alert);
					return "input";
				}
			}
		} catch (Exception ex) {
			logwrite.logMessage("Step -1: Resident Delete Exception Found : ", "error", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step -1 : Resident Delete Exception Found ResidentMgmtAction.class  : " + ex + " - Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));			
			alert.setCls("danger");
			alert.setMsg(getText("Exception in error deletion."));
			alertList.add(alert);
			return "error";
		} finally {
			commonObj = null; finaljj = null; json = null; json_data = null; logwrite = null;
			System.gc();
		}
		return SUCCESS;

}

	public String viewresidentActionFunction() {
		CommonDao commonObj = null;
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject locObjRecvdataJson = null;// Receive Data Json
		JSONObject locObjRspdataJson = null;// Response Data Json
		Log logwrite = null;
		try {
			commonObj = new CommonDao();
			logwrite =  new Log();
			logwrite.logMessage("Step 1 : Resident View Called viewresidentActionFunction() [Start]", "info", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step 1 : Resident View Called viewresidentActionFunction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			Map currentSession = ActionContext.getContext().getSession();
			if (deleteresidentid == null) {
				if ((deleteresidentid == null || deleteresidentid == -1 || deleteresidentid == 0) && currentSession.get("currentsession_deleteresidentid") != null) {
					String stfiddd = (String) currentSession.get("currentsession_deleteresidentid");
					deleteresidentid = Integer.parseInt(stfiddd);
				}
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_deleteresidentid",String.valueOf(deleteresidentid));

			}	
			String idcardtypname = "";
			String verifysts = "";
			JSONObject dataJson = new JSONObject();
			dataJson.put("restid", String.valueOf(deleteresidentid));
			dataJson.put("reststatus", "1");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI7003");
			finaljj.put("servicefor", "3");// select
			finaljj.put("data", dataJson);			
			String jsonTextFinal = finaljj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.resident.residentviewaction");
			logwrite.logMessage("Step 2 : Resident View Called Service API Url : "+finalUrl, "info", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step 2 : Resident View Called Service API Url : "+finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response_____________________________________"+response);
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					locObjRecvdataJson = locObjRecvJson.getJSONObject("data");
					JSONArray blacksnamearr = new JSONArray();
					JSONArray ar1 = new JSONArray();
					societyID = (String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"society_id");					
					String wings_name="";
					String flat_name="";
					String flatusr_id="";
					blacksnamearr = (JSONArray) Commonutility .toHasChkJsonRtnValObj(locObjRecvdataJson, "jArry_wing_flat");					
				if (blacksnamearr != null && blacksnamearr.length() > 0) {
					for(int j = 0; j < blacksnamearr.length(); j++){
						wings_name=(String)Commonutility.toHasChkJsonRtnValObj(blacksnamearr.getJSONObject(j), "wings_name");
						flat_name =(String)Commonutility.toHasChkJsonRtnValObj(blacksnamearr.getJSONObject(j), "flat_no");
						FlatList.add(new FlatMasterTblVO(wings_name,flat_name));
					}					
				}
				
					ar1 = locObjRecvdataJson.getJSONArray("userfamilydetail");					
					if(ar1!=null && ar1.length()>0){
						for (int i = 0; i < ar1.length(); i++) {							
						String childmobile=(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "childmobile");
						String childemail=(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "childemail");
						String childname=(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "childname");
						String childisd =(String)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbrisd");
						System.out.println("childisd --------------- "+childisd);
						String fmbrmembertype = String.valueOf((Integer)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbrmemtype"));
						String fmbrprfaccess = String.valueOf((Integer)Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbrprfaccess"));
						String fmbrUniqid = (String) Commonutility.toHasChkJsonRtnValObj(ar1.getJSONObject(i), "fmbruniqid");
						userFamilyList.add(new MvpFamilymbrTbl(childname,childemail,childmobile,childisd,fmbrmembertype,fmbrprfaccess,fmbrUniqid));						
					}
				 }
				 
					restRegObj.setTownshipName((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"townshipname"));
					townshipID =(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"township_id");
					restRegObj.setSocietyName((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"societyname"));
					
					restRegObj.setFirstName((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"firstname"));
					restRegObj.setLastName((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"lastname"));
					restRegObj.setEmailId((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"email"));
					restRegObj.setIsdCode((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"isd"));
					restRegObj.setMobileNo((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"mobno"));
					restRegObj.setIdCardTypeName((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"idcard_typname"));
					String idcard=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"idcard_typ");
					if (idcard!=null && !idcard.equalsIgnoreCase("") && !idcard.equalsIgnoreCase("null")) {
						restRegObj.setIdCardType(Integer.parseInt(idcard));
					}

					restRegObj.setIdProofNo((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"idproofno"));
					String gender_txt = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"gender");
					if (gender_txt!= null && gender_txt.equalsIgnoreCase("1")) {
						restRegObj.setGender_txt("Male");
					} else if (gender_txt!= null && gender_txt.equalsIgnoreCase("2")) {
						restRegObj.setGender_txt("FeMale");
					} else if (gender_txt!= null && gender_txt.equalsIgnoreCase("3")) {
						restRegObj.setGender_txt("Others");
					} else {
						restRegObj.setGender_txt("");
					}
					restRegObj.setGender((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"gender"));
					restRegObj.setDob((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"dob"));
					String bloodgroup=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"bloodgroup");
					restRegObj.setBloodType(bloodgroup);
					restRegObj.setAccessChannel(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"accesschennel")));
					restRegObj.setAddress1((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"address1"));
					restRegObj.setNoOfBlocks((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"noofblocks"));
					restRegObj.setNoofFlats((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"noofflats"));
					restRegObj.setAddress2((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"address2"));
					restRegObj.setCountryname((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"rest_cntryName"));
					restRegObj.setCityname((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"rest_cityName"));
					restRegObj.setStatename((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"rest_stateName"));
//					restRegObj.setPstlcodename((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"rest_pincodeName"));
					restRegObj.setOccupation((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"occupation"));
					String membeinfmly = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"member");
					if(membeinfmly != null && (membeinfmly.equalsIgnoreCase("0") || membeinfmly.equalsIgnoreCase("-1") || membeinfmly.equalsIgnoreCase("") || membeinfmly.equalsIgnoreCase("null") )){
						restRegObj.setMembersInFamily(1);
					} else{
						restRegObj.setMembersInFamily(Integer.parseInt((String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"member")));
					}
					if (ar1 != null) {
						restRegObj.setMembersInFamily(ar1.length());
					}
					
					String countrycode=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_rest_cntry");
					if (!countrycode.equalsIgnoreCase("")) {	
						restRegObj.setCountryCode(Integer.parseInt(countrycode));
					}
					String statecode=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_rest_stateid");
					if(statecode!=null && !statecode.equalsIgnoreCase("") && !statecode.equalsIgnoreCase("null")) {
						restRegObj.setStateId(Integer.parseInt(statecode));	
					}
					String citycode=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_rest_cityid");
					if(citycode!=null && !citycode.equalsIgnoreCase("") && !citycode.equalsIgnoreCase("null")) {
						restRegObj.setCityId(Integer.parseInt(citycode));
					}
					String lvrpstlid = (String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_rest_pstlid");
					if(lvrpstlid!=null && !lvrpstlid.equalsIgnoreCase("null") && !lvrpstlid.equalsIgnoreCase("")){
						restRegObj.setPinCode(lvrpstlid);
					}
					
			}
		}								
			logwrite.logMessage("Step 3 : Resident Single select View Called viewresidentActionFunction() [End]", "info", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step 3 : Resident Single select  View Called viewresidentActionFunction() [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
		
	} catch (Exception ex) {
		ex.printStackTrace();
		logwrite.logMessage("Step -1 : Resident Single select Called viewresidentActionFunction() Exception : "+ex, "info", ResidentMgmtAction.class);
		Commonutility.toWriteConsole("Step -1 : Resident Single select Called viewresidentActionFunction() Exception : "+ ex +" \n Time : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
	} finally {
		 commonObj = null;
		 locObjRecvJson = null;// Receive String to json
		 locObjRecvdataJson = null;// Receive Data Json
		 locObjRspdataJson = null;// Response Data Json
		 logwrite = null;
	}
	return SUCCESS;

}

	public String updatedresidentActionFunction() {
		CommonDao commonObj = null;
		Log logwrite = null;
		JSONObject json = null;
		JSONObject json_data = null;
		JSONObject dataJson = null;
		JSONArray lvrBlckjsonary = null;
		JSONArray lvrFlatjsonary = null;
		JSONObject finaljj = null;
		try {
			logwrite = new Log();
			commonObj = new CommonDao();
			logwrite.logMessage("Step 1 : Resident Update Called updatedresidentActionFunction() [Start]", "info", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step 1 : Resident Update Called updatedresidentActionFunction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			boolean result = false;			
			Map sessionMap = ActionContext.getContext().getSession();
		
			dataJson = new JSONObject();
			dataJson.put("restid",String.valueOf(deleteresidentid));
			Integer townval=(int)sessionMap.get("townshipId");
			if(townval==null){
				dataJson.put("townshipname", restRegObj.getTownshipName());	
			}else{
				dataJson.put("townshipname", "");	
			}
			int societyval=(int) sessionMap.get("sSoctyId");
			if(societyval==-1){
				dataJson.put("societyname", restRegObj.getSocietyName());
			}else{
				dataJson.put("societyname", String.valueOf(societyval));
			}
			dataJson.put("isdcode", restRegObj.getIsdCode());
			dataJson.put("mobno", restRegObj.getMobileNo());
			dataJson.put("emailid", restRegObj.getEmailId());
			dataJson.put("idcard_typ", Commonutility.toCheckNullEmpty(restRegObj.getIdCardType()));
			dataJson.put("idproofno", restRegObj.getIdProofNo());
			dataJson.put("fname", restRegObj.getFirstName());
			dataJson.put("lname", restRegObj.getLastName());
			dataJson.put("gender", restRegObj.getGender());
			dataJson.put("dob", restRegObj.getDob());
			dataJson.put("accesschannel", String.valueOf(restRegObj.getAccessChannel()));
			dataJson.put("noofblocks", restRegObj.getNoOfBlocks());
			dataJson.put("noofflats", restRegObj.getNoofFlats());
			dataJson.put("sate", String.valueOf(restRegObj.getStateId()));
			dataJson.put("city", String.valueOf(restRegObj.getCityId()));
			dataJson.put("country", String.valueOf(restRegObj.getCountryCode()));
			dataJson.put("postalcode",String.valueOf(restRegObj.getPinCode()));
			dataJson.put("add1", restRegObj.getAddress1());
			dataJson.put("add2", restRegObj.getAddress2());
			dataJson.put("occupation", restRegObj.getOccupation());
			dataJson.put("fmember", String.valueOf(restRegObj.getMembersInFamily()));	
			dataJson.put("bloodgrp", restRegObj.getBloodType());
			String[] lvrBlknamestrary = mySelect[0].split("!_!");
			lvrBlckjsonary = new JSONArray();
			for (int i = 0; i < lvrBlknamestrary.length; i++) {
				lvrBlckjsonary.put(lvrBlknamestrary[i]);
			}
			lvrBlknamestrary = null;
			dataJson.put("blacknameJary", lvrBlckjsonary);
			
			String[] lvrFlatNumstrary =myselect1[0].split("!_!");
			lvrFlatjsonary = new JSONArray();
			for (int j = 0; j < lvrFlatNumstrary.length; j++) {
				lvrFlatjsonary.put(lvrFlatNumstrary[j]);	
			}
			lvrFlatNumstrary = null;
			dataJson.put("flatnameJary", lvrFlatjsonary);
			Commonutility.toWriteConsole("######################[START]############################");
			Commonutility.toWriteConsole("famName : "+famName);
			Commonutility.toWriteConsole("famMobileNo : "+famMobileNo);
			Commonutility.toWriteConsole("famEmailId : "+famEmailId);
			Commonutility.toWriteConsole("famisdcode : "+famisdcode);
			Commonutility.toWriteConsole("fammemtype : "+fmbrMtype_str);
			Commonutility.toWriteConsole("famprfaccess : "+famprfaccess_str);
			Commonutility.toWriteConsole("######################[End]############################");
			dataJson.put("famName",famName);
			dataJson.put("famMobileNo",famMobileNo);
			dataJson.put("famEmailId",famEmailId);
			dataJson.put("famisdcode",famisdcode);
			int fmembercnt=restRegObj.getMembersInFamily();
			dataJson.put("fammemtype",fmbrMtype_str);
			dataJson.put("famprfaccess",famprfaccess_str);
			dataJson.put("fmemberuniqueid", fmemberuniqueid);//family member unique id
			/*dataJson.put("fammemtype",fmbrMtype);
			dataJson.put("famprfaccess",fmbrPrfAccess);*/
			dataJson.put("status", String.valueOf("1"));
			dataJson.put("entryby", String.valueOf(sessionMap.get("USERID")));
			dataJson.put("currentloginid",String.valueOf(sessionMap.get("USERID")));
			finaljj = new JSONObject();
			finaljj.put("servicecode", "SI7004");
			finaljj.put("servicefor", "2");// Edit
			finaljj.put("data", dataJson);
			//String srvcUrl="http://192.168.1.19:8087/socialindiaservices/lbrupdates?ivrservicefor=2";
			String jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.residentmgmt.residentupdatedaction");
			logwrite.logMessage("Step 2 : Resident Update updatedresidentActionFunction() [End]", "info", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step 2 : Resident Single select  View Called updatedresidentActionFunction() [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			String response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			json_data = json.getJSONObject("data");
			String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			logwrite.logMessage("Step 3 : Resident Update Called updatedresidentActionFunction() [End]", "info", ResidentMgmtAction.class);
			Commonutility.toWriteConsole("Step 3 : Resident Update Called updatedresidentActionFunction() [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			if (statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00") && (respCode.equalsIgnoreCase("R0103"))) {			
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("sucess.resident.updated"));
				}				
				alertList.add(alert);
				return "success";			 
			} else {			
				alert.setCls("error");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("error.resident.updated"));
				}					
				alertList.add(alert);
				return "input";
			}		
	} catch (Exception ex) {
		logwrite.logMessage("Step -1 : Resident Update Called updatedresidentActionFunction() [End]", "info", ResidentMgmtAction.class);
		Commonutility.toWriteConsole("Step -1 : Resident Update select  View Called updatedresidentActionFunction() [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
		alert.setCls("error");
		alert.setMsg(getText("Error in user updating"));
		alertList.add(alert);
		return "error";
	} finally {
		 commonObj = null; logwrite = null; json = null;  json_data = null; dataJson = null;  lvrBlckjsonary = null; lvrFlatjsonary = null; finaljj = null;
	}
}

	public String toValidateMobileno(){
		String lvrRtnval = "";
		Log lvrLog = null;
		JSONObject lvrRqstjson = null, lvrRqstdatajson = null;
		Common commonObj = null;
		JSONObject locObjRecvJson = null;
		JSONObject locObjRecvdataJson = null;
		Map sessionMap = null;
		try {
			sessionMap = ActionContext.getContext().getSession();lvrLog = new Log();
			Commonutility.toWriteConsole("Step 1 : toValidateMobileno called in ResidentMgmtAction.class [Start]");
			lvrLog.logMessage("Step 1 : toValidateMobileno called in ResidentMgmtAction.class [Start]", "info", ResidentMgmtAction.class);
			commonObj = new CommonDao();
			lvrRqstjson = new JSONObject();
			lvrRqstdatajson = new JSONObject();
			Integer societyval = (Integer) sessionMap.get("sSoctyId");
			if(!Commonutility.checkempty(societyID) && societyval !=null && societyval!=-1){
				societyID = String.valueOf(societyval);
			}
			lvrRqstdatajson.put("mobileno", Commonutility.toCheckNullEmpty(ivrMobileno));
			lvrRqstdatajson.put("townshipid", Commonutility.toCheckNullEmpty(townshipID));
			lvrRqstdatajson.put("societyid", Commonutility.toCheckNullEmpty(societyID));
			lvrRqstdatajson.put("currentloginid", Commonutility.toCheckNullEmpty(sessionMap.get("USERID")));
			lvrRqstjson.put("servicecode", "SI1101");
			lvrRqstjson.put("data", lvrRqstdatajson);
			String jsonTextFinal = lvrRqstjson.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("ajax.mobileno.validation.checkexist");
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
		    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
		    	  ivrmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
		    	  ivrstatuscode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
		    	  locObjRecvdataJson = (JSONObject) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson,"data");	
		    	  String lvrExiststs = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson, "existsflag"); // it return duplicate or not
		    	  Commonutility.toWriteConsole("Step 2 : lvrExiststs : "+lvrExiststs);
		    	  lvrLog.logMessage("Step 2 : lvrExiststs : "+lvrExiststs, "info", ResidentMgmtAction.class);
		    	  if(Commonutility.checkempty(lvrExiststs) && lvrExiststs.equalsIgnoreCase("NotExists")){
		    		  lvrRtnval = "NotExists";
		    		 
		    	  } else {
		    		  lvrRtnval = "Exists";
		    	  }
				} else {
					lvrRtnval = "NotExists";
				}
			} else {
				lvrRtnval = "NotExists";
			}
			Commonutility.toWriteConsole("Step 3 : toValidateMobileno called in ResidentMgmtAction.class [End]"); 
			lvrLog.logMessage("Step 3 : toValidateMobileno called in ResidentMgmtAction.class [End]", "info", ResidentMgmtAction.class);
		} catch(Exception e){
			lvrRtnval = "NotExists";
			Commonutility.toWriteConsole("Step -1 : toValidateMobileno called in ResidentMgmtAction.class [Exception] : "+e);
			lvrLog.logMessage("Step -1 : toValidateMobileno called in ResidentMgmtAction.class [Exception]", "info", ResidentMgmtAction.class);
		} finally {
			
		}
		 ivrMobcheckflag =lvrRtnval; 
		return lvrRtnval;
	}
	public String getFmemberuniqueid() {
		return fmemberuniqueid;
	}
	public void setFmemberuniqueid(String fmemberuniqueid) {
		this.fmemberuniqueid = fmemberuniqueid;
	}
	public UserMasterTblVo getRestRegObj() {
		return restRegObj;
	}

	public void setRestRegObj(UserMasterTblVo restRegObj) {
		this.restRegObj = restRegObj;
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

	public String getTownship_List() {
		return township_List;
	}

	public void setTownship_List(String township_List) {
		this.township_List = township_List;
	}

	public String[] getMySelect() {
		return mySelect;
	}

	public void setMySelect(String[] mySelect) {
		this.mySelect = mySelect;
	}

	public String[] getMyselect1() {
		return myselect1;
	}

	public void setMyselect1(String[] myselect1) {
		this.myselect1 = myselect1;
	}

	public Integer getDeleteresidentid() {
		return deleteresidentid;
	}

	public void setDeleteresidentid(Integer deleteresidentid) {
		this.deleteresidentid = deleteresidentid;
	}

	public List<FlatMasterTblVO> getFlatList() {
		return FlatList;
	}

	public void setFlatList(List<FlatMasterTblVO> flatList) {
		FlatList = flatList;
	}

	public String getTownshipID() {
		return townshipID;
	}

	public void setTownshipID(String townshipID) {
		this.townshipID = townshipID;
	}

	public String getSocietyID() {
		return societyID;
	}

	public void setSocietyID(String societyID) {
		this.societyID = societyID;
	}

	public String getFamName() {
		return famName;
	}

	public void setFamName(String famName) {
		this.famName = famName;
	}

	public String getFamMobileNo() {
		return famMobileNo;
	}

	public void setFamMobileNo(String famMobileNo) {
		this.famMobileNo = famMobileNo;
	}

	public String getFamEmailId() {
		return famEmailId;
	}

	public void setFamEmailId(String famEmailId) {
		this.famEmailId = famEmailId;
	}

	public List<MvpFamilymbrTbl> getUserFamilyList() {
		return userFamilyList;
	}

	public void setUserFamilyList(List<MvpFamilymbrTbl> userFamilyList) {
		this.userFamilyList = userFamilyList;
	}

	public String getFamisdcode() {
		return famisdcode;
	}

	public void setFamisdcode(String famisdcode) {
		this.famisdcode = famisdcode;
	}

	public String getFammemtype() {
		return fammemtype;
	}

	public void setFammemtype(String fammemtype) {
		this.fammemtype = fammemtype;
	}

	public String getFamprfaccess() {
		return famprfaccess;
	}

	public void setFamprfaccess(String famprfaccess) {
		this.famprfaccess = famprfaccess;
	}

	public String getMemtypechk() {
		return memtypechk;
	}

	public void setMemtypechk(String memtypechk) {
		this.memtypechk = memtypechk;
	}

	public String getFamprfaccess_str() {
		return famprfaccess_str;
	}

	public void setFamprfaccess_str(String famprfaccess_str) {
		this.famprfaccess_str = famprfaccess_str;
	}

	public String getFmbrMtype_str() {
		return fmbrMtype_str;
	}

	public void setFmbrMtype_str(String fmbrMtype_str) {
		this.fmbrMtype_str = fmbrMtype_str;
	}
	/**
	 * @return the ivrMobileno
	 */
	public String getIvrMobileno() {
		return ivrMobileno;
	}
	/**
	 * @param ivrMobileno the ivrMobileno to set
	 */
	public void setIvrMobileno(String ivrMobileno) {
		this.ivrMobileno = ivrMobileno;
	}
	/**
	 * @return the ivrMobcheckflag
	 */
	public String getIvrMobcheckflag() {
		return ivrMobcheckflag;
	}
	/**
	 * @param ivrMobcheckflag the ivrMobcheckflag to set
	 */
	public void setIvrMobcheckflag(String ivrMobcheckflag) {
		this.ivrMobcheckflag = ivrMobcheckflag;
	}
	public String getIvrTwsipid() {
		return ivrTwsipid;
	}
	public void setIvrTwsipid(String ivrTwsipid) {
		this.ivrTwsipid = ivrTwsipid;
	}
	public String getIvrTwsipname() {
		return ivrTwsipname;
	}
	public void setIvrTwsipname(String ivrTwsipname) {
		this.ivrTwsipname = ivrTwsipname;
	}
	public String getIvrSwityid() {
		return ivrSwityid;
	}
	public void setIvrSwityid(String ivrSwityid) {
		this.ivrSwityid = ivrSwityid;
	}
	public String getIvrSwityname() {
		return ivrSwityname;
	}
	public void setIvrSwityname(String ivrSwityname) {
		this.ivrSwityname = ivrSwityname;
	}
	

}
