package com.suixin.vz.strike.model;

import java.io.Serializable;
import java.util.List;

public class StrikeModel implements Serializable{	private static final long serialVersionUID = 303804375L;	private String msg;	private Data data;	private long status;
	public String getMsg() {		return this.msg;	}
	public void setMsg(String msg) {		this.msg = msg;	}
	public Data getData() {		return this.data;	}
	public void setData(Data data) {		this.data = data;	}
	public long getStatus() {		return this.status;	}
	public void setStatus(long status) {		this.status = status;	}
	public StrikeModel() {}
	public StrikeModel(String msg, Data data, long status){
		super();		this.msg = msg;		this.data = data;		this.status = status;
	}
	public String toString() {
		return "StrikeModel [msg = " + msg + ", data = " + data + ", status = " + status + "]";	}
}