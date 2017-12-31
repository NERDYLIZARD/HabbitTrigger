package com.example.hoppies.habbittrigger;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Trigger's detail fragment
 */
public class TriggerDetailFragment extends Fragment
        implements TextInputDialogFragment.TextInputDialogListener,
        DaySelectionDialogFragment.DaySelectionDialogListener
{
  private long triggerId;


  public TriggerDetailFragment()
  {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_trigger_detail, container, false);

    Trigger trigger = Trigger.triggers[(int) triggerId];

    /*title*/
    TextView title = view.findViewById(R.id.triggerTitle);
    title.setText(trigger.title);

    final LinearLayout triggerTitleWrapper = view.findViewById(R.id.triggerTitleWrapper);
    triggerTitleWrapper.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        // open text input dialog
        TextView triggerTitle = view.findViewById(R.id.triggerTitle);
        TextInputDialogFragment textInputDialogFragment = TextInputDialogFragment.newInstance(
                String.valueOf(triggerTitle.getText()),
                R.string.trigger,
                R.string.hint_trigger_title);
        textInputDialogFragment.show(TriggerDetailFragment.this.getChildFragmentManager(), "text_input_dialog");
      }
    });


    /*repeating days*/
    List<String> days = new ArrayList<>(Arrays.asList(trigger.days));
    final boolean[] booleanDays = getBooleanDays(days);
    TextView repeatingDays = view.findViewById(R.id.repeatingDays);
    repeatingDays.setText(getDaysRenderingFormat(booleanDays));

    final LinearLayout repeatingDaysWrapper = view.findViewById(R.id.repeatingDaysWrapper);
    repeatingDaysWrapper.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        // open checkbox dialog
        DaySelectionDialogFragment daySelectionDialogFragment = DaySelectionDialogFragment.newInstance(booleanDays);
        daySelectionDialogFragment.show(TriggerDetailFragment.this.getChildFragmentManager(), "day_selection_dialog");
      }
    });

    return view;
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


  /**
   * Convert an array of strings of day to an array of booleans
   * <p>
   * 0 - monday, 1 - tuesday, . . , 6 - sunday
   *
   * @param days Days represented as booleans
   * @return array of 7 booleans represent monday to sunday
   */
  private boolean[] getBooleanDays(List<String> days)
  {
    boolean[] booleanDays = new boolean[7];
    for (String day : days) {
      switch (day) {
        case "monday":
          booleanDays[0] = true;
          break;
        case "tuesday":
          booleanDays[1] = true;
          break;
        case "wednesday":
          booleanDays[2] = true;
          break;
        case "thursday":
          booleanDays[3] = true;
          break;
        case "friday":
          booleanDays[4] = true;
          break;
        case "saturday":
          booleanDays[5] = true;
          break;
        case "sunday":
          booleanDays[6] = true;
          break;
      }
    }
    return booleanDays;
  }


  /**
   * Convert an array of booleans to an array of strings of day
   * <p>
   * 0 - monday, 1 - tuesday, . . , 6 - sunday
   *
   * @param booleanDays A booleans' array represents selected days
   * @return String of arrays of selected days
   */
  private ArrayList<String> getStringDays(boolean[] booleanDays)
  {
    ArrayList<String> days = new ArrayList<>();

    if (booleanDays[0]) days.add("monday");
    if (booleanDays[1]) days.add("tuesday");
    if (booleanDays[2]) days.add("wednesday");
    if (booleanDays[3]) days.add("thursday");
    if (booleanDays[4]) days.add("friday");
    if (booleanDays[5]) days.add("saturday");
    if (booleanDays[6]) days.add("sunday");

    return days;
  }


  /**
   * Create rendering format of days by concatenating abbreviation of days
   * <p>
   * Format: MON TUE ... SUN / EVERY DAY
   *
   * @param booleanDays Booleans' array represents days
   * @return Concatenation of Abbreviation of days
   */
  private String getDaysRenderingFormat(boolean[] booleanDays)
  {
    if (booleanDays[0] && booleanDays[1] && booleanDays[2] && booleanDays[3]
            && booleanDays[4] && booleanDays[5] && booleanDays[6])
      return "EVERY DAY";

    StringBuilder daysRenderingFormat = new StringBuilder();
    for (int i = 0; i < 7; ++i) {
      if (booleanDays[i])
        daysRenderingFormat.append(getDayAbbreviation(i)).append(" ");
    }
    return daysRenderingFormat.toString();
  }


  /**
   * Obtain abbreviation of day.
   * <p>
   * 0 - MON, 1 - TUE, ... , 6 - SUN
   *
   * @param day Integer represents day
   * @return Abbreviation of day.
   */
  private String getDayAbbreviation(int day)
  {
    switch (day) {
      case 0:
        return "MON";
      case 1:
        return "TUE";
      case 2:
        return "WED";
      case 3:
        return "THU";
      case 4:
        return "FRI";
      case 5:
        return "SAT";
      case 6:
        return "SUN";
    }
    return "";
  }


  @Override
  public void onTextInputDialogOk(String title)
  {
    if (getView() != null) {
      TextView triggerTitle = getView().findViewById(R.id.triggerTitle);
      triggerTitle.setText(title);
    }
  }


  @Override
  public void onTextInputDialogCancel()
  {
  }


  @Override
  public void onDaysSelectionDialogOk(boolean[] booleanDays)
  {
    if (getView() != null) {
      TextView repeatingDays = getView().findViewById(R.id.repeatingDays);
      repeatingDays.setText(getDaysRenderingFormat(booleanDays));
    }
  }


  @Override
  public void onDaysSelectionDialogCancel()
  {
  }
}
