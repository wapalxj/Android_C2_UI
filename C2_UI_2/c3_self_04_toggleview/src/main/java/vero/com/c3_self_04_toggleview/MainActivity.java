package vero.com.c3_self_04_toggleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SwitchToggleView stv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stv= (SwitchToggleView) findViewById(R.id.stv);
        stv.setSwitchBackground(R.drawable.switch_background);
        stv.setSwitchSlide(R.drawable.slide_button_background);

        stv.setOnSwitchListener(new SwitchToggleView.OnSwitchListener() {
            @Override
            public void onSwitchChanged(boolean isOpened) {
                Toast.makeText(MainActivity.this,"isOpened："+isOpened,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
