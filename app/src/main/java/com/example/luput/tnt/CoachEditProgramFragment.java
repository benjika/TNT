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
    Trainee trainee;
    String traineeId;


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
                //Toast.makeText(context, "added by email", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "added by phone number", Toast.LENGTH_SHORT).show();
                //inflatePhoneDialog();
                floatingActionMenu.close(true);
                break;
        }

    }
}
