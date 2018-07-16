package com.example.luput.tnt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoachEditProgramFragment extends Fragment {


    private String email;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private List<TrainingProgram> programs = new ArrayList<>();


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
        programs = trainee.getPrograms();

        if (programs == null || programs.size() == 0) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.coachEdit_recycleView);
            recyclerView.setVisibility(View.GONE);
            TextView nothigToShow = (TextView) view.findViewById(R.id.coachEdit_nothingToShow);
            nothigToShow.setVisibility(View.VISIBLE);
        } else {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.coachEdit_recycleView);
            CoachEditProgramAdapter coachEditProgramAdapter = new CoachEditProgramAdapter(programs);
            recyclerView.setAdapter(coachEditProgramAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

            coachEditProgramAdapter.setOnitemListener(new CoachEditProgramAdapter.MyEditProgramListener() {
                @Override
                public void onProgramClick(int position, View view) {
                    Toast.makeText(view.getContext(), "Will show program", Toast.LENGTH_LONG).show();
                }
            });
        }

        return view;

    }

}
