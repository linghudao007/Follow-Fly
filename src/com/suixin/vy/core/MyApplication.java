package com.suixin.vy.core;

import android.app.Application;

public class MyApplication extends Application{
	private boolean isLogin;

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
