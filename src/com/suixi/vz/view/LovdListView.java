package com.suixi.vz.view;

import com.suixin.vy.ui.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class LovdListView extends ListView {

    View footer;//底部布局
    public LovdListView(Context context) {
        super(context);
        initView(context);
    }

    public LovdListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LovdListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }
    // 添加底部布局到listview
    public void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.footer_layout_vz, null);
        this.addFooterView(footer);
    }
    
}
