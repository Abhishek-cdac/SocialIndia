package com.socialindia.event;

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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.signup.profileUpdate;

public class EventServices extends ActionSupport{
	private String uniqueId;
	//byte imgByt[] = null;
	private File documentfile = null;
	private String documentfileFileName;
	private String eveid;
	private String evestartdate;
	private String eveenddate;
	private String eventShareids;
	private String usershardetails;
	private String lvrEvntfacilitytext;
	JSONObject obj = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	EventMstrTblVO eventobj = new EventMstrTblVO(null, null, null, null, null, null);
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();   
	private String eventtype;
	private String eventrsvp;
	private String eventrsvpview;
	private static final long serialVersionUID = 1L;

	public String execute() {
		return SUCCESS;
	}

	public String eventCreateFun() throws Exception {
			Commonutility.toWriteConsole("event create  loading.......");
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj=null;
			JSONObject json =null;
			String invite=null;
			String groupcode=null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null, lvrRspmsg = null;
			String[] invitearr=null;
			try {			
				//imgByt = profileUpdate.toReadFiletoBytes(documentfile);
				String useridval=String.valueOf(sessionMap.get("USERID"));
				obj.put("crntusrloginid",useridval );
				obj.put("eventtitle", eventobj.getEvetitle());				
				obj.put("eventenddate", eveenddate);
			
				invite=eventobj.getInviteid();				
				if(invite!=null && invite.contains(",") && invite.length()>1){	
					ja = new JSONArray();
					invitearr=invite.split(",");
					for(int k=0;k<invitearr.length;k++){
						ja.put(invitearr[k]);
					}
					obj.put("shareusridJary",ja);
				}else{
					if(invite!=null  && invite.length()>1 && Commonutility.checkempty(invite)){
						ja = new JSONArray();
						obj.put("shareusridJary", ja);
					}
				}
				
				groupcode = String.valueOf(sessionMap.get("GROUPCODE"));
				obj.put("crntusrgrpid",groupcode);
				obj.put("eventendtime", eventobj.getEveendtime());
				obj.put("shortdesc", eventobj.getEveshdesc());
				obj.put("eventdesc", eventobj.getEvedesc());
				obj.put("eventlocation", eventobj.getEvelocation());
				obj.put("ivrservicefor", "1");
				obj.put("eventstartdate", evestartdate);
				//obj.put("eventfiledata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
				if(documentfile!=null){
					obj.put("eventfilesrchpath", documentfile.getAbsolutePath());
					obj.put("eventfilename", documentfileFileName);
				} else{
					obj.put("eventfilesrchpath", "");
					obj.put("eventfilename", documentfileFileName);
				}				
				obj.put("eventstarttime", eventobj.getEvestarttime());				
				obj.put("eventvideopath", eventobj.getEvevideopath());
				obj.put("eventrsvp", eventrsvp);
				System.out.println("eventtype--------create---------"+eventrsvp);
				obj.put("eventtype", eventtype);
				
				obj.put("eventlatlong", eventobj.getEvelatlong());
				obj.put("eventtitlefunctionid", Commonutility.toCheckNullEmpty(eventobj.getEventfunctionid()));
				obj.put("eventfuntemlateid", Commonutility.toCheckNullEmpty(eventobj.getEventfuntempateid()));
				obj.put("eventfacilityid", Commonutility.toCheckNullEmpty(eventobj.getFacitityid()));
				obj.put("shareusrgrpid", "");
				finaljj=new JSONObject();
				finaljj.put("servicecode", "SI8001");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.event.creation.action");
				response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				statusCode = (String) json.get("statuscode");
				lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
				if (statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")) {
					alert.setCls("success");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("success.create.event"));
					}
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "success";
				} else {
					alert.setCls("danger");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("error.create.event"));
					}
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "input";
				}
			} catch (Exception e) {
				Commonutility.toWriteConsole("Exception Found EventServices.class eventCreateFun() : "+e);	
				alert.setCls("danger");
				alert.setMsg(getText("error.create.event"));				
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}finally{
				finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
				statusCode =null;invitearr=null;
			}		
	}

	public String modifyServices() {
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
		String lvrevntfunid = null, lvrEvntfuntxt = null;
		String lvrevnttemplatefunid = null, lvrEvnttemplatefuntxt = null,lvrEvnttemplatefacility=null;
		JSONObject json = null;
		JSONObject json_data = null;
		try{
			obj.put("eventid", uniqueId);
			obj.put("crntusrloginid", uniqueId);
			obj.put("ivrservicefor","3");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI8005");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.eventmodify.action.url");
			String response=commonObj.jsonRequest(finalUrl, temp);	
			Commonutility.toWriteConsole("response : "+response);
			json = new JSONObject(response);
			json_data = (JSONObject) Commonutility.toHasChkJsonRtnValObj(json, "data");
			String lvrStscode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			if(Commonutility.checkempty(lvrStscode) && (lvrStscode.equalsIgnoreCase("00") || lvrStscode.equalsIgnoreCase("0"))) {
				String title = (String)json_data.getString("evetitle");
				evestartdate = (String)json_data.getString("evestartdate");
				eveenddate = (String)json_data.getString("eveenddate");
				String startTime = (String)json_data.getString("evestarttime");
				String endTime = (String)json_data.getString("eveendtime");
				String videopath = (String)json_data.getString("evevideopath");
				String shortdesc = (String)json_data.getString("eveshrtdesc");
				String desc = (String)json_data.getString("evedesc");
				String evevideopath = (String)json_data.getString("evevideopath");
				String evefilename=(String)json_data.getString("evefilename");
				String lvreventlatlong = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventlatlong");
				eveid=(String)json_data.getString("eventid");
				eventobj.setEvefilename(json_data.getString("evefilename"));
				eventtype=(String)json_data.getString("eventtype");
				/*eventrsvp=(String)json_data.getString("eventrsvp");
				System.out.println("eventrsvp-------------8789"+eventrsvp);*/
				ActionContext.getContext().getSession().put("eveimg",eventobj.getEvefilename());
				String eveloc=(String)json_data.getString("evelocation");
				eventrsvp = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventrsvp");
				System.out.println("eventrsvp---------------777777:"+eventrsvp);
				lvrevntfunid = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventfunctionid");
				lvrEvntfuntxt = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventfunctiontext");
				lvrevnttemplatefunid = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventfuntemplateid");
				lvrEvnttemplatefuntxt = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventfuntemplatetext");
				lvrEvnttemplatefacility = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventfacilitytemplateid");
				lvrEvntfacilitytext = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventfacilitytemplatetext");
				
				eventobj.setEvetitle(title);
				eventobj.setEvestarttime(startTime);
				eventobj.setEveendtime(endTime);
				eventobj.setEvevideopath(videopath);
				eventobj.setEveshdesc(shortdesc);
				eventobj.setEvedesc(desc);
				eventobj.setEvevideopath(evevideopath);
				eventobj.setEvelocation(eveloc);
				eventobj.setEvelatlong(lvreventlatlong);
				eventobj.setEventfunctionid(lvrevntfunid);					
				eventobj.setEventfuntempateid(lvrevnttemplatefunid);
				eventobj.setEventfuntempatetext(lvrEvnttemplatefuntxt);
				eventobj.setFacitityid(lvrEvnttemplatefacility);
				eventobj.setEventfuname(lvrEvntfuntxt);
				ActionContext.getContext().getSession().put("EVENTID",eveid);
			} else{
				
			}
			
		} catch(Exception ex){
				Commonutility.toWriteConsole("Step -1: Exception found in "+getClass().getSimpleName()+".calss : "+ex);	
		} finally {
			
		}
		
	   return SUCCESS;
	}
	
	public String toeventmodifyform(){		
			Map sessionMap = null;
			JSONArray ja=null;String invite=null;String[] invitearr=null;String groupcode=null;JSONObject finaljj=null;String jsonTextFinal = null;
			String temp =null;String finalUrl =null;String response =null;JSONObject json =null;String lvrStscode =null;String useridval=null;
			Commonutility.toWriteConsole("event Updated  loading.......");
			try {					
				if (eventobj!=null && eveid!=null) {					
					sessionMap=ActionContext.getContext().getSession();
					//imgByt = profileUpdate.toReadFiletoBytes(documentfile);
					useridval=String.valueOf(sessionMap.get("USERID"));					
					obj.put("crntusrloginid",useridval );
					obj.put("eventid", eveid);
					obj.put("eventtitle",eventobj.getEvetitle());
					obj.put("eventenddate", eveenddate);
					ja = new JSONArray();
					invite = eventobj.getInviteid();				
					if(invite!=null && invite.contains(",") && invite.length()>1){			
						invitearr=invite.split(",");
						for(int k=0;k<invitearr.length;k++){
							ja.put(invitearr[k]);
						}
					}else{
						obj.put("shareusridJary", "");
					}
					obj.put("shareusridJary",ja);
					groupcode=String.valueOf(sessionMap.get("GROUPCODE"));
					obj.put("crntusrgrpid",groupcode);
					obj.put("eventendtime", eventobj.getEveendtime());
					obj.put("shortdesc", eventobj.getEveshdesc());
					obj.put("eventdesc", eventobj.getEvedesc());
					obj.put("ivrservicefor", "2");
					obj.put("eventlocation", eventobj.getEvelocation());
					obj.put("eventstartdate", evestartdate);
					//obj.put("eventfiledata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
					if(documentfile!=null) {
						obj.put("eventfilesrchpath", documentfile.getAbsolutePath());
					} else {
						obj.put("eventfilesrchpath", "");
					}
					
					obj.put("eventstarttime", eventobj.getEvestarttime());
					obj.put("eventfilename", documentfileFileName);
					obj.put("eventvideopath", eventobj.getEvevideopath());		
					obj.put("eventlatlong", eventobj.getEvelatlong());
					obj.put("eventtype", eventtype);
					obj.put("eventrsvp", eventrsvp);
					
					obj.put("eventtitlefunctionid", Commonutility.toCheckNullEmpty(eventobj.getEventfunctionid()));
					obj.put("eventfuntemlateid", Commonutility.toCheckNullEmpty(eventobj.getEventfuntempateid()));
					obj.put("eventfacilityid", Commonutility.toCheckNullEmpty(eventobj.getFacitityid()));
					
					
					obj.put("shareusrgrpid", "");
					finaljj=new JSONObject();
					finaljj.put("servicecode", "SI8002");		
					finaljj.put("data", obj);
					jsonTextFinal = finaljj.toString().trim();
					jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
					temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
					finalUrl = getText("socialindia.event.modifyaction.action");
					Commonutility.toWriteConsole("Step : 3 ");
					response = commonObj.jsonRequest(finalUrl, temp);
					json = new JSONObject(response);
					lvrStscode = (String) json.get("statuscode");
					String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
					if(Commonutility.checkempty(lvrStscode) && (lvrStscode.equalsIgnoreCase("00") || lvrStscode.equalsIgnoreCase("0"))) {
						alert.setCls("success");
						if (Commonutility.checkempty(lvrRspmsg)){
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg(getText("success.update.event"));
						}						
						alertList.add(alert);
						return "success";
					} else {
						alert.setCls("danger");						
						if (Commonutility.checkempty(lvrRspmsg)){
							alert.setMsg(lvrRspmsg);
						} else {
							alert.setMsg(getText("error.update.event"));
						}
						alertList.add(alert);
						return "input";
				}
			} else {
				alert.setCls("danger");										
				alert.setMsg(getText("error.update.event"));				
				alertList.add(alert);
				return "input";
			}
				
		} catch (Exception e) {
			Commonutility.toWriteConsole("Exception found in EventServices.class toeventmodifyform() : "+e);
			alert.setCls("danger");										
			alert.setMsg(getText("error.update.event"));				
			alertList.add(alert);
			return "input";
		}finally{
			ja=null;invite=null;invitearr=null;groupcode=null;finaljj=null;jsonTextFinal = null;temp =null;finalUrl =null;
			response =null;json =null;lvrStscode =null;useridval=null;	
		}		
	}
	public String viewServices(){
		Map sessionMap = null;String useridval=null;JSONObject finaljj=null;String jsonTextFinal=null;String finalUrl =null;String temp=null;String response=null;
		JSONObject json =null;JSONObject json_data =null;
		Map currentSession = ActionContext.getContext().getSession();
		String lvrEvnttemplatefuntxt = null,lvrEvnttemplatefacility=null;
		if(uniqueId==null ){					
			if((uniqueId==null)  && currentSession.get("currentsession_useruniqueId")!=null){							
				String userunque=(String)currentSession.get("currentsession_useruniqueId");
				uniqueId=userunque;
			}
		}else{
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_useruniqueId", String.valueOf(uniqueId));					
		}
		
		try{
			sessionMap = ActionContext.getContext().getSession();			
			useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("eventid", uniqueId);
			obj.put("crntusrloginid", useridval);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI8005");		
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.eventmodify.action.url");
			response=commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			json_data = json.getJSONObject("data");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			String lvrStscode = (String) json.get("statuscode");
			if(Commonutility.checkempty(lvrStscode) && (lvrStscode.equalsIgnoreCase("00") || lvrStscode.equalsIgnoreCase("0"))) {
				String title=(String)json_data.getString("evetitle");
				String startdate=(String)json_data.getString("evestartdate");
				String enddate=(String)json_data.getString("eveenddate");
				String startTime=(String)json_data.getString("evestarttime");
				String endTime=(String)json_data.getString("eveendtime");
				String videopath=(String)json_data.getString("evevideopath");
				String shortdesc=(String)json_data.getString("eveshrtdesc");
				String desc=(String)json_data.getString("evedesc");
				String evevideopath=(String)json_data.getString("evevideopath");
				String evefilename=(String)json_data.getString("evefilename");
				eveid=(String)json_data.getString("eventid");
				String evttype=(String)json_data.getString("eventtype");
				if(evttype.equalsIgnoreCase("2")){
					eventtype="Society Event";
				}else{
					eventtype="Commitee Meeting";
				}
				String evtrsvp= (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventrsvp");
				if(evtrsvp!=null && evtrsvp.equalsIgnoreCase("1")){
					eventrsvpview="Yes";
				}else{
					eventrsvpview="No";
				}
				
				
				String evelocation=(String)json_data.getString("evelocation");
				eventobj.setEvefilename(json_data.getString("evefilename"));
				
				lvrEvnttemplatefuntxt = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventfuntemplatetext");
				lvrEvntfacilitytext = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventfacilitytemplatetext");
				String lvrEvntfuntxt = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "eventfunctiontext");
				ActionContext.getContext().getSession().put("eveimg",eventobj.getEvefilename());
				eventobj.setEventfuntempatetext(lvrEvnttemplatefuntxt);
				eventobj.setEvetitle(title);
				eventobj.setEvestartdate(startdate);
				eventobj.setEveenddate(enddate);
				eventobj.setEvestarttime(startTime);
				eventobj.setEveendtime(endTime);
				eventobj.setEvevideopath(videopath);
				eventobj.setEveshdesc(shortdesc);
				eventobj.setEvedesc(desc);
				eventobj.setEvevideopath(evevideopath);
				eventobj.setEvelocation(evelocation);
				eventobj.setEventfuname(lvrEvntfuntxt);
				
				ActionContext.getContext().getSession().put("EVENTID",eveid);
			}
			
			
			}catch(Exception ex){
				Commonutility.toWriteConsole("Step -1: Exception found in "+getClass().getSimpleName()+".calss viewServices() : "+ex);		
			}finally{
				useridval=null;finaljj=null;jsonTextFinal=null;finalUrl =null;temp=null;response=null;
				json =null;json_data =null;
			}
			
		
		return SUCCESS;
	
	}
	public String inviteEventAction(){
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			String EVENTID=String.valueOf(sessionMap.get("EVENTID"));
			obj.put("crntusrloginid",useridval );
			obj.put("eventid", EVENTID);
			String groupcode=String.valueOf(sessionMap.get("GROUPCODE"));
			obj.put("crntusrgrpid",groupcode);			
			obj.put("shareusrgrpid","");
			JSONArray ja = new JSONArray();
			String invite=eventShareids;
			String[] invitearr=null;
			if(invite!=null && invite.contains(",") && invite.length()>1){			
				invitearr=invite.split(",");
			for(int k=0;k<invitearr.length;k++){
				ja.put(invitearr[k]);
			}
			}else{
				obj.put("shareusridJary", "");
			}
			obj.put("shareusridJary",ja);
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI8007");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.event.shareresidendaction.action");
			String response=commonObj.jsonRequest(finalUrl, temp);
  			JSONObject json = new JSONObject(response);
  			String statusCode = (String) json.get("statuscode");
  			if (statusCode.equalsIgnoreCase("0")) {
  				usershardetails=statusCode;
  				alert.setCls("success");
  				alert.setMsg(getText("Event Shared successfully."));
  				alertList.add(alert);
			return "success";
  			} else {
  				usershardetails=statusCode;
  				alert.setCls("danger");
  				alert.setMsg(getText("shared Error."));
  				alertList.add(alert);
			return "input";
  			}
  		
		}catch(Exception ex){
			Commonutility.toWriteConsole("Step -1: Exception found in "+getClass().getSimpleName()+".calss inviteEventAction() : "+ex);
		}
		return SUCCESS;
	}
	public String deleteEventAction(){
			Commonutility.toWriteConsole("------- Event Delete ------------------------");
			Map sessionMap = ActionContext.getContext().getSession();
			try{
				String useridval=String.valueOf(sessionMap.get("USERID"));
				obj.put("crntusrloginid",useridval );
				obj.put("eventid", uniqueId);
				JSONObject finaljj=new JSONObject();
				finaljj.put("servicecode", "SI8003");		
				finaljj.put("data", obj);
				String jsonTextFinal = finaljj.toString();
				jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
				String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
				String finalUrl =getText("socialindia.event.deleteaction.action");
				String response=commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
				String lvrStscode = (String) json.get("statuscode");
				if(Commonutility.checkempty(lvrStscode) && (lvrStscode.equalsIgnoreCase("00") || lvrStscode.equalsIgnoreCase("0"))) {				
					alert.setCls("success");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("success.delete.event"));
					}					
					alertList.add(alert);
				return "success";
				} else {
					alert.setCls("danger");
					if(Commonutility.checkempty(lvrRspmsg)){
						alert.setMsg(lvrRspmsg);
					} else {
						alert.setMsg(getText("error.delete.event"));
					}
					alertList.add(alert);
					return "input";
				}
  		
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1: Exception found in "+getClass().getSimpleName()+".calss deleteEventAction() : "+ex);
		}
		
	return SUCCESS;	
	}
public String getUniqueId() {
	return uniqueId;
}
public void setUniqueId(String uniqueId) {
	this.uniqueId = uniqueId;
}
public EventMstrTblVO getEventobj() {
	return eventobj;
}
public void setEventobj(EventMstrTblVO eventobj) {
	this.eventobj = eventobj;
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

public File getDocumentfile() {
	return documentfile;
}
public void setDocumentfile(File documentfile) {
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
	return eventShareids;
}
public void setEventShareids(String eventShareids) {
	this.eventShareids = eventShareids;
}
public String getUsershardetails() {
	return usershardetails;
}
public void setUsershardetails(String usershardetails) {
	this.usershardetails = usershardetails;
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

public String getLvrEvntfacilitytext() {
	return lvrEvntfacilitytext;
}

public void setLvrEvntfacilitytext(String lvrEvntfacilitytext) {
	this.lvrEvntfacilitytext = lvrEvntfacilitytext;
}

public String getEventtype() {
	return eventtype;
}

public void setEventtype(String eventtype) {
	this.eventtype = eventtype;
}

public String getEventrsvp() {
	return eventrsvp;
}

public void setEventrsvp(String eventrsvp) {
	this.eventrsvp = eventrsvp;
}

public String getEventrsvpview() {
	return eventrsvpview;
}

public void setEventrsvpview(String eventrsvpview) {
	this.eventrsvpview = eventrsvpview;
}





}

