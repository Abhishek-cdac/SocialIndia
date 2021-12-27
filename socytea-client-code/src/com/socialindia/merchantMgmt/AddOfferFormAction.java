package com.socialindia.merchantMgmt;

import com.opensymphony.xwork2.ActionSupport;
import com.socialindia.vo.MerchantTblVO;

public class AddOfferFormAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MerchantTblVO merchantobj = new MerchantTblVO();
	public String execute(){
		try{
			
		}catch (Exception  e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public MerchantTblVO getMerchantobj() {
		return merchantobj;
	}
	public void setMerchantobj(MerchantTblVO merchantobj) {
		this.merchantobj = merchantobj;
	}
	

}
