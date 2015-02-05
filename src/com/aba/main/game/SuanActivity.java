package com.aba.main.game;


import com.aba.main.BaseActivity;
import com.aba.main.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SuanActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jisuan);

		this.findViewById(R.id.btn01).setOnClickListener(this) ;
		this.findViewById(R.id.btn02).setOnClickListener(this) ;
		this.findViewById(R.id.btn03).setOnClickListener(this) ;
		this.findViewById(R.id.btn04).setOnClickListener(this) ;
		this.findViewById(R.id.btn05).setOnClickListener(this) ;
		this.findViewById(R.id.btn06).setOnClickListener(this) ;
		this.findViewById(R.id.btn07).setOnClickListener(this) ;
		this.findViewById(R.id.btn08).setOnClickListener(this) ;
		this.findViewById(R.id.btn09).setOnClickListener(this) ;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shudu, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Button bt = (Button) v;
		String title = bt .getText().toString() ;
		TextView tv = (TextView)this.findViewById(R.id.btn00) ;
		String name = tv.getText().toString() ;
		if(name.trim().length()==0){
			name = title ;
		} else {
			name += "+"+title ;
		}
		tv.setText(name) ;
	}

}
