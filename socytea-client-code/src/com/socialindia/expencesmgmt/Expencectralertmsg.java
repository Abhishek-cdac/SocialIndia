package com.socialindia.expencesmgmt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;

public class Expencectralertmsg extends ActionSupport{
	/**
	 * This class for avoid refresh time insert - multi time insert avoid
	 */
	private static final long serialVersionUID = 1L;
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	public String execute() {		
		Map sessionMap = ActionContext.getContext().getSession();				
		alertList = (List<AlertVo>) sessionMap.get("alertList");
		Commonutility.toWriteConsole(alertList);		
		return SUCCESS;
	}
	public List<AlertVo> getAlertList() {
		return alertList;
	}
	public void setAlertList(List<AlertVo> alertList) {
		this.alertList = alertList;
	}

}
