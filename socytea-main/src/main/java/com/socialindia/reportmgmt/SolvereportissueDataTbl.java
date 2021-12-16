package com.socialindia.reportmgmt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.vo.ReportIssueTblVO;

public class SolvereportissueDataTbl  extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();
	private String uniqueId;
	private String mobno;
	private String iVOreportissueid;
	 ReportIssueTblVO reportissueObj=new ReportIssueTblVO();
	public String execute() {
		System.out.println("------- Report solved ------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			String useridval = String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid", useridval);
			obj.put("solveid", uniqueId);		
			obj.put("mobno", mobno);
			obj.put("ivrservicefor", "4");
			JSONObject finaljj = new JSONObject();
			finaljj.put("servicecode", "SI10009");
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			System.out.println(jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.report.solvedreportissue");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Report Issue Solve Successfully"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("error");
				alert.setMsg(getText("Report Issue Solve Error"));
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {			
			System.out.println("Step -1 : Exception Found "+getClass().getSimpleName() +".class deleteActionfun() Time : "+Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:ss") +" : "+ex);
		} finally {

		}
		return SUCCESS;
	}
	public String ViewReportissue()
	{
		System.out.println("Tender Edit Block Enter.");
		Map currentSession = ActionContext.getContext().getSession();		
		JSONObject obj=new JSONObject();
		if(iVOreportissueid==null ){					
			if((iVOreportissueid==null)  && currentSession.get("currentsession_useriVOreportissueid")!=null){							
				String userunque=(String)currentSession.get("currentsession_useriVOreportissueid");
				iVOreportissueid=userunque;
			}
		}else{
			currentSession = ActionContext.getContext().getSession();
			currentSession.put("currentsession_useriVOreportissueid", String.valueOf(iVOreportissueid));					
		}
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject lvrfinaljsonobj = null;
		try{			
			obj.put("repid", iVOreportissueid);
			
			obj.put("crntusrloginid", String.valueOf(currentSession.get("USERID")));
			obj.put("ivrservicefor","3");
			lvrfinaljsonobj = new JSONObject();
			lvrfinaljsonobj.put("servicecode", "SI00061");		
			lvrfinaljsonobj.put("data", obj);
			String jsonTextFinal = lvrfinaljsonobj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.reportissueview.action");
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
					System.out.println("response:::::::: "+response);		
					reportissueObj.setUserId((String)Commonutility.toHasChkJsonRtnValObj(json_data,"userid"));
					reportissueObj.setRepId(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"uniqueid")));
					reportissueObj.setName((String)Commonutility.toHasChkJsonRtnValObj(json_data,"usrname"));
					reportissueObj.setMobileNo((String)Commonutility.toHasChkJsonRtnValObj(json_data,"mobno"));
					reportissueObj.setEmailId((String)Commonutility.toHasChkJsonRtnValObj(json_data,"emailid"));
					reportissueObj.setStatus((String)Commonutility.toHasChkJsonRtnValObj(json_data,"status"));
					
					reportissueObj.setDescr((String)Commonutility.toHasChkJsonRtnValObj(json_data,"desc"));
					reportissueObj.setEntryDatetime((String)Commonutility.toHasChkJsonRtnValObj(json_data,"entrydate"));
					reportissueObj.setReportTo((String)Commonutility.toHasChkJsonRtnValObj(json_data,"reportto"));
					reportissueObj.setReportToType((String)Commonutility.toHasChkJsonRtnValObj(json_data,"reporttotype"));
					
					//url 
					
				}
			}
		//	ActionContext.getContext().getSession().put("tenderSessID", reportissueObj.getTenderId());
		} catch(Exception Ex) {
			System.out.println("Exception Found in Viewbookingaction.class execute() : "+Ex);
		} finally {
			lvrfinaljsonobj = null; locObjRecvJson = null;
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
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getMobno() {
		return mobno;
	}
	public void setMobno(String mobno) {
		this.mobno = mobno;
	}
	public String getiVOreportissueid() {
		return iVOreportissueid;
	}
	public void setiVOreportissueid(String iVOreportissueid) {
		this.iVOreportissueid = iVOreportissueid;
	}
	public ReportIssueTblVO getReportissueObj() {
		return reportissueObj;
	}
	public void setReportissueObj(ReportIssueTblVO reportissueObj) {
		this.reportissueObj = reportissueObj;
	}
	
	
	
}
