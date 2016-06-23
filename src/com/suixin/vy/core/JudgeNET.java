package com.suixin.vy.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * 检查当前网络是否可用
 * 
 * @param context
 * @return
 */
public class JudgeNET {
    
    public static boolean isNetable(Context context)
    {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
        {
        	Toast.makeText(context, "似乎已断开与互联网的连接", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            
            if (networkInfo != null && networkInfo.isAvailable())
            {
                return true;
            }
        }
        Toast.makeText(context, "似乎已断开与互联网的连接", Toast.LENGTH_SHORT).show();
        return false;
    }
}
