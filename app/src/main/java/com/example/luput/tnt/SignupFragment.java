package com.example.luput.tnt;


import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.ResourceBundle;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment
        implements
        View.OnClickListener,
        View.OnFocusChangeListener,
        DatePickerDialog.OnDateSetListener,
        DateDialog.OnInputSelected {

    private static EditText etFirstName;
    private static EditText etLastName;
    private static EditText etEmail;
    private static EditText etPhone;
    private static EditText etCity;
    private static EditText etDateOfbirth;
    private static Spinner spinnerGender;
    private static ArrayAdapter<CharSequence> genderAdapter;
    private static int DateOfbirth_day;
    private static int DateOfbirth_month;
    private static int DateOfbirth_year;
    private static ImageButton profilePic;
    private static Button btnSignUp;
    private static RadioGroup radioTrainerOrTrainee;
    static View view;


    public SignupFragment() {
        // Required empty public constructor
    }

    public static SignupFragment newInstance() {
        SignupFragment signupFragment = new SignupFragment();
        return signupFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup, container, false);

        // Inflate the layout for this fragment

        radioTrainerOrTrainee = (RadioGroup) view.findViewById(R.id.radio_trainerOrTrainee);
        etFirstName = (EditText) view.findViewById(R.id.signup_firstName);
        etLastName = (EditText) view.findViewById(R.id.signup_lastName);
        spinnerGender = (Spinner) view.findViewById(R.id.signup_gender_spinner);
        genderAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.genders, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);
        // ((TextView) spinnerGender.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorPrimary));
        etEmail = (EditText) view.findViewById(R.id.signup_email);
        etPhone = (EditText) view.findViewById(R.id.signup_phone);
        etDateOfbirth = (EditText) view.findViewById(R.id.signup_dateOfBith);
        etCity = (EditText) view.findViewById(R.id.signup_city);

        etDateOfbirth.setOnFocusChangeListener(this);

        return view;
    }

    private void InitFields() {

    }

    @Override
    public void onFocusChange(View view1, boolean b) {
        switch (view1.getId()) {
            case R.id.signup_dateOfBith:
                if (etDateOfbirth.hasFocus()) {
                    DialogFragment datePicker = new DateDialog();
                    datePicker.show(getFragmentManager(), "date picker");
                    return;
                }
        }
    }

    public void onClick(View v) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        super.onResume();
        EditText dateOfBirth = (EditText) view.findViewById(R.id.signup_dateOfBith);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        dateOfBirth.setText(day + "/" + month + "/" + year);
    }

    @Override
    public void sendInput(DatePickerDialog datePickerDialog) {
        //int year, month, day;
       // Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.MONTH, month);
//        calendar.set(Calendar.DAY_OF_MONTH, day);
//
//        etDateOfbirth.setText(day + "/" + month + "/" + year);
    }
}
