package com.suixin.vy.fragment;

import java.util.ArrayList;
import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnDrawListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.suixin.vy.adapter.VPHomeAdapter;
import com.suixin.vy.ui.R;

public class IndexFragment extends Fragment implements OnClickListener {
	private List<View> list;
	private ViewPager vp_home;
	private boolean iv_order_clicked;
	private LinearLayout ll_home_classify;
	private TextView tv_hot, tv_local, tv_carpooling, tv_samecity;
	private int width;
	private String tv_clicked;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_index, container, false);
		geViewPagertData(inflater, container);
		iv_order_clicked = false;
		tv_clicked = "hot";

		// 实例化控件
		initView(view);
		// 获取控件宽度
		setViewWidth(tv_hot);
		// 绑定监听
		addListener();
		// ViewPager适配器
		VPHomeAdapter vpAdapter = new VPHomeAdapter(list);
		vp_home = (ViewPager) view.findViewById(R.id.vp_home);
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
		return view;
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

	private void initView(View view) {
		ll_home_classify = (LinearLayout) view
				.findViewById(R.id.ll_home_classify);
		tv_hot = (TextView) view.findViewById(R.id.tv_hot);
		tv_local = (TextView) view.findViewById(R.id.tv_local);
		tv_carpooling = (TextView) view.findViewById(R.id.tv_carpooling);
		tv_samecity = (TextView) view.findViewById(R.id.tv_samecity);
	}

	/** 设置转页 */
	private void geViewPagertData(LayoutInflater inflater, ViewGroup container) {
		list = new ArrayList<View>();
		list.add(inflater.inflate(R.layout.viewpager_home_view, container,
				false));
		list.add(inflater.inflate(R.layout.viewpager_currcity_view, container,
				false));
		list.add(inflater.inflate(R.layout.viewpager_pack_view, container,
				false));
		list.add(inflater.inflate(R.layout.viewpager_rank_view, container,
				false));
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
