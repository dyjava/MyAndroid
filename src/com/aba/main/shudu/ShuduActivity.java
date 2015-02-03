package com.aba.main.shudu;

import com.example.mytest.R;
import com.example.mytest.R.layout;
import com.example.mytest.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShuduActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shudu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shudu, menu);
		return true;
	}

}
