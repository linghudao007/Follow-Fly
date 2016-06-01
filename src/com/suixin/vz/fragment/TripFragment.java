package com.suixin.vz.fragment;

import java.util.ArrayList;
import java.util.List;

import com.suixin.vy.ui.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TripFragment extends Fragment {
    private CategoryTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;

   

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.activity_main_vz_fg, container, false);
    	 tabs = (CategoryTabStrip)view.findViewById(R.id.category_strip);
         pager = (ViewPager)view.findViewById(R.id.view_pager);
         adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
         pager.setAdapter(adapter);
         tabs.setViewPager(pager);
		return view;
	}



	public class MyPagerAdapter extends FragmentPagerAdapter {

        private final List<String> catalogs = new ArrayList<String>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            catalogs.add(getString(R.string.hot));
            catalogs.add("\u672c\u5730");
            catalogs.add(getString(R.string.found));
            catalogs.add(getString(R.string.attention));
            /*catalogs.add(getString(R.string.category_entertainment));
            catalogs.add(getString(R.string.category_tech));
            catalogs.add(getString(R.string.category_finance));
            catalogs.add(getString(R.string.category_military));
            catalogs.add(getString(R.string.category_world));
            catalogs.add(getString(R.string.category_image_ppmm));
            catalogs.add(getString(R.string.category_health));
            catalogs.add(getString(R.string.category_government));*/
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return catalogs.get(position);
        }

        @Override
        public int getCount() {
            return catalogs.size();
        }

        @Override
        public Fragment getItem(int position) {
            return NewsFragment.newInstance(position);
        }

    }

}
