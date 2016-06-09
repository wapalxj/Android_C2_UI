package vero.com.c2_hm_07_canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button draw_line;
    private Button draw_rect;
    private Button draw_circle;
    private Button draw_arc;
    private Button draw_trangle;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        iv= (ImageView) findViewById(R.id.iv);
        draw_line= (Button) findViewById(R.id.draw_line);
        draw_rect= (Button) findViewById(R.id.draw_rect);
        draw_circle= (Button) findViewById(R.id.draw_circle);
        draw_arc= (Button) findViewById(R.id.draw_arc);
        draw_trangle= (Button) findViewById(R.id.draw_trangle);
        draw_line.setOnClickListener(this);
        draw_rect.setOnClickListener(this);
        draw_circle.setOnClickListener(this);
        draw_arc.setOnClickListener(this);
        draw_trangle.setOnClickListener(this);
    }

    private void draw_trangle() {
        //画纸
        Bitmap bitmap=Bitmap.createBitmap(320,320, Bitmap.Config.ARGB_8888);
        //画布
        Canvas canvas=new Canvas(bitmap);
        //画笔
        Paint paint=new Paint();
        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);//设置抗锯齿
        //绘画
        Path path=new Path();
        float x1=160,y1=20;
        float x2=140,y2=200;
        float x3=180,y3=200;

        path.moveTo(x1,y1);
        path.lineTo(x2,y2);
        path.arcTo(new RectF(140,180,180,220),0,180);
        path.lineTo(x3,y3);
        path.lineTo(x1,y1);
        path.close();
        canvas.drawPath(path,paint);

        iv.setImageBitmap(bitmap);
    }

    private void draw_arc() {
        //画纸
        Bitmap bitmap=Bitmap.createBitmap(320,320, Bitmap.Config.ARGB_8888);
        //画布
        Canvas canvas=new Canvas(bitmap);
        //画笔
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);//设置抗锯齿

        //1.矩形
        RectF oval =new RectF(20,20,200,200);
        float startAngle=0;//起始角度
        float sweepAngle=120;//扫过角度
        boolean useCenter=false;//是否画中心，false:表示只画弧线，true:画出扇形
        //绘画
        canvas.drawArc(oval,startAngle,sweepAngle,useCenter,paint);
        iv.setImageBitmap(bitmap);
    }

    private void draw_circle() {
        //画纸
        Bitmap bitmap=Bitmap.createBitmap(320,320, Bitmap.Config.ARGB_8888);
        //画布
        Canvas canvas=new Canvas(bitmap);
        //画笔
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);//设置抗锯齿
        float cX=160,cY=160,radius=100;//圆心坐标和半径
        //绘画
        canvas.drawCircle(cX,cY,radius,paint);

        iv.setImageBitmap(bitmap);
    }

    private void draw_rect() {
        //画纸
        Bitmap bitmap=Bitmap.createBitmap(320,320, Bitmap.Config.ARGB_8888);
        //画布
        Canvas canvas=new Canvas(bitmap);
        //画笔
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);//边框
        paint.setStrokeWidth(10);//粗细
        //绘画
        canvas.drawRect(20,20,200,200,paint);

        iv.setImageBitmap(bitmap);
    }

    private void draw_line(){
        //画纸
        Bitmap bitmap=Bitmap.createBitmap(320,320, Bitmap.Config.ARGB_8888);
        //画布
        Canvas canvas=new Canvas(bitmap);
        //画笔
        Paint paint=new Paint();
        //绘画
        canvas.drawLine(10,10,200,200,paint);

        iv.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.draw_line:
                draw_line();
                break;
            case R.id.draw_rect:
                draw_rect();
                break;
            case R.id.draw_circle:
                draw_circle();
                break;
            case R.id.draw_arc:
                draw_arc();
                break;
            case R.id.draw_trangle:
                draw_trangle();
                break;
            default:
                break;
        }
    }



}
