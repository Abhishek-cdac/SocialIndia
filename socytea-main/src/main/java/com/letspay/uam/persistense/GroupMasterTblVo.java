package com.letspay.uam.persistense;

import com.letspay.login.persistense.UserMasterTblVo;

import java.io.Serializable;
import java.util.Date;

public class GroupMasterTblVo implements Serializable {

  private static final long serialVersionUID = 1L;
  private int groupCode;
  private String  groupCodeId;
  private String groupName;
  private String statusFlag;
  private int entryBy;
  private Integer updateBy;
  private Date entryDatetime;
  private String entryDate;
  private Date modifyDatetime;

  public GroupMasterTblVo(String gName, String groupcode, String status,
      String entrydate,int groupid) {
    this.groupName = gName;
    this.groupCodeId = groupcode;
    this.statusFlag = status;
    this.entryDate = entrydate;
    this.groupCode=groupid;

  }

  public int getGroupCode() {
    return groupCode;
  }

  public void setGroupCode(int groupCode) {
    this.groupCode = groupCode;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getStatusFlag() {
    return statusFlag;
  }

  public void setStatusFlag(String statusFlag) {
    this.statusFlag = statusFlag;
  }

  
  public int getEntryBy() {
	return entryBy;
}

public void setEntryBy(int entryBy) {
	this.entryBy = entryBy;
}

public Integer getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(Integer updateBy) {
    this.updateBy = updateBy;
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

public String getEntryDate() {
	return entryDate;
}

public void setEntryDate(String entryDate) {
	this.entryDate = entryDate;
}

public String getGroupCodeId() {
	return groupCodeId;
}

public void setGroupCodeId(String groupCodeId) {
	this.groupCodeId = groupCodeId;
}

}
