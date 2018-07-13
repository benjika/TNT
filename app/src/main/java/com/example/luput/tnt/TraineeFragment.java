package com.example.luput.tnt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TraineeFragment extends Fragment {

    RecyclerView Training_programs;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    String UserID;
    Trainee Current_Trainee;
    static View view;
    List<TrainingProgram> programs;


    public TraineeFragment() {
    }

<<<<<<< HEAD
=======
    static View view;

>>>>>>> origin/master
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setdata();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        view = inflater.inflate(R.layout.fragment_trainee, container, false);
<<<<<<< HEAD

        Training_programs = (RecyclerView) view.findViewById(R.id.training_program_recyclerView);
=======
        Training_programs = (RecyclerView) view.findViewById(R.id.training_program_recyclerView);
        final List<TrainingProgram> programs;
>>>>>>> origin/master
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserID = user.getUid();


        return view;
    }

    private void setdata() {
        mDatabase.child("trainee").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot trainee : dataSnapshot.getChildren()) {
<<<<<<< HEAD
                    if (trainee.getKey().toString().equals(UserID)) {
=======
                    if (trainee.getValue().toString().equals(UserID)) {
>>>>>>> origin/master
                        Current_Trainee = trainee.getValue(Trainee.class);
                        break;
                    }
                }
<<<<<<< HEAD

                if(Current_Trainee.getPrograms() != null) {
                    programs = fillProgram(Current_Trainee.getPrograms());

                    final ProgramAdapter programAdapter = new ProgramAdapter(programs);
=======
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        programs = fillProgram(Current_Trainee.getPrograms());
>>>>>>> origin/master

                    programAdapter.setListener(new ProgramAdapter.MyProgramListener() {
                        @Override
                        public void onProgramClick(int position, View view) {
                            TrainingProgram ClickedProgram = programs.get(position);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("ProgramToShow", ClickedProgram);
                            FullProgramFragment fullProgramFragment = new FullProgramFragment();
                            fullProgramFragment.setArguments(bundle);
                            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.add(view.getId(), fullProgramFragment)
                                    .commit();
                        }
                    });

                    Training_programs.setAdapter(programAdapter);
                }
            }

            @Override
<<<<<<< HEAD
            public void onCancelled(DatabaseError databaseError) {
=======
            public void onProgramClick(int position, View view) {
                TrainingProgram ClickedProgram = programs.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ProgramToShow", ClickedProgram);
                FullProgramFragment fullProgramFragment = new FullProgramFragment();
                fullProgramFragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(view.getId(), fullProgramFragment)
                        .commit();
>>>>>>> origin/master
            }
        });
    }

    private List<TrainingProgram> fillProgram(List<String> ProgramsID) {
        final List<TrainingProgram> returnList = new ArrayList<TrainingProgram>();
        final List<String> Programs = ProgramsID;

        mDatabase.child("programs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (String CurrentProgram : Programs) {
                    returnList.add(dataSnapshot.child(CurrentProgram).getValue(TrainingProgram.class));
                }
            }
<<<<<<< HEAD

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }


=======

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
>>>>>>> origin/master
        });

        return returnList;
    }
}
