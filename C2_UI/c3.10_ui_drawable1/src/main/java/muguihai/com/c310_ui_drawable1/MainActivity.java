package muguihai.com.c310_ui_drawable1;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawableView dw =new DrawableView(this);
        CustomDrawableView cw =new CustomDrawableView(this);
//        setContentView(dw);
        setContentView(cw);
    }
}
