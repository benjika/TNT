package com.example.luput.tnt;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginFragment extends Fragment {

    TextInputEditText UserName;
    TextInputEditText Password;
    Button LoginBTN;
<<<<<<< HEAD
    String Email;
    String password;


=======
    String password;
    String Email;
>>>>>>> origin/master

    private FragmentManager fragmentManager;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference DBref = FirebaseDatabase.getInstance().getReference();


    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        //mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        //mAuth.removeAuthStateListener(mAuthListener);
    }


    @Override
<<<<<<< HEAD
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
=======
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
>>>>>>> origin/master
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        UserName = view.findViewById(R.id.Login_email);
        Password = view.findViewById(R.id.Login_password);
        LoginBTN = view.findViewById(R.id.btn_sign_in);

<<<<<<< HEAD


        LoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Email = UserName.getText().toString();
                password = Password.getText().toString();
                final int temp = view1.getId();
=======
        Email = UserName.getText().toString();
        password = Password.getText().toString();

        LoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
>>>>>>> origin/master
                mAuth.signInWithEmailAndPassword(Email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userUID = mAuth.getCurrentUser().getUid();
                            if (DBref.child("trainee").child(userUID).getKey().isEmpty()) {
<<<<<<< HEAD
                                Intent intent = new Intent(getActivity(),afterLoginActiviry.class);
                                intent.putExtra("isCoach",true);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getActivity(),afterLoginActiviry.class);
                                intent.putExtra("isCoach",false);
                                startActivity(intent);
=======
                                //move to coach
                            } else {
                                //move to traineefragment
                                TraineeFragment fragment = new TraineeFragment();
                                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.add(view.getId(), fragment);
                                fragmentTransaction.commit();
>>>>>>> origin/master
                            }
                        } else {
                            //make popup
                        }
                    }
                });
<<<<<<< HEAD
            }
        });
=======

            }
        });


>>>>>>> origin/master
        return view;
    }
}
