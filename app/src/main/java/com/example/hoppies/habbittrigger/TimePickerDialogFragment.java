package com.example.hoppies.habbittrigger;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerDialogFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener
{
  public static final String HOUR = "hour";
  public static final String MINUTE = "minute";
  private TimePickerDialogListener listener;


  interface TimePickerDialogListener
  {
    void onTimeSet(int hour, int minute);
  }


  public TimePickerDialogFragment()
  {
    // Required empty public constructor
  }


  @NonNull
  public static TimePickerDialogFragment newInstance(int hour, int minute)
  {
    Bundle args = new Bundle();
    args.putInt(HOUR, hour);
    args.putInt(MINUTE, minute);

    TimePickerDialogFragment fragment = new TimePickerDialogFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @NonNull
  public Dialog onCreateDialog(Bundle savedInstanceState)
  {
    int hour = getArguments().getInt(HOUR);
    int minute = getArguments().getInt(MINUTE);

    return new TimePickerDialog(getActivity(), this, hour, minute,
            DateFormat.is24HourFormat(getActivity()));
  }


  @Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    try {
      // if parent is a Fragment
      if (getParentFragment() != null)
        listener = (TimePickerDialogListener) getParentFragment();
        // if parent is Context
      else
        listener = (TimePickerDialogListener) context;
      // if sibling
      //
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString()
              + " must implement TimePickerDialogListener");
    }
  }


  @Override
  public void onTimeSet(TimePicker timePicker, int hour, int minute)
  {
    listener.onTimeSet(hour, minute);
  }
}
