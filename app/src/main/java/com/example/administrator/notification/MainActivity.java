package com.example.administrator.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.administrator.notification.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.showToast.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showToast();
            }
        });
        activityMainBinding.showNotification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showNotification();
            }
        });
    }

    private void showNotification()
    {
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

    private void showToast()
    {
        Toast toast=new Toast(this);
        View view=getLayoutInflater().inflate(R.layout.remotelayout,null,false);
        toast.setView(view);

        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    private PendingIntent getPendingIntent()
    {
        Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
        int requestCode = 1001;
        Intent[] intentArray = {intent};
        return PendingIntent.getActivities(this, requestCode, intentArray, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
