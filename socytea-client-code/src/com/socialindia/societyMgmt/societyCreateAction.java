package com.socialindia.societyMgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.SocietyMstTbl;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class societyCreateAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SocietyMstTbl societyObj = new SocietyMstTbl(0, null, null, null, null,null, null,0,null);
	private int townshipId;	
	private AlertVo alert = new AlertVo();
	private Common commonObj = new CommonDao();
	private File logoImage;
	private File icoImage;
	private String flatnames;
	private String logoImageContentType;
	private String icoImageContentType;
	private String icoImageFileName;
	private String logoImageFileName;
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	MvpSocietyAccTbl societyAcc=new MvpSocietyAccTbl();
	
	
	private String Monthly_QTLY_maintenances;
	private String Municipal_tax;
	private String Water_charge;
	private String Federation_charges;
	private String Repair_fund;
	private String Sinking_fund;
	private String Major_repair_funds;
	private String Non_Occupancy_charges;
	private String Play_zone_fees;
	private String Penalties;
	private String Interest;
	private String Late_fees;
	private String Insurance_cost;
	private String Car_parking_1;
	private String Car_parking_2;
	private String Two_wheeler_1;
	private String Two_wheeler_2;
	private String Sundry_adjustment;
	private String Property_tax;
	private String Excess_Payments;
	private String Club_House;
	private String Arrears;
	private String Previous_Dues;
	private String APP_Subscription_Fee;
	private String Total_Payable;
	private String Amount_in_Words;
	private String Bill_No;
	private String Flat_No;
	private String Flat_Area;
	private String Notes;
	private String RSRVD1;
	private String RSRVD2;
	private String RSRVD3;
	private String RSRVD4;
	private String RSRVD5;
	private String RSRVD6;
	private String RSRVD7;
	private String RSRVD8;
	private String RSRVD9;
	private String RSRVD10;
	
	
	public String execute() {
		Log log = null;
		JSONObject lvrRqstjsonobj = null;
		JSONObject obj = null;
		JSONObject lvrRspjsonobj = null;
		Map sessionMap = ActionContext.getContext().getSession();
//		Map<String,String> chkboxs = new HashMap<String, String>();
		
		JSONObject chkboxs = new JSONObject();
		try {			
			lvrRqstjsonobj = new JSONObject();
			obj = new JSONObject();
			log = new Log();
			Commonutility.toWriteConsole("Step 1 : Society Create Checkbox : Interest = "+ Interest + " Bill_No = " +Bill_No);
			Commonutility.toWriteConsole("Step 1 : Society Create Action Called.[Start] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			log.logMessage("Step 1 : Society Create Action Called.[Start]", "info", societyCreateAction.class);
			lvrRqstjsonobj.put("servicecode", "SI0031");
			obj.put("townshipId", townshipId);
			obj.put("societyname", societyObj.getSocietyName());
			obj.put("noofmembers", societyObj.getNoOfMembers());
			obj.put("emailid", societyObj.getEmailId());
			obj.put("mobileno", societyObj.getMobileNo());
			obj.put("isdcode", societyObj.getIsdCode());

			obj.put("noofblockswings", societyObj.getNoOfBlocksWings());
			obj.put("registerno", societyObj.getRegisterNo());
			obj.put("colorcode", societyObj.getColourCode());
			obj.put("imprintname", societyObj.getImprintName());
			obj.put("flatnames", flatnames);

			obj.put("accountno", societyAcc.getBankAccNo());
			obj.put("bankname", societyAcc.getBankName());
			obj.put("accountname", societyAcc.getBankAccName());
			obj.put("ifsccode", societyAcc.getIfscCode());
			
			obj.put("GSTIN", societyObj.getGstin());
			obj.put("HSN", societyObj.getHsn());
			
			/*obj.put("societylogo", toByteAryToBase64EncodeStr(imgBytlogo));
			obj.put("societyico", toByteAryToBase64EncodeStr(imgBytico));*/
			obj.put("logoImageFileName", logoImageFileName);
			obj.put("icoImageFileName", icoImageFileName);
			
			chkboxs.put("Monthly_QTLY_Maintenances(Numeric)", Monthly_QTLY_maintenances);
			chkboxs.put("Municipal_Tax(Numeric)", Municipal_tax);
			chkboxs.put("Water_Charge(Numeric)", Water_charge);
			chkboxs.put("Federation_Charges(Numeric)", Federation_charges);
			chkboxs.put("Repair_Fund(Numeric)", Repair_fund);
			chkboxs.put("Sinking_Fund(Numeric)", Sinking_fund);
			chkboxs.put("Major_Repair_Funds(Numeric)", Major_repair_funds);
			chkboxs.put("Non_Occupancy_Charges(Numeric)", Non_Occupancy_charges);
			chkboxs.put("Play_Zone_Fees(Numeric)", Play_zone_fees);
			chkboxs.put("Penalties(Numeric)", Penalties);
			chkboxs.put("Interest(Numeric)", Interest);
			chkboxs.put("Late_Fees(Numeric)", Late_fees);
			chkboxs.put("Insurance_Cost(Numeric)", Insurance_cost);
			chkboxs.put("Car_Parking_1(Numeric)", Car_parking_1);
			chkboxs.put("Car_Parking_2(Numeric)", Car_parking_2);
			chkboxs.put("Two_Wheeler_1(Numeric)", Two_wheeler_1);
			chkboxs.put("Two_Wheeler_2(Numeric)", Two_wheeler_2);
			chkboxs.put("Sundry_Adjustment(Numeric)", Sundry_adjustment);
			chkboxs.put("Property_Tax(Numeric)", Property_tax);
			chkboxs.put("Excess_Payments(Numeric)", Excess_Payments);
			chkboxs.put("Club_House(Numeric)", Club_House);
			chkboxs.put("Arrears(Numeric)", Arrears);
			chkboxs.put("Previous_Dues(Numeric)", Previous_Dues);
			chkboxs.put("APP_Subscription_Fee(Numeric)", APP_Subscription_Fee);
			chkboxs.put("Total_Payable(Numeric)", Total_Payable);
			chkboxs.put("Amount_in_Words(Text)", "true");
			chkboxs.put("Bill_No(Alpha Numeric)", "true");
			chkboxs.put("Flat_No(Numeric)", "true");
			chkboxs.put("Flat_Area(Alpha Numeric)", "true");
			chkboxs.put("Notes(Text)", "true");
			chkboxs.put("RSRVD1(Alpha Numeric)", RSRVD1);
			chkboxs.put("RSRVD2(Alpha Numeric)", RSRVD2);
			chkboxs.put("RSRVD3(Alpha Numeric)", RSRVD3);
			chkboxs.put("RSRVD4(Alpha Numeric)", RSRVD4);
			chkboxs.put("RSRVD5(Alpha Numeric)", RSRVD5);
			chkboxs.put("RSRVD6(Alpha Numeric)", RSRVD6);
			chkboxs.put("RSRVD7(Alpha Numeric)", RSRVD7);
			chkboxs.put("RSRVD8(Alpha Numeric)", RSRVD8);
			chkboxs.put("RSRVD9(Alpha Numeric)", RSRVD9);
			chkboxs.put("RSRVD10(Alpha Numeric)", RSRVD10);
			
			obj.put("chkboxs", chkboxs);
			
			lvrRqstjsonobj.put("data", obj);
			
			String jsonTextFinal = lvrRqstjsonobj.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("society.management.create.action");
			Commonutility.toWriteConsole("Step 2 : Society Create Action Service Url : "+ finalUrl);
			log.logMessage("Step 2 : Society Create Action Service Url : "+ finalUrl, "info", societyCreateAction.class);
			String response = commonObj.jsonRequest(finalUrl, temp);
			lvrRspjsonobj = new JSONObject(response);
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(lvrRspjsonobj, "message");
			String statusCode = lvrRspjsonobj.getString("statuscode");
			if (statusCode.equalsIgnoreCase("2") || statusCode.equalsIgnoreCase("02")) {
				if(Commonutility.checkempty(lvrRspmsg)){
					addFieldError("societyObj.societyName",lvrRspmsg);
				} else {
					addFieldError("societyObj.societyName","Society name already exist");
				}				
				return "input";
			} else if (statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")) {
				JSONObject dataobj = new JSONObject();
				JSONObject imgobj = new JSONObject();
				JSONObject icoobj = new JSONObject();
				JSONObject respdataobj = new JSONObject();
				respdataobj=lvrRspjsonobj.getJSONObject("data");
				int societyid = respdataobj.getInt("societyid");							
				if(logoImage!=null){
					//byte imgBytlogo[] = null;
					//imgBytlogo = Commonutility.toReadFiletoBytes(logoImage);
					
					dataobj.put("servicecode", "SI6433");
					dataobj.put("userId", sessionMap.get("USERID"));
					dataobj.put("usrTyp", sessionMap.get("GROUPCODE"));
					imgobj.put("societyid", societyid);
					imgobj.put("updatestatus", 0);
					//imgobj.put("societylogo", Commonutility.toByteAryToBase64EncodeStr(imgBytlogo));
					if(logoImage!=null){
						imgobj.put("soclogoimgsrchpath",logoImage.getAbsolutePath());
					} else {
						imgobj.put("soclogoimgsrchpath","");
					}
					
					imgobj.put("logoImageFileName", logoImageFileName);
					dataobj.put("data", imgobj);
					String jsonStringTextFinal = dataobj.toString();
					jsonStringTextFinal = EncDecrypt.encrypt(jsonStringTextFinal);
					String dta = "ivrparams=" + URLEncoder.encode(jsonStringTextFinal);
					finalUrl = getText("socialindia.societyMng.addsocietyprofileImages");
					commonObj.jsonRequest(finalUrl, dta);
					//imgBytlogo = null;
				}
				if(icoImage!=null){
					//byte imgBytico[] = null;
					//imgBytico = Commonutility.toReadFiletoBytes(icoImage);
					dataobj = new JSONObject();
					dataobj.put("servicecode", "SI6433");
					dataobj.put("userId", sessionMap.get("USERID"));
					dataobj.put("usrTyp", sessionMap.get("GROUPCODE"));
					icoobj.put("societyid", societyid);
					icoobj.put("updatestatus", 0);
					//icoobj.put("societyico", Commonutility.toByteAryToBase64EncodeStr(imgBytico));					
					if(icoImage!=null){
						icoobj.put("socicoimgsrchpath",icoImage.getAbsolutePath());
					} else {
						icoobj.put("socicoimgsrchpath","");
					}									
					icoobj.put("icoImageFileName", icoImageFileName);
					dataobj.put("data", icoobj);
					String jsonStringTextFinal = dataobj.toString();
					jsonStringTextFinal = EncDecrypt.encrypt(jsonStringTextFinal);
					String dta = "ivrparams="+ URLEncoder.encode(jsonStringTextFinal);
					finalUrl = getText("socialindia.societyMng.addsocietyprofileImages");
					commonObj.jsonRequest(finalUrl, dta);	
					//imgBytico = null;
				}
																				
				alert.setCls("success");
				if(lvrRspmsg!=null && !lvrRspmsg.equalsIgnoreCase("null") && !lvrRspmsg.equalsIgnoreCase("")){
					alert.setMsg(lvrRspmsg);
				} else {
					alert.setMsg(getText("success.society.create"));
				}				
				alertList.add(alert);
				// while shortcut townshipmgmt via create Society
				if( (String) sessionMap.get("ivrTwsipid")!=null){
					sessionMap.remove("ivrTwsipid");
				}
				if( (String) sessionMap.get("ivrTwsipname")!=null){
					sessionMap.remove("ivrTwsipname");	
				}									
				sessionMap.put("alertList", alertList);
			} else {
				alert.setCls("danger");
				alert.setMsg(lvrRspmsg);
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
			}
			Commonutility.toWriteConsole("Step 2 : Society Create Action [End] : "+ Commonutility.toGetCurrentDatetime("yyyy-MM-dd hh:mm:ss"));
			log.logMessage("Step 2 : Society Create Action [End]", "info", societyCreateAction.class);
		} catch (Exception e) {
			Commonutility.toWriteConsole("Step -1 : Exception found in societyCreateAction.class : " + e);
			alert.setCls("danger");
			alert.setMsg(getText("error.society.create"));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			log.logMessage("Step -1 : Exception found : " + e, "error", societyCreateAction.class);
		} finally {
			lvrRqstjsonobj =  null; log = null; obj = null; lvrRspjsonobj = null; System.gc();societyObj=null;
		}
		return SUCCESS;		
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

	public SocietyMstTbl getSocietyObj() {
		return societyObj;
	}

	public void setSocietyObj(SocietyMstTbl societyObj) {
		this.societyObj = societyObj;
	}

	public int getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(int townshipId) {
		this.townshipId = townshipId;
	}

	public File getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(File logoImage) {
		this.logoImage = logoImage;
	}

	public File getIcoImage() {
		return icoImage;
	}

	public void setIcoImage(File icoImage) {
		this.icoImage = icoImage;
	}

	public String getLogoImageContentType() {
		return logoImageContentType;
	}

	public void setLogoImageContentType(String logoImageContentType) {
		this.logoImageContentType = logoImageContentType;
	}

	public String getIcoImageContentType() {
		return icoImageContentType;
	}

	public void setIcoImageContentType(String icoImageContentType) {
		this.icoImageContentType = icoImageContentType;
	}

	public String getIcoImageFileName() {
		return icoImageFileName;
	}

	public void setIcoImageFileName(String icoImageFileName) {
		this.icoImageFileName = icoImageFileName;
	}

	public String getLogoImageFileName() {
		return logoImageFileName;
	}

	public void setLogoImageFileName(String logoImageFileName) {
		this.logoImageFileName = logoImageFileName;
	}

	public String getFlatnames() {
		return flatnames;
	}

	public void setFlatnames(String flatnames) {
		this.flatnames = flatnames;
	}

	public MvpSocietyAccTbl getSocietyAcc() {
		return societyAcc;
	}

	public void setSocietyAcc(MvpSocietyAccTbl societyAcc) {
		this.societyAcc = societyAcc;
	}

	public String getMonthly_QTLY_maintenances() {
		return Monthly_QTLY_maintenances;
	}

	public void setMonthly_QTLY_maintenances(String monthly_QTLY_maintenances) {
		Monthly_QTLY_maintenances = monthly_QTLY_maintenances;
	}

	public String getMunicipal_tax() {
		return Municipal_tax;
	}

	public void setMunicipal_tax(String municipal_tax) {
		Municipal_tax = municipal_tax;
	}

	public String getWater_charge() {
		return Water_charge;
	}

	public void setWater_charge(String water_charge) {
		Water_charge = water_charge;
	}

	public String getFederation_charges() {
		return Federation_charges;
	}

	public void setFederation_charges(String federation_charges) {
		Federation_charges = federation_charges;
	}

	public String getRepair_fund() {
		return Repair_fund;
	}

	public void setRepair_fund(String repair_fund) {
		Repair_fund = repair_fund;
	}

	public String getSinking_fund() {
		return Sinking_fund;
	}

	public void setSinking_fund(String sinking_fund) {
		Sinking_fund = sinking_fund;
	}

	public String getMajor_repair_funds() {
		return Major_repair_funds;
	}

	public void setMajor_repair_funds(String major_repair_funds) {
		Major_repair_funds = major_repair_funds;
	}

	public String getNon_Occupancy_charges() {
		return Non_Occupancy_charges;
	}

	public void setNon_Occupancy_charges(String non_Occupancy_charges) {
		Non_Occupancy_charges = non_Occupancy_charges;
	}

	public String getPlay_zone_fees() {
		return Play_zone_fees;
	}

	public void setPlay_zone_fees(String play_zone_fees) {
		Play_zone_fees = play_zone_fees;
	}

	public String getPenalties() {
		return Penalties;
	}

	public void setPenalties(String penalties) {
		Penalties = penalties;
	}

	public String getInterest() {
		return Interest;
	}

	public void setInterest(String interest) {
		Interest = interest;
	}

	public String getLate_fees() {
		return Late_fees;
	}

	public void setLate_fees(String late_fees) {
		Late_fees = late_fees;
	}

	public String getInsurance_cost() {
		return Insurance_cost;
	}

	public void setInsurance_cost(String insurance_cost) {
		Insurance_cost = insurance_cost;
	}

	public String getCar_parking_1() {
		return Car_parking_1;
	}

	public void setCar_parking_1(String car_parking_1) {
		Car_parking_1 = car_parking_1;
	}

	public String getCar_parking_2() {
		return Car_parking_2;
	}

	public void setCar_parking_2(String car_parking_2) {
		Car_parking_2 = car_parking_2;
	}

	public String getTwo_wheeler_1() {
		return Two_wheeler_1;
	}

	public void setTwo_wheeler_1(String two_wheeler_1) {
		Two_wheeler_1 = two_wheeler_1;
	}

	public String getTwo_wheeler_2() {
		return Two_wheeler_2;
	}

	public void setTwo_wheeler_2(String two_wheeler_2) {
		Two_wheeler_2 = two_wheeler_2;
	}

	public String getSundry_adjustment() {
		return Sundry_adjustment;
	}

	public void setSundry_adjustment(String sundry_adjustment) {
		Sundry_adjustment = sundry_adjustment;
	}

	public String getProperty_tax() {
		return Property_tax;
	}

	public void setProperty_tax(String property_tax) {
		Property_tax = property_tax;
	}

	public String getExcess_Payments() {
		return Excess_Payments;
	}

	public void setExcess_Payments(String excess_Payments) {
		Excess_Payments = excess_Payments;
	}

	public String getClub_House() {
		return Club_House;
	}

	public void setClub_House(String club_House) {
		Club_House = club_House;
	}

	public String getArrears() {
		return Arrears;
	}

	public void setArrears(String arrears) {
		Arrears = arrears;
	}

	public String getPrevious_Dues() {
		return Previous_Dues;
	}

	public void setPrevious_Dues(String previous_Dues) {
		Previous_Dues = previous_Dues;
	}

	public String getAPP_Subscription_Fee() {
		return APP_Subscription_Fee;
	}

	public void setAPP_Subscription_Fee(String aPP_Subscription_Fee) {
		APP_Subscription_Fee = aPP_Subscription_Fee;
	}

	public String getTotal_Payable() {
		return Total_Payable;
	}

	public void setTotal_Payable(String total_Payable) {
		Total_Payable = total_Payable;
	}

	public String getAmount_in_Words() {
		return Amount_in_Words;
	}

	public void setAmount_in_Words(String amount_in_Words) {
		Amount_in_Words = amount_in_Words;
	}

	public String getBill_No() {
		return Bill_No;
	}

	public void setBill_No(String bill_No) {
		Bill_No = bill_No;
	}

	public String getFlat_No() {
		return Flat_No;
	}

	public void setFlat_No(String flat_No) {
		Flat_No = flat_No;
	}

	public String getFlat_Area() {
		return Flat_Area;
	}

	public void setFlat_Area(String flat_Area) {
		Flat_Area = flat_Area;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}

	public String getRSRVD1() {
		return RSRVD1;
	}

	public void setRSRVD1(String rSRVD1) {
		RSRVD1 = rSRVD1;
	}

	public String getRSRVD2() {
		return RSRVD2;
	}

	public void setRSRVD2(String rSRVD2) {
		RSRVD2 = rSRVD2;
	}

	public String getRSRVD3() {
		return RSRVD3;
	}

	public void setRSRVD3(String rSRVD3) {
		RSRVD3 = rSRVD3;
	}

	public String getRSRVD4() {
		return RSRVD4;
	}

	public void setRSRVD4(String rSRVD4) {
		RSRVD4 = rSRVD4;
	}

	public String getRSRVD5() {
		return RSRVD5;
	}

	public void setRSRVD5(String rSRVD5) {
		RSRVD5 = rSRVD5;
	}

	public String getRSRVD6() {
		return RSRVD6;
	}

	public void setRSRVD6(String rSRVD6) {
		RSRVD6 = rSRVD6;
	}

	public String getRSRVD7() {
		return RSRVD7;
	}

	public void setRSRVD7(String rSRVD7) {
		RSRVD7 = rSRVD7;
	}

	public String getRSRVD8() {
		return RSRVD8;
	}

	public void setRSRVD8(String rSRVD8) {
		RSRVD8 = rSRVD8;
	}

	public String getRSRVD9() {
		return RSRVD9;
	}

	public void setRSRVD9(String rSRVD9) {
		RSRVD9 = rSRVD9;
	}

	public String getRSRVD10() {
		return RSRVD10;
	}

	public void setRSRVD10(String rSRVD10) {
		RSRVD10 = rSRVD10;
	}

}
