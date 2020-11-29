package com.AP.Asthma_Pal;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class RegistrationActivity extends AppCompatActivity {


    //Initialize key variables
    private EditText Email, Password, ConPassword, FirstName, LastName, Phone;

    private Button Register;
    private TextView Return;
    private FirebaseAuth UserAuth;
    private String filename = "PersonalInformation";
    private Spinner spinner;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.Asthma_Pal.sharedPreferences";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File file = new File(getApplicationContext().getFilesDir(), filename);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Assign database
        UserAuth = FirebaseAuth.getInstance();

        //Assign key variables to views within activity
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        Password = findViewById(R.id.etPassword);
        ConPassword = findViewById(R.id.etConfirmPassword);
        Email = findViewById(R.id.etEmail);
        Register = findViewById(R.id.btnRegister);
        Return = findViewById(R.id.tvReturntoMenu);
        spinner = findViewById(R.id.countrySelection);
        FirstName = findViewById(R.id.etFirstName);
        LastName = findViewById(R.id.etLastName);
        Phone = findViewById(R.id.etPhone);


        //Listen for a click on the register button
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Make sure that fields are all utilized and passwords match by calling validate
                if(validate()){
                    //Remove spaces from information
                   String userPass = Password.getText().toString().trim();
                   String userEmail = Email.getText().toString().trim();
                   String userName = FirstName.getText().toString().trim();
                   String userLast = LastName.getText().toString().trim();
                   int userPhone = Integer.parseInt(Phone.getText().toString().trim());
                   String country = spinner.getSelectedItem().toString();
                   mPreferences.edit().putString("com.example.Asthma_Pal.FirstName", userName).apply();
                   mPreferences.edit().putString("com.example.Asthma_Pal.LastName", userLast).apply();
                   mPreferences.edit().putString("com.example.Asthma_Pal.Email", userEmail).apply();
                   mPreferences.edit().putString("com.example.Asthma_Pal.Country", country).apply();
                   mPreferences.edit().putInt("com.example.Asthma_Pal.Phone", userPhone);

                   //Add new user to the database
                   UserAuth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()) {
                               Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                               startActivity(intent);
                               RegistrationActivity.this.finish();
                           }
                           else
                               Toast.makeText(RegistrationActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                       }
                   });
                }
            }
        });

        //On click listener on text to allow the user to return to login
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                LoginActivity.RA.finish();
                startActivity(intent);
                RegistrationActivity.this.finish();
            }
        });
    }

    private boolean validate(){
        boolean valid = false;

        String pass = Password.getText().toString();
        String conPass = ConPassword.getText().toString();
        String email = Email.getText().toString();
        String userName = FirstName.getText().toString().trim();
        String userLast = LastName.getText().toString().trim();
        String country = spinner.getSelectedItem().toString();

        if(email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Please enter an Email and Password", Toast.LENGTH_SHORT).show();
        }
        else if(conPass.isEmpty()){
            Toast.makeText(this, "Please enter Confirmed Password", Toast.LENGTH_SHORT).show();
        }
        else if(!pass.equals(conPass)){
            Toast.makeText(this, "Password and Confirmed Password do not match", Toast.LENGTH_SHORT).show();
        }
        else if(userName.isEmpty()){
            Toast.makeText(this, "Please enter a first name", Toast.LENGTH_SHORT).show();
        }
        else if(userLast.isEmpty()){
            Toast.makeText(this, "Please enter a last name", Toast.LENGTH_SHORT).show();
        }
        else if(country.equals("Country")){
            Toast.makeText(this, "Please select a valid country", Toast.LENGTH_SHORT).show();
        }
        else
            valid = true;

        return valid;
    }
}