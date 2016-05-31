package com.suixin.vy.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class VPHomeAdapter extends PagerAdapter{
	private List<View> list;
	
	public VPHomeAdapter(List<View> list) {
		super();
		this.list = list;
	}

	@Override
	public int getCount() {
		
		return list!=null?list.size():0;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view==object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
		
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = list.get(position);
		container.removeView(view);
		container.addView(view);
		return view;
	}
	
}
