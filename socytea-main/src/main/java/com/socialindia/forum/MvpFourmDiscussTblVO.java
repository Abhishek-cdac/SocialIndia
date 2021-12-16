package com.socialindia.forum;

// default package
// Generated Jul 21, 2016 6:06:03 PM by Hibernate Tools 3.4.0.CR1


/**
 * MvpFourmDiscussTbl generated by hbm2java
 */
public class MvpFourmDiscussTblVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer discussId;
	private int topicsId;
	private String discussionDesc;
	private String firstName;
	private String imageName;
	private String entryDate;
	
	private String splDate;
	private String splTime;
	private String lastName;
	private String mobileNo;
	private int userId;
	
	private int topicsDescLen;
	private String topicsDescMin;
	private int commentLen;
	private String commentsMin;
	public MvpFourmDiscussTblVO() {
	}

	

	public MvpFourmDiscussTblVO(int userId,String  firstName,
			String discussionDesc, String imageName,String entrydate,String splDate,String splTime,String lastName,
			String mobileNo,int commentLen,String commentsMin) {
		this.userId = userId;
		this.firstName = firstName;
		this.discussionDesc = discussionDesc;
		this.imageName = imageName;
		this.entryDate = entrydate;
		this.splDate = splDate;
		this.splTime = splTime;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.commentLen = commentLen;
		this.commentsMin = commentsMin;
		
	}

	public Integer getDiscussId() {
		return this.discussId;
	}

	public void setDiscussId(Integer discussId) {
		this.discussId = discussId;
	}

	public String getDiscussionDesc() {
		return this.discussionDesc;
	}

	public void setDiscussionDesc(String discussionDesc) {
		this.discussionDesc = discussionDesc;
	}

	public int getTopicsId() {
		return topicsId;
	}

	public void setTopicsId(int topicsId) {
		this.topicsId = topicsId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getImageName() {
		return imageName;
	}



	public String getEntrydate() {
		return entryDate;
	}



	public void setEntrydate(String entrydate) {
		this.entryDate = entrydate;
	}



	public void setImageName(String imageName) {
		this.imageName = imageName;
	}



	public String getEntryDate() {
		return entryDate;
	}



	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}



	public String getSplDate() {
		return splDate;
	}



	public void setSplDate(String splDate) {
		this.splDate = splDate;
	}



	public String getSplTime() {
		return splTime;
	}



	public void setSplTime(String splTime) {
		this.splTime = splTime;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getMobileNo() {
		return mobileNo;
	}



	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public int getTopicsDescLen() {
		return topicsDescLen;
	}



	public void setTopicsDescLen(int topicsDescLen) {
		this.topicsDescLen = topicsDescLen;
	}



	public String getTopicsDescMin() {
		return topicsDescMin;
	}



	public void setTopicsDescMin(String topicsDescMin) {
		this.topicsDescMin = topicsDescMin;
	}



	public int getCommentLen() {
		return commentLen;
	}



	public void setCommentLen(int commentLen) {
		this.commentLen = commentLen;
	}



	public String getCommentsMin() {
		return commentsMin;
	}



	public void setCommentsMin(String commentsMin) {
		this.commentsMin = commentsMin;
	}

}
