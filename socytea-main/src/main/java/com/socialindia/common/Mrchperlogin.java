package com.socialindia.common;


import java.net.URLDecoder;
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
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.residentmgmt.ResidentMgmtAction;
import com.socialindia.vo.MerchantProductItemsTblVO;


public class Mrchperlogin extends ActionSupport{
	private String orderId;
	private String residentname;
	private String residentlastname;
	//private String residentmobno;
	private String residentblockname;
	private String residentflatno;
	private String residentadd1;
	private String residentadd2;
	private String residentmobno;
	
	private String residentcountry;
	private String residentstate;
	private String residentcity;
	private String residentpincode;
	private String residentcomments;
	
	private String orderfeatures;
	private String ordercuisines;
	private String ordernoofpersons;
	private String productorderid;
	private String supplyquality;
	private String[] acceptstatus_str;
	private String[] supplyquality_str;
	private String[] commentid_str;
	private String supplycomments;
	private String Prctacceptstatus;
	private String mrchcomments;
	JSONObject obj = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	commomServices commonSnippet = new commomServices();
	private static final long serialVersionUID = 1L;
	JSONObject locObjRecvJson = null;// Receive String to json
	JSONObject locObjRecvdataJson = null;// Receive Data Json
	JSONObject locObjRspdataJson = null;// Response Data Json
	List<MerchantProductItemsTblVO> OrderList = new ArrayList<MerchantProductItemsTblVO>();
	List<MerchantProductItemsTblVO> FlatList = new ArrayList<MerchantProductItemsTblVO>();
	public String execute() {
		Commonutility.toWriteConsole("------- Merchant perlogin View ------------------------");
		Map sessionMap = null;String useridval=null;JSONObject finaljj=null;String jsonTextFinal=null;String finalUrl =null;String temp=null;String response=null;
		JSONObject json =null;JSONObject json_data =null;
		Map currentSession = ActionContext.getContext().getSession();
		
		
		try{
			sessionMap = ActionContext.getContext().getSession();	
			String ss =null,st=null;
			if(orderId!=null && !orderId.equalsIgnoreCase("") && !orderId.equalsIgnoreCase("null"))
			{
				
			 ss=URLDecoder.decode(orderId);
			 st=EncDecrypt.decrypt(ss);
			}
			else
			{
				st=productorderid;
			}
			//get orderid 
			
			if(productorderid==null || productorderid.equalsIgnoreCase("")  ){	
				if((productorderid==null)  && currentSession.get("currentsession_useruniqueId")!=null){
					String userunque=(String)currentSession.get("currentsession_useruniqueId");
					productorderid=userunque;
				}
			}else{
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_useruniqueId", productorderid);					
			}
			obj.put("orderid", st);
			finaljj=new JSONObject();
			finaljj.put("servicecode", "SI39000");		
			finaljj.put("data", obj);
			jsonTextFinal = finaljj.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);			
			finalUrl =getText("socialindia.getproductorderdetailaction.url");
			response=commonObj.jsonRequest(finalUrl, temp);
			
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
			boolean ivIsJson = Commonutility.toCheckIsJSON(response);
			if (ivIsJson) {
				locObjRecvJson = new JSONObject(response);
				locObjRecvdataJson = locObjRecvJson.getJSONObject("data");
				JSONArray prdtorderarr = new JSONArray();
				JSONArray flatdetailsarr = new JSONArray();
				JSONArray ar1 = new JSONArray();
				
				String itemname="";
				String orderqty="";
				String supplyqty="";
				String commentid="";
				String itemstatus="";
				String flatnumber="";
				String blackname="";
				prdtorderarr = (JSONArray) Commonutility .toHasChkJsonRtnValObj(locObjRecvdataJson, "jArry_prdt_order");	
				flatdetailsarr = (JSONArray) Commonutility .toHasChkJsonRtnValObj(locObjRecvdataJson, "jArry_flat_details");	
			if (prdtorderarr != null && prdtorderarr.length() > 0) {
				for(int j = 0; j < prdtorderarr.length(); j++){
					itemname=(String)Commonutility.toHasChkJsonRtnValObj(prdtorderarr.getJSONObject(j), "orderfeatures");
					orderqty =String.valueOf((Integer)Commonutility.toHasChkJsonRtnValObj(prdtorderarr.getJSONObject(j), "orderqty"));
					supplyqty =String.valueOf((Integer)Commonutility.toHasChkJsonRtnValObj(prdtorderarr.getJSONObject(j), "supplyqty"));
					commentid =String.valueOf((Integer)Commonutility.toHasChkJsonRtnValObj(prdtorderarr.getJSONObject(j), "commentid"));
					itemstatus=String.valueOf((Integer)Commonutility.toHasChkJsonRtnValObj(prdtorderarr.getJSONObject(j),"itemstatus"));
					OrderList.add(new MerchantProductItemsTblVO(itemname,orderqty,supplyqty,commentid,itemstatus));
				}
				
			}
			if (flatdetailsarr != null && flatdetailsarr.length() > 0) {
				for(int k = 0; k < flatdetailsarr.length(); k++){
					flatnumber=(String)Commonutility.toHasChkJsonRtnValObj(flatdetailsarr.getJSONObject(k), "flatnumber");
					blackname =(String)Commonutility.toHasChkJsonRtnValObj(flatdetailsarr.getJSONObject(k), "blackname");
					
					FlatList.add(new MerchantProductItemsTblVO(flatnumber,blackname));
				}
				
			}
				residentname=((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restname"));
				residentlastname=((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restlname"));
				residentmobno =(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restmobno");
				residentblockname=((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restblockname"));
				residentflatno =(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restflatno");
				residentadd1=((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restadd1"));
				residentadd2=((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restadd2"));
				residentcountry=((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restcountry"));
				residentstate=((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"reststate"));
				residentcity=((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restcity"));
				residentpincode=((String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restpincode"));
				residentcomments=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"restcomments");
				productorderid=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"orderid");
				Prctacceptstatus=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"acceptstatus");
				mrchcomments=(String)Commonutility.toHasChkJsonRtnValObj(locObjRecvdataJson,"mrchcomments");
				
		}
	}
			
		
			
	
			}
			catch(Exception ex){
				Commonutility.toWriteConsole("Exception ------------------- "+ex);	
			}finally{
				useridval=null;finaljj=null;jsonTextFinal=null;finalUrl =null;temp=null;response=null;
				json =null;json_data =null;
			}
			
		
		return SUCCESS;
	}

	public String updatedMrchprdtordertblaction() {
		CommonDao commonObj = null;
		Log logwrite = null;
		JSONObject json = null;
		JSONObject json_data = null;
		JSONObject dataJson = null;
		JSONArray lvrBlckjsonary = null;
		JSONArray lvrFlatjsonary = null;
		JSONArray lvrcmtidjsonary = null;
		JSONObject finaljj = null;
		
		try {
			logwrite = new Log();
			commonObj = new CommonDao();
			logwrite.logMessage("Step 1 : Resident Update Called updatedresidentActionFunction() [Start]", "info", ResidentMgmtAction.class);
			System.out.println("Step 1 : Resident Update Called updatedresidentActionFunction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			boolean result = false;			
			Map sessionMap = ActionContext.getContext().getSession();
		
			dataJson = new JSONObject();
			dataJson.put("orderid",productorderid);
			
			dataJson.put("supplycomments", supplycomments);
			dataJson.put("acceptstatus","1");
			String[] lvrBlcknmestrary = acceptstatus_str[0].split("!_!");
			lvrBlckjsonary = new JSONArray();
			for (int i = 0; i < lvrBlcknmestrary.length; i++) {
				lvrBlckjsonary.put(lvrBlcknmestrary[i]);
			}
			lvrBlcknmestrary = null;
			dataJson.put("acceptstatusJary", lvrBlckjsonary);
			

			String[] lvrFlatNumstrary =supplyquality_str[0].split("!_!");
			lvrFlatjsonary = new JSONArray();
			for (int j = 0; j < lvrFlatNumstrary.length; j++) {
				lvrFlatjsonary.put(lvrFlatNumstrary[j]);	
			}
			lvrFlatNumstrary = null;
			dataJson.put("supplyqualityJary", lvrFlatjsonary);
			String[] lvrcmtidrary =commentid_str[0].split("!_!");
			lvrcmtidjsonary = new JSONArray();
			for (int j = 0; j < lvrcmtidrary.length; j++) {
				lvrcmtidjsonary.put(lvrcmtidrary[j]);	
			}
			lvrcmtidrary = null;
			dataJson.put("commentidJary", lvrcmtidjsonary);
			/*dataJson.put("fammemtype",fmbrMtype);
			dataJson.put("famprfaccess",fmbrPrfAccess);*/
			
			dataJson.put("entryby", String.valueOf(sessionMap.get("USERID")));
			dataJson.put("currentloginid",String.valueOf(sessionMap.get("USERID")));
			finaljj = new JSONObject();
			finaljj.put("servicecode", "SI39001");
			
			finaljj.put("data", dataJson);
			//String srvcUrl="http://192.168.1.19:8087/socialindiaservices/lbrupdates?ivrservicefor=2";
			String jsonTextFinal = finaljj.toString().trim();
			System.out.println("Step 2 : Resident Single select  View Called updatedMrchprdtordertblaction() [End] : " + jsonTextFinal);
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.common.mrchprdtorderupdatedaction");
			logwrite.logMessage("Step 2 : Update updatedMrchprdtordertblaction() [End]", "info", ResidentMgmtAction.class);
			
			String response = commonObj.jsonRequest(finalUrl, temp);
			json = new JSONObject(response);
			//json_data = json.getJSONObject("data");
			String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			logwrite.logMessage("Step 3 : Update Called updatedMrchprdtordertblaction() [End]", "info", ResidentMgmtAction.class);
			System.out.println("Step 3 : Update Called updatedMrchprdtordertblaction() [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			if (statusCode.equalsIgnoreCase("0") && respCode.equalsIgnoreCase("S39001")) {			
				 alert.setCls("success");
					alert.setMsg(getText("sucess.productorder.accept"));
					alertList.add(alert);
					execute();
					return "success";			 
			} else {		
				alert.setCls("error");
				alert.setMsg(getText("error.productorder.accept"));
				alertList.add(alert);
				return "input";
			}		
	} catch (Exception ex) {
		logwrite.logMessage("Step -1 : Update Called updatedMrchprdtordertblaction() [End]", "info", ResidentMgmtAction.class);
		System.out.println("Step -1 : Update select  View Called updatedMrchprdtordertblaction() [End] : " + ex);
		/*alert.setCls("error");
		alert.setMsg(getText("Error in user updating"));
		alertList.add(alert);*/
		return "input";
	} finally {
		 commonObj = null; logwrite = null; json = null;  json_data = null; dataJson = null;  lvrBlckjsonary = null; lvrFlatjsonary = null; finaljj = null;
	}
}
	
	
	
public String declineordertblaction()
{
	CommonDao commonObj = null;
	Log logwrite = null;
	JSONObject json = null;
	JSONObject json_data = null;
	JSONObject dataJson = null;
	
	JSONObject finaljj = null;
	
	try {
		logwrite = new Log();
		commonObj = new CommonDao();
		logwrite.logMessage("Step 1 : Resident Update Called updatedresidentActionFunction() [Start]", "info", ResidentMgmtAction.class);
		System.out.println("Step 1 : Resident Update Called updatedresidentActionFunction() [Start] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
		boolean result = false;			
		Map sessionMap = ActionContext.getContext().getSession();
	
		dataJson = new JSONObject();
		dataJson.put("orderid",productorderid);
		dataJson.put("acceptstatus","2");
		
		/*dataJson.put("fammemtype",fmbrMtype);
		dataJson.put("famprfaccess",fmbrPrfAccess);*/
		
		dataJson.put("entryby", String.valueOf(sessionMap.get("USERID")));
		dataJson.put("currentloginid",String.valueOf(sessionMap.get("USERID")));
		finaljj = new JSONObject();
		finaljj.put("servicecode", "SI39002");
		
		finaljj.put("data", dataJson);
		//String srvcUrl="http://192.168.1.19:8087/socialindiaservices/lbrupdates?ivrservicefor=2";
		String jsonTextFinal = finaljj.toString().trim();
		System.out.println("Step 2 : Resident Single select  View Called updatedMrchprdtordertblaction() [End] : " + jsonTextFinal);
		jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
		String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
		String finalUrl = getText("socialindia.common.declineorderupdatedaction");
		logwrite.logMessage("Step 2 : Update updatedMrchprdtordertblaction() [End]", "info", ResidentMgmtAction.class);
		
		String response = commonObj.jsonRequest(finalUrl, temp);
		json = new JSONObject(response);
		//json_data = json.getJSONObject("data");
		String statusCode = (String) json.get("statuscode");
		String respCode = (String) json.get("respcode");
		logwrite.logMessage("Step 3 : Update Called updatedMrchprdtordertblaction() [End]", "info", ResidentMgmtAction.class);
		System.out.println("Step 3 : Update Called updatedMrchprdtordertblaction() [End] : " + Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
		if (statusCode.equalsIgnoreCase("0") && respCode.equalsIgnoreCase("S39002")) {			
			 alert.setCls("success");
				alert.setMsg(getText("success.productorder.decline"));
				alertList.add(alert);
				execute();
				return "success";			 
		} else {		
			alert.setCls("error");
			alert.setMsg(getText("error.productorder.accept"));
			alertList.add(alert);
			return "input";
		}		
} catch (Exception ex) {
	logwrite.logMessage("Step -1 : Update Called updatedMrchprdtordertblaction() [End]", "info", ResidentMgmtAction.class);
	System.out.println("Step -1 : Update select  View Called updatedMrchprdtordertblaction() [End] : " + ex);
	/*alert.setCls("error");
	alert.setMsg(getText("Error in user updating"));
	alertList.add(alert);*/
	return "input";
} finally {
	 commonObj = null; logwrite = null; json = null;  json_data = null; dataJson = null;   finaljj = null;
}
}
	

	

	



public List<MerchantProductItemsTblVO> getFlatList() {
	return FlatList;
}

public void setFlatList(List<MerchantProductItemsTblVO> flatList) {
	FlatList = flatList;
}

public String getResidentlastname() {
	return residentlastname;
}

public void setResidentlastname(String residentlastname) {
	this.residentlastname = residentlastname;
}

public String getMrchcomments() {
		return mrchcomments;
	}

	public void setMrchcomments(String mrchcomments) {
		this.mrchcomments = mrchcomments;
	}

public String getPrctacceptstatus() {
		return Prctacceptstatus;
	}

	public void setPrctacceptstatus(String prctacceptstatus) {
		Prctacceptstatus = prctacceptstatus;
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

public String[] getCommentid_str() {
		return commentid_str;
	}

	public void setCommentid_str(String[] commentid_str) {
		this.commentid_str = commentid_str;
	}

public String getProductorderid() {
		return productorderid;
	}

	public void setProductorderid(String productorderid) {
		this.productorderid = productorderid;
	}

public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



public String getSupplyquality() {
	return supplyquality;
}

public void setSupplyquality(String supplyquality) {
	this.supplyquality = supplyquality;
}

public String getResidentname() {
	return residentname;
}









public void setResidentname(String residentname) {
	this.residentname = residentname;
}









public String getResidentblockname() {
	return residentblockname;
}









public void setResidentblockname(String residentblockname) {
	this.residentblockname = residentblockname;
}









public String getResidentflatno() {
	return residentflatno;
}









public void setResidentflatno(String residentflatno) {
	this.residentflatno = residentflatno;
}









public String getResidentadd1() {
	return residentadd1;
}









public void setResidentadd1(String residentadd1) {
	this.residentadd1 = residentadd1;
}









public String getResidentadd2() {
	return residentadd2;
}









public void setResidentadd2(String residentadd2) {
	this.residentadd2 = residentadd2;
}









public String getResidentmobno() {
	return residentmobno;
}









public void setResidentmobno(String residentmobno) {
	this.residentmobno = residentmobno;
}









public String getResidentcountry() {
	return residentcountry;
}









public void setResidentcountry(String residentcountry) {
	this.residentcountry = residentcountry;
}









public String getResidentstate() {
	return residentstate;
}









public void setResidentstate(String residentstate) {
	this.residentstate = residentstate;
}









public String getResidentcity() {
	return residentcity;
}









public void setResidentcity(String residentcity) {
	this.residentcity = residentcity;
}









public String getResidentpincode() {
	return residentpincode;
}









public void setResidentpincode(String residentpincode) {
	this.residentpincode = residentpincode;
}









public String getResidentcomments() {
	return residentcomments;
}









public void setResidentcomments(String residentcomments) {
	this.residentcomments = residentcomments;
}









public List<MerchantProductItemsTblVO> getOrderList() {
	return OrderList;
}









public void setOrderList(List<MerchantProductItemsTblVO> orderList) {
	OrderList = orderList;
}

public String getSupplycomments() {
	return supplycomments;
}

public void setSupplycomments(String supplycomments) {
	this.supplycomments = supplycomments;
}

public String[] getAcceptstatus_str() {
	return acceptstatus_str;
}

public void setAcceptstatus_str(String[] acceptstatus_str) {
	this.acceptstatus_str = acceptstatus_str;
}

public String[] getSupplyquality_str() {
	return supplyquality_str;
}

public void setSupplyquality_str(String[] supplyquality_str) {
	this.supplyquality_str = supplyquality_str;
}






}

