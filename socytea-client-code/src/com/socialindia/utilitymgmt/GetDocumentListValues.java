package com.socialindia.utilitymgmt;

import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class GetDocumentListValues extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String listValue;
	private Integer docFolder;
	private Integer docSubFolder;
	private String docFolderDate;
	private String docFileName;
	private Integer docShareUsrId;
	private String searchVal;
	private String searchFlag;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	 private Common commonObj = new CommonDao();
	  
	
	public String execute(){
		 	JSONObject locObjRecvJson = null;//Receive String to json	
			JSONObject locObjRecvdataJson = null;// Receive Data Json	
			String ivrservicecode=null;
	    	String ivrresponsecode=null;
	    	String ivrmsg=null;
	    	String ivrstatuscode=null;
		try{
			Map sessionMap = ActionContext.getContext().getSession();
			data.put("servicecode", "SI6401");
			data.put("currentloginid", sessionMap.get("USERID"));
			
			obj.put("docFolder", docFolder);
			obj.put("docSubFolder", docSubFolder);
			obj.put("docFolderDate", docFolderDate);
			obj.put("docFileName", docFileName);
			obj.put("docShareUsrId", docShareUsrId);
			obj.put("searchVal", searchVal);
			obj.put("searchFlag", searchFlag);
			
			data.put("data", obj);
			String jsonTextFinal_Countryid = data.toString().trim();
			jsonTextFinal_Countryid=EncDecrypt.encrypt(jsonTextFinal_Countryid);
			String tempcountry = "ivrparams=" + URLEncoder.encode(jsonTextFinal_Countryid);
			String tempcountryUrl = getText("socialindia.utility.getdocumentfolders");
	    	String response = commonObj.jsonRequest(tempcountryUrl, tempcountry);
	  		JSONObject json = new JSONObject(response);
	  		String lvrStscode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			if(Commonutility.checkempty(lvrStscode) && lvrStscode !=null && (lvrStscode.equalsIgnoreCase("00") || lvrStscode.equalsIgnoreCase("0"))){
				JSONObject json_data = json.getJSONObject("data");
				JSONArray foldernames = json_data.getJSONArray("foldernames");
				JSONArray documentIdDetails = json_data.getJSONArray("documentId");
				JSONArray maintUploadType = json_data.getJSONArray("maintenanceType");
				JSONArray societyuniqidary = (JSONArray) Commonutility.toHasChkJsonRtnValObj(json_data, "societyuniqidary"); 
				JSONArray lvrDownloadfileoth = (JSONArray) Commonutility.toHasChkJsonRtnValObj(json_data, "downloadpathary"); 
				if(foldernames.length()>0){
					Integer sdocFolder=0;
					Integer sdocSubFolder=0;
					Integer sdocShareUsrId=0;
					String sdocFolderDate="";
					String sdocFileName="";
					
					if (docFolder != null && docFolder == 0) {
						listValue = "	<ul style=\"padding:0 0 0 40px;\">";
					} else {
						listValue = "	<ul style=\"padding:0 0 0 40px;\">";
					}
					for (int l = 0; l < foldernames.length(); l++) {
						String fName = (String) foldernames.get(l);
						String doctId = (String) documentIdDetails.get(l);
						int uploadTyp = (Integer) maintUploadType.get(l);
						String fdtyp="fd";
						String filName="";
						//System.out.println("docFolder-----"+docFolder+"--------docSubFolder------------"+docSubFolder+"----docShareUsrId-----"+docShareUsrId+"----docFolderDate----"+docFolderDate+"----docFolderDate----"+docFolderDate.length());
						if(docFolder!=null && docFolder==0 && docSubFolder!=null && docSubFolder==0 && docShareUsrId!=null && docShareUsrId==0 && docFolderDate!=null && docFolderDate.length()==0){
							sdocFolder = Integer.parseInt(fName);
							if (fName.equalsIgnoreCase("1")) {
								filName = "public";
							} else if (fName.equalsIgnoreCase("2")) {
								filName = "private";
							} else {
								filName = "public";
							}
							fdtyp = "fd" + sdocFolder.toString();
							Commonutility.toWriteConsole(":::::::: Block 1 [Start]:::::::");
							Commonutility.toWriteConsole("docFolder : "+docFolder);
							Commonutility.toWriteConsole("docSubFolder : "+docSubFolder);
							Commonutility.toWriteConsole("docShareUsrId : "+docShareUsrId);
							Commonutility.toWriteConsole("docFolderDate : "+docFolderDate);
							if (sdocFolder == 1) {
							
								listValue+="<li><div class=\"imgaddplus\" >"
									     + "<div id=\"img"+fdtyp+"\" class=\"left pointer\" style=\"font-weight: bold;\" onclick=\"getadditionalfolder('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','"+sdocFileName+"',this);\">"
									     + "<i class=\"mdi-file-folder imagehide tinysmall left\" style=\"color: teal;margin: 3px 5px 0px 0px;\"></i>"
									     + "<i class=\"mdi-file-folder-open imageshow tinysmall left\" style=\"color: teal;margin: 3px 5px 0px 0px; display:none;\"></i>"
									     + "<span class=\"left\" style=\"line-height: 40px;text-indent: 5px;\">"+filName+"</span></div>"
									     + "</div><div style=\"clear:both;height: 15px;\"></div>"
									     + "<div id=\""+fdtyp+"\"></div></li>";
							} else {
								listValue+="<li><div class=\"imgaddplus\" >"
										 + "<div id=\"img"+fdtyp+"\" class=\"left pointer\" style=\"font-weight: bold;\" onclick=\"getadditionalfolder('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','"+sdocFileName+"',this);\">"
										 + "<i class=\"mdi-file-folder imagehide tinysmall left\" style=\"color: teal;margin: 3px 5px 0px 0px;\"></i>"
										 + "<i class=\"mdi-file-folder-open imageshow tinysmall left\" style=\"color: teal;margin: 3px 5px 0px 0px;display:none;\"></i>"
										 + "<span class=\"left\" style=\"line-height: 40px;text-indent: 5px;\">"+filName+"</span> </div>"
										 + "</div><div style=\"clear:both;height: 15px;\"></div>"
										 + "<div id=\""+fdtyp+"\"></div></li>";
								}
						} else if (docFolder!=null && docFolder>0 && docSubFolder!=null && docSubFolder==0 && docShareUsrId!=null && docShareUsrId==0 && docFolderDate!=null && docFolderDate.length()==0){// 001 Private - 002 Maintenance 
							sdocFolder = docFolder;
							sdocSubFolder = Integer.parseInt(fName);
							fdtyp="sfd"+sdocFolder.toString()+sdocSubFolder.toString();
							if(fName.equalsIgnoreCase("1")){
								filName="maintenance";
							}else if(fName.equalsIgnoreCase("2")){
								filName="general";
							}else{
								filName="maintenance";
							}
							Commonutility.toWriteConsole(":::::::: Block 2 [Start]:::::::");
							Commonutility.toWriteConsole("docFolder : "+docFolder);
							Commonutility.toWriteConsole("docSubFolder : "+docSubFolder);
							Commonutility.toWriteConsole("docShareUsrId : "+docShareUsrId);
							Commonutility.toWriteConsole("docFolderDate : "+docFolderDate);
							System.out.println("filName----------------------"+filName);
							listValue+="<li><div class=\"imgaddplus\">"
									+ "<div id=\"img"+fdtyp+"\" class=\"left pointer\" style=\"font-weight: bold;\" onclick=\"getadditionalfolder('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','"+sdocFileName+"',this);\">"
									+ "<i class=\"mdi-file-folder imagehide tinysmall left\" style=\"color: teal;margin: 3px 5px 0px 0px;\"></i>"
									+ "<i class=\"mdi-file-folder-open imageshow tinysmall left\" style=\"color: teal;margin: 3px 5px 0px 0px;display:none;\"></i>"
									+ "<span class=\"left\" style=\"line-height: 40px;text-indent: 5px;\">"+filName+"</span></div>"
									+ "</div><div style=\"clear:both;height: 15px;\"></div>"
									+ "<div id=\""+fdtyp+"\"></div></li>";
						} else if (docFolder!=null && docFolder>0 && docSubFolder!=null && docSubFolder>0 && docShareUsrId!=null && docShareUsrId==0 && docFolderDate!=null && docFolderDate.length()==0 ){ // 001 Private - 002 Maintenance - 003 Userid
							sdocFolder=docFolder;
							sdocSubFolder=docSubFolder;
							if (docFolder == 1) {
								sdocShareUsrId = docShareUsrId;
								sdocFolderDate = fName;
								fdtyp="sfd"+sdocFolder.toString()+sdocSubFolder.toString()+docShareUsrId+sdocFolderDate;
								filName=sdocFolderDate;
							} else {
								sdocShareUsrId = Integer.parseInt(fName);
								fdtyp="sfd"+sdocFolder.toString()+sdocSubFolder.toString()+sdocShareUsrId;
								filName=sdocShareUsrId.toString();
							}
							String socname = null;
							try {
								
								 socname = " (" + (String) societyuniqidary.get(l) + ")"; 
							} catch (Exception ee) {
								socname = "";
							}
							Commonutility.toWriteConsole(":::::::: Block 3 [Start]:::::::");
							Commonutility.toWriteConsole("docFolder : "+docFolder);
							Commonutility.toWriteConsole("docSubFolder : "+docSubFolder);
							Commonutility.toWriteConsole("docShareUsrId : "+docShareUsrId);
							Commonutility.toWriteConsole("docFolderDate : "+docFolderDate);
							Commonutility.toWriteConsole("socname : "+socname);
							System.out.println("filName----------------------"+filName);
							listValue+="<li><div class=\"imgaddplus\"  >"
									+ "<div id=\"img"+fdtyp+"\" class=\"left pointer\" style=\"font-weight: bold;\" onclick=\"getadditionalfolder('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','"+sdocFileName+"',this);\">"
									+ "<i class=\"mdi-file-folder imagehide tinysmall left\" style=\"color: teal;margin: 3px 5px 0px 0px;\"></i>"
									+ "<i class=\"mdi-file-folder-open imageshow tinysmall left\" style=\"color: teal;margin: 3px 5px 0px 0px;display:none;\"></i>"
									+ "<span class=\"left\" style=\"line-height: 40px;text-indent: 5px;\">"+filName+socname+"</span></div>"
									+ "</div><div style=\"clear:both;height: 15px;\"></div>"
									+ "<div id=\""+fdtyp+"\"></div></li>";
						} else if (docFolder!=null && docFolder>0 && docSubFolder!=null && docSubFolder>0 && docShareUsrId!=null && docShareUsrId==0 && docFolderDate!=null && docFolderDate.length()>0 ){
							sdocFolder = docFolder;
							sdocSubFolder = docSubFolder;
							sdocShareUsrId = docShareUsrId;
							sdocFolderDate = docFolderDate;
							sdocShareUsrId = docShareUsrId;
							sdocFileName = fName;
							fdtyp="sfd"+sdocFolder.toString()+sdocSubFolder.toString()+docShareUsrId+sdocFolderDate;
							filName=sdocFileName;	
							Commonutility.toWriteConsole(":::::::: Block 4 [Start]:::::::");
							Commonutility.toWriteConsole("docFolder : "+docFolder);
							Commonutility.toWriteConsole("docSubFolder : "+docSubFolder);
							Commonutility.toWriteConsole("docShareUsrId : "+docShareUsrId);
							Commonutility.toWriteConsole("docFolderDate : "+docFolderDate);
							if (docFolder == 1) {
								if (uploadTyp == 3) {
									listValue+="<li>"
											+ "<i id=\"folder\" class=\"mdi-editor-insert-drive-file tinysmall left pointer\" style=\"color: teal;margin: 0px 0px 0px 10px;color:#ff4081;\"></i>"
											+ "<span class=\"left\" style=\"min-width:170px;max-width:auto;word-wrap:break-word;line-height:35px;\">&nbsp;"+filName+"&nbsp;</span>"
											+ "<div class=\"folderdocclass left\" style=\"min-width:300px;\"><a  onclick=\"documentview('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"View\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.view")+"\"></i></a>"
											+ "<a  onclick=\"actionDelete('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','',this,"+doctId+","+docSubFolder+");\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Delete\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.delete")+"\"></i></a></div><div style=\"clear:both;height: 15px;\"></div></li>";
								} else {
									listValue+="<li>"
											+ "<i id=\"folder\" class=\"mdi-editor-insert-drive-file tinysmall left pointer\" style=\"color: teal;margin: 0px 0px 0px 10px;color:#ff4081;\"></i>"
												+ "<span class=\"left\" style=\"min-width:170px;max-width:auto;word-wrap:break-word;line-height:35px;\">&nbsp;"+filName+"&nbsp;</span>"
												+ "<div class=\"folderdocclass left\" style=\"min-width:300px;\"><a  onclick=\"documentview('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"View\" style=\"margin-right: 5px;cursor: pointer;\">"
												+ "<i class=\""+getText("button.color.view")+"\"></i></a><a  onclick=\"documentedit('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Edit\" style=\"margin-right: 5px;cursor: pointer;\">"
												+ "<i class=\""+getText("button.color.edit")+"\" ></i></a>"
												+ "<a  onclick=\"actionDelete('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','',this,"+doctId+","+docSubFolder+");\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Delete\" style=\"margin-right: 5px;cursor: pointer;\">"
												+ "<i class=\""+getText("button.color.delete")+"\"></i></a></div><div style=\"clear:both;height: 15px;\"></div></li>";
								}
							
							} else {
								if (uploadTyp == 3) {
										listValue+="<li><input type=\"checkbox\" id=\"docfile"+doctId+"\" name=\"check\" style=\"float:left;\"/>"
												+ "<i id=\"folder\" class=\"mdi-editor-insert-drive-file tinysmall left pointer\" style=\"color: teal;margin: 0px 0px 0px 10px;color:#ff4081;\"></i>"
												+ "<span class=\"left\" style=\"min-width:170px;max-width:auto;word-wrap:break-word;line-height:35px;\">&nbsp;"+filName+"&nbsp;</span>"
												+ "<div class=\"folderdocclass left\" style=\"min-width:300px;\"><a  onclick=\"documentview('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"View\" style=\"margin-right: 5px;cursor: pointer;\">"
												+ "<i class=\""+getText("button.color.view")+"\"></i></a>"
												+ "<a  onclick=\"actionDelete('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','',this,"+doctId+","+docSubFolder+");\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Delete\" style=\"margin-right: 5px;cursor: pointer;\">"
												+ "<i class=\""+getText("button.color.delete")+"\"></i></a></div><div style=\"clear:both;height: 15px;\"></div></li>";
								}else{
									listValue+="<li><input type=\"checkbox\" id=\"docfile"+doctId+"\" name=\"check\" style=\"float:left;\"/>"
											+ "<i id=\"folder\" class=\"mdi-editor-insert-drive-file tinysmall left pointer\" style=\"color: teal;margin: 0px 0px 0px 10px;color:#ff4081;\"></i>"
											+ "<span class=\"left\" style=\"min-width:170px;max-width:auto;word-wrap:break-word;line-height:35px;\">&nbsp;"+filName+"&nbsp;</span>"
											+ "<div class=\"folderdocclass left\" style=\"min-width:300px;\"><a  onclick=\"documentview('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"View\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.view")+"\"></i></a><a  onclick=\"documentedit('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Edit\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.edit")+"\" ></i></a>"
											+ "<a  onclick=\"actionDelete('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','',this,"+doctId+","+docSubFolder+");\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Delete\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.delete")+"\"></i></a></div><div style=\"clear:both;height: 15px;\"></div></li>";
									
								}
							}
				
						} else if (docFolder!=null && docFolder>0 && docSubFolder!=null && docSubFolder>0 && docShareUsrId!=null && docShareUsrId>0 && docFolderDate!=null && docFolderDate.length()==0 ){// 001 Private - 002 Maintenance - 003 Userid - 004 Date Folder
							sdocFolder=docFolder;
							sdocSubFolder=docSubFolder;
							sdocShareUsrId=docShareUsrId;
							sdocFolderDate=fName;
							fdtyp="sfd"+sdocFolder.toString()+sdocSubFolder.toString()+sdocShareUsrId+sdocFolderDate;
							filName=sdocFolderDate;	
							Commonutility.toWriteConsole(":::::::: Block 5 [Start]:::::::");
							Commonutility.toWriteConsole("docFolder : "+docFolder);
							Commonutility.toWriteConsole("docSubFolder : "+docSubFolder);
							Commonutility.toWriteConsole("docShareUsrId : "+docShareUsrId);
							Commonutility.toWriteConsole("docFolderDate : "+docFolderDate);
							listValue+="<li><div class=\"imgaddplus\">"
									+ "<div id=\"img"+fdtyp+"\" class=\"left pointer\" style=\"font-weight: bold;\" onclick=\"getadditionalfolder('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','"+sdocFileName+"',this);\">"
									+ "<i class=\"mdi-file-folder imagehide tinysmall left\" style=\"color: teal;margin: 3px 5px 0px 0px;\"></i>"
									+ "<i class=\"mdi-file-folder-open imageshow tinysmall left\" style=\"color: teal;margin: 3px 5px 0px 0px;display:none;\"></i>"
									+ "<span class=\"left\" style=\"line-height: 40px;text-indent: 5px;\">"+filName+"</span></div>"
									+ "</div><div style=\"clear:both;height: 15px;\"></div>"
									+ "<div id=\""+fdtyp+"\"></div></li>";
						}else if(docFolder!=null && docFolder>0 && docSubFolder!=null && docSubFolder>0 && docShareUsrId!=null && docShareUsrId>0 && docFolderDate!=null && docFolderDate.length()>0 ){ // 001 Private - 002 Maintenance - 003 Userid - 004 Date Folder- 005 List of files
							sdocFolder=docFolder;
							sdocSubFolder=docSubFolder;
							sdocShareUsrId=docShareUsrId;
							sdocFolderDate=docFolderDate;
							sdocShareUsrId=docShareUsrId;
							sdocFileName=fName;
							fdtyp="sfd"+sdocFolder.toString()+sdocSubFolder.toString()+sdocShareUsrId+sdocFolderDate;
							filName=sdocFileName;	
							Commonutility.toWriteConsole(":::::::: Block 6 [Start]:::::::");
							Commonutility.toWriteConsole("docFolder : "+docFolder);
							Commonutility.toWriteConsole("docSubFolder : "+docSubFolder);
							Commonutility.toWriteConsole("docShareUsrId : "+docShareUsrId);
							Commonutility.toWriteConsole("docFolderDate : "+docFolderDate);
							String lvrdpth = (String) lvrDownloadfileoth.get(l);
							if(docFolder==1){
								if(uploadTyp==3){
									listValue+="<li>"
											+ "<i id=\"folder\" class=\"mdi-file-folder-open left pointer\" style=\"color: teal;margin: 0px 0px 0px 10px;width:20px;height:20px;color:#ff4081;\"></i>"
											+ "<span class=\"left\"  style=\"min-width:170px;max-width:auto;word-wrap:break-word;line-height:35px;\">&nbsp;"+filName+"&nbsp;</span>"
											+ "<div class=\"folderdocclass left\" style=\" min-width:300px;\"><a  onclick=\"documentview('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"View\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.view")+"\"></i></a>"
											+ "<a  onclick=\"actionDelete('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','',this,"+doctId+","+docSubFolder+");\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Delete\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.delete")+"\"></i></a>" 
											+ "<a  onclick=\"dwnldfliedocpage('"+lvrdpth+"');\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"To Download\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("icon.download.save")+"\"></i></a>" 
											+ "</div><div style=\"clear:both;height: 15px;\"></div></li>";
								}else{
									listValue+="<li>"
											+ "<i id=\"folder\" class=\"mdi-editor-insert-drive-file tinysmall left pointer\" style=\"color: teal;margin: 0px 0px 0px 10px;color:#ff4081;\"></i>"
											+ "<span class=\"left\"  style=\"min-width:170px;max-width:auto;word-wrap:break-word;line-height:35px;\">&nbsp;"+filName+"&nbsp;</span>"
											+ "<div class=\"folderdocclass left\" style=\" min-width:300px;\"><a  onclick=\"documentview('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"View\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.view")+"\"></i></a>"
											/*		+ "<a   onclick=\"documentedit('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Edit\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.edit")+"\" ></i></a>"*/
											+ "<a  onclick=\"actionDelete('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','',this,"+doctId+","+docSubFolder+");\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Delete\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.delete")+"\"></i></a>" 
											+ "<a  onclick=\"dwnldfliedocpage('"+lvrdpth+"');\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"To Download\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("icon.download.save")+"\"></i></a>" 
											+ "</div><div style=\"clear:both;height: 15px;\"></div></li>";
								}
							}else{
								if(uploadTyp==3){
									listValue+="<li>"
											+ "<i id=\"folder\" class=\"mdi-editor-insert-drive-file tinysmall left pointer\" style=\"color: teal;margin: 0px 0px 0px 10px;color:#ff4081;\"></i>"
											+ "<span class=\"left\"  style=\"min-width:170px;max-width:auto;word-wrap:break-word;line-height:35px;\">&nbsp;"+filName+"&nbsp;</span>"
											+ "<div class=\"folderdocclass left\" style=\" min-width:300px;\"><a onclick=\"documentview('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"View\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.view")+"\"></i></a>"
											+ "<a  onclick=\"actionDelete('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','',this,"+doctId+","+docSubFolder+");\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Delete\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.delete")+"\"></i></a>"
											+ "<a  onclick=\"dwnldfliedocpage('"+lvrdpth+"');\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"To Download\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("icon.download.save")+"\"></i></a>" 
											+ "</div><div style=\"clear:both;height: 15px;\"></div></li>";
								}else{
									listValue+="<li>"
											+ "<i id=\"folder\" class=\"mdi-editor-insert-drive-file tinysmall left pointer\" style=\"color: teal;margin: 0px 0px 0px 10px;color:#ff4081;\"></i>"
											+ "<span class=\"left\"  style=\"min-width:170px;max-width:auto;word-wrap:break-word;line-height:35px;\">&nbsp;"+filName+"&nbsp;</span>"
											+ "<div class=\"folderdocclass left\" style=\" min-width:300px;\"><a  onclick=\"documentview('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"View\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.view")+"\"></i></a>"
													/*+ "<a  onclick=\"documentedit('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Edit\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("button.color.edit")+"\"></i></a>"*/
											+ "<a  onclick=\"actionDelete('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','',this,"+doctId+","+docSubFolder+");\" style=\"margin-right: 5px;cursor: pointer;\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Delete\" >"
											+ "<i class=\""+getText("button.color.delete")+"\"></i></a>" 
											+ "<a  onclick=\"dwnldfliedocpage('"+lvrdpth+"');\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"To Download\" style=\"margin-right: 5px;cursor: pointer;\">"
											+ "<i class=\""+getText("icon.download.save")+"\"></i></a>" 
											+ "</div><div style=\"clear:both;height: 15px;\"></div></li>";
								}
								
							}
						}else if(docFolder!=null && docFolder>0 && docSubFolder!=null && docSubFolder>0 && docShareUsrId!=null && docShareUsrId==0 && docFolderDate!=null && docFolderDate.length()>0 ){
							sdocFolder=docFolder;
							sdocSubFolder=docSubFolder;
							sdocShareUsrId=docShareUsrId;
							sdocFolderDate=docFolderDate;
							sdocShareUsrId=docShareUsrId;
							sdocFileName=fName;
							fdtyp="sfd"+sdocFolder.toString()+sdocSubFolder.toString()+sdocShareUsrId+sdocFolderDate;
							filName=sdocFileName;
							Commonutility.toWriteConsole(":::::::: Block 7 [Start]:::::::");
							Commonutility.toWriteConsole("docFolder : "+docFolder);
							Commonutility.toWriteConsole("docSubFolder : "+docSubFolder);
							Commonutility.toWriteConsole("docShareUsrId : "+docShareUsrId);
							Commonutility.toWriteConsole("docFolderDate : "+docFolderDate);
							if(uploadTyp==3){
								listValue+="<li>"
										+ "<i id=\"folder\" class=\"mdi-editor-insert-drive-file tinysmall left pointer\" style=\"color: teal;margin: 0px 0px 0px 10px;color:#ff4081;\"></i>"
										+ "<span class=\"left\" style=\"min-width:170px;\">&nbsp;"+filName+"&nbsp;</span>"
										+ "<div class=\"folderdocclass left\" style=\" min-width:300px;\"><a onclick=\"documentview('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"View\" style=\"margin-right: 5px;cursor: pointer;\">"
										+ "<i class=\""+getText("button.color.view")+"\"></i></a>"
										+ "<a  onclick=\"actionDelete('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','',this,"+doctId+","+docSubFolder+");\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Delete\" style=\"margin-right: 5px;cursor: pointer;\">"
										+ "<i class=\""+getText("button.color.delete")+"\"></i></a></div><div style=\"clear:both;height: 15px;\"></div></li>";
							}else{
								listValue+="<li>"
										+ "<i id=\"folder\" class=\"mdi-editor-insert-drive-file tinysmall left pointer\" style=\"color: teal;margin: 0px 0px 0px 10px;color:#ff4081;\"></i>"
										+ "<span class=\"left\" style=\"min-width:170px;\">&nbsp;"+filName+"&nbsp;</span>"
									/*	+ "<div class=\"folderdocclass left\" style=\" min-width:300px;\"><a onclick=\"documentview('"+doctId+"',"+docSubFolder+",'"+sdocShareUsrId+"')\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"View\" style=\"margin-right: 5px;cursor: pointer;\">"
										+ "<i class=\""+getText("button.color.view")+"\"></i></a><a   onclick=\"documentedit('"+doctId+"',"+docSubFolder+")\" class=\"left tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Edit\" style=\"margin-right: 5px;cursor: pointer;\">"
										+ "<i class=\""+getText("button.color.edit")+"\" ></i></a>"*/
										+ "<a  onclick=\"actionDelete('"+fdtyp+"','"+sdocFolder+"','"+sdocSubFolder+"','"+sdocShareUsrId+"','"+sdocFolderDate+"','',this,"+doctId+","+docSubFolder+");\" class=\"tooltipped\" data-toggle=\"modal\" data-target=\"#createMerchant\" data-position=\"bottom\" data-delay=\""+getText("material.tooltip.delay")+"\" data-tooltip=\"Delete\" style=\"margin-right: 5px;cursor: pointer;\">"
										+ "<i class=\""+getText("button.color.delete")+"\"></i></a></div><div style=\"clear:both;\"></div></li>";
							}
							
						}else{
							System.out.println("empty file name----------------------"+filName);
						}
						
						
					
					/*	if(sdocFolder==null){
							sdocFolder=0;
						}
						if(sdocSubFolder==null){
							sdocSubFolder=0;
						}
						if(sdocShareUsrId==null){
							sdocShareUsrId=0;
						}
						if(docFolderDate==null){
							docFolderDate="";
						}*/
				
					}
					listValue+=" </ul>";
				} else {
					listValue="No record";
				}
			} else {
				listValue="No record";
			}
	  								
		}catch (Exception e){
			listValue="No record";			
		}
		return SUCCESS;
	}

	public String getListValue() {
		return listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	public Integer getDocFolder() {
		return docFolder;
	}

	public void setDocFolder(Integer docFolder) {
		this.docFolder = docFolder;
	}

	public Integer getDocSubFolder() {
		return docSubFolder;
	}

	public void setDocSubFolder(Integer docSubFolder) {
		this.docSubFolder = docSubFolder;
	}

	public String getDocFolderDate() {
		return docFolderDate;
	}

	public void setDocFolderDate(String docFolderDate) {
		this.docFolderDate = docFolderDate;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public Integer getDocShareUsrId() {
		return docShareUsrId;
	}

	public void setDocShareUsrId(Integer docShareUsrId) {
		this.docShareUsrId = docShareUsrId;
	}

	public String getSearchVal() {
		return searchVal;
	}

	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	
}
