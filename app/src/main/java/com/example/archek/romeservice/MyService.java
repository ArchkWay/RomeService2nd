package com.example.archek.romeservice;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.concurrent.TimeUnit;


import static com.example.archek.romeservice.App.CHANNEL;

public class MyService extends Service {

    private NotificationManagerCompat notificationManager;
    int x;

    public int onStartCommand(Intent intent, int flags, int startId) {
        /*Запускаем фоновый процесс/ Start background process*/
        new Thread(new Runnable() {
            public void run() {
                notificationManager = NotificationManagerCompat.from(getApplicationContext());
                /* бесконечный цикл оповещений / perpetual cycle with notifications every 3 seconds*/
                for (x = 0; x <= 100; x = x + 5){
                    sendOnChannel1(x);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (x == 100) {//restart cycle
                        x = 0;
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        throw new UnsupportedOperationException("Not yet implemented");

    }
/*метод отправки уведомлений / approach for sending notifications*/
    private void sendOnChannel1(int x){
        String message = convert(x);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL)
                .setSmallIcon(R.drawable.ic_one)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }

/*метод для конвертации чисел в римские /
approach for convertation regular numbers in roman */
    public static String convert(int input) {
        if (input < 1 )
            return "0";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }

}
