package com.socialindia.merchantcateglistmaster;


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
import com.socialindia.categorymaster.CategoryServices;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.vo.MerchantCategoryTblVO;


public class merchantCategoryServices extends ActionSupport{
	private String uniqueId;
	private String statusflg;
	private String iVOmerchantCategoryname;
	private Integer iVOmerchantcategoryid;
	private String MerchantDesc;
	private File staffImage;
	private String staffImageFileName;
	JSONObject obj = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();
	MerchantCategoryTblVO mrchcategoryobj = new MerchantCategoryTblVO();
	private static final long serialVersionUID = 1L;

	public String execute() {
		return SUCCESS;
	}
	public String merchantcategCreateFun() throws Exception{
			System.out.println("woCreatmerchantcreate  loading.......");
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj=null;
			JSONObject json =null;
			String invite=null;
			String groupcode=null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null,msg=null;
			//byte imgByt[] = null;
			try {	
			if (iVOmerchantCategoryname != null && !iVOmerchantCategoryname.equalsIgnoreCase("null") && !iVOmerchantCategoryname.equalsIgnoreCase("")) {
				//imgByt = profileUpdate.toReadFiletoBytes(staffImage);	
				obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("merchantCategoryname",iVOmerchantCategoryname);
				obj.put("desc",MerchantDesc);
				//obj.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
				if(staffImage!=null){
					obj.put("imgsrchpth", staffImage.getAbsolutePath());
				} else {
					obj.put("imgsrchpth", "");
				}
				obj.put("imagename", staffImageFileName);
				obj.put("status", "1");
				obj.put("ivrservicefor", "1");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				finaljj=new JSONObject();
				finaljj.put("servicecode", "SI21001");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.merchantCategorycreation.action");
				response = commonObj.jsonRequest(finalUrl, temp);				
				json = new JSONObject(response);
				msg= (String) json.get("message");
				statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					
					if (iVOmerchantCategoryname == null) {
						addFieldError("groupmang.groupName", getText("Error.usercreate.groupname"));
					} else if (msg .equalsIgnoreCase("Merchant Category already exist.")) {
						iVOmerchantCategoryname = "";
						alert.setCls("danger");
						alert.setMsg(getText("merchantcategory.exists"));
						alertList.add(alert);
						sessionMap.put("alertList", alertList);
						return "input";
					} else {
						if (msg.equalsIgnoreCase("Merchant Category Created.")) {
							iVOmerchantCategoryname = "";
							alert.setCls("success");
							alert.setMsg(getText("merchantCategory.Create.Success"));
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "success";
						} else {
							alert.setCls("danger");
							alert.setMsg(getText("merchantCategory.Create.Error"));
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "input";
						}

					}
					alert.setCls("success");
					alert.setMsg(getText("merchantCategory.Create.Success"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("merchantCategory.Create.Error"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "error";
				}

			} else {

			}
		} catch (Exception e) {
			Commonutility.toWriteConsole("Step -1 : Exception Found "+getClass().getSimpleName() +".class merchantcategCreateFun() : "+e);	
		} finally {
			finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
			statusCode =null;msg=null;
		}
		return "success";
	}
	
	
	public String deleteActionfun() {
		System.out
				.println("------- merchantCategoryid Deactivation ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("merchantCategoryid", String.valueOf(uniqueId));
			obj.put("statusflg", statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI21002");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.merchantCategory.deleteaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("merchantCategory.Delete.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("merchantCategory.Delete.Error"));
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class deleteActionfun() Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:ss") +" : "+ex);
		} finally {

		}
		return SUCCESS;
	}

	public String activeActionfun() {
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("merchantCategoryid", String.valueOf(uniqueId));
			obj.put("statusflg", statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI21003");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.merchantCategory.activeaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("merchantCategory.Active.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("merchantCategory.Active.Error"));
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class activeActionfun() Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:ss") +" : "+ex);
		} finally {

		}
		return SUCCESS;
	}
	
	public String viewActionfun() {
		
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject locObjRecvdataJson = null;// Receive Data Json	
		JSONObject lvrRqstdatajsonobj = null;// Rqst Data Json
		JSONObject finaljj = null; // Final Rqst Json
		Log logwrite = null;
		String idcardtypname = "";
		String verifysts = "";
		try {	
			logwrite = new Log();
			System.out.println("Step 1 : Labor View CategoryServices.class viewlaborActionFunction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : Labor View  viewActionfun() [Start]", "info", CategoryServices.class);
			Map currentSession = ActionContext.getContext().getSession();
			if (iVOmerchantcategoryid == null) {
				if ((iVOmerchantcategoryid == null || iVOmerchantcategoryid == -1 || iVOmerchantcategoryid == 0) && currentSession.get("currentsession_iVOmerchantcategoryid") != null) {
					String stfiddd = (String) currentSession.get("currentsession_iVOmerchantcategoryid");
					String labservid = (String) currentSession.get("currentsession_deletelaborserviceid");
					iVOmerchantcategoryid = Integer.parseInt(stfiddd);
	
				}
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_iVOmerchantcategoryid", String.valueOf(iVOmerchantcategoryid));
			}
			lvrRqstdatajsonobj = new JSONObject();
			lvrRqstdatajsonobj.put("merchantCategoryid", String.valueOf(iVOmerchantcategoryid));
			lvrRqstdatajsonobj.put("status", "1");

			finaljj = new JSONObject();
			finaljj.put("servicecode", "SI21004");
			finaljj.put("servicefor", "2");// select
			finaljj.put("data", lvrRqstdatajsonobj);
			String jsonTextFinal = finaljj.toString();			
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.mrchcategorymstr.viewaction");
			System.out.println("Step 2 : category View CategoryServices.class viewActionfun() Service API : " + finalUrl);
			logwrite.logMessage("Step 2 : category View  viewActionfun() Service API : " + finalUrl, "info", CategoryServices.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("Step Reponse:   "+response);
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					locObjRecvdataJson = locObjRecvJson.getJSONObject("data");					
					mrchcategoryobj.setImagename(locObjRecvdataJson .getString("str_mrchcat_webimage"));
					ActionContext.getContext().getSession().put("staffProfileImage",
							mrchcategoryobj.getImagename());
					mrchcategoryobj.setMrchCategoryId((Integer)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_mrchcat_id"));
					mrchcategoryobj.setStatus(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_mrchcat_status")));
					mrchcategoryobj.setMrchCategoryName((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_mrchcat_name"));
					mrchcategoryobj.setDesc((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_mrchcat_desc"));
				}
			}
			ActionContext.getContext().getSession().put("MrchcategorySessID", mrchcategoryobj.getMrchCategoryId());
			logwrite.logMessage("Step 3 : category View viewActionfun() [End] ", "info", merchantCategoryServices.class);
		} catch (Exception ex) {
			System.out.println("Step -1 : category View categorymgmtadd.class viewActionfun() Exception Found : " +ex + "Time : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step -1 : category View viewActionfun() Exception Found : " + ex,  "error", merchantCategoryServices.class);
		} finally {
			 locObjRecvJson = null;// Receive String to json
			 locObjRecvdataJson = null;// Receive Data Json	
			 lvrRqstdatajsonobj = null;// Rqst Data Json
			 finaljj = null; // Final Rqst Json
			 logwrite = null;
		}
		return SUCCESS;
		
	}
	
	public String CategoryUpdateFun() {
		//byte imgByt[] = null;
		Log logwrite = null;
		JSONObject lvrRqstdatajsonobj = null;
		JSONObject lvrRqstjsonobj = null;
		JSONObject lvrRecvjsonobj = null;
		JSONObject lvrRecvdatajsonobj = null;
		try {
			logwrite =  new Log();
			lvrRqstdatajsonobj = new JSONObject();
			System.out.println("Step 1 : category Update merchantCategoryServices.class labormgmtUpdateaction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : category Update  labormgmtUpdateaction() [Start]", "info", merchantCategoryServices.class);
			Map sessionMap = ActionContext.getContext().getSession();
			//imgByt = profileUpdate.toReadFiletoBytes(staffImage);
			lvrRqstdatajsonobj.put("merchantCategoryid",String.valueOf(mrchcategoryobj.getMrchCategoryId()));
			lvrRqstdatajsonobj.put("merchantCategoryname", mrchcategoryobj.getMrchCategoryName());
			lvrRqstdatajsonobj.put("desc", mrchcategoryobj.getDesc());		
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			lvrRqstdatajsonobj.put("imagename",staffImageFileName);		
			//lvrRqstdatajsonobj.put("imgencdata",profileUpdate.toByteAryToBase64EncodeStr(imgByt));// encrypted string on image
			if(staffImage!=null){
				lvrRqstdatajsonobj.put("imgsrchpth",staffImage.getAbsolutePath());// encrypted string on image
			} else {
				lvrRqstdatajsonobj.put("imgsrchpth","");// encrypted string on image
			}
			lvrRqstjsonobj = new JSONObject();
			lvrRqstjsonobj.put("servicecode", "SI21005");
			lvrRqstjsonobj.put("servicefor", "3");// Edit
			lvrRqstjsonobj.put("data", lvrRqstdatajsonobj);			
			String jsonTextFinal = lvrRqstjsonobj.toString().trim();
			System.out.println("Step 2 : category Update merchantCategoryServices.class labormgmtUpdateaction() Service API  : " + jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.mrchcategorymstr.updatedaction");
			
			logwrite.logMessage("Step 2 : category Update  labormgmtUpdateaction() Service API  : " + finalUrl, "info", merchantCategoryServices.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRecvjsonobj = new JSONObject(response);
			lvrRecvdatajsonobj = lvrRecvjsonobj.getJSONObject("data");
			
			//result = json_data.getBoolean("resultFlag");
			String statusCode = (String) lvrRecvjsonobj.get("statuscode");
			String respCode = (String) lvrRecvjsonobj.get("respcode");
			
			if (statusCode.equalsIgnoreCase("0")) {		
				System.out.println("Step 3 : category Update merchantCategoryServices.class labormgmtUpdateaction() [End] - Success : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : category Update  labormgmtUpdateaction() [End] - Success ", "info", merchantCategoryServices.class);
				alert.setCls("success");
				alert.setMsg(getText("Category updated successfully"));
				alertList.add(alert);
				return "success";

			} else {
				System.out.println("Step 3 : category Update merchantCategoryServices.class labormgmtUpdateaction() [End] - Service return Error : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : category Update  labormgmtUpdateaction() [End] - Service return Error ", "info", merchantCategoryServices.class);
				alert.setCls("error");
				alert.setMsg(getText("Error in Category updating"));
				alertList.add(alert);
				return "input";
			}
			
		} catch (Exception ex) {
			System.out.println("Step -1 : category Update merchantCategoryServices.class labormgmtUpdateaction() Exception Found : " + ex +" Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step -1 : category Update  labormgmtUpdateaction() Exception Found : "+ex, "info", merchantCategoryServices.class);
			alert.setCls("error");
			alert.setMsg(getText("Error in user updating"));
			alertList.add(alert);
			return "error";
		} finally {
			lvrRqstdatajsonobj = null; lvrRqstjsonobj = null; lvrRecvjsonobj = null; lvrRecvdatajsonobj = null; logwrite = null;
		}
	}
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
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

	public String getiVOmerchantCategoryname() {
		return iVOmerchantCategoryname;
	}

	public void setiVOmerchantCategoryname(String iVOmerchantCategoryname) {
		this.iVOmerchantCategoryname = iVOmerchantCategoryname;
	}

	

	public MerchantCategoryTblVO getMrchcategoryobj() {
		return mrchcategoryobj;
	}
	public void setMrchcategoryobj(MerchantCategoryTblVO mrchcategoryobj) {
		this.mrchcategoryobj = mrchcategoryobj;
	}
	public String getStatusflg() {
		return statusflg;
	}

	public void setStatusflg(String statusflg) {
		this.statusflg = statusflg;
	}
	public String getMerchantDesc() {
		return MerchantDesc;
	}
	public void setMerchantDesc(String merchantDesc) {
		MerchantDesc = merchantDesc;
	}
	public Integer getiVOmerchantcategoryid() {
		return iVOmerchantcategoryid;
	}
	public void setiVOmerchantcategoryid(Integer iVOmerchantcategoryid) {
		this.iVOmerchantcategoryid = iVOmerchantcategoryid;
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
	
	

}

