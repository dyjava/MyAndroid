package com.aba.main.shudu;

import com.aba.main.R;
import com.aba.service.Factory;
import com.aba.service.area.AreaDao;
import com.aba.service.area.domain.AreaItem;
import com.aba.service.area.domain.AreaRequest;
import com.aba.service.area.domain.AreaResult;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CitysActivity extends Activity implements OnClickListener{

	private AreaDao dao ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dao = Factory.getInstance().getAreaDao() ;
		AreaRequest request = new AreaRequest() ;
		request.setLeve("1") ;
		AreaResult result = dao.findAreaByParentId(request) ;
		
		TableLayout layout=new TableLayout(this);  
		layout.setStretchAllColumns(true);
        layout.setOrientation(TableLayout.VERTICAL);
        TableRow row1 = new TableRow(this);
        row1.addView(this.createView("text",100)) ;
        layout.addView(row1);
        
        //»æÍ¼
        int n= 4 ;
        int c =0 ;
        TableRow row = new TableRow(this);
        for(AreaItem item:result.getMatches()){
        	row.addView(this.createView(item.getAlias(),c++)) ;
        	
        	if(row.getChildCount()==n){
        		layout.addView(row);
        		row = new TableRow(this);
        	}
        }

    	if(row.getChildCount()>0){
    		layout.addView(row);
    	}
        setContentView(layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shudu, menu);
		return true;
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
		view.setOnClickListener(this) ;
		return view ;
	}
	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
