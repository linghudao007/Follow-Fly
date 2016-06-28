package com.suixin.vlee.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.suixin.vy.ui.R;

public class ListViewLeeActivity extends Activity {

	private ListView lvData;
	private Button btn_retreat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_lee);

		lvData = (ListView) findViewById(R.id.list_view);
		
//		btn_retreat = (Button) findViewById(R.id.btn_retreat);
//
//		btn_retreat.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				finish();
//
//			}
//		});

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

	public void showAlertDialog(View view) {

		com.custom.lee.CustomDialog.Builder builder = new com.custom.lee.CustomDialog.Builder(
				this);
		builder.setMessage("确定要退出编辑吗");
		builder.setTitle("提示");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent(ListViewLeeActivity.this,
						MainActivity_Lee.class);
				startActivity(intent);
			}
		});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.create().show();

	}
}
