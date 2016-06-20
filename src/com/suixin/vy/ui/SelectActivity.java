package com.suixin.vy.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.vy.adapter.SelectAdapter;
import com.suixin.vy.core.BaseActivity;
import com.suixin.vy.model.select.PlanList;
import com.suixin.vy.model.select.SelectModel;

/**
 * 这是搜索目的地页面
 * 
 * @author yxy
 * 
 */
public class SelectActivity extends BaseActivity {
	/** 返回按钮 */
	private ImageView iv_back;
	/** 用户输入的目的地，出发地 */
	private EditText et_destName, et_departName;
	/** 明天，本周，本月按钮 */
	private TextView tv_time[];
	/** 标记选中按钮的位置，-1是都没被选中 */
	private int selected = -1;
	/** 搜索按钮 */
	private ImageView iv_select;
	private HttpUtils http;
	/** 解析得到的JAVABEAN */
	private SelectModel sModel;
	/** 记录用户选择的条件 */
	private String orderStr, startDate, endDate, destName, departName;
	/** 格式化日期 */
	private SimpleDateFormat format;
	/** 查询结果列表 */
	private ListView lv_select;
	/** 列表数据源 */
	private List<PlanList> selectList;
	/** 列表为空时的布局 */
	private LinearLayout selectemptyview;
	/** 列表适配器 */
	private SelectAdapter sAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.setContentView(R.layout.activity_select);
		initView();
		addListener();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			this.finish();
			break;
		case R.id.tv_tomorrow:
			setTvState(0);
			break;
		case R.id.tv_thisweek:
			setTvState(1);
			break;
		case R.id.tv_thismonth:
			setTvState(2);
			break;
		case R.id.iv_select:
			destName = et_destName.getText().toString().trim();
			departName = et_departName.getText().toString().trim();
			setTime();
			getJson();
			break;
		}
	}

	/** 设置时间参数 */
	private void setTime() {
		// 获取当前时间
		Calendar cal = Calendar.getInstance();
		switch (selected) {
		case 0:
			startDate = format.format(cal.getTime());
			cal.add(Calendar.DAY_OF_YEAR, 1);
			endDate = format.format(cal.getTime());
			break;
		case 1:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
			startDate = format.format(cal.getTime());
			cal.add(Calendar.WEEK_OF_YEAR, 1);// 获取下周一的日期
			endDate = format.format(cal.getTime());
			break;
		case 2:
			cal.set(Calendar.DAY_OF_MONTH, 1); // 设置为本月第一天
			startDate = format.format(cal.getTime());
			cal.add(Calendar.MONTH, 1); // 设置为下月第一天
			endDate = format.format(cal.getTime());
			break;
		}
	}

	private void getJson() {
		RequestParams params = new RequestParams();
		// 这是cookie里的参数
		params.addQueryStringParameter("AppVer", "2.0");
		params.addQueryStringParameter("DeviceType", "1");
		// 这是post请求参数,OrderStr为上次请求到哪里，
		// StartDate为出发时间，EndDate为结束日期
		// DestName为目的地，DepartName为出发地
		params.addBodyParameter("OrderStr", orderStr);
		params.addBodyParameter("StartDate", startDate);
		params.addBodyParameter("EndDate", endDate);
		params.addBodyParameter("DepartName", departName);
		params.addBodyParameter("DestName", destName);
		http.send(HttpRequest.HttpMethod.POST,
				"http://www.duckr.cn/api/v5/plan/search/dest/", params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						if (responseInfo == null) {
							return;
						}
						try {
							sModel = JSON.parseObject(responseInfo.result,
									SelectModel.class);
							// 判断是否获取成功
							if (sModel.getStatus() == 0
									&& sModel.getMsg().equals("搜索目的地信息成功")) {
								getData();
							}
						} catch (Exception e) {
							sModel = null;
							getData();
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						getJson();
					}
				});
	}

	/** 将得到的数据转成列表数据源 */
	@Override
	protected void getData() {
		selectList.clear();
		if (sModel == null || sModel.getData().getPlanList().size() == 0) {
			selectemptyview.setVisibility(View.VISIBLE);
		} else {
			selectemptyview.setVisibility(View.GONE);
			selectList.addAll(sModel.getData().getPlanList());
		}
		sAdapter.notifyDataSetInvalidated();
	}

	/** 设置按钮按下状态 */
	private void setTvState(int index) {
		if (selected != index) {
			selected = index;
			for (int i = 0; i < 3; i++) {
				if (i == selected) {			
					tv_time[i].setTextColor(Color.parseColor("#7F4802"));
					tv_time[i]
							.setBackgroundResource(R.drawable.shape_tv_yell_d_con);
				} else {
					tv_time[i].setTextColor(Color.parseColor("#777C73"));
					tv_time[i]
							.setBackgroundResource(R.drawable.shape_tv_blue_d_con);
				}
			}
		} else {
			selected = -1;
			tv_time[index].setTextColor(Color.parseColor("#777C73"));
			tv_time[index]
					.setBackgroundResource(R.drawable.shape_tv_blue_d_con);
			startDate = "";
			endDate = "";
		}
	}

	@Override
	protected void initView() {
		selectemptyview = (LinearLayout) findViewById(R.id.ll_selectempty);
		format = new SimpleDateFormat("yyyy-MM-dd");
		http = new HttpUtils();
		et_destName = (EditText) findViewById(R.id.et_select);
		et_departName = (EditText) findViewById(R.id.et_citystart);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		tv_time = new TextView[3];
		tv_time[0] = (TextView) findViewById(R.id.tv_tomorrow);
		tv_time[1] = (TextView) findViewById(R.id.tv_thisweek);
		tv_time[2] = (TextView) findViewById(R.id.tv_thismonth);
		iv_select = (ImageView) findViewById(R.id.iv_select);
		lv_select = (ListView) findViewById(R.id.lv_selectResult);
		selectList = new ArrayList<PlanList>();
		sAdapter = new SelectAdapter(this, selectList);
		lv_select.setAdapter(sAdapter);
		orderStr = "";
		startDate = "";
		endDate = "";
		destName = "";
		departName = "";

	}

	@Override
	protected void addListener() {
		iv_back.setOnClickListener(this);
		tv_time[0].setOnClickListener(this);
		tv_time[1].setOnClickListener(this);
		tv_time[2].setOnClickListener(this);
		iv_select.setOnClickListener(this);
	}
}
