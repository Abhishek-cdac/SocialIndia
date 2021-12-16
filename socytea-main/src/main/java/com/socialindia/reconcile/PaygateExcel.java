package com.socialindia.reconcile;

import java.io.File;
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
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class PaygateExcel extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private File payfile;
	private String payfileFileName;
	private File cyberfile;
	private String cyberfileFileName;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private String flg;
	private String paygatecyberId;
	public String execute() {
		Common commonObj =null;
		JSONObject obj = null;
		JSONObject data = null;
		Map sessionMap = null;
		String finalUrl=null;String jsonTextFinal =null;String response =null;String temp =null;
		try{
			System.out.println("Step 1: Enter the PaygateExcel upload class files:::::::::----"+payfileFileName);
			obj = new JSONObject();
			data = new JSONObject();
			sessionMap = ActionContext.getContext().getSession();
			commonObj = new CommonDao();
			obj.put("payFileName", payfileFileName);
			if (payfile!=null) {
				obj.put("payFile", payfile.getAbsolutePath());
			} else {
				obj.put("payFile", "");
			}
			
			//System.out.println("Step 2: Enter the PaygateExcel upload class files:::::::::----"+payfile.getAbsolutePath());
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			data.put("servicecode", "SI40000");
			data.put("servicefor", "1");
			data.put("data", obj);
			jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.paygeta.fileupload.excel");
			response = commonObj.jsonRequest(finalUrl, temp);
			
		}catch(Exception ex){
			System.out.println("PaygateExcel.class --------Exception-------- "+ex);
		}
		  return SUCCESS;
	  }
	public  String cyberPlateupload() {
		Common commonObj =null;
		JSONObject obj = null;
		JSONObject data = null;
		Map sessionMap = null;
		String finalUrl=null;String jsonTextFinal =null;String response =null;String temp =null;
		try{
			System.out.println("Step 1: Enter the cyberPlateupload upload class files:::::::::----"+cyberfileFileName);
			obj = new JSONObject();
			data = new JSONObject();
			sessionMap = ActionContext.getContext().getSession();
			commonObj = new CommonDao();
			obj.put("cyberFileName", cyberfileFileName);
			if (cyberfile!=null){
				obj.put("cyberFile", cyberfile.getAbsolutePath());
			} else {
				obj.put("cyberFile", "");
			}
			
			//System.out.println("Step 2: Enter the cyberPlateupload upload class files:::::::::----"+cyberfile.getAbsolutePath());
			obj.put("entryby", String.valueOf(sessionMap.get("USERID")));
			data.put("servicecode", "SI40000");
			data.put("servicefor", "1");
			data.put("data", obj);
			jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			finalUrl = getText("socialindia.cyberplate.fileupload.excel");
			response = commonObj.jsonRequest(finalUrl, temp);
		}catch(Exception ex){
			System.out.println("PaygateExcel.class --cyberPlateupload()------Exception-------- "+ex);
		}
		return SUCCESS;
		
	}public String cyberandpaygateDelete() {
		Common commonObj =null;
		JSONObject obj = null;
		JSONObject data = null;
		Map sessionMap = null;
		Commonutility.toWriteConsole(flg+"------- cyberandpaygateDelete Delete -------1111-----------------"+paygatecyberId);
		try{
			data=new JSONObject(); 
			obj=new JSONObject(); 
			commonObj = new CommonDao();
			sessionMap = ActionContext.getContext().getSession();
			obj.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));
			obj.put("deleteid", paygatecyberId);
			obj.put("flg", flg);
			data.put("servicecode", "SI8003");		
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal);
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);	
			String finalUrl =getText("socialindia.paycyber.deleteaction");
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
			return "success";
			} else {
				alert.setCls("danger");
				if(Commonutility.checkempty(lvrRspmsg)){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("error.delete.event"));
				}
				alertList.add(alert);
				return "input";
			}
		
	} catch (Exception ex) {
		Commonutility.toWriteConsole("Step -1: Exception found in "+getClass().getSimpleName()+".calss cyberandpaygateDelete() : "+ex);
	}
	
return SUCCESS;	

	}
	
	public File getPayfile() {
		return payfile;
	}
	public void setPayfile(File payfile) {
		this.payfile = payfile;
	}
	public String getPayfileFileName() {
		return payfileFileName;
	}
	public void setPayfileFileName(String payfileFileName) {
		this.payfileFileName = payfileFileName;
	}
	public File getCyberfile() {
		return cyberfile;
	}
	public void setCyberfile(File cyberfile) {
		this.cyberfile = cyberfile;
	}
	public String getCyberfileFileName() {
		return cyberfileFileName;
	}
	public void setCyberfileFileName(String cyberfileFileName) {
		this.cyberfileFileName = cyberfileFileName;
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
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	public String getPaygatecyberId() {
		return paygatecyberId;
	}
	public void setPaygatecyberId(String paygatecyberId) {
		this.paygatecyberId = paygatecyberId;
	}

	
	
}
