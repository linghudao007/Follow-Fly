package com.suixin.vy.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	private static final String APPLICATION_ID="1d48b3e46be2dcf7dbe14b8458205487";
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_login);
		initView();
		addListener();
		//yanz();
	}

	private void yanz() {
		BmobSMS.initialize(this,APPLICATION_ID);
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sendTime = format.format(new Date());
		BmobSMS.requestSMS(this, "这里不知道填啥", "审核通过后的短信内容",null,new RequestSMSCodeListener() {

		    @Override
		    public void done(Integer smsId,BmobException ex) {
		        // TODO Auto-generated method stub
		        if(ex==null){//
		            Log.i("bmob","短信发送成功，短信id："+smsId);//用于查询本次短信发送详情
		        }else{
		            Log.i("bmob","errorCode = "+ex.getErrorCode()+",errorMsg = "+ex.getLocalizedMessage());
		        }
		    }

		});
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.rl_background:
			this.finish();
			break;
		case R.id.tv_login:
			break;
		case R.id.tv_regist:
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
