package com.suixin.vy.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.suixin.vy.adapter.VPHomeAdapter;
import com.suixin.vy.ui.R;

public class IndexFragment extends Fragment {
	private List<View> list;
	private ViewPager vp_home;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_index, container, false);
		geViewPagertData(inflater, container);
		//ViewPager适配器
		VPHomeAdapter vpAdapter = new VPHomeAdapter(list);
		vp_home = (ViewPager) view.findViewById(R.id.vp_home);
		vp_home.setOnPageChangeListener(new OnPageChangeListener(){

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
	/**设置转页*/
	private void geViewPagertData(LayoutInflater inflater, ViewGroup container) {
		list = new ArrayList<View>();
		list.add(inflater.inflate(R.layout.viewpager_home_view, container, false));
		list.add(inflater.inflate(R.layout.viewpager_home_view, container, false));
		list.add(inflater.inflate(R.layout.viewpager_home_view, container, false));
		list.add(inflater.inflate(R.layout.viewpager_home_view, container, false));
	}

}
