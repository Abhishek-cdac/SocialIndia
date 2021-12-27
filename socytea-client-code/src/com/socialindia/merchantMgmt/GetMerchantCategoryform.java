package com.socialindia.merchantMgmt;

import com.opensymphony.xwork2.ActionSupport;

public class GetMerchantCategoryform extends ActionSupport{
	private Integer merchantCategory;
	public String execute(){
		try{
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public Integer getMerchantCategory() {
		return merchantCategory;
	}
	public void setMerchantCategory(Integer merchantCategory) {
		this.merchantCategory = merchantCategory;
	}

}
