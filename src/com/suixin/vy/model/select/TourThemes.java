package com.suixin.vy.model.select;

import java.io.Serializable;
import java.util.List;

public class TourThemes implements Serializable{	private static final long serialVersionUID = 396810470L;	private long tourThemeId;	private String coverUrl;	private String coverThumbUrl;	private String iconUrl;	private String themeDesc;	private String title;	private List<ChildThemes> childThemes;
	public long getTourThemeId() {		return this.tourThemeId;	}
	public void setTourThemeId(long tourThemeId) {		this.tourThemeId = tourThemeId;	}
	public String getCoverUrl() {		return this.coverUrl;	}
	public void setCoverUrl(String coverUrl) {		this.coverUrl = coverUrl;	}
	public String getCoverThumbUrl() {		return this.coverThumbUrl;	}
	public void setCoverThumbUrl(String coverThumbUrl) {		this.coverThumbUrl = coverThumbUrl;	}
	public String getIconUrl() {		return this.iconUrl;	}
	public void setIconUrl(String iconUrl) {		this.iconUrl = iconUrl;	}
	public String getThemeDesc() {		return this.themeDesc;	}
	public void setThemeDesc(String themeDesc) {		this.themeDesc = themeDesc;	}
	public String getTitle() {		return this.title;	}
	public void setTitle(String title) {		this.title = title;	}
	public List<ChildThemes> getChildThemes() {		return this.childThemes;	}
	public void setChildThemes(List<ChildThemes> childThemes) {		this.childThemes = childThemes;	}
	public TourThemes() {}
	public TourThemes(long tourThemeId, String coverUrl, String coverThumbUrl, String iconUrl, String themeDesc, String title, List<ChildThemes> childThemes){
		super();		this.tourThemeId = tourThemeId;		this.coverUrl = coverUrl;		this.childThemes = childThemes;		this.coverThumbUrl = coverThumbUrl;		this.iconUrl = iconUrl;		this.themeDesc = themeDesc;		this.title = title;
	}
	public String toString() {
		return "TourThemes [tourThemeId = " + tourThemeId + ", coverUrl = " + coverUrl + ", childThemes = " + childThemes + ", coverThumbUrl = " + coverThumbUrl + ", iconUrl = " + iconUrl + ", themeDesc = " + themeDesc + ", title = " + title + ", childThemes = " + childThemes + "]";	}
}