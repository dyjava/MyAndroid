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
		
		// ����һ�ſհ�ͼƬ
		int width = getWindowManager().getDefaultDisplay().getWidth() ;
		int height = getWindowManager().getDefaultDisplay().getHeight() ;
		baseBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		// ����һ�Ż���
		canvas = new Canvas(baseBitmap);
		// ��������Ϊ��ɫ
		canvas.drawColor(Color.BLUE);
		// ��������
		paint = new Paint();
		// ������ɫΪ��ɫ
		paint.setColor(Color.RED);
		// ���5������
		paint.setStrokeWidth(5);
		// �Ƚ���ɫ��������
		canvas.drawBitmap(baseBitmap, new Matrix(), paint);
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
	
	public void save(View view) {
		try {
			File file = new File(this.storedir, System.currentTimeMillis() + ".jpg");
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
