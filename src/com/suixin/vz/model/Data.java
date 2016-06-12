package com.suixin.vz.model;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    private static final long serialVersionUID = 1341749537L;

    private List<TourPicList> tourPicList;

    private long orderStr;

    // 获得旅游图片列表
    public List<TourPicList> getTourPicList() {
        return this.tourPicList;
    }

    public void setTourPicList(List<TourPicList> tourPicList) {
        this.tourPicList = tourPicList;
    }

    public long getOrderStr() {
        return this.orderStr;
    }

    public void setOrderStr(long orderStr) {
        this.orderStr = orderStr;
    }

    public Data() {
    }

    public Data(List<TourPicList> tourPicList, long orderStr) {
        super();
        this.tourPicList = tourPicList;
        this.orderStr = orderStr;
    }

    public String toString() {
        return "Data [tourPicList = " + tourPicList + ", orderStr = " + orderStr
                + "]";
    }
}