package com.socialindia.common;

import java.net.URLEncoder;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.login.EncDecrypt;

public class EncryptDecryptReport extends ActionSupport{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String fromdate;
		private String todate;
		private Integer sSoctyId;
		private Integer sTowshipId;
		private String selectid;
		private String startamount;
		private String endamount;
		private String transtype;
		String ipvParams="";
		
		public String urlEncodedString(){
			Map sessionMap =null;
			String lcsocitid=null;
			try{
				sessionMap = ActionContext.getContext().getSession();
				lcsocitid = String.valueOf(sessionMap.get("sSoctyId"));
				JSONObject data=new JSONObject();
				if(fromdate!=null){
					data.put("fromdate", fromdate);
				}else{
					data.put("fromdate", "");
				}
				if(todate!=null){
					data.put("todate", todate);
				}else{
					data.put("todate", "");
				}
				if(sSoctyId!=null){
					data.put("sSoctyId", sSoctyId);
				}else{
					data.put("sSoctyId",lcsocitid);
				}
				if(sTowshipId!=null){
					data.put("sTowshipId", sTowshipId);
				}else{
					data.put("sTowshipId",-1);
				}
				data.put("startamount", startamount);
				data.put("endamount", endamount);
				data.put("selectid", selectid);
				if(transtype!=null && !transtype.equalsIgnoreCase("null") && !transtype.equalsIgnoreCase("")){
					data.put("transtype", transtype);
				}else{
					data.put("transtype", "");
				}
				
				String jsonTextFinal = data.toString();
				jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
				ipvParams = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				
			}catch  (Exception e){
				e.printStackTrace();
			}
			return SUCCESS;
		}

		public String getFromdate() {
			return fromdate;
		}

		public void setFromdate(String fromdate) {
			this.fromdate = fromdate;
		}

		public String getTodate() {
			return todate;
		}

		public void setTodate(String todate) {
			this.todate = todate;
		}

		public Integer getsSoctyId() {
			return sSoctyId;
		}

		public void setsSoctyId(Integer sSoctyId) {
			this.sSoctyId = sSoctyId;
		}

		public Integer getsTowshipId() {
			return sTowshipId;
		}

		public void setsTowshipId(Integer sTowshipId) {
			this.sTowshipId = sTowshipId;
		}

		public String getSelectid() {
			return selectid;
		}

		public void setSelectid(String selectid) {
			this.selectid = selectid;
		}

		public String getStartamount() {
			return startamount;
		}

		public void setStartamount(String startamount) {
			this.startamount = startamount;
		}

		public String getEndamount() {
			return endamount;
		}

		public void setEndamount(String endamount) {
			this.endamount = endamount;
		}

		public String getIpvParams() {
			return ipvParams;
		}

		public void setIpvParams(String ipvParams) {
			this.ipvParams = ipvParams;
		}

		public String getTranstype() {
			return transtype;
		}

		public void setTranstype(String transtype) {
			this.transtype = transtype;
		}
		
		
}