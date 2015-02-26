package com.aba.main.shudu;


import com.aba.main.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.WindowManager;

public class ShuduActivity extends Activity {

	private float mDownX;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shudu);

		//去掉标题栏
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shudu, menu);
		return true;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println("ACTION_DOWN"+x);
			mDownX = x;          //记录按下时的x坐标
			break;
		case MotionEvent.ACTION_UP:
			int dis = (int) (x - mDownX);   //滑动的距离
			System.out.println("ACTION_UP:"+dis);
//			if(Math.abs(dis) > (mWidth * mMenuWeight / 2)){
//				if(dis > 0){          //如果>0则是向右滑动
//					toRightMove();
//				}else{				  //如果<0则是向左滑动
//					toLeftMove();
//				}
//			}
			break;
		default:
			break;
		}
		
		return true;
	}
	
}
