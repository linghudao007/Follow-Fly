package com.suixin.vy.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.vy.adapter.HomeListAdapter;
import com.suixin.vy.adapter.VPLoopAdapter;
import com.suixin.vy.core.HeightWarpViewPager;
import com.suixin.vy.core.MyBitmapConfig;
import com.suixin.vy.model.AllModel;
import com.suixin.vy.model.BannerList;
import com.suixin.vy.model.PlanList;
import com.suixin.vy.model.TourPicList;
import com.suixin.vy.model.UserList;
import com.suixin.vy.ui.R;
import com.suixin.vy.ui.ThemeActionActivity;

public class PushFragment extends Fragment implements OnClickListener {
	/** ListView */
	private ListView lv_home;
	/** 获取来列表的数据 */
	private List list_home;
	private List<String> type;
	/** 获取来的推荐总数据 */
	private AllModel all;
	/** 头部布局 */
	private View lv_home_head;
	/** 头部控件 */
	private LinearLayout ll_siftaction, ll_thisrecommend, ll_hottrip,
			ll_online;
	/** 头部的轮播部分 */
	private HeightWarpViewPager vp_home_head_loop;
	/** 轮播部分的数据源 */
	private List<BannerList> loopList;
	/** 轮播图的三个布局 */
	private List<View> list_loopView;
	/** 轮播适配器 */
	private VPLoopAdapter loopAdapter;
	/** 轮播器上的小点 */
	private View[] points;
	private BitmapUtils bitUtils;
	private View view;
	private LayoutInflater inflater;
	private HomeListAdapter adapter;
	private String tag = "PushFragment";
	private PushHandler pushHandler;
	private int loopindex = 1;
	private Activity activity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.viewpager_home_view, container, false);
		this.inflater = inflater;
		list_home = new ArrayList();
		type = new ArrayList<String>();
		loopList = new ArrayList<BannerList>();
		bitUtils = new BitmapUtils(activity);
		pushHandler = new PushHandler();
		// 实例化推荐中的控件
		initLv_home(inflater, container);
		// 网络请求数据
		getJson();
		// 适配列表
		adapter = new HomeListAdapter(list_home, type, activity);
		lv_home.setAdapter(adapter);
		addListener();
		return view;
	}

	private void addListener() {
		vp_home_head_loop.setOnClickListener(this);
		ll_siftaction.setOnClickListener(this);
		ll_thisrecommend.setOnClickListener(this);
		ll_hottrip.setOnClickListener(this);
		ll_online.setOnClickListener(this);
	}

	/** 实例化推荐中的控件 */
	private void initLv_home(LayoutInflater inflater, ViewGroup container) {
		lv_home = (ListView) view.findViewById(R.id.lv_home);
		lv_home_head = inflater.inflate(R.layout.head_listview_home, null);
		vp_home_head_loop = (HeightWarpViewPager) this.lv_home_head
				.findViewById(R.id.vp_home);
		points = new View[3];
		points[0] = (View) lv_home_head.findViewById(R.id.v_frag1);
		points[1] = (View) lv_home_head.findViewById(R.id.v_frag2);
		points[2] = (View) lv_home_head.findViewById(R.id.v_frag3);
		this.list_loopView = new ArrayList<View>();
		View[] loopViews = new View[5];
		for (int i = 0; i < loopViews.length; i++) {
			loopViews[i] = inflater.inflate(R.layout.viewpager_loop_view, null);
			this.list_loopView.add(loopViews[i]);
		}
		lv_home.addHeaderView(lv_home_head);
		ll_siftaction = (LinearLayout) lv_home_head
				.findViewById(R.id.ll_siftaction);
		ll_thisrecommend = (LinearLayout) lv_home_head
				.findViewById(R.id.ll_thisrecommend);
		ll_hottrip = (LinearLayout) lv_home_head.findViewById(R.id.ll_hottrip);
		ll_online = (LinearLayout) lv_home_head.findViewById(R.id.ll_online);
	}

	/** 通过接口解析JSON */
	private void getJson() {
		RequestParams params = new RequestParams();
		// 这是cookie里的参数
		params.addQueryStringParameter("AppVer", "2.0");
		params.addQueryStringParameter("DeviceType", "1");
		// 这是post请求参数
		params.addBodyParameter("OrderStr", "");
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,
				"http://www.duckr.cn/api/v5/homepage/recommend/", params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

						all = JSON.parseObject(responseInfo.result,
								AllModel.class);
						// 判断是否获取成功
						if (all.getStatus() == 0
								&& all.getMsg().equals("获取首页推荐列表成功")) {
							getHomeListViewData();
						}
						return;
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Log.e("ss", error.getExceptionCode() + ":" + msg);
					}
				});
	}

	/** 处理主页数据源 */
	public void getHomeListViewData() {
		List<String> mixedList = all.getData().getMixedList();
		type.clear();
		type.addAll(mixedList);
		List<BannerList> bannerList = all.getData().getBannerList();
		List<UserList> userList = all.getData().getUserList();
		List<TourPicList> tourPicList = all.getData().getTourPicList();
		List<PlanList> planList = all.getData().getPlanList();
		for (int i = 0, j = 0, k = 0, m = 0; i < type.size(); i++) {
			if (type.get(i).equals("0")) {
				list_home.add(tourPicList.get(j));
				j++;
			}
			if (type.get(i).equals("1")) {
				list_home.add(planList.get(k));
				k++;
			}
			if (type.get(i).equals("2")) {
				list_home.add(userList.get(m));
				m++;
			}
		}
		loopList.clear();
		for (int i = 0; i < bannerList.size(); i++) {
			loopList.add(bannerList.get(i));
		}
		setLoop();
		// 刷新主页列表
		adapter.notifyDataSetInvalidated();
	}

	/** 设置轮播功能 */
	private void setLoop() {
		for (int i = 0; i < loopList.size() + 2; i++) {
			ImageView iv_show = (ImageView) list_loopView.get(i).findViewById(
					R.id.iv_show);
			if (loopList != null && loopList.size() > 0) {
				if (i == 0) {
					bitUtils.display(iv_show, loopList.get(2)
							.getCoverThumbUrl(), MyBitmapConfig
							.getConfig(activity));
				} else if (i == 4) {
					bitUtils.display(iv_show, loopList.get(0)
							.getCoverThumbUrl(), MyBitmapConfig
							.getConfig(activity));
				} else {
					bitUtils.display(iv_show, loopList.get(i - 1)
							.getCoverThumbUrl(), MyBitmapConfig
							.getConfig(activity));
				}
			}
		}
		loopAdapter = new VPLoopAdapter(this.getActivity(), list_loopView,
				vp_home_head_loop);
		vp_home_head_loop.setAdapter(loopAdapter);
		vp_home_head_loop.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {
				// 屏蔽父布局的触摸事件
				v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}
		});
		// 设置轮播为显示
		vp_home_head_loop.setVisibility(View.VISIBLE);
		vp_home_head_loop.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				if (arg1 != 0) {
					return;
				}
				if (position < 1) { // 首位之前，跳转到末尾（N）
					position = loopList.size();
					vp_home_head_loop.setCurrentItem(position, false);
				} else if (position > loopList.size()) {
					vp_home_head_loop.setCurrentItem(1, false);
					position = 1;
				}
			}

			@Override
			public void onPageSelected(int position) {
				// 设置轮播图片点的显示
				setPointShow(position);
			}
		});
		// 设置轮播的初始显示页面位置
		vp_home_head_loop.setCurrentItem(1, false);
		// 开始计时器
		pushHandler.sendEmptyMessageDelayed(1, 2000);
	}

	/** 设置轮播图片点的显示 */
	private void setPointShow(int position) {
		loopindex = position;
		if (position == 4) {
			points[0]
					.setBackgroundResource(R.drawable.abc_list_longpressed_holoy);
			points[1]
					.setBackgroundResource(R.drawable.abc_list_pressed_holo_dark);
			points[2]
					.setBackgroundResource(R.drawable.abc_list_pressed_holo_dark);
			return;
		}
		if (position == 0) {
			points[2]
					.setBackgroundResource(R.drawable.abc_list_longpressed_holoy);
			points[1]
					.setBackgroundResource(R.drawable.abc_list_pressed_holo_dark);
			points[0]
					.setBackgroundResource(R.drawable.abc_list_pressed_holo_dark);
			return;
		}
		for (int i = 0; i < 3; i++) {
			if (position == (i + 1)) {
				points[i]
						.setBackgroundResource(R.drawable.abc_list_longpressed_holoy);
			} else {
				points[i]
						.setBackgroundResource(R.drawable.abc_list_pressed_holo_dark);
			}
		}
	}

	class PushHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				pushHandler.sendEmptyMessageDelayed(1, 2000);
				vp_home_head_loop.setCurrentItem(loopindex);
				loopindex++;
				break;
			}
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.ll_siftaction:
			intent.setClass(activity, ThemeActionActivity.class);
			activity.startActivity(intent);
			break;
		}

	}
}