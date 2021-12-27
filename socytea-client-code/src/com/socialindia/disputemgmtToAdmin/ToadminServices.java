package com.socialindia.disputemgmtToAdmin;


import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.disputemgmtToMerchant.DisputeRiseTbl;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;


public class ToadminServices extends ActionSupport{
	  private String uniqueId;
	  private String statusflg;
	  private String closereasonfield;
	  private String emailcommentfield;
	  private String disp_entbyresidentid;
	  JSONObject obj = new JSONObject();
	  private AlertVo alert = new AlertVo();
	  private List<AlertVo> alertList = new ArrayList<AlertVo>();
	  private Common commonObj = new CommonDao(); 
	  commomServices commonSnippet=new commomServices();
	  
	  DisputeRiseTbl disputemerchantobj=new DisputeRiseTbl();
	private static final long serialVersionUID = 1L;

	public String execute() {		
		return SUCCESS;
	}
	public String ComplaintCreateFun() throws Exception{
		System.out.println("ComplaintCreateFun create  loading.......");
		Map sessionMap = ActionContext.getContext().getSession();
		JSONObject finaljj=null;
		JSONObject json =null;
		String invite=null;
		String groupcode=null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
		String statusCode =null,msg=null;
		String[] invitearr=null;
		try {	
			if(!disputemerchantobj.getDisputeTitle().equalsIgnoreCase("null")&& disputemerchantobj.getDisputeTitle()!=null) {
				obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("cmpltdesc",disputemerchantobj.getDisputeDesc());
				obj.put("cmplttitle",disputemerchantobj.getDisputeTitle());
				obj.put("cmplttoid","1");
				obj.put("cmplttogrpid","2");
				obj.put("status", "1");
				obj.put("ivrservicefor", "1");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				finaljj=new JSONObject();
				finaljj.put("servicecode", "SI320001");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				System.out.println("req::: "+jsonTextFinal);
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.disputemgmt.toadmincreatecomplaint");
			response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			msg= (String) json.get("message");
			statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("success.complaint.created"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("error.complaint.created"));
				alertList.add(alert);
				return "error";
			}
			
			}
			else
			{
				
			}
		} catch (Exception e) {
			System.out.println("Exception--------------- "+e);
			e.printStackTrace();
		}finally{
			finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
			statusCode =null;invitearr=null;msg=null;
		}
	return "success";
}

	public String modifyServices() {
		System.out.println("------- disputeadmin Edit ------------------------");
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
			obj.put("tomerchantid", String.valueOf(uniqueId));
			obj.put("crntusrloginid", String.valueOf(currentSession.get("USERID")));
			obj.put("ivrservicefor","3");
			 finaljj=new JSONObject();
			finaljj.put("servicecode", "SI32003");		
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.disputemgmt.toadminview");
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
						
					
					disputemerchantobj.setDisputeId(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"disp_id")));
					disputemerchantobj.setDisputeTitle((String)Commonutility.toHasChkJsonRtnValObj(json_data,"disp_title"));
					disputemerchantobj.setDisputeDesc((String)Commonutility.toHasChkJsonRtnValObj(json_data,"disp_desc"));
					disputemerchantobj.setTwnshpname((String)Commonutility.toHasChkJsonRtnValObj(json_data,"disp_entbyresidenttownshipname"));
					disputemerchantobj.setSocietyname((String)Commonutility.toHasChkJsonRtnValObj(json_data,"disp_entbyresidentsocietyname"));
					disputemerchantobj.setResidentname((String)Commonutility.toHasChkJsonRtnValObj(json_data,"disp_entbyresidentfname") + " "+(String)Commonutility.toHasChkJsonRtnValObj(json_data,"disp_entbyresidentlname"));
					disputemerchantobj.setMerchantname((String)Commonutility.toHasChkJsonRtnValObj(json_data,"disp_toadminname"));
					disp_entbyresidentid=((String)Commonutility.toHasChkJsonRtnValObj(json_data,"disp_entbyresidentid"));
					disputemerchantobj.setDisputeT0Id(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"disp_toid")));
				}
			}

			ActionContext.getContext().getSession().put("disputemerchantSessID", disputemerchantobj.getDisputeId());
		}
		catch(Exception Ex)
		{
			System.out.println("Exception disputemerchant view-------------- "+Ex);
		}
		finally
		{
			finaljj=null;response=null;finalUrl=null;temp=null;jsonTextFinal=null;
		}
	   return SUCCESS;
	}
	public String deleteCmpltAction(){
		System.out.println("------- cmplt Deactivation ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			obj.put("tomerchantid",String.valueOf(uniqueId));
			obj.put("statusflg",statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI32005");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.disputemgmt.toadmincmpltdelete");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Text.complaint.delete"));
				alertList.add(alert);
			return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Text.complaint.delete"));
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
	public String closeCmpltAction(){
		System.out.println("------- cmplt close ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			obj.put("tomerchantid",String.valueOf(uniqueId));
			obj.put("statusflg",statusflg);
			obj.put("ivrservicefor", "4");
			obj.put("reason", closereasonfield);
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI32002");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.disputemgmt.toadmincmpltclose");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("disputemgnmt.closereason.success"));
				alertList.add(alert);
			return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("disputemgnmt.closereason.error"));
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
	public String sendemailCmpltAction()
	{
		System.out.println("email send to admin");
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			System.out.println("---00------ ");
			String useridval=String.valueOf(sessionMap.get("USERID"));
			System.out.println("--ffff------- "+useridval);
			obj.put("crntusrloginid",useridval );
			System.out.println("--------- "+useridval);
			obj.put("tomerchantid",2);
			System.out.println("-1-------- "+useridval);
			obj.put("comments",emailcommentfield);
			obj.put("disputeid",uniqueId);
			System.out.println("-2-------- "+uniqueId);
			obj.put("statusflg",statusflg);
			System.out.println("-3-------- "+statusflg);
			obj.put("ivrservicefor", "7");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI32006");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.disputemgmt.toadmincmpltemailsend");
			String response=commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response-------------"+response);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("disputemgnmt.email.success"));
				alertList.add(alert);
			return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("disputemgnmt.email.error"));
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
	public String sendSMSCmpltAction()
	{
		System.out.println("SMS send to admin");
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			obj.put("tomerchantid",2);
			obj.put("comments",emailcommentfield);
			obj.put("disputeid",uniqueId);
			obj.put("statusflg",statusflg);
			obj.put("ivrservicefor", "8");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI32007");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.disputemgmt.toadmincmpltsmssend");
			String response=commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response-------------"+response);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("disputemgnmt.sms.success"));
				alertList.add(alert);
			return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("disputemgnmt.sms.error"));
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


public String getStatusflg() {
	return statusflg;
}
public void setStatusflg(String statusflg) {
	this.statusflg = statusflg;
}
public DisputeRiseTbl getDisputemerchantobj() {
	return disputemerchantobj;
}
public void setDisputemerchantobj(DisputeRiseTbl disputemerchantobj) {
	this.disputemerchantobj = disputemerchantobj;
}
public String getClosereasonfield() {
	return closereasonfield;
}
public void setClosereasonfield(String closereasonfield) {
	this.closereasonfield = closereasonfield;
}
public String getEmailcommentfield() {
	return emailcommentfield;
}
public void setEmailcommentfield(String emailcommentfield) {
	this.emailcommentfield = emailcommentfield;
}
public String getDisp_entbyresidentid() {
	return disp_entbyresidentid;
}
public void setDisp_entbyresidentid(String disp_entbyresidentid) {
	this.disp_entbyresidentid = disp_entbyresidentid;
}











}

