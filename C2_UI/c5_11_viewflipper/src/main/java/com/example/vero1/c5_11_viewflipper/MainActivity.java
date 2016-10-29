package com.example.vero1.c5_11_viewflipper;

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
    private float mTouchDownX;
    private float mTouchUpX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipper=(ViewFlipper)findViewById(R.id.flipper);
        //自动翻页
//        flipper.setAutoStart(true);
//        flipper.setFlipInterval(2000);
        flipper.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTouchDownX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mTouchUpX= event.getX();
            //down<<<up:从左向右划
            if (mTouchUpX-mTouchDownX>100){
                flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.right_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.right_out));
                flipper.showPrevious();
            }else
            //down>>>>>up:从右向左划
            if (mTouchDownX-mTouchUpX>100){
                flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.left_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.left_out));
                flipper.showNext();
            }
            return true;
        }
        return false;
    }


}
