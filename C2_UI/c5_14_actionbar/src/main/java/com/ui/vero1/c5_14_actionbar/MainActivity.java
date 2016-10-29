package com.ui.vero1.c5_14_actionbar;


import android.app.ActionBar;
import android.app.Activity;
import android.app.VoiceInteractor;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends Activity {
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//隐藏ActionBar--此MainActivity需要继承自Activity
        ActionBar actionBar =this.getActionBar();
//        actionBar.hide();

        Button btn1=(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, daohang2.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
 //searchView：
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setSubmitButtonEnabled(true);//显示子搜索按钮
        searchView.setIconifiedByDefault(true);//编辑时显示图标
        searchView.setMaxWidth(600);

        //设置监听
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit( String arg0 )
            {
                Toast.makeText(MainActivity.this, searchView.getQuery() + "", Toast.LENGTH_SHORT).show();
                return true ;
            }

            @Override
            public boolean onQueryTextChange( String arg0 )
            {
                Toast.makeText(MainActivity.this, searchView.getQuery() + "", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
//Action Provider：分享
        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider provider = (ShareActionProvider) shareItem
                .getActionProvider();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        provider.setShareIntent(intent);
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
        if (id == R.id.xxx) {
            Toast.makeText(this,"xxx",Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.dp) {
            Toast.makeText(this,"dp",Toast.LENGTH_SHORT).show();
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
