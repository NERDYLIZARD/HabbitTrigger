package com.example.hoppies.habbittrigger;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Trigger's detail fragment
 */
public class TriggerDetailFragment extends Fragment
{
  private long triggerId;


  public TriggerDetailFragment()
  {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(@NonNull LayoutInflater  inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_trigger_detail, container, false);
  }


  @Override
  public void onStart()
  {
    super.onStart();

    View view = getView();

    if (view != null) {
      Trigger trigger = Trigger.triggers[(int) triggerId];
      TextView title = view.findViewById(R.id.trigger_title);
      title.setText(trigger.title);
    }
  }


  /**
   * Set trigger's id
   *
   * @param id Trigger's id
   */
  public void setTriggerId(long id)
  {
    triggerId = id;
  }
}
