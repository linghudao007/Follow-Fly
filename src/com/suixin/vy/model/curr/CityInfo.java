package com.suixin.vy.model.curr;

import java.io.Serializable;
import java.util.List;

public class CityInfo implements Serializable{	private static final long serialVersionUID = 384212882L;	private String cityName;	private String cityImage;	private String cityShortName;	private String cityThumbImage;
	public String getCityName() {		return this.cityName;	}
	public void setCityName(String cityName) {		this.cityName = cityName;	}
	public String getCityImage() {		return this.cityImage;	}
	public void setCityImage(String cityImage) {		this.cityImage = cityImage;	}
	public String getCityShortName() {		return this.cityShortName;	}
	public void setCityShortName(String cityShortName) {		this.cityShortName = cityShortName;	}
	public String getCityThumbImage() {		return this.cityThumbImage;	}
	public void setCityThumbImage(String cityThumbImage) {		this.cityThumbImage = cityThumbImage;	}
	public CityInfo() {}
	public CityInfo(String cityName, String cityImage, String cityShortName, String cityThumbImage){
		super();		this.cityName = cityName;		this.cityImage = cityImage;		this.cityShortName = cityShortName;		this.cityThumbImage = cityThumbImage;
	}
	public String toString() {
		return "CityInfo [cityName = " + cityName + ", cityImage = " + cityImage + ", cityShortName = " + cityShortName + ", cityThumbImage = " + cityThumbImage + "]";	}
}