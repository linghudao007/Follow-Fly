package com.suixin.vy.model.details;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{	private static final long serialVersionUID = 1035978754L;	private PartnerPlan partnerPlan;	private List<Object> tourPicList;
	public PartnerPlan getPartnerPlan() {		return this.partnerPlan;	}
	public void setPartnerPlan(PartnerPlan partnerPlan) {		this.partnerPlan = partnerPlan;	}
	public List<Object> getTourPicList() {		return this.tourPicList;	}
	public void setTourPicList(List<Object> tourPicList) {		this.tourPicList = tourPicList;	}
	public Data() {}
	public Data(PartnerPlan partnerPlan, List<Object> tourPicList){
		super();		this.partnerPlan = partnerPlan;		this.tourPicList = tourPicList;
	}
	public String toString() {
		return "Data [partnerPlan = " + partnerPlan + ", tourPicList = " + tourPicList + "]";	}
}