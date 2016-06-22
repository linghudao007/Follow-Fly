package com.suixin.vy.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.vy.adapter.PackAdapter;
import com.suixin.vy.adapter.ThemeAdapter;
import com.suixin.vy.core.AppConfig;
import com.suixin.vy.core.BaseActivity;
import com.suixin.vy.core.JudgeNET;
import com.suixin.vy.model.theme.PlanList;
import com.suixin.vy.model.theme.ThemeModel;

/**
 * 精选页面
 * 
 * @author yxy
 * 
 */
public class ThemeActionActivity extends BaseActivity {
	private ImageView iv_back;
	private TextView tv_title;
	/** 精选页面的接口地址 */
	private static final String URLSTR = "http://www.duckr.cn/api/v5/homepage/recommend/select/activities/";
	/** listView */
	private ListView lv_themeactivity;
	/** 精选请求来的总数据 */
	private ThemeModel themeModel;
	/** 列表显示数据源 */
	private List<PlanList> themeList;
	/** 精选适配器 */
	private ThemeAdapter themeAdapter;
	/** 网络请求 */
	private HttpUtils http;
	/** 记录用户选择的条件 */
	private String orderStr;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.setContentView(R.layout.activity_themeaction);

		initView();
		addListener();
		getJson();
	}

	@Override
	protected void initView() {
		iv_back = (ImageView) findViewById(R.id.iv_back);
		tv_title = (TextView) findViewById(R.id.tv_title);
		if (this.getIntent().getStringExtra("title") != null) {
			tv_title.setText(this.getIntent().getStringExtra(AppConfig.TITLE));
		}
		lv_themeactivity = (ListView) findViewById(R.id.lv_themeactivity);
		http = new HttpUtils();
		themeList = new ArrayList<PlanList>();
		themeAdapter = new ThemeAdapter(this, themeList);
		lv_themeactivity.setAdapter(themeAdapter);
		orderStr = "";
	}

	@Override
	protected void addListener() {
		iv_back.setOnClickListener(this);
	}

	private void getJson() {
		if(!JudgeNET.isNetable(this)){
			return;
		}
		RequestParams params = new RequestParams();
		// 这是cookie里的参数
		params.addQueryStringParameter("AppVer", "2.0");
		params.addQueryStringParameter("DeviceType", "1");
		// 这是post请求参数,OrderStr为上次请求到哪里，
		params.addBodyParameter("OrderStr", orderStr);
		http.send(HttpRequest.HttpMethod.POST, URLSTR, params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						if (responseInfo == null) {
							return;
						}

						themeModel = JSON.parseObject(responseInfo.result,
								ThemeModel.class);
						Log.e("ss", themeModel.toString());
						// 判断是否获取成功
						if (themeModel.getStatus() == 0) {
							getData();
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
					}
				});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			this.finish();
			break;
		}
	}

	@Override
	protected void getData() {
		themeList.clear();
		themeList.addAll(themeModel.getData().getPlanList());
		themeAdapter.notifyDataSetChanged();
	}
}
