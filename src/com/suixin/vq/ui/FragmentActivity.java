package com.suixin.vq.ui;
import java.util.ArrayList;
import java.util.List;
import com.suixin.vy.ui.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class FragmentActivity extends Activity {
	private ImageButton  bt;
	private ImageButton  bt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_1_vqmain);
       bt=(ImageButton )findViewById(R.id.btn_lz);
        		bt.setOnClickListener(new  OnClickListener() {
       
					@Override
					public void onClick(View v) {
						Intent  intent  =new  Intent();
						intent.setClass(FragmentActivity.this, SecondActivity.class);
						startActivity(intent);
				}
				
			}); 
        		 bt2=(ImageButton )findViewById(R.id.btn_1);
         		bt2.setOnClickListener(new  OnClickListener() {
        
 					@Override
 					public void onClick(View v) {
 						Intent  intent  =new  Intent();
 						intent.setClass(FragmentActivity.this, CuttondActivity.class);
 						startActivity(intent);
 				}
 				
 			}); 
	}
}