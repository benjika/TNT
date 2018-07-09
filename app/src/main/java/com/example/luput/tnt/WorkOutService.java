package com.example.luput.tnt;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Calendar;


public class WorkOutService extends IntentService {
    private static final String THREAD_NAME  = "workOutThread";
    private static final int CHANNEL_ID = 13;

    public WorkOutService(){
        super(THREAD_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Bundle bundle = intent.getExtras();
        DaysOfTrainning days = (DaysOfTrainning)bundle.get("currentdaysofprogram");
        boolean [] dayofworkout = new boolean[8];
        int currendDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        initdays(days,dayofworkout);

        if(dayofworkout[currendDay]){
            Notification.Builder builder = new Notification.Builder(this,CHANNEL_ID)
                    .setSmallIcon(R.drawable.notificon)
                    .setContentTitle("Today is Work Out Day")
                    .build();
        }
    }

    private void initdays(DaysOfTrainning days, boolean[] dayofworkout) {
        dayofworkout[1] = days.isSunday();
        dayofworkout[2] = days.isMonday();
        dayofworkout[3] = days.isTuesday();
        dayofworkout[4] = days.isWednesday();
        dayofworkout[5] = days.isThursday();
        dayofworkout[6] = days.isFriday();
        dayofworkout[7] = days.isSaturday();
    }


}
