package com.suixin.vy.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MyApplication extends Application{
    public static String headPath; 
	
	public boolean isLogin() {
		SharedPreferences sp = this.getSharedPreferences("AppConfig",Context.MODE_PRIVATE);
		return sp.getBoolean("isLogin", false);
	}

	public void setLogin(boolean isLogin) {
		SharedPreferences sp = this.getSharedPreferences("AppConfig",Context.MODE_PRIVATE);
		sp.edit().putBoolean("isLogin",isLogin).commit();
	}
}
