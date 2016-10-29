package muguihai.com.c310_ui_drawable1;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

/**
 * Created by Administrator on 2015/8/21.
 *  BitmapDrawable
 */
public class DrawableView extends View {
    BitmapDrawable mBitmapDrawable;
    public DrawableView(MainActivity context) {
        super(context);
        mBitmapDrawable =( BitmapDrawable)getResources().getDrawable(R.drawable.cap);
        mBitmapDrawable.setBounds(100,100,350,560);//drawable中必须要被调用的方法

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBitmapDrawable.draw(canvas);
    }
}
