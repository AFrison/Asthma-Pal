package com.AP.Asthma_Pal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class JournalHistoryActivity extends AppCompatActivity {


    private ArrayList<String> mDate = new ArrayList<>();
    private ArrayList<String> mCough = new ArrayList<>();
    private ArrayList<String> mWheeze = new ArrayList<>();
    private ArrayList<String> mChest = new ArrayList<>();
    private ArrayList<String> mSleep = new ArrayList<>();
    private ArrayList<String> mExercise = new ArrayList<>();
    private ArrayList<String> mMeds = new ArrayList<>();
    DatabaseHelper db;
    private Button backDE, newEntry;
    public boolean EHactive;
    private static final String TAG = "JournalHist";
    private ListView list;
    private ArrayList<JournalEntry> logs = new ArrayList<JournalEntry>();


    @Override
    protected void onStart() {
        super.onStart();
        EHactive = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        EHactive = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_history);
        backDE =findViewById(R.id.btnBacktoEnt);
        newEntry = findViewById(R.id.btnNewEntry);
        list = findViewById(R.id.listView);

        db = new DatabaseHelper(this);
        Cursor data = db.getListContents();


        if(data.getCount() == 0){
            Toast.makeText(this, "Database is empty", Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d(TAG, "Getting Data");
            while (data.moveToNext()){
                mDate.add(data.getString(0));
                mCough.add(data.getString(1));
                mWheeze.add(data.getString(2));
                mChest.add(data.getString(3));
                mSleep.add(data.getString(4));
                mExercise.add(data.getString(5));
                mMeds.add(data.getString(6));
            }

            for(int i = 0; i < mDate.size(); i++ ){
                JournalEntry entry = new JournalEntry(mDate.get(i),mCough.get(i),mWheeze.get(i),mChest.get(i),mSleep.get(i), mExercise.get(i), mMeds.get(i));
                logs.add(entry);
            }

            populateList();
        }


        //initRecyclerView();

           newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JournalHistoryActivity.this, DailySymptomsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        backDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JournalHistoryActivity.this, DailyEntryActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void populateList(){
        EntryAdapter adapter = new EntryAdapter(this, R.layout.layout_listitem, logs);
        list.setAdapter(adapter);
        }
    }
/*
    private void initRecyclerView(){
        Log.d(TAG, "Entering Recycler view");
        RecyclerView recyclerView = findViewById(R.id.recylerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mDate, mCough, mWheeze, mChest, mSleep, mExercise, mMeds, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }*/
//}