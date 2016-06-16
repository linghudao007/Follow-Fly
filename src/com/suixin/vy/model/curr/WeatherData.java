package com.suixin.vy.model.curr;

import java.io.Serializable;
import java.util.List;

public class WeatherData implements Serializable{	private static final long serialVersionUID = 797429512L;	private String dayName;	private String dWindLevel;	private String t;	private String nightName;	private String dayId;	private String date;	private String nightId;	private String nWindLevel;
	public String getDayName() {		return this.dayName;	}
	public void setDayName(String dayName) {		this.dayName = dayName;	}
	public String getDWindLevel() {		return this.dWindLevel;	}
	public void setDWindLevel(String dWindLevel) {		this.dWindLevel = dWindLevel;	}
	public String getT() {		return this.t;	}
	public void setT(String t) {		this.t = t;	}
	public String getNightName() {		return this.nightName;	}
	public void setNightName(String nightName) {		this.nightName = nightName;	}
	public String getDayId() {		return this.dayId;	}
	public void setDayId(String dayId) {		this.dayId = dayId;	}
	public String getDate() {		return this.date;	}
	public void setDate(String date) {		this.date = date;	}
	public String getNightId() {		return this.nightId;	}
	public void setNightId(String nightId) {		this.nightId = nightId;	}
	public String getNWindLevel() {		return this.nWindLevel;	}
	public void setNWindLevel(String nWindLevel) {		this.nWindLevel = nWindLevel;	}
	public WeatherData() {}
	public WeatherData(String dayName, String dWindLevel, String t, String nightName, String dayId, String date, String nightId, String nWindLevel){
		super();		this.dayName = dayName;		this.dWindLevel = dWindLevel;		this.t = t;		this.nightName = nightName;		this.dayId = dayId;		this.date = date;		this.nightId = nightId;		this.nWindLevel = nWindLevel;
	}
	public String toString() {
		return "WeatherData [dayName = " + dayName + ", dWindLevel = " + dWindLevel + ", t = " + t + ", nightName = " + nightName + ", dayId = " + dayId + ", date = " + date + ", nightId = " + nightId + ", nWindLevel = " + nWindLevel + "]";	}
}