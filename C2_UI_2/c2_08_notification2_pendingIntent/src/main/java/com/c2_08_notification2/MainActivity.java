package com.c2_08_notification2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 兼容模式
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send_notification(View view){
        //首先获取NotificationManager
        NotificationManager manager=
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel://112"));
        PendingIntent pendingIntent=PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);


        Notification.Builder builder= new Notification.Builder(MainActivity.this)
                .setContentTitle("my notification2")
                .setContentText("这里是文本")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.head)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent);

        Notification notification=builder.getNotification();
        manager.notify(1,notification);
    }
}
