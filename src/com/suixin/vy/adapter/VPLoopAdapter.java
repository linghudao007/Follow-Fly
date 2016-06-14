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
	private List<BannerList> loopList;
	private BitmapUtils bitUtils;
	private Context context;
	private HeightWarpViewPager vp;

	public VPLoopAdapter(Context context, List<View> list,
			List<BannerList> loopList, HeightWarpViewPager vp) {
		super();
		this.list = list;
		this.loopList = loopList;
		bitUtils = new BitmapUtils(context);
		this.vp = vp;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		//container.removeView(list.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		View view = list.get(position);
		ImageView iv_show = (ImageView) view.findViewById(R.id.iv_show);
		container.removeView(list.get(position));
		if (loopList != null && loopList.size() > 0) {
			if (position == 0) {
				bitUtils.display(iv_show, loopList.get(2).getCoverThumbUrl(),
						MyBitmapConfig.getConfig(context));
			} else if (position == 4) {
				bitUtils.display(iv_show, loopList.get(0).getCoverThumbUrl(),
						MyBitmapConfig.getConfig(context));
			} else {
				bitUtils.display(iv_show, loopList.get(position-1)
						.getCoverThumbUrl(), MyBitmapConfig.getConfig(context));
			}
		}
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
