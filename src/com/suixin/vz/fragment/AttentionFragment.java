package com.suixin.vz.fragment;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.attention.molde.AttentionMolde;
import com.suixin.attention.molde.TourPicList;
import com.suixin.vy.ui.R;
import com.suixin.vz.ui.adapter.AttentionListAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class AttentionFragment extends Fragment {

    /** 获取来显示的数据 */
    private List<TourPicList> list;
    /** ListView */
    private ListView lv_vz_att_home;
    /** 获取来的关注总数据 */
    private AttentionMolde attention;
    
    private View view;
    private AttentionListAdapter adapter;
    private String tag = "attentionFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attention_vz,
                container, false);
        this.view=view;
        list = new ArrayList<TourPicList>();
        Log.e("aa", "list");
        // 实例化关注中的控件
        initLv_attention(inflater, container);
        getJson();
        Log.e("aa", "getJson");
        adapter = new AttentionListAdapter(list, this.getActivity());
        lv_vz_att_home.setAdapter(adapter);
        Log.e("aa", "lv_vz_home");
        return view;
    }
    
    /** 实例化热门中的控件 */
    private void initLv_attention(LayoutInflater inflater, ViewGroup container) {
        lv_vz_att_home = (ListView) view.findViewById(R.id.lv_vz_att_home);
    }
    // 解析热门
    private void getJson() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("OrderStr", "");
        Log.e("aa", "params");
        HttpUtils http = new HttpUtils();
        Log.e("aa", http + "");
        http.send(HttpRequest.HttpMethod.POST,
                "http://www.duckr.cn/api/v5/tourpic/concern/list/", params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        Log.e("aa", "onStart");
                    }
                    @Override
                    public void onLoading(long total, long current,
                            boolean isUploading) {
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.e("aa", "数据获取成功");
                        attention = JSON.parseObject(responseInfo.result,AttentionMolde.class);
                        // 判断是否获取成功
                       if (attention.getStatus() == 0 ) {
                            getAttentionListViewData();
                        }
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Log.e("aa", error.getExceptionCode() + ":" + msg);
                    }
                });
    }
    private void getAttentionListViewData() {
        List<TourPicList> tourPicList = attention.getData().get(1).getTourPicList();
        list.clear();
        list.addAll(tourPicList);
        adapter.notifyDataSetInvalidated();
        Log.e("aa",adapter+"attentionifyDa");
    }
}
