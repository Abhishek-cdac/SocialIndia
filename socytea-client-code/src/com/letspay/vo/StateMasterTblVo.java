package com.letspay.vo;

import java.io.Serializable;

public class StateMasterTblVo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer stateId;
  private CountryMasterTblVo countryId;
  private String stateName;

  public StateMasterTblVo(int stateID, String stateName) {
	  this.stateId =stateID;
	  
	  this.stateName =stateName;
	// TODO Auto-generated constructor stub
}

public Integer getStateId() {
    return stateId;
  }

  public void setStateId(Integer stateId) {
    this.stateId = stateId;
  }

  public CountryMasterTblVo getCountryId() {
    return countryId;
  }

  public void setCountryId(CountryMasterTblVo countryId) {
    this.countryId = countryId;
  }

  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

}
