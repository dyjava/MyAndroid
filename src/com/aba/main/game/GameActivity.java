package com.aba.main.game;

import java.util.ArrayList;
import java.util.List;

import com.aba.main.BaseActivity;
import com.aba.main.util.ViewOnClickListener;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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
        
        //Ëæ»ú
        List<String> list = new ArrayList<String>() ;
        for(int i=0;i<8;i++){
        	list.add(i+1+"") ;
        }
        list.add("") ;
        
        //»æÍ¼
        int i=0;
        for(int r=0;r<3;r++){
        	TableRow row = new TableRow(this);
        	for(int c=0;c<3;c++){
        		//Ëæ»úÌî³ä
        		int point = (int)(Math.random()*list.size()) ;
        		String text = list.remove(point);
				row.addView(this.createView(text, i++)) ;
        	}
        	layout.addView(row);
        }
        setContentView(layout);
	}

	//´´½¨°´Å¥
	private View createView(String text, int id){
		TextView view = new TextView(this) ;
		
		view.setText(text);
		view.setId(id) ;
		view.setWidth(200) ;
		view.setHeight(200) ;
		view.setTextColor(Color.BLUE) ;
		view.setTextSize(24) ;
		int point =5 ;
		view.setLeft(point) ;
		view.setRight(point) ;
		view.setTop(point) ;
		view.setBottom(point) ;
		view.setGravity(Gravity.CENTER);
		view.setBackgroundColor(Color.rgb(210, 105, 30)) ;
		view.setOnClickListener(new ViewOnClickListener(this)) ;
		return view ;
	}
	
	@Override
	public void viewOnClick(View v) {
		TextView bt = (TextView) v;
		String title = bt .getText().toString() ;
		int id = bt.getId() ;
		//½ôÁÚµÄÎª¿Õ£¬ÏÔÊ¾ÄÚÈÝ»¥»»
		for(int i=0;i<9;i++){
			TextView view = (TextView) this.findViewById(i) ;
			if(view.getText().toString().length()==0){
				if(i==id-1 || i==id+1 || i==id+3 || i==id-3){
					bt.setText("") ;
					view.setText(title) ;
				}
				break ;
			}
		}
		
	}


}
