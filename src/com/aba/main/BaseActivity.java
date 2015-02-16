package com.aba.main;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BaseActivity extends Activity {
	protected File storedir ;

	public BaseActivity(){
		//项目存储数据目录
		storedir = Environment.getDataDirectory() ;
		storedir = Environment.getExternalStorageDirectory() ;
		this.storedir = new File("/storage/sdcard0/","dyong") ;
		if(!this.storedir.exists()){
			this.storedir.mkdirs() ;
		}
	}
	public void viewOnClick(View v){
		
	}

	class ChangeOnClickListener implements OnClickListener{
		private BaseActivity act ;
		private Class<?> clazz ;
		
		public ChangeOnClickListener(BaseActivity act, Class<?> clazz){
			this.act = act ;
			this.clazz = clazz ;
		}
		@Override
		public void onClick(View v) {
			TextView tv = (TextView) v ;
			String context = tv.getText().toString() ;
			
			//切换页面
			Intent intent = new Intent(act, clazz);
			
		    //用Bundle携带数据
		    Bundle bundle=new Bundle();
		    //传递name参数为tinyphp
		    bundle.putString("context", context);
		    intent.putExtras(bundle);
		    
	        startActivity(intent);
		}
	}
}
