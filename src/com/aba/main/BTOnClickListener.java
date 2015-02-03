package com.aba.main;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BTOnClickListener implements OnClickListener {

	MainActivity act ;
	public BTOnClickListener(MainActivity mainActivity) {
		act = mainActivity ;
	}
	@Override
	public void onClick(View v) {
		String title = ((Button)v).getText().toString() ;
		act.show(title) ;
	}

}
