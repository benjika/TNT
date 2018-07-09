package com.example.luput.tnt;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class FullProgramAdapter extends RecyclerView.Adapter<FullProgramAdapter.DrillViewHolder> {

    List<ExerciseDrill> Drills;

    @Override
    public void onBindViewHolder(DrillViewHolder holder, int position) {
        final ExerciseDrill drill = Drills.get(position);
        holder.DrillName.setText(drill.getNameOfExercise());
        holder.NumberOfSets.setText(String.valueOf(drill.getNumberOfSets()));
        holder.RestInSeconds.setText(String.valueOf(drill.getNumberOfRestInSeconds()));
        holder.WeightInKG.setText(String.valueOf(drill.getWeightInKg()));
        holder._Description.setText(drill.getDescription());
        holder.LinkToMovie.setMovementMethod(LinkMovementMethod.getInstance());
        holder.LinkToMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(drill.getLinkToVideo()));
                ContextCompat.startActivity(view.getContext(),browserIntent,null);
            }
        });
    }

    public FullProgramAdapter(List<ExerciseDrill> drills){
        Drills = drills;
    }

    @Override
    public DrillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_driil,parent,false);
        DrillViewHolder drillViewHolder = new DrillViewHolder(view);
        return drillViewHolder;
    }

    @Override
    public int getItemCount() {
        return Drills.size();
    }

    public class  DrillViewHolder extends RecyclerView.ViewHolder {

      TextView DrillName;
      TextView NumberOfSets;
      TextView WeightInKG;
      TextView RestInSeconds;
      TextView _Description;
      TextView LinkToMovie;

      public DrillViewHolder(View itemView) {
          super(itemView);

          DrillName = itemView.findViewById(R.id.drill_name);
          NumberOfSets = itemView.findViewById(R.id.Number_of_sets);
          WeightInKG = itemView.findViewById(R.id.weight_in_kg);
          RestInSeconds = itemView.findViewById(R.id.Rest_In_seconds);
          _Description = itemView.findViewById(R.id.drill_description);
          LinkToMovie = itemView.findViewById(R.id.Link_To_Drill_Movie);
      }
  }

}
