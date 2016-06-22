package com.suixin.vy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.suixin.vy.core.AppConfig;
import com.suixin.vy.core.CircleImageView;
import com.suixin.vy.core.MyBitmapConfig;
import com.suixin.vy.core.TimeFactory;
import com.suixin.vy.model.pack.PlanList;
import com.suixin.vy.ui.PhotoFillActivity;
import com.suixin.vy.ui.R;

public class PackAdapter extends BaseAdapter {
	private List<PlanList> list;
	private LayoutInflater inflater;
	private BitmapUtils bitUtils;
	private Context context;

	/** 个人 */
	private static final int PERSONAL = 0;
	/** 官方 */
	private static final int OFFICIAL = 1;

	public PackAdapter(Context context, List<PlanList> list) {
		super();
		this.list = list;
		inflater = LayoutInflater.from(context);
		this.context = context;
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
		Log.e("ss", "getView");
		int type = getItemViewType(position);
		ViewHolder holder = null;
		if (v == null) {
			holder = new ViewHolder();
			switch (type) {
			case PERSONAL:
				v = inflater.inflate(R.layout.listview_type_pact, null);
				holder.photos = new ImageView[3];
				holder.photos[0] = (ImageView) v.findViewById(R.id.Iv_no1);
				holder.photos[1] = (ImageView) v.findViewById(R.id.Iv_no2);
				holder.photos[2] = (ImageView) v.findViewById(R.id.Iv_no3);
				holder.head = (CircleImageView) v.findViewById(R.id.txt2);
				holder.name = (TextView) v.findViewById(R.id.tv_mon);
				holder.age = (TextView) v.findViewById(R.id.tv_sexandage);
				holder.time = (TextView) v.findViewById(R.id.tv_hello);
				holder.path = (TextView) v.findViewById(R.id.tv_path);
				holder.describe = (TextView) v.findViewById(R.id.im_btn_jpg);
				holder.location = (TextView) v.findViewById(R.id.textView1);
				holder.reviewCount = (TextView) v.findViewById(R.id.tV_1);
				holder.praiseCount = (TextView) v.findViewById(R.id.tV_2);
				break;
			case OFFICIAL:
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
				break;
			}
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		PlanList plan = (PlanList) list.get(position);
		switch (type) {
		case PERSONAL:
			setPersonalContent(holder, plan);
			break;
		case OFFICIAL:
			setPlanContent(holder, plan);
			break;
		}
		return v;
	}

	private void setPersonalContent(ViewHolder holder, final PlanList plan) {
		bitUtils.display(holder.head, plan.getMemberList().get(0)
				.getAvatarThumbUrl(), MyBitmapConfig.getConfig(context));
		holder.name.setText(plan.getMemberList().get(0).getNick());
		holder.age.setText(plan.getMemberList().get(0).getAge() + "");
		// 判断男女
		if (plan.getMemberList().get(0).getSex() != 2) {
			holder.age.setBackgroundResource(R.drawable.boy);
		} else {
			holder.age.setBackgroundResource(R.drawable.girl);
		}
		holder.time.setText(TimeFactory.format(plan.getCreatedTime()));
		String path = "";
		for (int i = 0; i < plan.getDestinationNames().size(); i++) {
			path = path + "-" + plan.getDestinationNames().get(i);
		}
		holder.path.setText(plan.getDepartName() + path);
		holder.describe.setText(plan.getDescription());
		holder.location.setText(plan.getPublishPlace());
		if (plan.getCommentNumber() == 0) {
			holder.reviewCount.setText("评论");
		} else {
			holder.reviewCount.setText(plan.getCommentNumber() + "");
		}
		if (plan.getIsFavored() != 1) {
			Drawable left = context.getResources().getDrawable(
					R.drawable.xiangqu_2);
			left.setBounds(0, 0, left.getMinimumWidth(),
					left.getMinimumHeight());
			holder.praiseCount.setCompoundDrawables(left, null, null, null);

		} else {
			Drawable left = context.getResources().getDrawable(
					R.drawable.xiangqu_1);
			left.setBounds(0, 0, left.getMinimumWidth(),
					left.getMinimumHeight());
			holder.praiseCount.setCompoundDrawables(left, null, null, null);
		}
		if (plan.getFavorNum() == 0) {
			holder.praiseCount.setText("赞");
		} else {
			holder.praiseCount.setText(plan.getFavorNum() + "");
		}
		if (plan.getFirstPhotoUrl().equals("")) {
			holder.photos[0].setVisibility(View.INVISIBLE);
		} else {
			holder.photos[0].setVisibility(View.VISIBLE);
			bitUtils.display(holder.photos[0], plan.getFirstPhotoUrl());
			holder.photos[0].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					startActivity(plan, 1);
				}

			});
		}
		if (plan.getSecondPhotoUrl().equals("")) {
			holder.photos[1].setVisibility(View.INVISIBLE);
		} else {
			holder.photos[1].setVisibility(View.VISIBLE);
			bitUtils.display(holder.photos[1], plan.getSecondPhotoUrl());
			holder.photos[1].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					startActivity(plan, 2);
				}

			});
		}
		if (plan.getThirdPhotoUrl().equals("")) {
			holder.photos[2].setVisibility(View.INVISIBLE);
		} else {
			holder.photos[2].setVisibility(View.VISIBLE);
			bitUtils.display(holder.photos[2], plan.getThirdPhotoUrl());
			holder.photos[2].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					startActivity(plan, 3);
				}

			});
		}
	}
	/**启动照片大图显示页面*/
	private void startActivity(final PlanList plan, int i) {
		Intent intent = new Intent();
		intent.setClass(context, PhotoFillActivity.class);
		intent.putExtra(AppConfig.PHOTOINDEX, i);
		ArrayList<String> arr = new ArrayList<String>();
		if (!plan.getFirstPhotoUrl().equals("")) {
			arr.add(plan.getFirstPhotoUrl());
		}
		if (!plan.getSecondPhotoUrl().equals("")) {
			arr.add(plan.getSecondPhotoUrl());
		}
		if (!plan.getThirdPhotoUrl().equals("")) {
			arr.add(plan.getThirdPhotoUrl());
		}
		intent.putStringArrayListExtra(AppConfig.PHOTO, arr);
		context.startActivity(intent);
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

	@Override
	public int getItemViewType(int position) {
		int type = 0;
		switch ((int) list.get(position).getType()) {
		case 2:
			type = PERSONAL;
			break;
		case 4:
			type = OFFICIAL;
			break;
		}
		return type;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	class ViewHolder {
		private CircleImageView head;
		private TextView name, time, age, path, describe, location,
				reviewCount, praiseCount;
		private ImageView[] photos;
		private ImageView bg, isLike;
		private TextView title, startTime, playType, price, qi;
	}

}
