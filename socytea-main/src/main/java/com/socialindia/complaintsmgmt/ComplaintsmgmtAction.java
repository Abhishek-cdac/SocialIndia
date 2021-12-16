package com.socialindia.complaintsmgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.complaints.persistance.ComplaintattchTblVO;
import com.socialindia.complaints.persistance.ComplaintsTblVO;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class ComplaintsmgmtAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	//ComplaintsTblVO cmpltRegObj =  new ComplaintsTblVO(0, null, null, null,null, null, 0, 0, null,null,null,null,null,null,null);
	ComplaintsTblVO cmpltRegObj =  new ComplaintsTblVO();
	List<ComplaintattchTblVO> Imglist =new ArrayList<ComplaintattchTblVO>();
	private Common commonObj = new CommonDao();
	private Integer deletecomplaintid;
	private String hiddencmpltidval;
	private String closereasonval;
	private List<File> documentfile = new ArrayList<File>();
	private String documentfileFileName;
	private String documenttypeval = null;
	private String labordetail = null;
	private String searchval;
	private String searchname;
	private String slectfield;
	private String society;
	private String townshipid;
	private String cmpltShareids;
	private String laborshardetails;
	private String cmpltid;
	private String cmpltfrmusrid;
	private String statustype;
	private String testblockid;
	private String statusflgstr;
	List<ComplaintattchTblVO> videofileatch =new ArrayList<ComplaintattchTblVO>();
	List<ComplaintattchTblVO> videothumpimg =new ArrayList<ComplaintattchTblVO>();

	public String execute() {
		return SUCCESS;
	}

	public String Closereasonpage() {
		//cmpltRegObj = new ComplaintsTblVO(0, null, null, null,null, null, 0, 0, null,null,null,null,null,null,null);
		Map currentSession = ActionContext.getContext().getSession();
		Map sessionMap = ActionContext.getContext().getSession();
		if (hiddencmpltidval == null) {
			if ((hiddencmpltidval == null || hiddencmpltidval.equalsIgnoreCase("-1") || hiddencmpltidval.equalsIgnoreCase("0"))
					&& currentSession.get("currentsession_hiddencmpltidval") != null) {
				String stfiddd = (String) currentSession.get("currentsession_hiddencmpltidval");
				
				hiddencmpltidval = stfiddd;
				
			}
		} else {
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_hiddencmpltidval", String.valueOf(hiddencmpltidval));
			
		}
		cmpltRegObj.setComplaintsId(Integer.parseInt(hiddencmpltidval));
		return SUCCESS;
	}
	
	public String deactivecomplaintActionFunction() {
		String result = "";
		try {
			//cmpltRegObj = new ComplaintsTblVO(0, null, null, null,null, null, 0, 0, null,null,null,null,null,null,null);
			JSONObject dataJson=new JSONObject();
			Map sessionMap = ActionContext.getContext().getSession();
			dataJson.put("complaintid", String.valueOf(cmpltRegObj.getComplaintsId()));
			dataJson.put("crntusrloginid", sessionMap.get("USERID"));// Current login id
			dataJson.put("cmplintclosedby", String.valueOf(sessionMap.get("USERID")));
			dataJson.put("desc", closereasonval);
			dataJson.put("status", statustype);
			dataJson.put("ivrservicefor", "4");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI9003");		
			finaljj.put("data", dataJson);
			String jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.complaintmgmt.complaintdeactive");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject json_data = json.getJSONObject("data");
			String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			if ((statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0") )&& respCode.equalsIgnoreCase("R0171")) {
					alert.setCls("success");
					alert.setMsg(getText("Text.complaint.deactive"));
					alertList.add(alert);
					return "success";
				} else {
					alert.setCls("danger");
					//alert.setCls("error");
					alert.setMsg(getText("Text.complaint.deactive.error"));
					alertList.add(alert);
					return "input";
				}					
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Exception found in "+ this.getClass().getSimpleName() +".class in method of "+ Thread.currentThread().getStackTrace()[1].getMethodName()+"() : " + ex);			
		}
		return SUCCESS;
	}

	public String viewcomplaintActionFunction() {
		try {
			//cmpltRegObj = new ComplaintsTblVO(0, null, null, null,null, null, 0, 0, null,null,null,null,null,null,null);
			JSONObject locObjRecvJson = null;// Receive String to json
			JSONObject locObjRecvdataJson = null;// Receive Data Json
			JSONObject locObjRspdataJson = null;// Response Data Json
			Map currentSession = ActionContext.getContext().getSession();
			Map sessionMap = ActionContext.getContext().getSession();
			if (deletecomplaintid == null) {
				if ((deletecomplaintid == null || deletecomplaintid == -1 || deletecomplaintid == 0)
						&& currentSession.get("currentsession_deletecomplaintid") != null) {
					String stfiddd = (String) currentSession
							.get("currentsession_deletecomplaintid");
					
					deletecomplaintid = Integer.parseInt(stfiddd);					
				}
			} else {
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_deletecomplaintid", String.valueOf(deletecomplaintid));
				
			}

			JSONObject dataJson=new JSONObject();
			dataJson.put("complaintid", String.valueOf(deletecomplaintid));
			dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));// Current login id
			dataJson.put("ivrservicefor", "3");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI9005");		
			finaljj.put("data", dataJson);
			String jsonTextFinal = finaljj.toString();		
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.complaintmgmt.complaintview");
			String response = commonObj.jsonRequest(finalUrl, temp);
			String docfilename ="";
			String docformat ="";
			
			String docfilepath="";
			String evtrsvp="";
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					JSONObject json_data = locObjRecvJson.getJSONObject("data");
					// staffProfileImage=json_data.getString("str_lbr_webimage");
					JSONArray docnamearr = new JSONArray();
					docnamearr = (JSONArray) Commonutility .toHasChkJsonRtnValObj(json_data, "jArry_doc_files");					
					if(docnamearr !=null && docnamearr.length() > 0) {
						String lvrvideoext = getText("Text.video.extensions");
						JSONObject downloadjj=new JSONObject();
						for(int i =0 ;i<docnamearr.length();i++) {
							
							docfilename = (String)Commonutility.toHasChkJsonRtnValObj(docnamearr.getJSONObject(i),"filesname");
							//docfilepath = (String)Commonutility.toHasChkJsonRtnValObj(docnamearr.getJSONObject(i),"filepath");
							if(lvrvideoext.contains(FilenameUtils.getExtension(docfilename))){
								String lvrThmimg = (String)Commonutility.toHasChkJsonRtnValObj(docnamearr.getJSONObject(i),"thumpimg");
								videofileatch.add(new ComplaintattchTblVO(docfilename,lvrThmimg));								
							} else {
								Imglist.add(new ComplaintattchTblVO(docfilename));
							}
							
							
						}
					}
					/*ActionContext.getContext().getSession().put("cmpltImage1",cmpltRegObj.getComplaintsFileName1());*/
					cmpltfrmusrid=json_data.getString("cmpltfrmusrid");					
					cmpltRegObj.setComplaintName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltfrmusrname"));					
					//cmpltRegObj.setCmpltfromgrpname((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltfrmgrpname"));										
					cmpltRegObj.setCmpltfromgrpname((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmplttodest")); //  complaints to group name
					cmpltRegObj.setComplaint_to((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltlaborname"));
					cmpltRegObj.setGrpname((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltfrmgrpname"));					
					cmpltRegObj.setComplaintsTitle((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmplttitle"));					
					cmpltRegObj.setEntryDatetime((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltentrydate"));
					cmpltRegObj.setModifyDatetime((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltmodifydate"));
				   // cmpltRegObj.setStatus(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltstatus")));
					
				  evtrsvp=String.valueOf((Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltstatus"))));
					System.out.println("staata--------------"+evtrsvp);

				if (evtrsvp.equalsIgnoreCase("3")) {
						statusflgstr = "Resolved";
					} else if (evtrsvp.equalsIgnoreCase("2")) {
						statusflgstr = "Declined";
					} else if (evtrsvp.equalsIgnoreCase("1")) {
						statusflgstr = "Pending";
					} else if (evtrsvp.equalsIgnoreCase("0")) {
						statusflgstr = "Delete";
					} else {
						statusflgstr = "Delete";
					}
					
				/*	ActionContext.getContext().getSession().put("cmpltImage2",cmpltRegObj.getComplaintsFileName2());
					ActionContext.getContext().getSession().put("cmpltImage3",cmpltRegObj.getComplaintsFileName3());*/
					/*cmpltRegObj.setComplaintsFileName1((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltfilename1"));
					cmpltRegObj.setComplaintsFileName2((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltfilename2"));
					cmpltRegObj.setComplaintsFileName3((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltfilename3"));*/
					cmpltRegObj.setComplaintsDesc((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltdesc"));
					cmpltRegObj.setComplaintsOthersEmail((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltotheremail"));
					cmpltRegObj.setComplaintsId(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltid")));
					cmpltRegObj.setShrtdesc((String)Commonutility.toHasChkJsonRtnValObj(json_data,"cmpltshortdesc"));
				}
			}

			ActionContext.getContext().getSession().put("cmpltSessID", cmpltRegObj.getComplaintsId());
			
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Exception found in "+ this.getClass().getSimpleName() +".class in method of "+ Thread.currentThread().getStackTrace()[1].getMethodName()+"() : " + ex);
		} finally {

		}
		return SUCCESS;
			
		}
		
	public String deletecomplaintActionFunction() {
		String result = "";
		try {
		JSONObject dataJson=new JSONObject();
		Map sessionMap = ActionContext.getContext().getSession();
		dataJson.put("complaintid", String.valueOf(deletecomplaintid));
		dataJson.put("crntusrloginid", sessionMap.get("USERID"));// Current login id
		dataJson.put("cmplintclosedby", String.valueOf(sessionMap.get("USERID")));
		dataJson.put("ivrservicefor", "5");
		JSONObject finaljj=new JSONObject();
		finaljj.put("servicecode", "SI9004");		
		finaljj.put("data", dataJson);
		String jsonTextFinal = finaljj.toString().trim();
		jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
		String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
		String finalUrl = getText("socialindia.complaintmgmt.complaintdelete");
		String response = commonObj.jsonRequest(finalUrl, temp);
		JSONObject json = new JSONObject(response);
		JSONObject json_data = json.getJSONObject("data");
		String statusCode = (String) json.get("statuscode");
		String respCode = (String) json.get("respcode"); 
		String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
		if (statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Text.complaint.delete"));
				}				
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("Text.complaint.delete.error"));
				}				
				alertList.add(alert);
				return "input";
			}
		
	
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Exception found in "+ this.getClass().getSimpleName() +".class in method of "+ Thread.currentThread().getStackTrace()[1].getMethodName()+"() : " + ex);
		}
		return SUCCESS;
}

	public String cmpltclosereason() {
		try {
			JSONObject dataJson=new JSONObject();
			Map sessionMap = ActionContext.getContext().getSession();
			dataJson.put("complaintid", hiddencmpltidval);
			dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID")));// Current login id
			dataJson.put("cmplintclosedby", String.valueOf(sessionMap.get("USERID")));
			dataJson.put("ivrservicefor", "4");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI9008");		
			finaljj.put("data", dataJson);
			String jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);

			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
	
			String finalUrl = getText("socialindia.complaintmgmt.complaintdeactive");
			System.out.println("=======finalUrl====" + finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("=========response===" + response);
			JSONObject json = new JSONObject(response);
			JSONObject json_data = json.getJSONObject("data");
	
	
			String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			if (statusCode.equalsIgnoreCase("00")
					&& respCode.equalsIgnoreCase("R0171")) {
				alert.setCls("success");
				alert.setMsg(getText("Text.complaint.deactive"));
				alertList.add(alert);
				return "success";
			} else {
				//alert.setCls("error");
				alert.setCls("danger");
				alert.setMsg(getText("Text.complaint.deactive.error"));
				alertList.add(alert);
				return "input";
		}
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Exception found in "+ this.getClass().getSimpleName() +".class in method of "+ Thread.currentThread().getStackTrace()[1].getMethodName()+"() : " + ex);
		}
		return SUCCESS;
	}

	public String createComplaintActionFunction(){
		JSONObject dataJson = null;
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			if (testblockid!=null && Commonutility.checkempty(testblockid) && testblockid.equalsIgnoreCase("1")){
				if (cmpltRegObj!=null && (cmpltRegObj.getComplaintsTitle()!=null && !cmpltRegObj.getComplaintsTitle().equalsIgnoreCase("") && cmpltid =="" || cmpltid == null)){	
					commomServices commonSnippet = new commomServices();
				
					dataJson = new JSONObject();
					dataJson.put("ivrservicefor", "1");
					dataJson.put("crntusrloginid", String.valueOf(sessionMap.get("USERID"))); // Current login id
					dataJson.put("crntusrgrpid", String.valueOf(sessionMap.get("GROUPCODE"))); // Current group id
					dataJson.put("cmplintemail", cmpltRegObj.getComplaintsOthersEmail());
					dataJson.put("cmplinttitle", cmpltRegObj.getComplaintsTitle());	
					dataJson.put("cmplintshrtdesc", cmpltRegObj.getShrtdesc());
					dataJson.put("cmplintdesc", cmpltRegObj.getComplaintsDesc());	
					dataJson.put("complainttoflag", "0");// 0 - external, 1-committee, 2 - admin
					dataJson.put("cmplintvideopath", "");// //invitation video path
					dataJson.put("cmplintousrid", "");
					dataJson.put("cmplintogrpid", "");			 			 		 	
					//dataJson.put("cmplintfiledata1",Commonutility.toByteAryToBase64EncodeStr(imgByt));// encrypted string on image
					//dataJson.put("cmplintfilename1", "11.jpg");//invitation image name
					String[] filename=null;	
					String sFileName = documentfileFileName;	 	
					//JSONArray lvrImgname = new JSONArray();
					//JSONArray lvrImgdata = new JSONArray();
						boolean lvrImgflg =false;	 	
				 	JSONObject finaljj=new JSONObject();
					finaljj.put("servicecode", "SI9001");		
					finaljj.put("data", dataJson);
					String jsonTextFinal = finaljj.toString().trim();
					jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
					String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
					String finalUrl = getText("socialindia.complaintmgmt.complaintcreate");
					String response = commonObj.jsonRequest(finalUrl, temp);
					System.out.println("response:::::::::: "+response);
					JSONObject json = new JSONObject(response);
					String statusCode = (String) json.get("statuscode");
					String respCode = (String) json.get("respcode");
					String lvrRspmsg = (String)Commonutility.toHasChkJsonRtnValObj(json, "message");
					JSONObject json_data = json.getJSONObject("data");
					cmpltRegObj = null;
					String lvrComplintid =	(String)Commonutility.toHasChkJsonRtnValObj(json_data, "complaintid");
					System.out.println("compltid:::::::::"+lvrComplintid+"::::::::: "+documentfileFileName );
					if(lvrComplintid!=null && !lvrComplintid.equalsIgnoreCase("null") && !lvrComplintid.equalsIgnoreCase("")){
						cmpltid = lvrComplintid;
						if (documentfileFileName!=null && !documentfileFileName.equalsIgnoreCase("null")) {
							if (sFileName.contains(",")) {

							} else {
								sFileName = sFileName + ",";
							}
							filename = sFileName.split(",");
							double totfilesize = 0;
							//byte imgBytlogo[] = new byte[2048];
							JSONObject imgJobj = new JSONObject();
							JSONObject imgDataJobj = new JSONObject();
							for (int f = 0; f < documentfile.size(); f++) {
								//imgBytlogo = new byte[2048];
								double filesiz = documentfile.get(f).length();
								totfilesize += filesiz;
								String fileName = filename[f];								
								// dataJson.put("cmplintfiledata"+i,profileUpdate.toByteAryToBase64EncodeStr(imgBytlogo));//
								// encrypted string on image
								// dataJson.put("cmplintfilename"+i, fileName);
								// RPK
								//imgBytlogo = commonSnippet.toReadFiletoBytes(documentfile.get(f));								
								imgJobj = new JSONObject();
								imgDataJobj = new JSONObject();
								imgJobj.put("cmplaintid", lvrComplintid);
								imgJobj.put("fileName", fileName);
								//imgJobj.put("imageDetail", profileUpdate.toByteAryToBase64EncodeStr(imgBytlogo));
								if (documentfile.get(f)!=null) {
									imgJobj.put("imgsrchpth", documentfile.get(f).getAbsolutePath());
								} else {
									imgJobj.put("imgsrchpth", "");
								}
								
								imgJobj.put("isdeleteImage", "no");// default
								imgDataJobj.put("data", imgJobj);
								String jsonTextFinal1 = imgDataJobj.toString();
								jsonTextFinal1 = EncDecrypt.encrypt(jsonTextFinal1);
								String temp1 = "ivrparams=" + URLEncoder.encode(jsonTextFinal1);
								String finalUrl1 = getText("complaints.attach.service");
								String response1 = commonObj.jsonRequest(finalUrl1, temp1);
								lvrImgflg = true;
								imgJobj = null; imgDataJobj = null;											
						}	
			
		 	} else {
		 		System.out.println("No image upload..");
		 			/*for(int j=1;j<=3;j++) {
					dataJson.put("cmplintfiledata"+j,"");// encrypted string on image
				 	dataJson.put("cmplintfilename"+j, "");			 	
		 			}*/
		 	
		 	}
	 	}	
		if (statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")) {	
			alert.setCls("success");
			if(Commonutility.checkempty(lvrRspmsg)){
				alert.setMsg(lvrRspmsg);
			} else {
				alert.setMsg(getText("success.complaint.created"));
			}			
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "success";

		} else {
			alert.setCls("danger");
			if(Commonutility.checkempty(lvrRspmsg)){
				alert.setMsg(lvrRspmsg);
			} else {
				alert.setMsg(getText("error.complaint.created"));
			}			
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		}
		} else {
			
		}
		}
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Exception found in "+ this.getClass().getSimpleName() +".class in method of "+ Thread.currentThread().getStackTrace()[1].getMethodName()+"() : " + ex);
			alert.setCls("danger");			
			alert.setMsg(getText("error.complaint.created"));			
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		} finally {
			dataJson = null; cmpltRegObj = null; System.gc();
		}
	
	return SUCCESS;
}
	
	public String getLaborDetail() {
		try {
			Map sessionMap = ActionContext.getContext().getSession();
			JSONObject dataJson = new JSONObject();
			dataJson.put("lbrstatus", "1");
			dataJson.put("countflg", "yes");
			dataJson.put("countfilterflg", "yes");
		/*dataJson.put("startrow", String.valueOf(start));// starting row
		dataJson.put("totalrow", String.valueOf(amount));// overalltotal row
		dataJson.put("srchdtsrch", sdtSearch);// overalltotal row
*/		
			if (slectfield == null) {
				dataJson.put("slectfield", "");
			} else {
				dataJson.put("slectfield", slectfield);
			}
			if (searchname == null) {
				dataJson.put("searchname", "");
			} else {
				dataJson.put("searchname", searchname);
			}
			if (searchname == null) {
				dataJson.put("searchflg", "");
			} else {
				dataJson.put("searchflg", searchval);
			}
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "S9009");
			finaljj.put("data", dataJson);
     
    
     
	     String jsonTextFinal = finaljj.toString();	
		jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
		String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
		String finalUrl = getText("socialindia.labormgmtdatatableview.action");
		String response=commonObj.jsonRequest(finalUrl, temp);
		JSONObject json = new JSONObject(response);
		JSONObject json_data=json.getJSONObject("data");
		JSONArray ar =null;
		 ar = json_data.getJSONArray("labordetails");
		 JSONObject jsonList = new JSONObject();
		 JSONObject retval = new JSONObject();
		 JSONArray jArray =new JSONArray();
			for (int i = 0; i < ar.length(); i++) {
				jsonList = null;
				jsonList=ar.getJSONObject(i);
				retval = new JSONObject();
				String firstName=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_name");
				String labmobno=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_mobno");
				String lbr_wrktypName=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_wrktypName");
				String emailId = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_email");
				String userid = (String) Commonutility.toHasChkJsonRtnValObj(jsonList, "lbr_id");
				String imageNameMobile=(String) Commonutility.toHasChkJsonRtnValObj(jsonList, "str_lbr_mobileimage");
				if (imageNameMobile!=null && imageNameMobile.length()>0){
					imageNameMobile="/templogo/labor/mobile/"+userid+"/"+imageNameMobile;
				} else {
					imageNameMobile=getText("Resource.path")+"/images/social/logo.png";
				}
			
				if (lbr_wrktypName == null) {
					lbr_wrktypName = "";
				}
				String searchfield = "";
			
				if(firstName!=null && firstName.length()>0){
					searchfield += " " + firstName;
				}
			
				if(labmobno!=null && labmobno.length()!=0){
					searchfield +=" " + labmobno;
				}
				if (emailId != null && emailId.length() != 0) {
					searchfield += " " + emailId;
				}
				retval.put("searchkey", searchfield);
				retval.put("searchvalue", userid);
				retval.put("imageNameMobile", imageNameMobile);
				retval.put("userid", userid);
				retval.put("firstName", firstName);
				retval.put("labmobno", labmobno);
				jArray.put(retval);
			}

			labordetail = jArray.toString();

		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Exception found in "+ this.getClass().getSimpleName() +".class in method of "+ Thread.currentThread().getStackTrace()[1].getMethodName()+"() : " + ex);
		} finally {
			
		}
		return SUCCESS;
	}
	
	public String inviteCmpltAction() {
		System.out.println("------- complaint Share ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			String CmpltID=cmpltid;
			JSONObject obj=new JSONObject();
			obj.put("crntusrloginid",useridval );
			obj.put("cmpltid", CmpltID);
			String groupcode=String.valueOf(sessionMap.get("GROUPCODE"));
			obj.put("crntusrgrpid",groupcode);
			JSONArray ja = new JSONArray();
			String invite = cmpltShareids;
			String[] invitearr = null;
			if (invite != null && invite.contains(",") && invite.length() > 1) {
				invitearr = invite.split(",");
				for (int k = 0; k < invitearr.length; k++) {
					ja.put(invitearr[k]);
				}
			} else {
				obj.put("sharelaboridJary", "");
			}
			obj.put("sharelaboridJary",ja);	
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI9010");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println("req::: " + jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("complaintmgmt.cmpltsharelaboraction.sendemailaction");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				laborshardetails = statusCode;
				alert.setCls("success");
				alert.setMsg(getText("Event Shared successfully."));
				alertList.add(alert);
				return "success";
			} else {
				laborshardetails = statusCode;
				alert.setCls("danger");
				alert.setMsg(getText("shared Error."));
				alertList.add(alert);
				return "input";
			}
		
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Exception found in "+ this.getClass().getSimpleName() +".class in method of "+ Thread.currentThread().getStackTrace()[1].getMethodName()+"() : " + ex);
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

	public Integer getDeletecomplaintid() {
		return deletecomplaintid;
	}

	public void setDeletecomplaintid(Integer deletecomplaintid) {
		this.deletecomplaintid = deletecomplaintid;
	}

	public ComplaintsTblVO getCmpltRegObj() {
		return cmpltRegObj;
	}

	public void setCmpltRegObj(ComplaintsTblVO cmpltRegObj) {
		this.cmpltRegObj = cmpltRegObj;
	}

	public String getHiddencmpltidval() {
		return hiddencmpltidval;
	}

	public void setHiddencmpltidval(String hiddencmpltidval) {
		this.hiddencmpltidval = hiddencmpltidval;
	}

	public String getClosereasonval() {
		return closereasonval;
	}

	public void setClosereasonval(String closereasonval) {
		this.closereasonval = closereasonval;
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

	public String getDocumenttypeval() {
		return documenttypeval;
	}

	public void setDocumenttypeval(String documenttypeval) {
		this.documenttypeval = documenttypeval;
	}

	
	public String getLabordetail() {
		return labordetail;
	}

	public void setLabordetail(String labordetail) {
		this.labordetail = labordetail;
	}

	public String getSearchval() {
		return searchval;
	}

	public void setSearchval(String searchval) {
		this.searchval = searchval;
	}

	public String getSearchname() {
		return searchname;
	}

	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}

	public String getSlectfield() {
		return slectfield;
	}

	public void setSlectfield(String slectfield) {
		this.slectfield = slectfield;
	}

	public String getSociety() {
		return society;
	}

	public void setSociety(String society) {
		this.society = society;
	}

	public String getTownshipid() {
		return townshipid;
	}

	public void setTownshipid(String townshipid) {
		this.townshipid = townshipid;
	}

	public String getCmpltShareids() {
		return cmpltShareids;
	}

	public void setCmpltShareids(String cmpltShareids) {
		this.cmpltShareids = cmpltShareids;
	}

	public String getLaborshardetails() {
		return laborshardetails;
	}

	public void setLaborshardetails(String laborshardetails) {
		this.laborshardetails = laborshardetails;
	}

	public String getCmpltid() {
		return cmpltid;
	}

	public void setCmpltid(String cmpltid) {
		this.cmpltid = cmpltid;
	}

	public String getCmpltfrmusrid() {
		return cmpltfrmusrid;
	}

	public void setCmpltfrmusrid(String cmpltfrmusrid) {
		this.cmpltfrmusrid = cmpltfrmusrid;
	}

	public List<ComplaintattchTblVO> getImglist() {
		return Imglist;
	}

	public void setImglist(List<ComplaintattchTblVO> imglist) {
		Imglist = imglist;
	}
	
	public String getStatustype() {
		return statustype;
	}
	
	public void setStatustype(String statustype) {
		this.statustype = statustype;
	}
	
	public String getTestblockid() {
		return testblockid;
	}
	
	public void setTestblockid(String testblockid) {
		this.testblockid = testblockid;
	}
	
	public List<ComplaintattchTblVO> getVideofileatch() {
		return videofileatch;
	}
	
	public void setVideofileatch(List<ComplaintattchTblVO> videofileatch) {
		this.videofileatch = videofileatch;
	}
	public List<ComplaintattchTblVO> getVideothumpimg() {
		return videothumpimg;
	}
	
	public void setVideothumpimg(List<ComplaintattchTblVO> videothumpimg) {
		this.videothumpimg = videothumpimg;
	}

	public String getStatusflgstr() {
		return statusflgstr;
	}

	public void setStatusflgstr(String statusflgstr) {
		this.statusflgstr = statusflgstr;
	}

}
