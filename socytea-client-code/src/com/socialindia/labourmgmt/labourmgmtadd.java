package com.socialindia.labourmgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.labour.persistance.FeedbackTblVO;
import com.socialindia.labour.persistance.LaborProfileTblVO;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.profileUpdate;
import com.socialindia.vo.MerchantIssueTblVO;

public class labourmgmtadd extends ActionSupport {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	LaborProfileTblVO labRegObj = new LaborProfileTblVO(0, null, null, null, null, null, null, null, null,0.0,null,null,null,null);
	List<FeedbackTblVO> FeedbackList = new ArrayList<FeedbackTblVO>();
	List<LaborProfileTblVO> CatskillsList = new ArrayList<LaborProfileTblVO>();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	//byte imgByt[] = new byte[1024];
	private File staffImage;
	private String staffImageFileName;
	private String staffProfileImage;
	private int staffsessID;
	private String Country_id;
	private String State_id;
	private String City_id;
	private String ID_CARD_TYP;
	private String[] mySelect;
	private Integer deletelaborid;
	private Integer LaboruniqueId;
	private String deletelaborserviceid;
	private String laborName_hidd_txt;
	private String Feedbkby_lname;
	private String Feedbkby_fname;
	private String laborcompanyname;
	private String feedbackDateby;
	private String feedbackimgweb;
	private String feedbackimgmob;
	private String feedbacktext;
	private String feedbackrating;
	private String feedback_id;
	private String feedback_userid;
	private String laborgrpcode;
	private String City_id_name;
	private String categoryskillidval;
	private String offertxt;
	private String offercheck;
	private String issuelistbox;
	private String[] offerissuetxt;
	 private List<String> issueFeatures;
	 private List<MerchantIssueTblVO> issList = new ArrayList<MerchantIssueTblVO>();
	 private String issuetext;
	 private String laborcompanyid;
	private Common commonObj = new CommonDao();

	public String execute() {
		byte imgByt[] = null;
		JSONObject obj = null;
		JSONObject data = null;
		Map sessionMap = null;
		String[] lvrCatNamestrary = null;
		JSONArray lrvCatenameJAry = null;
		String[] lvrCatNamestrary1 = null;
		JSONArray lrvCatenameJAry1 = null;
		Log logwrite = null;
		JSONObject lvrRcvrespjsonobj = null;

		try {
			logwrite = new Log();
			obj = new JSONObject();
			data = new JSONObject();
			sessionMap = ActionContext.getContext().getSession();
			Commonutility.toWriteConsole("****** "+categoryskillidval);
			Commonutility.toWriteConsole("Step 1 : Labor Create Called labourmgmtadd.class [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Labor Create Called [Start]", "info", labourmgmtadd.class);
			//imgByt = profileUpdate.toReadFiletoBytes(staffImage);
			obj.put("name", labRegObj.getIvrBnLBR_NAME());
			obj.put("emailid", labRegObj.getIvrBnLBR_EMAIL());
			obj.put("ISDcode", labRegObj.getIvrBnLBR_ISD_CODE());
			obj.put("mobno", labRegObj.getIvrBnLBR_PH_NO());
			obj.put("cardtyp", String.valueOf(ID_CARD_TYP));
			obj.put("cardno", labRegObj.getIvrBnID_CARD_NO());
			obj.put("laborcompanyname", laborcompanyid);
			obj.put("country", String.valueOf(Country_id));
			obj.put("sate", String.valueOf(State_id));
			obj.put("city", String.valueOf(City_id));
			obj.put("add1", labRegObj.getIvrBnLBR_ADD_1());
			obj.put("add2", labRegObj.getIvrBnLBR_ADD_2());

			obj.put("keyforsrch", labRegObj.getIvrBnKEY_FOR_SEARCH());
			obj.put("verifystatus", labRegObj.getIvrBnVERIFIED_STATUS());
			obj.put("kycname", "");
			obj.put("wktypid", String.valueOf(labRegObj.getWrktypId()));
			obj.put("descrp", labRegObj.getIvrBnLBR_DESCP());
			obj.put("postalcode", String.valueOf(labRegObj.getIvrBnLBR_PSTL_ID()));
			//obj.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
			obj.put("imagename", staffImageFileName);
			if (staffImage!=null) {
				obj.put("lbrimgsrchpath", staffImage.getAbsolutePath());
			} else {
				obj.put("lbrimgsrchpath", "");
			}
			String issuedata=issuelistbox+","+offercheck;
			obj.put("offerisstxt", issuedata);
			obj.put("status", "1");
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			obj.put("experience", labRegObj.getIvrBnLBR_EXPERIENCE());
			obj.put("cost", labRegObj.getIvrBnLBR_COST());
			obj.put("costper", labRegObj.getIvrBnLBR_COSTPER());
			obj.put("cityname", City_id_name);
			lvrCatNamestrary = mySelect[0].split(",");
			lrvCatenameJAry = new JSONArray();
			for (int i = 0; i < lvrCatNamestrary.length; i++) {
				lrvCatenameJAry.put(lvrCatNamestrary[i]);
			}
			lrvCatenameJAry1 = new JSONArray();
			JSONObject jobj =new JSONObject();
			if(!categoryskillidval.equalsIgnoreCase("")&& !categoryskillidval.equalsIgnoreCase("null") && categoryskillidval.length() > 0)
			{
			String[] categoryskillidvalarr=categoryskillidval.split("!_!");
			String[] delimt=null;
				for (int i = 0; i < categoryskillidvalarr.length; i++) {
					jobj = new JSONObject();
					delimt = categoryskillidvalarr[i].split("_");
				 	//String[] splitArray = lvrCatNamestrary1.split("!_!");
				 	/*String str = String.join(",", lvrCatNamestrary1);
					//using iterables
					List<String> list = Arrays.asList(array);
					String joined3 = String.join(",", list);*/
				 	jobj.put("cate_id", Commonutility.toCheckNullEmpty(delimt[0]));
				 	jobj.put("skill_id",   Commonutility.toCheckNullEmpty(delimt[1]));
					lrvCatenameJAry1.put(jobj);
					jobj = null;
				}
				obj.put("cateoryJary", lrvCatenameJAry1);
			}

			//data.put("cateoryJary" , "");
			data.put("servicecode", "R0124");
			data.put("servicefor", "1");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			Commonutility.toWriteConsole("Step 1 : Labor Create  labourmgmtadd.class Service API : " + jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.labormgmt.laboraddaction");
			logwrite.logMessage("Step 2 : Labor Create Service API : " + finalUrl, "info", labourmgmtadd.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRcvrespjsonobj = new JSONObject(response);
			String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
			String respCode = (String) lvrRcvrespjsonobj.get("respcode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(lvrRcvrespjsonobj, "message");
			JSONObject json_data = lvrRcvrespjsonobj.getJSONObject("data");
			Commonutility.toWriteConsole("Step 3: Labor Create Called labourmgmtadd.class [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 3 : Labor Create Called [End]", "info", labourmgmtadd.class);
			Commonutility.toWriteConsole("lvrRcvrespjsonobj : "+lvrRcvrespjsonobj);
			if (statusCode.equalsIgnoreCase("00") && respCode.equalsIgnoreCase("R0124")) {
				alert.setCls("success");
				if (Commonutility.checkempty(lvrRspmsg)) {
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Labor created successfully"));
				}					
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "success";

			} else if (statusCode.equalsIgnoreCase("02") && respCode.equalsIgnoreCase("R0155")) { 
				alert.setCls("danger");
				if (Commonutility.checkempty(lvrRspmsg)) {
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg("Duplicate, Labor already registered with us.");
				}								
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			} else {
				alert.setCls("danger");
				if (Commonutility.checkempty(lvrRspmsg)) {
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg("Labor Not Created. Error.");
				}								
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}

		} catch (Exception ex) {

			Commonutility.toWriteConsole("Step -1 : Labor Create Called labourmgmtadd.class Exception Found : " + ex);
			logwrite.logMessage("Step -1 : Labor Create Called Exception Found : "+ex, "error", labourmgmtadd.class);
			alert.setCls("danger");
			alert.setMsg(getText("Text.customerreg.key.error"));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally {
			imgByt = null; obj = null; data = null;  sessionMap = null; lvrCatNamestrary = null; lrvCatenameJAry = null; logwrite = null; lvrRcvrespjsonobj = null;
			System.gc();
		}
	}

	public String deletelaborActionFunction() {
		String result = "";
		JSONObject finaljj = null;
		JSONObject json = null;
		Log logwrite = null;
		JSONObject json_data = null;
		try {
			logwrite =  new Log();
			Commonutility.toWriteConsole("Step 1 : Labor Delete Called labourmgmtadd.class deletelaborActionFunction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Labor Delete Called deletelaborActionFunction() [Start]", "info", labourmgmtadd.class);
			if (deletelaborid != 0 && deletelaborid > 0) {
				finaljj = new JSONObject();
				Map sessionMap = ActionContext.getContext().getSession();
				JSONObject dataJson = new JSONObject();
				dataJson.put("lbrid", String.valueOf(deletelaborid));
				dataJson.put("lbrserviceid", deletelaborserviceid);
				dataJson.put("lbrstatus", "1");

				finaljj.put("servicecode", "SI3003");
				finaljj.put("servicefor", "4");// select
				finaljj.put("data", dataJson);
				String jsonTextFinal = finaljj.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);

				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				String finalUrl = getText("socialindia.labormgmt.labordeleteaction");
				Commonutility.toWriteConsole("Step 2 : Labor Delete Called labourmgmtadd.class deletelaborActionFunction() Service API : " + finalUrl);
				logwrite.logMessage("Step 2 : Labor Delete Called deletelaborActionFunction() Service API : " + finalUrl, "info", labourmgmtadd.class);
				String response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				json_data = json.getJSONObject("data");
				result = json_data.getString("status");
				String statusCode = (String) json.get("statuscode");
				String respCode = (String) json.get("respcode");
				Commonutility.toWriteConsole("Step 3 : Labor Delete Called labourmgmtadd.class deletelaborActionFunction() [End] :"+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss") +" Status Code : " + statusCode + " Response Code : "+respCode);
				logwrite.logMessage("Step 3 : Labor Delete Called deletelaborActionFunction() [End] Status Code : " + statusCode + " Response Code : " + respCode, "info", labourmgmtadd.class);
				if (statusCode.equalsIgnoreCase("00") && respCode.equalsIgnoreCase("R0128")) {
					if (result.equalsIgnoreCase("success")) {
						alert.setCls("success");
						alert.setMsg(getText("Labor deleted successfully."));
						alertList.add(alert);
						return "success";
					} else {
						alert.setCls("error");
						alert.setMsg(getText("Error in user delete."));
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
			Commonutility.toWriteConsole("Step -1 : Labor Delete Called labourmgmtadd.class deletelaborActionFunction() Exception Found : " + ex +" Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step -1 : Labor Delete Called deletelaborActionFunction() Exception Found : "+ex, "error", labourmgmtadd.class);
			alert.setCls("error");
			alert.setMsg(getText("Exception in error deletion."));
			alertList.add(alert);
			return "error";
		} finally {
			finaljj = null; json = null; logwrite = null; json_data = null;
		}
		return SUCCESS;

	}

	public String viewlaborActionFunction() {
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject locObjRecvdataJson = null;// Receive Data Json
		JSONObject lvrRqstdatajsonobj = null;// Rqst Data Json
		JSONObject finaljj = null; // Final Rqst Json
		Log logwrite = null;
		String idcardtypname = "";
		String verifysts = "";
		try {
			logwrite = new Log();
			Commonutility.toWriteConsole("Step 1 : Labor View labourmgmtadd.class viewlaborActionFunction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Labor View  viewlaborActionFunction() [Start]", "info", labourmgmtadd.class);
			Map currentSession = ActionContext.getContext().getSession();
			if (deletelaborid == null) {
				if ((deletelaborid == null || deletelaborid == -1 || deletelaborid == 0) && currentSession.get("currentsession_deletelaborid") != null) {
					String stfiddd = (String) currentSession.get("currentsession_deletelaborid");
					String labservid = (String) currentSession.get("currentsession_deletelaborserviceid");
					deletelaborid = Integer.parseInt(stfiddd);
					deletelaborserviceid = labservid;
				}
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_deletelaborid", String.valueOf(deletelaborid));
				currentSession.put("currentsession_deletelaborserviceid", deletelaborserviceid);
			}
			lvrRqstdatajsonobj = new JSONObject();
			lvrRqstdatajsonobj.put("lbrid", String.valueOf(deletelaborid));
			lvrRqstdatajsonobj.put("lbrserviceid", deletelaborserviceid);
			lvrRqstdatajsonobj.put("lbrstatus", "1");

			finaljj = new JSONObject();
			finaljj.put("servicecode", "SI3002");
			finaljj.put("servicefor", "3");// select
			finaljj.put("data", lvrRqstdatajsonobj);
			String jsonTextFinal = finaljj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.labormgmt.laborviewaction");
			Commonutility.toWriteConsole("Step 2 : Labor View labourmgmtadd.class viewlaborActionFunction() Service API : " + finalUrl);
			logwrite.logMessage("Step 2 : Labor View  viewlaborActionFunction() Service API : " + finalUrl, "info", labourmgmtadd.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Step Reponse:   "+response);
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					locObjRecvdataJson = locObjRecvJson.getJSONObject("data");
					labRegObj.setImageNameWeb(locObjRecvdataJson.getString("str_lbr_webimage"));
					ActionContext.getContext().getSession().put("staffProfileImage", labRegObj.getImageNameWeb());
					JSONArray skills_category = new JSONArray();
					String catskillidname = "";
					String catskillids= "";
					String cat_skillidname = "";
					String cat_skillids= "";
					skills_category = (JSONArray) Commonutility .toHasChkJsonRtnValObj(locObjRecvdataJson, "jArry_lbr_catg");
					Commonutility.toWriteConsole("skills_category   "+skills_category.length());
					if (skills_category.length() > 0) {
						for (int j = 0; j < skills_category.length(); j++) {
							catskillidname = (String)Commonutility.toHasChkJsonRtnValObj(skills_category.getJSONObject(j), "categoryname");
							catskillids = (String)Commonutility.toHasChkJsonRtnValObj(skills_category.getJSONObject(j), "categoryid");
							cat_skillidname = (String)Commonutility.toHasChkJsonRtnValObj(skills_category.getJSONObject(j), "skillsname");
							cat_skillids = (String)Commonutility.toHasChkJsonRtnValObj(skills_category.getJSONObject(j), "skillsid");
							CatskillsList.add(new LaborProfileTblVO(catskillidname,catskillids,cat_skillidname,cat_skillids));

						}
						catskillidname+=(String)Commonutility.toHasChkJsonRtnValObj(skills_category.getJSONObject(skills_category.length()-1), "categoryname");
						catskillids+=(String)Commonutility.toHasChkJsonRtnValObj(skills_category.getJSONObject(skills_category.length()-1), "categoryid");
						cat_skillidname += (String)Commonutility.toHasChkJsonRtnValObj(skills_category.getJSONObject(skills_category.length()-1), "skillsname") + ", ";
						cat_skillids += (String)Commonutility.toHasChkJsonRtnValObj(skills_category.getJSONObject(skills_category.length()-1), "skillsid")+ ", ";
					} else {
						catskillidname = ""; catskillids= "";
						 cat_skillidname = "";;cat_skillids= "";
					}
					labRegObj.setSkillslistids(catskillids);
					labRegObj.setSkillslistname(catskillidname);
					labRegObj.setIvrBnKEY_FOR_SEARCH((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"str_lbr_keyfrsrch"));
//					labRegObj.setIvrBnKEY_FOR_SEARCH((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"lbr_kyforsrch"));
					labRegObj.setIvrBnLBR_DESCP((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"str_lbr_descp"));
					labRegObj.setIvrBnLBR_PH_NO((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_lbr_phno"));
					labRegObj.setIvrBnLBR_ISD_CODE((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_lbr_isdcode"));
					labRegObj.setIvrBnLBR_COUNTRY_ID((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_lbr_cntry"));
					labRegObj.setIvrBnLBR_STATE_ID((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_lbr_stateid"));
					labRegObj.setIvrBnLBR_CITY_ID((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_lbr_cityid"));
					labRegObj.setIvrBnLBR_PSTL_ID((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_lbr_pstlid"));
					laborcompanyid=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"companyId");
					laborcompanyname = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"companyName");
					String idcardtypval =(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_lbr_cardtyp_name");
					String idcardtyeid = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_lbr_cardtyp");
					labRegObj.setIvrBnID_CARD_TYPNAME(idcardtypval);
					if(Commonutility.toCheckisNumeric(idcardtyeid)){
						labRegObj.setIvrBnID_CARD_TYP(Integer.parseInt(idcardtyeid));
					}else{
						labRegObj.setIvrBnID_CARD_TYP(0);
					}
					labRegObj.setIvrBnLBR_EMAIL(locObjRecvdataJson .getString("strv_lbr_email"));
					String locvrfstsStr=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_lbr_verifysts");
					labRegObj.setIvrBnVERIFIED_STATUS(locvrfstsStr);
					labRegObj.setIvrBnLBR_ID(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_lbr_id")));
					labRegObj.setIvrBnLBR_SERVICE_ID((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_lbr_serviceid"));
					labRegObj.setIvrBnLBR_ADD_1((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_lbr_add1"));
					labRegObj.setIvrBnLBR_ADD_2((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_lbr_add2"));
					labRegObj.setIvrBnID_CARD_NO((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_lbr_cardno"));
					labRegObj.setIvrBnLBR_NAME((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_lbr_name"));
					labRegObj.setIvrBnLBR_RATING(Double.parseDouble((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_lbr_rating")));
					labRegObj.setWrktypname((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"str_lbr_wrktypname"));
					labRegObj.setWrktypId((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_lbr_wrktypid"));
					labRegObj.setLbrcntryName((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"lbr_cntryName"));
					labRegObj.setLbrstateName((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"lbr_stateName"));
					labRegObj.setLbrcityName((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"lbr_cityName"));
					labRegObj.setLbrpincode((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"lbr_pincodeName"));
					labRegObj.setIvrBnLBR_EXPERIENCE((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"experience"));
					labRegObj.setIvrBnLBR_COST((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"cost"));
					labRegObj.setIvrBnLBR_COSTPER((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"costper"));

					JSONArray issuedata=locObjRecvdataJson.getJSONArray("issuedata");
					System.out.println("issuedata ------------------- "+issuedata);
					issueFeatures=new ArrayList<String>();
					String issval="";
					 for (int j = 0; j < issuedata.length(); j++) {
						 JSONArray temm=null;
		   					temm = issuedata.getJSONArray(j);
		   					int indx=(Integer)temm.get(0);
		   					String splitval=(String)temm.get(1);
		   					if(splitval!=null && !splitval.equalsIgnoreCase("null") && !splitval.equalsIgnoreCase("")){
		    					issval+=splitval+", ";

					 }
		   				/*	 issList.add(new MerchantIssueTblVO(indx,splitval));
		   					issueFeatures.add((String)temm.get(1));	 */
		   				 splitval="";
			        }
					 issuetext=issval;
					 System.out.println("splitval ------------ "+issuetext);

				}
			}
			ActionContext.getContext().getSession().put("laborSessID", labRegObj.getIvrBnLBR_ID());
			Commonutility.toWriteConsole("Step 3 : Labor View labourmgmtadd.class viewlaborActionFunction() [End] : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 3 : Labor View viewlaborActionFunction() [End] ", "info", labourmgmtadd.class);
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Labor View labourmgmtadd.class viewlaborActionFunction() Exception Found : " +ex + "Time : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step -1 : Labor View viewlaborActionFunction() Exception Found : " + ex,  "error", labourmgmtadd.class);
		} finally {
			 locObjRecvJson = null;// Receive String to json
			 locObjRecvdataJson = null;// Receive Data Json
			 lvrRqstdatajsonobj = null;// Rqst Data Json
			 finaljj = null; // Final Rqst Json
			 logwrite = null;
		}
		return SUCCESS;

	}

	public String labormgmtUpdateaction() {
		Log logwrite = null;
		JSONObject lvrRqstdatajsonobj = null;
		JSONObject lvrRqstjsonobj = null;
		JSONObject lvrRecvjsonobj = null;
		JSONObject lvrRecvdatajsonobj = null;
		String[] lvrCatNamestrary1 = null;
		JSONArray lrvCatenameJAry1 = null;
		try {
			logwrite =  new Log();
			lvrRqstdatajsonobj = new JSONObject();
			Commonutility.toWriteConsole("Step 1 : Labor Update labourmgmtadd.class labormgmtUpdateaction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Labor Update  labormgmtUpdateaction() [Start]", "info", labourmgmtadd.class);
			Map sessionMap = ActionContext.getContext().getSession();
			//imgByt = profileUpdate.toReadFiletoBytes(staffImage);
			String compval=offercheck+","+issuelistbox;
			System.out.println("compval -------------- "+compval);
			
			lvrRqstdatajsonobj.put("lbrid",String.valueOf(deletelaborid));
			lvrRqstdatajsonobj.put("name", labRegObj.getIvrBnLBR_NAME());
			lvrRqstdatajsonobj.put("emailid", labRegObj.getIvrBnLBR_EMAIL());
			lvrRqstdatajsonobj.put("add1", labRegObj.getIvrBnLBR_ADD_1());
			lvrRqstdatajsonobj.put("add2", labRegObj.getIvrBnLBR_ADD_2());
			lvrRqstdatajsonobj.put("ISDcode", labRegObj.getIvrBnLBR_ISD_CODE());
			lvrRqstdatajsonobj.put("mobno", labRegObj.getIvrBnLBR_PH_NO());
			lvrRqstdatajsonobj.put("postalcode", String.valueOf(labRegObj.getIvrBnLBR_PSTL_ID()));
			lvrRqstdatajsonobj.put("city", String.valueOf(labRegObj.getIvrBnLBR_CITY_ID()));
			lvrRqstdatajsonobj.put("sate", String.valueOf(labRegObj.getIvrBnLBR_STATE_ID()));
			lvrRqstdatajsonobj.put("country", String.valueOf(labRegObj.getIvrBnLBR_COUNTRY_ID()));

			//lvrRqstdatajsonobj.put("rating", String.valueOf(labRegObj.getIvrBnLBR_RATING()));
			lvrRqstdatajsonobj.put("kycname", "");

			lvrRqstdatajsonobj.put("verifystatus", labRegObj.getIvrBnVERIFIED_STATUS());// 0 - unverified, 1 - Verified
			lvrRqstdatajsonobj.put("keyforsrch", labRegObj.getIvrBnKEY_FOR_SEARCH());
			lvrRqstdatajsonobj.put("wktypid", labRegObj.getWrktypId());
			lvrRqstdatajsonobj.put("descrp", labRegObj.getIvrBnLBR_DESCP());
			lvrRqstdatajsonobj.put("laborcompanyname", laborcompanyid);
			lvrRqstdatajsonobj.put("imagename",staffImageFileName);
			if (staffImage!=null) {
				lvrRqstdatajsonobj.put("lbrimgsrchpath", staffImage.getAbsolutePath());
			} else {
				lvrRqstdatajsonobj.put("lbrimgsrchpath", "");
			}

			//lvrRqstdatajsonobj.put("imgencdata",profileUpdate.toByteAryToBase64EncodeStr(imgByt));// encrypted string on image


			lvrRqstdatajsonobj.put("cardtyp", String.valueOf(labRegObj.getIvrBnID_CARD_TYP()));
			lvrRqstdatajsonobj.put("cardno", labRegObj.getIvrBnID_CARD_NO());
			lvrRqstdatajsonobj.put("entryby",String.valueOf(sessionMap.get("USERID")));
			lvrRqstdatajsonobj.put("currentloginid",String.valueOf(sessionMap.get("USERID")));
			lvrRqstdatajsonobj.put("experience", labRegObj.getIvrBnLBR_EXPERIENCE());
			lvrRqstdatajsonobj.put("cost", labRegObj.getIvrBnLBR_COST());
			lvrRqstdatajsonobj.put("costper", labRegObj.getIvrBnLBR_COSTPER());
			lvrRqstdatajsonobj.put("cityname", labRegObj.getLbrcityName());

			int count=0;
			String[] functiontxtid = compval.split(",");
			/*String[] functiontxt = offerissuetxt[0].split("!_!");
			JSONArray functiontext = new JSONArray();
			for (int i = 0; i < functiontxt.length; i++) {
				functiontext.put(Commonutility.toCheckNullEmpty(functiontxt[i]));
			}
			functiontxt = null;
			lvrRqstdatajsonobj.put("issueid", functiontext);*/

			JSONArray functiontxttext = new JSONArray();
			for (int i = 0; i < functiontxtid.length; i++) {
				functiontxttext.put(functiontxtid[i]);
				count=i;
			}
			lvrRqstdatajsonobj.put("issuetxt", functiontxttext);
			lvrRqstdatajsonobj.put("count", count);
			/*functiontxtid = null;*/
		//	JSONArray ja = new JSONArray();
			/*if(! mySelect[0].equalsIgnoreCase("0"))
			{
				String[] s2 = mySelect[0].split(",");
				for (int i = 0; i < s2.length; i++) {
					ja.put(s2[i]);
				}
			}
			lvrRqstdatajsonobj.put("cateoryJary", ja);	*/
			lrvCatenameJAry1 = new JSONArray();
			JSONObject jobj =new JSONObject();
			Commonutility.toWriteConsole("********* _______ "+categoryskillidval);
			if(!categoryskillidval.equalsIgnoreCase("")&& !categoryskillidval.equalsIgnoreCase("null") && categoryskillidval.length() > 0)
			{
			String[] categoryskillidvalarr=categoryskillidval.split("!_!");
			String[] delimt=null;
			for (int i = 0; i < categoryskillidvalarr.length; i++) {
				 jobj =new JSONObject();
				 delimt=categoryskillidvalarr[i].split("_");

			//	String[] splitArray = lvrCatNamestrary1.split("!_!");
				/*String str = String.join(",", lvrCatNamestrary1);

				//using iterables
				List<String> list = Arrays.asList(array);
				String joined3 = String.join(",", list);*/
				jobj.put("cate_id", Commonutility.toCheckNullEmpty(delimt[0]));
				jobj.put("skill_id",   Commonutility.toCheckNullEmpty(delimt[1]));
				lrvCatenameJAry1.put(jobj);
				jobj=null;
			}
			lvrRqstdatajsonobj.put("cateoryJary" , lrvCatenameJAry1);
			}
			lvrRqstjsonobj = new JSONObject();
			lvrRqstjsonobj.put("servicecode", "SI3001");
			lvrRqstjsonobj.put("servicefor", "2");// Edit
			lvrRqstjsonobj.put("data", lvrRqstdatajsonobj);
			String jsonTextFinal = lvrRqstjsonobj.toString().trim();
			Commonutility.toWriteConsole("Step 2 : Labor Update labourmgmtadd.class labormgmtUpdateaction() Service API  : " + jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.labormgmt.laborupdatedaction");

			logwrite.logMessage("Step 2 : Labor Update  labormgmtUpdateaction() Service API  : " + finalUrl, "info", labourmgmtadd.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRecvjsonobj = new JSONObject(response);
			lvrRecvdatajsonobj = lvrRecvjsonobj.getJSONObject("data");

			//result = json_data.getBoolean("resultFlag");
			String statusCode = (String) lvrRecvjsonobj.get("statuscode");
			String respCode = (String) lvrRecvjsonobj.get("respcode");

			if (statusCode.equalsIgnoreCase("00") && respCode.equalsIgnoreCase("R0126")) {
				Commonutility.toWriteConsole("Step 3 : Labor Update labourmgmtadd.class labormgmtUpdateaction() [End] - Success : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : Labor Update  labormgmtUpdateaction() [End] - Success ", "info", labourmgmtadd.class);
				alert.setCls("success");
				alert.setMsg(getText("Labor updated successfully"));
				alertList.add(alert);
				return "success";

			} else {
				Commonutility.toWriteConsole("Step 3 : Labor Update labourmgmtadd.class labormgmtUpdateaction() [End] - Service return Error : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : Labor Update  labormgmtUpdateaction() [End] - Service return Error ", "info", labourmgmtadd.class);
				alert.setCls("error");
				alert.setMsg(getText("Error in user updating"));
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Labor Update labourmgmtadd.class labormgmtUpdateaction() Exception Found : " + ex +" Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step -1 : Labor Update  labormgmtUpdateaction() Exception Found : "+ex, "info", labourmgmtadd.class);
			alert.setCls("error");
			alert.setMsg(getText("Error in user updating"));
			alertList.add(alert);
			return "error";
		} finally {
			lvrRqstdatajsonobj = null; lvrRqstjsonobj = null; lvrRecvjsonobj = null; lvrRecvdatajsonobj = null; logwrite = null;
		}
	}

	public String labourfeedbackaction() {
		JSONObject locObjRecvJson = null;// Receive String to json
		Log logwrite = null;
		JSONObject lvrRqstdatajsonobj = null;
		JSONObject locObjRecvdataJson = null;// Receive Data Json
		JSONArray jsonarr = null;
		try {
			logwrite = new Log();
			lvrRqstdatajsonobj = new JSONObject();
			Commonutility.toWriteConsole("Step 1 : Labor Feedback View labourmgmtadd.class labourfeedbackaction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Labor Feedback View  labormgmtUpdateaction() [Start]", "info", labourmgmtadd.class);
			Map sessionMap = ActionContext.getContext().getSession();
			String lvrLaborid = "";
			if(deletelaborid==null ){
				if((deletelaborid==null)  && sessionMap.get("crntsissionlbid")!=null){
					String userunque=(String)sessionMap.get("crntsissionlbid");
					lvrLaborid=userunque;
				}
			}else{
				sessionMap = ActionContext.getContext().getSession();
				sessionMap.put("crntsissionlbid", String.valueOf(deletelaborid));
				lvrLaborid = String.valueOf(deletelaborid);
			}

			String sdtSearch="";
			lvrRqstdatajsonobj.put("fdbkstatus", "1");
			lvrRqstdatajsonobj.put("countflg", "yes");
			lvrRqstdatajsonobj.put("countfilterflg", "yes");
			lvrRqstdatajsonobj.put("startrow", "0");// starting row
			lvrRqstdatajsonobj.put("totalrow", "1000");// total row
			lvrRqstdatajsonobj.put("srchdtsrch", sdtSearch);
			lvrRqstdatajsonobj.put("searchflg", "");
			lvrRqstdatajsonobj.put("laborfdbkflg", "laborfbdkview");
			lvrRqstdatajsonobj.put("fdbkforuser",lvrLaborid);
			//lvrRqstdatajsonobj.put("fdbkforuser",String.valueOf(LaboruniqueId));//labor id / merchant id/ admin/user id
			lvrRqstdatajsonobj.put("fdbkforusergrp", laborgrpcode);//labor group id / merchant group id/ admin group id/user group id
			lvrRqstdatajsonobj.put("currentloginid", String.valueOf(sessionMap.get("USERID")));// Current login id
			lvrRqstdatajsonobj.put("fdbkforsocid", String.valueOf(sessionMap.get("sSoctyId")));// Current society id
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI5000");
			finaljj.put("data", lvrRqstdatajsonobj);
			String jsonTextFinal = finaljj.toString().trim();
			Commonutility.toWriteConsole("Step 2 : Labor Feedback View labourmgmtadd.class labourfeedbackaction() Service API : " + jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.labormgmt.laborFeedbackaction");
			logwrite.logMessage("Step 2 : Labor Feedback View  labormgmtUpdateaction() Service API : " + finalUrl, "info", labourmgmtadd.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Response:   "+response);
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					locObjRecvdataJson = locObjRecvJson.getJSONObject("data");
					jsonarr = (JSONArray) Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"feedbackdetails");
					if (jsonarr.length() > 0) {
						for(int j = 0; j < jsonarr.length(); j++){

							laborName_hidd_txt = (String) Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "feedbacttoname");// feedback given to Name , is labor / merchant/committee
							Feedbkby_fname = (String) Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "dbkby_fname");// feedback given by user name
							feedbackDateby = (String) Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "feedbackdate");
							feedbackDateby = feedbackDateby.substring(0, 10);
							feedbackimgweb = (String) Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "dbkby_imgweb");
							feedbackimgmob = (String) Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "dbkby_imgmob");
							Feedbkby_lname = (String) Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "dbkby_lname");
							feedbackrating  = (String) Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "feedbackrating");
							feedbacktext = (String) Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "feedbacktext");
							feedback_id = (String) Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "feedbackid");
							feedback_userid = (String) Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "fdbkby_userid");// // feedback given by user id
							FeedbackList.add(new FeedbackTblVO(Feedbkby_fname,feedbackDateby, feedbackimgweb,feedbackimgmob, Feedbkby_lname, Integer.parseInt(feedbackrating),feedbacktext, Integer.parseInt(feedback_id),Integer.parseInt(feedback_userid)));


							/*Feedbkby_fname = jsonarr.getJSONObject(j).getString("dbkby_fname");
							feedbackDateby =jsonarr.getJSONObject(j).getString("feedbackdate");
							feedbackDateby= feedbackDateby.substring(0, 10);
							feedbackimgweb =jsonarr.getJSONObject(j).getString("dbkby_imgweb");
							feedbackimgmob =jsonarr.getJSONObject(j).getString("dbkby_imgweb");
							Feedbkby_lname =jsonarr.getJSONObject(j).getString("dbkby_lname");
							feedbackrating =jsonarr.getJSONObject(j).getInt("feedbackrating");
							feedbacktext =jsonarr.getJSONObject(j).getString("feedbacktext");
							feedback_id =jsonarr.getJSONObject(j).getInt("feedbackid");
							feedback_userid =jsonarr.getJSONObject(j).getInt("fdbkby_userid");	*/
						}

					}
				}
				Commonutility.toWriteConsole("Step 3 : Labor Feedback View labourmgmtadd.class labourfeedbackaction() [End] - Success : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : Labor Feedback View  labormgmtUpdateaction() [End] - Success", "info", labourmgmtadd.class);
			}else{
				Commonutility.toWriteConsole("Step 3 : Labor Feedback View labourmgmtadd.class labourfeedbackaction() [End] - Service API return Error : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : Labor Feedback View  labormgmtUpdateaction() [End] - Service API return Error", "info", labourmgmtadd.class);
			}
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Labor Feedback View labourmgmtadd.class labourfeedbackaction() Exception Found : " + ex +" Time : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step -1 : Labor Feedback View  labormgmtUpdateaction() Exception Found : "+ex, "info", labourmgmtadd.class);
			alert.setCls("error");
			alert.setMsg(getText("Error in FeedBack"));
			alertList.add(alert);
			return "error";
		} finally {
			locObjRecvJson = null;// Receive String to json
			logwrite = null;
			lvrRqstdatajsonobj = null;
			locObjRecvdataJson = null;// Receive Data Json
			jsonarr = null;
		}
		return SUCCESS;
	}

	public LaborProfileTblVO getLabRegObj() {
		return labRegObj;
	}

	public void setLabRegObj(LaborProfileTblVO labRegObj) {
		this.labRegObj = labRegObj;
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

	public File getStaffImage() {
		return staffImage;
	}

	public void setStaffImage(File staffImage) {
		this.staffImage = staffImage;
	}

	public String getStaffImageFileName() {
		return staffImageFileName;
	}

	public void setStaffImageFileName(String staffImageFileName) {
		this.staffImageFileName = staffImageFileName;
	}

	public String getStaffProfileImage() {
		return staffProfileImage;
	}

	public void setStaffProfileImage(String staffProfileImage) {
		this.staffProfileImage = staffProfileImage;
	}

	public int getStaffsessID() {
		return staffsessID;
	}

	public void setStaffsessID(int staffsessID) {
		this.staffsessID = staffsessID;
	}

	public String getCountry_id() {
		return Country_id;
	}

	public void setCountry_id(String country_id) {
		Country_id = country_id;
	}

	public String getState_id() {
		return State_id;
	}

	public void setState_id(String state_id) {
		State_id = state_id;
	}

	public String getCity_id() {
		return City_id;
	}

	public void setCity_id(String city_id) {
		City_id = city_id;
	}

	public String getID_CARD_TYP() {
		return ID_CARD_TYP;
	}

	public void setID_CARD_TYP(String iD_CARD_TYP) {
		ID_CARD_TYP = iD_CARD_TYP;
	}

	public String[] getMySelect() {
		return mySelect;
	}

	public void setMySelect(String[] mySelect) {
		this.mySelect = mySelect;
	}

	public Integer getDeletelaborid() {
		return deletelaborid;
	}

	public void setDeletelaborid(Integer deletelaborid) {
		this.deletelaborid = deletelaborid;
	}

	public String getDeletelaborserviceid() {
		return deletelaborserviceid;
	}

	public void setDeletelaborserviceid(String deletelaborserviceid) {
		this.deletelaborserviceid = deletelaborserviceid;
	}

	public Integer getLaboruniqueId() {
		return LaboruniqueId;
	}

	public void setLaboruniqueId(Integer laboruniqueId) {
		LaboruniqueId = laboruniqueId;
	}

	public String getLaborName_hidd_txt() {
		return laborName_hidd_txt;
	}

	public void setLaborName_hidd_txt(String laborName_hidd_txt) {
		this.laborName_hidd_txt = laborName_hidd_txt;
	}

	public String getFeedbkby_lname() {
		return Feedbkby_lname;
	}

	public void setFeedbkby_lname(String feedbkby_lname) {
		Feedbkby_lname = feedbkby_lname;
	}

	public String getFeedbkby_fname() {
		return Feedbkby_fname;
	}

	public void setFeedbkby_fname(String feedbkby_fname) {
		Feedbkby_fname = feedbkby_fname;
	}

	public String getFeedbackDateby() {
		return feedbackDateby;
	}

	public void setFeedbackDateby(String feedbackDateby) {
		this.feedbackDateby = feedbackDateby;
	}

	public String getFeedbackimgweb() {
		return feedbackimgweb;
	}

	public void setFeedbackimgweb(String feedbackimgweb) {
		this.feedbackimgweb = feedbackimgweb;
	}

	public String getFeedbackimgmob() {
		return feedbackimgmob;
	}

	public void setFeedbackimgmob(String feedbackimgmob) {
		this.feedbackimgmob = feedbackimgmob;
	}

	public String getFeedbacktext() {
		return feedbacktext;
	}

	public void setFeedbacktext(String feedbacktext) {
		this.feedbacktext = feedbacktext;
	}

	/*public Integer getFeedbackrating() {
		return feedbackrating;
	}

	public void setFeedbackrating(Integer feedbackrating) {
		this.feedbackrating = feedbackrating;
	}

	public Integer getFeedback_id() {
		return feedback_id;
	}

	public void setFeedback_id(Integer feedback_id) {
		this.feedback_id = feedback_id;
	}*/

	public List<FeedbackTblVO> getFeedbackList() {
		return FeedbackList;
	}

	public void setFeedbackList(List<FeedbackTblVO> feedbackList) {
		FeedbackList = feedbackList;
	}

	/*public Integer getFeedback_userid() {
		return feedback_userid;
	}

	public void setFeedback_userid(Integer feedback_userid) {
		this.feedback_userid = feedback_userid;
	}
*/

	public String getLaborgrpcode() {
		return laborgrpcode;
	}

	public String getFeedbackrating() {
		return feedbackrating;
	}

	public void setFeedbackrating(String feedbackrating) {
		this.feedbackrating = feedbackrating;
	}

	public String getFeedback_id() {
		return feedback_id;
	}

	public void setFeedback_id(String feedback_id) {
		this.feedback_id = feedback_id;
	}

	public String getFeedback_userid() {
		return feedback_userid;
	}

	public void setFeedback_userid(String feedback_userid) {
		this.feedback_userid = feedback_userid;
	}

	public void setLaborgrpcode(String laborgrpcode) {
		this.laborgrpcode = laborgrpcode;
	}

	public String getCity_id_name() {
		return City_id_name;
	}

	public void setCity_id_name(String city_id_name) {
		City_id_name = city_id_name;
	}

	public String getCategoryskillidval() {
		return categoryskillidval;
	}

	public void setCategoryskillidval(String categoryskillidval) {
		this.categoryskillidval = categoryskillidval;
	}

	public List<LaborProfileTblVO> getCatskillsList() {
		return CatskillsList;
	}

	public void setCatskillsList(List<LaborProfileTblVO> catskillsList) {
		CatskillsList = catskillsList;
	}

	public String getOffertxt() {
		return offertxt;
	}

	public void setOffertxt(String offertxt) {
		this.offertxt = offertxt;
	}

	public String getOffercheck() {
		return offercheck;
	}

	public void setOffercheck(String offercheck) {
		this.offercheck = offercheck;
	}

	public String getIssuelistbox() {
		return issuelistbox;
	}

	public void setIssuelistbox(String issuelistbox) {
		this.issuelistbox = issuelistbox;
	}

	public String[] getOfferissuetxt() {
		return offerissuetxt;
	}

	public void setOfferissuetxt(String[] offerissuetxt) {
		this.offerissuetxt = offerissuetxt;
	}

	public List<String> getIssueFeatures() {
		return issueFeatures;
	}

	public void setIssueFeatures(List<String> issueFeatures) {
		this.issueFeatures = issueFeatures;
	}

	public List<MerchantIssueTblVO> getIssList() {
		return issList;
	}

	public void setIssList(List<MerchantIssueTblVO> issList) {
		this.issList = issList;
	}

	public String getIssuetext() {
		return issuetext;
	}

	public void setIssuetext(String issuetext) {
		this.issuetext = issuetext;
	}

	public String getLaborcompanyname() {
		return laborcompanyname;
	}

	public void setLaborcompanyname(String laborcompanyname) {
		this.laborcompanyname = laborcompanyname;
	}

	public String getLaborcompanyid() {
		return laborcompanyid;
	}

	public void setLaborcompanyid(String laborcompanyid) {
		this.laborcompanyid = laborcompanyid;
	}

	
    




}
