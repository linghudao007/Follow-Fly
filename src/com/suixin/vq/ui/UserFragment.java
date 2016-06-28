package com.suixin.vq.ui;

import java.util.ArrayList;
import java.util.List;

import com.suixin.vy.core.BaseActivity;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UserFragment extends Fragment {
	private ImageButton bt,bt2;
	private Activity activity;
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_vqmain, container,false);
		bt = (ImageButton) view.findViewById(R.id.btn_lz);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, SecondActivity.class);
				startActivity(intent);
			}
		});
		bt2 = (ImageButton)view. findViewById(R.id.btn_1);
		bt2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, CuttondActivity.class);
				startActivity(intent);
			}

		});
		
		return view;
	}
}