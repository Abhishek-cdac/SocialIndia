package com.letspay.vo;

import java.io.Serializable;

public class CityMasterTblVo implements Serializable {

  private static final long serialVersionUID = 1L;
  private int cityId;
  private StateMasterTblVo stateId;
  private String cityName;

  public CityMasterTblVo(int cityID, String cityName) {
	// TODO Auto-generated constructor stub
	  this.cityId =cityID;
	  this.cityName=cityName;
	  
}

public int getCityId() {
    return cityId;
  }

  public void setCityId(int cityId) {
    this.cityId = cityId;
  }

  public StateMasterTblVo getStateId() {
    return stateId;
  }

  public void setStateId(StateMasterTblVo stateId) {
    this.stateId = stateId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

}
