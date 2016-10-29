package com.ui.vero1.c5_12_gesturedetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    private  LinearLayout linearLayout;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout=(LinearLayout)findViewById(R.id.line);
        linearLayout.setOnTouchListener(this);
        gestureDetector =new GestureDetector(this,new MyGesture());
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
       gestureDetector.onTouchEvent(event);
        return true;
    }

    class MyGesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("ges","onSingleTapUp---up事件");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("ges","onLongPress---长按:手指按在持续一段时间，并且没有松开");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("ges","onScroll--- 滚动:手指在触摸屏上滑动");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("ges","onFling----抛掷:手指在触摸屏上迅速移动，并松开的动作");
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i("ges","onShowPress---按住:手指按在触摸屏上，时间范围在按下起效，在长按之前。");
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i("ges","onDown---down事件");
            return super.onDown(e);
        }

    }



}
