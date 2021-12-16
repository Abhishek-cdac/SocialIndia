package com.socialindia.townShipMgmt;

import com.letspay.uam.persistense.TownshipMstTbl;
import com.opensymphony.xwork2.ActionSupport;

public class createTownShip extends ActionSupport {
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

TownshipMstTbl townshipObj=new TownshipMstTbl();
	
	private String countryId;
	private String stateId;
	private String cityId;
	private String pinCode;
	public String execute(){				
		return SUCCESS;
	}
	
	public TownshipMstTbl getTownShipMst() {
		return townshipObj;
	}
	public void setTownShipMst(TownshipMstTbl townshipObj) {
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

}
