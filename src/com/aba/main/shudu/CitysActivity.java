package com.aba.main.shudu;

import com.aba.service.Factory;
import com.aba.service.area.AreaDao;
import com.aba.service.area.domain.AreaItem;
import com.aba.service.area.domain.AreaRequest;
import com.aba.service.area.domain.AreaResult;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
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
		//��title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ȫ��
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);    
	       
//		if (android.os.Build.VERSION.SDK_INT > 9) {
//	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//	        StrictMode.setThreadPolicy(policy);
//	    }
		dao = Factory.getInstance().getAreaDao() ;
		
		TableLayout layout=new TableLayout(this);  
		layout.setStretchAllColumns(true);
        layout.setOrientation(TableLayout.VERTICAL);
        layout.setId(0) ;
//        layout.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT, 1.0f)) ;
        
        setContentView(layout);
        this.getData("0") ;
	}

	private void getData(final String id){
		new Thread(){
    		@Override
    		public void run(){
    			//��Ҫִ�еķ���
    			//ִ����Ϻ��handler����һ������Ϣ
    			request.setId(id+"") ;
    			result = dao.findAreaByParentId(request) ;
    			
    			handler.sendEmptyMessage(0);
    		}
    	}.start();
	}
	
	//����Handler����
	private Handler handler = new Handler(){
		@Override
		//������Ϣ���ͳ�����ʱ���ִ��Handler���������
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			//����UI
			draw() ;
		}
	};
	private void draw(){
        //��ͼ
        int n= 4 ;
        TableRow row = new TableRow(this);
        TableLayout layout = (TableLayout) this.findViewById(0);
        layout.removeAllViews() ;
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

	//������ť
	private View createView(String text, int id){
		TextView view = new TextView(this) ;
		
		view.setText(text);
		view.setId(id) ;
		view.setWidth(150) ;
		view.setHeight(100) ;
		view.setTextColor(Color.BLUE) ;
		view.setTextSize(24) ;
		view.setTop(100) ;
		view.setGravity(Gravity.CENTER);
		view.setBackgroundColor(Color.rgb(210, 105, 30)) ;
		view.setOnClickListener(this) ;
		return view ;
	}
	
	@Override
	public void onClick(View v) {
		String id = v.getId()+"" ;
		this.getData(id) ;
	}
	
}
