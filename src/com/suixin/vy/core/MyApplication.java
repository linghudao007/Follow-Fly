package com.suixin.vy.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.suixin.vy.model.UserModel;
import com.suixin.vy.ui.HomeActivity;

import cn.bmob.v3.datatype.BmobDate;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;

public class MyApplication extends Application {
	public static String headPath;

	public boolean isLogin() {
		SharedPreferences sp = this.getSharedPreferences("AppConfig",
				Context.MODE_PRIVATE);
		return sp.getBoolean("isLogin", false);
	}

	public void setLogin(boolean isLogin) {
		SharedPreferences sp = this.getSharedPreferences("AppConfig",
				Context.MODE_PRIVATE);
		sp.edit().putBoolean("isLogin", isLogin).commit();
	}

	public void setUserInfo(Long name, String pass) {
		this.setUserInfo(name, pass, "男",
				BmobDate.createBmobDate("yyyy-MM-dd", "1993-1-1"),
				String.valueOf(name), new Integer(0), null, "", new Integer(0),
				new Integer(0));
	}

	public void removeUserInfo() {
		SharedPreferences sp = this.getSharedPreferences("AppConfig",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("name");
		editor.remove("pass");
		editor.remove("sex");
		editor.remove("birthday");
		editor.remove("nick");
		editor.remove("point");
		editor.remove("declaration");
		editor.remove("fans");
		editor.remove("attention");
		editor.commit();
	}

	public void setUserInfo(Long name, String pass, String sex,
			BmobDate birthday, String nick, Integer point, Bitmap head,
			String declaration, Integer attention, Integer fans) {
		SharedPreferences sp = this.getSharedPreferences("AppConfig",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putLong("name", name);
		editor.putString("pass", pass);
		if (sex != null) {
			editor.putString("sex", sex);
		}
		if (birthday != null) {
			editor.putString("birthday", birthday.getDate());
		}
		if (nick != null) {
			editor.putString("nick", nick);
		}
		if (point != null) {
			editor.putInt("point", point);
		}
		if (declaration != null) {
			editor.putString("declaration", declaration);
		}
		if (attention != null) {
			editor.putInt("attention", attention);
		}
		if (fans != null) {
			editor.putInt("fans", fans);
		}
		editor.commit();
	}

	public UserModel getUserInfo() {
		SharedPreferences sp = this.getSharedPreferences("AppConfig",
				Context.MODE_PRIVATE);
		UserModel user = new UserModel();
		user.setName(sp.getLong("name", new Long(0)));
		user.setPass(sp.getString("pass", ""));
		user.setSex(sp.getString("sex", ""));
		user.setBirthday(BmobDate.createBmobDate("yyyy-MM-dd",
				sp.getString("birthday", "")));
		user.setNick(sp.getString("nick", ""));
		user.setPoint(sp.getInt("point", new Integer(0)));
		user.setDeclaration(sp.getString("declaration", ""));
		user.setAttention(sp.getInt("attention", new Integer(0)));
		user.setFans(sp.getInt("fans", new Integer(0)));
		return user;
	}
}
