package com.suixin.vy.model.select;

import java.io.Serializable;

public class RoutePlaces implements Serializable{	private static final long serialVersionUID = 2080192544L;	private long placeLocLng;	private long routeDay;	private String description;	private long placeLocLat;	private String placeName;	private String placeCoverThumbUrl;	private String createdTime;	private String placeCoverUrl;	private long routePlaceId;
	public long getPlaceLocLng() {		return this.placeLocLng;	}
	public void setPlaceLocLng(long placeLocLng) {		this.placeLocLng = placeLocLng;	}
	public long getRouteDay() {		return this.routeDay;	}
	public void setRouteDay(long routeDay) {		this.routeDay = routeDay;	}
	public String getDescription() {		return this.description;	}
	public void setDescription(String description) {		this.description = description;	}
	public long getPlaceLocLat() {		return this.placeLocLat;	}
	public void setPlaceLocLat(long placeLocLat) {		this.placeLocLat = placeLocLat;	}
	public String getPlaceName() {		return this.placeName;	}
	public void setPlaceName(String placeName) {		this.placeName = placeName;	}
	public String getPlaceCoverThumbUrl() {		return this.placeCoverThumbUrl;	}
	public void setPlaceCoverThumbUrl(String placeCoverThumbUrl) {		this.placeCoverThumbUrl = placeCoverThumbUrl;	}
	public String getCreatedTime() {		return this.createdTime;	}
	public void setCreatedTime(String createdTime) {		this.createdTime = createdTime;	}
	public String getPlaceCoverUrl() {		return this.placeCoverUrl;	}
	public void setPlaceCoverUrl(String placeCoverUrl) {		this.placeCoverUrl = placeCoverUrl;	}
	public long getRoutePlaceId() {		return this.routePlaceId;	}
	public void setRoutePlaceId(long routePlaceId) {		this.routePlaceId = routePlaceId;	}
	public RoutePlaces() {}
	public RoutePlaces(long placeLocLng, long routeDay, String description, long placeLocLat, String placeName, String placeCoverThumbUrl, String createdTime, String placeCoverUrl, long routePlaceId){
		super();		this.placeLocLng = placeLocLng;		this.routeDay = routeDay;		this.description = description;		this.placeLocLat = placeLocLat;		this.placeName = placeName;		this.placeCoverThumbUrl = placeCoverThumbUrl;		this.createdTime = createdTime;		this.placeCoverUrl = placeCoverUrl;		this.routePlaceId = routePlaceId;
	}
	public String toString() {
		return "RoutePlaces [placeLocLng = " + placeLocLng + ", routeDay = " + routeDay + ", description = " + description + ", placeLocLat = " + placeLocLat + ", placeName = " + placeName + ", placeCoverThumbUrl = " + placeCoverThumbUrl + ", createdTime = " + createdTime + ", placeCoverUrl = " + placeCoverUrl + ", routePlaceId = " + routePlaceId + "]";	}
}