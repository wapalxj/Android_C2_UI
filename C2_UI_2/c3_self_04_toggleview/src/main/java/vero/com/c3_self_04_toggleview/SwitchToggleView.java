package vero.com.c3_self_04_toggleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by vero on 2016/6/3.
 */
public class SwitchToggleView  extends View{
    private final static int STATE_NONE=0;
    private final static int STATE_DOWN=1;
    private final static int STATE_MOVE=2;
    private final static int STATE_UP=3;
    private Bitmap switchBackground;
    private Bitmap switchSlide;
    private boolean isOpened=true;
    private int state=STATE_NONE;//标记状态
    private Paint paint=new Paint();
    private float curX;


    public SwitchToggleView(Context context) {
        this(context,null);
    }

    public SwitchToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 背景設置
     */

    public void setSwitchBackground(int redId){
        switchBackground = BitmapFactory.decodeResource(getResources(),redId);

    }

    public void setSwitchSlide(int redId){
        switchSlide= BitmapFactory.decodeResource(getResources(),redId);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (switchBackground!=null){
            int width=switchBackground.getWidth();
            int height=switchBackground.getHeight();
            setMeasuredDimension(width,height);//view调用这个方法
        }else {
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //绘制背景的显示
        if (switchBackground!=null){
            int left=0;
            int top=0;
            canvas.drawBitmap(switchBackground,left,top,paint);
        }

        if (switchSlide==null){
            return;
        }

        int slideWidth=switchSlide.getWidth();//记录滑块的宽度
        int switchWidth = switchBackground.getWidth();
        switch (state){
            case STATE_DOWN:
            case STATE_MOVE:
                if (!isOpened){
                    //关闭状态
                    if (curX<slideWidth/2f){
                        //点击滑块左侧，滑块不动
                        canvas.drawBitmap(switchSlide,0,0,paint);
                    }else {
                        //点击滑块的右侧，滑块中线和按的X坐标对齐
                        float left=curX-slideWidth/2f;
                        //设置最大的left
                        int maxLeft=switchWidth-slideWidth;
                        if (left>maxLeft){
                            left=maxLeft;
                        }
                        canvas.drawBitmap(switchSlide,left,0,paint);
                    }
                }else {
                    //打开状态
                    float middle=switchWidth-slideWidth / 2f;
                    if (curX>middle){
                        canvas.drawBitmap(switchSlide,switchWidth-slideWidth,0,paint);
                    }else {
                        float left=curX-slideWidth/2f;
                        if (left<0){
                            left=0;
                        }
                        canvas.drawBitmap(switchSlide,left,0,paint);
                    }
                }
                break;
            case STATE_UP:
            case STATE_NONE:
                //绘制滑块:空状态
                if (!isOpened){
                    canvas.drawBitmap(switchSlide,0,0,paint);
                }else {
                    canvas.drawBitmap(switchSlide,switchWidth-slideWidth,0,paint);
                }
                break;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //如果滑块是关闭的
                state=STATE_DOWN;
                curX = event.getX();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                state=STATE_MOVE;
                curX = event.getX();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                state=STATE_UP;
                curX = event.getX();
                //判断状态改变
                int switchWidth = switchBackground.getWidth();
                if (switchWidth / 2f >curX && isOpened){
                    //关闭
                    isOpened=false;
                    //回调
                    if (mListener!=null){
                        mListener.onSwitchChanged(false);
                    }

                }else if (switchWidth / 2f <=curX && !isOpened){
                    //打开
                    isOpened=true;
                    //回调
                    if (mListener!=null){
                        mListener.onSwitchChanged(true);
                    }
                }
                invalidate();
                break;

            default:
                break;
        }
        return true;//消费touch
    }

    /**
     * 接口回调
     * @param listener
     */

    private OnSwitchListener mListener;
    public interface  OnSwitchListener{
        void onSwitchChanged(boolean isOpened);
    }
    public void setOnSwitchListener(OnSwitchListener listener ){
        mListener=listener;
    }
}
