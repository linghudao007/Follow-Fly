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
import com.suixin.vz.ui.adapter.MRecyclerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FindFragment extends Fragment {
    /** ListView */
    private RecyclerView rlv_find;

    /** 获取来发现的显示数据 */
    private List<TourPicList> list;

    private MRecyclerAdapter adapter;

    /** 获取来的发现总数据 */
    private StrikeModel Strike;

    private View view;

    private Activity activity;

    private String tag = "FindFragment";

    public void onAttach(android.app.Activity activity) {
        this.activity = activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview_find_vz,
                container, false);
        this.view = view;
        // 实例化发现中的控件
        initLv_find(inflater, container);
        getJson();
        return view;
    }

    /** 实例化发现中的控件 */
    private void initLv_find(LayoutInflater inflater, ViewGroup container) {
        rlv_find = (RecyclerView) view.findViewById(R.id.rlv_find);
        list = new ArrayList<TourPicList>();
        // 设置adapter
        adapter = new MRecyclerAdapter(list, activity);
        rlv_find.setAdapter(adapter);
        // 设置layoutManager 参数含义显而易见，2就是2列，第二个参数是垂直方向
        rlv_find.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        // 设置item之间的间隔
    }

    // 解析发现
    private void getJson() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("OrderStr", "");
        HttpUtils http = new HttpUtils();
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
        list.clear();
        list.addAll(tourPicList);
        adapter.notifyDataSetChanged();
        Log.e("ff", adapter + "notifyDa");
    }
}
