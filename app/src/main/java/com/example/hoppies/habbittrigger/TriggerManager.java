package com.example.hoppies.habbittrigger;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created on 24-Dec-17.
 */

public class TriggerManager
{
  private final String TAG = this.getClass().getSimpleName();

  private Context context;
  private AlarmManager alarmMng;


  public TriggerManager(Context context)
  {
    this.context = context;
    alarmMng = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
  }


  public void setTrigger(long id, int[] days, String[] times)
  {
    for (int day : days) {
      for (String time : times) {

        // set calendar i.e time format
        String hour = time.split(":")[0];
        String minute = time.split(":")[1];

        Calendar triggerTime = Calendar.getInstance();
        triggerTime.set(Calendar.DAY_OF_WEEK, day);
        triggerTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        triggerTime.set(Calendar.MINUTE, Integer.parseInt(minute));

        Log.i(TAG, String.valueOf(triggerTime.getTime()));

        // fetch new Intent of OnTriggerReceiver
        Intent intent = new Intent(context, OnTriggerReceiver.class);

        // set PendingIntend with different requestCode
        // requestCode format:
          // id.day.hour.minute
          // 123.1.14.30
          // id: 123, day: SUN, time: 14:30

        // NOTE: Max int = 2,147,483,647. (RequestCode must be integer)
        // An issue occurs once _id of Trigger is larger than 4 digits e.g > 9999.
          // Our string requestCode is constraint by 10 digits of 2,147,483,647.
          // The requestCode can only be at most 9 digits to ensure that once it's converted to integer, it won't exceed the range
          // The requestCode mandatory contains 5 digits of combination of day.hour.minute. e.g 1.14.30
          // Therefore, the _id that is appended in front of day.hour.minute must be at most 4 digits.
          // Solution: modulus _id by 9999 to obtain 4-digit long _id.

        // Bug: if _id % 9999 results the existing Trigger's _id.
          // e.g. the (_id = 1) == (_id = 10000) due 10000 % 9999 = 1
          // but it's not likely that the first Trigger is still on the system once the 10000th Trigger is created.
        // I highly doubt this will ever happen. But is good to note.

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(id % 9999))
                .append(String.valueOf(day))
                .append(hour)
                .append(minute);

        int requestCode = Integer.parseInt(stringBuilder.toString());
        // store requestCode in DB and use it for alarm cancellation

        Log.i("requestCode", String.valueOf(requestCode));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);

        // issue the PendingIntend through alarm
        alarmMng.setRepeating(AlarmManager.RTC_WAKEUP,
                triggerTime.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY * 7,
                pendingIntent);
      }
    }

  }


}
