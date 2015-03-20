package com.example.kissthefrog;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class WimmelView extends View {

    private                 Random  _rnd;
    private                 Paint   _paint = new Paint();
    private                 long    _randomSeed = 1;
    private                 int     _imageCount;

    private static  final   int[]   _images = { R.drawable.part1, 
    											R.drawable.part2,
    											R.drawable.part3,
    											R.drawable.part4,
    											R.drawable.part5 };

    public WimmelView(Context context) {
        super(context);
        _paint.setAntiAlias(true);
    }

    /**
     * @param size should either be getWidth() or getHeight() - bmp size
     */
    private float randPos(int size) {
    	return (float) (_rnd.nextFloat() * size);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        _rnd = new Random(_randomSeed);
        for (int img : _images) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), img);
            for (int i = 0; i < _imageCount / _images.length; i++) {
                float left = randPos(getWidth()  - bmp.getWidth());
                float top  = randPos(getHeight() - bmp.getHeight());
                canvas.drawBitmap(bmp, left, top, _paint);
            }
            bmp.recycle();
        }
    }

    public void setImageCount(int imagecount) {
        _imageCount = imagecount;
        _randomSeed = System.currentTimeMillis();
        invalidate();
    }
}
