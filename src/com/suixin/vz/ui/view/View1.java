package com.suixin.vz.ui.view;

import com.suixin.vy.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class View1 {

    public View v;
    private LayoutInflater inflater;
    public View1(Context context){
        inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.activity_view1_vz, null);
    }
}
