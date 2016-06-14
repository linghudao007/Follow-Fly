package com.suixin.vy.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suixin.vy.model.GVBottomModel;
import com.suixin.vy.ui.R;
/**
 * 主页活动窗口下部导航栏的适配器
 * @author Administrator
 *
 */
public class GVBottomAdapter extends BaseAdapter {
	private List<GVBottomModel> list_gv;
	private LayoutInflater inflater;
	private GridView gv_bottom;

	public GVBottomAdapter(Context context, List<GVBottomModel> list_gv,GridView gv_bottom) {
		super();
		this.list_gv = list_gv;
		this.gv_bottom=gv_bottom;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list_gv != null ? list_gv.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return list_gv != null ? list_gv.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GVBottomModel gvbModel = list_gv.get(position);
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.gridview_gv_bottom_item,
					null);
			holder = new ViewHolder();
			holder.iv_tab = (ImageView) convertView.findViewById(R.id.iv_tab);
			holder.iv_msg = (ImageView) convertView.findViewById(R.id.iv_msg);
			holder.tv_tab = (TextView) convertView.findViewById(R.id.tv_tab);
			holder.rl_tab=(RelativeLayout)convertView.findViewById(R.id.rl_tab);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (gvbModel.isFlag()) {
			holder.iv_tab.setVisibility(View.GONE);
			holder.tv_tab.setVisibility(View.GONE);
			holder.iv_msg.setVisibility(View.GONE);	
			holder.rl_tab.setVisibility(View.GONE);
		} else {
			holder.tv_tab.setText(gvbModel.getTv_tab());
			holder.rl_tab.setVisibility(View.VISIBLE);
			holder.iv_tab.setVisibility(View.VISIBLE);
			holder.tv_tab.setVisibility(View.VISIBLE);
			if(gvbModel.isMsg_flag()){
				holder.iv_msg.setVisibility(View.VISIBLE);	
			}else{
				holder.iv_msg.setVisibility(View.GONE);
			}
			if(gv_bottom.isItemChecked(position)){
				holder.iv_tab.setImageResource(gvbModel.getIv_tab());
				holder.tv_tab.setTextColor(Color.BLACK);
			}else{
				holder.iv_tab.setImageResource(gvbModel.getIv_tab_normal());
				holder.tv_tab.setTextColor(Color.parseColor("#C2BBAE"));
			}
		}
		return convertView;
	}

	class ViewHolder {
		ImageView iv_tab, iv_msg;
		TextView tv_tab;
		RelativeLayout rl_tab;
	}

}
