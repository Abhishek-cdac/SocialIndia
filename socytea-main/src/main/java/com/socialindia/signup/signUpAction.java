package com.socialindia.signup;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;











import org.json.JSONArray;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.uam.persistense.MenuMasterTblVo;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.letspay.vo.CityMasterTblVo;
import com.letspay.vo.CountryMasterTblVo;
import com.letspay.vo.StateMasterTblVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.getCategoryCodeMethod;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.generalmgnt.persistance.IDCardMasterTblVO;
import com.socialindia.generalmgnt.persistance.PostalCodeMasterTblVO;
import com.socialindia.login.EncDecrypt;
import com.socialindia.login.persistense.UserMasterTblVo;

public class signUpAction extends ActionSupport {
	private String activationKey;
	private String date;
	private int countrycode1;
	private int stateId;
	private int cityid;
	private int cardtype;
	private int countryno;
	private String countryCode;
	private String stateCode;
	private String cityCode;
	private String postalCode;
	private String countryName;
	private String stateName;
	private String cityName;
	private String pincodeNo;
	private String idcardtypename;
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	Log log=new Log();
	  public List<IDCardMasterTblVO> idcardMstr = new ArrayList<IDCardMasterTblVO>();
	private UserMasterTblVo custRegObj = new UserMasterTblVo(null, null, null,
			null, null, null, null, null, 0, null);
	  public List<StateMasterTblVo> statmstr = new ArrayList<StateMasterTblVo>();
	private List<MenuMasterTblVo> rightsList = new ArrayList<MenuMasterTblVo>();
	private CountryMasterTblVo countryMst=new CountryMasterTblVo(0, null);
	private StateMasterTblVo stateData=new StateMasterTblVo(0,null);
	private CityMasterTblVo cityData=new CityMasterTblVo(0,null);
	private PostalCodeMasterTblVO postalData=new PostalCodeMasterTblVO(0,null);
	 public List<PostalCodeMasterTblVO> pstlMstr = new ArrayList<PostalCodeMasterTblVO>();
	  public List<CityMasterTblVo> cityMstr = new ArrayList<CityMasterTblVo>();
	JSONObject obj = new JSONObject();
	private Integer societyId;
	JSONObject data = new JSONObject();
	private String townshiplogoname = "";
	private String usersocietyid = "";
	private String townshipiconame = "";
	private String townshipcolourcode = "";

	public String execute() {
		
		if (custRegObj.getGroupCode() > 1) {
			try {
				obj.put("servicecode", "SI0018");
				obj.put("userName", custRegObj.getUserName());
				obj.put("firstName", custRegObj.getFirstName());
				obj.put("lastName", custRegObj.getLastName());
				obj.put("dob", date);
				obj.put("emailId", custRegObj.getEmailId());
				obj.put("groupCodeType", custRegObj.getGroupCode());
				obj.put("gender", custRegObj.getGender());
				obj.put("mobileNo", custRegObj.getMobileNo());
				obj.put("idCardType", custRegObj.getIdCardType());
				obj.put("idProofNo", custRegObj.getIdProofNo());
				obj.put("bloodGroup", custRegObj.getBloodType());
				obj.put("address1", custRegObj.getAddress1());
				obj.put("societyId", societyId);
				data.put("data", obj);
				String jsonTextFinal = data.toString();
				String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
				System.out.println("=====jsonText=====" + jsonTextFinal);
				String finalUrl = getText("signup.except.admin.action");
				System.out.println("====finalUrl====" + finalUrl);
				String response = commonObj.jsonRequest(finalUrl, temp);
				System.out.println("=========response===" + response);
				JSONObject json = new JSONObject(response);
				String statusCode = (String) json.get("statuscode");
				String message = (String) json.get("message");
				JSONArray ar = null;
				System.out.println("=============message=======" + message);
				JSONObject json_data = new JSONObject();
				if (message.equalsIgnoreCase("userExist")) {					
					addFieldError("custRegObj.userName",
							"User name already exist");
					return "input";
				} else if (message.equalsIgnoreCase("EmailExist")) {
					addFieldError("custRegObj.emailId",
							"Email already exist");
					return "input";
				} else if (message.equalsIgnoreCase("mobnoExist")) {
					addFieldError("custRegObj.mobileNo",
							"mobile no already exist");
					return "input";
				}
				if (statusCode.equalsIgnoreCase("0000")) {
					json_data = json.getJSONObject("data");
					ar = json_data.getJSONArray("menu");
					JSONObject jsonList = new JSONObject();
					JSONObject jsonuser = new JSONObject();

					for (int i = 0; i < ar.length(); i++) {
						jsonList = null;
						jsonList = ar.getJSONObject(i);
						String menuType = jsonList.getString("menuType");
						String rootDesc = jsonList.getString("rootDesc");
						String menuPath = jsonList.getString("menuPath");
						String menuDesc = jsonList.getString("menuDesc");
						int menuID = jsonList.getInt("menuID");
						String menuClass = "";
						if(jsonList.has("menuClass")){
	    					menuClass = jsonList.getString("menuClass");
	    				}else{
	    					menuClass = "";
	    				} 
						rightsList.add(new MenuMasterTblVo(menuID, menuDesc, menuPath, menuType, rootDesc,menuClass));
					}
					ActionContext.getContext().getSession()
							.put("RIGHTSLST", rightsList);
					jsonuser = json_data.getJSONObject("townshipdetail");
					townshiplogoname = jsonuser.getString("townshiplogoname");
					usersocietyid = jsonuser.getString("usersocietyid");
					townshipiconame = jsonuser.getString("townshipiconame");
					townshipcolourcode = jsonuser
							.getString("townshipcolourcode");
					ActionContext.getContext().getSession()
							.put("townshiplogoname", townshiplogoname);
					ActionContext.getContext().getSession()
							.put("usersocietyid", usersocietyid);
					ActionContext.getContext().getSession()
							.put("townshipiconame", townshipiconame);
					ActionContext.getContext().getSession()
							.put("townshipcolourcode", townshipcolourcode);
					alert.setCls("success");
					alert.setMsg(getText("Text.customerreg.success.msg"));
					alertList.add(alert);

				} else {
					alert.setCls("danger");
					alert.setMsg(getText("Text.customerreg.key.error"));
					alertList.add(alert);
					return "input";
				}
			} catch (Exception ex) {
				System.out.println("excute committee()----> " + ex);
			}
			return "dashboard";
		}

		return SUCCESS;
	}

	public String verifyActiveKey()  {
		System.out.println("===========verify====Key==========");
		System.out.println("========customerreg==" + custRegObj.getUserName());
		System.out.println("========customerreg==" + custRegObj.getEmailId());
		System.out.println("========customerreg==" + custRegObj.getMobileNo());
		System.out.println("========customerreg==" + custRegObj.getGroupCode());
		System.out.println("========address==" + custRegObj.getAddress1());
		System.out.println("=============date===========" + date);
		try {
			obj.put("servicecode", "SI0001");
			obj.put("activationKey", activationKey);
			obj.put("userName", custRegObj.getUserName());
			obj.put("firstName", custRegObj.getFirstName());
			obj.put("lastName", custRegObj.getLastName());
			obj.put("dob", date);
			obj.put("emailId", custRegObj.getEmailId());
			obj.put("groupCodeType", custRegObj.getGroupCode());
			obj.put("gender", custRegObj.getGender());
			obj.put("mobileNo", custRegObj.getMobileNo());
			obj.put("idCardType", custRegObj.getIdCardType());
			obj.put("idProofNo", custRegObj.getIdProofNo());
			obj.put("bloodGroup", custRegObj.getBloodType());
			obj.put("address1", custRegObj.getAddress1());
			data.put("data", obj);

			String jsonTextFinal = data.toString();
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			System.out.println("=====jsonText=====" + jsonTextFinal);
			ResourceBundle rb = ResourceBundle.getBundle("action");
			String finalUrl = rb.getString("signup.action.url");
			System.out.println("====finalUrl====" + finalUrl);
			String response = commonObj.jsonRequest(finalUrl, temp);
			System.out.println("=========response===" + response);
			JSONObject json = new JSONObject(response);
			String statusCode = (String) json.get("statuscode");
			String message = (String) json.get("message");
			JSONArray ar = null;
			System.out.println("=============message=======" + message);
			JSONObject json_data = new JSONObject();
			if (message.equalsIgnoreCase("userExist")) {
				System.out.println("=============ffffffffff=======" + message);
				addFieldError("custRegObj.userName", "User name already exist");
				return "input";
			} else if (message.equalsIgnoreCase("EmailExist")) {
				addFieldError("custRegObj.emailId", "Email already exist");
				return "input";
			} else if (message.equalsIgnoreCase("mobnoExist")) {
				addFieldError("custRegObj.mobileNo", "mobile no already exist");
				return "input";
			}
			if (statusCode.equalsIgnoreCase("0000")) {
				System.out.println("==============Key===match=======");
				json_data = json.getJSONObject("data");
				ar = json_data.getJSONArray("menu");
				System.out.println("==============ar===" + ar.length());
				JSONObject jsonList = new JSONObject();

				for (int i = 0; i < ar.length(); i++) {
					jsonList = null;
					jsonList = ar.getJSONObject(i);
					String menuType = jsonList.getString("menuType");
					String rootDesc = jsonList.getString("rootDesc");
					String menuPath = jsonList.getString("menuPath");
					String menuDesc = jsonList.getString("menuDesc");
					int menuID = jsonList.getInt("menuID");					
					String menuClass = "";
					if(jsonList.has("menuClass")){
    					menuClass = jsonList.getString("menuClass");
    				}else{
    					menuClass = "";
    				} 
					rightsList.add(new MenuMasterTblVo(menuID, menuDesc, menuPath, menuType, rootDesc,menuClass));
				}
				System.out.println("============rightsList==="
						+ rightsList.size());
				ActionContext.getContext().getSession()
						.put("RIGHTSLST", rightsList);
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Text.customerreg.key.error"));
				alertList.add(alert);
				return "input";
			}
		} catch (Exception ex) {
			System.out.println("verifyActiveKey()----> " + ex);
		}
		return SUCCESS;
	}

	public String profileUpdate() {
		try {
			System.out.println("===========custRegObj===blood type====="
					+ custRegObj.getBloodType());
			data.put("servicecode", "SI0024");
			obj.put("firstName", custRegObj.getFirstName());
			obj.put("lastName", custRegObj.getLastName());
			obj.put("dob", custRegObj.getDob());
			obj.put("gender", custRegObj.getGender());
			obj.put("idCardType", custRegObj.getIdCardType());
			obj.put("idProofNo", custRegObj.getIdProofNo());
			obj.put("flatno", custRegObj.getFlatNo());
			obj.put("occupation", custRegObj.getOccupation());
			obj.put("bloodGroup", custRegObj.getBloodType());
			obj.put("noofmembers", custRegObj.getMembersInFamily());
			obj.put("countryid", custRegObj.getCountryCode());
			obj.put("stateid", custRegObj.getStateId());
			obj.put("cityid", custRegObj.getCityId());
			obj.put("pincode", custRegObj.getPinCode());
			obj.put("address1", custRegObj.getAddress1());
			obj.put("address2", custRegObj.getAddress2());
			data.put("data", obj);
		} catch (Exception ex) {
			System.out.println("====profileUpdate====Exception==========" + ex);
		}
		return SUCCESS;
	}

	public String changePassword() {
		return SUCCESS;
	}
	
	public String profileCancel() {	
		return SUCCESS;
	}

	public String signupmethod() {
		return SUCCESS;
	}

	public String profile() {	
		try {
			new getCategoryCodeMethod().getCategoryDetail();
			JSONObject jsonobj = new JSONObject();
			JSONObject finaldata = new JSONObject();
			Map sessionMap = ActionContext.getContext().getSession();
			finaldata.put("servicecode", "SI0023");
			jsonobj.put("userid", sessionMap.get("USERID"));
			finaldata.put("data", jsonobj);
			String jsonTextFinal = finaldata.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp1 = "ivrparams=" + URLEncoder.encode(jsonTextFinal);

			String finalUrl = getText("user.profile.edit.action");
			String response = commonObj.jsonRequest(finalUrl, temp1);
			JSONObject json_data=new JSONObject();
			JSONObject json = new JSONObject(response);
			//String statusCode = (String) json.get("statuscode");
			json_data=json.getJSONObject("data");
			//custRegObj.setStateId(Integer.parseInt(json_data.getString("stateid")));
			custRegObj.setUserId(json_data.getInt("userid"));
			custRegObj.setOccupation(json_data.getString("occupation"));
			custRegObj.setIdCardType(json_data.getInt("idcardtype"));
			cardtype=json_data.getInt("idcardtype");
			idcardtypename=json_data.getString("idcardtypename");
			custRegObj.setAddress1(json_data.getString("address1"));
			custRegObj.setAddress2(json_data.getString("address2"));
			custRegObj.setLastName(json_data.getString("lastname"));
			custRegObj.setBloodType(json_data.getString("bloodtype"));
			custRegObj.setMembersInFamily(json_data.getInt("noofmember"));
			custRegObj.setFirstName(json_data.getString("firstname"));
			/*countryMst.setCountryId(json_data.getInt("countryid"));*/
			//String countryno =String.valueOf(countryName);	
			countryno=json_data.getInt("countryid");
			countryCode=String.valueOf(countryno);
			countryName=json_data.getString("countryName");	
			stateData.setStateId(json_data.getInt("stateid"));			
			int stateno=json_data.getInt("stateid");
			stateCode=String.valueOf(stateno);			
			stateName=json_data.getString("stateName");			
			cityData.setCityId(json_data.getInt("cityid"));
			int cityno=json_data.getInt("cityid");
			cityCode=String.valueOf(cityno);
			cityName=json_data.getString("cityName");
			postalData.setPstlId(json_data.getInt("pincode"));
			int postno=json_data.getInt("pincode");
			postalCode=String.valueOf(postno);
			pincodeNo=json_data.getString("pincodeNo");	
			custRegObj.setDob(json_data.getString("dob"));
			custRegObj.setGender(json_data.getString("gender"));
			custRegObj.setIdProofNo(json_data.getString("idproofno"));
			custRegObj.setFlatNo(json_data.getString("flatno"));
			custRegObj.setNoofflats(json_data.getInt("noofflats"));
			custRegObj.setAccessMedia(json_data.getInt("accessmedia"));
			 
		} catch (Exception ex) {
			System.out.println("Exception in signUpAction :" + ex);
			 log.logMessage("Exception in signUpAction flow emailFlow----> " + ex, "error",signUpAction.class);
		}
		return SUCCESS;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
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

	public UserMasterTblVo getCustRegObj() {
		return custRegObj;
	}

	public void setCustRegObj(UserMasterTblVo custRegObj) {
		this.custRegObj = custRegObj;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<MenuMasterTblVo> getRightsList() {
		return rightsList;
	}

	public void setRightsList(List<MenuMasterTblVo> rightsList) {
		this.rightsList = rightsList;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public String getTownshiplogoname() {
		return townshiplogoname;
	}

	public void setTownshiplogoname(String townshiplogoname) {
		this.townshiplogoname = townshiplogoname;
	}

	public String getUsersocietyid() {
		return usersocietyid;
	}

	public void setUsersocietyid(String usersocietyid) {
		this.usersocietyid = usersocietyid;
	}

	public String getTownshipiconame() {
		return townshipiconame;
	}

	public void setTownshipiconame(String townshipiconame) {
		this.townshipiconame = townshipiconame;
	}

	public String getTownshipcolourcode() {
		return townshipcolourcode;
	}

	public void setTownshipcolourcode(String townshipcolourcode) {
		this.townshipcolourcode = townshipcolourcode;
	}

/*	public int getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}
*/
	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public List<StateMasterTblVo> getStatmstr() {
		return statmstr;
	}

	public void setStatmstr(List<StateMasterTblVo> statmstr) {
		this.statmstr = statmstr;
	}

	public CountryMasterTblVo getCountryMst() {
		return countryMst;
	}

	public void setCountryMst(CountryMasterTblVo countryMst) {
		this.countryMst = countryMst;
	}

	public List<CityMasterTblVo> getCityMstr() {
		return cityMstr;
	}

	public void setCityMstr(List<CityMasterTblVo> cityMstr) {
		this.cityMstr = cityMstr;
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

	public List<PostalCodeMasterTblVO> getPstlMstr() {
		return pstlMstr;
	}

	public void setPstlMstr(List<PostalCodeMasterTblVO> pstlMstr) {
		this.pstlMstr = pstlMstr;
	}

	public PostalCodeMasterTblVO getPostalData() {
		return postalData;
	}

	public void setPostalData(PostalCodeMasterTblVO postalData) {
		this.postalData = postalData;
	}

	public int getCardtype() {
		return cardtype;
	}

	public void setCardtype(int cardtype) {
		this.cardtype = cardtype;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPincodeNo() {
		return pincodeNo;
	}

	public void setPincodeNo(String pincodeNo) {
		this.pincodeNo = pincodeNo;
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

	public String getIdcardtypename() {
		return idcardtypename;
	}

	public void setIdcardtypename(String idcardtypename) {
		this.idcardtypename = idcardtypename;
	}	
	
}
