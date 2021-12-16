package com.socialindia.function;

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

public class Functionservices  extends ActionSupport{

	/**
	 * 
	 */
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private String funname = null;
	private String[] functionname = null;
	private String functionid = null;
	private String statusflg = null;
	private String functiontxtname = null;
	private String[] functiontxttxtid = null;
	private String[] functionnametxt = null;
	private String functiontype;
	private List<FunctionMgmtTblVO> funList = new ArrayList<FunctionMgmtTblVO>();
	private static final long serialVersionUID = 1L;

	public String execute() {
		return SUCCESS;

	}

	public String createFunction() {
		JSONObject obj = null;
		JSONObject data = null;
		Common commonObj = null;
		JSONArray functiontext = null;
		JSONObject json = null;
		Map sessionMap = null;
		try {
			sessionMap = ActionContext.getContext().getSession();
			obj = new JSONObject();
			data = new JSONObject();
			commonObj = new CommonDao();
			
			obj.put("functiontype", functiontype);
			obj.put("funname", funname);
			String[] functiontxt = functionname[0].split("!_!");
			functiontext = new JSONArray();
			for (int i = 0; i < functiontxt.length; i++) {
				functiontext.put(functiontxt[i]);
			}
			functiontxt = null;
			obj.put("functiontxt", functiontext);
			data.put("servicecode", "SI330001");
			data.put("servicefor", "1");
			data.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.function.functioncreation");
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("Response : function ::::::::::::::::"+ response);
			json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Function added successfully."));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Error ."));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}
				
		} catch (Exception ex) {
			System.out.println("Exception ------ " + ex);
			alert.setCls("danger");
			alert.setMsg(getText("Error ."));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			return "input";
		}		
	}

	public String updateActionmethod() {
		JSONObject obj = null;
		JSONObject data = null;
		Common commonObj = null;
		JSONArray functiontext = null;
		JSONArray functiontxttext = null;
		JSONObject json = null;
		int count = 0;
		try {
			obj = new JSONObject();
			data = new JSONObject();
			commonObj = new CommonDao();
			Map sessionMap = ActionContext.getContext().getSession();
			if(Commonutility.checkempty(functionid)){
			obj.put("functionid", functionid);
			obj.put("functiontype", functiontype);
			obj.put("funname", funname);
			String[] functiontxt = functionnametxt[0].split("!_!");
			String[] functiontxtid = functiontxttxtid[0].split("!_!");
			functiontext = new JSONArray();
			for (int i = 0; i < functiontxt.length; i++) {
				functiontext.put(functiontxt[i]);
			}
			functiontxt = null;
			obj.put("functiontxt", functiontext);

			functiontxttext = new JSONArray();
			for (int i = 0; i < functiontxtid.length; i++) {
				functiontxttext.put(functiontxtid[i]);
				count = i;
			}
			obj.put("functiontxtid", functiontxttext);
			obj.put("count", String.valueOf(count));
			functiontxtid = null;
			data.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
			data.put("servicecode", "SI330002");
			data.put("servicefor", "2");
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.function.functionupdate");
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("response ---------- " + response);
			json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Function updated successfully."));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Error ."));
				alertList.add(alert);
				return "input";
			}
			} else {
				
			}

		} catch (Exception ex) {
			System.out.println("Exception ------ " + ex);
		}

		return SUCCESS;
	}

	public String editFunction() {
		JSONObject obj = null;
		JSONObject data = null;
		Common commonObj = null;
		JSONArray functiontext = null;
		JSONObject json = null;
		JSONArray ar = null;
		FunctionMgmtTblVO funobj = null;
		try {
			funobj = new FunctionMgmtTblVO(null, null, null, null, 0, null);
			obj = new JSONObject();
			data = new JSONObject();
			commonObj = new CommonDao();
			Map sessionMap = ActionContext.getContext().getSession();
			
			if(functionid==null ){					
				if((functionid==null)  && sessionMap.get("crntfunctionid")!=null){							
					String userunque=(String)sessionMap.get("crntfunctionid");
					functionid=userunque;
				}
			}else{
				sessionMap = ActionContext.getContext().getSession();
				sessionMap.put("crntfunctionid", String.valueOf(functionid));					
			}
			obj.put("functionid", functionid);
			data.put("servicecode", "SI330001");
			data.put("servicefor", "3");
			data.put("data", obj);
			data.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.function.functionselect");
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("Response : editttt ::::::::::::::::" + response);
			json = new JSONObject(response);
			JSONObject json_data = json.getJSONObject("data");
			ar = json_data.getJSONArray("functiondetails");
			JSONObject jsonList = new JSONObject();
			String functionname = null, action = "", splitval = "", status = null;
			int indx = 0;
			for (int i = 0; i < ar.length(); i++) {
				jsonList = null;
				jsonList = ar.getJSONObject(i);
				functionid = jsonList.getString("functionid");
				funname = jsonList.getString("functionname");
				functiontype = jsonList.getString("functiontype");
				functiontext = jsonList.getJSONArray("eventemp");
				for (int j = 0; j < functiontext.length(); j++) {
					JSONArray temm = null;
					temm = functiontext.getJSONArray(j);
					indx = (Integer) temm.get(0);
					splitval = (String) temm.get(1);
					System.out.println(indx + "----" + splitval);
					funList.add(new FunctionMgmtTblVO(null, null, null,splitval, indx, null));
					splitval = "";
				}
				System.out.println("funList ----------- " + funList.size());
			}
		} catch (Exception ex) {

		}
		return SUCCESS;
	}

	public String deActiveFunction() {
		JSONObject obj = null;
		JSONObject data = null;
		Common commonObj = null;
		JSONArray functiontext = null;
		JSONObject json = null;
		JSONArray ar = null;

		FunctionMgmtTblVO funobj = null;
		try {
			funobj = new FunctionMgmtTblVO(null, null, null, null, 0, null);
			obj = new JSONObject();
			data = new JSONObject();
			commonObj = new CommonDao();
			Map sessionMap = ActionContext.getContext().getSession();
			obj.put("functionid", functionid);
			obj.put("statusflg", statusflg);
			data.put("servicecode", "SI330004");
			data.put("servicefor", "4");
			data.put("data", obj);
			data.put("currentloginid", String.valueOf(sessionMap.get("USERID")));
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.function.functiondeactive");
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("Response : deactive ::::::::::::::::"+ response);
			json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			if (statusCode.equalsIgnoreCase("0")) {
				alert.setCls("success");
				alert.setMsg(getText("Deactive successfully."));
				alertList.add(alert);
				return "success";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Error ."));
				alertList.add(alert);
				return "input";
			}

		} catch (Exception ex) {
			System.out.println("Exception ----deactive--" + ex);
		}
		return SUCCESS;
	}

	public String getFunname() {
		return funname;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}

	public String[] getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String[] functionname) {
		this.functionname = functionname;
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

	public String getFunctionid() {
		return functionid;
	}

	public void setFunctionid(String functionid) {
		this.functionid = functionid;
	}

	public String getFunctiontxtname() {
		return functiontxtname;
	}

	public void setFunctiontxtname(String functiontxtname) {
		this.functiontxtname = functiontxtname;
	}

	public List<FunctionMgmtTblVO> getFunList() {
		return funList;
	}

	public void setFunList(List<FunctionMgmtTblVO> funList) {
		this.funList = funList;
	}

	public String getStatusflg() {
		return statusflg;
	}

	public void setStatusflg(String statusflg) {
		this.statusflg = statusflg;
	}

	public String[] getFunctiontxttxtid() {
		return functiontxttxtid;
	}

	public void setFunctiontxttxtid(String[] functiontxttxtid) {
		this.functiontxttxtid = functiontxttxtid;
	}

	public String[] getFunctionnametxt() {
		return functionnametxt;
	}

	public void setFunctionnametxt(String[] functionnametxt) {
		this.functionnametxt = functionnametxt;
	}

	public String getFunctiontype() {
		return functiontype;
	}

	public void setFunctiontype(String functiontype) {
		this.functiontype = functiontype;
	}


	

}
