package com.suixin.vy.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.suixin.vy.core.CircleImageView;
import com.suixin.vy.core.MyBitmapConfig;
import com.suixin.vy.core.TimeFactory;
import com.suixin.vy.model.details.CommentList;
import com.suixin.vy.ui.R;

/**
 * 详细页面的适配器
 * 
 * @author yxy
 * 
 */
public class DetAdapter extends BaseAdapter {
	private List<CommentList> list;
	private LayoutInflater inflater;
	private BitmapUtils bitUtils;
	private Context context;

	public DetAdapter(List<CommentList> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
		bitUtils = new BitmapUtils(context);
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
			v = inflater.inflate(R.layout.listview_comm, null);
			holder = new ViewHolder();
			holder.head = (CircleImageView) v.findViewById(R.id.iv_head);
			holder.name = (TextView) v.findViewById(R.id.tv_name);
			holder.say = (TextView) v.findViewById(R.id.tv_say);
			holder.time = (TextView) v.findViewById(R.id.tv_time);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		CommentList comm = list.get(position);
		bitUtils.display(holder.head, comm.getUser().getAvatarThumbUrl(), MyBitmapConfig.getConfig(context));
		holder.name.setText(comm.getUser().getNick());
		holder.say.setText(comm.getContent());
		holder.time.setText(TimeFactory.format(comm.getCreatedTime()));
		return v;
	}

	class ViewHolder {
		private CircleImageView head;
		private TextView name, time, say;
	}
}
