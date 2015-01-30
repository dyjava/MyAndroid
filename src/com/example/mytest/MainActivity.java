package com.example.mytest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.findViewById(R.id.btn01).setOnClickListener(new BTOnClickListener(this)) ;
		this.findViewById(R.id.btn02).setOnClickListener(new BTOnClickListener(this)) ;
		this.findViewById(R.id.btn03).setOnClickListener(new BTOnClickListener(this)) ;
		this.findViewById(R.id.btn04).setOnClickListener(new BTOnClickListener(this)) ;
		this.findViewById(R.id.btn05).setOnClickListener(new BTOnClickListener(this)) ;
		this.findViewById(R.id.btn06).setOnClickListener(new BTOnClickListener(this)) ;
		this.findViewById(R.id.btn07).setOnClickListener(new BTOnClickListener(this)) ;
		this.findViewById(R.id.btn08).setOnClickListener(new BTOnClickListener(this)) ;
		this.findViewById(R.id.btn09).setOnClickListener(new BTOnClickListener(this)) ;
		
		this.findViewById(R.id.btnurl).setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button bt = (Button)v ;
				String url = bt.getText().toString() ;
				
				//切换
				Intent intent = new Intent(MainActivity.this, WebActivity.class);

			    //用Bundle携带数据
			    Bundle bundle=new Bundle();
			    //传递name参数为tinyphp
			    bundle.putString("url", url);
			    intent.putExtras(bundle);
			    
                startActivity(intent);
			}
			
		}) ;
	}
	

	public void show(String title){
		TextView tv = (TextView)this.findViewById(R.id.btn00) ;
		String name = tv.getText().toString() ;
		if(name.trim().length()==0){
			name = title ;
		} else {
			name += "+"+title ;
		}
		tv.setText(name) ;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
}

