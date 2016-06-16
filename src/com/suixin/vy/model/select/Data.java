package com.suixin.vy.model.select;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{	private static final long serialVersionUID = 1046977828L;	private List<String> mixedList;	private List<PlanList> planList;	private String orderStr;
	public List<String> getMixedList() {		return this.mixedList;	}
	public void setMixedList(List<String> mixedList) {		this.mixedList = mixedList;	}
	public List<PlanList> getPlanList() {		return this.planList;	}
	public void setPlanList(List<PlanList> planList) {		this.planList = planList;	}
	public String getOrderStr() {		return this.orderStr;	}
	public void setOrderStr(String orderStr) {		this.orderStr = orderStr;	}
	public Data() {}
	public Data(List<String> mixedList, List<PlanList> planList, String orderStr){
		super();		this.mixedList = mixedList;		this.planList = planList;		this.orderStr = orderStr;
	}
	public String toString() {
		return "Data [mixedList = " + mixedList + ", planList = " + planList + ", orderStr = " + orderStr + "]";	}
}