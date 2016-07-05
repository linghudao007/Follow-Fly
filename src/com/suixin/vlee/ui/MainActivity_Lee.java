package com.suixin.vlee.ui;

import com.suixin.vy.ui.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_Lee extends Activity implements OnClickListener {
	private Button but_next;
	private TextView tv[];
	private Button btn_quxiao;
	/** 记录被点击的按钮 */
	private int index = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		initView();
		addListener();
	}

	private void addListener() {
		for (int i = 0; i < tv.length; i++) {
			tv[i].setOnClickListener(this);
		}
		but_next.setOnClickListener(this);
		btn_quxiao.setOnClickListener(this);

	}

	private void initView() {
		btn_quxiao = (Button) findViewById(R.id.btn_quxiao);
		but_next = (Button) findViewById(R.id.but_next);
		tv = new TextView[16];
		tv[0] = (TextView) findViewById(R.id.tv_1);
		tv[1] = (TextView) findViewById(R.id.tv_2);
		tv[2] = (TextView) findViewById(R.id.tv_3);
		tv[3] = (TextView) findViewById(R.id.tv_4);
		tv[4] = (TextView) findViewById(R.id.tv_5);
		tv[5] = (TextView) findViewById(R.id.tv_6);
		tv[6] = (TextView) findViewById(R.id.tv_7);
		tv[7] = (TextView) findViewById(R.id.tv_8);
		tv[8] = (TextView) findViewById(R.id.tv_9);
		tv[9] = (TextView) findViewById(R.id.tv_10);
		tv[10] = (TextView) findViewById(R.id.tv_11);
		tv[11] = (TextView) findViewById(R.id.tv_12);
		tv[12] = (TextView) findViewById(R.id.tv_13);
		tv[13] = (TextView) findViewById(R.id.tv_14);
		tv[14] = (TextView) findViewById(R.id.tv_15);
		tv[15] = (TextView) findViewById(R.id.tv_16);

	}

	private void setShow(int x) {
		if (index == x) {
			tv[x].setBackgroundColor(Color.WHITE);
			index = -1;
		} else {
			index = x;
			for (int i = 0; i < tv.length; i++) {
				if (i == index) {
					tv[i].setBackgroundResource(R.drawable.shape_button_highlight_clicked_lee);
				} else {
					tv[i].setBackgroundColor(Color.WHITE);
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_1:

			setShow(0);
			break;
		case R.id.tv_2:
			setShow(1);
			break;
		case R.id.tv_3:
			setShow(2);
			break;
		case R.id.tv_4:
			setShow(3);
			break;
		case R.id.tv_5:
			setShow(4);
			break;
		case R.id.tv_6:
			setShow(5);
			break;
		case R.id.tv_7:
			setShow(6);
			break;
		case R.id.tv_8:
			setShow(7);
			break;
		case R.id.tv_9:
			setShow(8);
			break;
		case R.id.tv_10:
			setShow(9);
			break;
		case R.id.tv_11:
			setShow(10);
			break;
		case R.id.tv_12:
			setShow(11);
			break;
		case R.id.tv_13:
			setShow(12);
			break;
		case R.id.tv_14:
			setShow(13);
			break;
		case R.id.tv_15:
			setShow(14);
			break;
		case R.id.tv_16:
			setShow(15);
			break;
		case R.id.btn_quxiao:
			this.finish();
			break;
		case R.id.but_next:
			if (index == -1) {
				Toast.makeText(MainActivity_Lee.this, "请选择约伴条件",
						Toast.LENGTH_SHORT).show();
			} else if (index < 8) {
				Intent intent = new Intent(MainActivity_Lee.this,
						ListViewLeeActivityThree.class);
				startActivity(intent);
			} else if (index > 7) {
				Intent intent1 = new Intent(MainActivity_Lee.this,
						ListViewLeeActivity.class);
				startActivity(intent1);
			}
			break;

		}

	}

}