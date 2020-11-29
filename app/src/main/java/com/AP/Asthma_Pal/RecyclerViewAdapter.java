package com.AP.Asthma_Pal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    public static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mDate = new ArrayList<>();
    private ArrayList<String> mCough = new ArrayList<>();
    private ArrayList<String> mWheeze = new ArrayList<>();
    private ArrayList<String> mChest = new ArrayList<>();
    private ArrayList<String> mSleep = new ArrayList<>();
    private ArrayList<String> mExercise = new ArrayList<>();
    private ArrayList<String> mMeds = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mDate, ArrayList<String> mCough, ArrayList<String> mWheeze, ArrayList<String> mChest, ArrayList<String> mSleep, ArrayList<String> mExercise, ArrayList<String> mMeds, Context mContext) {
        this.mDate = mDate;
        this.mCough = mCough;
        this.mWheeze = mWheeze;
        this.mChest = mChest;
        this.mSleep = mSleep;
        this.mExercise = mExercise;
        this.mMeds = mMeds;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindMessage : called.");

        holder.Tchest.setText(mChest.get(position));
        holder.Tcough.setText(mCough.get(position));
        holder.Tdate.setText(mDate.get(position));
        holder.Texercise.setText(mExercise.get(position));
        holder.Tmeds.setText(mMeds.get(position));
        holder.Tsleep.setText(mSleep.get(position));
        holder.Twheeze.setText(mWheeze.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Tdate, Tcough, Twheeze, Tchest, Tsleep, Texercise, Tmeds;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            Tdate = itemView.findViewById(R.id.tvDate);
            Tcough =itemView.findViewById(R.id.tvCoughing);
            Twheeze = itemView.findViewById(R.id.tvWheezing);
            Tchest = itemView.findViewById(R.id.tvTightChested);
            Tsleep = itemView.findViewById(R.id.tvSleepIssues);
            Texercise = itemView.findViewById(R.id.tvStoppedExercising);
            Tmeds = itemView.findViewById(R.id.tvUsedMeds);
        }
    }
}
