package com.AP.Asthma_Pal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    private String filename = "PersonalInformation";
    private TextView textViewCountry, textViewFirstName, textViewLastName, textViewPhone, textViewEmail;
    private SharedPreferences mPreferences;
    private Button physinfo;
    private String sharedPrefFile = "com.example.Asthma_Pal.sharedPreferences";
    private String defVal = "NULL";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        int phonenum = 0;

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        physinfo = findViewById(R.id.btnPhysInfo);
        textViewCountry = findViewById(R.id.txtCountry);
        textViewFirstName = findViewById(R.id.txtFirstName);
        textViewLastName = findViewById(R.id.txtLastName);
        textViewPhone = findViewById(R.id.txtPhoneNumber);
        textViewEmail = findViewById(R.id.txtEmail);


        phonenum = mPreferences.getInt("com.example.Asthma_Pal.phone", 0);
        textViewCountry.setText(mPreferences.getString("com.example.Asthma_Pal.Country", defVal));
        textViewFirstName.setText(mPreferences.getString("com.example.Asthma_Pal.FirstName", defVal));
        textViewLastName.setText(mPreferences.getString("com.example.Asthma_Pal.LastName", defVal));
        textViewPhone.setText(phonenum);
        textViewEmail.setText(mPreferences.getString("com.example.Asthma_Pal.Email", defVal));
/*
        physinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent physInformation = new Intent(InformationActivity.this, PhysicianInformation.class);
                startActivity(physInformation);
            }
        });
*/
    }
}