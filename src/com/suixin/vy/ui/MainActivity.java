package com.suixin.vy.ui;

import java.util.ArrayList;
import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.suixin.vy.adapter.GVBottomAdapter;
import com.suixin.vy.core.BaseActivity;
import com.suixin.vy.model.GVBottomModel;

public class MainActivity extends BaseActivity {
	private GridView gv_bottom;
	private List<GVBottomModel> list_gv;
	private GVBottomAdapter gvbAdapter;
	private Button btn_home;
	private boolean isOpneBtn_home;
	private ImageView iv_invite,iv_showtrip,iv_video;
	private TextView tv_invite,tv_showtrip,tv_video;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		// 初始化控件
		initView();
		// 生成数据源
		getData();
		// 设置适配器
		setAdapter();
		// 绑定监听
		addListener();
		// 初始化状态
		isOpneBtn_home = false;
	}

	private void setAdapter() {
		gvbAdapter = new GVBottomAdapter(this, list_gv, gv_bottom);
		gv_bottom.setAdapter(gvbAdapter);
		gv_bottom.setItemChecked(0, true);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_home:
			// 判断加号按钮是否打开
			if (this.isOpneBtn_home) {
				closeAdd();
				this.isOpneBtn_home = false;
			} else {
				opneAdd();
				this.isOpneBtn_home = true;
			}
			break;
		case R.id.v_addshow:
			closeAdd();
			this.isOpneBtn_home = false;
			break;
		default:
			break;
		}
	}

	/** 打开加号按钮 */
	private void opneAdd() {
		//设置透明度
		Animation anim_btn = AnimationUtils.loadAnimation(this,
				R.anim.btn_home_rotate);
		View v_addshow = findViewById(R.id.v_addshow);
		v_addshow.setVisibility(View.VISIBLE);
		v_addshow.setOnClickListener(this);
		btn_home.startAnimation(anim_btn);
		//显示3个按钮
		findViewById(R.id.ll_addshow).setVisibility(View.VISIBLE);
		PropertyValuesHolder holder_x =PropertyValuesHolder.ofFloat("scaleX",0.1f,1.1f);
		PropertyValuesHolder holder_y =PropertyValuesHolder.ofFloat("scaleY",0.1f,1.1f);
		PropertyValuesHolder holder_t =PropertyValuesHolder.ofFloat("translationY",-300);
		
//		ObjectAnimator anim_1 = ObjectAnimator.ofPropertyValuesHolder(iv_invite, holder_x,holder_y).setDuration(100);
//		ObjectAnimator anim_2 = ObjectAnimator.ofPropertyValuesHolder(iv_showtrip,holder_x,holder_y).setDuration(100);
//		ObjectAnimator anim_3 = ObjectAnimator.ofPropertyValuesHolder(iv_video,holder_x,holder_y).setDuration(100);
//		ObjectAnimator anim_4 = ObjectAnimator.ofPropertyValuesHolder(tv_invite,holder_x,holder_y).setDuration(100);
//		ObjectAnimator anim_5 = ObjectAnimator.ofPropertyValuesHolder(tv_showtrip,holder_x,holder_y).setDuration(100);
//		ObjectAnimator anim_6 = ObjectAnimator.ofPropertyValuesHolder(tv_video,holder_x,holder_y).setDuration(100);
		ObjectAnimator anim_a1 = ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.rl_invite), holder_x,holder_y).setDuration(100);
 		ObjectAnimator anim_a2 = ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.rl_showtrip),holder_x,holder_y).setDuration(100);
		ObjectAnimator anim_a3 = ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.rl_video),holder_x,holder_y).setDuration(100);
		ObjectAnimator anim_s =ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.ll_addshow), holder_t).setDuration(100);
		PropertyValuesHolder holder_xx=PropertyValuesHolder.ofFloat("scaleX",1.1f,1);
		PropertyValuesHolder holder_yy =PropertyValuesHolder.ofFloat("scaleY",1.1f,1);
//		ObjectAnimator anim_11 = ObjectAnimator.ofPropertyValuesHolder(iv_invite,  holder_xx,holder_yy).setDuration(50);
//		ObjectAnimator anim_12 = ObjectAnimator.ofPropertyValuesHolder(iv_showtrip, holder_xx,holder_yy).setDuration(50);
//		ObjectAnimator anim_13 = ObjectAnimator.ofPropertyValuesHolder(iv_video,holder_xx,holder_yy).setDuration(50);
//		ObjectAnimator anim_14 = ObjectAnimator.ofPropertyValuesHolder(tv_invite,holder_xx,holder_yy).setDuration(50);
//		ObjectAnimator anim_15 = ObjectAnimator.ofPropertyValuesHolder(tv_showtrip,holder_xx,holder_yy).setDuration(50);
//		ObjectAnimator anim_16 = ObjectAnimator.ofPropertyValuesHolder(tv_video,holder_xx,holder_yy).setDuration(50);
		
		ObjectAnimator anim_a11 = ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.rl_invite),holder_xx,holder_yy).setDuration(50);
		ObjectAnimator anim_a12 = ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.rl_showtrip),holder_xx,holder_yy).setDuration(50);
		ObjectAnimator anim_a13 = ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.rl_video),holder_xx,holder_yy).setDuration(50);
		
		AnimatorSet set =new AnimatorSet();
		//set.playTogether(anim_1,anim_2,anim_3,anim_4,anim_5,anim_6,anim_s);
		//set.play(Anim_1).before(Anim_11);
		//set.playTogether(anim_11,anim_12,anim_13,anim_14,anim_15,anim_16);
		
		set.playTogether(anim_a1,anim_a2,anim_a3);
		set.play(anim_a1).before(anim_a11);
		set.playTogether(anim_a1,anim_a2,anim_a3);
		set.start();
	}

	/** 关闭加号按钮 */
	private void closeAdd() {
		//设置透明度
		Animation anim_btn = AnimationUtils.loadAnimation(this,
				R.anim.btn_home_rotatere);
		findViewById(R.id.v_addshow).setVisibility(View.GONE);
		btn_home.startAnimation(anim_btn);
		//显示3个按钮
		findViewById(R.id.ll_addshow).setVisibility(View.GONE);
	}

	@Override
	protected void initView() {
		gv_bottom = (GridView) findViewById(R.id.gv_bottom);
		gv_bottom.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
		btn_home = (Button) findViewById(R.id.btn_home);
		iv_invite=(ImageView)findViewById(R.id.iv_invite);
		iv_showtrip=(ImageView)findViewById(R.id.iv_showtrip);
		iv_video=(ImageView)findViewById(R.id.iv_video);
		tv_invite=(TextView)findViewById(R.id.tv_invite);
		tv_showtrip=(TextView)findViewById(R.id.tv_showtrip);
		tv_video=(TextView)findViewById(R.id.tv_video);
	}

	@Override
	protected void addListener() {
		gv_bottom.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				gvbAdapter.notifyDataSetChanged();
			}
		});
		btn_home.setOnClickListener(this);
	}

	@Override
	protected void getData() {
		list_gv = new ArrayList<GVBottomModel>();
		list_gv.add(new GVBottomModel(R.drawable.tab_home_focused,
				R.drawable.tab_home_normal, "首页", false, false));
		list_gv.add(new GVBottomModel(R.drawable.tab_tourpic_focused,
				R.drawable.tab_tourpic_normal, "旅途", false, false));
		list_gv.add(new GVBottomModel(0, 0, "", true, false));
		list_gv.add(new GVBottomModel(R.drawable.tab_chat_focused,
				R.drawable.tab_chat_normal, "聊天", false, false));
		list_gv.add(new GVBottomModel(R.drawable.tab_me_focused,
				R.drawable.tab_me_normal, "我", false, false));
	}

}
