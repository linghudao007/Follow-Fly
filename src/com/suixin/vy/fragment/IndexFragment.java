package com.suixin.vy.fragment;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.vy.adapter.VPHomeAdapter;
import com.suixin.vy.model.AllModel;
import com.suixin.vy.model.PlanList;
import com.suixin.vy.model.TourPicList;
import com.suixin.vy.model.UserList;
import com.suixin.vy.ui.R;

public class IndexFragment extends Fragment implements OnClickListener {
	private List<View> list;
	private ViewPager vp_home;
	private boolean iv_order_clicked;
	private LinearLayout ll_home_classify;
	private TextView tv_hot, tv_local, tv_carpooling, tv_samecity;
	private int width;
	private String tv_clicked;
	/** 每个页面中的ListView */
	private ListView lv_home;
	/** vp_home里的每个页面 */
	private View v_home, v_currcity, v_pack, v_rank;
	/** 头部布局 */
	private View lv_home_head;
	/**获取来的推荐总数据*/
	private AllModel all;
	/**获取来显示的数据*/
	private List list_home;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_index, container, false);
		iv_order_clicked = false;
		tv_clicked = "hot";
		// 实例化整个碎片中的控件
		initView(view);
		// 实例化vp_home里的每个页面
		initVp_home(inflater, container);
		// 实例化推荐中的控件
		initLv_home(inflater, container);
		// 设置 Vp_home 数据源
		geViewPagertData();
		// 获取控件宽度
		setViewWidth(tv_hot);
		// 绑定监听
		addListener();
		// ViewPager适配器
		VPHomeAdapter vpAdapter = new VPHomeAdapter(list);
		vp_home.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				Toast.makeText(getActivity(),
						"ScrollStateChanged参数arg0：" + arg0, 1000).show();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Toast.makeText(
						getActivity(),
						"Scrolled参数arg0：" + arg0 + "参数arg1：" + arg1 + "参数arg2："
								+ arg2, 1000).show();
			}

			@Override
			public void onPageSelected(int index) {
				// 页面联动
				switch (index) {
				case 0:
					hotClick();
					break;
				case 1:
					localClick();
					break;
				case 2:
					carpoolingClick();
					break;
				case 3:
					samecityClick();
					break;
				default:
					break;
				}
			}

		});
		vp_home.setAdapter(vpAdapter);

		getJson();

		return view;
	}

	/** 实例化推荐中的控件 */
	private void initLv_home(LayoutInflater inflater, ViewGroup container) {
		lv_home = (ListView) v_home.findViewById(R.id.lv_home);
		lv_home_head = inflater.inflate(R.layout.head_listview_home, lv_home,
				false);
		lv_home.addHeaderView(lv_home_head);
		Log.e("ss", "addHeaderView");
	}

	/** 实例化布局 */
	private void initVp_home(LayoutInflater inflater, ViewGroup container) {
		v_home = inflater.inflate(R.layout.viewpager_home_view, container,
				false);
		v_currcity = inflater.inflate(R.layout.viewpager_currcity_view,
				container, false);
		v_pack = inflater.inflate(R.layout.viewpager_pack_view, container,
				false);
		v_rank = inflater.inflate(R.layout.viewpager_rank_view, container,
				false);

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
						// testTextView.setText("reply: " +
						// responseInfo.result);
						try {
							Log.e("ss",
									new String(responseInfo.result
											.getBytes("Unicode"), "Unicode"));
							all = JSON.parseObject(
									responseInfo.result, AllModel.class);
							Log.e("ss", all.toString());
							//判断是否获取成功
							if(all.getStatus()==0&&all.getMsg().equals("获取首页推荐列表成功")){
								List<String> type = all.getData().getMixedList();
								List<UserList> userList = all.getData().getUserList();
								List<TourPicList> tourPicList = all.getData().getTourPicList();
								List<PlanList> planList = all.getData().getPlanList();
								list_home = new ArrayList();
								for(int i = 0,j=0,k=0,m=0;i<type.size();i++){
									if(type.get(i).equals("0")){
										list_home.add(tourPicList.get(j));
										j++;
									}
									if(type.get(i).equals("1")){
										list_home.add(planList.get(k));
										k++;
									}
									if(type.get(i).equals("2")){
										list_home.add(userList.get(m));
										m++;
									}
								}
								
							}
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Log.e("ss", error.getExceptionCode() + ":" + msg);
					}
				});

	}

	/** 获取控件宽度 */
	private void setViewWidth(final TextView view) {
		ViewTreeObserver vto2 = view.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				width = view.getWidth();
			}
		});
	}

	private void addListener() {
		tv_hot.setOnClickListener(this);
		tv_local.setOnClickListener(this);
		tv_carpooling.setOnClickListener(this);
		tv_samecity.setOnClickListener(this);
	}

	/** 实例化碎片中的控件 */
	private void initView(View view) {
		ll_home_classify = (LinearLayout) view
				.findViewById(R.id.ll_home_classify);
		tv_hot = (TextView) view.findViewById(R.id.tv_hot);
		tv_local = (TextView) view.findViewById(R.id.tv_local);
		tv_carpooling = (TextView) view.findViewById(R.id.tv_carpooling);
		tv_samecity = (TextView) view.findViewById(R.id.tv_samecity);
		vp_home = (ViewPager) view.findViewById(R.id.vp_home);
	}

	/** 设置 Vp_home 数据源 */
	private void geViewPagertData() {
		list = new ArrayList<View>();
		list.add(v_home);
		list.add(v_currcity);
		list.add(v_pack);
		list.add(v_rank);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_hot:
			vp_home.setCurrentItem(0);
			break;
		case R.id.tv_local:
			vp_home.setCurrentItem(1);
			break;
		case R.id.tv_carpooling:
			vp_home.setCurrentItem(2);
			break;
		case R.id.tv_samecity:
			vp_home.setCurrentItem(3);
			break;
		default:
			break;
		}
	}

	/** 推荐点击效果 */
	private void hotClick() {
		if (!tv_clicked.equals("hot")) {
			tv_hot.setTextColor(Color.parseColor("#AD7F2D"));
			tv_hot.setBackgroundResource(R.drawable.home_tab_selected);

			tv_local.setTextColor(Color.BLACK);
			tv_local.setBackgroundColor(Color.TRANSPARENT);
			tv_carpooling.setTextColor(Color.BLACK);
			tv_carpooling.setBackgroundColor(Color.TRANSPARENT);
			tv_samecity.setTextColor(Color.BLACK);
			tv_samecity.setBackgroundColor(Color.TRANSPARENT);
			tv_clicked = "hot";
		}
	}

	/** 本地点击效果 */
	private void localClick() {
		if (!tv_clicked.equals("local")) {
			tv_local.setTextColor(Color.parseColor("#AD7F2D"));
			tv_local.setBackgroundResource(R.drawable.home_tab_selected);

			tv_hot.setTextColor(Color.BLACK);
			tv_hot.setBackgroundColor(Color.TRANSPARENT);
			tv_carpooling.setTextColor(Color.BLACK);
			tv_carpooling.setBackgroundColor(Color.TRANSPARENT);
			tv_samecity.setTextColor(Color.BLACK);
			tv_samecity.setBackgroundColor(Color.TRANSPARENT);
			tv_clicked = "local";
		}
	}

	/** 约伴点击效果 */
	private void carpoolingClick() {
		if (!tv_clicked.equals("carpooling")) {
			tv_carpooling.setTextColor(Color.parseColor("#AD7F2D"));
			tv_carpooling.setBackgroundResource(R.drawable.home_tab_selected);

			tv_local.setTextColor(Color.BLACK);
			tv_local.setBackgroundColor(Color.TRANSPARENT);
			tv_hot.setTextColor(Color.BLACK);
			tv_hot.setBackgroundColor(Color.TRANSPARENT);
			tv_samecity.setTextColor(Color.BLACK);
			tv_samecity.setBackgroundColor(Color.TRANSPARENT);
			tv_clicked = "carpooling";
		}
	}

	/** 达人点击效果 */
	private void samecityClick() {
		if (!tv_clicked.equals("samecity")) {
			tv_samecity.setTextColor(Color.parseColor("#AD7F2D"));
			tv_samecity.setBackgroundResource(R.drawable.home_tab_selected);

			tv_local.setTextColor(Color.BLACK);
			tv_local.setBackgroundColor(Color.TRANSPARENT);
			tv_carpooling.setTextColor(Color.BLACK);
			tv_carpooling.setBackgroundColor(Color.TRANSPARENT);
			tv_hot.setTextColor(Color.BLACK);
			tv_hot.setBackgroundColor(Color.TRANSPARENT);
			tv_clicked = "samecity";
		}
	}

}
