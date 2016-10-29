package com.example.c310_ui_drawable2_xml;

import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        Resources res=getResources();
        LinearLayout _linearLayout =new LinearLayout(this);
        ImageView _imageView =new ImageView(this);
        TransitionDrawable drawable =(TransitionDrawable)res.getDrawable(R.drawable.expands);
        _imageView.setAdjustViewBounds(true);
        _imageView.setImageDrawable(drawable);
        _imageView.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT,Gallery.LayoutParams.MATCH_PARENT));
        _linearLayout.addView(_imageView);
        setContentView(_linearLayout);
    }

}
