package com.example.luput.tnt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CoachAdapter extends RecyclerView.Adapter<CoachAdapter.ViewHolder> {

    private static final String TAG = "CoachAdapter";

    private ArrayList<Trainee> trainees = new ArrayList<>();
    private Context mContext;
    private MyClickListener myClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullNameTV;
        TextView emailTV;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            fullNameTV = itemView.findViewById(R.id.coach_item_firstName);
            emailTV = itemView.findViewById(R.id.coach_item_email);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    myClickListener.onItemClick(getAdapterPosition(), v);
                }
            });
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
        String fullName = trainee.getFirstName() + " " + trainee.getLastName();
        holder.fullNameTV.setText(fullName);
        holder.emailTV.setText(trainee.getEmailAddress());
        /*holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on " + trainee.getFirstName() + " "
                        + trainee.getLastName());
                Toast.makeText(mContext, trainee.getFirstName() + " "
                        + trainee.getLastName(), Toast.LENGTH_SHORT).show();


            }
        });*/

    }

    @Override
    public int getItemCount() {
        return trainees.size();
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(MyClickListener clickListener) {
        myClickListener = clickListener;
    }
}
