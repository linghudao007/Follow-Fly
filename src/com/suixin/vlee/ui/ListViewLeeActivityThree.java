package com.suixin.vlee.ui;

import java.util.ArrayList; 
import java.util.List;

import com.suixin.vy.ui.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter; 
import android.widget.ListView;

public class ListViewLeeActivityThree extends Activity {

	private ListView lvData;
	private ListView listview_lee_item_foot ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_lee);

		
		lvData = (ListView) findViewById(R.id.list_view);
		lvData.setAdapter(getAdapter());
		
		listview_lee_item_foot.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(arg0.getId() == R.layout.listview_lee_item_foot){
					Intent intent = new Intent(ListViewLeeActivityThree.this,ListViewLeeActivityTow.class);
				startActivity(intent);
				}
				}
			});
		} 
 
	private BaseAdapter getAdapter() {

		return new MyAdapter(this, getMyData());
	}

	private List<Message> getMyData() {

		List<Message> msgList = new ArrayList<Message>();
		Message msg;
		msg = new Message();
		msg.setType(MyAdapter.listvit_item_head_one);
		msgList.add(msg);

		msg = new Message();
		msg.setType(MyAdapter.listvit_tow_lee);
		msgList.add(msg);

		msg = new Message();
		msg.setType(MyAdapter.listvit_item_foot);
		msgList.add(msg);

		return msgList;

	}

}