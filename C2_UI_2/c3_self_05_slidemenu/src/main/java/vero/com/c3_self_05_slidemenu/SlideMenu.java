package vero.com.c3_self_05_slidemenu;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by vero on 2016/6/4.
 */
public class SlideMenu extends ViewGroup{

    private View mLeftView;
    private View mContentView;
    private int mLeftWidth;
    private int mLeftHeight;
    private float downX;
    private float downY;

    private Scroller mScroller;

    private boolean isLeftShow=false;

    public SlideMenu(Context context) {
        this(context,null);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller =new Scroller(context);

    }

    /**
     * 加载完成XML布局后调用的
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLeftView=getChildAt(0);
        mContentView=getChildAt(1);
        //获取布局时候的参数
        LayoutParams params=mLeftView.getLayoutParams();
        mLeftWidth = params.width;
        mLeftHeight = params.height;
    }


    /**
     * 测量
     * @param widthMeasureSpec:父控件传过来的：期望值：默认为父控件数值
     * @param heightMeasureSpec：父类传过来的：期望值：默认为父控件数值
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int mode=MeasureSpec.getMode(widthMeasureSpec);//获得mode
//        int size=MeasureSpec.getSize(widthMeasureSpec);//获得后30位值
//        MeasureSpec.makeMeasureSpec(200,MeasureSpec.EXACTLY);//组装mode+数值

        //設置子控件
        //左側：高度
        int leftWidthMeasureSpec=MeasureSpec.makeMeasureSpec(mLeftWidth,MeasureSpec.EXACTLY);
        mLeftView.measure(leftWidthMeasureSpec,heightMeasureSpec);

        //右侧:和父容器一样
        mContentView.measure(widthMeasureSpec,heightMeasureSpec);

        //設置自己
        int parentWidth=MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentWidth,parentHeight);
//                super.onMeasure(leftWidthMeasureSpec, rightHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //左側佈局
        int width=mLeftView.getMeasuredWidth();
        int height=mLeftView.getMeasuredHeight();
        int left=-width;
        int top=0;
        int right=0;
        int bottom=height;
        mLeftView.layout(left,top,right,bottom);
        mContentView.layout(0,0,mContentView.getMeasuredWidth(),mContentView.getMeasuredHeight());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //拦截
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX=ev.getX();
                downY=ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX=ev.getX();
                float moveY=ev.getY();
                if (Math.abs(moveX-downX)>Math.abs(moveY-downY)){
                    //水平方向的移动
                    return true;//拦截事件
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX=event.getX();
                float moveY=event.getY();

                int diffX=(int)( downX-moveX+0.5f);//差值,+0.5f:四舍五入
                int scrollX=getScrollX()+diffX;//getScrollX():当前屏幕的X值
                if (scrollX<0 && scrollX<=-mLeftView.getMeasuredWidth()){
                    //从左往右
                    scrollTo(-mLeftView.getMeasuredWidth(),0);
                }else if (scrollX>0){
                    //从右往左
                    scrollTo(0,0);
                }else {
                    scrollBy(diffX,0);
                }
                downX=moveX;
                downY=moveY;
                break;
            case MotionEvent.ACTION_UP:
                // 松开时的逻辑
                // 判断是要去打开，要去关闭

                int width = mLeftView.getMeasuredWidth();
                int currentX = getScrollX();
                float middle = -width / 2f;
                switchMenu(currentX <= middle);
                break;
            default:
                break;
        }
        return true;//消费掉
    }


    private void switchMenu(boolean showLeft) {

        isLeftShow = showLeft;
        int width = mLeftView.getMeasuredWidth();
        int currentX = getScrollX();
        if (!showLeft) {
            // 关闭
            // scrollTo(0, 0);
            // 起始点---》结束点
            // -100------->0 -100,-99,-98.....0

            int startX = currentX;
            int startY = 0;

            int endX = 0;
            int endY = 0;

            int dx = endX - startX;// 增量的值
            int dy = endY - startY;

            int duration = Math.abs(dx) * 10;// 时长
            if (duration >= 600) {
                duration = 600;
            }

            // 模拟数据变化
            mScroller.startScroll(startX, startY, dx, dy, duration);

        } else {
            // 打开
            // scrollTo(-width, 0);

            int startX = currentX;
            int startY = 0;

            int endX = -width;
            int endY = 0;

            int dx = endX - startX;// 增量的值
            int dy = endY - startY;

            int duration = Math.abs(dx) * 10;// 时长
            if (duration >= 600) {
                duration = 600;
            }

            // 模拟数据变化
            mScroller.startScroll(startX, startY, dx, dy, duration);
        }
        invalidate();// UI刷新---> draw() -->drawChild() --> computeScroll()
    }
    /**
     * 滚动计算=
     */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            //更新UI
            scrollTo(mScroller.getCurrX(),0);//mScroller.getCurrX()--->过渡中间值
            invalidate();
        }
    }

    public void toggle() {
        switchMenu(!isLeftShow);
    }
}
