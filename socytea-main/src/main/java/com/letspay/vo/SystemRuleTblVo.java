package com.letspay.vo;

import java.io.Serializable;
import java.util.Date;

public class SystemRuleTblVo implements Serializable {

  private static final long serialVersionUID = 1L;

  public SystemRuleTblVo() {
  }

  private int sysruleId;
  private String sysruleCode;
  private String sysruleDesc;
  private String condition;
  private String action;
  private Integer statusFlag;
  private Integer entryBy;
  private Date entryDateTime;

  public int getSysruleId() {
    return sysruleId;
  }

  public void setSysruleId(int sysruleId) {
    this.sysruleId = sysruleId;
  }

  public String getSysruleCode() {
    return sysruleCode;
  }

  public void setSysruleCode(String sysruleCode) {
    this.sysruleCode = sysruleCode;
  }

  public String getSysruleDesc() {
    return sysruleDesc;
  }

  public void setSysruleDesc(String sysruleDesc) {
    this.sysruleDesc = sysruleDesc;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public Integer getStatusFlag() {
    return statusFlag;
  }

  public void setStatusFlag(Integer statusFlag) {
    this.statusFlag = statusFlag;
  }

  public Integer getEntryBy() {
    return entryBy;
  }

  public void setEntryBy(Integer entryBy) {
    this.entryBy = entryBy;
  }

  public Date getEntryDateTime() {
    return entryDateTime;
  }

  public void setEntryDateTime(Date entryDateTime) {
    this.entryDateTime = entryDateTime;
  }

}
