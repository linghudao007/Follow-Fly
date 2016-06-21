package com.suixin.vy.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PhotoAdapter extends PagerAdapter{
	private List<View> list;
	
	

	public PhotoAdapter(List<View> list) {
		super();
		this.list = list;
	}


	@Override
	public int getCount() {
		return list!=null?list.size():0;
	}

	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
	}


	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v = list.get(position);
		container.removeView(list.get(position));
		container.addView(v);
		return v;
	}


	@Override
	public boolean isViewFromObject(View v, Object obj) {
		return v==obj;
	}

}
