package com.socialindia.login;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.MenuMasterTblVo;
import com.letspay.uam.persistense.TownshipMstTbl;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


import org.apache.struts2.ServletActionContext;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.ServletContext;

public class DefaultRedirectAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	List<MenuMasterTblVo> menuList = new ArrayList<MenuMasterTblVo>();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	private List<TownshipMstTbl> townshiplist = new ArrayList<TownshipMstTbl>();
	JSONObject json = new JSONObject();

	@Override
	public String execute() {
		try {
			//if (context.getAttribute("MENUTYP") == null) {
			ServletContext context = ServletActionContext.getServletContext();
				System.out.println("Enter to login page");
				json.put("menuList", "11");
				String jsonTextFinal = json.toString();
				String temp = "paramval=" + URLEncoder.encode(jsonTextFinal);				
				String finalUrl = getText("menuMaster.load.full.list");				
				String response = commonObj.jsonRequest(finalUrl, temp);				
				 json = new JSONObject(response);
				String statusCode = (String) json.get("statuscode");
				JSONArray ar = null;
				JSONArray ar1 = null;
				JSONObject json_data = new JSONObject();
				if (statusCode.equalsIgnoreCase("E0001")) {
					json_data = json.getJSONObject("data");
					ar = json_data.getJSONArray("allMenuList");					
					JSONObject jsonList = new JSONObject();

					for (int i = 0; i < ar.length(); i++) {
						jsonList = null;
						jsonList = ar.getJSONObject(i);
						String menuType = jsonList.getString("menuType");
						String rootDesc = jsonList.getString("rootDesc");
						//String menuPath = jsonList.getString("menuPath");
						String menuDesc = jsonList.getString("menuDesc"); 
						String menuClass = "";
						if(jsonList.has("menuClass")){
	    					menuClass = jsonList.getString("menuClass");
	    				}else{
	    					menuClass = "";
	    				}    			
						int menuID = jsonList.getInt("menuId");						
						menuList.add(new MenuMasterTblVo(menuID, menuDesc, menuDesc, menuType, rootDesc,menuClass));
					}
					ar1 = json_data.getJSONArray("townshipdetail");
					for(int i=0;i<ar1.length();i++)
					{							
						jsonList=null;
						jsonList=ar1.getJSONObject(i);
						int townshipuniid=jsonList.getInt("townshipuniid");
						String townshipname=jsonList.getString("townshipname");
						
						townshiplist.add(new TownshipMstTbl(townshipuniid,townshipname,null,null,null,null));
					}
					ActionContext.getContext().getSession().put("TOWNSHIPLIST", townshiplist);					
					context.setAttribute("MENUTYP", menuList);					
					
				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Text.customerreg.key.error"));
					alertList.add(alert);
					return "input";
				}
			//}
		} catch (Exception ex) {
			System.out.println("Exception Found DefaultRedirectAction : "+ ex);
		}

		return SUCCESS;
	}

	public List<MenuMasterTblVo> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuMasterTblVo> menuList) {
		this.menuList = menuList;
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

}
