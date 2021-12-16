package com.socialindia.townShipMgmt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.TownshipMstTbl;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;

public class townshipCreateAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private String countryId;
	private String stateId;
	private String cityId;
	private String pinCode;
	private File logoImage;
	private File icoImage;
	private String logoImageContentType;
	private String icoImageContentType;
	private String icoImageFileName;
	private String logoImageFileName;
	
	private AlertVo alert = new AlertVo();
	private Common commonObj = new CommonDao();
	TownshipMstTbl townshipObj = new TownshipMstTbl();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	public String execute() {		
		Log logWrite = null;
		JSONObject obj = null;
		JSONObject data = null;
		Map sessionMap = ActionContext.getContext().getSession();	
		try {			
			obj = new JSONObject();
			data = new JSONObject();
			logWrite = new Log();
			
			if (townshipObj != null && townshipObj.getTownshipName()!=null && Commonutility.checkempty(townshipObj.getTownshipName())) {
			data.put("servicecode", "SI0026");
			data.put("userId", sessionMap.get("USERID"));
			data.put("usrTyp", sessionMap.get("GROUPCODE"));
			obj.put("townshipname", townshipObj.getTownshipName());
			obj.put("noofsociety", townshipObj.getNoOfSociety());
			obj.put("noofflats", townshipObj.getNoOfFlats());
			obj.put("address", townshipObj.getAddress());
			obj.put("countrycode", countryId);
			obj.put("statecode", stateId);
			obj.put("citycode", cityId);
			obj.put("pincode", pinCode);
			Commonutility.toWriteConsole("pinCode =>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + pinCode);
			obj.put("buildername", townshipObj.getBuilderName());
			obj.put("buildernameemail", townshipObj.getEmailId());
			obj.put("isdcode", townshipObj.getIsdCode());
			obj.put("mobileno", townshipObj.getMobileNo());
			obj.put("landmark", townshipObj.getLandMark());
			obj.put("imprintname", townshipObj.getImprintName());
			obj.put("townshipcolor", townshipObj.getTownshipcolourcode());
			obj.put("logoImageFileName", logoImageFileName);
			obj.put("icoImageFileName", icoImageFileName);
			data.put("data", obj);
			String jsonTextFinal = data.toString();			
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("townShipMgmt.management.create.action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = json.getString("statuscode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			townshipObj = null;
			obj = null;
			data = null;
			if (statusCode.equalsIgnoreCase("02")) {
				addFieldError("townshipObj.townshipName", lvrRspmsg);			
				return "input";
			} else if (statusCode.equalsIgnoreCase("03")) {
				addFieldError("townshipObj.mobileNo", lvrRspmsg);				
				return "input";
			} else if (statusCode.equalsIgnoreCase("00")) {// success
						
				JSONObject respdataobj = new JSONObject();
				respdataobj=json.getJSONObject("data");
				int townshipid = respdataobj.getInt("townshipid");
				if(logoImage!=null){
					//byte imgBytlogo[] = null;
					JSONObject dataobj = new JSONObject();
					JSONObject imgobj = new JSONObject();
					//imgBytlogo = toReadFiletoBytes(logoImage);
					
					dataobj.put("servicecode", "SI6432");
					dataobj.put("userId", sessionMap.get("USERID"));
					dataobj.put("usrTyp", sessionMap.get("GROUPCODE"));
					imgobj.put("townshipid", townshipid);
					imgobj.put("updatestatus", 0);					
					//imgobj.put("townshiplogo", toByteAryToBase64EncodeStr(imgBytlogo));
				
					if(logoImage!=null) {
						imgobj.put("tshiplogosrchpth", logoImage.getAbsolutePath());	
						Commonutility.toWriteConsole("tshiplogosrchpth : "+ logoImage.getAbsolutePath());
					} else {
						imgobj.put("tshiplogosrchpth", "");
					}
					imgobj.put("logoImageFileName", logoImageFileName);
					dataobj.put("data", imgobj);
					String jsonStringTextFinal = dataobj.toString();
					jsonStringTextFinal = EncDecrypt.encrypt(jsonStringTextFinal);
					String dta = "ivrparams=" + URLEncoder.encode(jsonStringTextFinal);
					finalUrl = getText("socialindia.townshipMng.addtownshipprofileImages");
					commonObj.jsonRequest(finalUrl, dta);	
					imgobj = null;	//imgBytlogo = null;			
				}
				
				if (icoImage != null){
					//byte imgBytico[] = null;
					//imgBytico = toReadFiletoBytes(icoImage);
					JSONObject dataobj = new JSONObject();
					JSONObject icoobj = new JSONObject();
					dataobj = new JSONObject();
					dataobj.put("servicecode", "SI6432");
					dataobj.put("userId", sessionMap.get("USERID"));
					dataobj.put("usrTyp", sessionMap.get("GROUPCODE"));
					icoobj.put("townshipid", townshipid);
					icoobj.put("updatestatus", 0);
					//icoobj.put("townshipico", toByteAryToBase64EncodeStr(imgBytico));
					//icoobj.put("townshipico", "");
					if(icoImage!=null) {
						icoobj.put("townshipicosrchpath", icoImage.getAbsolutePath());
						Commonutility.toWriteConsole("icoImage : "+ icoImage.getAbsolutePath());
					} else {
						icoobj.put("townshipicosrchpath", "");
					}
					icoobj.put("icoImageFileName", icoImageFileName);
					dataobj.put("data", icoobj);
					String jsonStringTextFinal = dataobj.toString();				
					jsonStringTextFinal = EncDecrypt.encrypt(jsonStringTextFinal);
					String dta = "ivrparams="+ URLEncoder.encode(jsonStringTextFinal);
					finalUrl = getText("socialindia.townshipMng.addtownshipprofileImages");
					commonObj.jsonRequest(finalUrl, dta);					
					icoobj = null;//imgBytico = null;
				}				
				alert.setCls("success");
				alert.setMsg(lvrRspmsg);//getText("success.township.create")
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
			} else {
				alert.setCls("danger");
				alert.setMsg(lvrRspmsg);
				alertList.add(alert);
				sessionMap.put("alertList", alertList);
			}
			} else {
				
			}
		} catch (Exception e) {
			Commonutility.toWriteConsole("Exception Found townshipCreateAction : " + e);
			alert.setCls("danger");
			alert.setMsg(getText("error.township.create"));
			alertList.add(alert);
			sessionMap.put("alertList", alertList);
			logWrite.logMessage( "Service code : ivrservicecode, Sorry, townshipCreateAction an unhandled error occurred", "info", townshipCreateAction.class);
		} finally {
			logWrite = null; obj = null; data = null;
		}
		return SUCCESS;
	}

	public static String toByteAryToBase64EncodeStr(byte[] pFileBytes) {
		String locRtnEncodeStr = null;
		try {
			locRtnEncodeStr = Base64.encodeBase64String(pFileBytes);
		} catch (Exception e) {
			Commonutility.toWriteConsole("Exception toByteAryToBase64EncodeStr() : " + e);
		} finally {
			pFileBytes = null;
		}
		return locRtnEncodeStr;
	}

	public static byte[] toReadFiletoBytes(File filepath) {// read file to byte
		byte toredbyts[] = null;
		byte[] toreturnBytes = null;
		FileInputStream fins = null;
		ByteArrayOutputStream bout = null;
		try {
			toredbyts = new byte[(int) filepath.length()];
			fins = new FileInputStream(filepath);
			bout = new ByteArrayOutputStream();
			for (int readNum; (readNum = fins.read(toredbyts)) != -1;) {
				bout.write(toredbyts, 0, readNum); // no doubt here is 0
			}
			toreturnBytes = bout.toByteArray();
		} catch (Exception e) {
		} finally {
			try {
				if (fins != null) {
					fins.close();
					fins = null;
				}
				if (bout != null) {
					bout.close();
					bout = null;
				}
			} catch (IOException e) {
			}
		}
		return toreturnBytes;
	}

	public TownshipMstTbl getTownshipObj() {
		return townshipObj;
	}

	public void setTownshipObj(TownshipMstTbl townshipObj) {
		this.townshipObj = townshipObj;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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
}
