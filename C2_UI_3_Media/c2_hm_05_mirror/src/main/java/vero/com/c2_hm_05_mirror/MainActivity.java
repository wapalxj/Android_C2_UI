package vero.com.c2_hm_05_mirror;

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
    private Button btn_mirror;
    private Button btn_re;
    private ImageView iv_src;
    private ImageView iv_mirror;
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
        btn_mirror = (Button) findViewById(R.id.btn_mirror);
        btn_re = (Button) findViewById(R.id.btn_re);
        iv_src = (ImageView) findViewById(R.id.iv_src);
        iv_mirror = (ImageView) findViewById(R.id.iv_mirror);
        iv_src.setImageBitmap(srcBitmap);
        iv_mirror.setImageBitmap(srcBitmap);
        btn_mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opts();
            }
        });
        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re();
            }
        });

    }

    /**
     * 镜像
     */
    public void opts() {
        //画纸
        Bitmap copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        //画布
        Canvas canvas = new Canvas(copyBitmap);
        //画笔
        Paint paint = new Paint();
        //matrix
        Matrix matrix = new Matrix();

        //2步处理需要用到post
//        matrix.setScale(-1f,1f);
//        matrix.postTranslate(srcBitmap.getWidth(), 0);

        //1步也可以做到
        matrix.setScale(-1f,1f,srcBitmap.getWidth()/2,srcBitmap.getHeight()/2);

        //绘画
        canvas.drawBitmap(srcBitmap, matrix, paint);
        iv_mirror.setImageBitmap(copyBitmap);
    }

    /**
     * 倒映
     */
    public void re() {
        //画纸
        Bitmap copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        //画布
        Canvas canvas = new Canvas(copyBitmap);
        //画笔
        Paint paint = new Paint();
        //matrix
        Matrix matrix = new Matrix();

        //1步也可以做到
        matrix.setScale(1f,-1f,srcBitmap.getWidth()/2,srcBitmap.getHeight()/2);

        //绘画
        canvas.drawBitmap(srcBitmap, matrix, paint);
        iv_mirror.setImageBitmap(copyBitmap);
    }
}