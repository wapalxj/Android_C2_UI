package com.example.c310_ui_drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

/**
 * Created by Administrator on 2015/8/21.
 *  BitmapDrawable
 */
public class DrawableView extends View {
    BitmapDrawable mbitmapDrawable;
    public DrawableView(Context context) {
        super(context);
        mbitmapDrawable=( BitmapDrawable)getResources().getDrawable(R.drawable.cap);
        mbitmapDrawable.setBounds(100,100,350,560);//drawable中必须要被调用的方法
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mbitmapDrawable.draw(canvas);
    }
}
