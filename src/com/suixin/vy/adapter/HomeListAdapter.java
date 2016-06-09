package com.suixin.vy.adapter;

import java.util.List;

import com.suixin.vy.core.CircleImageView;
import com.suixin.vy.ui.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeListAdapter extends BaseAdapter {
	private List list;
	private List<String> typeList;
	private LayoutInflater inflater;
	private static final int TOURPIC = 0;
	private static final int PLAN = 1;
	private static final int USER = 2;

	public HomeListAdapter(List list, List<String> typeList,
			Context context) {
		super();
		this.list = list;
		this.typeList = typeList;
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
		if (typeList.get(position).equals("0")) {
			type = 0;
		}
		if (typeList.get(position).equals("1")) {
			type = 1;
		}
		if (typeList.get(position).equals("2")) {
			type = 2;
		}
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

	@Override
	public View getView(int position, View v, ViewGroup vg) {
		ViewHolder holder = null;
		int type = getItemViewType(position);
		if (v == null) {
			holder = new ViewHolder();
			switch (type) {
			case TOURPIC:
				v =inflater.inflate(R.layout.listview_type_msg,null);
				break;
			case PLAN:
				v = inflater.inflate(R.layout.listview_type_collective,null);
				break;
			case USER:
				v = inflater.inflate(R.layout.listview_type_user,null);
				//holder.
				break;
			}
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		switch (type) {
		case TOURPIC:
			break;
		case PLAN:
			break;
		case USER:
			break;
		}
		return v;
	}

	class ViewHolder {
		private CircleImageView head;
		private TextView name,time,age,describe,location,reviewCount,praiseCount;
		private ImageView[] photos;
		private TextView concern;
		private ImageView bg,isLike;
		private TextView title,startTime,playType,price,qi;
	}
}
