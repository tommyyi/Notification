package com.example.administrator.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            Notification.Builder builder = new Notification.Builder(this).setContentTitle("content title").setContentText("content text").setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentIntent(getPendingIntent());
            builder.setAutoCancel(true);
            RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.remotelayout);
            remoteView.setOnClickPendingIntent(R.id.image, getPendingIntent());
            builder.setContent(remoteView);
            Notification notification = builder.build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(100, notification);
        }
    }

    private PendingIntent getPendingIntent()
    {
        Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
        int requestCode = 1001;
        Intent[] intentArray = {intent};
        return PendingIntent.getActivities(this, requestCode, intentArray, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
