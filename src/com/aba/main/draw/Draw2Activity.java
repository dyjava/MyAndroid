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

public class Draw2Activity extends BaseActivity{
	private Bitmap baseBitmap;
	private Canvas canvas;
	private Paint paint;
	private ImageView iv ;
	
	private int paintColor = Color.RED ;
	private int canvasColor = Color.BLACK ;
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
		menu.add(3, 1, 0, "����") ;
		
		/** 
		 * add()�������ĸ������������ǣ�
         * 1��������������Ļ���дMenu.NONE,
         * 2��Id���������Ҫ��Android�������Id��ȷ����ͬ�Ĳ˵�
         * 3��˳���Ǹ��˵�������ǰ������������Ĵ�С����
         * 4���ı����˵�����ʾ�ı�
         */
		bgcolor.add(1, Color.RED, 0, "��ɫ") ;
		bgcolor.add(1, Color.YELLOW, 0, "��ɫ") ;
		bgcolor.add(1, Color.BLUE, 0, "��ɫ") ;
		bgcolor.add(1, Color.GREEN, 0, "��ɫ") ;
		bgcolor.add(1, Color.WHITE, 0, "��ɫ") ;
		bgcolor.add(1, Color.BLACK, 0, "��ɫ") ;
		
		pencolor.add(2, Color.RED, 0, "��ɫ") ;
		pencolor.add(2, Color.YELLOW, 0, "��ɫ") ;
		pencolor.add(2, Color.BLUE, 0, "��ɫ") ;
		pencolor.add(2, Color.GREEN, 0, "��ɫ") ;
		pencolor.add(2, Color.WHITE, 0, "��ɫ") ;
		pencolor.add(2, Color.BLACK, 0, "��ɫ") ;
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		int group = item.getGroupId() ;
		//����
		if(group==3){
			this.save() ;
			return true ;
		}
		//����ɫ
		if(group==1){
			canvasColor = item.getItemId() ;
			this.create() ;
		} else if(group==2){
			//������ɫ
			paintColor = item.getItemId() ;
			paint.setColor(paintColor);
		}
		
		return true ;
	}
	
	public void save() {
		try {
			File file = new File(storedir, System.currentTimeMillis() + ".jpg");
			OutputStream stream = new FileOutputStream(file);
			baseBitmap.compress(CompressFormat.JPEG, 100, stream);
			stream.close();
			// ģ��һ���㲥��֪ͨϵͳsdcard������
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);
			
			Toast.makeText(this, "����ͼƬ�ɹ�", 0).show();
		} catch (Exception e) {
			Toast.makeText(this, "����ͼƬʧ��", 0).show();
			e.printStackTrace();
		}
	}

}
