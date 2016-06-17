package com.suixin.vy.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.suixin.vy.adapter.HomeListAdapter.ViewHolder;
import com.suixin.vy.core.CircleImageView;
import com.suixin.vy.core.MyBitmapConfig;
import com.suixin.vy.model.UserList;
import com.suixin.vy.model.rank.HotDuckrList;
import com.suixin.vy.ui.R;

public class RankListAdapter extends BaseAdapter {
	private List<HotDuckrList> list;
	private LayoutInflater inflater;
	private BitmapUtils bitUtils;
	private Context context;

	public RankListAdapter(Context context, List<HotDuckrList> list) {
		super();
		this.list = list;
		bitUtils = new BitmapUtils(context);
		inflater = LayoutInflater.from(context);
		this.context = context;
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
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup arg2) {
		ViewHolder holder = null;
		if (v == null) {
			holder = new ViewHolder();
			v = inflater.inflate(R.layout.listview_type_user, null);
			holder.head = (CircleImageView) v.findViewById(R.id.iv_head);
			holder.name = (TextView) v.findViewById(R.id.tv_username);
			holder.age = (TextView) v.findViewById(R.id.tv_sexandage);
			holder.concerncount = (TextView) v.findViewById(R.id.tv_location);
			holder.describe = (TextView) v.findViewById(R.id.tv_say);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		setUserContent(position, holder);
		return v;
	}

	/** 设置USER的显示 */
	private void setUserContent(int position, ViewHolder holder) {
		HotDuckrList hot = list.get(position);
		holder.name.setText(hot.getNick());
		holder.age.setText(hot.getAge() + "");
		// 判断男女
		if (hot.getSex() != 2) {
			holder.age.setBackgroundResource(R.drawable.boy);
		} else {
			holder.age.setBackgroundResource(R.drawable.girl);
		}
		holder.concerncount.setText(hot.getFansNum() + "人关注");
		holder.describe.setText(hot.getSignature());
		bitUtils.display(holder.head, hot.getAvatarThumbUrl(),
				MyBitmapConfig.getConfig(context));
	}
	class ViewHolder {
		private ImageView head;
		private TextView name, age, describe, concerncount;
		private TextView concern;
	}

}
