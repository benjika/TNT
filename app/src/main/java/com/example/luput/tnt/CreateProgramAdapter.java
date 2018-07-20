package com.example.luput.tnt;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CreateProgramAdapter extends RecyclerView.Adapter<CreateProgramAdapter.ViewHolder> {

    private List<ExerciseDrill> listOfDrills;
    private Context context;

    public CreateProgramAdapter(List<ExerciseDrill> listOfDrills, Context context) {
        this.listOfDrills = listOfDrills;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drill_holder, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExerciseDrill exerciseDrill = listOfDrills.get(position);
        holder.nameOfDrill.setText(exerciseDrill.getNameOfExercise());
        holder.numOfSets.setText(exerciseDrill.getNumberOfSets()+"");
        holder.weightInKG.setText(exerciseDrill.getWeightInKg() + "");
        holder.numOfReps.setText(exerciseDrill.getNumberOfRepeat() + "");
        holder.restTime.setText(exerciseDrill.getNumberOfRestInSeconds() + "");
        holder.drillDescription.setText(exerciseDrill.getDescription());
        holder.linkToVideo.setText(exerciseDrill.getLinkToVideo());
    }

    @Override
    public int getItemCount() {
        return listOfDrills.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameOfDrill;
        public TextView numOfSets;
        public TextView weightInKG;
        public TextView numOfReps;
        public TextView restTime;
        public TextView drillDescription;
        public TextView linkToVideo;

        public ViewHolder(View itemView) {
            super(itemView);
            nameOfDrill = (TextView) itemView.findViewById(R.id.drillHolder_drillName);
            numOfSets = (TextView) itemView.findViewById(R.id.drillHolder_NumberOfSets);
            weightInKG = (TextView) itemView.findViewById(R.id.drillHolder_weightInKg);
            numOfReps = (TextView) itemView.findViewById(R.id.drillHolder_NumberOfRepeats);
            restTime = (TextView) itemView.findViewById(R.id.drillHolder_RestInSeconds);
            drillDescription = (TextView) itemView.findViewById(R.id.drillHolder_drillDescription);
            linkToVideo = (TextView) itemView.findViewById(R.id.drillHolder_LinkToDrillMovie);

        }
    }
}
