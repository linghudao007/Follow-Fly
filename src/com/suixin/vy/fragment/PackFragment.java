package com.suixin.vy.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.suixin.vy.core.JudgeNET;
import com.suixin.vy.model.pack.PackModel;
import com.suixin.vy.model.pack.PlanList;
import com.suixin.vy.ui.R;

public class PackFragment extends Fragment implements OnClickListener,
		OnKeyListener {
	/** 约伴整体布局 */
	private View view;
	/** 约伴页面的接口地址 */
	private static final String URLSTR = "http://www.duckr.cn/api/v5/homepage/partner/";
	/** 页面控件 */
	private LinearLayout ll_selectable, ll_selecttag;
	private TextView[] tv;
	private static final int TVCOUNT = 5;
	private EditText et_citystart, et_cityend;
	private ImageView iv_unfold;
	private ListView lv_pank;
	/** 当前选中的出行时间 -1-没选，0-明天，1-本周，2-本月 */
	private int stime = -1;
	/** 当前选中的活动类别 -1-没选，3-旅行约伴，4-同城活动 */
	private int sclass = -1;
	/** 约伴请求来的总数据 */
	private PackModel packModel;
	/** 列表显示数据源 */
	private List<PlanList> packList;
	/** 约伴适配器 */
	private PackAdapter packAdapter;
	/** 格式化日期 */
	private SimpleDateFormat format;
	/** 网络请求 */
	private HttpUtils http;
	private BitmapUtils bitUtils;
	
	/** 记录用户选择的条件 */
	private String locName = "北京市", destName, departName, startDate, endDate,
			orderStr,planType;
	private int startY = 0;
	private Activity activity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.viewpager_pack_view, container,
				false);
		this.view = view;
		initData();
		initPackView(inflater, container);
		addListener();
		listViewListener();
		//getJson();
		return view;
	}

	private void listViewListener() {
		lv_pank.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					startY = (int) event.getY();
				}
				if (ll_selecttag.getVisibility() == View.GONE
						&& event.getAction() == MotionEvent.ACTION_MOVE) {
					int y = (int) event.getY();
					if (y < startY) {
						ll_selecttag.setVisibility(View.VISIBLE);
						ll_selectable.setVisibility(View.GONE);
					}
				}
				return false;
			}

		});
	}

	private void addListener() {
		iv_unfold.setOnClickListener(this);
		for (int i = 0; i < tv.length; i++) {
			tv[i].setOnClickListener(this);
		}
		et_citystart.setOnKeyListener(this);
		et_cityend.setOnKeyListener(this);
	}

	private void initData() {
		format = new SimpleDateFormat("yyyy-MM-dd");
		http = new HttpUtils();
		packList = new ArrayList<PlanList>();
		packAdapter = new PackAdapter(getActivity(), packList);
		orderStr = "";
		startDate = "";
		endDate = "";
		destName = "";
		departName = "";
	}

	private void initPackView(LayoutInflater inflater, ViewGroup container) {
		ll_selectable = (LinearLayout) view.findViewById(R.id.ll_selectable);
		ll_selecttag = (LinearLayout) view.findViewById(R.id.ll_selecttag);
		tv = new TextView[TVCOUNT];
		tv[0] = (TextView) view.findViewById(R.id.tv_tomorrow);
		tv[1] = (TextView) view.findViewById(R.id.tv_thisweek);
		tv[2] = (TextView) view.findViewById(R.id.tv_thismonth);
		tv[3] = (TextView) view.findViewById(R.id.tv_pack);
		tv[4] = (TextView) view.findViewById(R.id.tv_samecityact);
		et_citystart = (EditText) view.findViewById(R.id.et_citystart);
		et_cityend = (EditText) view.findViewById(R.id.et_cityend);
		iv_unfold = (ImageView) view.findViewById(R.id.iv_unfold);
		lv_pank = (ListView) view.findViewById(R.id.lv_pank);
		lv_pank.setAdapter(packAdapter);
	}

	private void getJson() {
		if(!JudgeNET.isNetable(activity)){
			return;
		}
		departName = et_citystart.getText().toString().trim();
		destName = et_cityend.getText().toString().trim();
		setTime();
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
		params.addBodyParameter("PlanType", planType);
		params.addBodyParameter("LocName", locName);
		http.send(HttpRequest.HttpMethod.POST,
				"http://www.duckr.cn/api/v5/plan/search/dest/", params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						if (responseInfo == null) {
							return;
						}
						packModel = JSON.parseObject(responseInfo.result,
								PackModel.class);
						// 判断是否获取成功
						if (packModel.getStatus() == 0) {
							getData();
						}

					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
					}
				});
	}

	protected void getData() {
		packList.clear();
		packList.addAll(packModel.getData().getPlanList());
		if (packList.size() == 0) {
			Toast.makeText(getActivity(), "还没有您筛选的约伴", 1000).show();
		}
		packAdapter.notifyDataSetInvalidated();
	}

	/** 设置时间参数 */
	private void setTime() {
		// 获取当前时间
		Calendar cal = Calendar.getInstance();
		switch (stime) {
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
		case -1:
			startDate = "";
			endDate = "";
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_unfold:
			ll_selectable.setVisibility(View.VISIBLE);
			ll_selecttag.setVisibility(View.GONE);
			break;
		case R.id.tv_tomorrow:
			setTvTimeState(0);
			break;
		case R.id.tv_thisweek:
			setTvTimeState(1);
			break;
		case R.id.tv_thismonth:
			setTvTimeState(2);
			break;
		case R.id.tv_pack:
			setTvClassState(3);
			break;
		case R.id.tv_samecityact:
			setTvClassState(4);
			break;
		}
	}

	private void setTvTimeState(int index) {
		if (stime == index) {
			stime = -1;
			tv[index].setTextColor(Color.parseColor("#777C73"));
			tv[index].setBackgroundResource(R.drawable.shape_tv_blue_d_con);
			return;
		}
		stime = index;
		for (int i = 0; i < 3; i++) {
			setTvColor(index, i);
		}
	}

	private void setTvClassState(int index) {
		if (sclass == index) {
			sclass = -1;
			tv[index].setTextColor(Color.parseColor("#777C73"));
			tv[index].setBackgroundResource(R.drawable.shape_tv_blue_d_con);
			planType = "2";
			return;
		}
		sclass = index;
		if(index==3){
			planType = "1";
		}else{
			planType = "2";
		}
		
		for (int i = 3; i < 5; i++) {
			setTvColor(index, i);
		}
	}

	private void setTvColor(int index, int i) {
		if (i == index) {
			tv[i].setTextColor(Color.parseColor("#7F4802"));
			tv[i].setBackgroundResource(R.drawable.shape_tv_yell_d_con);
		} else {
			tv[i].setTextColor(Color.parseColor("#777C73"));
			tv[i].setBackgroundResource(R.drawable.shape_tv_blue_d_con);
		}
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent e) {
		if (v.getId() == R.id.et_citystart && keyCode == KeyEvent.KEYCODE_ENTER) {
			getJson();
		}
		if (v.getId() == R.id.et_cityend && keyCode == KeyEvent.KEYCODE_ENTER) {
			getJson();
		}
		return true;
	}
}
