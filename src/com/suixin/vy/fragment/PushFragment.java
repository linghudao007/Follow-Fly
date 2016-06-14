package com.suixin.vy.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.vy.adapter.HomeListAdapter;
import com.suixin.vy.adapter.VPLoopAdapter;
import com.suixin.vy.core.HeightWarpViewPager;
import com.suixin.vy.model.AllModel;
import com.suixin.vy.model.BannerList;
import com.suixin.vy.model.PlanList;
import com.suixin.vy.model.TourPicList;
import com.suixin.vy.model.UserList;
import com.suixin.vy.ui.R;

public class PushFragment extends Fragment {
	/** ListView */
	private ListView lv_home;
	/** 获取来列表的数据 */
	private List list_home;
	private List<String> type;
	/** 获取来的推荐总数据 */
	private AllModel all;
	/** 头部布局 */
	private View lv_home_head;
	/** 头部的轮播部分 */
	private HeightWarpViewPager vp_home_head_loop;
	/** 轮播部分的数据源 */
	private List<BannerList> loopList;
	/** 轮播图的三个布局 */
	private List<View> list_loopView;
	/** 轮播适配器 */
	private VPLoopAdapter loopAdapter;

	private View view;
	private LayoutInflater inflater;
	private HomeListAdapter adapter;
	private String tag = "PushFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.viewpager_home_view, container, false);
		this.inflater = inflater;
		list_home = new ArrayList();
		type = new ArrayList<String>();
		loopList = new ArrayList<BannerList>();
		// 实例化推荐中的控件
		initLv_home(inflater, container);
		// 网络请求数据
		getJson();
		// 适配列表
		adapter = new HomeListAdapter(list_home, type, this.getActivity());
		lv_home.setAdapter(adapter);
		vp_home_head_loop.setCurrentItem(1, false);
		return view;
	}

	/** 实例化推荐中的控件 */
	private void initLv_home(LayoutInflater inflater, ViewGroup container) {
		lv_home = (ListView) view.findViewById(R.id.lv_home);
		lv_home_head = inflater.inflate(R.layout.head_listview_home, null);
		vp_home_head_loop = (HeightWarpViewPager) this.lv_home_head
				.findViewById(R.id.vp_home);
		this.list_loopView = new ArrayList<View>();
		View view_0 = inflater.inflate(R.layout.viewpager_loop_view, null);
		View view_1 = inflater.inflate(R.layout.viewpager_loop_view, null);
		View view_2 = inflater.inflate(R.layout.viewpager_loop_view, null);
		View view_3 = inflater.inflate(R.layout.viewpager_loop_view, null);
		View view_4 = inflater.inflate(R.layout.viewpager_loop_view, null);
		this.list_loopView.add(view_0);
		this.list_loopView.add(view_1);
		this.list_loopView.add(view_2);
		this.list_loopView.add(view_3);
		this.list_loopView.add(view_4);
		
		lv_home.addHeaderView(lv_home_head);
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
					public void onStart() {
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						if (isUploading) {
							// testTextView.setText("upload: " + current +
							// "/" +
							// total);
						} else {
							// testTextView.setText("reply: " + current +
							// "/" +
							// total);
						}
					}

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
		adapter.notifyDataSetInvalidated();
	}

	private void setLoop() {
		loopAdapter = new VPLoopAdapter(this.getActivity(), list_loopView,
				loopList, vp_home_head_loop);
		vp_home_head_loop.setAdapter(loopAdapter);
		vp_home_head_loop.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				if(arg1!=0){
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
			}
		});
		vp_home_head_loop.setCurrentItem(1, false);
	}
}
