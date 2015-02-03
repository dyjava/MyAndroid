package com.aba.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BaseActivity extends Activity {

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
		
		//�л�ҳ��
		Intent intent = new Intent(act, clazz);
		
	    //��BundleЯ������
	    Bundle bundle=new Bundle();
	    //����name����Ϊtinyphp
	    bundle.putString("context", context);
	    intent.putExtras(bundle);
	    
        startActivity(intent);
	}
}
}
