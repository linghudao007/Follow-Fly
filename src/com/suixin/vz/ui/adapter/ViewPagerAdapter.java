package com.suixin.vz.ui.adapter;
import java.util.List;

import android.content.Context;
// ViewPager适配器
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> mData;
    public ViewPagerAdapter(Context context,List<View> data){
        this.mData = data;
    }
    //要显示图片的数量
    @Override
    public int getCount() {
        return mData!=null?mData.size():0;
    }
    //对显示图片进行绑定
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0==arg1;
    }
    //销毁View
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //将View从ViewGroup中移除
        container.removeView(mData.get(position));
    }
    //添加View
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //将View添加到ViewGroup
        container.addView(mData.get(position));
        return mData.get(position);
    }

}
