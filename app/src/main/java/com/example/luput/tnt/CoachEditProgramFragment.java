package com.example.luput.tnt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoachEditProgramFragment extends Fragment {


    private String email;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


    public CoachEditProgramFragment() {
        // Required empty public constructor
    }

    public static CoachEditProgramFragment newInstance() {
        return new CoachEditProgramFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coach_edit_program, container, false);
        Trainee trainee = (Trainee) getArguments().getSerializable("Trainee");


        return view;

    }

}
