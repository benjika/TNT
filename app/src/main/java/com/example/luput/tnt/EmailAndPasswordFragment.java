package com.example.luput.tnt;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailAndPasswordFragment extends Fragment {


    static View view;
    private  static EditText edEmail;
    private  static EditText edEmailConfirm;
    private  static EditText edPassword;
    private  static EditText edPasswordConfirm;
    private  static Button btnSingUp;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener mAuthListener;

    public EmailAndPasswordFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_email_and_password, container, false);

        edEmail = (EditText) view.findViewById(R.id.signup_email);
        edEmailConfirm = (EditText) view.findViewById(R.id.signup_email_confirm);
        edPassword = (EditText)  view.findViewById(R.id.signup_password);
        edPasswordConfirm = (EditText)  view.findViewById(R.id.signup_password_confirm);
        btnSingUp = (Button)  view.findViewById(R.id.btn_sign_up_continue);

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cheackFields()){
                    final String username = edEmail.getText().toString();
                    final String password = edPassword.getText().toString();
                    mAuth.createUserWithEmailAndPassword(username,password);
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    private boolean cheackFields() {
        boolean isValid = true;

        if(edEmail.getText().toString().equals(edEmailConfirm.getText().toString())){
            isValid = false;
        }
        if(edPassword.getText().toString().equals(edPasswordConfirm.getText().toString())){
            isValid = false;
        }

        return isValid;
    }

}
