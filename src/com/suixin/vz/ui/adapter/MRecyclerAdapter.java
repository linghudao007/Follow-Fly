package com.suixin.vz.ui.adapter;

import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.suixin.vy.ui.R;
import com.suixin.vz.strike.model.TourPicList;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MRecyclerAdapter
        extends RecyclerView.Adapter<MRecyclerAdapter.MyHolder> {
    
    public interface OnRecyckerItemClickListener{
        
        void onItemClick(View item,int position);
    }
    
    private OnRecyckerItemClickListener listener;
    
    
    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView fill, heart;

        private TextView describe, number;
        private View item;
        public View getItem() {
            return item;
        }
        public MyHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            fill = (ImageView) itemView.findViewById(R.id.iv_fill);
            heart = (ImageView) itemView.findViewById(R.id.iv_heart);
            describe = (TextView) itemView.findViewById(R.id.tv_describe);
            number = (TextView) itemView.findViewById(R.id.tv_number);
        }
    }

    private List<TourPicList> list;

    @SuppressWarnings("unused")
    private Context context;

    private BitmapUtils bitUtils;

    public MRecyclerAdapter(List<TourPicList> list, Context context,OnRecyckerItemClickListener listener) {
        this.list = list;
        this.context = context;
        bitUtils = new BitmapUtils(context);
        if(listener != null) {
            this.listener = listener;
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.waterfallitem_vz, parent, false);
        MyHolder vh = new MyHolder(v);
        return vh;
 
    }

    @Override
    public void onBindViewHolder(MyHolder holder,final int position) {
        TourPicList plan = (TourPicList) list.get(position);
        bitUtils.display(holder.fill, plan.getThumbPhotoUrls().get(0));
        if (plan.getIsNewOrHot() == 0) {
            holder.heart.setImageResource(R.drawable.commercial_plan_heart_red);
        } else {
            holder.heart.setImageResource(R.drawable.commercial_plan_heart);
        }
        holder.number.setText(plan.getThumbPhotoUrls().size() + "张");
        holder.describe.setText(plan.getPlaceName());
        
        View v = holder.getItem();
        v.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onItemClick(v, position);
                }
            }
        });
       

    }
}
