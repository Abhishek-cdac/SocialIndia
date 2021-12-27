package com.socialindia.categorymaster;

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
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.CategoryMasterTblVO;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class CategoryServices extends ActionSupport{
	private String uniqueId;
	private String statusflg;
	private String iVOcategoryname;
	private Integer iVOcategoryid;
	private File staffImage;
	private String staffImageFileName;
	JSONObject obj = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();
	CategoryMasterTblVO categoryobj = new CategoryMasterTblVO(0, null);
	private static final long serialVersionUID = 1L;

	public String execute() {		
		return SUCCESS;
	}
	public String CategoryCreateFun() throws Exception{
			System.out.println("category create  loading.......");
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj=null;
			JSONObject json =null;
			String invite=null;
			String groupcode=null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null,msg=null;
			String[] invitearr=null;
			//byte imgByt[] = null;
			try {	
				if(!categoryobj.getiVOCATEGORY_NAME().equalsIgnoreCase("null")&& categoryobj.getiVOCATEGORY_NAME()!=null) {
				//imgByt = profileUpdate.toReadFiletoBytes(staffImage);	
				obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("categoryname",categoryobj.getiVOCATEGORY_NAME());
				obj.put("status", "1");
				obj.put("ivrservicefor", "1");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				//obj.put("imgencdata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
				if(staffImage!=null) {
					obj.put("imgsrchpth", staffImage.getAbsolutePath());
				} else {
					obj.put("imgsrchpth", "");
				}
				obj.put("imagename", staffImageFileName);			
				finaljj=new JSONObject();
				finaljj.put("servicecode", "SI15001");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				System.out.println("req::: "+jsonTextFinal);
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.categorymgmt.creation.action");
				response = commonObj.jsonRequest(finalUrl, temp);
				Commonutility.toWriteConsole("response >>>>>>>>>>>> : "+response);
				json = new JSONObject(response);
				msg = (String) json.get("message");
				statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {										
						if (msg.equalsIgnoreCase("Category Created.")) {
							iVOcategoryname = "";
							alert.setCls("success");
							alert.setMsg(getText("Category.Create.Success"));
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "success";
						} else {
							alert.setCls("danger");
							alert.setMsg(getText("Category.Create.Error"));
							alertList.add(alert);
							sessionMap.put("alertList", alertList);
							return "input";
						}				        
				     				
				} else if (statusCode.equalsIgnoreCase("2")) {					
						iVOcategoryname = "";
						alert.setCls("danger");
						alert.setMsg(getText("Text.cate.exist"));
						alertList.add(alert);
						sessionMap.put("alertList", alertList);
						return "input";
					
					
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Category.Create.Error"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "error";
				}
				
				}
				else
				{
					
				}
			} catch (Exception e) {
				Commonutility.toWriteConsole("Exception Found in CategoryServices.CategoryCreateFun() : "+e);
			}finally{
				finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
				statusCode =null;invitearr=null;msg=null;
			}
		return "success";
	}
		
	public String deleteActionfun() {
		System.out.println("------- category Deactivation ------------------------");
			Map sessionMap = ActionContext.getContext().getSession();
			try{
				String useridval=String.valueOf(sessionMap.get("USERID"));
				obj.put("crntusrloginid",useridval );
				obj.put("categoryid", String.valueOf(uniqueId));
				obj.put("statusflg",statusflg);
				obj.put("ivrservicefor", "5");
				JSONObject finaljj=new JSONObject();
				finaljj.put("servicecode", "SI15002");		
				finaljj.put("data", obj);
				String jsonTextFinal = finaljj.toString();
				System.out.println(jsonTextFinal);
				jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
				String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
				String finalUrl =getText("socialindia.category.deleteaction");
				String response=commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				String statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					alert.setCls("success");
					alert.setMsg(getText("Idcard.Delete.Success"));
					alertList.add(alert);
				return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Idcard.Delete.Error"));
					alertList.add(alert);
					return "input";
				}
  		
			}catch(Exception ex){
			System.out.println("Exception ----- "+ex);
		}
			finally{
				
			}
	return SUCCESS;	
	}
	
	public String activeActionfun() {
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			obj.put("categoryid", String.valueOf(uniqueId));
			obj.put("statusflg",statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI15003");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.category.activeaction");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Idcard.Active.Success"));
				alertList.add(alert);
			return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Idcard.Active.Error"));
				alertList.add(alert);
				return "input";
			}
		
		}catch(Exception ex){
		System.out.println("Exception ----- "+ex);
	}
		finally{
			
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
			if (iVOcategoryid == null) {
				if ((iVOcategoryid == null || iVOcategoryid == -1 || iVOcategoryid == 0) && currentSession.get("currentsession_iVOcategoryid") != null) {
					String stfiddd = (String) currentSession.get("currentsession_iVOcategoryid");
					String labservid = (String) currentSession.get("currentsession_deletelaborserviceid");
					iVOcategoryid = Integer.parseInt(stfiddd);
	
				}
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_iVOcategoryid", String.valueOf(iVOcategoryid));
			}
			lvrRqstdatajsonobj = new JSONObject();
			lvrRqstdatajsonobj.put("categoryid", String.valueOf(iVOcategoryid));
			lvrRqstdatajsonobj.put("status", "1");

			finaljj = new JSONObject();
			finaljj.put("servicecode", "SI15004");
			finaljj.put("servicefor", "2");// select
			finaljj.put("data", lvrRqstdatajsonobj);
			String jsonTextFinal = finaljj.toString();			
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.categorymstr.viewaction");
			System.out.println("Step 2 : category View CategoryServices.class viewActionfun() Service API : " + finalUrl);
			logwrite.logMessage("Step 2 : category View  viewActionfun() Service API : " + finalUrl, "info", CategoryServices.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("Step Reponse:   "+response);
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					locObjRecvdataJson = locObjRecvJson.getJSONObject("data");					
					categoryobj.setImageNameWeb(locObjRecvdataJson .getString("str_cat_webimage"));
					ActionContext.getContext().getSession().put("staffProfileImage",
					categoryobj.getImageNameWeb());
					categoryobj.setiVOCATEGORY_ID((Integer)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"intv_cat_id"));
					categoryobj.setiVOACT_STAT(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cat_status")));
					categoryobj.setiVOCATEGORY_NAME((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cat_name"));
					categoryobj.setiVOCTG_SHTFORM((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"strv_cat_shrtdesc"));
				}
			}
			ActionContext.getContext().getSession().put("categorySessID", categoryobj.getiVOCATEGORY_ID());
			logwrite.logMessage("Step 3 : category View viewActionfun() [End] ", "info", CategoryServices.class);
		} catch (Exception ex) {
			System.out.println("Step -1 : category View categorymgmtadd.class viewActionfun() Exception Found : " +ex + "Time : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step -1 : category View viewActionfun() Exception Found : " + ex,  "error", CategoryServices.class);
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
			System.out.println("Step 1 : category Update CategoryServices.class labormgmtUpdateaction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step 1 : category Update  labormgmtUpdateaction() [Start]", "info", CategoryServices.class);
			Map sessionMap = ActionContext.getContext().getSession();
			//imgByt = profileUpdate.toReadFiletoBytes(staffImage);
			lvrRqstdatajsonobj.put("categoryid",String.valueOf(categoryobj.getiVOCATEGORY_ID()));
			lvrRqstdatajsonobj.put("name", categoryobj.getiVOCATEGORY_NAME());		
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			lvrRqstdatajsonobj.put("imagename",staffImageFileName);		
			
			if(staffImage!=null) {
				lvrRqstdatajsonobj.put("imgsrchpth", staffImage.getAbsolutePath());
			} else {
				lvrRqstdatajsonobj.put("imgsrchpth", "");
			}
			
			//lvrRqstdatajsonobj.put("imgencdata",profileUpdate.toByteAryToBase64EncodeStr(imgByt));// encrypted string on image
			
			
					
			lvrRqstjsonobj = new JSONObject();
			lvrRqstjsonobj.put("servicecode", "SI15005");
			lvrRqstjsonobj.put("servicefor", "3");// Edit
			lvrRqstjsonobj.put("data", lvrRqstdatajsonobj);			
			String jsonTextFinal = lvrRqstjsonobj.toString().trim();
			System.out.println("Step 2 : category Update CategoryServices.class labormgmtUpdateaction() Service API  : " + jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.categorymgmt.categoryupdatedaction");
			
			logwrite.logMessage("Step 2 : category Update  labormgmtUpdateaction() Service API  : " + finalUrl, "info", CategoryServices.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRecvjsonobj = new JSONObject(response);
			lvrRecvdatajsonobj = lvrRecvjsonobj.getJSONObject("data");
			
			//result = json_data.getBoolean("resultFlag");
			String statusCode = (String) lvrRecvjsonobj.get("statuscode");
			String respCode = (String) lvrRecvjsonobj.get("respcode");
			
			if (statusCode.equalsIgnoreCase("0")) {		
				System.out.println("Step 3 : category Update CategoryServices.class labormgmtUpdateaction() [End] - Success : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : category Update  labormgmtUpdateaction() [End] - Success ", "info", CategoryServices.class);
				alert.setCls("success");
				alert.setMsg(getText("Category updated successfully"));
				alertList.add(alert);
				return "success";

			} else {
				System.out.println("Step 3 : category Update CategoryServices.class labormgmtUpdateaction() [End] - Service return Error : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
				logwrite.logMessage("Step 3 : category Update  labormgmtUpdateaction() [End] - Service return Error ", "info", CategoryServices.class);
				alert.setCls("error");
				alert.setMsg(getText("Error in Category updating"));
				alertList.add(alert);
				return "input";
			}
			
		} catch (Exception ex) {
			System.out.println("Step -1 : category Update CategoryServices.class labormgmtUpdateaction() Exception Found : " + ex +" Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			logwrite.logMessage("Step -1 : category Update  labormgmtUpdateaction() Exception Found : "+ex, "info", CategoryServices.class);
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

public String getiVOcategoryname() {
	return iVOcategoryname;
}
public void setiVOcategoryname(String iVOcategoryname) {
	this.iVOcategoryname = iVOcategoryname;
}
public CategoryMasterTblVO getCategoryobj() {
	return categoryobj;
}
public void setCategoryobj(CategoryMasterTblVO categoryobj) {
	this.categoryobj = categoryobj;
}
public String getStatusflg() {
	return statusflg;
}
public void setStatusflg(String statusflg) {
	this.statusflg = statusflg;
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
public Integer getiVOcategoryid() {
	return iVOcategoryid;
}
public void setiVOcategoryid(Integer iVOcategoryid) {
	this.iVOcategoryid = iVOcategoryid;
}











}

