package com.suixin.vy.model.curr;

import java.io.Serializable;
import java.util.List;

import com.suixin.vy.model.PlanList;
import com.suixin.vy.model.TourPicList;

public class Data implements Serializable{	private static final long serialVersionUID = 884781017L;	private List<PlanList> planList;	private List<String> mixedList;	private WeatherData weatherData;	private String orderStr;	private List<TourPicList> tourPicList;	private CityInfo cityInfo;
	public List<PlanList> getPlanList() {		return this.planList;	}
	public void setPlanList(List<PlanList> planList) {		this.planList = planList;	}
	public List<String> getMixedList() {		return this.mixedList;	}
	public void setMixedList(List<String> mixedList) {		this.mixedList = mixedList;	}
	public WeatherData getWeatherData() {		return this.weatherData;	}
	public void setWeatherData(WeatherData weatherData) {		this.weatherData = weatherData;	}
	public String getOrderStr() {		return this.orderStr;	}
	public void setOrderStr(String orderStr) {		this.orderStr = orderStr;	}
	public List<TourPicList> getTourPicList() {		return this.tourPicList;	}
	public void setTourPicList(List<TourPicList> tourPicList) {		this.tourPicList = tourPicList;	}
	public CityInfo getCityInfo() {		return this.cityInfo;	}
	public void setCityInfo(CityInfo cityInfo) {		this.cityInfo = cityInfo;	}
	public Data() {}
	public Data(List<PlanList> planList, List<String> mixedList, WeatherData weatherData, String orderStr, List<TourPicList> tourPicList, CityInfo cityInfo){
		super();		this.planList = planList;		this.mixedList = mixedList;		this.weatherData = weatherData;		this.orderStr = orderStr;		this.tourPicList = tourPicList;		this.cityInfo = cityInfo;
	}
	public String toString() {
		return "Data [planList = " + planList + ", mixedList = " + mixedList + ", weatherData = " + weatherData + ", orderStr = " + orderStr + ", tourPicList = " + tourPicList + ", cityInfo = " + cityInfo + "]";	}
}