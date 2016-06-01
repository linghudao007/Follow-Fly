package com.suixin.vy.fragment;

import java.util.ArrayList;
import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
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
	private ImageView iv_order;
	private boolean iv_order_clicked;
	private LinearLayout ll_home_classify, ll_order;
	private TextView tv_hot, tv_local, tv_carpooling, tv_samecity;
	private TextView tv_currentcity;
	private int width;
	private View v_line;
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
				Toast.makeText(getActivity(), "ScrollStateChanged参数arg0："+arg0, 1000).show();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Toast.makeText(getActivity(), "Scrolled参数arg0："+arg0+"参数arg1："+arg1+"参数arg2："+arg2, 1000).show();
			}

			@Override
			public void onPageSelected(int arg0) {
				Toast.makeText(getActivity(), "Selected当前页面--"+arg0, 1000).show();
			}

		});
		vp_home.setAdapter(vpAdapter);
		return view;
	}

	/** 获取控件宽度 */

	private void setViewWidth(final TextView view) {
		// 此方法要VIEW已绘制了才有用
		// int width=view.getWidth();
		// int w =
		// View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		// int h =
		// View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		// view.measure(w, h);
		// width =view.getMeasuredWidth();
		ViewTreeObserver vto2 = view.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				width = view.getWidth();

			}
		});

		// LayoutParams para2 = (LayoutParams) ll_order.getLayoutParams();
		// para2.width = width*3;// 修改宽度
		// ll_order.setLayoutParams(para2);

	}

	private void addListener() {

		iv_order.setOnClickListener(this);
		tv_hot.setOnClickListener(this);
		tv_local.setOnClickListener(this);
		tv_carpooling.setOnClickListener(this);
		tv_samecity.setOnClickListener(this);
		tv_currentcity.setOnClickListener(this);
	}

	private void initView(View view) {
		v_line = (View) view.findViewById(R.id.v_line);
		iv_order = (ImageView) view.findViewById(R.id.iv_order);
		ll_home_classify = (LinearLayout) view
				.findViewById(R.id.ll_home_classify);
		ll_order = (LinearLayout) view.findViewById(R.id.ll_order);
		tv_hot = (TextView) view.findViewById(R.id.tv_hot);
		tv_local = (TextView) view.findViewById(R.id.tv_local);
		tv_carpooling = (TextView) view.findViewById(R.id.tv_carpooling);
		tv_samecity = (TextView) view.findViewById(R.id.tv_samecity);
		tv_currentcity=(TextView)view.findViewById(R.id.tv_currentcity);
	}

	/** 设置转页 */
	private void geViewPagertData(LayoutInflater inflater, ViewGroup container) {
		list = new ArrayList<View>();
		list.add(inflater.inflate(R.layout.viewpager_home_view, container,
				false));
		list.add(inflater.inflate(R.layout.viewpager_home_view, container,
				false));
		list.add(inflater.inflate(R.layout.viewpager_home_view, container,
				false));
		list.add(inflater.inflate(R.layout.viewpager_home_view, container,
				false));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_order:
			orderClick();
			break;
		case R.id.tv_hot:
			hotClick();
			break;
		case R.id.tv_local:
			localClick();
			break;
		case R.id.tv_carpooling:
			carpoolingClick();
			break;
		case R.id.tv_samecity:
			samecityClick();
			break;
		case R.id.tv_currentcity:
			//Intent intent = new Intent(this);
			break;
		default:
			break;
		}
	}

	/** 热门点击效果 */
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

	/** 拼车点击效果 */
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

	/** 同城点击效果 */
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

	/** 排序按钮点击动画效果 */
	private void orderClick() {
		if (!this.iv_order_clicked) {
			iv_order.setImageResource(R.drawable.filtericon_click);
			this.iv_order_clicked = true;
			if (ll_order.getVisibility() == View.GONE) {
				ll_order.setVisibility(View.VISIBLE);
				//动态修改宽度
				LayoutParams para2 = (LayoutParams) ll_order.getLayoutParams();
				para2.width = width*3;// 修改宽度
				ll_order.setLayoutParams(para2);
				ll_order.setPivotX(width * 3);
			}
			tabAnim(-width, 0, 0, 1);
		} else {
			iv_order.setImageResource(R.drawable.filtericon);
			this.iv_order_clicked = false;
			tabAnim(0, 1, 1, 0);
		}
	}

	/** tab的动画效果,移动的宽，透明度，缩放 */
	private void tabAnim(int tox, float toalpha, float fromscalex,
			float toscalex) {
		PropertyValuesHolder holder_xl = PropertyValuesHolder.ofFloat(
				"translationX", tox);
		PropertyValuesHolder holder_xc = PropertyValuesHolder.ofFloat(
				"translationX", tox * 2);
		PropertyValuesHolder holder_xs = PropertyValuesHolder.ofFloat(
				"translationX", tox * 3);
		PropertyValuesHolder holder_a = PropertyValuesHolder.ofFloat("alpha",
				toalpha);
		PropertyValuesHolder holder_s = PropertyValuesHolder.ofFloat("scaleX",
				fromscalex, toscalex);

		ObjectAnimator animh = ObjectAnimator.ofPropertyValuesHolder(tv_hot,
				holder_a);

		ObjectAnimator animl = ObjectAnimator.ofPropertyValuesHolder(tv_local,
				holder_xl, holder_a);

		ObjectAnimator animc = ObjectAnimator.ofPropertyValuesHolder(
				tv_carpooling, holder_xc, holder_a);

		ObjectAnimator anims = ObjectAnimator.ofPropertyValuesHolder(
				tv_samecity, holder_xs, holder_a);
		//判断当前选中的按钮，使此按钮只移动不改变可见度
		if (tv_clicked.equals("hot")) {
			animh = ObjectAnimator.ofPropertyValuesHolder(tv_hot);
		}
		if (tv_clicked.equals("local")) {
			animl = ObjectAnimator.ofPropertyValuesHolder(tv_local, holder_xl);
		}
		if (tv_clicked.equals("carpooling")) {
			animc = ObjectAnimator.ofPropertyValuesHolder(tv_carpooling,
					holder_xc);
		}
		if (tv_clicked.equals("samecity")) {
			anims = ObjectAnimator.ofPropertyValuesHolder(tv_samecity,
					holder_xs);
		}
		ObjectAnimator animv = ObjectAnimator.ofPropertyValuesHolder(v_line,
				holder_xs);
		ObjectAnimator animll_or = ObjectAnimator.ofPropertyValuesHolder(
				ll_order, holder_s);
		AnimatorSet set = new AnimatorSet();
		set.playTogether(animh, animl, animc, anims, animv, animll_or);
		set.setDuration(500);
		set.start();
	}

}
