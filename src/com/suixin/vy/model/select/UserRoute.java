package com.suixin.vy.model.select;

import java.io.Serializable;
import java.util.List;

public class UserRoute implements Serializable{
	private static final long serialVersionUID = 1517769624L;
	private List<RoutePlaces> routePlaces;
	private long userRouteId;
	private String updatedTime;
	private String routeCost;
	private String routeTitle;

	public List<RoutePlaces> getRoutePlaces() {
		return this.routePlaces;
	}

	public void setRoutePlaces(List<RoutePlaces> routePlaces) {
		this.routePlaces = routePlaces;
	}

	public long getUserRouteId() {
		return this.userRouteId;
	}

	public void setUserRouteId(long userRouteId) {
		this.userRouteId = userRouteId;
	}

	public String getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getRouteCost() {
		return this.routeCost;
	}

	public void setRouteCost(String routeCost) {
		this.routeCost = routeCost;
	}

	public String getRouteTitle() {
		return this.routeTitle;
	}

	public void setRouteTitle(String routeTitle) {
		this.routeTitle = routeTitle;
	}

	public UserRoute() {}

	public UserRoute(List<RoutePlaces> routePlaces, long userRouteId, String updatedTime, String routeCost, String routeTitle){
		super();
		this.routePlaces = routePlaces;
		this.userRouteId = userRouteId;
		this.updatedTime = updatedTime;
		this.routeCost = routeCost;
		this.routeTitle = routeTitle;
	}

	public String toString() {
		return "UserRoute [routePlaces = " + routePlaces + ", userRouteId = " + userRouteId + ", updatedTime = " + updatedTime + ", routeCost = " + routeCost + ", routeTitle = " + routeTitle + "]";
	}
}