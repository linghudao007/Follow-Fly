package com.suixin.vy.model.pack;

import java.io.Serializable;
import java.util.List;

public class TourThemes implements Serializable{	private static final long serialVersionUID = 856760523L;	private String title;	private String themeDesc;	private String coverThumbUrl;	private long tourThemeId;	private String coverUrl;
	public String getTitle() {		return this.title;	}
	public void setTitle(String title) {		this.title = title;	}
	public String getThemeDesc() {		return this.themeDesc;	}
	public void setThemeDesc(String themeDesc) {		this.themeDesc = themeDesc;	}
	public String getCoverThumbUrl() {		return this.coverThumbUrl;	}
	public void setCoverThumbUrl(String coverThumbUrl) {		this.coverThumbUrl = coverThumbUrl;	}
	public long getTourThemeId() {		return this.tourThemeId;	}
	public void setTourThemeId(long tourThemeId) {		this.tourThemeId = tourThemeId;	}
	public String getCoverUrl() {		return this.coverUrl;	}
	public void setCoverUrl(String coverUrl) {		this.coverUrl = coverUrl;	}
	public TourThemes() {}
	public TourThemes(String title, String themeDesc, String coverThumbUrl, long tourThemeId, String coverUrl){
		super();		this.title = title;		this.themeDesc = themeDesc;		this.coverThumbUrl = coverThumbUrl;		this.tourThemeId = tourThemeId;		this.coverUrl = coverUrl;
	}
	public String toString() {
		return "TourThemes [title = " + title + ", themeDesc = " + themeDesc + ", coverThumbUrl = " + coverThumbUrl + ", tourThemeId = " + tourThemeId + ", coverUrl = " + coverUrl + "]";	}
}