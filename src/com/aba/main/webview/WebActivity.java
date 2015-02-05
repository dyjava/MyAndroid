package com.aba.main.webview;

import com.aba.main.MainActivity;
import com.aba.main.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class WebActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //设置activity的进度条，下面一句必须在setContentView之前，否则会报错。下面的语句也可以用activity.requestWindowFeature(Window.FEATURE_PROGRESS);来替代
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        
		setContentView(R.layout.activity_web);
		Bundle bundle = this.getIntent().getExtras();
        //接收url值
        String url = bundle.getString("context");

		WebView webview = (WebView) this.findViewById(R.id.webkit) ;
		//html设置
		webview.getSettings().setJavaScriptEnabled(true);
		webview.requestFocus();//触摸焦点起作用
		webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY) ;//取消滚动条
		
		
		webview.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(url);
				return true;
			}
		}) ;
//		
//		//此方法可以处理webview 在加载时和加载完成时一些操作
//		webview.setWebChromeClient(new WebChromeClient(){
//			@Override
//			public void onProgressChanged(WebView view, int newProgress) {
//				//activity的进度是0 to 10000 (both inclusive),所以要*100
//                activity.setProgress(newProgress * 100);
//                
//				TextView title = null;
//				if(newProgress==100){
//					// 这里是设置activity的标题， 也可以根据自己的需求做一些其他的操作
//					title.setText("加载完成");
//				}else{
//					title.setText("加载中.......");
//				}
////				webview.getTitle() ;
//			}
//		});

		webview.loadUrl(url) ;
	
		this.findViewById(R.id.btback).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//切换
				Intent intent = new Intent(WebActivity.this, MainActivity.class);
                startActivity(intent);
			}
			
		}) ;
	}
	

  //设置回退  
  //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法  
  public boolean onKeyDown(int keyCode, KeyEvent event) {
	  WebView webview = (WebView) this.findViewById(R.id.webkit) ;
	  if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
		  webview.goBack(); //goBack()表示返回WebView的上一页面  
		  return true;
	  }
	  return false;
  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web, menu);
		return true;
	}

}
