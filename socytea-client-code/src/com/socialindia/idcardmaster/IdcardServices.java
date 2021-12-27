package com.socialindia.idcardmaster;


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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.IDCardMasterTblVO;
import com.socialindia.login.EncDecrypt;


public class IdcardServices extends ActionSupport{
	  private String uniqueId;
	  private String statusflg;
	  private String iVOidcradname;
	  JSONObject obj = new JSONObject();
	  private AlertVo alert = new AlertVo();
	  private List<AlertVo> alertList = new ArrayList<AlertVo>();
	  private Common commonObj = new CommonDao(); 
	  commomServices commonSnippet=new commomServices();
	  IDCardMasterTblVO idcardobj=new IDCardMasterTblVO(0, null);
	private static final long serialVersionUID = 1L;

	public String execute() {		
		return SUCCESS;
	}
	public String IdcardCreateFun() throws Exception{
			
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj=null;
			JSONObject json =null;
			String invite=null;
			String groupcode=null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null;
			String msg =null;
			String[] invitearr=null;
			 boolean result = false;
			try {	
				if(iVOidcradname!=null && !iVOidcradname.equalsIgnoreCase("null") && !iVOidcradname.equalsIgnoreCase("")) {
					System.out.println("idcard create  loading.......");
					obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
					obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
					obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
					obj.put("idcardname",iVOidcradname);
					obj.put("status", "1");
					obj.put("ivrservicefor", "1");
					obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
					JSONArray imagearray = new JSONArray();
					JSONArray fileNamearray = new JSONArray();
					finaljj=new JSONObject();
					finaljj.put("servicecode", "SI14001");		
					finaljj.put("data", obj);
					jsonTextFinal = finaljj.toString().trim();					
					jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
					temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
					finalUrl = getText("socialindia.idcardmgmt.creation.action");
					response = commonObj.jsonRequest(finalUrl, temp);
					System.out.println("resp::: "+response);
					json = new JSONObject(response);
					statusCode = (String) json.get("statuscode");
					msg= (String) json.get("message");
				if (statusCode.equalsIgnoreCase("0")) {
					if (iVOidcradname == null) {
				        addFieldError("groupmang.groupName",getText("Error.usercreate.groupname"));
				      } else if (msg.equalsIgnoreCase("Idcardtype already exist.")) {
						iVOidcradname = "";
						alert.setCls("danger");
						alert.setMsg(getText("Text.idcard.exist"));
						alertList.add(alert);
						sessionMap.put("alertList", alertList);
				        return "input";
				      } else {
				          if (msg.equalsIgnoreCase("Idcardtype Created.")) {
				        	  iVOidcradname="";
				            alert.setCls("success");
				            alert.setMsg(getText("Idcard.Create.Success"));
				            alertList.add(alert);
				            sessionMap.put("alertList", alertList);
				            return "success";
				          } else {
				            alert.setCls("danger");
				            alert.setMsg(getText("Idcard.Create.Error"));
				            alertList.add(alert);
				            sessionMap.put("alertList", alertList);
				            return "input";
				          }
				        
				      }
					alert.setCls("success");
					alert.setMsg(getText("Idcard.Create.Success"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Idcard.Create.Error"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "error";
				}
				
			} else {

			}
		} catch (Exception e) {
			Commonutility.toWriteConsole("Exception Found in IdcardServices.IdcardCreateFun() : "+e);
		} finally {
				finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
				statusCode =null;invitearr=null; msg =null;
		}
		return "success";
	}
	
	
	public String deleteActionfun(){
			System.out.println("------- idcard Deactivation ------------------------");
			Map sessionMap = ActionContext.getContext().getSession();
			try{
				String useridval=String.valueOf(sessionMap.get("USERID"));
				obj.put("crntusrloginid",useridval );
				obj.put("idcardid", String.valueOf(uniqueId));
				obj.put("statusflg",statusflg);
				obj.put("ivrservicefor", "5");
				JSONObject finaljj=new JSONObject();
				finaljj.put("servicecode", "SI14002");		
				finaljj.put("data", obj);
				String jsonTextFinal = finaljj.toString();
				System.out.println(jsonTextFinal);
				jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
				String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
				String finalUrl =getText("socialindia.idcard.deleteaction");
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
	public String activeActionfun()
	{
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			obj.put("idcardid", String.valueOf(uniqueId));
			obj.put("statusflg",statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI14003");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.idcard.activeaction");
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
public IDCardMasterTblVO getIdcardobj() {
	return idcardobj;
}
public void setIdcardobj(IDCardMasterTblVO idcardobj) {
	this.idcardobj = idcardobj;
}
public String getStatusflg() {
	return statusflg;
}
public void setStatusflg(String statusflg) {
	this.statusflg = statusflg;
}
public String getiVOidcradname() {
	return iVOidcradname;
}
public void setiVOidcradname(String iVOidcradname) {
	this.iVOidcradname = iVOidcradname;
}












}

