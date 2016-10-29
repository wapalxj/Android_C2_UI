package com.example.administrator.c3_21_spinner_excise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        String prompt=getResources().getString(R.string.spinner);
        spinner.setPrompt(prompt);//需要设置spinnerMode
        //1.数据源
        List<User> list=new ArrayList<>();
        list.add(new User("vero",20));
        list.add(new User("vnix",25));
        list.add(new User("Alicc",20));
        //2.建立adapter,source

        MyAdapter<User> myAdapter=new MyAdapter<>(this,list,R.layout.my_adapter);

        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,id+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
