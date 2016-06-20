package com.suixin.vy.model.pack;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{	private static final long serialVersionUID = 439350410L;	private String orderStr;	private List<PlanList> planList;
	public String getOrderStr() {		return this.orderStr;	}
	public void setOrderStr(String orderStr) {		this.orderStr = orderStr;	}
	public List<PlanList> getPlanList() {		return this.planList;	}
	public void setPlanList(List<PlanList> planList) {		this.planList = planList;	}
	public Data() {}
	public Data(String orderStr, List<PlanList> planList){
		super();		this.orderStr = orderStr;		this.planList = planList;
	}
	public String toString() {
		return "Data [orderStr = " + orderStr + ", planList = " + planList + "]";	}
}