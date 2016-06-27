package com.suixin.vy.model.details;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{	private static final long serialVersionUID = 801468626L;	private List<Object> partnerPlan;	private TourPic tourPic;
	public List<Object> getPartnerPlan() {		return this.partnerPlan;	}
	public void setPartnerPlan(List<Object> partnerPlan) {		this.partnerPlan = partnerPlan;	}
	public TourPic getTourPic() {		return this.tourPic;	}
	public void setTourPic(TourPic tourPic) {		this.tourPic = tourPic;	}
	public Data() {}
	public Data(List<Object> partnerPlan, TourPic tourPic){
		super();		this.partnerPlan = partnerPlan;		this.tourPic = tourPic;
	}
	public String toString() {
		return "Data [partnerPlan = " + partnerPlan + ", tourPic = " + tourPic + "]";	}
}