package com.socialindia.skillsmgmt;

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
import com.socialindia.login.EncDecrypt;

public class SkillAction extends ActionSupport{
	private String[] skillnamelist=null;
	private String category_hidd_id;
	private String category_id_name;
	private String skillname;
	private String skillId;
	private String skillstatus;
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private static final long serialVersionUID = 1L;
	public String execute(){
		  Log logwrite = null;
		  Map sessionMap = null;
		  JSONObject obj = null;
		  JSONObject data = null;
		  JSONObject lvrRcvrespjsonobj = null;
		  try{
			  	logwrite = new Log();
				obj = new JSONObject();
				data = new JSONObject();
				sessionMap = ActionContext.getContext().getSession();
				logwrite.logMessage("Step 1 : Skill Create Called [Start]", "info", SkillAction.class);	
				obj.put("status", "1");
				String[] skill = skillnamelist[0].split("!_!");
				JSONArray skilllist = new JSONArray();
				for (int i = 0; i < skill.length; i++) {
					skilllist.put(skill[i]);
				}
				skill = null;
				obj.put("skilllist", skilllist);
				obj.put("categoryid", category_hidd_id);
				data.put("entryby", String.valueOf(sessionMap.get("USERID")));
				data.put("servicecode", "SI3800001");
				data.put("servicefor", "1");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				String finalUrl = getText("socialindia.skillupdate.skillUpdateaction");
				String response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrespjsonobj = new JSONObject(response);
				String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
		            alert.setCls("success");
		            alert.setMsg(getText("Skill added successfully"));
		            alertList.add(alert);
		            sessionMap.put("alertList", alertList);
		            return "success";
				} else {
		            alert.setCls("danger");
		            alert.setMsg(getText("Error"));
		            alertList.add(alert);
		            sessionMap.put("alertList", alertList);
		            return "input";
		          }
				
			  
		  } catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : Skill Create Called SkillAction.class Exception Found : " + ex);
			  logwrite.logMessage("Step -1 : Skill Create Called Exception Found : "+ex, "error", SkillAction.class); 
			  alert.setCls("danger");
	            alert.setMsg(getText("Error"));
	            alertList.add(alert);
	            sessionMap.put("alertList", alertList);
	            return "input";
		  } finally {
			  
		  }		  		
	  
	}
	
	public String editSkillAction(){
		  Log logwrite = null;
		  Map sessionMap = null;
		  JSONObject obj = null;
		  JSONObject data = null;
		  JSONObject lvrRcvrespjsonobj = null;
		  try{
			  	logwrite = new Log();
				obj = new JSONObject();
				data = new JSONObject();
				sessionMap = ActionContext.getContext().getSession();
				logwrite.logMessage("Step 1 : Skill Edit Called [Start]", "info", SkillAction.class);	
				obj.put("skilllist", skillId);
				data.put("entryby", String.valueOf(sessionMap.get("USERID")));
				data.put("servicecode", "SI3800003");
				data.put("servicefor", "3");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				String finalUrl = getText("socialindia.skillupdate.skilleditaction");
				String response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrespjsonobj = new JSONObject(response);
				String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
				JSONObject json_data = lvrRcvrespjsonobj.getJSONObject("data");
				JSONObject json = json_data.getJSONObject("skilldetails");
				skillId = json.getString("skillid");
				skillname = json.getString("skillname");
				category_hidd_id = json.getString("skillcategoryid");
				category_id_name=json.getString("skillcategoryname");
				
		  }catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : Skill Edit Called SkillAction.class Exception Found : " + ex);
				logwrite.logMessage("Step -1 : Skill Edit Called Exception Found : "+ex, "error", SkillAction.class); 
		  }
		  
		  return SUCCESS;
	  
	}
	public String modifySkillAction(){
		  Log logwrite = null;
		  Map sessionMap = null;
		  JSONObject obj = null;
		  JSONObject data = null;
		  JSONObject lvrRcvrespjsonobj = null;
		  try{
			  	logwrite = new Log();
				obj = new JSONObject();
				data = new JSONObject();
				sessionMap = ActionContext.getContext().getSession();
				logwrite.logMessage("Step 1 : Skill modify Called [Start]", "info", SkillAction.class);	
				obj.put("status", "1");
				obj.put("skillid", skillId);
				obj.put("skillname", skillname);
				obj.put("categoryid", category_hidd_id);
				data.put("entryby", String.valueOf(sessionMap.get("USERID")));
				data.put("servicecode", "SI3800002");
				data.put("servicefor", "2");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				String finalUrl = getText("socialindia.skillmodify.skillUpdateaction");
				String response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrespjsonobj = new JSONObject(response);
				String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
		            alert.setCls("success");
		            alert.setMsg(getText("Skill updated successfully"));
		            alertList.add(alert);
		            return "success";
				} else {
		            alert.setCls("danger");
		            alert.setMsg(getText("Error"));
		            alertList.add(alert);
		            return "input";
		          }
				
			  
		  }catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : Skill modify Called SkillAction.class Exception Found : " + ex);
				logwrite.logMessage("Step -1 : Skill modify Exception Found : "+ex, "error", SkillAction.class); 
		  }
		  
		  return SUCCESS;
	}
	public String activeandDeactiveskill(){
		  Log logwrite = null;
		  Map sessionMap = null;
		  JSONObject obj = null;
		  JSONObject data = null;
		  JSONObject lvrRcvrespjsonobj = null;
		  String temp="";String finalUrl="";String response ="";
		  try{
			  	logwrite = new Log();
				obj = new JSONObject();
				data = new JSONObject();
				sessionMap = ActionContext.getContext().getSession();
				logwrite.logMessage("Step 1 : Skill modify Called [Start]", "info", SkillAction.class);	
				obj.put("status", skillstatus);
				obj.put("skillid", skillId);
				data.put("entryby", String.valueOf(sessionMap.get("USERID")));
				data.put("servicecode", "SI3800004");
				data.put("servicefor", "4");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				finalUrl = getText("socialindia.skillmodify.skillactiveordeactive");
				response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrespjsonobj = new JSONObject(response);
				String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
				
			  
		  }catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : Skill modify Called SkillAction.class Exception Found : " + ex);
				logwrite.logMessage("Step -1 : Skill modify Exception Found : "+ex, "error", SkillAction.class); 
		  }finally{
			logwrite = null;
			 sessionMap = null;
			  obj = null;
			  data = null;
			  lvrRcvrespjsonobj = null;
			 temp=""; finalUrl="";response =""; 
		  }
		  
		  return SUCCESS;
	
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
	public String[] getSkillnamelist() {
		return skillnamelist;
	}
	public void setSkillnamelist(String[] skillnamelist) {
		this.skillnamelist = skillnamelist;
	}
	public String getCategory_hidd_id() {
		return category_hidd_id;
	}
	public void setCategory_hidd_id(String category_hidd_id) {
		this.category_hidd_id = category_hidd_id;
	}
	public String getCategory_id_name() {
		return category_id_name;
	}
	public void setCategory_id_name(String category_id_name) {
		this.category_id_name = category_id_name;
	}
	public String getSkillId() {
		return skillId;
	}
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
	public String getSkillname() {
		return skillname;
	}
	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}
	public String getSkillstatus() {
		return skillstatus;
	}
	public void setSkillstatus(String skillstatus) {
		this.skillstatus = skillstatus;
	}
	

}
