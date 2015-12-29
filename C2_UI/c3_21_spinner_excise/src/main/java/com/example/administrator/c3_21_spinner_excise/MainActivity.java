package com.example.administrator.c3_21_spinner_excise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Spinner;

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
