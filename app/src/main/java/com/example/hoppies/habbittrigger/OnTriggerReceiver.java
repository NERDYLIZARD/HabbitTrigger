package com.example.hoppies.habbittrigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created on 24-Dec-17.
 */

public class OnTriggerReceiver extends BroadcastReceiver
{
  private final String TAG = this.getClass().getSimpleName();


  @Override
  public void onReceive(Context context, Intent intent)
  {
    Log.i(TAG, "onReceive");

    Intent triggerService = new Intent(context, TriggerService.class);
    context.startService(triggerService);
  }

}
