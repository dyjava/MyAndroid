package com.aba.main.shudu;

import com.aba.main.R;
import com.aba.service.Factory;
import com.aba.service.area.AreaDao;
import com.aba.service.area.domain.AreaItem;
import com.aba.service.area.domain.AreaRequest;
import com.aba.service.area.domain.AreaResult;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CitysActivity extends Activity implements OnClickListener{

	private AreaDao dao ;
	private AreaResult result = new AreaResult() ;
	private AreaRequest request = new AreaRequest() ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		if (android.os.Build.VERSION.SDK_INT > 9) {
//	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//	        StrictMode.setThreadPolicy(policy);
//	    }
		dao = Factory.getInstance().getAreaDao() ;
		
		TableLayout layout=new TableLayout(this);  
		layout.setStretchAllColumns(true);
        layout.setOrientation(TableLayout.VERTICAL);
        layout.setId(0) ;
        layout.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT, 1.0f)) ;
        
        TableRow row1 = new TableRow(this);
        row1.addView(this.createView("text",100)) ;
        layout.addView(row1);
        
        setContentView(layout);

    	new Thread(){
    		@Override
    		public void run(){
    			//你要执行的方法
    			//执行完毕后给handler发送一个空消息
    			request.setLeve("1") ;
    			result = dao.findAreaByParentId(request) ;
    			
    			handler.sendEmptyMessage(0);
    		}
    	}.start();
    	
	}

	//定义Handler对象
	private Handler handler = new Handler(){
		@Override
		//当有消息发送出来的时候就执行Handler的这个方法
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			//处理UI
			draw() ;
		}
	};
	private void draw(){
        //绘图
        int n= 3 ;
        TableRow row = new TableRow(this);
        TableLayout layout = (TableLayout) this.findViewById(0);
		for(AreaItem item:result.getMatches()){
        	row.addView(this.createView(item.getAlias(),item.getAreaid() )) ;
        	
        	if(row.getChildCount()==n){
        		layout .addView(row);
        		row = new TableRow(this);
        	}
        }

    	if(row.getChildCount()>0){
    		layout.addView(row);
    	}
	}

	//创建按钮
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		TextView view = (TextView) v ;
		final String id = v.getId()+"";
    	new Thread(){
    		@Override
    		public void run(){
    			request.setId(id) ;
    			result = dao.findAreaById(request) ;
    			
    			handler.sendEmptyMessage(0);
    		}
    	}.start();
	}
	
}
