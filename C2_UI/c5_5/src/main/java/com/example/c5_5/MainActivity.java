package com.example.c5_5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private final int MIN_IMAGE=48;
    private ImageView imageView;
    private TextView textView1;
    private SeekBar seekBar1;
    private TextView textView2;
    private SeekBar seekBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.image);
        textView1=(TextView)findViewById(R.id.textView1);
        seekBar1 =(SeekBar)findViewById(R.id.seekbar1);
        textView2=(TextView)findViewById(R.id.textView2);
        seekBar2 =(SeekBar)findViewById(R.id.seekbar2);

        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);

        //获取屏幕尺寸
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        seekBar1.setMax(displayMetrics.widthPixels-MIN_IMAGE);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekbar1:
                int newWidth=progress+MIN_IMAGE;
                int newHeight=progress+MIN_IMAGE;
                imageView.setLayoutParams(new LinearLayout.LayoutParams(newWidth,newHeight));
                textView1.setText("宽度："+newWidth+"，高度："+newHeight);
                break;
            case R.id.seekbar2:
                Bitmap bitmap =((BitmapDrawable) ContextCompat.getDrawable(this,R.drawable.image)).getBitmap();
                Matrix matrix=new Matrix();
                matrix.setRotate(progress);
                bitmap=Bitmap.createBitmap(bitmap,0,0, bitmap.getWidth(),bitmap.getHeight(),matrix,true);
                imageView.setImageBitmap(bitmap);
                textView2.setText("旋转："+progress);
                break;
            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

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


}
