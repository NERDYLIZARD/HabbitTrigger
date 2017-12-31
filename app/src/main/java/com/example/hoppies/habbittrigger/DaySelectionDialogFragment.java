package com.example.hoppies.habbittrigger;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;


public class DaySelectionDialogFragment extends DialogFragment
{

  public static final String DAYS = "days";
  private DaySelectionDialogListener listener;

  interface DaySelectionDialogListener
  {
    void onDaysSelectionDialogOk(boolean[] booleanDays);

    void onDaysSelectionDialogCancel();
  }


  public DaySelectionDialogFragment()
  {
    // Required empty public constructor
  }


  public static DaySelectionDialogFragment newInstance(boolean[] days)
  {
    Bundle args = new Bundle();
    args.putBooleanArray(DAYS, days);

    DaySelectionDialogFragment fragment = new DaySelectionDialogFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState)
  {
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.dialog_day_selection, null);

    final boolean[] days = getArguments().getBooleanArray(DAYS);

    final CheckBox[] dayCheckboxes = new CheckBox[]{
            view.findViewById(R.id.mondayCheckbox),
            view.findViewById(R.id.tuesdayCheckbox),
            view.findViewById(R.id.wednesdayCheckbox),
            view.findViewById(R.id.thursdayCheckbox),
            view.findViewById(R.id.fridayCheckbox),
            view.findViewById(R.id.saturdayCheckbox),
            view.findViewById(R.id.sundayCheckbox),
    };

    View.OnClickListener checkboxListener = new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        if (days == null) return;

        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
          case R.id.mondayCheckbox:
            days[0] = checked;
            break;
          case R.id.tuesdayCheckbox:
            days[1] = checked;
            break;
          case R.id.wednesdayCheckbox:
            days[2] = checked;
            break;
          case R.id.thursdayCheckbox:
            days[3] = checked;
            break;
          case R.id.fridayCheckbox:
            days[4] = checked;
            break;
          case R.id.saturdayCheckbox:
            days[5] = checked;
            break;
          case R.id.sundayCheckbox:
            days[6] = checked;
            break;
        }
      }
    };

    /* populate checkboxes and set their listener*/
    for (int i = 0; i < 7; ++i) {
      dayCheckboxes[i].setChecked(days[i]);
      dayCheckboxes[i].setOnClickListener(checkboxListener);
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setView(view)
            .setTitle(R.string.repeat)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
            {
              @Override
              public void onClick(DialogInterface dialogInterface, int i)
              {
                listener.onDaysSelectionDialogOk(days);
              }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
            {
              @Override
              public void onClick(DialogInterface dialogInterface, int i)
              {
                listener.onDaysSelectionDialogCancel();
              }
            });

    return builder.create();
  }


  @Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    try {
      // if parent is a Fragment
      if (getParentFragment() != null)
        listener = (DaySelectionDialogListener) getParentFragment();
        // if parent is Context
      else
        listener = (DaySelectionDialogListener) context;
      // if sibling
      //
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString()
              + " must implement DaysSelectionDialogListener");
    }
  }
}
