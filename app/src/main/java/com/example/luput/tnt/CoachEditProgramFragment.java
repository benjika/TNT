package com.example.luput.tnt;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoachEditProgramFragment extends Fragment implements View.OnClickListener {


    private String email;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private List<TrainingProgram> programs = new ArrayList<>();
    private FloatingActionMenu floatingActionMenu;
    private Context context;
    Coach coach;
    Trainee trainee;
    String traineeId;

    @Override
    public void onResume() {
        super.onResume();
    }

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
        context = container.getContext();
        trainee = (Trainee) getArguments().getSerializable("Trainee");
        traineeId = (String) getArguments().getString("TraineeUid");
        coach = (Coach) getArguments().getSerializable("Coach");

        programs = trainee.getPrograms();
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

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

            coachEditProgramAdapter.setOnItemListener(new CoachEditProgramAdapter.MyEditProgramListener() {
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
                public void onProgramCheck(int position, Boolean isChecked) {
                    programs.get(position).setCurrentProgram(isChecked);
                    mDatabase.child("trainee").child(traineeId).child("programs").setValue(programs);
                }
            });


        }

        FloatingActionButton floatingActionButton_createNewProgran = (FloatingActionButton) view.findViewById(R.id.coachEditProgram_menuItem_AddNewProgram);
        FloatingActionButton floatingActionButton_addFromBank = (FloatingActionButton) view.findViewById(R.id.coachEditProgram_menuItem_addFromBank);
        floatingActionMenu = (FloatingActionMenu) view.findViewById(R.id.coachEditProgram_famenu);
        floatingActionButton_createNewProgran.setOnClickListener(this);
        floatingActionButton_addFromBank.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coachEditProgram_menuItem_AddNewProgram:
                CreateProgramFragment createProgramFragment = new CreateProgramFragment();
                FragmentManager fragmentManager = getFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Trainee", trainee);
                bundle.putString("TraineeUid", traineeId);
                createProgramFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), createProgramFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                floatingActionMenu.close(true);
                break;
            case R.id.coachEditProgram_menuItem_addFromBank:
                CoachAddFromBankFragment coachAddFromBankFragment = new CoachAddFromBankFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("Trainee", trainee);
                bundle1.putSerializable("Coach", coach);
                bundle1.putString("TraineeUid", traineeId);
                coachAddFromBankFragment.setArguments(bundle1);
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(((ViewGroup) getView().getParent()).getId(), coachAddFromBankFragment);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
                floatingActionMenu.close(true);
                floatingActionMenu.close(true);
                break;
        }

    }
}
