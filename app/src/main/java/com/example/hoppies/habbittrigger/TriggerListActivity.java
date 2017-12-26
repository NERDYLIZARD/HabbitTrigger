package com.example.hoppies.habbittrigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TriggerListActivity extends AppCompatActivity
{

  private TriggerManager triggerManager;
  private Trigger trigger = Trigger.triggers[0];

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trigger_list);

    // test create alarm
    triggerManager = new TriggerManager(this);
    triggerManager.setTrigger(trigger.id, trigger.days, trigger.times);
  }

}
