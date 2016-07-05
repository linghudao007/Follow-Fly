package com.suixin.vz.fragment;

import java.util.ArrayList;
import java.util.List;

import org.simple.eventbus.EventBus;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.vy.core.AppConfig;
import com.suixin.vy.model.TourPicList;
import com.suixin.vy.ui.DetailsActivity;
import com.suixin.vy.ui.R;
import com.suixin.vz.model.HotModel;
import com.suixin.vz.ui.adapter.HotListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class HotFragment extends Fragment
        implements OnRefreshListener, OnItemClickListener {

    /** 获取来显示的数据 */
    private List<com.suixin.vz.model.TourPicList> list;

    /** 刷新布局 */
    private SwipeRefreshLayout reflayout;

    /** ListView */
    private ListView lv_vz_home;

    /** 获取来的热门总数据 */
    private HotModel hot;

    private View view;

    private HotListAdapter adapter;

    private String tag = "HotFragment";

    private Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_listview_vz,
                container, false);
        this.view = view;
        list = new ArrayList<com.suixin.vz.model.TourPicList>();
        // 实例化热门中的控件
        initLv_hot(inflater, container);
        addListener();
        getJson();
        adapter = new HotListAdapter(list, this.getActivity());
        lv_vz_home.setAdapter(adapter);
        // 第一次进页面先刷新一次
        reflayout.post(new Runnable() {
            @Override
            public void run() {
                reflayout.setRefreshing(true);
                getJson();
            }
        });
        return view;
    }

    private void addListener() {
        reflayout.setOnRefreshListener(this);
        lv_vz_home.setOnItemClickListener(this);
    }

    /** 实例化热门中的控件 */
    private void initLv_hot(LayoutInflater inflater, ViewGroup container) {
        lv_vz_home = (ListView) view.findViewById(R.id.lv_vz_home);
        reflayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_ref);
        reflayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        reflayout.setSize(SwipeRefreshLayout.LARGE);
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
                        reflayout.setRefreshing(false);
                        hot = JSON.parseObject(responseInfo.result,
                                HotModel.class);
                        // 判断是否获取成功
                        if (hot.getStatus() == 0) {
                            getHotListViewData();
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        reflayout.setRefreshing(false);
                    }
                });
    }

    private void getHotListViewData() {
        List<com.suixin.vz.model.TourPicList> tourPicList = hot.getData()
                .getTourPicList();
        list.clear();
        list.addAll(tourPicList);
        adapter.notifyDataSetInvalidated();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        Intent intent = new Intent();
        // 打开旅途详情页面
        EventBus.getDefault().postSticky(view, AppConfig.DetView);
        intent.setClass(activity, DetailsActivity.class);
        intent.putExtra(AppConfig.TITLE, "旅途详情");
        intent.putExtra("planGuid",
                ((com.suixin.vz.model.TourPicList) list.get(position))
                        .getGuid());
        activity.startActivity(intent);
    }

    @Override
    public void onRefresh() {
        reflayout.setRefreshing(true);
        getJson();
    }
}
