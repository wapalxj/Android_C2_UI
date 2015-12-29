package com.example.administrator.c3_14_textview1_excise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

/**
 * Created by Administrator on 2015/9/20.
 */
public class CustomView extends View {
    public CustomView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //BitmapDrawable
        BitmapDrawable bitmapDrawable=(BitmapDrawable)getResources().getDrawable(R.drawable.sendbar);
        bitmapDrawable.setBounds(50, 50, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
        bitmapDrawable.draw(canvas);
        //Bitmap
//        Bitmap ori = BitmapFactory.decodeResource(this.getResources(),R.drawable.sendbar);
//        Bitmap bitmap=Bitmap.createBitmap(ori,0,0,ori.getWidth(),ori.getHeight());
//        canvas.drawBitmap(bitmap,0,0,null);
    }
}
