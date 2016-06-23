package com.suixin.vy.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.suixin.vy.adapter.PhotoAdapter;
import com.suixin.vy.core.AppConfig;
import com.suixin.vy.core.BaseActivity;
import com.suixin.vy.core.JudgeNET;
import com.suixin.vy.core.MyBitmapConfig;

/**
 * 照片大图显示页面
 * 需要intent 传进一个照片int 下标数（从0计算），和照片网址列表
 * @author yxy
 * 
 */
public class PhotoFillActivity extends BaseActivity {
	private TextView tv_count;
	private ViewPager vp_photoshow;
	private ImageView iv_back;
	/** 显示照片的数量 */
	private int photocount;
	/** 初始显示第几个页面 */
	private int photoindex;
	/** 照片的地址 */
	private List<String> photo_url;
	/** viewpager的每个页面 */
	private List<View> photoView;
	/** 适配器 */
	private PhotoAdapter pAdapter;
	private LayoutInflater inflater;
	private BitmapUtils bitUtils;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.setContentView(R.layout.activity_photofill);
		photo_url = new ArrayList<String>();
		photoView = new ArrayList<View>();
		bitUtils = new BitmapUtils(this);
		photo_url = this.getIntent().getStringArrayListExtra(AppConfig.PHOTO);
		photocount = photo_url.size();
		photoindex = this.getIntent().getIntExtra(AppConfig.PHOTOINDEX, 1);
		pAdapter = new PhotoAdapter(photoView);
		inflater = LayoutInflater.from(this);
		initView();
		getData();
		addListener();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			this.finish();
			break;
		}
	}

	@Override
	protected void initView() {
		tv_count = (TextView) findViewById(R.id.tv_count);
		tv_count.setText(photoindex + "/" + photocount);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		vp_photoshow = (ViewPager) findViewById(R.id.vp_photoshow);
		vp_photoshow.setAdapter(pAdapter);
	}

	@Override
	protected void addListener() {
		iv_back.setOnClickListener(this);
		vp_photoshow.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageSelected(int position) {
				tv_count.setText(position + 1 + "/" + photocount);
			}

		});
	}

	@Override
	protected void getData() {
		if(!JudgeNET.isNetable(this)){
			return;
		}
		for (int i = 0; i < photocount; i++) {
			View v = inflater.inflate(R.layout.viewpager_photo, null);
			ImageView iv = (ImageView)v.findViewById(R.id.iv_photofill);
			bitUtils.display(iv, photo_url.get(i),
					MyBitmapConfig.getConfig(this));
			photoView.add(v);
		}
		vp_photoshow.setCurrentItem(photoindex-1);
		pAdapter.notifyDataSetChanged();
		
	}

}
