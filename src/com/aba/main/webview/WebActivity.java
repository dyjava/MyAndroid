package com.aba.main.webview;

import com.aba.main.MainActivity;
import com.example.mytest.R;
import com.example.mytest.R.id;
import com.example.mytest.R.layout;
import com.example.mytest.R.menu;

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
        //����activity�Ľ�����������һ�������setContentView֮ǰ������ᱨ����������Ҳ������activity.requestWindowFeature(Window.FEATURE_PROGRESS);�����
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//���ر�����
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//����״̬��
        
		setContentView(R.layout.activity_web);
		Bundle bundle = this.getIntent().getExtras();
        //����urlֵ
        String url = bundle.getString("context");

		WebView webview = (WebView) this.findViewById(R.id.webkit) ;
		//html����
		webview.getSettings().setJavaScriptEnabled(true);
		webview.requestFocus();//��������������
		webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY) ;//ȡ��������
		
		
		webview.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//��д�˷������������ҳ��������ӻ����ڵ�ǰ��webview����ת��������������Ǳ�
				view.loadUrl(url);
				return true;
			}
		}) ;
//		
//		//�˷������Դ���webview �ڼ���ʱ�ͼ������ʱһЩ����
//		webview.setWebChromeClient(new WebChromeClient(){
//			@Override
//			public void onProgressChanged(WebView view, int newProgress) {
//				//activity�Ľ�����0 to 10000 (both inclusive),����Ҫ*100
//                activity.setProgress(newProgress * 100);
//                
//				TextView title = null;
//				if(newProgress==100){
//					// ����������activity�ı��⣬ Ҳ���Ը����Լ���������һЩ�����Ĳ���
//					title.setText("�������");
//				}else{
//					title.setText("������.......");
//				}
////				webview.getTitle() ;
//			}
//		});

		webview.loadUrl(url) ;
	
		this.findViewById(R.id.btback).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//�л�
				Intent intent = new Intent(WebActivity.this, MainActivity.class);
                startActivity(intent);
			}
			
		}) ;
	}
	

  //���û���  
  //����Activity���onKeyDown(int keyCoder,KeyEvent event)����  
  public boolean onKeyDown(int keyCode, KeyEvent event) {
	  WebView webview = (WebView) this.findViewById(R.id.webkit) ;
	  if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
		  webview.goBack(); //goBack()��ʾ����WebView����һҳ��  
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
