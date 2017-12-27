package com.example.hoppies.habbittrigger;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

public class TriggerService extends IntentService
{
  private final String TAG = this.getClass().getSimpleName();


  public TriggerService()
  {
    super("TriggerService");
  }


  public TriggerService(String name)
  {
    super(name);
  }


  @Override
  protected void onHandleIntent(@Nullable Intent intent)
  {
    Log.i(TAG, "start");

    Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    NotificationManager notificationMng = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    Notification notification = new Notification.Builder(this)
            .setContentTitle("Test Title")
            .setContentText("Test Description")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setSound(sound)
            .build();

    notificationMng.notify(0, notification);

  }


}

