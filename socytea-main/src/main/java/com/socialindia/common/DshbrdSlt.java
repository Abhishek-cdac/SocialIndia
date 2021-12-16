package com.socialindia.common;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.vo.AuditlogTblVO;

public class DshbrdSlt extends ActionSupport{

	/**
	 * 
	 */
	private Common commonObj = new CommonDao();
	private static final long serialVersionUID = 1L;
	private int activeuser;
	private int usercomplaints;
	private int lbrCount;
	private int mctcount;
	private int noappDownload; 
	private int failedlogon;
	private int appuse;
	private Long statusHealth;
	private Integer recentactid;
	private String recentactdesc;
	private Integer recentactentryby;
	private String recentactentrytime;
	private String sdval;
	private String apval;
	private String opval;
	private String urldata;
	private String cyberbalance;
	
	 List<AuditlogTblVO> RecentActList = new ArrayList<AuditlogTblVO>();
	public String execute(){
		
		JSONObject dataJson=null;JSONObject finaljj=null;String response =null;
		String jsonTextFinal = null;String temp =null;String finalUrl=null;JSONObject json =null;
		
		try{
		dataJson = new JSONObject();
		dataJson.put("reststatus", "1");
		finaljj = new JSONObject();
		finaljj.put("servicecode", "SI24000");
		finaljj.put("servicefor", "3");// select
		finaljj.put("data", dataJson);
		jsonTextFinal = finaljj.toString();
		jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
		temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
		finalUrl = getText("socialindia.dashboard.action");
		response = commonObj.jsonRequest(finalUrl, temp);
		JSONObject json_data = new JSONObject(response);
		json = json_data.getJSONObject("data");
		activeuser=(Integer)json.getInt("activeuser");
		usercomplaints=(Integer)json.getInt("usercomplaints");
		lbrCount = (Integer)json.getInt("lbrCount");
		mctcount= (Integer)json.getInt("mctcount");
		noappDownload = (Integer)json.getInt("noappDownload");
		failedlogon = (Integer)json.getInt("failedlogon");
		appuse = (Integer)json.getInt("appuse");
		statusHealth=(Long) json.getLong("statusHealth");
		
		}catch(Exception ex){
			System.out.println("Ex--------"+ex);
		}
		return SUCCESS;
	}
	
	public String getrecentactivity() {
		JSONObject locObjRecvJson = null;// Receive String to json
		JSONObject locObjRecvdataJson = null;// Receive Data Json
		JSONObject locObjRspdataJson = null;// Response Data Json
		JSONArray jsonarr = new JSONArray();
		JSONArray array = new JSONArray();
		JSONObject finaljj = new JSONObject();
		JSONObject dataJson = new JSONObject();
		String temp = null;
		String jsonTextFinal = null;
		String finalUrl = null;
		String response = null;

		try {
			System.out.println("recent activity");
			Map sessionMap = ActionContext.getContext().getSession();
			String sdtSearch="";
			dataJson.put("fdbkstatus", "1");
			dataJson.put("countflg", "yes");
			dataJson.put("countfilterflg", "yes");
			dataJson.put("startrow", "0");// starting row
			dataJson.put("totalrow", "5");// total row
			dataJson.put("srchdtsrch", sdtSearch);
			dataJson.put("searchflg", "");
			dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));// Current login id
			dataJson.put("fdbkforsocid", String.valueOf(sessionMap.get("sSoctyId")));// Current society id
			finaljj.put("servicecode", "SI34002");		
			finaljj.put("data", dataJson);
			jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.dashboard.recentactivity");
			response = commonObj.jsonRequest(finalUrl, temp);
			int notifid=0;
			if (response != null && !response.equalsIgnoreCase("null")
					&& response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					locObjRecvJson = new JSONObject(response);
					JSONObject json_data = locObjRecvJson.getJSONObject("data");
					jsonarr =(JSONArray) Commonutility.toHasChkJsonRtnValObj(json_data,"recentactdetails");
					
					if (jsonarr.length() > 0) {						
						for(int j = 0; j < jsonarr.length(); j++){
							String rcnst = (String)  Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "recentactivity_id");
							if (Commonutility.checkempty(rcnst)) {
								recentactid = Integer.parseInt(rcnst);
							}
							String lvrrcnstentryby =  (String)Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "recentactivity_entry");
							if (Commonutility.checkempty(lvrrcnstentryby)) {
								recentactentryby = Integer.parseInt(lvrrcnstentryby);
							}
							recentactdesc = (String)  Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "recentactivity_name");
							//recentactentryby = (Integer)  Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "recentactivity_entry");
							recentactentrytime = (String)  Commonutility.toHasChkJsonRtnValObj(jsonarr.getJSONObject(j), "recentactivity_entrydate");
							RecentActList.add(new AuditlogTblVO(recentactid,recentactdesc, recentactentryby,recentactentrytime));						
						}
						
					} else {

					}
				}
			}
		} catch (Exception ex) {
			/*alert.setCls("error");
			alert.setMsg("Error in Notification");
			alertList.add(alert);*/
			return "error";
		}
		finally
		{
			finaljj=null;dataJson =null;temp =null;finalUrl=null;jsonTextFinal =null;response =null;
			
		}
	   return SUCCESS;
	}
	// Get Wallet Balance to cyberplat
	public String cyberplatedatamethod(){
		JSONObject json =null;
		String temp=null;
		String response=null;
		try{
			temp = "SD="+sdval+"&AP="+apval+"&OP="+opval+"";
			response = commonObj.jsonRequest(urldata, temp);
			Commonutility.toWriteConsole("Payment wallet Balance response : "+response);
			if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				
				if (ivIsJson) {
					JSONObject locObjRecvJson = new JSONObject(response);
					cyberbalance = (String)  Commonutility.toHasChkJsonRtnValObj(locObjRecvJson, "Amount") + " Rs";
					
				} else {
					cyberbalance = "0 Rs";
				}
			} else {
				cyberbalance = "0 Rs"; 
			}
			Commonutility.toWriteConsole("Payment wallet Balance : "+cyberbalance);
		}catch(Exception ex){
			System.out.println("Exception found in DshbrdSlt.cyberplatedatamethod () : "+ex);
		}
		return SUCCESS;
	}
	public int getActiveuser() {
		return activeuser;
	}
	public void setActiveuser(int activeuser) {
		this.activeuser = activeuser;
	}
	public int getUsercomplaints() {
		return usercomplaints;
	}
	public void setUsercomplaints(int usercomplaints) {
		this.usercomplaints = usercomplaints;
	}
	public int getLbrCount() {
		return lbrCount;
	}
	public void setLbrCount(int lbrCount) {
		this.lbrCount = lbrCount;
	}
	public int getMctcount() {
		return mctcount;
	}
	public void setMctcount(int mctcount) {
		this.mctcount = mctcount;
	}
	public int getNoappDownload() {
		return noappDownload;
	}
	public void setNoappDownload(int noappDownload) {
		this.noappDownload = noappDownload;
	}
	public int getFailedlogon() {
		return failedlogon;
	}
	public void setFailedlogon(int failedlogon) {
		this.failedlogon = failedlogon;
	}
	public int getAppuse() {
		return appuse;
	}
	public void setAppuse(int appuse) {
		this.appuse = appuse;
	}
	public Long getStatusHealth() {
		return statusHealth;
	}
	public void setStatusHealth(Long statusHealth) {
		this.statusHealth = statusHealth;
	}
	public Integer getRecentactid() {
		return recentactid;
	}
	public void setRecentactid(Integer recentactid) {
		this.recentactid = recentactid;
	}
	public String getRecentactdesc() {
		return recentactdesc;
	}
	public void setRecentactdesc(String recentactdesc) {
		this.recentactdesc = recentactdesc;
	}
	public Integer getRecentactentryby() {
		return recentactentryby;
	}
	public void setRecentactentryby(Integer recentactentryby) {
		this.recentactentryby = recentactentryby;
	}
	public List<AuditlogTblVO> getRecentActList() {
		return RecentActList;
	}
	public void setRecentActList(List<AuditlogTblVO> recentActList) {
		RecentActList = recentActList;
	}
	public String getRecentactentrytime() {
		return recentactentrytime;
	}
	public void setRecentactentrytime(String recentactentrytime) {
		this.recentactentrytime = recentactentrytime;
	}
	public String getSdval() {
		return sdval;
	}
	public void setSdval(String sdval) {
		this.sdval = sdval;
	}
	public String getApval() {
		return apval;
	}
	public void setApval(String apval) {
		this.apval = apval;
	}
	public String getOpval() {
		return opval;
	}
	public void setOpval(String opval) {
		this.opval = opval;
	}
	public String getUrldata() {
		return urldata;
	}
	public void setUrldata(String urldata) {
		this.urldata = urldata;
	}
	public String getCyberbalance() {
		return cyberbalance;
	}
	public void setCyberbalance(String cyberbalance) {
		this.cyberbalance = cyberbalance;
	}
	
	
}
