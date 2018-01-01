package com.example.hoppies.habbittrigger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
public class TriggerTimeFragment extends Fragment
        implements TimePickerDialogFragment.TimePickerDialogListener
{

  // TODO: having state as int hour and int minute instead of String time
  // test value time
  private String time = "14:30";
  private boolean is24HourTimeFormat;


  public TriggerTimeFragment()
  {
  }


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_trigger_time, container, false);

    LinearLayout timeWrapper = view.findViewById(R.id.timeWrapper);
    // TODO: accept argument as int hour and int minute
    int hour = Integer.parseInt(time.split(":")[0]);
    int minute = Integer.parseInt(time.split(":")[1]);
    renderTimeView(view, hour, minute);

    timeWrapper.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
        TimePickerDialogFragment timePickerDialogFragment = TimePickerDialogFragment.newInstance(hour, minute);
        timePickerDialogFragment.show(getChildFragmentManager(), "time_picker_dialog");
      }
    });

    return view;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState)
  {
    super.onActivityCreated(savedInstanceState);
    is24HourTimeFormat = DateFormat.is24HourFormat(getActivity());
  }


  @Override
  public void onTimeSet(int hour, int minute)
  {
    View view = getView();
    renderTimeView(view, hour, minute);

    time = getTimeString(hour, minute);
    // return String time to DetailFragment/TabHost
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
