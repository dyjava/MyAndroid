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
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout=new LinearLayout(this);
		// ����һ�ſհ�ͼƬ
		int width = getWindowManager().getDefaultDisplay().getWidth() ;
		int height = getWindowManager().getDefaultDisplay().getHeight() ;
		baseBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		
		this.create() ;

		this.createImageView() ;
		
		layout.addView(iv) ;

		setContentView(layout);
	}
	private void create(){
		// ��������
		paint = new Paint();
		// ������ɫΪ��ɫ
		paint.setColor(paintColor);
		// ���5������
		paint.setStrokeWidth(5);
		
		//��������
		canvas = new Canvas(baseBitmap);
		// ��������Ϊ��ɫ
		canvas.drawColor(canvasColor);
		// �Ƚ���ɫ��������
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
						// ��ȡ�ְ���ʱ������
						startX = (int) event.getX();
						startY = (int) event.getY();
						break;
					case MotionEvent.ACTION_MOVE:
						// ��ȡ���ƶ��������
						int stopX = (int) event.getX();
						int stopY = (int) event.getY();
						// �ڿ�ʼ�ͽ�������仭һ����
						canvas.drawLine(startX, startY, stopX, stopY, paint);
						// ʵʱ���¿�ʼ����
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
		SubMenu bgcolor = menu.addSubMenu("����ɫ") ;
		SubMenu pencolor = menu.addSubMenu("����ɫ") ;
		
		bgcolor.add(0, 1, 0, "��ɫ") ;
		bgcolor.add(0, 2, 0, "��ɫ") ;
		bgcolor.add(0, 3, 0, "��ɫ") ;
		bgcolor.add(0, 4, 0, "��ɫ") ;
		bgcolor.add(0, 5, 0, "��ɫ") ;
		bgcolor.add(0, 6, 0, "��ɫ") ;
		
		pencolor.add(0, 101, 0, "��ɫ") ;
		pencolor.add(0, 102, 0, "��ɫ") ;
		pencolor.add(0, 103, 0, "��ɫ") ;
		pencolor.add(0, 104, 0, "��ɫ") ;
		pencolor.add(0, 105, 0, "��ɫ") ;
		pencolor.add(0, 106, 0, "��ɫ") ;
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
