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
import com.suixin.vy.ui.R;
import com.suixin.vz.strike.model.StrikeModel;
import com.suixin.vz.strike.model.TourPicList;
import com.suixin.vz.ui.DetailsActivityTow;
import com.suixin.vz.ui.adapter.MRecyclerAdapter;
import com.suixin.vz.ui.adapter.MRecyclerAdapter.OnRecyckerItemClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class FindFragment extends Fragment implements OnRefreshListener,OnItemClickListener,OnRecyckerItemClickListener{
    /** RecyclerView */
    private RecyclerView rlv_find;

    private SwipeRefreshLayout swipe_refresh;

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
        addListener();
        getJson();
     // 第一次进页面先刷新一次
        swipe_refresh.post(new Runnable() {
            @Override
            public void run() {
                swipe_refresh.setRefreshing(true);
                getJson();
            }
        });
        return view;
    }

    /** 实例化发现中的控件 */
    private void initLv_find(LayoutInflater inflater, ViewGroup container) {
        rlv_find = (RecyclerView) view.findViewById(R.id.rlv_find);
        list = new ArrayList<TourPicList>();
        // 设置layoutManager 参数含义显而易见，2就是2列，第二个参数是垂直方向
        final StaggeredGridLayoutManager sg = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        sg.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rlv_find.setLayoutManager(sg);
        // 设置adapter
        adapter = new MRecyclerAdapter(list, activity,this);
        rlv_find.setAdapter(adapter);
        rlv_find.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                    int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                sg.invalidateSpanAssignments();
            }

        });

        // 设置item之间的间隔
        rlv_find.addItemDecoration(new DividerItemDecoration(activity,
                DividerItemDecoration.VERTICAL_LIST));
        rlv_find.addItemDecoration(new DividerItemDecoration(activity,
                DividerItemDecoration.HORIZONTAL_LIST));
        swipe_refresh = (SwipeRefreshLayout) view
                .findViewById(R.id.refresh);
        swipe_refresh.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipe_refresh.setSize(SwipeRefreshLayout.LARGE);
        swipe_refresh.setOnRefreshListener(this);
        
    }

    private void addListener() {
        swipe_refresh.setOnRefreshListener(this);
        
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
                        // 判断是否获取成功
                        if (Strike.getStatus() == 0) {
                            getHotListViewData();
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        swipe_refresh.setRefreshing(false);
                    }
                });
    }

    private void getHotListViewData() {
        List<TourPicList> tourPicList = Strike.getData().getTourPicList();
        list.clear();
        list.addAll(tourPicList);
        adapter.notifyDataSetChanged();
        if(swipe_refresh != null) {
            Log.e("ff", "setRefreshing");
            swipe_refresh.setRefreshing(false);
            swipe_refresh.invalidate();
        }
    }

    @Override
    public void onRefresh() {
        swipe_refresh.setRefreshing(true);
        getJson();  
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        // 每次都调用该方法 复用view
       /* Intent intent = new Intent();
        // 打开旅途详情页面
        EventBus.getDefault().postSticky(view, AppConfig.DetView);
        intent.setClass(activity, DetailsActivity.class);
        intent.putExtra(AppConfig.TITLE, "旅途详情");
        intent.putExtra("planGuid",
                ((TourPicList) rlv_find.getTag(position)).getGuid());
        activity.startActivity(intent);*/
    }

    @Override
    public void onItemClick(View item, int position) {
        Intent intent = new Intent();
        // 打开旅途详情页面
        EventBus.getDefault().postSticky(item, AppConfig.DetView);
        intent.setClass(activity, DetailsActivityTow.class);
        intent.putExtra(AppConfig.TITLE, "旅途详情");
        intent.putExtra("planGuid", list.get(position).getGuid());
        activity.startActivity(intent);
    }

}
