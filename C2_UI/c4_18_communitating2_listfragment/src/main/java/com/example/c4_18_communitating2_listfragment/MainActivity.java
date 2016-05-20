package com.example.c4_18_communitating2_listfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Edit_Fragment.AddItem{
    List_Fragment  fragment;
    ArrayAdapter arrayAdapter;
    ArrayList<String> data;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment=(List_Fragment )getFragmentManager().findFragmentById(android.R.id.list);
        arrayAdapter =(ArrayAdapter)fragment.getAdapter();
        data =(ArrayList)fragment.getData();
        listView=fragment.getListView();
    }

    @Override
    public void add(String item) {
        data.add(item);
        arrayAdapter.notifyDataSetChanged();
        listView.setSelection(data.size());
//        Toast.makeText(MainActivity.this, "" + listView, Toast.LENGTH_SHORT).show();
    }

}
