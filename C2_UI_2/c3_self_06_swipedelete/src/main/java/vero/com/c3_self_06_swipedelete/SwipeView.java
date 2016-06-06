package vero.com.c3_self_06_swipedelete;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vero on 2016/6/4.
 */
public class SwipeView extends ViewGroup {
    private View mContentView;
    private View mDeleteView;
    private int mDeleteWidth;

    private ViewDragHelper dragHelper;
    private boolean isOpened=false;

    public SwipeView(Context context) {
        this(context, null);
    }

    public SwipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        dragHelper = ViewDragHelper.create(this, new MyCallBack());
    }

    @Override
    protected void onFinishInflate() {
        mContentView = getChildAt(0);
        mDeleteView = getChildAt(1);
        LayoutParams params = mDeleteView.getLayoutParams();
        mDeleteWidth = params.width;
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //测量内容
        mContentView.measure(widthMeasureSpec, heightMeasureSpec);
        //测量删除
        int deleteWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mDeleteWidth, MeasureSpec.EXACTLY);
        mDeleteView.measure(deleteWidthMeasureSpec, heightMeasureSpec);

        //测量自己
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //mContentView
        int cLeft = 0;
        int cTop = 0;
        int cRight = mContentView.getMeasuredWidth();
        int cBottom = mContentView.getMeasuredHeight();
        mContentView.layout(cLeft, cTop, cRight, cBottom);

        //mDeleteView
        int dLeft = mContentView.getMeasuredWidth();
        int dTop = 0;
        int dRight = mContentView.getMeasuredWidth() + mDeleteView.getMeasuredWidth();
        int dBottom = mDeleteView.getMeasuredHeight();
        mDeleteView.layout(dLeft, dTop, dRight, dBottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    class MyCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //child:触摸的view
            //pointerId:touch事件的id
            return child == mContentView || child == mDeleteView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //当touch移动后的回调
            //child:谁移动了
            //left:child的left值(相对父容器)
            //dx:增量
            //返回值：确定要移动的值

            if (child == mContentView) {
                if (left < 0 && left < -mDeleteView.getMeasuredWidth()) {
                    left = -mDeleteView.getMeasuredWidth();
                } else if (left > 0) {
                    left = 0;
                }
            }

            if (child == mDeleteView) {
                if (left < mContentView.getMeasuredWidth() - mDeleteView.getMeasuredWidth()) {
                    left = mContentView.getMeasuredWidth() - mDeleteView.getMeasuredWidth();
                } else if (left > mContentView.getMeasuredWidth()) {
                    left = mContentView.getMeasuredWidth();
                }
            }

            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            //控件移动时的回调
            //changedView:谁移动了
            //left,top:changedView的左上角坐标(相对父容器)
            //dx,dy：增量
            invalidate();
            ViewCompat.postInvalidateOnAnimation(SwipeView.this);//兼容的invalidate()，用于兼容真机
            int contentWidth = mContentView.getMeasuredWidth();

            if (changedView == mContentView) {
                mDeleteView.layout(left + contentWidth, 0, contentWidth + left + mDeleteView.getMeasuredWidth(), mDeleteView.getMeasuredHeight());
            }

            if (changedView == mDeleteView) {
                mContentView.layout(left - contentWidth, 0, left, mContentView.getMeasuredHeight());
            }


            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //up时候的回调
            //releasedChild：释放的view
            //xvel，yvel：速率
            int left = mContentView.getLeft();
            if (-left < mDeleteView.getMeasuredWidth() / 2f) {
                //关闭
                close();
            } else {
                //打开
                open();
            }
            invalidate();
            ViewCompat.postInvalidateOnAnimation(SwipeView.this);//兼容的invalidate()，用于兼容真机
        }
    }

    public void close() {
//                mContentView.layout(0, 0, mContentView.getMeasuredWidth(), mContentView.getMeasuredHeight());
//                mDeleteView.layout(mContentView.getMeasuredWidth(), 0, mContentView.getMeasuredWidth() + mDeleteView.getMeasuredWidth(), mDeleteView.getMeasuredHeight());
        //平滑滚动
        dragHelper.smoothSlideViewTo(mContentView,0,0);
        dragHelper.smoothSlideViewTo(mDeleteView,mContentView.getMeasuredWidth(),0);
        isOpened=false;

        //回调
        if (listener!=null){
            listener.onSwipeChanged(SwipeView.this,false);
        }
    }

    public void open() {
//                int contentWidth = mContentView.getMeasuredWidth();
//                mContentView.layout(-mDeleteView.getMeasuredWidth(),
//                        0,
//                        contentWidth - mDeleteView.getMeasuredWidth(),
//                        mContentView.getMeasuredHeight()
//                );
//                mDeleteView.layout(mContentView.getMeasuredWidth() - mDeleteView.getMeasuredWidth(),
//                        0,
//                        mContentView.getMeasuredWidth(),
//                        mDeleteView.getMeasuredHeight());
        //平滑滚动
        dragHelper.smoothSlideViewTo(mContentView,-mDeleteView.getMeasuredWidth(),0);
        dragHelper.smoothSlideViewTo(mDeleteView,mContentView.getMeasuredWidth() - mDeleteView.getMeasuredWidth(),0);
        isOpened=false;

        //回调
        if (listener!=null){
            listener.onSwipeChanged(SwipeView.this,true);
        }

    }

    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)){
            invalidate();
            ViewCompat.postInvalidateOnAnimation(SwipeView.this);//兼容的invalidate()，用于兼容真机
        }
    }

    /**
     * 暴露接口
     */

    private OnSwipeListener listener;

    public interface OnSwipeListener{
        void onSwipeChanged(SwipeView sv,boolean isOpened);
    }

    public void setOnSwipeListener(OnSwipeListener listener){
        this.listener=listener;
    }
}
