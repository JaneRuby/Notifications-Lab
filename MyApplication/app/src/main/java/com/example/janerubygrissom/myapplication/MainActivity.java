package com.example.janerubygrissom.myapplication;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder notificationBuilder;

    Intent intent;


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationBuilder = new NotificationCompat.Builder(this);

        NotificationCompat.BigPictureStyle pictureStyle =
                new android.support.v4.app.NotificationCompat.BigPictureStyle();

        if (isConnected()) {
            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                    R.drawable.wireless_ok));
            intent = new Intent(this, SecondActivity.class);
            notificationBuilder.setAutoCancel(true)
                    .setContentTitle("Connected!")
                    .setSmallIcon(R.drawable.vector);

        } else {
            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                    R.drawable.wirless_none));
            intent = new Intent(this, MainActivity.class);
            notificationBuilder.setAutoCancel(false)
                    .setContentTitle("Not Connected!")
                    .setSmallIcon(android.R.drawable.ic_dialog_alert);

        }

        notificationBuilder.setStyle(pictureStyle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 123, intent, 0);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManagerCompat.from(MainActivity.this).notify(123, notificationBuilder.build());

    }


    boolean isConnected() {

        ConnectivityManager
                connectMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
