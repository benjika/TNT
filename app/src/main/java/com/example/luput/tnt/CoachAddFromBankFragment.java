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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static java.util.Collections.sort;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoachAddFromBankFragment extends Fragment {

    Context context;
    Trainee trainee;
    String traineeId;
    String coachId;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    Coach coach;

    public CoachAddFromBankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coach_add_from_bank, container, false);

        context = container.getContext();
        trainee = (Trainee) getArguments().getSerializable("Trainee");
        traineeId = (String) getArguments().getString("TraineeUid");
        coachId = firebaseUser.getUid();
        coach = (Coach) getArguments().getSerializable("Coach");
        final List<TrainingProgram> coachProgramsFromBank = coach.getTrainingProgramBank();
        if (coachProgramsFromBank.size() == 0) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.coachAddFromBank_recycleView);
            recyclerView.setVisibility(View.GONE);
            TextView nothigToShow = (TextView) view.findViewById(R.id.coachAddFromBank_nothingToShow);
            nothigToShow.setVisibility(View.VISIBLE);
        } else {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.coachAddFromBank_recycleView);
            CoachAddFromBankAdapter coachAddFromBankAdapter = new CoachAddFromBankAdapter(coachProgramsFromBank);
            recyclerView.setAdapter(coachAddFromBankAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

            coachAddFromBankAdapter.setOnitemListener(new CoachAddFromBankAdapter.MyAddFromBankListener() {
                @Override
                public void onProgramClick(int position, View view) {
                    FullProgramFragment fullProgramFragment = new FullProgramFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ProgramToShow", trainee.getPrograms().get(position));
                    fullProgramFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), fullProgramFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                @Override
                public void onProgramLongClick(final int position, View view) {
                    LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
                    final View mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_from_bank, null);
                    AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
                    alertDialogBuilderUserInput.setView(mView).setCancelable(true)
                            .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            List<TrainingProgram> programs = trainee.getPrograms();
                            programs.add(coachProgramsFromBank.get(position));
                            mDatabase.child("trainee").child(traineeId).child("programs").setValue(programs);
                            trainee.setPrograms(programs);

                            CoachEditProgramFragment coachEditProgramFragment = new CoachEditProgramFragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("Trainee", trainee);
                            bundle.putSerializable("TraineeUid", traineeId);
                            bundle.putSerializable("Coach", coach);
                            coachEditProgramFragment.setArguments(bundle);
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), coachEditProgramFragment);
                            fragmentTransaction.commit();

                        }
                    });
                    alertDialogBuilderUserInput.show();

                }
            });

        }


        return view;
    }

}
