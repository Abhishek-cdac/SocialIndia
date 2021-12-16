package com.socialindia.common;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionSupport;

public class Societyprelog extends ActionSupport{ 
	private static final long serialVersionUID = 1L;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private String toStringval;
	public String execute(){
		//String response="{\"respcode\":\"0\"}";
		 JSONObject lvrRcvrespjsonobj = null;
		try{
		lvrRcvrespjsonobj = new JSONObject(toStringval);
		String statusCode =lvrRcvrespjsonobj.getString("servicecode");
		String message =lvrRcvrespjsonobj.getString("message");
		if (statusCode.equalsIgnoreCase("00")) {
            alert.setCls("success");
            alert.setMsg(message);
            alertList.add(alert);
            return "success";
		} else {
            alert.setCls("danger");
            alert.setMsg(message);
            alertList.add(alert);
            return "input";
          }
		}catch(Exception ex){
			
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
	public String getToStringval() {
		return toStringval;
	}
	public void setToStringval(String toStringval) {
		this.toStringval = toStringval;
	}
	
	

}
