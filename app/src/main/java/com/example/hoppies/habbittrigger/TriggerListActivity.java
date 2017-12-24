package com.example.hoppies.habbittrigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TriggerListActivity extends AppCompatActivity
{

  private TriggerManager triggerManager;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trigger_list);

    triggerManager = new TriggerManager(this);
    triggerManager.setTrigger();


  }

}
