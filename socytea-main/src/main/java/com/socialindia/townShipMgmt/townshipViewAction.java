package com.socialindia.townShipMgmt;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.TownshipMstTbl;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.letspay.vo.CityMasterTblVo;
import com.letspay.vo.CountryMasterTblVo;
import com.letspay.vo.StateMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.PostalCodeMasterTblVO;
import com.socialindia.login.EncDecrypt;

public class townshipViewAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uniqTownShipIdEdit;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private AlertVo alert = new AlertVo();
	TownshipMstTbl townShipMst = new TownshipMstTbl();
	Log log = new Log();
	private String townshipId;
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	private File logoImage;
	private File icoImage;
	private String logoImageContentType;
	private String icoImageContentType;
	private String icoImageFileName;
	private String logoImageFileName;
	private String townshipUniqId;
	private String countryCode;
	private String stateCode;
	private String cityCode;
	private String postalCode;
	private CountryMasterTblVo countryMst = new CountryMasterTblVo(0, null);
	private StateMasterTblVo stateData = new StateMasterTblVo(0, null);
	private CityMasterTblVo cityData = new CityMasterTblVo(0, null);
	private PostalCodeMasterTblVO postalData = new PostalCodeMasterTblVO(0, null);
	public List<PostalCodeMasterTblVO> pstlMstr = new ArrayList<PostalCodeMasterTblVO>();
	public List<CityMasterTblVo> cityMstr = new ArrayList<CityMasterTblVo>();
	public List<StateMasterTblVo> statmstr = new ArrayList<StateMasterTblVo>();
	private CountryMasterTblVo countryMstList = new CountryMasterTblVo(0, null);
	byte imgBytlogo[] = new byte[2048];
	byte imgBytico[] = new byte[2048];
	public String execute() {
		try{		
			Map sessionMap = ActionContext.getContext().getSession();
			data.put("servicecode", "SI0027");
			obj.put("uniqTownShipIdEdit",uniqTownShipIdEdit);
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
	
			String finalUrl = getText("townShipMgmt.management.edit.view.action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("response : "+response);
			JSONObject json = new JSONObject(response);
			String statusCode = json.getString("statuscode");
		if (statusCode.equalsIgnoreCase("0") || statusCode.equalsIgnoreCase("00")) {			
			JSONObject json_data=json.getJSONObject("data");
			townShipMst.setTownshipName(json_data.getString("townshipname"));
			townShipMst.setNoOfSociety(String.valueOf(json_data.getInt("noofsociety")));
			townShipMst.setNoOfFlats(String.valueOf(json_data.getInt("noofflats")));
			townShipMst.setActivationKey(json_data.getString("activationkey"));
			townShipMst.setCountryName(json_data.getString("country"));
			townShipMst.setStateName(json_data.getString("state"));
			townShipMst.setCityName(json_data.getString("city"));
			townShipMst.setTownshipcolourcode(json_data.getString("townshipcolourcode"));
			townShipMst.setTownshiplogoname(json_data.getString("townshiplogo"));
			townShipMst.setTownshipiconame(json_data.getString("townshipico"));
			townShipMst.setTownshipId(json_data.getInt("townshipid"));
			townShipMst.setPinCode(json_data.getString("pincode"));
			townShipMst.setAddress(json_data.getString("address"));
			townShipMst.setTownshipId(json_data.getInt("townshipid"));
			townShipMst.setBuilderName(json_data.getString("buildername"));
			townShipMst.setMobileNo(json_data.getString("mobileno"));
			townShipMst.setEmailId(json_data.getString("emailid"));
			townShipMst.setLandMark(json_data.getString("landmark"));
			townShipMst.setImprintName(json_data.getString("imprintname"));
		}
		
		}catch(Exception e){
			Commonutility.toWriteConsole("Step -1 : Exception Found townshipViewAction.class execute() : "+e);
			log.logMessage("Step -1 : Exception found in townshipViewAction.clss execute() : "+e, "error", townshipViewAction.class);
		} finally {
			
		}

		return SUCCESS;
	}

	public String edittownshipform(){
		try{			
			Map currentSession = ActionContext.getContext().getSession();			
			if((uniqTownShipIdEdit==null || uniqTownShipIdEdit.equalsIgnoreCase("null") )  && currentSession.get("currentsession_uniqTownShipIdEdit")!=null){			
				uniqTownShipIdEdit=(String)currentSession.get("currentsession_uniqTownShipIdEdit");
			}else{
				currentSession = ActionContext.getContext().getSession();
				currentSession.put("currentsession_uniqTownShipIdEdit", uniqTownShipIdEdit);					
			}			
			data.put("servicecode", "SI0028");
			obj.put("uniqTownShipIdEdit",uniqTownShipIdEdit);
			data.put("data", obj);
			String jsonTextFinal = data.toString();			
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("townShipMgmt.management.edit.user.data.action");			
			String response = commonObj.jsonRequest(finalUrl, temp);			
			JSONObject json = new JSONObject(response);
			String statusCode = json.getString("statuscode");
			if (statusCode.equalsIgnoreCase("00") || statusCode.equalsIgnoreCase("0")) {				
			JSONObject json_data=json.getJSONObject("data");			
			townShipMst.setTownshipName(json_data.getString("townshipname"));
			townShipMst.setNoOfSociety(String.valueOf(json_data.getInt("noofsociety")));
			townShipMst.setNoOfFlats(String.valueOf(json_data.getInt("noofflats")));
			
			townShipMst.setTownshipcolourcode(json_data.getString("townshipcolourcode"));
			townShipMst.setTownshiplogoname(json_data.getString("townshiplogo"));
			townShipMst.setTownshipiconame(json_data.getString("townshipico"));
			townShipMst.setTownshipId(json_data.getInt("townshipid"));
			townShipMst.setCountryFullName(json_data.getString("countryname"));
			townShipMst.setStateFullName(json_data.getString("statename"));
			townShipMst.setCityFullName(json_data.getString("cityname"));
//			townShipMst.setPinFullCode(json_data.getString("pincodename"));
			townShipMst.setPinFullCode(json_data.getInt("pincode")+"");
			townShipMst.setBuilderName(json_data.getString("buildername"));
			townShipMst.setIsdCode(json_data.getString("isdcode"));
			townShipMst.setMobileNo(json_data.getString("mobileno"));
			townShipMst.setEmailId(json_data.getString("emailid"));
			townShipMst.setLandMark(json_data.getString("landmark"));
			townShipMst.setImprintName(json_data.getString("imprintname"));						
			countryMst.setCountryId(json_data.getInt("countryid"));
			stateData.setStateId(json_data.getInt("stateid"));
			cityData.setCityId(json_data.getInt("cityid"));
//			postalData.setPstlId(json_data.getInt("pincode"));			
			townShipMst.setAddress(json_data.getString("address"));
			}
			  
		} catch (Exception e) {
			e.printStackTrace();
			Commonutility.toWriteConsole("Step -1 : Exception found in townshipViewAction.class : "+e);	
			log.logMessage("Step -1 : Exception found in townshipViewAction.class : "+e, "error", townshipViewAction.class);
		}

		return SUCCESS;
	}
	
	public String townshipEditUpdate(){
		try {
			Commonutility.toWriteConsole("Step 1 : Township Edit / Update Called. [Start]");
			Map sessionMap = ActionContext.getContext().getSession();
			data.put("servicecode", "SI0029");
			obj.put("uniqTownShipIdEdit", townShipMst.getTownshipId());
			obj.put("townshipname", townShipMst.getTownshipName());
			obj.put("noofsociety", townShipMst.getNoOfSociety());
			obj.put("noofflats", townShipMst.getNoOfFlats());
			obj.put("townshipcolorcode", townShipMst.getTownshipcolourcode());
			obj.put("countryCode", countryCode);
			obj.put("stateCode", stateCode);
			obj.put("cityCode", cityCode);
			obj.put("postalCode", postalCode);
			obj.put("address", townShipMst.getAddress());
			obj.put("buildername", townShipMst.getBuilderName());
			obj.put("isdcode", townShipMst.getIsdCode());
			obj.put("mobileno", townShipMst.getMobileNo());
			obj.put("emailid", townShipMst.getEmailId());
			obj.put("landmark", townShipMst.getLandMark());
			obj.put("imprintname", townShipMst.getImprintName());
			if (logoImage != null) {
				obj.put("logoImageFileName", logoImageFileName);
			} else {
				obj.put("logoImageFileName", "");
			}
			if (icoImage != null) {
				obj.put("icoImageFileName", icoImageFileName);
			} else {
				obj.put("icoImageFileName", "");
			}
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("townShipMgmt.management.update.user.data.action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			JSONObject json = new JSONObject(response);
			String statusCode = json.getString("statuscode");
			String lvrRspmsg = (String) Commonutility.toHasChkJsonRtnValObj(json, "message");
			Commonutility.toWriteConsole("Status code : "+statusCode);
		if(statusCode.equalsIgnoreCase("02")){
			addFieldError("townShipMst.townshipName", lvrRspmsg);
			return "input";
		} else if (statusCode.equalsIgnoreCase("03")){
			addFieldError("townShipMst.mobileNo", lvrRspmsg);
			return "input";
		} else if (statusCode.equalsIgnoreCase("00")){
				String jsonStringTextFinal = "";
				String dta = "";
				JSONObject dataobj = new JSONObject();
				JSONObject imgobj = new JSONObject();
				JSONObject icoobj = new JSONObject();
				int townshipid = townShipMst.getTownshipId();			
				if (logoImage != null) {
					dataobj.put("servicecode", "SI6432");
					dataobj.put("userId", sessionMap.get("USERID"));
					dataobj.put("usrTyp", sessionMap.get("GROUPCODE"));
					imgobj.put("townshipid", townshipid);
					imgobj.put("updatestatus", 0);
					//imgBytlogo = toReadFiletoBytes(logoImage);
					//imgobj.put("townshiplogo",toByteAryToBase64EncodeStr(imgBytlogo));
					imgobj.put("tshiplogosrchpth", logoImage.getAbsolutePath());	
					imgobj.put("logoImageFileName", logoImageFileName);
					dataobj.put("data", imgobj);
					jsonStringTextFinal = dataobj.toString();
		
					jsonStringTextFinal = EncDecrypt.encrypt(jsonStringTextFinal);
					dta = "ivrparams="+ URLEncoder.encode(jsonStringTextFinal);
					finalUrl = getText("socialindia.townshipMng.addtownshipprofileImages");
					commonObj.jsonRequest(finalUrl, dta);
				}
				if (icoImage != null) {
					dataobj = new JSONObject();
					dataobj.put("servicecode", "SI6432");
					dataobj.put("userId", sessionMap.get("USERID"));
					dataobj.put("usrTyp", sessionMap.get("GROUPCODE"));
					icoobj.put("townshipid", townshipid);
					icoobj.put("updatestatus", 0);
					//imgBytico = toReadFiletoBytes(icoImage);
					//icoobj.put("townshipico", toByteAryToBase64EncodeStr(imgBytico));
					icoobj.put("townshipicosrchpath", icoImage.getAbsolutePath());
					icoobj.put("icoImageFileName", icoImageFileName);
					dataobj.put("data", icoobj);
					jsonStringTextFinal = dataobj.toString();
			
					jsonStringTextFinal = EncDecrypt.encrypt(jsonStringTextFinal);
					dta = "ivrparams="+ URLEncoder.encode(jsonStringTextFinal);
					finalUrl = getText("socialindia.townshipMng.addtownshipprofileImages");
					commonObj.jsonRequest(finalUrl, dta);
			}
			imgobj = null;
			icoobj = null;			
			alert.setCls("success");
			alert.setMsg(lvrRspmsg);//getText("success.township.update")
			alertList.add(alert);
		} else {
			alert.setCls("danger");
			alert.setMsg(lvrRspmsg);//getText("success.township.update")
			alertList.add(alert);
		}
		
		
		}catch(Exception ex){			
			alert.setCls("danger");
			alert.setMsg(getText("error.township.update"));
			alertList.add(alert);
			log.logMessage("townshipEditUpdate Exception : "+ex, "error", townshipViewAction.class);
		}
		return SUCCESS;
	}
	
	/*public static String toByteAryToBase64EncodeStr(byte[] pFileBytes){
 		String locRtnEncodeStr=null;
 		try{
 			locRtnEncodeStr= Base64.encodeBase64String(pFileBytes);
 		}catch(Exception e){
 			System.out.println("Exception toByteAryToBase64EncodeStr() : "+e);
 		}finally{
 			pFileBytes=null;
 		}
 		return locRtnEncodeStr;
 	}
	
	public static byte[] toReadFiletoBytes(File filepath) {// read file to byte	       
        byte toredbyts[] = null;
        byte[] toreturnBytes = null;
        FileInputStream fins = null;
        ByteArrayOutputStream bout = null;
        try {
            toredbyts = new byte[(int)filepath.length()];
            fins = new FileInputStream(filepath);
            bout = new ByteArrayOutputStream();
            for (int readNum; (readNum = fins.read(toredbyts)) != -1;) {
                bout.write(toredbyts, 0, readNum); //no doubt here is 0 
            }
            toreturnBytes = bout.toByteArray();
        } catch (Exception e) {
        } finally {
            try {
                if(fins!=null){fins.close();fins=null;}
                if(bout!=null){bout.close();bout=null;}               
            } catch (IOException e) {}
        }
        return toreturnBytes;
    } */
	public String getUniqTownShipIdEdit() {
		return uniqTownShipIdEdit;
	}

	public void setUniqTownShipIdEdit(String uniqTownShipIdEdit) {
		this.uniqTownShipIdEdit = uniqTownShipIdEdit;
	}

	public TownshipMstTbl getTownShipMst() {
		return townShipMst;
	}

	public void setTownShipMst(TownshipMstTbl townShipMst) {
		this.townShipMst = townShipMst;
	}

	public String getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(String townshipId) {
		this.townshipId = townshipId;
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

	public String getTownshipUniqId() {
		return townshipUniqId;
	}

	public void setTownshipUniqId(String townshipUniqId) {
		this.townshipUniqId = townshipUniqId;
	}

	public CountryMasterTblVo getCountryMst() {
		return countryMst;
	}

	public void setCountryMst(CountryMasterTblVo countryMst) {
		this.countryMst = countryMst;
	}

	public StateMasterTblVo getStateData() {
		return stateData;
	}

	public void setStateData(StateMasterTblVo stateData) {
		this.stateData = stateData;
	}

	public CityMasterTblVo getCityData() {
		return cityData;
	}

	public void setCityData(CityMasterTblVo cityData) {
		this.cityData = cityData;
	}

	public PostalCodeMasterTblVO getPostalData() {
		return postalData;
	}

	public void setPostalData(PostalCodeMasterTblVO postalData) {
		this.postalData = postalData;
	}

	public List<PostalCodeMasterTblVO> getPstlMstr() {
		return pstlMstr;
	}

	public void setPstlMstr(List<PostalCodeMasterTblVO> pstlMstr) {
		this.pstlMstr = pstlMstr;
	}

	public List<CityMasterTblVo> getCityMstr() {
		return cityMstr;
	}

	public void setCityMstr(List<CityMasterTblVo> cityMstr) {
		this.cityMstr = cityMstr;
	}

	public List<StateMasterTblVo> getStatmstr() {
		return statmstr;
	}

	public void setStatmstr(List<StateMasterTblVo> statmstr) {
		this.statmstr = statmstr;
	}

	public CountryMasterTblVo getCountryMstList() {
		return countryMstList;
	}

	public void setCountryMstList(CountryMasterTblVo countryMstList) {
		this.countryMstList = countryMstList;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	
}
