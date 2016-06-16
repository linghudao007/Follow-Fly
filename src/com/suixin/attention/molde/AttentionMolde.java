  package com.suixin.attention.molde;

import java.io.Serializable;
import java.util.List;

public class AttentionMolde implements Serializable{	private static final long serialVersionUID = 994840597L;	private String msg;	private List<Data> data;	public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    private long status;
	public String getMsg() {		return this.msg;	}
	public void setMsg(String msg) {		this.msg = msg;	}
	
	public long getStatus() {		return this.status;	}
	public void setStatus(long status) {		this.status = status;	}
	public AttentionMolde() {}
	public AttentionMolde(String msg, List<Data> data, long status){
		super();		this.msg = msg;		this.data = data;		this.status = status;
	}
	public String toString() {
		return "AttentionMolde [msg = " + msg + ", data = " + data + ", status = " + status + "]";	}
}