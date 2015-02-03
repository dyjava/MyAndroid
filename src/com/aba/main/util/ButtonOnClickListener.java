package com.aba.main.util;


import com.aba.main.BaseActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//°´Å¥¼àÌý
public class ButtonOnClickListener implements OnClickListener {

	BaseActivity act ;
	public ButtonOnClickListener(BaseActivity act) {
		this.act = act ;
	}
	@Override
	public void onClick(View v) {
		Button bt = (Button)v ;
		act.btOnClick(bt) ;
	}

}
