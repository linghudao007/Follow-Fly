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
import com.suixin.vy.ui.R;
import com.suixin.vz.model.HotModel;
import com.suixin.vz.ui.adapter.HotListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HotFragment extends Fragment {

    /** 获取来显示的数据 */
    private List<com.suixin.vz.model.TourPicList> list;
    /** ListView */
    private ListView lv_vz_home;
    /** 获取来的热门总数据 */
    private HotModel hot;
    
    private View view;
    private HotListAdapter adapter;
    private String tag = "HotFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_listview_vz,
                container, false);
        this.view=view;
        list = new ArrayList<com.suixin.vz.model.TourPicList>();
        // 实例化热门中的控件
        initLv_hot(inflater, container);
        getJson();
        adapter = new HotListAdapter(list, this.getActivity());
        lv_vz_home.setAdapter(adapter);
        return view;
    }
    
    /** 实例化热门中的控件 */
    private void initLv_hot(LayoutInflater inflater, ViewGroup container) {
        lv_vz_home = (ListView) view.findViewById(R.id.lv_vz_home);
    }
    // 解析热门
    private void getJson() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("OrderStr", "");
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                "http://www.duckr.cn/api/v5/tourpic/popular/list/", params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                    }
                    @Override
                    public void onLoading(long total, long current,
                            boolean isUploading) {
                        if (isUploading) {
                            // testTextView.setText("upload: " + current + "/" +
                            // total);
                        } else {
                            // testTextView.setText("reply: " + current + "/" +
                            // total);
                        }
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        hot = JSON.parseObject(responseInfo.result,
                                HotModel.class);
                        // 判断是否获取成功
                        if (hot.getStatus() == 0 ) {
                            getHotListViewData();
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }
    private void getHotListViewData() {
        List<com.suixin.vz.model.TourPicList> tourPicList = hot.getData().getTourPicList();
        list.clear();
        list.addAll(tourPicList);
        adapter.notifyDataSetInvalidated();
    }
}
