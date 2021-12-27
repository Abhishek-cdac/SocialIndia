package com.socialindia.signup;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.common.commomServices;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class GetdashboardChartData extends ActionSupport implements
		Serializable {
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	commomServices commonSnippet = new commomServices();
	private Common commonObj = new CommonDao();

	private String townshipDetail;
	private String societyDetail;
	private String flatDetail;
	private String ivrFlgtwon;
	private String ivrGrpTbl;
	public String execute() {
		JSONObject json = null;
		JSONObject respData = null;
		try {
			Map sessionMap = ActionContext.getContext().getSession();
			data.put("servicecode", "SI6433");
			data.put("currentloginid", sessionMap.get("USERID"));

			obj.put("userId", sessionMap.get("USERID"));
			obj.put("usrTyp", sessionMap.get("GROUPCODE"));
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("socialindia.dashboard.getdashboardchartdetails");
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("Dashboard response : " + response);
			json = new JSONObject(response);
			respData = (JSONObject)Commonutility.toHasChkJsonRtnValObj(json, "data");
			
			String dashboardDteail =(String) Commonutility.toHasChkJsonRtnValObj(respData,"dashboardDteail");
			if (dashboardDteail != null && !dashboardDteail.equalsIgnoreCase("null") && !dashboardDteail.equalsIgnoreCase("")) {
				if (dashboardDteail.contains("<DEL>")) {
					String[] dashdetail = dashboardDteail.split("<DEL>");
					ivrFlgtwon = dashdetail[0];
					townshipDetail = dashdetail[1];
					societyDetail = dashdetail[2];
					if (ivrFlgtwon != null && ivrFlgtwon.equalsIgnoreCase("TOWN")) {
						flatDetail = dashdetail[3];	
						if(townshipDetail!=null && townshipDetail.contains("!_!")){
							String tblName[] = townshipDetail.split("!_!");
							String sctyCnt[] = societyDetail.split("!_!");
							String rstCnt[] = flatDetail.split("!_!");
							String tblFrmm="<thead> <tr> <th data-field=\"id\">S.No.</th> <th data-field=\"month\">Township Name</th> <th data-field=\"item-sold\">No. Of Society</th><th data-field=\"item-price\">No. Of Resident</th>  </tr> </thead>";
							int lvrSno =1;
							for(int i=0;i<tblName.length;i++){
								tblFrmm+="<tr><td>"+lvrSno+"</td><td>"+tblName[i]+"</td><td>"+sctyCnt[i]+"</td><td>"+rstCnt[i]+"</td></tr>";
								lvrSno++;
							}
							ivrGrpTbl=tblFrmm;
						}else{
							ivrGrpTbl="No Data Found";
						}
						
					} else if (ivrFlgtwon != null && ivrFlgtwon.equalsIgnoreCase("SOCY")) { 
						if(townshipDetail!=null && townshipDetail.contains("!_!")){
							String tblName[] = townshipDetail.split("!_!");
							String sctyCnt[] = societyDetail.split("!_!");
							//String rstCnt[] = flatDetail.split("!_!");
							String tblFrmm="<thead> <tr> <th data-field=\"id\">S.No.</th> <th data-field=\"month\">Society Name</th> <th data-field=\"item-price\">No. Of Resident</th>  </tr> </thead>";
							int lvrSno =1;
							for(int i=0;i<tblName.length;i++){
								tblFrmm+="<tr><td>"+lvrSno+"</td><td>"+tblName[i]+"</td><td>"+sctyCnt[i]+"</td></tr>";
								lvrSno++;
							}
							ivrGrpTbl=tblFrmm;
						}else{
							ivrGrpTbl="No Data Found";
						}
						flatDetail = "";
					}else {
						
						
					}
																						
				} else {
					townshipDetail = "Society1!_!Society2!_!Society3!_!Society4!_!Society5!_!Society6!_!Society7";
					societyDetail = "60!_!20!_!90!_!80!_!50!_!85!_!40";
					flatDetail = "100!_!50!_!20!_!40!_!80!_!50!_!80";					
				}

			} else {
				townshipDetail = "Society1!_!Society2!_!Society3!_!Society4!_!Society5!_!Society6!_!Society7";
				societyDetail = "60!_!20!_!90!_!80!_!50!_!85!_!40";
				flatDetail = "100!_!50!_!20!_!40!_!80!_!50!_!80";
			}
			System.out.println("townshipDetail : " + townshipDetail);
			System.out.println("societyDetail : " + societyDetail);
			System.out.println("flatDetail : " + flatDetail);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			json = null;
			respData = null;
		}
		return SUCCESS;
	}

	
	public String getIvrGrpTbl() {
		return ivrGrpTbl;
	}


	public void setIvrGrpTbl(String ivrGrpTbl) {
		this.ivrGrpTbl = ivrGrpTbl;
	}


	public String getIvrFlgtwon() {
		return ivrFlgtwon;
	}


	public void setIvrFlgtwon(String ivrFlgtwon) {
		this.ivrFlgtwon = ivrFlgtwon;
	}


	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public String getTownshipDetail() {
		return townshipDetail;
	}

	public void setTownshipDetail(String townshipDetail) {
		this.townshipDetail = townshipDetail;
	}

	public String getSocietyDetail() {
		return societyDetail;
	}

	public void setSocietyDetail(String societyDetail) {
		this.societyDetail = societyDetail;
	}

	public String getFlatDetail() {
		return flatDetail;
	}

	public void setFlatDetail(String flatDetail) {
		this.flatDetail = flatDetail;
	}

}
