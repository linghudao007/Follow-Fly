package com.suixin.vy.adapter;

import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.suixin.vy.core.HeightWarpViewPager;
import com.suixin.vy.core.MyBitmapConfig;
import com.suixin.vy.model.BannerList;
import com.suixin.vy.ui.R;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.graphics.Bitmap;

/** 轮播图片的适配器 */
public class VPLoopAdapter extends PagerAdapter {
	/** 用于显示的列表 */
	private List<View> list;
	/** 数据源列表 */
	private HeightWarpViewPager vp;

	public VPLoopAdapter(Context context, List<View> list,
			 HeightWarpViewPager vp) {
		super();
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
