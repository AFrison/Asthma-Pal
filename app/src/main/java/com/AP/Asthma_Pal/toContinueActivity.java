package com.AP.Asthma_Pal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class toContinueActivity extends AppCompatActivity {

    Button toSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_continue);

        toSetting = findViewById(R.id.btnToSettings);

        toSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(toContinueActivity.this, SettingsActivity.class);
            }
        });
    }
}