package com.aba.main.game;

import com.aba.main.BaseActivity;
import com.aba.main.R;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Draw2Activity extends BaseActivity implements OnTouchListener{

    private ImageView image;
    private Paint paint;
    private Canvas canvas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw2);
        
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // ÆÁÄ»¿í¶È£¨ÏñËØ£©
        int height = metric.heightPixels;   // ÆÁÄ»¸ß¶È£¨ÏñËØ£©
//        float density = metric.density;      // ÆÁÄ»ÃÜ¶È£¨0.75 / 1.0 / 1.5£©
//        int densityDpi = metric.densityDpi;  // ÆÁÄ»ÃÜ¶ÈDPI£¨120 / 160 / 240£©

        Bitmap newb1 = Bitmap.createBitmap( width, height, Config.ARGB_8888 );
        canvas = new Canvas(newb1);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        
        image = (ImageView) this.findViewById(R.id.imagedraw2) ;
        image.setBackgroundColor(Color.YELLOW) ;
        image.setOnTouchListener(this);
//        image.setMaxWidth(width) ;
//        image.setMaxHeight(height) ;
        
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(image) ;
    }

    private float downx = 0;
    private float downy = 0;
    private float upx = 0;
    private float upy = 0;
    public boolean onTouch(View v, MotionEvent event) {
    	int action = event.getAction();
    	switch (action) {
	    	case MotionEvent.ACTION_DOWN:
	            downx = event.getX();
	            downy = event.getY();
	            break;
	        case MotionEvent.ACTION_MOVE:
	            // Â·¾¶»­°å
	            upx = event.getX();
	            upy = event.getY();
	            canvas.drawLine(downx, downy, upx, upy, paint);
	            //image.invalidate();
	            downx = upx;
	            downy = upy;
	            break;
	        case MotionEvent.ACTION_UP:
	            // Ö±Ïß»­°å
	            upx = event.getX();
	            upy = event.getY();
	            canvas.drawLine(downx, downy, upx, upy, paint);
	            //image.invalidate();// Ë¢ÐÂ
	            break;
	        default:
	            break;
        }

        return true;
    }

}
