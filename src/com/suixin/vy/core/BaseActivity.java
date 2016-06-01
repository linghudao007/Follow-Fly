package com.suixin.vy.core;


import android.support.v4.app.FragmentActivity;
import android.view.View.OnClickListener;
public abstract class BaseActivity extends FragmentActivity implements OnClickListener{
	protected abstract void initView();
	protected abstract void addListener();
	protected abstract void getData();
}
