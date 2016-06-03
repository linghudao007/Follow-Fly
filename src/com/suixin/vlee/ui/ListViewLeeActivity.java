package com.suixin.vlee.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.suixin.vy.ui.R;

public class ListViewLeeActivity extends Activity {

	private ListView lvData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_lee);

		lvData = (ListView) findViewById(R.id.list_view);

		lvData.setAdapter(getAdapter());
	}

	private BaseAdapter getAdapter() {

		return new MyAdapter(this, getMyData());
	}

	private List<Message> getMyData() {

		List<Message> msgList = new ArrayList<Message>();
		Message msg;
		
		msg = new Message();
		msg.setType(MyAdapter.listvit_item_head_tow);
		msgList.add(msg);
		
		msg = new Message();
		msg.setType(MyAdapter.listvit_item_one);
		msgList.add(msg);

		msg = new Message();
		msg.setType(MyAdapter.listvit_item_photo);
		msgList.add(msg);

		msg = new Message();
		msg.setType(MyAdapter.listvit_item_foot);
		msgList.add(msg);

		return msgList;

	}

}
