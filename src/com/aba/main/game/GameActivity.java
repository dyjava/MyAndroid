package com.aba.main.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.aba.main.MainActivity;
import com.example.mytest.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TableLayout layout=new TableLayout(this);  
		layout.setStretchAllColumns(true);
        layout.setOrientation(TableLayout.VERTICAL);
        
        //Ëæ»ú
        List<Object> set = new ArrayList<Object>() ;
        set.add(2) ;
        set.add(4) ;
        set.add(6) ;
        set.add(1) ;
        set.add(7) ;
        set.add(8) ;
        set.add(5) ;
        set.add(3) ;
        set.add("") ;
        
        //»æÍ¼
        int i=0;
        for(int r=0;r<3;r++){
        	TableRow row = new TableRow(this);
        	for(int c=0;c<3;c++){
        		Button btn = new Button(this);
//        		btn.setText(r+"-"+c) ;
        		btn.setText((8-i)+"") ;
        		if(i==8){
        			btn.setText("") ;
        		}
        		btn.setId(i++) ;
        		btn.setWidth(100) ;
        		btn.setHeight(100) ;
        		btn.setOnClickListener(new GameOnClickListener(this)) ;
        		
        		row.addView(btn) ;
        	}
        	layout.addView(row);
        }
        setContentView(layout);  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	class GameOnClickListener implements OnClickListener {

		GameActivity act ;
		public GameOnClickListener(GameActivity mainActivity) {
			act = mainActivity ;
		}
		@Override
		public void onClick(View v) {
			Button bt = (Button)v ;
			String title = bt.getText().toString() ;
			int id = bt.getId() ;
			for(int i=0;i<9;i++){
				Button bt2 = (Button) act.findViewById(i) ;
				if(bt2.getText().toString().length()==0){
					if(i==id-1 || i==id+1 || i==id+3 || i==id-3){
						bt.setText("") ;
						bt2.setText(title) ;
					}
					break ;
				}
			}
			
			
		}

	}


}
