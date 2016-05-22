package com.c2_07_dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button dia_confirm;
    private Button dia_single_check;
    private Button dia_multi;
    private Button dia_progress;
    private Button dia_progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        this.dia_confirm = (Button) findViewById(R.id.dia_confirm);
        this.dia_single_check = (Button) findViewById(R.id.dia_single_check);
        this.dia_multi = (Button) findViewById(R.id.dia_multi);
        this.dia_progress = (Button) findViewById(R.id.dia_progress);
        this.dia_progressBar = (Button) findViewById(R.id.dia_progressBar);
        dia_confirm.setOnClickListener(this);
        dia_single_check.setOnClickListener(this);
        dia_multi.setOnClickListener(this);
        dia_progress.setOnClickListener(this);
        dia_progressBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dia_confirm:
                dia_confirm();
                break;
            case R.id.dia_single_check:
                dia_single_check();
                break;
            case R.id.dia_multi:
                dia_multi();
                break;
            case R.id.dia_progress:
                dia_progress();
                break;
            case R.id.dia_progressBar:
                dia_progressBar();
                break;
            default:
                break;
        }
    }

    public void dia_confirm(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("哈哈哈")
                .setMessage("有选择吗？")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"yes",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"no",Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    public void dia_single_check(){
        final String [] items={"vero","vnix","vae"};
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("单选")
                //-1:checkedItem:默认选中项
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"checked---->"+items[which],Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    public void dia_multi(){
        final String [] items={"vero","vnix","vae"};
        final boolean [] checkedItems={false,false,false};
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("多选")
                //checkedItems:对应项是否默认选中
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked){
                            Toast.makeText(MainActivity.this,"checked---->"+items[which],Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this,"unChecked---->"+items[which],Toast.LENGTH_SHORT).show();
                        }
                        checkedItems[which]=isChecked;
                    }
                })
                .show();
    }

    public void dia_progress(){
        ProgressDialog dialog=new ProgressDialog(MainActivity.this);
        dialog.setTitle("拼命加载中");
        dialog.setMessage("拼命加载中");
        dialog.show();
    }

    public void dia_progressBar(){
        ProgressDialog dialog=new ProgressDialog(MainActivity.this);
        dialog.setTitle("拼命加载中");
        dialog.setMessage("拼命加载中");
        dialog.setMax(100);
        dialog.show();
        for (int i=0;i<100;i++){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dialog.setProgress(i);
            if (dialog.getProgress()>=100){
                dialog.dismiss();
            }
        }

    }
}
