package com.example.hoppies.habbittrigger;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

    LinearLayout specifiedTimeWrapper = view.findViewById(R.id.specifiedTimeWrapper);

    // run fragmentTransaction add TriggerTimeFragment once when this fragment is newly created.
      // prevent the redundant call when device has been rotated i.e activity and all its fragments are recreated.
    if (savedInstanceState == null) {
      for (String time : times) {
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
        getFragmentManager().beginTransaction().add(specifiedTimeWrapper.getId(), TriggerTimeFragment.newInstance(hour, minute), "tag").commit();
      }
    }
    return view;
  }


}
