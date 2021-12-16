package com.letspay.vo;

import java.io.Serializable;
import java.util.Date;

public class IsdcodeMasterTblVo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer isdCodeid;
  private Integer isdCodevalue;
  private CountryMasterTblVo countryId;
  private Integer entryBy;
  private Date entryDatetime;
  private Date modifyDatetime;

  public Integer getIsdCodeid() {
    return isdCodeid;
  }

  public void setIsdCodeid(Integer isdCodeid) {
    this.isdCodeid = isdCodeid;
  }

  public Integer getIsdCodevalue() {
    return isdCodevalue;
  }

  public void setIsdCodevalue(Integer isdCodevalue) {
    this.isdCodevalue = isdCodevalue;
  }

  public CountryMasterTblVo getCountryId() {
    return countryId;
  }

  public void setCountryId(CountryMasterTblVo countryId) {
    this.countryId = countryId;
  }

  public Integer getEntryBy() {
    return entryBy;
  }

  public void setEntryBy(Integer entryBy) {
    this.entryBy = entryBy;
  }

  public Date getEntryDatetime() {
    return entryDatetime;
  }

  public void setEntryDatetime(Date entryDatetime) {
    this.entryDatetime = entryDatetime;
  }

  public Date getModifyDatetime() {
    return modifyDatetime;
  }

  public void setModifyDatetime(Date modifyDatetime) {
    this.modifyDatetime = modifyDatetime;
  }

}
