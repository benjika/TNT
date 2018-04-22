package com.example.luput.tnt;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;


public class SignUpFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    Button pick_date_button;
    int year, day, month;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pick_date_button = (Button) getView().findViewById(R.id.signup_date_of_birth);
        pick_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        Spinner spinner = (Spinner) getView().findViewById(R.id.signup_trainer_trainee);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.choose_trainer_trainee, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year_from_cal, int month_from_cal, int day_from_cal) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year_from_cal);
        calendar.set(Calendar.MONTH, month_from_cal);
        calendar.set(Calendar.DAY_OF_MONTH, day_from_cal);

        year = year_from_cal;
        month = month_from_cal;
        day = day_from_cal;

        String fullDate = day + "/" + month + "/" + year;
        pick_date_button.setText(fullDate);
    }
}

