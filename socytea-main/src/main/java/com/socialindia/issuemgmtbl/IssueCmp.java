package com.socialindia.issuemgmtbl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class IssueCmp extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String mrchntId;
	 private List<String> issueFeatures;
	 private List<String> listissue;
	 private List<String> issuecomp;
	 private String[] listval;
	 private String groupflg;
	public String execute(){
		 JSONObject obj = null;
		 JSONObject data = null;
		 Common commonObj = null;
		 JSONObject json=null;
		 JSONArray ar =null;
		 Collection<String> different=null;
		 Collection<String> similar=null;
		 JSONArray issuedecss = null;
		 Map sessionMap = null;
		 try{
			obj = new JSONObject();
			data = new JSONObject();
			commonObj = new CommonDao();
			sessionMap = ActionContext.getContext().getSession();
			obj.put("mrchntId",mrchntId);	
			obj.put("grouptxtflg",groupflg);	
			data.put("servicecode", "SI350004");
			data.put("servicefor", "3");
			data.put("crntusrloginid",String.valueOf(sessionMap.get("USERID")));		
			data.put("data", obj);
			String jsonTextFinal = data.toString().trim();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);			
			String finalUrl = getText("socialindia.issue.selectissuecmp");
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("Response : deactive ::::::::::::::::" + response);
			json = new JSONObject(response);
			JSONObject dataobj=new JSONObject();
			dataobj=(JSONObject) Commonutility.toHasChkJsonRtnValObj(json,"data");	
			JSONArray issuedetails=dataobj.getJSONArray("issuedetails");
			 JSONObject jsonList = new JSONObject();
			 issueFeatures=new ArrayList<String>();
			 issuecomp=new ArrayList<String>();
			 for(int i=0;i<issuedetails.length();i++){
				 jsonList=null;
					jsonList=issuedetails.getJSONObject(i);
					String issuedes=jsonList.getString("issdesc");
					issuecomp.add(issuedes.trim());
					issuedecss=jsonList.getJSONArray("issuedesc");
					listissue=new ArrayList<String>();
					 for (int j = 0; j < issuedecss.length(); j++) {
						 JSONArray temm=null;			
		    					temm = issuedecss.getJSONArray(j);			    					
		    					int indx=(Integer)temm.get(0);			    					
		    					String splitval=(String)temm.get(1);	
		    				
		    					 if(splitval!=null && !splitval.equalsIgnoreCase("null") && !splitval.equalsIgnoreCase("")){
				    					listissue.add(splitval.trim());
				    					 splitval="";
							 }
			        }
					  similar = new HashSet<String>(issuecomp);
			          different = new HashSet<String>();
			          different.addAll(issuecomp);
			          different.addAll(listissue);
			          similar.retainAll(listissue);
			          different.removeAll(similar);
						
			 }
			 issueFeatures.addAll(different);
			 issueFeatures.addAll(similar);
			 System.out.println(different+"issueFeatures --------147852----- "+listissue);
			
		 }catch(Exception ex){
			 
		 }
		
		return SUCCESS;
	}
	public String getMrchntId() {
		return mrchntId;
	}
	public void setMrchntId(String mrchntId) {
		this.mrchntId = mrchntId;
	}
	public List<String> getIssueFeatures() {
		return issueFeatures;
	}
	public void setIssueFeatures(List<String> issueFeatures) {
		this.issueFeatures = issueFeatures;
	}
	public List<String> getListissue() {
		return listissue;
	}
	public void setListissue(List<String> listissue) {
		this.listissue = listissue;
	}
	public List<String> getIssuecomp() {
		return issuecomp;
	}
	public void setIssuecomp(List<String> issuecomp) {
		this.issuecomp = issuecomp;
	}
	public String[] getListval() {
		return listval;
	}
	public void setListval(String[] listval) {
		this.listval = listval;
	}
	public String getGroupflg() {
		return groupflg;
	}
	public void setGroupflg(String groupflg) {
		this.groupflg = groupflg;
	}
	
	
	

}
