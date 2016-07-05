package com.suixin.vlee.ui;

import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.suixin.vy.ui.R;

public class MyAdapter extends BaseAdapter implements OnClickListener{

	public static final String KEY = "key";
	public static final String VALUE = "value";
	public static final int listvit_item_one = 0;
	public static final int listvit_item_tow = 1;
	public static final int listvit_item_photo = 2;
	public static final int listvit_item_sex = 3;
	public static final int listvit_item_foot = 4;
	public static final int listvit_item_head_tow = 5;
	public static final int listvit_item_head_one = 6;
	public static final int listvit_tow_lee = 7;
	public static final int listvit_item_release= 8;
	private LayoutInflater mInflater;
	private Context context;              

	private List<Message> myList;
	
	


	public MyAdapter(Context context, List<Message> myList
			) {
       this.context = context;
		this.myList = myList;

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
						R.layout.activity_main_lee, null);
			
				break;

			case listvit_item_sex:
				convertView = mInflater.inflate(R.layout.listview_lee_item_sex,
						null);
				break;
			case listvit_item_foot:
				convertView = mInflater.inflate(
						R.layout.listview_lee_item_foot, null);
				Button btn = (Button)convertView.findViewById(R.id.but_next);
				btn.setOnClickListener(this);
				Log.e("sss", "foot");
				break;
				
			case listvit_item_head_tow:

				convertView = mInflater.inflate(
						R.layout.listview_item_head_tow, null);
				break;
			case listvit_item_head_one:

				convertView = mInflater.inflate(
						R.layout.listview_item_head_one, null);
				break;
			case listvit_tow_lee:

				convertView = mInflater.inflate(
						R.layout.listview_tow_lee, null);
				break;
			case listvit_item_release:

				convertView = mInflater.inflate(
						R.layout.item_release_lee, null);
				Button btn1 = (Button)convertView.findViewById(R.id.but_release);
				btn1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Toast.makeText(context,"发布成功",1000 ).show();
					}
				});
				break;
				
				default:
					break;
			}
		
		return convertView;
	}

	

	@Override
	public int getItemViewType(int position) {

		Message msg = myList.get(position);
		int type = msg.getType();
		return type;
	}

	@Override
	public void onClick(View v) {
		 Intent intent = new Intent(context,ListViewLeeActivityTow.class);
		 context.startActivity(intent);
	}

 
    
   
    
    

	
	
	
	
	
	
	
	
	
}
