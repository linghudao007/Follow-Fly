package com.suixin.vy.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.suixin.vy.core.HeightWarpViewPager;
import com.suixin.vy.ui.ThemeActionActivity;

/** 轮播图片的适配器 */
public class VPLoopAdapter extends PagerAdapter {
	/** 用于显示的列表 */
	private List<View> list;
	/** 数据源列表 */
	private HeightWarpViewPager vp;
	private Context context;

	public VPLoopAdapter(Context context, List<View> list,
			HeightWarpViewPager vp) {
		super();
		this.context = context;
		this.list = list;
		this.vp = vp;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = list.get(position);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, ThemeActionActivity.class);
				context.startActivity(intent);
			}

		});

		container.removeView(list.get(position));
		container.addView(list.get(position));
		return view;
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
}
