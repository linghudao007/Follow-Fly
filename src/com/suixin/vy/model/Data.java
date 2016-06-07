package com.suixin.vy.model;

import java.io.Serializable;
import java.util.List;


public class Data implements Serializable{	private static final long serialVersionUID = 1095732413L;	private List<BannerList> bannerList;	private List<UserList> userList;	private List<String> mixedList;	private String orderStr;	private List<TourPicList> tourPicList;	private List<PlanList> planList;
	public List<BannerList> getBannerList() {		return this.bannerList;	}
	public void setBannerList(List<BannerList> bannerList) {		this.bannerList = bannerList;	}
	public List<UserList> getUserList() {		return this.userList;	}
	public void setUserList(List<UserList> userList) {		this.userList = userList;	}
	public List<String> getMixedList() {		return this.mixedList;	}
	public void setMixedList(List<String> mixedList) {		this.mixedList = mixedList;	}
	public String getOrderStr() {		return this.orderStr;	}
	public void setOrderStr(String orderStr) {		this.orderStr = orderStr;	}
	public List<TourPicList> getTourPicList() {		return this.tourPicList;	}
	public void setTourPicList(List<TourPicList> tourPicList) {		this.tourPicList = tourPicList;	}
	public List<PlanList> getPlanList() {		return this.planList;	}
	public void setPlanList(List<PlanList> planList) {		this.planList = planList;	}
	public Data() {}
	public Data(List<BannerList> bannerList, List<UserList> userList, List<String> mixedList, String orderStr, List<TourPicList> tourPicList, List<PlanList> planList){
		super();		this.bannerList = bannerList;		this.userList = userList;		this.mixedList = mixedList;		this.orderStr = orderStr;		this.tourPicList = tourPicList;		this.planList = planList;
	}
	public String toString() {
		return "Data [bannerList = " + bannerList + ", userList = " + userList + ", mixedList = " + mixedList + ", orderStr = " + orderStr + ", tourPicList = " + tourPicList + ", planList = " + planList + "]";	}
}