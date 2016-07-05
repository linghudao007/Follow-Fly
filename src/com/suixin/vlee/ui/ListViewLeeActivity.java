package com.suixin.vlee.ui;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.suixin.vy.ui.R;

public class ListViewLeeActivity extends Activity {

	private ListView lvData;
	ViewHolder holder;

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
		msg.setType(MyAdapter.listvit_item_release);
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
				finish();
			}
		});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick1(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}
				});

		builder.create().show();

	}
}
