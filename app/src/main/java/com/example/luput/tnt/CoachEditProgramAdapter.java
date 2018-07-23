package com.example.luput.tnt;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class CoachEditProgramAdapter extends RecyclerView.Adapter<CoachEditProgramAdapter.ViewHolder> {

    private static final String TAG = "CoachEditProgramAdapter";

    private List<TrainingProgram> programs = new ArrayList<>();
    private Context context;
    private MyEditProgramListener myEditProgramListener;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_program_cell, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        final TrainingProgram trainingProgram = programs.get(position);
        holder.programTitleTV.setText(trainingProgram.getNameOfTheProgram());
        holder.currentProgramCB.setChecked(trainingProgram.isCurrentProgram());
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView programTitleTV;
        CheckBox currentProgramCB;
        CardView parentLayout;

        public ViewHolder(final View itemView) {
            super(itemView);

            programTitleTV = (TextView) itemView.findViewById(R.id.training_program_header);
            currentProgramCB = (CheckBox) itemView.findViewById(R.id.current_program);
            parentLayout = (CardView) itemView.findViewById(R.id.programCell_Card_LinearLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myEditProgramListener.onProgramClick(getAdapterPosition(), view);
                }
            });
            currentProgramCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    myEditProgramListener.onProgramCheck(getAdapterPosition(), isChecked);
                }
            });
        }
    }

    interface MyEditProgramListener {
        void onProgramClick(int position, View view);

        void onProgramCheck(int position, Boolean isChecked);
    }

    public void setOnItemListener(MyEditProgramListener listener) {
        myEditProgramListener = listener;
    }


    public CoachEditProgramAdapter(List<TrainingProgram> programsNew) {
        this.programs = programsNew;
    }

}
