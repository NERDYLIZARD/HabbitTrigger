package com.example.hoppies.habbittrigger;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A fragment display time view as per system time format i.e 12-hour/24-hour format.
 */
public class TimeViewFragment extends Fragment
        implements TimePickerDialogFragment.TimePickerDialogListener
{

  public static final String TAG = "tag";
  public static final String HOUR = "hour";
  public static final String MINUTE = "minute";
  private TimeViewListener listener;


  public TimeViewFragment()
  {
  }


  interface TimeViewListener
  {
    void onTimeChange(String tag, int hour, int minute);
  }


  @NonNull
  public static TimeViewFragment newInstance(String tag, int hour, int minute)
  {
    Bundle args = new Bundle();
    args.putString(TAG, tag);
    args.putInt(HOUR, hour);
    args.putInt(MINUTE, minute);

    TimeViewFragment fragment = new TimeViewFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_trigger_time, container, false);

    LinearLayout timeWrapper = view.findViewById(R.id.timeWrapper);
    int hour = getArguments().getInt(HOUR);
    int minute = getArguments().getInt(MINUTE);
    renderTimeView(view, hour, minute);

    timeWrapper.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        int hour = getArguments().getInt(HOUR);
        int minute = getArguments().getInt(MINUTE);
        TimePickerDialogFragment timePickerDialogFragment = TimePickerDialogFragment.newInstance(hour, minute);
        timePickerDialogFragment.show(getChildFragmentManager(), "time_picker_dialog");
      }
    });

    return view;
  }


  @Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    try {
      // if parent is a Fragment
      if (getParentFragment() != null)
        listener = (TimeViewListener) getParentFragment();
        // if parent is Context
      else
        listener = (TimeViewListener) context;
      // if sibling
      //
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString()
              + " must implement TimeViewListener");
    }
  }


  @Override
  public void onTimeSet(int hour, int minute)
  {
    View view = getView();
    renderTimeView(view, hour, minute);

    // update time and callback only if time if modified
    if (hour != getArguments().getInt(HOUR)
            || minute != getArguments().getInt(MINUTE)) {
      getArguments().putInt(HOUR, hour);
      getArguments().putInt(MINUTE, minute);
      listener.onTimeChange(getArguments().getString(TAG), hour, minute);
    }
  }


  /**
   * Convert integer hour and minute into String time hh:mm with leading 0 if integer is single digit.
   * <p>
   * E.g. hour = 5, minute = 3 get converted to "05:03"
   *
   * @param hour   Integer indicate an hour of day
   * @param minute Integer indicate a minute
   * @return String time format hh:mm
   */
  private String getTimeString(int hour, int minute)
  {
    String hourString = String.valueOf(hour);
    hourString = (hour / 10 == 0) ? "0" + hourString : hourString;
    String minuteString = String.valueOf(minute);
    minuteString = (minute / 10 == 0) ? "0" + minuteString : minuteString;

    return hourString + ":" + minuteString;
  }


  /**
   * Render timeView according to system time format.
   * <p>
   * Input: hour = 14 and minute = 30.
   * 12-hour system format: 02:30 PM
   * 24-hour system format: 14:30
   *
   * @param view   timeViewWrapper
   * @param hour   Integer indicate an hour of day
   * @param minute Integer indicate a minute
   */
  private void renderTimeView(View view, int hour, int minute)
  {
    boolean is24HourTimeFormat = DateFormat.is24HourFormat(getActivity());

    if (!is24HourTimeFormat) {
      TextView timeSuffix = view.findViewById(R.id.timeSuffix);
      timeSuffix.setText((hour < 12) ? " AM" : " PM");

      if (hour > 12)
        hour %= 12;
    }

    TextView timeView = view.findViewById(R.id.timeView);
    timeView.setText(getTimeString(hour, minute));
  }

}
