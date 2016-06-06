package vero.com.c3_self_05_slidemenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SlideMenu slideMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slideMenu= (SlideMenu) findViewById(R.id.slideMenu);
    }

    public void clickTab(View view){
        Toast.makeText(MainActivity.this,"点击了："+((TextView)view).getText(),Toast.LENGTH_SHORT).show();
        slideMenu.toggle();
    }

    public void clickBack(View view){
        slideMenu.toggle();
    }
}
