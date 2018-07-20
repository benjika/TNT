package com.example.luput.tnt;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateProgramFragment extends Fragment implements View.OnClickListener {
    Context context;
    Spinner spinner1;
    private int valOfSpinner;
    String[] musclesGroups = {"Chest", "Back", "Biceps", "Triceps", "Legs", "Shoulders", "ABs"};
    FloatingActionButton floatingActionButton;


    public void setValOfSpinner(int valOfSpinner) {
        this.valOfSpinner = valOfSpinner;
    }

    public CreateProgramFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_program, container, false);
        context = container.getContext();
        valOfSpinner = -1;
        //addListenerOnSpinnerItemSelection();
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.createProgram_fab);
        floatingActionButton.setOnClickListener(this);

        return view;
    }

    public void inflateNewDrillDialog() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        final View mView = layoutInflaterAndroid.inflate(R.layout.drill_new, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(mView);

        Spinner spinner = (Spinner) mView.findViewById(R.id.drillNew_MuscleGroupSpinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListenerAddProgramSpinner());


        CustomOnItemSelectedListenerAddProgramSpinner customOnItemSelectedListenerAddProgramSpinner
                = new CustomOnItemSelectedListenerAddProgramSpinner();

        final TextInputEditText addtraineeET = (TextInputEditText) mView.findViewById(R.id.coach_add_trainee_email);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        if (valOfSpinner == -1) {
                            Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();
                        } else {
                            final String muscleGroup = musclesGroups[valOfSpinner];

                            EditText nameOfDrillET = (EditText) mView.findViewById(R.id.drillNew_drillName);
                            final String nameOfDrillSTR = nameOfDrillET.toString();

                            EditText numOfSetsET = (EditText) mView.findViewById(R.id.drillNew_NumberOfSets);
                            final int numOfSetsINT = Integer.parseInt(numOfSetsET.toString());

                            EditText weightInKgET = (EditText) mView.findViewById(R.id.drillNew_weightInKg);
                            final Float weightInKgFL = Float.parseFloat(weightInKgET.toString());

                            EditText numOfRepsET = (EditText) mView.findViewById(R.id.drillNew_NumberOfRepeats);
                            final int numOfRepsINT = Integer.parseInt(numOfRepsET.toString());

                            EditText RestInSecondsET = (EditText) mView.findViewById(R.id.drillNew_RestInSeconds);
                            final int RestInSecondsINT = Integer.parseInt(RestInSecondsET.toString());

                            EditText drillDescriptionET = (EditText) mView.findViewById(R.id.drillNew_drillDescription);
                            final String drillDescriptionSTR = drillDescriptionET.toString();

                            EditText LinkToDrillMovieET = (EditText) mView.findViewById(R.id.drillNew_LinkToDrillMovie);
                            final String LinkToDrillMovieSTR = LinkToDrillMovieET.toString();

                            ExerciseDrill exerciseDrill = new ExerciseDrill(nameOfDrillSTR,
                                    LinkToDrillMovieSTR, weightInKgFL, numOfSetsINT,
                                    numOfRepsINT, RestInSecondsINT, drillDescriptionSTR);

                            /*final String emailToAdd = addtraineeET.getText().toString();
                            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot trainee : dataSnapshot.child("trainee").getChildren()) {
                                        Trainee currTraine = trainee.getValue(Trainee.class);
                                        if (currTraine.getEmailAddress().equals(emailToAdd)) {
                                            key = trainee.getKey();
                                            break;
                                        }
                                    }
                                    if (key != null) {
                                        Coach coach = dataSnapshot.child("coach").child(UserID).getValue(Coach.class);
                                        if (coach.getTrainees() != null) {
                                            ArrayList<String> newlist = new ArrayList<String>(coach.getTrainees());
                                            newlist.add(key);
                                            coach.setTrainees(newlist);
                                        } else {
                                            ArrayList<String> newlist = new ArrayList<String>();
                                            newlist.add(key);
                                            coach.setTrainees(newlist);
                                        }
                                        mDatabase.child("coach").child(UserID).setValue(coach);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });*/
                        }
                    }
                });
        alertDialogBuilderUserInput.setNegativeButton(getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) getView().findViewById(R.id.drillNew_MuscleGroupSpinner);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListenerAddProgramSpinner());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.createProgram_fab):
                inflateNewDrillDialog();
                break;
        }
    }
}

