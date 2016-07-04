package com.suixin.vy.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.listener.FindListener;

import com.suixin.vy.core.AppConfig;
import com.suixin.vy.core.BaseActivity;
import com.suixin.vy.core.MyApplication;
import com.suixin.vy.model.UserModel;

public class LoginingActivity extends BaseActivity {
	private ImageView close;
	private LinearLayout bg;
	private TextView login;
	private EditText uname, upass;// 用户名，密码
	private String name, pass;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_login_1);
		// 第一：默认初始化
		Bmob.initialize(this, AppConfig.BMOB_APPLICATION_ID);
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
		case R.id.tv_logining:
			name = uname.getText().toString().trim();
			pass = upass.getText().toString().trim();
			if (name.equals("") || pass.equals("")) {
				toast("用户名或密码不能为空");
				return;
			}
			queryData();
			break;
		}
	}

	/**
	 * 查询数据,判断此用户名密码是否正确
	 */
	public void queryData() {
		BmobQuery<UserModel> query = new BmobQuery<UserModel>();
		query.addWhereEqualTo("name", Long.valueOf(name));
		query.addWhereEqualTo("pass", pass);
		query.findObjects(new FindListener<UserModel>() {
			@Override
			public void done(List<UserModel> object,
					cn.bmob.v3.exception.BmobException e) {
				if (e == null && object.size() > 0) {
					MyApplication myapp = ((MyApplication) getApplicationContext());
					toast("登录成功");
					myapp.setLogin(true);
					UserModel u = object.get(0);
					myapp.setUserInfo(Long.valueOf(name), pass, u.getSex(),
							u.getBirthday(), u.getNick(), u.getPoint(),
							u.getHead(), u.getDeclaration(), u.getAttention(),
							u.getFans());
					finish();
				} else {
					Log.i("bmob",
							"失败：" + e.getMessage() + "," + e.getErrorCode());
					toast("用户名或密码不正确");
				}
			}
		});
	}

	@Override
	protected void initView() {
		close = (ImageView) findViewById(R.id.iv_close);
		login = (TextView) findViewById(R.id.tv_logining);
		bg = (LinearLayout) findViewById(R.id.rl_background);
		uname = (EditText) findViewById(R.id.et_tel);
		upass = (EditText) findViewById(R.id.et_pass);
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

	private void toast(String content) {
		Toast.makeText(this, content, 1000).show();
	}
}
