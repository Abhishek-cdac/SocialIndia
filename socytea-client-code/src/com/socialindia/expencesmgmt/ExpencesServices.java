package com.socialindia.expencesmgmt;


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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class ExpencesServices extends ActionSupport {
	private String uniqueId;
	byte imgByt[] = null;
	private List<File> documentfile = new ArrayList<File>();
	private String documentfileFileName;
	private String eveid;
	private String evestartdate;
	private String eveenddate;
	private String expenceShareids;
	private String deleteexpenceid;
	private String townshipname;
	private String socityname;
	JSONObject obj = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private List<ExpenceTblVO> expencedocList = new ArrayList<ExpenceTblVO>();
	ExpenceTblVO expenceobj = new ExpenceTblVO(null, null, null, null, null, null, 0);
	 
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();
	private static final long serialVersionUID = 1L;

	public String execute() {
		return SUCCESS;
	}
	public String expenceCreateFun() throws Exception{			
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj=null;
			JSONObject json =null;
			String invite=null;
			String groupcode=null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null;
			String[] invitearr=null;
			Log logWrite = null;
			try {	
				logWrite = new Log();
				logWrite.logMessage("Step 1 : Expences insert called. [Start]", "info", ExpencesServices.class);
				Commonutility.toWriteConsole("tep 1 : Expences insert called. [Start]");
				String sFileName = documentfileFileName;
				String[] filename=null;
				obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("expencetitle",expenceobj.getExpenceFor());
				obj.put("expencenoproduct", expenceobj.getNoOfProduct());
				obj.put("expenceamt", expenceobj.getProductPerAmt());
				obj.put("expencetotalamt", expenceobj.getExpenceTotAmt());
				obj.put("expencedesc", expenceobj.getDescr());
				obj.put("expencestatus", "1");
				obj.put("ivrservicefor", "1");				
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));				
				finaljj=new JSONObject();
				finaljj.put("servicecode", "SI13000");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();				
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.expence.creation.action");
				logWrite.logMessage("Step 2 : Expences service url : "+finalUrl, "info", ExpencesServices.class);
				Commonutility.toWriteConsole("Step 2 : Expences service url : "+finalUrl);
				response = commonObj.jsonRequest(finalUrl, temp);
				logWrite.logMessage("Step 3 : Expences service response : "+response, "info", ExpencesServices.class);
				Commonutility.toWriteConsole("Step 3 : Expences service response : "+response);
				json = new JSONObject(response);
				statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					logWrite.logMessage("Step 4 : Expences service statusCode : "+statusCode, "info", ExpencesServices.class);
					Commonutility.toWriteConsole("Step 4 : Expences service statusCode : "+statusCode);
					alert.setCls("success");
					alert.setMsg(getText("Expence.Create.Success"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "success";
				} else {
					logWrite.logMessage("Step 4 : Expences service statusCode : "+statusCode, "info", ExpencesServices.class);
					Commonutility.toWriteConsole("Step 4 : Expences service statusCode : "+statusCode);
					alert.setCls("danger");
					alert.setMsg(getText("Expence.Create.Error"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "input";
				}
			} catch (Exception e) {
				logWrite.logMessage("Step -1 : Exception Found in "+getClass().getSimpleName()+".class : " + e, "info", ExpencesServices.class);				
				Commonutility.toWriteConsole("Step -1 : Exception Found in "+getClass().getSimpleName()+".class : " + e);
				alert.setCls("danger");
				alert.setMsg(getText("Expence.Create.Error"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}finally{
				finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
				statusCode =null;invitearr=null;
			}		
	}
	public String modifyServices() {
		System.out.println("------- Expence Edit ------------------------");
		Map currentSession = ActionContext.getContext().getSession();			
		if(uniqueId==null ){					
			if((uniqueId==null)  && currentSession.get("currentsession_useruniqueId")!=null){							
				String userunque=(String)currentSession.get("currentsession_useruniqueId");
				uniqueId=userunque;
			}
		}else{
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_useruniqueId", String.valueOf(uniqueId));					
		}
		JSONObject finaljj=null;
		String response=null,finalUrl=null,temp=null,jsonTextFinal=null;
		try{
			JSONObject locObjRecvJson = null;// Receive String to json
			obj.put("expenceid", uniqueId);
			obj.put("crntusrloginid", String.valueOf(currentSession.get("USERID")));
			obj.put("ivrservicefor","3");
			 finaljj=new JSONObject();
			finaljj.put("servicecode", "SI13001");		
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.expencemodify.action");
			response=commonObj.jsonRequest(finalUrl, temp);	
			System.out.println("response -------- "+response);
			String docfilename ="";
			String docformat ="";
			String docfilepath="";
			String filedownloadurl="";
			if (response != null && !response.equalsIgnoreCase("null")
					&& response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					JSONObject json_data = locObjRecvJson.getJSONObject("data");
					
					
					expenceobj.setExpnId(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"expenceid")));
					expenceobj.setExpenceFor((String)Commonutility.toHasChkJsonRtnValObj(json_data,"expencetitle"));
					expenceobj.setProductPerAmt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"expenceprdtperamt"));
					expenceobj.setDescr((String)Commonutility.toHasChkJsonRtnValObj(json_data,"expencedesc"));
					expenceobj.setExpenceTotAmt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"expencetotamt"));
					expenceobj.setNoOfProduct((String)Commonutility.toHasChkJsonRtnValObj(json_data,"expencenoofprdt"));
					townshipname=((String)Commonutility.toHasChkJsonRtnValObj(json_data,"expencetownshipname"));
					socityname=((String)Commonutility.toHasChkJsonRtnValObj(json_data,"expencesocietyname"));
					//url 
					
				}
			}

			
		}
		catch(Exception Ex)
		{
			System.out.println("Exception expence view-------------- "+Ex);
		}
		finally
		{
			finaljj=null;response=null;finalUrl=null;temp=null;jsonTextFinal=null;
		}
	   return SUCCESS;
	}
	
	public String toexpencemodifyform(){		
		System.out.println("expence update  loading......."+expenceobj.getExpnId());
		Map sessionMap = ActionContext.getContext().getSession();
		JSONObject finaljj=null;
		JSONObject json =null;
		String invite=null;
		String groupcode=null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
		String statusCode =null;
		String[] invitearr=null;
		try {	
			String sFileName = documentfileFileName;
			String[] filename=null;
			obj.put("expenceid", String.valueOf(expenceobj.getExpnId()));
			obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
			obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
			obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
			obj.put("expencetitle",expenceobj.getExpenceFor());
			obj.put("expencenoproduct", expenceobj.getNoOfProduct());
			obj.put("expenceamt", expenceobj.getProductPerAmt());
			obj.put("expencetotalamt", expenceobj.getExpenceTotAmt());
			obj.put("expencedesc", expenceobj.getDescr());
			obj.put("ivrservicefor", "2");
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI13002");		
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString().trim();
			System.out.println("req::: "+jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.expenceupdate.action");
			response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Expence.Update.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Expence.Update.Error"));
				alertList.add(alert);
				return "input";
			}
		} catch (Exception e) {
			System.out.println("Exception--------------- "+e);
			e.printStackTrace();
		}finally{
			finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
			statusCode =null;invitearr=null;
		}
	return "success";
	}
	
	public String deleteExpenceAction(){
			System.out.println("------- Expence Delete ------------------------");
			Map sessionMap = ActionContext.getContext().getSession();
			try{
				String useridval=String.valueOf(sessionMap.get("USERID"));
				obj.put("crntusrloginid",useridval );
				obj.put("expenceid", deleteexpenceid);
				obj.put("ivrservicefor", "5");
				JSONObject finaljj=new JSONObject();
				finaljj.put("servicecode", "SI13004");		
				finaljj.put("data", obj);
				String jsonTextFinal = finaljj.toString();
				System.out.println(jsonTextFinal);
				jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
				String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
				String finalUrl =getText("socialindia.expence.deleteaction");
				String response=commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				String statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					alert.setCls("success");
					alert.setMsg(getText("Expence.Delete.Success"));
					alertList.add(alert);
				return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Expence.Delete.Error"));
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


public List<File> getDocumentfile() {
	return documentfile;
}
public void setDocumentfile(List<File> documentfile) {
	this.documentfile = documentfile;
}
public String getDocumentfileFileName() {
	return documentfileFileName;
}
public void setDocumentfileFileName(String documentfileFileName) {
	this.documentfileFileName = documentfileFileName;
}
public String getEveid() {
	return eveid;
}
public void setEveid(String eveid) {
	this.eveid = eveid;
}
public String getEventShareids() {
	return expenceShareids;
}
public void setEventShareids(String expenceShareids) {
	this.expenceShareids = expenceShareids;
}

public String getDeleteexpenceid() {
	return deleteexpenceid;
}
public void setDeleteexpenceid(String deleteexpenceid) {
	this.deleteexpenceid = deleteexpenceid;
}
public String getEvestartdate() {
	return evestartdate;
}
public void setEvestartdate(String evestartdate) {
	this.evestartdate = evestartdate;
}
public String getEveenddate() {
	return eveenddate;
}
public void setEveenddate(String eveenddate) {
	this.eveenddate = eveenddate;
}
public ExpenceTblVO getExpenceobj() {
	return expenceobj;
}
public void setExpenceobj(ExpenceTblVO expenceobj) {
	this.expenceobj = expenceobj;
}
public List<ExpenceTblVO> getExpencedocList() {
	return expencedocList;
}
public void setExpencedocList(List<ExpenceTblVO> expencedocList) {
	this.expencedocList = expencedocList;
}
public String getTownshipname() {
	return townshipname;
}
public void setTownshipname(String townshipname) {
	this.townshipname = townshipname;
}
public String getSocityname() {
	return socityname;
}
public void setSocityname(String socityname) {
	this.socityname = socityname;
}






}

