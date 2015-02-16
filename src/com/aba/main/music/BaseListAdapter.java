package com.aba.main.music;

import java.util.List;

import com.aba.main.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseListAdapter extends BaseAdapter implements OnClickListener {

        private MusicPlayerActivity act;
        private LayoutInflater inflater;
        private List<String> mMusicList ;
        public BaseListAdapter(MusicPlayerActivity mContext, List<String> mMusicList) {
            this.act = mContext;
            inflater = LayoutInflater.from(mContext);
            this.mMusicList = mMusicList ;
        }
        
        @Override
        public int getCount() {
            return mMusicList.size();
        }

        @Override
        public Object getItem(int position) {
            return mMusicList.get(position) ;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if(convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.activity_music_item, null);
                viewHolder.img = (ImageView) convertView.findViewById(R.id.img);
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.info = (TextView) convertView.findViewById(R.id.info);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            
            Music music = act.getMusic(position) ;
//            viewHolder.img.setBackgroundResource(R.id.img);
            viewHolder.title.setText(music.getPath());
            viewHolder.info.setText(music.getTime());
            viewHolder.img.setId(position) ;
            viewHolder.img.setOnClickListener(this);
            
            return convertView;
        }
        
        class ViewHolder {
            ImageView img;
            TextView title;
            TextView info;
            Button button;
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            act.onListItemClick(null, v, id, id) ;
        }

}
