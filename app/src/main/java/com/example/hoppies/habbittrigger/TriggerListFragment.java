package com.example.hoppies.habbittrigger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TriggerListFragment extends ListFragment
{


  public TriggerListFragment()
  {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    String [] triggerTitles = new String[Trigger.triggers.length];
    for (int i = 0; i < triggerTitles.length; ++i)
      triggerTitles[i] = Trigger.triggers[i].title;

    setListAdapter(new ArrayAdapter<String>(
            inflater.getContext(),
            android.R.layout.simple_list_item_1,
            triggerTitles
    ));

    return super.onCreateView(inflater, container, savedInstanceState);
  }

}
