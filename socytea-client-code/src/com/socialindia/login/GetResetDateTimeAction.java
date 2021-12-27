package com.socialindia.login;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;

public class GetResetDateTimeAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	JSONObject obj = null;
	protected HttpServletRequest servletRequest;
	protected HttpServletResponse servletResponse;

	/**
	 * Login.
	 */
	@Override
	public String execute() {
		
		JSONObject json_data = new JSONObject();
		String response = null;
		try {
			obj = new JSONObject();
			obj.put("servicecode", "SI0000");
			obj.put("userid", servletRequest.getParameter("userid"));
			
			Commonutility.toWriteConsole("Step 1 : GetResetDateTimeAction Called."+servletRequest.getParameter("userid"));
			
			String jsonTextFinal = obj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);

			// start: getresetdatetime
			String finalUrl = getText("login.user.getresetdatetime.action");
			Commonutility.toWriteConsole("Step 3 : Service Url : " + finalUrl);

			CommonDao commonObj = new CommonDao();
			response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Step 4 : GetResetDateTimeAction Service Response :"+ response);

			JSONObject json = new JSONObject(response);
			json_data = json.getJSONObject("data");

			String resetDatetime = (String) Commonutility.toHasChkJsonRtnValObj(json_data, "resetDatetime");
			Commonutility.toWriteConsole("Step 4 : GetResetDateTimeAction  resetDatetime:" + resetDatetime);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("resetDatetime", resetDatetime);
			
			serverResponse("SI0000", "0000", "0", "sucess", jsonObj);
			
			
		} catch (Exception ex) {
			Commonutility.toWriteConsole("Step -1 : GetResetDateTimeAction - [Database Connection Not Found] : "	+ ex);
		} finally {
			json_data = null;
		}
		return SUCCESS;
	}
	
	private void serverResponse(String serviceCode, String statusCode,
			String respCode, String message, JSONObject dataJson)
			throws Exception {
		PrintWriter out;
		JSONObject responseMsg = new JSONObject();
		HttpServletResponse response;
		response = ServletActionContext.getResponse();
		out = response.getWriter();
		try {
			responseMsg = new JSONObject();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			responseMsg.put("servicecode", serviceCode);
			responseMsg.put("statuscode", statusCode);
			responseMsg.put("respcode", respCode);
			responseMsg.put("message", message);
			responseMsg.put("data", dataJson);
			String as = responseMsg.toString();
			out.print(as);
			out.close();
		} catch (Exception ex) {
			out = response.getWriter();
			out.print("{\"servicecode\":\"" + serviceCode + "\",");
			out.print("{\"statuscode\":\"2\",");
			out.print("{\"respcode\":\"E0002\",");
			out.print("{\"message\":\"Sorry, an unhandled error occurred\",");
			out.print("{\"data\":\"{}\"}");
			out.close();
			ex.printStackTrace();
		}
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.servletResponse = arg0;

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.servletRequest = arg0;
	}
}