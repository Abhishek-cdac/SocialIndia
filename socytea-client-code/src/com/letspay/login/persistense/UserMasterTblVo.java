package com.letspay.login.persistense;

import com.letspay.uam.persistense.GroupMasterTblVo;

import java.io.Serializable;
import java.util.Date;



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
  private String isdCode;
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
  private String groupName;
  private int groupCode;
  private String pinCode;
  private Integer loggedOut;
  private Integer ivrBnISONLINEFLG;
  
  private String committeeRole;
  private String townshipName;
  private String societyName;
  private int accessChannel;
  private String noOfBlocks;
  private String noofFlats;
  private String wingsname;
  private String flatnoname;
  private String userImage;
  
  private GroupMasterTblVo groupId;
 
  private Integer postalid;
  
private String countryname;
private String statename;
private String cityname;
private String pstlcodename;
private String idCardTypeName;
private String Gender_txt;

private Integer numOfLogins = 2;
private Integer currentLogins = 0;
private Date resetDatetime;


   public UserMasterTblVo(String userName,String firstName, String lastName,String dob,String address1,
		   String bloodType, String mNo,String eMail,Integer uId,String gName,String commrole,String townname,String socname,int accchannel, String pGroupcodestr) {
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
	    
	    this.committeeRole = commrole;
	    this.townshipName = townname;
	    this.societyName = socname;
	    this.accessChannel = accchannel;	
	    this.groupUniqId = pGroupcodestr;
	  
  }

public String getIdCardTypeName() {
	return idCardTypeName;
}

public void setIdCardTypeName(String idCardTypeName) {
	this.idCardTypeName = idCardTypeName;
}

public String getWingsname() {
	return wingsname;
}

public void setWingsname(String wingsname) {
	this.wingsname = wingsname;
}

public String getFlatnoname() {
	return flatnoname;
}

public void setFlatnoname(String flatnoname) {
	this.flatnoname = flatnoname;
}

public String getCountryname() {
	return countryname;
}

public void setCountryname(String countryname) {
	this.countryname = countryname;
}

public String getStatename() {
	return statename;
}

public void setStatename(String statename) {
	this.statename = statename;
}

public String getCityname() {
	return cityname;
}

public void setCityname(String cityname) {
	this.cityname = cityname;
}

public String getPstlcodename() {
	return pstlcodename;
}

public void setPstlcodename(String pstlcodename) {
	this.pstlcodename = pstlcodename;
}

public String getNoofFlats() {
	return noofFlats;
}

public void setNoofFlats(String noofFlats) {
	this.noofFlats = noofFlats;
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

public String getNoOfBlocks() {
	return noOfBlocks;
}

public void setNoOfBlocks(String noOfBlocks) {
	this.noOfBlocks = noOfBlocks;
}

public String getCommitteeRole() {
	return committeeRole;
}

public void setCommitteeRole(String committeeRole) {
	this.committeeRole = committeeRole;
}

public String getTownshipName() {
	return townshipName;
}

public void setTownshipName(String townshipName) {
	this.townshipName = townshipName;
}

public String getSocietyName() {
	return societyName;
}

public void setSocietyName(String societyName) {
	this.societyName = societyName;
}

public int getAccessChannel() {
	return accessChannel;
}

public void setAccessChannel(int accessChannel) {
	this.accessChannel = accessChannel;
}

public String getIsdCode() {
	return isdCode;
}

public void setIsdCode(String isdCode) {
	this.isdCode = isdCode;
}

public Integer getPostalid() {
	return postalid;
}

public void setPostalid(Integer postalid) {
	this.postalid = postalid;
}

public String getGender_txt() {
	return Gender_txt;
}

public void setGender_txt(String gender_txt) {
	Gender_txt = gender_txt;
}

public String getUserImage() {
	return userImage;
}

public void setUserImage(String userImage) {
	this.userImage = userImage;
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
