package com.example.hoppies.habbittrigger;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Trigger List Fragment
 */
public class TriggerListFragment extends ListFragment
{
  private final String TAG = this.getClass().getSimpleName();


  private TriggerListListener listener;


  public TriggerListFragment()
  {
    // Required empty public constructor
  }


  /**
   * Trigger's listener Interface
   */
  interface TriggerListListener
  {
    void triggerClicked(long id);
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    String[] triggerTitles = new String[Trigger.triggers.length];
    for (int i = 0; i < triggerTitles.length; ++i)
      triggerTitles[i] = Trigger.triggers[i].title;

    setListAdapter(new ArrayAdapter<>(
            inflater.getContext(),
            android.R.layout.simple_list_item_1,
            triggerTitles
    ));

    return super.onCreateView(inflater, container, savedInstanceState);
  }


  @Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    if (context instanceof Activity)
      listener = (TriggerListListener) context;
  }


  @Override
  public void onListItemClick(ListView l, View v, int position, long id)
  {
    super.onListItemClick(l, v, position, id);
    if (listener != null) {
      listener.triggerClicked(id);
    }
  }

}
