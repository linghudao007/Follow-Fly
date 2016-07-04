package com.suixin.vy.ui;

import java.util.ArrayList;
import java.util.List;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
@SuppressLint("SdCardPath")
public class DetailsActivity extends BaseActivity implements OnRefreshListener{
	private ImageView iv_back,iv_share;
	private TextView tv_title;
	/** 刷新布局 */
	private SwipeRefreshLayout reflayout;
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
		// 第一次进页面先刷新一次
		reflayout.post(new Runnable() {
			@Override
			public void run() {
				reflayout.setRefreshing(true);
				if (what == 1) {
					// 旅途
					getJson();
				} else if (what == 2) {
					// 约伴
					getJson_y();
				}
			}
		});
		
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
		    showShare();
			break;
		}
	}
	/**分享*/
	private void showShare() {
	    ShareSDK.initSDK(this);
	    OnekeyShare oks = new OnekeyShare();
	    //关闭sso授权
	    oks.disableSSOWhenAuthorize(); 

	    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
	    oks.setTitle(getString(R.string.app_name));
	    // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
	    oks.setTitleUrl("http://suixinfly.bmob.cn/");
	    // text是分享文本，所有平台都需要这个字段
	    oks.setText("随心旅行官方®");
	    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
	    //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
	    // url仅在微信（包括好友和朋友圈）中使用
	    oks.setUrl("http://suixinfly.bmob.cn/");
	    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
	    oks.setComment("随心旅行官方®");
	    // site是分享此内容的网站名称，仅在QQ空间使用
	    oks.setSite(getString(R.string.app_name));
	    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
	    oks.setSiteUrl("http://suixinfly.bmob.cn/");
	   // 启动分享GUI
	    oks.show(this);
	    }

	@Override
	protected void initView() {
		reflayout = (SwipeRefreshLayout) findViewById(R.id.swipe_ref);
		reflayout.setColorSchemeResources(R.color.refresh_1, R.color.refresh_2);
		reflayout.setSize(SwipeRefreshLayout.LARGE);
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
		reflayout.setOnRefreshListener(this);
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
						reflayout.setRefreshing(false);
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
						reflayout.setRefreshing(false);
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
						reflayout.setRefreshing(false);
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
						reflayout.setRefreshing(false);
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

	@Override
	public void onRefresh() {
		reflayout.setRefreshing(true);
		if (what == 1) {
			// 旅途
			getJson();
		} else if (what == 2) {
			// 约伴
			getJson_y();
		}
	}
}
