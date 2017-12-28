package com.example.hoppies.habbittrigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TriggerListActivity extends AppCompatActivity
        implements TriggerListFragment.TriggerListListener
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
//    triggerManager.setSpecifiedTimeTrigger(specifiedTimeTrigger.id, specifiedTimeTrigger.days, specifiedTimeTrigger.times);
//    triggerManager.setIntervalTrigger(intervalTrigger.id, intervalTrigger.days, intervalTrigger.beginningTime, intervalTrigger.endingTime, intervalTrigger.interval);
  }


  /**
   * Send intent to start TriggerDetailActivity with selected Trigger's id as an extra
   *
   * @param id Trigger's id
   */
  @Override
  public void triggerClicked(long id)
  {
    Intent intent = new Intent(this, TriggerDetailActivity.class);
    intent.putExtra(TriggerDetailActivity.EXTRA_TRIGGER_ID, id);
    startActivity(intent);
  }
}
