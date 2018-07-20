package com.example.luput.tnt;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class FullProgramFragment extends Fragment {
    TextView ProgramHeadLine;
    RecyclerView TrainingProgramDrills;
    List<ExerciseDrill> Program;
    //DatabaseReference DB = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_trainingprogram, container, false);

        Bundle bundle = getArguments();
        final TrainingProgram ProgramToShow = (TrainingProgram) bundle.getSerializable("ProgramToShow");
        ProgramHeadLine = view.findViewById(R.id.Full_Program_Headline);
        TrainingProgramDrills = view.findViewById(R.id.full_training_program_drills);


        ProgramHeadLine.setText(ProgramToShow.getNameOfTheProgram());
        Program.addAll(ProgramToShow.getListOfDrills());

        //region stam
        /*DB.child("drills").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (String CurrentDrillID : ProgramToShow.getListOfDrillsForA()) {
                    ProgramA.add(dataSnapshot.child(CurrentDrillID).getValue(ExerciseDrill.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        if (ProgramToShow.getListOfDrillsForB() != null) { // cheack for program B
            DB.child("drills").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (String CurrentDrillID : ProgramToShow.getListOfDrillsForB()) {
                        ProgramA.add(dataSnapshot.child(CurrentDrillID).getValue(ExerciseDrill.class));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        } else {
            ProgramB = null;
        }
*/
        //endregion
        FullProgramAdapter adapter = new FullProgramAdapter(Program);
        TrainingProgramDrills.setAdapter(adapter);

        return view;
    }
}
