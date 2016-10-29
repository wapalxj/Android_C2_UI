package com.example.administrator.c3_21_adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSpinner();
    }
    public void showSpinner(){
        final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        String prompt=getResources().getString(R.string.spinner);
        spinner.setPrompt(prompt);//需要设置spinnerMode
        //1.建立数据源
        String [] mString=getResources().getStringArray(R.array.vnixspinner);
        //2,3.建立Adapter和source
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,mString);
        //4绑定
        spinner.setAdapter(adapter);
        //事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,((TextView)view).toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,id+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
