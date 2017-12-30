package com.example.hoppies.habbittrigger;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Trigger's detail fragment
 */
public class TriggerDetailFragment extends Fragment
        implements TextInputDialogFragment.TextInputDialogListener
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

    final TextView triggerTitle = view.findViewById(R.id.trigger_title);
    triggerTitle.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        // open text input dialog
        TextInputDialogFragment textInputDialogFragment = TextInputDialogFragment.newInstance(
                String.valueOf(triggerTitle.getText()),
                R.string.hint_trigger_title);
        textInputDialogFragment.show(TriggerDetailFragment.this.getChildFragmentManager(), "text_input_dialog");
      }
    });

    return view;
  }


  @Override
  public void onStart()
  {
    super.onStart();

    View view = getView();

    if (view != null) {
      Trigger trigger = Trigger.triggers[(int) triggerId];
      TextView title = view.findViewById(R.id.trigger_title);
      title.setText(trigger.title);
    }
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


  @Override
  public void onTextInputDialogOk(String title)
  {
    TextView triggerTitle = getView().findViewById(R.id.trigger_title);
    triggerTitle.setText(title);
  }


  @Override
  public void onTextInputDialogCancel()
  {
  }
}
