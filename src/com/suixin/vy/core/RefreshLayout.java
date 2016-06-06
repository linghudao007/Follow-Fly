package com.suixin.vy.core;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.suixin.vy.ui.R;

public class RefreshLayout extends LinearLayout implements OnTouchListener {
	/** 下拉状态 */
	public static final int STATUS_PULL_TO_REFRESH = 0;
	/** 释放立即刷新状态 */
	public static final int STATUS_RELEASE_TO_REFRESH = 1;
	/** 正在刷新状态 */
	public static final int STATUS_REFRESHING = 2;
	/** 非刷新状态 */
	public static final int STATUS_NOREFRESH = 3;

	/** 下拉回滚速度 */
	public static final int SPEED = -20;
	/** 下拉刷新的回调接口 */
	private PullToRefreshListener pullListener;

	/** 下拉头部view */
	private View headView;

	/** 需要刷新的ListView */
	private ListView lv;
	/** 刷新时动画图片 */
	private ImageView iv_progress;
	/** 刷新箭头 */
	private ImageView iv_arrow;
	/** 提示下拉和释放的文字 */
	private TextView tv_refreshstate;
	/** 下拉头部的布局参数 */
	private MarginLayoutParams headParams;
	/** 下拉头的高度 */
	private int hideHeadHeight;

	/** 当前下拉状态 */
	private int currentStatus = STATUS_NOREFRESH;
	/** 上次下拉状态 */
	private int lastStatus = currentStatus;
	/** 手指按下的屏幕Y轴坐标 */
	private float yDown;
	/** 手指可以移动的距离 */
	private int touchY;
	/** layout是否已加载过一次 */
	private boolean isloaded;
	/** 是否可以刷新 */
	private boolean ablePull;

	/** 构造方法 */
	public RefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 加载头部的布局
		this.headView = LayoutInflater.from(context).inflate(
				R.layout.view_fullrefresh, null, true);
		// 通过头部布局实例化 显示动画的图片，箭头图片，和刷新文字
		this.iv_progress = (ImageView) headView.findViewById(R.id.iv_progress);
		this.iv_arrow = (ImageView) headView.findViewById(R.id.iv_arrow);
		this.tv_refreshstate = (TextView) headView
				.findViewById(R.id.tv_refreshstate);
		// 获取手指可移动的最大距离
		this.touchY = ViewConfiguration.get(context).getScaledTouchSlop();
		// 设置此线性布局为垂直方向
		setOrientation(this.VERTICAL);

		addView(headView, 0);

	}

	/** 进行一些关键性初始化操作，比如：将下拉箭头向上进行偏移隐藏，给listview注册触摸事件 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		super.onLayout(changed, l, t, r, b);
		if (changed && !this.isloaded) {
			this.hideHeadHeight = -this.headView.getHeight();
			this.headParams = (MarginLayoutParams) this.headView
					.getLayoutParams();
			this.headParams.topMargin = this.hideHeadHeight;

			this.lv = (ListView) this.getChildAt(1);
			this.lv.setOnTouchListener(this);
			this.isloaded = true;
		}
	}

	/** 当ListView被触摸时调用，其中处理列各种下拉刷新的具体逻辑 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		setIsAbleToPull(event);
		// 判断是否可以刷新
		if (this.ablePull) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// 获取手按下的起点
				this.yDown = event.getRawY();
				break;
			case MotionEvent.ACTION_MOVE:
				// 计算手移动了多少距离
				int yMove = (int) (event.getRawY() - this.yDown);
				// 如果手指是下滑状态，并且下拉头是完全隐藏的，就屏蔽下拉事件
				if (yMove <= 0
						&& this.headParams.topMargin <= this.hideHeadHeight) {
					return false;
				}
				if (yMove < this.touchY) {
					return false;
				}
				if (this.currentStatus != this.STATUS_REFRESHING) {
					if (this.headParams.topMargin > 0) {
						this.currentStatus = this.STATUS_RELEASE_TO_REFRESH;
					} else {
						this.currentStatus = this.STATUS_PULL_TO_REFRESH;
					}
					// 通过偏移下拉头的TopMargin,来实现下拉效果
					this.headParams.topMargin = (yMove / 2)
							+ this.hideHeadHeight;
					this.headView.setLayoutParams(headParams);
				}
				break;
			case MotionEvent.ACTION_UP:
			default:
				if (this.currentStatus == this.STATUS_RELEASE_TO_REFRESH) {
					new RefreshingTask().execute();
				} else if (this.currentStatus == this.STATUS_PULL_TO_REFRESH) {
					new HideHeadTask().execute();
				}
				break;
			}
			// 时刻记更新下拉头中的信息
			if (this.currentStatus == this.STATUS_PULL_TO_REFRESH
					|| this.currentStatus == this.STATUS_RELEASE_TO_REFRESH) {
				updateHeadView();
				// 当前正处于下拉或释放状态，要让ListView失去焦点，否则被点击的那一项会一直处于选中状态
				this.lv.setPressed(false);
				this.lv.setFocusable(false);
				this.lv.setFocusableInTouchMode(false);
				this.lastStatus = this.currentStatus;
				// 当前正处于下拉或释放状态，通过返回true屏蔽掉listview的滚动事件
				return true;
			}
		}
		return false;
	}

	/** 设置是否可以滚动 */
	private void setIsAbleToPull(MotionEvent event) {
		View firstChild = lv.getChildAt(0);
		if (firstChild != null) {
			int firstVisiblePos = lv.getFirstVisiblePosition();
			if (firstVisiblePos == 0 && firstChild.getTop() == 0) {
				if (!ablePull) {
					yDown = event.getRawY();
				}
				// 如果首个元素的上边缘，距离父布局值为0，就说明ListView滚动到了最顶部，此时应该允许下拉刷新
				ablePull = true;
			} else {
				if (headParams.topMargin != hideHeadHeight) {
					headParams.topMargin = hideHeadHeight;
					headView.setLayoutParams(headParams);
				}
				ablePull = false;
			}
		} else {
			// 如果ListView中没有元素，也应该允许下拉刷新
			ablePull = true;
		}
	}

	
	public void setOnRefreshListener(PullToRefreshListener listener, int id) {
		pullListener = listener;
	}

	/**
	 * 当所有的刷新逻辑完成后，记录调用一下，否则你的ListView将一直处于正在刷新状态。
	 */
	public void finishRefreshing() {
		currentStatus = STATUS_NOREFRESH;
		new HideHeadTask().execute();
	}

	/**
	 * 更新下拉头中的信息。
	 */
	private void updateHeadView() {
		if (lastStatus != currentStatus) {
			if (currentStatus == STATUS_PULL_TO_REFRESH) {
				tv_refreshstate.setText(getResources().getString(
						R.string.pull_to_refresh));
				iv_arrow.setVisibility(View.VISIBLE);
				iv_progress.setVisibility(View.GONE);
				rotateArrow();
			} else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
				tv_refreshstate.setText(getResources().getString(
						R.string.release_to_refresh));
				iv_arrow.setVisibility(View.VISIBLE);
				iv_progress.setVisibility(View.GONE);
				rotateArrow();
			} else if (currentStatus == STATUS_REFRESHING) {
				tv_refreshstate.setText(getResources().getString(
						R.string.refreshing));
				iv_progress.setVisibility(View.VISIBLE);
				iv_arrow.clearAnimation();
				iv_arrow.setVisibility(View.GONE);
			}

		}
	}

	/**
	 * 根据当前的状态来旋转箭头。
	 */
	private void rotateArrow() {
		float pivotX = iv_arrow.getWidth() / 2f;
		float pivotY = iv_arrow.getHeight() / 2f;
		float fromDegrees = 0f;
		float toDegrees = 0f;
		if (currentStatus == STATUS_PULL_TO_REFRESH) {
			fromDegrees = 180f;
			toDegrees = 360f;
		} else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
			fromDegrees = 0f;
			toDegrees = 180f;
		}
		RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees,
				pivotX, pivotY);
		animation.setDuration(100);
		animation.setFillAfter(true);
		iv_arrow.startAnimation(animation);
	}

	/**
	 * 正在刷新的任务，在此任务中会去回调注册进来的下拉刷新监听器。
	 */
	class RefreshingTask extends AsyncTask<Void, Integer, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			int topMargin = headParams.topMargin;
			while (true) {
				topMargin = topMargin + SPEED;
				if (topMargin <= 0) {
					topMargin = 0;
					break;
				}
				publishProgress(topMargin);
				sleep(10);
			}
			currentStatus = STATUS_REFRESHING;
			publishProgress(0);
			if (pullListener != null) {
				pullListener.onRefresh();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... topMargin) {
			updateHeadView();
			headParams.topMargin = topMargin[0];
			headView.setLayoutParams(headParams);
		}
	}

	/**
	 * 隐藏下拉头的任务，当未进行下拉刷新或下拉刷新完成后，此任务将会使下拉头重新隐藏。
	 */
	class HideHeadTask extends AsyncTask<Void, Integer, Integer> {
		@Override
		protected Integer doInBackground(Void... params) {
			int topMargin = headParams.topMargin;
			while (true) {
				topMargin = topMargin + SPEED;
				if (topMargin <= hideHeadHeight) {
					topMargin = hideHeadHeight;
					break;
				}
				publishProgress(topMargin);
				sleep(10);
			}
			return topMargin;
		}

		@Override
		protected void onProgressUpdate(Integer... topMargin) {
			headParams.topMargin = topMargin[0];
			headView.setLayoutParams(headParams);
		}

		@Override
		protected void onPostExecute(Integer topMargin) {
			headParams.topMargin = topMargin;
			headView.setLayoutParams(headParams);
			currentStatus = STATUS_NOREFRESH;
		}
	}

	/**
	 * 使当前线程睡眠指定的毫秒数。
	 * 
	 *  指定当前线程睡眠多久，以毫秒为单位
	 */
	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下拉刷新的监听器，使用下拉刷新的地方应该注册此监听器来获取刷新回调。
	 */
	public interface PullToRefreshListener {
		/**
		 * 刷新时会去回调此方法，在方法内编写具体的刷新逻辑。注意此方法是在子线程中调用的， 你可以不必另开线程来进行耗时操作。
		 */
		void onRefresh();
	}

}
