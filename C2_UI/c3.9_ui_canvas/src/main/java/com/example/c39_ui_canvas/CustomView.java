package com.example.c39_ui_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Administrator on 2015/8/21.
 */
public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint =new Paint();
//        canvas.clipRect(20,20,280,270);//矩形裁剪
        canvas.rotate(45f);//旋转

//        canvas.save();//保存上面的状态,可用restore读取

        canvas.scale(2, 2);//缩放
//        draw background
        canvas.drawColor(Color.YELLOW);
//        draw red-rect
        paint.setColor(Color.RED);
        canvas.drawRect(10, 10, 200, 200, paint);

//        canvas.restore();//读取保存的canvas状态，于是下面的绘制不会再缩放

//        draw string
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("hellovnix", 100, 50, paint);
//        draeOval
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);//�趨�����
        RectF rectF=new RectF(20,20,220,120);
        canvas.drawOval(rectF, paint);
//      drawRoundRect
        paint.setColor(Color.GRAY);
        rectF=new RectF(20,50,250,120);
        canvas.drawRoundRect(rectF,40,60,paint);



    }
}
