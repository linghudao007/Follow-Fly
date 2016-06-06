package com.suixin.vy.core;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 根据宽度确定正方形图片
 * 
 * @author yxy
 * 
 */
public class SquareImageView extends ImageView {
	private Context context;

	public SquareImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Toast.makeText(context, "MyImageView", 1000).show();
		this.context = context;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}

}
