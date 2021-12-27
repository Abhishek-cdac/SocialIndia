package com.socialindia.tendermgmt;

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

public class TenderServices extends ActionSupport{
	  private String uniqueId;
	  //byte imgByt[]=null;
	  private List <File> documentfile = new ArrayList <File> ();
	  private String documentfileFileName; 
	  private String eveid;
	  private String evestartdate;
	  private String eveenddate;
	  private String tenderShareids;
	  private String usershardetails;
	  private String townshipname;
	  JSONObject obj = new JSONObject();
	  private AlertVo alert = new AlertVo();
	  private List<AlertVo> alertList = new ArrayList<AlertVo>();
	  TenderTblVO tenderobj=new TenderTblVO(null,null,null,null,0,null);
	  List<TenderDocTblVO> tenderdocList =new ArrayList<TenderDocTblVO>();
	  private Common commonObj = new CommonDao(); 
	  commomServices commonSnippet=new commomServices();
	private static final long serialVersionUID = 1L;

	public String execute() {		
		return SUCCESS;
	}
	public String tenderCreateFun() throws Exception {
			System.out.println("tender create  loading.......");
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj = null;
			JSONObject json = null;			
			String groupcode = null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null;
			try {	
				String sFileName = documentfileFileName;
				String[] filename=null;
				obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("tendertitle",tenderobj.getTenderName());
				obj.put("tenderdate", tenderobj.getTenderDate());
				obj.put("tenderdesc", tenderobj.getTenderDetails());
				obj.put("ivrservicefor", "1");				
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				JSONArray imagearray = new JSONArray();
				JSONArray fileNamearray = new JSONArray();
				if(documentfileFileName!=null && !documentfileFileName.equalsIgnoreCase("null")){					
				
				if (sFileName.contains(",")) {
				} else {
					sFileName = sFileName + ",";
				}
				filename = sFileName.split(",");
				double totfilesize = 0;
				//byte imgBytlogo[] = new byte[2048];
					for(int f=0;f<documentfile.size();f++){
						//imgBytlogo=new byte[2048];
						double filesiz=documentfile.get(f).length();
						totfilesize+=filesiz;
						String fileName=filename[f];						
						//imgBytlogo=commonSnippet.toReadFiletoBytes(documentfile.get(f));						
						//imagearray.put(commonSnippet.toByteAryToBase64EncodeStr(imgBytlogo));
						if(documentfile.get(f)!=null){
							imagearray.put(documentfile.get(f).getAbsolutePath());
							fileNamearray.put(fileName);
						} else {
							//imagearray.put("");
						}						
						
					}
					//obj.put("imageDetail",imagearray);
					obj.put("imgsrchpath",imagearray);
					obj.put("fileName",fileNamearray);
				} else {
					/*obj.put("imageDetail","");
					obj.put("fileName","");*/
				}
				finaljj=new JSONObject();
				finaljj.put("servicecode", "SI12000");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();	
				System.out.println("request::::::::::: "+jsonTextFinal);
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.tender.creation.action");
				response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					alert.setCls("success");
					alert.setMsg(getText("Tender.Create.Success"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Tender.Create.Error"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "error";
				}
			} catch (Exception e) {
				alert.setCls("danger");
				alert.setMsg(getText("Tender.Create.Error"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				System.out.println("Exception Found in TenderServices.class tenderCreateFun() : "+e);				
			}finally{
				finaljj=null;json =null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
				statusCode =null;
			}
		return "success";
	}
	public String modifyServices() {
		System.out.println("Tender Edit Block Enter.");
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
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject lvrfinaljsonobj = null;
		try{			
			obj.put("tenderid", uniqueId);
			obj.put("crntusrloginid", String.valueOf(currentSession.get("USERID")));
			obj.put("ivrservicefor","3");
			lvrfinaljsonobj = new JSONObject();
			lvrfinaljsonobj.put("servicecode", "SI12001");		
			lvrfinaljsonobj.put("data", obj);
			String jsonTextFinal = lvrfinaljsonobj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.tendermodify.action");
			String response = commonObj.jsonRequest(finalUrl, temp);			
			String docfilename ="";
			String docformat ="";
			String docfilepath="";
			String filedownloadurl="";
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					JSONObject json_data = locObjRecvJson.getJSONObject("data");
					JSONArray docnamearr = new JSONArray();
					docnamearr = (JSONArray) Commonutility .toHasChkJsonRtnValObj(json_data, "jArry_doc_files");					
					if(docnamearr !=null && docnamearr.length() > 0)
					{
						JSONObject downloadjj=new JSONObject();
						for(int i =0 ;i<docnamearr.length();i++)
						{
							
							docfilename = (String)Commonutility.toHasChkJsonRtnValObj(docnamearr.getJSONObject(i),"filesname");
							docformat = (String)Commonutility.toHasChkJsonRtnValObj(docnamearr.getJSONObject(i),"fileformat");
							docfilepath = (String)Commonutility.toHasChkJsonRtnValObj(docnamearr.getJSONObject(i),"filepath");
							downloadjj = new JSONObject();
							downloadjj.put("servicecode", "SI12015");
							downloadjj.put("docfilepath", docfilepath);
							downloadjj.put("docfilename", docfilename);
							String downloadTextFinal = downloadjj.toString();
							downloadTextFinal=EncDecrypt.encrypt(downloadTextFinal);
							filedownloadurl = getText("socialindia.downloadfile.action")+"?ivrparams="+URLEncoder.encode(downloadTextFinal);
							tenderdocList.add(new TenderDocTblVO(docfilename, docformat,docfilepath,filedownloadurl));
							downloadjj = null;
						}
					}					
					tenderobj.setTenderId(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"tenderid")));
					tenderobj.setTenderName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"tendertitle"));
					tenderobj.setTenderDate((String)Commonutility.toHasChkJsonRtnValObj(json_data,"tenderdate"));
					tenderobj.setTenderDetails((String)Commonutility.toHasChkJsonRtnValObj(json_data,"tenderdesc"));
					tenderobj.setEntryBy((String)Commonutility.toHasChkJsonRtnValObj(json_data,"tenderentryby"));
					tenderobj.setSocietyname((String)Commonutility.toHasChkJsonRtnValObj(json_data,"tendersocietyname"));
					townshipname=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"tendertownshipname");
					//url 
					
				}
			}
			ActionContext.getContext().getSession().put("tenderSessID", tenderobj.getTenderId());
		} catch(Exception Ex) {
			System.out.println("Exception Found in TenderServices.class modifyServices() : "+Ex);
		} finally {
			lvrfinaljsonobj = null; locObjRecvJson = null;
		}
	   return SUCCESS;
	}
	
	public String totendermodifyform(){		
		System.out.println("Tender Modify Block Enter. Tender Id : "+tenderobj.getTenderId());
		Map sessionMap = ActionContext.getContext().getSession();
		JSONObject finaljj = null;
		JSONObject json = null;
		String groupcode=null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
		String statusCode =null;
		JSONArray imagearray = null;
		JSONArray fileNamearray = null;	
		try {	
			String sFileName = documentfileFileName;
			String[] filename=null;
			obj.put("tenderid", String.valueOf(tenderobj.getTenderId()));
			obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
			obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
			obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
			obj.put("tendertitle",tenderobj.getTenderName());
			obj.put("tenderdate", tenderobj.getTenderDate());
			obj.put("tenderdesc", tenderobj.getTenderDetails());
			obj.put("ivrservicefor", "2");
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			imagearray = new JSONArray();
			fileNamearray = new JSONArray();			
			if(documentfileFileName!=null && !documentfileFileName.equalsIgnoreCase("null")) {						
				if (sFileName != null && sFileName.contains(",")) {
				} else {
					sFileName = sFileName + ",";
				}
				filename = sFileName.split(",");
				double totfilesize = 0;
				//byte imgBytlogo[] = new byte[2048];
				for (int f = 0; f < documentfile.size(); f++) {
			    	//imgBytlogo=new byte[2048];
			    	double filesiz = documentfile.get(f).length();
			    	totfilesize += filesiz;
			    	String fileName=filename[f];
			    	//imgBytlogo=commonSnippet.toReadFiletoBytes(documentfile.get(f));
			    	//imagearray.put(commonSnippet.toByteAryToBase64EncodeStr(imgBytlogo));			    	
			    	if(documentfile.get(f)!=null){
						imagearray.put(documentfile.get(f).getAbsolutePath());
						fileNamearray.put(fileName);
					} else {
						//imagearray.put("");
					}				    				    				  
			    }
			   // obj.put("imageDetail",imagearray);
			    obj.put("imgSrchpth",imagearray);
			    obj.put("fileName",fileNamearray);
			} else {
				obj.put("imageDetail","");
				obj.put("fileName","");
			}
			finaljj = new JSONObject();
			finaljj.put("servicecode", "SI12002");		
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString().trim();			
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.tender.update.action");
			response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Tender.Update.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Tender.Update.Error"));
				alertList.add(alert);
				return "input";
			}
		} catch (Exception e) {
			System.out.println("Exception Found in TenderService.class totendermodifyform() : "+e);			
		}finally{
			finaljj=null;json =null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
			statusCode =null;
		}
		System.out.println("Tender Modify Block Exit.");
		return "success";
	}
	
	public String deleteTenderAction(){
			System.out.println("["+Commonutility.toGetCurrentDatetime("yyyy-MM-dd HH:ss")+"] Tender Delete Block Enter.");
			Map sessionMap = ActionContext.getContext().getSession();
			try{							
				String useridval=String.valueOf(sessionMap.get("USERID"));
				obj.put("crntusrloginid",useridval );
				obj.put("tenderid", uniqueId);
				obj.put("ivrservicefor", "5");
				JSONObject finaljj=new JSONObject();
				finaljj.put("servicecode", "SI12004");		
				finaljj.put("data", obj);
				String jsonTextFinal = finaljj.toString();
				System.out.println(jsonTextFinal);
				jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
				String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
				String finalUrl =getText("socialindia.tender.deleteaction");
				String response=commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				String statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					alert.setCls("success");
					alert.setMsg(getText("Tender.Delete.Success"));
					alertList.add(alert);
				return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Tender.Delete.Error"));
					alertList.add(alert);
					return "input";
				}
  		
			}catch(Exception ex){
				System.out.println("["+Commonutility.toGetCurrentDatetime("yyyy-MM-dd HH:ss")+"] Exception found TenderServices.class deleteTenderAction() : "+ex);
			}finally{
			
			}
			System.out.println("["+Commonutility.toGetCurrentDatetime("yyyy-MM-dd HH:ss")+"] Tender Delete Block Exit.");
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
	return tenderShareids;
}
public void setEventShareids(String tenderShareids) {
	this.tenderShareids = tenderShareids;
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
public TenderTblVO getTenderobj() {
	return tenderobj;
}
public void setTenderobj(TenderTblVO tenderobj) {
	this.tenderobj = tenderobj;
}
public List<TenderDocTblVO> getTenderdocList() {
	return tenderdocList;
}
public void setTenderdocList(List<TenderDocTblVO> tenderdocList) {
	this.tenderdocList = tenderdocList;
}
public String getTownshipname() {
	return townshipname;
}
public void setTownshipname(String townshipname) {
	this.townshipname = townshipname;
}





}

