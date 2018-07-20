package com.example.luput.tnt;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateProgramFragment extends Fragment implements View.OnClickListener {
    Context context;
    Spinner spinner1;
    private int valOfSpinner;
    String[] musclesGroups = {"Chest", "Back", "Biceps", "Triceps", "Legs", "Shoulders", "ABs"};
    FloatingActionButton floatingActionButton;
    private Button daysOfProgramBTN;
    private Button finishBTN;
    private DaysOfTrainning daysOfTrainningArr;
    Trainee trainee;
    String traineeId;
    private List<TrainingProgram> programs = new ArrayList<>();
    TrainingProgram trainingProgramToAdd;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private boolean daysWereChosen = false;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    public void setValOfSpinner(int valOfSpinner) {
        this.valOfSpinner = valOfSpinner;
    }

    public CreateProgramFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(adapter);
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
        daysOfProgramBTN = (Button) view.findViewById(R.id.createProgram_chooseDaysBTN);
        daysOfProgramBTN.setOnClickListener(this);
        daysOfTrainningArr = new DaysOfTrainning(false, false, false,
                false, false, false, false);
        trainee = (Trainee) getArguments().getSerializable("Trainee");
        traineeId = (String) getArguments().getString("TraineeUid");
        programs = trainee.getPrograms();
        trainingProgramToAdd = new TrainingProgram();

        finishBTN = view.findViewById(R.id.createProgram_finish);
        finishBTN.setOnClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.createProgram_recyclerView);
        recyclerView.setHasFixedSize(false);

        adapter = new CreateProgramAdapter(trainingProgramToAdd.getListOfDrills(), context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
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

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                       /* if (valOfSpinner == -1) {
                            Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();
                        } else {*/
                        //    final String muscleGroup = musclesGroups[valOfSpinner];

                        EditText nameOfDrillET = (EditText) mView.findViewById(R.id.drillNew_drillName);
                        final String nameOfDrillSTR = nameOfDrillET.getText().toString();

                        EditText numOfSetsET = (EditText) mView.findViewById(R.id.drillNew_NumberOfSets);
                        final int numOfSetsINT = Integer.parseInt(numOfSetsET.getText().toString());

                        EditText weightInKgET = (EditText) mView.findViewById(R.id.drillNew_weightInKg);
                        final Float weightInKgFL = Float.parseFloat(weightInKgET.getText().toString());

                        EditText numOfRepsET = (EditText) mView.findViewById(R.id.drillNew_NumberOfRepeats);
                        final int numOfRepsINT = Integer.parseInt(numOfRepsET.getText().toString());

                        EditText RestInSecondsET = (EditText) mView.findViewById(R.id.drillNew_RestInSeconds);
                        final int RestInSecondsINT = Integer.parseInt(RestInSecondsET.getText().toString());

                        EditText drillDescriptionET = (EditText) mView.findViewById(R.id.drillNew_drillDescription);
                        final String drillDescriptionSTR = drillDescriptionET.getText().toString();

                        EditText LinkToDrillMovieET = (EditText) mView.findViewById(R.id.drillNew_LinkToDrillMovie);
                        final String LinkToDrillMovieSTR = LinkToDrillMovieET.getText().toString();

                        ExerciseDrill exerciseDrill = new ExerciseDrill(nameOfDrillSTR,
                                LinkToDrillMovieSTR, weightInKgFL, numOfSetsINT,
                                numOfRepsINT, RestInSecondsINT, drillDescriptionSTR);
                        trainingProgramToAdd.addNewDrill(exerciseDrill);
                        adapter.notifyDataSetChanged();
                        /*adapter.notifyItemInserted(trainingProgramToAdd.getListOfDrills().size()); /*= new CreateProgramAdapter(trainingProgramToAdd.getListOfDrills()
                                    ,context);
                        recyclerView.setAdapter(adapter);*/

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
                        //  }
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

    public void inflateChooseDayDialog() {
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final String[] daysOfWeek = getResources().getStringArray(R.array.daysOfWeek);
        final boolean[] selected = new boolean[7];
        for (int i = 0; i < selected.length; i++) {
            switch (i) {
                case 0:
                    selected[0] = daysOfTrainningArr.isSunday();
                    break;
                case 1:
                    selected[1] = daysOfTrainningArr.isMonday();
                    break;
                case 2:
                    selected[2] = daysOfTrainningArr.isTuesday();
                    break;
                case 3:
                    selected[3] = daysOfTrainningArr.isWednesday();
                    break;
                case 4:
                    selected[4] = daysOfTrainningArr.isThursday();
                    break;
                case 5:
                    selected[1] = daysOfTrainningArr.isFriday();
                    break;
                case 6:
                    selected[1] = daysOfTrainningArr.isSaturday();
                    break;
            }
        }
        builder.setTitle("Choose days");
        builder.setMultiChoiceItems(daysOfWeek, selected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                selected[position] = isChecked;
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        daysOfTrainningArr.setSunday(selected[0]);
                        daysOfTrainningArr.setMonday(selected[1]);
                        daysOfTrainningArr.setTuesday(selected[2]);
                        daysOfTrainningArr.setWednesday(selected[3]);
                        daysOfTrainningArr.setThursday(selected[4]);
                        daysOfTrainningArr.setFriday(selected[5]);
                        daysOfTrainningArr.setSaturday(selected[6]);

                        String strToShow = "";
                        for (int j = 0; j < selected.length; j++) {
                            switch (j) {
                                case 0:
                                    if (selected[j]) {
                                        strToShow = strToShow + "Sunday ";
                                        daysWereChosen = true;
                                    }
                                    break;
                                case 1:
                                    if (selected[j]) {
                                        strToShow = strToShow + "Monday ";
                                        daysWereChosen = true;
                                    }
                                    break;
                                case 2:
                                    if (selected[j]) {
                                        strToShow = strToShow + "Tuesday ";
                                        daysWereChosen = true;
                                    }
                                    break;
                                case 3:
                                    if (selected[j]) {
                                        strToShow = strToShow + "Wednesday ";
                                        daysWereChosen = true;
                                    }
                                    break;
                                case 4:
                                    if (selected[j]) {
                                        strToShow = strToShow + "Thursday ";
                                        daysWereChosen = true;
                                    }
                                    break;
                                case 5:
                                    if (selected[j]) {
                                        strToShow = strToShow + "Friday ";
                                        daysWereChosen = true;
                                    }
                                    break;
                                case 6:
                                    if (selected[j]) {
                                        strToShow = strToShow + "Saturday";
                                        daysWereChosen = true;
                                    }
                                    break;
                            }

                        }

                        if (strToShow.equals("")) {
                            daysWereChosen = false;
                            strToShow = "Choose days";
                        }
                        daysOfProgramBTN.setText(strToShow);
                    }

                }

        );
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }

    public void initFinish() {
        TextView nameOfProgram = (TextView) getView().findViewById(R.id.createProgram_nameOfProgram);
        String nameOfProgramSTR = nameOfProgram.getText().toString();
        if (nameOfProgramSTR.equals("")) {
            Toast.makeText(context, "Invalid Name", Toast.LENGTH_LONG).show();
        } else if (!daysWereChosen) {
            Toast.makeText(context, "No days were chosen", Toast.LENGTH_LONG).show();
        } else if (trainingProgramToAdd.getNumOfDrills() == 0) {
            Toast.makeText(context, "No drills were added", Toast.LENGTH_LONG).show();
        } else {
            trainingProgramToAdd.setDaysOfWorkOut(daysOfTrainningArr);
            trainingProgramToAdd.setNameOfTheProgram(nameOfProgramSTR);
            trainingProgramToAdd.setCurrentProgram(true);
            programs.add(trainingProgramToAdd);
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    trainee.setPrograms(programs);
                    mDatabase.child("trainee").child(traineeId).setValue(trainee);
                    CoachEditProgramFragment coachEditProgramFragment = new CoachEditProgramFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Trainee", trainee);
                    bundle.putString("TraineeUid", traineeId);
                    coachEditProgramFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(((ViewGroup)getView().getParent()).getId(), coachEditProgramFragment);
                    fragmentTransaction.commit();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
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

            case (R.id.createProgram_chooseDaysBTN):
                inflateChooseDayDialog();
                break;

            case (R.id.createProgram_finish):
                initFinish();
                break;
        }
    }
}

