package com.aba.main.util;


import com.aba.main.BaseActivity;

import android.view.View;
import android.view.View.OnClickListener;

//°´Å¥¼àÌý
public class ViewOnClickListener implements OnClickListener {

	BaseActivity act ;
	public ViewOnClickListener(BaseActivity act) {
		this.act = act ;
	}
	@Override
	public void onClick(View v) {
		act.viewOnClick(v) ;
	}

}
