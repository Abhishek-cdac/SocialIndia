package com.socialindia.flashnews;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class FlashNews extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String townshipid;
	private String townshipName;
	private String societyName;
	private String societyId;
	private String flashnewcontent;
	private String exdate;
	private String flashId;
	private String extime;
	private File flashnewimage;
	private String flashnewimageFileName;
	private String flashnewprofileimage;
	private String title;
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	
	public String  execute() {
		
		return SUCCESS;
	}
	public String createFlashNews(){
		 // byte imgByt[] = null;
		  Log logwrite = null;
		  Map sessionMap = null;
		  JSONObject obj = null;
		  JSONObject data = null;
		  JSONObject lvrRcvrespjsonobj = null;
		  String temp = null,finalUrl=null,response=null;
		  try{
			  if(flashnewcontent!=null){
			  	logwrite = new Log();
				obj = new JSONObject();
				data = new JSONObject();
				sessionMap = ActionContext.getContext().getSession();
				logwrite.logMessage("Step 1 : FlashNews Create Called [Start]", "info", FlashNews.class);	
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				if(societyId!=null && !societyId.equalsIgnoreCase("")&& !societyId.equalsIgnoreCase("-1")){
					obj.put("societyId", societyId);
				} else {
					obj.put("societyId", String.valueOf(sessionMap.get("sSoctyId")));
				}
				obj.put("flashnewcontent", flashnewcontent );
				//SimpleDateFormat displayFormat = new SimpleDateFormat("hh.mm.ss");
			   // SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
			   // Date date = parseFormat.parse(extime);
			    obj.put("exdate", Commonutility.toCheckNullEmpty(exdate)); // dd-MM-yyyy
			    obj.put("extime", Commonutility.toCheckNullEmpty(extime)); // hh:mm a
			    obj.put("title", title ); 
			    if(flashnewimage!=null){
			    	 obj.put("flashnewimage", flashnewimage.getAbsolutePath()); 
					 obj.put("flashnewimageName", flashnewimageFileName);	
			    }else{
			    	obj.put("flashnewimage", ""); 
					 obj.put("flashnewimageName", "");	
			    }
				data.put("servicecode", "SI410001");
				data.put("currentloginid", Commonutility.toCheckNullEmpty(String.valueOf(sessionMap.get("USERID"))));
				data.put("servicefor", "1");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				finalUrl = getText("socialindia.flashnew.newflashnew");
				response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrespjsonobj = new JSONObject(response);
				String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
		            alert.setCls("success");
		            alert.setMsg(getText("flashnew.Create.Success"));
		            alertList.add(alert);
		            sessionMap.put("alertList", alertList);
		            return "success";
				} else {
		            alert.setCls("danger");
		            alert.setMsg(getText("flashnew.Create.Error"));
		            alertList.add(alert);
		            sessionMap.put("alertList", alertList);
		            return "input";
		          }
			  }
		  }catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : FlashNews Create Called FlashNewsadd.class Exception Found : " + ex);
			  logwrite.logMessage("Step -1 : FlashNews Create Called Exception Found : "+ex, "error", FlashNews.class);
			  alert.setCls("danger");
	          alert.setMsg(getText("flashnew.Create.Error"));
	          alertList.add(alert);
	          sessionMap.put("alertList", alertList);
	          return "input";
		  }		
		  return "success";
	  }
	
	public String viewFlashNews(){
			  Log logwrite = null;
			  Map sessionMap = null;
			  JSONObject obj = null;
			  JSONObject data = null;
			  JSONObject lvrRcvrespjsonobj = null;
			  JSONObject json_data = null;
			  String temp = null,finalUrl=null,response=null;
			  try{
				  sessionMap = ActionContext.getContext().getSession();
				  if (flashId == null) {
						if ((flashId == null)&& sessionMap.get("locflashId") != null) {
							String userunque = (String) sessionMap.get("locflashId");
							flashId = userunque;
						}
					} else {
						sessionMap.put("locflashId", String.valueOf(flashId));
					}
				  	logwrite = new Log();
					obj = new JSONObject();
					data = new JSONObject();
					
					logwrite.logMessage("Step 1 : FlashNews view Called [Start]", "info", FlashNews.class);	
					obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
					obj.put("flashId", flashId);
					data.put("servicecode", "SI410003");
					data.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
					data.put("servicefor", "3");
					data.put("data", obj);
					String jsonTextFinal = data.toString().trim();
					jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
					temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
					finalUrl = getText("socialindia.flashnew.viewflashnew");
					response = commonObj.jsonRequest(finalUrl, temp);
					lvrRcvrespjsonobj = new JSONObject(response);
					String lvrStscode = (String) lvrRcvrespjsonobj.get("statuscode");
					json_data = (JSONObject) Commonutility.toHasChkJsonRtnValObj(lvrRcvrespjsonobj, "data");
					if(Commonutility.checkempty(lvrStscode) && (lvrStscode.equalsIgnoreCase("00") || lvrStscode.equalsIgnoreCase("0"))) {
						JSONObject json = json_data.getJSONObject("flashnewdetails");
						String exdates = (String) json.getString("expirydate");
						if (Commonutility.checkempty(exdates)) {
						
							String ss[] = exdates.split(" ");
							exdate = ss[0];
							System.out.println(exdates+"----exdate --------- "+exdate);
							SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
							SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
							Date date = displayFormat.parse(ss[1]);
							extime = parseFormat.format(date);
						}
						flashnewcontent = (String) json.getString("newcontent");
						societyId = (String) json.getString("societyId");
						societyName = (String) json.getString("societyName");
						townshipName = (String) json.getString("townshipname");
						townshipid = (String) json.getString("townshipId");
						flashId = (String) json.getString("fleshnewId");
						title = (String) json.getString("newstitle");
						flashnewprofileimage = (String) json.getString("newsimagename");
						ActionContext.getContext().getSession().put("profileimage",flashnewprofileimage);
					}
				  
			  }catch(Exception ex){
				  Commonutility.toWriteConsole("Step -1 : FlashNews view Called FlashNewsview.class Exception Found : " + ex);
				  logwrite.logMessage("Step -1 : FlashNews Create view Exception Found : "+ex, "error", FlashNews.class);
		          return "input";
			  }		  		 
		  return SUCCESS;
		  
	  }public String updateFlashNews(){
		 // byte imgByt[] = null;
		  Log logwrite = null;
		  Map sessionMap = null;
		  JSONObject obj = null;
		  JSONObject data = null;
		  JSONObject lvrRcvrespjsonobj = null;
		  String temp = null,finalUrl=null,response=null;
		  try{
			  	logwrite = new Log();
				obj = new JSONObject();
				data = new JSONObject();
				sessionMap = ActionContext.getContext().getSession();
				logwrite.logMessage("Step 1 : FlashNews Update Called [Start]", "info", FlashNews.class);	
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				obj.put("flashId", flashId);
				if(societyId!=null && !societyId.equalsIgnoreCase("")&& !societyId.equalsIgnoreCase("-1")){
					obj.put("societyId", societyId);
				} else {
					obj.put("societyId", String.valueOf(sessionMap.get("sSoctyId")));
				}
				obj.put("flashnewcontent", flashnewcontent );
			//	SimpleDateFormat displayFormat = new SimpleDateFormat("hh.mm.ss");
			//    SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
			 //   Date date = parseFormat.parse(extime);
				 obj.put("exdate", Commonutility.toCheckNullEmpty(exdate)); // dd-MM-yyyy
				 obj.put("extime", Commonutility.toCheckNullEmpty(extime)); // hh:mm a
				 obj.put("title", title);
				 if (flashnewimage != null) {
					 obj.put("flashnewimage", flashnewimage.getAbsolutePath());
					obj.put("flashnewimageName", flashnewimageFileName);
				 } else {
					 obj.put("flashnewimage", "");
					obj.put("flashnewimageName", "");
				 }
				data.put("servicecode", "SI410001");
				data.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
				data.put("servicefor", "1");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				finalUrl = getText("socialindia.flashnew.modifyflashnew");
				response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrespjsonobj = new JSONObject(response);
				String statusCode = (String) lvrRcvrespjsonobj.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
		            alert.setCls("success");
		            alert.setMsg(getText("flashnew.update.Success"));
		            alertList.add(alert);
		            sessionMap.put("alertList", alertList);
		            return "success";
				} else {
		            alert.setCls("danger");
		            alert.setMsg(getText("flashnew.update.Error"));
		            alertList.add(alert);
		            sessionMap.put("alertList", alertList);
		            return "input";
		          }
		  }catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : FlashNews Update Called Update.class Exception Found : " + ex);
			  logwrite.logMessage("Step -1 : FlashNews Create Update Exception Found : "+ex, "error", FlashNews.class);
			  alert.setCls("danger");
	          alert.setMsg(getText("flashnew.Update.Error"));
	          alertList.add(alert);
	          sessionMap.put("alertList", alertList);
	          return "input";
		  }		  		 
	  }public String deleteFlashNews(){
		  Log logwrite = null;
		  Map sessionMap = null;
		  JSONObject obj = null;
		  JSONObject data = null;
		  JSONObject lvrRcvrespjsonobj = null;
		  JSONObject json_data = null;
		  String temp = null,finalUrl=null,response=null;
			String ivrservicecode = null;
			String ivrresponsecode = null;
			String ivrmsg=null;
			String ivrstatuscode=null;
			JSONObject locObjRecvJson = null;
			//JSONObject locObjRecvdataJson = null;

		  try{
			  	logwrite = new Log();
				obj = new JSONObject();
				data = new JSONObject();
				sessionMap = ActionContext.getContext().getSession();
				logwrite.logMessage("Step 1 : FlashNews Delete Called [Start]", "info", FlashNews.class);	
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				obj.put("flashId", flashId);
				data.put("servicecode", "SI410004");
				data.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
				data.put("servicefor", "4");
				data.put("data", obj);
				String jsonTextFinal = data.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
				finalUrl = getText("socialindia.flashnew.Deleteflashnew");
				response = commonObj.jsonRequest(finalUrl, temp);
				lvrRcvrespjsonobj = new JSONObject(response);
				json_data = (JSONObject) Commonutility.toHasChkJsonRtnValObj(lvrRcvrespjsonobj, "data");
				
				if(response!=null && !response.equalsIgnoreCase("null") && response.length()>0) {
		    		  boolean ivIsJson = Commonutility.toCheckIsJSON(response);
		    		  if (ivIsJson) {
		    			  locObjRecvJson = new JSONObject(response);
				    	  ivrservicecode = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "servicecode");
				    	  ivrresponsecode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "respcode");
				    	  ivrmsg = (String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "message");
				    	  ivrstatuscode=(String) Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "statuscode");
							if (ivrstatuscode.equalsIgnoreCase("00") || ivrstatuscode.equalsIgnoreCase("0")) {
								alert.setCls("success");
								if(Commonutility.checkempty(ivrmsg)){
									alert.setMsg("Flash news deleted successfully.");
								} else {
									alert.setMsg("Flash news deleted successfully.");
								}
								alertList.add(alert);
								return "success";
							} else {
								alert.setCls("danger");
								if(Commonutility.checkempty(ivrmsg)){
									alert.setMsg(ivrmsg);
								} else {
									alert.setMsg("Campaign deletion error.");
								}
								alertList.add(alert);
								return "input";
							}
		    		  }
				 } else {
					    alert.setCls("danger");
						alert.setMsg("Campaign deletion error.");
						alertList.add(alert);
						return "input";
				 }
		  }catch(Exception ex){
			  Commonutility.toWriteConsole("Step -1 : FlashNews view Called FlashNewsview.class Exception Found : " + ex);
			  logwrite.logMessage("Step -1 : FlashNews Create view Exception Found : "+ex, "error", FlashNews.class);
	          return "input";
		  }		  		 
	  
	  
	  
	  return SUCCESS;
	  
  }
	public String getTownshipid() {
		return townshipid;
	}
	public void setTownshipid(String townshipid) {
		this.townshipid = townshipid;
	}
	public String getTownshipName() {
		return townshipName;
	}
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}
	public String getSocietyName() {
		return societyName;
	}
	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}
	public String getSocietyId() {
		return societyId;
	}
	public void setSocietyId(String societyId) {
		this.societyId = societyId;
	}
	public String getFlashnewcontent() {
		return flashnewcontent;
	}
	public void setFlashnewcontent(String flashnewcontent) {
		this.flashnewcontent = flashnewcontent;
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
	public String getExdate() {
		return exdate;
	}
	public void setExdate(String exdate) {
		this.exdate = exdate;
	}
	public String getFlashId() {
		return flashId;
	}
	public void setFlashId(String flashId) {
		this.flashId = flashId;
	}
	public String getExtime() {
		return extime;
	}
	public void setExtime(String extime) {
		this.extime = extime;
	}
	public File getFlashnewimage() {
		return flashnewimage;
	}
	public void setFlashnewimage(File flashnewimage) {
		this.flashnewimage = flashnewimage;
	}
	public String getFlashnewimageFileName() {
		return flashnewimageFileName;
	}
	public void setFlashnewimageFileName(String flashnewimageFileName) {
		this.flashnewimageFileName = flashnewimageFileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFlashnewprofileimage() {
		return flashnewprofileimage;
	}
	public void setFlashnewprofileimage(String flashnewprofileimage) {
		this.flashnewprofileimage = flashnewprofileimage;
	}
	

}
