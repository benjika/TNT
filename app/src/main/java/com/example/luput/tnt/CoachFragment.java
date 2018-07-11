package com.example.luput.tnt;

import android.content.Context;
import android.net.Uri;
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

import static java.util.Collections.sort;


public class CoachFragment extends Fragment {

    private static String TAG = "CoachFaragment";

    private OnFragmentInteractionListener mListener;

    private ArrayList<String> firstNamesALIst = new ArrayList<>();
    private ArrayList<String> lastNamesALIst = new ArrayList<>();
    private ArrayList<String> emailsALIst = new ArrayList<>();
    List<Trainee> traineeList;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserID = firebaseUser.getUid();
        traineeList = traineesOfThisCoach();
        initNames();

    }

    private void initNames() {
        Log.d(TAG, "initnames: Started");

        //TODO add names from Firebase
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d(TAG, "initnames: Started");


        return inflater.inflate(R.layout.fragment_coach, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.coach_recyclerView);
        //CoachAdapter coachAdapter =
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private List<Trainee> traineesOfThisCoach() {
        final List<Trainee> listToReturn = new ArrayList<>();
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

    public int numOfTrainees() {
        return traineeList.size();
    }

    public List<Trainee> getTraineeList() {
        return traineeList;
    }
}
