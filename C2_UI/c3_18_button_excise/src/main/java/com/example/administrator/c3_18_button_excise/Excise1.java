package com.example.administrator.c3_18_button_excise;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Excise1 extends AppCompatActivity {
    ToggleButton toggleButton;
    CheckBox checkBox;
    RadioButton radioButton;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excise1);
        showButton();
        showToggleButton();
        showCheckBox();
        showRadioButton();
        showCheckedTextView();

        Button start=(Button)findViewById(R.id.start);
        i=new Intent(Excise1.this,Excise1_XML.class);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

    }
    public void showCheckedTextView(){
        CheckedTextView checkedTextView =(CheckedTextView)findViewById(R.id.checkedTextView);
        checkedTextView.setChecked(true);
//        checkedTextView.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView checkedTextView1=( CheckedTextView)v;
                checkedTextView1.toggle();
            }
        });
    }
    public void showRadioButton(){
        radioButton=(RadioButton)findViewById(R.id.radioButton);
        radioButton.setText("请选择我");
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    radioButton.setText("你选中了我");
//                    radioButton.setButtonDrawable(R.drawable.yun);//改变RadioButton的button样式
                }else if (!isChecked){
                    radioButton.setText("请选择我");
//                    radioButton.setButtonDrawable(R.drawable.yun);//改变RadioButton的button样式
                }
            }
        });
    }
    public void showCheckBox(){//通过代码动态CheckBox的文本,背景,button
        checkBox =(CheckBox)findViewById(R.id.checkBox);
        checkBox.setChecked(false);
        checkBox.setText("请选择我!");
        checkBox.setBackgroundResource(R.drawable.btno);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkBox.setText("你选中了!");
                    checkBox.setBackgroundResource(R.drawable.btnb);
                }else if (!isChecked){
                    checkBox.setText("请选择我!");
                    checkBox.setBackgroundColor(Color.GRAY);
                    checkBox.setBackgroundResource(R.drawable.btno);
//                    checkBox.setButtonDrawable(R.drawable.yun);//改变checkBox的button样式
                }
            }
        });
    }
    public void showToggleButton(){//通过代码动态toggleBtn的文本和背景
        toggleButton =(ToggleButton)findViewById(R.id.toggleButton);
        toggleButton.setFocusable(true);
        toggleButton.setChecked(false);
        toggleButton.setBackgroundResource(R.drawable.btnb);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    toggleButton.setTextOn("vero-ON");
                    toggleButton.setBackgroundResource(R.drawable.btnb);
                }else if(!isChecked){
                    toggleButton.setTextOff("vero-OFF");
                    toggleButton.setBackgroundResource(R.drawable.btno);
                }
            }
        });
    }
    public void showButton(){//通过代码动态修改button背景
        Button btn=(Button)findViewById(R.id.button);
        btn.setBackgroundResource(R.drawable.btng);
        btn.setFocusable(true);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Button button=(Button) v;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    button.setBackgroundResource(R.drawable.btng);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    button.setBackgroundResource(R.drawable.btno);
                }
                if (event.getEventTime()-event.getDownTime()>=2000){//模拟long
                    Toast.makeText(Excise1.this,"你长按了！",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_excise1, menu);
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
