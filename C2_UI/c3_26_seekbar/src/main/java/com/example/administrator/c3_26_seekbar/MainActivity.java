package com.example.administrator.c3_26_seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.QuickContactBadge;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSeekBar();
        showQuickContactBadge();
        showRatingBar();
    }
    public void showRatingBar(){
        RatingBar ratingBar=(RatingBar)findViewById(R.id.ratingBar2);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this, "rating" + rating, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void showSeekBar(){
        SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar3);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("vnixProgressChange","pro:"+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i("vnixStart", "pro:" + seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("vnixStop", "pro:" + seekBar.getProgress());
            }
        });
    }
    public void showQuickContactBadge(){
        QuickContactBadge qcb=(QuickContactBadge)findViewById(R.id.quickContactBadge);
        qcb.assignContactFromPhone("18468050252", true);
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
