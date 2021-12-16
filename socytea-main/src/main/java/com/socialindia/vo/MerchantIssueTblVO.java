package com.socialindia.vo;

public class MerchantIssueTblVO {
	private int issueid;
	private String issueDesc;
	
	public MerchantIssueTblVO(int issueid, String issueDesc) {
		this.issueid=issueid;
		this.issueDesc=issueDesc;
		
	}
	public int getIssueid() {
		return issueid;
	}
	public void setIssueid(int issueid) {
		this.issueid = issueid;
	}
	public String getIssueDesc() {
		return issueDesc;
	}
	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}
	
	

}
