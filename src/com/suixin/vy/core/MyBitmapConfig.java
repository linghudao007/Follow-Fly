package com.suixin.vy.core;

import android.content.Context;
import android.graphics.Bitmap;

import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
/**这是一个xUtils里的BitmapDisplayConfig实例化加配置小工具*/
public class MyBitmapConfig {
	private static BitmapDisplayConfig bigPicDisplayConfig=new BitmapDisplayConfig();
	public static BitmapDisplayConfig getConfig(Context context){
		bigPicDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
		bigPicDisplayConfig.setBitmapMaxSize(BitmapCommonUtils
			.getScreenSize(context));
		return bigPicDisplayConfig;
	}
}
