package com.example.girdviewtest;

import java.util.ArrayList;
import java.util.HashMap;

import com.suixin.vy.ui.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Toast;
import android.provider.MediaStore;

public class MainActivity extends Activity {

	private GridView gridView1;                   //网格显示缩略图
	private Button buttonPublish;                //发布按钮
	private final int IMAGE_OPEN = 1;        //打开图片标记
	private String pathImage;                       //选择图片路径
	private Bitmap bmp;                               //导入临时图片
	private ArrayList<HashMap<String, Object>> imageItem;
	private SimpleAdapter simpleAdapter;     //适配器

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lee);
        /*
         * 防止键盘挡住输入框
         * 不希望遮挡设置activity属性 android:windowSoftInputMode="adjustPan"
         * 希望动态调整高度 android:windowSoftInputMode="adjustResize"
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.
        		SOFT_INPUT_ADJUST_PAN);
        //锁定屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main_lee);
        //获取控件对象
        gridView1 = (GridView) findViewById(R.id.GridView1);

        /*
         * 载入默认图片添加图片加号
         * 通过适配器实现
         * SimpleAdapter参数imageItem为数据源 R.layout.griditem_addpic为布局
         */
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon_addpic_unfocused); //加号
        imageItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("itemImage", bmp);
        imageItem.add(map);
        simpleAdapter = new SimpleAdapter(this, 
        		imageItem, R.layout.griditem_addpic, 
                new String[] { "itemImage"}, new int[] { R.id.imageView1}); 
        /*
         * HashMap载入bmp图片在GridView中不显示,但是如果载入资源ID能显示 如
         * map.put("itemImage", R.drawable.img);
         * 解决方法:
         *              1.自定义继承BaseAdapter实现
         *              2.ViewBinder()接口实现
         *  参考 http://blog.csdn.net/admin_/article/details/7257901
         */
        simpleAdapter.setViewBinder(new ViewBinder() {  
		    @Override  
		    public boolean setViewValue(View view, Object data,  
		            String textRepresentation) {  
		        if(view instanceof ImageView && data instanceof Bitmap){  
		            ImageView i = (ImageView)view;  
		            i.setImageBitmap((Bitmap) data);  
		            return true;  
		        }  
		        return false;  
		    }
        });  
        gridView1.setAdapter(simpleAdapter);
        
        /*
         * 监听GridView点击事件
         * 报错:该函数必须抽象方法 故需要手动导入import android.view.View;
         */
        gridView1.setOnItemClickListener(new OnItemClickListener() {
  			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
  				if( imageItem.size() == 10) { //第一张为默认图片
  					Toast.makeText(MainActivity.this, "图片数9张已满", Toast.LENGTH_SHORT).show();
  				}
  				else if(position == 0) { //点击图片位置为+ 0对应0张图片
  					Toast.makeText(MainActivity.this, "添加图片", Toast.LENGTH_SHORT).show();
  					//选择图片
  					Intent intent = new Intent(Intent.ACTION_PICK,       
  	                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
  	                startActivityForResult(intent, IMAGE_OPEN);  
  	                //通过onResume()刷新数据
  				}
  				else {
  					dialog(position);
  					//Toast.makeText(MainActivity.this, "点击第" + (position + 1) + " 号图片", 
  					//		Toast.LENGTH_SHORT).show();
  				}
				
			}
  		});  
    }
    
    //获取图片路径 响应startActivityForResult  
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        super.onActivityResult(requestCode, resultCode, data);        
        //打开图片  
        if(resultCode==RESULT_OK && requestCode==IMAGE_OPEN) {        
            Uri uri = data.getData();  
            if (!TextUtils.isEmpty(uri.getAuthority())) {  
                //查询选择图片  
                Cursor cursor = getContentResolver().query(  
                        uri,  
                        new String[] { MediaStore.Images.Media.DATA },  
                        null,   
                        null,   
                        null);  
                //返回 没找到选择图片  
                if (null == cursor) {  
                    return;  
                }  
                //光标移动至开头 获取图片路径  
                cursor.moveToFirst();  
                pathImage = cursor.getString(cursor  
                        .getColumnIndex(MediaStore.Images.Media.DATA));  
            }
        }  //end if 打开图片
    }
    
    //刷新图片
    @Override
	protected void onResume() {
		super.onResume();
		if(!TextUtils.isEmpty(pathImage)){
			Bitmap addbmp=BitmapFactory.decodeFile(pathImage);
			HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("itemImage", addbmp);
	        imageItem.add(map);
	        simpleAdapter = new SimpleAdapter(this, 
	        		imageItem, R.layout.griditem_addpic, 
	                new String[] { "itemImage"}, new int[] { R.id.imageView1}); 
	        simpleAdapter.setViewBinder(new ViewBinder() {  
			    @Override  
			    public boolean setViewValue(View view, Object data,  
			            String textRepresentation) {  
			        if(view instanceof ImageView && data instanceof Bitmap){  
			            ImageView i = (ImageView)view;  
			            i.setImageBitmap((Bitmap) data);  
			            return true;  
			        }  
			        return false;  
			    }
	        }); 
	        gridView1.setAdapter(simpleAdapter);
	        simpleAdapter.notifyDataSetChanged();
			//刷新后释放防止手机休眠后自动添加
	        pathImage = null;
		}
	}
    
    /*
     * Dialog对话框提示用户删除操作
     * position为删除图片位置
     */
    protected void dialog(final int position) {
    	AlertDialog.Builder builder = new Builder(MainActivity.this);
    	builder.setMessage("确认移除已添加图片吗？");
    	builder.setTitle("提示");
    	builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
    		@Override
    		public void onClick(DialogInterface dialog, int which) {
    			dialog.dismiss();
    			imageItem.remove(position);
    	        simpleAdapter.notifyDataSetChanged();
    		}
    	});
    	builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
    		@Override
    		public void onClick(DialogInterface dialog, int which) {
    			dialog.dismiss();
    			}
    		});
    	builder.create().show();
    }

}
