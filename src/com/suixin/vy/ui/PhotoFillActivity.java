package com.suixin.vy.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.suixin.vy.core.BaseActivity;

public class PhotoFillActivity extends BaseActivity {
	private TextView tv_count;
	private ViewPager vp_photoshow;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.setContentView(R.layout.activity_photofill);
		initView();
		addListener();
	}

	@Override
	public void onClick(View arg0) {
		
	}

	@Override
	protected void initView() {
		tv_count=(TextView)findViewById(R.id.tv_count);
	}

	@Override
	protected void addListener() {
	}

	@Override
	protected void getData() {
	}

}
