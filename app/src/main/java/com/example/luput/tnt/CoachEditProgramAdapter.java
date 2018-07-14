package com.example.luput.tnt;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CoachEditProgramAdapter extends RecyclerView.Adapter<CoachEditProgramAdapter.ViewHolder> {

    private static final String TAG = "CoachEditProgramAdapter";

    private List<TrainingProgram> programs = new ArrayList<>();
    private Context context;
    private MyEditProgramListener myEditProgramListener;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_program_cell, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        TrainingProgram trainingProgram = programs.get(position);
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

        public ViewHolder(View itemView) {
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
        }
    }

    interface MyEditProgramListener {
        void onProgramClick(int position, View view);
    }

    public void setOnitemListener(MyEditProgramListener listener) {
        myEditProgramListener = listener;
    }

    public CoachEditProgramAdapter(List<TrainingProgram> programsNew) {
        this.programs = programsNew;
    }

}
