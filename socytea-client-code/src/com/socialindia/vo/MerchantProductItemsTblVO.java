package com.socialindia.vo;

public class MerchantProductItemsTblVO {
	
	private String itemName;
	private String orderQty;
	private String supplyQty;
	private String commentid;
	private String itemstatus;
	private String flatnumber;
	private String blackname;
	
	public String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	public MerchantProductItemsTblVO(String itemname2, String orderqty2,
			String supplyqty2,String commentid ,String itemstatus) {
		this.itemName=itemname2;
		this.orderQty=orderqty2;
		this.supplyQty=supplyqty2;
		this.commentid=commentid;
		this.itemstatus=itemstatus;
		// TODO Auto-generated constructor stub
	}
	
	
	public MerchantProductItemsTblVO(String flatnumber2, String blackname2) {
		
		this.flatnumber=flatnumber2;
		this.blackname=blackname2;	
		// TODO Auto-generated constructor stub
	}
	public String getFlatnumber() {
		return flatnumber;
	}
	public void setFlatnumber(String flatnumber) {
		this.flatnumber = flatnumber;
	}
	public String getBlackname() {
		return blackname;
	}
	public void setBlackname(String blackname) {
		this.blackname = blackname;
	}
	public String getItemstatus() {
		return itemstatus;
	}
	public void setItemstatus(String itemstatus) {
		this.itemstatus = itemstatus;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}
	public String getSupplyQty() {
		return supplyQty;
	}
	public void setSupplyQty(String supplyQty) {
		this.supplyQty = supplyQty;
	}
	
	
	

}
