package com.example.luput.tnt;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;




/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private EditText etFirstName;
    private EditText etLastName;
    private boolean isCoach;
    private EditText Email;
    private EditText Password;
    private Button btnSignUp;
    private RadioGroup radioTrainerOrTrainee;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference DB = FirebaseDatabase.getInstance().getReference();
    Utils utils = new Utils();

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

        radioTrainerOrTrainee = view.findViewById(R.id.radio_trainerOrTrainee);
        etFirstName = view.findViewById(R.id.signup_firstName);
        etLastName = view.findViewById(R.id.signup_lastName);
        Email = view.findViewById(R.id.signup_email);
        Password = view.findViewById(R.id.signup_password);
        btnSignUp =  view.findViewById(R.id.signup_submit);

        radioTrainerOrTrainee.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radio_trainee){
                    isCoach = false;
                }
                else{
                    isCoach = true;
                }
            }
        });

        //region SignUp Logic

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkFields()){
                    final ProgressDialog dialog = new ProgressDialog(getActivity()); // this = YourActivity
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("Loading. Please wait...");
                    dialog.setIndeterminate(true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    final String UserName = Email.getText().toString();
                    String password = Password.getText().toString();
                    mAuth.createUserWithEmailAndPassword(UserName,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String UserID = mAuth.getCurrentUser().getUid();
                                if(isCoach){
                                    DB.child(utils.COACH_BRANCH).child(UserID).setValue(UserID);
                                    Coach coach = new Coach(etFirstName.getText().toString(),etLastName.getText().toString(),UserName);
                                    DB.child(utils.COACH_BRANCH).child(UserID).setValue(coach);
                                    Intent intent = new Intent(getActivity(), afterLoginActiviry.class);
                                    intent.putExtra("isCoach", true);
                                    dialog.cancel();
                                    startActivity(intent);
                                    getActivity().finish();

                                }
                                else {
                                    DB.child(utils.TRAINEE_BRANCH).child(UserID).setValue(UserID);
                                    Trainee trainee = new Trainee(etFirstName.getText().toString(),etLastName.getText().toString(),UserName);
                                    DB.child(utils.TRAINEE_BRANCH).child(UserID).setValue(trainee);
                                    Intent intent = new Intent(getActivity(), afterLoginActiviry.class);
                                    intent.putExtra("isCoach", false);
                                    dialog.cancel();
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            }
                            else{
                                utils.makeSimplePopup("Oops","Something happend plz try again",getActivity());
                                dialog.cancel();
                            }
                        }
                    });

                }
                else{
                    utils.makeSimplePopup("Error in input","Please make sure you fill all the input fields",getActivity());
                }
            }
        });

        //endregion

        return view;
    }

    private boolean checkFields() {
        boolean isValid = true;

        String FirstName = etFirstName.getText().toString();
        FirstName.trim();
        if (FirstName.equals("")) {
            isValid = false;
        }
        if (isEnglishOrHebrew(FirstName)){
            etFirstName.setText(FirstName);
        }
        else{
            isValid = false;
        }

        String LastName = etLastName.getText().toString();
        LastName.trim();
        if (LastName.equals("")) {
            isValid = false;
        }
        if (isEnglishOrHebrew(LastName)) {
            etLastName.setText(LastName);
        }
        else {
            isValid = false;
        }

        if(radioTrainerOrTrainee.getCheckedRadioButtonId() == -1){
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


}