package com.example.hoppies.habbittrigger;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

public class TriggerService extends Service
{
  private static final String TAG = TriggerService.class.getCanonicalName();

  public TriggerService()
  {
  }

  @Override
  public IBinder onBind(Intent intent)
  {
    // TODO: Return the communication channel to the service.
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId)
  {

    Log.i(TAG, "start");

    Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    NotificationManager notificationMng = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    Notification notification = new Notification.Builder(this)
            .setContentTitle("Test Title")
            .setContentText("Test Description")
            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setSound(sound)
            .build();

    notificationMng.notify(startId, notification);

    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onCreate()
  {
    super.onCreate();
    Log.i(TAG, "create");
  }

}

