package com.example.c3_39_tabhostandtabwidget;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabHost tabHost1=getTabHost();
        tabHost1.setBackgroundColor(Color.GRAY);
        TabHost.TabSpec tabSpec = tabHost1.newTabSpec("tab1")
                .setIndicator("", getResources().getDrawable(android.R.drawable.ic_dialog_email))
                .setContent(new Intent(MainActivity.this, listdemo.class));
        TabHost.TabSpec tabSpec2=tabHost1.newTabSpec("tab2")
                .setIndicator("", getResources().getDrawable(android.R.drawable.ic_lock_idle_lock))
                .setContent(new Intent(MainActivity.this, List2.class));

        tabHost1.addTab(tabSpec);
        tabHost1.addTab(tabSpec2);

//        tabHost1.getTabWidget().setBackgroundResource(R.drawable.tabwidget_color);//spec默认背景颜色//这行也可以在xml中设置background
        tabHost1.getCurrentTabView().setBackgroundResource(R.drawable.tabwidget_color);//spec设置默认选中的颜色
        tabHost1.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                tabHost1.getCurrentTabView().setBackgroundResource(R.drawable.tabwidget_color);
//                setTabColor(tabHost1);
            }
        });

    }
    //动态设置sepc背景颜色
    public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++){
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#AAAAAA"));
        }
        tabhost.getCurrentTabView().setBackgroundColor(Color.parseColor("#ff383737"));
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
