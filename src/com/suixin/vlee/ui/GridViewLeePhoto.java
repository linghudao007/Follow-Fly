package com.suixin.vlee.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suixin.vy.ui.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class GridViewLeePhoto extends Activity {
    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lee);
        gview = (GridView) findViewById(R.id.GridView1);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String [] from ={"image","text"};
        int [] to = {R.id.tv_usernameno5,R.id.action_settings};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.activity_main_lee, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);
    }

    
    
    public List<Map<String, Object>> getData(){        
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<20;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image",20);
            map.put("text", 20);
            data_list.add(map);
        }
            
        return data_list;
    }
    

}
