package com.example.luput.tnt;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CoachBankProgramsFragment extends Fragment {
    private String email;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private List<TrainingProgram> programs = new ArrayList<>();
    private FloatingActionMenu floatingActionMenu;
    private Context context;
    Coach coach;
    Trainee trainee;
    View view;
    String traineeId;
    FloatingActionButton floatingActionButton;

    @Override
    public void onResume() {
        super.onResume();
    }

    public CoachBankProgramsFragment() {
        // Required empty public constructor
    }

    public static CoachBankProgramsFragment newInstance() {
        return new CoachBankProgramsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_coach_bank_programs, container, false);
        context = container.getContext();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                coach = dataSnapshot.child("coach").child(firebaseUser.getUid()).getValue(Coach.class);
                programs = coach.getTrainingProgramBank();
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.coachBankPrograms_recycleView);

                if (programs == null || programs.size() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    TextView nothigToShow = (TextView) view.findViewById(R.id.coachBankPrograms_nothingToShow);
                    nothigToShow.setVisibility(View.VISIBLE);
                } else {
                    CoachBankProgramsAdapter coachBankProgramsAdapter = new CoachBankProgramsAdapter(programs);
                    recyclerView.setAdapter(coachBankProgramsAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    coachBankProgramsAdapter.setOnItemListener(new CoachBankProgramsAdapter.MyCoachBankProgramsListener() {
                        @Override
                        public void onProgramClick(int position, View view) {
                            FullProgramFragment fullProgramFragment = new FullProgramFragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("ProgramToShow", coach.getTrainingProgramBank().get(position));
                            fullProgramFragment.setArguments(bundle);
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), fullProgramFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.coachbankPrograms_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }
}

