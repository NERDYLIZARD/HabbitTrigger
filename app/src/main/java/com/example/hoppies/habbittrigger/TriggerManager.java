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

        // set PendingIntend with different request code
        // requestCode format:
        // id.day.hour.minute
        // 1.1.14.30
        // id: 1, day: SUN, time: 14:30

        // NOTE: An issue could occur if user ever enters over 2,147,483,647 tasks. (Max int value).
        // I highly doubt this will ever happen. But is good to note.

        StringBuilder stringBuilder = new StringBuilder();
        // TODO: Try modulo id
        stringBuilder.append(String.valueOf(id))
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
