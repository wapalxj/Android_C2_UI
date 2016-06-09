package vero.com.c2_hm_03_translate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btn_translate;
    private ImageView iv_src;
    private ImageView iv_translate;
    Bitmap srcBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path = "mnt/sdcard/img_small_1.jpg";
        srcBitmap = BitmapFactory.decodeFile(path);
        initView();
    }

    public void initView() {
        btn_translate = (Button) findViewById(R.id.btn_translate);
        iv_src = (ImageView) findViewById(R.id.iv_src);
        iv_translate= (ImageView) findViewById(R.id.iv_translate);
        iv_src.setImageBitmap(srcBitmap);
        iv_translate.setImageBitmap(srcBitmap);
        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opts();
            }
        });

    }

    public void opts() {
        //画纸
        Bitmap copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        //画布
        Canvas canvas = new Canvas(copyBitmap);
        //画笔
        Paint paint = new Paint();
        //matrix
        Matrix matrix = new Matrix();
        matrix.setTranslate(50,50);
        //绘画
        canvas.drawBitmap(srcBitmap, matrix, paint);
        iv_translate.setImageBitmap(copyBitmap);
    }
}