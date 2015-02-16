package com.aba.main.game;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.aba.main.BaseActivity;
import com.aba.main.R;
import com.aba.main.util.ViewOnClickListener;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends BaseActivity {

	//ͼƬ�洢����
	private Hashtable<Integer,Integer> table = new Hashtable<Integer,Integer>() ;
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		//��title
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		//ȫ��
//		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		this.setTitle("ƴͼ��Ϸ") ;
		this.setTitleColor(R.color.forestgreen) ;
		
		TableLayout layout=new TableLayout(this);  
		layout.setStretchAllColumns(true);
        layout.setOrientation(TableLayout.VERTICAL);
        
        //���
        List<String> list = new ArrayList<String>() ;
        for(int i=0;i<8;i++){
        	list.add(i+"") ;
        }
        list.add("") ;
        
        //ͼ��,3��ͼƬ�����ȡ
        int type = (int)(Math.random()*3) ;
        
        //��ͼ
        int i=0;
        for(int r=0;r<3;r++){
        	TableRow row = new TableRow(this);
        	for(int c=0;c<3;c++){
        		//������
        		int point = (int)(Math.random()*list.size()) ;
        		String text = list.remove(point);
				row.addView(this.createView("table_"+type+"_"+text, i++)) ;
        	}
        	layout.addView(row);
        }
        TableRow row = new TableRow(this);
        row.addView(new TextView(this)) ;
        TextView text = new TextView(this) ;
        text.setText("�ο�ԭͼ��") ;
        row.addView(text) ;
        layout.addView(row);
        
        row = new TableRow(this);
        row.addView(new TextView(this)) ;
        ImageView img = new ImageView(this) ;
        int resID = getResources().getIdentifier("table_"+type, "drawable", "com.aba.main");
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resID);
		img.setImageBitmap(bitmap);	//����Bitmap
		img.setAdjustViewBounds(true) ;
		img.setMaxHeight(240) ;
		img.setMaxWidth(240) ;
		img.setScaleType(ScaleType.CENTER_CROP) ;
        row.addView(img) ;
        layout.addView(row);
        
        setContentView(layout);
	}

	//������ť
	private View createView(String text, int id){
		ImageView image = new ImageView(this) ;
		image.setId(id) ;
		try {
			//�ӱ���ȡͼƬ
//			File url = new File("") ;
//			FileInputStream fis = new FileInputStream(url );
//			Bitmap bitmap = BitmapFactory.decodeStream(fis);
			
			
//			ApplicationInfo appInfo = getApplicationInfo();
//			int resID = getResources().getIdentifier(text+"jpg", "drawable", appInfo.packageName) ;
			
			//��ȡͼƬ��Դ
			int resID = getResources().getIdentifier(text, "drawable", "com.aba.main");
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resID);
			image.setImageBitmap(bitmap);	//����Bitmap
			
//			//�洢ͼƬ��Դ��view�Ķ��ձ�
			table.put(id, resID) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image.setAdjustViewBounds(true) ;
		image.setMaxHeight(240) ;
		image.setMaxWidth(240) ;
		image.setScaleType(ScaleType.CENTER_CROP) ;
		image.setOnClickListener(new ViewOnClickListener(this)) ;
		
		return image ;
	}
	
	@Override
	public void viewOnClick(View v) {
		ImageView vi = (ImageView) v;
		int id1 = vi.getId() ;
		int value1 = table.get(id1) ;
		//���ڵ�Ϊ�գ���ʾ���ݻ���
		for(int i=0;i<9;i++){
			ImageView view = (ImageView) this.findViewById(i) ;
			int id2 = view.getId() ;
			int value2 = table.get(id2) ;
			
			if(value2==0){
				if(id2==id1-1 || id2==id1+1 || id2==id1+3 || id2==id1-3){
					//ͼƬ����
					vi.setImageBitmap(BitmapFactory.decodeResource(getResources(), value2));
					view.setImageBitmap(BitmapFactory.decodeResource(getResources(), value1));
					table.put(id1, value2) ;
					table.put(id2, value1) ;
				}
				break ;
			}
		}
		
	}


}
