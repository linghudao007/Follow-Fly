package com.suixin.vy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.suixin.vy.ui.R;

public class PackFragment extends Fragment implements OnClickListener{
	/**约伴页面的接口*/
	private static final String URLSTR="http://www.duckr.cn/api/v5/homepage/partner/";
	/**页面控件*/
	private LinearLayout ll_selectable,ll_selecttag;
	private TextView[] tv;
	private EditText et_citystart,et_cityend;
	private ImageView iv_unfold;
	private ListView lv_pank;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.viewpager_pack_view,
				container, false);
		return view;
	}
	@Override
	public void onClick(View arg0) {
		
	}
}
