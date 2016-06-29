package com.suixin.vy.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;

import com.suixin.vy.core.BaseActivity;

public class LoginActivity extends BaseActivity{
	private RelativeLayout bg;
	private TextView login,regist;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_login);
		initView();
		addListener();
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.rl_background:
			this.finish();
			break;
		case R.id.tv_login:
			Intent intent = new Intent(this,LoginingActivity.class);
			this.startActivity(intent);
			this.finish();
			break;
		case R.id.tv_regist:
			Intent intent_r = new Intent(this,RegistActivity.class);
			this.startActivity(intent_r);
			this.finish();
			break;
		}
	}

	@Override
	protected void initView() {
		bg = (RelativeLayout)findViewById(R.id.rl_background);
		login=(TextView)findViewById(R.id.tv_login);
		regist=(TextView)findViewById(R.id.tv_regist);
		
	}

	@Override
	protected void addListener() {
		bg.setOnClickListener(this);
		login.setOnClickListener(this);
		regist.setOnClickListener(this);
	}

	@Override
	protected void getData() {
	
	}

}
