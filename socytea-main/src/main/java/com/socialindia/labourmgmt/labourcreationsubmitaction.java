package com.socialindia.labourmgmt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.letspay.common.Common;
import com.letspay.common.CommonDao;
import com.letspay.vo.AlertVo;
import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.generalmgnt.persistance.PostalCodeMasterTblVO;
import com.socialindia.labour.persistance.LaborProfileTblVO;

public class labourcreationsubmitaction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LaborProfileTblVO labRegObj = new LaborProfileTblVO(0, null, null, null, null, null, null, null, null, 0.0, null, null, null, null);
	PostalCodeMasterTblVO pstlcodeObj = new PostalCodeMasterTblVO(0, null);
	private AlertVo alert = new AlertVo();
	private List<AlertVo> alertList = new ArrayList<AlertVo>();
	byte imgByt[] = new byte[1024];
	private File staffImage;
	private String staffImageFileName;
	private String staffProfileImage;
	private int staffsessID;
	private String Country_id;
	private String State_id;
	private String City_id;
	JSONObject obj = new JSONObject();
	JSONObject data = new JSONObject();
	private Common commonObj = new CommonDao();

	public String execute() {
		return SUCCESS;
	}

	public String Labourcreation() {
		return SUCCESS;
	}

	public LaborProfileTblVO getLabRegObj() {
		return labRegObj;
	}

	public void setLabRegObj(LaborProfileTblVO labRegObj) {
		this.labRegObj = labRegObj;
	}

	public PostalCodeMasterTblVO getPstlcodeObj() {
		return pstlcodeObj;
	}

	public void setPstlcodeObj(PostalCodeMasterTblVO pstlcodeObj) {
		this.pstlcodeObj = pstlcodeObj;
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

	public File getStaffImage() {
		return staffImage;
	}

	public void setStaffImage(File staffImage) {
		this.staffImage = staffImage;
	}

	public String getStaffImageFileName() {
		return staffImageFileName;
	}

	public void setStaffImageFileName(String staffImageFileName) {
		this.staffImageFileName = staffImageFileName;
	}

	public String getStaffProfileImage() {
		return staffProfileImage;
	}

	public void setStaffProfileImage(String staffProfileImage) {
		this.staffProfileImage = staffProfileImage;
	}

	public Common getCommonObj() {
		return commonObj;
	}

	public void setCommonObj(Common commonObj) {
		this.commonObj = commonObj;
	}

	public int getStaffsessID() {
		return staffsessID;
	}

	public void setStaffsessID(int staffsessID) {
		this.staffsessID = staffsessID;
	}

	public String getCountry_id() {
		return Country_id;
	}

	public void setCountry_id(String country_id) {
		Country_id = country_id;
	}

	public String getState_id() {
		return State_id;
	}

	public void setState_id(String state_id) {
		State_id = state_id;
	}

	public String getCity_id() {
		return City_id;
	}

	public void setCity_id(String city_id) {
		City_id = city_id;
	}

}
