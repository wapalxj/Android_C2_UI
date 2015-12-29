package com.example.c3_55_dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        String [] data=new String []{"vero","customerLayout-dialog","customerLayout-alertDialog"};
        ArrayAdapter adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.select_dialog_item,data);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
        if (position==0){
            on0();
        }else  if (position==1){
            on1();
        }else {
            on2();
        }
        super.onListItemClick(l, v, position, id);
    }

    public void on0(){//使用默认的AlertDialog
        AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                .setTitle("vvvvv")
                .setIcon(R.drawable.btncir2)
                .setMessage("do you wanna exit?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//退出Activity
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭dialog,或者：
//                        dialog.cancel();
                    }
                })
                .show();
    }
    public void on1(){//使用自定义Dialog
        Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(android.R.layout.select_dialog_singlechoice);//使用自定义Dialog
//            dialog.findViewById();//自定义layout可以使用此方法
        dialog.setTitle("自定义Dialog");
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public void on2(){//使用自定义AlertDialog
        AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                .setView(R.layout.support_simple_spinner_dropdown_item)//自定义AlertDialog
                .setTitle("自定义AlertDialog")
                .setIcon(R.drawable.btncir1)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//退出Activity
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();//关闭dialog,或者：
                        dialog.cancel();
                    }
                })
                .show();
//             alertDialog.findViewById();//自定义layout可以使用此方法
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
