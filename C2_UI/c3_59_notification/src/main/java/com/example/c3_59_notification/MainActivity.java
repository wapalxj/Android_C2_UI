package com.example.c3_59_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.得到NotificationManeger对象
        notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2.构造一个Notification实例
                Notification.Builder notify =new Notification.Builder(MainActivity.this);
                PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this, 0, new Intent(MainActivity.this, Test.class), 0);//将要处理的任务
                notify.setContentIntent(pendingIntent)
                      .setSmallIcon(android.R.drawable.ic_dialog_email)
                      .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)//设置可以清除
                        .setContentTitle("This is ContentTitle")//设置下拉列表里的标题
                        .setContentText("**************email");//设置上下文内容



                Notification notification=notify.getNotification();

                notification.contentView =new RemoteViews(getPackageName(),R.layout.customer_notify);
                //加入震动
                long [] vib={0,100,200,300};
                notification.vibrate=vib;
                //加入声音和闪光
                notification.defaults|=Notification.DEFAULT_SOUND;
                notification.defaults|=Notification.DEFAULT_LIGHTS;
                //3.启动提醒
                notificationManager.notify(123, notification);

            }
        });
    }

    @Override
    protected void onStop() {
        notificationManager.cancelAll();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
