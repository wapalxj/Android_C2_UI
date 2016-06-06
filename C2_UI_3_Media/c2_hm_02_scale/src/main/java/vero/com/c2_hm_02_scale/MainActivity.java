package vero.com.c2_hm_02_scale;

import android.app.Notification;
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

    private Button btn_scale;
    private ImageView iv_src;
    private ImageView iv_dest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        btn_scale= (Button) findViewById(R.id.btn_scale);
        iv_src= (ImageView) findViewById(R.id.iv_src);
        iv_dest= (ImageView) findViewById(R.id.iv_dest);
        btn_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opts();
            }
        });
    }

    public void opts(){
        String path="mnt/sdcard/img_small_1.jpg";
        Bitmap srcBitmap= BitmapFactory.decodeFile(path);
        iv_src.setImageBitmap(srcBitmap);

        //画纸
        Bitmap copyBitmap=Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(),srcBitmap.getConfig());
        //画布
        Canvas canvas=new Canvas(copyBitmap);
        //画笔
        Paint paint=new Paint();
        //matrix
        Matrix matrix=new Matrix();
        matrix.setScale(0.5f,0.5f);//不设置则完全按照原图绘制
        //绘画
        canvas.drawBitmap(srcBitmap,matrix,paint);
        iv_dest.setImageBitmap(copyBitmap);
    }
}
