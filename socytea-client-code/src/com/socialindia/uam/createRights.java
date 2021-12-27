package com.socialindia.uam;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.load.LoadServices;
import com.letspay.login.persistense.LoginDaoServices;
import com.letspay.login.persistense.LoginServices;
import com.letspay.login.persistense.UserMasterTblVo;
import com.letspay.uam.persistense.GroupMasterTblVo;
import com.letspay.uam.persistense.MenuMasterTblVo;
import com.letspay.uam.persistense.RightsMasterTblVo;
import com.letspay.utils.CommonUtils;
import com.letspay.utils.CommonUtilsDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;



import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class createRights extends ActionSupport {
	private RightsMasterTblVo rightsMast = new RightsMasterTblVo();
	 private MenuMasterTblVo menu = new MenuMasterTblVo(0, null, null, null, null,null);
	  private GroupMasterTblVo groupval = new GroupMasterTblVo(null, null, null, null,0);
	  CommonUtils common = new CommonUtilsDao();
	  JSONObject obj = new JSONObject();
	  private Common commonObj = new CommonDao();
	  AlertVo alert = new AlertVo();
	  private List<MenuMasterTblVo> rightsList = new ArrayList<MenuMasterTblVo>();
	  List<AlertVo> alertList = new ArrayList<AlertVo>();
  /**
   * Rights assigned for grouped users.
   */
	private int groupCode;
  public String execute() {
    try{
    	 boolean result = false;
	  HttpServletRequest request = ServletActionContext.getRequest();
	  String[] menurights = request.getParameterValues("rights");    
      if (menurights == null) {
          addFieldError("groupCode", getText("Please Select Sub Menu"));
        } else {
          for (int i = 0; i < menurights.length; i++) {
            String string = menurights[i];            
          }
        }
      Date date = common.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
      if (groupCode > 0 && menurights.length > 0 && menurights != null) {          
          JSONArray jArray =new JSONArray();
          List<Object> tt= new ArrayList();
          JSONObject jsonObj=new JSONObject();
          JSONObject jsondata=new JSONObject();
          for (int j = 0; j < menurights.length; j++) {
            int mmid = Integer.parseInt(menurights[j]);
          
				JSONObject finalJson = new JSONObject();
				finalJson.put("menuID", mmid);
				tt.add(finalJson);
			finalJson=null;
          }
        
       
         jsondata.put("groupCode", groupCode);
         jsondata.put("servicecode", "SI0017");
         jsondata.put("allMenuID", tt);
			String jsonTextFinal = jsondata.toString();
			String temp="ivrparams="+ jsondata;
			System.out.println("=====jsonText=====" + jsonTextFinal);
			String finalUrl = getText("rightsmanagement.rights.creation");
			System.out.println("====finalUrl===="+finalUrl);
			/*String finalAction=rb.getString("signup.action.admin.activekey.check");
			System.out.println("========finalAction==="+finalAction);*/
			JSONArray ar =new JSONArray();
			String response=commonObj.jsonRequest(finalUrl, temp);
			System.out.println("=========response==="+response);
			JSONObject json = new JSONObject(response);
			 JSONObject json_data = json.getJSONObject("data");
			 ar = json_data.getJSONArray("menu");
				System.out.println("==============ar==="+ar.length());
				JSONObject jsonList = new JSONObject();
				boolean resultFlag =json_data.getBoolean("resultFlag");
				for(int i=0;i<ar.length();i++)
				{	
					jsonList=null;
					jsonList=ar.getJSONObject(i);
					String menuType=jsonList.getString("menuType");
					String rootDesc=jsonList.getString("rootDesc");
					String menuPath=jsonList.getString("menuPath");
					String menuDesc=jsonList.getString("menuDesc");
					int menuID=jsonList.getInt("menuID");
					String menuClass = "";
					if(jsonList.has("menuClass")){
    					menuClass = jsonList.getString("menuClass");
    				}else{
    					menuClass = "";
    				} 
					rightsList.add(new MenuMasterTblVo(menuID,menuDesc, menuPath, menuType, rootDesc,menuClass));
				}			
				if(groupCode==1 || groupCode==2){
				ActionContext.getContext().getSession().put("RIGHTSLST", rightsList);
				}
					
           /* rightsMast.setEntryBy(1);
            rightsMast.setRightsAdd(1);
            rightsMast.setRightsEdit(1);
            rightsMast.setRightsDelete(1);
            rightsMast.setRightsView(1);
            rightsMast.setEntryDatetime(date);
            rightsMast.setModifyDatetime(date);*/
           // System.out.println("rightsMast===" + rightsMast.getMenuId());
          //  result = uamDeatils.insertGroupRights(rightsMast);
            
				if (resultFlag == true) {
					alert.setCls("success");
					alert.setMsg(getText("Error.rights.set.success"));
					alertList.add(alert);
				}
          } else {
              if (groupCode > 0) {
                  /**
                   * Selected group.
                   */
                } else {
                  addFieldError("rightsMast.groupCode.groupCode", getText("Please select group"));
                }
                if (menurights != null && menurights.length > 0) {
                  /**
                   * Menu rights not empty
                   */
                } else {
                  addFieldError("SelectRights", getText("Please select rights"));
                }
                result = false;
              }
     
      
    }catch(Exception ex){
    	System.out.println("===createRights====Exception==========="+ex);
    }
      return SUCCESS;
    }
public RightsMasterTblVo getRightsMast() {
	return rightsMast;
}
public void setRightsMast(RightsMasterTblVo rightsMast) {
	this.rightsMast = rightsMast;
}
public int getGroupCode() {
	return groupCode;
}
public void setGroupCode(int groupCode) {
	this.groupCode = groupCode;
}
public MenuMasterTblVo getMenu() {
	return menu;
}
public void setMenu(MenuMasterTblVo menu) {
	this.menu = menu;
}
public GroupMasterTblVo getGroupval() {
	return groupval;
}
public void setGroupval(GroupMasterTblVo groupval) {
	this.groupval = groupval;
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
public List<MenuMasterTblVo> getRightsList() {
	return rightsList;
}
public void setRightsList(List<MenuMasterTblVo> rightsList) {
	this.rightsList = rightsList;
} 


}
