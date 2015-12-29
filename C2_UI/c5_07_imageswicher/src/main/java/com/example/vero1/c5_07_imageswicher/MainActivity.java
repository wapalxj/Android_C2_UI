package com.example.vero1.c5_07_imageswicher;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory,View.OnClickListener{
    private ImageSwitcher imageSwitcher;
    private LinearLayout linearLayout;
    private ImageView imageView;
//    private int drawable[]={
//            R.drawable.i1,
//            R.drawable.i2,
//            R.drawable.i3,
//            R.drawable.i4,
//            R.drawable.i5,
//    };
//    private int small[]={
//            R.drawable.small,
//            R.drawable.small,
//            R.drawable.small,
//            R.drawable.small,
//            R.drawable.small,
//    };
// 原图
private Integer[] mImages = {
        R.drawable.android1, R.drawable.android2, R.drawable.android3, R.drawable.android4
        , R.drawable.android5, R.drawable.android6, R.drawable.android7, R.drawable.android8
        , R.drawable.android9, R.drawable.android10, R.drawable.android11, R.drawable.android12
        , R.drawable.android13, R.drawable.android14, R.drawable.android15
};
    // 缩略图
    private Integer[] mSmallImages = {
            R.drawable.android_1, R.drawable.android_2, R.drawable.android_3, R.drawable.android_4,
            R.drawable.android_5, R.drawable.android_6, R.drawable.android_7, R.drawable.android_8,
            R.drawable.android_9, R.drawable.android_10, R.drawable.android_11,
            R.drawable.android_12,
            R.drawable.android_13, R.drawable.android_14, R.drawable.android_15
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout=(LinearLayout)findViewById(R.id.line);
        imageSwitcher=(ImageSwitcher)findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(this);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        imageSwitcher.setImageResource(mImages[0]);
//        linearLayout.setLayoutParams(new ActionBar.LayoutParams());
        for (int i=0;i<mSmallImages.length;i++){
            imageView =new ImageView(this);
            imageView.setImageResource(mSmallImages[i]);
            imageView.setId(i);
            imageView.setOnClickListener(this);
            linearLayout.addView(imageView);
        }


    }
    @Override
    public void onClick(View v) {
        imageSwitcher.setImageResource(mImages[v.getId()]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View makeView() {
        return new ImageView(this);
    }


}
