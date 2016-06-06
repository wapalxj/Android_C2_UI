package com.c2_08_notification;

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
        //

        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel://112"));
        PendingIntent pendingIntent=PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_ONE_SHOT);


        Notification notification= new Notification.Builder(MainActivity.this)
                .setContentTitle("my notification")
                .setContentText("这里是文本")
                .setSmallIcon(R.drawable.head)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .build()
        ;
        manager.notify(1,notification);
    }
}
