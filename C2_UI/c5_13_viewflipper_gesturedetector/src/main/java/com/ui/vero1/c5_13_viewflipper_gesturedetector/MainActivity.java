package com.ui.vero1.c5_13_viewflipper_gesturedetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    private ViewFlipper flipper;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipper=(ViewFlipper)findViewById(R.id.flipper);
        //自动翻页
//        flipper.setAutoStart(true);
//        flipper.setFlipInterval(2000);
        flipper.setOnTouchListener(this);

        gestureDetector =new GestureDetector(this,new MyGesture());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    class MyGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e("5_13_ges", "onSingleTapUp---up事件");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.e("5_13_ges", "onLongPress---长按:手指按在持续一段时间，并且没有松开");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e("5_13_ges", "onScroll--- 滚动:手指在触摸屏上滑动");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("5_13_ges", "onFling----抛掷:手指在触摸屏上迅速移动，并松开的动作");
            if (e2.getX()-e1.getX()>=100&& Math.abs(velocityX)>0){
                //down<<<up:从左向右划
                Log.e("5_13_ges", "onFling----从左向右划");
                flipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_out));
                flipper.showPrevious();
            }else {
                //down>>>>>up:从右向左划
                Log.e("5_13_ges", "onFling----从右向左划");
                flipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_out));
                flipper.showNext();
            }
            return false;
        }
        @Override
        public void onShowPress(MotionEvent e) {
            Log.e("5_13_ges","onShowPress---按住:手指按在触摸屏上，时间范围在按下起效，在长按之前。");
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.e("5_13_ges","onDown---down事件");
            return super.onDown(e);
        }

    }

}
