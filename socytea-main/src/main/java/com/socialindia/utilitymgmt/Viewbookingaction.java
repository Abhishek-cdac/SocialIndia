package com.socialindia.utilitymgmt;

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
import com.socialindia.vo.FacilityBookingTblVO;

public class Viewbookingaction extends ActionSupport {
	
	private String iVObookingid;
	private String committeecomments;
	private String uniqueId;
	 private Common commonObj = new CommonDao(); 
	  commomServices commonSnippet=new commomServices();
	  private AlertVo alert = new AlertVo();
	  private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private static final long serialVersionUID = 1L;
	 FacilityBookingTblVO bookingobj=new FacilityBookingTblVO();

	public String execute() {
		Commonutility.toWriteConsole("Booking view action called.Viewbookingaction.class");
		Map currentSession = ActionContext.getContext().getSession();		
		JSONObject obj=new JSONObject();
							
			if( iVObookingid==null  && currentSession.get("currentsession_useriVObookingid")!=null){							
				String userunque=(String) currentSession.get("currentsession_useriVObookingid");
				iVObookingid=userunque;
			}else{
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_useriVObookingid", String.valueOf(iVObookingid));			
			}
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject lvrfinaljsonobj = null;
		try{			
			obj.put("bookingid", iVObookingid);			
			obj.put("crntusrloginid", String.valueOf(currentSession.get("USERID")));
			obj.put("ivrservicefor","3");
			lvrfinaljsonobj = new JSONObject();
			lvrfinaljsonobj.put("servicecode", "SI00034");		
			lvrfinaljsonobj.put("data", obj);
			String jsonTextFinal = lvrfinaljsonobj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.bookingview.action");
			Commonutility.toWriteConsole("jsonTextFinal : "+jsonTextFinal);
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
					bookingobj.setBookingId(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_id")));
					bookingobj.setPlace((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_place"));
					bookingobj.setFacplace((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_facplace"));
					bookingobj.setFacTitle((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_facbktitle"));
					bookingobj.setTitle((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_facilityname"));
					bookingobj.setBookedBy((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_name"));
					bookingobj.setEndTime((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_enddate"));
					bookingobj.setFacdesc((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_facbkdesc"));
					bookingobj.setBookingFacId(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_facid")));
					bookingobj.setDescription((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_facdesc"));
					bookingobj.setStartTime((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_startdate"));
					bookingobj.setContactNo((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_mobno"));
					bookingobj.setBookingStatus(Integer.parseInt((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_status")));
					uniqueId=(String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_id");
					bookingobj.setCommiteecomment((String)Commonutility.toHasChkJsonRtnValObj(json_data,"commitee_comments"));
					bookingobj.setBookedusrid((String)Commonutility.toHasChkJsonRtnValObj(json_data,"booking_usrid"));
					//url 
					
				}
			}
		//	ActionContext.getContext().getSession().put("tenderSessID", bookingobj.getTenderId());
		} catch(Exception Ex) {
			Commonutility.toWriteConsole("Exception Found in Viewbookingaction.class execute() : "+Ex);
		} finally {
			lvrfinaljsonobj = null; locObjRecvJson = null;
		}
		
		return SUCCESS;
	}
	
	public String approvalActionfun(){
		Commonutility.toWriteConsole("-------booking Approval------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			JSONObject obj=new JSONObject();
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			obj.put("bookingid", String.valueOf(uniqueId));
			obj.put("statusflg","1");
			obj.put("committeecomments", committeecomments);
			obj.put("ivrservicefor", "4");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI00035");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			Commonutility.toWriteConsole(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.booking.approvalaction");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("00")) {
				alert.setCls("success");
				alert.setMsg(getText("Booking Approval Successfully"));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Booking Approval error"));
				alertList.add(alert);
				return "input";
			}
		
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : Exception found in "+ this.getClass().getSimpleName() +".class in method of "+ Thread.currentThread().getStackTrace()[1].getMethodName()+"() : " + ex);
		} finally {

		}
		return SUCCESS;
	}
	
	public String declineActionfun(){
		Commonutility.toWriteConsole("-------booking Decline------------------------");
		Map sessionMap = ActionContext.getContext().getSession();
		try{
			JSONObject obj=new JSONObject();
			String useridval=String.valueOf(sessionMap.get("USERID"));
			obj.put("crntusrloginid",useridval );
			obj.put("bookingid", String.valueOf(uniqueId));
			obj.put("committeecomments", committeecomments);
			obj.put("statusflg","2");
			obj.put("ivrservicefor", "5");
			JSONObject finaljj=new JSONObject();
			finaljj.put("servicecode", "SI00036");		
			finaljj.put("data", obj);
			String jsonTextFinal = finaljj.toString();
			Commonutility.toWriteConsole(jsonTextFinal);
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			String finalUrl =getText("socialindia.booking.declinelaction");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("00")) {
				alert.setCls("success");
				alert.setMsg(getText("Booking Declined"));
				alertList.add(alert);	
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Booking Decline Failure"));
				alertList.add(alert);
				return "input";
			}
		
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Exception ----- " + ex);
		} finally {

		}
		return SUCCESS;
	}
	public String getiVObookingid() {
		return iVObookingid;
	}

	public void setiVObookingid(String iVObookingid) {
		this.iVObookingid = iVObookingid;
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

	public FacilityBookingTblVO getBookingobj() {
		return bookingobj;
	}

	public void setBookingobj(FacilityBookingTblVO bookingobj) {
		this.bookingobj = bookingobj;
	}

	public String getCommitteecomments() {
		return committeecomments;
	}

	public void setCommitteecomments(String committeecomments) {
		this.committeecomments = committeecomments;
	}
	

}
