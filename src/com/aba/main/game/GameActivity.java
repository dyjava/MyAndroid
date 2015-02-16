package com.aba.main.game;

import java.util.ArrayList;
import java.util.List;

import com.aba.main.BaseActivity;
import com.aba.main.util.ViewOnClickListener;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TableLayout layout=new TableLayout(this);  
		layout.setStretchAllColumns(true);
        layout.setOrientation(TableLayout.VERTICAL);
        
        //随机
        List<String> list = new ArrayList<String>() ;
        for(int i=0;i<8;i++){
        	list.add(i+1+"") ;
        }
        list.add("") ;
        
        //绘图
        int i=0;
        for(int r=0;r<3;r++){
        	TableRow row = new TableRow(this);
        	for(int c=0;c<3;c++){
        		//随机填充
        		int point = (int)(Math.random()*list.size()) ;
        		String text = list.remove(point);
				row.addView(this.createView(text, i++)) ;
        	}
        	layout.addView(row);
        }
        setContentView(layout);
	}

	//创建按钮
	private View createView(String text, int id){
		ImageView image = new ImageView(this) ;
		image.setId(id) ;
		try {
			//从本地取图片
//			File url = new File("") ;
//			FileInputStream fis = new FileInputStream(url );
//			Bitmap bitmap = BitmapFactory.decodeStream(fis);
			
			
//			ApplicationInfo appInfo = getApplicationInfo();
//			int resID = getResources().getIdentifier(text+"jpg", "drawable", appInfo.packageName) ;
			int resID = getResources().getIdentifier("table_1_"+text, "drawable", "com.aba.main");
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resID);
			
			image.setImageBitmap(bitmap);	//设置Bitmap
			
			image.setAdjustViewBounds(true) ;
			image.setMaxHeight(200) ;
			image.setMaxWidth(200) ;
			image.setScaleType(ScaleType.CENTER_CROP) ;
			image.setOnClickListener(new ViewOnClickListener(this)) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		TextView view = new TextView(this) ;
//		
//		view.setText(text);
//		view.setId(id) ;
//		view.setWidth(200) ;
//		view.setHeight(200) ;
//		view.setTextColor(Color.BLUE) ;
//		view.setTextSize(24) ;
//		int point =5 ;
//		view.setLeft(point) ;
//		view.setRight(point) ;
//		view.setTop(point) ;
//		view.setBottom(point) ;
//		view.setGravity(Gravity.CENTER);
//		view.setBackgroundColor(Color.rgb(210, 105, 30)) ;
//		view.setOnClickListener(new ViewOnClickListener(this)) ;
		return image ;
	}
	
	@Override
	public void viewOnClick(View v) {
		ImageView bt = (ImageView) v;
//		String title = bt.getText().toString() ;
		int id = bt.getId() ;
		//紧邻的为空，显示内容互换
		for(int i=0;i<9;i++){
			TextView view = (TextView) this.findViewById(i) ;
			if(view.getText().toString().length()==0){
				if(i==id-1 || i==id+1 || i==id+3 || i==id-3){
//					bt.setText("") ;
//					view.setText(title) ;
				}
				break ;
			}
		}
		
	}


}
