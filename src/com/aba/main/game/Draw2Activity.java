package com.aba.main.game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.aba.main.BaseActivity;
import com.aba.main.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Draw2Activity extends BaseActivity{
	private ImageView iv;
	private Bitmap baseBitmap;
	private Canvas canvas;
	private Paint paint;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw2);
		this.iv = (ImageView) this.findViewById(R.id.iv);
		
		// 创建一张空白图片
		int width = getWindowManager().getDefaultDisplay().getWidth() ;
		int height = getWindowManager().getDefaultDisplay().getHeight() ;
		baseBitmap = Bitmap.createBitmap(width, height-20, Bitmap.Config.ARGB_8888);
		// 创建一张画布
		canvas = new Canvas(baseBitmap);
		// 画布背景为灰色
		canvas.drawColor(Color.BLUE);
		// 创建画笔
		paint = new Paint();
		// 画笔颜色为红色
		paint.setColor(Color.RED);
		// 宽度5个像素
		paint.setStrokeWidth(5);
		// 先将灰色背景画上
		canvas.drawBitmap(baseBitmap, new Matrix(), paint);
		iv.setImageBitmap(baseBitmap);
		
		iv.setOnTouchListener(new OnTouchListener() {
			int startX;
			int startY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						// 获取手按下时的坐标
						startX = (int) event.getX();
						startY = (int) event.getY();
						break;
					case MotionEvent.ACTION_MOVE:
						// 获取手移动后的坐标
						int stopX = (int) event.getX();
						int stopY = (int) event.getY();
						// 在开始和结束坐标间画一条线
						canvas.drawLine(startX, startY, stopX, stopY, paint);
						// 实时更新开始坐标
						startX = (int) event.getX();
						startY = (int) event.getY();
						iv.setImageBitmap(baseBitmap);
						break;
				}
			return true;
		}
		});
	}
	
	public void save(View view) {
		try {
			File file = new File(Environment.getExternalStorageDirectory(),
					System.currentTimeMillis() + ".jpg");
			OutputStream stream = new FileOutputStream(file);
			baseBitmap.compress(CompressFormat.JPEG, 100, stream);
			stream.close();
			// 模拟一个广播，通知系统sdcard被挂载
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);
			
			Toast.makeText(this, "保存图片成功", 0).show();
		} catch (Exception e) {
			Toast.makeText(this, "保存图片失败", 0).show();
			e.printStackTrace();
		}
	}

}
