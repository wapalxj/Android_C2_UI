package com.example.c3_65_self_define;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.text.method.Touch;
import android.util.EventLog;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


/**
 * Created by Administrator on 2015/11/8.
 */
public class MyButton extends View{
    private OnKeyDownLister  mOnKeyDownLister;
    public interface OnKeyDownLister{
        void onKeyDown(String content);
    }
    public MyButton(Context context) {
        super(context);
    }
    public MyButton(Context context,OnKeyDownLister onKeyDownLister) {
        super(context);
        mOnKeyDownLister =onKeyDownLister;
    }
    public void setOnKeyDownLister(OnKeyDownLister onKeyDownLister){
        mOnKeyDownLister =onKeyDownLister;
    }

    //写一个让自类能重载的方法
    protected void disPlayName(){

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            if ((event.getAction()== MotionEvent.ACTION_UP)) {
                mOnKeyDownLister.onKeyDown("verooooo");
                disPlayName();
            }

        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mOnKeyDownLister.onKeyDown("verooooo");
        disPlayName();
        return super.onKeyDown(keyCode, event);
    }
}
