package com.aba.main.music;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aba.main.R;
import com.aba.main.filebrowser.FileBrowserActivity;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MusicPlayerActivity extends Activity {
	
    /* ����������ť */
    private ImageButton mFrontImageButton   = null;
    private ImageButton mStopImageButton    = null;
    private ImageButton mStartImageButton   = null;
    private ImageButton mPauseImageButton   = null;
    private ImageButton mNextImageButton    = null;
    
    /* MediaPlayer���� */
    public MediaPlayer  mMediaPlayer        = null;
    
    /* �����б� */
    private List<String> mMusicList = new ArrayList<String>();
    
    /* ��ǰ���Ÿ��������� */
    private int currentListItme = 0;
    
    /* ���ֵ�·�� */
    private String MUSIC_PATH = new String("/storage/sdcard0/Music/"); // /storage/sdcard0/music
    public static final int FILE_RESULT_CODE = 1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_musicplayer);
    	/* ������ʾ�����б� */
    	musicList();
    	/* ����MediaPlayer���� */
    	mMediaPlayer        = new MediaPlayer();
    	mFrontImageButton = (ImageButton) findViewById(R.id.LastImageButton);
        mStopImageButton = (ImageButton) findViewById(R.id.StopImageButton);
        mStartImageButton = (ImageButton) findViewById(R.id.StartImageButton);
        mPauseImageButton = (ImageButton) findViewById(R.id.PauseImageButton);
        mNextImageButton = (ImageButton) findViewById(R.id.NextImageButton);
        
        //ֹͣ��ť  
        mStopImageButton.setOnClickListener(new ImageButton.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		/* �Ƿ����ڲ��� */
        		if (mMediaPlayer.isPlaying()){
        			//����MediaPlayer����ʼ״̬ 
        			mMediaPlayer.reset();
        		}
        	}
        });
        
        //��ʼ��ť 
        mStartImageButton.setOnClickListener(new ImageButton.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
        	}
        });
        
        //��ͣ   
        mPauseImageButton.setOnClickListener(new ImageButton.OnClickListener(){
        	public void onClick(View view){
        		if (mMediaPlayer.isPlaying()){
        			/* ��ͣ */
        			mMediaPlayer.pause();
        		} else {
        			/* ��ʼ���� */
        			mMediaPlayer.start();
        		}
        	}
        });
        
        //��һ��   
        mNextImageButton.setOnClickListener(new ImageButton.OnClickListener(){
        	@Override 
        	public void onClick(View arg0){
        		nextMusic();
        	}
        });
        
        //��һ�� 
        mFrontImageButton.setOnClickListener(new ImageButton.OnClickListener(){
        	@Override
        	public void onClick(View arg0){
        		FrontMusic();
        	}
        });
        
        //ѡ���ļ��а�ť
        findViewById(R.id.selectfile).setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent(MusicPlayerActivity.this,FileBrowserActivity.class);
        		startActivityForResult(intent, FILE_RESULT_CODE);
        	}
        }) ;
    }
    
    /*<----------------------------------------------------------------->*/
    public Music getMusic(int position){
    	Music music = new Music() ;
    	try {
        	String path = MUSIC_PATH + mMusicList.get(position) ;
        	MediaPlayer media = new MediaPlayer();
			media.setDataSource(path) ;
			
			music.setPath(mMusicList.get(position)) ;
			music.setName(media.getCurrentPosition()+"") ;
			music.setTime(media.getDuration()/60+"") ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return music ;
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event){
    	if ( keyCode ==  KeyEvent.KEYCODE_BACK){
    		mMediaPlayer.stop();
    		mMediaPlayer.release();
    		this.finish();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    	
    }
    
    /* �����ǵ���б�ʱ�����ű���������� */
    protected void onListItemClick(ListView l, View v, int position, long id){
    	currentListItme = position;
    	playMusic(MUSIC_PATH + mMusicList.get(position));
    }
    
    /* �����б� */
    public void musicList() {
    	//ȡ��ָ��λ�õ��ļ�������ʾ�������б� 
    	File home = new File(MUSIC_PATH);
    	if (home.listFiles(new MusicFilter()).length > 0) {
    		for (File file : home.listFiles(new MusicFilter())) {
    			mMusicList.add(file.getName());
    		}
//    		ArrayAdapter<String> musicList = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, mMusicList);
    		BaseListAdapter musicList = new BaseListAdapter(this, mMusicList) ;
    		ListView list = (ListView)this.findViewById(R.id.myArrayList) ;
    		list.setAdapter(musicList) ;
    	}
    }
    

	private void playMusic(String path){
    	try{
    		/* ����MediaPlayer */ 
    		mMediaPlayer.reset();
    		/* ����Ҫ���ŵ��ļ���·�� */
    		mMediaPlayer.setDataSource(path);
    		/* ׼������ */
    		mMediaPlayer.prepare();
    		/* ��ʼ���� */
    		mMediaPlayer.start();
    		mMediaPlayer.setOnCompletionListener(new OnCompletionListener(){
    			public void onCompletion(MediaPlayer arg0){
    				//�������һ��֮�������һ��   
    				nextMusic();
    			}
    		});
    	}catch (IOException e){}
    }
    
    /* ��һ�� */ 
    private void nextMusic() {
    	if (++currentListItme >= mMusicList.size()){
    		currentListItme = 0;
    	} else {
    		playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
    	}
    }
    
    /* ��һ�� */
    private void FrontMusic() {
    	if (--currentListItme >= 0) {
    		currentListItme = mMusicList.size();
    	} else {
    		playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
    	}
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(FILE_RESULT_CODE == requestCode){
    		Bundle bundle = null;
    		if(data!=null&&(bundle=data.getExtras())!=null){
    			MUSIC_PATH = bundle.getString("savePath") ;
    			((TextView)findViewById(R.id.path)).setText(MUSIC_PATH) ;
//    			textView.setText("ѡ���ļ���Ϊ��"+bundle.getString("file"));
    			this.recreate() ;
    		}
    	}
    }
}

/* �����ļ����� */
class MusicFilter implements FilenameFilter{
	public boolean accept(File dir, String name) {
		//���ﻹ��������������ʽ�������ļ�  
		return (name.endsWith(".mp3"));
	}
}
