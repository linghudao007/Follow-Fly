package com.suixin.vq.ui;

import java.util.ArrayList;
import java.util.List;

import com.suixin.vy.core.BaseActivity;
import com.suixin.vy.core.MyApplication;
import com.suixin.vy.model.UserModel;
import com.suixin.vy.ui.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UserFragment extends Fragment implements OnClickListener {
	private ImageButton ib_set, ib_info;
	private Activity activity;
	private View view;
	private UserModel userinfo;

	private TextView name, attention, point, fans;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_vqmain, container, false);
		this.view = view;
		initView();
		addListener();
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
	}
	

	@Override
	public void onResume() {
		super.onResume();
		MyApplication myapp = ((MyApplication) activity.getApplicationContext());
		userinfo = myapp.getUserInfo();
		name.setText(userinfo.getName() + "");
		attention.setText("关注 "+userinfo.getAttention());
		point.setText("积分 "+userinfo.getPoint() + "");
		fans.setText("粉丝 "+userinfo.getFans() + "");
	}

	private void addListener() {
		ib_set.setOnClickListener(this);
		ib_info.setOnClickListener(this);
	}

	private void initView() {
		ib_set = (ImageButton) view.findViewById(R.id.ib_set);
		ib_info = (ImageButton) view.findViewById(R.id.ib_info);
		name = (TextView) view.findViewById(R.id.tv_name);
		attention = (TextView) view.findViewById(R.id.tv_attention);
		point = (TextView) view.findViewById(R.id.tv_point);
		fans = (TextView) view.findViewById(R.id.tv_fans);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_set:
			Intent intent = new Intent(activity, SetActivity.class);
			activity.startActivityForResult(intent, 100);
			break;
		case R.id.ib_info:
			Intent intent_2 = new Intent(activity, UserInfoSetActivity.class);
			startActivity(intent_2);
			break;
		}

	}
	
}