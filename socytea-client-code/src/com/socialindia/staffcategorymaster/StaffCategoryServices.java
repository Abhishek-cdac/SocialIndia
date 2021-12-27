package com.socialindia.staffcategorymaster;


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
import com.socialindia.generalmgnt.persistance.CategoryMasterTblVO;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;


public class StaffCategoryServices extends ActionSupport{
	  private String uniqueId;
	  private String statusflg;
	  private String iVOcategoryname;
	  JSONObject obj = new JSONObject();
	  private AlertVo alert = new AlertVo();
	  private List<AlertVo> alertList = new ArrayList<AlertVo>();
	  private Common commonObj = new CommonDao(); 
	  commomServices commonSnippet=new commomServices();
	  CategoryMasterTblVO categoryobj=new CategoryMasterTblVO(0, null);
	private static final long serialVersionUID = 1L;

	public String execute() {		
		return SUCCESS;
	}
	public String StaffCategoryCreateFun() throws Exception{
			System.out.println("category create  loading.......");
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj=null;
			JSONObject json =null;
			String invite=null;
			String groupcode=null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null,msg=null;
			String[] invitearr=null;
			try {	
				if(!iVOcategoryname.equalsIgnoreCase("null")&& iVOcategoryname!=null)
				{
				obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("categoryname",iVOcategoryname);
				obj.put("status", "1");
				obj.put("ivrservicefor", "1");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				finaljj=new JSONObject();
				finaljj.put("servicecode", "SI16001");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				System.out.println("req::: "+jsonTextFinal);
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.staffcategorycreation.action");
				response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				msg = (String) json.get("message");
				statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					
					if (iVOcategoryname == null) {
						addFieldError("groupmang.groupName", getText("Error.usercreate.groupname"));
					} else {
				          if (msg.equalsIgnoreCase("Staff Category Created.")) {
				        	  iVOcategoryname="";
				        	  alert.setCls("success");
				        	  alert.setMsg(getText("StaffCategory.Create.Success"));
				        	  alertList.add(alert);
				        	  sessionMap.put("alertList", alertList);
				        	  return "success";
				          } else if (msg.equalsIgnoreCase("Staff Category already exist.")) {
								iVOcategoryname = "";
								alert.setCls("danger");
								alert.setMsg(getText("StaffCategory.exist"));
								alertList.add(alert);
								sessionMap.put("alertList", alertList);
								return "input";
							} else {
								alert.setCls("danger");
								alert.setMsg(getText("StaffCategory.Create.Error"));
								alertList.add(alert);
								sessionMap.put("alertList", alertList);
								return "input";
				          }
				        
				      }
					//alert.setCls("success");
					//alert.setMsg(getText("StaffCategory.Create.Success"));
					//alertList.add(alert);
					//return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("StaffCategory.Create.Error"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "error";
				}
				
			} else {

			}
			} catch (Exception e) {
				Commonutility.toWriteConsole("Exception Found in StaffCategoryServices.StaffCategoryCreateFun() : "+e);
			}finally{
				finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
				statusCode =null;invitearr=null;msg=null;
			}
		return "success";
	}
	
	
	public String deleteActionfun(){
			System.out.println("------- category Deactivation ------------------------");
			Map sessionMap = ActionContext.getContext().getSession();
			try{
				String useridval=String.valueOf(sessionMap.get("USERID"));
				obj.put("crntusrloginid",useridval );
				obj.put("categoryid", String.valueOf(uniqueId));
				obj.put("statusflg",statusflg);
				obj.put("ivrservicefor", "5");
				JSONObject finaljj=new JSONObject();
				finaljj.put("servicecode", "SI16002");		
				finaljj.put("data", obj);
				String jsonTextFinal = finaljj.toString();
				System.out.println(jsonTextFinal);
				jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
				String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
				String finalUrl =getText("socialindia.staffcategory.deleteaction");
				String response=commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				String statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					alert.setCls("success");
					alert.setMsg(getText("StaffCategory.Delete.Success"));
					alertList.add(alert);
				return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("StaffCategory.Delete.Error"));
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
			obj.put("categoryid", String.valueOf(uniqueId));
			obj.put("statusflg",statusflg);
			obj.put("ivrservicefor", "5");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI16003");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.staffcategory.activeaction");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("StaffCategory.Active.Success"));
				alertList.add(alert);
			return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("StaffCategory.Active.Error"));
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












}

