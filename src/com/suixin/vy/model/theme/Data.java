package com.suixin.vy.model.theme;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{	private static final long serialVersionUID = 1408222312L;	private long orderStr;	private List<PlanList> planList;
	public long getOrderStr() {		return this.orderStr;	}
	public void setOrderStr(long orderStr) {		this.orderStr = orderStr;	}
	public List<PlanList> getPlanList() {		return this.planList;	}
	public void setPlanList(List<PlanList> planList) {		this.planList = planList;	}
	public Data() {}
	public Data(long orderStr, List<PlanList> planList){
		super();		this.orderStr = orderStr;		this.planList = planList;
	}
	public String toString() {
		return "Data [orderStr = " + orderStr + ", planList = " + planList + "]";	}
}