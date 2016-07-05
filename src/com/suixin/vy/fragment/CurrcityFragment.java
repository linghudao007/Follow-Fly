package com.suixin.vy.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.vy.adapter.HomeListAdapter;
import com.suixin.vy.core.JudgeNET;
import com.suixin.vy.model.PlanList;
import com.suixin.vy.model.TourPicList;
import com.suixin.vy.model.curr.CurrCityModel;
import com.suixin.vy.model.curr.WeatherData;
import com.suixin.vy.ui.R;

public class CurrcityFragment extends Fragment implements OnClickListener,
		OnRefreshListener {
	/** 刷新布局 */
	private SwipeRefreshLayout reflayout;
	/** 本地城市页整体布局 */
	private View view;
	/** 本地城市页ListView */
	private ListView lv_currcity;
	/** 本地城市页的头部布局 */
	private View currCityheadview;
	/** 头部控件 */
	private ImageView iv_top, iv_weather;
	private TextView tv_weather_str, tv_temp, tv_wind;
	private View v_cut;
	private RelativeLayout rl_hide;

	/** listview数据源 */
	private List list_curr;
	/** listtype */
	private List<String> type;
	/** 适配器 */
	private HomeListAdapter currAdapter;
	/** 获取到的JavaBean */
	private CurrCityModel currCityModel;
	/** 请求地址 */
	private static final String URLSTR = "http://www.duckr.cn/api/v5/homepage/inhabitcity/";
	/** 当前城市 */
	private String cityname = "北京市";
	/** 网络请求参数配置 */
	private RequestParams params;
	/** 网络请求 */
	private HttpUtils http;
	private BitmapUtils bitUtils;
	private Activity activity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.viewpager_currcity_view,
				container, false);
		this.view = view;
		initData();
		initCurrView(inflater, container);
		reflayout.setOnRefreshListener(this);
		// 第一次进页面先刷新一次
		reflayout.post(new Runnable() {
			@Override
			public void run() {
				reflayout.setRefreshing(true);
				// 网络请求数据
				getJson();
			}
		});
		return view;
	}

	/** 初始化数据 */
	private void initData() {
		list_curr = new ArrayList();
		type = new ArrayList<String>();
		params = new RequestParams();
		http = new HttpUtils();
		bitUtils = new BitmapUtils(getActivity());
		currAdapter = new HomeListAdapter(list_curr, type, getActivity());
	}

	private void getJson() {
		if (!JudgeNET.isNetable(activity)) {
			return;
		}
		// 这是cookie里的参数
		params.addQueryStringParameter("AppVer", "2.0");
		params.addQueryStringParameter("DeviceType", "1");
		// 这是post请求参数
		params.addBodyParameter("OrderStr", "");
		params.addBodyParameter("LocName", cityname);
		http.send(HttpRequest.HttpMethod.POST, URLSTR, params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						reflayout.setRefreshing(false);
						currCityModel = JSON.parseObject(responseInfo.result,
								CurrCityModel.class);
						if (responseInfo == null) {
							rl_hide.setVisibility(View.INVISIBLE);

							return;
						}
						// 判断是否获取成功
						if (currCityModel.getStatus() == 0) {
							setHeadViewData();
							getCurrCityListViewData();
						}
						return;
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						reflayout.setRefreshing(false);
						Log.e("ss", error.getExceptionCode() + ":" + msg);
					}
				});

	}

	/** 更新头部显示 */
	protected void setHeadViewData() {
		bitUtils.display(iv_top, currCityModel.getData().getCityInfo()
				.getCityThumbImage());
		if (currCityModel.getData().getWeatherData() == null) {
			rl_hide.setVisibility(View.INVISIBLE);
			return;
		}
		rl_hide.setVisibility(View.VISIBLE);
		WeatherData wData = currCityModel.getData().getWeatherData();
		String weather = "";
		if (wData.getDayName().equals("")) {
			weather = wData.getNightName();
		} else {
			weather = wData.getDayName();
		}
		tv_weather_str.setText(weather);
		tv_temp.setText(wData.getT());
		tv_wind.setText(wData.getDWindLevel());
		if (weather.equals("晴")) {
			iv_weather.setImageResource(R.drawable.weather_00);
		}
		if (weather.equals("多云")) {
			iv_weather.setImageResource(R.drawable.weather_01);
		}
		if (weather.equals("阴")) {
			iv_weather.setImageResource(R.drawable.weather_02);
		}
		if (weather.equals("阵雨")) {
			iv_weather.setImageResource(R.drawable.weather_03);
		}
		if (weather.equals("雷阵雨")) {
			iv_weather.setImageResource(R.drawable.weather_04);
		}
		if (weather.equals("小雨")) {
			iv_weather.setImageResource(R.drawable.weather_07);
		}
		if (weather.equals("中雨")) {
			iv_weather.setImageResource(R.drawable.weather_08);
		}
		if (weather.equals("大雨")) {
			iv_weather.setImageResource(R.drawable.weather_09);
		}
		if (weather.equals("中到大雨")) {
			iv_weather.setImageResource(R.drawable.weather_09);
		}
		if (weather.contains("雪")) {
			iv_weather.setImageResource(R.drawable.weather_15);
		}
		if (weather.contains("雪") && weather.contains("雨")) {
			iv_weather.setImageResource(R.drawable.weather_06);
		}
		if (weather.contains("雾")) {
			iv_weather.setImageResource(R.drawable.weather_18);
		}
	}

	/** 处理数据 */
	protected void getCurrCityListViewData() {
		list_curr.clear();
		type.clear();
		type.addAll(currCityModel.getData().getMixedList());
		List<TourPicList> tourPicList = currCityModel.getData()
				.getTourPicList();
		List<PlanList> planList = currCityModel.getData().getPlanList();
		for (int i = 0, j = 0, k = 0; i < type.size(); i++) {
			if (type.get(i).equals("0")) {
				list_curr.add(tourPicList.get(j));
				j++;
			}
			if (type.get(i).equals("1")) {
				list_curr.add(planList.get(k));
				k++;
			}
		}
		currAdapter.notifyDataSetInvalidated();
	}

	/** 实例化本地城市页面控件 */
	private void initCurrView(LayoutInflater inflater, ViewGroup container) {
		reflayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_ref);

		reflayout.setColorSchemeResources(R.color.refresh_1, R.color.refresh_2);

		reflayout.setSize(SwipeRefreshLayout.LARGE);
		lv_currcity = (ListView) view.findViewById(R.id.lv_currcity);
		currCityheadview = inflater.inflate(R.layout.head_listview_currcity,
				null);
		lv_currcity.addHeaderView(currCityheadview);
		lv_currcity.setAdapter(currAdapter);
		// 实例化头部控件
		iv_top = (ImageView) currCityheadview.findViewById(R.id.iv_top);
		iv_weather = (ImageView) currCityheadview.findViewById(R.id.iv_weather);
		tv_weather_str = (TextView) currCityheadview
				.findViewById(R.id.tv_weather_str);
		tv_temp = (TextView) currCityheadview.findViewById(R.id.tv_temp);
		tv_wind = (TextView) currCityheadview.findViewById(R.id.tv_wind);
		v_cut = (View) currCityheadview.findViewById(R.id.v_cut);
		rl_hide = (RelativeLayout) currCityheadview.findViewById(R.id.rl_hide);
	}

	@Override
	public void onClick(View arg0) {

	}

	@Override
	public void onRefresh() {
		reflayout.setRefreshing(true);
		getJson();
	}
}
