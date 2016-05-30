package com.suixin.vy.model;

public class GVBottomModel {
	private int iv_tab;
	private int iv_tab_normal;
	private String tv_tab;
	private boolean flag;
	private boolean msg_flag;
	
	
	public GVBottomModel(int iv_tab,int iv_tab_normal, String tv_tab, boolean flag,
			boolean msg_flag) {
		super();
		this.iv_tab = iv_tab;
		this.iv_tab_normal=iv_tab_normal;
		this.tv_tab = tv_tab;
		this.flag = flag;
		this.msg_flag = msg_flag;
	}
	
	public int getIv_tab_normal() {
		return iv_tab_normal;
	}

	public void setIv_tab_normal(int iv_tab_normal) {
		this.iv_tab_normal = iv_tab_normal;
	}

	public boolean isMsg_flag() {
		return msg_flag;
	}
	public void setMsg_flag(boolean msg_flag) {
		this.msg_flag = msg_flag;
	}
	public int getIv_tab() {
		return iv_tab;
	}
	public void setIv_tab(int iv_tab) {
		this.iv_tab = iv_tab;
	}
	public String getTv_tab() {
		return tv_tab;
	}
	public void setTv_tab(String tv_tab) {
		this.tv_tab = tv_tab;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}

