package com.example.vero1.c4_1_fragment_excise;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Fragment top;
    Fragment bot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        top =new top_f();
        bot=new bot_f();
        getFragmentManager().beginTransaction().add(R.id.f1,top).commit();
        getFragmentManager().beginTransaction().add(R.id.f2,bot).commit();
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(top).commit();
                getFragmentManager().beginTransaction().replace(R.id.f2, new top_f()).commit();
            }
        });
    }
}
