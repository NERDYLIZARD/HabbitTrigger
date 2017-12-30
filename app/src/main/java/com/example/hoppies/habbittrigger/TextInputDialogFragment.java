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
import android.widget.EditText;

/**
 *
 */
public class TextInputDialogFragment extends DialogFragment
{
  public static final String TEXT_INPUT = "textInput";
  public static final String TITLE = "title";
  public static final String HINT = "hint";
  private TextInputDialogListener listener;

  interface TextInputDialogListener
  {
    void onTextInputDialogOk(String textInput);

    void onTextInputDialogCancel();
  }


  public TextInputDialogFragment()
  {
    // Required empty public constructor
  }


  /**
   * Static factory - Instantiate an object and initialize its member variables.
   *
   * @param textInput Populated text input
   * @param title Title of dialog
   * @param hint Hint/placeholder
   * @return An instance of TextInputDialogFragment
   */
  @NonNull
  public static TextInputDialogFragment newInstance(String textInput, int title, int hint)
  {
    Bundle args = new Bundle();
    args.putString(TEXT_INPUT, textInput);
    args.putInt(TITLE, title);
    args.putInt(HINT, hint);

    TextInputDialogFragment fragment = new TextInputDialogFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    try {
      // if parent is a Fragment
      if (getParentFragment() != null)
        listener = (TextInputDialogListener) getParentFragment();
      // if parent is Context
      else
        listener = (TextInputDialogListener) context;
      // if sibling
        //
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString()
              + " must implement TextInputDialogListener");
    }
  }


  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState)
  {
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.dialog_text_input, null);

    final EditText editText = view.findViewById(R.id.edit_text);
    editText.setText(getArguments().getString(TEXT_INPUT));
    editText.setHint(getArguments().getInt(HINT));
    editText.setSelection(editText.length());

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setView(view)
            .setTitle(getArguments().getInt(TITLE))
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
            {
              @Override
              public void onClick(DialogInterface dialogInterface, int i)
              {
                listener.onTextInputDialogOk(String.valueOf(editText.getText()));
              }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
            {
              @Override
              public void onClick(DialogInterface dialogInterface, int i)
              {
                listener.onTextInputDialogCancel();
              }
            });

    return builder.create();
  }


}