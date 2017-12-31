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
    List<String> stringDays = new ArrayList<>(Arrays.asList(trigger.days));
    final boolean[] days = getBooleanDays(stringDays);
    TextView repeatingDays = view.findViewById(R.id.repeatingDays);
    repeatingDays.setText(getDaysRenderingFormat(stringDays));

    final LinearLayout repeatingDaysWrapper = view.findViewById(R.id.repeatingDaysWrapper);
    repeatingDaysWrapper.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        // open checkbox dialog
        DaySelectionDialogFragment daySelectionDialogFragment = DaySelectionDialogFragment.newInstance(days);
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


  // TODO: change parameter to boolean, easy to format as Everyday, weekday, weekend
  private String getDaysRenderingFormat(List<String> days)
  {
    StringBuilder daysRenderingFormat = new StringBuilder();
    for (String day : days)
      daysRenderingFormat.append(getDayAbbreviation(day)).append("  ");
    return daysRenderingFormat.toString();
  }


  private String getDayAbbreviation(String day)
  {
    return day.substring(0, 3).toUpperCase();
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
      ArrayList<String> days = getStringDays(booleanDays);
      repeatingDays.setText(getDaysRenderingFormat(days));
    }
  }


  @Override
  public void onDaysSelectionDialogCancel()
  {
  }
}
