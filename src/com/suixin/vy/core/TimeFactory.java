package com.suixin.vy.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class TimeFactory {
	
	public static String format(String time){
		long now = System.currentTimeMillis()/1000;
		long createTime=0;
		Date d = null;
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d = sdf.parse(time);
			createTime = d.getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if((now-createTime)/60<=0){
			time="1分钟内";
		}else if((now-createTime)/60<60){
			time=(now-createTime)/60+"分钟前";
		}else if((now-createTime)/3600<24){
			time=(now-createTime)/3600+"小时前";
		}else if((now-createTime)/86400<=31){
			time=(now-createTime)/86400+"天前";
		}else {
			time=time.split(" ")[0];
		}
		return time;
	}
}
