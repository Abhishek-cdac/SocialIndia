package com.socialindia.gatepass;

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

public class GatepassServices extends ActionSupport{
	private String uniqueId;
	private String uniqueGatepassno;
	private String passType;
	private String skillName;
	private String idcardName;
	private String Visited_date;
	private String Visited_time;
	private String Block_Name;
	private String Flat_Name;
	private String Visited_expiry_date;
	private String Visited_exprydateonly;
	byte imgByt[] = null;
	private File documentfile = null;
	private String documentfileFileName;
	private String flag;
	JSONObject obj = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();
	private String eventtype;
	private static final long serialVersionUID = 1L;
	MvpGatepassDetailTbl gatepassObj = new MvpGatepassDetailTbl();
	public String execute() {
		return SUCCESS;
	}

	public String gatepassCreateFun() throws Exception {
			Commonutility.toWriteConsole("gatepass create  loading......."+gatepassObj.getPassId());
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
				obj.put("rid",useridval );
				obj.put("visitor_name", gatepassObj.getVisitorName());
				obj.put("visitor_mobile_no", gatepassObj.getMobileNo());
				obj.put("date_of_birth",gatepassObj.getDateOfBirth() );
				obj.put("visitor_id_type", gatepassObj.getIdcardtype());
				obj.put("visitor_id_no", gatepassObj.getIdCardNumber());
				obj.put("visitor_accompanies", gatepassObj.getNoOfAccompanies());
				obj.put("issue_date", gatepassObj.getIssueDate());
				obj.put("issue_time",gatepassObj.getIssueTime() );
				obj.put("expiry_date", gatepassObj.getExpiryDate());
				obj.put("skill_id", gatepassObj.getSkillid());
				obj.put("other_detail", gatepassObj.getDescription());
				obj.put("pass_type", passType);
				obj.put("vehicle_no", gatepassObj.getVehicleNumber());
				obj.put("add_edit", "1");
				obj.put("visitor_email",gatepassObj.getEmail());
				//obj.put("gatepassfiledata", profileUpdate.toByteAryToBase64EncodeStr(imgByt));
				if(documentfile!=null){
					obj.put("gatepassfiledata", documentfile.getAbsolutePath());
				} else {
					obj.put("gatepassfiledata", "");
				}
				obj.put("gatepassfilename", documentfileFileName);
				obj.put("gatepass_id", gatepassObj.getPassId());
				obj.put("visitor_location", gatepassObj.getVisitorLocation());
				ja = new JSONArray();

				groupcode=String.valueOf(sessionMap.get("GROUPCODE"));

				finaljj=new JSONObject();
				finaljj.put("servicecode", "0058");
				finaljj.put("is_mobile", "0");
				finaljj.put("data", obj);
				jsonTextFinal = finaljj.toString().trim();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				finalUrl = getText("socialindia.gatepass.creation.action");
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

	public String ReuseServices() {
		Map currentSession = ActionContext.getContext().getSession();
		if(uniqueGatepassno==null ){
			if((uniqueGatepassno==null)  && currentSession.get("currentsession_useruniqueGatepassno")!=null){
				String userunque=(String)currentSession.get("currentsession_useruniqueGatepassno");
				uniqueGatepassno=userunque;
			}
		}else{
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_useruniqueGatepassno", String.valueOf(uniqueGatepassno));
		}
		String visitorname = null, lvrmobno = null;
		String lvrpassid=null,lvrvistorpassno=null,lvrexpirydays=null,lvrvisitordob = null,lvrissueexpirydate=null,lvrimageName=null, lvrvisitorlocation=null,lvrvisitoremailid = null,lvridcardtype=null,lvridcardno=null,lvrnoofaccompanies=null,lvrskillid=null,lvrdesc=null,lvrpasstype=null,lvrvehicleno=null,generatedate=null;
		JSONObject json = null;
		JSONObject json_data = null;
		try{

			obj.put("visitor_pass_no", uniqueGatepassno);
			obj.put("rid",String.valueOf(currentSession.get("USERID")) );
		
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "0058");
			finaljj.put("is_mobile", "0");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
			String finalUrl =getText("socialindia.reusegatepass.action.url");
			String response=commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("response : "+response);
			json = new JSONObject(response);
			json_data = (JSONObject) Commonutility.toHasChkJsonRtnValObj(json, "data");
			String lvrStscode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			if(Commonutility.checkempty(lvrStscode) && (lvrStscode.equalsIgnoreCase("00") || lvrStscode.equalsIgnoreCase("0"))) {

				visitorname = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "visitor_name");
				lvrmobno = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "visitor_mobile_no");
				lvrvisitordob = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "date_of_birth");
				lvrvisitoremailid = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "visitor_email");
				lvridcardtype = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "visitor_id_type");
				lvridcardno = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "visitor_id_no");
				lvrnoofaccompanies = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "visitor_accompanies");
				lvrskillid = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "skill_id");
				lvrdesc = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "other_detail");
				lvrpasstype = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "pass_type");
				lvrvehicleno = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "vehicle_no");
				lvrvisitorlocation = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "visitor_location");
				lvrvisitorlocation = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "visitor_location");
				lvrissueexpirydate = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "issue_expirydate");
				lvrimageName = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "imageName");
				lvrpassid = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "visitor_pass_id");
				lvrvistorpassno = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "visitor_pass_no");
				generatedate=(String) Commonutility.toHasChkJsonRtnValObj(json_data, "generator_date");
				lvrexpirydays=(String) Commonutility.toHasChkJsonRtnValObj(json_data, "expiry_days");
				if (Commonutility.checkempty(generatedate)){
					generatedate=Commonutility.tochangeonefrmtoanother("yyyy-MM-dd","dd-MM-yyyy",generatedate);
				} else {
					generatedate = "";
				}
				
				if(lvrpasstype!=null && lvrpasstype.equalsIgnoreCase("1")){
					//gatepassObj.setExpiryDate(lvrexpirydays);
					Visited_expiry_date=lvrexpirydays;
					Visited_exprydateonly = lvrexpirydays + " Days ";
				} else{
					//gatepassObj.setExpiryDate(lvrissueexpirydate);
					Visited_expiry_date=lvrissueexpirydate;
					Visited_exprydateonly = lvrissueexpirydate;
				}
				
				System.out.println("Visited_expiry_date== "+Visited_expiry_date);
				gatepassObj.setVisitorName(visitorname);
				gatepassObj.setMobileNo(lvrmobno);
				gatepassObj.setDateOfBirth(lvrvisitordob);
				gatepassObj.setEmail(lvrvisitoremailid);;
				gatepassObj.setIdcardtype(lvridcardtype);
				gatepassObj.setIdCardNumber(lvridcardno);
				gatepassObj.setNoOfAccompanies(lvrnoofaccompanies);
				gatepassObj.setSkillid(lvrskillid);
				gatepassObj.setDescription(lvrdesc);
			//	gatepassObj.setPassType(lvrpasstype);
				passType=lvrpasstype;
				gatepassObj.setVehicleNumber(lvrvehicleno);
				gatepassObj.setVisitorLocation(lvrvisitorlocation);
				gatepassObj.setVisitorImage(lvrimageName);
				gatepassObj.setPassId(lvrpassid);
				gatepassObj.setEntryDatetime(generatedate);
				gatepassObj.setGatepassNo(lvrvistorpassno);
				skillName=(String) Commonutility.toHasChkJsonRtnValObj(json_data, "skills_name");
				idcardName=(String) Commonutility.toHasChkJsonRtnValObj(json_data, "idcard_name");
				Visited_date=(String) Commonutility.toHasChkJsonRtnValObj(json_data, "issue_date");
				Visited_time=(String) Commonutility.toHasChkJsonRtnValObj(json_data, "issue_time");
				Block_Name =(String) Commonutility.toHasChkJsonRtnValObj(json_data, "block_name");
				Flat_Name =(String) Commonutility.toHasChkJsonRtnValObj(json_data, "flat_no");
				ActionContext.getContext().getSession().put("GATEPASSID",gatepassObj.getPassId());
				ActionContext.getContext().getSession().put("visitorimg",gatepassObj.getVisitorImage());
				flag = "success";
			} else{
				flag = "fail";
			}

		} catch(Exception ex){
			flag = "error";
				Commonutility.toWriteConsole("Step -1: Exception found in "+getClass().getSimpleName()+".calss : "+ex);
		} finally {

		}

	   return SUCCESS;
	}

	public String deleteGatepassAction(){
		Commonutility.toWriteConsole("------- gatepass Delete ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("rid",useridval );
			obj.put("pass_id", uniqueId);
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "0058");
			finaljj.put("is_mobile", "0");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
			String finalUrl =getText("socialindia.gatepass.deleteaction.action");
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
				sessionMap.put("alertList", alertList);
			return "success";
			} else {
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("error.delete.event"));
				}
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
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

	public MvpGatepassDetailTbl getGatepassObj() {
		return gatepassObj;
	}

	public void setGatepassObj(MvpGatepassDetailTbl gatepassObj) {
		this.gatepassObj = gatepassObj;
	}

	public String getUniqueGatepassno() {
		return uniqueGatepassno;
	}

	public void setUniqueGatepassno(String uniqueGatepassno) {
		this.uniqueGatepassno = uniqueGatepassno;
	}

	public String getPassType() {
		return passType;
	}

	public void setPassType(String passType) {
		this.passType = passType;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getIdcardName() {
		return idcardName;
	}

	public void setIdcardName(String idcardName) {
		this.idcardName = idcardName;
	}

	public String getVisited_date() {
		return Visited_date;
	}

	public void setVisited_date(String visited_date) {
		Visited_date = visited_date;
	}

	public String getVisited_time() {
		return Visited_time;
	}

	public void setVisited_time(String visited_time) {
		Visited_time = visited_time;
	}

	public String getBlock_Name() {
		return Block_Name;
	}

	public void setBlock_Name(String block_Name) {
		Block_Name = block_Name;
	}

	public String getFlat_Name() {
		return Flat_Name;
	}

	public void setFlat_Name(String flat_Name) {
		Flat_Name = flat_Name;
	}

	public String getVisited_expiry_date() {
		return Visited_expiry_date;
	}

	public void setVisited_expiry_date(String visited_expiry_date) {
		Visited_expiry_date = visited_expiry_date;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the visited_exprydateonly
	 */
	public String getVisited_exprydateonly() {
		return Visited_exprydateonly;
	}

	/**
	 * @param visited_exprydateonly the visited_exprydateonly to set
	 */
	public void setVisited_exprydateonly(String visited_exprydateonly) {
		Visited_exprydateonly = visited_exprydateonly;
	}

  
}

