package com.suixin.vy.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suixin.vy.core.BaseActivity;

public class RegistActivity extends BaseActivity {
	private ImageView close;
	private LinearLayout bg;
	private TextView next;
	private EditText uname,upass;
	private static final String APPLICATION_ID = "1d48b3e46be2dcf7dbe14b8458205487";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_login_2);
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
		case R.id.tv_next:
			break;
		}
	}

	@Override
	protected void initView() {
		close = (ImageView) findViewById(R.id.iv_close);
		next = (TextView) findViewById(R.id.tv_next);
		bg = (LinearLayout) findViewById(R.id.rl_background);
		uname=(EditText)findViewById(R.id.et_tel);
		upass=(EditText)findViewById(R.id.et_pass);
	}

	@Override
	protected void addListener() {
		bg.setOnClickListener(this);
		close.setOnClickListener(this);
		next.setOnClickListener(this);
		
	}

	@Override
	protected void getData() {

	}
}
