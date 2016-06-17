package com.suixin.vy.model.rank;

import java.io.Serializable;

public class RandModel implements Serializable{
	private Data data;
	private String msg;
	private int status;
	public RandModel(Data data, String msg, int status) {
		super();
		this.data = data;
		this.msg = msg;
		this.status = status;
	}
	public RandModel() {
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "RandModel [data=" + data + ", msg=" + msg + ", status="
				+ status + "]";
	}
	
}
