package com.example.hoppies.habbittrigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Hoppies on 24-Dec-17.
 */

public class OnTriggerReceiver extends BroadcastReceiver
{
  private static final String TAG = OnTriggerReceiver.class.getCanonicalName();

  @Override
  public void onReceive(Context context, Intent intent)
  {
    Log.i(TAG, "onReceive");

    Intent serviceIntent = new Intent(context, TriggerService.class);
    context.startService(serviceIntent);
  }

}
