package com.aba.main;

import com.aba.main.game.Draw2Activity;
import com.aba.main.game.DrawActivity;
import com.aba.main.game.GameActivity;
import com.aba.main.shudu.ShuduActivity;
import com.aba.main.util.ViewOnClickListener;
import com.aba.main.webview.WebActivity;
import com.example.mytest.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.findViewById(R.id.btn01).setOnClickListener(new ViewOnClickListener(this)) ;
		this.findViewById(R.id.btn02).setOnClickListener(new ViewOnClickListener(this)) ;
		this.findViewById(R.id.btn03).setOnClickListener(new ViewOnClickListener(this)) ;
		this.findViewById(R.id.btn04).setOnClickListener(new ViewOnClickListener(this)) ;
		this.findViewById(R.id.btn05).setOnClickListener(new ViewOnClickListener(this)) ;
		this.findViewById(R.id.btn06).setOnClickListener(new ViewOnClickListener(this)) ;
		this.findViewById(R.id.btn07).setOnClickListener(new ViewOnClickListener(this)) ;
		this.findViewById(R.id.btn08).setOnClickListener(new ViewOnClickListener(this)) ;
		this.findViewById(R.id.btn09).setOnClickListener(new ViewOnClickListener(this)) ;
		
		//webview
		this.findViewById(R.id.btnurl).setOnClickListener(new ChangeOnClickListener(this, WebActivity.class)) ;
		//数独
		this.findViewById(R.id.btshu).setOnClickListener(new ChangeOnClickListener(this, ShuduActivity.class)) ;
		//数独
		this.findViewById(R.id.btgame).setOnClickListener(new ChangeOnClickListener(this, GameActivity.class)) ;
		//数独
		this.findViewById(R.id.btdraw).setOnClickListener(new ChangeOnClickListener(this, DrawActivity.class)) ;
		//数独
		this.findViewById(R.id.btdraw2).setOnClickListener(new ChangeOnClickListener(this, Draw2Activity.class)) ;
	}
	

	public void viewOnClick(View v){
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
}

