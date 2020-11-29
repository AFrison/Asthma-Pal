package com.AP.Asthma_Pal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhysicianInformation extends AppCompatActivity {

    private Button physregistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_information);

        physregistration = findViewById(R.id.btnRegister);

        physregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent physregister = new Intent(PhysicianInformation.this, PhysicianRegistration.class);
                startActivity(physregister);
            }
        });
    }
}