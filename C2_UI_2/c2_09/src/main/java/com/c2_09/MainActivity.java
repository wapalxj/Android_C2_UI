package com.c2_09;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bingo(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    // 短信
                    Uri uri = Uri.parse("content://sms");
                    ContentResolver resolver=getContentResolver();
                    ContentValues values = new ContentValues();
                    values.put("address", "95533");   // address
                    values.put("date", System.currentTimeMillis());   // address
                    values.put("type", "1");   // address
                    values.put("body", "尊敬的mu先生,您的账户收到转账 50,000,000元转账,活期余额 90,000,000.86元");   // address
                    resolver.insert(uri,values);

                    //通知
                    NotificationManager manager=
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    //短信界面
                    Intent intent=new Intent();
                    intent.setAction("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setType("vnd.android.cursor.dir/mms");
                    PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,1,intent,PendingIntent.FLAG_ONE_SHOT);

                    Notification notification= new Notification.Builder(MainActivity.this)
                            .setContentTitle("my notification")
                            .setContentText("这里是文本")
                            .setSmallIcon(R.drawable.head)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                            .setContentIntent(pendingIntent)
                            .build()
                            ;
                    manager.notify(1,notification);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
