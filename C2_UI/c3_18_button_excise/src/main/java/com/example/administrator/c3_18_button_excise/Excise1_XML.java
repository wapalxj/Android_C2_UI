package com.example.administrator.c3_18_button_excise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;

public class Excise1_XML extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excise1__xml);
        CheckedTextView checkedTextView=(CheckedTextView)findViewById(R.id.checkedTextView2);
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CheckedTextView)v).toggle();
            }
        });
    }

}
