package com.suixin.vy.fragment;

import java.util.ArrayList;
import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Fragment;
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
	private int width,height;
	private View v_line;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_index, container, false);
		geViewPagertData(inflater, container);
		iv_order_clicked = false;

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
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int arg0) {
				Toast.makeText(getActivity(), "xxx", 1000).show();
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

		LayoutParams para2 = (LayoutParams) ll_order.getLayoutParams();

		para2.width = width*3;// 修改宽度

		ll_order.setLayoutParams(para2);

	}

	private void addListener() {
		iv_order.setOnClickListener(this);

	}

	private void initView(View view) {
		v_line=(View)view.findViewById(R.id.v_line);
		iv_order = (ImageView) view.findViewById(R.id.iv_order);
		ll_home_classify = (LinearLayout) view
				.findViewById(R.id.ll_home_classify);
		ll_order = (LinearLayout) view.findViewById(R.id.ll_order);
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
		default:
			break;
		}
	}

	/** 排序按钮点击动画效果 */
	private void orderClick() {
		if (!this.iv_order_clicked) {
			iv_order.setImageResource(R.drawable.filtericon_click);
			this.iv_order_clicked = true;
			PropertyValuesHolder holder_xl = PropertyValuesHolder.ofFloat(
					"translationX", -width);
			PropertyValuesHolder holder_xc = PropertyValuesHolder.ofFloat(
					"translationX", -width * 2);
			PropertyValuesHolder holder_xs = PropertyValuesHolder.ofFloat(
					"translationX", -width * 3);
			PropertyValuesHolder holder_a = PropertyValuesHolder.ofFloat(
					"alpha", 0);

			ObjectAnimator animl = ObjectAnimator.ofPropertyValuesHolder(
					tv_local, holder_xl, holder_a);
			ObjectAnimator animc = ObjectAnimator.ofPropertyValuesHolder(
					tv_carpooling, holder_xc, holder_a);
			ObjectAnimator anims = ObjectAnimator.ofPropertyValuesHolder(
					tv_samecity, holder_xs, holder_a);
			ObjectAnimator animv = ObjectAnimator.ofPropertyValuesHolder(v_line, holder_xs);
			ObjectAnimator animll_or = ObjectAnimator.ofPropertyValuesHolder(
					ll_order, holder_xs);
			AnimatorSet set = new AnimatorSet();
			set.playTogether(animl, animc, anims,animv,animll_or);
			set.setDuration(500);
			set.start();
			Toast.makeText(getActivity(), width + "", 1000).show();

		} else {
			iv_order.setImageResource(R.drawable.filtericon);
			this.iv_order_clicked = false;
			PropertyValuesHolder holder_xl = PropertyValuesHolder.ofFloat(
					"translationX", 0);
			PropertyValuesHolder holder_xc = PropertyValuesHolder.ofFloat(
					"translationX", 0);
			PropertyValuesHolder holder_xs = PropertyValuesHolder.ofFloat(
					"translationX",0);
			PropertyValuesHolder holder_a = PropertyValuesHolder.ofFloat(
					"alpha", 1);

			ObjectAnimator animl = ObjectAnimator.ofPropertyValuesHolder(
					tv_local, holder_xl, holder_a);
			ObjectAnimator animc = ObjectAnimator.ofPropertyValuesHolder(
					tv_carpooling, holder_xc, holder_a);
			ObjectAnimator anims = ObjectAnimator.ofPropertyValuesHolder(
					tv_samecity, holder_xs, holder_a);
			ObjectAnimator animv = ObjectAnimator.ofPropertyValuesHolder(v_line, holder_xs);
			ObjectAnimator animll_or = ObjectAnimator.ofPropertyValuesHolder(
					ll_order, holder_xs);
			AnimatorSet set = new AnimatorSet();
			set.playTogether(animl, animc, anims,animv,animll_or);
			set.setDuration(500);
			set.start();
		}
	}

}
