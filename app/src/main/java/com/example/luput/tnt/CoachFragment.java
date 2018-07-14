package com.example.luput.tnt;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

public class CoachFragment extends Fragment implements View.OnClickListener {

    private static String TAG = "CoachFaragment";
    ArrayList<Trainee> traineeList = new ArrayList<>();

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String UserID;
    //FloatingActionButton floatingActionButton;
    Dialog addTraineeDialog;
    String key;
    TextView nothigToShow;
    Context context;

    com.github.clans.fab.FloatingActionMenu floatingActionMenu;
    com.github.clans.fab.FloatingActionButton floatingActionButton_mobile;
    com.github.clans.fab.FloatingActionButton floatingActionButton_email;

    public CoachFragment() {
        // Required empty public constructor
    }

    public static CoachFragment newInstance(String param1, String param2) {
        CoachFragment fragment = new CoachFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coach, container, false);
        context = container.getContext();
        UserID = firebaseUser.getUid();
        traineeList = traineesOfThisCoach(container, view);
        floatingActionMenu = view.findViewById(R.id.menu);
        floatingActionMenu.getMenuIconView().setColorFilter(Color.WHITE);
        floatingActionButton_email = view.findViewById(R.id.coach_menu_item_email);
        floatingActionButton_mobile = view.findViewById(R.id.coach_menu_item_mobile);
        floatingActionButton_email.setOnClickListener(this);
        floatingActionButton_mobile.setOnClickListener(this);
        floatingActionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // addTraineeDialog();
            }
        });

        //Init Adapter
        if (traineeList.size() == 0) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.coach_recyclerView);
            recyclerView.setVisibility(View.GONE);
            nothigToShow = view.findViewById(R.id.coach_nothingToShow);
            nothigToShow.setVisibility(View.VISIBLE);
        } else {
            //region temp
            /*
            RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.coach_recyclerView);
            CoachAdapter coachAdapter = new CoachAdapter(container.getContext(), traineeList);
            recyclerView.setAdapter(coachAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

            coachAdapter.setOnItemClickListener(new CoachAdapter.MyClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    String email = traineeList.get(position).getEmailAddress();
                    FragmentManager fragmentManager = getFragmentManager();
                    Bundle bundle = new Bundle();

                    for (Trainee trainee : traineeList) {
                        if (trainee.getEmailAddress().equals(email)) {
                            bundle.putSerializable("Trainee", trainee);
                        }
                    }
                    CoachEditProgramFragment coachEditProgramFragment = CoachEditProgramFragment.newInstance();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(container.getId(), coachEditProgramFragment);
                    fragmentTransaction.commit();
                }
            });
            */
            //endregion
        }

        return view;
    }


    public void addTraineeDialog() {
        addTraineeDialog = new Dialog(getView().getContext());
        addTraineeDialog.setContentView(R.layout.coach_add_trainee_email_dialog);
        addTraineeDialog.setTitle("Add trainee");

        /*final Button addTrainee = (Button) addTraineeDialog.findViewById(R.id.coach_addTrainee_dialog_BTNAdd);
        Button cancelTrainee = (Button) addTraineeDialog.findViewById(R.id.coach_addTrainee_dialog_BTNCancel);
        final EditText addtraineeET = (EditText) addTraineeDialog.findViewById(R.id.coach_addTrainee_ET);
        addTrainee.setEnabled(true);
        cancelTrainee.setEnabled(true);

        addTrainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailToAdd = addtraineeET.getText().toString();
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
                });
            }
        });

        cancelTrainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTraineeDialog.cancel();
            }
        });
        addTraineeDialog.show();*/
    }

    private ArrayList<Trainee> traineesOfThisCoach(final ViewGroup container, final View view) {
        final ArrayList<Trainee> listToReturn = new ArrayList<>();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Coach coach = dataSnapshot.child("coach").child(UserID).getValue(Coach.class);
                if (coach.getTrainees() != null) {
                    if (!coach.getTrainees().isEmpty()) {
                        List<String> listOfTraineesUID = new ArrayList<String>();
                        listOfTraineesUID = coach.getTrainees();
                        for (String Uid : listOfTraineesUID) {
                            Trainee traineeToAdd = dataSnapshot.child("trainee").child(Uid).getValue(Trainee.class);
                            listToReturn.add(traineeToAdd);
                        }

                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.coach_recyclerView);
                        CoachAdapter coachAdapter = new CoachAdapter(container.getContext(), traineeList);
                        recyclerView.setAdapter(coachAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
                        recyclerView.setVisibility(View.VISIBLE);
                        nothigToShow.setVisibility(View.GONE);

                        coachAdapter.setOnItemClickListener(new CoachAdapter.MyClickListener() {
                            @Override
                            public void onItemClick(int position, View v) {
                                String email = traineeList.get(position).getEmailAddress();
                                FragmentManager fragmentManager = getFragmentManager();
                                Bundle bundle = new Bundle();

                                for (Trainee trainee : traineeList) {
                                    if (trainee.getEmailAddress().equals(email)) {
                                        bundle.putSerializable("Trainee", trainee);
                                    }
                                }
                                CoachEditProgramFragment coachEditProgramFragment = CoachEditProgramFragment.newInstance();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(container.getId(), coachEditProgramFragment);
                                fragmentTransaction.commit();
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        sort(listToReturn);
        return listToReturn;
    }

    public List<Trainee> getTraineeList() {
        return traineeList;
    }

    public void editTrainee(String email) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coach_menu_item_email:
              inflateEmailDialog();
               // Toast.makeText(context, "added by email", Toast.LENGTH_SHORT).show();
                floatingActionMenu.close(true);
                break;
            case R.id.coach_menu_item_mobile:
                Toast.makeText(context, "added by phone number", Toast.LENGTH_SHORT).show();
                floatingActionMenu.close(true);
                break;
        }


    }

    public void inflateEmailDialog() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View mView = layoutInflaterAndroid.inflate(R.layout.coach_add_trainee_email_dialog, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(mView);

        final TextInputEditText addtraineeET = (TextInputEditText) mView.findViewById(R.id.coach_add_trainee_email);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        final String emailToAdd = addtraineeET.getText().toString();
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
                        });
                    }
                });
        alertDialogBuilderUserInput.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }
}

