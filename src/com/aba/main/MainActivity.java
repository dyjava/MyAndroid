package com.aba.main;

import com.aba.main.draw.Draw2Activity;
import com.aba.main.draw.DrawActivity;
import com.aba.main.filebrowser.FileBrowserActivity;
import com.aba.main.game.GameActivity;
import com.aba.main.game.SuanActivity;
import com.aba.main.music.MusicPlayerActivity;
import com.aba.main.shudu.CitysActivity;
import com.aba.main.shudu.ShuduActivity;
import com.aba.main.webview.WebActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
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
        row.addView(this.createView("http://m.cankaoxiaoxi.com/", i++)) ;
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
        row.addView(this.createView("播放器", i++)) ;
        row.addView(this.createView("选择文件", i++)) ;
        
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
			case 7: clazz = MusicPlayerActivity.class ; break ;
			case 8: clazz = FileBrowserActivity.class ; break ;
			
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
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			dialog();
			return true;
		}
		return true;
	}
	
	protected void dialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("确定要退出吗?");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//AccoutList.this.finish();
				//System.exit(1);
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			@Override 
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
}

