package com.suixin.vy.model.select;

import java.io.Serializable;
import java.util.List;

public class ChildThemes implements Serializable{	private static final long serialVersionUID = 22891798L;	private long tourThemeId;	private String coverUrl;	private List<Object> childThemes;	private String coverThumbUrl;	private String iconUrl;	private String themeDesc;	private String title;
	public long getTourThemeId() {		return this.tourThemeId;	}
	public void setTourThemeId(long tourThemeId) {		this.tourThemeId = tourThemeId;	}
	public String getCoverUrl() {		return this.coverUrl;	}
	public void setCoverUrl(String coverUrl) {		this.coverUrl = coverUrl;	}
	public List<Object> getChildThemes() {		return this.childThemes;	}
	public void setChildThemes(List<Object> childThemes) {		this.childThemes = childThemes;	}
	public String getCoverThumbUrl() {		return this.coverThumbUrl;	}
	public void setCoverThumbUrl(String coverThumbUrl) {		this.coverThumbUrl = coverThumbUrl;	}
	public String getIconUrl() {		return this.iconUrl;	}
	public void setIconUrl(String iconUrl) {		this.iconUrl = iconUrl;	}
	public String getThemeDesc() {		return this.themeDesc;	}
	public void setThemeDesc(String themeDesc) {		this.themeDesc = themeDesc;	}
	public String getTitle() {		return this.title;	}
	public void setTitle(String title) {		this.title = title;	}
	public ChildThemes() {}
	public ChildThemes(long tourThemeId, String coverUrl, List<Object> childThemes, String coverThumbUrl, String iconUrl, String themeDesc, String title){
		super();		this.tourThemeId = tourThemeId;		this.coverUrl = coverUrl;		this.childThemes = childThemes;		this.coverThumbUrl = coverThumbUrl;		this.iconUrl = iconUrl;		this.themeDesc = themeDesc;		this.title = title;
	}
	public String toString() {
		return "ChildThemes [tourThemeId = " + tourThemeId + ", coverUrl = " + coverUrl + ", childThemes = " + childThemes + ", coverThumbUrl = " + coverThumbUrl + ", iconUrl = " + iconUrl + ", themeDesc = " + themeDesc + ", title = " + title + "]";	}
}