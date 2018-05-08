package com.example.luput.tnt;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class DateDialog extends DialogFragment {

    public OnInputSelected onInputSelected;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) - 25;
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),
                (DatePickerDialog.OnDateSetListener) SignupFragment.newInstance(), year, month, day);
    }

    public interface OnInputSelected {
        void sendInput(DatePickerDialog datePickerDialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputSelected = (OnInputSelected) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastExeption" + e.getMessage());
        }
    }
}
