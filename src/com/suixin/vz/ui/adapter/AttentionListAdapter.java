package com.suixin.vz.ui.adapter;

import java.util.List;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.suixin.attention.molde.TourPicList;
import com.suixin.vy.core.CircleImageView;
import com.suixin.vy.core.TimeFactory;
import com.suixin.vy.ui.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AttentionListAdapter extends BaseAdapter {
    private List<TourPicList> list;

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

    public AttentionListAdapter(List<TourPicList> list, Context context) {
        super();
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        bitUtils = new BitmapUtils(context);
        bigPicDisplayConfig = new BitmapDisplayConfig();
        bigPicDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
        bigPicDisplayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
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
        switch (((TourPicList) list.get(position)).getThumbPhotoUrls().size()) {
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
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 10;
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
            }
            v.setTag(holder);
        } 
            else {
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
        }
        return v;
    }

    private void setTourPicContent(ViewHolder holder, TourPicList tourPic) {
        bitUtils.display(holder.head,
                tourPic.getCreaterUser().getAvatarThumbUrl(),
                bigPicDisplayConfig);
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
            Drawable left = context.getResources()
                    .getDrawable(R.drawable.xiangqu_2);
            left.setBounds(0, 0, left.getMinimumWidth(),
                    left.getMinimumHeight());
            holder.praiseCount.setCompoundDrawables(left, null, null, null);

        } else {
            Drawable left = context.getResources()
                    .getDrawable(R.drawable.xiangqu_1);
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

    class ViewHolder {
        private CircleImageView head;

        private TextView name, time, age, describe, location, reviewCount,
                praiseCount;

        private ImageView[] photos;
    }
}
