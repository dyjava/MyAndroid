package com.aba.main;

import com.aba.main.game.Draw2Activity;
import com.aba.main.game.DrawActivity;
import com.aba.main.game.GameActivity;
import com.aba.main.game.SuanActivity;
import com.aba.main.shudu.CitysActivity;
import com.aba.main.shudu.ShuduActivity;
import com.aba.main.webview.WebActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TableLayout layout=new TableLayout(this);  
		layout.setStretchAllColumns(true);
        layout.setOrientation(TableLayout.VERTICAL);
        
        int i =0;
        TableRow row = new TableRow(this);
        row.addView(this.createView("http://m.12580.com", i++)) ;
        row.addView(this.createView("数独", i++)) ;
        row.addView(this.createView("拼图", i++)) ;

        layout.addView(row);
        row = new TableRow(this);
        row.addView(this.createView("画图", i++)) ;
        row.addView(this.createView("画图2", i++)) ;
        row.addView(this.createView("城市", i++)) ;
        
        layout.addView(row);
        row = new TableRow(this);
        row.addView(this.createView("计算", i++)) ;
        
        layout.addView(row);

        setContentView(layout);
	}
	
	//创建按钮
	private View createView(String text, int id){
		Button view = new Button(this) ;
		
		view.setText(text);
		view.setId(id) ;
		view.setWidth(200) ;
		view.setHeight(200) ;
		view.setTextColor(Color.BLUE) ;
		view.setTextSize(24) ;
		view.setGravity(Gravity.CENTER);
		view.setBackgroundColor(Color.rgb(210, 105, 30)) ;
		view.setOnClickListener(this) ;
		return view ;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Class<?> clazz = this.getClass() ;
		switch(v.getId()){
			case 0: clazz = WebActivity.class ; break ;
			case 1: clazz = ShuduActivity.class ; break ;
			case 2: clazz = GameActivity.class ; break ;
			case 3: clazz = DrawActivity.class ; break ;
			case 4: clazz = Draw2Activity.class ; break ;
			case 5: clazz = CitysActivity.class ; break ;
			case 6: clazz = SuanActivity.class ; break ;
			
			default: clazz = SuanActivity.class ; break ;
		}
		TextView tv = (TextView) v ;
		String context = tv.getText().toString() ;
		//切换页面
		Intent intent = new Intent(this, clazz);
		
	    //用Bundle携带数据
	    Bundle bundle=new Bundle();
	    //传递name参数为tinyphp
	    bundle.putString("context", context);
	    intent.putExtras(bundle);
	    
        startActivity(intent);
	}
	
}

