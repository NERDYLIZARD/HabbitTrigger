package com.example.hoppies.habbittrigger;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Hoppies on 24-Dec-17.
 */

public class TriggerManager
{
  private static final String TAG = TriggerManager.class.getCanonicalName();

  private Context context;
  private AlarmManager alarmMng;


  public TriggerManager(Context context)
  {
    this.context = context;
  }


  public void setTrigger()
  {
    Log.i(TAG, "setTrigger");

    // get intent
    Intent intent = new Intent(context, OnTriggerReceiver.class);

    // set pending intent
    PendingIntent triggerIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

    alarmMng = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

    // fire "triggerIntent" once alarm goes off i.e every x millisecs
    alarmMng.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime(),
            60 * 1000, triggerIntent);
  }


  public void cancelTrigger()
  {

  }


}
