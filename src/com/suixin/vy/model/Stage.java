package com.suixin.vy.model;

import java.io.Serializable;
import java.util.List;

public class Stage implements Serializable{	private static final long serialVersionUID = 370437932L;	private String planGuid;	private long earnest;	private long isMember;	private String startTime;	private long totalNumber;	private String endTime;	private long joinNumber;	private long price;
	public String getPlanGuid() {		return this.planGuid;	}
	public void setPlanGuid(String planGuid) {		this.planGuid = planGuid;	}
	public long getEarnest() {		return this.earnest;	}
	public void setEarnest(long earnest) {		this.earnest = earnest;	}
	public long getIsMember() {		return this.isMember;	}
	public void setIsMember(long isMember) {		this.isMember = isMember;	}
	public String getStartTime() {		return this.startTime;	}
	public void setStartTime(String startTime) {		this.startTime = startTime;	}
	public long getTotalNumber() {		return this.totalNumber;	}
	public void setTotalNumber(long totalNumber) {		this.totalNumber = totalNumber;	}
	public String getEndTime() {		return this.endTime;	}
	public void setEndTime(String endTime) {		this.endTime = endTime;	}
	public long getJoinNumber() {		return this.joinNumber;	}
	public void setJoinNumber(long joinNumber) {		this.joinNumber = joinNumber;	}
	public long getPrice() {		return this.price;	}
	public void setPrice(long price) {		this.price = price;	}
	public Stage() {}
	public Stage(String planGuid, long earnest, long isMember, String startTime, long totalNumber, String endTime, long joinNumber, long price){
		super();		this.planGuid = planGuid;		this.earnest = earnest;		this.isMember = isMember;		this.startTime = startTime;		this.totalNumber = totalNumber;		this.endTime = endTime;		this.joinNumber = joinNumber;		this.price = price;
	}
	public String toString() {
		return "Stage [planGuid = " + planGuid + ", earnest = " + earnest + ", isMember = " + isMember + ", startTime = " + startTime + ", totalNumber = " + totalNumber + ", endTime = " + endTime + ", joinNumber = " + joinNumber + ", price = " + price + "]";	}
}