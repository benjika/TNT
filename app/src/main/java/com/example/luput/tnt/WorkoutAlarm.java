package com.example.luput.tnt;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

public class WorkoutAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DaysOfTrainning days =(DaysOfTrainning)intent.getExtras().get("days");
        boolean [] Days = initDays(days);
        int currendDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        if(Days[currendDay]){
            Notification builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.notificon)
                    .setContentTitle("Today is Work Out Day")
                    .build();

            NotificationManager notificationManager =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1,builder);
        }

    }

    private boolean[] initDays(DaysOfTrainning days) {
        boolean [] retArray = new boolean[8];

        retArray[1] = days.isSunday();
        retArray[2] = days.isMonday();
        retArray[3] = days.isTuesday();
        retArray[4] = days.isWednesday();
        retArray[5] = days.isThursday();
        retArray[6] = days.isFriday();
        retArray[7] = days.isSaturday();


        return retArray;
    }
}

