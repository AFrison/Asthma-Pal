package com.AP.Asthma_Pal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DailyEntryActivity extends AppCompatActivity {

    private Button chart, test, home, entries;
    public static Activity DA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_entry);


        chart =  findViewById(R.id.btnGraphs);
        test = findViewById(R.id.btnJournalEntry);
        home = findViewById(R.id.btnBack);
        entries = findViewById(R.id.btnJournalHistory);
        DA = this;


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.active) {
                    Intent intent = new Intent(DailyEntryActivity.this, DailySymptomsActivity.class);
                    startActivity(intent);
                    MainActivity.MA.finish();
                }
                else{
                    Intent intent = new Intent(DailyEntryActivity.this, DailySymptomsActivity.class);
                    startActivity(intent);
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.active) {
                    Intent intent = new Intent(DailyEntryActivity.this, MainActivity.class);
                    startActivity(intent);
                    MainActivity.MA.finish();
                    finish();
                }else {
                    Intent intent = new Intent(DailyEntryActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.active) {
                    Intent intent = new Intent(DailyEntryActivity.this, ChartActivity.class);
                    startActivity(intent);
                    MainActivity.MA.finish();
                }
                else{
                    Intent intent = new Intent(DailyEntryActivity.this, ChartActivity.class);
                    startActivity(intent);
                }
            }
        });

        entries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.active) {
                    Intent intent = new Intent(DailyEntryActivity.this, JournalHistoryActivity.class);
                    startActivity(intent);
                    MainActivity.MA.finish();
                }
                else{
                    Intent intent = new Intent(DailyEntryActivity.this, JournalHistoryActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}