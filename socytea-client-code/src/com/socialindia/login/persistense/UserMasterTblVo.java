package com.socialindia.login.persistense;


import java.io.Serializable;
import java.util.Date;

import com.letspay.uam.persistense.GroupMasterTblVo;



public class UserMasterTblVo implements Serializable {

  private static final long serialVersionUID = 1L;

  private int userId;
  private String userName;
  private String lastName;
  private String firstName;
  private String password;
  private String flatNo;
  private String dob;
  private String mobileNo;
  private String bloodType;
  private String emailId;
  private String address1;
  private String address2;
  private String gender;
  private int idCardType;
  private String idProofNo;
  private int cityId;
  private int countryCode;
  private int stateId;
  private String occupation;
  private int membersInFamily;
  private int loginCount;
  private Integer statusFlag;
  private Date loginDatetime;
  private int entryBy;
  private Date entryDatetime;
  private Date modifyDatetime;
  private String groupUniqId;
  private String pinCode;
  private String groupName;
  private int groupCode;
  private int noofflats;
  private int accessMedia;
  private GroupMasterTblVo groupId;
  
  private Integer loggedOut;
  private Integer ivrBnISONLINEFLG;
  private Integer numOfLogins = 2;
  private Integer currentLogins = 0;
  private Date resetDatetime;
  
  private String entryDatetimeFormated;
	private String modifyDatetimeFormated;
	private String countryName;
	private String stateName;
	private String cityName;
 

   public UserMasterTblVo(String userName,String firstName, String lastName,String dob,String address1,
		   String bloodType, String mNo,String eMail,Integer uId,String gName) {
	   this.userId=uId;
	   this.userName = userName;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.dob = dob;
	    this.address1 = address1;
	    this.bloodType = bloodType;
	    this.mobileNo = mNo;
	    this.emailId = eMail;
	    this.groupName = gName;
	  
  }

public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

 

  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

 

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }


  public Integer getStatusFlag() {
    return statusFlag;
  }

  public void setStatusFlag(Integer statusFlag) {
    this.statusFlag = statusFlag;
  }

  public Date getLoginDatetime() {
    return loginDatetime;
  }

  public void setLoginDatetime(Date loginDatetime) {
    this.loginDatetime = loginDatetime;
  }

  public int getEntryBy() {
    return entryBy;
  }

  public void setEntryBy(int ival) {
    this.entryBy = ival;
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

  public GroupMasterTblVo getGroupId() {
    return groupId;
  }

  public void setGroupId(GroupMasterTblVo groupId) {
    this.groupId = groupId;
  }

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getGroupName() {
	return groupName;
}

public void setGroupName(String groupName) {
	this.groupName = groupName;
}


public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public int getIdCardType() {
	return idCardType;
}

public void setIdCardType(int idCardType) {
	this.idCardType = idCardType;
}

public String getIdProofNo() {
	return idProofNo;
}

public void setIdProofNo(String idProofNo) {
	this.idProofNo = idProofNo;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getFlatNo() {
	return flatNo;
}

public void setFlatNo(String flatNo) {
	this.flatNo = flatNo;
}

public String getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = dob;
}

public String getBloodType() {
	return bloodType;
}

public void setBloodType(String bloodType) {
	this.bloodType = bloodType;
}

public String getAddress1() {
	return address1;
}

public void setAddress1(String address1) {
	this.address1 = address1;
}

public String getAddress2() {
	return address2;
}

public void setAddress2(String address2) {
	this.address2 = address2;
}

public int getCityId() {
	return cityId;
}

public void setCityId(int cityId) {
	this.cityId = cityId;
}

public int getCountryCode() {
	return countryCode;
}

public void setCountryCode(int countryCode) {
	this.countryCode = countryCode;
}

public int getStateId() {
	return stateId;
}

public void setStateId(int stateId) {
	this.stateId = stateId;
}

public String getOccupation() {
	return occupation;
}

public void setOccupation(String occupation) {
	this.occupation = occupation;
}

public int getMembersInFamily() {
	return membersInFamily;
}

public void setMembersInFamily(int membersInFamily) {
	this.membersInFamily = membersInFamily;
}

public int getLoginCount() {
	return loginCount;
}

public void setLoginCount(int loginCount) {
	this.loginCount = loginCount;
}

public String getGroupUniqId() {
	return groupUniqId;
}

public void setGroupUniqId(String groupUniqId) {
	this.groupUniqId = groupUniqId;
}

public int getGroupCode() {
	return groupCode;
}

public void setGroupCode(int groupCode) {
	this.groupCode = groupCode;
}

public String getPinCode() {
	return pinCode;
}

public void setPinCode(String pinCode) {
	this.pinCode = pinCode;
}



public int getAccessMedia() {
	return accessMedia;
}

public void setAccessMedia(int accessMedia) {
	this.accessMedia = accessMedia;
}

public int getNoofflats() {
	return noofflats;
}

public void setNoofflats(int noofflats) {
	this.noofflats = noofflats;
}

public String getEntryDatetimeFormated() {
	return entryDatetimeFormated;
}

public void setEntryDatetimeFormated(String entryDatetimeFormated) {
	this.entryDatetimeFormated = entryDatetimeFormated;
}

public String getModifyDatetimeFormated() {
	return modifyDatetimeFormated;
}

public void setModifyDatetimeFormated(String modifyDatetimeFormated) {
	this.modifyDatetimeFormated = modifyDatetimeFormated;
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

public Integer getLoggedOut() {
	return loggedOut;
}

public void setLoggedOut(Integer loggedOut) {
	this.loggedOut = loggedOut;
}

public Integer getIvrBnISONLINEFLG() {
	return ivrBnISONLINEFLG;
}

public void setIvrBnISONLINEFLG(Integer ivrBnISONLINEFLG) {
	this.ivrBnISONLINEFLG = ivrBnISONLINEFLG;
}

public Integer getNumOfLogins() {
	return numOfLogins;
}

public void setNumOfLogins(Integer numOfLogins) {
	this.numOfLogins = numOfLogins;
}

public Integer getCurrentLogins() {
	return currentLogins;
}

public void setCurrentLogins(Integer currentLogins) {
	this.currentLogins = currentLogins;
}

public Date getResetDatetime() {
	return resetDatetime;
}

public void setResetDatetime(Date resetDatetime) {
	this.resetDatetime = resetDatetime;
}






}
