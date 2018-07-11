package com.example.luput.tnt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CoachAdapter extends RecyclerView.Adapter<CoachAdapter.ViewHolder> {

    private static final String TAG = "CoachAdapter";

    private ArrayList<Trainee> trainees = new ArrayList<>();
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView firstNameTV;
        TextView lastNameTV;
        TextView emailTV;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            firstNameTV = itemView.findViewById(R.id.coach_item_firstName);
            lastNameTV = itemView.findViewById(R.id.coach_item_lastName);
            emailTV = itemView.findViewById(R.id.coach_item_email);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_items,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public CoachAdapter(Context mContext, ArrayList<Trainee> traineesAdded) {
        this.trainees = traineesAdded;
        this.mContext = mContext;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        final Trainee trainee = trainees.get(position);
        holder.firstNameTV.setText(trainee.getFirstName());
        holder.lastNameTV.setText(trainee.getLastName());
        holder.emailTV.setText(trainee.getEmailAddress());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on " + trainee.getFirstName() + " "
                        + trainee.getLastName());
                Toast.makeText(mContext, trainee.getFirstName() + " "
                        + trainee.getLastName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return trainees.size();
    }


}
