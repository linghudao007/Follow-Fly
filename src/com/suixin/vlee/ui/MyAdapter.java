package com.suixin.vlee.ui;

import java.util.List;

import com.suixin.vy.ui.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 想比较原来的多了 getItemViewType和getViewTypeCount这两个方法， 原来循环使用layout布局，起到了优化的作用
 * */
public class MyAdapter extends BaseAdapter {

	public static final String KEY = "key";
	public static final String VALUE = "value";

	public static final int listvit_item_one = 0;// 5种不同的布局
	public static final int listvit_item_tow = 1;
	public static final int listvit_item_photo = 2;
	public static final int listvit_item_sex = 3;
	public static final int listvit_item_foot = 4;
	private LayoutInflater mInflater;

	private List<Message> myList;

	public MyAdapter(Context context, List<Message> myList) {

		this.myList = myList;

		for (Message msg : myList) {

			Log.d("myList:", msg.getType() + "");
		}

		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return myList.size();
	}

	public Object getItem(int arg0) {
		return myList.get(arg0);
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public View getView(int position, View convertView, ViewGroup arg2) {

		int type = getItemViewType(position);
		if (convertView == null) {

			switch (type) {
			case listvit_item_one:

				convertView = mInflater.inflate(R.layout.listview_lee_item_one,
						null);
				break;

			case listvit_item_tow:

				convertView = mInflater.inflate(R.layout.listview_lee_item_tow,
						null);
				break;

			case listvit_item_photo:

				convertView = mInflater.inflate(
						R.layout.listview_lee_item_photo, null);
				break;

			case listvit_item_sex:

				convertView = mInflater.inflate(R.layout.listview_lee_item_sex,
						null);
				break;
			case listvit_item_foot:

				convertView = mInflater.inflate(
						R.layout.listview_lee_item_foot, null);
				break;

			default:
				break;
			}

		}
		return convertView;
	}

	/**
	 * 根据数据源的position返回需要显示的的layout的type
	 * 
	 * */
	@Override
	public int getItemViewType(int position) {

		Message msg = myList.get(position);
		int type = msg.getType();
		return type;
	}

}
