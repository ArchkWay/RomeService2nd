package com.example.archek.romeservice;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Collections;

public class App extends Application {
    public static final String CHANNEL = "channel";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }
    private void createNotificationChannels(){
        /*Создаём канал для оповещений/Create channel for notifications*/
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL,
                    "Channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is channel1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannels(Collections.singletonList(channel1));
        }
    }
}
