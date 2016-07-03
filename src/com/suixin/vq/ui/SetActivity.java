package com.suixin.vq.ui;

import org.simple.eventbus.EventBus;

import com.suixin.vy.core.AppConfig;
import com.suixin.vy.core.BaseActivity;
import com.suixin.vy.core.MyApplication;
import com.suixin.vy.ui.LoginActivity;
import com.suixin.vy.ui.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class SetActivity extends BaseActivity {
	private TextView tv_logout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__1_vqmain);
		initView();
		addListener();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_logout:
			/**
			 * 退出登录，清空用户登录保存的信息
			 */
			MyApplication appState = ((MyApplication) getApplicationContext());
			appState.setLogin(false);
			appState.removeUserInfo();
			this.setResult(10);
			this.finish();
			break;
		}

	}

	@Override
	protected void initView() {
		tv_logout = (TextView) findViewById(R.id.tv_logout);

	}

	@Override
	protected void addListener() {
		tv_logout.setOnClickListener(this);

	}

	@Override
	protected void getData() {

	}

}