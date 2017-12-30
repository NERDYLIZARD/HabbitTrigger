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


  /**
   * Set Interval Trigger within specified duration of times.
   * <p>
   * Segment a duration according to an interval to obtain times array.
   * Then, call setSpecifiedTimeTrigger() with the times array as parameter
   *
   * @param id            id of Trigger
   * @param days          repeating days of Trigger
   * @param beginningTime beginning of duration
   * @param endingTime    ending of duration
   * @param interval      interval of repeating time of Trigger
   */
  public void setIntervalTrigger(long id, int[] days, String beginningTime, String endingTime, String interval)
  {
    // calculate time segments
    int beginningTimeInSecs = getTimeInSecs(beginningTime);
    int endingTimeInSecs = getTimeInSecs(endingTime);
    int intervalInSecs = getTimeInSecs(interval);
    int segments = (Math.abs(endingTimeInSecs - beginningTimeInSecs) / intervalInSecs) + 1;

    // obtain time of each segment
    String[] times = new String[segments];

    int h = Integer.parseInt(beginningTime.split(":")[0]);
    int m = Integer.parseInt(beginningTime.split(":")[1]);
    int ih = Integer.parseInt(interval.split(":")[0]);
    int im = Integer.parseInt(interval.split(":")[1]);

    times[0] = beginningTime;
    // loop through time segments
    for (int i = 1; i < segments; ++i) {
      h += ih;
      m += im;
      h += (m >= 60) ? 1 : 0;
      m %= 60;
      // if hour or minute has single digit, prefix it with 0 e.g 1:2 -> 01:02
      String hour = ((h / 10) > 0) ? String.valueOf(h) : "0" + String.valueOf(h);
      String minute = ((m / 10) > 0) ? String.valueOf(m) : "0" + String.valueOf(m);
      String time = hour + ":" + minute;
      times[i] = time;
    }
    setSpecifiedTimeTrigger(id, days, times);
  }


  public void setSpecifiedTimeTrigger(long id, int[] days, String[] times)
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
        triggerTime.set(Calendar.SECOND, 0);

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


  private int getTimeInSecs(String time)
  {
    int hours = Integer.parseInt(time.split(":")[0]);
    int minutes = Integer.parseInt(time.split(":")[1]);
    return hours * 3600 + minutes * 60;
  }

}
