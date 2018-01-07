package com.example.hoppies.habbittrigger;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpecifiedTimeTriggerFragment extends Fragment
  implements TimeViewFragment.TimeViewListener
{
  // test times
  List<String> times = new ArrayList<>(Arrays.asList("08:30", "14:30"));


  public SpecifiedTimeTriggerFragment()
  {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.fragment_specified_time_trigger, container, false);

    LinearLayout timeViewList = view.findViewById(R.id.timeViewList);
    List<String> timeViewTags = new ArrayList<>();

    // run fragmentTransaction add TimeViewFragment once when this fragment is newly created.
      // prevent the redundant call when device has been rotated i.e activity and all its fragments are recreated.
    if (savedInstanceState == null) {
      for (int i = 0; i < times.size(); ++i) {
        int hour = Integer.parseInt(times.get(i).split(":")[0]);
        int minute = Integer.parseInt(times.get(i).split(":")[1]);
        String tag = String.valueOf(i);
        getChildFragmentManager().beginTransaction()
                .add(timeViewList.getId(), TimeViewFragment.newInstance(tag, hour, minute), tag)
                .commit();
      }
    }

    return view;
  }


  @Override
  public void onTimeChange(String tag, int hour, int minute)
  {
    Log.i("tag", tag);
    Log.i("h", String.valueOf(hour));
    Log.i("m", String.valueOf(minute));

    // remove all TimeView fragments
    // update times[]
    // sort time[]: using Local time and compare int sec
    // add TimeView fragments according to updated times[]
  }
}
