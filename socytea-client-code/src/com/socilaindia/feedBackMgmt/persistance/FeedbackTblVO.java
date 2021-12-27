package com.socilaindia.feedBackMgmt.persistance;

public class FeedbackTblVO {
	private String dbkby_lname;
	private String feedbackdate;
	private String dbkby_fname;
	private String feedbacktext;
	private String feedbackid;
	private String fdbkby_userid;
	private String feedbackrating;
	private String feedbackforusrtyp;
	private String feed_group;
	private String feedbacktoname;
	private String lbrserviceid;
	private String feedbacttoid;
	public FeedbackTblVO(String dbkby_lname,String feedbackdate, String dbkby_fname, String feedbacktext, String feedbackid, String fdbkby_userid, String feedbackrating,String feedbacktoname,String feed_group,String lbrserviceid,String feedbacttoid) {
		super();
		this.dbkby_lname = dbkby_lname;
		this.feedbackdate = feedbackdate;
		this.dbkby_fname= dbkby_fname;
		this.feedbacktext =feedbacktext;
		this.feedbackid = feedbackid;
		this.fdbkby_userid = fdbkby_userid;
		this.feedbackrating=feedbackrating;
		this.feedbacktoname=feedbacktoname;
		this.feed_group=feed_group;
		this.lbrserviceid=lbrserviceid;
		this.feedbacttoid=feedbacttoid;
		
	}
	public String getDbkby_lname() {
		return dbkby_lname;
	}
	public void setDbkby_lname(String dbkby_lname) {
		this.dbkby_lname = dbkby_lname;
	}
	public String getFeedbackdate() {
		return feedbackdate;
	}
	public void setFeedbackdate(String feedbackdate) {
		this.feedbackdate = feedbackdate;
	}
	public String getDbkby_fname() {
		return dbkby_fname;
	}
	public void setDbkby_fname(String dbkby_fname) {
		this.dbkby_fname = dbkby_fname;
	}
	public String getFeedbacktext() {
		return feedbacktext;
	}
	public void setFeedbacktext(String feedbacktext) {
		this.feedbacktext = feedbacktext;
	}
	public String getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
	}
	public String getFdbkby_userid() {
		return fdbkby_userid;
	}
	public void setFdbkby_userid(String fdbkby_userid) {
		this.fdbkby_userid = fdbkby_userid;
	}
	public String getFeedbackrating() {
		return feedbackrating;
	}
	public void setFeedbackrating(String feedbackrating) {
		this.feedbackrating = feedbackrating;
	}
	public String getFeedbackforusrtyp() {
		return feedbackforusrtyp;
	}
	public void setFeedbackforusrtyp(String feedbackforusrtyp) {
		this.feedbackforusrtyp = feedbackforusrtyp;
	}
	public String getFeed_group() {
		return feed_group;
	}
	public void setFeed_group(String feed_group) {
		this.feed_group = feed_group;
	}
	public String getFeedbacktoname() {
		return feedbacktoname;
	}
	public void setFeedbacktoname(String feedbacktoname) {
		this.feedbacktoname = feedbacktoname;
	}
	public String getLbrserviceid() {
		return lbrserviceid;
	}
	public void setLbrserviceid(String lbrserviceid) {
		this.lbrserviceid = lbrserviceid;
	}
	public String getFeedbacttoid() {
		return feedbacttoid;
	}
	public void setFeedbacttoid(String feedbacttoid) {
		this.feedbacttoid = feedbacttoid;
	}
	
	
	

}
