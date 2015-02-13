package com.aba.main.draw;

import com.aba.main.BaseActivity;
import com.aba.main.R;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Draw3Activity extends BaseActivity{
	private Bitmap baseBitmap;
	private Canvas canvas;
	private Paint paint;
	private ImageView iv ;
	
	private int paintColor = Color.RED ;
	private int canvasColor = Color.BLUE ;
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
		
		bgcolor.add(0, 1, 0, "红色") ;
		bgcolor.add(0, 2, 0, "黄色") ;
		bgcolor.add(0, 3, 0, "蓝色") ;
		bgcolor.add(0, 4, 0, "绿色") ;
		bgcolor.add(0, 5, 0, "白色") ;
		bgcolor.add(0, 6, 0, "黑色") ;
		
		pencolor.add(0, 101, 0, "红色") ;
		pencolor.add(0, 102, 0, "黄色") ;
		pencolor.add(0, 103, 0, "蓝色") ;
		pencolor.add(0, 104, 0, "绿色") ;
		pencolor.add(0, 105, 0, "白色") ;
		pencolor.add(0, 106, 0, "黑色") ;
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case 1:
				canvasColor = Color.RED ;
				break;
			case 2:
				canvasColor = Color.YELLOW ;
				break;
			case 3:
				canvasColor = Color.BLUE ;
				break;
			case 4:
				canvasColor = Color.GREEN ;
				break;
			case 5:
				canvasColor = Color.WHITE ;
				break;
			case 6:
				canvasColor = Color.BLACK ;
				break;
				

			case 101:
				paintColor = Color.RED ;
				break;
			case 102:
				paintColor = Color.YELLOW ;
				break;
			case 103:
				paintColor = Color.BLUE ;
				break;
			case 104:
				paintColor = Color.GREEN ;
				break;
			case 105:
				paintColor = Color.WHITE ;
				break;
			case 106:
				paintColor = Color.BLACK ;
				break;
		}
		if(item.getItemId()<100){
			this.create() ;
		} else {
			paint.setColor(paintColor);
		}
		
		return true ;
	}  

}
