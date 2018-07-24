package com.example.luput.tnt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>{

    MyProgramListener Listener;
    List<TrainingProgram> Programs;


    @Override
    public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_program_cell,parent,false);
        ProgramViewHolder programViewHolder = new ProgramViewHolder(view);
        return programViewHolder;
    }

    @Override
    public void onBindViewHolder(ProgramViewHolder holder, int position) {
        TrainingProgram program = Programs.get(position);
        holder.ProgramName.setText(program.getNameOfTheProgram());
        holder.CurrentProgram.setChecked(program.isCurrentProgram());
    }

    @Override
    public int getItemCount() {
        return Programs.size();
    }


    interface MyProgramListener{
        void onProgramClick(int position, View view);
    }


    public void setListener(MyProgramListener listener) {
        Listener = listener;
    }

    public ProgramAdapter(List<TrainingProgram> programs) {
        Programs = programs;
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {

        TextView ProgramName;
        CheckBox CurrentProgram;

        public ProgramViewHolder(View itemView){
            super(itemView);

            ProgramName = (TextView) itemView.findViewById(R.id.training_program_header);
            CurrentProgram = (CheckBox) itemView.findViewById(R.id.current_program);
            CurrentProgram.setClickable(false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Listener.onProgramClick(getAdapterPosition(),view);
                }
            });
        }

    }


}
