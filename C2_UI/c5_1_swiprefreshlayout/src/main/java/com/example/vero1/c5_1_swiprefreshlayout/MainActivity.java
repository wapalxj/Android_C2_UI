package com.example.vero1.c5_1_swiprefreshlayout;

import android.os.Handler;
import android.preference.PreferenceActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout =(SwipeRefreshLayout)findViewById(R.id.ref);
//        swipeRefreshLayout.setOnRefreshListener(this);
        textView =(TextView)findViewById(R.id.textView);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    //2秒钟以后：
                    public void run() {
                        //结束动画
                        swipeRefreshLayout.setRefreshing(false);
                        int i=(int)(Math.random()*100+1);
                        textView.setText(String.valueOf(i));
                    }
                },2000);
            }
        });
    }

//    @Override
//    public void onRefresh() {
//        //开始动画
//        swipeRefreshLayout.setRefreshing(true);
//        (new Handler()).postDelayed(new Runnable() {
//            @Override
//            //2秒钟以后：
//            public void run() {
//                //结束动画
//                swipeRefreshLayout.setRefreshing(false);
//                int i=(int)(Math.random()*100+1);
//               textView.setText(String.valueOf(i));
//            }
//        },2000);
//    }

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
