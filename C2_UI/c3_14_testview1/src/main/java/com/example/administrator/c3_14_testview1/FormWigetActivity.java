package com.example.administrator.c3_14_testview1;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.XMLReader;

public class FormWigetActivity extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_wiget);
        showTextView();
        Button button= (Button)findViewById(R.id.pmd);
        i=new Intent(FormWigetActivity.this,paoMaDeng.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }
    private void showTextView(){
        TextView textView1=(TextView)findViewById(R.id.textview1);
/*       textView1.append(Html.fromHtml("<br/>html:<br/><b>vero</b><p>vnix</p><p></p><a href=\"http://www.baidu.com\">baidu</a>"));
//html设置图片

        Html.ImageGetter imgGetter=new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {//图片获取
                if (source!=null) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.water);
                    bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
                    return bitmapDrawable;
                }else {
                    return  null;
                }
            }
        };
        Html.TagHandler tagHandler = new Html.TagHandler() {//标签解析
            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
                Toast.makeText(FormWigetActivity.this,tag, Toast.LENGTH_SHORT).show();
            }
        };
        textView1.append(Html.fromHtml("<p>vnix1111111<img src=\"\"/></p>",imgGetter,tagHandler));//src必须要设置，可以空
    */
//span将图片插入到文字之间
        BitmapDrawable sp=(BitmapDrawable)getResources().getDrawable(R.drawable.water);
        sp.setBounds(0,0,70,85);

        SpannableStringBuilder ssb=new SpannableStringBuilder("hello  i am vnix!");
        ImageSpan is=new ImageSpan(sp);
        ClickableSpan clickableSpan =new ClickableSpan() {//可点击的span
            @Override
            public void onClick(View widget) {//事件
                Toast.makeText(FormWigetActivity.this,"click",Toast.LENGTH_SHORT).show();
            }
        };
        ssb.setSpan(is,5,6,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(clickableSpan,0,5,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView1.setText(ssb);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form_wiget, menu);
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
