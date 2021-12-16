package com.socialindia.common;

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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.notificationmaster.NotificationTblVO;

public class DashboardCountFavorites extends ActionSupport{
	/**
	 * 
	 */
	JSONObject obj = new JSONObject();
	 private AlertVo alert = new AlertVo();
	  private List<AlertVo> alertList = new ArrayList<AlertVo>();
	  private Common commonObj = new CommonDao(); 
	  commomServices commonSnippet=new commomServices();
	  private String Favcountall;
	private static final long serialVersionUID = 1L;
	public String execute() {	
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
			Map sessionMap = ActionContext.getContext().getSession();
			String sdtSearch="";
			System.out.println("Favorites count start>>>>>>");
			dataJson.put("status", "1");
			dataJson.put("currentloginid", String.valueOf(sessionMap.get("USERID")));// Current login id
			finaljj.put("servicecode", "SI34000");		
			finaljj.put("data", dataJson);
			jsonTextFinal = finaljj.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.dashboard.favoritescount");
			response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("respone>>>>>> "+response);
			JSONObject json = new JSONObject(response);
			
			if (response != null && !response.equalsIgnoreCase("null")
					&& response.length() > 0) {
				boolean ivIsJson = Commonutility.toCheckIsJSON(response);
				if (ivIsJson) {
					/**/
					JSONObject json_data = json.getJSONObject("data");
					String statusCode = (String) json.get("statuscode");
					String respCode = (String) json.get("respcode");
					System.out.println("data--------- "+json_data);
					Favcountall=json_data.getString("count");
					
				}
			}
		} catch (Exception ex) {
			alert.setCls("error");
			alert.setMsg("Error in Notification");
			alertList.add(alert);
			return "error";
		}
		finally
		{
			finaljj=null;dataJson =null;temp =null;finalUrl=null;jsonTextFinal =null;response =null;
			
		}
	   return SUCCESS;
	}
	public String getFavcountall() {
		return Favcountall;
	}
	public void setFavcountall(String favcountall) {
		Favcountall = favcountall;
	}
		

}
