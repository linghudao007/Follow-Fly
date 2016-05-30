package com.suixin.vy.core;

import android.app.Activity;
import android.view.View.OnClickListener;
public abstract class BaseActivity extends Activity implements OnClickListener{
	protected abstract void initView();
	protected abstract void addListener();
	protected abstract void getData();
}
