package com.suixin.vy.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
/**
 * 首页模块的翻页适配器
 * @author Administrator
 *
 */
public class VPHomeAdapter extends FragmentStatePagerAdapter {
	private List<Fragment> list;

	public VPHomeAdapter(FragmentManager fm,List<Fragment> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public Fragment getItem(int position) {
		return list!=null?list.get(position):null;
	}

}
