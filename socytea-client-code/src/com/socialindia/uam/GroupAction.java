package com.socialindia.uam;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.GroupMasterTblVo;
import com.letspay.utils.CommonUtils;
import com.letspay.utils.CommonUtilsDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;


/**
 * Loading all groups on page load.
 */
public class GroupAction extends ActionSupport {

  private static final long serialVersionUID = 1L;
  List<GroupMasterTblVo> groupList = new ArrayList<GroupMasterTblVo>();
  private Common commonObj = new CommonDao();
  JSONObject obj = new JSONObject();
  private GroupMasterTblVo groupmang = new GroupMasterTblVo(null, null, null, null,0);
  AlertVo alert = new AlertVo();
  CommonUtils common = new CommonUtilsDao();
  List<AlertVo> alertList = new ArrayList<AlertVo>();
  private int menuid;
  private Integer deletegroupid;
  private String spli;
  private Integer hiddengroupid;
  private String NewgroupName;
  /**
   * show all groups.
   */
  public String execute() {
   
    try {
    	Commonutility.toWriteConsole("Step 1 : Enter GroupAction.class");
    	obj.put("servicecode", "SI0015");
    	obj.put("groupList", "");
        String jsonTextFinal = obj.toString();
        String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);        
        String finalUrl = getText("groupMaster.load.full.list");  
        Commonutility.toWriteConsole("Step 2 : Group Service URL : "+finalUrl);
        String response = commonObj.jsonRequest(finalUrl, temp);
        Commonutility.toWriteConsole("Step 3 : Group Response : "+response);
        JSONObject json = new JSONObject(response);
        JSONObject json_data = json.getJSONObject("data");
        JSONArray ar = null;
        ar = json_data.getJSONArray("groupMgmt");
        JSONObject jsonList = new JSONObject();
        for (int i = 0; i < ar.length(); i++) {
          jsonList = null;
          jsonList = ar.getJSONObject(i);
          String groupname = jsonList.getString("groupname");
          String groupcode = jsonList.getString("groupcode");
          String status = jsonList.getString("statusflg");
          String entrydate = jsonList.getString("entrydate");        
          groupList.add(new GroupMasterTblVo(groupname, groupcode, status, entrydate,0));
              
        }        
        Commonutility.toWriteConsole("Step 4 : Group Action End.");
      ActionContext.getContext().getSession().put("groupMasterList", groupList);
    } catch (Exception ex) {
      ex.printStackTrace();
      Commonutility.toWriteConsole("Step -1 : Exception Found in GroupAction.class execute() : " + ex);
    }

    return SUCCESS;
  }

  /**
   * Assign rights to the specific group.
   */
  public String getAssignedRights() {
    try {    	
    	obj.put("servicecode", "SI0016");
    	obj.put("menuId", menuid);

		String jsonTextFinal = obj.toString();
		String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);		
		String finalUrl = getText("userrights.for.rights.menu");
		String response = commonObj.jsonRequest(finalUrl, temp);
		JSONObject json = new JSONObject(response);
		String statusCode = (String) json.get("statuscode");
		JSONObject json_data = new JSONObject();
		String respCode = (String) json.get("respcode");
		if (statusCode.equalsIgnoreCase("0") && respCode.equalsIgnoreCase("0000")) {		
			json_data = json.getJSONObject("data");
			String aa = json_data.getString("groupname");
		      String repl = aa.replace("'", "");
		      spli = repl.replaceAll(",", "||");
			} else {
			alert.setCls("danger");
			alert.setMsg(getText("Text.customerreg.key.error"));
			alertList.add(alert);
			return "input";
			}


    
    } catch (Exception ex) {
      Commonutility.toWriteConsole("Step -1 : Exception Found in GroupAction.class getAssignedRights() : " + ex);
    }
    return SUCCESS;
  }
  public String creategroup() {	 
		boolean result = false;
		String existGroup = "";
		Map sessionMap = ActionContext.getContext().getSession();
		try {
			Date date;
	       String query = "SELECT CASE WHEN COUNT(*)=0 THEN 'NEW' ELSE "
	          + "'ALREADY EXISTS' END FROM GroupMasterTblVo where groupName='"
	          + NewgroupName + "'";
	       	obj.put("servicecode", "SI0012");
	      	obj.put("query", query);
	        obj.put("groupName", NewgroupName);
	        String jsonTextFinal = obj.toString();
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("groupmanagement.create.user.group");
			String response=commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			result = json_data.getBoolean("resultFlag");	      
			String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			if (statusCode.equalsIgnoreCase("0") && respCode.equalsIgnoreCase("0000")) {
				if (NewgroupName == null) {
					addFieldError("groupmang.groupName", getText("Error.usercreate.groupname"));
				} else if (result == false) {				
					NewgroupName = "";
					alert.setCls("danger");
					alert.setMsg(getText("error.grpname.exists"));
					alertList.add(alert);
					sessionMap.put("alertList", alertList);
					return "input";
				} else {
					if (result == true) {
						NewgroupName = "";
						Commonutility.toWriteConsole("result-------- " + result);
						alert.setCls("success");
						alert.setMsg(getText("success.grpname.create"));
						alertList.add(alert);
						sessionMap.put("alertList", alertList);
						return "success";
					} else {
						alert.setCls("danger");
						alert.setMsg(getText("error.grpname.unhandle"));
						alertList.add(alert);
						sessionMap.put("alertList", alertList);
						return "input";
					}
				}
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Text.customerreg.key.error"));
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
				return "input";
			}
	    } catch (Exception ex) {
	    	Commonutility.toWriteConsole("Step -1 : Exception Found in GroupAction.class creategroup() : " + ex);
	    	alert.setCls("danger");
	    	alert.setMsg(getText("error.grpname.unhandle"));
	    	alertList.add(alert);
	    	sessionMap.put("alertList", alertList);
	    	return "error";
		} finally {

		}
	    return SUCCESS;
	  }
  public String deleteGroupAction() {	    
	    boolean result = false;	   
	    try {
	    /*  userDetail = (UserMasterTblVo) ActionContext.getContext().getSession()
	          .get("USERINFO");*/
	    	obj.put("servicecode", "SI0014");

	    	obj.put("deleteGrpId", deletegroupid);
	        String jsonTextFinal = obj.toString();
			String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
			Commonutility.toWriteConsole("=====jsonText=====" + jsonTextFinal);
			String finalUrl = getText("groupmanagement.delete.user.group");
			Commonutility.toWriteConsole("=======finalUrl===="+finalUrl);
			String response=commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("=========response==="+response);
			JSONObject json = new JSONObject(response);
			JSONObject json_data=json.getJSONObject("data");
			result = json_data.getBoolean("resultFlag");
			String statusCode = (String) json.get("statuscode");
			String respCode = (String) json.get("respcode");
			if (statusCode.equalsIgnoreCase("0") && respCode.equalsIgnoreCase("0000")) {

	      if (result == true) {
	        alert.setCls("success");
	        alert.setMsg(getText("Group deleted successfully."));
	        alertList.add(alert);
	        return "success";
	      } else {
	        alert.setCls("danger");
	        alert.setMsg(getText("Error while deleting in group."));
	        alertList.add(alert);
	        return "input";
	      }
			}
			else
			{
				alert.setCls("danger");
				alert.setMsg(getText("Text.customerreg.key.error"));
				alertList.add(alert);
				return "input";
			}


	    } catch (Exception ex) {
	    	Commonutility.toWriteConsole("Step -1 : Exception Found in GroupAction.class deleteGroupAction() : " + ex);
	      alert.setCls("danger");
	      alert.setMsg(getText("Error while deleting in group."));
	      alertList.add(alert);
	      return "error";
	    }
	  }
  
  public String editGroup() {
	    boolean result = false;
	    try {

	      Date date;
	    /*  userDetail = (UserMasterTblVo) ActionContext.getContext().getSession()
	          .get("USERINFO");*/
	      if (groupmang.getGroupName() == null) {
	        addFieldError("groupmang.groupName",
	            getText("Error.usercreate.groupname"));
	      } else {
	        if (groupmang.getGroupName() != null
	            && groupmang.getGroupName().length() > 0) {
	        	  obj.put("servicecode", "SI0013");
	        	obj.put("editGroupId", hiddengroupid);
	        	obj.put("groupName", groupmang.getGroupName());
		        String jsonTextFinal = obj.toString();
				String temp="ivrparams="+ URLEncoder.encode(jsonTextFinal);
				Commonutility.toWriteConsole("=====jsonText=====" + jsonTextFinal);
				String finalUrl = getText("groupmanagement.edit.user.group");
				Commonutility.toWriteConsole("=======finalUrl===="+finalUrl);
				String response=commonObj.jsonRequest(finalUrl, temp);
				Commonutility.toWriteConsole("=========response==="+response);
				JSONObject json = new JSONObject(response);
				JSONObject json_data=json.getJSONObject("data");
				result = json_data.getBoolean("resultFlag");
	      /*    Commonutility.toWriteConsole("userDetail login--------- "
	              + userDetail.getEntryBy());*/
	         // groupmang.setGroupCode(hiddengroupid);
	          //groupmang.setEntryBy(userDetail);
	      //    groupmang.setModifyDatetime(date);
	        //  result = uam.updateGroupData(groupmang);
				String statusCode = (String) json.get("statuscode");
				String respCode = (String) json.get("respcode");
				if (statusCode.equalsIgnoreCase("0") && respCode.equalsIgnoreCase("0000")) {
	          if (result == true) {
	            alert.setCls("success");
	            alert.setMsg(getText("Group updated successfully."));
	            alertList.add(alert);
	            return "success";
	          } else {
	            alert.setCls("danger");
	            alert.setMsg(getText("Error while updating in group."));
	            alertList.add(alert);
	            return "input";
	          }
				}
				else
				{
					alert.setCls("danger");
					alert.setMsg(getText("Text.customerreg.key.error"));
					alertList.add(alert);
					return "input";
				}
						
	        }
	      }
	    } catch (Exception ex) {
	      alert.setCls("danger");
	      alert.setMsg(getText("Error while updating in group."));
	      alertList.add(alert);
	      return "error";
	    }
	    return "success";
	  }


  public List<GroupMasterTblVo> getGroupList() {
    return groupList;
  }

  public void setGroupList(List<GroupMasterTblVo> groupList) {
    this.groupList = groupList;
  }

  public int getMenuid() {
    return menuid;
  }

  public void setMenuid(int menuid) {
    this.menuid = menuid;
  }

  public String getSpli() {
    return spli;
  }

  public void setSpli(String spli) {
    this.spli = spli;
  }

public GroupMasterTblVo getGroupmang() {
	return groupmang;
}

public void setGroupmang(GroupMasterTblVo groupmang) {
	this.groupmang = groupmang;
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

public Integer getDeletegroupid() {
	return deletegroupid;
}

public void setDeletegroupid(Integer deletegroupid) {
	this.deletegroupid = deletegroupid;
}

public Integer getHiddengroupid() {
	return hiddengroupid;
}

public void setHiddengroupid(Integer hiddengroupid) {
	this.hiddengroupid = hiddengroupid;
}

public String getNewgroupName() {
	return NewgroupName;
}

public void setNewgroupName(String newgroupName) {
	NewgroupName = newgroupName;
}


}
