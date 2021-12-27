package com.socialindia.vo;

import com.opensymphony.xwork2.ActionSupport;

public class MerchantCategoryDetail extends ActionSupport{
	
	private String typeName;
	private String quantity;
	private String power;
	private String companyName;
	private String cuisines;
	private int breakFast;
	private int lunch;
	private int dinner;
	private int indoor;
	private int delivery;
	private int takeAway;
	private int cafe;
	private int luxuryDining;
	private int nightlife;
	private String breakFastavl;
	private String lunchavl;
	private String dinneravl;
	private String indooravl;
	private String deliveryavl;
	private String takeAwayavl;
	private String cafeavl;
	private String luxuryDiningavl;
	private String nightlifeavl;
	
	public MerchantCategoryDetail(){}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCuisines() {
		return cuisines;
	}

	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}

	public int getBreakFast() {
		return breakFast;
	}

	public void setBreakFast(int breakFast) {
		this.breakFast = breakFast;
	}

	public int getLunch() {
		return lunch;
	}

	public void setLunch(int lunch) {
		this.lunch = lunch;
	}

	public int getDinner() {
		return dinner;
	}

	public void setDinner(int dinner) {
		this.dinner = dinner;
	}

	public int getIndoor() {
		return indoor;
	}

	public void setIndoor(int indoor) {
		this.indoor = indoor;
	}

	public int getDelivery() {
		return delivery;
	}

	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}

	public int getTakeAway() {
		return takeAway;
	}

	public void setTakeAway(int takeAway) {
		this.takeAway = takeAway;
	}

	public int getCafe() {
		return cafe;
	}

	public void setCafe(int cafe) {
		this.cafe = cafe;
	}

	public int getLuxuryDining() {
		return luxuryDining;
	}

	public void setLuxuryDining(int luxuryDining) {
		this.luxuryDining = luxuryDining;
	}

	public int getNightlife() {
		return nightlife;
	}

	public void setNightlife(int nightlife) {
		this.nightlife = nightlife;
	}

	
	public String getBreakFastavl() {
		if(breakFast==1){
			breakFastavl="Yes";
		}else{
			breakFastavl="No";
		}
		
		return breakFastavl;
	}

	public void setBreakFastavl(String breakFastavl) {
		this.breakFastavl = breakFastavl;
	}

	public String getLunchavl() {
		if(lunch==1){
			lunchavl="Yes";
		}else{
			lunchavl="No";
		}
		return lunchavl;
	}

	public void setLunchavl(String lunchavl) {
		this.lunchavl = lunchavl;
	}

	public String getDinneravl() {
		if(dinner==1){
			dinneravl="Yes";
		}else{
			dinneravl="No";
		}
		return dinneravl;
	}

	public void setDinneravl(String dinneravl) {
		this.dinneravl = dinneravl;
	}

	public String getIndooravl() {
		if(indoor==1){
			indooravl="Yes";
		}else{
			indooravl="No";
		}
		return indooravl;
	}

	public void setIndooravl(String indooravl) {
		this.indooravl = indooravl;
	}

	public String getDeliveryavl() {
		if(delivery==1){
			deliveryavl="Yes";
		}else{
			deliveryavl="No";
		}
		return deliveryavl;
	}

	public void setDeliveryavl(String deliveryavl) {
		this.deliveryavl = deliveryavl;
	}

	public String getTakeAwayavl() {
		if(takeAway==1){
			takeAwayavl="Yes";
		}else{
			takeAwayavl="No";
		}
		return takeAwayavl;
	}

	public void setTakeAwayavl(String takeAwayavl) {
		this.takeAwayavl = takeAwayavl;
	}

	public String getCafeavl() {
		if(cafe==1){
			cafeavl="Yes";
		}else{
			cafeavl="No";
		}
		return cafeavl;
	}

	public void setCafeavl(String cafeavl) {
		this.cafeavl = cafeavl;
	}

	public String getLuxuryDiningavl() {
		if(luxuryDining==1){
			luxuryDiningavl="Yes";
		}else{
			luxuryDiningavl="No";
		}
		return luxuryDiningavl;
	}

	public void setLuxuryDiningavl(String luxuryDiningavl) {
		this.luxuryDiningavl = luxuryDiningavl;
	}

	public String getNightlifeavl() {
		if(nightlife==1){
			nightlifeavl="Yes";
		}else{
			nightlifeavl="No";
		}
		return nightlifeavl;
	}

	public void setNightlifeavl(String nightlifeavl) {
		this.nightlifeavl = nightlifeavl;
	}
	
	
	

}
