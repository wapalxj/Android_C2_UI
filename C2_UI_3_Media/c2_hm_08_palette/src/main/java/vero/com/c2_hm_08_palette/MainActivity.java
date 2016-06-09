package vero.com.c2_hm_08_palette;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private View color_red;
    private View color_green;
    private View color_blue;
    private View color_yellow;
    private View color_pink;
    private SeekBar sb_stroke;
    private ImageView iv;

    private Bitmap bitmap;// 画纸
    private Canvas canvas;// 画布
    private Paint paint;// 画笔

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        color_red = findViewById(R.id.color_red);
        color_green = findViewById(R.id.color_green);
        color_blue = findViewById(R.id.color_blue);
        color_yellow = findViewById(R.id.color_yellow);
        color_pink = findViewById(R.id.color_pink);
        sb_stroke = (SeekBar) findViewById(R.id.sb);
        iv = (ImageView) findViewById(R.id.iv);
        color_red.setOnClickListener(this);
        color_green.setOnClickListener(this);
        color_blue.setOnClickListener(this);
        color_yellow.setOnClickListener(this);
        color_pink.setOnClickListener(this);

        sb_stroke.setOnSeekBarChangeListener(this);
        iv.setOnTouchListener(this);
        ViewGroup.LayoutParams params=iv.getLayoutParams();
        bitmap=Bitmap.createBitmap(params.width,params.height, Bitmap.Config.ARGB_8888);
        canvas=new Canvas(bitmap);
        paint=new Paint();
        canvas.drawColor(Color.WHITE);//白底
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.color_red:
                paint.setColor(Color.RED);
                break;
            case R.id.color_green:
                paint.setColor(Color.GREEN);
                break;
            case R.id.color_blue:
                paint.setColor(Color.BLUE);
                break;
            case R.id.color_yellow:
                paint.setColor(Color.YELLOW);
                break;
            case R.id.color_pink:
                paint.setColor(Color.parseColor("#FFFF99FF"));
                break;
            default:
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress=seekBar.getProgress();
        paint.setStrokeWidth(progress);
    }


    /**
     * iv监听
     */
    float startX,startY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX=event.getX();
                    startY=event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float endX=event.getX();
                    float endY=event.getY();
                    canvas.drawLine(startX,startY,endX,endY,paint);
                    startX=endX;
                    startY=endY;
                    iv.setImageBitmap(bitmap);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
            return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                //将bitmap存储到本地
                Bitmap.CompressFormat format= Bitmap.CompressFormat.JPEG;
                int quality=100;//不压缩
                File file=new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".jpg");
                OutputStream os=null;
                try {
                    os=new FileOutputStream(file);
                    bitmap.compress(format,quality,os);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    if (os!=null){
                        try {
                            os.close();
                            os=null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //模拟SD卡挂载(自动将图片挂到Gallery中)
                Intent intent=new Intent(Intent.ACTION_MEDIA_MOUNTED);
                intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
                sendBroadcast(intent);
                Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
