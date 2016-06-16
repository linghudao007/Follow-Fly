package com.suixin.vy.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suixin.vy.adapter.VPHomeAdapter;
import com.suixin.vy.ui.R;
import com.suixin.vy.ui.SelectActivity;

public class IndexFragment extends Fragment implements OnClickListener {
	/** 记录当前被点击的标题 */
	private String tv_clicked;
	private TextView tv_hot, tv_local, tv_carpooling, tv_samecity;
	private RelativeLayout rl_select;

	/** vp_home里的每个页面 (数据源) */
	private ViewPager vp_home;
	private Fragment v_home, v_currcity, v_pack, v_rank;
	private List<Fragment> list;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		list = new ArrayList<Fragment>();
		View view = inflater.inflate(R.layout.fragment_index, container, false);
		tv_clicked = "hot";
		
		// 实例化整个碎片中的控件
		initView(view);
		// 绑定监听
		addListener();
		// 设置 Vp_home 数据源
		geViewPagertData();
		// ViewPager绑定适配器
		addAdapter();
		// 给翻页添加监听
		vp_homeSetLisntener();
		return view;
	}

	/** ViewPager绑定适配器 */
	private void addAdapter() {
		VPHomeAdapter vpAdapter = new VPHomeAdapter(this.getChildFragmentManager(), list);
		vp_home.setOffscreenPageLimit(3);
		vp_home.setAdapter(vpAdapter);
	}

	/** 给翻页添加监听 */
	private void vp_homeSetLisntener() {
		vp_home.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
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
	}

	/** 获取控件宽度 */
	private void setViewWidth(final TextView view) {
		ViewTreeObserver vto2 = view.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				int width = view.getWidth();
			}
		});
	}
	
	/**给普通控件绑定监听*/
	private void addListener() {
		tv_hot.setOnClickListener(this);
		tv_local.setOnClickListener(this);
		tv_carpooling.setOnClickListener(this);
		tv_samecity.setOnClickListener(this);
		rl_select.setOnClickListener(this);
	}

	/** 实例化碎片中的控件 */
	private void initView(View view) {
		tv_hot = (TextView)view.findViewById(R.id.tv_hot);
		tv_local = (TextView) view.findViewById(R.id.tv_local);
		tv_carpooling = (TextView) view.findViewById(R.id.tv_carpooling);
		tv_samecity = (TextView) view.findViewById(R.id.tv_samecity);
		vp_home = (ViewPager) view.findViewById(R.id.vp_home);
		rl_select=(RelativeLayout)view.findViewById(R.id.rl_home_select);
	}

	/** 设置 Vp_home 数据源 */
	private void geViewPagertData() {
		v_home = new PushFragment();
		v_currcity = new CurrcityFragment();
		v_pack = new PackFragment();
		v_rank = new RankFragment();
		
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
		case R.id.rl_home_select:
			Intent intent = new Intent(getActivity(),SelectActivity.class);
			getActivity().startActivity(intent);
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
