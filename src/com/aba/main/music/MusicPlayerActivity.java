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
	
    /* MediaPlayer对象 */
    public MediaPlayer  mMediaPlayer = null;
    
    /* 播放列表 */
    private List<String> mMusicList = new ArrayList<String>();
    
    /* 当前播放歌曲的索引 */
    private int currentListItme = 0;
    
    /* 音乐的路径 */
    private String MUSIC_PATH = new String("/storage/sdcard0/Music/"); // /storage/sdcard0/music
    public static final int FILE_RESULT_CODE = 1;
    /** 
     * 
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_musicplayer);
    	
    	mMusicList = new ArrayList<String>();
    	
    	/* 更新显示播放列表 */
    	musicList();
    	
    	/* 构建MediaPlayer对象 */
    	mMediaPlayer = new MediaPlayer();
        
        //停止按钮  
        ((ImageButton) findViewById(R.id.StopImageButton)).setOnClickListener(new ImageButton.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		/* 是否正在播放 */
        		if (mMediaPlayer.isPlaying()){
        			//重置MediaPlayer到初始状态 
        			mMediaPlayer.reset();
        		}
        	}
        });
        
        //开始按钮 
        ((ImageButton) findViewById(R.id.StartImageButton)).setOnClickListener(new ImageButton.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
        	}
        });
        
        //暂停   
        ((ImageButton) findViewById(R.id.PauseImageButton)).setOnClickListener(new ImageButton.OnClickListener(){
        	public void onClick(View view){
        		if (mMediaPlayer.isPlaying()){
        			/* 暂停 */
        			mMediaPlayer.pause();
        		} else {
        			/* 开始播放 */
        			mMediaPlayer.start();
        		}
        	}
        });
        
        //下一首   
        ((ImageButton) findViewById(R.id.NextImageButton)).setOnClickListener(new ImageButton.OnClickListener(){
        	@Override 
        	public void onClick(View arg0){
        		nextMusic();
        	}
        });
        
        //上一首 
        ((ImageButton) findViewById(R.id.LastImageButton)).setOnClickListener(new ImageButton.OnClickListener(){
        	@Override
        	public void onClick(View arg0){
        		FrontMusic();
        	}
        });
        
        //选择文件夹按钮
        findViewById(R.id.selectfile).setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent(MusicPlayerActivity.this,FileBrowserActivity.class);
        		startActivityForResult(intent, FILE_RESULT_CODE);
        	}
        }) ;
        //文件路径
        ((TextView)findViewById(R.id.path)).setText(MUSIC_PATH) ;
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
    
    /* 当我们点击列表时，播放被点击的音乐 */
    protected void onListItemClick(ListView l, View v, int position, long id){
    	currentListItme = position;
    	playMusic(MUSIC_PATH + mMusicList.get(position));
    }
    
    /* 播放列表 */
    public void musicList() {
    	//取得指定位置的文件设置显示到播放列表 
    	File home = new File(MUSIC_PATH);
    	if (home.listFiles(new MusicFilter()).length > 0) {
    		for (File file : home.listFiles(new MusicFilter())) {
    			mMusicList.add(file.getName());
    		}
//    		ArrayAdapter<String> musicList = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, mMusicList);
    		BaseListAdapter musicList = new BaseListAdapter(this, mMusicList) ;
    		((ListView)this.findViewById(R.id.myArrayList)).setAdapter(musicList) ;
    	}
    }
    

	private void playMusic(String path){
    	try{
    		/* 重置MediaPlayer */ 
    		mMediaPlayer.reset();
    		/* 设置要播放的文件的路径 */
    		mMediaPlayer.setDataSource(path);
    		/* 准备播放 */
    		mMediaPlayer.prepare();
    		/* 开始播放 */
    		mMediaPlayer.start();
    		mMediaPlayer.setOnCompletionListener(new OnCompletionListener(){
    			public void onCompletion(MediaPlayer arg0){
    				//播放完成一首之后进行下一首   
    				nextMusic();
    			}
    		});
    	}catch (IOException e){}
    }
    
    /* 下一首 */ 
    private void nextMusic() {
    	if (++currentListItme >= mMusicList.size()){
    		currentListItme = 0;
    	} else {
    		playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
    	}
    }
    
    /* 上一首 */
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
//    			textView.setText("选择文件夹为："+bundle.getString("file"));
//    			MusicPlayerActivity.this.recreate() ;
//    			Intent intent = new Intent(MusicPlayerActivity.this, MusicPlayerActivity.class);
//    			startActivity(intent);
    			this.onCreate(null) ;
    		}
    	}
    }
}

/* 过滤文件类型 */
class MusicFilter implements FilenameFilter{
	public boolean accept(File dir, String name) {
		//这里还可以设置其他格式的音乐文件  
		return (name.endsWith(".mp3"));
	}
}
