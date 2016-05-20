package ui2.com.c9_an50_01_toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    public void initView(){
        mToolBar= (Toolbar) findViewById(R.id.main_toolbar);
        //将toolbar当做ActionBar
        setSupportActionBar(mToolBar);
    }

}
