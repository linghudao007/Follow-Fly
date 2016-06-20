package com.suixin.vz.ui.adapter;

import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.suixin.vy.ui.R;
import com.suixin.vz.strike.model.TourPicList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 双ListView
 * 
 * @author vz
 *
 */
public class FindAdapter extends BaseAdapter {
    private List list;

    private List<TourPicList> typeList;

    private LayoutInflater inflater;

    private Context context;

    private BitmapDisplayConfig bigPicDisplayConfig;

    private BitmapUtils bitUtils;

    private static final int PLAN = 0;

    public FindAdapter(List list, Context context) {
        super();
        this.list = list;
        this.typeList = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        bitUtils = new BitmapUtils(context);
        bigPicDisplayConfig = new BitmapDisplayConfig();
        // bigPicDisplayConfig.setShowOriginal(true);显示原图
        bigPicDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
        bigPicDisplayConfig
                .setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
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
        if (typeList.get(position).equals("1")) {
            type = 0;
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
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
            case PLAN:
                v = inflater.inflate(R.layout.waterfallitem_vz, null);
                holder.fill = (ImageView) v.findViewById(R.id.iv_fill);
                holder.heart = (ImageView) v.findViewById(R.id.iv_heart);
                holder.describe = (TextView) v.findViewById(R.id.tv_describe);
                holder.number = (TextView) v.findViewById(R.id.tv_number);
                break;
            }
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        switch (type) {
        case PLAN:
            setPlanContent(position, holder);
            break;
        }
        return v;
    }

    private void setPlanContent(int position, ViewHolder holder) {
        TourPicList plan = (TourPicList) list.get(position);
        bitUtils.display(holder.fill, plan.getThumbPhotoUrls().get(0));
        if (plan.getIsNewOrHot() == 0) {
            holder.heart.setImageResource(R.drawable.commercial_plan_heart_red);
        } else {
            holder.heart
                    .setImageResource(R.drawable.commercial_plan_heart);
        }
        holder.number.setText(plan.getThumbPhotoUrls().size()+"张");
        holder.describe.setText(plan.getPlaceName());
    }

    class ViewHolder {

        private ImageView fill, heart;

        private TextView describe, number;
    }
}
