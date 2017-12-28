package com.example.hoppies.habbittrigger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TriggerDetailActivity extends AppCompatActivity
{
  public static final String EXTRA_TRIGGER_ID = "trigger_id";


  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trigger_detail);

    TriggerDetailFragment triggerDetailFragment = (TriggerDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_trigger_detail);
    long triggerId = (long) getIntent().getExtras().get(EXTRA_TRIGGER_ID);
    triggerDetailFragment.setTriggerId(triggerId);
  }
}
