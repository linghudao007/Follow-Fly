package com.suixin.vy.adapter;

import java.util.List;
import com.suixin.vy.core.CircleImageView;
import com.suixin.vy.ui.R;
import com.suixin.vy.ui.R.string;
import com.suixin.vy.model.TourPicList;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.suixin.vy.core.CircleImageView;
import com.suixin.vy.core.TimeFactory;
import com.suixin.vy.model.PlanList;
import com.suixin.vy.model.UserList;
import com.suixin.vy.ui.R;

public class HomeListAdapter extends BaseAdapter {
	private List list;
	private List<String> typeList;
	private LayoutInflater inflater;
	private Context context;
	private BitmapDisplayConfig bigPicDisplayConfig;
	private BitmapUtils bitUtils;
	private static final int TOURPIC_1 = 1;
	private static final int TOURPIC_2 = 2;
	private static final int TOURPIC_3 = 3;
	private static final int TOURPIC_4 = 4;
	private static final int TOURPIC_5 = 5;
	private static final int TOURPIC_6 = 6;
	private static final int TOURPIC_7 = 7;
	private static final int TOURPIC_8 = 8;
	private static final int TOURPIC_9 = 9;
	private static final int PLAN = 0;
	private static final int USER = 10;

	public HomeListAdapter(List list, List<String> typeList, Context context) {
		super();
		this.list = list;
		this.typeList = typeList;
		this.context = context;
		inflater = LayoutInflater.from(context);
		bitUtils = new BitmapUtils(context);
		bigPicDisplayConfig = new BitmapDisplayConfig();
		// bigPicDisplayConfig.setShowOriginal(true);显示原图
		bigPicDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
		bigPicDisplayConfig.setBitmapMaxSize(BitmapCommonUtils
				.getScreenSize(context));
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
			switch (((TourPicList) list.get(position)).getThumbPhotoUrls()
					.size()) {
			case 1:
				type = 1;
				break;
			case 2:
				type = 2;
				break;
			case 3:
				type = 3;
				break;
			case 4:
				type = 4;
				break;
			case 5:
				type = 5;
				break;
			case 6:
				type = 6;
				break;
			case 7:
				type = 7;
				break;
			case 8:
				type = 8;
				break;
			case 9:
				type = 9;
				break;
			}
		}
		if (typeList.get(position).equals("1")) {
			type = 0;
		}
		if (typeList.get(position).equals("2")) {
			type = 10;
		}
		return type;
	}

	@Override
	public int getViewTypeCount() {
		return 11;
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
			case TOURPIC_1:
				v = inflater.inflate(R.layout.listview_type_msg_1, null);
				initTourPic_Same(v, holder, type);
				holder.photos[0] = (ImageView) v.findViewById(R.id.Iv_no1);
				break;
			case TOURPIC_2:
				v = inflater.inflate(R.layout.listview_type_msg_2, null);
				initTourPic_Same(v, holder, type);
				holder.photos[0] = (ImageView) v.findViewById(R.id.Iv_no2_1);
				holder.photos[1] = (ImageView) v.findViewById(R.id.Iv_no2_2);
				break;
			case TOURPIC_3:
				v = inflater.inflate(R.layout.listview_type_msg_3, null);
				initTourPic_Same(v, holder, type);
				holder.photos[0] = (ImageView) v.findViewById(R.id.Iv_no3_1);
				holder.photos[1] = (ImageView) v.findViewById(R.id.Iv_no3_2);
				holder.photos[2] = (ImageView) v.findViewById(R.id.iv_no3_3);
				break;
			case TOURPIC_4:
				v = inflater.inflate(R.layout.listview_type_msg_4, null);
				initTourPic_Same(v, holder, type);
				holder.photos[0] = (ImageView) v.findViewById(R.id.Iv_no4_1);
				holder.photos[1] = (ImageView) v.findViewById(R.id.Iv_no4_2);
				holder.photos[2] = (ImageView) v.findViewById(R.id.Iv_no4_3);
				holder.photos[3] = (ImageView) v.findViewById(R.id.Iv_no4_4);
				break;
			case TOURPIC_5:
				v = inflater.inflate(R.layout.listview_type_msg_5, null);
				initTourPic_Same(v, holder, type);
				holder.photos[0] = (ImageView) v.findViewById(R.id.Iv_no5_1);
				holder.photos[1] = (ImageView) v.findViewById(R.id.Iv_no5_2);
				holder.photos[2] = (ImageView) v.findViewById(R.id.Iv_no5_3);
				holder.photos[3] = (ImageView) v.findViewById(R.id.Iv_no5_4);
				holder.photos[4] = (ImageView) v.findViewById(R.id.Iv_no5_5);
				break;
			case TOURPIC_6:
				v = inflater.inflate(R.layout.listview_type_msg_6, null);
				initTourPic_Same(v, holder, type);
				holder.photos[0] = (ImageView) v.findViewById(R.id.Iv_no6_1);
				holder.photos[1] = (ImageView) v.findViewById(R.id.Iv_no6_2);
				holder.photos[2] = (ImageView) v.findViewById(R.id.Iv_no6_3);
				holder.photos[3] = (ImageView) v.findViewById(R.id.Iv_no6_4);
				holder.photos[4] = (ImageView) v.findViewById(R.id.Iv_no6_5);
				holder.photos[5] = (ImageView) v.findViewById(R.id.Iv_no6_6);
				break;
			case TOURPIC_7:
				v = inflater.inflate(R.layout.listview_type_msg_7, null);
				initTourPic_Same(v, holder, type);
				holder.photos[0] = (ImageView) v.findViewById(R.id.Iv_no7_1);
				holder.photos[1] = (ImageView) v.findViewById(R.id.Iv_no7_2);
				holder.photos[2] = (ImageView) v.findViewById(R.id.Iv_no7_3);
				holder.photos[3] = (ImageView) v.findViewById(R.id.Iv_no7_4);
				holder.photos[4] = (ImageView) v.findViewById(R.id.Iv_no7_5);
				holder.photos[5] = (ImageView) v.findViewById(R.id.Iv_no7_6);
				holder.photos[6] = (ImageView) v.findViewById(R.id.Iv_no7_7);
				break;
			case TOURPIC_8:
				v = inflater.inflate(R.layout.listview_type_msg_8, null);
				initTourPic_Same(v, holder, type);
				holder.photos[0] = (ImageView) v.findViewById(R.id.Iv_no8_1);
				holder.photos[1] = (ImageView) v.findViewById(R.id.Iv_no8_2);
				holder.photos[2] = (ImageView) v.findViewById(R.id.Iv_no8_3);
				holder.photos[3] = (ImageView) v.findViewById(R.id.Iv_no8_4);
				holder.photos[4] = (ImageView) v.findViewById(R.id.Iv_no8_5);
				holder.photos[5] = (ImageView) v.findViewById(R.id.Iv_no8_6);
				holder.photos[6] = (ImageView) v.findViewById(R.id.Iv_no8_7);
				holder.photos[7] = (ImageView) v.findViewById(R.id.Iv_no8_8);
				break;
			case TOURPIC_9:
				v = inflater.inflate(R.layout.listview_type_msg_9, null);
				initTourPic_Same(v, holder, type);
				holder.photos[0] = (ImageView) v.findViewById(R.id.Iv_no9_1);
				holder.photos[1] = (ImageView) v.findViewById(R.id.Iv_no9_2);
				holder.photos[2] = (ImageView) v.findViewById(R.id.Iv_no9_3);
				holder.photos[3] = (ImageView) v.findViewById(R.id.Iv_no9_4);
				holder.photos[4] = (ImageView) v.findViewById(R.id.Iv_no9_5);
				holder.photos[5] = (ImageView) v.findViewById(R.id.Iv_no9_6);
				holder.photos[6] = (ImageView) v.findViewById(R.id.Iv_no9_7);
				holder.photos[7] = (ImageView) v.findViewById(R.id.Iv_no9_8);
				holder.photos[8] = (ImageView) v.findViewById(R.id.Iv_no9_9);
				break;
			case PLAN:
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
			case USER:
				v = inflater.inflate(R.layout.listview_type_user, null);
				holder.head = (CircleImageView) v.findViewById(R.id.iv_head);
				holder.name = (TextView) v.findViewById(R.id.tv_username);
				holder.age = (TextView) v.findViewById(R.id.tv_sexandage);
				holder.location = (TextView) v.findViewById(R.id.tv_location);
				holder.describe = (TextView) v.findViewById(R.id.tv_say);
				break;
			}
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		switch (type) {
		case TOURPIC_1:
		case TOURPIC_2:
		case TOURPIC_3:
		case TOURPIC_4:
		case TOURPIC_5:
		case TOURPIC_6:
		case TOURPIC_7:
		case TOURPIC_8:
		case TOURPIC_9:
			TourPicList tourPic = (TourPicList) list.get(position);
			setTourPicContent(holder, tourPic);
			break;
		case PLAN:
			setPlanContent(position, holder);
			break;
		case USER:
			setUserContent(position, holder);
			break;
		}
		return v;
	}

	private void setTourPicContent(ViewHolder holder, TourPicList tourPic) {
		bitUtils.display(holder.head, tourPic.getCreaterUser()
				.getAvatarThumbUrl(), bigPicDisplayConfig);
		holder.name.setText(tourPic.getCreaterUser().getNick());
		holder.age.setText(tourPic.getCreaterUser().getAge() + "");
		// 判断男女
		if (tourPic.getCreaterUser().getSex() != 2) {
			holder.age.setBackgroundResource(R.drawable.boy);
		} else {
			holder.age.setBackgroundResource(R.drawable.girl);
		}
		holder.time.setText(TimeFactory.format(tourPic.getCreatedTime()));
		holder.describe.setText(tourPic.getDescription());
		holder.location.setText(tourPic.getPlaceName());
		if (tourPic.getCommentNum() == 0) {
			holder.reviewCount.setText("评论");
		} else {
			holder.reviewCount.setText(tourPic.getCommentNum() + "");
		}
		if (tourPic.getIsLike() != 1) {
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
		if (tourPic.getLikeNum() == 0) {
			holder.praiseCount.setText("赞");
		} else {
			holder.praiseCount.setText(tourPic.getLikeNum() + "");
		}
		for (int i = 0; i < tourPic.getThumbPhotoUrls().size(); i++) {
			bitUtils.display(holder.photos[i],
					tourPic.getThumbPhotoUrls().get(i));
		}
	}

	/** 实例化相同的部分 */
	private void initTourPic_Same(View v, ViewHolder holder, int type) {
		holder.photos = new ImageView[type];
		holder.head = (CircleImageView) v.findViewById(R.id.txt2);
		holder.name = (TextView) v.findViewById(R.id.tv_mon);
		holder.age = (TextView) v.findViewById(R.id.tv_sexandage);
		holder.time = (TextView) v.findViewById(R.id.tv_hello);
		holder.describe = (TextView) v.findViewById(R.id.im_btn_jpg);
		holder.location = (TextView) v.findViewById(R.id.textView1);
		holder.reviewCount = (TextView) v.findViewById(R.id.tV_1);
		holder.praiseCount = (TextView) v.findViewById(R.id.tV_2);
	}

	/** 设置Plan的显示 */
	private void setPlanContent(int position, ViewHolder holder) {
		PlanList plan = (PlanList) list.get(position);
		bitUtils.display(holder.bg, plan.getFirstPhotoUrl());
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

	/** 设置USER的显示 */
	private void setUserContent(int position, ViewHolder holder) {
		UserList user = (UserList) list.get(position);
		holder.name.setText(user.getNick());
		holder.age.setText(user.getAge() + "");
		// 判断男女
		if (user.getSex() != 2) {
			holder.age.setBackgroundResource(R.drawable.boy);
		} else {
			holder.age.setBackgroundResource(R.drawable.girl);
		}
		if (!TextUtils.isEmpty(user.getLivingPlace())) {
			holder.location.setText(user.getLivingPlace());
		} else {
			holder.location.setText("");
		}
		if (!TextUtils.isEmpty(user.getSignature())) {
			holder.describe.setText(user.getSignature());
		} else {
			holder.location.setText("");
		}
		bitUtils.display(holder.head, user.getAvatarThumbUrl(),
				bigPicDisplayConfig);
	}

	class ViewHolder {
		private CircleImageView head;
		private TextView name, time, age, describe, location, reviewCount,
				praiseCount;
		private ImageView[] photos;
		private TextView concern;
		private ImageView bg, isLike;
		private TextView title, startTime, playType, price, qi;
	}
}