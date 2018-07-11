package com.example.luput.tnt;



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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.content.ContentValues.TAG;


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
    private FirebaseAuth mAuth;
    private DatabaseReference DB = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth.AuthStateListener AuthListenerl;

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

        radioTrainerOrTrainee = (RadioGroup) view.findViewById(R.id.radio_trainerOrTrainee);
        etFirstName = (EditText) view.findViewById(R.id.signup_firstName);
        etLastName = (EditText) view.findViewById(R.id.signup_lastName);
        Email = view.findViewById(R.id.signup_email);
        Password = view.findViewById(R.id.signup_password);
        btnSignUp = (Button) view.findViewById(R.id.signup_submit);
        mAuth = FirebaseAuth.getInstance();

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

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkFields()){
                    final String UserName = Email.getText().toString();
                    String password = Password.getText().toString();
                    mAuth.createUserWithEmailAndPassword(UserName,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String UserID = mAuth.getCurrentUser().getUid();
                                Log.d("CK", UserID);
                                if(isCoach){
                                    DB.child("coach").child(UserID).setValue(UserID);
                                    Coach coach = new Coach(etFirstName.getText().toString(),etLastName.getText().toString(),UserName);
                                    DB.child("coach").child(UserID).setValue(coach);
                                }
                                else {
                                    DB.child("trainee").child(UserID).setValue(UserID);
                                    Trainee trainee = new Trainee(etFirstName.getText().toString(),etLastName.getText().toString(),UserName);
                                    DB.child("trainee").child(UserID).setValue(trainee);
                                }
                            }
                        }
                    });

                }
            }
        });




        return view;
    }

    private boolean checkFields() {
        boolean isValid = true;

        //Check first name
        String FirstName = etFirstName.getText().toString();
        FirstName.trim();
        if (FirstName.equals("")) isValid=false;
        if (isEnglishOrHebrew(FirstName)) etFirstName.setText(FirstName);
        else isValid = false;

        //Check last name
        String LastName = etLastName.getText().toString();
        LastName.trim();
        if (LastName.equals("")) isValid=false;
        if (isEnglishOrHebrew(LastName)) etLastName.setText(LastName);
        else isValid=false;

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
/*
    private static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }*/
    /*
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );
       /* File image;
        image = File.createTempFile(imageFileName, ".jpg",  getActivity().getApplicationContext().getCacheDir());

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
*/
}