package com.example.c3_33_listview3_cursor;

import android.database.Cursor;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayCursor();
    }

    public void displayCursor(){
        Cursor cursor =getContentResolver().query(Contacts.Phones.CONTENT_URI,null,null,null,null);
        SimpleCursorAdapter simpleCursorAdapter= new SimpleCursorAdapter(this,R.layout.my_adapter,cursor,
                                                    new String []{Contacts.Phones.NAME, Contacts.Phones.NUMBER},
                                                    new int[]{R.id.textView,R.id.textView2});

        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(simpleCursorAdapter);

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
