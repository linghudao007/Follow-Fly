package com.suixin.vz.find;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ImageLoaderTask extends AsyncTask<TaskParam, Void, Bitmap> {

    private TaskParam param;
    private final WeakReference<View> ViewReference; // 防止内存溢出

    public ImageLoaderTask(View View) {
        ViewReference = new WeakReference<View>(View);
    }

    @Override
    protected Bitmap doInBackground(TaskParam... params) {

        param = params[0];
        return loadImageFile(param.getFilename(), param.getAssetManager());
    }

    private Bitmap loadImageFile(final String filename,
            final AssetManager manager) {
        InputStream is = null;
        try {
            
            Bitmap bmp = BitmapCache.getInstance().getBitmap(filename,
                    param.getAssetManager());
            return bmp;
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), "fetchDrawable failed", e);
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (ViewReference != null) {
            View View = ViewReference.get();
            if (View != null) {
                if (bitmap != null) {
                    int width = bitmap.getWidth();// 获取真实宽高
                    int height = bitmap.getHeight();
                    LayoutParams lp = View.getLayoutParams();
                    lp.height = (height * param.getItemWidth()) / width;// 调整高度
                    Drawable d = new BitmapDrawable(bitmap);
                    View.setLayoutParams(lp);
                    View.setBackground(d);

                }
            }
        }
    }
}