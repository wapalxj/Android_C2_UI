package com.example.c35_customui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;

/**
 * Created by Administrator on 2015/8/15.
 */
public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
        setFocusable(true);//设置可以作为焦点
        setFocusableInTouchMode(true);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        canvas.drawRoundRect(10, 10, 250, 250,20,20,paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("hellovnix",25,50,paint);
    }

//    ����onKeyUp�ص�����(��Ҫ���ý����ȡ)
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_1){//按1键
            Log.i("vnix","vero-onKeyUp");
        }
        return super.onKeyUp(keyCode, event);
    }
//        ����onTouchEvent�ص�����(��Ҫ���ý����ȡ)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if(event.getAction()==MotionEvent.ACTION_UP){
//            Log.i("vnix","vero-onTouch--UP!!");
//        }else if(event.getAction()==MotionEvent.ACTION_DOWN){
//            Log.i("vnix","vero-onTouch--DOWN!!");
//        }
        if(event.getAction()==MotionEvent.ACTION_UP){
            Log.i("vnix","vero-onTouch--UP!!");
            if(event.getEventTime()-event.getDownTime()>=3000){//模拟 long clicked
                Log.i("vnix","you pressed 3 s !!");
            }
        }else if(event.getAction()==MotionEvent.ACTION_DOWN){
            Log.i("vnix","vero-onTouch--DOWN!!");
        }

        return super.onTouchEvent(event);
    }
}
