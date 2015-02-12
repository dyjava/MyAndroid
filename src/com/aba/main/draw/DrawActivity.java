package com.aba.main.draw;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import com.aba.main.BaseActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DrawActivity extends BaseActivity implements OnTouchListener,OnClickListener{

    private ImageView image;
    private Paint paint;
    private Canvas canvas;
    private Bitmap bitmap;
    private Bitmap alterBitmap;
    private Button choose;
    private Button save;
    private final static int RESULT = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        image = (ImageView) findViewById(R.id.image);
//        choose = (Button) findViewById(R.id.chooseButton);
//        save=(Button)findViewById(R.id.saveButton);
        /*
         * bitmap = Bitmap.createBitmap(getWindowManager().getDefaultDisplay()
         * .getWidth(), getWindowManager().getDefaultDisplay().getHeight(),
         * Bitmap.Config.ARGB_8888); canvas = new Canvas(bitmap);// »­°å paint =
         * new Paint();// »­Ë¢ paint.setColor(Color.BLUE);
         * image.setImageBitmap(bitmap);
         */
        image = new ImageView(this) ;
        choose = new Button(this) ;
        choose.setText("Ñ¡Ôñ") ;
        save = new Button(this) ;
        save.setText("±£´æ") ;
        
        image.setOnTouchListener(this);
        choose.setOnClickListener(this);
        save.setOnClickListener(this);
        
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(choose) ;
        layout.addView(image) ;
        layout.addView(save) ;
        setContentView(layout);
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
	            image.invalidate();
	            downx = upx;
	            downy = upy;
	            break;
	        case MotionEvent.ACTION_UP:
	            // Ö±Ïß»­°å
	            upx = event.getX();
	            upy = event.getY();
	            canvas.drawLine(downx, downy, upx, upy, paint);
	            image.invalidate();// Ë¢ÐÂ
	            break;
	        default:
	            break;
        }

        return true;
    }

    public void onClick(View arg0) {
        if(arg0==choose){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT);
        }else if(arg0==save){
            //±£´æ»­ºÃµÄÍ¼Æ¬
            if(alterBitmap!=null){
                try {
                    Uri imageUri=getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());
                    OutputStream outputStream=getContentResolver().openOutputStream(imageUri);
                    alterBitmap.compress(CompressFormat.PNG, 90, outputStream);
                    Toast.makeText(getApplicationContext(), "save!", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri imageFileUri = data.getData();
            Display display = getWindowManager().getDefaultDisplay();
            float dw = display.getWidth();
            float dh = display.getHeight();
//            DisplayMetrics metric = new DisplayMetrics();
//            getWindowManager().getDefaultDisplay().getMetrics(metric);
//            int dw = metric.widthPixels;     // ÆÁÄ»¿í¶È£¨ÏñËØ£©
//            int dh = metric.heightPixels;   // ÆÁÄ»¸ß¶È£¨ÏñËØ£©

            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(imageFileUri), null, options);
                int heightRatio = (int) Math.ceil(options.outHeight / dh);
                int widthRatio = (int) Math.ceil(options.outWidth / dw);
                if (heightRatio > 1 && widthRatio > 1) {
                    if (heightRatio > widthRatio) {
                        options.inSampleSize = heightRatio;
                    } else {
                        options.inSampleSize = widthRatio;
                    }
                }
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(imageFileUri), null, options);
                alterBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                        bitmap.getHeight(), bitmap.getConfig());
                canvas = new Canvas(alterBitmap);
                paint = new Paint();
                paint.setColor(Color.RED);
                paint.setStrokeWidth(10);
                Matrix matrix = new Matrix();
                canvas.drawBitmap(bitmap, matrix, paint);
                image.setImageBitmap(alterBitmap);
                image.setOnTouchListener(this);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
