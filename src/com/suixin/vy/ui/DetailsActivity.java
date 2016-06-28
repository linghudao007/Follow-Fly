package com.suixin.vy.ui;

import java.util.ArrayList;
import java.util.List;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.suixin.vy.adapter.DetAdapter;
import com.suixin.vy.adapter.ThemeAdapter;
import com.suixin.vy.core.AppConfig;
import com.suixin.vy.core.BaseActivity;
import com.suixin.vy.core.JudgeNET;
import com.suixin.vy.model.details.CommentList;
import com.suixin.vy.model.details.DetModel;
import com.suixin.vy.model.details.y.DetModel_y;
import com.suixin.vy.model.theme.PlanList;
import com.suixin.vy.model.theme.ThemeModel;

/**
 * 详细信息展示 页面 intent需要三个参数 2.标题 3.请求参数planGuid 用EventBus传递进来View
 */
public class DetailsActivity extends BaseActivity {
	private ImageView iv_back,iv_share;
	private TextView tv_title;
	
	/** 评论listView */
	private ListView lv_comment;
	/** 约伴评论的接口地址 PSOT */
	private static final String URLSTR_Y = "http://www.duckr.cn/api/v3/plan/comment/get/";
	/** 约伴总数据 */
	private DetModel_y detModel_y;
	/** 旅途评论的接口地址 GET */
	private static final String URLSTR_L = "http://www.duckr.cn/api/v5/tourpic/detail/";
	/** 旅途总数据 */
	private DetModel detModel;
	/** 列表显示数据源 */
	private List<CommentList> commentList;
	/** 详细适配器 */
	private DetAdapter detAdapter;
	/** 网络请求 */
	private HttpUtils http;
	/** 记录用户选择的条件 */
	private String planGuid, orderStr;
	/** 判断是否请求评论结果（约伴 2，旅途 1,其他 0） */
	private int what = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		EventBus.getDefault().registerSticky(this);
		this.setContentView(R.layout.activity_details);
		initView();
		addListener();
		if (what == 1) {
			// 旅途
			getJson();
		} else if (what == 2) {
			// 约伴
			getJson_y();
		}
	}

	@Subscriber(tag = AppConfig.DetView, mode = ThreadMode.MAIN)
	private void getView(View view) {
		if (lv_comment.getHeaderViewsCount() < 1) {
			lv_comment.addHeaderView(view);
			lv_comment.setAdapter(detAdapter);
		}
		EventBus.getDefault().removeStickyEvent(view.getClass(),
				AppConfig.DetView);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			this.finish();
			break;
		case R.id.iv_share:
			share();
			break;
		}
	}
	/**分享*/
	private void share() {
		
	}

	@Override
	protected void initView() {
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_share=(ImageView)findViewById(R.id.iv_share);
		tv_title = (TextView) findViewById(R.id.tv_title);
		String tit = this.getIntent().getStringExtra(AppConfig.TITLE);
		planGuid = this.getIntent().getStringExtra("planGuid");
		if (tit != null) {
			tv_title.setText(tit);
			if (tit.equals("旅途详情")) {
				what = 1;
			} else if (tit.equals("约伴详情")) {
				what = 2;
			} else {
				what = 0;
			}
		}
		lv_comment = (ListView) findViewById(R.id.lv_content);
		http = new HttpUtils();
		commentList = new ArrayList<CommentList>();
		detAdapter = new DetAdapter(commentList, this);
		orderStr = "";
	}

	@Override
	protected void addListener() {
		iv_back.setOnClickListener(this);
		iv_share.setOnClickListener(this);
	}

	private void getJson_y() {
		if (!JudgeNET.isNetable(this)) {
			return;
		}
		RequestParams params = new RequestParams();
		// 这是cookie里的参数
		params.addQueryStringParameter("AppVer", "2.0");
		params.addQueryStringParameter("DeviceType", "1");
		// 这是post请求参数,OrderStr为上次请求到哪里，
		params.addBodyParameter("OrderStr", orderStr);
		params.addBodyParameter("PlanGuid", planGuid);
		http.send(HttpRequest.HttpMethod.POST, URLSTR_Y, params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						if (responseInfo == null) {
							return;
						}
						detModel_y = JSON.parseObject(responseInfo.result,
								DetModel_y.class);
						// 判断是否获取成功
						if (detModel_y.getStatus() == 0) {
							getData_y();
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
					}
				});
	}

	protected void getData_y() {
		commentList.clear();
		if (detModel_y.getData().getCommentList() != null) {
			commentList.addAll(detModel_y.getData().getCommentList());
			detAdapter.notifyDataSetChanged();
		}
	}

	private void getJson() {
		if (!JudgeNET.isNetable(this)) {
			return;
		}
		RequestParams params = new RequestParams();
		// 这是cookie里的参数
		params.addQueryStringParameter("AppVer", "2.0");
		params.addQueryStringParameter("DeviceType", "1");
		http.send(HttpRequest.HttpMethod.GET, URLSTR_L+planGuid, params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						if (responseInfo == null) {
							return;
						}
						detModel = JSON.parseObject(responseInfo.result,
								DetModel.class);
						// 判断是否获取成功
						if (detModel.getStatus() == 0) {
							getData();
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
					}
				});
	}

	@Override
	protected void getData() {
		commentList.clear();
		if (detModel.getData().getTourPic().getCommentList() != null) {
			commentList.addAll(detModel.getData().getTourPic().getCommentList());
			detAdapter.notifyDataSetChanged();
		}
	}
}
