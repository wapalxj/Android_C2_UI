package vero.com.c2_hm_06_color;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private ImageView iv;
    private SeekBar sb_Red;
    private SeekBar sb_Green;
    private SeekBar sb_Blue;
    private Bitmap mSrcBitmap;
    private float redPercent=1;
    private float greenPercent=1;
    private float bluePercent=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        String path="mnt/sdcard/img_small_1.jpg";
        mSrcBitmap = BitmapFactory.decodeFile(path);
        iv.setImageBitmap(mSrcBitmap);
    }

    void initView(){
        iv= (ImageView) findViewById(R.id.iv);
        sb_Red= (SeekBar) findViewById(R.id.sb_red);
        sb_Green= (SeekBar) findViewById(R.id.sb_green);
        sb_Blue= (SeekBar) findViewById(R.id.sb_blue);

        sb_Red.setOnSeekBarChangeListener(this);
        sb_Green.setOnSeekBarChangeListener(this);
        sb_Blue.setOnSeekBarChangeListener(this);

    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //0。获取进度,将0-100按0-2映射
        int progress=seekBar.getProgress();
        float percent=progress/50f;//(0-2f)

        if (seekBar==sb_Red){
            redPercent=percent;
        }else if (seekBar==sb_Green){
            greenPercent=percent;
        }else if (seekBar==sb_Blue){
            bluePercent=percent;
        }


        //1.获取拷贝
        Bitmap copyBitmap=Bitmap.createBitmap(mSrcBitmap.getWidth(),mSrcBitmap.getHeight(),mSrcBitmap.getConfig());
        Canvas canvas=new Canvas(copyBitmap);
        Paint paint=new Paint();
        //设置画笔的颜色过滤

        //颜色矩阵，0--2,0:没有，2:最多
        float [] cm=new float[]{
                1*redPercent,0,0,0,0,//red vector
                0,1*greenPercent,0,0,0,//green vector
                0,0,1*bluePercent,0,0,//blue vector
                0,0,0,1,0//alpha vector
        };
        paint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(cm)));
        //2.处理图片的颜色数据
        Matrix matrix=new Matrix();
        canvas.drawBitmap(mSrcBitmap,matrix,paint);
        //3.展示结果
        iv.setImageBitmap(copyBitmap);
    }
}
