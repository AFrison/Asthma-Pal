package com.AP.Asthma_Pal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EntryAdapter extends ArrayAdapter<JournalEntry> {

    private static final String TAG = "Adapter";
    private Context mContext;
    private int mResource;

    public EntryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<JournalEntry> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String Date = getItem(position).getDate();
        String Cough = getItem(position).getCough();
        String Wheeze = getItem(position).getWheeze();
        String Chest = getItem(position).getChest();
        String Sleep = getItem(position).getSleep();
        String Exercise = getItem(position).getExcercise();
        String Meds = getItem(position).getMeds();

        JournalEntry entry = new JournalEntry(Date, Cough, Wheeze, Chest, Sleep, Exercise,Meds);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView Tdate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView Tcough = (TextView) convertView.findViewById(R.id.tvCoughing);
        TextView Twheeze =(TextView) convertView.findViewById(R.id.tvWheezing);
        TextView Tchest = (TextView) convertView.findViewById(R.id.tvTightChested);
        TextView Tsleep = (TextView) convertView.findViewById(R.id.tvSleepIssues);
        TextView Texercise = (TextView) convertView.findViewById(R.id.tvStoppedExercising);
        TextView Tmeds = (TextView) convertView.findViewById(R.id.tvUsedMeds);

        Log.d(TAG, entry.print());

        Tdate.setText(entry.getDate());
        Tcough.setText(entry.getCough());
        Twheeze.setText(entry.getWheeze());
        Tsleep.setText(entry.getSleep());
        Tchest.setText(entry.getChest());
        Texercise.setText(entry.getExcercise());
        Tmeds.setText(entry.getMeds());

        return convertView;
    }
}
