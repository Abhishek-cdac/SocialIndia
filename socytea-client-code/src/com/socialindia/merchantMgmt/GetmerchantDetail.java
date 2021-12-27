package com.socialindia.merchantMgmt;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.vo.MerchantCategoryDetail;
import com.socialindia.vo.MerchantIssueTblVO;
import com.socialindia.vo.MerchantStockDetailTblVO;
import com.socialindia.vo.MerchantTblVO;

public class GetmerchantDetail   extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();
	private AlertVo alert = new AlertVo();
	private String[] listarr=null;
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	MerchantTblVO merchantobj=new MerchantTblVO();
	MerchantCategoryDetail merchantcatdetobj=new MerchantCategoryDetail();
	MerchantStockDetailTblVO merchantStockobj=new MerchantStockDetailTblVO();
	private List<MerchantStockDetailTblVO> merchantStockobjList = new ArrayList<MerchantStockDetailTblVO>();
	private List<MerchantCategoryDetail> merchantcatdetList = new ArrayList<MerchantCategoryDetail>();
	private String merchantid;
	private List<String> issueFeatures;
	private String issuetext;
	private List<MerchantIssueTblVO> issList = new ArrayList<MerchantIssueTblVO>();
	public String execute() {
		Integer lvrMrcid = null;
		Log logWrite = null;
		try{
			logWrite = new Log();
			Map sessionMap = ActionContext.getContext().getSession();
			if (merchantobj.getMrchntId()!=null) {
				merchantid = String.valueOf(merchantobj.getMrchntId());	
			}			
			Commonutility.toWriteConsole("Step 1 : Merchant  detail GetmerchantDetail.calss called [Start]");
			logWrite.logMessage("Step 1 : Merchant detail GetmerchantUtilDetail.calss called [Start]", "info", GetmerchantUtilDetail.class);
			//if ((merchantid == null || merchantid == "-1" || merchantid == 0) && sessionMap.get("currentsession_merchantid") != null) {
			if ((merchantid == null || merchantid.equalsIgnoreCase("-1") || merchantid.equalsIgnoreCase("0") || merchantid.equalsIgnoreCase("null") || merchantid.equalsIgnoreCase("")) && sessionMap.get("currentsession_merchantid") != null) {
					String stfiddd = (String) sessionMap.get("currentsession_merchantid");
					lvrMrcid = Integer.parseInt(stfiddd);
					merchantobj.setMrchntId(lvrMrcid);		
					merchantid  = stfiddd;					
			} else {				
				sessionMap = ActionContext.getContext().getSession();
				sessionMap.put("currentsession_merchantid", String.valueOf(merchantid));
				merchantobj.setMrchntId(Integer.parseInt(merchantid));				
				//merchantobj.setMrchntId(merchantid);
			}
			Commonutility.toWriteConsole("Step 2 : Merchant Offer detail GetmerchantDetail.calss Mercahnt  Id : "+merchantid);	
			logWrite.logMessage("Step 2 : Merchant Offer detail GetmerchantDetail.calss Mercahnt  Id : "+merchantid, "info", GetmerchantDetail.class);			
			data.put("servicecode", "SI6419");
			data.put("currentloginid", sessionMap.get("USERID"));			
			obj.put("userId", sessionMap.get("USERID"));
			obj.put("usrTyp", sessionMap.get("GROUPCODE"));			
			obj.put("mrchntId",Integer.parseInt(merchantid));
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);						
			String finalUrl = getText("socialindia.mrchantMng.getmerchantviewdetails");	
			Commonutility.toWriteConsole("Step 3 : Merchant  detail GetmerchantDetail.calss Service URL : " + finalUrl);
			logWrite.logMessage("Step 3 : Merchant detail GetmerchantDetail.calss Service URL : " + finalUrl, "info", GetmerchantDetail.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Step 4 : Merchant detail GetmerchantDetail.calss Service Response: " + response);
			logWrite.logMessage("Step 4 : Merchant detail GetmerchantDetail.calss Service Response: " + response, "info", GetmerchantDetail.class);
			JSONObject json = new JSONObject(response);
			JSONObject dataobj=new JSONObject();
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json,"statuscode");			
			dataobj=(JSONObject) Commonutility.toHasChkJsonRtnValObj(json,"data");			
			Integer merchantCategoryId = (Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"merchantCategoryId");//merchantCategoryidname
			String merchantCategoryIdname=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"merchantCategoryidname");
			String mrchntName=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mrchntName");
			String mrchntEmail=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mrchntEmail");
			String mrchntIsdCode=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mrchntIsdCode");
			String mrchntPhNo=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mrchntPhNo");
			String keyForSearch=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"keyForSearch");
			String storeLocation=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"storeLocation");
			String storeOpentime=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"storeOpentime");
			String storeClosetime=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"storeClosetime");
			String mrchntAdd1=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mrchntAdd1");
			String mrchntAdd2=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"mrchntAdd2");
			String countryName=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"countryName");
			Integer countryId=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"countryId");
			String stateName=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"stateName");
			Integer stateId=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"stateId");
			String cityName=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"cityName");
			Integer cityId=(Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"cityId");
			String pstlcodeDesc = (String) Commonutility.toHasChkJsonRtnValObj(dataobj,"pstlcodeDesc");
			Integer pstlcode = (Integer) Commonutility.toHasChkJsonRtnValObj(dataobj,"pstlId");
			String merchDescription=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"merchDescription");
			String website=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"website");
			String shopName=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"shopName");
			String storeImage=(String) Commonutility.toHasChkJsonRtnValObj(dataobj,"storeimage");
			
			JSONArray functiontext=dataobj.getJSONArray("issuedata");
			issueFeatures=new ArrayList<String>();
			String issval="";
			 for (int j = 0; j < functiontext.length(); j++) {
				 JSONArray temm=null;			
   					temm = functiontext.getJSONArray(j);			    					
   					int indx=(Integer)temm.get(0);			    					
   					String splitval=(String)temm.get(1);		
   					 issList.add(new MerchantIssueTblVO(indx,splitval));
   					issueFeatures.add((String)temm.get(1));	 
   					issval+=splitval+", ";
   					 splitval="";
	        }
			 issuetext=issval;
			merchantobj.setMerchantCategoryId(merchantCategoryId);
			merchantobj.setMerchantCategoryName(merchantCategoryIdname);
			
			merchantobj.setMrchntName(mrchntName);
			merchantobj.setMrchntEmail(mrchntEmail);
			merchantobj.setMrchntIsdCode(mrchntIsdCode);
			merchantobj.setMrchntPhNo(mrchntPhNo);
			merchantobj.setKeyForSearch(keyForSearch);
			merchantobj.setStoreLocation(storeLocation);
			merchantobj.setStoreOpentime(storeOpentime);
			merchantobj.setStoreClosetime(storeClosetime);
			merchantobj.setMrchntAdd1(mrchntAdd1);
			merchantobj.setMrchntAdd2(mrchntAdd2);
			merchantobj.setCountryName(countryName);
			if(countryId>0){merchantobj.setCountryId(countryId);}
			merchantobj.setStateName(stateName);
			if(stateId>0){merchantobj.setStateId(stateId);}
			merchantobj.setCityName(cityName);
			if(cityId>0){merchantobj.setCityId(cityId);}
			merchantobj.setPstlcodeDesc(pstlcodeDesc);
			if(pstlcode>0){merchantobj.setPstlcode(pstlcode);}
			merchantobj.setMerchDescription(merchDescription);
			merchantobj.setWebsite(website);
			merchantobj.setShopName(shopName);
			merchantobj.setStoreimage(storeImage);
			ActionContext.getContext().getSession().put("merchantProfileImage", merchantobj.getStoreimage());
			if( merchantobj.getMerchantCategoryId()==1){
				JSONObject merchStockarrayobj=new JSONObject();
				JSONArray merchStockarray=new JSONArray();
				merchStockarray=(JSONArray) Commonutility.toHasChkJsonRtnValObj(dataobj,"merchantStockData");
				for(int j=0;j<merchStockarray.length();j++){
					merchantcatdetobj=new MerchantCategoryDetail();
					merchantStockobj=new MerchantStockDetailTblVO();
					merchStockarrayobj=merchStockarray.getJSONObject(j);
					String typeName=(String) Commonutility.toHasChkJsonRtnValObj(merchStockarrayobj,"typeName");
					Integer quantity=(Integer) Commonutility.toHasChkJsonRtnValObj(merchStockarrayobj,"quantity");
					merchantStockobj.setTypeName(typeName);
					merchantStockobj.setQuantity(Integer.toString(quantity));
					merchantStockobjList.add(merchantStockobj);
					merchStockarrayobj=null;
				}
				merchStockarray=null;
			}else if( merchantobj.getMerchantCategoryId()==2){
				JSONObject merchStockarrayobj=new JSONObject();
				JSONArray merchStockarray=new JSONArray();
				merchStockarray=(JSONArray) Commonutility.toHasChkJsonRtnValObj(dataobj,"merchantStockData");
				for(int j=0;j<merchStockarray.length();j++){
					merchantcatdetobj=new MerchantCategoryDetail();
					merchantStockobj=new MerchantStockDetailTblVO();
					merchStockarrayobj=merchStockarray.getJSONObject(j);
					String typeName=merchStockarrayobj.getString("typeName");
					Integer quantity=(Integer) Commonutility.toHasChkJsonRtnValObj(merchStockarrayobj,"quantity");
					String power=merchStockarrayobj.getString("power");
					String companyName=merchStockarrayobj.getString("companyName");
					merchantStockobj.setTypeName(typeName);
					merchantStockobj.setQuantity(Integer.toString(quantity));
					merchantStockobj.setPower(power);
					merchantStockobj.setCompanyName(companyName);
					merchantStockobjList.add(merchantStockobj);
					merchStockarrayobj=null;
				}
				merchStockarray=null;
			}else if( merchantobj.getMerchantCategoryId()==3){
				JSONObject merchStockarrayobj=new JSONObject();
				JSONArray merchStockarray=new JSONArray();
				merchStockarray=(JSONArray) Commonutility.toHasChkJsonRtnValObj(dataobj,"merchantStockData");
				for(int j=0;j<merchStockarray.length();j++){
					merchantcatdetobj=new MerchantCategoryDetail();
					merchantStockobj=new MerchantStockDetailTblVO();
					merchStockarrayobj=merchStockarray.getJSONObject(j);
					String typeName=merchStockarrayobj.getString("typeName");
					Integer quantity=(Integer) Commonutility.toHasChkJsonRtnValObj(merchStockarrayobj,"quantity");
					merchantStockobj.setTypeName(typeName);
					merchantStockobj.setQuantity(Integer.toString(quantity));
					merchantStockobjList.add(merchantStockobj);
					merchStockarrayobj=null;
				}
				merchStockarray=null;
			}else if( merchantobj.getMerchantCategoryId()==4){
				JSONObject merchStockobj=new JSONObject();
				merchantcatdetobj=new MerchantCategoryDetail();
				merchStockobj=(JSONObject) Commonutility.toHasChkJsonRtnValObj(dataobj,"merchantStockData");
				String cuisines=(String) Commonutility.toHasChkJsonRtnValObj(merchStockobj,"cuisines");
				String typeName=(String) Commonutility.toHasChkJsonRtnValObj(merchStockobj,"typeName");
				merchantStockobj.setCuisines(cuisines);
				merchantcatdetobj.setCuisines(cuisines);
				if(typeName!=null && typeName.contains("!_!")){
					String[] typenameArray=typeName.split("!_!");
					for(int k=0;k<typenameArray.length;k++){						
						if(typenameArray[k]!=null && typenameArray[k].contains("=")){
							String[] restTyp=typenameArray[k].split("=");
							if(restTyp.length>=1){
							if(restTyp[0].equalsIgnoreCase("breakFast")){								
								merchantcatdetobj.setBreakFast(Integer.parseInt(restTyp[1]));
							}else if(restTyp[0].equalsIgnoreCase("lunch")){
								merchantcatdetobj.setLunch(Integer.parseInt(restTyp[1]));
							}else if(restTyp[0].equalsIgnoreCase("dinner")){
								merchantcatdetobj.setDinner(Integer.parseInt(restTyp[1]));
							}else if(restTyp[0].equalsIgnoreCase("indoor")){
								merchantcatdetobj.setIndoor(Integer.parseInt(restTyp[1]));
							}else if(restTyp[0].equalsIgnoreCase("delivery")){
								merchantcatdetobj.setDelivery(Integer.parseInt(restTyp[1]));
							}else if(restTyp[0].equalsIgnoreCase("takeAway")){
								merchantcatdetobj.setTakeAway(Integer.parseInt(restTyp[1]));
							}else if(restTyp[0].equalsIgnoreCase("cafe")){
								merchantcatdetobj.setCafe(Integer.parseInt(restTyp[1]));
							}else if(restTyp[0].equalsIgnoreCase("luxuryDining")){
								merchantcatdetobj.setLuxuryDining(Integer.parseInt(restTyp[1]));
							}else if(restTyp[0].equalsIgnoreCase("nightlife")){
								merchantcatdetobj.setNightlife(Integer.parseInt(restTyp[1]));
							}
							}
						}
					}
				}
				merchStockobj=null;
				
			} else {
				
			}
			 if(statusCode.equalsIgnoreCase("error")){
				alert.setCls("danger");
				alert.setMsg(getText("Merchant.view.error"));
				alertList.add(alert);
				return "input";
			}
			 ActionContext.getContext().getSession().put("MarchantSessID", merchantobj.getMrchntId());
			 json=null;
			 dataobj=null;
			 Commonutility.toWriteConsole("Step 5 : Merchant detail GetmerchantDetail.calss [End]");
			logWrite.logMessage("Step 5 : Merchant detail GetmerchantDetail.calss [End]", "info", GetmerchantDetail.class);
		} catch (Exception e) {
			Commonutility.toWriteConsole("Step -1 : Merchant detail GetmerchantDetail.calss [Exception] : "+e);
			logWrite.logMessage("Step -1 : Merchant detail GetmerchantDetail.calss [Exception] : "+e, "error", GetmerchantDetail.class);
		} finally {
			obj = null;
			data = null;
		}
		return SUCCESS;
	}

	public Common getCommonObj() {
		return commonObj;
	}

	public void setCommonObj(Common commonObj) {
		this.commonObj = commonObj;
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

	public MerchantTblVO getMerchantobj() {
		return merchantobj;
	}

	public void setMerchantobj(MerchantTblVO merchantobj) {
		this.merchantobj = merchantobj;
	}

	public MerchantCategoryDetail getMerchantcatdetobj() {
		return merchantcatdetobj;
	}

	public void setMerchantcatdetobj(MerchantCategoryDetail merchantcatdetobj) {
		this.merchantcatdetobj = merchantcatdetobj;
	}

	public MerchantStockDetailTblVO getMerchantStockobj() {
		return merchantStockobj;
	}

	public void setMerchantStockobj(MerchantStockDetailTblVO merchantStockobj) {
		this.merchantStockobj = merchantStockobj;
	}

	public List<MerchantStockDetailTblVO> getMerchantStockobjList() {
		return merchantStockobjList;
	}

	public void setMerchantStockobjList(
			List<MerchantStockDetailTblVO> merchantStockobjList) {
		this.merchantStockobjList = merchantStockobjList;
	}

	public List<MerchantCategoryDetail> getMerchantcatdetList() {
		return merchantcatdetList;
	}

	public void setMerchantcatdetList(
			List<MerchantCategoryDetail> merchantcatdetList) {
		this.merchantcatdetList = merchantcatdetList;
	}

//	public Integer getMerchantid() {
//		return merchantid;
//	}
//
//	public void setMerchantid(Integer merchantid) {
//		this.merchantid = merchantid;
//	}

	
	public List<MerchantIssueTblVO> getIssList() {
		return issList;
	}

	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public void setIssList(List<MerchantIssueTblVO> issList) {
		this.issList = issList;
	}

	public List<String> getIssueFeatures() {
		return issueFeatures;
	}

	public void setIssueFeatures(List<String> issueFeatures) {
		this.issueFeatures = issueFeatures;
	}

	public String getIssuetext() {
		return issuetext;
	}

	public void setIssuetext(String issuetext) {
		this.issuetext = issuetext;
	}



}