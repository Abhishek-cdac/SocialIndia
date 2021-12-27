package com.socialindia.eNewsmgmt;


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


public class eNewsServices extends ActionSupport{
	  private String uniqueId;
	  private String townshipname;
	  private String societyname;
	  byte imgByt[]=null;
		private List <File> documentfile = new ArrayList <File> ();
	  private String documentfileFileName; 

	  JSONObject obj = new JSONObject();
	  private AlertVo alert = new AlertVo();
	  private List<AlertVo> alertList = new ArrayList<AlertVo>();
	  
	  EeNewsTblVO eNewsobj=new EeNewsTblVO(null, null, null, 0, null, null,null);
	  List<EeNewsDocTblVO> EenewImglist =new ArrayList<EeNewsDocTblVO>();
	  private Common commonObj = new CommonDao(); 
	  commomServices commonSnippet=new commomServices();
	private static final long serialVersionUID = 1L;

	public String execute() {		
		return SUCCESS;
	}
	public String eNewsCreateFun() throws Exception{
			System.out.println("eNews create  loading......."+eNewsobj.getIvrBnTITLE());
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject finaljj=null;
			JSONObject json =null;
			String invite=null;
			String groupcode=null;String temp =null;JSONArray ja =null;String finalUrl=null;String jsonTextFinal =null;String response =null;
			String statusCode =null;
			String[] invitearr=null;
			try {	
				if(!eNewsobj.getIvrBnTITLE().equalsIgnoreCase("null")&& eNewsobj.getIvrBnTITLE()!=null) {
				String sFileName = documentfileFileName;
				String[] filename=null;
				
				obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
				obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
				obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
				obj.put("enewstitle",eNewsobj.getIvrBnTITLE());
				obj.put("enewsdesc", eNewsobj.getIvrBnDESCRIPTION());
				obj.put("enewsshdesc", eNewsobj.getIvrBnSHRTDESCRIPTION());
				obj.put("eNewsstatus", "1");
				obj.put("ivrservicefor", "1");
				obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
				JSONArray imagearray = new JSONArray();
				JSONArray fileNamearray = new JSONArray();
				if(documentfileFileName!=null && !documentfileFileName.equalsIgnoreCase("null")) {					
				
					if(sFileName.contains(",")){
					}else{
						sFileName=sFileName+",";
					}
					filename=sFileName.split(",");
					double totfilesize=0;
					//byte imgBytlogo[]=new byte[2048];
					for(int f=0;f<documentfile.size();f++){
						//imgBytlogo=new byte[2048];
						double filesiz=documentfile.get(f).length();
						totfilesize+=filesiz;
						String fileName=filename[f];
						//imgBytlogo=commonSnippet.toReadFiletoBytes(documentfile.get(f));
						//imagearray.put(commonSnippet.toByteAryToBase64EncodeStr(imgBytlogo));
						if (documentfile.get(f)!=null) {
							imagearray.put(documentfile.get(f).getAbsolutePath());
							fileNamearray.put(fileName);
						} else {
							
						}
					}
					//obj.put("imageDetail",imagearray);
					obj.put("imgsrchpth",imagearray);
					obj.put("fileName",fileNamearray);
				} else {
					/*obj.put("imageDetail","");
					obj.put("fileName","");*/
				}
				
				finaljj=new JSONObject();
				finaljj.put("servicecode", "SI11001");		
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				System.out.println("req::: "+jsonTextFinal);
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.eNews.creation.action");
				response = commonObj.jsonRequest(finalUrl, temp);
				json = new JSONObject(response);
				statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					alert.setCls("success");
					alert.setMsg(getText("eNew.Create.Success"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("eNew.Create.Error"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "error";
				}
				
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("eNew.Create.Error"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "error";
			}
			} catch (Exception e) {
				Commonutility.toWriteConsole("Exception Found in "+getClass().getSimpleName()+".class : "+e);
				alert.setCls("danger");
				alert.setMsg(getText("eNew.Create.Error"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "error";
			}finally{
				finaljj=null;json =null;invite=null;groupcode=null;temp =null;ja =null;finalUrl=null;jsonTextFinal =null;response =null;
				statusCode =null;invitearr=null;
			}		
	}
	public String modifyServices() {
		System.out.println("------- Enews Edit ------------------------");
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
			obj.put("enewsid", String.valueOf(uniqueId));
			obj.put("crntusrloginid", String.valueOf(currentSession.get("USERID")));
			obj.put("ivrservicefor","3");
			 finaljj=new JSONObject();
			finaljj.put("servicecode", "SI11005");		
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.eNewsmodify.action");
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
					JSONArray docnamearr = new JSONArray();
					docnamearr = (JSONArray) Commonutility .toHasChkJsonRtnValObj(json_data, "jArry_doc_files");					
					if(docnamearr !=null && docnamearr.length() > 0)
					{
						JSONObject downloadjj=new JSONObject();
						for(int i =0 ;i<docnamearr.length();i++)
						{
							
							docfilename = (String)Commonutility.toHasChkJsonRtnValObj(docnamearr.getJSONObject(i),"filesname");
							docfilepath = (String)Commonutility.toHasChkJsonRtnValObj(docnamearr.getJSONObject(i),"filepath");
							
							EenewImglist.add(new EeNewsDocTblVO(docfilepath,docfilename));
							
						}
					}				
					
					eNewsobj.setIvrBnENEWS_ID(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"enewsid")));
					eNewsobj.setIvrBnTITLE((String)Commonutility.toHasChkJsonRtnValObj(json_data,"enewstitle"));
					eNewsobj.setIvrBnSHRTDESCRIPTION((String)Commonutility.toHasChkJsonRtnValObj(json_data,"enewsshrtdesc"));
					eNewsobj.setIvrBnDESCRIPTION((String)Commonutility.toHasChkJsonRtnValObj(json_data,"enewsdesc"));
					societyname=((String)Commonutility.toHasChkJsonRtnValObj(json_data,"enewsentbyresidentsocietyname"));
					townshipname=((String)Commonutility.toHasChkJsonRtnValObj(json_data,"enewsentbyresidenttownshipname"));
					
					
				}
			}

			ActionContext.getContext().getSession().put("EnewsSessID", eNewsobj.getIvrBnENEWS_ID());
		}
		catch(Exception Ex)
		{
			System.out.println("Exception eNews view-------------- "+Ex);
		}
		finally
		{
			finaljj=null;response=null;finalUrl=null;temp=null;jsonTextFinal=null;
		}
	   return SUCCESS;
	}
	
	public String toeNewsmodifyform(){		
		System.out.println("eNews update  loading......."+eNewsobj.getIvrBnTITLE());
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
			obj.put("enewsid",String.valueOf(eNewsobj.getIvrBnENEWS_ID()));
			obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
			obj.put("crntusrgrpid",String.valueOf(sessionMap.get("GROUPCODE")));
			obj.put("crntusrsocietyid",String.valueOf(sessionMap.get("sSoctyId")));
			obj.put("enewstitle",eNewsobj.getIvrBnTITLE());
			obj.put("enewsdesc", eNewsobj.getIvrBnDESCRIPTION());
			obj.put("enewsshdesc", eNewsobj.getIvrBnSHRTDESCRIPTION());
			obj.put("eNewsstatus", "1");
			obj.put("ivrservicefor", "1");
			System.out.println("1----");
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			JSONArray imagearray = new JSONArray();
			JSONArray fileNamearray = new JSONArray();
			if(documentfileFileName!=null && !documentfileFileName.equalsIgnoreCase("null"))
			{					
			
				if(sFileName.contains(",")){
				}else{
					sFileName=sFileName+",";
				}
				filename=sFileName.split(",");
				double totfilesize=0;
				byte imgBytlogo[]=new byte[2048];
				for(int f=0;f<documentfile.size();f++){
					imgBytlogo=new byte[2048];
					double filesiz=documentfile.get(f).length();
					totfilesize+=filesiz;
					String fileName=filename[f];
					//imgBytlogo=commonSnippet.toReadFiletoBytes(documentfile.get(f));
					//imagearray.put(commonSnippet.toByteAryToBase64EncodeStr(imgBytlogo));
					
					if(documentfile.get(f)!=null){
						imagearray.put(documentfile.get(f).getAbsolutePath());
						fileNamearray.put(fileName);
					} else {
						
					}
				}
				//obj.put("imageDetail",imagearray);
				obj.put("imgsrchpth",imagearray);
				obj.put("fileName",fileNamearray);
			} else {
				/*obj.put("imageDetail","");
				obj.put("fileName","");*/
			}
			
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI11002");		
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString().trim();
			System.out.println("req::: "+jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.eNews.updated.action");
			response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("eNew.update.Success"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("eNew.update.Error"));
				alertList.add(alert);
				return "error";
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
	
	public String deleteEnewAction(){
			System.out.println("------- Enew Delete ------------------------");
			Map sessionMap = ActionContext.getContext().getSession();
			try{
				String useridval=String.valueOf(sessionMap.get("USERID"));
				obj.put("crntusrloginid",useridval );
				obj.put("enewsid", String.valueOf(uniqueId));
				obj.put("ivrservicefor", "5");
				JSONObject finaljj=new JSONObject();
				finaljj.put("servicecode", "SI11004");		
				finaljj.put("data", obj);
				String jsonTextFinal = finaljj.toString();
				System.out.println(jsonTextFinal);
				jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
				String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
				String finalUrl =getText("socialindia.eNews.deleteaction");
				String response=commonObj.jsonRequest(finalUrl, temp);
				JSONObject json = new JSONObject(response);
				String statusCode = (String) json.get("statuscode");
				if (statusCode.equalsIgnoreCase("0")) {
					alert.setCls("success");
					alert.setMsg(getText("eNew.Delete.Success"));
					alertList.add(alert);
				return "success";
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("eNew.Delete.Error"));
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
public EeNewsTblVO geteNewsobj() {
	return eNewsobj;
}
public void seteNewsobj(EeNewsTblVO eNewsobj) {
	this.eNewsobj = eNewsobj;
}
public List<EeNewsDocTblVO> getEenewImglist() {
	return EenewImglist;
}
public void setEenewImglist(List<EeNewsDocTblVO> eenewImglist) {
	EenewImglist = eenewImglist;
}
public String getTownshipname() {
	return townshipname;
}
public void setTownshipname(String townshipname) {
	this.townshipname = townshipname;
}
public String getSocietyname() {
	return societyname;
}
public void setSocietyname(String societyname) {
	this.societyname = societyname;
}










}

