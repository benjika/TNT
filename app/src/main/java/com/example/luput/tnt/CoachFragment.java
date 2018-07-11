package com.example.luput.tnt;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;
import static java.util.Collections.sort;


public class CoachFragment extends Fragment {

    private static String TAG = "CoachFaragment";

    private OnFragmentInteractionListener mListener;

    ArrayList<Trainee> traineeList = new ArrayList<>();

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String UserID;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d(TAG, "initnames: Started");
        View view = inflater.inflate(R.layout.fragment_coach, container, false);
        UserID = firebaseUser.getUid();
        traineeList = traineesOfThisCoach();

        //Init Adapter
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.coach_recyclerView);
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
                fragmentTransaction.replace(R.id.fragment_container, coachEditProgramFragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private ArrayList<Trainee> traineesOfThisCoach() {
        final ArrayList<Trainee> listToReturn = new ArrayList<>();
        mDatabase.child("coach").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Coach coach = dataSnapshot.child(UserID).getValue(Coach.class);
                List<String> listOfTraineesUID = new ArrayList<>();
                listOfTraineesUID = coach.getTrainees();
                for (String Uid : listOfTraineesUID) {
                    Trainee traineeToAdd = dataSnapshot.child(Uid).getValue(Trainee.class);
                    listToReturn.add(traineeToAdd);
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
}
