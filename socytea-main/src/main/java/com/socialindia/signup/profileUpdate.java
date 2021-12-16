package com.socialindia.signup;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.utils.Log;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.Commonutility;
import com.socialindia.login.EncDecrypt;
import com.socialindia.login.persistense.UserMasterTblVo;

public class profileUpdate extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	private Common commonObj = new CommonDao();
	private String countryCode;
	private String stateCode;
	private String cityCode;
	private String postalCode;
	private String idCardType;
	private File profileImage;
	private String profileImageContentType;
	private String profileImageFileName;
	private String userProfileImage;
	private String accessMedia;
	byte imgByt[] = null;
	Log log = new Log();
	 private UserMasterTblVo custRegObj = new UserMasterTblVo(null, null, null,null, null, null,null,null, 0, null);

	public String execute() {
		String result = "";
		try {							
			data.put("servicecode", "SI0024");		
			obj.put("userid", custRegObj.getUserId());
			obj.put("firstname", custRegObj.getFirstName());
			obj.put("lastname", custRegObj.getLastName());
			obj.put("dob", custRegObj.getDob());
			obj.put("getnder", custRegObj.getGender());
			obj.put("idcardtype", idCardType);
			obj.put("idproofno", custRegObj.getIdProofNo());
			obj.put("flatno", custRegObj.getFlatNo());
			obj.put("occupation", custRegObj.getOccupation());
			obj.put("bloodtype", custRegObj.getBloodType());
			obj.put("noofmember", custRegObj.getMembersInFamily());
			obj.put("countryCode", countryCode);
			obj.put("stateCode", stateCode);
			obj.put("cityCode", cityCode);
			obj.put("postalCode", postalCode);
			obj.put("address1", custRegObj.getAddress1());
			obj.put("address2", custRegObj.getAddress2());
			obj.put("noofflats", custRegObj.getNoofflats());
			obj.put("accessMedia", custRegObj.getAccessMedia());
			/*imgByt = Commonutility.toReadFiletoBytes(profileImage);
			obj.put("imageweb", Commonutility.toByteAryToBase64EncodeStr(imgByt));
			obj.put("profileImageFileName", profileImageFileName);*/			
			obj.put("imageweb", "");
			obj.put("profileImageFileName", profileImageFileName);		
			if(profileImage!=null){
				obj.put("srcimgpath", profileImage.getAbsolutePath());						
				Commonutility.toWriteConsole("Step 2 : profileImage getCanonicalPath:: "+profileImage.getAbsolutePath());	
			} else {
				obj.put("srcimgpath", "");		
				Commonutility.toWriteConsole("Step 2 : profileImage getCanonicalPath:: else");	
			}
			data.put("data", obj);
			String jsonTextFinal = data.toString();
			jsonTextFinal = EncDecrypt.encrypt(jsonTextFinal);
			String temp = "ivrparams=" + URLEncoder.encode(jsonTextFinal);
			String finalUrl = getText("user.profile.update.action");
			String response = commonObj.jsonRequest(finalUrl, temp);
			Commonutility.toWriteConsole("Response : " + response);
			if(response!=null){
				Commonutility.toWriteConsole("Response :1 " + response);
			JSONObject json = new JSONObject(response);	
			Commonutility.toWriteConsole("Response :2 " + response);
			String statusCode = (String) Commonutility.toHasChkJsonRtnValObj(json, "statuscode");
			if(statusCode!=null && statusCode.equalsIgnoreCase("0")){
				Commonutility.toWriteConsole("Response :3 " + response);
				JSONObject json_data = json.getJSONObject("data");
				userProfileImage=json_data.getString("profileimage");
				if(profileImage!=null){
					ActionContext.getContext().getSession().put("userProfileImage", userProfileImage);
				}	
				
				
				alert.setCls("success");
				alert.setMsg(getText("Profile Updated Successfully"));
				alertList.add(alert);
				return "success";
			}	else if(statusCode!=null && statusCode.equalsIgnoreCase("1")){
				alert.setCls("danger");
				alert.setMsg(getText("Error in profile Updating"));
				alertList.add(alert);
				return "input";
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Error in profile Updating"));
				alertList.add(alert);
				return "input";
			}
			} else {
				alert.setCls("danger");
				alert.setMsg(getText("Error in profile Updating"));
				alertList.add(alert);
				return "input";
			}
		}catch(Exception ex){
			Commonutility.toWriteConsole("Exception Found in profileUpdate.class : "+ex);
			 log.logMessage("Exception Found in profileUpdate.class : "+ex, "error",profileUpdate.class);
		}finally{
			obj=null;
			data=null;
		}
		return result;
	}

	public static String toByteAryToBase64EncodeStr(byte[] pFileBytes){
 		String locRtnEncodeStr=null;
 		try{
 			locRtnEncodeStr= Base64.encodeBase64String(pFileBytes);
 		}catch(Exception e){
 			Commonutility.toWriteConsole("Exception toByteAryToBase64EncodeStr() : "+e);
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

	public String getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}

	public File getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(File profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImageContentType() {
		return profileImageContentType;
	}

	public void setProfileImageContentType(String profileImageContentType) {
		this.profileImageContentType = profileImageContentType;
	}

	public String getProfileImageFileName() {
		return profileImageFileName;
	}

	public void setProfileImageFileName(String profileImageFileName) {
		this.profileImageFileName = profileImageFileName;
	}

	public String getUserProfileImage() {
		return userProfileImage;
	}

	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}

	public String getAccessMedia() {
		return accessMedia;
	}

	public void setAccessMedia(String accessMedia) {
		this.accessMedia = accessMedia;
	}
	
	
}
