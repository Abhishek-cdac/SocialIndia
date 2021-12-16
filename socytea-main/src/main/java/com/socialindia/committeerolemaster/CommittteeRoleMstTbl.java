package com.socialindia.committeerolemaster;

// default package
// Generated Jul 2, 2016 4:59:50 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CommittteeRoleMstTbl generated by hbm2java
 */
public class CommittteeRoleMstTbl implements java.io.Serializable {

	private Integer roleId;
	private String roleName;
	private String roleDescp;
	private Integer entryBy;
	private Integer status;
	private Date entryDatetime;
	private Date modifyDatetime;

	public CommittteeRoleMstTbl() {
	}

	

	public CommittteeRoleMstTbl(Integer cmmteeid, String cmmteeName,
			Integer cmmteestatus) {
		// TODO Auto-generated constructor stub
		this.roleId =cmmteeid;
		this.roleName = cmmteeName;
		this.status=cmmteestatus;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescp() {
		return this.roleDescp;
	}

	public void setRoleDescp(String roleDescp) {
		this.roleDescp = roleDescp;
	}

	public Integer getEntryBy() {
		return this.entryBy;
	}

	public void setEntryBy(Integer entryBy) {
		this.entryBy = entryBy;
	}

	public Date getEntryDatetime() {
		return this.entryDatetime;
	}

	public void setEntryDatetime(Date entryDatetime) {
		this.entryDatetime = entryDatetime;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
