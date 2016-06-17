package com.suixin.vy.model.rank;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{
	private CityDuckr cityDuckr;
	private List<DuckrBoradList> duckrBoradList;
	private List<HotDuckrList> hotDuckrList;
	private OnlineDuckr onlineDuckr;
	private long orderStr;
	private List<Object> storyList;
	
	public Data(CityDuckr cityDuckr, List<DuckrBoradList> duckrBoradList,
			List<HotDuckrList> hotDuckrList, OnlineDuckr onlineDuckr,
			long orderStr, List<Object> storyList) {
		super();
		this.cityDuckr = cityDuckr;
		this.duckrBoradList = duckrBoradList;
		this.hotDuckrList = hotDuckrList;
		this.onlineDuckr = onlineDuckr;
		this.orderStr = orderStr;
		this.storyList = storyList;
	}
	public Data() {
	}
	@Override
	public String toString() {
		return "Data [cityDuckr=" + cityDuckr + ", duckrBoradList="
				+ duckrBoradList + ", hotDuckrList=" + hotDuckrList
				+ ", onlineDuckr=" + onlineDuckr + ", orderStr=" + orderStr
				+ ", storyList=" + storyList + "]";
	}
	public CityDuckr getCityDuckr() {
		return cityDuckr;
	}
	public void setCityDuckr(CityDuckr cityDuckr) {
		this.cityDuckr = cityDuckr;
	}
	public List<DuckrBoradList> getDuckrBoradList() {
		return duckrBoradList;
	}
	public void setDuckrBoradList(List<DuckrBoradList> duckrBoradList) {
		this.duckrBoradList = duckrBoradList;
	}
	public List<HotDuckrList> getHotDuckrList() {
		return hotDuckrList;
	}
	public void setHotDuckrList(List<HotDuckrList> hotDuckrList) {
		this.hotDuckrList = hotDuckrList;
	}
	public OnlineDuckr getOnlineDuckr() {
		return onlineDuckr;
	}
	public void setOnlineDuckr(OnlineDuckr onlineDuckr) {
		this.onlineDuckr = onlineDuckr;
	}
	public long getOrderStr() {
		return orderStr;
	}
	public void setOrderStr(long orderStr) {
		this.orderStr = orderStr;
	}
	public List<Object> getStoryList() {
		return storyList;
	}
	public void setStoryList(List<Object> storyList) {
		this.storyList = storyList;
	}
}
