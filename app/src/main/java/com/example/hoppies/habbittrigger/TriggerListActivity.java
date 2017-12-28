package com.example.hoppies.habbittrigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TriggerListActivity extends AppCompatActivity
{

  private TriggerManager triggerManager;
  private Trigger specifiedTimeTrigger = Trigger.triggers[0];
  private Trigger intervalTrigger = Trigger.triggers[1];

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trigger_list);

    // test create alarm
    triggerManager = new TriggerManager(this);
    triggerManager.setSpecifiedTimeTrigger(specifiedTimeTrigger.id, specifiedTimeTrigger.days, specifiedTimeTrigger.times);
    triggerManager.setIntervalTrigger(intervalTrigger.id, intervalTrigger.days, intervalTrigger.beginningTime, intervalTrigger.endingTime, intervalTrigger.interval);
  }

}
