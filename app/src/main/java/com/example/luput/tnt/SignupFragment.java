package com.example.luput.tnt;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener {

    private EditText etFirstName;
    private EditText etLastName;
    private int isCoach = 0;
    private EditText Email;
    private EditText Password;
    private Button btnSignUp;
    private RadioGroup radioTrainerOrTrainee;
    private FirebaseAuth mAuth;
    private DatabaseReference DB = FirebaseDatabase.getInstance().getReference();
    Utils utils = new Utils();
    private Button imACoach;
    private Button imATrainee;

    @Override
    public void onStart() {
        //mAuth.addAuthStateListener(AuthListenerl);
        super.onStart();
    }

    @Override
    public void onStop() {
        //mAuth.removeAuthStateListener(AuthListenerl);
        super.onStop();
    }

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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        // Inflate the layout for this fragment

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        AppEventsLogger.activateApp(getActivity().getApplicationContext());

        //radioTrainerOrTrainee = (RadioGroup) view.findViewById(R.id.radio_trainerOrTrainee);
        etFirstName = (EditText) view.findViewById(R.id.signup_firstName);
        etLastName = (EditText) view.findViewById(R.id.signup_lastName);
        Email = view.findViewById(R.id.signup_email);
        Password = view.findViewById(R.id.signup_password);
        btnSignUp = (Button) view.findViewById(R.id.signup_submit);
        mAuth = FirebaseAuth.getInstance();
        imACoach = view.findViewById(R.id.signup_imACoach);
        imATrainee = view.findViewById(R.id.signup_imAtrainee);
       /* radioTrainerOrTrainee.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radio_trainee) {
                    isCoach = false;
                } else {
                    isCoach = true;
                }
            }
        });*/
        imACoach.setOnClickListener(this);
        imATrainee.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        return view;
    }

    private boolean checkFields() {
        boolean isValid = true;

        String FirstName = etFirstName.getText().toString();
        FirstName.trim();
        if (FirstName.equals("")) {
            isValid = false;
        }
        if (isEnglishOrHebrew(FirstName)) {
            etFirstName.setText(FirstName);
        } else {
            isValid = false;
        }

        String LastName = etLastName.getText().toString();
        LastName.trim();
        if (LastName.equals("")) {
            isValid = false;
        }
        if (isEnglishOrHebrew(LastName)) {
            etLastName.setText(LastName);
        } else {
            isValid = false;
        }

        if (radioTrainerOrTrainee.getCheckedRadioButtonId() == -1) {
            isValid = false;
        }

        return isValid;
    }

    private static boolean isEnglishOrHebrew(String s) {
        s.trim();
        char c = s.charAt(0);
        if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HEBREW) {
            for (char charac : s.toCharArray())
                if (!((Character.UnicodeBlock.of(charac) == Character.UnicodeBlock.HEBREW) || charac == ' '))
                    return false;
            return true;
        } else if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
            for (char charac : s.toCharArray()) {
                if (!((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || charac == ' ')) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.signup_submit):
                if (checkFields()) {
                    final ProgressDialog dialog = new ProgressDialog(getActivity()); // this = YourActivity
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("Loading. Please wait...");
                    dialog.setIndeterminate(true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    final String UserName = Email.getText().toString();
                    String password = Password.getText().toString();
                    mAuth.createUserWithEmailAndPassword(UserName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String UserID = mAuth.getCurrentUser().getUid();
                                if (isCoach == 1) {
                                    DB.child(utils.COACH_BRANCH).child(UserID).setValue(UserID);
                                    Coach coach = new Coach(etFirstName.getText().toString(), etLastName.getText().toString(), UserName);
                                    DB.child(utils.COACH_BRANCH).child(UserID).setValue(coach);
                                    Intent intent = new Intent(getActivity(), afterLoginActivity.class);
                                    intent.putExtra("isCoach", true);
                                    dialog.cancel();
                                    startActivity(intent);
                                    getActivity().finish();

                                } else if (isCoach == 2) {
                                    DB.child(utils.TRAINEE_BRANCH).child(UserID).setValue(UserID);
                                    Trainee trainee = new Trainee(etFirstName.getText().toString(), etLastName.getText().toString(), UserName);
                                    DB.child(utils.TRAINEE_BRANCH).child(UserID).setValue(trainee);
                                    Intent intent = new Intent(getActivity(), afterLoginActivity.class);
                                    intent.putExtra("isCoach", false);
                                    dialog.cancel();
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            } else {
                                utils.makeSimplePopup("Oops", "Something happend plz try again", getActivity());
                                dialog.cancel();
                            }
                        }
                    });

                } else {
                    utils.makeSimplePopup("Error in input", "Please make sure you fill all the input fields", getActivity());
                }
                break;
            case (R.id.signup_imACoach):
                imACoach.setBackground(getResources().getDrawable(R.drawable.button_background_light));
                imACoach.setTextColor(getResources().getColor(R.color.icons));
                imACoach.setCompoundDrawablesWithIntrinsicBounds(null,
                        getResources().getDrawable(R.drawable.coachlogo_white), null, null);
                imATrainee.setBackground(getResources().getDrawable(R.drawable.frame_radio));
                imATrainee.setTextColor(getResources().getColor(R.color.colorPrimary));
                imATrainee.setCompoundDrawablesWithIntrinsicBounds(null,
                        getResources().getDrawable(R.drawable.traineelogo_green), null, null);
                isCoach = 1;
                break;
            case (R.id.signup_imAtrainee):
                imATrainee.setBackground(getResources().getDrawable(R.drawable.button_background_light));
                imATrainee.setTextColor(getResources().getColor(R.color.icons));
                imATrainee.setCompoundDrawablesWithIntrinsicBounds(null,
                        getResources().getDrawable(R.drawable.traineelogo_white), null, null);
                imACoach.setBackground(getResources().getDrawable(R.drawable.frame_radio));
                imACoach.setTextColor(getResources().getColor(R.color.colorPrimary));
                imACoach.setCompoundDrawablesWithIntrinsicBounds(null,
                        getResources().getDrawable(R.drawable.coachlogo_green), null, null);
                isCoach = 2;
                break;
        }

    }
}