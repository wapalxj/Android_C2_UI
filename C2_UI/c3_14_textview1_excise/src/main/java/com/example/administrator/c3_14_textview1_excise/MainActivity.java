package com.example.administrator.c3_14_textview1_excise;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 设置图片的N种方法
 */
//读取drawable
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        loadSpan();
//        loadCompound();
//        loadCustomeView();
        loadHtmlDrawable();
//        loadWebHtmlDrawable();
//        loadHtmlLocal();
//        loadOndraw();
    }
    public void loadOndraw(){
        TextView textView=new TextView(this){
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                BitmapDrawable bitmapDrawable=(BitmapDrawable)getResources().getDrawable(R.drawable.yun);
                bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
                bitmapDrawable.draw(canvas);
            }
        };
        textView.setWidth(1000);
        textView.setHeight(500);
        RelativeLayout r=new RelativeLayout(this);

    //方法1：需要设置layout的id
        r=(RelativeLayout) findViewById(R.id.main);
        r.addView(textView);
        //下面2行(设置margin)必须放在addView之后
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)textView.getLayoutParams();
        params.setMargins(0, 50, 0, 0);
    //  setContentView(r);//方法2：或者这种不需要设置ID
    }
    public void loadHtmlLocal(){
        TextView textView=(TextView)findViewById(R.id.tv1);
        textView.append(Html.fromHtml("<br/><p>hello I am from My PC!</p>"));

        Html.ImageGetter imageGetter=new Html.ImageGetter() {//drawable设置

            @Override
            public Drawable getDrawable(String source) {
                System.out.println(source);
                BitmapDrawable bitmapDrawable=(BitmapDrawable)Drawable.createFromPath("res/drawable/fbone.png");

                bitmapDrawable.setBounds(0, 0, 70, 85);
                return bitmapDrawable;
            }
        };
        textView.append(Html.fromHtml("<img src=\"file:///F:/myworks/me.jpg\"/>", imageGetter, null));//注意此行的引号
    }
    public void loadHtmlDrawable(){
        TextView textView=(TextView)findViewById(R.id.tv1);
        textView.append(Html.fromHtml("<br/><p>hello I am from HTML!</p>"));
        //不设置source,则可以将<img>的 src设置为空

//        Html.ImageGetter imageGetter=new Html.ImageGetter() {//drawable设置
//            @Override
//            public Drawable getDrawable(String source) {
//
//                BitmapDrawable bitmapDrawable=(BitmapDrawable)getResources().getDrawable(R.drawable.smalldp);
//                bitmapDrawable.setBounds(0, 0, 70, 85);
//                return bitmapDrawable;
//            }
//        };
//        Html.TagHandler tagHandler=new Html.TagHandler() {//标签解析
//            @Override
//            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
//                Toast.makeText(MainActivity.this,tag,Toast.LENGTH_SHORT).show();
//            }
//        };
//        textView.append(Html.fromHtml("<img src=''/><br>end", imageGetter, tagHandler));


     //设置source:则要将img src设为正确的地址
        Html.ImageGetter imageGetter=new Html.ImageGetter() {//drawable设置
            @Override
            public Drawable getDrawable(String source) {
                int id=Integer.parseInt(source);
                BitmapDrawable bitmapDrawable=(BitmapDrawable)getResources().getDrawable(id);
                bitmapDrawable.setBounds(0, 0, (int)(bitmapDrawable.getIntrinsicWidth()*0.5), (int)(bitmapDrawable.getIntrinsicWidth()*0.5));
                return bitmapDrawable;
            }
        };
        textView.append(Html.fromHtml("<img src='"+R.drawable.yun+"'/>", imageGetter, null));//注意此行的引号
    }
    public void loadWebHtmlDrawable(){
        TextView textView =(TextView)findViewById(R.id.tv1);
        textView.append(Html.fromHtml("I am loading a img from Web:"));
        Html.ImageGetter imageGetter=new Html.ImageGetter() {//drawable设置
            @Override
            public Drawable getDrawable(String source) {
                URL url;
                BitmapDrawable bitmapDrawable;
                try {
                    url = new URL(source);
                     bitmapDrawable= (BitmapDrawable)Drawable.createFromStream(url.openStream(),"");
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

                bitmapDrawable.setBounds(0, 0, (int)(bitmapDrawable.getIntrinsicWidth()*0.2), (int)(bitmapDrawable.getIntrinsicHeight()*0.2));
                return bitmapDrawable;
            }
        };
        textView.append(Html.fromHtml("<br/><img src=\"http://pic004.cnblogs.com/news/201211/20121108_091749_1.jpg\"/>", imageGetter, null));//注意此行的引号
    }
    public void loadCustomeView(){
        CustomView customView=new CustomView(this);
        RelativeLayout r=new RelativeLayout(this);
        r.addView(customView);
    }
    public  void loadCompound(){
        TextView textView =(TextView)findViewById(R.id.tv1);

        BitmapDrawable left=(BitmapDrawable)getResources().getDrawable(R.drawable.smalldp);
        left.setBounds(0,0,(int)(left.getIntrinsicWidth()*0.3),(int)(left.getIntrinsicHeight()*0.3));

        BitmapDrawable top=(BitmapDrawable)getResources().getDrawable(R.drawable.fbone);
        top.setBounds(0,0,(int)(top.getIntrinsicWidth()*0.3),(int)(top.getIntrinsicHeight()*0.3));

        BitmapDrawable right=(BitmapDrawable)getResources().getDrawable(R.drawable.yun);
        right.setBounds(0,0,(int)(right.getIntrinsicWidth()*0.3),(int)(right.getIntrinsicHeight()*0.3));

        BitmapDrawable bottom=(BitmapDrawable)getResources().getDrawable(R.drawable.pin);
        bottom.setBounds(0,0,(int)(bottom.getIntrinsicWidth()*0.1),(int)(bottom.getIntrinsicHeight()*0.1));

        textView.setCompoundDrawables(left, top, right, bottom);
    }
    public void loadSpan(){
        TextView textView=(TextView)findViewById(R.id.tv1);
        textView.append(Html.fromHtml("<p>vnix</p><br/>"));

        Drawable drawable=getResources().getDrawable(R.drawable.water);
        drawable.setBounds(0,0,(int)(drawable.getIntrinsicWidth()*0.3),(int)(drawable.getIntrinsicHeight()*0.3));
        //将图片span插入到text中
        ImageSpan imageSpan =new ImageSpan(drawable);
        SpannableStringBuilder ssb=new SpannableStringBuilder("hello  i am vnix!");//必须设置此字符串，下面的插入位置针对此字符串
        ssb.setSpan(imageSpan,4,5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //为span文本设置可点击
        ClickableSpan clickableSpan =new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this,"vnix!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }
        };
        ssb.setSpan(clickableSpan,4,15,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.append(ssb);
    }

}
