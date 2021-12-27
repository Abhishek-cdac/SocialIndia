package com.letspay.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.letspay.load.LoadDaoServices;
import com.letspay.load.LoadServices;
import com.letspay.uam.persistense.GroupMasterTblVo;
import com.letspay.uam.persistense.RightsMasterTblVo;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 4614410521339579694L;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	LoadServices loadservic = new LoadDaoServices();
	List<GroupMasterTblVo> groupList = new ArrayList<GroupMasterTblVo>();
	// UamServices uam = new UamDaoServices();
	private List<RightsMasterTblVo> rightsList = new ArrayList<RightsMasterTblVo>();

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Map session = ac.getSession();

		Integer userInfo = (Integer) session.get("USERID");
		// if the user object is non null, the user is logged in.
		if (userInfo != null) {
			ServletContext context = ServletActionContext.getServletContext();
			Map sessionMap = ActionContext.getContext().getSession();
			String result = invocation.invoke();
			return result;

		} else {
			LogOut logout = new LogOut();
			logout.doLogout();
			alert.setCls("danger");
			alert.setMsg("Your session has expired. Please login again");
			alertList.add(alert);
			return "notLoggedIn";
		}

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

	public LoadServices getLoadservic() {
		return loadservic;
	}

	public void setLoadservic(LoadServices loadservic) {
		this.loadservic = loadservic;
	}

	public List<RightsMasterTblVo> getRightsList() {
		return rightsList;
	}

	public void setRightsList(List<RightsMasterTblVo> rightsList) {
		this.rightsList = rightsList;
	}

	public List<GroupMasterTblVo> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupMasterTblVo> groupList) {
		this.groupList = groupList;
	}

}
/*
 * else { LogOut logout = new LogOut(); logout.doLogout(); return
 * "notLoggedUserIn"; }
 */
// }

