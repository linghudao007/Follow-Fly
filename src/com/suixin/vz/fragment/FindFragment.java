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
import com.suixin.vz.strike.model.StrikeModel;
import com.suixin.vz.strike.model.TourPicList;
import com.suixin.vz.ui.adapter.FindAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FindFragment extends Fragment {
    /** 获取来发现的显示数据 */
    private List<TourPicList> list;
    private List<TourPicList> list2;

    /** ListView */
    private ListView lv_vz_Strike1, lv_vz_Strike2;

    /** 获取来的发现总数据 */
    private StrikeModel Strike;

    private View view;

    private FindAdapter adapter;
    private FindAdapter adapters;

    private String tag = "FindFragment";

    private Activity activity;

    public void onAttach(android.app.Activity activity) {
        this.activity = activity;
        super.onAttach(activity);
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview_find_vz,
                container, false);
        this.view = view;
        list = new ArrayList<TourPicList>();
        list2 = new ArrayList<TourPicList>();
        Log.e("ff", "list");
        // 实例化发现中的控件
        initLv_find(inflater, container);
        getJson();
        Log.e("ff", "getJson");
        adapters = new FindAdapter(list, this.activity);
        lv_vz_Strike1.setAdapter(adapters);
        adapter = new FindAdapter(list2, this.activity);
        lv_vz_Strike2.setAdapter(adapter);
        Log.e("ff", "lv_vz_Strike");
        return view;
    }

    /** 实例化发现中的控件 */
    private void initLv_find(LayoutInflater inflater, ViewGroup container) {
        lv_vz_Strike1 = (ListView) view.findViewById(R.id.lv_find1);
        lv_vz_Strike2 = (ListView) view.findViewById(R.id.lv_find2);
    }

    // 解析发现
    private void getJson() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("OrderStr", "");
        Log.e("ff", "params");
        HttpUtils http = new HttpUtils();
        Log.e("ff", http + "");
        http.send(HttpRequest.HttpMethod.POST,
                "http://www.duckr.cn/api/v5/tourpic/sequare/list/", params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        Log.e("ff", "onStart");
                    }

                    @Override
                    public void onLoading(long total, long current,
                            boolean isUploading) {
                        if (isUploading) {
                        } else {
                        }
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Strike = JSON.parseObject(responseInfo.result,
                                StrikeModel.class);
                        Log.e("xx", "数据获取成功");
                        // 判断是否获取成功
                        if (Strike.getStatus() == 0) {
                            getHotListViewData();
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Log.e("xx", error.getExceptionCode() + ":" + msg);
                    }
                });
    }

    private void getHotListViewData() {
        List<TourPicList> tourPicList = Strike.getData().getTourPicList();
        int spit = (int) Math.ceil(tourPicList.size()/2);
        Log.i("getHotListViewData", spit+"");
        
        list.clear();
        list.addAll(tourPicList.subList(0, spit));
        
        list2.clear();
        list2.addAll(tourPicList.subList(spit, tourPicList.size()));
        adapter.notifyDataSetInvalidated();
        Log.e("ff", adapter + "notifyDa");
    }

}
