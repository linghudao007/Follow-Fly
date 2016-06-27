package com.suixin.vy.adapter;

import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.suixin.vy.adapter.SelectAdapter.ViewHolder;
import com.suixin.vy.core.CircleImageView;
import com.suixin.vy.core.MyBitmapConfig;
import com.suixin.vy.model.theme.PlanList;
import com.suixin.vy.ui.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ThemeAdapter extends BaseAdapter {
	private List<PlanList> list;
	private LayoutInflater inflater;
	private Context context;
	private BitmapUtils bitUtils;

	public ThemeAdapter(Context context, List<PlanList> list) {
		super();
		this.context = context;
		bitUtils = new BitmapUtils(context);
		this.list = list;
		this.inflater = LayoutInflater.from(context);
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
			v = inflater.inflate(R.layout.listview_type_collective, null);
			holder.bg = (ImageView) v.findViewById(R.id.iv_fillbg);
			holder.isLike = (ImageView) v.findViewById(R.id.iv_islike);
			holder.title = (TextView) v.findViewById(R.id.tv_description);
			holder.location = (TextView) v.findViewById(R.id.tv_playline);
			holder.playType = (TextView) v.findViewById(R.id.tv_tag);
			holder.startTime = (TextView) v.findViewById(R.id.tv_starttime);
			holder.time = (TextView) v.findViewById(R.id.tv_allday);
			holder.price = (TextView) v.findViewById(R.id.tv_price);
			holder.qi = (TextView) v.findViewById(R.id.tv_pepo);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		PlanList plan = (PlanList) list.get(position);
		setPlanContent(holder, plan);
		return v;
	}

	/** 设置Plan的显示 */
	private void setPlanContent(ViewHolder holder, PlanList plan) {
		bitUtils.display(holder.bg, plan.getFirstPhotoUrl(),
				MyBitmapConfig.getConfig(context));
		if (plan.getIsFavored() == 0) {
			holder.isLike.setImageResource(R.drawable.commercial_plan_heart);
		} else {
			holder.isLike
					.setImageResource(R.drawable.commercial_plan_heart_red);
		}
		holder.title.setText(plan.getDeclaration());
		holder.location.setText(plan.getDestinationNames().get(0));
		if (plan.getTourThemes().size() >= 1) {
			holder.playType.setText(plan.getTourThemes().get(0).getTitle());
			holder.playType.setVisibility(View.VISIBLE);
		} else {
			holder.playType.setVisibility(View.GONE);
		}
		if (plan.getStage().size() == 1) {
			holder.startTime.setText(plan.getStartTime());
			holder.time.setText("全程" + plan.getDaysLong() + "天");
			holder.qi.setText("/人");
		} else {
			holder.startTime.setText("全程" + plan.getDaysLong() + "天");
			holder.time.setText("多期活动");
			holder.qi.setText("起/人");
		}
		holder.price.setText(plan.getCostPrice() + "");
	}

	class ViewHolder {
		private TextView time,location;
		private ImageView bg, isLike;
		private TextView title, startTime, playType, price, qi;
	}
}
