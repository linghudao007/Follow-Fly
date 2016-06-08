package com.suixin.vy.fragment;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.vy.adapter.HomeListAdapter;
import com.suixin.vy.model.AllModel;
import com.suixin.vy.model.PlanList;
import com.suixin.vy.model.TourPicList;
import com.suixin.vy.model.UserList;
import com.suixin.vy.ui.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PushFragment extends Fragment {
	/** 获取来显示的数据 */
	private List list_home;
	private List<String> type;
	/** 头部布局 */
	private View lv_home_head;
	/** ListView */
	private ListView lv_home;
	/** 获取来的推荐总数据 */
	private AllModel all;

	private View view;
	private LayoutInflater inflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.viewpager_home_view, container, false);
		this.inflater=inflater;
		// 实例化推荐中的控件
		initLv_home(inflater,container);
		//getJson();
		return view;
	}

	/** 实例化推荐中的控件 */
	private void initLv_home(LayoutInflater inflater,ViewGroup container) {
		lv_home = (ListView) view.findViewById(R.id.lv_home);
		lv_home_head = inflater.inflate(R.layout.head_listview_home,null);
		//Log.e("xx", lv_home_head+"");
		lv_home.addHeaderView(lv_home_head);
		Log.e("ss", "addHeaderView");
	}

	/** 通过接口解析JSON */
	private void getJson() {
		RequestParams params = new RequestParams();
		params.addBodyParameter("OrderStr", "");
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,
				"http://www.duckr.cn/api/v5/homepage/recommend/", params,
				new RequestCallBack<String>() {
					@Override
					public void onStart() {
						Log.e("ss", "onStart");
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						if (isUploading) {
							// testTextView.setText("upload: " + current + "/" +
							// total);
						} else {
							// testTextView.setText("reply: " + current + "/" +
							// total);
						}
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						try {
							Log.e("ss",
									new String(responseInfo.result
											.getBytes("Unicode"), "Unicode"));
							all = JSON.parseObject(responseInfo.result,
									AllModel.class);
							Log.e("ss", all.toString());
							// 判断是否获取成功
							if (all.getStatus() == 0
									&& all.getMsg().equals("获取首页推荐列表成功")) {
								getHomeListViewData();
								HomeListAdapter homelistAdapter = new HomeListAdapter(
										list_home, type, inflater);
								lv_home.setAdapter(homelistAdapter);
							}
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Log.e("ss", error.getExceptionCode() + ":" + msg);
					}
				});
	}

	private void getHomeListViewData() {
		List<String> type = all.getData().getMixedList();
		List<UserList> userList = all.getData().getUserList();
		List<TourPicList> tourPicList = all.getData().getTourPicList();
		List<PlanList> planList = all.getData().getPlanList();
		list_home = new ArrayList();
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
	}

}
