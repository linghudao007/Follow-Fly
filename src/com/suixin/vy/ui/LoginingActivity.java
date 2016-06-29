package com.suixin.vy.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;

import com.suixin.vy.core.BaseActivity;

public class LoginingActivity extends BaseActivity {
	private ImageView close;
	private LinearLayout bg;
	private TextView login;
	private static final String APPLICATION_ID = "1d48b3e46be2dcf7dbe14b8458205487";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_login_1);
		initView();
		addListener();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_background:
		case R.id.iv_close:
			this.finish();
			break;
		}
	}

	@Override
	protected void initView() {
		close = (ImageView) findViewById(R.id.iv_close);
		login = (TextView) findViewById(R.id.tv_logining);
		bg = (LinearLayout) findViewById(R.id.rl_background);
	}

	@Override
	protected void addListener() {
		bg.setOnClickListener(this);
		close.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	protected void getData() {
	}

	private void yanz() {
		BmobSMS.initialize(this, APPLICATION_ID);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sendTime = format.format(new Date());
		BmobSMS.requestSMS(this, "这里不知道填啥", "审核通过后的短信内容", null,
				new RequestSMSCodeListener() {

					@Override
					public void done(Integer smsId, BmobException ex) {
						// TODO Auto-generated method stub
						if (ex == null) {//
							Log.i("bmob", "短信发送成功，短信id：" + smsId);// 用于查询本次短信发送详情
						} else {
							Log.i("bmob", "errorCode = " + ex.getErrorCode()
									+ ",errorMsg = " + ex.getLocalizedMessage());
						}
					}

				});
	}
}
