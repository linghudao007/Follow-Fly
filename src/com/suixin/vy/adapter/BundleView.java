package com.suixin.vy.adapter;

import java.io.Serializable;

import android.view.View;

public class BundleView implements Serializable{
	private View view;

	public BundleView(View view) {
		super();
		this.view = view;
	}
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
