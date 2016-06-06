package vero.com.c2_hm_01_load_image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btn_load_small;
    private Button btn_load_big;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    public void initView(){
        iv= (ImageView) findViewById(R.id.iv);
        btn_load_small= (Button) findViewById(R.id.btn_load_small);
        btn_load_big= (Button) findViewById(R.id.btn_load_big);
        btn_load_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSmallImage();
            }
        });
        btn_load_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadBigImage();
                load();
            }
        });
    }

    public void loadSmallImage(){
        String path="mnt/sdcard/img_small_1.jpg";
        Bitmap bitmap=BitmapFactory.decodeFile(path);
//        Bitmap bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getName()+"/img_small_2.jpg");
        iv.setImageBitmap(bitmap);
    }

    public void loadBigImage(){
        String path="mnt/sdcard/img_big_4.jpg";
        BitmapFactory.Options options=new BitmapFactory.Options();
        //宽和高：1000*1000--->inSampleSize=4----->250*250---->将图片缩小为原来的1/16
        options.inSampleSize=4;//采样率
        Bitmap bitmap=BitmapFactory.decodeFile(path,options);
        iv.setImageBitmap(bitmap);
        Toast.makeText(MainActivity.this,"loadBigImage",Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据手机的屏幕宽高大小动态计算采样率
     */
    public void load(){
        String path="mnt/sdcard/img_big_4.jpg";

        //1.屏幕宽高
        DisplayMetrics metrics=getResources().getDisplayMetrics();
        int screenWidth=metrics.widthPixels;
        int screenHeight=metrics.heightPixels;
        //2.图片宽高 exif--->可以获取图片的详细信息
        try {
            ExifInterface exif=new ExifInterface(path);
            int picWidth=exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH,0);
            int picHeight=exif.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH,0);

            //采样率=图片的宽度/屏幕的宽度
            int widthSample= (int) (picWidth*1f/screenWidth+0.5f);//1.9+0.5=2.4--->2====>1.9更接近2
            int heightSample= (int) (picHeight*1f/screenHeight+0.5f);
            int sample= (int) Math.sqrt(widthSample*heightSample);
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inSampleSize=sample;//采样率
            Bitmap bitmap=BitmapFactory.decodeFile(path,options);
            iv.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(MainActivity.this,"load",Toast.LENGTH_SHORT).show();

    }
}
