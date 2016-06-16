package com.suixin.vz.strike.model;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{	private static final long serialVersionUID = 1077239799L;	private List<TourPicList> tourPicList;	private String orderStr;
	public List<TourPicList> getTourPicList() {		return this.tourPicList;	}
	public void setTourPicList(List<TourPicList> tourPicList) {		this.tourPicList = tourPicList;	}
	public String getOrderStr() {		return this.orderStr;	}
	public void setOrderStr(String orderStr) {		this.orderStr = orderStr;	}
	public Data() {}
	public Data(List<TourPicList> tourPicList, String orderStr){
		super();		this.tourPicList = tourPicList;		this.orderStr = orderStr;
	}
	public String toString() {
		return "Data [tourPicList = " + tourPicList + ", orderStr = " + orderStr + "]";	}
}