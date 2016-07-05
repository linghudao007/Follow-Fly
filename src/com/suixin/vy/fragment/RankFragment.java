package com.suixin.vy.fragment;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.vy.adapter.RankListAdapter;
import com.suixin.vy.core.JudgeNET;
import com.suixin.vy.core.MyBitmapConfig;
import com.suixin.vy.model.rank.DuckrBoradList;
import com.suixin.vy.model.rank.HotDuckrList;
import com.suixin.vy.model.rank.RandModel;
import com.suixin.vy.ui.R;

public class RankFragment extends Fragment implements OnClickListener,
		OnRefreshListener {
	/** 刷新布局 */
	private SwipeRefreshLayout reflayout;
	/** 达人整体布局 */
	private View view;
	/** 达人列表listView */
	private ListView lv_rank;
	/** listView的头布局 */
	private View rankHeadView;
	/** 头部布局控件 */
	private TextView more;
	private ImageView[] beforeSix;
	private TextView[] names;
	private TextView[] gocitynums;
	private ImageView iv_online, iv_samecity;

	/** 数据源 */
	private RandModel randModel;
	/** 列表数据源 */
	private List<HotDuckrList> rankList;

	/** 列表适配器 */
	private RankListAdapter rankAdapter;

	/** 网络请求 */
	private HttpUtils http;
	private BitmapUtils bitUtils;
	/** 网络请求参数配置 */
	private RequestParams params;
	/** 当前城市 */
	private String cityname = "北京市";

	private static final String URLSTR = "http://www.duckr.cn/api/v5/homepage/duckr/";
	private static final int SIX = 6;
	private Activity activity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.viewpager_rank_view, container,
				false);
		this.view = view;
		initData();
		initRankView(inflater, container);
		addListener();

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

	private void addListener() {
		reflayout.setOnRefreshListener(this);
	}

	/** 初始化数据 */
	private void initData() {
		params = new RequestParams();
		http = new HttpUtils();
		bitUtils = new BitmapUtils(getActivity());
		rankList = new ArrayList<HotDuckrList>();
		rankAdapter = new RankListAdapter(getActivity(), rankList);
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
						try {
							randModel = JSON.parseObject(responseInfo.result,
									RandModel.class);
						} catch (Exception e) {
							try {
								InputStream is = activity.getResources()
										.getAssets().open("rand.txt");
								byte[] b = new byte[is.available()];
								is.read(b);
								String json = new String(b);
								randModel = JSON.parseObject(json,
										RandModel.class);
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						// 判断是否获取成功
						if (randModel != null && randModel.getStatus() == 0) {
							setHeadViewData();
							getRankListViewData();
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

	protected void setHeadViewData() {
		List<DuckrBoradList> sixList = randModel.getData().getDuckrBoradList();
		for (int i = 0; i < beforeSix.length; i++) {
			bitUtils.display(beforeSix[i], sixList.get(i).getAvatarThumbUrl(),
					MyBitmapConfig.getConfig(getActivity()));
			names[i].setText(sixList.get(i).getNick());
			gocitynums[i].setText("去过" + sixList.get(i).getHaveGoNum() + "个城市");
		}
		bitUtils.display(iv_online, randModel.getData().getOnlineDuckr()
				.getCoverThumbUrl());
		bitUtils.display(iv_samecity, randModel.getData().getCityDuckr()
				.getCoverThumbUrl());
	}

	protected void getRankListViewData() {
		rankList.clear();
		rankList.addAll(randModel.getData().getHotDuckrList());
		rankAdapter.notifyDataSetChanged();
	}

	/** 实例化本地城市页面控件 */
	private void initRankView(LayoutInflater inflater, ViewGroup container) {
		reflayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_ref);
		reflayout.setColorSchemeResources(R.color.refresh_1, R.color.refresh_2);
		reflayout.setSize(SwipeRefreshLayout.LARGE);
		lv_rank = (ListView) view.findViewById(R.id.lv_rank);
		rankHeadView = inflater.inflate(R.layout.head_listview_rank, null);
		lv_rank.addHeaderView(rankHeadView);
		lv_rank.setAdapter(rankAdapter);
		more = (TextView) rankHeadView.findViewById(R.id.tv_more);
		beforeSix = new ImageView[SIX];
		beforeSix[0] = (ImageView) rankHeadView.findViewById(R.id.iv_no1);
		beforeSix[1] = (ImageView) rankHeadView.findViewById(R.id.iv_no2);
		beforeSix[2] = (ImageView) rankHeadView.findViewById(R.id.iv_no3);
		beforeSix[3] = (ImageView) rankHeadView.findViewById(R.id.iv_no4);
		beforeSix[4] = (ImageView) rankHeadView.findViewById(R.id.iv_no5);
		beforeSix[5] = (ImageView) rankHeadView.findViewById(R.id.iv_no6);
		names = new TextView[SIX];
		names[0] = (TextView) rankHeadView.findViewById(R.id.tv_usernameno1);
		names[1] = (TextView) rankHeadView.findViewById(R.id.tv_usernameno2);
		names[2] = (TextView) rankHeadView.findViewById(R.id.tv_usernameno3);
		names[3] = (TextView) rankHeadView.findViewById(R.id.tv_usernameno4);
		names[4] = (TextView) rankHeadView.findViewById(R.id.tv_usernameno5);
		names[5] = (TextView) rankHeadView.findViewById(R.id.tv_usernameno6);
		gocitynums = new TextView[SIX];
		gocitynums[0] = (TextView) rankHeadView
				.findViewById(R.id.tv_gocitynumno1);
		gocitynums[1] = (TextView) rankHeadView
				.findViewById(R.id.tv_gocitynumno2);
		gocitynums[2] = (TextView) rankHeadView
				.findViewById(R.id.tv_gocitynumno3);
		gocitynums[3] = (TextView) rankHeadView
				.findViewById(R.id.tv_gocitynumno4);
		gocitynums[4] = (TextView) rankHeadView
				.findViewById(R.id.tv_gocitynumno5);
		gocitynums[5] = (TextView) rankHeadView
				.findViewById(R.id.tv_gocitynumno6);
		iv_online = (ImageView) rankHeadView.findViewById(R.id.iv_online);
		iv_samecity = (ImageView) rankHeadView.findViewById(R.id.iv_samecity);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_more:
			break;
		}
	}

	@Override
	public void onRefresh() {
		getJson();
	}

}
