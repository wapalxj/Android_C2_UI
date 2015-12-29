package com.ui.vero1.c5_14_actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;

import java.lang.reflect.Method;

public class daohang2 extends Activity {
    private ActionBar actionBar;
    private String[] arry_list;
    //下拉
    private ActionBar.OnNavigationListener mOnNavigationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daohang2);
        actionBar =this.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //ActionBar+Tab
        initView();
        //下拉列表导航
//        initViewlist();
    }
    //tab
    private void initView() {
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // 添加Tab选项
        ActionBar.Tab tab = actionBar
                .newTab()
                .setText("p1")
                .setTabListener(
                        new TabListener<Fragment1>(this, "p1",
                                Fragment1.class));
        actionBar.addTab(tab);

        tab = actionBar
                .newTab()
                .setText("p2")
                .setTabListener(
                        new TabListener<Fragment2>(this, "p12",
                                Fragment2.class));
        actionBar.addTab(tab);
        tab = actionBar
                .newTab()
                .setText("p3")
                .setTabListener(
                        new TabListener<Fragment3>(this, "p3",
                                Fragment3.class));
        actionBar.addTab(tab);
    }

    private void initViewlist(){
        // //导航模式必须设为NAVIGATION_MODE_LIST
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // 定义一个下拉列表数据适配器
        arry_list = getResources().getStringArray(R.array.action_list);
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.action_list,
                android.R.layout.simple_spinner_dropdown_item);


        mOnNavigationListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int position, long itemId) {
                Fragment newFragment = null;
                switch (position) {
                    case 0:
                        newFragment = new Fragment1();
                        break;
                    case 1:
                        newFragment = new Fragment2();
                        break;
                    case 2:
                        newFragment = new Fragment3();
                        break;
                    default:
                        break;
                }
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, newFragment).commit();
                return true;
            }
        };
        actionBar.setListNavigationCallbacks(mSpinnerAdapter,
                mOnNavigationListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daohang2, menu);
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
        if (id == android.R.id.home) {
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //让menu的图标显示
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
