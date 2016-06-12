package com.suixin.vz.ui.adapter;

import java.util.List;

import com.suixin.vy.core.CircleImageView;
import com.suixin.vy.ui.R;
import com.suixin.vz.model.TourPicList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeListAdapters extends BaseAdapter {
	private List<TourPicList> list;
	private LayoutInflater inflater;
	private static final int TOURPIC = 0;

	public HomeListAdapters(List<TourPicList> list,
			Context context) {
		super();
		
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return list != null ? list.get(position) : null;
	}

	@Override
	public int getItemViewType(int position) {
		int type = 0;
		return type;
	}

	@Override
	public int getViewTypeCount() {
		return 3;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
    @Override
	public View getView(int position, View v, ViewGroup vg) {
		ViewHolder holder = null;
		int type = getItemViewType(position);
		if (v == null) {
			holder = new ViewHolder();
			switch (type) {
			case TOURPIC:
				v =inflater.inflate(R.layout.fragment_hot_vz,null);
				break;
			}
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		switch (type) {
		case TOURPIC:
			break;
		}
		return v;
	}

	class ViewHolder {
		private CircleImageView head_vz;
		private TextView tv_mon,tv_sexandage,tv_hello,tv_vz_concern,im_btn_jpg,tv_place,tV_comment,tV_laudation;
	}
}
