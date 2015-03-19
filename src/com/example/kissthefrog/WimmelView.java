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
    											R.drawable.part2 };

    public WimmelView(Context context) {
        super(context);
        _paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        _rnd = new Random(_randomSeed);
        for (int img : _images) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), img);
            for (int i = 0; i < 10; i++) {
                float left = (float) (_rnd.nextFloat() * (getWidth()  - bmp.getWidth()));
                float top  = (float) (_rnd.nextFloat() * (getHeight() - bmp.getHeight()));
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
