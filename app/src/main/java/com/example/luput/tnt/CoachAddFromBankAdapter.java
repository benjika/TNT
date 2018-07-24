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

public class CoachAddFromBankAdapter extends RecyclerView.Adapter<CoachAddFromBankAdapter.ViewHolder> {
    private List<TrainingProgram> programs = new ArrayList<>();
    private Context context;
    private CoachAddFromBankAdapter.MyAddFromBankListener myAddFromBankListener;

    private static final String TAG = "CoachAddFromBankAdapter";

    @Override
    public CoachAddFromBankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_program_cell_coach_bank, parent, false);
        CoachAddFromBankAdapter.ViewHolder viewHolder = new CoachAddFromBankAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CoachAddFromBankAdapter.ViewHolder holder, int position) {
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

            programTitleTV = (TextView) itemView.findViewById(R.id.trainingBank_program_header);
            parentLayout = (CardView) itemView.findViewById(R.id.programCellBank_Card_LinearLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myAddFromBankListener.onProgramClick(getAdapterPosition(), view);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    myAddFromBankListener.onProgramLongClick(getAdapterPosition(), view);
                    return true;
                }
            });

        }
    }

    interface MyAddFromBankListener {
        void onProgramClick(int position, View view);

        void onProgramLongClick(int position, View view);

    }

    public void setOnitemListener(MyAddFromBankListener listener) {
        myAddFromBankListener = listener;
    }

    public CoachAddFromBankAdapter(List<TrainingProgram> programsNew) {
        this.programs = programsNew;
    }
}
