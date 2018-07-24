package com.example.luput.tnt;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateProgramFragment extends Fragment implements View.OnClickListener {
    Context context;
    FloatingActionButton floatingActionButton;
    private Button daysOfProgramBTN;
    private Button finishBTN;
    private DaysOfTrainning daysOfTrainningArr;
    Trainee trainee;
    String traineeId;
    private List<TrainingProgram> programs = new ArrayList<>();
    TrainingProgram trainingProgramToAdd;
    private boolean daysWereChosen = false;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    int musculeGroup;
    ExpandableLayout expandableLayout;

    public CreateProgramFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_program, container, false);


        context = container.getContext();

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

        expandableLayout = (ExpandableLayout) view.findViewById(R.id.createProgram_expandable);
        expandableLayout.setRenderer(new ExpandableLayout.Renderer<MusculeCategory, ExerciseDrill>() {
            @Override
            public void renderParent(View view, MusculeCategory musculeCategory, boolean isExpanded, int parentPosition) {
                ((TextView) view.findViewById(R.id.expandable_parentName)).setText(musculeCategory.name);
                view.findViewById(R.id.expandable_arrow).setBackgroundResource
                        (isExpanded ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down);
            }

            @Override
            public void renderChild(View view2, ExerciseDrill exerciseDrill, int parentPosition, int childPosition) {
                ((TextView) view2.findViewById(R.id.drillHolder_drillName)).
                        setText(exerciseDrill.getNameOfExercise());
                ((TextView) view2.findViewById(R.id.drillHolder_NumberOfSets)).
                        setText(exerciseDrill.getNumberOfSets() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_weightInKg)).
                        setText(exerciseDrill.getWeightInKg() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_NumberOfRepeats)).
                        setText(exerciseDrill.getNumberOfRepeat() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_RestInSeconds)).
                        setText(exerciseDrill.getNumberOfRestInSeconds() + "");
                if (!(exerciseDrill.getDescription()).equals("")) {
                    ((TextView) view2.findViewById(R.id.drillHolder_drillDescription)).
                            setText(exerciseDrill.getDescription());
                } else {
                    ((TextView) view2.findViewById(R.id.drillHolder_drillDescription)).
                            setVisibility(View.GONE);
                }
                if (!(exerciseDrill.getLinkToVideo()).equals("")) {
                    final String str = exerciseDrill.getLinkToVideo();
                    ((TextView) view2.findViewById(R.id.drillHolder_LinkToDrillMovie)).
                            setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view1) {
                                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                            Uri.parse(str + ""));
                                    try {
                                        startActivity(webIntent);
                                    } catch (ActivityNotFoundException ex) {
                                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    ((TextView) view2.findViewById(R.id.drillHolder_LinkToDrillMovie)).setVisibility(View.GONE);
                }

            }

        });
        //  initExpandableLayout();


        return view;
    }

    public void inflateNewDrillDialog() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        final View mView = layoutInflaterAndroid.inflate(R.layout.drill_new, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(mView);
        final Context context1 = alertDialogBuilderUserInput.getContext();
        musculeGroup = 0;
        Spinner spinner = (Spinner) mView.findViewById(R.id.drillNew_MuscleGroupSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.muscle_groups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                musculeGroup = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                musculeGroup = 0;
            }
        });


        CustomOnItemSelectedListenerAddProgramSpinner customOnItemSelectedListenerAddProgramSpinner
                = new CustomOnItemSelectedListenerAddProgramSpinner();

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        String[] muscles = getResources().getStringArray(R.array.muscle_groups);

                        EditText nameOfDrillET = (EditText) mView.findViewById(R.id.drillNew_drillName);
                        if ((nameOfDrillET.getText().toString()).equals("")) {
                            Toast.makeText(context1, "", Toast.LENGTH_SHORT).show();
                        } else {
                            final String nameOfDrillSTR = nameOfDrillET.getText().toString();
                            EditText numOfSetsET = (EditText) mView.findViewById(R.id.drillNew_NumberOfSets);
                            if ((numOfSetsET.getText().toString()).equals("") ||
                                    Integer.parseInt(numOfSetsET.getText().toString()) < 1) {
                                Toast.makeText(context1, "", Toast.LENGTH_SHORT).show();
                            } else {
                                final int numOfSetsINT = Integer.parseInt(numOfSetsET.getText().toString());
                                EditText weightInKgET = (EditText) mView.findViewById(R.id.drillNew_weightInKg);
                                if ((weightInKgET.getText().toString()).equals("") ||
                                        Float.parseFloat(numOfSetsET.getText().toString()) < 0)
                                    Toast.makeText(context1, "", Toast.LENGTH_SHORT).show();
                                else {
                                    final Float weightInKgFL = Float.parseFloat(weightInKgET.getText().toString());
                                    EditText numOfRepsET = (EditText) mView.findViewById(R.id.drillNew_NumberOfRepeats);
                                    if ((numOfRepsET.getText().toString()).equals("") ||
                                            Integer.parseInt(numOfSetsET.getText().toString()) < 0)
                                        Toast.makeText(context1, "", Toast.LENGTH_SHORT).show();
                                    else {
                                        final int numOfRepsINT = Integer.parseInt(numOfRepsET.getText().toString());
                                        EditText RestInSecondsET = (EditText) mView.findViewById(R.id.drillNew_RestInSeconds);
                                        if ((RestInSecondsET.getText().toString()).equals("") ||
                                                Integer.parseInt(RestInSecondsET.getText().toString()) < 0)
                                            Toast.makeText(context1, "", Toast.LENGTH_SHORT).show();
                                        else {

                                            final int RestInSecondsINT = Integer.parseInt(RestInSecondsET.getText().toString());

                                            EditText drillDescriptionET = (EditText) mView.findViewById(R.id.drillNew_drillDescription);
                                            final String drillDescriptionSTR = drillDescriptionET.getText().toString();

                                            EditText LinkToDrillMovieET = (EditText) mView.findViewById(R.id.drillNew_LinkToDrillMovie);
                                            if (!(LinkToDrillMovieET.getText().toString()).equals("") &&
                                                    (linkToId(LinkToDrillMovieET.getText().toString()).equals(""))) {
                                                Toast.makeText(context1, "", Toast.LENGTH_SHORT).show();
                                            } else {
                                                final String LinkToDrillMovieSTR = linkToId(LinkToDrillMovieET.getText().toString());

                                                ExerciseDrill exerciseDrill = new ExerciseDrill(nameOfDrillSTR,
                                                        LinkToDrillMovieSTR, weightInKgFL, numOfSetsINT,
                                                        numOfRepsINT, RestInSecondsINT, drillDescriptionSTR);
                                                if (musculeGroup == 1) {
                                                    trainingProgramToAdd.addNewDrillChest(exerciseDrill);
                                                    if (trainingProgramToAdd.getSizeOfDrillsChest() == 1) {
                                                        Section<MusculeCategory, ExerciseDrill> section = new Section<>();
                                                        section.parent = new MusculeCategory(muscles[1]);
                                                        section.children = trainingProgramToAdd.getListOfDrillsChest();
                                                        expandableLayout.addSection(section);
                                                    } else
                                                        expandableLayout.addChildren(new MusculeCategory(muscles[1]), trainingProgramToAdd.getListOfDrillsChest());
                                                } else if (musculeGroup == 2) {
                                                    trainingProgramToAdd.addNewDrillBack(exerciseDrill);
                                                    if (trainingProgramToAdd.getSizeOfDrillsBack() == 1) {
                                                        Section<MusculeCategory, ExerciseDrill> section = new Section<>();
                                                        section.parent = new MusculeCategory(muscles[2]);
                                                        section.children = trainingProgramToAdd.getListOfDrillsBack();
                                                        expandableLayout.addSection(section);
                                                    } else
                                                        expandableLayout.addChildren(new MusculeCategory(muscles[2]), trainingProgramToAdd.getListOfDrillsBack());
                                                } else if (musculeGroup == 3) {
                                                    trainingProgramToAdd.addNewDrillBiceps(exerciseDrill);
                                                    if (trainingProgramToAdd.getSizeOfDrillsBiceps() == 1) {
                                                        Section<MusculeCategory, ExerciseDrill> section = new Section<>();
                                                        section.parent = new MusculeCategory(muscles[3]);
                                                        section.children = trainingProgramToAdd.getListOfDrillsBiceps();
                                                        expandableLayout.addSection(section);
                                                    } else
                                                        expandableLayout.addChildren(new MusculeCategory(muscles[3]), trainingProgramToAdd.getListOfDrillsBiceps());
                                                } else if (musculeGroup == 4) {
                                                    trainingProgramToAdd.addNewDrillTriceps(exerciseDrill);
                                                    if (trainingProgramToAdd.getSizeOfDrillsTriceps() == 1) {
                                                        Section<MusculeCategory, ExerciseDrill> section = new Section<>();
                                                        section.parent = new MusculeCategory(muscles[4]);
                                                        section.children = trainingProgramToAdd.getListOfDrillsTriceps();
                                                        expandableLayout.addSection(section);
                                                    } else
                                                        trainingProgramToAdd.addNewDrillTriceps(exerciseDrill);
                                                    expandableLayout.addChildren(new MusculeCategory(muscles[4]), trainingProgramToAdd.getListOfDrillsTriceps());
                                                } else if (musculeGroup == 5) {
                                                    trainingProgramToAdd.addNewDrillLegs(exerciseDrill);
                                                    if (trainingProgramToAdd.getSizeOfDrillsLegs() == 1) {
                                                        Section<MusculeCategory, ExerciseDrill> section = new Section<>();
                                                        section.parent = new MusculeCategory(muscles[5]);
                                                        section.children = trainingProgramToAdd.getListOfDrillsLegs();
                                                        expandableLayout.addSection(section);
                                                    } else
                                                        expandableLayout.addChildren(new MusculeCategory(muscles[5]), trainingProgramToAdd.getListOfDrillsLegs());
                                                } else if (musculeGroup == 6) {
                                                    trainingProgramToAdd.addNewDrillShoulders(exerciseDrill);
                                                    if (trainingProgramToAdd.getSizeOfDrillsShoulders() == 1) {
                                                        Section<MusculeCategory, ExerciseDrill> section = new Section<>();
                                                        section.parent = new MusculeCategory(muscles[6]);
                                                        section.children = trainingProgramToAdd.getListOfDrillsShoulders();
                                                        expandableLayout.addSection(section);
                                                    } else
                                                        expandableLayout.addChildren(new MusculeCategory(muscles[6]), trainingProgramToAdd.getListOfDrillsShoulders());
                                                } else if (musculeGroup == 7) {
                                                    trainingProgramToAdd.addNewDrillABs(exerciseDrill);
                                                    if (trainingProgramToAdd.getSizeOfDrillsABs() == 1) {
                                                        Section<MusculeCategory, ExerciseDrill> section = new Section<>();
                                                        section.parent = new MusculeCategory(muscles[7]);
                                                        section.children = trainingProgramToAdd.getListOfDrillsABs();
                                                        expandableLayout.addSection(section);
                                                    } else
                                                        expandableLayout.addChildren(new MusculeCategory(muscles[7]), trainingProgramToAdd.getListOfDrillsABs());
                                                } else {
                                                    Toast.makeText(context1, getResources().getString(R.string.noMuscleGroup), Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
        alertDialogBuilderUserInput.setNegativeButton(

                getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
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
        } else if (trainingProgramToAdd.getSizeOfTotalPrograms() == 0) {
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
                    fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), coachEditProgramFragment);
                    fragmentTransaction.commit();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
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



    void initExpandableLayout() {
        expandableLayout.setRenderer(new ExpandableLayout.Renderer<MusculeCategory, ExerciseDrill>() {
            @Override
            public void renderParent(View view, MusculeCategory musculeCategory, boolean isExpanded, int parentPosition) {
                ((TextView) view.findViewById(R.id.expandable_parentName)).setText(musculeCategory.name);
                view.findViewById(R.id.expandable_arrow).setBackgroundResource
                        (isExpanded ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down);
            }

            @Override
            public void renderChild(View view2, ExerciseDrill exerciseDrill, int parentPosition, int childPosition) {
                ((TextView) view2.findViewById(R.id.drillHolder_drillName)).
                        setText(exerciseDrill.getNameOfExercise());
                ((TextView) view2.findViewById(R.id.drillHolder_NumberOfSets)).
                        setText(exerciseDrill.getNumberOfSets() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_weightInKg)).
                        setText(exerciseDrill.getWeightInKg() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_NumberOfRepeats)).
                        setText(exerciseDrill.getNumberOfRepeat() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_RestInSeconds)).
                        setText(exerciseDrill.getNumberOfRestInSeconds() + "");
                if (!(exerciseDrill.getDescription()).equals("")) {
                    ((TextView) view2.findViewById(R.id.drillHolder_drillDescription)).
                            setText(exerciseDrill.getDescription());
                } else {
                    ((TextView) view2.findViewById(R.id.drillHolder_drillDescription)).
                            setVisibility(View.GONE);
                }
                if (!(exerciseDrill.getLinkToVideo()).equals("")) {
                    final String str = exerciseDrill.getLinkToVideo();
                    ((TextView) view2.findViewById(R.id.drillHolder_LinkToDrillMovie)).
                            setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view1) {
                                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                            Uri.parse(str + ""));
                                    try {
                                        startActivity(webIntent);
                                    } catch (ActivityNotFoundException ex) {
                                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    ((TextView) view2.findViewById(R.id.drillHolder_LinkToDrillMovie)).setVisibility(View.GONE);
                }

            }

        });
        /*
        expandableLayout.setRenderer(new ExpandableLayout.Renderer<MusculeCategory, ExerciseDrill>() {
            @Override
            public void renderParent(View view, String s, boolean isExpanded, int parentPosition) {
                ((TextView) view.findViewById(R.id.expandable_parentName)).setText(s);
                view.findViewById(R.id.expandable_arrow).setBackgroundResource
                        (isExpanded ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down);
            }

            @Override
            public void renderChild(View view2, final ExerciseDrill exerciseDrill, int parentPosition, int childPosition) {
                ((TextView) view2.findViewById(R.id.drillHolder_drillName)).
                        setText(exerciseDrill.getNameOfExercise());
                ((TextView) view2.findViewById(R.id.drillHolder_NumberOfSets)).
                        setText(exerciseDrill.getNumberOfSets() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_weightInKg)).
                        setText(exerciseDrill.getWeightInKg() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_NumberOfRepeats)).
                        setText(exerciseDrill.getNumberOfRepeat() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_RestInSeconds)).
                        setText(exerciseDrill.getNumberOfRestInSeconds() + "");
                if (!(exerciseDrill.getDescription()).equals("")) {
                    ((TextView) view2.findViewById(R.id.drillHolder_drillDescription)).
                            setText(exerciseDrill.getDescription());
                } else {
                    ((TextView) view2.findViewById(R.id.drillHolder_drillDescription)).
                            setVisibility(View.GONE);
                }
                if (!(exerciseDrill.getLinkToVideo()).equals("")) {
                    ((TextView) view2.findViewById(R.id.drillHolder_LinkToDrillMovie)).
                            setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view1) {
                                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                            Uri.parse(exerciseDrill.getLinkToVideo()));
                                    try {
                                        startActivity(webIntent);
                                    } catch (ActivityNotFoundException ex) {
                                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    ((TextView) view2.findViewById(R.id.drillHolder_LinkToDrillMovie)).setVisibility(View.GONE);
                }

            }
        });*/
    }

    void inputToExpandableLayout() {
        String[] musclesGroup = getResources().getStringArray(R.array.muscle_groups);
        if (trainingProgramToAdd.getSizeOfDrillsChest() > 0) {
            Section<String, ExerciseDrill> section = new Section<>();
            section.parent = musclesGroup[1];
            section.children = trainingProgramToAdd.getListOfDrillsChest();
            expandableLayout.addSection(section);
        }
        if (trainingProgramToAdd.getSizeOfDrillsBack() > 0) {
            Section<String, ExerciseDrill> section = new Section<>();
            section.parent = musclesGroup[2];
            section.children = trainingProgramToAdd.getListOfDrillsBack();
            expandableLayout.addSection(section);
        }
        if (trainingProgramToAdd.getSizeOfDrillsBiceps() > 0) {
            Section<String, ExerciseDrill> section = new Section<>();
            section.parent = musclesGroup[3];
            section.children = trainingProgramToAdd.getListOfDrillsBiceps();
            expandableLayout.addSection(section);
        }
        if (trainingProgramToAdd.getSizeOfDrillsTriceps() > 0) {
            Section<String, ExerciseDrill> section = new Section<>();
            section.parent = musclesGroup[4];
            section.children = trainingProgramToAdd.getListOfDrillsTriceps();
            expandableLayout.addSection(section);
        }
        if (trainingProgramToAdd.getSizeOfDrillsABs() > 0) {
            Section<String, ExerciseDrill> section = new Section<>();
            section.parent = musclesGroup[5];
            section.children = trainingProgramToAdd.getListOfDrillsABs();
            expandableLayout.addSection(section);
        }
        if (trainingProgramToAdd.getSizeOfDrillsShoulders() > 0) {
            Section<String, ExerciseDrill> section = new Section<>();
            section.parent = musclesGroup[6];
            section.children = trainingProgramToAdd.getListOfDrillsShoulders();
            expandableLayout.addSection(section);
        }
        if (trainingProgramToAdd.getSizeOfDrillsLegs() > 0) {
            Section<String, ExerciseDrill> section = new Section<>();
            section.parent = musclesGroup[7];
            section.children = trainingProgramToAdd.getListOfDrillsLegs();
            expandableLayout.addSection(section);
        }
    }

    String linkToId(String url) {
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        if (url.equals("")) return "";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url); //url is youtube url for which you want to extract the id.
        if (matcher.find()) {
            return matcher.group();
        } else return "";
    }
}

