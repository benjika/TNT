package com.example.luput.tnt;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CoachBankProgramsAdapter extends RecyclerView.Adapter<CoachBankProgramsAdapter.ViewHolder> {

    private static final String TAG = "CoachBankProgramsAdap";

    private List<TrainingProgram> programs = new ArrayList<>();
    private Context context;
    private CoachBankProgramsAdapter.MyCoachBankProgramsListener myCoachBankProgramsListener;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    public CoachBankProgramsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_program_cell, parent, false);
        CoachBankProgramsAdapter.ViewHolder viewHolder = new CoachBankProgramsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        TrainingProgram trainingProgram = programs.get(position);
        holder.programTitleTV.setText(trainingProgram.getNameOfTheProgram());
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView programTitleTV;
        CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            programTitleTV = (TextView) itemView.findViewById(R.id.trainingProgramBank_header);
            parentLayout = (CardView) itemView.findViewById(R.id.programCellBank_Card_LinearLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myCoachBankProgramsListener.onProgramClick(getAdapterPosition(), view);
                }
            });

        }
    }

    interface MyCoachBankProgramsListener {
        void onProgramClick(int position, View view);
    }

    public void setOnItemListener(CoachBankProgramsAdapter.MyCoachBankProgramsListener listener) {
        myCoachBankProgramsListener = listener;
    }


    public CoachBankProgramsAdapter(List<TrainingProgram> programsNew) {
        this.programs = programsNew;
    }
}
