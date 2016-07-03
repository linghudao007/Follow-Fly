package com.suixin.vy.ui;

import java.util.List;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
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
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.suixin.vy.core.AppConfig;
import com.suixin.vy.core.BaseActivity;
import com.suixin.vy.core.MyApplication;
import com.suixin.vy.model.UserModel;

public class RegistActivity extends BaseActivity {
	private ImageView close;// 关闭活动按钮
	private LinearLayout bg;// 用于点击背景退出
	private RelativeLayout rl_1, rl_2;// 切换布局显示
	private TextView next, back, counttime, finish;// 下一步，上一步,完成注册（重新发送，显示倒计时）
	private EditText uname, upass, verify;// 用户名，密码，验证码输入框
	private String name, pass, smsCode;// 用户名，密码，验证码字符串
	private TextView tv_hint_tel;// 显示+86用户
	private long now;
	private TimeHandler handler = new TimeHandler();
	private static final int SMSCODESIZE = 6;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_login_2);
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
			finish();
			break;
		case R.id.tv_next:
			/**
			 * 进入下一步： 1.先判断用户输入是否合法 2.判断用户是否存在 2.5不存在则发送验证请求,
			 * 2.5.5跳转下一页,开始倒计时
			 */
			if (!registable()) {
				return;
			}
			queryData();
			break;
		case R.id.tv_back:
			/**
			 * 返回用户输入账号密码页面: 1.控制显示隐藏, 2.清空验证码框内容, 3.清空倒计时消息
			 */
			rl_1.setVisibility(View.VISIBLE);
			rl_2.setVisibility(View.GONE);
			verify.setText("");
			if (handler.hasMessages(0x10000)) {
				handler.removeMessages(0x10000);
			}
			break;
		case R.id.tv_countdown:
			/**
			 * 当用户输入验证码正确时变成完成，时间超过1分钟变为重新发送
			 */
			if (counttime.getText().equals("重新发送")) {
				commitRegistInfo();
			} else {
				// 此时为倒计时中不做点击回应
			}
			break;
		case R.id.tv_finish:
			/**
			 * 此时可判断验证码是否正确来完成注册, 1.正确：将数据插入到表格， 插入成功->保存用户信息，修改登录状态。
			 * 插入失败->页面不做跳转 2.不正确：弹出提示框，提示用户验证码输入错误
			 */
			smsCode = verify.getText().toString().trim();
			verifySmsCode();
			break;
		}
	}

	/** 能否提交注册 */
	private boolean registable() {
		name = uname.getText().toString().trim();
		pass = upass.getText().toString().trim();
		if (name.equals("") || pass.equals("")) {
			toast("用户名或密码不能为空");
			return false;
		}
		if (pass.toCharArray().length < 6) {
			toast("密码不能小于6位");
			return false;
		}
		if (isMobileNO(name)) {
			return true;
		} else {
			toast("手机号格式不正确");
			return false;
		}
	}

	/**
	 * 查询数据,判断此用户名是否已存在
	 */
	public void queryData() {
		BmobQuery<UserModel> query = new BmobQuery<UserModel>();
		query.addWhereEqualTo("name", Long.valueOf(name));
		query.findObjects(new FindListener<UserModel>() {
			@Override
			public void done(List<UserModel> object,
					cn.bmob.v3.exception.BmobException e) {
				if (e == null && object.size() > 0) {
					toast("此用户名已存在，请直接登录");
				} else {
					Log.i("bmob",
							"失败：" + e.getMessage() + "," + e.getErrorCode());
					/**
					 * 请求验证码，判断验证码是否成功，则跳转到填写验证码页面
					 */
					if (handler.hasMessages(0x10001)) {
						handler.removeMessages(0x10001);
					}
					handler.sendEmptyMessage(0x10001);
				}
			}
		});
	}

	/** 提交注册信息,获取验证码 */
	private void commitRegistInfo() {
		BmobSMS.initialize(this, AppConfig.BMOB_APPLICATION_ID);
		BmobSMS.requestSMSCode(this, name, "注册验证码",
				new RequestSMSCodeListener() {
					@Override
					public void done(Integer smsId, BmobException ex) {
					}
				});
	}

	/** 验证验证码 */
	private void verifySmsCode() {
		BmobSMS.verifySmsCode(this, name, smsCode, new VerifySMSCodeListener() {
			@Override
			public void done(BmobException ex) {
				if (ex == null) {// 短信验证码已验证成功
					Log.i("bmob", "验证通过");
					insertData();
				} else {
					Log.i("bmob", "验证失败：code =" + ex.getErrorCode() + ",msg = "
							+ ex.getLocalizedMessage());
					toast("验证码错误");
				}
			}
		});
	}

	/** 插入数据 */
	private void insertData() {
		UserModel user = new UserModel();
		user.setName(Long.valueOf(name));
		user.setPass(pass);
		user.save(new SaveListener<String>() {
			@Override
			public void done(String objectId,
					cn.bmob.v3.exception.BmobException e) {
				if (e == null) {
					toast("恭喜您注册成功");
					MyApplication myapp = ((MyApplication) getApplicationContext());
					myapp.setLogin(true);
					myapp.setUserInfo(Long.valueOf(name), pass);
					finish();
				} else {
					Log.i("bmob",
							"失败：" + e.getMessage() + "," + e.getErrorCode());
				}
			}
		});
	}

	/** 判断手机号格式是否正确 */
	public static final boolean isMobileNO(String mobile) {
		return Pattern.compile("^((1[358][0-9])|(14[57])|(17[0678]))\\d{8}$")
				.matcher(mobile).matches();
	}

	@Override
	protected void initView() {
		close = (ImageView) findViewById(R.id.iv_close);
		next = (TextView) findViewById(R.id.tv_next);
		back = (TextView) findViewById(R.id.tv_back);
		counttime = (TextView) findViewById(R.id.tv_countdown);
		finish = (TextView) findViewById(R.id.tv_finish);
		bg = (LinearLayout) findViewById(R.id.rl_background);
		uname = (EditText) findViewById(R.id.et_tel);
		upass = (EditText) findViewById(R.id.et_pass);
		verify = (EditText) findViewById(R.id.et_verify);
		rl_1 = (RelativeLayout) findViewById(R.id.rl_logining);
		rl_2 = (RelativeLayout) findViewById(R.id.rl_logining_2);
		tv_hint_tel = (TextView) findViewById(R.id.tv_hint_tel);
	}

	@Override
	protected void addListener() {
		bg.setOnClickListener(this);
		close.setOnClickListener(this);
		next.setOnClickListener(this);
		back.setOnClickListener(this);
		counttime.setOnClickListener(this);
		finish.setOnClickListener(this);
		verify.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				/**
				 * 当用户输入6位数字时注册按钮显示
				 */
				if (s.length() == SMSCODESIZE) {
					counttime.setVisibility(View.GONE);
					finish.setVisibility(View.VISIBLE);
				} else {
					counttime.setVisibility(View.VISIBLE);
					finish.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	protected void getData() {

	}

	private void toast(String content) {
		Toast.makeText(this, content, 1000).show();
	}

	private class TimeHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x10000:
				if (now > 0) {
					now--;
					if (handler.hasMessages(msg.what)) {
						handler.removeMessages(msg.what);
					}
					handler.sendEmptyMessageDelayed(0x10000, 1000);
					counttime.setText(now + "s");
				} else {
					counttime.setText("重新发送");
					counttime.setVisibility(View.VISIBLE);
					finish.setVisibility(View.GONE);
				}
				break;
			case 0x10001:
				commitRegistInfo();
				if (rl_1.getVisibility() == View.VISIBLE) {
					rl_1.setVisibility(View.GONE);
					rl_2.setVisibility(View.VISIBLE);
					tv_hint_tel.setText("+86 " + name);
				}
				now = 60;
				if (handler.hasMessages(0x10000)) {
					handler.removeMessages(0x10000);
				}
				handler.sendEmptyMessageDelayed(0x10000, 1000);
				break;
			}
		}
	}
}
