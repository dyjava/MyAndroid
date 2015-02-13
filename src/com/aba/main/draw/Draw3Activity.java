package com.aba.main.draw;

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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Draw3Activity extends BaseActivity{
	private Bitmap baseBitmap;
	private Canvas canvas;
	private Paint paint;
	private ImageView iv ;
	
	private int paintColor = Color.RED ;
	private int canvasColor = Color.WHITE ;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout=new LinearLayout(this);
		// 创建一张空白图片
		int width = getWindowManager().getDefaultDisplay().getWidth() ;
		int height = getWindowManager().getDefaultDisplay().getHeight() ;
		baseBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		
		this.create() ;

		this.createImageView() ;
		
		layout.addView(iv) ;

		setContentView(layout);
	}
	private void create(){
		// 创建画笔
		paint = new Paint();
		// 画笔颜色为红色
		paint.setColor(paintColor);
		// 宽度5个像素
		paint.setStrokeWidth(5);
		
		//创建画布
		canvas = new Canvas(baseBitmap);
		// 画布背景为灰色
		canvas.drawColor(canvasColor);
		// 先将灰色背景画上
		canvas.drawBitmap(baseBitmap, new Matrix(), paint);
	}
	
	private void createImageView(){
		iv = new ImageView(this) ;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shudu, menu);
		SubMenu bgcolor = menu.addSubMenu("背景色") ;
		SubMenu pencolor = menu.addSubMenu("画笔色") ;
		menu.add(3, 1, 0, "保存") ;
		
		/** 
		 * add()方法的四个参数，依次是：
         * 1、组别，如果不分组的话就写Menu.NONE,
         * 2、Id，这个很重要，Android根据这个Id来确定不同的菜单
         * 3、顺序，那个菜单现在在前面由这个参数的大小决定
         * 4、文本，菜单的显示文本
         */
		bgcolor.add(1, Color.RED, 0, "红色") ;
		bgcolor.add(1, Color.YELLOW, 0, "黄色") ;
		bgcolor.add(1, Color.BLUE, 0, "蓝色") ;
		bgcolor.add(1, Color.GREEN, 0, "绿色") ;
		bgcolor.add(1, Color.WHITE, 0, "白色") ;
		bgcolor.add(1, Color.BLACK, 0, "黑色") ;
		
		pencolor.add(2, Color.RED, 0, "红色") ;
		pencolor.add(2, Color.YELLOW, 0, "黄色") ;
		pencolor.add(2, Color.BLUE, 0, "蓝色") ;
		pencolor.add(2, Color.GREEN, 0, "绿色") ;
		pencolor.add(2, Color.WHITE, 0, "白色") ;
		pencolor.add(2, Color.BLACK, 0, "黑色") ;
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		int group = item.getGroupId() ;
		//保存
		if(group==3){
			this.save() ;
			return true ;
		}
		//背景色
		if(group==1){
			canvasColor = item.getItemId() ;
			this.create() ;
		} else if(group==2){
			//画笔颜色
			paintColor = item.getItemId() ;
			paint.setColor(paintColor);
		}
		
		return true ;
	}
	
	public void save() {
		try {
			File file = new File("/tmp", System.currentTimeMillis() + ".jpg");
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
